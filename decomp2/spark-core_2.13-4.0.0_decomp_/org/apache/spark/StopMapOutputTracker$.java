package org.apache.spark;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class StopMapOutputTracker$ implements MapOutputTrackerMessage, Product, Serializable {
   public static final StopMapOutputTracker$ MODULE$ = new StopMapOutputTracker$();

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
      return "StopMapOutputTracker";
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
      return x$1 instanceof StopMapOutputTracker$;
   }

   public int hashCode() {
      return -174801091;
   }

   public String toString() {
      return "StopMapOutputTracker";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StopMapOutputTracker$.class);
   }

   private StopMapOutputTracker$() {
   }
}
