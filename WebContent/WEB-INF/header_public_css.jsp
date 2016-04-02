<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="utilities.*"%>
<%	
	ColourSchemeDao colscheme = new ColourSchemeDao();
	colscheme.showColourScheme(request);
%>
<style type="text/css">

/*
@dark-base: #2A5B84;
@med-base: #EBC137;
@bg-base: #fe0de0;
@text-base: #BD8025;
*/

/* **************** dark colour ******************* */
hr {
  border-top: 1px ${colour_scheme.med_colour } solid;}
.frontend h1,
.frontend h2,
.frontend h3,
.frontend h4,
.frontend h5,
.frontend h6 {
  color: ${colour_scheme.med_colour };
}

.btn-primary {
	background-color: ${colour_scheme.med_colour };
}

.frontend .base {
  border-top: 1px solid ${colour_scheme.dark_colour };
}

.frontend h1,
.frontend h2 {
  border-bottom: 1px ${colour_scheme.dark_colour } solid;
}

.frontend .navbar {
  background-color: ${colour_scheme.dark_colour };
}

/* **************** med & text colour ******************* */
.frontend .btn-primary {
  background-color: ${colour_scheme.med_colour };
  color: ${colour_scheme.light_colour };
  border-color: ${colour_scheme.med_colour };
}

.frontend .postMeta {
  color: ${colour_scheme.med_colour };
}

.frontend .navbar-nav > li > a:hover,
.frontend .navbar-nav > li > a:focus {
  color: ${colour_scheme.light_colour };
  background-color: ${colour_scheme.med_colour };
}

body.frontend {
  background-color: ${colour_scheme.light_colour };
  color: ${colour_scheme.text_colour };
}

.frontend .btn-primary {
  background-color: ${colour_scheme.med_colour };
  color: ${colour_scheme.light_colour };
  border-color: ${colour_scheme.med_colour };
}

.frontend .navbar-nav > li > a {
  color: ${colour_scheme.light_colour };
}
.frontend .navbar-nav > li > a:hover,
.frontend .navbar-nav > li > a:focus {
  color: ${colour_scheme.light_colour };
  background-color: ${colour_scheme.med_colour };
}
.glyphicon {
  color: ${colour_scheme.dark_colour };
}
</style>