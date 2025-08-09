package org.apache.zookeeper.server.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.zookeeper.common.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamOutputter implements CommandOutputter {
   private static final Logger LOG = LoggerFactory.getLogger(StreamOutputter.class);
   private final String clientIP;

   public StreamOutputter(String clientIP) {
      this.clientIP = clientIP;
   }

   public String getContentType() {
      return "application/octet-stream";
   }

   public void output(CommandResponse response, OutputStream os) {
      try {
         InputStream is = response.getInputStream();

         try {
            IOUtils.copyBytes(is, os, 1024, true);
         } catch (Throwable var7) {
            if (is != null) {
               try {
                  is.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (is != null) {
            is.close();
         }
      } catch (IOException e) {
         LOG.warn("Exception streaming out data to {}", this.clientIP, e);
      }

   }
}
