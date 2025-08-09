package org.datanucleus.metadata;

import org.datanucleus.exceptions.NucleusFatalUserException;
import org.datanucleus.util.Localiser;

public class InvalidMetaDataException extends NucleusFatalUserException {
   private static final long serialVersionUID = -1227318171934042330L;
   protected String messageKey;

   protected InvalidMetaDataException(String key, String message) {
      super(message);
      this.messageKey = key;
   }

   public InvalidMetaDataException(String key, Object... params) {
      this(key, Localiser.msg(key, params));
   }

   public String getMessageKey() {
      return this.messageKey;
   }
}
