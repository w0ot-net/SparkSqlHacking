package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.util.ArrayUtil;

public class DefaultInfoImpl implements DefaultInfo, Formatable {
   private DataValueDescriptor defaultValue;
   private String defaultText;
   private int type;
   private String[] referencedColumnNames;
   private String originalCurrentSchema;
   private static final int BITS_MASK_IS_DEFAULTVALUE_AUTOINC = 1;
   private static final int BITS_MASK_IS_GENERATED_COLUMN = 2;

   public DefaultInfoImpl() {
   }

   public DefaultInfoImpl(boolean var1, String var2, DataValueDescriptor var3) {
      this.type = calcType(var1);
      this.defaultText = var2;
      this.defaultValue = var3;
   }

   public DefaultInfoImpl(String var1, String[] var2, String var3) {
      if (var2 == null) {
         var2 = new String[0];
      }

      this.type = 2;
      this.defaultText = var1;
      this.referencedColumnNames = var2;
      this.originalCurrentSchema = var3;
   }

   public String getDefaultText() {
      return this.defaultText;
   }

   public String[] getReferencedColumnNames() {
      return (String[])ArrayUtil.copy(this.referencedColumnNames);
   }

   public String getOriginalCurrentSchema() {
      return this.originalCurrentSchema;
   }

   public String toString() {
      if (this.isDefaultValueAutoinc()) {
         return "GENERATED_BY_DEFAULT";
      } else {
         return this.isGeneratedColumn() ? "GENERATED ALWAYS AS ( " + this.defaultText + " )" : this.defaultText;
      }
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.defaultText = (String)var1.readObject();
      this.defaultValue = (DataValueDescriptor)var1.readObject();
      this.type = var1.readInt();
      if (this.isGeneratedColumn()) {
         int var2 = var1.readInt();
         this.referencedColumnNames = new String[var2];

         for(int var3 = 0; var3 < var2; ++var3) {
            this.referencedColumnNames[var3] = (String)var1.readObject();
         }

         this.originalCurrentSchema = (String)var1.readObject();
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.defaultText);
      var1.writeObject(this.defaultValue);
      var1.writeInt(this.type);
      if (this.isGeneratedColumn()) {
         int var2 = this.referencedColumnNames.length;
         var1.writeInt(var2);

         for(int var3 = 0; var3 < var2; ++var3) {
            var1.writeObject(this.referencedColumnNames[var3]);
         }

         var1.writeObject(this.originalCurrentSchema);
      }

   }

   public int getTypeFormatId() {
      return 326;
   }

   public DataValueDescriptor getDefaultValue() {
      return this.defaultValue;
   }

   public void setDefaultValue(DataValueDescriptor var1) {
      this.defaultValue = var1;
   }

   public boolean isDefaultValueAutoinc() {
      return (this.type & 1) != 0;
   }

   public boolean isGeneratedColumn() {
      return (this.type & 2) != 0;
   }

   private static int calcType(boolean var0) {
      int var1 = 0;
      if (var0) {
         var1 |= 1;
      }

      return var1;
   }
}
