package org.apache.logging.log4j.spi;

import java.io.Closeable;

public interface LoggerAdapter extends Closeable {
   Object getLogger(String name);
}
