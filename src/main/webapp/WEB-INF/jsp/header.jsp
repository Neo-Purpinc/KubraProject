<nav class="navbar sticky-top navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <div class="navbar-header">
            Kubra
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>

        </button>
        <div class="navbar-collapse collapse" id="navbarNav">
            <ul class="nav navbar-nav navbar-right ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#mesInfosModal">
                        <i class="tim-icons icon-single-02"></i>
                        Mes informations personnelles
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/deconnexion">
                        <i class="tim-icons icon-simple-remove"></i>
                        Déconnexion
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- Mes informations personnelles Modal -->
<div class="modal modal-black fade" id="mesInfosModal" tabindex="-1" role="dialog" aria-labelledby="mesInfosModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" >
            <div class="modal-header text-lg-center">
                <h6 class="modal-title txt-20px" id="mesInfosModalLabel" >Mes informations personnelles</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <form class="form" method="post" action="modification">
                    <div class="card-content">
                        <div class="input-group no-border form-control-lg">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <i class="tim-icons icon-single-02"></i>
                                </div>
                            </div>
                            <input type="text" class="form-control" id="emailInfos" name="emailInfos" value="<c:out value="${utilisateur.email}"/>" disabled>
                        </div>
                        <div class="input-group no-border form-control-lg">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <i class="tim-icons icon-key-25"></i>
                                </div>
                            </div>
                            <input type="password" id="motDePasseInfos" name="motDePasseInfos" placeholder="Ancien mot de passe" class="form-control" size="32" maxlength="32" required>
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
                </form>
                    <div class="card-text">
                        <small class="card-text text-muted float-right">
                            Je souhaite <a href="#" data-dismiss="modal" onclick="showSuppression(); return false;" class="card-link">supprimer</a> mon compte et le portefeuille associé.
                        </small>
                    </div>
                    <input type="submit" value="Enregistrer" class="btn btn-primary btn-lg">
                    <input type="button" data-dismiss="modal" aria-hidden="true" class="btn btn-primary btn-lg" value="Retour">
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="confirmationSuppressionModal" tabindex="-1" role="dialog" aria-labelledby="confirmationSuppressionModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmationSuppressionModalLabel">Êtes-vous sûr ?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="showInformations(); return false;">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <p class="card-text text-center">
                    Une fois confirmé, vous ne pourrez-plus revenir en arrière.
                    Votre portefeuille (même non vide) et votre compte seront définitivement supprimés de notre plateforme.
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="showInformations(); return false;">Retour</button>
                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/suppression'">Confirmer</button>
            </div>
        </div>
    </div>
</div>
<script>
    function showInformations(){
        $('#mesInfosModal').modal('show');
    }
    function showSuppression(){
        $('#confirmationSuppressionModal').modal('show');
    }
</script>

<!-- Core JS Files -->
<script src="<c:url value="/assets/js/core/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/popper.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/bootstrap.min.js"/>"></script>
<!-- Plugins JS Files -->
<script src="<c:url value="/assets/js/plugins/perfect-scrollbar.jquery.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-notify.js" />"></script>
<script src="<c:url value="/assets/js/black-dashboard.js?v=1.1.0"/>"></script>