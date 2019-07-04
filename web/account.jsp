<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <title>Mon compte</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <form method="POST" action="home" id="form">
            <%@ include file="menu.jsp"%>
            <div class="z-depth-1 row" style="padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
                <div class='row'>
                    <div class='col s12'>
                        <div class="col s12 m12 l3">
                            <img class="circle responsive-img" src="${user.getImgUtilisateur()}" width="200"
                                 height="200">
                        </div>

                        <div class='input-field col s1'>
                            <label>Nom : </label>
                        </div>
                        <div class='input-field col s3'>
                            <input disabled class='validate' type='text' name='nom' id='nom' value="${user.getNomUtilisateur()}" />

                        </div>
                        <div class='input-field col s1'>
                            <label>Prénom : </label>
                        </div>
                        <div class='input-field col s4'>
                            <input disabled class='validate' type='text' name='prenom' id='prenom' value="${user.getPrenomUtilisateur()}" />

                        </div>
                        <div class='input-field col s1'>
                            <label>Email : </label>
                        </div>
                        <div class='input-field col s8'>
                            <input disabled class='validate' type='email' name='email' id='email'
                                   value="${user.getMailUtilisateur()}" />
                        </div>
                        <div class='input-field col s1'>
                            <label>Adresse : </label>
                        </div>
                        <div class='input-field col s8'>
                            <input disabled class='validate' type='text' name='adresse' id='adresse'
                                   value="${user.getAdresseFacturation()}" />

                        </div>
                        <div class='input-field col s6'>
                            <a class="waves-effect waves-light btn">Ajouter une Carte Bancaire</a>
                        </div>
                        <div class='input-field col s6'>
                            <input type="button" value="Modifier mes informations"
                                   class="waves-effect waves-light btn" id="ButtonModify"
                                   onclick="$('#nom').prop('disabled', false), $('#prenom').prop('disabled', false), $('#number').prop('disabled', false), $('#adresse').prop('disabled', false), $('#ButtonModify').text('Valider mes informations')">
                        </div>
                        <div class="section">
                            <div class="col s12 m4" style="margin-top: 5ch">
                                <div class="card">
                                    <div class="card-content Black-text">
                                        <span class="card-title">Carte</span>
                                        <img class="responsive-img" src="img/carte.png">
                                    </div>
                                    <div class='row'>
                                        <div class='col s12'>

                                            <div class='input-field  col s4'>
                                                <label>code : </label>
                                            </div>
                                            <div class='input-field col s8'>
                                                <input disabled class='validate' type='text' name='code' id='code'
                                                       value="${sysBank.getNumeroCarte()}" />
                                            </div>

                                        </div>
                                    </div>
                                    <div class="card-action">
                                        <a class="waves-effect waves-light btn">Modifier</a>
                                        <a class="waves-effect waves-light btn">Supprimer</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col s12 m8" style="margin-top: 5ch">
                            <ul class="collection with-header">
                                <li class="collection-header">
                                    <h5>Vos commandes :</h5>
                                </li>
                                <li  style="border:0 !important;">
                                    <c:forEach var="commande" items="${commandes}">
                                        <ul class="collapsible" data-collapsible="accordion" style="box-shadow: none !important; border : 0 !important;;">
                                            <li>
                                                <div class="collapsible-header"><i class="material-icons">payment</i>Commande n°${commande.key.getIdCommande()}</div>
                                                <div class="collapsible-body">
                                                    <table>
                                                        <thead>
                                                            <tr>
                                                                <th>Nom du produit</th>
                                                                <th>Code promo</th>
                                                                <th>Prix Unitaire</th>
                                                                <th>Quantité</th>
                                                                <th>Transporteur</th>
                                                                <th>Etat</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="commandeDetail" items="${commande.value}">
                                                                <tr>
                                                                    <td>${commandeDetail.getIdProduit().getNameProduit()}</td>
                                                                    <td>${commandeDetail.getIdProduit().getProductCode()}</td>
                                                                    <td>${commandeDetail.getIdProduit().getPrixStandartProduit()}€</td>
                                                                    <td>${commandeDetail.getQuantiteProduit()}</td>
                                                                    <td>${commandeDetail.getIdTransporteur().getNomTransporteur()}</td>
                                                                    <td>${commandeDetail.getIdStatus().getNomStatusCommandeDetails()}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </li>
                                        </ul>
                                    </c:forEach>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <script types="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js"></script>
        <script>
                                                        $(document).ready(function () {
                                                            // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
                                                            $('.modal').modal();
                                                            $('.collapsible').collapsible();
                                                        });
        </script>
    </body>
</html>
