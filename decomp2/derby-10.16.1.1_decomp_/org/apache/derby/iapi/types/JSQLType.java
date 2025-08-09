package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.shared.common.error.StandardException;

public final class JSQLType implements Formatable {
   public static final byte SQLTYPE = 0;
   public static final byte JAVA_CLASS = 1;
   public static final byte JAVA_PRIMITIVE = 2;
   public static final byte NOT_PRIMITIVE = -1;
   public static final byte BOOLEAN = 0;
   public static final byte CHAR = 1;
   public static final byte BYTE = 2;
   public static final byte SHORT = 3;
   public static final byte INT = 4;
   public static final byte LONG = 5;
   public static final byte FLOAT = 6;
   public static final byte DOUBLE = 7;
   private static final String[] wrapperClassNames = new String[]{"java.lang.Boolean", "java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double"};
   private static final String[] primitiveNames = new String[]{"boolean", "char", "byte", "short", "int", "long", "float", "double"};
   private byte category = 2;
   private DataTypeDescriptor sqlType;
   private String javaClassName;
   private byte primitiveKind;

   public JSQLType() {
      this.initialize((byte)4);
   }

   public JSQLType(DataTypeDescriptor var1) {
      this.initialize(var1);
   }

   public JSQLType(String var1) {
      byte var2 = getPrimitiveID(var1);
      if (var2 != -1) {
         this.initialize(var2);
      } else {
         this.initialize(var1);
      }

   }

   public JSQLType(byte var1) {
      this.initialize(var1);
   }

   public byte getCategory() {
      return this.category;
   }

   public byte getPrimitiveKind() {
      return this.primitiveKind;
   }

   public String getJavaClassName() {
      return this.javaClassName;
   }

   public DataTypeDescriptor getSQLType() throws StandardException {
      if (this.sqlType == null) {
         String var1;
         if (this.category == 1) {
            var1 = this.javaClassName;
         } else {
            var1 = getWrapperClassName(this.primitiveKind);
         }

         this.sqlType = DataTypeDescriptor.getSQLDataTypeDescriptor(var1);
      }

      return this.sqlType;
   }

   public static String getPrimitiveName(byte var0) {
      return primitiveNames[var0];
   }

   public int getTypeFormatId() {
      return 307;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      byte var2 = var1.readByte();
      switch (var2) {
         case 0 -> this.initialize((DataTypeDescriptor)var1.readObject());
         case 1 -> this.initialize((String)var1.readObject());
         case 2 -> this.initialize(var1.readByte());
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(this.category);
      switch (this.category) {
         case 0 -> var1.writeObject(this.sqlType);
         case 1 -> var1.writeObject(this.javaClassName);
         case 2 -> var1.writeByte(this.primitiveKind);
      }

   }

   private void initialize(byte var1) {
      this.initialize((byte)2, (DataTypeDescriptor)null, (String)null, var1);
   }

   private void initialize(DataTypeDescriptor var1) {
      this.initialize((byte)0, var1, (String)null, (byte)-1);
   }

   private void initialize(String var1) {
      this.initialize((byte)1, (DataTypeDescriptor)null, var1, (byte)-1);
   }

   private void initialize(byte var1, DataTypeDescriptor var2, String var3, byte var4) {
      this.category = var1;
      this.sqlType = var2;
      this.javaClassName = var3;
      this.primitiveKind = var4;
   }

   public static String getWrapperClassName(byte var0) {
      return var0 == -1 ? "" : wrapperClassNames[var0];
   }

   public static byte getPrimitiveID(String var0) {
      for(byte var1 = 0; var1 <= 7; ++var1) {
         if (primitiveNames[var1].equals(var0)) {
            return var1;
         }
      }

      return -1;
   }
}
