<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Menu Management</h2>
        <ol class="breadcrumb">
            <li>
                <a ui-sref="index.dashboard">Home</a>
            </li>
			<li class="active">
                <strong>Menu Management</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
            	<div>
                    <button class="btn btn-primary" ui-sref="index.addmember">Add New Menu</button>
                </div>
                <div class="tableContainer" ng-controller="TreeTableController">
    				<script type="text/ng-template" id="tree_node">
        				<tr tt-node is-branch="node.children.length > 0">
            				<td>
								<span ng-if="node.children.length > 0" class="folder" ng-bind="node.menuname"></span>
								<span ng-if="!node.children || node.children.length < 1" class="file" ng-bind="node.menuname"></span>
							</td>
            				<td ng-bind="node.id"></td>
            				<td ng-bind="node.parentId"></td>
        				</tr>
    				</script>
				    <table tt-table tt-params="expanded_params">
				        <thead>
				        <tr>
				            <th>Menu Name</th>
				            <th>menu id</th>
				            <th>parent</th>
				        </tr>
				        </thead>
				        <tbody></tbody>
				    </table>
				</div>

                <!-- <div class="ibox-content">
                    <input type="text" class="form-control input-sm m-b-xs" id="filter"
                           placeholder="Search in table">
                	
                </div> -->
            </div>
        </div>
    </div>
</div>



