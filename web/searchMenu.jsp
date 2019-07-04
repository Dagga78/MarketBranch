<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div class="pr-0 hide-on-med-and-down animate fadeLeft">
    <div class="card">
        <div class="card-content">
            <span class="card-title">Categories</span>
            <hr class="p-0 mb-10" style="border: solid 1px #cf6818 !important">
            <ul class="collapsible categories-collapsible"  style="box-shadow: none !important; border:0 !important">
                <c:forEach var="categorie" items="${listCategories}">
                    <li>
                        <div class="collapsible-header"  tabindex="0">${categorie.key.getNomCategorie()}<i class="material-icons">
                                keyboard_arrow_right </i></div>
                        <div class="collapsible-body" style="display: none;">
                            <ul class="collection">
                                <c:forEach var="ssCategorie" items="${categorie.value}">
                                    <p><a class="collection-item" style="cursor: pointer;" onclick="$('#button').val('filterSsCategorie');$('#ssCategorie').val('${ssCategorie.getIdSousCategorie()}');$('#form').submit();">${ssCategorie.getNomSousCategorie()}</a></p>
                                    </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <span class="card-title">Prix</span>
            <hr>
            <p class="range-field">
                <input type="range" id="test5" min="0" max="1000"><span class="thumb">Prix:<span class="value"></span>€</span>
            </p>
            <p>
                <button class="btn" style="width: 100%">Rechercher</button>
            </p>

        </div>
    </div>
</div>