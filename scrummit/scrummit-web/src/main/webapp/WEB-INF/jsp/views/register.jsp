<%@page import="java.util.Date"%>
<script>
	
</script>
<div class="signup-box loginscreen  animated fadeInDown"
	ng-controller="RegCtrl">
	<div>
		<div class="text-center">
			<h1>Create An Account</h1>
		</div>



		<form class="m-t" role="form" ng-submit="sendPost()">

			<div class="row">
				<div class="col-xs-12 col-md-6">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" required=""
							ng-model="user.username">
					</div>
					<div class="form-group">
						<label for="email">Email address</label> <input type="email"
							id="email" class="form-control" required="" ng-model="user.email">
					</div>
				</div>
				<div class="col-xs-12 col-md-6">
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							id="password" class="form-control" required=""
							ng-model="user.password">
					</div>
					<div class="row">

						<div class="form-group col-md-6">
							<label for="firstname">First Name</label> <input type="text"
								id="firstname" class="form-control" required=""
								ng-model="user.firstName">
						</div>

						<div class="form-group col-md-6">
							<label for="lastname">Last Name</label> <input type="text"
								id="lastname" class="form-control" required=""
								ng-model="user.lastName">
						</div>
					</div>

				</div>
				<div>
					<h2 class="col-xs-12 text-center">Organization Data</h2>
				</div>
				<div class="col-xs-12 col-md-6">
					<div class="form-group">
						<label for="organizationame">Organization Name</label> <input
							type="text" class="form-control" id="organizationame" required=""
							ng-model="user.assocOrgId.organizationName">
					</div>

					<div class="form-group">
						<label for="orgemail">Organization Email address</label> <input
							type="orgemail" id="orgemail" class="form-control" required=""
							ng-model="user.assocOrgId.contacts.email">
					</div>
				</div>
				<div class="col-xs-12 col-md-6">
					<div class="form-group">
						<label for="address">Address</label> <input type="text"
							id="address" class="form-control" required=""
							ng-model="user.assocOrgId.contacts.address">
					</div>
					<div class="form-group">
						<label for="phone">Phone</label> <input type="text" id="phone"
							class="form-control" required=""
							ng-model="user.assocOrgId.contacts.phone">
					</div>
				</div>
			</div>
			<div
				ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }"
				ng-if="flash">
				<p ng-bind="flash.message"></p>
				<br> <a ng-if="flash.type === 'success'" ng-click="goToLogin()">Login Now</a>
			</div>

			<div class="row text-center" ng-if="isNotsubmitted">
				<div class="col-sm-4 col-sm-offset-4">
					<input type="submit" class="btn btn-primary block full-width m-b"
						value="Create account" />
				</div>
			</div>
			<div class="row text-center">
				<img ng-if="dataLoading"
					src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />

			</div>

		</form>

		<p class="m-t text-center">
			<small>Mitrais Scrummit &copy; <%=new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%></small>
		</p>
	</div>
</div>

