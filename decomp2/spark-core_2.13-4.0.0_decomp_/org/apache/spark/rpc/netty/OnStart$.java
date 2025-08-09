package org.apache.spark.rpc.netty;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class OnStart$ implements InboxMessage, Product, Serializable {
   public static final OnStart$ MODULE$ = new OnStart$();

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
      return "OnStart";
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
      return x$1 instanceof OnStart$;
   }

   public int hashCode() {
      return 327758243;
   }

   public String toString() {
      return "OnStart";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OnStart$.class);
   }

   private OnStart$() {
   }
}
