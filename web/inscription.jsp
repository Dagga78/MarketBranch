<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html>
    <head>
        <%@ include file="header.jsp" %>

        <title>Inscription</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <form method="POST" action="signin" id="form">
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

                    <div class="container">
                        <div class="row">
                            <div class="col s12">
                                <ul class="tabs">
                                    <li class="tab col s6"><a class="active orange-chart white-text" href="#client">Client</a></li>
                                    <li class="tab col s6"><a href="#vendeur" class="marron-chart white-text">Vendeur</a></li>
                                </ul>
                            </div>
                            <div id="client" class="col s12">
                                <div class="z-depth-1 " style="padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
                                    <div class='row'>
                                        <div class='col s12'>
                                        </div>
                                    </div>

                                    <div class='row'>
                                        <div class='input-field col s12'>
                                            <input class='validate' type='email' name='email' id='email' />
                                            <label for='email'>E-mail</label>
                                        </div>



                                        <div class='input-field col s12'>
                                            <input class='validate' type='password' name='password' id='password' />
                                            <label for='password'>Mot de passe</label>
                                        </div>
                                        <label class='right'>
                                            <a href='#!'><b>Mot de passe oublié?</b></a>
                                        </label>
                                    </div>
                                    <center>
                                        <div class='row'>
                                            <div class="input-field col s12"><button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect' style="background-color: #cf6818">Connexion</button></div>

                                            <div class="input-field col s12"><a onclick="$('#button').val('inscription');$('#form').submit();" name='btn_login' class='col s12 btn btn-large waves-effect waves-light' style="background-color: #622e06">Inscription</a></div>
                                        </div>
                                    </center>
                                </div>
                            </div>
                            <div id="vendeur" class="col s12">
                                <div class="z-depth-1 grey lighten-4 row" style="padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
                                    <div class='row'>
                                        <div class='col s12'>
                                        </div>
                                    </div>

                                    <div class='row'>
                                        <div class='input-field col s12'>
                                            <input class='validate' type='email' name='email' id='email' />
                                            <label for='email'>E-mail</label>
                                        </div>



                                        <div class='input-field col s12'>
                                            <input class='validate' type='password' name='password' id='password' />
                                            <label for='password'>Mot de passe</label>
                                        </div>
                                        <label class='right'>
                                            <a href='#!'><b>Mot de passe oublié?</b></a>
                                        </label>
                                    </div>
                                    <center>
                                        <div class='row'>
                                            <div class="input-field col s12"><button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect' style="background-color: #cf6818">Connexion</button></div>

                                            <div class="input-field col s12"><a onclick="$('#button').val('inscription');$('#form').submit();" name='btn_login' class='col s12 btn btn-large waves-effect waves-light' style="background-color: #622e06">Inscription</a></div>
                                        </div>
                                    </center>
                                </div>
                            </div>
                        </div>

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
