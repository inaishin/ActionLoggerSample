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

import java.util.List;

import dao.MoveDAO;
import model.MoveInfo;
import model.SearchInfo;


//
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存
@WebServlet("/searchmove")
public class SearchMove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchMove() {
        super();

    }

    //GETリクエスト(新規登録リンクをクリック)時は登録ページ表示
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //GETリクエスト
	    RequestDispatcher dispatcher =
		        req.getRequestDispatcher
		            ("WebSystem?view=activities"); //フォワード：情報渡し
		    dispatcher.forward(req, resp);
	}
	
	//POSTリクエスト時はフォームの入力値をUserクラスに渡す,登録確認ページを表示
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession(); //セッションスコープの生成
		
		SearchInfo search = new SearchInfo(); //SearchInfoクラスのインスタンス化
		search.setDay(req.getParameter("day")); //リクエストパラメータ[day]を渡す
		search.setPlace(req.getParameter("place"));
		try {
			session.setAttribute("day", search.getDay()); 
			session.setAttribute("place", search.getPlace()); 
			
			RequestDispatcher dispatcher =
			        req.getRequestDispatcher
			            ("WebSystem/?view=activities"); //フォワード：情報渡し
			    dispatcher.forward(req, resp);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
