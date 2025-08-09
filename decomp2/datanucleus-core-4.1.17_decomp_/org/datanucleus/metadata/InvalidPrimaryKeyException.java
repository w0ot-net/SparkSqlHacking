package org.datanucleus.metadata;

public class InvalidPrimaryKeyException extends InvalidClassMetaDataException {
   private static final long serialVersionUID = 4755699002846237657L;

   public InvalidPrimaryKeyException(String key, Object... params) {
      super(key, params);
   }
}
