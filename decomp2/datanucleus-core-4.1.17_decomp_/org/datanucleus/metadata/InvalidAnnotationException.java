package org.datanucleus.metadata;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class InvalidAnnotationException extends NucleusUserException {
   private static final long serialVersionUID = -8436370607632552044L;
   protected String messageKey;

   public InvalidAnnotationException(String key, Throwable cause, Object... params) {
      super(Localiser.msg(key, params), cause);
      this.setFatal();
   }

   public InvalidAnnotationException(String key, Object... params) {
      super(Localiser.msg(key, params));
      this.setFatal();
   }

   public String getMessageKey() {
      return this.messageKey;
   }
}
