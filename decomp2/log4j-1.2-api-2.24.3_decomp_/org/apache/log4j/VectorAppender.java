package org.apache.log4j;

import java.util.Vector;
import org.apache.log4j.spi.LoggingEvent;

public class VectorAppender extends AppenderSkeleton {
   public Vector vector = new Vector();

   public void activateOptions() {
   }

   public void append(final LoggingEvent event) {
      try {
         Thread.sleep(100L);
      } catch (Exception var3) {
      }

      this.vector.addElement(event);
   }

   public synchronized void close() {
      if (!this.closed) {
         this.closed = true;
      }
   }

   public Vector getVector() {
      return this.vector;
   }

   public boolean isClosed() {
      return this.closed;
   }

   public boolean requiresLayout() {
      return false;
   }
}
