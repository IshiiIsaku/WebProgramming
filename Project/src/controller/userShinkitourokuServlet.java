package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import dao.UserDao;


@WebServlet("/userShinkitourokuServlet")
public class userShinkitourokuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public userShinkitourokuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる

		//セッションの確認
        HttpSession session = request.getSession();

        if(session.getAttribute("userInfo") == null){
	             // // ログイン画面サーブレットにリダイレクト
 		         response.sendRedirect("loginServlet");

 		         return;
		          }

		// ユーザ新規登録のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userShinkitouroku.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

     // リクエストパラメータの入力項目を取得
     		String loginId = request.getParameter("loginID");
     		String password = request.getParameter("password");
     		String password1 = request.getParameter("password1");
     		String userName =request.getParameter("userName");
     		String birthday =request.getParameter("birthday");



    		/**暗号化**/
    		//ハッシュを生成したい元の文字列
				String source = password;
				//ハッシュ生成前にバイト配列に置き換える際のCharset
				Charset charset = StandardCharsets.UTF_8;
				//ハッシュアルゴリズム
				String algorithm = "MD5";

				//ハッシュ生成処理
				byte[] bytes = null;
				try {
					bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				String result = DatatypeConverter.printHexBinary(bytes);
				//標準出力
				System.out.println(result);

    		/** 入力したパスワードが違う場合 **/
    		if (!(password.equals(password1))) {
    			// リクエストスコープにエラーメッセージをセット
    			request.setAttribute("erMsg", "入力された内容は正しくありません");


    			// ユーザ新規登録jspにフォワード
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userShinkitouroku.jsp");
 				dispatcher.forward(request, response);
 				return;
    		}

    		/** 入力項目に1つでも未記入のものがある場合 **/
    		 if(loginId == null|| loginId.length() == 0||
    		    password == null || password.length() == 0||
    		    password1 == null || password1.length() == 0||
    		    userName == null || userName.length() == 0||
    		    birthday == null || birthday.length() == 0){


    			 //入力された内容をセット
    			 request.setAttribute("loginId", loginId);
    			 request.setAttribute("userName", userName);
    			 request.setAttribute("birthday", birthday);

    			 // リクエストスコープにエラーメッセージをセット

        			request.setAttribute("erMsg", "入力された内容は正しくありません");
        			// ユーザ新規登録jspにフォワード
     				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userShinkitouroku.jsp");
     				dispatcher.forward(request, response);
     				return;

    		 }
    		 /** 既に登録されているログインIDが入力された場合 **/


    		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
     		UserDao userDao = new UserDao();
     		try {
     			userDao.shinki(loginId, result, userName, birthday);
     		} catch(SQLException e) {

     			//入力された内容をセット
     		 request.setAttribute("loginId", loginId);
   			 request.setAttribute("userName", userName);
   			 request.setAttribute("birthday", birthday);


     			// リクエストスコープにエラーメッセージをセット
    			request.setAttribute("erMsg", "入力された内容は正しくありません");
    			// ユーザ新規登録jspにフォワード


 				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userShinkitouroku.jsp");
 				dispatcher.forward(request, response);
 				return;
     		}


    		// ユーザ一覧のサーブレットにリダイレクト
    		response.sendRedirect("UserListServlet");

    		}


	}





