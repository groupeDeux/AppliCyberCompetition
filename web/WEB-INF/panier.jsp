<%-- 
    Document   : pannier
    Created on : 12 mars 2015
    Author     : M.Conte
--%>


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




                <%
                    Panier sessionPanier = (Panier) session.getAttribute("sessionPanier");
                    ArrayList<Epreuve> lesEpreuvesDuPanier = (ArrayList<Epreuve>) sessionPanier.getLesEpreuvesAuPanier();
                %>
                <!-- REPRISE DU CODE PAR GAETAN -->
                <div class='row'>
                    <div>
                        <ul class='nav nav-tabs'id='panierTab'>
                            <li role='presentation' class='active'><a href='#panierTabPanier' data-toggle='tab'>Panier</a></li>
                            <li role='presentation'><a href='#panierTabInformation' data-toggle='tab'>Informations</a></li>
                            <li role='presentation'><a href='#panierTabPaiement' data-toggle='tab'>Paiement</a></li>
                            <li role='presentation'><a href='#panierTabTerminerCommande' data-toggle='tab'>Terminer commande</a></li>
                        </ul>
                    </div>

                    <div class='tab-content'>

                        <!-- 
                            Panneau PANIER
                        -->

                        <div role="tabpanel" class='tab-pane active' id='panierTabPanier'>
                            <div class="row">
                                <br/>
                            </div>
                            <%-- Dans le cas ou il n'y a aucun éléments dans le panier --%>
                            
                            <%
                                
                                  if(lesEpreuvesDuPanier.size()==0){
                            %>
                            <div class='well text-center'>
                                <h4>Votre panier est vide</h4>
                            </div>
                        </div>
                            
                            <%
                                }else{
                            %>
                            <div class='well text-center'>
                                <h4>Votre panier contient <% out.print(lesEpreuvesDuPanier.size() + " élément(s) "); %></h4>
                            </div>
                            <table class="table table-bordered text-center">
                                <thead>
                                    <tr>
                                        <th>Produit</th>
                                        <th>Type</th>
                                        <th>Prix a l'unité</th>
                                        <th>Quantitée</th>
                                        <th>Total</th>
                                        <th>Supprimer</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int nElementPanier;
                                        double montantTotal = 0;
                                        ArrayList<String> lesTicketsDuPanier = (ArrayList<String>) sessionPanier.getListeAuPanier();
                                        ArrayList<Integer> leNombreDeTicketDuPanier = (ArrayList<Integer>) sessionPanier.getNombreDeBillet();
                                        for (nElementPanier = 0; nElementPanier < lesEpreuvesDuPanier.size(); nElementPanier++) {
                                            Epreuve epreuve = lesEpreuvesDuPanier.get(nElementPanier);
                                            String typeDeTicket = lesTicketsDuPanier.get(nElementPanier);
                                            int nbDeTicket = leNombreDeTicketDuPanier.get(nElementPanier);
                                            double montantEpreuve = epreuve.getTarif();
                                            montantEpreuve *= nbDeTicket;
                                            montantTotal += montantEpreuve;
                                    %>
                                    <tr>
                                        <td><%= epreuve.getNomEpreuve()%></td>
                                        <td><%= typeDeTicket%></td>
                                        <td><%= epreuve.getTarif()%>&nbsp;€</td>
                                        <td>
                                            <select id='panierQuantitee:<%=nElementPanier%>'>
                                                <%
                                                    int optionV = 0;
                                                    int maxTicket;
                                                    if (epreuve.getNbDePlace() > 10) {
                                                        maxTicket = 10;
                                                    } else {
                                                        maxTicket = epreuve.getNbDePlace();
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
                                        <th colspan='2'><%= montantTotal%>&nbsp;€</th>
                                    </tr>
                                </tfoot>
                            </table>
                            <button class='btn btn-danger' id='suppPanier'>Vider le contenu du panier&nbsp;<span  class='glyphicon glyphicon-trash'></span></button>
                            <button class='btn btn-default pull-right'>Valider le panier&nbsp;<span  class='glyphicon glyphicon-ok'></span></button>
                        </div>
                        <% }; %>








                        <!--
                            TABLEAU INFORMATIONS 
                        -->

                        <div  role="tabpanel" class='tab-pane' id='panierTabInformation'>
                            <div class="row">
                                <br/>
                            </div>
                            <div class='well text-center'>
                                <h4>
                                    Paiement de la commande
                                </h4>
                            </div>
                            <div class ="rowPanierFormulaire">

                                <br> 
                                <div class="container">

                                    <div class="row">  
                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label for="email">Nom du destinataire </label>
                                                <input type="email" class="form-control" id="email" placeholder="Saisissez l'identité du destinataire de la commande">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">  
                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label for="email">Prénom du destinataire </label>
                                                <input type="email" class="form-control" id="email" placeholder="Saisissez l'identité du destinataire de la commande">
                                            </div>
                                        </div>
                                    </div>  

                                    <div class="row">  
                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label for="email">Adresse de livraison </label>
                                                <input type="email" class="form-control" id="email" placeholder="Saisissez l'adresse de livraison">
                                            </div>
                                        </div>
                                    </div>  

                                    <div class="row">  
                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label for="email">Numéro de téléphone du destinataire </label>
                                                <input type="email" class="form-control" id="email" placeholder="Saisissez le numéro de téléphone du destinataire">
                                            </div>
                                        </div>
                                    </div>  

                                    <div>
                                        <strong>Choisissez votre mode de paiement:  </strong>
                                    </div>


                                    <br>

                                    <div class="row">

                                        <div class="col-xs-2">
                                            <div class="input-group">
                                                MasterCard
                                                <span class="input-group-addon">
                                                    <input type="radio" name='carte'>
                                                </span>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">

                                        <div class="col-xs-2">
                                            <div class="input-group">
                                                Visa
                                                <span class="input-group-addon">
                                                    <input type="radio" name='carte'>
                                                </span>
                                            </div>
                                        </div>

                                    </div>
                                    <br> 

                                    <!-- 
                                   formulaires de saisie
                                    -->  

                                    <div class="row">



                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label for="email">Nom et prénom du titulaire de la carte: </label>
                                                <input type="email" class="form-control" id="email" placeholder="Saisissez l'identité du titulaire de votre carte banquaire">
                                            </div>
                                        </div>



                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label>Date d'expiration de la carte: </label>
                                                <input class="form-control"  placeholder="Saisissez la date d'expiration de la carte (JJ/MM/AAAA)">
                                            </div>
                                        </div>



                                        <div class="col-xs-7">
                                            <div class="form-group">
                                                <label>Code secret: </label>
                                                <input type="password" class="form-control" placeholder="Saisissez le code secret de la carte">
                                            </div>
                                        </div>



                                        <!-- 
                                           formulaire suite: boutons validation et retour en arrière au panier  
                                        -->  


                                        <div class="col-xs-7">
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true">
                                                </span>
                                                Valider le paiement    
                                            </button>
                                        </div>
                                        <div class="col-xs-5">
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true">
                                                </span>
                                                Revenir au panier  
                                            </button>
                                            <br>
                                            <br>
                                        </div>

                                    </div>  
                                </div>
                            </div>  
                        </div>




                        <!--
    
                                TAB PAIEMENT
    
                        -->






                        <div class='tab-pane' role='tab-panel' id='panierTabPaiement'>
                            <div class="row">
                                <br/>
                            </div>
                            <div class='well text-center'>
                                <h4>
                                    Confirmation du paiement
                                </h4>
                            </div>

                            <div class ="rowPanierFormulaire">
                                <div class="container">
                                    <br>
                                    <div>
                                        <strong>Confirmez vous la commande de:  </strong>
                                    </div>
                                    <br>
                                </div>   
                            </div> 

                            <!-- 
                           Zone du tableau détaillant les achats
                            -->


                            <div class="rowPanier">
                                <div class="row" style="font-weight:bold">  
                                    <div class="col-xs-3  bordureDroite">PRODUIT</div>
                                    <div class="col-xs-3  bordureDroite">PRIX</div>
                                    <div class="col-xs-3  bordureDroite">QUANTITE</div>
                                    <div class="col-xs-3 ">TOTAL</div>
                                </div>
                            </div>

                            <!-- 
                             Contenu tableau détaillant les achats
                            -->
                            <div class="rowPanier">  
                                <div class="row ">
                                    <div class="col-xs-3  bordureDroite"><br>........</div>
                                    <div class="col-xs-3  bordureDroite"><br>........</div>
                                    <div class="col-xs-3  bordureDroite">........</div>
                                    <div class="col-xs-3"><br>........</div>
                                </div>
                            </div>  


                            <div class ="rowPanierFormulaire">
                                <div class="container">
                                    <br>
                                    <br>
                                    <div>
                                        <strong>Pour un montant total de: ........ </strong>
                                    </div>   

                                    <div class="row">    
                                        <div class="col-xs-3">
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-euro" aria-hidden="true">
                                                </span>
                                                Confirmer le paiement    
                                            </button>
                                        </div>

                                        <div class="col-xs-4">
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true">
                                                </span>
                                                Revenir au formulaire de paiement 
                                            </button>
                                        </div>

                                        <div class="col-xs-5">
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true">
                                                </span>
                                                Revenir au panier  
                                            </button>
                                            <br>
                                            <br>
                                        </div>
                                    </div>
                                </div> 
                            </div>   
                        </div>

                        <!--
                        
                        TAB TERMINER COMMANDE 
                        
                        -->
                        <div class='tab-pane' role='tab-panel' id='panierTabTerminerCommande'>
                            <div class="row">
                                <br/>
                            </div>
                            <div class='well text-center'>
                                <h4>
                                    Commande terminée
                                </h4>
                            </div>

                            <div class ="rowPanierFormulaire">
                                <div class="container">
                                    <br>
                                    <div>
                                        <strong>Félicitation, votre commande n°....... a bien été prise en compte. </strong>
                                    </div>
                                    <div>
                                        <strong>Votre commande vient de vous être envoyé sur votre boite mail. </strong>
                                    </div>
                                    <div class="row">    
                                        <div class="col-xs-3">
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true">
                                                </span>
                                                Revenir à la page d'acceuil    
                                            </button>
                                            <br>
                                            <br>
                                        </div>
                                        <br>
                                    </div>   
                                </div> 
                            </div>
                        </div>
                        <!-- FIN DE TERMINER COMMANDE -->

                    </div>


                    <!-- 
                         4EME PARTIE ACHAT TERMINE/ AFFICHAGE NUMERO TRANSACTION
                    -->  


                </div>             
            </div>
            <script src="js/bootstrap.js" type="text/javascript"></script>
            <script  src="js/cyberCompetition.js" type="text/javascript" ></script>
            <script src="js/jsPanier.js" type="text/javascript"></script>
    </body>
</html>

