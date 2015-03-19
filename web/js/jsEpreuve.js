/* 
 * La page jsEpreuve contient le code javascript utilisé par la page epreuve.jsp
 */

/* Prototype qui permet de capitalize les nom de type */
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
};

/* Gestion du filtre en fonction des data-tags situé dans le premier div d'une epreuve */
/* Création des variables pour la recherche de tags */
var $epreuves = $('div.row.rowEpreuve');
var $buttons = $('#epreuvesButtonsTags');
var tagged = {};

/* Recherche de tag dans les epreuves:
 * Les epreuves contiennent des 'tags' : data-tags
 * La recherche ce fait dans ces data-tags qui sont contenu dans la
 * row qui entoure chaque épreuve.
 * */
$epreuves.each(function () {
    var epreuve = this;
    var tags = $(this).data('tags');

    if (tags) {
        tags.split(',').forEach(function (tagName) {
            if (tagged[tagName] == null) {
                tagged[tagName] = [];
            }
            tagged[tagName].push(epreuve);
        });
    }
});



/* Creation du bouton 'Tout' dans l'id 'buttonsTags' */
$('<li role="presentation" id="epreuvesToutTag" class="active"><a href="#">Tout<span class="badge"></span></a></li>').appendTo($buttons);

/* Creation des autres boutons en fonction des tags */
$.each(tagged, function (tagName) {
    $('<li role="presentation" id="' + tagName + '"><a href="#">' + tagName.capitalize() + '&nbsp;<span class="badge">' +
            tagged[tagName].length + '</span></a></li>').appendTo($buttons);
});

$("#epreuvesToutTag").click(function () {
    $epreuves.show();
});

/* Fonction qui permet de faire une recherche dans les filtres quand
 * on clique sur un des boutons contenant un "tagName"
 * Attention, les boutons ont un ID : qui correspond à la catégorie*/
$('li[role="presentation"]').click(function () {
    if ($(this).attr("id") !== null && $(this).attr("id") !== "epreuvesToutTag") {
        $epreuves.hide().filter(tagged[$(this).attr("id")]).show();
    }
});


/* Fonction permettant de changer la classe d'un bouton lorsqu'on clic dessus */
$("li[role='presentation']").click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});




$('a').tooltip({
    'delay': {show: 1250, hide: 1000}
});



/*  Changement du pointeur au passage au dessus d'un data-toggle*/
/*$('div[data-toggle]:not([class])').hover */
$('div[data-toggle]').hover(
        function () {
            $(this).css('cursor', 'hand');

        },
        function () {
            $(this).css('cursor', 'pointer');

        }
);


/* Changement du glyphicon quand on clic tu l'un des affichages des epreuves  */
$('div[data-info]').click(
        function () {
            if ($(this).attr('data-info') === 'close') {
                $(this).attr('data-info', 'open');
                $(this).find('span').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-up').css('color','#337AB7');
                
            } else {
                $(this).attr('data-info', 'close');
                $(this).find('span').removeClass('glyphicon-menu-up').addClass('glyphicon-menu-down');
            }
            
        }
);

/* Changement du glyphicon quand on click sur les panel heading */
$('div.panel-heading[data-toggle]').click(function(){
     var glyph = $(this).find('span');
     if(glyph.attr('class')==='pull-right glyphicon glyphicon-menu-down'){glyph.attr('class','pull-right glyphicon glyphicon-menu-up');}
     else{glyph.attr('class','pull-right glyphicon glyphicon-menu-down');}
});

/* Affichage des tooltips : Permet de respect la charte graphique */
/* Code Javascript permettant d'afficher un tooltip via bootstrap */
$(function () {
    $('[data-toggle~="tooltip"]').tooltip();
});


/* Affichage du bouton pour remonter en haut */

  $(window).scroll(function(){
    var scrolled = $(window).scrollTop();
    if (scrolled > 400) $('#go-top').fadeIn('slow');
    if (scrolled < 200) $('#go-top').fadeOut('slow');
  });
  
  //Click event
  $('#go-top').click(function () {
    $("html, body").animate({ scrollTop: "0" },200);
  });
  
  
  
  /*Chargement de la liste des participant de l epreuve lorsqu'on click dessus*/
  $("div[name='selectEpreuve']").click(function (e) {
    var idEpreuve= $(this).attr("value"); 
    $('#infoEpreuve'+idEpreuve).load("GetListParticipantDUneEpreuve?idEpreuve="+idEpreuve);
  });



