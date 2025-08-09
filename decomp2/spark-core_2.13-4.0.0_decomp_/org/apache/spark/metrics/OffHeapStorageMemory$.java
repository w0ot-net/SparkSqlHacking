package org.apache.spark.metrics;

import java.io.Serializable;
import org.apache.spark.memory.MemoryManager;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class OffHeapStorageMemory$ extends MemoryManagerExecutorMetricType implements Product, Serializable {
   public static final OffHeapStorageMemory$ MODULE$ = new OffHeapStorageMemory$();

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
      return "OffHeapStorageMemory";
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
      return x$1 instanceof OffHeapStorageMemory$;
   }

   public int hashCode() {
      return -724650175;
   }

   public String toString() {
      return "OffHeapStorageMemory";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OffHeapStorageMemory$.class);
   }

   private OffHeapStorageMemory$() {
      super(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final long apply(final MemoryManager x$5) {
            return x$5.offHeapStorageMemoryUsed();
         }
      });
   }
}
