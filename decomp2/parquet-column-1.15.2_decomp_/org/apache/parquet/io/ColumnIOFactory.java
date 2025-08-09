package org.apache.parquet.io;

import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;
import org.apache.parquet.schema.TypeVisitor;

public class ColumnIOFactory {
   private final String createdBy;
   private final boolean validating;

   public ColumnIOFactory() {
      this((String)null, false);
   }

   public ColumnIOFactory(String createdBy) {
      this(createdBy, false);
   }

   public ColumnIOFactory(boolean validating) {
      this((String)null, validating);
   }

   public ColumnIOFactory(String createdBy, boolean validating) {
      this.createdBy = createdBy;
      this.validating = validating;
   }

   public MessageColumnIO getColumnIO(MessageType requestedSchema, MessageType fileSchema) {
      return this.getColumnIO(requestedSchema, fileSchema, true);
   }

   public MessageColumnIO getColumnIO(MessageType requestedSchema, MessageType fileSchema, boolean strict) {
      ColumnIOCreatorVisitor visitor = new ColumnIOCreatorVisitor(this.validating, requestedSchema, this.createdBy, strict);
      fileSchema.accept(visitor);
      return visitor.getColumnIO();
   }

   public MessageColumnIO getColumnIO(MessageType schema) {
      return this.getColumnIO(schema, schema);
   }

   private static class ColumnIOCreatorVisitor implements TypeVisitor {
      private MessageColumnIO columnIO;
      private GroupColumnIO current;
      private List leaves;
      private final boolean validating;
      private final MessageType requestedSchema;
      private final String createdBy;
      private int currentRequestedIndex;
      private Type currentRequestedType;
      private boolean strictTypeChecking;

      private ColumnIOCreatorVisitor(boolean validating, MessageType requestedSchema, String createdBy, boolean strictTypeChecking) {
         this.leaves = new ArrayList();
         this.validating = validating;
         this.requestedSchema = requestedSchema;
         this.createdBy = createdBy;
         this.strictTypeChecking = strictTypeChecking;
      }

      public void visit(MessageType messageType) {
         this.columnIO = new MessageColumnIO(this.requestedSchema, this.validating, this.createdBy);
         this.visitChildren(this.columnIO, messageType, this.requestedSchema);
         this.columnIO.setLevels();
         this.columnIO.setLeaves(this.leaves);
      }

      public void visit(GroupType groupType) {
         if (this.currentRequestedType.isPrimitive()) {
            this.incompatibleSchema(groupType, this.currentRequestedType);
         }

         GroupColumnIO newIO = new GroupColumnIO(groupType, this.current, this.currentRequestedIndex);
         this.current.add(newIO);
         this.visitChildren(newIO, groupType, this.currentRequestedType.asGroupType());
      }

      private void visitChildren(GroupColumnIO newIO, GroupType groupType, GroupType requestedGroupType) {
         GroupColumnIO oldIO = this.current;
         this.current = newIO;

         for(Type type : groupType.getFields()) {
            if (requestedGroupType.containsField(type.getName())) {
               this.currentRequestedIndex = requestedGroupType.getFieldIndex(type.getName());
               this.currentRequestedType = requestedGroupType.getType(this.currentRequestedIndex);
               if (this.currentRequestedType.getRepetition().isMoreRestrictiveThan(type.getRepetition())) {
                  this.incompatibleSchema(type, this.currentRequestedType);
               }

               type.accept(this);
            }
         }

         this.current = oldIO;
      }

      public void visit(PrimitiveType primitiveType) {
         if (!this.currentRequestedType.isPrimitive() || this.strictTypeChecking && this.currentRequestedType.asPrimitiveType().getPrimitiveTypeName() != primitiveType.getPrimitiveTypeName()) {
            this.incompatibleSchema(primitiveType, this.currentRequestedType);
         }

         PrimitiveColumnIO newIO = new PrimitiveColumnIO(primitiveType, this.current, this.currentRequestedIndex, this.leaves.size());
         this.current.add(newIO);
         this.leaves.add(newIO);
      }

      private void incompatibleSchema(Type fileType, Type requestedType) {
         throw new ParquetDecodingException("The requested schema is not compatible with the file schema. incompatible types: " + requestedType + " != " + fileType);
      }

      public MessageColumnIO getColumnIO() {
         return this.columnIO;
      }
   }
}
