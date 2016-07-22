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

function OrgMembersController($location, $http, $timeout, $scope, UserService, FlashService) {
	var om = this;
	om.orgMember = {};

	om.orgMember.username = angular.copy(om.orgMember.firstname);
	console.log(om.orgMember.username);

    UserService.GetSession().then(function(data){
        console.log(data);
        console.log(data.assocOrgId.organizatioName);
        $scope.defaultOrg = data;
    });

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

	$scope.addMember = function () {

        var memberData = {
            username: $scope.member.username,
            password: $scope.member.password,
            email: $scope.member.email,
            firstName: $scope.member.firstName,
            lastName: $scope.member.lastName,
            assocOrgId: {
                organizationId: $scope.defaultOrg.assocOrgId.organizationId,
                organizatioName: $scope.defaultOrg.assocOrgId.organizatioName,
                dbSettings: {
                    dbName: $scope.defaultOrg.assocOrgId.dbSettings.dbName
                }
            }
        };

        $scope.isNotsubmitted = true;
        $scope.activationKey = "";

        UserService.AddOrgMember(memberData).then(function(data){
            if(data.error == 1){
                FlashService.Error(data.message);
            }else{
                FlashService.Success(data.message);
            }
            $scope.timerMsg();
            $scope.dataLoading = false;
            $scope.isNotSubmitted = false;
        }, function(data){
            FlashService.Error(data.message);
            $scope.isNotSubmitted = false;
        });
	}

	$scope.timerMsg = function(){
        $scope.alertDisplayed = true;
        $timeout(function() {
            $scope.alertDisplayed = false;
        }, 5000)
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
	$scope.openCreateCardModal = function(size, cardId) {
		var modalInstance = $uibModal.open({
            templateUrl: 'views/card_modal',
            controller: CardModalController,
            size: size,
            resolve: {
                cardId : function(){
                    return cardId;
                }
            }
        });

	}
}

function CardModalController($scope, $location, $http, $uibModalInstance, CardService,
                             FlashService, OrganizationMemberService, $stateParams,
                             ProjectDetailService, IterationService, cardId) {

    $scope.card = {};
    $scope.tasks = [];
    $scope.mode = 0; /* 0 for create, 1 for edit */
    $scope.status = {
        0: "Todo",
        1: "In Progress",
        2: "Completed"
    };
    $scope.points = {
        0: "0 point",
        1: "1 point",
        2: "2 points",
        3: "3 points",
        5: "5 points",
        8: "8 points",
        13: "13 points",
        20: "20 points",
        40: "40 points",
        100: "100 points",
    };
    $scope.epics = {
        0: "Epic 1"
    }
    $scope.convertToInt = function(val){
        return parseInt(val);
    };


	$scope.orgmembers = OrganizationMemberService.query();
	$scope.card.owner = $scope.orgmembers.id;

	$scope.showMessage = 0;
    $scope.oldCard = {};
    $scope.oldTasks = [];

	var ic = this;
	$scope.projectName = $stateParams.name;
	$scope.projectId = "";

	this.getIterations = getIterations;

/*	(function init() {
		getIterations();
	})();*/

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


    angular.element(document).ready(function () {
        getIterations();

        /* modify mode */
        if (cardId !== undefined){
            $scope.mode = 1; /* 0 for create, 1 for edit */
            CardService.getById(cardId).then(function(data){
                $scope.card = data;
                $scope.card.iteration = data.iteration.id;
                $scope.oldCard = angular.copy($scope.card);
            });
            CardService.getTasksByCardId(cardId).then(function(data){
                $scope.tasks = data;
                $scope.oldTasks = angular.copy($scope.tasks);
            });
        }
    });

    $scope.addTask = function (){
        $scope.tasks.push({
            description: "",
            owner: "",
            status: ""
        });
        console.log($scope.tasks);
    }

    $scope.saveCard = function (){

        newCard = $scope.card;
        newTasks = $scope.tasks;

        console.log("owner: " + newCard.owner);
        console.log("iteration:" + newCard.iteration);

        var iteration = {"id" : newCard.iteration};

        if (newCard.iteration == undefined){
            iteration = null;
        }
        newCard.iteration = iteration;

        if ($scope.mode == 0){

            CardService.saveCard(newCard).then(function(response){
                $scope.dataLoading = true;
                if (response.success == true){
                    FlashService.Success(response.message);
                    console.log(response.message);
                    $http.post('rest/iteration/board/', {'iteration': {'id': response.iteration.id}, 'status': response.status, 'cards': [{'id': response.id}]}).success(function(data){
                        console.log("Creating/Updating board for new card");
                        IterationService.getPrjDetailCtrl().loadBoard(response.iteration.id);
                    });
                    if (newTasks.length > 0){
                        angular.forEach(newTasks, function(newTask, index){
                            $http.post('rest/task/create/',{"description": newTask.description, "owner": newTask.owner, "status": newTask.status, "card":{"id":response.id}}).success(function(data){
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
        }else{
            var modifiedCard = {
                "id": newCard.id,
                "title": newCard.title,
                "description": newCard.description,
                "estimate": newCard.estimate,
                "iteration": newCard.iteration,
                "owner": newCard.owner,
                "status": newCard.status,
                "points": newCard.points
            };

            CardService.saveCard(modifiedCard).then(function(response){
                $scope.dataLoading = true;
                 if (response.success == true){
                    FlashService.Success(response.message);
                    console.log(response.message);
                    $scope.showMessage = 1;
                    IterationService.getPrjDetailCtrl().loadBoard(response.iteration.id);
                    $scope.card = {};

                    if (newTasks.length > 0){
                        angular.forEach(newTasks, function(newTask, index){
                            if (newTask.id !== undefined){
                                $http.put('rest/task/update/',
                                    {"id": newTask.id,
                                     "description": newTask.description,
                                     "owner": newTask.owner,
                                     "status": newTask.status,
                                     "card":{"id":response.id}
                                     })
                                    .success(function(data){
                                        console.log("Task #" + (index + 1) + " successfully modified!");
                                    }
                                );
                            }else{
                                $http.post('rest/task/create/',
                                    {"description": newTask.description, "owner": newTask.owner, "status": newTask.status, "card":{"id":response.id}})
                                    .success(function(data){
                                        console.log("Task #" + (index + 1) + " successfully saved!");
                                    }
                                );
                            }
                        });
                        $scope.tasks = {};
                    }
                 }
            });
        }
    };

    $scope.cancel = function() {
    	$uibModalInstance.dismiss('cancel');
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



function MenuController($scope, $location, $http, MenuService) {
	MenuService.testAja('').then(function(data){
        $scope.usre = data;
    });
	
	/*MenuService.retrieve1stLevelMenu().then(function(data){
		$scope.zmenus = data;
	});*/
	
	
	
}

function TreeTableController($scope, $q, $http, ngTreetableParams, MenuService){
	function ambildatamenu(){
		var deferred = $q.defer();
        MenuService.retrieve1stLevelMenu().then(function(data){
        	deferred.resolve(data);
    	});
        return deferred.promise;
	}
	var datamnu = ambildatamenu();
	
	$scope.expanded_params = new ngTreetableParams({
        getNodes: function(parent) {
            return parent ? parent.children : datamnu;
        },
        getTemplate: function(node) {
            return 'tree_node';
        },
        options: {
//            initialState: 'expanded'
        }
    });

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
	.controller('NotifModalController', NotifModalController)
	.controller('MenuController', MenuController)
	.controller('TreeTableController',TreeTableController);
