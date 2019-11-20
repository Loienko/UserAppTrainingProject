package net.ukr.dreamsicle.controller;

import net.ukr.dreamsicle.dto.UserDto;
import net.ukr.dreamsicle.exception.ApplicationException;
import net.ukr.dreamsicle.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static net.ukr.dreamsicle.dao.imp.UserDaoImpl.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

@WebServlet("/users")
public class UserController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    private static final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        LOGGER.info("Get User the id: " + id);

        String usersResult = Optional.ofNullable(id)
                .map(ids -> userService.findById(Integer.parseInt(ids)))
                .orElse(userService.findAll());

        try {
            response.getOutputStream().println(usersResult);
        } catch (IOException e) {
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        LOGGER.info("Create new User with data: " + userName + ", " + firstName + ", " + lastName + ", " + roleId);

        response.getWriter().write(userService.create(
                UserDto.builder()
                        .userName(userName)
                        .firstName(firstName)
                        .lastName(lastName)
                        .roleId(roleId)
                        .build()
        ));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idForUpdate = request.getParameter("id");
        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        LOGGER.info("Update User by the id: " + idForUpdate + " with data: " + userName + ", " + firstName + ", " + lastName + ", " + roleId);

        response.getWriter().write(userService.update(
                Integer.parseInt(idForUpdate),
                UserDto.builder()
                        .userName(userName)
                        .firstName(firstName)
                        .lastName(lastName)
                        .roleId(roleId)
                        .build()
        ));
    }
}
