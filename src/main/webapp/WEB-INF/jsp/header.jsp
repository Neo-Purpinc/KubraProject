<nav class="navbar sticky-top navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <div class="row justify-content-between">
            <div class="col-1">
                <span>K</span>
            </div>
            <div class="col-6">
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
<c:import url="accountForm.jsp" />
