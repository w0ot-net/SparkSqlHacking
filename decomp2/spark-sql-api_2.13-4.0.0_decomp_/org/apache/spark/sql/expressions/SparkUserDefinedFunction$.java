package org.apache.spark.sql.expressions;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.ScalaReflection$;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoder;
import org.apache.spark.sql.types.DataType;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkUserDefinedFunction$ implements Serializable {
   public static final SparkUserDefinedFunction$ MODULE$ = new SparkUserDefinedFunction$();

   public Seq $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$4() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return scala.None..MODULE$;
   }

   public boolean $lessinit$greater$default$6() {
      return true;
   }

   public boolean $lessinit$greater$default$7() {
      return true;
   }

   public SparkUserDefinedFunction apply(final Object function, final TypeTags.TypeTag returnTypeTag, final Seq inputTypeTags) {
      AgnosticEncoder outputEncoder = ScalaReflection$.MODULE$.encoderFor(returnTypeTag);
      Seq inputEncoders = (Seq)inputTypeTags.map((tag) -> scala.util.Try..MODULE$.apply(() -> ScalaReflection$.MODULE$.encoderFor(tag)).toOption());
      DataType x$3 = outputEncoder.dataType();
      Option x$4 = scala.Option..MODULE$.apply(outputEncoder);
      boolean x$5 = outputEncoder.nullable();
      Option x$6 = this.apply$default$5();
      boolean x$7 = this.apply$default$7();
      return new SparkUserDefinedFunction(function, x$3, inputEncoders, x$4, x$6, x$5, x$7);
   }

   public SparkUserDefinedFunction apply(final Object function, final Seq inputEncoders, final AgnosticEncoder outputEncoder) {
      Seq x$2 = (Seq)inputEncoders.map((x) -> scala.Option..MODULE$.apply(x));
      Option x$3 = scala.Option..MODULE$.apply(outputEncoder);
      DataType x$4 = outputEncoder.dataType();
      boolean x$5 = outputEncoder.nullable();
      Option x$6 = this.apply$default$5();
      boolean x$7 = this.apply$default$7();
      return new SparkUserDefinedFunction(function, x$4, x$2, x$3, x$6, x$5, x$7);
   }

   public SparkUserDefinedFunction apply(final Object function, final DataType returnType, final int cardinality) {
      return new SparkUserDefinedFunction(function, returnType, (Seq)scala.package..MODULE$.Seq().fill(cardinality, () -> scala.None..MODULE$), scala.None..MODULE$, this.apply$default$5(), this.apply$default$6(), this.apply$default$7());
   }

   public Seq apply$default$3() {
      return .MODULE$;
   }

   public Option apply$default$4() {
      return scala.None..MODULE$;
   }

   public Option apply$default$5() {
      return scala.None..MODULE$;
   }

   public boolean apply$default$6() {
      return true;
   }

   public boolean apply$default$7() {
      return true;
   }

   public SparkUserDefinedFunction apply(final Object f, final DataType dataType, final Seq inputEncoders, final Option outputEncoder, final Option givenName, final boolean nullable, final boolean deterministic) {
      return new SparkUserDefinedFunction(f, dataType, inputEncoders, outputEncoder, givenName, nullable, deterministic);
   }

   public Option unapply(final SparkUserDefinedFunction x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(x$0.f(), x$0.dataType(), x$0.inputEncoders(), x$0.outputEncoder(), x$0.givenName(), BoxesRunTime.boxToBoolean(x$0.nullable()), BoxesRunTime.boxToBoolean(x$0.deterministic()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkUserDefinedFunction$.class);
   }

   private SparkUserDefinedFunction$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
