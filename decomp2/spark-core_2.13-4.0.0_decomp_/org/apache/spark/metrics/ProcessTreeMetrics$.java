package org.apache.spark.metrics;

import java.io.Serializable;
import org.apache.spark.executor.ProcfsMetrics;
import org.apache.spark.executor.ProcfsMetricsGetter$;
import org.apache.spark.memory.MemoryManager;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class ProcessTreeMetrics$ implements ExecutorMetricType, Product, Serializable {
   public static final ProcessTreeMetrics$ MODULE$ = new ProcessTreeMetrics$();
   private static final Seq names;

   static {
      Product.$init$(MODULE$);
      names = new .colon.colon("ProcessTreeJVMVMemory", new .colon.colon("ProcessTreeJVMRSSMemory", new .colon.colon("ProcessTreePythonVMemory", new .colon.colon("ProcessTreePythonRSSMemory", new .colon.colon("ProcessTreeOtherVMemory", new .colon.colon("ProcessTreeOtherRSSMemory", scala.collection.immutable.Nil..MODULE$))))));
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq names() {
      return names;
   }

   public long[] getMetricValues(final MemoryManager memoryManager) {
      ProcfsMetrics allMetrics = ProcfsMetricsGetter$.MODULE$.pTreeInfo().computeAllMetrics();
      long[] processTreeMetrics = new long[this.names().length()];
      processTreeMetrics[0] = allMetrics.jvmVmemTotal();
      processTreeMetrics[1] = allMetrics.jvmRSSTotal();
      processTreeMetrics[2] = allMetrics.pythonVmemTotal();
      processTreeMetrics[3] = allMetrics.pythonRSSTotal();
      processTreeMetrics[4] = allMetrics.otherVmemTotal();
      processTreeMetrics[5] = allMetrics.otherRSSTotal();
      return processTreeMetrics;
   }

   public String productPrefix() {
      return "ProcessTreeMetrics";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProcessTreeMetrics$;
   }

   public int hashCode() {
      return 1480946038;
   }

   public String toString() {
      return "ProcessTreeMetrics";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProcessTreeMetrics$.class);
   }

   private ProcessTreeMetrics$() {
   }
}
