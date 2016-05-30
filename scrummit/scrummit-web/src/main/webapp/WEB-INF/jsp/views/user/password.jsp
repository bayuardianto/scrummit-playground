<div class="signup-box loginscreen  animated fadeInDown">
    <div class="col-xs-12" ng-controller="UserController as uc">
    	<div class="row">
    			<div class="text-center">
		    		<h2>Change password</h2>
		    	</div>
    	</div>
    	
        
        <form class="m-t" role="form" name="myForm" ng-submit="uc.updatePassword()">
        	<div class="row">
        			<div class="form-group">
		            	<label for="oldPassword">Old Password</label>
		            	<input type="password" id="oldPassword" name="oldPassword" ng-model="uc.oldPassword" class="form-control" required>
		            </div>
        		
		            
        	</div>
        	<div class="row">
		            <div class="form-group">
		            	<label for="newPassword">New Password</label>
		                <input type="password" id="newPassword" name="newPassword" ng-model="uc.newPassword" class="form-control" required="">
		            </div>	
  			</div>
        	<div class="row">
		            <div class="form-group">
		            	<label for="newPasswordConfirm">Confirm Password</label>
		            	<input type="password" id="newPasswordConfirm" name="newPasswordConfirm" ng-model="newPasswordConfirm" class="form-control" required pw-check="newPassword">
		            </div>
        	</div>
       		<div class="msg-block" ng-show="myForm.$error">
			  <span class="text-danger" ng-show="myForm.newPasswordConfirm.$error.pwmatch">
			    Passwords don't match.
			  </span>
			</div>
			<div class="row">
		        	<div
						ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }"
						ng-if="flash">
						<p ng-bind="flash.message"></p>
					</div>
			</div>
			
        	<div class="row text-center">
        		<div class="col-sm-4 col-sm-offset-4">
        			<input type="submit" class="btn btn-primary block full-width m-b" value="Update"
        			ng-disabled="myForm.newPasswordConfirm.$error.pwmatch"/>
        		</div>
        	</div>
        	<div class="row text-center">
				<img ng-if="dataLoading"
					src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />

			</div>
        </form>
        
    </div>
</div>