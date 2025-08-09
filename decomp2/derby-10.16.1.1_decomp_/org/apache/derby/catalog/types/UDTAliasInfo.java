package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.iapi.services.io.Formatable;

public class UDTAliasInfo implements AliasInfo, Formatable {
   private static final int FIRST_VERSION = 0;

   public boolean isTableFunction() {
      return false;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(0);
   }

   public int getTypeFormatId() {
      return 474;
   }

   public String toString() {
      return "LANGUAGE JAVA";
   }

   public String getMethodName() {
      return null;
   }
}
