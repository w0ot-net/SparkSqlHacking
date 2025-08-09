package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ResubmitFailedStages$ implements DAGSchedulerEvent, Product, Serializable {
   public static final ResubmitFailedStages$ MODULE$ = new ResubmitFailedStages$();

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
      return "ResubmitFailedStages";
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
      return x$1 instanceof ResubmitFailedStages$;
   }

   public int hashCode() {
      return 1392879517;
   }

   public String toString() {
      return "ResubmitFailedStages";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResubmitFailedStages$.class);
   }

   private ResubmitFailedStages$() {
   }
}
