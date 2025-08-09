package org.apache.ivy.util.filter;

public class NotFilter implements Filter {
   private Filter op;

   public NotFilter(Filter op) {
      this.op = op;
   }

   public Filter getOp() {
      return this.op;
   }

   public boolean accept(Object o) {
      return !this.op.accept(o);
   }
}
