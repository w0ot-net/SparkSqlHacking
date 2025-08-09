package org.apache.parquet.schema;

public class TypeUtil {
   public static void checkValidWriteSchema(GroupType schema) {
      schema.accept(new TypeVisitor() {
         public void visit(GroupType groupType) {
            if (groupType.getFieldCount() <= 0) {
               throw new InvalidSchemaException("Cannot write a schema with an empty group: " + groupType);
            } else {
               for(Type type : groupType.getFields()) {
                  type.accept(this);
               }

            }
         }

         public void visit(MessageType messageType) {
            this.visit((GroupType)messageType);
         }

         public void visit(PrimitiveType primitiveType) {
         }
      });
   }
}
