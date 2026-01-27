package com.example.apitemplate.Service;

import com.example.apitemplate.Repository.StructureTemplateRepository;
import com.example.apitemplate.Structure.StructureTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureTemplateService {
    private StructureTemplateRepository repository;

    public StructureTemplateService(StructureTemplateRepository repository) {
        this.repository = repository;
    }

    public List<StructureTemplate> getStructures(){
        return repository.getStructures();
    }

    public StructureTemplate findStructureById(String id){
        return repository.findStructureById(id);
    }

    public String addStructure(StructureTemplate structureTemplate){
        return repository.addStructure(structureTemplate);
    }
}
