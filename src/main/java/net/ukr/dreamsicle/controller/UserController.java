package net.ukr.dreamsicle.controller;

import net.ukr.dreamsicle.exception.ApplicationException;
import net.ukr.dreamsicle.exception.ThrowingConsumer;
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
        LOGGER.info("Get Entity the id: " + id);

        Optional.ofNullable(id).ifPresentOrElse(
                (ThrowingConsumer<String>) ids ->
                        response.getOutputStream().println(userService.findById(Integer.parseInt(ids))),
                () -> {
                    try {
                        response.getOutputStream().println(userService.findAll());
                    } catch (IOException e) {
                        throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
                    }
                }
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
