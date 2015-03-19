<%-- 
    Document   : index
    Created on : 6 mars 2015, 13:11:28
    Author     : Gato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CyberCompetition</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/cyberCompetition.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.js" ></script>
        <link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="./img/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <div class="container">

            <!--   
                WRAPPER DU CONTENU DE LA PAGE 
                COL-XS-10 COL-XS-OFFSET-1
            -->

            <div class="col-xs-10 col-xs-offset-1">

                <!-- 
                        BARRE DE NAVIGATION
                -->
                <div class='row'>
                    <div class="masthead">
                        <h2 class="text-muted"><a href="index.jsp" data-toggle="tooltip" data-placement="right" title="Acceder à l'accueil">CyberCompetition</a></h2>
                        <nav>
                            <ul class="nav nav-justified">
                                <li><a href="index.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder à l'accueil">Accueil</a></li>
                                <li><a href="GetListDiscipline" data-toggle="tooltip" data-placement="bottom" title="Acceder aux disciplines">Disciplines</a></li>
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder à la billetterie">Billetterie</a></li>
                                <li class='active'><a href="GetPanier" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li><a href="admin.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>


                <!-- 
                       Page de fin de commande
                -->

                <div class='row'>
                    <div class="page-header text-center">
                        <h2>Commande terminée</h2>
                    </div>
                </div>
                <div class='row'>
                    <div class="well text-center">
                        <Strong>Félicitation</Strong>, votre commande n°${idTransaction} a bien été prise en compte.<br/>Un email vous sera envoyé avec le contenu des billets.<br/><h3>Bonne continuation !</h3>
                    </div>
                </div>
                <div class='row'>
                    <div class='col-xs-4 col-xs-offset-4'>
                        <a class="btn btn-lg btn-default btn-block" href="index.jsp" id='btnRetourAccueil'>Retour à l'accueil</a>
                    </div>
                </div>

                <!-- 
               
                       Afficher la date de la dernière mise à jour .Ce tag contient
                    du code Java nommé un scriptlet.ref: charte graphique: texte 
                -->
                <footer class="footer">
                    <%! Date dateDuJour;%>
                    <% dateDuJour = new Date();%>
                    <p class='text-muted pull-right'><i> Date de dernière mise à jour : <%= dateDuJour %></i></p>
                    <p class="text-muted">&copy; Master 2 CCI Grenoble : Groupe2</p>
                </footer>
            </div>
        </div>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>

