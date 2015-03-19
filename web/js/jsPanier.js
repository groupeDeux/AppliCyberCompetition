/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  function verifform()
  {
      
     if(document.formulaire.mail.value == ""){
        alert("Veuillez entrer votre adresse électronique!");
        document.formulaire.mail.focus();
        return false;
    }
    var mail = $("input[name='mail']").val();
    $.post("verifIdCompte",{ mail: mail},function(msg){
       if(msg!==""){
           alert(msg);
            return false;
       }
        
    });

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
   if(document.formulaire.rue.value == "")
    {
     alert("Veuillez entrer le nom de votre rue!");
     document.formulaire.rue.focus();
     return false;
    }
    if(document.formulaire.numRue.value == "")
    {
     alert("Veuillez entrer le numéro de votre rue!");
     document.formulaire.numRue.focus();
     return false;
    }
     if(document.formulaire.ville.value == "")
    {
     alert("Veuillez entrer votre ville!");
     document.formulaire.ville.focus();
     return false;
    }
  
   
    if(document.formulaire.numTelephone.value == "")
    {
     alert("Veuillez entrer votre numéro de téléphone!");
     document.formulaire.numTelephone.focus();
     return false;
    }
    if(document.formulaire.carte.value == "")
    {
     alert("Veuillez entrer votre type de carte banquaire!");
     document.formulaire.carte.focus();
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
    
   
    }
     /* 
   if(document.formulaire.email.value.indexOf('@') == -1)
    {
     alert("Ce n'est pas une adresse électronique valable!");
     document.formulaire.email.focus();
     return false;
    }
    
  
  
    /* 
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
 */ 


//$('a[href="#panierTabPanier"]').click(function (e) {
//    e.preventDefault();
//    $(this).tab('show');
//});
//
//$('a[href="#panierTabInformation"]').click(function (e) {
//    e.preventDefault();
//    $(this).tab('show');
//});
//
//$('a[href="#panierTabPaiement"]').click(function (e) {
//    e.preventDefault();
//    $(this).tab('show');
//});
//
//$('a[href="#panierTabTerminerCommande"]').click(function (e) {
//    e.preventDefault();
//    $(this).tab('show');
//});


/* Liens permettant de naviger dans les tabs du panier */
$("li[role='presentation']").on('click', function () {
            //$(this).preventDefault();
           if($(this).hasClass("disabled")){
               $("#courant").tab('show');
               var tab =$("#courant").val();
                $("#tabs").tabs({
                            active: tab
                 });
            }else{
                var tab =$("#courant").val();
                $("#courant").prop("id","presentation"+tab);
                $(this).prop("id","courant");
            }

 });

/* Quand on clique sur un element supp du panier */

$(':button[id^="panierBtnSup"]').click(function () {
    document.location.href = "SupBillet?numeroDuBillet=" + $(this).attr('id');
}
);

/* Supprimer tout le contenu du panier ! */

$('#suppPanier').click(function () {
    document.location.href = "SupBillet?numeroDuBillet=TOUT";
}
);

/* Validation du panier */
$('#validerPanier').click(function () {
    document.location.href = "ValiderPanier";
}
);


/* Lors du changement de valeur de billet dans le panier on appel la servlet changementQuantitee */
$('[id^="panierQuantitee"]').on('change', function () {
    var numeroDuBillet = $(this).attr('id');
    var quantitee = $(this).val();
    document.location.href = "changementQuantitee?numeroDuBillet=" + numeroDuBillet + "&quantitee=" + quantitee;
}
);

/* Lors du click, on redirige vers la servlet EnregistrementBdPanier */
$('#validationPaiement').click(function(){
    document.location.href="EnregistrementBdPanier";
});

/* JS du popover du panier */
$('[data-toggle="popover"]').popover({
      title:'Informations sur le billet',
      placement:'right',
      html: 'true'
});
