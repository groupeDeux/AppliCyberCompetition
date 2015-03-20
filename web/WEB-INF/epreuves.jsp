

<%@page import="java.util.Date"%>
<%@page import="CyberComp_G2.Model.ConstituerEquipe.Sportif"%>
<%@page import="CyberComp_G2.Model.ConstituerEquipe.Equipe"%>
<%-- 
    Document   : epreuve
    Created on : 8 mars 2015, 07:55:52
    Author     : vivi
--%>
<%@page import="CyberComp_G2.Model.Panier.Panier"%>
<%@page import="CyberComp_G2.Model.ConsulterEpreuve.Epreuve"%>
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
    </head>
    <body>
        <div class="container">

            <!--   
                    WRAPPER DU CONTENU DE LA PAGE 
                    COL-XS-10 COL-XS-OFFSET-1
            -->

            <div class="col-xs-10 col-xs-offset-1">
                <div class='row'>
                    <!-- 
                            BARRE DE NAVIGATION
                    -->
                    <div class="masthead" id='barreDeNavigation'>
                        <h2 class="text-muted"><a href="index.jsp" data-toggle="tooltip" data-placement="right" title="Acceder à l'acceuil">CyberCompetition</a></h2>
                        <nav>
                            <ul class="nav nav-justified">
                                <li><a href="index.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder à l'accueil">Accueil</a></li>
                                <li><a href="GetListDiscipline" data-toggle="tooltip" data-placement="bottom" title="Acceder aux disciplines">Disciplines</a></li>
                                <li class='active'><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder à la billetterie">Billetterie</a></li>
                                <li><a href="GetPanier" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li><a href='admin.jsp' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!--
                    RECHERCHE DANS LES EPREUVES
                -->
                <div class='row'>
                    <div class="page-header text-center">
                        <h3><small>Recherche d'</small> Epreuves <small>par discipline</small></h3>
                    </div>
                </div>
                <div class='row'>
                    <form class='form' action='GetListEpreuve'>
                        <div class='col-xs-3'>
                            <div class='form-group'>
                                <!--Choix de la liste des disciplines disponible au début-->
                                <label>Discipline:</label>
                                <select class="form-control" name='epreuvesSelectDiscipline' id='epreuvesSelectDiscipline'>
                                    <option value="Tout">Tout</option>
                                    <%
                                        int nDiscipline = 0;
                                        ArrayList<String> lesDisciplines = (ArrayList<String>) request.getAttribute("listDisciplines");
                                        for (nDiscipline = 0; nDiscipline < lesDisciplines.size(); nDiscipline++) {
                                            String discipline = lesDisciplines.get(nDiscipline);
                                    %>
                                    <option value='<%=discipline%>'><%=discipline%></option>
                                    <% }; %>
                                </select>
                            </div>
                        </div>
                        <div class='form-group'>
                            <div class='col-xs-3'>
                                <label>&nbsp;</label>
                                <button class='btn btn-default btn-block' type='submit'>Afficher</button>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- 
                    LISTE DES EPREUVES
                -->
                <div class='row'>
                    <div class="page-header text-center">
                        <h3><small>Liste des</small> Epreuves</h3>
                    </div>
                </div>
                <!--
                        NAVIGATION PAR TYPES
                -->
                <div class="row">
                    <ul class="nav nav-pills" id="epreuvesButtonsTags">
                    </ul>
                    <br/>
                </div>


                <div class='row'>
                    <h3 class='pull-right'>Disciplines</h3>
                    <h3 class='col-xs-offset-1'>&nbsp;&nbsp;&nbsp;&nbsp;Epreuves</h3>
                    <hr/>
                </div>

                <!--
                        MEDIA DE LA LISTE DES EPREUVES
                -->    
                <%
                    int i;
                    int lesId = 0;
                    ArrayList<Epreuve> lesEpreuves = (ArrayList<Epreuve>) request.getAttribute("listEpreuveEquipe");
                    for (i = 0; i < lesEpreuves.size(); i++) {
                        Epreuve epreuveSelectionnee = lesEpreuves.get(i);
                        lesId = lesId + i;
                %>


                <%--
                    Creation d'un onglet d'une epreuve, a chaque epreuve correspond un data-tags qui permet d'etre référencé par la catégorie est par Equipe ou Individuel
                    media-left: correspond au placement de l'image dans les medias
                    media-body: correspond au corps du medias
                    Dans ce corps ce trouve deux tableaux qui peuvent être agrandit sous forme de "panel panel-default"
                --%>
                <div class="row rowEpreuve" data-tags="<%= epreuveSelectionnee.getCategorie()%>,Equipe">
                    <div class="media">
                        <div class="media-left">
                            <%-- Cliquer sur l'image permet d'afficher l'epreuve --%>
                            <div data-toggle='collapse' name ='selectEpreuve' href='#epreuvesId<%=epreuveSelectionnee.getIdEpreuve()%>' value="<%=epreuveSelectionnee.getIdEpreuve()%>">
                                <img class="media-object img-rounded" src='./img/image-media-<%=epreuveSelectionnee.getNomDiscipline()%>.jpg' alt='image de <%=epreuveSelectionnee.getNomDiscipline()%>' data-toggle="tooltip" data-placement="top" title="Afficher informations supp.">
                            </div>
                        </div>
                        <div class='media-body'>
                            <%-- Cliquer sur cette partie media-heading permet d'afficher l'epreuve --%>
                            <div data-toggle='collapse tooltip' data-placement="top" title="Afficher informations supp." data-info='close' name ='selectEpreuve' href='#epreuvesId<%=epreuveSelectionnee.getIdEpreuve()%>' value="<%=epreuveSelectionnee.getIdEpreuve()%>" >
                                <h4 class='media-heading pull-right'><%=epreuveSelectionnee.getNomDiscipline()%>&nbsp;</h4>
                                <h3 class='media-heading'><%=epreuveSelectionnee.getNomEpreuve()%><small>&nbsp;<%=epreuveSelectionnee.getCategorie()%></small></h3>
                                <div class='pull-right'><h3><span class='glyphicon glyphicon-menu-down'></span>&nbsp;</h3></div>
                                <h5 class='media-heading'>Début : <%=epreuveSelectionnee.getDateDebut()%>h00</h5>
                                <h5 class='media-heading'>Fin : <%=epreuveSelectionnee.getDateFin()%>h00</h5>
                                <br/>
                            </div>
                            <div class="collapse" id="epreuvesId<%=epreuveSelectionnee.getIdEpreuve()%>">
                                
                                <%--
                                        LISTE DES PARTICIPANTS
                                        Panel d'affichage de la liste des participants à l'epreuve 
                                        panel-heading: Titre de cette page
                                        panel-body: corps de la page a afficher
                                --%>
                                <div id="infoEpreuve<%=epreuveSelectionnee.getIdEpreuve()%>">
                                </div>
                                <%--
                                <div class="panel panel-default">
                                    <div class="panel-heading" data-toggle='collapse' href='#epreuvesParticipants<%=epreuveSelectionnee.getIdEpreuve()%>'>
                                        <h4>
                                            Liste des participants de l'épreuve
                                            <span class='pull-right glyphicon glyphicon-menu-down'></span>
                                        </h4>
                                    </div>
                                    <div id="epreuvesParticipants<%=epreuveSelectionnee.getIdEpreuve()%>" class="panel-collapse collapse">
                                        <div class='panel-body'>
                                            ===========
                                        </div>
                                    </div>
                                </div>
                                --%>    
                                <%--
                                LISTE DES MEDAILLES
                                Chargee lorsqu'on clique sur une epreuve
                                si il y a des mdailles sinon affichage des achats billets
                                --%>
                                <%--
<div id="epreuvesMedailles<%=epreuveSelectionnee.getIdEpreuve()%>" class="panel panel-default">
                                    
                                </div>
                                
                                %-->
                                <%--
                                        ACHETER UN BILLET
                                        Panel d'affichage pouracheter un billet
                                        panel-heading: Titre de cette page
                                        panel-body: corps de la page a afficher
                                --%>
                                <%--
                                <div class="panel panel-default">
                                    <div class="panel-heading" data-toggle='collapse' href='#epreuvesBillets<%=epreuveSelectionnee.getIdEpreuve()%>'>
                                        <h4>
                                            Acheter un billet ou un ticket video.
                                            <span class='pull-right glyphicon glyphicon-menu-down'></span>
                                        </h4>
                                    </div>
                                    <div id="epreuvesBillets<%=epreuveSelectionnee.getIdEpreuve()%>" class="panel-collapse collapse">
                                        <div class='panel-body'>
                                            <form method="post" class="form-inline" action="AjoutPanier">
                                                <div class='col-xs-2'>
                                                    <div class='form-group' style="padding-top:6px;">
                                                        <strong>Prix: <%=epreuveSelectionnee.getTarif()%>€</strong>
                                                    </div>
                                                </div>
                                                <div class='col-xs-1'>
                                                    <div class='form-group'>
                                                        <div class="radio-inline" style="padding-top:6px;">
                                                            <input type="radio" name="epreuvesRadio" value="Billet:<%=epreuveSelectionnee.getIdEpreuve()%>" checked>
                                                            <label class='control-label'>Billet</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class='col-xs-2'>
                                                    <div class='form-group'>
                                                        <div class="radio-inline" style="padding-top:6px;">
                                                            <input type="radio" name="epreuvesRadio" value="TicketVideo:<%=epreuveSelectionnee.getIdEpreuve()%>">
                                                            <label class='control-label'>Ticket Video</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class='col-xs-4'>
                                                    <div class="form-group">
                                                        <label class='control-label'>Nombre de places:</label>
                                                        <select class="form-control" name='epreuvesNbPlaces'>
                                                            <%
                                                                int optionValue = 0;
                                                                int maxTicket = 0;
                                                                int nbDePlacesDispo = epreuveSelectionnee.getNbDePlace() - epreuveSelectionnee.getNbPlaceAcheter();
                                                                if (nbDePlacesDispo > 10) {
                                                                    maxTicket = 10;
                                                                } else {
                                                                    maxTicket = nbDePlacesDispo;
                                                                }
                                                                for (optionValue = 1; optionValue <= maxTicket; optionValue++) {
                                                            %>
                                                            <option value="<%= optionValue%>"><%= optionValue%> </option>
                                                            <% }; %>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class='col-xs-3'>
                                                    <div class='form-group'>
                                                        <button type="submit" class="btn btn-primary pull-right">Ajouter au Panier&nbsp;<span class="glyphicon glyphicon-shopping-cart"></span></button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                --%>

                            </div>
                        </div>
                    </div>
                </div>
                <%}%>

                <%--
                    LISTE DES PARTICIPANTS INDIVIDUELLES
                --%>
                <%

                    lesEpreuves = (ArrayList<Epreuve>) request.getAttribute("listEpreuveInd");
                    for (i = 0; i < lesEpreuves.size(); i++) {
                        Epreuve epreuveSelectionnee = lesEpreuves.get(i);
                        lesId = lesId + i;
                %>



                <div class="row rowEpreuve" data-tags="<%= epreuveSelectionnee.getCategorie()%>,Individuel">
                    <div class="media">
                        <div class="media-left">
                            <%-- Cliquer sur l'image permet d'afficher l'epreuve --%>
                            <div data-toggle='collapse tooltip' data-placement="top" title="Afficher informations supp." data-info='close' name ='selectEpreuve' href='#epreuvesId<%=epreuveSelectionnee.getIdEpreuve()%>' value="<%=epreuveSelectionnee.getIdEpreuve()%>">
                                <img class="media-object img-rounded" src='./img/image-media-<%=epreuveSelectionnee.getNomDiscipline()%>.jpg' alt='image de <%=epreuveSelectionnee.getNomDiscipline()%>' data-toggle="tooltip" data-placement="top" title="Afficher informations supp.">
                            </div>
                        </div>
                        <div class='media-body'>
                            <%-- Cliquer sur cette partie media-heading permet d'afficher l'epreuve --%>
                            <div data-toggle='collapse tooltip' data-placement="top" title="Afficher informations supp." data-info='close' name ='selectEpreuve' href='#epreuvesId<%=epreuveSelectionnee.getIdEpreuve()%>' value="<%=epreuveSelectionnee.getIdEpreuve()%>">
                                <h4 class='media-heading pull-right'><%=epreuveSelectionnee.getNomDiscipline()%>&nbsp;</h4>
                                <h3 class='media-heading'><%=epreuveSelectionnee.getNomEpreuve()%><small>&nbsp;<%=epreuveSelectionnee.getCategorie()%></small></h3>
                                <div class='pull-right'><h3><span class='glyphicon glyphicon-menu-down'></span>&nbsp;</h3></div>
                                <h5 class='media-heading'>Début : <%=epreuveSelectionnee.getDateDebut()%>h00</h5>
                                <h5 class='media-heading'>Fin : <%=epreuveSelectionnee.getDateFin()%>h00</h5>
                                <br/>
                            </div>
                            <div class="collapse" id="epreuvesId<%=epreuveSelectionnee.getIdEpreuve()%>">
                                
                                <div id="infoEpreuve<%=epreuveSelectionnee.getIdEpreuve()%>">
                                </div>
                                <%--
                                        LISTE DES PARTICIPANTS
                                        Panel d'affichage de la liste des participants à l'epreuve 
                                        panel-heading: Titre de cette page
                                        panel-body: corps de la page a afficher
                                --%>
                                <%--
                                <div class="panel panel-default">
                                    <div class="panel-heading" data-toggle='collapse' href='#epreuvesParticipants<%=epreuveSelectionnee.getIdEpreuve()%>'>
                                        <h4>
                                            Liste des participants de l'épreuve
                                            <span class='pull-right glyphicon glyphicon-menu-down'></span>
                                        </h4>
                                    </div>
                                    <div id="epreuvesParticipants<%=epreuveSelectionnee.getIdEpreuve()%>" class="panel-collapse collapse">
                                        <div class='panel-body'>
                                            ===================================

                                        </div>
                                    </div>
                                </div>
                                --%>
                                <%--
                                        ACHETER UN BILLET
                                        Panel d'affichage pouracheter un billet
                                        panel-heading: Titre de cette page
                                        panel-body: corps de la page a afficher
                                --%>
                                <%--
                                <div class="panel panel-default">
                                    <div class="panel-heading" data-toggle='collapse' href='#epreuvesBillets<%=epreuveSelectionnee.getIdEpreuve()%>'>
                                        <h4>
                                            Acheter un billet ou un ticket video.
                                            <span class='pull-right glyphicon glyphicon-menu-down'></span>
                                        </h4>
                                    </div>
                                    <div id="epreuvesBillets<%=epreuveSelectionnee.getIdEpreuve()%>" class="panel-collapse collapse">
                                        <div class='panel-body'>
                                            <form method="post" class="form-inline" action="AjoutPanier">
                                                <div class='col-xs-2'>
                                                    <div class='form-group' style="padding-top:6px;">
                                                        <strong>Prix: <%=epreuveSelectionnee.getTarif()%>€</strong>
                                                    </div>
                                                </div>
                                                <div class='col-xs-1'>
                                                    <div class='form-group'>
                                                        <div class="radio-inline" style="padding-top:6px;">
                                                            <input type="radio" name="epreuvesRadio" value="Billet:<%=epreuveSelectionnee.getIdEpreuve()%>" checked>
                                                            <label class='control-label'>Billet</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class='col-xs-2'>
                                                    <div class='form-group'>
                                                        <div class="radio-inline" style="padding-top:6px;">
                                                            <input type="radio" name="epreuvesRadio" value="TicketVideo:<%=epreuveSelectionnee.getIdEpreuve()%>">
                                                            <label class='control-label'>Ticket Video</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class='col-xs-4'>
                                                    <div class="form-group">
                                                        <label class='control-label'>Nombre de places:</label>
                                                        <select class="form-control" name='epreuvesNbPlaces'>
                                                            <%
                                                                int optionValue = 0;
                                                                int maxTicket = 0;
                                                                int nbDePlacesDispo = epreuveSelectionnee.getNbDePlace() - epreuveSelectionnee.getNbPlaceAcheter();
                                                                if (nbDePlacesDispo > 10) {
                                                                    maxTicket = 10;
                                                                } else {
                                                                    maxTicket = nbDePlacesDispo;
                                                                }
                                                                for (optionValue = 1; optionValue <= maxTicket; optionValue++) {
                                                            %>
                                                            <option value="<%= optionValue%>"> <%= optionValue%> </option>
                                                            <% }; %>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class='col-xs-3'>
                                                    <div class='form-group'>
                                                        <button type="submit" class="btn btn-primary pull-right">Ajouter au Panier&nbsp;<span class="glyphicon glyphicon-shopping-cart"></span></button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                --%>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
                <!--
                        FOOTER DE LA PAGE
                -->
                <footer class="footer">
                    <%! Date dateDuJour;%>
                    <% dateDuJour = new Date();%>
                    <p class='text-muted pull-right'><i> Date de dernière mise à jour : <%= dateDuJour%></i></p>
                    <p class="text-muted">&copy; Master 2 CCI Grenoble : Groupe2</p>
                </footer>
            </div>
            <div id='go-top' class='glyphicon glyphicon-circle-arrow-up'><a href='#barreDeNavigation'></a></div>
        </div>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/jsEpreuve.js" type="text/javascript"></script>
    </body>
</html>
