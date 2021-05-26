<nav class="navbar sticky-top navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <div class="row">
            <div class="col float-left">
                <img src="<c:url value="/assets/videos/logo.gif" />" alt="logo360" width="15%"/>
            </div>
            <div class="col float-right">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-bar navbar-kebab"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="tim-icons icon-single-02 text-default"></i>
                                &#9660;
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" data-toggle="modal" data-target="#mesInfosModal">Mes informations personnelles</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
<!-- Mes informations personnelles Modal -->
<div class="modal modal-black fade" id="mesInfosModal" tabindex="-1" role="dialog" aria-labelledby="mesInfosModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" align="center">
            <div class="modal-header text-lg-center">
                <h6 class="modal-title txt-20px" id="mesInfosModalLabel" >Mes informations personnelles</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <form class="form" method="post" action="compte">
                    <div class="card-content">
                        <div class="input-group no-border form-control-lg">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <i class="tim-icons icon-single-02"></i>
                                </div>
                            </div>
                            <input type="email" id="disabledInput" name="emailInfos" class="form-control" placeholder="Adresse email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" >
                        </div>
                        <div class="input-group no-border form-control-lg">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <i class="tim-icons icon-key-25"></i>
                                </div>
                            </div>
                            <input type="password" id="motdepasseInfos" name="motdepasseInfos" placeholder="Ancien mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                        </div>
                        <div class="input-group no-border form-control-lg">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <i class="tim-icons icon-key-25"></i>
                                </div>
                            </div>
                            <input type="password" id="nouveauMdpInfos" name="nouveauMdpInfos" placeholder="Nouveau mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                        </div>
                    </div>
                    <input type="submit" value="Enregistrer" class="btn btn-neutral btn-round btn-lg btn-block">
                    <input type="button" data-dismiss="modal" aria-hidden="true" class="btn btn-neutral btn-round btn-lg btn-block" value="Retour">
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Core JS Files -->
<script src="<c:url value="/assets/js/core/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/popper.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/bootstrap.min.js"/>"></script>
<!-- Plugins JS Files -->
<script src="<c:url value="/assets/js/plugins/perfect-scrollbar.jquery.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-notify.js" />"></script>
<script src="<c:url value="/assets/js/black-dashboard.js?v=1.1.0"/>"></script>