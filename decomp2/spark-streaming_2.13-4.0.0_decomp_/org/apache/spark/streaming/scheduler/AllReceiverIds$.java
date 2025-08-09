package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class AllReceiverIds$ implements ReceiverTrackerLocalMessage, Product, Serializable {
   public static final AllReceiverIds$ MODULE$ = new AllReceiverIds$();

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
      return "AllReceiverIds";
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
      return x$1 instanceof AllReceiverIds$;
   }

   public int hashCode() {
      return 496997896;
   }

   public String toString() {
      return "AllReceiverIds";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AllReceiverIds$.class);
   }

   private AllReceiverIds$() {
   }
}
