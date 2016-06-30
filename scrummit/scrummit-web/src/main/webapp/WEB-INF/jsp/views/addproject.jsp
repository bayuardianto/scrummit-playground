<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>{{title}}</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>View Project</a>
            </li>
            <li class="active">
                <strong>{{topmenu}}</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <%--<ng-form name="fProject">--%>
    <%--<input type="hidden" name="value" ng-model="id" value="{{id}}">--%>
    <form class="m-t" role="form" ng-submit="saveProject()">
        <div class="form-group">
            <label for="projectName">Project Name</label>
            <input type="text" class="form-control" id="projectName" required="" ng-model="project.name" aria-required="true">
        </div>
        <div></div>
        <div class="form-group">
            <label for="projectDesc">Project Description</label>
            <input type="textarea" class="form-control" id="projectDesc" required="" ng-model="project.description" aria-required="true">
        </div>

        <div class="hr-line-dashed"></div>
        <ng-form name="fMember">
            <label>Project Members</label>
            <div></div>
            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="userSelector">Member's Name</label>
                            <div></div>
                            <select chosen id="userSelector" class="chosen-select" style="width:350px;" tabindex="4" required
                                    ng-model="selectedUser" ng-options="orgmember.userId as orgmember.fullName for orgmember in orgmembers">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="roleSelector">Role</label>
                            <div></div>
                            <select chosen id="roleSelector" class="chosen-select" style="width:350px;" tabindex="4" required
                                    ng-model="selectedRole" ng-options="role.id as role.text for role in roles">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <%--<button class="btn btn-primary" ng-click="storeTemp()">Add Member</button>--%>
                    <label for="btnAddMember"> </label>
                    <div></div>
                    <input type="button" class="btn btn-primary block m-b"
                           value="Add Member" ng-click="storeTemp()" id="btnAddMember" style="height:80%;"/>
                </div>
            </div>
            <div></div>
            <table class="footable table table-stripped" data-page-size="5">
                <thead>
                <tr>
                    <th>Member Name</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="member in project.members">
                    <td><a ng-click="removeMember(member, project.members)"><i class="fa fa-trash" aria-hidden="true"></i></a>
                        {{ member.userName }}</td>
                    <td>{{ member.roleName }}</td>
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
            <%--<table datatable="ng" dt-options="dtOptions"  class="table table-striped table-bordered table-hover dataTables-example">
                <thead>
                <tr>
                    <th>Member Name</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in project.members">
                    <td>{{ x.userName }}</td>
                    <td>{{ x.roleName }}</td>
                </tr>
                </tbody>
            </table>--%>
        </ng-form>
        <div class="row">
            <div class="col-xs-12 col-md-6">
                <div class="form-group">
                    <a ui-sref="index.projects">
                        <button class="btn btn-default">Cancel</button>
                    </a>
                </div>
            </div>
            <div class="col-xs-12 col-md-6">
                <div class="form-group">
                    <button class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
        <div
                ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }"
                ng-if="flash">
            <p ng-bind="flash.message"></p>
        </div>

        <%--</ng-form>--%>
    </form>

</div>
