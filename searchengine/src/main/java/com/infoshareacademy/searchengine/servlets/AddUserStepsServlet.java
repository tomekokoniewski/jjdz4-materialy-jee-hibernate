package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-user-steps")
public class AddUserStepsServlet extends AddUserFormServlet {

    @EJB
    UsersRepositoryDao usersRepositoryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addUser(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user;
        if (req.getSession().getAttribute("user") == null) {
            user = new User();
            req.getSession().setAttribute("user", user);
        }

        int step = Integer.parseInt(req.getParameter("step"));
        storeUserDataFromSteps(req, resp, step);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(getNextView(step, req, resp));
        requestDispatcher.forward(req, resp);
    }

    private String getNextView(int step, HttpServletRequest req, HttpServletResponse resp) {
        if (step == 1 && !isValidData(req, resp, step)) {
            return "/add-user-step-1.jsp";
        } else if (step < 3) {
            return "/add-user-step-" + (step + 1) + ".jsp";
        }
        return "/index.jsp";
    }

    private boolean isValidData(HttpServletRequest req, HttpServletResponse resp, int step) {
        if (step == 1 && req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            if (usersRepositoryDao.getUserById(Integer.parseInt(req.getParameter("id"))) != null) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                req.setAttribute("errorMessage", "User with this ID exists.");
                return false;
            }
        }
        if (step == 1 && req.getParameter("login") != null && !req.getParameter("login").isEmpty()) {
            if (usersRepositoryDao.getUserByLogin(req.getParameter("login")) != null) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                req.setAttribute("errorMessage", "User with this login exists.");
                return false;
            }
        }
        return true;
    }

    private void storeUserDataFromSteps(HttpServletRequest req, HttpServletResponse resp, int step) {
        User user = (User) req.getSession().getAttribute("user");

        switch (step) {
            case 1:
                user.setId(Integer.parseInt(req.getParameter("id")));
                user.setLogin(req.getParameter("login"));
                break;
            case 2:
                user.setName(req.getParameter("name"));
                user.setSurname(req.getParameter("surname"));
                user.setAge(Integer.parseInt(req.getParameter("age")));
                break;
            case 3:
                setUserGender(req, user);
                if (isValidData(req, resp, step)) {
                    usersRepositoryDao.addUser(user);
                    req.getSession().setAttribute("user", null);
                    req.setAttribute("okMessage", "User with ID " + user.getId() + " has been added.");
                }
        }
    }
}
