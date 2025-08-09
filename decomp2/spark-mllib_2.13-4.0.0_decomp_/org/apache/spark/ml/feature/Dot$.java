package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class Dot$ implements InteractableTerm, Product, Serializable {
   public static final Dot$ MODULE$ = new Dot$();

   static {
      Term.$init$(MODULE$);
      InteractableTerm.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ColumnInteraction asInteraction() {
      return InteractableTerm.asInteraction$(this);
   }

   public Term interact(final Term other) {
      return InteractableTerm.interact$(this, other);
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

   public String productPrefix() {
      return "Dot";
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
      return x$1 instanceof Dot$;
   }

   public int hashCode() {
      return 68905;
   }

   public String toString() {
      return "Dot";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Dot$.class);
   }

   private Dot$() {
   }
}
