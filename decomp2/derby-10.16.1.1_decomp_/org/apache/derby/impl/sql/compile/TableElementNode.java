package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;

class TableElementNode extends QueryTreeNode {
   public static final int AT_UNKNOWN = 0;
   public static final int AT_ADD_FOREIGN_KEY_CONSTRAINT = 1;
   public static final int AT_ADD_PRIMARY_KEY_CONSTRAINT = 2;
   public static final int AT_ADD_UNIQUE_CONSTRAINT = 3;
   public static final int AT_ADD_CHECK_CONSTRAINT = 4;
   public static final int AT_DROP_CONSTRAINT = 5;
   public static final int AT_MODIFY_COLUMN = 6;
   public static final int AT_DROP_COLUMN = 7;
   public static final int AT_MODIFY_CONSTRAINT = 8;
   String name;
   int elementType;

   TableElementNode(String var1, ContextManager var2) {
      super(var2);
      this.name = var1;
   }

   public String toString() {
      return "";
   }

   boolean hasPrimaryKeyConstraint() {
      return false;
   }

   boolean hasUniqueKeyConstraint() {
      return false;
   }

   boolean hasForeignKeyConstraint() {
      return false;
   }

   boolean hasCheckConstraint() {
      return false;
   }

   boolean hasConstraint() {
      return false;
   }

   String getName() {
      return this.name;
   }

   int getElementType() {
      if (this.hasForeignKeyConstraint()) {
         return 1;
      } else if (this.hasPrimaryKeyConstraint()) {
         return 2;
      } else if (this.hasUniqueKeyConstraint()) {
         return 3;
      } else if (this.hasCheckConstraint()) {
         return 4;
      } else if (this instanceof ConstraintDefinitionNode) {
         return 5;
      } else if (this instanceof ModifyColumnNode) {
         return ((ModifyColumnNode)this).kind == 4 ? 7 : 6;
      } else {
         return this.elementType;
      }
   }
}
