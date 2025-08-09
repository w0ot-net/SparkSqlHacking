package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.iapi.services.io.Formatable;

public class MethodAliasInfo implements AliasInfo, Formatable {
   private String methodName;

   public MethodAliasInfo() {
   }

   public MethodAliasInfo(String var1) {
      this.methodName = var1;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.methodName = (String)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.methodName);
   }

   public int getTypeFormatId() {
      return 312;
   }

   public String getMethodName() {
      return this.methodName;
   }

   public boolean isTableFunction() {
      return false;
   }

   public String toString() {
      return this.methodName;
   }
}
