package cn.hxh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @GetMapping(value = "/")
    public String home() {
        return html();
    }

    String[] hrefs = new String[]{"diary", "main", "resources", "swagger","anyone/owner.pdf"};
    String[] texts = new String[]{"Diary", "Request Page", "Resources", "Swagger Page","Owner"};

    String html() {
        String top = "<!DOCTYPE html><html><head><title>home</title><meta charset=\"UTF-8\"><meta name=\"content-type\" content=\"text/html\"></head>";
        String h1 = "<h1>Services List</h1><body><ul>";
        String end = "</ul></body><html>";
        return top + h1 + body() + end;
    }

    String body() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hrefs.length; i++) {
            sb.append(href(hrefs[i], texts[i]));
        }
        return sb.toString();
    }

    String href(String path, String text) {
        return "<li><a style=\"text-align: center\" href=\"/" + path + "\">" + text + "</a></li>";
    }
}
