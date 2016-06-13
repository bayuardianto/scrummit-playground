<div id="right-sidebar" ng-show="rightSidebar" class="sidebar-open" ng-controller="IterationController as ic">
    <div class="sidebar-container" full-scroll>
            <div>
                <div class="sidebar-title">
                    <h4> <i class="fa fa-archive"></i> Project History</h4>
                </div>
                <ul class="sidebar-list">
                    <li ng-repeat="iteration in iterations">
                        <a ng-click="loadBoard(iteration.id)">
                            {{iteration.name}}
                        </a>
                    </li>
				</ul>

            </div>
            <div>
            	 <button type="button" class="btn btn-primary block full-width m-b" ng-click="openCreateIterationModal('lg')"><i class="fa fa-plus"></i> Add Iteration</button>
            </div>

    </div>
</div>