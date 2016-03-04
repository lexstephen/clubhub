
	<div class="col-sm-12">
	<form action="/clubhub/UserController" method="post" class="form" role="form">
		  <div class="form-group">
		  	<h3>Member Login</h3>
		    <label for="inptUsername" class="hidden">Username</label>
		    <input type="text" class="form-control" name="username" id="inptUsername" value="${username}">
		  </div>
		  <div class="form-group">
		    <label for="inptPassword" class="hidden">Password</label>
		    <input type="password" class="form-control" name="password" id="inptPassword" value="${password}">
		  </div>
		  <div class="checkbox">
		    <label>
		      <input type="checkbox"> Remember Me?
		    </label>
		  </div>
		  <input type="hidden" name="option" value="login">
		  <input type="submit" class="btn btn-default" value="Login">
		</form>
	</div>