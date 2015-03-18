// ------------------------------------- Creer Equipe -----------------------------------------------------------------

/*
*losrque clic sur valider equipe  apelle du controler GetlistDelegation si delegation selectionner sinon message erreur
*/
$("#valCreer").on('click', function () {
    
    var delegation = document.getElementById("selectionDelegationCreer").value;
    var nomEquipe = document.getElementById("nomEquipeCreer").value;
    var categorie = $('input:radio[name=radioType]:checked').val();
    if(delegation === "") { 
         document.getElementById("verifDelegationCreer").innerHTML="<span class='glyphicon glyphicon-remove'></span>";
    }else{
       document.getElementById("verifDelegationCreer").innerHTML="";
       document.location.href="GetListDelegation?tabs=1&delegation="+delegation+"&nomEquipe="+nomEquipe+"&categorie="+categorie;
    }
    
});

/*
 * lorsque clic sur creer equipe :Si tout les sportifs sont selectioner appelle de la servelt de qui  mets en BD sinon message d'erreur
 */
$("#validerCreerEquipe").on('click', function () {
   var nbSportif=1;
   var sportifsSontSelectionner= true;
   var url ="ModifierEquipe?Creation=true";
   $("select[name='selectNomAjouter']").each(function (){
       if($(this).val()===""){
            sportifsSontSelectionner = false ; 
       }else{
           url = url + "&sportifSelect"+nbSportif+"="+$(this).val();   
           nbSportif++;
       }
   });
   
   if (sportifsSontSelectionner === false){
       document.getElementById("ControlValAjouterSportif").innerHTML="Selectionner tout les sportifs";
   }else{
        document.location.href=url;
   }
});

/*
 * lorque selection sportif : si sportif deja present message d'erreur
 */
$("select[name='selectNomAjouter']").on('change',function (){
    var nbDeSportifIdentique =0;
    if($(this).val()!==""){
       var idSprotifChange =$(this).val();
      $("select[name='selectNomAjouter']").each(function (){
         if(idSprotifChange === $(this).val()){
             nbDeSportifIdentique++;
         }
      }); 
    }
    if (nbDeSportifIdentique >1){
        $(this).val("");
        document.getElementById("ControlValAjouterSportif").innerHTML="Sportif deja present";
    }else{
        document.getElementById("ControlValAjouterSportif").innerHTML="";
    }
});

/*
 * lorque annuler clic remisa zero session
 */
$("#annulerCreerSpoptif").on('click', function () {
    document.location.href="CloseSession";
});


/*
 * lorsque ajouter sportif clic : si tout les sportif selectionner envoie servelts modif equipe sinon message d'erreur
 */
$("#valAjouterSportif").on('click', function () {
   var nbSportif=1;
   var sportifsSontSelectionner= true;
   var url ="AddSportif?&mode=creerEquipe";
   $("select[name='selectNomAjouter']").each(function (){
       if($(this).val()===""){
            sportifsSontSelectionner = false ; 
       }else{
            url = url + "&sportifSelect"+nbSportif+"="+$(this).val();   
            nbSportif++;
       }
   });
   
   if (sportifsSontSelectionner === false){
       document.getElementById("ControlValAjouterSportif").innerHTML="Selectionner tout les sportifs";
   }else{
        document.location.href=url;
   }
});

/*
 * lorsque supprimer sportif clic : modifie l'equipe
 */
$("button[name='valSupprimerSportif']").on('click', function () {
    var nbSportif=1;
    var idSportifASuprimer = $("#selectNomAjouter"+$(this).val()).val();
    var url = "SupSportif?mode=creerEquipe&idSportifASuprimer="+idSportifASuprimer;
    $("select[name='selectNomAjouter']").each(function (){
       if($(this).val()===""){
       }else{
            url = url + "&sportifSelect"+nbSportif+"="+$(this).val();
            nbSportif++;
       }
   });
    document.location.href=url;
});


/*
 * lorque delegation changer : message d'erreur si pas de selection
 */
$("#selectionDelegationCreer").on('change', function () {
    var delegation = document.getElementById("selectionDelegationCreer").value;
    if(delegation === "") {
        document.getElementById("verifDelegationCreer").innerHTML="<span class='glyphicon glyphicon-remove'></span>";
    }else{
        document.getElementById("verifDelegationCreer").innerHTML="";
    }
});


//---------------------------------------------------Modif Equipe ------------------------------------------------


/*
 * lorsque clic sur creer equipe :Si tout les sportifs sont selectioner appelle de la servelt de qui  mets en BD sinon message d'erreur
 */
$("#validerEquipeModif").on('click', function () {
   var nbSportif=1;
   var sportifsSontSelectionner= true;
   var url ="ModifierEquipe?Creation=false";
   $("select[name='selectModifier']").each(function (){
       if($(this).val()===""){
            sportifsSontSelectionner = false ; 
       }else{
           url = url + "&sportifSelect"+nbSportif+"="+$(this).val();   
           nbSportif++;
       }
   });
   
   if (sportifsSontSelectionner === false){
       document.getElementById("ControlValAjouterSportifModif").innerHTML="Selectionner tout les sportifs";
   }else{
        document.location.href=url;
   }
});

/*
 * lorque selection sportif : si sportif deja present message d'erreur
 */
$("select[name='selectModifier']").on('change',function (){
    var nbDeSportifIdentique =0;
    if($(this).val()!==""){
       var idSprotifChange =$(this).val();
      $("select[name='selectModifier']").each(function (){
         if(idSprotifChange === $(this).val()){
             nbDeSportifIdentique++;
         }
      }); 
    }
    if (nbDeSportifIdentique >1){
        $(this).val("");
        document.getElementById("ControlValAjouterSportifModif").innerHTML="Sportif deja present";
    }else{
        document.getElementById("ControlValAjouterSportifModif").innerHTML="";
    }
});

/*
 * lorque annuler clic remisa zero session
 */
$("#annulerEquipeModif").on('click', function () {
  document.location.href="CloseSession";
});

/*
 * lorsque ajouter sportif clic : si tout les sportif selectionner envoie servelts modif equipe sinon message d'erreur
 */
$("#valAjouterSportifModif").on('click', function () {
   var nbSportif=1;
   var sportifsSontSelectionner= true;
   var url ="AddSportif?&mode=modifEquipe";
   $("select[name='selectModifier']").each(function (){
       if($(this).val()===""){
            sportifsSontSelectionner = false ; 
       }else{
           url = url + "&sportifSelect"+nbSportif+"="+$(this).val();   
           nbSportif++;
       }
   });
   
   if (sportifsSontSelectionner === false){
       document.getElementById("ControlValAjouterSportifModif").innerHTML="Selectionner tout les sportifs";
   }else{
        document.location.href=url;
   }
});

/*
 * lorsque supprimer sportif clic : modifie l'equipe
 */
$("button[name='valSupprimerSportifModif']").on('click', function () {
    var nbSportif=1;
    var idSportifASuprimer = $("#selectNomModifier"+$(this).val()).val();
    var url = "SupSportif?mode=modifEquipe&idSportifASuprimer="+idSportifASuprimer;
    $("select[name='selectModifier']").each(function (){
       if($(this).val()===""){
       }else{
            url = url + "&sportifSelect"+nbSportif+"="+$(this).val();
            nbSportif++;
       }
   });
    document.location.href=url;
});



/* Chargement des informations dans listequipe et listeAjoutSportif */
$("select[name='selectDelegationModifier']").on('change', function () {
    var delegation = document.getElementById("selectDelegationModifier").value;
    if (delegation !== "") {
        document.getElementById('selectEquipeModifier').options.length=1;
        document.getElementById("selectEquipeModifier").disabled = false;
        document.location.href="GetListEquipeDUneDelegation?delegation="+delegation;
        
    }
});


/* Chargement des informations dans listeSuprSportif */
$("#selectEquipeModifier").on('change', function (){
    var idEquipe = document.getElementById("selectEquipeModifier").value;
    if (idEquipe !== "") {
        document.location.href="initEquipeModif?idEquipe="+idEquipe;
    }
});

//---------------------------------------------Suprimer Equipe----------------------------------------------
    
/* Chargement des informations dans listequipe*/      
$("#selectDelegationSupp").on('change', function () {
   var delegation = document.getElementById("selectDelegationSupp").value;
   if (delegation !== "") {
        document.location.href="GetListEquipeSupprimable?delegation="+delegation; 
         
    }
});

/*
 * si equipe selectionner appel servlet de supresion equipe 
 */
$("#supprimerEquipe").on('click',function (){
    var idEquipeAsupprimer = $("#selectEquipeSupp").val();
    if(idEquipeAsupprimer != ""){
       document.location.href="SupprimerEquipe?idEquipe="+idEquipeAsupprimer; 
    }
    
});
