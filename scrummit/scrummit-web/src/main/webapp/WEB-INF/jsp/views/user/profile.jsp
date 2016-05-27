<div class="signup-box loginscreen  animated fadeInDown">
    <div ng-controller="UserController as uc">
    	<div class="text-center">
    		<h2>Update account information</h2>
    	</div>
        
        <form class="m-t" role="form" action="#">
        
        	<div class="row">
        		<div class="col-xs-6">
		            <div class="form-group">
		            	<label for="email">Email address</label>
		                <input type="email" id="email" name="email" ng-model="uc.email" class="form-control" required="">
		            </div>
  					<div class="form-group">
  						<label for="firstname">First Name</label>
		                <input type="text" id="firstname" name="firstname" ng-model="uc.firstname" class="form-control" required="">
		            </div>
        		</div>
  				<div class="col-xs-6">
		            <div class="form-group">
		            	<label for="password">Password</label>
		                <input type="password" id="password" name="password" ng-model="uc.password" class="form-control" required="">
		            </div>
		            
		            <div class="form-group">
  						<label for="lastname">Last Name</label>
		                <input type="text" id="lastname" class="form-control" name="lastname" ng-model="uc.lastname" required="">
		            </div>
		            
  				</div>
        	</div>
        	<div class="row text-center">
        		<div class="col-sm-4 col-sm-offset-4">
        			<input type="button" class="btn btn-primary block full-width m-b" value="Update"/>
        		</div>
        	</div>
        
        </form>
        
        <p class="m-t text-center"> <small>Inspinia web app framework base on Bootstrap 3 &copy; 2014</small> </p>
    </div>
</div>