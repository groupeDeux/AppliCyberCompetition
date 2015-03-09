/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Change le nom du titre dans la page admin entre sportif et equipe */
$("a[href='#tab4']").click(function () {
    $('#titreAdmin').text(" Sportifs");
});

/* Change le nom du titre dans la page admin entre sportif et equipe */
$("a[href!='#tab4']").click(function () {
    $('#titreAdmin').text(" Equipes");
});


/* Chargement des informations dans listequipe et listeAjoutSportif */
$("select[name='selectDelegationModifier']").on('change', function () {
    var delegation = document.getElementById("selectDelegationModifier").value;
    if (delegation !== "") {
        $("#selectEquipeModifier").load("GetListEquipe?delegation="+delegation);
        document.getElementById("selectEquipeModifier").disabled = false;
    }
    if (delegation !== "") {
        $("#selectNomAjouter").load("GetListSportifParDelgation?delegation="+delegation);
    }
});
/* Chargement des informations dans listeSuprSportif */
$("#selectEquipeModifier").on('change', function () {
                var idEquipe = document.getElementById("selectEquipeModifier").value;
                if (idEquipe !== "") {
                    $("#selectNomASuprimer").load("GetListSportifParEquipe?idEquipe="+idEquipe);
                    document.getElementById("selectNomAjouter").disabled = false;
                    document.getElementById("selectNomASuprimer").disabled = false;
                 } 
});
/* Chargement des informations dans listequipe    */      
$("#selectDelegationSupp").on('change', function () {
   var delegation = document.getElementById("selectDelegationSupp").value;
   if (delegation !== "") {
         $("#selectEquipeSupp").load("GetListEquipe?delegation="+delegation);
         document.getElementById("selectEquipeSupp").disabled = false;
    }
});

/* Javascrip qui permet d'afficher un tooltip via bootstrap */
$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});



$('a').tooltip({
     'delay': { show: 1250, hide: 1000 }
});

$("li[role='presentation']").click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});