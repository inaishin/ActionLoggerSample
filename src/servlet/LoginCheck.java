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

import dao.UserDAO;
import model.UserInfo;

//ログイン時の処理

@WebServlet("/logincheck")
public class LoginCheck extends HttpServlet {

	//Getメソッドでこのページが呼ばれることはない。不正処理の疑いもあるが、とりあえずログインフォームにリダイレクト
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    RequestDispatcher dispatcher =
		        req.getRequestDispatcher
		            ("/WEB-INF/jsp/LoginPage.jsp");
		    dispatcher.forward(req, resp);
	}

	//ログインフォームからのPOSTリクエスト時
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		req.setCharacterEncoding("UTF-8");
		String passwordHash = ""; 
		try {
			//パスワードのハッシュ化
			String rawPassword = req.getParameter("password"); //リクエスト時のパスワード(平文)入手
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			passwordHash = String.format("%064x", new BigInteger(1, digest.digest())); //リクエスト時パスワード(ハッシュ化)をセット
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//DBからユーザーを取得
		UserDAO userDAO = new UserDAO();
		UserInfo user = userDAO.get( req.getParameter("userid") ); //ユーザーDBからリクエストidに該当するuserid取得
		
		//DBからの取得が成功　AND パスワードハッシュが合致
		if(user != null && user.getPwdHash().equals(passwordHash)) { //登録時パスhashと入力時パスhashの判断
		    HttpSession session = req.getSession(); //セッションスコープの生成
		    session.setAttribute("userid", user.getUserId() ); //セッションスコープ内に属性名userid,DB情報useridを保存
		    session.setAttribute("username", user.getName()); //セッションスコープ内に属性名user,DB情報userを保存
			resp.sendRedirect("/WebSystem/"); //リダイレクト:別ページに飛ばす,~/WebSystem/(MainUrl.java)に移行

		}
		else { //DBから取得失敗時,パスワード不一致時
			//TODO ログインエラーにリダイレクト
			//エラー画面がまだないのでログイン画面に戻す
			resp.sendRedirect("/WebSystem/login");
		}
	}
}
