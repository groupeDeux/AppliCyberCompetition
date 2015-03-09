/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#valCreer").on('click', function () {
    var delegation = document.getElementById("selectDelegationModifier").value;
    if(delegation == "") {
        document.getElementById("verifDelegationCreer").innerHTML="<span class='glyphicon glyphicon-remove'></span>";
    }else{
        document.getElementById("verifDelegationCreer").innerHTML="<span class='glyphicon glyphicon-ok'></span>";
        //document.location.href="GetListEpreuve";
        $("#tabs").tabs({
            active: 1
        });
        $("#presentation1").removeClass('active');
        $("#presentation2").addClass('active');
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