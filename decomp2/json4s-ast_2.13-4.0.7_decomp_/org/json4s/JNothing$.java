package org.json4s;

import scala.None;
import scala.None.;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class JNothing$ extends JValue {
   public static final JNothing$ MODULE$ = new JNothing$();

   public None values() {
      return .MODULE$;
   }

   public String productPrefix() {
      return "JNothing";
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
      return x$1 instanceof JNothing$;
   }

   public int hashCode() {
      return -382044125;
   }

   public String toString() {
      return "JNothing";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JNothing$.class);
   }

   private JNothing$() {
   }
}
