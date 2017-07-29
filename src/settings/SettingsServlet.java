package settings;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = "{Placeholder}";

    public SettingsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ 	"<head>"
				+ 		"<meta charset='UTF-8'>"
				+ 		"<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
				+ 		"<!-- Font Mono-Sans -->"
				+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
				+ 		"<!-- Latest compiled and minified CSS -->"
				+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
				+ 		"<!-- Optional theme -->"
				+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
				+ 		"<link rel='stylesheet' href='../css/Settings.css'>"
				+ 		"<script src='../script/Settings.js'></script>"
				+ 		"<title>Settings</title>"
				+ 	"</head>"
				+ 	"<body>"
				+ 		"<div id='theDiv'>"
				+ 			"<div class='container-fluid' id='Top-Container-Background'>"
				+ 				"<div class='container-fluid' id='Top-Container'>"
				+ 					"<!-- Navigation -->"
				+ 					"<nav class='navbar navbar-default'>"
				+ 						"<div class='container-fluid'>"
				+ 							"<div class='navbar-header'>"
				+ 								"<a class='navbar-brand' href='#'>Bas?</a>"
				+ 							"</div>"
				+ 							"<div class='collapse navbar-collapse'>"
				+ 								"<ul class='nav navbar-nav'>"
				+ 									"<li class='active'><a href='#'>Home</a></li>"
				+ 									"<li><a href='Directory'>Directory</a></li>"
				+ 								"</ul>"
				+ 								"<ul class='nav navbar-nav navbar-right'>"
				+ 									"<li class='dropdown'>"
				+ 										"<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Welcome, Bob. </a>"
				+ 										"<ul class='dropdown-menu'>"
				+ 											"<li><a href='#'>Setting</a></li>"
				+ 											"<li><a href='html/Login.html'>Logout</a></li>"
				+ 										"</ul>"
				+ 									"</li>"
				+ 								"</ul>"
				+ 							"</div>"
				+ 						"</div>"
				+ 					"</nav>"
				+ 				"</div>"
				+ 			"</div>"
				+ 			"<div id='contentDiv'>"
				+ 				"<div id='headerDiv'>"
				+ 					"<h2>Settings</h2>"
				+ 				"</div>"
				+ 				"<div id='aDiv'>"
				+ 					"<form id='setForm'>"
				+ 						"<div id='showName'>"
				+ 							"<div class='categoryDiv'>"
				+ 								"<p>Username</p>"
				+ 							"</div>"
				+ 							"<div class='flexDiv'>"
				+ 								"<p>" + username + "</p>"
				+ 							"</div>"
				+ 						"</div>"
				+ 						"<div id='showEmail'>"
				+ 							"<div class='categoryDiv'>"
				+ 								"<p>Email</p>"
				+ 							"</div>"
				+ 							"<div class='flexDiv'>"
				+ 								"<p id='showPEmail'>{Placeholder}</p>"
				+ 								"<span id='editEmailLink' onclick='showChangeEmailDiv()'>Edit</span>"
				+ 							"</div>"
				+ 							"<div id='changeEmailDiv'>"
				+ 								"<input type='email' id='inputEmail' name='inputEmail' required>"
				+ 								"<div id='inputError'>Please enter your email.</div>"
				+ 								"<div id='emailBtnDiv'>"
				+ 									"<button id='emailBtn'>Update email</button>"
				+ 								"</div>"
				+ 							"</div>"
				+ 						"</div>"
				+ 						"<div id='editPass'>"
				+ 							"<div class='categoryDiv'>"
				+ 								"<p>Password</p>"
				+ 							"</div>"
				+ 							"<div class='flexDiv'>"
				+ 								"<div id='editPassLinkDiv'>"
				+ 									"<span id='editPassLink' onclick='showChangePassDiv()'>Click to change password.</span>"
				+ 								"</div>"
				+ 							"</div>"
				+ 							"<div id='changePassDiv'>"
				+ 								"<div id='oldPassDiv' class='flexDiv'>"
				+ 									"<label class='passInputLabel'>Old password: </label>"
				+ 									"<input type='password' id='oldPass' class='passInput' name='oldPass' required>"
				+ 								"</div>"
				+ 								"<div id='inputPError1'>Please enter your email.</div>"
				+ 								"<div id='newPassDiv' class='flexDiv'>"
				+ 									"<label class='passInputLabel'>New password: </label>"
				+ 									"<input type='password' id='newPass' class='passInput' name='newPass' required>"
				+ 								"</div>"
				+ 								"<div id='inputPError2'>Please enter your email.</div>"
				+ 								"<div id='confirmNewPassDiv' class='flexDiv'>"
				+ 									"<label class='passInputLabel'>Confirm password: </label>"
				+ 									"<input type='password' id='confirmNewPass' class='passInput' name='confirmNewPass' required>"
				+ 								"</div>"
				+ 								"<div id='inputPError3'>Please enter your email.</div>"
				+ 								"<div id='passBtnDiv'>"
				+ 									"<button id='passBtn'>Update password</button>"
				+ 								"</div>"
				+ 							"</div>"
				+ 						"</div>"
				+ 					"</form>"
				+ 				"</div>"
				+ 			"</div>"
				+ 		"</div>"
				+ 	"</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}