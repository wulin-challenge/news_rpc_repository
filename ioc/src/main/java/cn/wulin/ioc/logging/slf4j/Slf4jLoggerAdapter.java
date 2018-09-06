package cn.wulin.ioc.logging.slf4j;

import java.io.File;

import cn.wulin.ioc.logging.Level;
import cn.wulin.ioc.logging.Logger;
import cn.wulin.ioc.logging.LoggerAdapter;

public class Slf4jLoggerAdapter implements LoggerAdapter {

    private Level level;
    private File file;

    public Logger getLogger(String key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    public Logger getLogger(Class<?> key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
