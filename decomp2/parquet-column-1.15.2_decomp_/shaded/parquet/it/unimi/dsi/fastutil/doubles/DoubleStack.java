package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import shaded.parquet.it.unimi.dsi.fastutil.Stack;

public interface DoubleStack extends Stack {
   void push(double var1);

   double popDouble();

   double topDouble();

   double peekDouble(int var1);

   /** @deprecated */
   @Deprecated
   default void push(Double o) {
      this.push(o);
   }

   /** @deprecated */
   @Deprecated
   default Double pop() {
      return this.popDouble();
   }

   /** @deprecated */
   @Deprecated
   default Double top() {
      return this.topDouble();
   }

   /** @deprecated */
   @Deprecated
   default Double peek(int i) {
      return this.peekDouble(i);
   }
}
