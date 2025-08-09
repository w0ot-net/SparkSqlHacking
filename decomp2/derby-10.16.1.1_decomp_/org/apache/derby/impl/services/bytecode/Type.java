package org.apache.derby.impl.services.bytecode;

import org.apache.derby.iapi.services.classfile.ClassHolder;

final class Type {
   static final Type LONG = new Type("long", "J");
   static final Type INT = new Type("int", "I");
   static final Type SHORT = new Type("short", "S");
   static final Type BYTE = new Type("byte", "B");
   static final Type BOOLEAN = new Type("boolean", "Z");
   static final Type FLOAT = new Type("float", "F");
   static final Type DOUBLE = new Type("double", "D");
   static final Type STRING = new Type("java.lang.String", "Ljava/lang/String;");
   private final String javaName;
   private final short vmType;
   private final String vmName;
   final String vmNameSimple;

   Type(String var1, String var2) {
      this.vmName = var2;
      this.javaName = var1;
      this.vmType = BCJava.vmTypeId(var2);
      this.vmNameSimple = ClassHolder.convertToInternalClassName(var1);
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("[");
      var1.append(this.javaName);
      var1.append(", ");
      var1.append(this.vmType);
      var1.append(", ");
      var1.append(this.vmName);
      var1.append(", ");
      var1.append(this.vmNameSimple);
      var1.append("]");
      return var1.toString();
   }

   String javaName() {
      return this.javaName;
   }

   String vmName() {
      return this.vmName;
   }

   short vmType() {
      return this.vmType;
   }

   int width() {
      return width(this.vmType);
   }

   static int width(short var0) {
      switch (var0) {
         case -1:
            return 0;
         case 3:
         case 5:
            return 2;
         default:
            return 1;
      }
   }
}
