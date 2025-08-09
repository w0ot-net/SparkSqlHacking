package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class NoConstraint$ implements StringConstraint, Product, Serializable {
   public static final NoConstraint$ MODULE$ = new NoConstraint$();

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
      return "NoConstraint";
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
      return x$1 instanceof NoConstraint$;
   }

   public int hashCode() {
      return 1388500638;
   }

   public String toString() {
      return "NoConstraint";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoConstraint$.class);
   }

   private NoConstraint$() {
   }
}
