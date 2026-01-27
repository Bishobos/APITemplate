package com.example.apitemplate.Repository;

import com.example.apitemplate.Structure.StructureTemplate;
import org.springframework.stereotype.Repository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class StructureTemplateRepository {
    private List<StructureTemplate> structures = new ArrayList<>();
    private int structureId = 1;
    private static Set<String> existingIds;
    ObjectMapper objectMapper = new ObjectMapper();

    public StructureTemplateRepository(){
        readData();
    }

    private void addStructure(StructureTemplate structureToAdd){
        try{
            structureToAdd.setId(generateUniqueId());
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
        structures.add(structureToAdd);
        structureId++;
    }

    public void addStructuresFromJSON(String jsonAsString){
        List<StructureTemplate> newStructures = objectMapper.readValue(jsonAsString,
                new TypeReference<List<StructureTemplate>>() {});
        for(StructureTemplate structureTemplate: newStructures){
            addStructure(structureTemplate);
        }
        writeData();
    }

    public List<StructureTemplate> getStructures(){
        return structures;
    }
    public static Set<String> getExistingIds(){return existingIds;}

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
        objectMapper.readValue(new File("src/main/java/com/example/apitemplate/Repository/repository.json"), this.getClass());
    }

    private String generateUniqueId(){
        String newId;
        do {
            newId = UUID.randomUUID().toString();
        } while( existingIds.contains(newId));
        return newId;
    }
}
