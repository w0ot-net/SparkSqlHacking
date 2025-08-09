package org.apache.derby.impl.services.bytecode;

import java.io.IOException;
import org.apache.derby.iapi.services.classfile.ClassHolder;
import org.apache.derby.iapi.services.classfile.ClassMember;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

class BCClass extends GClass {
   String limitMsg;
   protected ClassHolder classHold;
   protected String superClassName;
   protected String name;
   BCJava factory;
   final Type classType;

   public LocalField addField(String var1, String var2, int var3) {
      Type var4 = this.factory.type(var1);
      ClassMember var5 = this.classHold.addMember(var2, var4.vmName(), var3);
      int var6 = this.classHold.addFieldReference(var5);
      return new BCLocalField(var4, var6);
   }

   public ByteArray getClassBytecode() throws StandardException {
      if (this.bytecode != null) {
         return this.bytecode;
      } else {
         try {
            this.bytecode = this.classHold.getFileFormat();
         } catch (IOException var2) {
            throw StandardException.newException("XBCM1.S", var2, new Object[]{this.getFullName()});
         }

         this.classHold = null;
         if (this.limitMsg != null) {
            throw StandardException.newException("XBCM4.S", new Object[]{this.getFullName(), this.limitMsg});
         } else {
            return this.bytecode;
         }
      }
   }

   public String getName() {
      return this.name;
   }

   public MethodBuilder newMethodBuilder(int var1, String var2, String var3) {
      return this.newMethodBuilder(var1, var2, var3, (String[])null);
   }

   public MethodBuilder newMethodBuilder(int var1, String var2, String var3, String[] var4) {
      BCMethod var5 = new BCMethod(this, var2, var3, var1, var4, this.factory);
      return var5;
   }

   public MethodBuilder newConstructorBuilder(int var1) {
      return new BCMethod(this, "void", "<init>", var1, (String[])null, this.factory);
   }

   String getSuperClassName() {
      return this.superClassName;
   }

   ClassHolder modify() {
      return this.classHold;
   }

   BCClass(ClassFactory var1, String var2, int var3, String var4, String var5, BCJava var6) {
      super(var1, var2.concat(var4));
      this.name = var4;
      if (var5 == null) {
         var5 = "java.lang.Object";
      }

      this.superClassName = var5;
      this.classType = var6.type(this.getFullName());
      this.classHold = new ClassHolder(this.qualifiedName, var6.type(var5).vmNameSimple, var3);
      this.factory = var6;
   }

   ClassFactory getClassFactory() {
      return this.cf;
   }

   void addLimitExceeded(BCMethod var1, String var2, int var3, int var4) {
      StringBuffer var5 = new StringBuffer();
      if (this.limitMsg != null) {
         var5.append(this.limitMsg);
         var5.append(", ");
      }

      var5.append("method:");
      var5.append(var1.getName());
      var5.append(" ");
      var5.append(var2);
      var5.append(" (");
      var5.append(var4);
      var5.append(" > ");
      var5.append(var3);
      var5.append(")");
      this.limitMsg = var5.toString();
   }

   void addLimitExceeded(String var1) {
      if (this.limitMsg != null) {
         this.limitMsg = this.limitMsg + ", " + var1;
      } else {
         this.limitMsg = var1;
      }

   }
}
