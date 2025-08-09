package org.datanucleus.metadata;

public class InvalidClassMetaDataException extends InvalidMetaDataException {
   private static final long serialVersionUID = 6498740110129166983L;
   String className;

   public InvalidClassMetaDataException(String key, Object... params) {
      super(key, params);
      this.className = (String)params[0];
   }

   public String getClassName() {
      return this.className;
   }
}
