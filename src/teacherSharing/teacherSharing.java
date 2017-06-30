package teacherSharing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileUpload.CryptoException;
import fileUpload.encryption;

/**
 * Servlet implementation class teacherSharing
 */
@WebServlet("/teachersharing")
public class teacherSharing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherSharing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		File decryptedFile = new File("document.decrypted");
		File file = new File("C:/Users/Lim Xuan Zheng/Documents/GitHub/Bas/src/encrypted/document.encrypted.encrypted");
		String key = "Mary has one cat";
        try {
        	decryption.decrypt(key, file, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            System.out.println("fail");
        }
        File files = new File("C:/Users/Lim Xuan Zheng/Documents/GitHub/Bas/src/encrypted");
		File f1 = new File(files, "document.decrypted");
		f1.createNewFile();
        
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
