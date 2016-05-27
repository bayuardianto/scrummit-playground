/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
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
        .state('index.dashboard', {
            url: "/dashboard",
            templateUrl: "views/dashboard",
            data: { pageTitle: 'Dashboard' }
        })
        .state('index.projects', {
            url: "/projects",
            templateUrl: "views/projects",
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
        .state('verified', {
        	url: "/verified",
        	templateUrl: "views/verified",
        	data: { pageTitle: 'Verified', specialClass: 'gray-bg'}
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
