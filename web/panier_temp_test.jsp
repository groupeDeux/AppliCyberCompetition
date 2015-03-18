<%-- 
    Document   : pannier
    Created on : 12 mars 2015
    Author     : M.Conte
--%>

<%@page import="CyberComp_G2.Model.Utilisateur.Utilisateur" %>
<%@page import="CyberComp_G2.Model.ConsulterEpreuve.Epreuve"%>
<%@page import="CyberComp_G2.Model.Panier.Panier" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
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
        <link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="./img/favicon.ico" type="image/x-icon">
        <script type="text/javascript" src="js/jsPanier.js"></script>



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
                        <h2><small>Votre</small> Panier</h2>
                    </div>
                </div>
                
                
                 <div id="tabs">
                        <ul class="nav nav-tabs">
                            
                            <li role="presentation" class="active courant"  id ="courant" value='0'><a href="#tab1">Creer Equipe</a></li>
                            <li role="presentation"   class="disabled" id ="presentation2" value='1'><a href="#tab2">Modifier Equipe</a></li>
                            <li role="presentation" value='2' ><a href="#tab3">Supprimer Equipe</a></li>

                        </ul>

                        <div class="row">
                            <br/>
                        </div>
                        <!--
 -------------------------------------------------------------ID TAB1 : CREER UNE EQUIPE---------------------------------------------------------------------------
                        -->
                        <div id='tab1'>
                            coucou1
                        </div>
                        
                        <div id='tab2'>
                            coucou2
                        </div>
                        <div id='tab3'>
                            coucou3
                        </div>



                </div> 
            </div>            
        </div>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script  src="js/cyberCompetition.js" type="text/javascript" ></script>
        <script src="js/jsPanier.js" type="text/javascript"></script>
        <script>
        $(document).ready(function () {
            $("#tabs").tabs();
        });

        $("li[role='presentation']").on('click', function () {
           if($(this).hasClass("disabled")){
               $("#courant").tab('show');
               var tab =$("#courant").val();
                $("#tabs").tabs({
                            active: tab
                 });
            }else{
                var tab =$("#courant").val();
                $("#courant").prop("id","presentation"+tab);
                $(this).prop("id","courant");
            }
          
           // var temp = $(this).attr('class');
           //$(this).removeClass('active');
           //temp = $(this).attr('class');

            //$("#presentation1").tab('show');
            //$("#tabs").tabs("option", "active", 0);
             /*$(this).removeClass("active");
             $("#tab3").addClass("active");
             alert();*/
             /*$("#tabs").tabs({
                    active: 2
              });
              $("#tabs").tabs({
                    select: 2
              });*/
        });
//$("#tabs").tabs({
//   active :1
//});

        </script>
    </body>
</html>

