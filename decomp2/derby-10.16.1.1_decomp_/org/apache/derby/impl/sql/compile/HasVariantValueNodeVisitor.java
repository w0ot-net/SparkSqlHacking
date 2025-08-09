package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class HasVariantValueNodeVisitor implements Visitor {
   private boolean hasVariant;
   private int variantType;
   private boolean ignoreParameters;

   HasVariantValueNodeVisitor() {
      this.variantType = 0;
      this.ignoreParameters = false;
   }

   HasVariantValueNodeVisitor(int var1, boolean var2) {
      this.variantType = var1;
      this.ignoreParameters = var2;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof ValueNode) {
         if (this.ignoreParameters && ((ValueNode)var1).requiresTypeFromContext()) {
            return var1;
         }

         if (((ValueNode)var1).getOrderableVariantType() <= this.variantType) {
            this.hasVariant = true;
         }
      }

      return var1;
   }

   public boolean skipChildren(Visitable var1) {
      return false;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   public boolean stopTraversal() {
      return this.hasVariant;
   }

   boolean hasVariant() {
      return this.hasVariant;
   }
}
