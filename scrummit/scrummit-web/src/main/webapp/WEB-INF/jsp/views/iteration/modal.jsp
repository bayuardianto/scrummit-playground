<div class="inmodal">
    <div class="modal-header">
        <h5 class="modal-title">Add Iteration</h5>
    </div>
    <form class="m-t" id="form" name="form" role="form">
        <div class="modal-body">
        	<div class="form-group">
        		<label for="iterationName">Name</label>
        		<input class="form-control" type="text" placeholder="Enter Iteration name" id="iterationName" ng-model="iterationName" required/>
        	</div>
        	<div class="form-group">
        		<label for="iterationDescription">Description</label>
        		<textarea class="form-control" id="description" placeholder="Description" ng-model="description"></textarea>
        	</div>
        	<div class="form-group" id="data_5"> 
                 <div class="form-group" id="data_5">
                 	 <label for="daterangepicker">Duration</label>
                     <input id="daterangepicker" readonly date-range-picker class="form-control date-picker" type="text" ng-model="daterange" />
                 </div>
             </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" ng-click="cancel()">Close</button>
        	<button type="submit" ng-disabled="form.$invalid || !form.$dirty" class="btn btn-primary" ng-click="submit()">Add</button>
        </div>
    </form>
</div>