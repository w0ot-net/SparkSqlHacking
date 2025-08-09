package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
public final class Success$ implements TaskEndReason, Product, Serializable {
   public static final Success$ MODULE$ = new Success$();

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
      return "Success";
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
      return x$1 instanceof Success$;
   }

   public int hashCode() {
      return -202516509;
   }

   public String toString() {
      return "Success";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Success$.class);
   }

   private Success$() {
   }
}
