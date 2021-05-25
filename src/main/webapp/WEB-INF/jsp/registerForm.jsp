
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
          <form class="form" method="post" action="inscription">
            <div class="card-content">
              <div class="input-group no-border form-control-lg">
                <div class="input-group-prepend">
                  <div class="input-group-text">
                    <i class="tim-icons icon-single-02"></i>
                  </div>
                </div>
                <input type="email" id="email" name="email" class="form-control" placeholder="Adresse email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" required>
                <span class="erreur">${form.erreurs['email']}</span>
              </div>
              <div class="input-group no-border form-control-lg">
                <div class="input-group-prepend">
                  <div class="input-group-text">
                    <i class="tim-icons icon-key-25"></i>
                  </div>
                </div>
                <input type="password" id="motdepasse" name="motdepasse" placeholder="Mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                <span class="erreur">${form.erreurs['motdepasse']}</span>
              </div>
              <div class="input-group no-border form-control-lg">
                <div class="input-group-prepend">
                  <div class="input-group-text">
                    <i class="tim-icons icon-key-25"></i>
                  </div>
                </div>
                <input type="password" id="confirmation" name="confirmation" placeholder="Confirmation du mot de passe" class="form-control" value="" size="32" maxlength="32" required>
                <span class="erreur">${form.erreurs['confirmation']}</span>
              </div>
              <div class="form-check">
                <label class="form-check-label">
                  <input class="form-check-input" type="checkbox" value="" required>
                  J'ai lu et j'accepte les
                  <span class="form-check-sign">
                  <span class="check"></span>
              </span>
                </label>
                <a href="#cguModal" data-toggle="modal" data-target="#cguModal" class="text-info">CGU</a>.
              </div>
              <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </div>
            <input type="submit" value="Inscription" class="btn btn-neutral btn-round btn-lg btn-block">
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
  window.onload = function() {
    if(${empty form.erreurs ? 'false' : 'true'}) {
      $('#registerModal').show();
    }
  }
</script>