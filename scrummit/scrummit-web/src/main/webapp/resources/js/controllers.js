/**
 * INSPINIA - Responsive Admin Theme
 *
 */

/**
 * MainCtrl - controller
 */
function MainCtrl() {

    this.userName = 'Example user';
    this.helloText = 'Welcome in SeedProject';
    this.descriptionText = 'It is an application skeleton for a typical AngularJS web app. You can use it to quickly bootstrap your angular webapp projects and dev environment for these projects.';

};

function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;
 
        vm.login = login;
		vm.logout = logout;

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.success == 1) {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        };

		function logout() {
			AuthenticationService.Logout(function (response){
				if (response) {
					AuthenticationService.ClearCredentials();
					$location.path('/');
				}
			});
		};
};

function UserController($location, $cookies, UserService, FlashService) {
	var uc = this;
	this.update = update;
	this.updatePassword = updatePassword;
	var ck = $cookies.getObject('globals') || {};
	
	(function initController() {
        if (ck.currentUser) {
			UserService.GetByUsername(ck.currentUser.username, function (response){
				if (response) {
					uc.email = response.email;
					uc.username = response.username
					uc.firstname = response.firstName;
					uc.lastname = response.lastName;
				}
			});
		}
     })();
			
	function update() {
		
		if (ck.currentUser) {
			var username = ck.currentUser.username;
			UserService.UpdateUser(username, uc.password, uc.email, uc.firstname, uc.lastname, function(response){
				uc.dataLoading = true;
				if (response && response.error == 0) {
					FlashService.Success(response.message);
				} else {
					uc.dataLoading = false;
					FlashService.Error(response.message);
				}
			});
		}
	};
	
	function updatePassword() {
		if (ck.currentUser) {
			var username = ck.currentUser.username;
			
			UserService.ChangePassword(username, uc.oldPassword, uc.newPassword, function(response){
				uc.dataLoading = true;
				if (response && response.error == 0) {
					FlashService.Success(response.message);
				} else {
					uc.dataLoading = false;
					FlashService.Error(response.message);
				}
			});
		}
	}
}

function RegistrationController($location, $scope, $http, FlashService){
	$scope.isNotsubmitted = true;
	$scope.sendPost = function() {
		$scope.dataLoading = true;
		var data = $scope.user;
		$http.post("register/", data).success(function(data, status) {
			debugger;
			FlashService.Success("Account was created. You can login now. An email with verification link have been sent to your email, please activate your account.");
			$scope.dataLoading = false;
			$scope.isNotsubmitted = false;
		}).error(function(data, status) {
			FlashService.Error("There was an error in creating your account, please try again");
			$scope.dataLoading = false;
		});
	}
	$scope.goToLogin = function (){
		$location.path('/login');
	};
};

function ViewProjectController($scope, $http) {

    $http.get('rest/project/list').
    success(function(data) {
        $scope.projects = data;
    });
};

angular
	.module('inspinia')
	.controller('MainCtrl', MainCtrl)
	.controller('LoginController', LoginController)
	.controller('UserController', UserController)
	.controller('ViewProjectController', ViewProjectController)
	.controller('RegCtrl', RegistrationController);
