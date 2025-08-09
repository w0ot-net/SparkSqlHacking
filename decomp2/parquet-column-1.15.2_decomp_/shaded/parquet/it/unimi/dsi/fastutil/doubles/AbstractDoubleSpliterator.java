package shaded.parquet.it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleSpliterator implements DoubleSpliterator {
   protected AbstractDoubleSpliterator() {
   }

   public final boolean tryAdvance(DoubleConsumer action) {
      return this.tryAdvance(action);
   }

   public final void forEachRemaining(DoubleConsumer action) {
      this.forEachRemaining(action);
   }
}
