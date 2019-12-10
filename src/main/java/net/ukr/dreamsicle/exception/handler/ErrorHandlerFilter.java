package net.ukr.dreamsicle.exception.handler;

import net.ukr.dreamsicle.exception.DreamsicleException;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandlerFilter", urlPatterns = "/*")
public class ErrorHandlerFilter extends AbstractFilter {

    private static final Logger LOGGER = Logger.getLogger(ErrorHandlerFilter.class);

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (DreamsicleException exception) {
            String requestURI = request.getRequestURI();
            LOGGER.error("Some problem " + requestURI, exception);

            try {
                response.setStatus(exception.getErrorCode());
                new ExceptionDto().handleAppSpecificException(exception, response.getWriter());
            } catch (Exception ex) {
                new ExceptionDto().handleUnexpectedException(ex);
            }

        }
    }

}
