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
	$scope.activationKey = "";
	$scope.sendPost = function() {
		$scope.dataLoading = true;
		var data = $scope.user;
		$http.post("register/", data).success(function(data, status) {
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
	
	angular.element(document).ready(function () {
		var param = $location.search();

		
		var verifyAccount = function() {
			$scope.dataLoading = true;
			$http.get("register/verify?key="+$scope.activationKey, null).success(function(status) {
				var defaultSuccessMessage = "Your account successfully activated. Enjoy using all available feature\n You can go back to main page:";
				if(status.error == 1 && status.message){
					defaultSuccessMessage = status.message;
				}
				FlashService.Success(defaultSuccessMessage);
				$scope.dataLoading = false;
			}).error(function(status) {
				FlashService.Error("There was an error in verifying your account, please try again");
				$scope.dataLoading = false;
			});
		};
		
		if(param.key){
			$scope.activationKey = param.key;
			verifyAccount();
		}
	});
	

	
};

function ViewProjectController($scope, $state, $http, $stateParams) {
	
    $http.get('rest/project/').
    success(function(data) {
        $scope.projects = data;
    });
    
};

function ProjectDetailController($scope, $http, $location, $stateParams) {
	var pc = this;
	$scope.name = $stateParams.name;
			
	(function init() {
		$http.get('rest/project/name/' + $scope.name).success(function (data){
			console.log($scope.name);
			$scope.todoList = [
			                   {
			                       content: 'Simply dummy text of the printing and typesetting industry.',
			                       date: '12.10.2015',
			                       statusClass: 'warning',
			                       tagName: 'Mark'
			                   },
			                   {
			                       content: 'Many desktop publishing packages and web page editors now use Lorem Ipsum as their default.',
			                       date: '05.04.2015',
			                       statusClass: 'success',
			                       tagName: 'Tag'
			                   },
			                   {
			                       content: 'Sometimes by accident, sometimes on purpose (injected humour and the like).',
			                       date: '16.11.2015',
			                       statusClass: 'info',
			                       tagName: 'Mark'
			                   },
			                   {
			                       content: 'All the Lorem Ipsum generators',
			                       date: '06.10.2015',
			                       statusClass: 'danger',
			                       tagName: 'Tag'
			                   }
			               ];
			               $scope.inProgressList = [
			                   {
			                       content: 'Quisque venenatis ante in porta suscipit.',
			                       date: '12.10.2015',
			                       statusClass: 'success',
			                       tagName: 'Mark'
			                   },
			                   {
			                       content: ' Phasellus sit amet tortor sed enim mollis accumsan in consequat orci.',
			                       date: '05.04.2015',
			                       statusClass: 'success',
			                       tagName: 'Tag'
			                   },
			                   {
			                       content: 'Nunc sed arcu at ligula faucibus tempus ac id felis. Vestibulum et nulla quis turpis sagittis fringilla.',
			                       date: '16.11.2015',
			                       statusClass: 'warning',
			                       tagName: 'Mark'
			                   },
			                   {
			                       content: 'Ut porttitor augue non sapien mollis accumsan. Nulla non elit eget lacus elementum viverra.',
			                       date: '09.12.2015',
			                       statusClass: 'warning',
			                       tagName: 'Tag'
			                   }
			               ];
			               $scope.completedList = [
			                   {
			                       content: 'Sometimes by accident, sometimes on purpose (injected humour and the like).',
			                       date: '16.11.2015',
			                       statusClass: 'info',
			                       tagName: 'Mark'
			                   },
			                   {
			                       content: 'Ut porttitor augue non sapien mollis accumsan. Nulla non elit eget lacus elementum viverra.',
			                       date: '09.12.2015',
			                       statusClass: 'warning',
			                       tagName: 'Tag'
			                   },
			                   {
			                       content: 'Which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.',
			                       date: '09.12.2015',
			                       statusClass: 'warning',
			                       tagName: 'Tag'
			                   },
			                   {
			                       content: 'Packages and web page editors now use Lorem Ipsum as',
			                       date: '08.04.2015',
			                       statusClass: 'warning',
			                       tagName: 'Tag'
			                   }
			               ];

			               $scope.sortableOptions = {
			                   connectWith: ".connectList"
			               };
		});
	})();
}

function CardController($scope, $uibModal) {
	$scope.openCreateCardModal = function(size) {
		var modalInstance = $uibModal.open({
            templateUrl: 'views/card_modal',
            controller: CardModalController,
            size: size
        });
	}
}

function CardModalController($scope, $uibModalInstance) {
	$scope.ok = function () {
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}

function ProjectController($scope, $location ,ProjectService, OrganizationMemberService, FlashService, $stateParams) {
	var pc = this;
	pc.id = $stateParams.id;

	//get organization members data for combo box

	$scope.orgmembers = OrganizationMemberService.query();

	//initialize roles for drop down
	$scope.roles = [{id: 0, text: "PROJECT OWNER"}, {id: 1, text: "SCRUM MASTER"}, {id: 2, text: "MEMBER"}];

	$scope.project = {};
	//$scope.project.members.member = [];
	$scope.project.members = [];

	$scope.member = {};
	$scope.members = [];

	//get projects data
	$scope.projects = ProjectService.query();

	//get specific project by id
	if(pc.id != null)
	{
		ProjectService.get({project: pc.id}).$promise.then(function(value){
			$scope.project = value;
			$scope.title = "Update " + value.name + " Project";
		});
		$scope.topmenu = "Update Project";
	}
	else 
	{
		$scope.title = "Add New Project";
		$scope.topmenu = "Add Project";
	}




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

			var memberCheck = $.grep($scope.project.members, function (member) {
				return member.userId == memberId;
			})[0];

			if (memberCheck == null) {
				$scope.member.userId = memberId;
				$scope.member.role = roleId;
				$scope.member.roleName = roleName;
				$scope.member.userName = memberName;
				$scope.project.members.push($scope.member);
			}
			else {
				FlashService.Error("This person is already on member list.");
			}
			$scope.member = {};
		}
		else {
			FlashService.Error("Please select person or role.");
		}
	};
	
	$scope.saveProject = function () {
		if($scope.project.id != null) {
			updateProject();
		}
		else {
			createProject();
		}
	};
	
	function createProject() {
		ProjectService.save($scope.project, function (response) {
			FlashService.Success("Create project success.");
		}, function (err) {
			FlashService.Error(err.message);
		});
	};
	
	function updateProject() {
		var project = ProjectService.get({project: $scope.project.id});
		
		project.name = $scope.project.name;
		project.description = $scope.project.description;
		project.members = $scope.project.members;
		

		ProjectService.update({project: $scope.project.id}, project, function (response) {
			FlashService.Success("Update project success.");
		}, function (err) {
			FlashService.Error(err.message);
		});
	};

	$scope.goToAdd = function() {
		$location.url('/addproject.jsp');
	};
};

angular
	.module('inspinia')
	.controller('MainCtrl', MainCtrl)
	.controller('LoginController', LoginController)
	.controller('UserController', UserController)
	.controller('ViewProjectController', ViewProjectController)
	.controller('RegCtrl', RegistrationController)
	.controller('ProjectDetailController', ProjectDetailController)
	.controller('ProjectController', ProjectController)
	.controller('CardController', CardController)
	.controller('CardModalController', CardModalController);
