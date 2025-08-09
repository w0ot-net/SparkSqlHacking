package org.apache.zookeeper.server.admin;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface CommandOutputter {
   String getContentType();

   default void output(CommandResponse response, PrintWriter pw) {
   }

   default void output(CommandResponse response, OutputStream os) {
   }
}
