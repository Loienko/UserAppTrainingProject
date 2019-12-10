package net.ukr.dreamsicle.exception.handler;

import net.ukr.dreamsicle.exception.DreamsicleException;
import org.apache.log4j.Logger;

import java.io.PrintWriter;

public class ExceptionDto {

    private static final Logger LOGGER = Logger.getLogger(ExceptionDto.class);

    public void handleAppSpecificException(DreamsicleException exception, PrintWriter writer) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(exception.getErrorCode().toString())
                .errorMessage(exception.getMessage())
                .build();
        writer.println(ErrorMessage.builder().convertToJson(errorMessage));
    }

    public void handleUnexpectedException(Exception ex) {
        LOGGER.info("Unexpected Exception!" + ex.getMessage(), ex);

        throw new RuntimeException(ex);
    }
}
