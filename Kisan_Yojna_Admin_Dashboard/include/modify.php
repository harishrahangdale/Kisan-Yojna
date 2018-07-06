<form action="abcd.php?id=<?php echo $_GET['id']; ?>" id="frmFileUpload" class="dropzone" method="post" enctype="multipart/form-data">
<!-- <form action="abc.php" method="post"> -->
<div class="container-fluid">
    <div class="block-header">
        <h2>Modify Content</h2>
    </div>
    <!-- Adding LANGUAGE select -->
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        SELECT LANGUAGE
                    </h2>

                </div>
                <div class="body">
                    <div class="row clearfix">
                        <div class="col-md-3">
                            <p>
                                <b>Choose Here</b>
                            </p>
                            <select name="lan" class="form-control show-tick">
                                <option value="<?php echo $row['state']; ?>"><?php echo $row['state']; ?></option>
                                <option value="Marathi">Marathi</option>
                                <option value="Hindi">Hindi</option>
                                <option value="Gujrati">Gujrati</option>
                            </select>

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- Ending LANGUAGE select -->
    <!-- Adding Menu select -->
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        SELECT MENU
                    </h2>

                </div>
                <div class="body">
                    <div class="row clearfix">
                        <div class="col-md-3">
                            <p>
                                <b>Choose Here</b>
                            </p>
                            <select class="form-control show-tick" name="menu">
                                <option value="<?php echo $row['menu']; ?>"><?php echo $row['menu']; ?></option>
                                <option value="home">home</option>
                                <option value="government_schemes">government_schemes</option>
                                <option value="volunteer_organization_schemes">volunteer_organization_schemes</option>
                            </select>

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- Ending menu select -->
    <!-- start -->
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        Tittle , Description
                    </h2>

                </div>
                <div class="body">

                        <label for="email_address">Tittle</label>
                        <div class="form-group">
                          <div class="form-line">
                              <input type="text" name="title" id="email_address_2" class="form-control" placeholder="Enter Tittle" value="<?php echo $row['title']; ?>">
                          </div>
                        </div>
                        <label for="password">Description</label>
                        <div class="form-group">
                          <div class="form-line">
                              <input type="text" name="des" id="email_address_2" class="form-control" placeholder="Enter Description" value="<?php echo $row['des']; ?>" >
                          </div>
                        </div>


                </div>
            </div>
        </div>
    </div>
    <!-- End   -->
    <!-- CKEditor -->
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        Main Contents
                    </h2>

                </div>
                <div class="body">
                    <!-- <textarea name="content" id="ckeditor">
                        Hello
                    </textarea> -->
                    <!-- <form action="abc.php" method="post"> -->
                      <textarea name="abc" id="ckeditor">
                          <?php echo $row['webinfo']; ?>
                      </textarea>
                      <!-- <input type="submit" name="submit"> -->
                    <!-- </form> -->
                </div>
            </div>
        </div>
    </div>
    <!-- #END# CKEditor -->
    <!--  -->

    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        Upload Banner Image
                    </h2>

                </div>
                <div class="body">

                      <!-- <div class="dz-message">
                          <div class="drag-icon-cph">
                              <i class="material-icons">touch_app</i>
                          </div>
                          <h3>Drop files here or click to upload.</h3>
                          <em>(This is just a demo dropzone. Selected files are <strong>not</strong> actually uploaded.)</em>
                      </div> -->
                      <!-- <div class="fallback">
                          <input type="file" name="fileToUpload" />
                      </div> -->
                      <!-- <form action="upload.php" method="post" enctype="multipart/form-data"> -->

                          <input type="file" name="fileToUpload" id="fileToUpload">

                      <!-- </form> -->

                </div>
            </div>
        </div>
    </div>
    <!--  -->
    <!--  -->
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        Submit Here
                    </h2>

                </div>
                <div class="body">
                  <button type="submit" name="submit" class="btn btn-primary m-t-15 waves-effect">Click Me i Will Update</button>
                </div>
            </div>
        </div>
    </div>
    <!--  -->
</div>
</form>
