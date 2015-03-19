<%-- 
    Document   : infoEpreuve
    Created on : 19 mars 2015, 15:16:16
    Author     : fureta
--%>

<%@page import="CyberComp_G2.Model.ConsulterEpreuve.Resultat"%>
<%@page import="CyberComp_G2.Model.ConsulterEpreuve.Epreuve"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-default">
    <div class="panel-heading" data-toggle='collapse' href='#epreuvesParticipants${epreuveSelectionnee.idEpreuve}'>
        <h4>
            Liste des participants de l'épreuve
            <span class='pull-right glyphicon glyphicon-menu-down'></span>
        </h4>
    </div>
    <div id="epreuvesParticipants${epreuveSelectionnee.idEpreuve}" class="panel-collapse collapse">
        <div class='panel-body'>
            <jsp:include page="/ParticipantsDUneEpreuveHTML"/>
        </div>
    </div>
</div>

<div id="epreuvesMedailles${epreuveSelectionnee.idEpreuve}" class="panel panel-default">

</div>
<%
    Resultat resultatEpreuve = (Resultat) request.getAttribute("resultatEpreuve");
    if (resultatEpreuve == null) {
%>    
<div class="panel panel-default">
    <div class="panel-heading" data-toggle='collapse' href='#epreuvesBillets${epreuveSelectionnee.idEpreuve}'>
        <h4>
            Acheter un billet ou un ticket video.
            <span class='pull-right glyphicon glyphicon-menu-down'></span>
        </h4>
    </div>
    <div id="epreuvesBillets${epreuveSelectionnee.idEpreuve}" class="panel-collapse collapse">
        <div class='panel-body'>
            <form method="post" class="form-inline" action="AjoutPanier">
                <div class='col-xs-2'>
                    <div class='form-group' style="padding-top:6px;">
                        <strong>Prix: ${epreuveSelectionnee.tarif} €</strong>
                    </div>
                </div>
                <div class='col-xs-1'>
                    <div class='form-group'>
                        <div class="radio-inline" style="padding-top:6px;">
                            <input type="radio" name="epreuvesRadio" value="Billet:${epreuveSelectionnee.idEpreuve}" checked>
                            <label class='control-label'>Billet</label>
                        </div>
                    </div>
                </div>
                <div class='col-xs-2'>
                    <div class='form-group'>
                        <div class="radio-inline" style="padding-top:6px;">
                            <input type="radio" name="epreuvesRadio" value="TicketVideo:${epreuveSelectionnee.idEpreuve}">
                            <label class='control-label'>Ticket Video</label>
                        </div>
                    </div>
                </div>
                <div class='col-xs-4'>
                    <div class="form-group">
                        <label class='control-label'>Nombre de places:</label>
                        <select class="form-control" name='epreuvesNbPlaces'>
                            <%
                                Epreuve epreuveSelectionnee = (Epreuve) request.getAttribute("epreuveSelectionnee");
                                int optionValue = 0;
                                int maxTicket = 0;
                                int nbDePlacesDispo = epreuveSelectionnee.getNbDePlace() - epreuveSelectionnee.getNbPlaceAcheter();
                                if (nbDePlacesDispo > 10) {
                                    maxTicket = 10;
                                } else {
                                    maxTicket = nbDePlacesDispo;
                                }
                                for (optionValue = 1; optionValue <= maxTicket; optionValue++) {
                            %>
                            <option value="<%= optionValue%>"><%= optionValue%> </option>
                            <% };%>
                        </select>
                    </div>
                </div>
                <div class='col-xs-3'>
                    <div class='form-group'>
                        <button type="submit" class="btn btn-primary pull-right">Ajouter au Panier&nbsp;<span class="glyphicon glyphicon-shopping-cart"></span></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%
    } else {
        // l'epreuve à un résultat
%>
<div class="panel panel-default">
    <div class="panel-heading" data-toggle='collapse' href='#epreuveMedailles${epreuveSelectionnee.idEpreuve}'>
        <h4>
            Désolé cette épreuve n'est plus disponible à l'achat.
            <br/> Cliquez pour voir les résultats
            <span class='pull-right glyphicon glyphicon-menu-down'></span>
        </h4>
    </div>
    <div id="epreuveMedailles${epreuveSelectionnee.idEpreuve}" class="panel-collapse collapse">
        <div class='panel-body'>
            <<ul>
                <li>OR: <%=resultatEpreuve.getOr().asHTML()%></li>
                <li>ARGENT: <%=resultatEpreuve.getArgent().asHTML()%></li>
                <li>BRONZE: <%=resultatEpreuve.getBronze().asHTML()%></li>
            </ul>
        </div>
    </div>
</div>
<%
    
    }
%>