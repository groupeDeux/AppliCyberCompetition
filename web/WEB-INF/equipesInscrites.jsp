<%-- 
    Document   : equipesInscrites
    Created on : 13 mars 2015, 18:18:11
    Author     : magourar
--%>

<%@page import="CyberComp_G2.Model.ConstituerEquipe.Equipe"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int j = 0;
    ArrayList<Equipe> lesEquipesInscrites = (ArrayList<Equipe>) request.getAttribute("listEquipesInscrites");
    if (lesEquipesInscrites != null) {
        if (lesEquipesInscrites.size() != 0) {
            for (j = 0; j < lesEquipesInscrites.size(); j++) {
                int idEquipe = lesEquipesInscrites.get(j).getIdEquipe();
                //String nomEquipe = lesEquipesInscrites.get(j).getNomEquipe();
                String pays = lesEquipesInscrites.get(j).getPays();  //methode de superClasse Participant

%> <div><%=idEquipe%> : <%=idEquipe%>  - <%=pays%>  </div>
<% }
                                            } else { %> 
<div> Aucun participant pour le moment !  </div>
<% }

                                        } else { %>
<div> Pas d'épreuve choisie  </div>
<%
    }
%>
