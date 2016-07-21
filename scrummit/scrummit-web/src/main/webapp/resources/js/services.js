function AuthenticationService($http, $cookies, $rootScope, $timeout, UserService) {
	var service = {};
	 
    service.Login = Login;
    service.Logout = Logout;
    service.SetCredentials = SetCredentials;
    service.ClearCredentials = ClearCredentials;

    return service;

    function Login(username, password, callback) {
    	$http.post('login/authenticate/', { username: username, password: password }).success(function (response) {
                callback(response);
        });
    }
    
    function Logout(callback) {
    	$http.post('logout/').success(function (response) {
                callback(response);
        });
    }
    
    function SetCredentials(username, password) {
        var authdata = Base64.encode(username + ':' + password);

        $rootScope.globals = {
            currentUser: {
                username: username,
                authdata: authdata
            }
        };

        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
        $cookies.putObject('globals', $rootScope.globals);
    }

    function ClearCredentials() {
        $rootScope.globals = {};
        $cookies.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic';
    }
    
}

//Base64 encoding service used by AuthenticationService
var Base64 = {

    keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',

    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        do {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                this.keyStr.charAt(enc1) +
                this.keyStr.charAt(enc2) +
                this.keyStr.charAt(enc3) +
                this.keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        } while (i < input.length);

        return output;
    },

    decode: function (input) {
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
        var base64test = /[^A-Za-z0-9\+\/\=]/g;
        if (base64test.exec(input)) {
            window.alert("There were invalid base64 characters in the input text.\n" +
                "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                "Expect errors in decoding.");
        }
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        do {
            enc1 = this.keyStr.indexOf(input.charAt(i++));
            enc2 = this.keyStr.indexOf(input.charAt(i++));
            enc3 = this.keyStr.indexOf(input.charAt(i++));
            enc4 = this.keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";

        } while (i < input.length);

        return output;
    }
};

function UserService($http, $q) {
	var service = {};
	 
    service.GetByUsername = GetByUsername;
    service.UpdateUser = UpdateUser;
    service.ChangePassword = ChangePassword;
    service.GetById = GetById;
    service.GetSession = GetSession;
    service.AddOrgMember = AddOrgMember;

    return service;

    function AddOrgMember (userMember) {
        var deferred = $q.defer();
        $http.post("register/addmember",userMember)
        .success(function(data, status){
            console.log("add member org succeed");
            deferred.resolve(data);
        })
        .error(function(data, status){
            console.log("error");
            deferred.reject(data);
        });
        return deferred.promise;
    }

    function GetSession(){
        var deferred = $q.defer();
        $http.get('rest/getcurrentuser/ ')
        .success(function(data){
            deferred.resolve(data);
        })
        .error(function(data){
            console.log("error");
            deferred.resolve(data);
        });
        return deferred.promise;
    }

    function GetByUsername(username, callback) {
        $http.get('rest/user/' + username).success(function (data){
        	callback(data);
        });
    }
    
    function GetByOrgId(callback) {
    	$http.get('rest/userbyorg').success(function (data){
    		callback(data);
    	});
    }
    
    function GetById(id, callback) {
    	$http.get('rest/user/id/'+ id).success(function (data){
    		callback(data);
    	});
    }
    
    function UpdateUser(username, password, email, firstname, lastname, callback) {
    	$http.post('rest/user/update', {username: username, password: password, email: email, firstName: firstname, lastName: lastname}).success(function(response){
    		response.success = true;
    		callback(response);
    	}).error(function(response){
    		response.success = false;
    		response.message = "There was an error while updating your account, please try again";
    		callback(response);
    	});
    	
    }
    
    function ChangePassword(username, password, newPassword, callback) {
    	$http.post('rest/user/update', {username: username, password: password, newPassword: newPassword}).success(function(response){
    		response.success = true;
    		callback(response);
    	}).error(function(response){
    		response.success = false;
    		response.message = "There was an error while updating your account, please try again";
    		callback(response);
    	});
    }

    // private functions
    function handleSuccess(res) {
        return res.data;
    }

    function handleError(error) {
        return function () {
            return { success: false, message: error };
        };
    }
}

function FlashService($rootScope) {
    var service = {};

    service.Success = Success;
    service.Error = Error;

    initService();

    return service;

    function initService() {
        $rootScope.$on('$locationChangeStart', function () {
            clearFlashMessage();
        });

        function clearFlashMessage() {
            var flash = $rootScope.flash;
            if (flash) {
                if (!flash.keepAfterLocationChange) {
                    delete $rootScope.flash;
                } else {
                    // only keep for a single location change
                    flash.keepAfterLocationChange = false;
                }
            }
        }
    }

    function Success(message, keepAfterLocationChange) {
        $rootScope.flash = {
            message: message,
            type: 'success', 
            keepAfterLocationChange: keepAfterLocationChange
        };
    }

    function Error(message, keepAfterLocationChange) {
        $rootScope.flash = {
            message: message,
            type: 'error',
            keepAfterLocationChange: keepAfterLocationChange
        };
    }
}

function ProjectService($resource) {
    return $resource('rest/project/:project',{project: "@project"},
        {
            update: {
                method: 'PUT'
            }
        });
}

function ProjectDetailService($http) {
	var service = {};
	service.getProjectByName = getProjectByName;
	
	return service;
	
	function getProjectByName(name, callback) {
		$http.get('rest/proj/' + name).success(function(data){
			callback(data);
		});
	};
}

function IterationService($http) {
	var prjDetailCtrl = null;
	var service = {
			project: '',
			setPrjDetailCtrl: function(ctrl){
				prjDetailCtrl = ctrl;
			},
			getPrjDetailCtrl: function(){
				return prjDetailCtrl;
			},
			iterations: []
	};
    service.create = create;
    service.getIterationsByProject = getIterationsByProject;
    service.getLastIteration = getLastIteration;
    service.getIterationById = getIterationById;
    return service;
    
    function create(iteration, callback) {
    	$http.post('rest/iteration/', iteration).success(function(response){
    		callback(response);
    	});
    }
    
    function getIterationsByProject(project, callback) {
    	$http.get('rest/iteration/project/'+project).success(function(response){
    		callback(response);
    	});
    }
    
    function getLastIteration(project, callback) {
    	$http.get('rest/iteration/last/'+project).success(function(response){
    		callback(response);
    	});
    }
    
    function getIterationById(id, callback) {
    	$http.get('rest/iteration/'+id).success(function(response){
    		callback(response);
    	});
    }
}

function OrganizationMemberService($resource) {
    return $resource('rest/orgmembers/:orgmember',{orgmember: "@orgmember"});
}

function CardService($http, $q){
    var service = {};
    service.saveCard = saveCard;
    service.getById = getById;
    service.getTasksByCardId = getTasksByCardId;
    return service;


    function getTasksByCardId (id){
        var deferred = $q.defer();
        $http.get('rest/task/bycardid/'+id)
        .success(function(data){
            deferred.resolve(data);
        })
        .error(function(data){
            console.log("error");
            deferred.resolve(data);
        });
        return deferred.promise;
    }

    function getById(id){
        var deferred = $q.defer();
        $http.get('rest/card/'+id)
        .success(function(data){
            deferred.resolve(data);
        })
        .error(function(data){
            console.log("error");
            deferred.resolve(data);
        });
        return deferred.promise;
    }

    function saveCard(objCard){
        var deferred = $q.defer();

        if (objCard.id !== undefined){
            $http.put('rest/card/update', objCard)
            .success(function(response){
                response.success = true;
                response.message = "Card updated!";
                deferred.resolve(response);
            })
            .error(function(response){
                response.success = false;
                response.message = 'There was an error when saving Card, please try again!';
                deferred.resolve(response);
            });
        }else{
            $http.post('rest/card/create', objCard)
            .success(function(response){
                response.success = true;
                response.message = "Card created!";
                deferred.resolve(response);
            })
            .error(function(response){
                response.success = false;
                response.message = 'There was an error when saving Card, please try again!';
                deferred.resolve(response);
            });
        }

        return deferred.promise;
    }
}

var zserverPath="https://localhost:12001/";

function MenuService($http, $q){
	 var service = {};
	 service.testAja = testAja;
	 service.retrieve1stLevelMenu = retrieve1stLevelMenu;
	 
	 return service;
	 function testAja(id){
	        var deferred = $q.defer();
	        $http.get('https://localhost:12001/tesOneUser')
	        .success(function(data){
	            deferred.resolve(data);
	        })
	        .error(function(data){
	            console.log("error");
	            deferred.resolve(data);
	        });
	        return deferred.promise;
	    }
	 
	 function retrieve1stLevelMenu(){
		 var deferred = $q.defer();
		 $http.get(zserverPath+"firstLevelMenu")
		 .success(function(data){
			 deferred.resolve(data);
		 })
		 .error(function(){
			 console.log("error retrieve1stLevelMenu");
			 deferred.resolve(data);
		 });
		 return deferred.promise;
	 }
}

angular
    .module('inspinia')
    .factory('AuthenticationService', AuthenticationService)
    .factory('UserService', UserService)
    .factory('FlashService', FlashService)
    .factory('ProjectService', ProjectService)
    .factory('OrganizationMemberService', OrganizationMemberService)
    .factory('CardService', CardService)
    .factory('IterationService', IterationService)
    .factory('ProjectDetailService', ProjectDetailService)
    .factory('MenuService', MenuService);
