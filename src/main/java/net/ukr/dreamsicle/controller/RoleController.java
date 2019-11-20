package net.ukr.dreamsicle.controller;

import net.ukr.dreamsicle.exception.ApplicationException;
import net.ukr.dreamsicle.exception.ThrowingConsumer;
import net.ukr.dreamsicle.service.RoleService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static net.ukr.dreamsicle.dao.imp.UserDaoImpl.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

@WebServlet("/roles")
public class RoleController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RoleController.class);

    private static final RoleService roleService = new RoleService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String roleId = req.getParameter("roleId");
        LOGGER.info("Get Entity by an id: " + roleId);

        Optional.ofNullable(roleId).ifPresentOrElse(
                (ThrowingConsumer<String>) ids ->
                        resp.getOutputStream().println(roleService.findById(Integer.parseInt(ids))),
                () -> {
                    try {
                        resp.getOutputStream().println(roleService.findAll());
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
