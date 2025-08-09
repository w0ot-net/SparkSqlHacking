package org.apache.spark.repl;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;
import scala.tools.nsc.Settings;
import scala.tools.nsc.util.package.;

public final class SparkILoop$ {
   public static final SparkILoop$ MODULE$ = new SparkILoop$();

   public String run(final String code, final Settings sets) {
      return .MODULE$.stringFromStream((ostream) -> {
         $anonfun$run$1(code, sets, ostream);
         return BoxedUnit.UNIT;
      });
   }

   public String run(final List lines) {
      return this.run(lines.map((x$3) -> x$3 + "\n").mkString(), this.run$default$2());
   }

   public Settings run$default$2() {
      return new Settings();
   }

   // $FF: synthetic method
   public static final void $anonfun$run$1(final String code$1, final Settings sets$1, final OutputStream ostream) {
      scala.Console..MODULE$.withOut(ostream, (JFunction0.mcZ.sp)() -> {
         BufferedReader input = new BufferedReader(new StringReader(code$1));
         PrintWriter output = new PrintWriter(new OutputStreamWriter(ostream), true);
         SparkILoop repl = new SparkILoop(input, output);
         if (sets$1.classpath().isDefault()) {
            sets$1.classpath().value_$eq(scala.sys.package..MODULE$.props().apply("java.class.path"));
         }

         return repl.run(sets$1);
      });
   }

   private SparkILoop$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
