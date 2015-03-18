<%-- 
    Document   : disciplines
    Created on : 10 mars 2015, 14:38:25
    Author     : Gato
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <li class='active'><a href="GetListDiscipline" data-toggle="tooltip" data-placement="bottom" title="Acceder aux disciplines">Disciplines</a></li>
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves">Epreuves</a></li>
                                <li><a href='#' data-toggle="tooltip" data-placement="bottom" title="Acceder aux résultats des épreuves">Resultats</a></li>
                                <li><a href="GetPanier" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li><a href='GetListDelegation' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <div class='row'>
                    <div class="page-header text-center">
                        <h2><small>Liste des</small> Disciplines</h2>
                    </div>
                </div>

                <div class='row'>
                    <%
                        int nDiscipline = 0;
                        ArrayList<String> lesDisciplines = (ArrayList<String>) request.getAttribute("listDisciplines");
                        for (nDiscipline = 0; nDiscipline < lesDisciplines.size(); nDiscipline++) {
                            String discipline = lesDisciplines.get(nDiscipline);
                    %>
                    <div class='col-xs-4'>
                        <div class='thumbnail'>
                            <img src="./img/image-media-<%=discipline%>T.jpg" alt=".." style="margin-top: 10px;" class="img-rounded">
                            <div class="caption">
                                <h3 class="text-center"><%=discipline%></h3>
                                <p>Une belle description d'une discipline se trouvera ici !</p>
                                <p><a href="#" class="btn btn-default btn-block" role='button' name="<%=discipline%>" data-toggle="tooltip" data-placement="bottom" title="Acceder aux épreuves de cette discipline">Liste des épreuves de <strong><%=discipline%></strong></a></p>
                            </div>
                        </div>
                    </div>
                    <% };%>
                </div>
                  <footer class="footer">
                            <%! Date dateDuJour;%>
                            <% dateDuJour = new Date();%>
                            <p class='text-muted pull-right'><i> Date de dernière mise à jour : <%= dateDuJour%></i></p>
                            <p class="text-muted">&copy; Master 2 CCI Grenoble : Groupe2</p>
                        </footer>
            </div>
        </div>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/cyberCompetition.js" ></script>
        <script>
            $('a[name]').click(function(){document.location.href="GetListEpreuve?epreuvesSelectDiscipline="+$(this).attr("name");});
        </script>
    </body>
</html>
