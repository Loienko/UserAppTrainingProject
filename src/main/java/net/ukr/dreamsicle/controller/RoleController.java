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
import java.util.stream.Collectors;

@WebServlet("/roles")
public class RoleController extends HttpServlet implements DreamsicleUtilController {

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
            LOGGER.error(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleJsonBody = request.getReader().lines().collect(Collectors.joining());
        LOGGER.debug("Create a new Role with Body: " + roleJsonBody);

        response.getWriter().write(
                roleService.create(
                        RoleDto.builder()
                                .roleDtoFromJson(roleJsonBody)
                )
        );
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idForUpdate = request.getParameter("id");
        LOGGER.info("Update Role by the id: " + idForUpdate);
        String roleJsonBody = request.getReader().lines().collect(Collectors.joining());
        LOGGER.debug("Update Role by the Body: " + roleJsonBody);

        response.getWriter().write(
                roleService.update(
                        Integer.parseInt(idForUpdate),
                        RoleDto.builder().roleDtoFromJson(roleJsonBody)
                ));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        LOGGER.info("Delete Entity by the id: " + id);

        response.getWriter().write(roleService.delete(Integer.parseInt(id)));
    }
}
