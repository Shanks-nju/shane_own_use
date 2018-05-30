package cn.hxh.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {

    }

    public static void writeOut(String filePath, String str) {
        BufferedWriter bw = null;
        try {
            try {
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
                bw.write(str);
            } finally {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            }
        } catch (IOException ioe) {
            log.error("Error of writing out", ioe);
        }
    }

    public static String readFile(String filePath) {
        File file = new File(filePath);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        try (FileInputStream fis = new FileInputStream(file)) {
            int tmp = fis.read(bytes);
            if (tmp != length) {
                throw new Exception();
            }
            return new String(bytes);
        } catch (Exception e) {
            log.error("Error of reading file", e);
            return null;
        }
    }

    public static byte[] readBytesFromFile(String filePath) {
        File file = new File(filePath);
        long length = file.length();
        byte[] rt = new byte[(int) length];
        try (FileInputStream fis = new FileInputStream(file)) {
            int tmp = fis.read(rt);
            if (tmp != length) {
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("Error of reading bytes", e);
        }
        return rt;
    }

    public static String[] readLines(String filePath) {
        BufferedReader br = null;
        List<String> lists = new ArrayList<>();
        try {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
                String tmp = br.readLine();
                while (tmp != null) {
                    lists.add(tmp);
                    tmp = br.readLine();
                }
                return lists.toArray(new String[0]);
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } catch (IOException ioe) {
            log.error("Error of reading lines", ioe);
            return null;
        }
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (!file.delete()) {
                log.error("Error of deleting file");
            }
        }
    }

    public static void deleteDirRecursively(String dir) {
        if (!FileSystemUtils.deleteRecursively(new File(dir))) {
            log.error("Error of deleting directory");
        }
    }
}
