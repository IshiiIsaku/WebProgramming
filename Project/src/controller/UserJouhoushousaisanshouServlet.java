package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;


@WebServlet("/UserJouhoushousaisanshou")
public class UserJouhoushousaisanshouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserJouhoushousaisanshouServlet() {
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


		//URLからGETパラメータとしてIDを受け取る
				String loginId = request.getParameter("id");



				// loginIdを引数にして、loginIdに紐づくユーザ情報を出力する
				UserDao userDao = new UserDao();
	    		User user = userDao.shousai(loginId);


				// ユーザ情報をリクエストスコープにセットしてjspにフォワード

				// リクエストスコープにユーザ一覧情報をセット
				request.setAttribute("user", user);

				// ユーザ情報詳細参照のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userJouhoushousaisanshou.jsp");
				dispatcher.forward(request, response);



	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);


	}

}
