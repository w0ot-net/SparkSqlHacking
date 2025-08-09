package org.apache.derby.impl.services.bytecode;

class BCMethodDescriptor {
   static final String[] EMPTY = new String[0];
   private final String[] vmParameterTypes;
   private final String vmReturnType;
   private final String vmDescriptor;

   BCMethodDescriptor(String[] var1, String var2, BCJava var3) {
      this.vmParameterTypes = var1;
      this.vmReturnType = var2;
      this.vmDescriptor = var3.vmType(this);
   }

   static String get(String[] var0, String var1, BCJava var2) {
      return (new BCMethodDescriptor(var0, var1, var2)).toString();
   }

   String buildMethodDescriptor() {
      int var1 = this.vmParameterTypes.length;
      int var2 = 30 * (var1 + 1);
      StringBuffer var3 = new StringBuffer(var2);
      var3.append('(');

      for(int var4 = 0; var4 < var1; ++var4) {
         var3.append(this.vmParameterTypes[var4]);
      }

      var3.append(')');
      var3.append(this.vmReturnType);
      return var3.toString();
   }

   public String toString() {
      return this.vmDescriptor;
   }

   public int hashCode() {
      return this.vmParameterTypes.length | this.vmReturnType.hashCode() & -256;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof BCMethodDescriptor var2)) {
         return false;
      } else if (var2.vmParameterTypes.length != this.vmParameterTypes.length) {
         return false;
      } else {
         for(int var3 = 0; var3 < this.vmParameterTypes.length; ++var3) {
            if (!this.vmParameterTypes[var3].equals(var2.vmParameterTypes[var3])) {
               return false;
            }
         }

         return this.vmReturnType.equals(var2.vmReturnType);
      }
   }
}
