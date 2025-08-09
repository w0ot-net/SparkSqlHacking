package org.apache.derby.impl.services.bytecode;

import org.apache.derby.iapi.services.compiler.ClassBuilder;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public abstract class GClass implements ClassBuilder {
   protected ByteArray bytecode;
   protected final ClassFactory cf;
   protected final String qualifiedName;

   public GClass(ClassFactory var1, String var2) {
      this.cf = var1;
      this.qualifiedName = var2;
   }

   public String getFullName() {
      return this.qualifiedName;
   }

   public GeneratedClass getGeneratedClass() throws StandardException {
      return this.cf.loadGeneratedClass(this.qualifiedName, this.getClassBytecode());
   }

   protected void writeClassFile(String var1, boolean var2, Throwable var3) throws StandardException {
   }

   final void validateType(String var1) {
   }
}
