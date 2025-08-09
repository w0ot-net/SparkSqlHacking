package shaded.parquet.it.unimi.dsi.fastutil.floats;

import shaded.parquet.it.unimi.dsi.fastutil.Stack;

public interface FloatStack extends Stack {
   void push(float var1);

   float popFloat();

   float topFloat();

   float peekFloat(int var1);

   /** @deprecated */
   @Deprecated
   default void push(Float o) {
      this.push(o);
   }

   /** @deprecated */
   @Deprecated
   default Float pop() {
      return this.popFloat();
   }

   /** @deprecated */
   @Deprecated
   default Float top() {
      return this.topFloat();
   }

   /** @deprecated */
   @Deprecated
   default Float peek(int i) {
      return this.peekFloat(i);
   }
}
