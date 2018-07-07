package cn.hxh.controller.password;

import cn.hxh.object.Password;
import cn.hxh.storage.PasswordDataImp;
import cn.hxh.storage.interfaces.PasswordData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@RestController
public class PasswordService {

    @Autowired
    PasswordData passwordData;

    @PostMapping(value = "passwords")
    public String request(@RequestBody @Valid Code code) {
        return getPasswordsString(code.getPassword());
    }

    @PostMapping(value = "password/{id}")
    public boolean request(@RequestBody @Valid Code code, @PathVariable @NotBlank String id) {
        return passwordData.delete(id, code.getPassword());
    }

    @PostMapping(value = "password/new")
    public boolean request(@RequestBody @Valid CreateRequest body) {
        Password password = new Password();
        password.setWhere(body.getWhere().getBytes());
        password.setAccount(body.getAccount().getBytes());
        password.setPassword(body.getPassword().getBytes());
        if (body.getExt() != null)
            for (CreateRequest.Pair pair : body.getExt()) {
                password.addPair(pair.key, pair.value);
            }
        return passwordData.create(password, body.getCode());
    }

    private static String getPasswordsString(String code) {
        Map<String, Password> passwords = PasswordDataImp.getPasswords(code);
        if (passwords == null || passwords.isEmpty()) {
            return "Code is wrong.";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Password> entry : passwords.entrySet()) {
            sb.append(String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
        }
        return sb.toString();
    }
}
