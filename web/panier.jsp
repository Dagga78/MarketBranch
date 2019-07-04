<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html>
    <head>
        <%@ include file="header.jsp" %>

        <title>Accueil - ${user.getPrenomUtilisateur()}</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <form method="POST" action="home" id="form">
            <%@ include file="menu.jsp"%>
            <div class="row">
                <div class="col s12">
                    <h4 class="col s12">Votre Panier :</h4>
                </div>
                <div class="col s9">      
                    <c:set var = "salaryPanier" scope = "session" value = "0"/>
                    <c:forEach var="produit" items="${paniers}">
                        <div class="col s12 m4 l12">
                            <div class="card animate fadeLeft">
                                <div class="card-content">
                                    <div class="row">
                                        <div class="col s12 m12 l2">
                                            <img src="${produit.getIdProduit().getImgProduit()}" class="responsive-img" alt="">
                                        </div>
                                        <div class="col s12 m12 l8">
                                            <span class="card-title text-ellipsis">${produit.getIdProduit().getNameProduit()}</span>
                                            <h5>${produit.getIdProduit().getPrixStandartProduit()}€</h5>
                                        </div>
                                        <div class="col s12 l2 valign-wrapper center-align">
                                            <span class="col s12">x${produit.getQuantiteProduit()}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var = "salaryPanier" scope = "session" value = "${salaryPanier + (produit.getIdProduit().getPrixStandartProduit()*produit.getQuantiteProduit())}"/>

                    </c:forEach>
                </div>
                <div class="col s12 m12 l3">
                    <div class="card">
                        <div class="card-content">
                            <div class="row">
                                <div class="col s6">
                                    <h5 class="">Prix :</h5>
                                </div> 
                                <div class="col s6">
                                    <h5 class="red-text">${salaryPanier}€</h5>
                                </div> 
                                <p class="col s12" >
                                    <a class="waves-effect col s12 green darken-3 btn modal-trigger" >Payer</a>
                                </p>
                            </div>
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