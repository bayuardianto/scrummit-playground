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
	var ck = $cookies.getObject('globals') || {};
	
	(function initController() {
        if (ck.currentUser) {
			UserService.GetByUsername(ck.currentUser.username, function (response){
				if (response) {
					uc.email = response.email;
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
				if (response && response.success == true) {
					FlashService.Success("Account information was updated successfully!");
				} else {
					uc.dataLoading = false;
					FlashService.Error(response.message);
				}
			});
		}
	};
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

    $http.get('http://localhost:8080/scrummit/rest/project/').
    success(function(data) {
        $scope.projects = data;
    });
};

function ProjectController($scope, $location ,ProjectService, OrganizationMemberService) {

	//get projects data
	$scope.projects = ProjectService.query();

	//$scope.newProject = $scope.newProject;

	//get organization members data for combo box
	$scope.orgmembers = OrganizationMemberService.query();

	//initialize roles for drop down
	$scope.roles = [{id: 1, text: "Role 1"}, {id: 2, text: "Role 2"}];

	$scope.member = {};
	$scope.members = [];
	
	$scope.storeTemp = function() {
		/* do validation here or in html before ... */

		if($scope.selectedUser != null && $scope.selectedRole != null) {
			var memberId = $scope.selectedUser;
			var memberName = $.grep($scope.orgmembers, function (member) {
				return member.id == memberId;
			})[0].fullName;

			var roleId = $scope.selectedRole;
			var roleName = $.grep($scope.roles, function (role) {
				return role.id == roleId;
			})[0].text;

			var memberCheck = $.grep($scope.members, function (member) {
				return member.userId == memberId;
			})[0];

			if (memberCheck == null) {
				$scope.member.userId = memberId;
				$scope.member.role = roleId;
				$scope.member.roleName = roleName;
				$scope.member.userName = memberName;
				$scope.members.push($scope.member);
			}
			else {
				// notify({
				// 	message:'This person is already on the member list.',
				// 	classes: 'alert-info'
				// });
			}
			$scope.member = {};
		}
		else {
			// notify({
			// 	message:'Please select a person or a role.',
			// 	classes: 'alert-info'
			// });
		}
	};
	
	$scope.saveProject = function() {
		if($scope.members != null) {
			$scope.project.members = $scope.members;
		}
		
		ProjectService.save($scope.project, function (data) {
			// notify({
			// 	message:'New project data has been successfully saved.',
			// 	classes: 'alert-info'
			// });
		}, function (err) {
			// notify({
			// 	message:'Save project failed.',
			// 	classes: 'alert-info'
			// });
		});
	};
};

angular
	.module('inspinia')
	.controller('MainCtrl', MainCtrl)
	.controller('LoginController', LoginController)
	.controller('UserController', UserController)
	.controller('RegCtrl', RegistrationController)
	.controller('ViewProjectController', ViewProjectController)
	.controller('ProjectController', ProjectController);
