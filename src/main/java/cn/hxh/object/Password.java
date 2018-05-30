package cn.hxh.object;

import cn.hxh.util.HH;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class Password {
    @JsonProperty
    byte[] where;
    @JsonProperty
    byte[] account;
    @JsonProperty
    byte[] password;
    @JsonProperty
    List<Pair> ext;

    public void setAccount(byte[] account) {
        this.account = account;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setWhere(byte[] where) {
        this.where = where;
    }

    public void addExt(byte[] key, byte[] value) {
        if (ext == null) {
            ext = new ArrayList<>();
        }
        ext.add(new Pair(key, value));
    }

    public void clean() {
        HH.eraseArray(account);
        HH.eraseArray(password);
        HH.eraseArray(where);
        for (Pair pair : ext) {
            pair.clean();
        }
    }

    static class Pair {
        @JsonProperty
        byte[] key;
        @JsonProperty
        byte[] value;

        Pair() {
            // for jackson
        }

        Pair(byte[] key, byte[] value) {
            this.key = key;
            this.value = value;
        }

        void clean() {
            HH.eraseArray(key);
            HH.eraseArray(value);
        }

        @Override
        public String toString() {
            return "\t" + new String(key) + ':' + new String(value) + '\t';
        }
    }

    @Override
    public String toString() {
        return new String(where) + ' ' + new String(account) + ' ' + new String(password) + (ext == null ? "" : '\t' + ext.toString()) + '\n';
    }
}
