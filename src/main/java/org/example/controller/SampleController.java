package org.example.controller;

import lombok.extern.log4j.Log4j;
import org.example.domain.SampleDTO;
import org.example.domain.SampleDTOList;
import org.example.domain.TodoDTO;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@Log4j
@RequestMapping("/sample/*")
public class SampleController {

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {
        System.out.println("basic get!");
        log.info("basic get.......");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        System.out.println("basic get only get......");
        log.info("basic get only get............");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        System.out.println("ex01 " + dto);
        log.info("" + dto);
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
        System.out.println("[ex02]name: " + name + ", age: " + age);
        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
        System.out.println("[ex02List]: " + ids);
        log.info("ids: " + ids);
        return "ex02List";
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        System.out.println("[ex02Array]: " + Arrays.toString(ids));
        log.info("ids: " + ids);
        return "ex02Array";
    }

    @GetMapping("ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info("list dtos: " + list);
        System.out.println("[ex02Bean]: " + list);
        return "ex02Bean";
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        System.out.println(todo);
        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto,@ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);

        System.out.println("dto: " + dto);
        System.out.println("page: " + page);

        return "/sample/ex04";
    }

    @GetMapping("/ex06")
    public @ResponseBody SampleDTO ex06() {
        log.info("/ex06.......");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        System.out.println(dto);
        return dto;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        log.info("/ex07.........");
        String msg = "{\"name\": \"홍길동\"}";
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type","application/json;charset=UTF-8");
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public void exUpload() {
        log.info("/exUpload........");
        System.out.println("/exUpload........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        files.forEach(file -> {
            log.info("------------------------------");
            log.info("name:" + file.getOriginalFilename());
            log.info("size:" + file.getSize());
            System.out.println("--------------------");
            System.out.println("name:" + file.getOriginalFilename());
            System.out.println("size:" + file.getSize());
        });
    }
}
