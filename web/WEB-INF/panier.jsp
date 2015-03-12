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
                                <li ><a href="index.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder à l'accueil">Accueil</a></li>
                                <li><a href="GetListDiscipline" data-toggle="tooltip" data-placement="bottom" title="Acceder aux disciplines">Disciplines</a></li>
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves">Epreuves</a></li>
                                <li><a href='#' data-toggle="tooltip" data-placement="bottom" title="Acceder aux résultats des épreuves">Resultats</a></li>
                                <li class='active'><a href="#" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li><a href='GetListDelegation' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                
                
                 <!-- 
                Affichage du titre panier
                -->
                
                <div class='row'>
                    <div class="page-header text-center">
                        <h2><small>Votre</small> Panier </h2>
                    </div>
                </div>
                
                <!-- 
                   Elément barre progression achat en 4 étapes : Panier/valider/paiement/Confirmation paiement... prévoir un truc pour pouvoir visualier à quelle étape l'acheteur se trouve (ex: colorer la zone)
                -->
        
                    <div class="row">
                        <div class="col-xs-3"> (1) Panier </div>
                        <div class="col-xs-3"> (2) Valider</div>
                        <div class="col-xs-3"> (3) Paiment</div>
                        <div class="col-xs-3"> (4) Confirmation paiement</div>
                    </div>

                 <!-- 
                  Zone titre tableau et récapitulatif du nombre d'éléments différents acheté
                  -->
                
                  <div class="row">
        
                        <div class="col-xs-12">Votre panier contient XX éléments / est vide... </div>
                  </div>
                
                   <!-- 
                  Zone titre DU tableau des achats
                  -->
                  
                 <div class="row">
        
                        <div class="col-xs-6">PRODUIT</div>
                        <div class="col-xs-2">PRIX</div>
                        <div class="col-xs-2">QUANTITE</div>
                        <div class="col-xs-2">TOTAL</div>
                  </div>
                
                   <!-- 
                  Zone du tableau détaillant les achats
                  -->
                  
                  <div>
                   <div>
                  
                  
                 <!-- 
                   Zone calcul du montant total du panier ... 
                  -->
                
                  <div class="row">
                        <div class="col-xs-8">Montant total du panier =</div>
                        <div class="col-xs-4"> XXXX €</div>
                  </div>
                  
                  
                   <!-- 
                   Zone contenant les éléments bouton vider panier + bouton Suivant alignés
                  -->
                <div class="row">
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-default" aria-label="Left Align">
                            <span class="glyphicon glyphicon-align-left" aria-hidden="false"></span> 
                        Vider le contenu du panier</button>
                    </div>
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-default" aria-label="Left Align">
                            <span class=" glyphicon glyphicon-ok" aria-hidden="false"></span>
                        Passer à l'étape suivant</button>
                    </div>
                </div>
           
                 <!-- 
                   fin code
                -->
                
            </div>
        </div>
        <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/cyberCompetition.js" ></script>
    </body>
</html>

