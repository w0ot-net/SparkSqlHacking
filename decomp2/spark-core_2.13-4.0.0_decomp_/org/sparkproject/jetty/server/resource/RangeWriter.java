package org.sparkproject.jetty.server.resource;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public interface RangeWriter extends Closeable {
   void writeTo(OutputStream var1, long var2, long var4) throws IOException;
}
