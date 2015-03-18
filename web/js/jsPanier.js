/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  function verifform()
  {
   if(document.formulaire.nom.value == "")
    {
     alert("Veuillez entrer votre nom!");
     document.formulaire.nom.focus();
     return false;
    }
    if(document.formulaire.prenom.value == "")
    {
     alert("Veuillez entrer votre prénom!");
     document.formulaire.prenom.focus();
     return false;
    }
   if(document.formulaire.adresse.value == "")
    {
     alert("Veuillez entrer votre adresse!");
     document.formulaire.adresse.focus();
     return false;
    }
   if(document.formulaire.email.value == "")
    {
     alert("Veuillez entrer votre adresse électronique!");
     document.formulaire.email.focus();
     return false;
    }
   if(document.formulaire.telephone.value == "")
    {
     alert("Veuillez entrer votre numéro de téléphone!");
     document.formulaire.telephone.focus();
     return false;
    }
    if(document.formulaire.titulaire.value == "")
    {
     alert("Veuillez entrer l'identité du titulaire de la carte banquaire!");
     document.formulaire.titulaire.focus();
     return false;
    }
    if(document.formulaire.idCarte.value == "")
    {
     alert("Veuillez entrer l'identifiant de la carte banquaire!");
     document.formulaire.idCarte.focus();
     return false;
    }
    if(document.formulaire.dateValidite.value == "")
    {
     alert("Veuillez entrer la date d'expiration de votre carte banquaire!");
     document.formulaire.dateValidite.focus();
     return false;
    }
    if(document.formulaire.codeSecret.value == "")
    {
     alert("Veuillez entrer le code secret de votre carte banquaire!");
     document.formulaire.codeSecret.focus();
     return false;
    }
   if(document.formulaire.email.value.indexOf('@') == -1)
    {
     alert("Ce n'est pas une adresse électronique valable!");
     document.formulaire.email.focus();
     return false;
    }
   var chkZ = 1;
   for(i=0;i<document.formulaire.telephone.value.length;++i)
     if(document.formulaire.telephone.value.charAt(i) < "0"
     || document.formulaire.telephone.value.charAt(i) > "9")
       chkZ = -1;
   if(chkZ == -1)
   {
     alert("La mention de votre numéro de téléphone n'est pas un nombre!");
     document.formulaire.telephone.focus();
     return false;
    }
    var chkz = 1;
   for(i=0;i<document.formulaire.codeSecret.value.length;++i)
     if(document.formulaire.codeSecret.value.charAt(i) < "0"
     || document.formulaire.codeSecret.value.charAt(i) > "9")
       chkz = -1;
   if(chkz == -1)
   {
     alert("L'identifiant de votre carte banquaire n'est pas un nombre!");
     document.formulaire.codeSecret.focus();
     return false;
    }
    var chkA = 1;
   for(i=0;i<document.formulaire.idCarte.value.length;++i)
     if(document.formulaire.idCarte.value.charAt(i) < "0"
     || document.formulaire.idCarte.value.charAt(i) > "9")
       chkA = -1;
   if(chkA == -1)
   {
     alert("La mention de votre code secret n'est pas un nombre!");
     document.formulaire.idCarte.focus();
     return false;
    }
  }
 

/* Liens permettant de naviger dans les tabs du panier */
$('a[href="#panierTabPanier"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

$('a[href="#panierTabInformation"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

$('a[href="#panierTabPaiement"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

$('a[href="#panierTabTerminerCommande"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});



/* Quand on clique sur un element supp du panier */

$(':button[id^="panierBtnSup"]').click(function(){
    document.location.href="SupBillet?numeroDuBillet="+$(this).attr('id');
    }
);

/* Supprimer tout le contenu du panier ! */

$('#suppPanier').click(function(){
    document.location.href="SupBillet?numeroDuBillet=TOUT";
    }
);

$('[id^="panierQuantitee"]').on('change',function(){
     var numeroDuBillet = $(this).attr('id');
     var quantitee = $(this).val();
     alert("NumberoDuBillet = " + numeroDuBillet.toString() + " et Quantitee: " + quantitee);
     document.location.href="changementQuantitee?numeroDuBillet="+numeroDuBillet+"&quantitee="+quantitee;
    }
);

/* numeroDuBillet="+numeroDuBillet+"&quantitee="+quantitee */

