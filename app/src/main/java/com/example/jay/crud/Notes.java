package com.example.jay.crud;

public class Notes {
    private String note_head,addedTime,ID;

    public Notes(String note_head, String addedTime, String ID) {
        this.note_head = note_head;
        this.addedTime = addedTime;
        this.ID = ID;
    }

    public String getNote_head() {
        return note_head;
    }

    public void setNote_head(String note_head) {
        this.note_head = note_head;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
