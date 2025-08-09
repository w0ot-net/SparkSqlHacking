package org.datanucleus.metadata;

import java.io.Serializable;

public class IdentityStrategy implements Serializable {
   private static final long serialVersionUID = -6851202349718961853L;
   public static final IdentityStrategy NATIVE = new IdentityStrategy(1);
   public static final IdentityStrategy SEQUENCE = new IdentityStrategy(2);
   public static final IdentityStrategy IDENTITY = new IdentityStrategy(3);
   public static final IdentityStrategy INCREMENT = new IdentityStrategy(4);
   public static final IdentityStrategy UUIDSTRING = new IdentityStrategy(5);
   public static final IdentityStrategy UUIDHEX = new IdentityStrategy(6);
   public static final IdentityStrategy CUSTOM = new IdentityStrategy(7);
   private final int typeId;
   private String customName;

   private IdentityStrategy(int i) {
      this.typeId = i;
   }

   public String getCustomName() {
      return this.customName;
   }

   public int hashCode() {
      return this.typeId;
   }

   public boolean equals(Object o) {
      if (o instanceof IdentityStrategy) {
         return ((IdentityStrategy)o).typeId == this.typeId;
      } else {
         return false;
      }
   }

   public String toString() {
      switch (this.typeId) {
         case 1:
            return "native";
         case 2:
            return "sequence";
         case 3:
            return "identity";
         case 4:
            return "increment";
         case 5:
            return "uuid-string";
         case 6:
            return "uuid-hex";
         case 7:
            return "custom";
         default:
            return "";
      }
   }

   public int getType() {
      return this.typeId;
   }

   public static IdentityStrategy getIdentityStrategy(String value) {
      if (value == null) {
         return NATIVE;
      } else if (NATIVE.toString().equals(value)) {
         return NATIVE;
      } else if (SEQUENCE.toString().equals(value)) {
         return SEQUENCE;
      } else if (IDENTITY.toString().equals(value)) {
         return IDENTITY;
      } else if (INCREMENT.toString().equals(value)) {
         return INCREMENT;
      } else if ("TABLE".equalsIgnoreCase(value)) {
         return INCREMENT;
      } else if (UUIDSTRING.toString().equals(value)) {
         return UUIDSTRING;
      } else if (UUIDHEX.toString().equals(value)) {
         return UUIDHEX;
      } else {
         IdentityStrategy strategy = new IdentityStrategy(7);
         strategy.customName = value;
         return strategy;
      }
   }
}
