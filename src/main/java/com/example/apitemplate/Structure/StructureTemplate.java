package com.example.apitemplate.Structure;

public class StructureTemplate implements ValidStructure {
    private boolean idExists;
    private int id; //structure id needs to exist
    private final String text; //template attribute

    public StructureTemplate(String text) {
        this.text = text;
    }

    public StructureTemplate(int id, String text) {
        this.id = id;
        this.text = text;
        this.idExists = true;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        if(!idExists){
            this.id = id;
        }
        else throw new IllegalStateException();

    }
}
