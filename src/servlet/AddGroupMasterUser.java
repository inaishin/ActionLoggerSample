package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import model.GroupInfo;
import model.SearchInfo;

//グループidにユーザー追加-管理グループ
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存

@WebServlet("/addgroupmasteruser")
public class AddGroupMasterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public AddGroupMasterUser() {
		super();

	}
	
	//GETリクエスト(新規登録リンクをクリック)時は登録ページ表示
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //GETリクエスト
		    RequestDispatcher dispatcher =
			        req.getRequestDispatcher
			            ("/WebSystem/?view=joinGroupMasterUser"); //フォワード：情報渡し
			    dispatcher.forward(req, resp);
		}
		
		//POSTリクエスト時はフォームの入力値をUserクラスに渡す,登録確認ページを表示
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			
			HttpSession session = req.getSession(); //セッションスコープの生成
			
			GroupInfo groupinfo=new GroupInfo(); //SearchInfoクラスのインスタンス化
			groupinfo.setGroupid(req.getParameter("groupid")); //リクエストパラメータ[day]を渡す
			try {
				String userid=(String)session.getAttribute("userid"); //セッションスコープからインスタンス取得
				GroupDAO group= new GroupDAO();
				
				group.saveMasterid(groupinfo.getGroupid(),userid );
				RequestDispatcher dispatcher =
				        req.getRequestDispatcher
				            ("/WebSystem/?view=joinGroupMasterUser"); //フォワード：情報渡し
				    dispatcher.forward(req, resp);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
}