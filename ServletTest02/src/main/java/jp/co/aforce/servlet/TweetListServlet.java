package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Tweet;
import jp.co.aforce.dao.TweetDAO;

//这是一个Servlet注解，表明这个Servlet处理/tweet_list URL的请求。
@WebServlet("/tweet_list")
//定义了一个名为TweetListServlet的类，它继承了HttpServlet，表示这是一个Servlet类。
public class TweetListServlet extends HttpServlet {
	
	//这表示下面的方法是重写父类（HttpServlet）的方法。
	@Override
	
	//这个方法处理HTTP GET请求。
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response
	)throws ServletException, IOException {
		
		//创建一个TweetDAO对象。TweetDAO可能是一个数据访问对象类，用于与数据库交互。
		TweetDAO tweetDAO = new TweetDAO();
		
		//开始一个try块，用于捕获和处理可能的异常。
		try {
			
			//调用TweetDAO对象的getAllTweets方法，获取所有推文的列表，并将其存储在tweets变量中。
			List<Tweet> tweets = tweetDAO.getAllTweets();
			//将获取到的推文列表添加到请求属性中，名称为tweets，以便在后续的JSP页面中使用。
			request.setAttribute("tweets", tweets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		request.getRequestDispatcher("tweet_list.jsp").forward(request, response);
	}
}
