package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class CallSite$ implements Serializable {
   public static final CallSite$ MODULE$ = new CallSite$();
   private static final String SHORT_FORM = "callSite.short";
   private static final String LONG_FORM = "callSite.long";
   private static final CallSite empty = new CallSite("", "");

   public String SHORT_FORM() {
      return SHORT_FORM;
   }

   public String LONG_FORM() {
      return LONG_FORM;
   }

   public CallSite empty() {
      return empty;
   }

   public CallSite apply(final String shortForm, final String longForm) {
      return new CallSite(shortForm, longForm);
   }

   public Option unapply(final CallSite x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.shortForm(), x$0.longForm())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CallSite$.class);
   }

   private CallSite$() {
   }
}
