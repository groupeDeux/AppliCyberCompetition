<%-- 
    Document   : admin
    Created on : 6 mars 2015, 14:22:31
    Author     : Gato
--%>
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
                        <h2 class="text-muted">CyberCompetition</h2>
                        <nav>
                            <ul class="nav nav-justified">
                                <li><a href="index.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder à l'acceuil">Acceuil</a></li>
                                <li><a href="GetListDiscipline" data-toggle="tooltip" data-placement="bottom" title="Acceder aux disciplines">Disciplines</a></li>
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves">Epreuves</a></li>
                                <li><a href='#' data-toggle="tooltip" data-placement="bottom" title="Acceder aux résultats des épreuves">Resultats</a></li>
                                <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li  class='active'><a href='GetListDelegation' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
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
                            <li role="presentation" class="active" id ="presentation1"><a href="#tab1">Creer Equipe</a></li>
                            <li role="presentation" id ="presentation2"><a href="#tab2">Modifier Equipe</a></li>
                            <li role="presentation"><a href="#tab3">Supprimer Equipe</a></li>
                            <li role="presentation"><a href="#tab4">Creer Sportif</a></li>
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
                                    Cette page vous permet de creer une équipe.
                                </div>
                                <h4><strong>L'equipe : </strong></h4>
                                <div class="row">
                                    <div class="form-group">
                                        
                                        <label class='col-xs-3 control-label'>Délégation :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectionDelegationCreer' name='listDeleg'>
                                                <option value=''>Choix</option>
                                                <%
                                                    int i = 0;
                                                    ArrayList<Delegation> lesDelegations = (ArrayList<Delegation>) request.getAttribute("listDelegations");
                                                    for (i = 0; i < lesDelegations.size(); i++) {
                                                        String pays = lesDelegations.get(i).getPays();
                                                %><option value='<%=pays%>'><%=pays%></option>
                                                <%
                                                    };
                                                %>
                                            </select>
                                        </div>
                                            <div class='col-xs-1 control-label'id="verifDelegationCreer"></div>    
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom:</label>
                                        <div class='col-xs-6'>
                                            <input type='text' class='form-control' placeholder="Nom de l'équipe" id="nomEquipeCreer">
                                        </div>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class='form-group'>
                                        <label class='col-xs-3 control-label'>Categorie :</label>
                                        <div class="col-xs-6 " id="radioboutons" >
                                            <div class='radio-inline'>
                                                <label>
                                                    <input  type="radio" name="radioType" value="Masculin" checked>
                                                    Masculin
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="radioType" value="Feminin">
                                                    Feminin
                                                </label>  
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="radioType" value="Mixte">
                                                    Mixte
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-6 col-xs-offset-3">
                                            <button type="button" class="btn btn-default btn-block" id="valCreer">Valider</button>
                                        </div>
                                    </div>
                                </div>
                                <h4 style="display: none" id="titreLesSportifs"><strong>Les sportifs : </strong></h4>
                                 <div id="ajout" hidden="true">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectNomAjouter1'>
                                                <option value="">Choix</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectNomAjouter2'>
                                                <option value="">Choix</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div id="nbSportif" hidden="true" value="2">2</div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-1 col-xs-offset-7">
                                            <button style="display: none" type="button" class="btn btn-danger btn-block " id="valSuprimerSportif" ><span class="glyphicon glyphicon-minus"></span></button>
                                        </div>
                                        <div class="col-xs-1">
                                            <button style="display: none" type="button" class="btn btn-primary btn-block " id="valAjouterSportif"><span class="glyphicon glyphicon-plus"></span></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="ValCreationEquipe" style="display: none">
                                    <div class="form-group">
                                        <div class="col-xs-3 ">
                                            <button type="button" class="btn btn-danger btn-block " id="annulerCreerSpoptif" >Annuler</button>
                                        </div>
                                        <div class="col-xs-6">
                                            <button  type="submit" class="btn btn-default btn-block " id="validerCreerSpotif">Creer Equipe</button>
                                        </div>
                                    </div>
                                </div> 
                            </form>
                        </div>

                        <!--
                                ID TAB2 : MODIFIER UNE EQUIPE
                        -->

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
                                                %><option value='<%=pays%>'><%=pays%></option>
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
                                            <select class="form-control" id='selectEquipeModifier' name="selectEquipeModifier" disabled="true">
                                                <option value="">Choix</option>
                                                <div id="choixEquipeModifier">
                                                </div>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <!--
                                    AJOUTER UN SPORTIF DANS UNE EQUIPE
                            -->



                            <form class="form-horizontal">

                                <h4>Ajouter un <strong>sportif</strong></h4>

                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom :</label>
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
                                SUPPRIMER UN SPORTIF DANS UNE EQUIPE
                            -->


                            <form class="form-horizontal">
                                <h4>Supprimer un <strong>sportif</strong></h4>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" selectNomAjouter='selectNomASuprimer'id='selectNomASuprimer'disabled="true">
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
                                                %><option value='<%=pays%>'><%=pays%></option>
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
                                            <button type="button" class="btn btn-danger btn-block">Supprimer&nbsp;<span class="glyphicon glyphicon-trash"></span></button>
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
            });
            
        </script>
       <script type="text/javascript" src="js/cyberCompetition.js"></script>
       <script src="js/FormAdmin.js" type="text/javascript"></script>
    </body>
</html>
