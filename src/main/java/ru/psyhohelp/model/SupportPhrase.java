package ru.psyhohelp.model;

public class SupportPhrase {
    private int id;
    private String phrase;

    public SupportPhrase(int id, String phrase) {
        this.id = id;
        this.phrase = phrase;
    }

    public int getId() {
        return id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
