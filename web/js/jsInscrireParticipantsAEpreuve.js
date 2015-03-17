/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Page javaScript pour l inscription des participants aux epreuves
 * Ctrler GetListEquipe/GetListSportifs/GetListEpreuve  <--> InscrireParticipantsAEpreuves.jsp*/
$("#selectionEpreuveEquipe").on('change', function () {
    //recuperation de l'idEpreuve choisie
    var idEpreuve=document.getElementById("selectionEpreuveEquipe").value;
    // passer selecteur equipe a inscrire en true

    //$("#equipeInscrites").load("GetListEquipesInscritesEtCompatibles?idEpreuve="+idEpreuve);
    //
    // appel du controler pour calculer equipes deja inscrites et compatibles a l'inscription 
   document.location.href="GetListEquipesInscritesEtCompatibles?idEpreuve="+idEpreuve;
});

$("#selectionEpreuveIndividuelle").on('change', function () {
    //recuperation de l'idEpreuve choisi
    var idEpreuve=document.getElementById("selectionEpreuveIndividuelle").value;
    
     document.location.href="GetListSportifInscritEtCompatibles?idEpreuve="+idEpreuve;
});

$("#ajouterEquipe").on('click', function () {
   var idEpreuve=document.getElementById("selectionEpreuveEquipe").value;
   var idEquipe=document.getElementById("selectionEquipeAjouter").value;
   document.location.href="AjouterParticipation?idEpreuve="+idEpreuve+"&idEquipe="+idEquipe;
});

$("#supprimerEquipe").on('click', function () {
    alert();
   var idEpreuve=document.getElementById("selectionEpreuveEquipe").value;
   var idEquipe=document.getElementById("selectionEquipeSupprimer").value;
   document.location.href="SupprimerParticipation?idEpreuve="+idEpreuve+"&idEquipe="+idEquipe;
});

$("#ajouterSportif").on('click', function () {
    alert();
   var idEpreuve=document.getElementById("selectionEpreuveIndividuelle").value;
   var idSportif=document.getElementById("selectSportifAjouter").value;
   document.location.href="AjouterParticipation?idEpreuve="+idEpreuve+"&idSportif="+idSportif;
});