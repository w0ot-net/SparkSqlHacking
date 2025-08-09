package org.apache.spark.status.protobuf;

import java.util.Map;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;

public final class Utils$ {
   public static final Utils$ MODULE$ = new Utils$();

   public Option getOptional(final boolean condition, final Function0 result) {
      return (Option)(condition ? new Some(result.apply()) : .MODULE$);
   }

   public void setStringField(final String input, final Function1 f) {
      if (input != null) {
         f.apply(input);
      }
   }

   public String getStringField(final boolean condition, final Function0 result) {
      return condition ? (String)result.apply() : null;
   }

   public void setJMapField(final Map input, final Function1 putAllFunc) {
      if (input != null && !input.isEmpty()) {
         putAllFunc.apply(input);
      }
   }

   private Utils$() {
   }
}
