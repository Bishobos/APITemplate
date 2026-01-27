package com.example.apitemplate.Response;

import java.time.LocalDateTime;

public class StructureTemplatePostResponse implements ValidPostResponse {
    private LocalDateTime createdDateTime;
    private String id;

    public StructureTemplatePostResponse(String id){
        this.id = id;
        this.createdDateTime = LocalDateTime.now();
    }

    public static Class<? extends ValidPostResponse> getResponseClass(){
        return StructureTemplatePostResponse.class;
    }

    @Override
    public String toString() {
        return "StructureTemplatePostResponse{" +
                "createdDateTime=" + createdDateTime +
                ", id='" + id + '\'' +
                '}';
    }
}
