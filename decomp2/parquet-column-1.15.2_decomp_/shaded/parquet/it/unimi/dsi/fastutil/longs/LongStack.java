package shaded.parquet.it.unimi.dsi.fastutil.longs;

import shaded.parquet.it.unimi.dsi.fastutil.Stack;

public interface LongStack extends Stack {
   void push(long var1);

   long popLong();

   long topLong();

   long peekLong(int var1);

   /** @deprecated */
   @Deprecated
   default void push(Long o) {
      this.push(o);
   }

   /** @deprecated */
   @Deprecated
   default Long pop() {
      return this.popLong();
   }

   /** @deprecated */
   @Deprecated
   default Long top() {
      return this.topLong();
   }

   /** @deprecated */
   @Deprecated
   default Long peek(int i) {
      return this.peekLong(i);
   }
}
