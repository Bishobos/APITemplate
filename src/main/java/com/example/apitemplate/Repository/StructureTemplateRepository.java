package com.example.apitemplate.Repository;

import com.example.apitemplate.Structure.StructureTemplate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.stereotype.Repository;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

@Repository
public class StructureTemplateRepository {
    private List<StructureTemplate> structures = new ArrayList<>();
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
        String id;
        if(structureToAdd.getId() == null){
            id = generateUniqueId();
            StructureTemplate structureWithId = new StructureTemplate(id, structureToAdd.getText());
            structures.add(structureWithId);
            existingIds.add(id);
            writeData();
            return id;
        }
        id = structureToAdd.getId();
        structures.add(structureToAdd);
        existingIds.add(id);
        writeData();
        return id;
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
        try {
            StructureTemplateRepository temp = objectMapper.readValue(new File("src/main/java/com/example/apitemplate/Repository/repository.json"), this.getClass());
            this.structures= temp.getStructures();
            this.existingIds= temp.getExistingIds();
        } catch (JacksonException e) {
            System.out.println("Storage is empty.");
        }

    }

    private String generateUniqueId(){
        String newId;
        if(existingIds.isEmpty()){
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
        do {
            newId = UUID.randomUUID().toString().replaceAll("-", "");
        } while (existingIds.contains(newId));
        existingIds.add(newId);
        return newId;
    }

    public boolean changeStructureById(StructureTemplate newStructureTemplate){
        for (int i = 0; i < structures.size(); i++) {
            if (structures.get(i).getId().equals(newStructureTemplate.getId())) {
                structures.set(i, newStructureTemplate);
                existingIds.remove(structures.get(i).getId());
                writeData();
                return true;
            }
        }
        return false;
    }

    public boolean removeStructureById(StructureTemplate structureTemplate){
        for(StructureTemplate template: structures){
            if(template.getId().equals(structureTemplate.getId())){
                structures.remove(template);
                existingIds.remove(template.getId());
                writeData();
                return true;
            }
        }
        return false;
    }

}
