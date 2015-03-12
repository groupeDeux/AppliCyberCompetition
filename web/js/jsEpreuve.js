/* 
 * La page jsEpreuve contient le code javascript utilisé par la page epreuve.jsp
 */



/* Gestion du filtre en fonction des data-tags situé dans le premier div d'une epreuve */
/* Création des variables pour la recherche de tags */
var $epreuves = $('div.row.rowEpreuve');
var $buttons = $('#buttonsTags');
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
$('<li role="presentation" id="toutTag" class="active"><a href="#">Tout<span class="badge"></span></a></li>').appendTo($buttons);

/* Creation des autres boutons en fonction des tags */
$.each(tagged, function (tagName) {
    $('<li role="presentation" id="' + tagName + '"><a href="#">' + tagName + '&nbsp;<span class="badge">' +
            tagged[tagName].length + '</span></a></li>').appendTo($buttons);
});

$("#toutTag").click(function () {
    $epreuves.show();
});

/* Fonction qui permet de faire une recherche dans les filtres quand
 * on clique sur un des boutons contenant un "tagName"
 * Attention, les boutons ont un ID : qui correspond à la catégorie*/
$('li[role="presentation"]').click(function () {
    if ($(this).attr("id") !== null && $(this).attr("id") !=="toutTag") {
        $epreuves.hide().filter(tagged[$(this).attr("id")]).show();
    }
});


/* Fonction permettant de changer la classe d'un bouton lorsqu'on clic dessus */
$("li[role='presentation']").click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});


/* Affichage des tooltips : Permet de respect la charte graphique */ 
/* Code Javascript permettant d'afficher un tooltip via bootstrap */
$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$('a').tooltip({
     'delay': { show: 1250, hide: 1000 }
});

/* Changement de l'information pour ouvrir une fenetre */
$('div.row.rowEpreuve').click(function(){
    var info = $(this).find('div[data-info]');
    if(info.attr('data-info')==='close'){
        info.addClass('in');
        info.attr('data-info','open');
    }else{
        info.removeClass('in');
        info.attr('data-info','close');
    }
});

/* Change l'icone si on est sur une epreuve ou non */
$('div.row.rowEpreuve').hover(function(){$(this).css('cursor','hand');},function(){$(this).css('cursor','pointer');});