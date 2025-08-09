package org.sparkproject.jetty.util.thread;

import java.security.PrivilegedAction;
import org.sparkproject.jetty.util.security.SecurityUtils;

class PrivilegedThreadFactory {
   static Thread newThread(PrivilegedAction creator) {
      return (Thread)SecurityUtils.doPrivileged(creator);
   }

   private PrivilegedThreadFactory() {
   }
}
