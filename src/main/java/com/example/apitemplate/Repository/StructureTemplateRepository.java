package com.example.apitemplate.Repository;

import com.example.apitemplate.Structure.StructureTemplate;
import org.springframework.stereotype.Repository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StructureTemplateRepository {
    private List<StructureTemplate> structures = new ArrayList<>();
    private int structureId = 1;
    ObjectMapper objectMapper = new ObjectMapper();

    public StructureTemplateRepository(){
        readData();
    }

    private void addStructure(StructureTemplate structureToAdd){
        try{
            structureToAdd.setId(structureId);
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

    public StructureTemplate findStructureById(int id){
        for(StructureTemplate structureTemplate: structures){
            if(id == structureTemplate.getId()){
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
}
