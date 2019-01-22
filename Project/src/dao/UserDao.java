package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";
            //UserDetailServlet?id=${user.id}

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);

            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE id!=1";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");

                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }



    //ユーザ新規登録

    public void shinki(String loginId, String password,String userName,String birthday) throws SQLException {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();



            // INSERT文を準備
	   			 String sql = "INSERT INTO user(login_id,password,name,birth_date,create_date,update_date) VALUES (?,?,?,?,now(),now())";

             // 実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);
            pStmt.setString(3, userName);
            pStmt.setString(4, birthday);


            pStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //ユーザ情報詳細

   // ログインIDに紐づくユーザ情報を返す

    public User shousai(String loginId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ?";
            //UserJouhoushousaiServlet?id=${user.loginId};

             // SELECTを実行し、結果表を取得
            // 実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,loginId);

            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }
            int id = rs.getInt("id");
            String loginIdData = rs.getString("login_id");
            String name = rs.getString("name");
            Date birthDate = rs.getDate("birth_date");
            String password = rs.getString("password");
            String createDate = rs.getString("create_date");
            String updateDate = rs.getString("update_date");

            return new User(id,loginIdData,name,birthDate,password,createDate,updateDate);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

//ユーザ情報更新

    public void koushin(String loginId, String password,String userName,String birthday)  {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            if(password.equals("D41D8CD98F00B204E9800998ECF8427E")) {

            	String sql = "UPDATE user SET name = ?,birth_date = ?,update_date = now() WHERE login_id = ?";

            	PreparedStatement pStmt = conn.prepareStatement(sql);
                 pStmt.setString(3, loginId);
                 pStmt.setString(1, userName);
                 pStmt.setString(2, birthday);


                pStmt.executeUpdate();
            }

           // UPDATE文を準備
            else {
           String sql = "UPDATE user SET password = ?,name = ?,birth_date = ?,update_date = now() WHERE login_id = ?";


             // 実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(4, loginId);
            pStmt.setString(1, password);
            pStmt.setString(2, userName);
            pStmt.setString(3, birthday);


           pStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

    }

//ユーザ情報削除

    public void sakujo(String loginId){
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // UPDATE文を準備
           String sql = "DELETE FROM user WHERE login_id = ?";


             // 実行し、結果をSQLに
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }


    }

    //検索
    public List<User> kensaku(String loginId,String userName,String birthday,String birthday1) {

    	 Connection conn = null;
         List<User> userList = new ArrayList<User>();
         String sql;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            sql = "SELECT * FROM user WHERE id!=1";


            //ログインIDが入力された時の条件
            if(!loginId.equals("")) {

         // SELECT文を準備
            sql +=" AND login_id ='"+loginId+"'";


            }

            //ユーザ名が入力された時の条件
            if(!userName.equals("")) {
         // SELECT文を準備


            sql +=" AND name like '%"+userName+"%'";


            }

          //ログインIDが入力された時の条件
            if(!birthday.equals("")) {

         // SELECT文を準備
            sql += " AND birth_date >= '"+birthday+"'";

            }

            if(!birthday1.equals("")) {

            	sql += " AND birth_date <= '"+birthday1+"'";


            }


         // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId1 = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");

                User user = new User(id, loginId1, name, birthDate, password, createDate, updateDate);

                userList.add(user);

            } }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                // データベース切断
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            return userList ;
        }




    }










