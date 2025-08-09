package org.apache.hive.service.cli;

import java.util.List;
import org.apache.hadoop.hive.serde2.thrift.Type;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hive.service.rpc.thrift.TPrimitiveTypeEntry;
import org.apache.hive.service.rpc.thrift.TTypeDesc;
import org.apache.hive.service.rpc.thrift.TTypeEntry;

public class TypeDescriptor {
   private final Type type;
   private String typeName = null;
   private TypeQualifiers typeQualifiers = null;

   public TypeDescriptor(Type type) {
      this.type = type;
   }

   public TypeDescriptor(TTypeDesc tTypeDesc) {
      List<TTypeEntry> tTypeEntries = tTypeDesc.getTypes();
      TPrimitiveTypeEntry top = ((TTypeEntry)tTypeEntries.get(0)).getPrimitiveEntry();
      this.type = Type.getType(top.getType());
      if (top.isSetTypeQualifiers()) {
         this.setTypeQualifiers(TypeQualifiers.fromTTypeQualifiers(top.getTypeQualifiers()));
      }

   }

   public TypeDescriptor(String typeName) {
      this.type = Type.getType(typeName);
      if (this.type.isComplexType()) {
         this.typeName = typeName;
      } else if (this.type.isQualifiedType()) {
         PrimitiveTypeInfo pti = TypeInfoFactory.getPrimitiveTypeInfo(typeName);
         this.setTypeQualifiers(TypeQualifiers.fromTypeInfo(pti));
      }

   }

   public Type getType() {
      return this.type;
   }

   public TTypeDesc toTTypeDesc() {
      TPrimitiveTypeEntry primitiveEntry = new TPrimitiveTypeEntry(this.type.toTType());
      if (this.getTypeQualifiers() != null) {
         primitiveEntry.setTypeQualifiers(this.getTypeQualifiers().toTTypeQualifiers());
      }

      TTypeEntry entry = TTypeEntry.primitiveEntry(primitiveEntry);
      TTypeDesc desc = new TTypeDesc();
      desc.addToTypes(entry);
      return desc;
   }

   public String getTypeName() {
      return this.typeName != null ? this.typeName : this.type.getName();
   }

   public TypeQualifiers getTypeQualifiers() {
      return this.typeQualifiers;
   }

   public void setTypeQualifiers(TypeQualifiers typeQualifiers) {
      this.typeQualifiers = typeQualifiers;
   }

   public Integer getColumnSize() {
      if (this.type.isNumericType()) {
         return this.getPrecision();
      } else {
         switch (this.type) {
            case STRING_TYPE:
            case BINARY_TYPE:
               return Integer.MAX_VALUE;
            case CHAR_TYPE:
            case VARCHAR_TYPE:
               return this.typeQualifiers.getCharacterMaximumLength();
            case DATE_TYPE:
               return 10;
            case TIMESTAMP_TYPE:
               return 29;
            default:
               return null;
         }
      }
   }

   public Integer getPrecision() {
      return this.type == Type.DECIMAL_TYPE ? this.typeQualifiers.getPrecision() : this.type.getMaxPrecision();
   }

   public Integer getDecimalDigits() {
      switch (this.type) {
         case TIMESTAMP_TYPE:
            return 9;
         case BOOLEAN_TYPE:
         case TINYINT_TYPE:
         case SMALLINT_TYPE:
         case INT_TYPE:
         case BIGINT_TYPE:
            return 0;
         case FLOAT_TYPE:
            return 7;
         case DOUBLE_TYPE:
            return 15;
         case DECIMAL_TYPE:
            return this.typeQualifiers.getScale();
         default:
            return null;
      }
   }
}
