<c:import url="../header.jsp" />
<div class="content">
    <div class="container-fluid padding-top-65 h-100">
        <div class="row h-100">
            <div class="col-lg-6" align="center">
                <div class="row">
                    <h6 class="modal-title txt-3rem">Classement</h6>
                </div>
                <div class="row padding-top-1rem h4rem" align="center">
                    <div class="col-12">
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <label class="btn btn-sm btn-primary btn-simple" id="0">
                                <input type="radio" name="options" checked="">
                                <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Quotidien</span>
                                <span class="d-block d-sm-none"><i class="tim-icons icon-single-02"></i></span>
                            </label>
                            <label class="btn btn-sm btn-primary btn-simple active" id="1">
                                <input type="radio" class="d-none d-sm-none" name="options">
                                <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Hebdomadaire</span>
                                <span class="d-block d-sm-none"><i class="tim-icons icon-gift-2"></i></span>
                            </label>
                            <label class="btn btn-sm btn-primary btn-simple active" id="2">
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
                <div class="row padding-top-1rem h-75 w90">
                    <div class="col-12 card">
                        <div class="card-body">
                            <div class="table-responsive ps">
                                <table class="table tablesorter " id="tableClassement">
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
                    <h6 class="modal-title txt-3rem">Mon portefeuille</h6>
                </div>
                <div class="row padding-top-1rem h4rem w90">
                    <div class="col-12">
                        <button class="btn btn-neutral btn-round float-lg-right">Accéder au détail</button>
                    </div>
                </div>
                <div class="row padding-top-1rem h-75 w90">
                    <div class="col-12 card">
                        <div class="card-body">
                            <div class="table-responsive ps">
                                <table class="table tablesorter " id="tablePortefeuille">
                                    <thead>

                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <%--
                <c:if test="${empty portefeuille}">
                    <div class="row w90">
                        <img src="<c:url value="/assets/videos/logo.gif" />" alt="logo360" width="15%" />
                    </div>
                </c:if>
                --%>
            </div>
        </div>
    </div>
</div>

<c:import url="../footer.jsp"/>

<script>
    $.notifyDefaults({
        placement: {
            from: "bottom",
            align: 'left'
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
</script>


<!-- Core JS Files -->
<script src="<c:url value="/assets/js/core/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/popper.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/bootstrap.min.js"/>"></script>
<!-- Plugins JS Files -->
<script src="<c:url value="/assets/js/plugins/perfect-scrollbar.jquery.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-notify.js" />"></script>
<script src="<c:url value="/assets/js/black-dashboard.js?v=1.1.0"/>"></script>