


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
                                <li ><a href="index.jsp" data-toggle="tooltip" data-placement="bottom" title="Acceder à l'accueil">Accueil</a></li>
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
                        <h2><small>Votre</small> Panier </h2>
                    </div>
                </div>
                
                 
               <!-- 
                   Elément barre progression achat en 4 étapes : Panier/Paiement/Confirmation commade... prévoir un truc pour pouvoir visualier à quelle étape l'acheteur se trouve (ex: colorer la zone)
                -->
                <div class="rowPanierBarre">
                    <div class='row' style="font-weight:bold"> 
                        <div class="col-xs-2"> Panier </div>
                        <div class="col-xs-1"><span class="glyphicon glyphicon-menu-right" aria-hidden="false"></span> </div>
                        <div class="col-xs-2"> Paiement </div>
                        <div class="col-xs-1"><span class="glyphicon glyphicon-menu-right" aria-hidden="false"></span> </div>    
                        <div class="col-xs-3"> Confirmation commande </div>  
                        <div class="col-xs-1"> <span class="glyphicon glyphicon-menu-right" aria-hidden="false"></span>  </div>
                        <div class="col-xs-2"> Terminer Achat </div>      
                    </div>
                </div>                
                
                                <br> 
                                <br> 
                                <br> 
                                        <!-- 
                                           message renseignant sur le nombre d'élément total mis au panier
                                        -->

                <div class="rowPanierBarre" style="font-weight:bold">
                 
                    <div class="row">
                        <br> 
                    <div class="col-xs-12">Votre panier contient XX éléments / est vide... </div>
                    </div>
                    <br>
                </div> 
                   
                
                
               <!-- 
                  Zone titre du "" des achats
                  -->
                <div class="rowPanier">
                    <div class="row" style="font-weight:bold">  
     
                        <div class="col-xs-3  bordureDroite">PRODUIT</div>
                        <div class="col-xs-2  bordureDroite">PRIX</div>
                        <div class="col-xs-3  bordureDroite">MODIFIER QUANTITE</div>
                        <div class="col-xs-2  bordureDroite">TOTAL</div>
                        <div class="col-xs-2"><p class="centered-text">SUPPRIMER</p></div>
                    </div>
                </div>
            
  
                   <!-- 
                  Zone du tableau détaillant les achats
                  -->
                <div class="rowPanier">  
                    <div class="row ">
                        <div class="col-xs-3  bordureDroite"><br>NomDuProduit 1</div>
                        <div class="col-xs-2  bordureDroite"><br>PrixALUnite</div>
                        <div class="col-xs-3  bordureDroite">
                            <div class="dropdown">
                                <div class="form-group">
                                    <label class='control-label'>Nombre de places:
                                                        <select class="form-control">
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                        </select> 
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-2  bordureDroite"><br>TOTAL ?</div>
                        <div class="col-xs-2">
                            <br>
                            <button type="button" class="btn btn-danger" aria-label="Left Align">
                               <span class="glyphicon glyphicon-remove" aria-hidden="false"></span> 
                            </button>
                        </div> 
                    </div>
                </div>
                    <!-- 
                  Zone du tableau détaillant les achats N2
                  -->
                  
                  
                  
                  
                <div class="rowPanier">  
                    <div class="row ">
                        <div class="col-xs-3  bordureDroite"><br>NomDuProduit 2</div>
                        <div class="col-xs-2  bordureDroite"><br>PrixALUnite</div>
                        <div class="col-xs-3  bordureDroite">
                            <div class="dropdown">
                                <div class="form-group">
                                    <label class='control-label'>Nombre de places:
                                                        <select class="form-control">
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                        </select> 
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-2  bordureDroite"><br>TOTAL ?</div>
                        <div class="col-xs-2">
                            <br>
                            <button type="button" class="btn btn-danger" aria-label="Left Align">
                               <span class="glyphicon glyphicon-remove" aria-hidden="false"></span> 
                            </button>
                        </div> 
                    </div>
                </div>
                  <!-- 
                   Zone calcul du montant total du panier ... 
                  -->
                  
                  <br>
                <div class="rowPanier">
                    <div class="row">
                          <div class="text-center" style="font-weight:bold">
                              <div class="col-xs-8 bordureDroite">Montant total du panier = </div>
                          </div>
                            <div class="col-xs-4"> XXXX ?</div>
                    </div>
                </div>
                  <br> 
                  <br>
                  <br> 
                   <!-- 
                   Zone contenant les éléments bouton vider panier + bouton Suivant alignés
                  -->
                <div class="row">
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-default" aria-label="Left Align" >
                            
                        Vider le contenu du panier      
                            <span class="glyphicon glyphicon-trash" aria-hidden="false"></span> 
                        </button>
                        </div>
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-default" aria-label="Left Align">
                        Passer au paiement de la commande    
                            <span class=" glyphicon glyphicon-ok" aria-hidden="false"></span>
                        </button>
                    </div>
                </div>
                <br> 
                <br> 
                <br> 
                  
                
                
                  <!-- 
                   Seconde partie du processus de paiement qui apparaitrait après validation 
                  -->
                  
                <div class ="rowPanierCadre">  
                  <div class="row">  
                    <div class="container">
                      <div class="col-xs-6">
                        <div><strong>Saisie des informations banquaires:   </strong></div>
                      </div>
                    </div>
                  </div> 
                </div> 
                  
                
                 
                 <!-- 
                   formulaires
                  -->   
                  <div class ="rowPanierFormulaire">
                        <br> 
                  <div class="container">
                      <div><strong>Choisissez votre mode de paiement:  </strong></div>
                        </div>
                  
                  <br>
                  
                        <div class="row">
                            <div class="container">
                              <div class="col-xs-2">
                                <div class="input-group">
                                    MasterCard
                                  <span class="input-group-addon">
                                    <input type="radio">
                                  </span>
                                 </div>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="container">
                              <div class="col-xs-2">
                                <div class="input-group">
                                    Visa
                                  <span class="input-group-addon">
                                    <input type="radio">
                                  </span>
                                </div>
                              </div>
                            </div>
                        </div>
                    <br> 
            <div class="row">
              <div class="container">
                <div class="col-xs-6">
                     <div class="form-group">
                        <label for="email">Nom et prénom du titulaire de la carte: </label>
                        <input type="email" class="form-control" id="email" placeholder="Saisissez le nom du titulaire de votre carte banquaire">
                    </div>
                </div>
              </div>
                
              <div class="container">
                <div class="col-xs-6">
                     <div class="form-group">
                        <label>Date d'expiration de la carte: </label>
                        <input class="form-control"  placeholder="Saisissez la date d'expiration de la carte (JJ/MM/AAAA)">
                    </div>
                </div>
              </div>
                
              <div class="container">
                <div class="col-xs-6">
                     <div class="form-group">
                        <label>Code secret: </label>
                        <input type="password" class="form-control" placeholder="Saisissez le code secret de la carte">
                    </div>
                </div>
              </div>
              
    
                
                
              <div class="container">
                <div class="col-xs-6">
                    <br>
                    <br>
                     <button type="submit" class="btn btn-default">Valider le paiement   <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></button>
                    </div>
                </div>
              </div>                    
                          
              <br>
            
            </div>
            </div>
                  
               
              
            <!--
                        FOOTER DE LA PAGE
                -->
            
                
                <footer class="footer">
                  <div>
                   
                  </div>
                </footer>  
            
                  
                 <!-- 
                   fin code
                -->
                
            
        </div>
        <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/cyberCompetition.js" ></script>
    </body>
</html>

