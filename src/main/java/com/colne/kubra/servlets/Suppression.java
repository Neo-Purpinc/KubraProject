package com.colne.kubra.servlets;

import com.colne.kubra.dao.UtilisateurDao;

import javax.servlet.annotation.WebServlet;

@WebServlet("/suppression" )
public class Suppression {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE              = "/WEB-INF/jsp/restricted/home.jsp";
    private UtilisateurDao utilisateurDao;
}
