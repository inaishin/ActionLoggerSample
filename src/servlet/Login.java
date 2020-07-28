package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ログインページの表示

@WebServlet("/login") //アノテーション
public class Login extends HttpServlet {

	@Override
	//GETリクエスト時はログインページ表示　GETリクエスト：URL,ブックマ,リンク時のリクエスト
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		//フォワード:同一アプリ内での引継ぎ
	    RequestDispatcher dispatcher =
	    		req.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp"); //転送先を指定
		    dispatcher.forward(req, resp); //リクエスト/レスポンスを渡す
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
	}

}
