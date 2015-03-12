<%-- 
    Document   : admin
    Created on : 6 mars 2015, 14:22:31
    Author     : Gato
--%>

<%@page import="CyberComp_G2.Model.ConsulterEpreuve.EpreuveParEquipe"%>
<%@page import="CyberComp_G2.DAO.ConsulterEpreuve.GetConsulterEpreuveDAO"%>
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
                </div>

                <!-- 
                        TITRE DE LA PAGE 
                --> 
                <div class="page-header">
                    <h2 class="text-center"><span id="titreAdmin"> Inscriptions aux épreuves</span></h2>
                </div>
                <!-- 
                        DEBUT DE LA PAGINATION DU TABLEAU 
                -->
                <div class="row">

                    <ul class="nav nav-tabs">
                        <!-- Onglet: choix Epreuve par equipe ou Epreuve individuelle-->
                        <li role="presentation" class="active" id ="presentation1"><a href="#tab1">Epreuves par équipe</a></li>
                        <li role="presentation" id ="presentation2"><a href="#tab2">Epreuves individuelles</a></li>
                    </ul>

                    <div class="row">
                        <br/>
                    </div>

                    <!--
                            ID TAB1 : Inscrire des équipes à une épreuve par équipes
                    page de l'onglet 1
                    -->
                    <div id='tab1'>
                        <form class="form-horizontal">
                            <div class="well">
                                Cette page vous permet d'inscrire des équipes à une épreuve
                            </div>
                            <div class="row">
                                <div class="form-group">

                                    <label class='col-xs-3 control-label'>Epreuve:</label>
                                    <div class='col-xs-6'>
                                        <select class="form-control" id='selectionEpreuvesEquipe' name='listEpreuvesEquipe'>
                                            <option value=''>Choix</option>
                                             <%
                                                 int i=0;
                                                  ArrayList<EpreuveParEquipe> lesEpreuvesEquipe = (ArrayList<EpreuveParEquipe>) request.getAttribute("listEpreuveEquipe");
                                                    for (i = 0; i < lesEpreuvesEquipe.size(); i++) {
                                                        int idEpreuve = lesEpreuvesEquipe.get(i).getIdEpreuve();
                                                        String nomEpreuve=lesEpreuvesEquipe.get(i).getNomEpreuve();
                                                        String categorie=lesEpreuvesEquipe.get(i).getCategorie();
                                                        
                                                %>      <option value='<%=idEpreuve%>'><%= idEpreuve%> : <%=nomEpreuve%>  -  <%=categorie%>  </option>
                                                <% }; %>
                                           
                                        </select>
                                    </div>
                                    <div class='col-xs-1 control-label'id="verifEpreuveChoisie"> </div>    
                                </div>
                            </div>
                        </form>

                        <!--
                                 AJOUTER UNE EQUIPE A L EPREUVE
                        -->

                        <form class="form-horizontal">

                            <h4> <strong> Ajouter une équipe </strong></h4>

                            <div class="row">
                                <div class="form-group">
                                    <label class='col-xs-3 control-label'> Equipe:</label>
                                    <div class='col-xs-6'>
                                        <select class="form-control" id='selectEqAjouter' disabled="true">
                                            <option value="">Choix</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-6 col-xs-offset-3">
                                        <button type="button" class="btn btn-default btn-block">Ajouter &nbsp;<span class="glyphicon glyphicon-plus"></span></button>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <!-- 
                            SUPPRIMER UNE EQUIPE A L EPREUVE
                        -->


                        <form class="form-horizontal">
                            <h4>  <strong> Supprimer une equipe </strong></h4>
                            <div class="row">
                                <div class="form-group">
                                    <label class='col-xs-3 control-label'> Equipe:</label>
                                    <div class='col-xs-6'>
                                        <select class="form-control" selectEquipe='selectEquipeASupprimer'id='selectEquipeASupprimer'disabled="true">
                                            <option value="">Choix</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class='row'>
                                <div class="form-group">
                                    <div class='col-xs-offset-3 col-xs-6'>
                                        <button type="button" class="btn btn-danger btn-block">Supprimer&nbsp;<span class="glyphicon glyphicon-trash"></span></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>


                    <!--
                            ID TAB2 : Inscrire des sportifs à une épreuve individuelle
                    -->
                    <div id='tab2'>
                        <form class="form-horizontal">
                            <div class="well">
                                Cette page vous permet d'inscrire des sportifs à une épreuve individuelle
                            </div>
                            <div class="row">
                                <div class="form-group">

                                    <label class='col-xs-3 control-label'>Epreuve:</label>
                                    <div class='col-xs-6'>
                                        <select class="form-control" id='selectionEpreuvesEquipe' name='listEpreuvesEquipe'>
                                            <option value=''>Choix</option>
                                            <!-- Appel liste Equipe du controler 
                                            (parametre sur la categorie selon epreuve choisie) -->
                                        </select>
                                    </div>
                                    <div class='col-xs-1 control-label'id="verifEpreuveChoisie"> </div>    
                                </div>
                            </div>

                            <!--
                                     AJOUTER UN SPORTIF A L EPREUVE
                            -->

                            <form class="form-horizontal">

                                <h4> <strong> Ajouter un sportif </strong></h4>

                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'> Sportif:</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectNomAjouter' disabled="true">
                                                <option value="">Choix</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-6 col-xs-offset-3">
                                            <button type="button" class="btn btn-default btn-block">Ajouter &nbsp;<span class="glyphicon glyphicon-plus"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <!-- 
                                SUPPRIMER UN SPORTIF DE L EPREUVE
                            -->


                            <form class="form-horizontal">
                                <h4>  <strong> Supprimer un sportif </strong></h4>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'> Sportif:</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" selectEquipe='selectEquipeASupprimer'id='selectEquipeASupprimer'disabled="true">
                                                <option value="">Choix</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class="form-group">
                                        <div class='col-xs-offset-3 col-xs-6'>
                                            <button type="button" class="btn btn-danger btn-block">Supprimer&nbsp;<span class="glyphicon glyphicon-trash"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
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

