package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import shaded.parquet.it.unimi.dsi.fastutil.Stack;

public interface BooleanStack extends Stack {
   void push(boolean var1);

   boolean popBoolean();

   boolean topBoolean();

   boolean peekBoolean(int var1);

   /** @deprecated */
   @Deprecated
   default void push(Boolean o) {
      this.push(o);
   }

   /** @deprecated */
   @Deprecated
   default Boolean pop() {
      return this.popBoolean();
   }

   /** @deprecated */
   @Deprecated
   default Boolean top() {
      return this.topBoolean();
   }

   /** @deprecated */
   @Deprecated
   default Boolean peek(int i) {
      return this.peekBoolean(i);
   }
}
