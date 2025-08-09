package org.apache.spark.ml.param;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.ml.util.Identifiable;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.Function1;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r4AAC\u0006\u0001-!AA\u0005\u0001B\u0001B\u0003%Q\u0005\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003*\u0011!!\u0004A!A!\u0002\u0013I\u0003\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000bq\u0002A\u0011A\"\t\u000b\u001d\u0003A\u0011\u0001%\t\u000bq\u0003A\u0011I/\t\u000b}\u0003A\u0011\t1\u0003\u001b%sG/\u0011:sCf\u0004\u0016M]1n\u0015\taQ\"A\u0003qCJ\fWN\u0003\u0002\u000f\u001f\u0005\u0011Q\u000e\u001c\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u00011C\u0001\u0001\u0018!\rA\u0012dG\u0007\u0002\u0017%\u0011!d\u0003\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u00049}\tS\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005q\u0011\u0013BA\u0012\u001e\u0005\rIe\u000e^\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0005a1\u0013BA\u0014\f\u0005\u0019\u0001\u0016M]1ng\u0006!a.Y7f!\tQ\u0013G\u0004\u0002,_A\u0011A&H\u0007\u0002[)\u0011a&F\u0001\u0007yI|w\u000e\u001e \n\u0005Aj\u0012A\u0002)sK\u0012,g-\u0003\u00023g\t11\u000b\u001e:j]\u001eT!\u0001M\u000f\u0002\u0007\u0011|7-A\u0004jgZ\u000bG.\u001b3\u0011\tq94$O\u0005\u0003qu\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005qQ\u0014BA\u001e\u001e\u0005\u001d\u0011un\u001c7fC:\fa\u0001P5oSRtD#\u0002 @\u0001\u0006\u0013\u0005C\u0001\r\u0001\u0011\u0015!S\u00011\u0001&\u0011\u0015AS\u00011\u0001*\u0011\u0015!T\u00011\u0001*\u0011\u0015)T\u00011\u00017)\u0011qD)\u0012$\t\u000b\u00112\u0001\u0019A\u0013\t\u000b!2\u0001\u0019A\u0015\t\u000bQ2\u0001\u0019A\u0015\u0002\u0003]$\"!\u0013'\u0011\u0007aQ5$\u0003\u0002L\u0017\tI\u0001+\u0019:b[B\u000b\u0017N\u001d\u0005\u0006\u001b\u001e\u0001\rAT\u0001\u0006m\u0006dW/\u001a\t\u0004\u001fR3V\"\u0001)\u000b\u0005E\u0013\u0016\u0001B;uS2T\u0011aU\u0001\u0005U\u00064\u0018-\u0003\u0002V!\n!A*[:u!\t9&,D\u0001Y\u0015\tI&+\u0001\u0003mC:<\u0017BA.Y\u0005\u001dIe\u000e^3hKJ\f!B[:p]\u0016s7m\u001c3f)\tIc\fC\u0003N\u0011\u0001\u00071$\u0001\u0006kg>tG)Z2pI\u0016$\"aG1\t\u000b\tL\u0001\u0019A\u0015\u0002\t)\u001cxN\u001c"
)
public class IntArrayParam extends Param {
   public ParamPair w(final List value) {
      return this.w(((IterableOnceOps).MODULE$.ListHasAsScala(value).asScala().map((x$9) -> BoxesRunTime.boxToInteger($anonfun$w$4(x$9)))).toArray(scala.reflect.ClassTag..MODULE$.Int()));
   }

   public String jsonEncode(final int[] value) {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq(), (x) -> $anonfun$jsonEncode$6(BoxesRunTime.unboxToInt(x))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public int[] jsonDecode(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      return (int[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Int(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   // $FF: synthetic method
   public static final int $anonfun$w$4(final Integer x$9) {
      return BoxesRunTime.unboxToInt(x$9);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$jsonEncode$6(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   public IntArrayParam(final Params parent, final String name, final String doc, final Function1 isValid) {
      super((Identifiable)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
   }

   public IntArrayParam(final Params parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
