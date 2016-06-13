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

	$scope.currPage = 0;
	$scope.pageSize = 5;

	$scope.name = $stateParams.name;
	
	$scope.todoList = [];
	$scope.inProgressList = [];
	$scope.completedList = [];
	
	(function init() {
		getProjectByName($scope.name, function(response){
			console.log(response.id);
			$http.get('rest/card/byiteration/' + '5754cf1f09e2370228ed9ddd').success(function(data){
				
				angular.forEach(data.cardList, function(value, key) {
				  if (value.status == 0) {
					  $scope.todoList.push(value);
				  } else if(value.status == 1) {
					  $scope.inProgressList.push(value);
				  } else if (value.status == 2) {
					  $scope.completedList.push(value);
				  }
				});
				
			});
			
			 $scope.sortableOptions = {
	                   connectWith: ".connectList"
	               };
		});
	})();
	
	function getProjectByName(name, callback) {
		$http.get('rest/proj/' + name).success(function(data){
			callback(data);
		});
	};
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

function ProjectController($scope, $location ,ProjectService, 
						   OrganizationMemberService, FlashService, $stateParams, 
						   $uibModal, $timeout) {
	var pc = this;
	pc.id = $stateParams.id;

	pc.projectMembers = [];

	//get organization members data for combo box
	$scope.orgmembers = OrganizationMemberService.query();

	//initialize roles for drop down
	$scope.roles = [{id: 0, text: "PROJECT OWNER"}, {id: 1, text: "SCRUM MASTER"}, {id: 2, text: "MEMBER"}];

	$scope.project = {};
	$scope.project.members = [];

	$scope.member = {};
	$scope.members = [];

	//get projects data
	var getProjects = function () {
		ProjectService.query(function (value) {
			$scope.projects = value;
			$timeout(function(){
				$('.table').trigger('footable_redraw');
			}, 100);
		});
	};
	getProjects();

	//get specific project by id
	if(pc.id != null)
	{
		ProjectService.get({project: pc.id}).$promise.then(function(value){
			$scope.project = value;
			if(value.members.length == 1 && value.members[0].userId == null)
				$scope.project.members = angular.copy([]);
			$scope.title = "Update " + value.name + " Project";
		}, function (response) {

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
				return member.userId == memberId;
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
				//FlashService.Error("This person is already on member list.");
				openNotif("sm", "The person selected is already on member list.");
			}
			$scope.member = {};
		}
		else {
			//FlashService.Error("Please select person or role.");
			openNotif("sm", "Please select person or role.");
		}
	};

	$scope.removeMember = function (member, projectMembers) {
		$scope.project.members = angular.copy(projectMembers);
		var index = $scope.project.members.indexOf(member);
		$scope.project.members.splice(index, 1);
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
			//FlashService.Success("Create project success.");
			openNotif("sm", "Save project success.")
		}, function (err) {
			openNotif("sm", "Save project failed. " + err.message())
		});
	};
	
	function updateProject() {
		var project = ProjectService.get({project: $scope.project.id});
		
		project.name = $scope.project.name;
		project.description = $scope.project.description;
		project.members = angular.copy($scope.project.members);

		ProjectService.update({project: $scope.project.id}, project, function (response) {
			//FlashService.Success("Update project success.");
			openNotif("sm", "Update project success.");
		}, function (err) {
			//FlashService.Error(err.message);
			openNotif("sm", "Update project failed. " + err.message())
		});
	};

	$scope.openArchiveModal = function(size, id) {
		var modalInstance = $uibModal.open({
			templateUrl: 'views/archive_modal',
			controller: ProjectModalController,
			size: size,
			resolve: {
				id: function () {return id;}
			}
		});

		modalInstance.result.then(function () {
			getProjects();
		});
	};

	var openNotif = function (size, message) {
		var modalInstance = $uibModal.open({
			templateUrl: 'views/notif_modal',
			controller: NotifModalController,
			size: size,
			resolve: {
				message: function () {return message;}
			}
		});

		modalInstance.result.then(function () {

		});
	};

	$scope.goToAdd = function() {
		$location.url('/addproject.jsp');
	};

	$scope.cancel = function() {
		$location.url('/views/projects.jsp');
	};
};

function ProjectModalController($scope, $uibModalInstance, ProjectService, id) {
	$scope.id = id;
	

	$scope.ok = function () {
		ProjectService.delete({project: id.id}, function (response) {
			$uibModalInstance.close();
		});
	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
}

function NotifModalController($scope, $uibModalInstance, message) {
	$scope.message = message;

	$scope.ok = function () {
		$uibModalInstance.close();
	};
}


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
	.controller('CardModalController', CardModalController)
	.controller('ProjectModalController', ProjectModalController)
	.controller('NotifModalController', NotifModalController);
