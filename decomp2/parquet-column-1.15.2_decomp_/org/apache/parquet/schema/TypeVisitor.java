package org.apache.parquet.schema;

public interface TypeVisitor {
   void visit(GroupType var1);

   void visit(MessageType var1);

   void visit(PrimitiveType var1);
}
