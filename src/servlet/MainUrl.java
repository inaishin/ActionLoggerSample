package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//URLクリック,ページ訪問時

@WebServlet("/") //webアノテーション
public class MainUrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//GETリクエスト：URL,ブックマ,リンク時のリクエスト
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSessionインタフェースのオブジェクトを取得
	    HttpSession session = request.getSession(); //セッションスコープの取得,セッションスコープ：保存インスタンスをインスタンス/レスポンス後でも残る
	    //セッションスコープ内のuseridを取得
	    String userid = (String)session.getAttribute("userid");
	    
	    
	    //useridが登録されてない場合,ログインページ表示
	    if(userid==null) {
	    	response.sendRedirect("/WebSystem/login"); //レスポンスで別ページ呼び出し
	    }
	    
	    else {
	    	//トップページを表示
		    RequestDispatcher dispatcher =
		        request.getRequestDispatcher
		            ("/WEB-INF/jsp/mainView.jsp");
		    dispatcher.forward(request, response); //同一アプリケーション内jsp/サーブに処理渡し
	    }
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

