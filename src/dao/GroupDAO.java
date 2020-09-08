package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupInfo;
import model.GroupMasterInfo;
import model.MoveInfo;


public class GroupDAO {
	// データベース接続に使用する情報
		private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionloggersample";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";
	
		
		//全検索
		public List<GroupInfo> findAll() { //DB内情報の全取得,引数ありでログイン中のuserid受取
			List<GroupInfo> groupList=new ArrayList<>(); //list生成

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					
				// SELECT文の準備
				String sql = "SELECT * FROM TEAM  ORDER BY teamid";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
				

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					GroupInfo group = new GroupInfo();
					group.setGroupid(rs.getString("teamid")); //DB内-列名をmoveクラスにセット
					group.setGroupname(rs.getString("teamname")); //DB内-列名をmoveクラスにセット
					
					groupList.add(group); //リスト追加
				}
			} catch (SQLException e) {//エラー処理
				e.printStackTrace(); 
				//return null;
			}
			return groupList;
		}
			
		
			//DB登録
			//戻り値:true 成功 , false 失敗
			public boolean save(GroupInfo group) {
				// データベース接続
				try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

					// INSERT文の準備(idは自動連番なので指定しなくてよい）
					String sql = "INSERT INTO TEAM " + "( teamid, teamname) "
							+ "VALUES ( ?, ? )";
					PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementインターフェース:SQL文を表す
					// INSERT文中の「?」に使用する値を設定しSQLを完成
					pStmt.setString(1, group.getGroupid()); //DBにデータ登録
					pStmt.setString(2, group.getGroupname());
				
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
			
		
			
		//userid/teamid登録
		public boolean savaTeamidUseidr(String teamid,String id) {
			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// INSERT文の準備(idは自動連番なので指定しなくてよい）
				String sql = "INSERT　INTO TEAMID_USERID " + "( teamid, userid) "+ "VALUES ( ?, ? )";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementインターフェース:SQL文を表す
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				pStmt.setString(1, teamid); //DBにデータ登録
				pStmt.setString(2, id);
			
				// INSERT文を実行
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
		
		
		
		//masteruserid/teamid登録
		public boolean saveMasterid(String teamid,String id) {
			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// INSERT文の準備(idは自動連番なので指定しなくてよい）
				String sql = "INSERT　INTO TEAMID_MASTERID  " + "( teamid, MASTERID) "
						+ "VALUES ( ?, ? )";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementインターフェース:SQL文を表す
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				pStmt.setString(1, teamid); //DBにデータ登録
				pStmt.setString(2, id);
			
				// INSERT文を実行
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
		
		
		//GroupMaster所属情報
		public List<GroupMasterInfo> GroupMaster(String masterid) { //DB内情報の全取得,引数ありでログイン中のuserid受取
			List<GroupMasterInfo> groupList=new ArrayList<>(); //list生成

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					
				// SELECT文の準備
				String sql ="SELECT team.TEAMNAME ,user.NAME ,day,STIME ,FTIME ,PLACE ,reason,other from TEAMID_USERID as ID join TEAM join MOVE join user "
						+ "WHERE ID.TEAMID  =  (SELECT teamid from TEAMID_MASTERID  where MASTERID =?) and ID.teamid =TEAM.TEAMID and MOVE.USERID =ID.USERID and user.USERID = ID.userid "
						+ "ORDER BY team.teamid, user.name , move.DAY DESC,move.STIME ";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
				pStmt.setString(1,masterid); //パラメーターセット
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
				

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					GroupMasterInfo groupmaster = new GroupMasterInfo();
					groupmaster.setTeamname(rs.getString("teamname")); //DB内teamname列名をsetTeamnameにセット
					groupmaster.setName(rs.getString("name"));
					groupmaster.setDay(rs.getString("day"));
					groupmaster.setStime(rs.getString("stime"));
					groupmaster.setFtime(rs.getString("ftime"));
					groupmaster.setPlace(rs.getString("place"));
					groupmaster.setReason(rs.getString("reason"));
					groupmaster.setOther(rs.getString("other"));
					
					groupList.add(groupmaster); //リスト追加
				}
			} catch (SQLException e) {//エラー処理
				e.printStackTrace(); 
				//return null;
			}
			return groupList;
		}
		
		
		//GroupMaster内検索
		public List<GroupMasterInfo> SearchGroupMaster(String masterid,String day,String place) { //DB内情報の全取得,引数ありでログイン中のuserid受取
			List<GroupMasterInfo> groupList=new ArrayList<>(); //list生成

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					
				// SELECT文の準備
				String sql ="SELECT team.TEAMNAME ,user.NAME ,day,STIME ,FTIME ,PLACE ,reason,other from TEAMID_USERID as ID join TEAM join MOVE join user "
						+ "WHERE ID.TEAMID  =  (SELECT teamid from TEAMID_MASTERID  where MASTERID =?) and ID.teamid =TEAM.TEAMID and MOVE.USERID =ID.USERID and user.USERID = ID.userid and DAY LIKE '%"+day+"%' AND PLACE LIKE '%"+place+"%'"
						+ "ORDER BY team.teamid, user.name , move.DAY DESC,move.STIME ";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
				pStmt.setString(1,masterid); //パラメーターセット
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
				

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					GroupMasterInfo groupmaster = new GroupMasterInfo();
					groupmaster.setTeamname(rs.getString("teamname")); //DB内teamname列名をsetTeamnameにセット
					groupmaster.setName(rs.getString("name"));
					groupmaster.setDay(rs.getString("day"));
					groupmaster.setStime(rs.getString("stime"));
					groupmaster.setFtime(rs.getString("ftime"));
					groupmaster.setPlace(rs.getString("place"));
					groupmaster.setReason(rs.getString("reason"));
					groupmaster.setOther(rs.getString("other"));
					
					groupList.add(groupmaster); //リスト追加
				}
			} catch (SQLException e) {//エラー処理
				e.printStackTrace(); 
				//return null;
			}
			return groupList;
		}
		
		
		
		//所属グループ取得
		public List<GroupInfo> findUser(String userid) { //DB内情報の全取得,引数ありでログイン中のuserid受取
			List<GroupInfo> groupList=new ArrayList<>(); //list生成

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					
				// SELECT文の準備
				String sql = "SELECT teamname FROM TEAMID_USERID  as a join  TEAM  as b WHERE a.teamid =b.teamid and userid=?";
				PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
				pStmt.setString(1, userid); //パラメータセット
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
				

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					GroupInfo group = new GroupInfo();
					group.setGroupname(rs.getString("teamname")); //DB内-列名をmoveクラスにセット
					
					groupList.add(group); //リスト追加
				}
			} catch (SQLException e) {//エラー処理
				e.printStackTrace(); 
				//return null;
			}
			return groupList;
		}
		
		
		
		//管理ユーザーグループ取得
				public List<GroupInfo> findMaster(String userid) { //DB内情報の全取得,引数ありでログイン中のuserid受取
					List<GroupInfo> groupList=new ArrayList<>(); //list生成

					// データベース接続
					try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
							
						// SELECT文の準備
						String sql = "SELECT teamname FROM TEAMID_MASTERID  as a join  TEAM  as b WHERE a.teamid =b.teamid and masterid=?";
						PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
						pStmt.setString(1, userid); //パラメータセット
						// SELECTを実行
						ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
						

						// SELECT文の結果をuserに格納
						while (rs.next()) {
							GroupInfo group = new GroupInfo();
							group.setGroupname(rs.getString("teamname")); //DB内-列名をmoveクラスにセット
							
							groupList.add(group); //リスト追加
						}
					} catch (SQLException e) {//エラー処理
						e.printStackTrace(); 
						//return null;
					}
					return groupList;
				}
		
			
			
}
