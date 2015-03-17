<%-- 
    Document   : admin
    Created on : 6 mars 2015, 14:22:31
    Author     : Gato
--%>

<%@page import="CyberComp_G2.Model.ConstituerEquipe.Sportif"%>
<%@page import="CyberComp_G2.Model.ConsulterEpreuve.EpreuveIndividuelle"%>
<%@page import="CyberComp_G2.Model.ConstituerEquipe.Equipe"%>
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
                    <div id="tabs">      
                        <ul class="nav nav-tabs">
                            <!-- pour determiner l'onglet actif au hargement de la page -->
                             <% int activeTab = 1;
                                if (session.getAttribute("activeTab") != null) {
                                    activeTab = (Integer) session.getAttribute("activeTab");
                                }
                            %>
                            <!-- Onglet: choix Epreuve par equipe ou Epreuve individuelle-->
                            <li role="presentation"  <%if (activeTab==1){%>class="active" <%}%>id ="InscrireEquipeEpreuve"><a href="#tab1">Epreuves par équipe</a></li>
                            <li role="presentation" <%if (activeTab==2){%>class="active" <%}%> id ="inscrireSportifEpreuve"><a href="#tab2">Epreuves individuelles</a></li>
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
                                            <select class="form-control" id='selectionEpreuveEquipe' name='listEpreuvesEquipe'>
                                                <option value=''>Choix</option>
                                                <%
                                                    /*lorsqu'on a selectionne une epreuve on recupere son id 
                                                    et on affiche sa ligne dans le select = option selected*/
                                                    int idEpreuveSelec=0;
                                                    if(request.getAttribute("idEpreuveSelec")!=null){  
                                                        idEpreuveSelec= Integer.parseInt((String) request.getAttribute("idEpreuveSelec")); 
                                                    }
                                                    //liste d epreuve par equipe a afficher dans le select epreuves
                                                    ArrayList<EpreuveParEquipe> lesEpreuvesEquipe = (ArrayList<EpreuveParEquipe>) session.getAttribute("listEpreuveEquipe");
                                                    if (lesEpreuvesEquipe != null) {
                                                        int i = 0;
                                                        for (i = 0; i < lesEpreuvesEquipe.size(); i++) {
                                                            int idEpreuve = lesEpreuvesEquipe.get(i).getIdEpreuve();
                                                            String nomEpreuve = lesEpreuvesEquipe.get(i).getNomEpreuve();
                                                            String categorie = lesEpreuvesEquipe.get(i).getCategorie();
                                                        // on parcours la liste et si idEp=idEpSelectionné on affiche les infos dans le select= option selected
                                                %>      <option value='<%=idEpreuve%>' <% if(idEpreuve==idEpreuveSelec){%> selected <%}%> ><%= idEpreuve%> : <%=nomEpreuve%>  -  <%=categorie%> </option>
                                                <% };
                                                    } else {
                                                        // bouton fixe sur epreuve choisi = 
                                                        //EpreuveParEquipe EpreuveChoisi = (Epreuve) request.getAttribute("EpreuveChoisi");
                                                    }
                                                %>

                                            </select>

                                        </div>
                                        <div class='col-xs-1 control-label'id="verifEpreuveChoisie"> </div>    
                                    </div>
                                </div>
                            </form>

                            <!--
                                     AFFICHER LES EQUIPES DEJA INSCRITES A L' EPREUVE CHOISIE
                            -->


                            <h4> <strong> Les équipes inscrites </strong></h4>
                            <div id="equipeInscrites">
                                <%
                                    int j = 0;
                                    ArrayList<Equipe> lesEquipesInscrites = (ArrayList<Equipe>) session.getAttribute("listEquipesInscrites");
                                    if (lesEquipesInscrites != null) {
                                        if (lesEquipesInscrites.size() != 0) {
                                            for (j = 0; j < lesEquipesInscrites.size(); j++) {
                                                int idEquipe = lesEquipesInscrites.get(j).getIdEquipe();
                                                //String nomEquipe = lesEquipesInscrites.get(j).getNomEquipe();
                                                String pays = lesEquipesInscrites.get(j).getPays();  //methode de superClasse Participant

                                %> <div> <%=idEquipe%>  - <%=pays%>  </div>
                                <% }
                                            } else { %> 
                                <div> Aucun participant pour le moment !  </div>
                                <% }

                                } else { %>
                                <div> Pas d'épreuve choisie  </div>
                                <%
                                    }
                                %>

                            </div>

                            <!--
                                     AJOUTER UNE EQUIPE A L EPREUVE
                            -->

                            <form class="form-horizontal">

                                <h4> <strong> Ajouter une équipe </strong></h4>

                                <div class="row">
                                    <div class="form-group">
                                        <label class='col-xs-3 control-label'> Equipe:</label>
                                        <div class='col-xs-6'>
                                            
                                                <%
                                                    ArrayList<Equipe> lesEquipesCompatibles = (ArrayList<Equipe>) session.getAttribute("listEquipesCompatibles");
                                                    if (lesEquipesCompatibles != null) {
                                                        if (lesEquipesCompatibles.size() != 0) {
                                                            %><select class="form-control" id="selectionEquipeAjouter">
                                                            <option value="">Choix</option> <%
                                                            int i = 0;
                                                            for (i = 0; i < lesEquipesCompatibles.size(); i++) {
                                                                int idEquipe = lesEquipesCompatibles.get(i).getIdEquipe();
                                                                //String nomEquipe = lesEquipesCompatibles.get(i).getNomEquipe();
                                                                String categorie = lesEquipesCompatibles.get(i).getCategorie();
                                                                String pays= lesEquipesCompatibles.get(i).getPays();
                                                                %><option value='<%=idEquipe%>'><%= idEquipe%> : <%=pays%>  -  <%=categorie%>  </option>
                                                            <% };
                                                        } else {%>
                                                        <div> Aucune équipe ne peut être inscrite  </div>

                                                        <%}
                                                    }%>

                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-6 col-xs-offset-3">
                                            <button type="button" id="ajouterEquipe" class="btn btn-default btn-block">Ajouter &nbsp;<span class="glyphicon glyphicon-plus"></span></button>
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
                                    Cette page vous permet d'inscrire les sportifs à des épreuves individuelles
                                </div>
                                <div class="row">
                                    <div class="form-group">

                                        <label class='col-xs-3 control-label'>Epreuve:</label>
                                        <div class='col-xs-6'>
                                            <select class="form-control" id='selectionEpreuveIndividuelle' name='listEpreuveIndividuelle'>
                                                <option value=''>Choix</option>
                                                <%
                                                    ArrayList<EpreuveIndividuelle> lesEpreuvesIndividuelles = (ArrayList<EpreuveIndividuelle>) session.getAttribute("listEpreuvesInv");
                                                    if (lesEpreuvesEquipe != null) {
                                                        int i = 0;
                                                        for (i = 0; i < lesEpreuvesIndividuelles.size(); i++) {
                                                            int idEpreuve = lesEpreuvesIndividuelles.get(i).getIdEpreuve();
                                                            String nomEpreuve = lesEpreuvesIndividuelles.get(i).getNomEpreuve();
                                                            String categorie = lesEpreuvesIndividuelles.get(i).getCategorie();

                                                %>      <option value='<%=idEpreuve%>'><%= idEpreuve%> : <%=nomEpreuve%>  -  <%=categorie%>  </option>
                                                <% };
                                                    } else {
                                                        // bouton fixe sur epreuve choisi = 
                                                        //EpreuveParEquipe EpreuveChoisi = (Epreuve) request.getAttribute("EpreuveChoisi");
                                                    }
                                                %>

                                            </select>
                                            </select>
                                        </div>
                                        <div class='col-xs-1 control-label'id="verifEpreuveChoisie"> </div>    
                                    </div>
                                </div>
                                                   <!--
                                     AFFICHER LES SPORTIFS DEJA INSCRITS A L' EPREUVE CHOISIE
                            -->


                            <h4> <strong> Les sportifs qui sont déja inscrits </strong></h4>
                            <div id="sportifInscrit">
                                <%
                                    int i = 0;
                                    ArrayList<Sportif> lesSportifsInscrits = (ArrayList<Sportif>) session.getAttribute("listSportifsInscrits");
                                    if (lesSportifsInscrits != null) {
                                        if (lesSportifsInscrits.size() != 0) {
                                            for (i = 0; i < lesSportifsInscrits.size(); i++) {
                                                int idSportif = lesSportifsInscrits.get(i).getIdSportif();
                                                String nomSportif = lesSportifsInscrits.get(i).getNom();
                                                String prenomSportif = lesSportifsInscrits.get(i).getPrenom(); 

                                                %> <div><%=idSportif%>: <%=nomSportif%> <%=prenomSportif%> </div>
                                            <% }
                                        } else { %> 
                                            <div> Aucun sportif pour le moment !  </div>
                                    <% }

                                    } else { %>
                                        <div> Pas d'épreuve choisie  </div>
                                <%
                                    }
                                %>

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
                                            
                                                <%
                                                    ArrayList<Sportif> lesSportifsCompatibles = (ArrayList<Sportif>) session.getAttribute("listSportifsCompatibles");
                                                    if (lesSportifsCompatibles != null) {
                                                        if (lesSportifsCompatibles.size() != 0) {
                                                            %><select class="form-control" id='selectEqAjouter'>
                                                            <option value="">Choix</option> <%
                                                            int k = 0;
                                                            for (k = 0; k < lesSportifsCompatibles.size(); k++) {
                                                                int idSportif = lesSportifsCompatibles.get(k).getIdSportif();
                                                                String nomSportif = lesSportifsCompatibles.get(k).getNom();
                                                                String prenomSportif = lesSportifsCompatibles.get(k).getPrenom();
                                                %>          <option value='<%=idSportif%>'>:<%=prenomSportif%> -<%=nomSportif%> </option>
                                                             <%};
                                                        } else {%>
                                                        <div> Aucun sportif  ne peut être inscrit  </div>

                                                        <%}
                                                    }%>

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
                    <div id="tabs"> 


                        <footer class="footer">
                            <p class='text-muted pull-right'><i>m.a.j: 10/03/2015</i></p>
                            <p class="text-muted">&copy; Master 2 CCI Grenoble : Groupe2</p>
                        </footer>
                    </div>
                </div>
                <script>
                    $(document).ready(function() {
                        $("#tabs").tabs();
                        $("#tabs").tabs({
                            active: <%=activeTab-1%>
                });
                    });
                </script>
                <script type="text/javascript" src="js/cyberCompetition.js"></script>
                <script src="js/jsInscrireParticipantsAEpreuve.js" type="text/javascript"></script>
                </body>
                </html>

