package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
public final class Resubmitted$ implements TaskFailedReason, Product, Serializable {
   public static final Resubmitted$ MODULE$ = new Resubmitted$();

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
      return "Resubmitted (resubmitted due to lost executor)";
   }

   public String productPrefix() {
      return "Resubmitted";
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
      return x$1 instanceof Resubmitted$;
   }

   public int hashCode() {
      return 654527496;
   }

   public String toString() {
      return "Resubmitted";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Resubmitted$.class);
   }

   private Resubmitted$() {
   }
}
