package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/new_tweet")
public class NewTweetServlet extends HttpServlet {
	@Override
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response
	)throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//获取请求参数content的值，代表推文的内容。
		String content = request.getParameter("content");
		//获取请求参数author的值，代表推文的作者。
		String author = request.getParameter("author");
		//创建一个TweetDAO对象。
		//TweetDAO是一个数据访问对象类，用于与数据库交互。
		TweetDAO tweetDAO = new TweetDAO();
		
		try {
			//调用TweetDAO对象的addTweet方法，
			//将新的推文内容和作者信息添加到数据库中。
			tweetDAO.addTweet(content, author);
			 request.getSession().setAttribute("message", "投稿が成功しました");
		} catch (Exception e) {
			e.printStackTrace();
			  request.getSession().setAttribute("message", "投稿に失敗しました");
		}
		
		response.sendRedirect("tweet_list");
	}
}
