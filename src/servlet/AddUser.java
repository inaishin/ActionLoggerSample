package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfo;

//ユーザー追加機能
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存
@WebServlet("/adduser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddUser() {
        super();

    }

    //GETリクエスト(新規登録リンクをクリック)時は登録ページ表示
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //GETリクエスト
	    RequestDispatcher dispatcher =
		        req.getRequestDispatcher
		            ("/WEB-INF/jsp/addUserPage.jsp"); //フォワード：情報渡し
		    dispatcher.forward(req, resp);
	}

	//POSTリクエスト時はフォームの入力値をUserクラスに渡す,登録確認ページを表示
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		UserInfo user = new UserInfo(); //UserInfoクラスのインスタンス化
		user.setUserId( req.getParameter("userid") ); //Userクラスset~メソッドにリクエストされたパラメータ[userid]を渡す
		user.setName( req.getParameter("name") );
		user.setAddress( req.getParameter("address") );
		user.setTel( req.getParameter("tel") );
		user.setEmail( req.getParameter("email") );
		
		try {
			//パスワードのハッシュ化、51～55行目
			String rawPassword = req.getParameter("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			String passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));
			
			user.setPwdHash(passwordHash);
			
			HttpSession session = req.getSession(); //セッションスコープの生成
			session.setAttribute("userToAdd", user); //セッションスコープに"useToAdd"という属性名でuesrインスタンス保存 
		    
			RequestDispatcher dispatcher =
			        req.getRequestDispatcher
			            ("/WEB-INF/jsp/addUserInfoPage.jsp"); //フォワード転送先指定
			    dispatcher.forward(req, resp);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}