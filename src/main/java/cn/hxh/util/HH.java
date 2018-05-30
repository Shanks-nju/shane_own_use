package cn.hxh.util;

import cn.hxh.object.Password;
import cn.hxh.util.file.FileUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HH {
    private static Logger log = LoggerFactory.getLogger(HH.class);

    private HH() {

    }

    public static void eraseArray(byte[] privacy) {
        for (byte bt : privacy) {
            bt = -1;
        }
    }

    public static String getPasswordsString(String filePath, String code) {
        String content = FileUtil.readFile(filePath);
        byte[] decrypted;
        try {
            decrypted = Privacy.decrypt(content, code.getBytes());
        } catch (Exception e) {
            log.error("Code is wrong", e);
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, Password.class);
        List<Password> passwords;
        try {
            passwords = mapper.readValue(decrypted, type);
        } catch (IOException e) {
            log.error("Fail to get file of passwords", e);
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Password password : passwords) {
            sb.append(password.toString());
        }
        return sb.toString();
    }
}
