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
        <link href="css/cssCyberCompetition.css" rel="stylesheet" type="text/css"/>
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
                                <li><a href="index.jsp">Acceuil</a></li>
                                <li><a href="#">Disciplines</a></li>
                                <li><a href="epreuves.html">Epreuves</a></li>
                                <li><a href='#'>Resultats</a></li>
                                <li><a href="#">Panier</a></li>
                                <li class='active'><a href='/WEB-INF/admin.jsp'>Admin</a></li>
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
                ${listDelegations.size()}
                <!-- 
                        DEBUT DE LA PAGINATION DU TABLEAU 
                -->
                <div class="row">
                    <div id="tabs">
                        <ul class="nav nav-tabs">
                            <li role="presentation" class="active"><a href="#tab1">Creer Equipe</a></li>
                            <li role="presentation"><a href="#tab2">Modifier Equipe</a></li>
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
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Délégation :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectionDelegationCreer' name='listDeleg'>
                                                <option value="">Choix</option>
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
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Nom:</label>
                                        <div class='col-xs-6'>
                                            <input type='text' class='form-control' placeholder="Nom de l'équipe">
                                        </div>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class='form-group'>
                                        <label class='col-xs-3 control-label'>Type :</label>
                                        <div class="col-xs-6">
                                            <div class='radio-inline'>
                                                <label>
                                                    <input type="radio" name="radioType" value="Masculin" checked>
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
                                            <button type="button" class="btn btn-default btn-block">Valider</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <!--
                                ID TAB2 : MODIFIER UNE EQUIPE
                        -->

                        <div id='tab2'>
                            <form class="form-horizontal">
                                <div class="well">
                                    Cette page vous permet de modifier les élements d'une équipe : 
                                    ajouter un joueur, ou encore supprimer un joueur.
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'>Délégation :</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" name='DelegationModifier' id='selectDelegationModifier'>
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
                                            <select class="form-control" id='selectEquipeModifier'>
                                                <option value="">Choix</option>
                                                <div>
                                                    <option id='Lyon'>Lyon</option>
                                                    <option>Paris</option>
                                                    <option>Grenoble</option>
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
                                            <select class="form-control" id='selectNomAjouter'>
                                                <option value="">Choix</option>
                                                <option value="JM Favre">JM Favre</option>
                                                <option>Marie Curie</option>
                                                <option>Jean Dujardin</option>
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
                                            <select class="form-control" id='selectDelegation'>
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
                                <div class='row'>
                                    <div class="form-group">
                                        <div class='col-xs-offset-3 col-xs-6'>
                                            <button type="button" class="btn btn-danger btn-block">Supprimer&nbsp;<span class="glyphicon glyphicon-trash"></span></button>
                                        </div>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class="form-group">
                                        <div class='col-xs-6 col-xs-offset-3'>
                                            <button type="submit" class="btn btn-primary btn-block">Valider modification &nbsp;<span class="glyphicon glyphicon-save"></span></button>
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
                                            <select class="form-control" id='selectEquipeSupp'>
                                                <option value="">Choix</option>
                                                <option>Lyon</option>
                                                <option>Paris</option>
                                                <option>Grenoble</option>
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
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#tabs").tabs();
            });

            $("li[role='presentation']").click(function () {
                $(this).addClass('active').siblings().removeClass('active');
            });
        </script>
        <script type="text/javascript" src="js/cyberCompetition.js" ></script>
    </body>
</html>
