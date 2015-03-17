<%-- 
    Document   : admin
    Created on : 17 mars 2015
    Author     : oprisora
--%>
<%@page import="CyberComp_G2.Model.ConstituerEquipe.Sportif"%>
<%@page import="CyberComp_G2.Model.ConstituerEquipe.Equipe"%>
<%@page import="CyberComp_G2.Model.ConstituerEquipe.Delegation"%>
<%@page import = "java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves">Epreuves</a></li>
                                <li><a href='#' data-toggle="tooltip" data-placement="bottom" title="Acceder aux résultats des épreuves">Resultats</a></li>
                                <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li  class='active'><a href='admin.jsp' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>


                    <!-- 
                            TITRE DE LA PAGE 
                    --> 
                    <div class="page-header">
                        <h2 class="text-center"> Validation </h2>
                    </div>
                    <% String etat = (String) request.getAttribute("etat"); 
                        Equipe newEquipe = (Equipe) request.getAttribute("newEquipe");
                    %>
                    <% if(etat.equals("creation")){
                    %>   
                    
                    <div id='equipeInscrite'>
                        <form class="form-horizontal">
                            <div class="well">
                                L'équipe a été inscrite 
                            </div>
                            <div> Equipe: Hsdzyf </div><br> 
                            <div> Les sportifs : </div>
                            <ul style="list-style-type: square">
                                <li> Curadhj </li>
                                <li> Ghstdg </li>
                                <li> BHjdadv </li>
                                <li> Nhdagd </li>
                                <li> Muadvcs </li>
                            </ul>
                    </div>
                    <% }else{ %>
                    <div id="equipeModif">
                        <form class="form-horizontal"> 
                            <div class="well"> L'équipe a été modifiée </div>
                            <div> Equipe: Hsdzyf </div><br>
                            <div> Les sportifs : </div>
                            <ul style='list-style-type: square'>
                                <li> Curadhj </li>
                                <li> Ghstdg </li>
                                <li> BHjdadv </li>
                                <li> Lseafrg </li>
                                <li> Muadvcs </li>
                            </ul>    
                    </div>

                    <div id='equipeSuppr'>
                        <form class="form-horizontal"> 
                            <div class="well"> L'équipe a été supprimée </div>
                    </div>

                    <div id='erreur'>
                        <form class="form-horizontal"> 
                            <div class="well"> Erreur : votre action n'a pas pu aboutir. <br> 
                                Veuillez recommencer. </div>
                    </div>
                    <%}%>
                    <div class='col-xs-6'>
                        <button type="button" class="btn btn-default btn-block" id="accueil"> Accueil </button>
                    </div>
                    <div class='col-xs-6'>
                        <button type="button" class="btn btn-default btn-block" id="retour"> Administrer Equipe </button>
                    </div>
                </div>
                <footer class="footer">
                    <p class='text-muted pull-right'><i>m.a.j: 10/03/2015</i></p>
                    <p class="text-muted">&copy; Master 2 CCI Grenoble : Groupe2</p>
                </footer>

            </div>
        </div>

        <script>
            $(document).ready(function () {
                $("#tabs").tabs();
            });

        </script>
        <script type="text/javascript" src="js/cyberCompetition.js"></script>
        <script src="js/FormAdmin.js" type="text/javascript"></script>
    </body>
</html>
