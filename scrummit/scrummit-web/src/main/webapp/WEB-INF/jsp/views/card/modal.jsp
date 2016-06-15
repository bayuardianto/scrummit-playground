<div class="inmodal">
	<div class="modal-header">
		<h5 class="modal-title">Create Card</h5>
	</div>
	<form class="m-t" role="form">
		<div class="modal-body">
			<div class="row">
				<div class="form-group">
					<label for="title">Title</label> <input type="text" id="title"
						name="title" class="form-control" ng-model="card.title"
						placeholder="Card title" required>
				</div>
				<div class="form-group">
					<label for="description">Description</label> <input type="text"
						id="description" name="description" class="form-control"
						ng-model="card.description" placeholder="Card description"
						required>
				</div>
				<div class="form-group row">


					<div class="col-xs-6">
						<div class="form-group">
							<label for="points">Points</label> <select
								class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty"
								ng-model="card.points" name="points">
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
							<label for="iteration">Iteration</label> <select
								class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty"
								name="iteration" ng-model="card.iteration"
								ng-options="iteration.id as iteration.name for iteration in iterations" required>
							</select>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="epic">Epic</label> <select
								class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty"
								name="epic" ng-model="card.epic">
								<option value="574fbf39e96aa23add1f059e">Epic 1</option>
							</select>
						</div>
						<div class="form-group">
							<label for="estimate">Estimate</label> <input type="text"
								id="estimate"
								class="form-control ng-pristine ng-untouched ng-valid ng-empty"
								ng-model="card.estimate" name="estimate"
								placeholder="Estimate (hours)">
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-xs-6">
						<label for="assignee">Assignee</label> <select
							class="form-control m-b ng-pristine ng-untouched ng-valid ng-empty"
							name="assignee" ng-model="card.assignee"
							ng-options="orgmember.userId as orgmember.fullName for orgmember in orgmembers">
						</select>
					</div>
					<div class="col-xs-6 form-group">
						<label for="status">Status</label> <select
							class="form-control m-b" name="status" ng-model="card.status">
							<option value='0'>Todo</option>
							<option value='1'>In Progress</option>
							<option value='2'>Completed</option>
						</select>
					</div>
				</div>
				<div class = "form-group">
                    <button type="button" class="btn btn-primary" data-toggle="modal" ng-click="addTask()">Add Task</button>
                </div>

                <div class="hr-line-dashed"></div>
                <div id="space-for-task"></div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" ng-click="saveCard()">Save</button>
			<button type="button" class="btn btn-white" ng-click="cancel()">Close</button>
		</div>
	</form>
</div>

