package shaded.parquet.it.unimi.dsi.fastutil;

public interface Stack {
   void push(Object var1);

   Object pop();

   boolean isEmpty();

   default Object top() {
      return this.peek(0);
   }

   default Object peek(int i) {
      throw new UnsupportedOperationException();
   }
}
