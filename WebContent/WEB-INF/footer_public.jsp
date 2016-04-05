<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<hr class="base">


			</div> <!--  end of div class content -->
			
			<div class="sidebar col-md-3 col-md-offset-1 col-xs-12">
				<%@ include file="/WEB-INF/sidebar_public.jsp" %>
			</div>
			
		</div> <!-- end of main div class row -->
		<div class="row">
			<div class="col-md-9 col-xs-12 ">
				<address> 
				  <strong>${preference.club_name_long }</strong> | 
				  ${preference.address }, 
				  ${preference.city }, ${preference.province } ${preference.postalcode } | 
				  <abbr title="Phone">P:</abbr> ${preference.formatted_telephone }
				</address>
			</div>
			<div class="col-md-3 col-xs-12 ">
				<img class="center-block" src="${pageContext.request.contextPath}/images/ch_logo.png">
			</div>
		</div>
	</div> <!--  end of main div class container -->

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/countryoptions.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/sorttable.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/invoice.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/colourscheme.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileinput.min.js" charset="utf-8"></script>
</body>
</html>  