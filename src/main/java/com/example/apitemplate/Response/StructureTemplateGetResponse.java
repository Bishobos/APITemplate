package com.example.apitemplate.Response;

import com.example.apitemplate.Structure.StructureTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class StructureTemplateGetResponse implements ValidStructureTemplateResponse{
    private LocalDateTime createdDateTime;
    private List<StructureTemplate> responseStructures;

    public StructureTemplateGetResponse(List<StructureTemplate> responseStructures){
        this.responseStructures = responseStructures;
        this.createdDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "StructureTemplateGetResponse{" +
                "createdDateTime=" + createdDateTime +
                ", responseStructures=" + responseStructures +
                '}';
    }
}
