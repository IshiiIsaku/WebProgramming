package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログインセッションがない場合、ログイン画面にリダイレクトさせる

		//セッションの確認
        HttpSession session = request.getSession();

        if(session.getAttribute("userInfo") == null){
	             // // ログイン画面サーブレットにリダイレクト
 		         response.sendRedirect("loginServlet");

 		         return;
		          }



		// ユーザ一覧情報を取得
				UserDao userDao = new UserDao();
				List<User> userList = userDao.findAll();

				// リクエストスコープにユーザ一覧情報をセット
				request.setAttribute("userList", userList);

				// ユーザ一覧のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userIchiran.jsp");
				dispatcher.forward(request, response);


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		 // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        // リクエストパラメータの入力項目を取得
     		String loginId = request.getParameter("loginId");
     		String userName =request.getParameter("userName");
     		String birthday =request.getParameter("birthday");
     		String birthday1 =request.getParameter("birthday1");

     		UserDao userDao = new UserDao();

		    List<User> userList = userDao.kensaku(loginId,userName,birthday,birthday1);

				// リクエストスコープにユーザ一覧情報をセット
				request.setAttribute("userList", userList);


    			// ユーザ新規登録jspにフォワード
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userIchiran.jsp");
 				dispatcher.forward(request, response);

	}

}
