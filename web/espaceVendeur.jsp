<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html>
    <head>
        <%@ include file="header.jsp" %>

        <title>Accueil</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script type="text/javascript" src="https://www.gstatic.com/firebasejs/6.1.0/firebase-app.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/firebasejs/6.1.0/firebase-firestore.js"></script>
        <script src="https://www.gstatic.com/firebasejs/6.1.1/firebase-storage.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <script type="text/javascript" src="js/init.js"></script>
        <script>
            $(document).ready(function () {
                // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
                $('.modal').modal();
                $(".button-collapse").sideNav({
                    edge: 'right',
                });
                $('ul.tabs').tabs();
                $('select').material_select();
                $('.chips').material_chip();
                $('.tooltipped').tooltip({
                    delay: 50
                });
                $('.carousel').carousel();
                $('.carousel.carousel-slider').carousel({fullWidth: true});
            });
        </script>
        <script>
            function addImg() {
                var input = document.getElementById('imgProduit');
                var file = input.files[0];
                var ref = store.ref('ImageMarketBranch/img_Produits/' + file.name);
                var uploadTask = ref.put(file);
                uploadTask.on('state_changed', function (snapshot) {
                    switch (snapshot.state) {
                        case firebase.storage.TaskState.PAUSED:
                            // console.log('Upload is paused'); 
                            break;
                        case firebase.storage.TaskState.RUNNING:
                            // console.log('Upload is running'); 
                            break;
                    }
                }, function (error) {
                    console.log(error);
                }, function () {

                    // get the uploaded image url back 
                    uploadTask.snapshot.ref.getDownloadURL().then(
                            function (downloadURL) {
                                $('#imgProduitUrl').val(downloadURL.toString());
                                $('#button').val('createProduct');
                                $('#form').submit();
                            });
                });
            }
        </script>
    </head>
    <body>
        <form method="POST" action="vendeur" id="form">
            <%@ include file="menuEspaceVendeur.jsp"%>
            <div class="row"></div>
            <div class="row"></div>
            <div  class="row">
                <div class="z-depth-1 grey lighten-4 col s12">
                    <div class="row"></div>
                    <div class="row">


                        <div class="col s4">
                            <img class="responsive-img" src="${vendeur.getImgVendeur()}">
                        </div>
                        <div class="col s2">
                            <h5 class="right-align">Adresse : </h5>
                        </div>
                        <div class="col s6">
                            <h5 class="left-align">${vendeur.getAdresseVendeur()} ${vendeur.getCityVendeur()}</h5>
                        </div>
                        <div class="col s2">
                            <h5 class="right-align">Email : </h5>
                        </div>
                        <div class="col s6">
                            <h5 class="left-align">${vendeur.getEmailVendeur()}</h5>
                        </div>
                        <div class="col s2">
                            <h5 class="right-align">Téléphone : </h5>
                        </div>
                        <div class="col s6">
                            <h5 class="left-align">${vendeur.getPhoneVendeur()}</h5>
                        </div>
                        <div class="col s2">
                            <h5 class="right-align">Description : </h5>
                        </div>
                        <div class="col s6">
                            <h5 class="left-align">${vendeur.getInformationVendeur()}</h5>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12"><h3>Vos produits : </h3></div>
                        <c:forEach var="produit" items="${produits}">
                            <div class="col s12 m4 l4">
                                <div class="card animate fadeLeft">
                                    <div class="card-content">
                                        <div onclick="$('#selectedProduct').val('${produit.getIdProduit()}');
                                                $('#button').val('selectedProduct');
                                                $('#form').submit();" style="cursor: pointer;">
                                            <p>${produit.getIdVendeur().getNomVendeur()}</p>
                                            <span class="card-title text-ellipsis">${produit.getNameProduit()}</span>
                                            <img src="${produit.getImgProduit()}" class="responsive-img" alt="">
                                        </div>
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
                                                <a class="waves-effect waves-light btn marron-chart col s12 input-field" onclick="$('#selectedProduct').val('${produit.getIdProduit()}');
                                                        $('#button').val('addPaner');
                                                        $('#form').submit();">Ajouter au panier</a>
                                                <a class="waves-effect waves-light btn orange-chart col s12 input-field" onclick="$('#selectedProduct').val('${produit.getIdProduit()}');
                                                        $('#button').val('selectedProduct');
                                                        $('#form').submit();">Voir le produit</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col s12 l9">
                    <div class="row">

                    </div>
                </div>
                <div class="fixed-action-btn toolbar">
                    <a class="btn-floating btn-large dark-purple orange-chart"> <i
                            class="large material-icons">settings</i>
                    </a>
                    <ul>
                        <li class="waves-effect waves-light tooltipped" data-position="top"
                            data-tooltip="Créer un nouveau produit"><a id="addProduct" href="#createProduct" class="modal-trigger"><i class="material-icons">add_circle</i></a></li>
                    </ul>
                </div>
                <input type="hidden" id="imgProduitUrl" name="imgProduitUrl">
                <div id="createProduct" class="modal">
                    <div class="modal-content">
                        <div class="row">
                            <input type="hidden" name="ref" id="ref" value="none" />
                            <div class="col s12"></div>
                            <h1>Créer un produit</h1>
                            <div class="input-field col s12">
                                <input id="nomProduit" name="nomProduit" type="text" class="validate" required="required">
                                <label for="nomProduit">Nom du produit</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="codeProduit" name="codeProduit" type="text" class="validate" required="required">
                                <label for="codeProduit">Code produit</label>
                            </div>
                            <div class="input-field col s12">
                                <textarea id="descriptionProduit" name="descriptionProduit" class="materialize-textarea"
                                          required="required"></textarea>
                                <label for="descriptionProduit">Description</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="prixProduit" name="prixProduit" type="number" class="validate" required="required">
                                <label for="prixProduit">Prix Standard du produit</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="quantiteProduit" name="quantiteProduit" type="number" class="validate" required="required">
                                <label for="quantiteProduit">Quantité unitaire</label>
                            </div>
                            <div class="col s6">
                                <label>Sous catégories</label> <select required="required" class="browser-default" id="ssCategorieProduit" name="ssCategorieProduit">
                                    <c:forEach var="ssCategorie" items="${listSsCategorie}">
                                        <option value="${ssCategorie.getIdSousCategorie()}">${ssCategorie.getNomSousCategorie()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="file-field input-field col s12">
                                <div class="btn dark-purple-outline">
                                    <span>Image</span> <input type="file" id="imgProduit"
                                                              accept="png,jpg,jpeg">
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                            <div class="col s12">
                                <a class="btn-flat col s12 dark-purple center-align modal-trigger"
                                   onclick="addImg();">Valider</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>