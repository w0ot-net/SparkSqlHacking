package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class JObject$ implements Product, Serializable {
   public static final JObject$ MODULE$ = new JObject$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public JObject apply(final Seq fs) {
      return new JObject(fs.toList());
   }

   public JObject apply(final List obj) {
      return new JObject(obj);
   }

   public Option unapply(final JObject x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.obj()));
   }

   public String productPrefix() {
      return "JObject";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JObject$;
   }

   public int hashCode() {
      return -688738263;
   }

   public String toString() {
      return "JObject";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JObject$.class);
   }

   private JObject$() {
   }
}
