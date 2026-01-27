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
    private List<String> existingIds;

    public StructureTemplateController(StructureTemplateService service) {
        this.service = service;
        this.existingIds = service.getExistingIds();
    }

    @GetMapping("/read")
    public ResponseEntity<ValidGetResponse> getStructures(){
        StructureTemplateGetResponse response = new StructureTemplateGetResponse(service.getStructures());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/read/{id}")
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
        if(structureTemplate.getId() != null && existingIds.contains(structureTemplate.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String addedId;
        StructureTemplatePostResponse response;
        addedId = service.addStructure(structureTemplate);
        response = new StructureTemplatePostResponse(addedId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/change")
    public ResponseEntity<ValidPutResponse> changeStructureTemplate(@RequestBody StructureTemplate structureTemplate){
        boolean changed = service.changeStructureById(structureTemplate);
        StructureTemplatePutResponse response = new StructureTemplatePutResponse(structureTemplate.getId());
        if(changed){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ValidDeleteResponse> deleteStructureTemplate(@RequestBody StructureTemplate structureTemplate){
        boolean changed = service.removeStructureById(structureTemplate);
        StructureTemplateDeleteResponse response = new StructureTemplateDeleteResponse(structureTemplate.getId());
        if(changed){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
