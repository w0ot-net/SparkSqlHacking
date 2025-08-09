package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.shared.common.util.ArrayUtil;

public class RowMultiSetImpl extends BaseTypeIdImpl {
   private String[] _columnNames;
   private TypeDescriptor[] _types;

   public RowMultiSetImpl() {
   }

   public RowMultiSetImpl(String[] var1, TypeDescriptor[] var2) {
      this._columnNames = (String[])ArrayUtil.copy(var1);
      this.setTypes(var2);
      if (var1 == null || var2 == null || var1.length != var2.length) {
         throw new IllegalArgumentException("Bad args: columnNames = " + var1 + ". types = " + var2);
      }
   }

   public String[] getColumnNames() {
      return (String[])ArrayUtil.copy(this._columnNames);
   }

   public TypeDescriptor[] getTypes() {
      return TypeDescriptorImpl.copyTypeDescriptors(this._types);
   }

   public void setTypes(TypeDescriptor[] var1) {
      this._types = TypeDescriptorImpl.copyTypeDescriptors(var1);
   }

   public String getSQLTypeName() {
      StringBuffer var1 = new StringBuffer();
      int var2 = this._columnNames.length;
      var1.append("TABLE ( ");

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 > 0) {
            var1.append(", ");
         }

         var1.append('"');
         var1.append(this._columnNames[var3]);
         var1.append('"');
         var1.append(' ');
         var1.append(this._types[var3].getSQLstring());
      }

      var1.append(" )");
      return var1.toString();
   }

   public int getJDBCTypeId() {
      return 1111;
   }

   public int getTypeFormatId() {
      return 469;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();
      this._columnNames = new String[var2];
      this._types = new TypeDescriptor[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         this._columnNames[var3] = var1.readUTF();
      }

      for(int var4 = 0; var4 < var2; ++var4) {
         this._types[var4] = (TypeDescriptor)var1.readObject();
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      int var2 = this._columnNames.length;
      var1.writeInt(var2);

      for(int var3 = 0; var3 < var2; ++var3) {
         var1.writeUTF(this._columnNames[var3]);
      }

      for(int var4 = 0; var4 < var2; ++var4) {
         var1.writeObject(this._types[var4]);
      }

   }
}
