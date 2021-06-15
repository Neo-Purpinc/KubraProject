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
                        <div class="table-responsive tableDiv" id="tableClassementDiv">
                            <table class="table" id="tableClassement">
                                <thead class="text-center">
                                    <tr>
                                        <th><span class="text-primary">#</span></th>
                                        <th><span class="text-primary">Nom</span></th>
                                        <th><span class="text-primary">Symbole</span></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                            <div id="loader-wrapper">
                                <h2>Veuillez patienter, nous récupérons les données.</h2>
                                <div class="loader"></div>
                            </div>
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
                        <c:if test="${ empty sessionScope.sessionPortefeuille.porteaction.actions_valeur}">
                            Vous n'avez aucune action dans votre portefeuille.
                            Vous pouvez en ajouter depuis le classement.
                        </c:if>
                        <c:if test="${ !empty sessionScope.sessionPortefeuille.porteaction.actions_valeur}">
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
                                            <td><button type="button" data-nom="<c:out value="${ item.key.nom }"/>" data-symbole="<c:out value="${ item.key.symbole }"/>" data-quantite="<c:out value="${ item.value }"/>" data-type="VENTE" class="btn btn-block btn-danger animation-on-hover" onclick="afficherTransaction(this); return false;">Vendre</button></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
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
                <div class="h4 text-center text-primary">Bénéfice/Perte : <span class="${sessionScope.sessionPortefeuille.porteaction.valeurTotale ge 0 ? 'text-green' : 'text-danger'}"><c:out value="${ sessionScope.sessionPortefeuille.porteaction.valeurTotale } EUR"/></span></div>
                <div class="h6 text-center sub-title text-primary">Historique des transactions</div>
                <div class="card-plain">
                    <div class="card-body">
                        <div class="table-responsive tableDiv" id="tablePortefeuilleDiv" >
                            <table class="table" id="tablePortefeuille">
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
                                        <td><span class="${ transac.type.equals('ACHAT') ? 'text-green' : 'text-danger'}"><c:out value="${ transac.type }"/></span></td>
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
                        <input type="number" class="form-control text-center" min="0" id="transactionQuantite" name="transactionQuantite" value="0" required>
                    </div>
                    <div class="form-group">
                        <label class="text-primary" for="transactionPrix">Prix (€)</label>
                        <input type="number" min="0" step="0.001" class="form-control text-center" id="transactionPrix" name="transactionPrix" required>
                    </div>
                    <div class="form-group">
                        <label class="text-primary" for="transactionDate">Date</label>
                        <input type="text" id="transactionDate" name="transactionDate" class="form-control datetimepicker-input text-center" data-toggle="datetimepicker" data-target="#transactionDate" required>
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
    $.ajax({
        url: 'http://127.0.0.1:5000/main',
        contentType: "application/json",
        dataType: 'json',
        beforeSend: function(){
            $('#loader-wrapper').show();
        },
        success: function(jsonArray){
            $.each(jsonArray, function(index,action){
                let nom = action.Nom;
                let symbole = action.Symbole;
                let boutonAchat = "<button class='btn btn-block btn-primary btn-vert animation-on-hover' onclick='afficherTransaction(this); return false;' type='button' data-nom='"+ nom + "' data-symbole='"+ symbole + "' data-type='ACHAT'>Acheter</button>";
                let boutonInfos = "<a class='btn btn-block btn-neutral animation-on-hover' href='https://www.boursorama.com/cours/1rP"+symbole+"' target='_blank' >+ d'infos</a>";
                let boutons = $('<div>').addClass("row").addClass("mr-1").append(
                    $('<div>').addClass('col-12').addClass('col-xs-6').addClass('mb-2').html(boutonInfos),
                    $('<div>').addClass('col-12').addClass('col-xs-6').html(boutonAchat)
                );
                $('<tr>').addClass("text-center").append(
                    $('<td>').text(index+1),
                    $('<td>').text(nom),
                    $('<td>').text(symbole),
                    $('<td>').html(boutons)
                ).appendTo('#tableClassement');
            });
        },
        complete: function(){
            $('#loader-wrapper').hide();
        }
    });
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
        $.ajax({
            url: 'http://127.0.0.1:5000/lastPrice/'+$(bouton).data('symbole'),
            contentType: "application/json",
            dataType: 'text',
            async: false,
            success: function(text){
                console.log(text);
                $("#transactionPrix").val(text);
            }
        });
        $("#transactionActionModal").modal('show');
    }
$(function(){
    $('#transactionDate').datetimepicker({
        format: 'DD/MM/YYYY HH:mm:ss',
        widgetParent: 'body',
        icons: {
            time: "tim-icons icon-watch-time",
            date: "tim-icons icon-calendar-60",
            up: "fa fa-chevron-up",
            down: "fa fa-chevron-down",
            previous: 'tim-icons icon-minimal-left',
            next: 'tim-icons icon-minimal-right',
            today: 'fa fa-screenshot',
            clear: 'fa fa-trash',
            close: 'fa fa-remove'
        }
    });
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
});
</script>