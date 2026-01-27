package com.example.apitemplate.Structure;

import com.example.apitemplate.Response.StructureTemplateGetResponse;
import com.example.apitemplate.Response.StructureTemplatePostResponse;
import com.example.apitemplate.Response.ValidGetResponse;
import com.example.apitemplate.Response.ValidPostResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.core.type.TypeReference;


public class StructureTemplate implements ValidStructure {
    private boolean idExists;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id; //structure id needs to exist
    private final String text; //template attribute

    public StructureTemplate(String text) {
        this.text = text;
    }

    public StructureTemplate(String id, String text) {
        this.id = id;
        this.text = text;
        this.idExists = true;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(String id) {
        if(!idExists){
            this.id = id;
        }
        else throw new IllegalStateException("Id already exists.");

    }

    @Override
    public Class<? extends ValidGetResponse> validGetResponseType(){
        return StructureTemplateGetResponse.getResponseClass();
    } //ValidGetResponse r = structureTemplate.getGetResponseType().getDeclaredConstructor().newInstance()

    @Override
    public Class<? extends ValidPostResponse> validPostResponseType(){
        return StructureTemplatePostResponse.getResponseClass();
    }

}
