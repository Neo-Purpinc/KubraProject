<footer>
    <div class="row text-center">
        <div class="col-4">
            <a href="#aProposModal" data-toggle="modal" data-target="#aProposModal">A propos</a>
        </div>
        <div class="col-4">
            <a href="#cguModal" data-toggle="modal" data-target="#cguModal">&copy; 2021 Copyright - Colne & Co</a>
        </div>
        <div class="col-4">
            <a href="#contactModal" data-toggle="modal" data-target="#contactModal">Contact</a>
        </div>
    </div>
</footer>


<!-- A Propos Modal -->
<div class="modal modal-black fade" id="aProposModal" tabindex="-1" role="dialog" aria-labelledby="aProposLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" align="center">
            <div class="modal-header text-lg-center">
                <h6 class="modal-title txt-20px" id="aProposLabel" >A propos</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <div class="text-primary" align="center">Qui sommes-nous ?</div>
                <br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac placerat vestibulum lectus mauris ultrices eros. Sed odio morbi quis commodo odio aenean sed. Eu turpis egestas pretium aenean.
            </div>
        </div>
    </div>
</div>

<!-- Contact Modal -->
<div class="modal modal-black fade" id="contactModal" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" align="center">
            <div class="modal-header text-lg-center">
                <h6 class="modal-title txt-20px" id="contactLabel" >Contact</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="inputEmail">Adresse mail</label>
                        <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Enter votre adresse mail">
                    </div>
                    <div class="form-group">
                        <label for="inputMessage">Message</label>
                        <textarea class="form-control" id="inputMessage" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Envoyer</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Core JS Files -->
<script src="<c:url value="/assets/js/core/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/js/core/bootstrap.min.js"/>"></script>