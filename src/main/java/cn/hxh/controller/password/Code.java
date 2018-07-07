package cn.hxh.controller.password;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Code {
    @JsonProperty(value = "code")
    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }
}
