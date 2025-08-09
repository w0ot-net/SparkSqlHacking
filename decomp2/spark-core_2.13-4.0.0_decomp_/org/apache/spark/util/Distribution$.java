package org.apache.spark.util;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Iterable;
import scala.runtime.java8.JFunction1;

public final class Distribution$ {
   public static final Distribution$ MODULE$ = new Distribution$();

   public Option apply(final Iterable data) {
      return (Option)(data.size() > 0 ? new Some(new Distribution(data)) : .MODULE$);
   }

   public void showQuantiles(final PrintStream out, final Iterable quantiles) {
      out.println("min\t25%\t50%\t75%\tmax");
      quantiles.foreach((JFunction1.mcVD.sp)(q) -> out.print(q + "\t"));
      out.println();
   }

   public PrintStream showQuantiles$default$1() {
      return System.out;
   }

   private Distribution$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
