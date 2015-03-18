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
   document.location.href="AjouterParticipationEquipe?idEpreuve="+idEpreuve+"&idEquipe="+idEquipe;
});

$("#supprimerEquipe").on('click', function () {
    alert();
   var idEpreuve=document.getElementById("selectionEpreuveEquipe").value;
   var idEquipe=document.getElementById("selectionEquipeSupprimer").value;
   document.location.href="SupprimerParticipationEquipe?idEpreuve="+idEpreuve+"&idEquipe="+idEquipe;
});
// si on clique sur le bouton identifié par id=ajouterSportif, on lance le controleur AjouterSportifEpreuve.java
$("#ajouterSportif").on('click', function () {
    alert();
   var idEpreuve=document.getElementById("selectionEpreuveIndividuelle").value;
   var idSportif=document.getElementById("selectionSportifAjouter").value;
   document.location.href="AjouterSportifEpreuve?idEpreuve="+idEpreuve+"&idSportif="+idSportif;
});

$("#supprimerSportif").on ('click', function (){
     alert();
   var idEpreuve=document.getElementById("selectionEpreuveIndividuelle").value;
   var idSportif=document.getElementById("selectionSportifSupprimer").value;
   document.location.href="SupprimerSportifEpreuve?idEpreuve="+idEpreuve+"&idSportif="+idSportif;
    
});