package org.json4s;

import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class JNull$ extends JValue {
   public static final JNull$ MODULE$ = new JNull$();

   public Null values() {
      return null;
   }

   public String productPrefix() {
      return "JNull";
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
      return x$1 instanceof JNull$;
   }

   public int hashCode() {
      return 70780145;
   }

   public String toString() {
      return "JNull";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JNull$.class);
   }

   private JNull$() {
   }
}
