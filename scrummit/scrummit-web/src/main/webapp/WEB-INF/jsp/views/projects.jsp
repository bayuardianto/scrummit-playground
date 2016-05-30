<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Project View</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
<%--            <li>
                <a>Tables</a>
            </li>
            <li class="active">
                <strong>Data Tables</strong>
            </li>--%>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <%--<div class="ibox-title">
                    <h5>Table with data from $scope - <strong>(The angular way)</strong></h5>

                    <div ibox-tools></div>
                </div>--%>
                <div class="ibox-content">
                    <label>Search: <input ng-model="searchText"></label>
                    <table datatable="ng" dt-options="dtOptions"  class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th>Project Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Created By</th>
                            <th>Created Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="project in projects | filter:searchText ">
                            <td>{{ project.name }}</td>
                            <td>{{ project.description }}</td>
                            <td>{{ project.status }}</td>
                            <td>{{ project.createdByName }}</td>
                            <td>{{ project.createdDate }}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>