package org.apache.spark.ml.param;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.ml.util.Identifiable;
import org.json4s.Formats;
import scala.Function1;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3AAC\u0006\u0001-!AA\u0006\u0001B\u0001B\u0003%Q\u0006\u0003\u00051\u0001\t\u0005\t\u0015!\u0003\"\u0011!\t\u0004A!A!\u0002\u0013\t\u0003\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u000be\u0002A\u0011\u0001\u001e\t\u000be\u0002A\u0011\u0001!\t\u000b\u0011\u0003A\u0011A#\t\u000bM\u0003A\u0011\t+\t\u000bY\u0003A\u0011I,\u0003!M#(/\u001b8h\u0003J\u0014\u0018-\u001f)be\u0006l'B\u0001\u0007\u000e\u0003\u0015\u0001\u0018M]1n\u0015\tqq\"\u0001\u0002nY*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u00019\u0002c\u0001\r\u001a75\t1\"\u0003\u0002\u001b\u0017\t)\u0001+\u0019:b[B\u0019AdH\u0011\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011Q!\u0011:sCf\u0004\"AI\u0015\u000f\u0005\r:\u0003C\u0001\u0013\u001e\u001b\u0005)#B\u0001\u0014\u0016\u0003\u0019a$o\\8u}%\u0011\u0001&H\u0001\u0007!J,G-\u001a4\n\u0005)Z#AB*ue&twM\u0003\u0002);\u00051\u0001/\u0019:f]R\u0004\"\u0001\u0007\u0018\n\u0005=Z!A\u0002)be\u0006l7/\u0001\u0003oC6,\u0017a\u00013pG\u00069\u0011n\u001d,bY&$\u0007\u0003\u0002\u000f57YJ!!N\u000f\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u000f8\u0013\tATDA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)\u0015YD(\u0010 @!\tA\u0002\u0001C\u0003-\u000b\u0001\u0007Q\u0006C\u00031\u000b\u0001\u0007\u0011\u0005C\u00032\u000b\u0001\u0007\u0011\u0005C\u00033\u000b\u0001\u00071\u0007\u0006\u0003<\u0003\n\u001b\u0005\"\u0002\u0017\u0007\u0001\u0004i\u0003\"\u0002\u0019\u0007\u0001\u0004\t\u0003\"B\u0019\u0007\u0001\u0004\t\u0013!A<\u0015\u0005\u0019K\u0005c\u0001\rH7%\u0011\u0001j\u0003\u0002\n!\u0006\u0014\u0018-\u001c)bSJDQAS\u0004A\u0002-\u000bQA^1mk\u0016\u00042\u0001T)\"\u001b\u0005i%B\u0001(P\u0003\u0011)H/\u001b7\u000b\u0003A\u000bAA[1wC&\u0011!+\u0014\u0002\u0005\u0019&\u001cH/\u0001\u0006kg>tWI\\2pI\u0016$\"!I+\t\u000b)C\u0001\u0019A\u000e\u0002\u0015)\u001cxN\u001c#fG>$W\r\u0006\u0002\u001c1\")\u0011,\u0003a\u0001C\u0005!!n]8o\u0001"
)
public class StringArrayParam extends Param {
   public ParamPair w(final List value) {
      return this.w(.MODULE$.ListHasAsScala(value).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public String jsonEncode(final String[] value) {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq(), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public String[] jsonDecode(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      return (String[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.classType(String.class), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public StringArrayParam(final Params parent, final String name, final String doc, final Function1 isValid) {
      super((Identifiable)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
   }

   public StringArrayParam(final Params parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
