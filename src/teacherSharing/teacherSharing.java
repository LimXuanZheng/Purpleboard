package teacherSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import database.model.User;
import database.model.UserAll;
import fileUpload.CryptoException;
import fileUpload.encryption;

/**
 * Servlet implementation class teacherSharing
 */
@WebServlet("/teachersharing")
public class teacherSharing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String filepathes;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public teacherSharing() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = 13;";
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



		ArrayList<String> names = new ArrayList<String>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<User> alluser = dbms.getDatabaseUser();
			for(User a: alluser){
				String j = a.getName();
				names.add(j);
			}

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(String s:names){
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


				/*

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
				+ 						"<li id='loginBtn'>"
				+ 							"<a href='#'>Welcome, Bob.</a>"
				+ 						"</li>"
				+ 					"</ul>"
				+ 				"</div>"
				+ 			"</nav>"
				+			"<p id='hidehere'>The class path is here</p>"
				+			"<div id='overlay'>"
				+				"<div id='content1'>"
				+					"<form id='anything' action='teacherupload' onsubmit='asubmit()'>"
				+						"<input type='radio'  id='checkifmy' name='chooseFile' onclick='javascript:showHidden();' checked>My Drive</input>"
				+						"<input type='radio'  id='checkifshare' name='chooseFile' onclick='javascript:showHidden();'>Shared Drive</input>"
				+						"<input type='radio'  id='checkifnew' name='chooseFile' onclick='javascript:showHidden();'>New Drive</input>"
				+						"<br><label class='custom-file'>"
				+							"<input type='file' id='datafilepath' class='custom-file-input' name='datafile' style='font-size:20px;'>"
				+							"<span class='custom-file-control'></span>"
				+						"</label>"
				+						"<br><p id='nameOfNewButton'>Name of Folder: <input type='text' name='FName' id='getBtnName'></input></p>"
				+						"<br><div class='dropdown' id='dropdowns'>"						
				+			    			"<button  type='button' onclick='showsomething()' class='dropbtn'>Teachers/Students</button>"
				+			    			 "<div id='myDropdown' class='dropdown-content'>"
				+			    			  	"<input type='text' placeholder='Search..' id='myInput' onkeyup='filterFunction()'>"
				+								"<div id='fixsize'>");
		for(String n:names){
				out.println(
								
								"<p class='niceparagraph' onclick ='displayhello()'> " + n + "</p>");
							
		}	    			    
							    			    
				
				out.println(
												"</div>"
				+			    			  "</div>"
				+			    			"</div>"
				+								"<div id='showtable' class='container'>"
				+									"<table class='table table-striped' id='smaller'>"
				+										"<thead>"
			    +										  "<tr>"
			    +										    "<th style='font-size:20px'>Name</th>"
			    +										  "<tr>"
			    +										"<thead>"
			    +										"<tbody>"
			    +										  "<tr>"
			    +										  	"<td>I am gay</td>"
			    +										  "</tr>"
			    +										"</tbody>"
				+									"</table>"
				+								"</div>"
				+						"<input type='submit' class='duo' id='submit' value='Submit'></input>"
				+					"</form>"
				//+					"<button id='confirm' onclick='confirm()' >Confirm</button>"
				+					"<button type='button' class='duo' id='cancel' onclick='cancel()'>Cancel</button>"
				//+					"</form>"
				+				"</div>"
				+			"</div>"
				+			"<div>"
				+				"<div class='contents' id='storeButton'>"
				+					"<button class='button' id='newbutton' onclick='createNew()'>New</button>"
				+					"<button class='button' id='mybutton'>My Drive</button>"//onclick='displayallmy()'
				+					"<button class='button' id='lastOne'>Shared Drive</button>"
				+				"</div>"
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

			out.println(
					"<tr>"
							+		"<td onclick='dosth(" + f.getFileID() + ")' name='whatisthisfile'>  " + f.getFileName() +  "</td>"
							//		+		"<td  onclick='downloadfile(this)'>" + f.getFileName() + "</td>"
							+		"<td>" + f.getUser().getName() + "</td>"
							+		"<td>" + f.getDate() + "</td>"
							+		"<td>" + f.getFileSize() + "</td>"
							+	"</tr>"
					);
		}
		out.println(					"</table>"
				+				"</div>"
				+			"</div>"
				+		"</div>"
				+	"<input type='hidden' name='index' id='pls'></input>"
				+	"</body>"
				+"</html>"
				);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = 13;";
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
		System.out.println(sss);
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
						decryption.decrypt(key1, tempFile, decryptedFile);
					} catch (CryptoException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
						System.out.println("fail");
					}
					response.setHeader("Content-Disposition", "attachment;filename=" + d.getFileName());
					byte[] bytesArray = new byte[(int) decryptedFile.length()];
					response.getOutputStream().write(bytesArray);
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
