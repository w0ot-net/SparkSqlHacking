package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.types.DataTypeDescriptor;

public class AggregateAliasInfo implements AliasInfo, Formatable {
   private static final int FIRST_VERSION = 0;
   private TypeDescriptor _forType;
   private TypeDescriptor _returnType;

   public AggregateAliasInfo() {
   }

   public AggregateAliasInfo(TypeDescriptor var1, TypeDescriptor var2) {
      this._forType = var1;
      this._returnType = var2;
   }

   public boolean isTableFunction() {
      return false;
   }

   public TypeDescriptor getForType() {
      return this._forType;
   }

   public TypeDescriptor getReturnType() {
      return this._returnType;
   }

   public void setCollationTypeForAllStringTypes(int var1) {
      this._forType = DataTypeDescriptor.getCatalogType(this._forType, var1);
      this._returnType = DataTypeDescriptor.getCatalogType(this._returnType, var1);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();
      this._forType = (TypeDescriptor)var1.readObject();
      this._returnType = (TypeDescriptor)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(0);
      var1.writeObject(this._forType);
      var1.writeObject(this._returnType);
   }

   public int getTypeFormatId() {
      return 475;
   }

   public String toString() {
      String var10000 = this._forType.getSQLstring();
      return "FOR " + var10000 + " RETURNS " + this._returnType.getSQLstring();
   }

   public String getMethodName() {
      return null;
   }
}
