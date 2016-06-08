<div class="inmodal" ng-controller = "CardModalController as cc">
    <div class="modal-header">
        <h5 class="modal-title">Create Card</h5>
    </div>
    <form class="m-t" role="form">
        <div class="modal-body">
            <div class="row">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" class="form-control" ng-model="cc.title" placeholder="Card title" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" class="form-control" ng-model="cc.description" placeholder="Card description" required>
                </div>
        		<div class="col-xs-6">
		            <div class="form-group">
                        <label for="points">Points</label>
                        <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" ng-model="cc.points" name="points">
                            <option value="0">0 points</option>
                            <option value="1">1 points</option>
                            <option value="2">2 points</option>
                            <option value="3">3 points</option>
                            <option value="5">5 points</option>
                            <option value="8">8 points</option>
                            <option value="13">13 points</option>
                            <option value="20">20 points</option>
                            <option value="40">40 points</option>
                            <option value="100">100 points</option>
                        </select>
		            </div>
  					<div class="form-group">
                        <label for="iteration">Iteration</label>
                        <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" name="iteration" ng-model="cc.iteration">
                            <option value="57550b64e98498f242f4a6ef">Iteration 1</option>
                            <option value="57566b925e061750d20c861f">Iteration 2</option>
                        </select>
		            </div>
        		</div>
  				<div class="col-xs-6">
		            <div class="form-group">
		            	<label for="epic">Epic</label>
                        <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" name="epic" ng-model="cc.epic">
                            <option value="574fbf39e96aa23add1f059e">Epic 1</option>
                        </select>
		            </div>
		            <div class="form-group">
  						<label for="estimate">Estimate</label>
		                <input type="text" id="estimate" class="form-control ng-pristine ng-untouched ng-valid ng-empty"  ng-model="cc.estimate" name="estimate" placeholder="Estimate (hours)">
		            </div>
  				</div>
                <div class="form-group">
                    <label for="assignee">Assignee</label>
                    <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" name="assignee" ng-model="cc.assignee"
                        ng-options="orgmember.userId as orgmember.fullName for orgmember in orgmembers">
                    </select>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label for="status">Status</label>
                        <select class="form-control m-b" name="status" ng-model="cc.status">
                            <option value='0'>Todo</option>
                            <option value='1'>In Progress</option>
                            <option value='2'>Completed</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" ng-click="cancel()">Close</button>
            <button type="button" class="btn btn-primary" ng-click="cc.saveCard()">Save changes</button>
        </div>
    </form>
</div>

