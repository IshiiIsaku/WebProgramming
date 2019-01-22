package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import dao.UserDao;
import model.User;


@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserUpdateServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// ログインセッションがない場合、ログイン画面にリダイレクトさせる

		//セッションの確認
        HttpSession session = request.getSession();

        if(session.getAttribute("userInfo") == null){
	             // // ログイン画面サーブレットにリダイレクト
 		         response.sendRedirect("loginServlet");

 		         return;
		          }



		//URLからGETパラメータとしてIDを受け取る
		String loginId = request.getParameter("id");

		// loginIdを引数にして、loginIdに紐づくユーザ情報を出力する
		UserDao userDao = new UserDao();
		User user = userDao.shousai(loginId);


		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("user", user);

	    // ユーザ情報更新のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userJouhoukoushin.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);

		 // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

         // リクエストパラメータの入力項目を取得
            String loginId = request.getParameter("loginId");
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
    			request.setAttribute("errorMsg", "入力された内容は正しくありません");

    			//他のやり方　インスタンス化して、詰めなおして、jsp側で名前を合わせる
    			//User user = new User();

    			//user.setLoginId(loginId);
    			//user.setName(userName);
    			//user.setBirthDate(birthday);

    			 request.setAttribute("loginId", loginId);
      			 request.setAttribute("userName", userName);
      			 request.setAttribute("birthday", birthday);


    			// ユーザ情報更新jspにフォワード
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userJouhoukoushin.jsp");
 				dispatcher.forward(request, response);
 				return;
    		}


    		/** 入力項目にパスワード以外の未記入のものがある場合 **/
   		 if(userName == null || userName.length() == 0||
   		    birthday == null || birthday.length() == 0){
   	         // リクエストスコープにエラーメッセージをセット
       			request.setAttribute("errorMsg", "入力された内容は正しくありません");

       			request.setAttribute("loginId", loginId);
      			request.setAttribute("userName", userName);
      			request.setAttribute("birthday", birthday);


       			// ユーザ情報更新jspにフォワード
    				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userJouhoukoushin.jsp");
    				dispatcher.forward(request, response);
    				return;

   	     }

   		UserDao userDao = new UserDao();


		userDao.koushin(loginId, result, userName, birthday);


   		 // ユーザ一覧のサーブレットにリダイレクト
 		response.sendRedirect("UserListServlet");

	}



}



