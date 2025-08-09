package org.apache.spark.metrics;

import java.io.Serializable;
import org.apache.spark.memory.MemoryManager;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class OffHeapUnifiedMemory$ extends MemoryManagerExecutorMetricType implements Product, Serializable {
   public static final OffHeapUnifiedMemory$ MODULE$ = new OffHeapUnifiedMemory$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String productPrefix() {
      return "OffHeapUnifiedMemory";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof OffHeapUnifiedMemory$;
   }

   public int hashCode() {
      return 811092248;
   }

   public String toString() {
      return "OffHeapUnifiedMemory";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OffHeapUnifiedMemory$.class);
   }

   private OffHeapUnifiedMemory$() {
      super(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final long apply(final MemoryManager m) {
            return m.offHeapExecutionMemoryUsed() + m.offHeapStorageMemoryUsed();
         }
      });
   }
}
