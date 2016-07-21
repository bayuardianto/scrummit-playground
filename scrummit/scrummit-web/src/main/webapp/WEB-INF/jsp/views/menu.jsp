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
            <th>ID</th>
            <th>Parent</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>


</div>