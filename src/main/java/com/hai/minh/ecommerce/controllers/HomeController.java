package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "API for home page")
public class HomeController extends AbstractController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> pdfToExcel() {
        return this.success("HELLO CCB");
    }
}
