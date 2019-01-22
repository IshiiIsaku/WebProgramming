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


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// ログインセッションがある場合、ユーザ一覧画面にリダイレクトさせる
		//String loginId = request.getParameter("loginId");

				//セッションの確認
		        HttpSession session = request.getSession();

		        if(session.getAttribute("userInfo") != null){
			             // ユーザ一覧サーブレットにリダイレクト
		 		         response.sendRedirect("UserListServlet");


		 		         return;
				          }
		 // フォワード！！
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginGamen.jsp");
 		dispatcher.forward(request, response);
 	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);



		 // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

     // リクエストパラメータの入力項目を取得
     		String loginId = request.getParameter("loginId");
     		String password = request.getParameter("password");

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

     	// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
    		UserDao userDao = new UserDao();
    		User user = userDao.findByLoginInfo(loginId, result);


    		/** テーブルに該当のデータが見つからなかった場合 **/
    		if (user == null) {
    			// リクエストスコープにエラーメッセージをセット
    			request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります");


     	// ログインjspにフォワード
     				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginGamen.jsp");
     				dispatcher.forward(request, response);
     				return;

    		}

    		/** テーブルに該当のデータが見つかった場合 **/
    		// セッションにユーザの情報をセット
    		HttpSession session = request.getSession();
    		session.setAttribute("userInfo", user);

    		// ユーザ一覧のサーブレットにリダイレクト
    		response.sendRedirect("UserListServlet");

    }


}
