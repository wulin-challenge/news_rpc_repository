package cn.wulin.ioc.logging.jcl;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import cn.wulin.ioc.logging.Level;
import cn.wulin.ioc.logging.Logger;
import cn.wulin.ioc.logging.LoggerAdapter;


public class JclLoggerAdapter implements LoggerAdapter {

    private Level level;
    private File file;

    public Logger getLogger(String key) {
        return new JclLogger(LogFactory.getLog(key));
    }

    public Logger getLogger(Class<?> key) {
        return new JclLogger(LogFactory.getLog(key));
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
