package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.util.IdUtil;

public class SynonymAliasInfo implements AliasInfo, Formatable {
   private String schemaName = null;
   private String tableName = null;

   public SynonymAliasInfo() {
   }

   public SynonymAliasInfo(String var1, String var2) {
      this.schemaName = var1;
      this.tableName = var2;
   }

   public String getSynonymTable() {
      return this.tableName;
   }

   public String getSynonymSchema() {
      return this.schemaName;
   }

   public boolean isTableFunction() {
      return false;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.schemaName = (String)var1.readObject();
      this.tableName = (String)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.schemaName);
      var1.writeObject(this.tableName);
   }

   public int getTypeFormatId() {
      return 455;
   }

   public String toString() {
      return IdUtil.mkQualifiedName(this.schemaName, this.tableName);
   }

   public String getMethodName() {
      return null;
   }
}
