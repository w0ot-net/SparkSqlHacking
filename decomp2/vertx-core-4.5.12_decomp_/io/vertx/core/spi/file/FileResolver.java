package io.vertx.core.spi.file;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public interface FileResolver extends Closeable {
   File resolveFile(String var1);

   void close() throws IOException;
}
