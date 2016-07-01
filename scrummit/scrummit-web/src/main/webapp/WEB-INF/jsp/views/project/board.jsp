<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-12">
        <h2>Project Board</h2>
        <ol class="breadcrumb">
            <li>
                <a ui-sref="index.dashboard">Home</a>

            </li>
            <li>
                <a ui-sref="index.projects">Projects</a>
            </li>
            <li class="active">
                <strong>Board</strong>
            </li>
        </ol>
        
    </div>
    
</div>
<span class="clear"></span>

<div class="wrapper wrapper-content  animated fadeInRight" ng-controller="CardController">
	<div class="row">
		<div>
			<div class="col-md-2">
	            	<button type="button" style="height:30px" ng-click="$root.rightSidebar = !$root.rightSidebar" class="btn-primary block full-width m-b"><i class="fa fa-calendar">
	            	</i> {{iterationName}}</button>
			</div>
		</div>
	</div>
	<span class="clear"></span>
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
	                       		<a ng-click="openCreateCardModal('lg',task.id)">Add Card</a></li>
	                        
	                    </ul>
                    </div>
                    <span class="divider">&nbsp;</span>
					<hr/>
                    <ul ui-sortable="sortableOptions" class="sortable-list connectList agile-list todo-list" ng-model="todoList">
                        <li class="warning-element" ng-repeat="task in todoList" id="{{task.id}}">
                            <b>{{task.title}}</b><span ng-click="openCreateCardModal('lg',task.id)" class="fa fa-pencil-square-o pull-right" title="Edit Card"></span>
                            <div class="agile-detail">
                            	{{task.description}}
                            	<span class="clear"></span>
                                <i class="fa fa-clock-o"></i> {{task.createdDate | date:'yyyy-MM-dd'}}
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
                    <ul ui-sortable="sortableOptions" class="sortable-list connectList agile-list in-progress-list" ng-model="inProgressList">
                        <li class="danger-element" ng-repeat="task in inProgressList" id="{{task.id}}" >
                            <b>{{task.title}}</b><span ng-click="openCreateCardModal('lg',task.id)" class="fa fa-pencil-square-o pull-right" title="Edit Card"></span>
                            <div class="agile-detail">
                            	{{task.description}}
                            	<span class="clear"></span>
                                <i class="fa fa-clock-o"></i> {{task.createdDate | date:'yyyy-MM-dd'}}
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
                    <ul ui-sortable="sortableOptions" class="sortable-list connectList agile-list completed-list" ng-model="completedList">
                        <li class="success-element" ng-repeat="task in completedList" id="{{task.id}}">
                            <b>{{task.title}}</b><span ng-click="openCreateCardModal('lg',task.id)" class="fa fa-pencil-square-o pull-right" title="Edit Card"></span>
                            <div class="agile-detail">
                            	{{task.description}}
                            	<span class="clear"></span>
                                <i class="fa fa-clock-o"></i> {{task.createdDate | date:'yyyy-MM-dd'}}
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            
        </div>
    </div>
    
</div>
<div ng-include="'views/project/iteration/bar'"></div>