package org.apache.derby.impl.sql;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.execute.ExecCursorTableReference;

public class CursorTableReference implements ExecCursorTableReference, Formatable {
   private String exposedName;
   private String baseName;
   private String schemaName;

   public CursorTableReference() {
   }

   public CursorTableReference(String var1, String var2, String var3) {
      this.exposedName = var1;
      this.baseName = var2;
      this.schemaName = var3;
   }

   public String getBaseName() {
      return this.baseName;
   }

   public String getExposedName() {
      return this.exposedName;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.baseName);
      var1.writeObject(this.exposedName);
      var1.writeObject(this.schemaName);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.baseName = (String)var1.readObject();
      this.exposedName = (String)var1.readObject();
      this.schemaName = (String)var1.readObject();
   }

   public int getTypeFormatId() {
      return 296;
   }

   public String toString() {
      return "";
   }
}
