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
        <form method="POST" action="administration" id="form">
            <%@ include file="menu.jsp"%>
            <div class="section"></div>

            <h2 style="color: #3fbdad">Administration</h2>
            <div class="section"></div>




            <div class="z-depth-1 grey lighten-4 row" style="padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
                <div class="row">
                    <div class="card col s6">
                        <h4 class="center-align">Statistique</h4>
                        <hr class="col s12">
                    </div>
                    <div class="card col s6">
                        <h4 class="center-align">Nombre de ventes</h4>
                        <hr class="col s12">

                    </div>
                </div>


                <div class="row">
                    <div class="col s12 m12 l12">
                        <ul class="collapsible categories-collapsible">
                            <li>

                                <div class="collapsible-header" tabindex="0">Vendeur <i class="material-icons">
                                        keyboard_arrow_right </i></div>
                                <div class="collapsible-body">
                                    <div class="row">
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="input-field col s6">
                                                    <input id="nom_vendeur" type="text" class="validate">
                                                    <label for="nom_vendeur">Nom vendeur</label>
                                                </div>
                                                <div class="input-field col s6">
                                                    <input id="email_vendeur" type="text" class="validate">
                                                    <label for="email_vendeur">E-mail vendeur</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col s8">
                                                    <input id="adresse_vendeur" type="text" class="validate">
                                                    <label for="adresse_vendeur">Adresse</label>
                                                </div>
                                                <div class="input-field col s4">
                                                    <input id="city_vendeur" type="text" class="validate">
                                                    <label for="city_vendeur">Ville</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col s6">
                                                    <input id="phone_vendeur" type="tel" class="validate">
                                                    <label for="phone_vendeur">T�l�phone</label>
                                                </div>
                                                <div class="input-field col s6">
                                                    <input id="site_web" type="text" class="validate">
                                                    <label for="site_web">Site Web</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col s12">
                                                    <input id="Information_vendeur" type="tel" class="validate">
                                                    <label for="Information_vendeur">Information
                                                        supl�mentaire</label>
                                                </div>
                                            </div>
                                            <a class="waves-effect waves-light green darken-1 btn col s12">Ajouter
                                                un vendeur</a>
                                        </form>
                                    </div>

                                    <div class="row">
                                        <div class="col s12">
                                            <div class="row">
                                                <div class="input-field col s8">
                                                    <input id="nom_vendeur_modify" type="text" class="autocomplete">
                                                    <label for="nom_vendeur_modify">Nom Vendeur</label>
                                                </div>
                                                <div class="col s4"><a
                                                        class="waves-effect waves-light orange darken-3 btn col s12">Modifier
                                                        un
                                                        vendeur</a></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12">
                                            <div class="row">
                                                <div class="input-field col s8">
                                                    <input id="nom_vendeur_delete" type="text" class="autocomplete">
                                                    <label for="nom_vendeur_delete">Nom vendeur</label>
                                                </div>
                                                <div class=" col s4"><a
                                                        class="waves-effect waves-light red darken-4 btn col s12">Supprimer
                                                        un
                                                        vendeur</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12">
                                            <div class="row">
                                                <div class="input-field col s8">
                                                    <input id="nom_vendeur_suspend" type="text"
                                                           class="autocomplete">
                                                    <label for="nom_vendeur_suspend">Nom vendeur</label>
                                                </div>

                                                <div class="input-field col s4"><a
                                                        class="waves-effect waves-light red darken-4 btn col s12">Suspendre
                                                        un
                                                        vendeur</a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </li>
                            <li>
                                <div class="collapsible-header active" tabindex="0">Cat�gorie de
                                    produits<i class="material-icons">
                                        keyboard_arrow_right </i></div>
                                <div class="collapsible-body">
                                    <div class="row">
                                        <div class="row">
                                            <div class="col s12">
                                                <div class="row">
                                                    <div class="input-field col s8">
                                                        <input id="nom_categorie_add" name="nom_categorie_add" type="text"
                                                               class="validate">
                                                        <label for="nom_categorie_add">Nouvelle Cat�gorie</label>
                                                    </div>

                                                    <div class="input-field col s4"><a onclick="$('#button').val('addCategorie');$('#form').submit()"
                                                                                       class="waves-effect waves-light green darken-1 btn col s12">Ajouter
                                                            une
                                                            categorie de produits</a></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s12">
                                                <div class="row">
                                                    <div class="col s4">
                                                        <label>Cat�gories</label> <select required="required" class="browser-default" id="nom_categorie_categorie_addsouscat" name="nom_categorie_categorie_addsouscat">
                                                            <c:forEach var="categorie" items="${listCategorie}">
                                                                <option value="${categorie.getIdCategorie()}">${categorie.getNomCategorie()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="input-field col s4">
                                                        <input id="nom_categorie_addsouscat" name="nom_categorie_addsouscat" type="text"
                                                               class="validate">
                                                        <label for="nom_categorie_addsouscat">Nom cat�gorie de
                                                            sous
                                                            produit</label>
                                                    </div>
                                                    <div class="input-field col s4"><a
                                                            class="waves-effect waves-light green darken-1 btn col s12"  onclick="$('#button').val('addSsCategorie');$('#form').submit()">Ajouter
                                                            une
                                                            categorie de sous produits</a></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s12">
                                                <div class="row">
                                                    <div class="col s4">
                                                        <label>Cat�gories</label> <select required="required" class="browser-default" id="nom_categorie_modifycat" name="nom_categorie_modifycat">
                                                            <c:forEach var="categorie" items="${listCategorie}">
                                                                <option value="${categorie.getIdCategorie()}">${categorie.getNomCategorie()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="input-field col s4">
                                                        <input id="nom_categorie_modifycatnew" name="nom_categorie_modifycatnew" type="text">
                                                        <label for="nom_categorie_modifycatnew">Nouveau nom
                                                            categorie de
                                                            produit</label>
                                                    </div>
                                                    <div class="input-field col s4"><a
                                                            class="waves-effect waves-light orange darken-3 btn col s12" onclick="$('#button').val('modifyCategory');$('#form').submit()">Modifier
                                                            une Cat�gorie de
                                                            produits</a></div>
                                                </div>
                                            </div>
                                        </div>
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="col s12">
                                                    <div class="row">
                                                        <div class="input-field col s4">
                                                            <input id="nom_categorie_modifysouscat" type="text"
                                                                   class="autocomplete">
                                                            <label for="nom_categorie_modifysouscat">Categorie de
                                                                sous
                                                                produit</label>
                                                        </div>
                                                        <div class="input-field col s4">
                                                            <input id="nom_categorie_modifysouscatnew" type="text"
                                                                   class="autocomplete">
                                                            <label for="nom_categorie_modifysouscatnew">Nouveau nom
                                                                categorie de sous
                                                                produit</label>
                                                        </div>
                                                        <div class="input-field col s4"><a
                                                                class="waves-effect waves-light orange darken-3 btn col s12">Modifier
                                                                une Cat�gorie de sous
                                                                produits</a></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <div class="row">
                                            <div class="col s12">
                                                <div class="row">
                                                    <div class="col s7">
                                                        <label>Cat�gories</label> <select required="required" class="browser-default" id="nom_categorie_deletecat" name="nom_categorie_deletecat">
                                                            <c:forEach var="categorie" items="${listCategorie}">
                                                                <option value="${categorie.getIdCategorie()}">${categorie.getNomCategorie()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="input-field col s5"><a
                                                            class="waves-effect waves-light red darken-4 btn col s12" onclick="$('#button').val('deleteCategory');$('#form').submit()">Supprimer
                                                            une
                                                            cat�gorie de
                                                            produits</a></div>
                                                </div>
                                            </div>
                                        </div>
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="col s12">
                                                    <div class="row">
                                                        <div class="input-field col s7">
                                                            <input id="nom_categorie_deletesouscat" type="text"
                                                                   class="autocomplete">
                                                            <label for="nom_categorie_deletesouscat">Categorie de
                                                                sous
                                                                produit</label>
                                                        </div>
                                                        <div class="input-field col s5"><a
                                                                class="waves-effect waves-light red darken-4 btn col s12">Supprimer
                                                                une
                                                                cat�gorie de sous produits</a></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                            </li>
                            <li>

                                <div class="collapsible-header" tabindex="0">Entrep�t<i class="material-icons">
                                        keyboard_arrow_right </i></div>
                                <div class="collapsible-body">
                                    <div class="row">
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="input-field col s3">
                                                    <input id="libelle_entrep�t" type="text" class="validate">
                                                    <label for="libelle_entrep�t">Nom entrep�t</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <input id="adresse_entrep�t" type="text" class="validate">
                                                    <label for="adresse_entrep�t">Adresse entrep�t</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <input id="city_entrep�t" type="text" class="validate">
                                                    <label for="city_entrep�t">Ville entrep�t</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <a class="waves-effect waves-light green darken-1 btn col s12">Ajouter
                                                        un entrep�t</a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row">
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="input-field col s2">
                                                    <input id="libelle_entrep�t_modify" type="text"
                                                           class="autocomplete">
                                                    <label for="libelle_entrep�t_modify">Nom entrep�t �
                                                        modifier</label>
                                                </div>
                                                <div class="input-field col s2">
                                                    <input id="libelle_entrep�t_modifyNew" type="text"
                                                           class="validate">
                                                    <label for="libelle_entrep�t_modifyNew">Nouveau nom
                                                        entrep�t</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <input id="adresse_entrep�t_modify" type="text"
                                                           class="validate">
                                                    <label for="adresse_entrep�t_modify">Adresse entrep�t</label>
                                                </div>
                                                <div class="input-field col s2">
                                                    <input id="city_entrep�t_modify" type="text" class="validate">
                                                    <label for="city_entrep�t_modify">Ville entrep�t</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <a class="waves-effect waves-light orange darken-1 btn col s12">Modifier
                                                        un entrep�t</a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row">
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="input-field col s9">
                                                    <input id="libelle_entrep�t_delete" type="text"
                                                           class="autocomplete">
                                                    <label for="libelle_entrep�t_delete">Nom entrep�t �
                                                        Supprimer</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <a class="waves-effect waves-light red darken-4 btn col s12">Supprimer
                                                        un
                                                        Entrep�t</a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </li>
                            <li>

                                <div class="collapsible-header" tabindex="0">Utilisateur <i class="material-icons">
                                        keyboard_arrow_right </i></div>
                                <div class="collapsible-body">
                                    <div class="row">
                                        <form class="col s12">
                                            <div class="row">
                                                <div class="input-field col s9">
                                                    <input id="libelle_entrep�t_delete" type="text"
                                                           class="autocomplete">
                                                    <label for="libelle_entrep�t_delete">Nom utilisateur �
                                                        suspendre</label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <a class="waves-effect waves-light red darken-4 btn col s12">Suspendre
                                                        un
                                                        utilisateur</a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </form>
    </div>

</form>
</body>
</html>
