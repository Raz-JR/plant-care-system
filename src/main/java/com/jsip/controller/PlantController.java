package com.jsip.controller;

import com.jsip.repository.PlantRepository;
import com.jsip.service.PlantService;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;


@Controller()
public class PlantController {

    private final PlantRepository plantRepository;
    private final PlantService plantService;

    public PlantController(PlantRepository plantRepository, PlantService plantService) {
        this.plantRepository = plantRepository;
        this.plantService = plantService;
    }

    @GetMapping("/")
    public String getAllPlants(Model model) {
        model.addAttribute("plants", plantRepository.findAll());
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        plantService.saveFile(file.getInputStream());
        return "redirect:/";
    }


}
