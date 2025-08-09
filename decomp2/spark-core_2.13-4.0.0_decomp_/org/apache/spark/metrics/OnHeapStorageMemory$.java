package org.apache.spark.metrics;

import java.io.Serializable;
import org.apache.spark.memory.MemoryManager;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class OnHeapStorageMemory$ extends MemoryManagerExecutorMetricType implements Product, Serializable {
   public static final OnHeapStorageMemory$ MODULE$ = new OnHeapStorageMemory$();

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
      return "OnHeapStorageMemory";
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
      return x$1 instanceof OnHeapStorageMemory$;
   }

   public int hashCode() {
      return 69064849;
   }

   public String toString() {
      return "OnHeapStorageMemory";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OnHeapStorageMemory$.class);
   }

   private OnHeapStorageMemory$() {
      super(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final long apply(final MemoryManager x$4) {
            return x$4.onHeapStorageMemoryUsed();
         }
      });
   }
}
