package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class EmptyTerm$ implements Term, Product, Serializable {
   public static final EmptyTerm$ MODULE$ = new EmptyTerm$();

   static {
      Term.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Terms asTerms() {
      return Term.asTerms$(this);
   }

   public Term add(final Term other) {
      return Term.add$(this, other);
   }

   public Term subtract(final Term other) {
      return Term.subtract$(this, other);
   }

   public Term interact(final Term other) {
      return Term.interact$(this, other);
   }

   public String productPrefix() {
      return "EmptyTerm";
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
      return x$1 instanceof EmptyTerm$;
   }

   public int hashCode() {
      return 583987001;
   }

   public String toString() {
      return "EmptyTerm";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EmptyTerm$.class);
   }

   private EmptyTerm$() {
   }
}
