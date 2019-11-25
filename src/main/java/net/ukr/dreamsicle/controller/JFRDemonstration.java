package net.ukr.dreamsicle.controller;

import net.ukr.dreamsicle.service.DemonstrationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo")
public class JFRDemonstration extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(JFRDemonstration.class);
    private static final DemonstrationService demonstrationService = new DemonstrationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        LOGGER.info("Demonstration work for param: " + param);

        if (param.equals("oom")) {
            demonstrationService.getOutOfMemoryError();
        } else {
            demonstrationService.getStackOverflowException();
        }
    }
}
