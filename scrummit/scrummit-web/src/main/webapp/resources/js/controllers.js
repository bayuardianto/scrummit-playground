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
					uc.username = response.username;
					uc.firstname = response.firstName;
					uc.lastname = response.lastName;
					uc.fullname = response.fullname;
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

function OrgMembersController($location, $http, $timeout, $scope, UserService) {
	
	var om = this;
	this.add = add;
	om.orgMember = {};
	
	om.orgMember.username = angular.copy(om.orgMember.firstname);
	console.log(om.orgMember.username);
	
	if($location.path().toString().indexOf("add")<=-1) {
		$http.get('rest/userbyorg/').
	    success(function(data) {
	    	$scope.orgMembers = data;
	        $timeout(function(){
				$('.table').trigger('footable_redraw');
			}, 100);
	        console.log($scope.orgMembers);
	    });
	}
	
	function add() {
		console.log(om.orgMember);
	}
	
};

function RegistrationController($location, $scope, $http, FlashService){
	$scope.isNotsubmitted = true;
	$scope.activationKey = "";
	$scope.sendPost = function() {
		$scope.dataLoading = true;
		var data = $scope.user;
		$http.post("register/", data).success(function(data, status) {
			//FlashService.Success("Account was created. You can login now. An email with verification link have been sent to your email, please activate your account.");
			if(data.error == 1) {
				FlashService.Error(data.message);
			}
			else {
				FlashService.Success(data.message);
			}
				
			$scope.dataLoading = false;
			$scope.isNotsubmitted = false;
		}).error(function(data, status) {
//			FlashService.Error("There was an error in creating your account, please try again");
			FlashService.Error(data);
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

function ProjectDetailController($scope, $http, $location, $stateParams, UserService, IterationService) {
	var pc = this;

	$scope.currPage = 0;
	$scope.pageSize = 5;
	$scope.loadBoard = loadBoard;
	$scope.name = $stateParams.name;
	$scope.daterange = {startDate: null, endDate: null};
	$scope.iteration = "";
	
	$scope.todoList = [];
	$scope.inProgressList = [];
	$scope.completedList = [];
	$scope.iterationName = "Iteration";
	
	(function init() {
		IterationService.setPrjDetailCtrl($scope);
		IterationService.getLastIteration($scope.name, function(data){
			if (data.id != null) {
				$scope.iterationName = data.name
				$scope.loadBoard(data.id);
			}
			
		});
		
	})();
	
	function loadBoard(iteration) {
		$scope.iteration = iteration;
		IterationService.getIterationById($scope.iteration, function(data){
			$scope.iterationName = data.name;
		});
		
		$http.get('rest/iteration/board/' + $scope.iteration).success(function(data){//get board information by iterationid
			$scope.todoList = data.todoList;
			$scope.inProgressList = data.inprogressList;
			$scope.completedList = data.completedList;
		});
		 
		 var start = -1;
		 var receive = -1;
		 
		 $scope.sortableOptions = {
             connectWith: ".connectList",
             start: function(e, ui) {
            	 element = ui.item.parent().attr('class');
            	 if (element.indexOf("todo-list")>=0) {
        	   		 start = 0;
        	   	 } else if (element.indexOf("in-progress-list")>=0) {
        	   		 start = 1;
        	   	 } else if (element.indexOf("completed-list")>=0) {
        	   		 start = 2;
        	   	 }
             },
             receive: function(e, ui) {
    	   		 var className = e.target.className;
    	   		 
        	   	 if (className.indexOf("todo-list")>=0) {
        	   		 receive = 0;
        	   	 } else if (className.indexOf("in-progress-list")>=0) {
        	   		 receive = 1;
        	   	 } else if (className.indexOf("completed-list")>=0) {
        	   		 receive = 2;
        	   	 }
             },
             stop: function(e, ui) {
           	 	 var startCards = [];
           	 	 var receiveCards = [];
           	 	 startCards = getCards(start);
          	 	 receiveCards = getCards(receive);
           	 	 if (start != receive && receive != -1) { // update both start and receive columns
               	 	 //update card status
               	 	 $http.put('rest/card/' + ui.item.attr("id") + "/status/" + receive);
               	 	 //update card orders in board
               	 	 $http.put('rest/iteration/board/' + $scope.iteration + '/' + start + '/' + receive, {"start": startCards, "receive": receiveCards});
               	 	 
           	 	 } else if (receive == -1) { // update start column (update card order)
               	 	$http.put('rest/iteration/board/' + $scope.iteration + '/'+start+'/' + receive, {"start": startCards});
           	 	 }
           	 	 //reset status
           	 	 start = -1;
           	 	 receive = -1;
             }
         };
	}
	
	getCards = function(status) {
		var cards = [];
		switch (status) {
	 		 case 0:
	 			var arr = $('.todo-list').sortable('toArray');
	 			$(arr).each(function(i, v){
	 				card = {};
	 				card["id"] = v;
	 				cards.push(card);
	 			});
	 			break;
	 		 case 1:
	 			var arr = $('.in-progress-list').sortable('toArray');
	 			$(arr).each(function(i, v){
	 				card = {};
	 				card["id"] = v;
	 				cards.push(card);
	 			});
	 			 break;
	 		 case 2:
	 			var arr = $('.completed-list').sortable('toArray');
	 			$(arr).each(function(i, v){
	 				card = {};
	 				card["id"] = v;
	 				cards.push(card);
	 			});
	 			 break;
			 default:
				 break; 
 		 }
		return cards;
	}
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

function CardModalController($scope, $http, $uibModalInstance, CardService, FlashService, OrganizationMemberService, $stateParams, ProjectDetailService, IterationService) {
	
	$scope.orgmembers = OrganizationMemberService.query();
	$scope.tasks = [];
	$scope.showMessage = 0;

	var ic = this;
	$scope.projectName = $stateParams.name;
	$scope.projectId = "";

	this.getIterations = getIterations;

	(function init() {
		getIterations();
	})();

    function getIterations() {

		ProjectDetailService.getProjectByName($scope.projectName, function(data){
			$scope.projectId = data.id;
			IterationService.getIterationsByProject(data.id, function(iterationData){
				if (data == null) {
					$scope.iterations = [];
				} else {
					$scope.iterations = iterationData;
				}
			});
		});
    }

    $scope.addTask = function (){
        $scope.tasks.push({
            description: "",
            owner: "",
            status: ""
        });
        console.log($scope.tasks);
    }

    $scope.saveCard = function (){
        var newCard = {};
        var newTasks = [];
        newCard = $scope.card;
        newTasks = $scope.tasks;

        console.log("assignee: " + newCard.assignee);
        console.log("iteration:" + newCard.iteration);

        var iteration = {"id" : newCard.iteration};
        var assignee = {"id": newCard.assignee};
        if (newCard.assignee == undefined){
            assignee = null;
        }
        if (newCard.iteration == undefined){
            iteration = null;
        }
        newCard.iteration = iteration;
        newCard.assignee = assignee;
        CardService.saveCard(newCard, function(response){
            $scope.dataLoading = true;
            if (response.success == true){
                FlashService.Success(response.message);
                console.log(response.message);
                if (newCard.iteration !== null){
                    $http.post('rest/iteration/board/', {'iteration': {'id': response.iteration.id}, 'status': response.status, 'cards': [{'id': response.id}]}).success(function(data){
                        console.log("Creating/Updating board for new card");
                        IterationService.getPrjDetailCtrl().loadBoard(response.iteration.id);
                    });
                }
                if (newTasks.length > 0){
                    angular.forEach(newTasks, function(newTask, index){
                        $http.post('rest/task/create/',{"description": newTask.description, "owner": {"id":newTask.owner}, "status": newTask.status, "card":{"id":response.id}}).success(function(data){
                            console.log("Task #" + (index + 1) + " successfully saved!");
                        });
                    })
                    $scope.tasks = {};
                }
                $scope.showMessage = 1;
            }else{
		    	$scope.dataLoading = false;
		    	console.log(response.message);
            }
        });
        $scope.card = {};
    };

    $scope.keypress = function(){
        console.log('flash message should be disappear');
        $scope.showMessage = 0;
    };

    $scope.cancel = function() {
    	$uibModalInstance.dismiss('cancel');
    };

    $scope.getAllCards = function(){
        CardService.getAllCards(function(response){
            console.log(response[0]);
        });
    };
}

function IterationController($scope, $uibModal, $stateParams, ProjectDetailService, IterationService) {
	var ic = this;
	$scope.projectName = $stateParams.name;
	$scope.projectId = "";
	
	this.refreshIterations = refreshIterations;
	
	(function init() {
		refreshIterations();
	})();
	
	function refreshIterations() {
		console.log("Iteration Controller - Project:"+ $scope.projectName);
		ProjectDetailService.getProjectByName($scope.projectName, function(data){
			$scope.projectId = data.id;
			IterationService.getIterationsByProject(data.id, function(iterationData){
				if (data == null) {
					$scope.iterations = [];
				} else {
					$scope.iterations = iterationData;
				}
			});
		});
	}
	
	$scope.openCreateIterationModal = function(size) {
		var modalInstance = $uibModal.open({
            templateUrl: 'views/iteration/modal',
            controller: IterationModalController,
            size: size,
            resolve: {
            	projectId: function(){
            		return $scope.projectId;
            	},
            	parentCtrl: function(){
            		return ic;
            	}
            }
        });
	}
}

function IterationModalController($scope, FlashService, $uibModalInstance, IterationService, projectId, parentCtrl) {
	
	$scope.iterationName = "";
	$scope.description = "";
	$scope.daterange = {startDate: null, endDate: null};
	
	$scope.ok = function () {
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    
    $scope.submit = function () {
    	
    	IterationService.create({
			"name": $scope.iterationName, 
			"description": $scope.description, 
			"project": {"id": projectId}, 
			"startDate": $scope.daterange.startDate, 
			"endDate": $scope.daterange.endDate
		}, function(data){
			if (data.error == 0) {
				parentCtrl.refreshIterations();
				$uibModalInstance.close();
			} else {
				alert(data.message);
			}
			
		});
    	
    }
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

function BacklogController($scope, $location, BacklogService){
    $scope.backlogs = [];
    angular.element(document).ready(function () {
        BacklogService.getBacklogs(function(response){
            backlogs = response;
        });
	});
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
	.controller('IterationController', IterationController)
	.controller('IterationModalController', IterationModalController)
	.controller('ProjectModalController', ProjectModalController)
	.controller('OrgMembersController', OrgMembersController)
	.controller('NotifModalController', NotifModalController);
