package com.franquisias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SwaggerRedirectController {

    @GetMapping("/")
    public RedirectView redirectRootToSwagger() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
