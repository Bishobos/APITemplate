package com.example.apitemplate.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StructureTemplateDeleteResponse implements ValidDeleteResponse{
    private LocalDateTime createdDateTime;
    private String id;

    @JsonCreator
    public StructureTemplateDeleteResponse(@JsonProperty("id") String id){
        this.id = id;
        this.createdDateTime = LocalDateTime.now();
    }
    @JsonGetter
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }
    @JsonGetter
    public String getId() {
        return id;
    }

    public static Class<? extends ValidDeleteResponse> getResponseClass(){
        return StructureTemplateDeleteResponse.class;
    }

    @Override
    public String toString() {
        return "StructureTemplatePutResponse{\n" +
                "createdDateTime=" + createdDateTime +
                ", \nSuccessfully removed id='" + id + '\'' +
                '}';
    }
}
