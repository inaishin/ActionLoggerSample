package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.MoveInfo;
import model.UserInfo;


public class MoveDAO {
	// データベース接続に使用する情報
		private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionloggersample";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";
		
		
		
		//ユーザーIDを指定して、ユーザー情報を取得
		//ユーザーIDが存在しない場合はnullを返す
		public List<MoveInfo> findAll(String userid) { //DB内情報の全取得,引数ありでログイン中のuserid受取
			List<MoveInfo> moveList=new ArrayList<>(); //list生成

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					
				// SELECT文の準備
				String sql = "SELECT * FROM MOVE WHERE userid=? ORDER BY DAY DESC,FTIME DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
				//?に条件文セット
				pStmt.setString(1,userid); //MoveInfoクラスに格納されているuseridを条件式に記述
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
				

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					MoveInfo move = new MoveInfo();
					move.setDay(rs.getString("day")); //DB内-列名をmoveクラスにセット
					move.setStarttime(rs.getString("stime"));
					move.setFinishtime(rs.getString("ftime"));
					move.setPlace(rs.getString("place"));
					move.setReason(rs.getString("reason"));
					move.setOther(rs.getString("other"));
					
					moveList.add(move); //リスト追加
				}
			} catch (SQLException e) {//エラー処理
				e.printStackTrace(); 
				//return null;
			}
			return moveList;
		}
		
		
		
		//DB検索
		public List<MoveInfo> Search(String userid,String day,String place) { //DB内情報の全取得,引数ありでログイン中のuserid受取
			List<MoveInfo> searchList=new ArrayList<>(); //list生成

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					
				// SELECT文の準備
				String sql = "SELECT * FROM MOVE WHERE userid=? AND DAY LIKE '%"+day+"%' AND PLACE LIKE '%"+place+"%' ORDER BY DAY DESC,FTIME DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
				//?に条件文セット
				pStmt.setString(1,userid);

				// SELECTを実行
				ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
				

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					MoveInfo move = new MoveInfo();
					move.setDay(rs.getString("day")); //DB内-列名をmoveクラスにセット
					move.setStarttime(rs.getString("stime"));
					move.setFinishtime(rs.getString("ftime"));
					move.setPlace(rs.getString("place"));
					move.setReason(rs.getString("reason"));
					move.setOther(rs.getString("other"));
					
					searchList.add(move); //リスト追加
				}
			} catch (SQLException e) {//エラー処理
				e.printStackTrace(); 
				//return null;
			}
			return searchList;
		}

		
		
		//ユーザーを指定して、活動記録をDBに追加
		//戻り値:true 成功 , false 失敗
		public boolean save(MoveInfo move) {
			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// INSERT文の準備(idは自動連番なので指定しなくてよい）
				String sql = "INSERT INTO MOVE " + "( day, stime, ftime, place, reason, other, userid ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementインターフェース:SQL文を表す
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				pStmt.setString(1, move.getDay()); //DBにデータ登録
				pStmt.setString(2, move.getStarttime());
				pStmt.setString(3, move.getFinishtime());
				pStmt.setString(4, move.getPlace());
				pStmt.setString(5, move.getReason());
				pStmt.setString(6, move.getOther());
				pStmt.setString(7, move.getUseid());

				// INSERT文を実行11
				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
}
