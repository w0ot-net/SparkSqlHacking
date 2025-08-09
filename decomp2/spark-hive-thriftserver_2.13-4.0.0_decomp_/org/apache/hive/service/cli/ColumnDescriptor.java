package org.apache.hive.service.cli;

import org.apache.hadoop.hive.serde2.thrift.Type;
import org.apache.hive.service.rpc.thrift.TColumnDesc;

public class ColumnDescriptor {
   private final String name;
   private final String comment;
   private final TypeDescriptor type;
   private final int position;

   public ColumnDescriptor(String name, String comment, TypeDescriptor type, int position) {
      this.name = name;
      this.comment = comment;
      this.type = type;
      this.position = position;
   }

   public ColumnDescriptor(TColumnDesc tColumnDesc) {
      this.name = tColumnDesc.getColumnName();
      this.comment = tColumnDesc.getComment();
      this.type = new TypeDescriptor(tColumnDesc.getTypeDesc());
      this.position = tColumnDesc.getPosition();
   }

   public static ColumnDescriptor newPrimitiveColumnDescriptor(String name, String comment, Type type, int position) {
      return new ColumnDescriptor(name, comment, new TypeDescriptor(type), position);
   }

   public String getName() {
      return this.name;
   }

   public String getComment() {
      return this.comment;
   }

   public TypeDescriptor getTypeDescriptor() {
      return this.type;
   }

   public int getOrdinalPosition() {
      return this.position;
   }

   public TColumnDesc toTColumnDesc() {
      TColumnDesc tColumnDesc = new TColumnDesc();
      tColumnDesc.setColumnName(this.name);
      tColumnDesc.setComment(this.comment);
      tColumnDesc.setTypeDesc(this.type.toTTypeDesc());
      tColumnDesc.setPosition(this.position);
      return tColumnDesc;
   }

   public Type getType() {
      return this.type.getType();
   }

   public boolean isPrimitive() {
      return this.type.getType().isPrimitiveType();
   }

   public String getTypeName() {
      return this.type.getTypeName();
   }
}
