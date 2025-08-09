package org.apache.spark.ml.param;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.ml.util.Identifiable;
import org.json4s.JArray;
import org.json4s.JValue;
import scala.Function1;
import scala.collection.IterableOnceOps;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114AAC\u0006\u0001-!AQ\u0005\u0001B\u0001B\u0003%a\u0005\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003+\u0011!)\u0004A!A!\u0002\u0013Q\u0003\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001c\t\u000bu\u0002A\u0011\u0001 \t\u000bu\u0002A\u0011\u0001#\t\u000b!\u0003A\u0011A%\t\u000bu\u0003A\u0011\t0\t\u000b\u0001\u0004A\u0011I1\u0003+\u0011{WO\u00197f\u0003J\u0014\u0018-_!se\u0006L\b+\u0019:b[*\u0011A\"D\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003\u001d=\t!!\u001c7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001/A\u0019\u0001$G\u000e\u000e\u0003-I!AG\u0006\u0003\u000bA\u000b'/Y7\u0011\u0007qy\u0012%D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0015\t%O]1z!\rarD\t\t\u00039\rJ!\u0001J\u000f\u0003\r\u0011{WO\u00197f\u0003\u0019\u0001\u0018M]3oiB\u0011\u0001dJ\u0005\u0003Q-\u0011a\u0001U1sC6\u001c\u0018\u0001\u00028b[\u0016\u0004\"a\u000b\u001a\u000f\u00051\u0002\u0004CA\u0017\u001e\u001b\u0005q#BA\u0018\u0016\u0003\u0019a$o\\8u}%\u0011\u0011'H\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u00022;\u0005\u0019Am\\2\u0002\u000f%\u001ch+\u00197jIB!A\u0004O\u000e;\u0013\tITDA\u0005Gk:\u001cG/[8ocA\u0011AdO\u0005\u0003yu\u0011qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0006\u007f\u0001\u000b%i\u0011\t\u00031\u0001AQ!J\u0003A\u0002\u0019BQ!K\u0003A\u0002)BQ!N\u0003A\u0002)BQAN\u0003A\u0002]\"BaP#G\u000f\")QE\u0002a\u0001M!)\u0011F\u0002a\u0001U!)QG\u0002a\u0001U\u0005\tq\u000f\u0006\u0002K\u001bB\u0019\u0001dS\u000e\n\u00051[!!\u0003)be\u0006l\u0007+Y5s\u0011\u0015qu\u00011\u0001P\u0003\u00151\u0018\r\\;f!\r\u0001VkV\u0007\u0002#*\u0011!kU\u0001\u0005kRLGNC\u0001U\u0003\u0011Q\u0017M^1\n\u0005Y\u000b&\u0001\u0002'jgR\u00042\u0001U+Y!\tIF,D\u0001[\u0015\tY6+\u0001\u0003mC:<\u0017B\u0001\u0013[\u0003)Q7o\u001c8F]\u000e|G-\u001a\u000b\u0003U}CQA\u0014\u0005A\u0002m\t!B[:p]\u0012+7m\u001c3f)\tY\"\rC\u0003d\u0013\u0001\u0007!&\u0001\u0003kg>t\u0007"
)
public class DoubleArrayArrayParam extends Param {
   public ParamPair w(final List value) {
      return this.w(((IterableOnceOps).MODULE$.ListHasAsScala(value).asScala().map((x$6) -> (double[])((IterableOnceOps).MODULE$.ListHasAsScala(x$6).asScala().map((x$7) -> BoxesRunTime.boxToDouble($anonfun$w$3(x$7)))).toArray(scala.reflect.ClassTag..MODULE$.Double()))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE))));
   }

   public String jsonEncode(final double[][] value) {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq().map((x$8) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$8).toImmutableArraySeq().map((value) -> $anonfun$jsonEncode$4(BoxesRunTime.unboxToDouble(value)))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public double[][] jsonDecode(final String json) {
      JValue var3 = org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      if (var3 instanceof JArray var4) {
         scala.collection.immutable.List values = var4.arr();
         return (double[][])values.map((x0$1) -> {
            if (x0$1 instanceof JArray var4) {
               scala.collection.immutable.List values = var4.arr();
               return (double[])values.map((jValue) -> BoxesRunTime.boxToDouble($anonfun$jsonDecode$7(jValue))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            } else {
               throw new IllegalArgumentException("Cannot decode " + json + " to Array[Array[Double]].");
            }
         }).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      } else {
         throw new IllegalArgumentException("Cannot decode " + json + " to Array[Array[Double]].");
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$w$3(final Double x$7) {
      return BoxesRunTime.unboxToDouble(x$7);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$jsonEncode$4(final double value) {
      return DoubleParam$.MODULE$.jValueEncode(value);
   }

   // $FF: synthetic method
   public static final double $anonfun$jsonDecode$7(final JValue jValue) {
      return DoubleParam$.MODULE$.jValueDecode(jValue);
   }

   public DoubleArrayArrayParam(final Params parent, final String name, final String doc, final Function1 isValid) {
      super((Identifiable)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE))));
   }

   public DoubleArrayArrayParam(final Params parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
