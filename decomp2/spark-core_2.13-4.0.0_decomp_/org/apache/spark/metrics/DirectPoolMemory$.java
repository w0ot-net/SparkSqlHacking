package org.apache.spark.metrics;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class DirectPoolMemory$ extends MBeanExecutorMetricType implements Product, Serializable {
   public static final DirectPoolMemory$ MODULE$ = new DirectPoolMemory$();

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
      return "DirectPoolMemory";
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
      return x$1 instanceof DirectPoolMemory$;
   }

   public int hashCode() {
      return 1796303142;
   }

   public String toString() {
      return "DirectPoolMemory";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DirectPoolMemory$.class);
   }

   private DirectPoolMemory$() {
      super("java.nio:type=BufferPool,name=direct");
   }
}
