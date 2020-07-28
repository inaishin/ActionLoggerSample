package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MoveDAO;
import dao.UserDAO;
import model.MoveInfo;
import model.UserInfo;

//ユーザーが登録するユーザー情報を確認した後、OKをクリックしたときの処理
@WebServlet("/addmoveconfirm")
public class AddMoveConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMoveConfirm() {
        super();
    }

    //フォームがPOSTリクエスト時
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//statusがconfirmedの場合
		//本来は正当な登録確認であることをチェックするべきであるが、とりあえずOmit
		
			HttpSession session = req.getSession();
			//セッションスコープに保存していた、DB登録前のユーザー情報を取得
			MoveInfo move = (MoveInfo)session.getAttribute("moveToAdd"); //セッションスコープ内属名moveTo~の値をクラスに渡す
			MoveDAO moveDAO = new MoveDAO();
			moveDAO.save(move);	//DBに保存
			//TODO 主キーの重複で保存できなかった場合の処理を追加
			
		
		//DBへの保存が成功したものとして、ログインページに遷移
		resp.sendRedirect("WebSystem/?view=addActivities");
	}
}
