package org.datanucleus.metadata;

public class InvalidMemberMetaDataException extends InvalidMetaDataException {
   private static final long serialVersionUID = -8889474376874514402L;
   String className;
   String memberName;

   public InvalidMemberMetaDataException(String key, Object... params) {
      super(key, params);
      this.className = (String)params[0];
      this.memberName = (String)params[1];
   }

   public String getClassName() {
      return this.className;
   }

   public String getMemberName() {
      return this.memberName;
   }
}
