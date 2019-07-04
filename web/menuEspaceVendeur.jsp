<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<input type="hidden" value="none" id="selectedProduct" name="selectedProduct"/>
<input type="hidden" value="none" id="button" name="button"/>
<input type="hidden" value="none" id="ssCategorie" name="ssCategorie"/>
<nav class="grey lighten-3"  style="box-shadow:none !important" >
    <div class="nav-wrapper ">
        <a onclick="$('#button').val('home');$('#form').submit();" class="brand-logo" style="cursor: pointer;"><img class="responsive-img" src="img/mb-logo2.png" width="200" height="40"></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a onclick="$('#button').val('vendeur');$('#form').submit();" class="grey-text">Accueil<i class="material-icons left grey-text">home</i></a></li>
            <li><a onclick="$('#button').val('pageVendeur');$('#form').submit();" class="grey-text">Mon compte<i class="material-icons left grey-text">people</i></a></li>
            <li><a onclick="$('#button').val('disconnect');$('#form').submit();" class="grey-text">Déconnexion<i class="material-icons left grey-text">cancel</i></a></li>
        </ul>
    </div>
</nav>
