package com.xl.smbms.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.xl.smbms.dao.UserMapper;
import com.xl.smbms.entity.Page;
import com.xl.smbms.entity.Role;
import com.xl.smbms.entity.User;
import com.xl.smbms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper us;

    @RequestMapping("/hi")
    public String hello(){
        return "index";
    }

    @RequestMapping("/new")
    public String add(){
        return "user/new";
    }


    @RequestMapping("/save")
    public String save(@Valid User user, BindingResult br, MultipartFile attach, HttpServletRequest request){

        System.out.println("save:"+user);

        if (br.hasErrors()) {
            //return "user/new";
        }
        us.insert(user);
        System.out.println(user.toString());
        String path = request.getSession().getServletContext().getRealPath("store");
        System.out.println("文件保存路径:"+path);

        String fileName = attach.getOriginalFilename();

        File target = new File(path+ File.separator+fileName);
        if (!target.exists()) {
            target.mkdirs();
        }

        try {
            attach.transferTo(target);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:list";
    }

    @RequestMapping("/check/{code}")
    @ResponseBody
    public String check(@PathVariable("code")String userCode){
        System.out.println("code:"+userCode);

        int result = us.check(userCode);

        return "login";
    }

    @RequestMapping("/list")
    public String search(Model m,User u, @RequestParam(defaultValue="1")int current){
        System.out.println("search:"+u);

        Page page = new Page();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", u);
        map.put("start", (current - 1)* page.getSize());
        map.put("size", page.getSize());

        page.setTotal(us.total(map));
        page.setCurrent(current);
        page.setFirst(1);
        page.setLast(page.getTotalPage());
        if (current >1) {
            page.setPrevious(current - 1);
        }else{
            page.setPrevious(1);
        }
        if (current < page.getTotalPage()) {
            page.setNext(current +1);
        }else{
            page.setNext(page.getTotalPage());
        }

        System.out.println("page:"+page);

        List<User> users = us.search(map);
        for (User user : users) {
            System.out.println(user);
        }
        m.addAttribute("users", users);
        m.addAttribute("page", page);

        return "user/list";
    }

    @RequestMapping("/login")
    public String login(String userCode,String userPassword,Model m,HttpSession session){
        System.out.println("login:"+userCode);
        System.out.println("login:"+userPassword);

        User user = us.login(userCode);
        if (user == null) {
            m.addAttribute("error","用户名不存在");
            return "login";
        }else if (!userPassword.equals(user.getUserPassword())) {
            m.addAttribute("error","密码不正确");
            return "login";
        }

//		if (session.getServletContext().getAttribute("roles")==null){
//			List<Role> roles = us.getAllRoles();
//			session.getServletContext().setAttribute("roles", roles);
//		}

        session.setAttribute("USER_SESSION", user);

        return "frame";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("USER_SESSION");
        return "redirect:/login";
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") String id){

        System.out.println("delete:"+id);

        return "login";

    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") String id , Model m){

        System.out.println("get:"+id);

        m.addAttribute("user",us.getUserById(id));

        return "user/view";

    }

    @RequestMapping("/update")
    public String update(User user){

        System.out.println("update:"+user);

        us.update(user);

        return "redirect:user/list";

    }




}