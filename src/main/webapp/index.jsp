<video autoplay muted loop id="myVideo">
    <source src="<c:url value="/assets/videos/background.webm" />" type="video/webm">
</video>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-lg-1" align="center">
                <img src="<c:url value="/assets/videos/logo.gif" />" alt="logo360" width="85%" />
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
<!-- Register Modal -->
<div class="modal fade modal-primary" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="card card-login card-plain">
                <div class="modal-header justify-content-center">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <i class="tim-icons icon-simple-remove"></i>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form" method="post" action="inscription">
                        <div class="card-content">
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-single-02"></i>
                                    </div>
                                </div>
                                <input type="email" id="email" name="email" class="form-control" placeholder="Adresse email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" required>
                            </div>
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" id="motdepasse" name="motdepasse" placeholder="Mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                            </div>
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" id="confirmation" name="confirmation" placeholder="Confirmation du mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" value="" required>
                                    J'ai lu et j'accepte les
                                    <span class="form-check-sign">
                  <span class="check"></span>
              </span>
                                </label>
                                <a href="#" id="showCGU" onclick="showCGU();return false;" class="text-info">CGU</a>.
                            </div>
                        </div>
                        <input type="submit" value="Inscription" class="btn btn-neutral btn-round btn-lg btn-block">
                    </form>
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
                    <form class="form" method="post" action="connexion">
                        <div class="card-content">
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-single-02"></i>
                                    </div>
                                </div>
                                <input type="text" class="form-control" id="emailLogin" name="emailLogin" placeholder="Adresse e-mail" required>
                            </div>
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" class="form-control" id="motdepasseLogin" name="motdepasseLogin" placeholder="Mot de passe" required>
                            </div>
                        </div>
                        <input type="submit" value="Connexion" class="btn btn-neutral btn-round btn-lg btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="WEB-INF/jsp/footer.jsp" />
<script>
    function showCGU(){
        $('#registerModal').modal('hide');
        $('#cguModal').modal('show');
    }
    function showRegister(){
        $(`#registerModal`).modal('show');
    }
</script>
<script>
    $(function () {
        $.notifyDefaults({
            placement: {
                from: "bottom",
                align: 'center'
            },
            animate:{
                enter: "animated fadeInUp",
                exit: "animated fadeOutDown"
            },
            type: 'info'
        });
        <c:if test="${ sessionScope.error == '1' }">
            <c:set var="error" value="0" scope="session" />
            $.notify({
                title: '<h6 class=\'txt-20px\'>Echec de la connexion</h6>',
                message: 'Veuillez r??essayer.'
            });
        </c:if>
        <c:if test="${!empty requestScope.utilisateur }">
            $.notify({
                title: '<h6 class=\'txt-20px\'>Inscription effectu??e avec succ??s</h6>',
                message: 'Vous pouvez d??s ?? pr??sent vous connecter.'
            });
        </c:if>
        <c:if test="${ requestScope.error == '2' }">
            $.notify({
                title: '<h6 class=\'txt-20px\'>Echec de l\'inscription</h6>',
                message: 'Veuillez r??essayer.'
            });
        </c:if>
    });
</script>