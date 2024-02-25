package ru.psyhohelp.model;

public class SupportPhrase {
    private String id;
    private String phrase;

    public SupportPhrase() {
    }

    public SupportPhrase(String id, String phrase) {
        this.id = id;
        this.phrase = phrase;
    }

    public String getId() {
        return id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
