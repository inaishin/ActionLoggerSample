package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import model.GroupInfo;
import model.MoveInfo;

//新規グループ作成
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存

@WebServlet("/addgroup")
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
  public AddGroup() {
      super();

  }

  //GETリクエスト(新規登録リンクをクリック)時は登録ページ表示
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //GETリクエスト
	    RequestDispatcher dispatcher =
		        req.getRequestDispatcher
		            ("?view=addGroup"); //フォワード：情報渡し
		    dispatcher.forward(req, resp);
	}

	//POSTリクエスト時はフォームの入力値をGroupクラスに渡す,
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession(); //セッションスコープの生成
		
		GroupInfo group = new GroupInfo(); //MoveInfoクラスのインスタンス化
		group.setGroupid( req.getParameter("groupid") ); //groupクラスset~メソッドにリクエストパラメータ[day]を渡す
		group.setGroupname( req.getParameter("groupname"));
		
		try {
			String txt="ID="+group.getGroupid()+",名="+group.getGroupname()+"のグループ作成完了";
			
			ServletContext sc = getServletContext();  //アプリケーションスコープ
			sc.setAttribute("log", txt); //アプリケーション保存
			
			GroupDAO groupdao=new GroupDAO();
			groupdao.save(group);
		    
			RequestDispatcher dispatcher =
			        req.getRequestDispatcher
			            ("?view=addGroup"); //フォワード転送先指定
			    dispatcher.forward(req, resp);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}