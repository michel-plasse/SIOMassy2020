/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author wowmi
 */
@WebServlet("/questionnaires")
public class ResultatQuestionnaireServlet extends HttpServlet {
    
    private final String VUE_BONNE = "/WEB-INF/resultatQuestionnaire.jsp";
    private final String VUE_KO = "/WEB-INF/exception.jsp";
}
