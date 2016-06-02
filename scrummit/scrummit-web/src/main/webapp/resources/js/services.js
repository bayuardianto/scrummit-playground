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

function UserService($http) {
	var service = {};
	 
    service.GetByUsername = GetByUsername;
    service.UpdateUser = UpdateUser;
    service.ChangePassword = ChangePassword;

    return service;

    function GetByUsername(username, callback) {
        $http.get('rest/user/' + username).success(function (data){
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
    return $resource('rest/project/:project',{project: "@project"});
}

function OrganizationMemberService($resource) {
    return $resource('rest/orgmembers/:orgmember',{orgmember: "@orgmember"});
}

angular
    .module('inspinia')
    .factory('AuthenticationService', AuthenticationService)
    .factory('UserService', UserService)
    .factory('FlashService', FlashService)
    .factory('ProjectService', ProjectService)
    .factory('OrganizationMemberService', OrganizationMemberService);