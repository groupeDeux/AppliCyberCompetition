<%-- 
    Document   : index
    Created on : 6 mars 2015, 13:11:28
    Author     : Gato
--%>

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
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves">Epreuves</a></li>
                                <li><a href='#' data-toggle="tooltip" data-placement="bottom" title="Acceder aux résultats des épreuves">Resultats</a></li>
                                <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li class='active'><a href="admin.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="row">
                    <div class="jumbotron">
                        <h1>Administration</h1>
                        <p class="lead">Bienvenue sur administration de <strong>CyberCompetition</strong></p>
                    </div>    
                </div>
                <div>
                    <div class ='row'>
                        <!--la classe col-xs-4  permet de diviser la zone de travail en 3 champs 
                        une zone de travail est défini sur 12 colomn 
                       -->
                   <div class='col-xs-4'>
                       <!--la classe thumbnail permet de créer les carée
                       -->
                        <div class='thumbnail'>
                            <!-- le premier carré contient un bouton pour accéder à la page de 
                            -->
                            <img src="img/adminEquipe.jpg"alt=".." style="margin-top: 10px;" class="img-rounded">
                            <div class="caption">
                                <h3 class="text-center">Administrer les équipes</h3>
                                <p> Créer, modifier ou supprimer une équipe</p>
                                <p><a href="GetListDelegation" class="btn btn-default btn-block" role='button' name="" data-toggle="tooltip" data-placement="bottom" title="Acceder a l'administration des equipes">Accéder à la page </a></p>
                            </div>
                        </div>
                   </div>
                    <div class='col-xs-4'>
                       <div class='thumbnail'>
                            <img src="img/lesEpreuves.jpg"alt=".." style="margin-top: 8px;width: 10; height: 40" class="img-rounded">
                            <div class="caption">
                                <h3 class="text-center">Inscription des participants à des épreuves </h3>
                                <p> Inscrire, modifier ou supprimer un participant à une épreuve </p>
                                <p><a href="GetListEpreuveInscription" class="btn btn-default btn-block" role='button' name="" data-toggle="tooltip" data-placement="bottom" title="Acceder à insciption participant à des épreuves ">Accéder à la page  </a></p>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>  
        </div>
        
        <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/cyberCompetition.js" ></script>
    </body>
</html>

