package teacherSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import database.DatabaseAccess;
import database.model.UserAll;
import fileUpload.CryptoException;

/**
 * Servlet implementation class teacherSharing2
 */
@WebServlet("/teacherSharing2")
public class teacherSharing2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(teacherSharing2.class.getName());
	private String username = "Bob";
	private String location = null;
	private String teemo = null;
	int teemo1 = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public teacherSharing2() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			location = (String)session.getAttribute("location");
			teemo = (String)session.getAttribute("userID");
			teemo1 = Integer.parseInt(teemo);
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}

		ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
		ThreadContext.put("Username", username);
		ThreadContext.put("Location", location);
		logger.debug("entered Teacher Sharing 2 page");
		ThreadContext.clearAll();

		PrintWriter out = response.getWriter();
		
		
		
		ArrayList<String> folder = new ArrayList<String>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<UserAll> fa = dbms.getDatabaseUserAll();
			for(UserAll qw: fa){
				if(qw.getUser().getUserID() == teemo1){
					folder = qw.getUser().getArrayFolder();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String myname = "";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<UserAll> fa = dbms.getDatabaseUserAll();
			for(UserAll qq:fa){
				if(qq.getUser().getUserID() == teemo1){
					myname = qq.getUser().getName();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	


		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = " + teemo1 + ";";
			ResultSet rs = dbms.getDatabaseData(query);
			while(rs.next()){
				key1 = rs.getString("Keys");
			}
			System.out.println(key1);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}



		ArrayList<String> names1 = new ArrayList<String>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<UserAll> alluser1 = dbms.getDatabaseUserAll();
			for(UserAll ea: alluser1){
				names1.add(ea.getUser().getName());
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(String s:names1){
			System.out.println(s);
		}




		String filename1 = "Nothing to show";

		int filesize1;
		Date date1;
		byte [] filedata;
		ArrayList<database.model.File> fileArray = new ArrayList<database.model.File>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			fileArray = dbms.getDatabaseFile();
			database.model.File file1 = fileArray.get(0);
			String name1 = file1.getUser().getName();
			System.out.println(name1);
			for(database.model.File s:fileArray){
				filename1 = s.getFileName();
				filesize1 = s.getFileSize();
				date1 = s.getDate();
				filedata = s.getFileData();
				System.out.println(filename1);
				System.out.println(filesize1);
				System.out.println(date1);


				/*
				File tempFile = File.createTempFile(filename1, ".tmp", null);
				FileOutputStream fos = new FileOutputStream(tempFile);
				fos.write(filedata);

				File decryptedFile = new File("document.decrypted");
				try {
					decryption.decrypt(key1, tempFile, decryptedFile);
				} catch (CryptoException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
					System.out.println("fail");
				}




				FileInputStream hh = new FileInputStream(decryptedFile);
				OutputStream h = response.getOutputStream();

				byte[] test = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = hh.read(test)) != -1) {
					h.write(test, 0, bytesRead);
				}



				 */


			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	



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
				+		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
				+		"<link rel='stylesheet' href='css/teacherSharing.css'>"
				+		"<title>TeacherSharing</title>"
				+		"<script src='script/teacher.js'></script>"
				+		"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
				+ 		"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>"
				+ 	"</head>"
				+ 	"<body id='body' onload='loadalltheshit()'>"
				+ 		"<div class='container-fluid' id='Container'>"
				+ 			"<!-- Navigation -->" 
				+ 			"<nav class='navbar navbar-default'>"
				+ 				"<div class='container-fluid'>"
				+ 					"<div class='navbar-header'>"
				+ 						"<a class='navbar-brand' href='Home'>Bas?</a>"
				+ 					"</div>"
				+ 					"<ul class='nav navbar-nav'>"
				+ 						"<li>"
				+ 							"<a href='Home'>Home</a>"
				+ 						"</li>"
				+ 						"<li class='active'>"
				+ 							"<a href='#'>Directory</a>"
				+ 						"</li>"
				+ 					"</ul>"
				+ 					"<ul class='nav navbar-nav navbar-right'>"
				+						"<li class='dropdown'>"
				+				   			"<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Welcome, " + username + ". </a>"
				+					       	"<ul class='dropdown-menu'>"
				+					       		"<li><a href='Settings'>Setting</a></li>"
				+					       		"<li><a href='Login'>Logout</a></li>"
				+							"</ul>"
				+						"</li>"
				+ 					"</ul>"
				+ 				"</div>"
				+ 			"</nav>"
				+			"<p id='hidehere'>The class path is here</p>"
				+			"<div id='overlay'>"
				+				"<div id='content1'>"
				+					"<div id='moveleft'>"
				+					"<form id='anything'  action='teacherupload' onsubmit='asubmit()'>"
				+						"<p style='font-size:50px;'>Select Your Upload Method</p>"
				+						"<input type='hidden' name='define1' id='d1'></input>"
				+						"<input type='hidden' name='stringofpeople' id='sp'></input>"
				+						"<input type='hidden' name='folder1' id='f1'></input>"
				+						"<input type='radio'  id='checkifmy' name='chooseFile' onclick='javascript:showHidden();' style='width:20px; height:20px;' checked></input><label style='font-size:30px;'>My Drive</label>"
				+						"<label style='margin-right:30px; margin-left:30px;'><input type='radio'  id='checkifshare' name='chooseFile' onclick='javascript:showHidden();' style='width:20px; height:20px;'></input><label style='font-size:30px;'>Shared Drive</label></label>"
				+						"<input type='radio'  id='checkifnew' name='chooseFile' onclick='javascript:showHidden();' style='width:20px; height:20px;'></input><label style='font-size:30px;'>Create new Assigment</label>"
				+						"<br><p onclick='enablebtn()'><label class='custom-file'>"
				+							"<input type='file' id='datafilepath' class='custom-file-input' name='datafile' style='font-size:20px;'>"
				+							"<span class='custom-file-control'></span>"
				+						"</label></p>"
				+						"<br><p id='nameOfNewButton'>Name of Folder: <input type='text' name='FName' id='getBtnName'></input></p>"
				+						"<br><div id='bigone'><div class='dropdown' id='dropdowns'>"						
				+			    			"<button  type='button' onclick='showsomething()' class='dropbtn'>Teachers/Students</button>"
				+			    			 "<div id='myDropdown' class='dropdown-content'>"
				+			    			  	"<input type='text' placeholder='Search..' id='myInput' onkeyup='filterFunction()'>"
				+								"<div id='fixsize'>");

		for(String n:names1){

			out.println(

					"<p class='niceparagraph' id='ppp'  onclick ='displayhello(this)'> " + n + "</p>");

		}	    			    


		out.println(
				"</div>"
						+			    			  "</div>"
						+			    			"</div>"
						+								"<div id='showtable' class='container11111'>"
						+									"<table id='smaller'>"
						+										  "<tr>"
						+										    "<th bgcolor='#0341FB' style='font-size:25px; width:100%;' align='center'>Name</th>"
						+											"<th bgcolor='#0341FB' style='font-size:25px; width:100%;'></th>"
						+										  "<tr>"
						+									"</table>"
						+								"</div>"
						+								"</div>"
						+						"<input type='submit' class='duo' id='submit' value='Submit' disabled='true'></input>"
						+						"<button type='button' class='duo' id='cancel' onclick='cancel1()'>Cancel</button>"
						+					"</form>"
						//+					"<button id='confirm' onclick='confirm()' >Confirm</button>"
						//+					"</form>"
						+				"</div>"
						+				"</div>"
						+			"</div>"
						+			"<div>"
						+				"<div class='contents' id='storeButton'>"
						+					"<button class='button' id='newbutton' onclick='createNew()'>New</button>"
						+					"<a href='teachersharing'><button class='button' id='mybutton'>My Drive</button></a>"//onclick='displayallmy()'
						+					"<button class='button' id='lastOne' onclick='refreshpage()'>Shared With Me</button>");



		for(String sss:folder){
			if(sss.equals("nothing")){
				System.out.println("nothing to print");
			}
			else{
			out.println("<a href='teacherSharing3'><button class='button'>"
					+	sss 
					+	"</button></a>");
			}
		}



		out.println(
				"</div>"
						+				"<div class='contents' id='storeTable'>"
						+					"<table id='displaying'>"
						+						"<tr>"
						+							"<th>Name</th>"
						+							"<th>Owner</th>"
						+							"<th>Last Modified</th>"
						+							"<th>File Size</th>"
						+						"</tr>"
				);
		out.println("<form id='downloadfile' action='teachersharing' method='post'>");

		for(database.model.File f: fileArray){
			for(String hai : f.convertRecipient()){
				if((hai.equals(teemo) || hai.equals(myname)) && f.getShareType() == 1){
					out.println(
							"<tr>"
									+		"<td onclick='dosth(" + f.getFileID() + ")' name='whatisthisfile'>  " + f.getFileName() +  "</td>"
									//		+		"<td  onclick='downloadfile(this)'>" + f.getFileName() + "</td>"
									+		"<td>" + f.getUser().getName() + "</td>"
									+		"<td>" + f.getDate() + "</td>"
									+		"<td>" + f.getFileSize() + " KB</td>"
									+	"</tr>"
							);
				}
			}
		}
		out.println(					"</table>"
				+				"</div>"
				+			"</div>"
				+		"</div>"
				+	"<input type='hidden' name='index' id='pls'></input>"
				+	"</form>"
				);

		out.println(	
				"</body>"
						+"</html>"
				);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("fuckyourmother");
		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = " + teemo1 + ";";
			ResultSet rs = dbms.getDatabaseData(query);
			while(rs.next()){
				key1 = rs.getString("Keys");
			}
			System.out.println(key1);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//PrintWriter out = response.getWriter();
		int sss = Integer.parseInt(request.getParameter("index"));
		//System.out.println(sss);
		System.out.println("hello111");
		
		
		

		ArrayList<database.model.File> fa = new ArrayList<database.model.File>();
		String hj = "blahblah";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			fa = dbms.getDatabaseFile();
			for(database.model.File c:fa){
				if(c.getFileID() == sss){
					hj = c.getUser().getKeys();
					System.out.println(hj);
				}
			}
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		System.out.println("hello");
		







		try{
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<database.model.File> fileArray = dbms.getDatabaseFile();
			for(database.model.File d: fileArray){
				if(d.getFileID() == sss){

					File tempFile = File.createTempFile(d.getFileName(), ".tmp", null);
					FileOutputStream fos = new FileOutputStream(tempFile);
					fos.write(d.getFileData());
					File decryptedFile = new File("document.decrypted");
					try {
						decryption.decrypt(hj, tempFile, decryptedFile);
					} catch (CryptoException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
						System.out.println("fail");
					}
					/*FileInputStream hh = new FileInputStream(decryptedFile);
			        OutputStream h = response.getOutputStream();

			        byte[] test = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = hh.read(test)) != -1) {
			                           h.write(test, 0, bytesRead);
			                       }
					 */
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();  
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", "attachment;filename=\"" + d.getFileName() +"\"");
					FileInputStream stream = new FileInputStream(decryptedFile);
					int i;   
					while ((i=stream.read()) != -1) {  
						out.write(i);   
					}   
					stream.close();   
					out.close();

					ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
					ThreadContext.put("Username", username);
					ThreadContext.put("Location", location);
					logger.debug("downloaded a file");
					ThreadContext.clearAll();

					//byte[] bytesArray = new byte[(int) decryptedFile.length()];
					//response.getOutputStream().write(bytesArray);
				}
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}