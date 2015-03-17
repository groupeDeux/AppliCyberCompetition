<%-- 
    Document   : admin
    Created on : 6 mars 2015, 14:22:31
    Author     : Gato
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
                                <li><a href="GetPanier" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li  class='active'><a href='admin.jsp' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- 
                        TITRE DE LA PAGE 
                --> 
                <div class="page-header">
                    <h2 class="text-center"><small>Administration des</small><span id="titreAdmin"> Equipes</span></h2>
                </div>
                <!-- 
                        DEBUT DE LA PAGINATION DU TABLEAU 
                -->
                <div class="row">
                    <div id="tabs">
                        <ul class="nav nav-tabs">
                            <% int tab = 0;
                                if (session.getAttribute("tabs") != null) {
                                    tab = (Integer) session.getAttribute("tabs") - 1;
                                }
                            %>
                            <li role="presentation" <%if (tab == 0) {%>class="active"<%}%>  id ="presentation1"><a href="#tab1">Creer Equipe</a></li>
                            <li role="presentation" <%if (tab == 1) {%>class="active"<%}%>   id ="presentation2"><a href="#tab2">Modifier Equipe</a></li>
                            <li role="presentation"<%if (tab == 2) {%>class="active"<%}%> ><a href="#tab3">Supprimer Equipe</a></li>
                            <li role="presentation"<%if (tab == 3) {%>class="active"<%}%> ><a href="#tab4">Creer Sportif</a></li>

                        </ul>

                        <div class="row">
                            <br/>
                        </div>
                        <!--
                                ID TAB1 : CREER UNE EQUIPE
                        -->
                        <div id='tab1'>
                            <form class="form-horizontal">
                                <div class="well">
                                    Cette page vous permet de créer une équipe.
                                </div>
                                <h4><strong>L'equipe : </strong></h4>
                                <div class="row">
                                    <div class="form-group">
                                        <%  Equipe newEquipe = (Equipe) session.getAttribute("newEquipe");
                                            String categorie = "";
                                            String nomEquipe = null;
                                            if (newEquipe != null) {
                                                categorie = newEquipe.getCategorie();
                                                nomEquipe = newEquipe.getNomEquipe();
                                            }
                                        %>
                                        <label class='col-xs-3 control-label'>Délégation :</label>
                                        <div class='col-xs-6'>
                                            <select  <%if (newEquipe != null) {%> disabled="true" <%}%> class="form-control" id='selectionDelegationCreer' name='listDeleg'>

                                                <%
                                                    int i = 0;
                                                    ArrayList<Delegation> lesDelegations = (ArrayList<Delegation>) session.getAttribute("listDelegations");
                                                    ArrayList<Sportif> lesSportifs = (ArrayList<Sportif>) session.getAttribute("lesSportifs");
                                                    if (newEquipe == null) {
                                                %> <option value=''>Choix</option>
                                                <%
                                                    for (i = 0; i < lesDelegations.size(); i++) {
                                                        String pays = lesDelegations.get(i).getPays();
                                                %>          <option value='<%=pays%>'><%=pays%></option>
                                                <%
                                                    }
                                                } else {
                                                %> <option value='<%=newEquipe.getPays()%>'><%=newEquipe.getPays()%></option>
                                                <%}
                                                %>

                                            </select>
                                        </div>
                                        <div class='col-xs-1 control-label erreurForm'id="verifDelegationCreer"></div>    
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom:</label>
                                        <div class='col-xs-6'>
                                            <input type='text'   <%if (newEquipe != null) {%> disabled="true" <%}%>class='form-control' placeholder="Nom de l'équipe" id="nomEquipeCreer" <%if (nomEquipe != null) {%> value="<%= nomEquipe%>" <%}%>></input>
                                        </div>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class='form-group'>
                                        <label class='col-xs-3 control-label'>Categorie :</label>
                                        <div class="col-xs-6 " id="radioboutons" >
                                            <div class='radio-inline'>
                                                <label>
                                                    <input <%if (newEquipe != null) {%> disabled="true" <% }%> type="radio" name="radioType" value="masculin" <%if (categorie.equals("masculin") || categorie.equals("")) {%>checked<% }%> >
                                                    Masculin
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input <%if (newEquipe != null) {%> disabled="true" <%}%>type="radio" name="radioType" value="feminin" <%if (categorie.equals("feminin")) {%>checked<% }%> >
                                                    Feminin
                                                </label>  
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input <%if (newEquipe != null) {%> disabled="true" <%}%> type="radio" name="radioType" value="mixte" <%if (categorie.equals("mixte")) {%>checked<% }%>>
                                                    Mixte
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%  if (newEquipe == null) { %>          
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-6 col-xs-offset-3">
                                            <button type="button" class="btn btn-default btn-block" id="valCreer" >Valider</button>
                                        </div>
                                    </div>
                                </div>
                                <%
                                } else {
                                %>
                                <h4><strong>Les sportifs : </strong></h4>

                                <div id="ajout">
                                    <% for (i = 1; i <= newEquipe.getNbDeSportif(); i++) {
                                    %> <div id='divAjoutSportif1' class="form-group">
                                        <label class='col-xs-3 control-label'>Sportif <%=i%> :</label>
                                        <div class='col-xs-5'>
                                            <select  class="form-control"  name ="selectNomAjouter" id='selectNomAjouter<%=i%>'>
                                                <option value="">Choix</option>
                                                <%int j;
                                                    int idASelectinner;
                                                    if (newEquipe.getLesMembres().size() >= i) {
                                                        idASelectinner = newEquipe.getLesMembres().get(i - 1).getIdSportif();
                                                    } else {
                                                        idASelectinner = 0;
                                                    }
                                                    for (j = 0; j < lesSportifs.size(); j++) {
                                                        int idSportif = lesSportifs.get(j).getIdParticipant();
                                                        String affichage = idSportif + " : " + lesSportifs.get(j).getNom() + " " + lesSportifs.get(j).getPrenom();

                                                %>
                                                <option value="<%=idSportif%>"<%if (idSportif == idASelectinner) {%>selected="selected"<%}%> ><%=affichage%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class='col-xs-1'> <button   <% if (newEquipe.getNbDeSportif() == 2) {%>disabled="true"<%}%> type="button" class="btn btn-danger btn-block " id="valSupprimerSportif<%=i%>" name='valSupprimerSportif' value='<%=i%>'=><span class="glyphicon glyphicon-minus"></span></button></div>    
                                    </div>
                                    <%}%>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-offset-5 col-xs-2 erreurForm" id="ControlValAjouterSportif">

                                        </div>
                                        <div class="col-xs-2">
                                            <button  type="button" class="btn btn-primary btn-block " id="valAjouterSportif"><span class="glyphicon glyphicon-plus"></span></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="ValCreationEquipe">
                                    <div class="form-group">
                                        <div class="col-xs-2 col-xs-offset-3 ">
                                            <button type="button" class="btn btn-danger btn-block "  id="annulerCreerSpoptif" >Annuler</button>
                                        </div>
                                        <div class="col-xs-4">
                                            <button  type="button" class="btn btn-default btn-block " id="validerCreerEquipe">Creer Equipe</button>
                                        </div>
                                    </div>
                                </div> 
                                <%}%>

                            </form>
                        </div>

                        <!--
                                ID TAB2 : MODIFIER UNE EQUIPE
                        -->
                        <% Equipe equipeModif = (Equipe) session.getAttribute("modifEquipe");
                            ArrayList<Sportif> lesSportifsModif = (ArrayList<Sportif>) session.getAttribute("lesSportifsModif");
                            ArrayList<Equipe> lesEquipes = (ArrayList<Equipe>) session.getAttribute("lesEquipes");
                            String paysSelecte = "";
                            int equipeSelecte = 0;
                            if (equipeModif != null) {
                                paysSelecte = equipeModif.getPays();
                                equipeSelecte = equipeModif.getIdEquipe();
                            }
                        %>
                        <div id='tab2'>
                            <form class="form-horizontal" id="formModifierEquipe">
                                <div class="well">
                                    Cette page vous permet de modifier les élements d'une équipe : 
                                    ajouter un joueur, ou encore supprimer un joueur.
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Délégation :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" name='selectDelegationModifier' id='selectDelegationModifier'>
                                                <option value="1">Choix</option>
                                                <%
                                                    for (i = 0; i < lesDelegations.size(); i++) {
                                                        String pays = lesDelegations.get(i).getPays();
                                                %><option value='<%=pays%>' <% if (pays.equals(paysSelecte) || pays.equals(request.getAttribute("delegation"))) {%> selected<%}%>><%=pays%></option>
                                                <%
                                                    };
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Equipe :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectEquipeModifier' name="selectEquipeModifier"  <% if (lesEquipes == null) {%>disabled="true"<%}%>>
                                                <option value="">Choix</option>
                                                <%
                                                    if (lesEquipes != null) {
                                                        for (i = 0; i < lesEquipes.size(); i++) {
                                                            String nomEquipetemp = lesEquipes.get(i).getNomEquipe();
                                                            int idEquipetemp = lesEquipes.get(i).getIdEquipe();
                                                %>          <option value='<%=idEquipetemp%>' <% if (idEquipetemp == equipeSelecte) {%> selected<%}%>><%=idEquipetemp%> : <%=nomEquipetemp%> </option>
                                                <%
                                                        }
                                                    }
                                                %>

                                                <div id="choixEquipeModifier">
                                                </div>
                                            </select>
                                        </div>
                                    </div>
                                </div>


                                <!--
                                        AJOUTER UN SPORTIF DANS UNE EQUIPE
                                -->
                                <div id="ajoutModif">                         
                                    <%if (equipeModif != null) {
                                        for (i = 1; i <= equipeModif.getNbDeSportif(); i++) {%> 
                                    <div id='divAjoutSportif2' class="form-group">
                                        <label class='col-xs-3 control-label'>Sportif <%=i%> :</label>
                                        <div class='col-xs-5'>
                                            <select  class="form-control"  name ="selectModifier" id='selectNomModifier<%=i%>'>
                                                <option value="">Choix</option>
                                                <%int idASelectinner;
                                                    int j;
                                                    if (equipeModif.getLesMembres().size() >= i) {
                                                        idASelectinner = equipeModif.getLesMembres().get(i - 1).getIdSportif();
                                                    } else {
                                                        idASelectinner = 0;
                                                    }
                                                    for (j = 0; j < lesSportifsModif.size(); j++) {
                                                        int idSportif = lesSportifsModif.get(j).getIdParticipant();
                                                        String affichage = idSportif + " : " + lesSportifsModif.get(j).getNom() + " " + lesSportifsModif.get(j).getPrenom();
                                                %>
                                                <option value="<%=idSportif%>"<%if (idSportif == idASelectinner) {%>selected="selected"<%}%> ><%=affichage%></option>
                                                <%}%>
                                            </select>    
                                        </div>
                                        <div class='col-xs-1'> <button   <% if (equipeModif.getNbDeSportif() == 2) {%>disabled="true"<%}%> type="button" class="btn btn-danger btn-block " id="valSupprimerSportifModif<%=i%>" name='valSupprimerSportifModif' value='<%=i%>'><span class="glyphicon glyphicon-minus"></span></button></div>    
                                    </div>
                                    <%}%> 
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-offset-5 col-xs-2 erreurForm" id="ControlValAjouterSportifModif">

                                            </div>
                                            <div class="col-xs-2">
                                                <button  type="button" class="btn btn-primary btn-block " id="valAjouterSportifModif"><span class="glyphicon glyphicon-plus"></span></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" id="ValCreationEquipe">
                                        <div class="form-group">
                                            <div class="col-xs-2 col-xs-offset-3 ">
                                                <button type="button" class="btn btn-danger btn-block "  id="annulerEquipeModif" >Annuler</button>
                                            </div>
                                            <div class="col-xs-4">
                                                <button  type="button" class="btn btn-default btn-block " id="validerEquipeModif">Modifier Equipe</button>
                                            </div>
                                        </div>
                                    </div> 
                                    <%}%> 
                                </div>
                            </form>

                        </div>

                        <!--
                                ID TAB3 : SUPPRIMER UNE EQUIPE
                        -->
                        <div id='tab3'>
                            <form class="form-horizontal">
                                <div class="well">
                                    Cette page vous permet de supprimer une équipe. Attention ! Toute modification est irréversible.
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Délégation :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectDelegationSupp'>
                                                <option value="">Choix</option>
                                                <%
                                                    for (i = 0; i < lesDelegations.size(); i++) {
                                                        String pays = lesDelegations.get(i).getPays();
                                                %>      <option value='<%=pays%>'><%=pays%></option>
                                                <%
                                                    };
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Equipe :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectEquipeSupp'disabled="true">
                                                <option value="">Choix</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class="form-group">
                                        <div class='col-xs-offset-3 col-xs-6'>
                                            <button type="button" class="btn btn-danger btn-block" id="supprimerEquipe">Supprimer&nbsp;<span class="glyphicon glyphicon-trash"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div id="tab4">
                            <h2> #Under Construction</h2>
                        </div>
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
                $("#tabs").tabs({
                    active: <%=tab%>
                });
            });
        </script>
        <script type="text/javascript" src="js/cyberCompetition.js"></script>
        <script src="js/FormAdmin.js" type="text/javascript"></script>
    </body>
</html>
