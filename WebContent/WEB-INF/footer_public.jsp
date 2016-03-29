

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<hr class="base">

<address>
				  <strong>${preference.club_name_long }</strong><br>
				  West of the Misty Mountains<br>
				  Rivendell, CA 94103<br>
				  <abbr title="Phone">P:</abbr> (123) 456-7890
				</address>
			</div> <!--  end of div class content -->
			
			<div class="sidebar col-sm-4 col-xs-12">
				<%@ include file="/WEB-INF/sidebar_public.jsp" %>
				<img class="centerImage" src="/clubhub/images/ch_logo.png">
			</div>
			
		</div> <!-- end of main div class row -->
	</div> <!--  end of main div class container -->
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/countryoptions.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/sorttable.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/invoice.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/colourscheme.js" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/fileinput.min.js"></script>
</body>
</html>  