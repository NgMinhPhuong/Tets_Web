package com.codegym.demoservlet;

import com.google.gson.Gson;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message = "123";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter printWriter = resp.getWriter();
        String s = "";
        try{
            s = Databases.getData();
        } catch (Exception e){
            e.printStackTrace();
        }
        printWriter.write(s);
        Databases.cnt++;
        if(Databases.cnt == 5) Databases.cnt = 1;
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        // Lấy dữ liệu từ yêu cầu
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // requestBody là chuỗi JSON gửi từ client
        String json = requestBody.toString();
        resp.getWriter().write(json);

        // Tiếp tục xử lý đối tượng JSON
        Gson gson = new Gson();
        MyObject a = gson.fromJson(json, MyObject.class);
        Databases.writeData(a);


    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        if(req.getMethod().equals("OPTIONS")){
            doOptions(req, resp);
        } else if(req.getMethod().equals("POST")){
            doPost(req, resp);
        } else if(req.getMethod().equals("GET")){
            doGet(req, resp);
        }
    }

    @Override
    public void init() {
        System.out.println("hàm tạo");
    }

    @Override
    public void destroy() {
        System.out.println("huy here");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        System.out.println("option");
    }
}