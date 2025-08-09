package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.Evolving;
import org.json4s.JInt;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.Option;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001\u0002\f\u0018\u0001\tB\u0001\"\u000e\u0001\u0003\u0006\u0004%\tA\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005o!A\u0001\t\u0001BC\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003C\u0011!1\u0005A!b\u0001\n\u00039\u0005\u0002\u0003)\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\rE\u0003A\u0011C\u000eS\u0011\u0019\t\u0006\u0001\"\u0005\u001a1\")!\f\u0001C\u0001m!)1\f\u0001C\u0001m!)A\f\u0001C!;\"1a\f\u0001C\u00013};a\u0001^\f\t\u0002e)hA\u0002\f\u0018\u0011\u0003Ib\u000fC\u0003R\u001d\u0011\u0005A\u0010C\u0004~\u001d\t\u0007I\u0011A!\t\ryt\u0001\u0015!\u0003C\u0011\u0019yh\u0002\"\u0001\u0002\u0002!I\u0011q\u0002\b\u0012\u0002\u0013\u0005\u0011\u0011\u0003\u0005\u000b\u0003Kq\u0011\u0013!C\t7\u0005E\u0001\"CA\u0014\u001d\u0005\u0005I\u0011BA\u0015\u00051\u0019\u0016N\\6Qe><'/Z:t\u0015\tA\u0012$A\u0005tiJ,\u0017-\\5oO*\u0011!dG\u0001\u0004gFd'B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0004\u0001M\u0019\u0001aI\u0015\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g!\tQ#G\u0004\u0002,a9\u0011AfL\u0007\u0002[)\u0011a&I\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019J!!M\u0013\u0002\u000fA\f7m[1hK&\u00111\u0007\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003c\u0015\n1\u0002Z3tGJL\u0007\u000f^5p]V\tq\u0007\u0005\u00029y9\u0011\u0011H\u000f\t\u0003Y\u0015J!aO\u0013\u0002\rA\u0013X\rZ3g\u0013\tidH\u0001\u0004TiJLgn\u001a\u0006\u0003w\u0015\nA\u0002Z3tGJL\u0007\u000f^5p]\u0002\nQB\\;n\u001fV$\b/\u001e;S_^\u001cX#\u0001\"\u0011\u0005\u0011\u001a\u0015B\u0001#&\u0005\u0011auN\\4\u0002\u001d9,XnT;uaV$(k\\<tA\u00059Q.\u001a;sS\u000e\u001cX#\u0001%\u0011\t%sugN\u0007\u0002\u0015*\u00111\nT\u0001\u0005kRLGNC\u0001N\u0003\u0011Q\u0017M^1\n\u0005=S%aA'ba\u0006AQ.\u001a;sS\u000e\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005'V3v\u000b\u0005\u0002U\u00015\tq\u0003C\u00036\u000f\u0001\u0007q\u0007C\u0003A\u000f\u0001\u0007!\tC\u0004G\u000fA\u0005\t\u0019\u0001%\u0015\u0005MK\u0006\"B\u001b\t\u0001\u00049\u0014\u0001\u00026t_:\f!\u0002\u001d:fiRL(j]8o\u0003!!xn\u0015;sS:<G#A\u001c\u0002\u0013)\u001cxN\u001c,bYV,W#\u00011\u0011\u0005\u0005TgB\u00012h\u001d\t\u0019WM\u0004\u0002-I&\t\u0001%\u0003\u0002g?\u00051!n]8oiML!\u0001[5\u0002\u000f)\u001bxN\\!T)*\u0011amH\u0005\u0003W2\u0014aA\u0013,bYV,'B\u00015jQ\t\u0001a\u000e\u0005\u0002pe6\t\u0001O\u0003\u0002r7\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0004(\u0001C#w_24\u0018N\\4\u0002\u0019MKgn\u001b)s_\u001e\u0014Xm]:\u0011\u0005Qs1c\u0001\b$oB\u0011\u0001p_\u0007\u0002s*\u0011!\u0010T\u0001\u0003S>L!aM=\u0015\u0003U\fq\u0003R#G\u0003VcEk\u0018(V\u001b~{U\u000b\u0016)V)~\u0013vjV*\u00021\u0011+e)Q+M)~sU+T0P+R\u0003V\u000bV0S\u001f^\u001b\u0006%A\u0003baBd\u0017\u0010F\u0004T\u0003\u0007\t)!!\u0004\t\u000bU\u0012\u0002\u0019A\u001c\t\r\u0001\u0013\u0002\u0019AA\u0004!\u0011!\u0013\u0011\u0002\"\n\u0007\u0005-QE\u0001\u0004PaRLwN\u001c\u0005\b\rJ\u0001\n\u00111\u0001I\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u001aTCAA\nU\rA\u0015QC\u0016\u0003\u0003/\u0001B!!\u0007\u0002\"5\u0011\u00111\u0004\u0006\u0005\u0003;\ty\"A\u0005v]\u000eDWmY6fI*\u0011\u0011/J\u0005\u0005\u0003G\tYBA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0016!\u0011\ti#a\r\u000e\u0005\u0005=\"bAA\u0019\u0019\u0006!A.\u00198h\u0013\u0011\t)$a\f\u0003\r=\u0013'.Z2u\u0001"
)
public class SinkProgress implements Serializable {
   private final String description;
   private final long numOutputRows;
   private final Map metrics;

   public static Map apply$default$3() {
      return SinkProgress$.MODULE$.apply$default$3();
   }

   public static SinkProgress apply(final String description, final Option numOutputRows, final Map metrics) {
      return SinkProgress$.MODULE$.apply(description, numOutputRows, metrics);
   }

   public static long DEFAULT_NUM_OUTPUT_ROWS() {
      return SinkProgress$.MODULE$.DEFAULT_NUM_OUTPUT_ROWS();
   }

   public String description() {
      return this.description;
   }

   public long numOutputRows() {
      return this.numOutputRows;
   }

   public Map metrics() {
      return this.metrics;
   }

   public String json() {
      return .MODULE$.compact(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String prettyJson() {
      return .MODULE$.pretty(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String toString() {
      return this.prettyJson();
   }

   public JValue jsonValue() {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("description"), new JString(this.description())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numOutputRows"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numOutputRows()))), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("metrics"), SafeJsonSerializer$.MODULE$.safeMapToJValue(this.metrics(), (s) -> new JString(s))));
   }

   public SinkProgress(final String description, final long numOutputRows, final Map metrics) {
      this.description = description;
      this.numOutputRows = numOutputRows;
      this.metrics = metrics;
   }

   public SinkProgress(final String description) {
      this(description, SinkProgress$.MODULE$.DEFAULT_NUM_OUTPUT_ROWS(), SinkProgress$.MODULE$.$lessinit$greater$default$3());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
