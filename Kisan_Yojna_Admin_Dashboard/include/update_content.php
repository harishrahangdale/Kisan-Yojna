<!-- Exportable Table -->
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                    All Records
                </h2>

            </div>
            <div class="body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover dataTable js-exportable">
                        <thead>
                            <tr>

                                <th>Tittle</th>
                                <th>State</th>
                                <th>Menu</th>
                                <th>Description</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                              <th>Tittle</th>
                              <th>State</th>
                              <th>Menu</th>
                              <th>Description</th>
                              <th>Update</th>
                              <th>Delete</th>
                            </tr>
                        </tfoot>
                        <tbody>
                          <?php while($row = mysqli_fetch_array($query)){ ?>
                            <tr>
                                <td><?php echo $row['title']; ?></td>
                                <td><?php echo $row['state']; ?></td>
                                <td><?php echo $row['menu']; ?></td>
                                <td><?php echo $row['des']; ?></td>
                                <td><a class="btn purple" href="index.php?path=update&a=update&id=<?php echo $row['id']; ?>" onclick="return confirm('Are you sure to modify?')" >Modify</a></td>
                                <td><a class="btn purple" href="index.php?path=update&a=del&id=<?php echo $row['id']; ?>" onclick="return confirm('Are you sure to delete?')" >Delete</a></td>
                            </tr>
                          <?php }  ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- #END# Exportable Table -->
