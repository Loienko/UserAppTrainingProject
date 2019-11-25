package net.ukr.dreamsicle.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemonstrationService {

    static final List<Object[]> arrays = new LinkedList<>();
    private static final Logger logger = LogManager.getLogger(DemonstrationService.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void getStackOverflowException() {
        logger.info("StackOverflowException");
        getStackOverflowException();
    }

    public void getOutOfMemoryError() {
        try {
            executor.submit(this::grabAllMemory).get();
        } catch (Throwable e) {
            logger.error("OutOfMemoryError: ->>>>>>>> ", e);
        }
    }

    private void grabAllMemory() {
        for (; ; ) {
            logger.info("create object!");
            arrays.add(new Object[100000]);
        }
    }
}
