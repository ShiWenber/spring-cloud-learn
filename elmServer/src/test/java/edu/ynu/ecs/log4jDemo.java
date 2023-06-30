package edu.ynu.ecs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class log4jDemo {
    @Test
    public void testLog4j(){
        log.warn("logger class: {}", log.getClass());
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
