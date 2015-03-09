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

$("select[name='selectDelegationModifier']").on('change', function () {

    var element = document.getElementById("selectDelegationModifier");
    var strUser = element.options[element.selectedIndex].value;

    if (strUser !== "") {
        $("#selectEquipeModifier").load("GetListEquipe");
    }
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$("li[role='presentation']").click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});

function onVaFiltrer() {
    var $epreuves = $('div.row.rowEpreuve');
    var $buttons = $('#buttons'); 
    var tagged = {};

    $epreuves.each(function () {
        var img = this;
        var tags = $(this).data('tags');
        var bool = false;

        if (tags) {
            tags.split(',').forEach(function (tagName) {
                if (tagged[tagName] == null) {
                    tagged[tagName] = [];
                }
                tagged[tagName].push(img);
            });
        }
    });
    window.alert(tagged.toString());
};

