<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html>
    <head>
        <%@ include file="header.jsp" %>

        <title>Inscription</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script type="text/javascript" src="https://www.gstatic.com/firebasejs/6.1.0/firebase-app.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/firebasejs/6.1.0/firebase-firestore.js"></script>
        <script src="https://www.gstatic.com/firebasejs/6.1.1/firebase-storage.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <script type="text/javascript" src="js/init.js"></script>
    </head>
    <body>
        <form method="POST" action="inscription" id="form">
            <input type="hidden" value="none" id="selectedProduct" name="selectedProduct"/>
            <input type="hidden" value="none" id="button" name="button"/>
            <nav class="grey lighten-3"  style="box-shadow:none !important" >
                <div class="nav-wrapper ">
                    <a href="#" class="brand-logo"><img class="responsive-img" src="img/mb-logo2.png" width="200" height="40"></a>
                </div>
            </nav>
            <main>
                <center>
                    <div class="section"></div>

                    <h2  style="color: #3fbdad">Inscription</h2>
                    <div class="section"></div>

                    <div class='col s12'>
                        <div class="col s12 m12 l3">

                        </div>

                        <div class='input-field col s1'>

                        </div>
                        <div class='input-field col s3'>
                            <label for="nom">Nom </label>
                            <input class='validate' type='text' name='nom' id='nom'  />

                        </div>
                        <div class='input-field col s1'>

                        </div>
                        <div class='input-field col s4'>
                            <label for="prenom">Prénom </label>
                            <input  class='validate' type='text' name='prenom' id='prenom'  />

                        </div>

                        <div class='input-field col s3'>
                            <label for="nom">Mot de passe : </label>
                            <input class='validate' type='text' name='password' id='password'  />

                        </div>

                        <div class='input-field col s1'>

                        </div>
                        <div class='input-field col s8'>
                            <label for="email">Email</label>
                            <input  class='validate' type='email' name='email' id='email'
                                    />
                        </div>
                        <div class='input-field col s1'>

                        </div>
                        <div class='input-field col s8'>
                            <label for="Adresse">Adresse</label>
                            <input  class='validate' type='text' name='adresse' id='adresse'
                                    />

                        </div>


                    </div>

                    <div class="input-field col s6">

                        <i class="waves-effect waves-light btn waves-input-wrapper" style="">
                            <input type="button" value="Valider mes informations" class="waves-button-input" id="ButtonModify" onclick="$('#button').val('inscription'); $('#form').submit()">
                        </i>

                    </div>
                </center>

                <div class="section"></div>
                <div class="section"></div>
            </main>
        </form>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <script>
                                $(document).ready(function () {
                                    $('ul.tabs').tabs();
                                });
        </script>
    </body>
</html>
