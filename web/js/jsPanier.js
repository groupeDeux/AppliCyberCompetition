/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/* Liens permettant de naviger dans les tabs du panier */
$('a[href="#panierTabPanier"]').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});

$('a[href="#panierTabInformation"]').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});

$('a[href="#panierTabPaiement"]').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});

$('a[href="#panierTabTerminerCommande"]').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});



/* Quand on clique sur un element supp du panier */

$(':button[id^="panierBtnSup"]').click(function () {
    document.location.href = "SupBillet?numeroDuBillet=" + $(this).attr('id');
}
);

/* Supprimer tout le contenu du panier ! */

$('#suppPanier').click(function () {
    document.location.href = "SupBillet?numeroDuBillet=TOUT";
}
);

/* Validation du panier */
$('#validerPanier').click(function () {
    document.location.href = "ValiderPanier";
}
);

/* Lors du changement de valeur de billet dans le panier on appel la servlet changementQuantitee */
$('[id^="panierQuantitee"]').on('change', function () {
    var numeroDuBillet = $(this).attr('id');
    var quantitee = $(this).val();
    document.location.href = "changementQuantitee?numeroDuBillet=" + numeroDuBillet + "&quantitee=" + quantitee;
}
);


