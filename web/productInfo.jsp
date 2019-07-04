<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information Produit - ${produit.getNameProduit()}</title>
        <%@ include file="header.jsp" %>
    </head>
    <body>
        <form method="POST" action="home" id="form">
            <%@ include file="menu.jsp"%>
            <div class="row"></div>
            <div class="row"></div>
            <div>
                <button class="btn btn-flat marron-chart white-text">Retour</button>
            </div>
            <div class="row">
                <div class="col s12">
                    <div class="col s6">
                        <img class="responsive-img materialboxed" src="${produit.getImgProduit()}">
                    </div>
                    <div class="col s6">
                        <label>Nom :</label>
                        <h2>
                            ${produit.getNameProduit()}
                        </h2>
                        <label>Description :</label>
                        <p>
                            ${produit.getDescriptionProduit()}
                        </p>
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.materialboxed').materialbox();
            });
        </script>
    </body>
</html>
