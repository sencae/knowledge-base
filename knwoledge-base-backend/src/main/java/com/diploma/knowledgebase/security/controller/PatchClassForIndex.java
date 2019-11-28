package com.diploma.knowledgebase.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatchClassForIndex {

    @RequestMapping(value = "/**/{path:[^.]*}")
    public String redirect() {
        return "forward:/index.html";
    }

}
