package com.example.apitemplate.Structure;

public class StructureTemplate implements ValidStructure {
    private boolean idExists;
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
        else throw new IllegalStateException();

    }
}
