package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.UserInfo;

//ユーザーが登録するユーザー情報を確認した後、OKをクリックしたときの処理
@WebServlet("/adduserconfirm")
public class AddUserConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddUserConfirm() {
        super();
    }

    //フォームがPOSTリクエスト時
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//statusがconfirmedの場合
		//本来は正当な登録確認であることをチェックするべきであるが、とりあえずOmit
		if(req.getParameter("status").equals("confirmed")) {
			HttpSession session = req.getSession();
			//セッションスコープに保存していた、DB登録前のユーザー情報を取得
			UserInfo user = (UserInfo)session.getAttribute("userToAdd");
			UserDAO userDAO = new UserDAO();
			userDAO.save(user);	//DBに保存
			//TODO 主キーの重複で保存できなかった場合の処理を追加
			
		}
		//DBへの保存が成功したものとして、ログインページに遷移
		resp.sendRedirect("/WebSystem/login");
	}
}
