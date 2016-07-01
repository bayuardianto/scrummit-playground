<div class="inmodal">
	<div class="modal-header">
		<h5 class="modal-title">Create Card</h5>
	</div>
	<div class="modal-body">
	    <form class="m-t" role="form" ng-submit="saveCard()">
			<div class="row">
				<div class="form-group">
					<label for="title">Title</label>
					<input type="text" ng-keydown="keypress()" class="form-control" ng-model="card.title" placeholder="Card title" required>
				</div>
				<div class="form-group">
					<label for="description">Description</label>
					<textarea type="text" ng-keydown="keypress()"  class="form-control" ng-model="card.description" placeholder="Card description" required>
					</textarea>
				</div>
				<div class="form-group row">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="points">Points</label>
                            <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" ng-model="card.points" ng-options="convertToInt(k) as v for (k,v) in points"></select>
						</div>
						<div class="form-group">
							<label for="iteration">Iteration</label>
							<select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty"  ng-model="card.iteration"
							    ng-options="item.id as item.name for item in iterations" >
							</select>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="epic">Epic</label>
                            <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" ng-model="card.epic" ng-options="convertToInt(k) as v for (k,v) in epic"></select>
						</div>
						<div class="form-group">
							<label for="estimate">Estimate</label>
							<input type="number" min="1" class="form-control ng-pristine ng-untouched ng-valid ng-empty" ng-model="card.estimate" placeholder="Estimate (hours)">
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-xs-6">
						<label>Owner</label>
						<select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty"  ng-model="card.owner"
							ng-options="orgmember.userId as orgmember.fullName for orgmember in orgmembers">
						</select>
					</div>
					<div class="col-xs-6 form-group">
						<label for="status">Status</label>
						<select class="form-control m-b" ng-model="card.status" ng-options="convertToInt(k) as v for (k,v) in status"></select>
					</div>
				</div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary" data-toggle="modal" ng-click="addTask()">Add Task</button>
                </div>
                <div ng-repeat="task in tasks">
                    <div class="form-group row">
                        <div class='col-sm-4'>
                            <input type='text' placeholder='Task Name' class='form-control m-b' ng-model="task.description">
                        </div>
                        <div class='col-sm-4'>
                            <select class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty" ng-model="task.owner"
                                ng-options="orgmember.userId as orgmember.fullName for orgmember in orgmembers">
                            </select>
                        </div>
                        <div class='col-sm-4'>
                            <select class="form-control m-b" ng-model="task.status" style="float:left;width: 90% !important" ng-options="convertToInt(k) as v for (k,v) in status"></select>
                            <span class="fa fa-trash-o" style="padding-left: 10px;padding-top: 12px;cursor: pointer" ng-click="#" aria-hidden="true"></span>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }"
                    ng-if="flash" ng-show="showMessage">
                    <p ng-bind="flash.message"></p>
                </div>
			</div>
            <div class="modal-footer">
                <button type="submit" ng-disabled="form.$invalid" class="btn btn-primary">Save</button>
                <button type="button" class="btn btn-white" ng-click="cancel()">Close</button>
            </div>
	    </form>
	</div>
</div>

