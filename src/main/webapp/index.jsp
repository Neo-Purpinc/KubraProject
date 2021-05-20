<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="fr">
<head>
    <title>Hello, world!</title>
    <link rel="shortcut icon" href="#">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <!-- Fonts and icons -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <!-- Black Dashboard CSS -->
    <link href="<c:url value="/assets/css/black-dashboard.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/css/nucleo-icons.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/css/style.css" />" rel="stylesheet">

    <!-- Core JS Files -->
    <script src="<c:url value="/assets/js/core/jquery.min.js"/>"></script>
    <script src="<c:url value="/assets/js/core/popper.min.js"/>"></script>
    <script src="<c:url value="/assets/js/core/bootstrap.min.js"/>"></script>
</head>
<body>
<video autoplay muted loop id="myVideo">
    <source src="<c:url value="/assets/videos/background.webm" />" type="video/webm">
</video>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-lg-1" align="center">
                <img src="./assets/videos/logo.gif" alt="logo360" width="85%" />
            </div>
            <div class="col-12 col-lg-8" align="center">
                <div id="carouselExampleIndicators" class="carousel slide semi-colon margin-top-3rem" data-bs-ride="carousel" >
                    <div class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <h5 align="left">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,
                                molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum
                                numquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium
                                optio, eaque rerum! Provident similique accusantium nemo autem.
                            </h5>
                        </div>
                        <div class="carousel-item">
                            <h5 align="left">
                                Veritatis obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam
                                nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,
                                tenetur error, harum nesciunt ipsum debitis quas aliquid.
                            </h5>
                        </div>
                        <div class="carousel-item">
                            <h5 align="left">
                                Sed quibusdam recusandae alias error harum maxime adipisci amet laborum. Perspiciatis
                                minima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit
                                quibusdam sed amet tempora.
                            </h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-3 margin-top-3rem" align="center">
                    <button class="btn btn-neutral btn-round btn-lg" data-toggle="modal" data-target="#registerModal">Inscription</button>
                    <br>
                    <button class="btn btn-neutral btn-round btn-lg" data-toggle="modal" data-target="#loginModal">Connexion</button>

                </div>
            </div>
        </div>
    </div>
    <c:import url="WEB-INF/jsp/footer.jsp" />
</div>

<!-- Register Modal -->
<div class="modal fade modal-primary" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="card card-login card-plain">
                <div class="modal-header justify-content-center">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <i class="tim-icons icon-simple-remove"></i>
                    </button>
                    <div class="header header-primary text-center">
                        <div class="modal-profile">
                            <i class="tim-icons icon-single-02 text-default"></i>
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <form class="form" method="" action="">
                        <div class="card-content">
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-single-02"></i>
                                    </div>
                                </div>
                                <input type="text" class="form-control" placeholder="Adresse email" required>
                            </div>
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" placeholder="Mot de passe" class="form-control" required>
                            </div>
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" placeholder="Confirmation du mot de passe" class="form-control" required>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" value="">
                                    J'ai lu et j'accepte les
                                    <span class="form-check-sign">
                  <span class="check"></span>
              </span>
                                </label>
                                <a href="#cguModal" data-toggle="modal" data-target="#cguModal" class="text-info">CGU</a>.
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer text-center pt-4">
                    <a href="#pablo" class="btn btn-neutral btn-round btn-lg btn-block">Inscription</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Login Modal -->
<div class="modal fade modal-primary" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="card card-login card-plain">
                <div class="modal-header justify-content-center">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <i class="tim-icons icon-simple-remove"></i>
                    </button>
                    <div class="header header-primary text-center">
                        <div class="modal-profile">
                            <i class="tim-icons icon-single-02 text-default"></i>
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <form class="form" method="" action="">
                        <div class="card-content">
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-single-02"></i>
                                    </div>
                                </div>
                                <input type="text" class="form-control" placeholder="Adresse e-mail" required>
                            </div>

                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" placeholder="Mot de passe" class="form-control" required>
                            </div>

                        </div>
                    </form>
                </div>
                <div class="modal-footer text-center pt-4">
                    <a href="#pablo" class="btn btn-neutral btn-round btn-lg btn-block">Connexion</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- CGU Modal -->
<div class="modal modal-black fade" id="cguModal" tabindex="-1" role="dialog" aria-labelledby="cguModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" align="center">
            <div class="modal-header text-lg-center">
                <h6 class="modal-title txt-20px" id="cguModalLabel" >Conditions Générales d'Utilisation</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <div>
                    <div class="text-primary" align="center">Texte 1</div>
                    <br>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac placerat vestibulum lectus mauris ultrices eros. Sed odio morbi quis commodo odio aenean sed. Eu turpis egestas pretium aenean.</p>
                </div>
                <div>
                    <div class="text-primary" align="center">Texte 2</div>
                    <br>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac placerat vestibulum lectus mauris ultrices eros. Sed odio morbi quis commodo odio aenean sed. Eu turpis egestas pretium aenean.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Plugins JS Files -->
<script src="<c:url value="/assets/js/plugins/perfect-scrollbar.jquery.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-notify.js" />"></script>
<script src="<c:url value="/assets/js/black-dashboard.js?v=1.1.0"/>"></script>
</body>
</html>