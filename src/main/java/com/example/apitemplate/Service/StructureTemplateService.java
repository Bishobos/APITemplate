package com.example.apitemplate.Service;

import com.example.apitemplate.Repository.StructureTemplateRepository;
import com.example.apitemplate.Structure.StructureTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureTemplateService {
    private final StructureTemplateRepository repository;

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
        String id = repository.addStructure(structureTemplate);
        return id;
    }

    public boolean changeStructureById(StructureTemplate structureTemplate){
        return repository.changeStructureById(structureTemplate);
    }
    public boolean removeStructureById(StructureTemplate structureTemplate){
        return repository.removeStructureById(structureTemplate);
    }
    public List<String> getExistingIds(){
        return repository.getExistingIds();
    }
}
