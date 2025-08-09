package org.apache.derby.catalog.types;

import [Lorg.apache.derby.catalog.TypeDescriptor;;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.io.Formatable;

public class TypeDescriptorImpl implements TypeDescriptor, Formatable {
   private BaseTypeIdImpl typeId;
   private int precision;
   private int scale;
   private boolean isNullable;
   private int maximumWidth;
   private int collationType = 0;

   public TypeDescriptorImpl() {
   }

   public TypeDescriptorImpl(BaseTypeIdImpl var1, int var2, int var3, boolean var4, int var5) {
      this.typeId = var1;
      this.precision = var2;
      this.scale = var3;
      this.isNullable = var4;
      this.maximumWidth = var5;
   }

   public TypeDescriptorImpl(BaseTypeIdImpl var1, int var2, int var3, boolean var4, int var5, int var6) {
      this.typeId = var1;
      this.precision = var2;
      this.scale = var3;
      this.isNullable = var4;
      this.maximumWidth = var5;
      this.collationType = var6;
   }

   public TypeDescriptorImpl(BaseTypeIdImpl var1, boolean var2, int var3) {
      this.typeId = var1;
      this.isNullable = var2;
      this.maximumWidth = var3;
      this.scale = 0;
      this.precision = 0;
   }

   public TypeDescriptorImpl(TypeDescriptorImpl var1, int var2, int var3, boolean var4, int var5) {
      this.typeId = var1.typeId;
      this.precision = var2;
      this.scale = var3;
      this.isNullable = var4;
      this.maximumWidth = var5;
   }

   public TypeDescriptorImpl(TypeDescriptorImpl var1, int var2, int var3, boolean var4, int var5, int var6) {
      this.typeId = var1.typeId;
      this.precision = var2;
      this.scale = var3;
      this.isNullable = var4;
      this.maximumWidth = var5;
      this.collationType = var6;
   }

   public TypeDescriptorImpl(TypeDescriptorImpl var1, boolean var2, int var3) {
      this.typeId = var1.typeId;
      this.precision = var1.precision;
      this.scale = var1.scale;
      this.isNullable = var2;
      this.maximumWidth = var3;
   }

   public static TypeDescriptor[] copyTypeDescriptors(TypeDescriptor[] var0) {
      return var0 == null ? null : (TypeDescriptor[])((TypeDescriptor;)var0).clone();
   }

   public int getMaximumWidth() {
      return this.maximumWidth;
   }

   public int getMaximumWidthInBytes() {
      switch (this.typeId.getJDBCTypeId()) {
         case -7:
         case -6:
         case -4:
         case -3:
         case -2:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 2004:
            return this.maximumWidth;
         case -5:
            return 40;
         case -1:
         case 1:
         case 12:
         case 2005:
            if (this.maximumWidth > 0 && 2 * this.maximumWidth < 0) {
               return Integer.MAX_VALUE;
            }

            return 2 * this.maximumWidth;
         case 0:
         case 1111:
         case 2000:
         case 2001:
         case 2002:
         case 2003:
         case 2006:
         default:
            return -1;
         case 2:
         case 3:
            return 2 * (this.precision + 2);
         case 16:
            return 1;
         case 91:
         case 92:
            return 6;
         case 93:
            return 16;
      }
   }

   public boolean isStringType() {
      switch (this.typeId.getJDBCTypeId()) {
         case -1:
         case 1:
         case 12:
         case 2005:
            return true;
         default:
            return false;
      }
   }

   public int getJDBCTypeId() {
      return this.typeId.getJDBCTypeId();
   }

   public String getTypeName() {
      return this.typeId.getSQLTypeName();
   }

   public int getPrecision() {
      return this.precision;
   }

   public int getScale() {
      return this.scale;
   }

   public boolean isNullable() {
      return this.isNullable;
   }

   public boolean isRowMultiSet() {
      return this.typeId instanceof RowMultiSetImpl;
   }

   public boolean isUserDefinedType() {
      return this.typeId.userType();
   }

   public int getCollationType() {
      return this.collationType;
   }

   public void setCollationType(int var1) {
      this.collationType = var1;
   }

   public String getSQLstring() {
      return this.typeId.toParsableString(this);
   }

   public String toString() {
      String var1 = this.getSQLstring();
      return !this.isNullable() ? var1 + " NOT NULL" : var1;
   }

   public BaseTypeIdImpl getTypeId() {
      return this.typeId;
   }

   public boolean equals(Object var1) {
      TypeDescriptor var2 = (TypeDescriptor)var1;
      if (this.getTypeName().equals(var2.getTypeName()) && this.precision == var2.getPrecision() && this.scale == var2.getScale() && this.isNullable == var2.isNullable() && this.maximumWidth == var2.getMaximumWidth()) {
         switch (this.typeId.getJDBCTypeId()) {
            case -1:
            case 1:
            case 12:
            case 2005:
               if (this.collationType != var2.getCollationType()) {
                  return false;
               }

               return true;
            default:
               return true;
         }
      } else {
         return false;
      }
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.typeId = (BaseTypeIdImpl)var1.readObject();
      this.precision = var1.readInt();
      switch (this.typeId.getJDBCTypeId()) {
         case -1:
         case 1:
         case 12:
         case 2005:
            this.scale = 0;
            this.collationType = var1.readInt();
            break;
         default:
            this.scale = var1.readInt();
            this.collationType = 0;
      }

      this.isNullable = var1.readBoolean();
      this.maximumWidth = var1.readInt();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.typeId);
      var1.writeInt(this.precision);
      switch (this.typeId.getJDBCTypeId()) {
         case -1:
         case 1:
         case 12:
         case 2005:
            var1.writeInt(this.collationType);
            break;
         default:
            var1.writeInt(this.scale);
      }

      var1.writeBoolean(this.isNullable);
      var1.writeInt(this.maximumWidth);
   }

   public int getTypeFormatId() {
      return 14;
   }

   public String[] getRowColumnNames() {
      return !this.isRowMultiSet() ? null : ((RowMultiSetImpl)this.typeId).getColumnNames();
   }

   public TypeDescriptor[] getRowTypes() {
      return !this.isRowMultiSet() ? null : ((RowMultiSetImpl)this.typeId).getTypes();
   }
}
