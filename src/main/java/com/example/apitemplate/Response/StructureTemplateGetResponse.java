package com.example.apitemplate.Response;

import com.example.apitemplate.Structure.StructureTemplate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.util.ObjectUtils;

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
        StringBuilder stringBuilder = new StringBuilder();
        for (StructureTemplate structureTemplate: responseStructures){
            stringBuilder.append(structureTemplate.toString());
            stringBuilder.append("\n");
        }
        return "StructureTemplateGetResponse{\n" +
                "createdDateTime=" + createdDateTime +
                ", \nresponseStructures=\n" + stringBuilder +
                '}';
    }
}
