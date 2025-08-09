package org.apache.spark.metrics;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import org.apache.spark.memory.MemoryManager;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class JVMOffHeapMemory$ implements SingleValueExecutorMetricType, Product, Serializable {
   public static final JVMOffHeapMemory$ MODULE$ = new JVMOffHeapMemory$();

   static {
      SingleValueExecutorMetricType.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq names() {
      return SingleValueExecutorMetricType.names$(this);
   }

   public long[] getMetricValues(final MemoryManager memoryManager) {
      return SingleValueExecutorMetricType.getMetricValues$(this, memoryManager);
   }

   public long getMetricValue(final MemoryManager memoryManager) {
      return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
   }

   public String productPrefix() {
      return "JVMOffHeapMemory";
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
      return x$1 instanceof JVMOffHeapMemory$;
   }

   public int hashCode() {
      return -1882797605;
   }

   public String toString() {
      return "JVMOffHeapMemory";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JVMOffHeapMemory$.class);
   }

   private JVMOffHeapMemory$() {
   }
}
