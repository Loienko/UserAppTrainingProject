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
import java.util.stream.Collectors;

@WebServlet("/users")
public class UserController extends HttpServlet implements DreamsicleUtilController {
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
            LOGGER.error(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e.fillInStackTrace());
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userJsonBody = request.getReader().lines().collect(Collectors.joining());
        LOGGER.debug("Create a new User with Body: " + userJsonBody);

        response.getWriter().write(
                userService.create(
                        UserDto.builder()
                                .userDtoFromJson(userJsonBody)
                )
        );
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idForUpdate = request.getParameter("id");
        LOGGER.info("Update User by the id: " + idForUpdate);
        String userJsonBody = request.getReader().lines().collect(Collectors.joining());
        LOGGER.debug("Update User by the Body: " + userJsonBody);

        response.getWriter().write(
                userService.update(
                        Integer.parseInt(idForUpdate),
                        UserDto.builder().userDtoFromJson(userJsonBody)
                ));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        LOGGER.info("Delete User by the id: " + id);

        response.getWriter().write(userService.delete(Integer.parseInt(id)));
    }
}
