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

    @GetMapping("/edit/{structureId}")
    public String editStructure(@PathVariable String structureId, Model model){
        StructureTemplate structure = service.findStructureById(structureId);
        if(structure==null){
            throw new IllegalArgumentException("invalid Id");
        }
        model.addAttribute("structure", structure);
        return "edit-structure-form";
    }

    @PutMapping("/edit/{structureId}")
    public String edit(@ModelAttribute StructureTemplate structureTemplate, @PathVariable String structureId){
        StructureTemplate newStructure = new StructureTemplate(structureId, structureTemplate.getText());
        service.changeStructureById(newStructure);
        return "redirect:/display";
    }

    @GetMapping("/remove/{structureId}")
    public String removeStructure(@PathVariable String structureId, Model model){
        StructureTemplate structure = service.findStructureById(structureId);
        if(structure==null){
            throw new IllegalArgumentException("invalid Id");
        }
        model.addAttribute("structure", structure);
        return "delete-structure";
    }

    @DeleteMapping("/remove/{structureId}")
    public String remove(@PathVariable String structureId){
        service.removeStructureById(structureId);
        return"redirect:/display";
    }

}
