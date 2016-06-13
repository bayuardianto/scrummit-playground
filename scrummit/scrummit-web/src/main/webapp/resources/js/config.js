/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, $httpProvider) {
    $urlRouterProvider.otherwise("/index/dashboard");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider

        .state('index', {
            abstract: true,
            url: "/index",
            templateUrl: "views/content",
        })
        .state('user', {
        	abstract: true,
        	url: "/user",
        	templateUrl: "views/content"
        })
        .state('project', {
        	abstract: true,
        	url: "/project",
        	templateUrl: "views/content"
        })
        .state('index.dashboard', {
            url: "/dashboard",
            templateUrl: "views/dashboard",
            data: { pageTitle: 'Dashboard' }
        })
        .state('index.projects', {
            controller: "ProjectController",
            url: "/projects",
            templateUrl: "views/projects",
            controllerAs: "vm",
            data: { pageTitle: 'Projects' }
        })
        .state('login', {
        	controller: "LoginController",
        	url: "/login",
        	templateUrl: "views/login",
        	controllerAs: "vm",
        	data: { pageTitle: 'Login', specialClass: 'gray-bg'}
        })
        .state('register', {
        	url: "/register",
        	templateUrl: "views/register",
        	data: { pageTitle: 'Register', specialClass: 'gray-bg'}
        })
        .state('user.profile', {
        	url: "/profile",
        	templateUrl: "user/profile"
        })
        .state('user.password', {
        	url: "/password",
        	templateUrl: "user/password"
        })
        .state('project.board', {
        	controller: "ProjectDetailController",
        	url: "/:name/board",
        	templateUrl: "views/project/board",
        	controllerAs: "pc",
        	resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
	                    {
	                        files: ['resources/js/plugins/moment/moment.min.js']
	                    },
                        {
                            name: 'ui.sortable',
                            files: ['resources/js/plugins/ui-sortable/sortable.js']
                        },
                        {
                            serie: true,
                            files: ['resources/js/plugins/daterangepicker/daterangepicker.js', 'resources/css/plugins/daterangepicker/daterangepicker-bs3.css']
                        },
                        {
                            name: 'datePicker',
                            files: ['resources/css/plugins/datapicker/angular-datapicker.css','resources/js/plugins/datapicker/angular-datepicker.js']
                        },
                        {
                            name: 'daterangepicker',
                            files: ['resources/js/plugins/daterangepicker/angular-daterangepicker.js']
                        }
                    ]);
                }
            }
        })
        .state('verified', {
        	url: "/verified",
        	templateUrl: "views/verified",
        	data: { pageTitle: 'Verified', specialClass: 'gray-bg'}
        })
        .state('index.createproject', {
            url: "/createproject",
            templateUrl: "views/addproject",
            controller: "ProjectController",
            data: { pageTitle: 'Add New Project' }
        })
        .state('index.updateproject', {
            url: "/updateproject/:id",
            controllerAs: 'pc',
            templateUrl: "views/addproject",
            controller: "ProjectController",
            data: { pageTitle: 'Update Project' }})
        .state('401', {
        	url: "/401",
        	templateUrl: "401",
        	data: { pageTitle: 'Unauthorized', specialClass: 'gray-bg' }
        });
    
    $httpProvider.interceptors.push(function($q, $location) {
    	  return {
    		  'responseError': function(rejection) {
    			  var defer = $q.defer();
    			  
                  if(rejection.status == 401){
                      //console.dir(rejection);
                	  
                      $location.path('/401');
                  }
                  defer.reject(rejection);
                  return defer.promise;
    		    }
    	  };
	});
}
angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state, $location, $cookies, $http) {
        $rootScope.$state = $state;
        
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register','/verified']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('login');
            }
        });
    });
