package org.sparkproject.jetty.server.handler.gzip;

import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.compression.CompressionPool;

public interface GzipFactory {
   CompressionPool.Entry getDeflaterEntry(Request var1, long var2);

   boolean isMimeTypeGzipable(String var1);
}
