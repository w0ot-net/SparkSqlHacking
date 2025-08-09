package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
public final class JobSucceeded$ implements JobResult, Product, Serializable {
   public static final JobSucceeded$ MODULE$ = new JobSucceeded$();

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
      return "JobSucceeded";
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
      return x$1 instanceof JobSucceeded$;
   }

   public int hashCode() {
      return -59670716;
   }

   public String toString() {
      return "JobSucceeded";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobSucceeded$.class);
   }

   private JobSucceeded$() {
   }
}
