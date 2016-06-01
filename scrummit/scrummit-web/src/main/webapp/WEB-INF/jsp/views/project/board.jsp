<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Project Board</h2>
        <ol class="breadcrumb">
            <li>
                <a ui-sref="index.dashboard">Home</a>

            </li>
            <li>
                <a ui-sref="index.projects">Project</a>
            </li>
            <li class="active">
                <strong>Board</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content  animated fadeInRight" ng-controller="CardController">

    <div class="row">
        <div class="col-lg-4">
            <div class="ibox">
                <div class="ibox-content">
                	<div uib-dropdown>
	                    <h3 class="pull-left">To-do</h3>
	                    <a uib-dropdown-toggle href class="pull-right">
	                    	<b class="caret">&nbsp;</b>
	                    </a>
	                    <ul uib-dropdown-menu class="animated fadeInDown m-t-xs pull-right">
	                        <li>
	                       		<a ng-click="openCreateCardModal('lg')">Add Card</a></li>
	                        
	                    </ul>
                    </div>
                    <span class="divider">&nbsp;</span>
					<hr/>
                    <ul ui-sortable="sortableOptions" class="sortable-list connectList agile-list" ng-model="todoList">
                        <li class="{{task.statusClass}}-element" ng-repeat="task in todoList">
                            {{task.content}}
                            <div class="agile-detail">
                                <a href="#" class="pull-right btn btn-xs btn-white">{{task.tagName}}</a>
                                <i class="fa fa-clock-o"></i> {{task.date}}
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="ibox">
                <div class="ibox-content">
                    <div uib-dropdown>
	                    <h3 class="pull-left">In Progress</h3>
	                    <a uib-dropdown-toggle href class="pull-right">
	                    	<b class="caret">&nbsp;</b>
	                    </a>
	                    <ul uib-dropdown-menu class="animated fadeInDown m-t-xs pull-right">
	                        <li>
	                       		<a ng-click="openCreateCardModal('lg')">Add Card</a></li>
	                        
	                    </ul>
                    </div>
                    <span class="clear">&nbsp;</span>

                    <hr/>
                    <ul ui-sortable="sortableOptions" class="sortable-list connectList agile-list" ng-model="inProgressList">
                        <li class="{{task.statusClass}}-element" ng-repeat="task in inProgressList">
                            {{task.content}}
                            <div class="agile-detail">
                                <a href="#" class="pull-right btn btn-xs btn-white">{{task.tagName}}</a>
                                <i class="fa fa-clock-o"></i> {{task.date}}
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="ibox">
                <div class="ibox-content">
                    <div uib-dropdown>
	                    <h3 class="pull-left">Completed</h3>
	                    <a uib-dropdown-toggle href class="pull-right">
	                    	<b class="caret">&nbsp;</b>
	                    </a>
	                    <ul uib-dropdown-menu class="animated fadeInDown m-t-xs pull-right">
	                        <li>
	                       		<a ng-click="openCreateCardModal('lg')">Add Card</a></li>
	                        
	                    </ul>
                    </div>
                    <span class="clear">&nbsp;</span>

                    <hr/>
                    <ul ui-sortable="sortableOptions" class="sortable-list connectList agile-list" ng-model="completedList">
                        <li class="{{task.statusClass}}-element" ng-repeat="task in completedList">
                            {{task.content}}
                            <div class="agile-detail">
                                <a href="#" class="pull-right btn btn-xs btn-white">{{task.tagName}}</a>
                                <i class="fa fa-clock-o"></i> {{task.date}}
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    

</div>