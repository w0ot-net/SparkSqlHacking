package org.apache.spark.metrics;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class MappedPoolMemory$ extends MBeanExecutorMetricType implements Product, Serializable {
   public static final MappedPoolMemory$ MODULE$ = new MappedPoolMemory$();

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
      return "MappedPoolMemory";
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
      return x$1 instanceof MappedPoolMemory$;
   }

   public int hashCode() {
      return -950749456;
   }

   public String toString() {
      return "MappedPoolMemory";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MappedPoolMemory$.class);
   }

   private MappedPoolMemory$() {
      super("java.nio:type=BufferPool,name=mapped");
   }
}
