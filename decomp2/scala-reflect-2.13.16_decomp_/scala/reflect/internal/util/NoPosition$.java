package scala.reflect.internal.util;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

public final class NoPosition$ extends UndefinedPosition implements Product, Serializable {
   public static final NoPosition$ MODULE$ = new NoPosition$();

   static {
      NoPosition$ var10000 = MODULE$;
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String productPrefix() {
      return "NoPosition";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof NoPosition$;
   }

   public int hashCode() {
      return -394684886;
   }

   public String toString() {
      return "NoPosition";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoPosition$.class);
   }

   private NoPosition$() {
   }
}
