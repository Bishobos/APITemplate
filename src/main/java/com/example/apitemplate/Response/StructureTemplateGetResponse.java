package com.example.apitemplate.Response;

import com.example.apitemplate.Structure.StructureTemplate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.util.List;

public class StructureTemplateGetResponse implements ValidGetResponse {
    private LocalDateTime createdDateTime;
    private List<StructureTemplate> responseStructures;

    @JsonCreator
    public StructureTemplateGetResponse(List<StructureTemplate> responseStructures){
        this.responseStructures = responseStructures;
        this.createdDateTime = LocalDateTime.now();
    }
    @JsonGetter("createdDateTime")
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }
    @JsonGetter("responseStructures")
    public List<StructureTemplate> getResponseStructures() {
        return responseStructures;
    }

    public static Class<? extends ValidGetResponse> getResponseClass(){
        return StructureTemplateGetResponse.class;
    }


    @Override
    public String toString() {
        return "StructureTemplateGetResponse{" +
                "createdDateTime=" + createdDateTime +
                ", responseStructures=" + responseStructures +
                '}';
    }
}
