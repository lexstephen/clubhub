
	<div class="col-sm-12"  style="padding-bottom:50px;">
	<form action="${pageContext.request.contextPath}/UserController" method="post" class="form" role="form">
		  <div class="form-group">
		  	<h3>Member Login</h3>
		    <label for="inptUsername" class="hidden">Username</label>
		    <input type="text" class="form-control" name="username" id="inptUsername" value="${username}" placeholder="username" onfocus="this.placeholder = ''" onblur="this.placeholder = 'username'">
		  </div>
		  <div class="form-group">
		    <label for="inptPassword" class="hidden">Password</label>
		    <input type="password" class="form-control" name="password" id="inptPassword" value="${password}" placeholder="password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'password'">
		  </div>
		  <div class="checkbox">
		    <label>
		      <input type="checkbox" name="setCookie" checked> Remember Me?
		    </label>
		  </div>
		  <input type="hidden" name="option" value="login">
		  <input type="submit" class="btn btn-default" value="Login">
		</form>
	</div>
	