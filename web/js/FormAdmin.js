/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#valCreer").on('click', function () {
    
    var delegation = document.getElementById("selectionDelegationCreer").value;
    var nomEquipe = document.getElementById("nomEquipeCreer").value;
    if(delegation === "") {    
    }else{
        //document.location.href="GetListEpreuve";
//        $("#tabs").tabs({
//            active: 1
//        });
//        $("#presentation1").removeClass('active');
//        $("#presentation2").addClass('active');
//        document.getElementById("selectDelegationModifier").value=delegation;
//        document.getElementById('selectEquipeModifier').options.length=1;
//        $("#selectEquipeModifier").append("<option value='newEquipe'>newEquipe : " + nomEquipe +"</option>");
//        document.getElementById("selectEquipeModifier").value="newEquipe";
//        document.getElementById("selectEquipeModifier").disabled=false;
//        $("#selectNomAjouter").load("GetListSportifParDelgation?delegation="+delegation);
//        document.getElementById('selectNomASuprimer').options.length=1;
//        document.getElementById("selectNomAjouter").disabled = false;
//        document.getElementById("selectNomASuprimer").disabled = false;
        var categorie = $('input:radio[name=radioType]:checked').val();
       document.getElementById('ajout').style.display="block";
        document.getElementById('valCreer').style.display="none";
        document.getElementById('radioboutons').innerHTML=categorie;
        document.getElementById("selectionDelegationCreer").disabled = true;
        document.getElementById('valAjouterSportif').style.display="block";
        document.getElementById('ValCreationEquipe').style.display="block";
         document.getElementById('titreLesSportifs').style.display="block";
        $("#selectNomAjouter1").load("GetListSportifParDelgation?delegation="+delegation);
        $("#selectNomAjouter2").load("GetListSportifParDelgation?delegation="+delegation);
    }
    
});

$("#annulerCreerSpoptif").on('click', function () {
    document.location.href="GetListDelegation";
});

$("#valAjouterSportif").on('click', function () {
    var nbSportif = document.getElementById("nbSportif").innerHTML;
    var delegation = document.getElementById("selectionDelegationCreer").value;
    nbSportif ++;
    document.getElementById('nbSportif').innerHTML=nbSportif;
    $("#ajout").append("<div class='form-group' id='divAjoutSportif"+nbSportif+"'><label class='col-xs-3 control-label'>Nom :</label><div class='col-xs-6'><select class='form-control' id='selectNomAjouter"+nbSportif+"'><option value=''>Choix"+nbSportif+"</option></select></div></div>");
    $("#selectNomAjouter"+nbSportif).load("GetListSportifParDelgation?delegation="+delegation);
    if(nbSportif>2){
       document.getElementById('valSuprimerSportif').style.display="block"; 
    }
});

$("#valSuprimerSportif").on('click', function () {
    var nbSportif = document.getElementById("nbSportif").innerHTML;
    
    v_div_parent = document.getElementById("ajout");
    v_div_enfant1 = document.getElementById("divAjoutSportif"+nbSportif);
    v_div_parent.removeChild(v_div_enfant1);
    nbSportif --;
    document.getElementById('nbSportif').innerHTML=nbSportif;
    if(nbSportif<3){
       document.getElementById('valSuprimerSportif').style.display="none";  
    }
});

$("#selectionDelegationCreer").on('change', function () {
    var delegation = document.getElementById("selectionDelegationCreer").value;
    if(delegation === "") {
        document.getElementById("verifDelegationCreer").innerHTML="<span class='glyphicon glyphicon-remove'></span>";
    }else{
        document.getElementById("verifDelegationCreer").innerHTML="<span class='glyphicon glyphicon-ok'></span>";
    }
});



/* Chargement des informations dans listequipe et listeAjoutSportif */
$("select[name='selectDelegationModifier']").on('change', function () {
    var delegation = document.getElementById("selectDelegationModifier").value;
    if (delegation !== "") {
        document.getElementById('selectEquipeModifier').options.length=1;
        $("#selectEquipeModifier").load("GetListEquipe?delegation="+delegation);
        document.getElementById("selectEquipeModifier").disabled = false;
    }
    if (delegation !== "") {
        document.getElementById('selectNomAjouter').options.length=1;
        $("#selectNomAjouter").load("GetListSportifParDelgation?delegation="+delegation);
        
    }
});


/* Chargement des informations dans listeSuprSportif */
$("#selectEquipeModifier").on('change', function (){
    var idEquipe = document.getElementById("selectEquipeModifier").value;
    if (idEquipe !== "") {
        //document.getElementById('selectNomASuprimer').options.length=1;
        $("#selectNomASuprimer").load("GetListSportifParEquipe?idEquipe="+idEquipe);
        document.getElementById("selectNomAjouter").disabled = false;
        document.getElementById("selectNomASuprimer").disabled = false;
    }
});


    
/* Chargement des informations dans listequipe    */      
$("#selectDelegationSupp").on('change', function () {
   var delegation = document.getElementById("selectDelegationSupp").value;
   if (delegation !== "") {
         document.getElementById('selectEquipeSupp').options.length=1;
         $("#selectEquipeSupp").load("GetListEquipe?delegation="+delegation);
         document.getElementById("selectEquipeSupp").disabled = false;
    }
});

//$("select[name='selectDelegationModifier']").on('change', function () {
//    var delegation = document.getElementById("selectDelegationModifier").value;
//    if (delegation !== "") {
//        $("#selectEquipeModifier").load("GetListEquipe?delegation="+delegation);
//        document.getElementById("selectEquipeModifier").disabled = false;
//    }
//    if (delegation !== "") {
//        $("#selectNomAjouter").load("GetListSportifParDelgation?delegation="+delegation);
//    }
//});