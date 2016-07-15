<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>{{title}}</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>Project</a>
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
    <form class="m-t" role="form" ng-submit="saveProject()">
        <div class="form-group">
            <label for="projectName">Project Name</label>
            <input type="text" class="form-control" id="projectName" required="" ng-model="project.name" aria-required="true">
        </div>
        <div></div>
        <div class="form-group">
            <label for="projectDesc">Project Description</label>
            <textarea class="form-control" id="projectDesc" required="" ng-model="project.description" aria-required="true">
            </textarea>
        </div>
        <ng-form name="fMember">
            <label>Project Members</label>
            <div class="row">
                <div class="col-xs-4">
                    <div class="form-group">
                        <label for="userSelector">Member's Name</label>
                        <select chosen id="userSelector" class="chosen-select form-control" style="width:350px;" tabindex="4" required
                                ng-model="selectedUser" ng-options="orgmember.userId as orgmember.fullName for orgmember in orgmembers">
                        </select>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group">
                        <label for="roleSelector">Role</label>
                        <select chosen id="roleSelector" class="chosen-select form-control" style="width:350px;" tabindex="4" required
                                ng-model="selectedRole" ng-options="role.id as role.text for role in roles">
                        </select>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group">
                        <span class="input-group-btn" style="padding-top: 25px;">
                            <input type="button" class="btn btn-primary block m-b btn-sm" value="Add Member" ng-click="storeTemp()" />
                        </span>
                    </div>
                </div>
            </div>

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
                <tfoot></tfoot>
            </table>

        </ng-form>
        <div>
            <div class="form-group col-xs-6">

            </div>
            <div class="form-group col-xs-6">
                <a ui-sref="index.projects">
                    <button class="btn btn-default">Cancel</button>
                </a>
                <button class="btn btn-primary">Save</button>
            </div>
        </div>
        <div
                ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }"
                ng-if="flash">
            <p ng-bind="flash.message"></p>
        </div>
    </form>

</div>
