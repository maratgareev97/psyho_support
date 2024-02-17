package ru.psyhohelp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface SupportPhraseController {

    public void getSupportPhrase(HttpServletRequest req, HttpServletResponse response) throws IOException;


    public void addSupportPhrase(HttpServletRequest request, HttpServletResponse response) throws IOException ;
}
