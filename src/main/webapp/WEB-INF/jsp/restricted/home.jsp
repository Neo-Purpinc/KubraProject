 <c:import url="../header.jsp" />
<div class="container-fluid" id="contenu">
    <div class="row">
        <div class="col-lg-6" align="center">
            <div class="row">
                <h6 class="modal-title txt-3rem">Classement</h6>
            </div>
            <div class="row mt-3 w90">
                <div class="col-12 card">
                    <div class="card-body">
                        <div class="table-responsive tableDiv">
                            <table class="table" id="tableClassement">
                                <thead class="text-center">
                                    <tr>
                                        <th><span class="text-primary">#</span></th>
                                        <th><span class="text-primary">Nom</span></th>
                                        <th><span class="text-primary">Symbole</span></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                            <div class="loader"></div>
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
                <div class="col-12 card" id="fixMobile">
                    <div class="card-body">
                        <div class="table-responsive tableDiv" id="tablePorteactionsDiv" >
                            <table class="table" id="tablePorteactions">
                                <thead class="text-center">
                                <tr>
                                    <th><span class="text-primary">Nom</span></th>
                                    <th><span class="text-primary">Symbole</span></th>
                                    <th><span class="text-primary">Valeur</span></th>
                                    <th><span class="text-primary">Quantité</span></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ sessionScope.sessionPortefeuille.porteaction.actions_quantites }"  var="item" >
                                    <tr class="text-center">
                                        <td><c:out value="${ item.key.nom }"/></td>
                                        <td><c:out value="${ item.key.symbole }"/></td>
                                        <td><c:out value="${ sessionScope.sessionPortefeuille.porteaction.actions_valeur[item.key] } EUR"/></td>
                                        <td><c:out value="${ item.value }"/></td>
                                        <td><button type="button" data-nom="<c:out value="${ item.key.nom }"/>" data-symbole="<c:out value="${ item.key.symbole }"/>" data-quantite="<c:out value="${ item.value }"/>" data-type="VENTE" class="btn btn-sm mr-1 btn-danger btn-animation-on-hover" onclick="afficherTransaction(this); return false;">Vendre</button></td>
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
                <div class="h6 text-center text-primary"><c:out value="${ sessionScope.sessionPortefeuille.porteaction.valeurTotale }"/></div>
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
                                <td class="${ transac.type.equals('ACHAT') ? 'text-info' : 'text-danger'}"><c:out value="${ transac.type }"/></td>
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
<div class="modal fade modal-black" id="transactionActionModal" tabindex="-1" role="dialog" aria-labelledby="transactionActionModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h6 class="modal-title txt-3rem" id="transactionActionModalLabel"></h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body text-center">
                <h6 class="txt-20px" id="transactionActionModalSousLabel"></h6>
                <form class="pt-4" id="formTransaction" method="POST" action="transaction">
                    <div class="form-group">
                        <label class="text-primary" for="transactionQuantite">Quantite</label>
                        <input type="number" class="form-control text-center" min="0" id="transactionQuantite" name="transactionQuantite" placeholder="0" required>
                    </div>
                    <div class="form-group">
                        <label class="text-primary" for="transactionPrix">Prix (€)</label>
                        <input type="number" min="0" step="0.001" class="form-control text-center" id="transactionPrix" name="transactionPrix" placeholder="valeur à récupérer de l'api et afficher ici" required>
                    </div>
                    <div class="form-group">
                        <label class="text-primary" for="transactionDate">Date</label>
                        <input type="text" class="form-control datepicker text-center" id="transactionDate" name="transactionDate" required>
                    </div>
                    <input id="transactionSymbole" name="transactionSymbole" type="hidden" required>
                    <input id="transactionQuantiteMax" name="transactionQuantiteMax" type="hidden">
                    <input id="transactionType" name="transactionType" type="hidden" required>
                </form>
            </div>
            <div class="modal-footer">
                <input type="button" data-dismiss="modal" aria-hidden="true" class="btn btn-danger btn-lg btn-block" value="Annuler">
                <input type="submit" id="submitTransactionForm"  class="btn btn-neutral btn-lg btn-block" form="formTransaction">
            </div>
        </div>
    </div>
</div>
<script>
    function afficherTransaction(bouton){
        $("#transactionActionModalLabel").text($(bouton).data('nom'));
        $("#transactionActionModalSousLabel").text($(bouton).data('symbole'));
        $("#transactionType").val($(bouton).data('type'));
        if($(bouton).data('type') === 'VENTE'){
            $("#transactionQuantite").attr({"max":$(bouton).data('quantite')});
            $("#transactionQuantiteMax").val($(bouton).data('quantite'));
            $("#submitTransactionForm").val("Vendre")
        } else{
            $("#submitTransactionForm").val("Acheter")
        }
        $("#transactionSymbole").val($(bouton).data('symbole'));
        let date = new Date().toISOString().substr(0, 19).replace('T', ' ');
        $("#transactionDate").val(date);
        $("#transactionActionModal").modal('show');
    }
    function afficherClassement(){
        $.ajax({
            url: 'http://127.0.0.1:5000/main',
            contentType: "application/json",
            dataType: 'json',
            beforeSend: function(){
                $('.loader').show();
            },
            success: function(jsonArray){
                $.each(jsonArray, function(index,action){
                    let nom = action.nom;
                    let symbole = action.symbole;
                    let acheter = "<button class='btn btn-sm btn-success animation-on-hover' onclick='afficherTransaction(this); return false;' type='button' data-nom='"+ nom + "' data-symbole='"+ symbole + "' data-type='ACHAT'>Acheter</button>";
                    let bouton = "<a class='btn btn-sm btn-neutral animation-on-hover' href='https://www.boursorama.com/cours/1rP"+symbole+"' target='_blank' >+ d'infos</a>";
                    $('<tr>').addClass("text-center").append(
                        $('<td>').text(index+1),
                        $('<td>').text(nom),
                        $('<td>').text(symbole),
                        $('<td>').html(bouton),
                        $('<td>').html(acheter)
                    ).appendTo('#tableClassement');
                });
            },
            complete: function(){
                $('.loader').hide();
            }
        });
    }
$(function(){
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
         <c:remove var="first_time" scope="session" />
         $.notify({
         title: '<h6 class=\'txt-20px\'>Bienvenue</h6>',
         message: 'La connexion s\'est déroulée avec succès.'
         });
     </c:if>
     <c:if test="${sessionScope.modification == 1}">
         <c:remove var="modification" scope="session" />
         $.notify({
         title: '<h6 class=\'txt-20px\'>Modification effectuée avec succès</h6>',
         message: 'Votre mot de passe a bien été modifiée.'
         });
     </c:if>
     <c:if test="${sessionScope.modification == 2}">
         <c:remove var="modification" scope="session" />
         $.notify({
         title: '<h6 class=\'txt-20px\'>Echec de la modification</h6>',
         message: 'Veuillez réessayez.'
         });
     </c:if>
    <c:if test="${ empty sessionScope.classement }">
        <c:set var="classement" value="1" scope="session" />
        afficherClassement();
    </c:if>
});
</script>