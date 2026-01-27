package com.example.apitemplate.Controller;

import com.example.apitemplate.Response.StructureTemplateGetResponse;
import com.example.apitemplate.Response.StructureTemplatePostResponse;
import com.example.apitemplate.Response.ValidGetResponse;
import com.example.apitemplate.Response.*;
import com.example.apitemplate.Service.StructureTemplateService;
import com.example.apitemplate.Structure.StructureTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/StructureTemplate")
public class StructureTemplateController {
    private StructureTemplateService service;

    public StructureTemplateController(StructureTemplateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ValidGetResponse> getStructures(){
        return new ResponseEntity<>(new StructureTemplateGetResponse(service.getStructures()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ValidGetResponse> getStructureById(@PathVariable String id){
        List<StructureTemplate> structureTemplateList = new ArrayList<>();
        structureTemplateList.add(service.findStructureById(id));
        if(!structureTemplateList.isEmpty()){
            return new ResponseEntity<>(new StructureTemplateGetResponse(structureTemplateList), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StructureTemplateGetResponse(structureTemplateList), HttpStatus.NOT_FOUND);
    }
    @PostMapping("/write")
    public ResponseEntity<ValidPostResponse> createStructureTemplate(@RequestBody StructureTemplate structureTemplate){
        String addedId = service.addStructure(structureTemplate);
        return new ResponseEntity<>(new StructureTemplatePostResponse(addedId), HttpStatus.CREATED);
    }


}
