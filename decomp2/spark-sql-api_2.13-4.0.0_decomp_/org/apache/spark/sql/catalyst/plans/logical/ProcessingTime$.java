package org.apache.spark.sql.catalyst.plans.logical;

import java.io.Serializable;
import org.apache.spark.sql.streaming.TimeMode;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ProcessingTime$ extends TimeMode implements Product, Serializable {
   public static final ProcessingTime$ MODULE$ = new ProcessingTime$();

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
      return "ProcessingTime";
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
      return x$1 instanceof ProcessingTime$;
   }

   public int hashCode() {
      return -806027104;
   }

   public String toString() {
      return "ProcessingTime";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProcessingTime$.class);
   }

   private ProcessingTime$() {
   }
}
