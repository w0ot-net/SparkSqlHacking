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
   bytes = "\u0006\u0005\t4AAC\u0006\u0001-!AA\u0005\u0001B\u0001B\u0003%Q\u0005\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003*\u0011!!\u0004A!A!\u0002\u0013I\u0003\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000bq\u0002A\u0011A\"\t\u000b\u001d\u0003A\u0011\u0001%\t\u000bm\u0003A\u0011\t/\t\u000by\u0003A\u0011I0\u0003!\u0011{WO\u00197f\u0003J\u0014\u0018-\u001f)be\u0006l'B\u0001\u0007\u000e\u0003\u0015\u0001\u0018M]1n\u0015\tqq\"\u0001\u0002nY*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u00019\u0002c\u0001\r\u001a75\t1\"\u0003\u0002\u001b\u0017\t)\u0001+\u0019:b[B\u0019AdH\u0011\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011Q!\u0011:sCf\u0004\"\u0001\b\u0012\n\u0005\rj\"A\u0002#pk\ndW-\u0001\u0004qCJ,g\u000e\u001e\t\u00031\u0019J!aJ\u0006\u0003\rA\u000b'/Y7t\u0003\u0011q\u0017-\\3\u0011\u0005)\ndBA\u00160!\taS$D\u0001.\u0015\tqS#\u0001\u0004=e>|GOP\u0005\u0003au\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001a4\u0005\u0019\u0019FO]5oO*\u0011\u0001'H\u0001\u0004I>\u001c\u0017aB5t-\u0006d\u0017\u000e\u001a\t\u00059]Z\u0012(\u0003\u00029;\tIa)\u001e8di&|g.\r\t\u00039iJ!aO\u000f\u0003\u000f\t{w\u000e\\3b]\u00061A(\u001b8jiz\"RAP A\u0003\n\u0003\"\u0001\u0007\u0001\t\u000b\u0011*\u0001\u0019A\u0013\t\u000b!*\u0001\u0019A\u0015\t\u000bQ*\u0001\u0019A\u0015\t\u000bU*\u0001\u0019\u0001\u001c\u0015\ty\"UI\u0012\u0005\u0006I\u0019\u0001\r!\n\u0005\u0006Q\u0019\u0001\r!\u000b\u0005\u0006i\u0019\u0001\r!K\u0001\u0002oR\u0011\u0011\n\u0014\t\u00041)[\u0012BA&\f\u0005%\u0001\u0016M]1n!\u0006L'\u000fC\u0003N\u000f\u0001\u0007a*A\u0003wC2,X\rE\u0002P)Zk\u0011\u0001\u0015\u0006\u0003#J\u000bA!\u001e;jY*\t1+\u0001\u0003kCZ\f\u0017BA+Q\u0005\u0011a\u0015n\u001d;\u0011\u0005]SV\"\u0001-\u000b\u0005e\u0013\u0016\u0001\u00027b]\u001eL!a\t-\u0002\u0015)\u001cxN\\#oG>$W\r\u0006\u0002*;\")Q\n\u0003a\u00017\u0005Q!n]8o\t\u0016\u001cw\u000eZ3\u0015\u0005m\u0001\u0007\"B1\n\u0001\u0004I\u0013\u0001\u00026t_:\u0004"
)
public class DoubleArrayParam extends Param {
   public ParamPair w(final List value) {
      return this.w(((IterableOnceOps).MODULE$.ListHasAsScala(value).asScala().map((x$5) -> BoxesRunTime.boxToDouble($anonfun$w$1(x$5)))).toArray(scala.reflect.ClassTag..MODULE$.Double()));
   }

   public String jsonEncode(final double[] value) {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq().map((valuex) -> $anonfun$jsonEncode$2(BoxesRunTime.unboxToDouble(valuex))), scala.Predef..MODULE$.$conforms()), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public double[] jsonDecode(final String json) {
      JValue var3 = org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      if (var3 instanceof JArray var4) {
         scala.collection.immutable.List values = var4.arr();
         return (double[])values.map((jValue) -> BoxesRunTime.boxToDouble($anonfun$jsonDecode$5(jValue))).toArray(scala.reflect.ClassTag..MODULE$.Double());
      } else {
         throw new IllegalArgumentException("Cannot decode " + json + " to Array[Double].");
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$w$1(final Double x$5) {
      return BoxesRunTime.unboxToDouble(x$5);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$jsonEncode$2(final double value) {
      return DoubleParam$.MODULE$.jValueEncode(value);
   }

   // $FF: synthetic method
   public static final double $anonfun$jsonDecode$5(final JValue jValue) {
      return DoubleParam$.MODULE$.jValueDecode(jValue);
   }

   public DoubleArrayParam(final Params parent, final String name, final String doc, final Function1 isValid) {
      super((Identifiable)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
   }

   public DoubleArrayParam(final Params parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
