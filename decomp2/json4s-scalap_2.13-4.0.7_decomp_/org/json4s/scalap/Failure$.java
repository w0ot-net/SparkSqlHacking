package org.json4s.scalap;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class Failure$ extends NoSuccess implements Product, Serializable {
   public static final Failure$ MODULE$ = new Failure$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Nothing error() {
      throw new ScalaSigParserError("No error");
   }

   public String productPrefix() {
      return "Failure";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Failure$;
   }

   public int hashCode() {
      return 578079082;
   }

   public String toString() {
      return "Failure";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Failure$.class);
   }

   private Failure$() {
   }
}
