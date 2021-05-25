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
                                <span class="erreur">${form.erreurs['emailLogin']}</span>
                            </div>
                            <div class="input-group no-border form-control-lg">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tim-icons icon-key-25"></i>
                                    </div>
                                </div>
                                <input type="password" class="form-control" id="motdepasseLogin" name="motdepasseLogin" placeholder="Mot de passe" required>
                                <span class="erreur">${form.erreurs['motdepasseLogin']}</span>
                            </div>

                        </div>
                        <input type="submit" value="Connexion" class="btn btn-neutral btn-round btn-lg btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>