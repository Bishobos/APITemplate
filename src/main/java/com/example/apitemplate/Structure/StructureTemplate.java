package com.example.apitemplate.Structure;

import com.example.apitemplate.Response.StructureTemplateGetResponse;
import com.example.apitemplate.Response.StructureTemplatePostResponse;
import com.example.apitemplate.Response.ValidGetResponse;
import com.example.apitemplate.Response.ValidPostResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.core.type.TypeReference;


public class StructureTemplate implements ValidStructure {
    //private boolean idExists;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id; //structure id needs to exist
    private String text; //template attribute

    @JsonCreator
    public StructureTemplate(@JsonProperty("text") String text) {this.text = text;}

    public StructureTemplate(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text){this.text = text;}

    public void assignId(String id) {
        if(this.id!= null){
            this.id = id;
        }
    }

    @Override
    public Class<? extends ValidGetResponse> validGetResponseType(){
        return StructureTemplateGetResponse.getResponseClass();
    } //ValidGetResponse r = structureTemplate.getGetResponseType().getDeclaredConstructor().newInstance()

    @Override
    public Class<? extends ValidPostResponse> validPostResponseType(){
        return StructureTemplatePostResponse.getResponseClass();
    }

    @Override
    public String toString() {
        return "StructureTemplate{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
