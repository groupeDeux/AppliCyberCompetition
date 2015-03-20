/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function surligne(champ, erreur)
{
    if (erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}


function verifPseudo(champ)
{
    if (champ.value.length < 1 || champ.value.length > 25)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}




function verifform()
{
    var msg ="";
    var verification = true;
    if (document.formulaire.nom.value == "")
    {
        document.formulaire.nom.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.prenom.value == "")
    {
        document.formulaire.prenom.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.rue.value == "")
    {
        document.formulaire.rue.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.numRue.value == "")
    {
        document.formulaire.numRue.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.mail.value == "") {
        document.formulaire.mail.style.backgroundColor = "#fba";
        verification = false;
    }
    /*
    var mail = $("input[name='mail']").val();
    $.post("verifIdCompte", {mail: mail}, function (message) {
        if (!message.startsWith("erreur")) {
            msg= msg +" Erreur d'adresse mail !";
            verification = false;
        }

    });
    */
    if (document.formulaire.ville.value == "")
    {
        document.formulaire.ville.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.numTelephone.value == "")
    {
        
        document.formulaire.numTelephone.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.idCarte.value == "")
    {
        document.formulaire.idCarte.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.dateValidite.value == "")
    {
        
        document.formulaire.dateValidite.style.backgroundColor = "#fba";
        verification = false;
    }
    if (document.formulaire.codeSecret.value == "")
    {
        
        document.formulaire.codeSecret.style.backgroundColor = "#fba";
        verification = false;
    }
    if ($('input[name="carte"]:checked').length == 0) {
        msg= msg + " Selectionnez un type de carte !";
        verification=false;
    }
    
    if(verification===false){alert("Champ manquant !" + msg); return false;}
    else{return true;};
}


/* Liens permettant de naviger dans les tabs du panier */
$("li[role='presentation']").on('click', function () {
    if ($(this).hasClass("disabled")) {
        $("#courant").tab('show');
        var tab = $("#courant").val();
        $("#tabs").tabs({
            active: tab
        });
    } else {
        var tab = $("#courant").val();
        $("#courant").prop("id", "presentation" + tab);
        $(this).prop("id", "courant");
    }

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

/* Lors du click, on redirige vers la servlet EnregistrementBdPanier */
$('#validationPaiement').click(function () {
    document.location.href = "EnregistrementBdPanier";
});

/* JS du popover du panier */
$('[data-toggle="popover"]').popover({
    title: 'Informations sur le billet',
    placement: 'right',
    html: 'true'
});
/*
function verification() {
    var fields = this.getElementsByClassName("form-control"),
            sendForm = true;
    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            fields[i].style.backgroundColor = "#ff0000";
            sendForm = false;
        }
        else {
            alert(fields[i].value);
            fields[i].style.backgroundColor = "#fff";
        }
    }
    if (!sendForm) {
        return false;
    }
}


$('form[name="formulaire"]').submit(function () {
    $('input', this).foreach(function () {
        if ($(this).val() == '') {
            $(this).css("background-color", "#FFFFFF");
        }
    });
});

*/
