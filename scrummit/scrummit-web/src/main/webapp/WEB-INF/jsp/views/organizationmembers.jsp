<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Organization Members</h2>
        <ol class="breadcrumb">
            <li>
                <a ui-sref="index.dashboard">Home</a>
            </li>
			<li class="active">
                <strong>Organization Members</strong>
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
                    <button class="btn btn-primary" ui-sref="index.addmember">Add New Member</button>
                </div>
                <div class="ibox-content">
                    <input type="text" class="form-control input-sm m-b-xs" id="filter"
                           placeholder="Search in table">
					
                    <table class="footable table table-stripped" data-page-size="5" data-filter=#filter>
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email Address</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="orgMember in orgMembers">
                                <td>{{ orgMember.firstName }}</td>
                                <td>{{ orgMember.lastName }}</td>
                                <td>{{ orgMember.email }}</td>
                                <td></td>
                            </tr>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="5">
                                <ul class="pagination pull-right"></ul>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                	
                </div>
            </div>
        </div>
    </div>
</div>