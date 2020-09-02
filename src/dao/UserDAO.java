package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupInfo;
import model.UserInfo;

//DB上のuserテーブルに対応するDAO,DAO:データベースの操作を担当するクラス
public class UserDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionloggersample";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//ユーザーIDを指定して、ユーザー情報を取得
	//ユーザーIDが存在しない場合はnullを返す
	public UserInfo get(String userId) {
		UserInfo user = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM user WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId); //

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をuserに格納
			while (rs.next()) {
				user = new UserInfo();
				user.setUserId(rs.getString("userid"));
				user.setPwdHash(rs.getString("pwdhash"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	
	//ユーザーを指定して、ユーザー情報を保存
	//戻り値:true 成功 , false 失敗
	public boolean save(UserInfo user) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO user " + "( userid, pwdhash, name, address, tel, email ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementインターフェース:SQL分を表す
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getUserId()); //SQLにデータ登録
			pStmt.setString(2, user.getPwdHash());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getAddress());
			pStmt.setString(5, user.getTel());
			pStmt.setString(6, user.getEmail());

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
	
	
	//プロフｨ表示
	public List<UserInfo> find(String userid) { //DB内情報の全取得,引数ありでログイン中のuserid受取
		List<UserInfo> userList=new ArrayList<>(); //list生成

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECT文の準備
			String sql = "SELECT userid , name,address ,tel, email  FROM USER WHERE userid =?";
			PreparedStatement pStmt = conn.prepareStatement(sql); //PreparedStatementクラス：SQL文をDBに送信する
			pStmt.setString(1, userid); //パラメーターセット
			
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery(); //ResultSetクラス：DBMSから検索結果受取,
					

					// SELECT文の結果をuserに格納
					while (rs.next()) {
						UserInfo user= new UserInfo();
						user.setUserId(rs.getString("userid")); //列名useridの値をセット
						user.setName(rs.getString("name"));
						user.setAddress(rs.getString("address"));
						user.setTel(rs.getString("tel"));
						user.setEmail(rs.getString("email"));
						
						userList.add(user); //リスト追加
					}
				} catch (SQLException e) {//エラー処理
					e.printStackTrace(); 
					//return null;
				}
				return userList;
			}
	
}
