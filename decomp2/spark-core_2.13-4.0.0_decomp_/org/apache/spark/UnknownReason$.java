package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
public final class UnknownReason$ implements TaskFailedReason, Product, Serializable {
   public static final UnknownReason$ MODULE$ = new UnknownReason$();

   static {
      TaskFailedReason.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean countTowardsTaskFailures() {
      return TaskFailedReason.countTowardsTaskFailures$(this);
   }

   public String toErrorString() {
      return "UnknownReason";
   }

   public String productPrefix() {
      return "UnknownReason";
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
      return x$1 instanceof UnknownReason$;
   }

   public int hashCode() {
      return -1830061298;
   }

   public String toString() {
      return "UnknownReason";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnknownReason$.class);
   }

   private UnknownReason$() {
   }
}
