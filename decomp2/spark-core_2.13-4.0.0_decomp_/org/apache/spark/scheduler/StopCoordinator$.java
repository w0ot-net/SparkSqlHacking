package org.apache.spark.scheduler;

import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class StopCoordinator$ implements OutputCommitCoordinationMessage, Product {
   public static final StopCoordinator$ MODULE$ = new StopCoordinator$();

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
      return "StopCoordinator";
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
      return x$1 instanceof StopCoordinator$;
   }

   public int hashCode() {
      return 371711406;
   }

   public String toString() {
      return "StopCoordinator";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StopCoordinator$.class);
   }

   private StopCoordinator$() {
   }
}
