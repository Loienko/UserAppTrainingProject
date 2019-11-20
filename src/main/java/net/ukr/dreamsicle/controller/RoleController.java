package net.ukr.dreamsicle.controller;

import net.ukr.dreamsicle.dto.RoleDto;
import net.ukr.dreamsicle.exception.ApplicationException;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String roleId = request.getParameter("id");
        LOGGER.info("Get Role by an id: " + roleId);

        String rolesResult = Optional.ofNullable(roleId)
                .map(ids -> roleService.findById(Integer.parseInt(ids)))
                .orElse(roleService.findAll());

        try {
            response.getOutputStream().println(rolesResult);
        } catch (IOException e) {
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleName = request.getParameter("name");
        String roleDescription = request.getParameter("description");

        LOGGER.info("Create a new Role with data" + roleName + ", " + roleDescription);

        response.getWriter().write(roleService.create(
                RoleDto.builder()
                        .roleName(roleName)
                        .roleDescription(roleDescription)
                        .build()
        ));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String roleId = request.getParameter("id");
        String roleName = request.getParameter("name");
        String roleDescription = request.getParameter("description");

        LOGGER.info("Update Role by the id: " + roleId + " with data" + roleName + ", " + roleDescription);

        response.getWriter().write(roleService.update(
                Integer.parseInt(roleId),
                RoleDto.builder()
                        .roleName(roleName)
                        .roleDescription(roleDescription)
                        .build()
        ));
    }
}
