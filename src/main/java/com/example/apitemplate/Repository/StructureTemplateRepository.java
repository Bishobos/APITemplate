package com.example.apitemplate.Repository;

import com.example.apitemplate.Structure.StructureTemplate;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.stereotype.Repository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

@Repository
public class StructureTemplateRepository {
    private List<StructureTemplate> structures = new ArrayList<>();
    private int structureId = 1;
    private List<String> existingIds = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    public StructureTemplateRepository(){
        readData();
    }
    @JsonCreator
    public StructureTemplateRepository(List<StructureTemplate> structures, List<String> newExistingIds){
        this.structures = structures;
        existingIds = newExistingIds;
    }

    public String addStructure(StructureTemplate structureToAdd){
        String newId = generateUniqueId();
        StructureTemplate structureWithId = new StructureTemplate(newId, structureToAdd.getText());
        structures.add(structureWithId);
        writeData();
        return structureWithId.getId();
    }

    public void addStructuresFromJSON(String jsonAsString){
        List<StructureTemplate> newStructures = objectMapper.readValue(jsonAsString,
                new TypeReference<List<StructureTemplate>>() {});
        for(StructureTemplate structureTemplate: newStructures){
            addStructure(structureTemplate);
        }
    }
    @JsonGetter("structures")
    public List<StructureTemplate> getStructures(){
        return structures;
    }
    @JsonGetter("existingIds")
    public List<String> getExistingIds(){return existingIds;}

    public StructureTemplate findStructureById(String id){
        for(StructureTemplate structureTemplate: structures){
            if(id.equals(structureTemplate.getId())){
                return structureTemplate;
            }
        }
        return null;
    }

    private void writeData(){
        objectMapper.writeValue(new File("src/main/java/com/example/apitemplate/Repository/repository.json"), this);
    }
    private void readData(){
        StructureTemplateRepository temp = objectMapper.readValue(new File("src/main/java/com/example/apitemplate/Repository/repository.json"), this.getClass());
        this.structures= temp.getStructures();
        this.existingIds= temp.getExistingIds();
    }

    private String generateUniqueId(){
        String newId;
        do {
            newId = UUID.randomUUID().toString().replaceAll("-", "");
        } while (existingIds.contains(newId));
        existingIds.add(newId);
        return newId;
    }
}
