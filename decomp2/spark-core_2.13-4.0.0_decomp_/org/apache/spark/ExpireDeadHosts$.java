package org.apache.spark;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ExpireDeadHosts$ implements Product, Serializable {
   public static final ExpireDeadHosts$ MODULE$ = new ExpireDeadHosts$();

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
      return "ExpireDeadHosts";
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
      return x$1 instanceof ExpireDeadHosts$;
   }

   public int hashCode() {
      return 1613438792;
   }

   public String toString() {
      return "ExpireDeadHosts";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExpireDeadHosts$.class);
   }

   private ExpireDeadHosts$() {
   }
}
