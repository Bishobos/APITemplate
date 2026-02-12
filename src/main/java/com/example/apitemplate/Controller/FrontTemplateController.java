package com.example.apitemplate.Controller;


import com.example.apitemplate.Service.StructureTemplateService;
import com.example.apitemplate.Structure.StructureTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/display")
public class FrontTemplateController {
    private final StructureTemplateService service;

    public FrontTemplateController(StructureTemplateService service){this.service = service;}

    @GetMapping
    public String defaultDisplay(Model model){
        List<StructureTemplate> structures = service.getStructures();
        model.addAttribute("structures", structures);
        return "defaultDisplay";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        StructureTemplate structureTemplate = new StructureTemplate();
        model.addAttribute("structure", structureTemplate);
        return "structure-registration-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute StructureTemplate structureTemplate){
        service.addStructure(structureTemplate);
        return "redirect:/display";
    }

    @GetMapping("/remove/{structureId}")
    public String removeStructure(@PathVariable String structureId, Model model){
        StructureTemplate structure = service.findStructureById(structureId);
        if(structure==null){
            throw new IllegalArgumentException("invalid Id");
        }
        model.addAttribute("structure", structure);
        System.out.println(structure.getId());
        return "delete-structure";
    }

    @DeleteMapping("/remove")
    public String remove(@ModelAttribute StructureTemplate structureTemplate){
        System.out.println(structureTemplate.getId());
        System.out.println(structureTemplate.getText());
        service.removeStructureById(structureTemplate);
        return"redirect:/display";
    }

}
