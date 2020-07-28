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

import model.MoveInfo;

//ユーザー追加機能
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存
@WebServlet("/move")
public class AddMove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMove() {
        super();

    }

    //GETリクエスト(新規登録リンクをクリック)時は登録ページ表示
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //GETリクエスト
	    RequestDispatcher dispatcher =
		        req.getRequestDispatcher
		            ("/WEB-INF/jsp/addActiviites.jsp"); //フォワード：情報渡し
		    dispatcher.forward(req, resp);
	}

	//POSTリクエスト時はフォームの入力値をUserクラスに渡す,登録確認ページを表示
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		MoveInfo move = new MoveInfo(); //MoveInfoクラスのインスタンス化
		move.setDay( req.getParameter("day") ); //Moveクラスset~メソッドにリクエストパラメータ[day]を渡す
		move.setStarttime( req.getParameter("starttime"));
		move.setFinishtime( req.getParameter("finishtime") );
		move.setPlace( req.getParameter("place") );
		move.setReason( req.getParameter("reason") );
		move.setOther( req.getParameter("other") );
		
		try {
			HttpSession session = req.getSession(); //セッションスコープの生成
			session.setAttribute("moveToAdd", move); //セッションスコープに"useToAdd"という属性名でuesrインスタンス保存 
		    
			RequestDispatcher dispatcher =
			        req.getRequestDispatcher
			            ("/WEB-INF/jsp/addMoveInfoPage.jsp"); //フォワード転送先指定
			    dispatcher.forward(req, resp);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}