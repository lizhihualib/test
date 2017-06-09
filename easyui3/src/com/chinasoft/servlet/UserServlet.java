package com.chinasoft.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.bean.User;
import com.chinasoft.query.UserQuery;
import com.chinasoft.service.IBaseService;
import com.chinasoft.service.impl.UserServiceImpl;
import com.chinasoft.utils.Date2JsonValueProcessor;
import com.chinasoft.utils.RequestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@WebServlet(name="userServlet",urlPatterns="/userServlet")
public class UserServlet extends BaseServlet{
	
	public void queryUsers(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		RequestUtil<UserQuery> reqs=new RequestUtil<UserQuery>(req);
		UserQuery uq=reqs.toBean(UserQuery.class);	
		
		IBaseService<User> userService=new UserServiceImpl();
		List<User> list=userService.queryAll(uq);
		int count=userService.queryAllCount(uq);
		
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new Date2JsonValueProcessor("yyyy-MM-dd"));
			
		JSONObject json=new JSONObject();
		json.put("rows", JSONArray.fromObject(list, config));
		json.put("total",count);
		System.out.println(json.toString());
		resp.getWriter().println(json);
	}
	
	public void updateUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
	
		RequestUtil<User> reqs=new RequestUtil<User>(req);	
		User user=reqs.toBean(User.class);
		
		
		System.out.println(user.toString());
		
		IBaseService<User> userService=new UserServiceImpl();
		boolean flag=userService.update(user);
		JSONObject json=new JSONObject();
		if(flag){
			json.put("status", 1);
			json.put("tip", "修改成功");
		}else{
			json.put("status", 0);
			json.put("tip", "修改失败");
		}
		resp.getWriter().println(json);
		
		
	}
	public void addUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		
		RequestUtil<User> reqs=new RequestUtil<User>(req);	
		User user=reqs.toBean(User.class);
		
		
		System.out.println(user.toString());
		
		IBaseService<User> userService=new UserServiceImpl();
		
		boolean flag=userService.add(user);
		
		JSONObject json=new JSONObject();
		if(flag){
			json.put("status", 1);
			json.put("tip", "添加成功");
		}else{
			json.put("status", 0);
			json.put("tip", "添加失败");
		}
		resp.getWriter().println(json);
		
		
	}
	public void deleteUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String ids=req.getParameter("ids");
		
		IBaseService<User> userService=new UserServiceImpl();
		
		boolean flag=userService.delete(ids);
		
		JSONObject json=new JSONObject();
		if(flag){
			json.put("status", 1);
			json.put("tip", "删除成功");
		}else{
			json.put("status", 0);
			json.put("tip", "删除失败");
		}
		resp.getWriter().println(json);
		
		
	}
}
