<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html>
    <head>
        <%@ include file="header.jsp" %>

        <title>Accueil</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <form method="POST" action="home" id="form">
            <%@ include file="menu.jsp"%>
            <div class="row"></div>
            <div class="row"></div>
            <div  class="row">
                <div class="col s3">
                    <%@ include file="searchMenu.jsp" %>
                </div>
                <div class="col s12 l9">
                    <div class="row">
                        <c:forEach var="produit" items="${produits}">
                            <div class="col s12 m4 l4">
                                <div class="card animate fadeLeft" style="height: 500px">
                                    <div class="card-content">
                                        <div onclick="$('#selectedProduct').val('${produit.getIdProduit()}');$('#button').val('selectedProduct');$('#form').submit();" style="cursor: pointer;">
                                            <p>${produit.getIdVendeur().getNomVendeur()}</p>
                                            <span class="card-title text-ellipsis">${produit.getNameProduit()}</span>
                                            <img src="${produit.getImgProduit()}" class="responsive-img" alt="" style=" height: 300px">
                                        </div>
                                        <div class="row"></div>
                                        <div class="row">
                                            <h5 class="col s12 m12 l8 mt-3">${produit.getPrixStandartProduit()}€</h5>
                                            <a class="col s12 m12 l4 mt-2 waves-effect waves-light gradient-45deg-deep-purple-blue btn modal-trigger"
                                               href="#produit_${produit.getIdProduit()}">Apercu</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Modal Structure -->
                                <div id="produit_${produit.getIdProduit()}" class="modal" tabindex="0">
                                    <div class="modal-content pt-2">
                                        <div class="row" id="product-one">
                                            <div class="col s12">
                                                <a class="modal-close right"><i class="material-icons red-text">close</i></a>
                                            </div>
                                            <div class="col m6">
                                                <img src="${produit.getImgProduit()}" class="responsive-img">
                                            </div>
                                            <div class="col m6">
                                                <p>${produit.getIdVendeur().getNomVendeur()}</p>
                                                <h5>${produit.getNameProduit()}</h5>
                                                <p class="mt-3">${produit.getIdSousCategorieProduit().getIdCategorie().getNomCategorie()} / ${produit.getIdSousCategorieProduit().getNomSousCategorie()}</p>
                                                <hr class="mb-5">
                                                <p class="mt-3">${produit.getDescriptionProduit()}</p>
                                                <h5>${produit.getPrixStandartProduit()}€</h5>
                                                <a class="waves-effect waves-light btn marron-chart col s12 input-field" onclick="$('#selectedProduct').val('${produit.getIdProduit()}');$('#button').val('addPaner');$('#form').submit();">Ajouter au panier</a>
                                                <a class="waves-effect waves-light btn orange-chart col s12 input-field" onclick="$('#selectedProduct').val('${produit.getIdProduit()}');$('#button').val('selectedProduct');$('#form').submit();">Voir le produit</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
    <script>
                                                    $(document).ready(function () {
                                                        // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
                                                        $('.modal').modal();
                                                    });
    </script>
</body>
</html>
