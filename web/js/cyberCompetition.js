/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

