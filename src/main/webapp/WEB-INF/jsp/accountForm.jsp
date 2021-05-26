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
                            <input type="email" id="emailInfos" name="emailInfos" class="form-control" placeholder="Adresse email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" required>
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
                            <input type="password" id="confirmationInfos" name="confirmationInfos" placeholder="Nouveau mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                        </div>
                    </div>
                    <input type="submit" value="Inscription" class="btn btn-neutral btn-round btn-lg btn-block">
                </form>
            </div>
        </div>
    </div>
</div>