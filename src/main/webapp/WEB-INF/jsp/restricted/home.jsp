 <c:import url="../header.jsp" />
<div class="container-fluid" id="contenu">
    <div class="row">
        <div class="col-lg-6" align="center">
            <div class="row">
                <h6 class="modal-title txt-3rem">Classement</h6>
            </div>
            <div class="row mt-3 h4rem" align="center">
                <div class="col-12">
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-sm btn-primary btn-simple active" id="0">
                            <input type="radio" name="options" checked="">
                            <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Quotidien</span>
                            <span class="d-block d-sm-none"><i class="tim-icons icon-single-02"></i></span>
                        </label>
                        <label class="btn btn-sm btn-primary btn-simple" id="1">
                            <input type="radio" class="d-none d-sm-none" name="options">
                            <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Hebdomadaire</span>
                            <span class="d-block d-sm-none"><i class="tim-icons icon-gift-2"></i></span>
                        </label>
                        <label class="btn btn-sm btn-primary btn-simple" id="2">
                            <input type="radio" class="d-none d-sm-none" name="options">
                            <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Mensuel</span>
                            <span class="d-block d-sm-none"><i class="tim-icons icon-gift-2"></i></span>
                        </label>
                        <label class="btn btn-sm btn-primary btn-simple" id="3">
                            <input type="radio" class="d-none" name="options">
                            <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Annuel</span>
                            <span class="d-block d-sm-none"><i class="tim-icons icon-tap-02"></i></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="row mt-3 w90">
                <div class="col-12 card">
                    <div class="card-body">
                        <div class="table-responsive tableDiv">
                            <table class="table" id="tableClassement">
                                <thead>

                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6" align="center">
            <div class="row">
                <h6 class="modal-title txt-3rem">Portefeuille</h6>
            </div>
            <div class="row mt-3 h4rem w90">
                <div class="col-12">
                    <button class="btn btn-neutral btn-round float-lg-right animation-on-hover" data-toggle="modal" data-target="#monPortefeuilleModal">Accéder au détail</button>
                </div>
            </div>
            <div class="row mt-3 w90">
                <div class="col-12 card">
                    <div class="card-body">
                        <div class="table-responsive tableDiv" id="tablePorteactionsDiv" >
                            <table class="table" id="tablePorteactions">
                                <thead class="text-center">
                                <tr>
                                    <th ><span class="text-primary">Nom</span></th>
                                    <th ><span class="text-primary">Symbole</span></th>
                                    <th ><span class="text-primary">Quantité</span></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ sessionScope.sessionPortefeuille.porteaction.actions_quantites }"  var="item" >
                                    <tr class="text-center">
                                        <td><c:out value="${ item.key.nom }"/></td>
                                        <td><c:out value="${ item.key.symbole }"/></td>
                                        <td><c:out value="${ item.value }"/></td>
                                        <td><button type="button" data-name="<c:out value="${ item.key.nom }"/>" data-symbole="<c:out value="${ item.key.symbole }"/>" data-quantite="<c:out value="${ item.value }"/>" class="btn btn-sm mr-1 btn-danger btn-animation-on-hover afficherVente">Vendre</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <c:import url="../footer.jsp"/>
<!-- Mon portefeuille détaillé Modal-->
<div class="modal fade modal-black" id="monPortefeuilleModal" tabindex="-1" role="dialog" aria-labelledby="monPortefeuilleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h6 class="modal-title txt-20px" id="monPortefeuilleModalLabel">Mon portefeuille détaillé</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <div class="h6 text-center sub-title text-primary">Historique des transactions</div>
                <div class="table-responsive tableDiv" >
                    <table class="table"  id="tablePortefeuille">
                        <thead class="text-center">
                        <tr>
                            <th>Nom</th>
                            <th>Symbole</th>
                            <th>Type de transaction</th>
                            <th>Quantité</th>
                            <th>Prix Unitaire</th>
                            <th>Prix Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ sessionScope.sessionPortefeuille.transactions }"  var="transac" >
                            <tr class="text-center">
                                <td><c:out value="${ transac.action.nom}"/></td>
                                <td><c:out value="${ transac.action.symbole}"/></td>
                                <td class="${(transac.type == 'ACHAT') ? 'achat' : 'vente'}"><c:out value="${ transac.type }"/></td>
                                <td><c:out value="${ transac.quantite}"/></td>
                                <td><c:out value="${ transac.prix_unitaire} EUR"/></td>
                                <td><c:out value="${ transac.prix_total} EUR"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Vente d'actions Modal -->
<div class="modal fade modal-black" id="venteActionModal" tabindex="-1" role="dialog" aria-labelledby="venteActionModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h6 class="modal-title txt-3rem" id="venteActionModalLabel"></h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body text-center">
                <h6 class="txt-20px" id="venteActionModalSousLabel"></h6>
                <form class="pt-4" id="formVente" method="POST" action="vente">
                    <div class="form-group">
                        <label class="text-primary" for="venteQuantite">Quantite</label>
                        <input type="number" class="form-control text-center" min="0" id="venteQuantite" name="venteQuantite" placeholder="0" required>
                    </div>
                    <div class="form-group">
                        <label class="text-primary" for="ventePrix">Prix (€)</label>
                        <input type="number" min="0" step="0.001" class="form-control text-center" id="ventePrix" name="ventePrix" placeholder="valeur à récupérer de l'api et afficher ici" required>
                    </div>
                    <div class="form-group">
                        <label class="text-primary" for="venteDate">Date</label>
                        <input type="text" class="form-control datepicker text-center" id="venteDate" name="venteDate" required>
                    </div>
                    <input id="venteNom" name="venteNom" type="hidden" required>
                    <input id="venteQuantiteMax" name="venteQuantiteMax" type="hidden" required>
                    <input id="venteType" name="venteType" type="hidden" value="VENTE" required>
                </form>
            </div>
            <div class="modal-footer">
                <input type="button" data-dismiss="modal" aria-hidden="true" class="btn btn-danger btn-lg btn-block" value="Annuler">
                <input type="submit" value="Vendre" class="btn btn-neutral btn-lg btn-block" form="formVente">
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $('.afficherVente').on('click', function(){
            $("#venteActionModalLabel").text($(this).data('name'));
            $("#venteActionModalSousLabel").text($(this).data('symbole'));
            $("#venteQuantite").attr({"max":$(this).data('quantite')});
            $("#venteQuantiteMax").val($(this).data('quantite'));
            $("#venteNom").val($(this).data('name'));
            let date = new Date().toISOString().substr(0, 19).replace('T', ' ');
            $("#venteDate").val(date);
            $("#venteActionModal").modal('show');
        });
    });
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
        <c:if test="${ sessionScope.first_time == 1 }">
        <c:set var="first_time" value="0" scope="session" />
        $.notify({
            title: '<h6 class=\'txt-20px\'>Bienvenue</h6>',
            message: 'La connexion s\'est déroulée avec succès.'
        });
        </c:if>
        <c:if test="${sessionScope.modification == '1'}">
        <c:set var="modification" value="0" scope="session" />
        $.notify({
            title: '<h6 class=\'txt-20px\'>Modification effectuée avec succès</h6>',
            message: 'Votre mot de passe a bien été modifiée.'
        });
        </c:if>
        <c:if test="${sessionScope.modification == '2'}">
        <c:set var="modification" value="0" scope="session" />
        $.notify({
            title: '<h6 class=\'txt-20px\'>Echec de la modification</h6>',
            message: 'Veuillez réessayez.'
        });
        </c:if>

    });
</script>