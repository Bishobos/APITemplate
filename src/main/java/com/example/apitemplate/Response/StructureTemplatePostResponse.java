package com.example.apitemplate.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StructureTemplatePostResponse implements ValidPostResponse {
    private LocalDateTime createdDateTime;
    private String id;

    @JsonCreator
    public StructureTemplatePostResponse(@JsonProperty("id") String id){
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

    public static Class<? extends ValidPostResponse> getResponseClass(){
        return StructureTemplatePostResponse.class;
    }

    @Override
    public String toString() {
        return "StructureTemplatePostResponse{" + "\n" +
                "createdDateTime=" + createdDateTime  + "\n" +
                ", id='" + id + '\'' +
                '}';
    }
}
