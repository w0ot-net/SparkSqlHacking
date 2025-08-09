package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class GetAllReceiverInfo$ implements ReceiverTrackerLocalMessage, Product, Serializable {
   public static final GetAllReceiverInfo$ MODULE$ = new GetAllReceiverInfo$();

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
      return "GetAllReceiverInfo";
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
      return x$1 instanceof GetAllReceiverInfo$;
   }

   public int hashCode() {
      return 602285416;
   }

   public String toString() {
      return "GetAllReceiverInfo";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GetAllReceiverInfo$.class);
   }

   private GetAllReceiverInfo$() {
   }
}
