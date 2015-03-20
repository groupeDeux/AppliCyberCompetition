<%-- 
    Document   : pannier
    Created on : 12 mars 2015
    Author     : M.Conte
--%>

<%@page import="java.util.Date"%>
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
        <!--<script type="text/javascript" src="js/jsPanier.js"></script>-->



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
                                <li><a href="GetListEpreuve" data-toggle="tooltip" data-placement="bottom" title="Acceder a la billetterie">Billetterie</a></li>
                                <li class='active'><a href="GetPanier" data-toggle="tooltip" data-placement="bottom" title="Acceder au panier">Panier</a></li>
                                <li><a href='admin.jsp' data-toggle="tooltip" data-placement="bottom" title="Acceder aux fonctions d'administration">Admin</a></li>
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

                <%-- Chargement des informations sur le contenu de du panier de la session ainsi que de l'utilisateur --%>


                <%
                    Utilisateur sessionUtilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
                    Panier sessionPanier = (Panier) session.getAttribute("sessionPanier");
                    ArrayList<Epreuve> lesEpreuvesDuPanier = (ArrayList<Epreuve>) sessionPanier.getLesEpreuvesAuPanier();
                    ArrayList<String> lesTicketsDuPanier = (ArrayList<String>) sessionPanier.getListeAuPanier();
                    ArrayList<Integer> leNombreDeTicketDuPanier = (ArrayList<Integer>) sessionPanier.getNombreDeBillet();
                %>

                <div id="tabs">
                    <div class='row'>
                        <ul class="nav nav-pills" id='panierTab' style="font-size:16px;">

                            <%
                                int valeurTab = (Integer) request.getAttribute("valeurTab");
                            %>

                                                                                   <li <%if (!sessionUtilisateur.isPaiementValider()) { %>id='courant'<% } %> role='presentation' class='<% if (valeurTab == 0) {%>active<%}
                                                                                   if (sessionUtilisateur.isPaiementValider()) { %>disabled<% } %>' ><a href='#panierTabPanier'  value='0'>Panier</a></li>
                                <li role='presentation' class='<% if (valeurTab == 1) {%>active <%}
                                if (!sessionUtilisateur.isPanierValider() || sessionUtilisateur.isPaiementValider()) { %>disabled<% } %>' value='1'><a  href='#panierTabInformation' >Informations</a></li>
                                <li role='presentation' class='<% if (valeurTab == 2) {%>active <%}
                                if (!sessionUtilisateur.isInfoValider() || sessionUtilisateur.isPaiementValider()) { %>disabled<% } %>' value='2'><a href='#panierTabPaiement'>Paiement</a></li>
                                <%--<li <%if (sessionUtilisateur.isPaiementValider()) { %>id='courant'<% } %> role='presentation' class='<% if (valeurTab == 3) {%>active <%}
                                                                                          if (!sessionUtilisateur.isPaiementValider()) { %>disabled<% } %>' value='3'><a  href='#panierTabTerminerCommande'  >Terminer commande</a></li>--%>
                        </ul>
                    </div>
                    <%-- 
                         Panneau PANIER
                    --%>
                    <div class='row'>
                        <div role="tabpanel" class='tab-pane active' id='panierTabPanier'>
                            <div class="row">
                                <br/>
                            </div>
                            <%-- 
                                    Dans le cas ou il n'y a aucun éléments dans le panier, on affiche pas le tableau ni les
                                    boutons qui permettrons de passser aux pages suivantes
                            --%>

                            <%
                                if (lesEpreuvesDuPanier.size() == 0) {
                            %>
                            <div class='well text-center'>
                                <h4>Votre panier est vide</h4>
                            </div>


                            <%
                            } else {
                            %>
                            <div class='well text-center'>
                                <h4>Votre panier contient <% out.print(lesEpreuvesDuPanier.size() + " élément(s) "); %></h4>
                            </div>

                            <%-- 
                                    Construction du tableau du panier, on fait une boucle sur les elements contenus dans sessionPanier
                                    La boucle se termine a la fin du tab pour une pagination interne a cette page
                            --%>

                            <table class="table table-bordered text-center">
                                <thead>
                                    <tr>
                                        <th>Produit</th>
                                        <th>Type</th>
                                        <th>Prix a l'unité</th>
                                        <th>Quantité</th>
                                        <th>Total</th>
                                        <th>Supprimer</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int nElementPanier;
                                        double mntTot = sessionPanier.montantTotal();
                                        for (nElementPanier = 0; nElementPanier < lesEpreuvesDuPanier.size(); nElementPanier++) {
                                            Epreuve epreuve = lesEpreuvesDuPanier.get(nElementPanier);
                                            String typeDeTicket = lesTicketsDuPanier.get(nElementPanier);
                                            int nbDeTicket = leNombreDeTicketDuPanier.get(nElementPanier);
                                            double montantEpreuve = epreuve.getTarif();
                                            montantEpreuve *= nbDeTicket;

                                    %>
                                    <tr>
                                        <td><div class="btn btn-default btn-block" data-toggle="popover" data-content="<% out.println("<strong>Epreuve : </strong>"+ epreuve.getNomEpreuve() +"<br><strong>Discipline : </strong>" + epreuve.getNomDiscipline() + "<br><strong>Catégorie : </strong>" + epreuve.getCategorie()+"<br><strong>Début : </strong>" + epreuve.getDateDebut() + "h00<br><strong>Fin : </strong>" + epreuve.getDateFin() + "h00") ;%>" ><%= epreuve.getNomEpreuve()%></div></td>
                                        <td><%= typeDeTicket%></td>
                                        <td><%= epreuve.getTarif()%>&nbsp;€</td>
                                        <td>
                                            <%-- Dans le cas ou le select est modifié, on appel une servlet ChangementQuantitee qui va modifier le nombre de billet dans la session --%>
                                            <select id='panierQuantitee:<%=nElementPanier%>'>
                                                <%
                                                    int optionV = 0;
                                                    int maxTicket;
                                                    int nbDePlacesDispo = epreuve.getNbDePlace() - epreuve.getNbPlaceAcheter();
                                                    if (nbDePlacesDispo > 10) {
                                                        maxTicket = 10;
                                                    } else {
                                                        maxTicket = nbDePlacesDispo;
                                                    }
                                                    for (optionV = 1; optionV <= maxTicket; optionV++) {
                                                %>
                                                <option value="<%= optionV%>" <% if (optionV == nbDeTicket) {
                                                        out.println("selected='selected'");
                                                    }%>><%= optionV%></option>
                                                <% };%>
                                            </select>
                                        </td>
                                        <td> <%= montantEpreuve%>&nbsp;€</td>
                                        <td>                            
                                            <button type="button" class="btn btn-danger" aria-label="Left Align" id='panierBtnSup:<%=nElementPanier%>'>
                                                <span class="glyphicon glyphicon-remove" aria-hidden="false"></span> 
                                            </button>
                                        </td>
                                    </tr>
                                    <% };%>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan='4' style='text-align:right;'>Montant total du panier=</th>
                                        <th colspan='2'><%= mntTot%>&nbsp;€</th>
                                    </tr>
                                </tfoot>
                            </table>
                            <%--
                                Deux boutons qui permettent de soit de supprimer le contenu du panier en entier,
                                pour cela on passe par une servlet supBillet, à l'aide d'une commande javaScript. Sinon on valide
                                les informations et donc on passe dans la servlet ValiderPanier qui va ensuite nous amener vers la prochaine tab
                            --%>

                            <button class='btn btn-danger' id='suppPanier'>Vider le contenu du panier&nbsp;<span  class='glyphicon glyphicon-trash'></span></button>
                            <button class='btn  pull-right <% if (!sessionUtilisateur.isPanierValider()) {%>btn-primary <%} else { %> btn-default <% } %>' id='validerPanier'>Valider le panier&nbsp;<span  class='glyphicon glyphicon-ok'></span></button>

                        </div>
                        <% };%>
                    </div>







                    <%--
                    
                        2EME TAB !!!!!!
                        TABLEAU INFORMATIONS 
                        
                    --%>
                    <div class='row'>
                        <div  role="tabpanel" class='tab-pane' id='panierTabInformation'>
                            <div class="row">
                                <br/>
                            </div>
                            <div class='well text-center'>
                                <h4>
                                    Paiement de la commande
                                </h4>
                            </div>
                            <form class="form-horizontal" name="formulaire" action="ValiderInformations" method="post" onSubmit="return verifform()">

                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Nom du destinataire :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="text" name="nom" placeholder="Saisissez le nom du destinataire de la commande" value="${param.nom}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Prénom du destinataire :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="text" name="prenom" placeholder="Saisissez le prénom du destinataire de la commande" value="${param.prenom}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Rue :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="text" name="rue" placeholder="Saisissez la rue du destinataire" value="${param.rue}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Numéro de rue :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="number" name="numRue" placeholder="Saisissez le numéro de rue du destinataire" value="${param.numRue}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Ville :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="text" name="ville" placeholder="Saisissez la ville du destinataire" value="${param.ville}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Adresse électronique :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="email" name="mail" placeholder="Saisissez l'adresse électronique du destinataire" value="${param.mail}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Numéro de téléphone :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="number" name="numTelephone" placeholder="Saisissez votre numéro de téléphone" value="${param.numTelephone}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Mode de paiement :</label>
                                    <div class="col-xs-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="carte" value="MasterCard">MasterCard
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="carte" value="Visa">Visa
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="carte" value="Autre">Autre
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Numéro de la carte :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="number" name="idCarte" placeholder="Saisissez le numéro de votre carte banquaire" value="${param.idCarte}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Date d'expiration de la carte :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="date" name="dateValidite" placeholder="Saisissez la date d'expiration de la carte (JJ/MM/AAAA)" value="${param.dateValidite}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">(*) Cryptogramme :</label>
                                    <div class="col-xs-6">
                                        <input class="form-control" type="number" name="codeSecret" placeholder="Saisissez le cryptogramme de la carte banquaire" value="${param.codeSecret}">
                                    </div>
                                </div>
                                <div class ='form-group'>
                                    <label class="col-xs-3 col-xs-offset-2 control-label" style="color:#D9534F;">(*) = champ obligatoire</label>
                                </div>
                                <div class='row'>
                                    <!--
                                    <div class="col-xs-2 col-xs-offset-1">
                                        <div class="btn btn-default"><a style="text-decoration:none;" href="#panierTabPanier">Page précédente&nbsp;<span class="glyphicon glyphicon-arrow-left"></span></a></div>
                                    </div>
                                    Ajout dans le bouton qui suis de col-xs-offset-3 , a enleve si on remet l'ancien bouton Page précédente
                                    -->
                                    <div class='col-xs-3 col-xs-offset-3'>
                                        <button type="reset" class="btn btn-danger btn-block">Effacer le contenu&nbsp;<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                                    </div>
                                    <div class='col-xs-3'>
                                        <button type="submit" class="btn btn-primary btn-block">Valider les informations&nbsp;<span class="glyphicon glyphicon-ok" aria-hidden="true"></span></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>



                    <!--

                            TAB PAIEMENT

                    -->





                    <div class='row'>
                        <div class='tab-pane' role='tab-panel' id='panierTabPaiement'>
                            <div class="row">
                                <br/>
                            </div>
                            <div class='well text-center'>
                                <h4>
                                    Confirmation du paiement
                                </h4>
                            </div>


                            <%--
                                On refait un tableau de confirmation de la commande avant de finaliser la commande, pour cela
                                on réutilise les variables utilisé dans le tab 1
                            --%>

                            <h4>Confirmez vous la commande suivante:</h4>
                            <table class="table table-bordered text-center">
                                <thead>
                                    <tr>
                                        <th>Produit</th>
                                        <th>Type</th>
                                        <th>Quantité</th>
                                        <th>Prix total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int nElementTableauFinal;
                                        double mntTot = sessionPanier.montantTotal();
                                        for (nElementTableauFinal = 0; nElementTableauFinal < lesEpreuvesDuPanier.size(); nElementTableauFinal++) {
                                            Epreuve epreuve = lesEpreuvesDuPanier.get(nElementTableauFinal);
                                            String typeDeTicket = lesTicketsDuPanier.get(nElementTableauFinal);
                                            int nbDeTicket = leNombreDeTicketDuPanier.get(nElementTableauFinal);
                                            double montantEpreuve = epreuve.getTarif();
                                            montantEpreuve *= nbDeTicket;
                                    %>
                                    <tr>
                                        <td><%= epreuve.getNomEpreuve()%></td>
                                        <td><%= typeDeTicket%></td>
                                        <td><%= nbDeTicket%></td>
                                        <td><%= montantEpreuve%>&nbsp;€</td>
                                    </tr>

                                    <%
                                        };
                                    %>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="3" style="text-align:right;">Montant total du panier=</th>
                                        <th style="text-align: center;"><%=mntTot%>&nbsp;€</th>
                                    </tr>
                                </tfoot>
                            </table>

                            <div class="paddingPanierValider">
                                <h4>Informations sur le destinataire :</h4>
                                <div class="row">
                                    <div class="col-xs-4 text-right"><strong>Nom du destinataire :</strong></div>
                                    <div class="col-xs-6"><%= sessionUtilisateur.getNom()%></div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-4 text-right"><strong>Prénom du destinataire :</strong></div>
                                    <div class="col-xs-6"><%= sessionUtilisateur.getPrenom()%></div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-4 text-right"><strong>Adresse électronique :</strong></div>
                                    <div class="col-xs-6"><%= sessionUtilisateur.getMail()%></div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-4 text-right"><strong>Mode de paiement :</strong></div>
                                    <div class="col-xs-6"><%= sessionUtilisateur.getTypeCarte()%></div>
                                </div>
                            </div>

                            <div class='row'>
                                <!--
                                <div class="col-xs-3 col-xs-offset-3">
                                    <div class="btn btn-default"><a  style="text-decoration:none;"  href="#panierTabInformation">Page précédente&nbsp;<span class="glyphicon glyphicon-arrow-left"></span></a></div>
                                </div>
                                Ajout de la classe col-xs-6 col-xs-offset-3 dans le bouton valider, a supprimer et remplacer par col-xs-3 si on met le boutton page précédente
                                -->
                                <div class='col-xs-6 col-xs-offset-3'>
                                    <button  class="btn btn-primary btn-block" data-toggle="tooltip" data-placement="right" title="La validation est définitive" id="validationPaiement">Valider le paiement&nbsp;<span class="glyphicon glyphicon-euro" aria-hidden="true"></span></button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- FIN DE COMMANDE -->
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
        <script  src="js/cyberCompetition.js" type="text/javascript" ></script>
        <script src="js/jsPanier.js" type="text/javascript"></script>
        <script>
                            $(document).ready(function () {
                                $("#tabs").tabs();
                                $("#tabs").tabs({
                                    active: <%= valeurTab%>
                                });
                            });
        </script>
    </body>
</html>

