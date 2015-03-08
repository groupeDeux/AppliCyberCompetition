/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("select[name='DelegationModifier").on('change', function () {
    $('#Lyon').text("Jean marie");
});

$("a[href='#tab4']").click(function () {
    $('#titreAdmin').text(" Sportifs");
});

$("a[href!='#tab4']").click(function () {
    $('#titreAdmin').text(" Equipes");
});

$("select[name='DelegationModifier").on('change', function(){
    var element = document.getElementById("SelectDelegationModifier");
    var strUser = element.options[element.selectedIndex].value;
    if(strUser!==""){
        $('select[id="selectEquipeModifier"] div').load("GetListEquipe",$('#selectEquipeModifier').serialize());
    }
});