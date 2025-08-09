package org.apache.logging.log4j.message;

import java.io.Serializable;

public interface Message extends Serializable {
   String getFormattedMessage();

   /** @deprecated */
   @Deprecated
   default String getFormat() {
      return null;
   }

   Object[] getParameters();

   Throwable getThrowable();
}
