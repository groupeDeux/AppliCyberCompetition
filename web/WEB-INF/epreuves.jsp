<%-- 
    Document   : epreuve
    Created on : 8 mars 2015, 07:55:52
    Author     : vivi
--%>

<%@page import="java.util.Random"%>
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
                    <div class="masthead">
                        <h2 class="text-muted">CyberCompetition</h2>
                        <nav>
                            <ul class="nav nav-justified">
                                <li><a href="index.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder à l'acceuil">Acceuil</a></li>
                                <li><a href="GetListDiscipline" data-toggle="tooltip" data-placement="bottom" title="Acceder aux disciplines">Disciplines</a></li>
                                <li class='active'><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves">Epreuves</a></li>
                                <li><a href='#' data-toggle="tooltip" data-placement="bottom" title="Acceder aux résultats des épreuves">Resultats</a></li>
                                <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li><a href='GetListDelegation' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!--
                    RECHERCHE DANS LES EPREUVES
                -->
                <div class='row'>
                    <div class="page-header text-center">
                        <h2><small>Recherche d'</small> Epreuves</h2>
                    </div>
                </div>

                <div class='row'>
                    <form class='form' action='GetListEpreuve'>
                        <div class='col-xs-3'>
                            <div class='form-group'>
                                <!--Choix de la liste des disciplines disponible au début-->
                                <label>Discipline:</label>
                                <select class="form-control" name='selectDisciplineEpreuve' id='selectionDelegation'>
                                    <option value="">Choix</option>
                                    <%
                                      int nDiscipline=0;
                                      ArrayList<String> lesDisciplines = (ArrayList<String>) request.getAttribute("listDisciplines");
                                      for(nDiscipline=0; nDiscipline<lesDisciplines.size();nDiscipline++){
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
                        <h2><small>Liste des</small> Epreuves</h2>
                    </div>
                </div>
                <!--
                        NAVIGATION PAR TYPES
                -->
                <div class="row">
                    <ul class="nav nav-pills" id="buttonsTags">
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
                -->    <%
                    int i;
                    int lesId=0;
                    ArrayList<Epreuve> lesEpreuves = (ArrayList<Epreuve>) request.getAttribute("listEpreuveEquipe");
                    for (i = 0; i < lesEpreuves.size(); i++) {
                        Epreuve epreuveSelectionnee = lesEpreuves.get(i);
                        lesId=lesId+i;
                %>
                <div class="row rowEpreuve" data-tags="<%= epreuveSelectionnee.getCategorie()%>,Equipe">
                    <div class="media">
                        <div class="media-left">
                            <a href="#media<%=lesId%>" data-toggle="collapse" aria-expanded="false"><img class="media-object img-rounded" src='./img/image-media-<%=epreuveSelectionnee.getNomDiscipline()%>.jpg' alt='image de ski' data-toggle="tooltip" data-placement="top" title="Afficher informations supp."></a>
                        </div>

                        <div class='media-body'>
                            <h4 class='media-heading pull-right'><%=epreuveSelectionnee.getNomDiscipline()%></h4>
                            <h3 class='media-heading'><%=epreuveSelectionnee.getNomEpreuve()%><small>&nbsp;<%=epreuveSelectionnee.getCategorie()%></small></h3>
                            <h5 class='media-heading'>Debut: <%=epreuveSelectionnee.getDateDebut()%>h00</h5>
                            <h5 class='media-heading'>Fin: <%=epreuveSelectionnee.getDateFin()%>h00</h5>
                            <div class="collapse"  id="media<%=lesId%>">
                                <p>Epreuve entre la team A et la team B <br/>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.

                                    Donec sed odio dui. Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
                <%
                    
                    lesEpreuves = (ArrayList<Epreuve>) request.getAttribute("listEpreuveInd");
                    for (i = 0; i < lesEpreuves.size(); i++) {
                        Epreuve epreuveSelectionnee = lesEpreuves.get(i);
                        lesId=lesId+i;
                %>
                <div class="row rowEpreuve" data-tags="<%=epreuveSelectionnee.getCategorie()%>,Individuelle">
                    <div class="media">
                        <div class="media-left">
                            <a href="#media<%=lesId%>" data-toggle="collapse" aria-expanded="false"><img class="media-object img-rounded" src='./img/image-media-<%=epreuveSelectionnee.getNomDiscipline()%>.jpg' alt='image de ski' data-toggle="tooltip" data-placement="top" title="Afficher informations supp."></a>
                        </div>

                        <div class='media-body'>
                            <h4 class='media-heading pull-right'><%=epreuveSelectionnee.getNomDiscipline()%></h4>
                            <h3 class='media-heading'><%=epreuveSelectionnee.getNomEpreuve()%><small>&nbsp;<%=epreuveSelectionnee.getCategorie()%></small></h3>
                            <h5 class='media-heading'>Debut: <%=epreuveSelectionnee.getDateDebut()%>h00</h5>
                            <h5 class='media-heading'>Fin: <%=epreuveSelectionnee.getDateFin()%>h00</h5>
                            <div class="collapse"  id="media<%=lesId%>">
                                <p>Epreuve entre la team A et la team B <br/>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.

                                    Donec sed odio dui. Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
                <!--
                        FOOTER DE LA PAGE
                -->
                <footer class="footer">
                    <p class='text-muted pull-right'><i>m.a.j: 10/03/2015</i></p>
                    <p class="text-muted">&copy; Master 2 CCI Grenoble : Groupe2</p>
                </footer>
            </div>
        </div>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/cyberCompetition.js" type="text/javascript"></script>
        <script src="js/filtreEpreuve.js" type="text/javascript"></script>
    </body>
</html>
