package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Stable
public final class NullType$ extends NullType implements Product, Serializable {
   public static final NullType$ MODULE$ = new NullType$();

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
      return "NullType";
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
      return x$1 instanceof NullType$;
   }

   public int hashCode() {
      return -1841688191;
   }

   public String toString() {
      return "NullType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NullType$.class);
   }

   private NullType$() {
   }
}
