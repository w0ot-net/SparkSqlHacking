package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

public class UserDefinedTypeIdImpl extends BaseTypeIdImpl {
   protected String className;

   public UserDefinedTypeIdImpl() {
   }

   public UserDefinedTypeIdImpl(String var1) throws StandardException {
      if (var1.charAt(0) == '"') {
         String[] var2 = IdUtil.parseMultiPartSQLIdentifier(var1);
         this.schemaName = var2[0];
         this.unqualifiedName = var2[1];
         Object var3 = null;
      } else {
         this.schemaName = null;
         this.unqualifiedName = var1;
         this.className = var1;
      }

      this.JDBCTypeId = 2000;
   }

   public UserDefinedTypeIdImpl(String var1, String var2, String var3) {
      super(var1, var2);
      this.className = var3;
      this.JDBCTypeId = 2000;
   }

   public String getClassName() {
      return this.className;
   }

   public boolean userType() {
      return true;
   }

   public boolean isBound() {
      return this.className != null;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.className = var1.readUTF();
      this.JDBCTypeId = 2000;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      if (this.className == null) {
         throw new IOException("Internal error: class name for user defined type has not been determined yet.");
      } else {
         var1.writeUTF(this.className);
      }
   }

   public int getTypeFormatId() {
      return 264;
   }
}
