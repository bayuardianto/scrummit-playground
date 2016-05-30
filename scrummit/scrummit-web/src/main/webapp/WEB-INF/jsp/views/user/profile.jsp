<div class="signup-box loginscreen  animated fadeInDown">
    <div ng-controller="UserController as uc">
    	<div class="text-center">
    		<h2>Update account information</h2>
    	</div>
        
        <form class="m-t" role="form" ng-submit="uc.update()">
        
        	<div class="row">
        		<div class="col-xs-6">
		            <div class="form-group">
		            	<label for="username">Username</label>
		            	<input type="text" id="username" name="username" ng-model="uc.username" class="form-control" readonly>
		            </div>
  					<div class="form-group">
  						<label for="firstname">First Name</label>
		                <input type="text" id="firstname" name="firstname" ng-model="uc.firstname" class="form-control" required="">
		            </div>
        		</div>
  				<div class="col-xs-6">
		            <div class="form-group">
		            	<label for="email">Email address</label>
		                <input type="email" id="email" name="email" ng-model="uc.email" class="form-control" required="">
		            </div>
		            
		            <div class="form-group">
  						<label for="lastname">Last Name</label>
		                <input type="text" id="lastname" class="form-control" name="lastname" ng-model="uc.lastname" required="">
		            </div>
		            
  				</div>
        	</div>
        	<div
				ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }"
				ng-if="flash">
				<p ng-bind="flash.message"></p>
			</div>
        	<div class="row text-center">
        		<div class="col-sm-4 col-sm-offset-4">
        			<input type="submit" class="btn btn-primary block full-width m-b" value="Update"/>
        		</div>
        	</div>
        	<div class="row text-center">
				<img ng-if="dataLoading"
					src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />

			</div>
        </form>
        
    </div>
</div>