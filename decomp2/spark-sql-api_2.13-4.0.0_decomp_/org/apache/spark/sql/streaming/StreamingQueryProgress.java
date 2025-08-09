package org.apache.spark.sql.streaming;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.UUID;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.json4s.JArray;
import org.json4s.JInt;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\t-a\u0001\u0002\u0015*\u0001QB\u0001b\u0012\u0001\u0003\u0006\u0004%\t\u0001\u0013\u0005\t#\u0002\u0011\t\u0011)A\u0005\u0013\"A!\u000b\u0001BC\u0002\u0013\u0005\u0001\n\u0003\u0005T\u0001\t\u0005\t\u0015!\u0003J\u0011!!\u0006A!b\u0001\n\u0003)\u0006\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0011\u0002,\t\u0011}\u0003!Q1A\u0005\u0002UC\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\tC\u0002\u0011)\u0019!C\u0001E\"Aa\r\u0001B\u0001B\u0003%1\r\u0003\u0005h\u0001\t\u0015\r\u0011\"\u0001c\u0011!A\u0007A!A!\u0002\u0013\u0019\u0007\u0002C5\u0001\u0005\u000b\u0007I\u0011\u00016\t\u0011M\u0004!\u0011!Q\u0001\n-D\u0001\u0002\u001e\u0001\u0003\u0006\u0004%\t!\u001e\u0005\to\u0002\u0011\t\u0011)A\u0005m\"A\u0001\u0010\u0001BC\u0002\u0013\u0005\u0011\u0010C\u0005\u0002\u0004\u0001\u0011\t\u0011)A\u0005u\"Q\u0011Q\u0001\u0001\u0003\u0006\u0004%\t!a\u0002\t\u0015\u0005E\u0001A!A!\u0002\u0013\tI\u0001\u0003\u0006\u0002\u0014\u0001\u0011)\u0019!C\u0001\u0003+A!\"!\b\u0001\u0005\u0003\u0005\u000b\u0011BA\f\u0011)\ty\u0002\u0001BC\u0002\u0013\u0005\u0011\u0011\u0005\u0005\u000b\u0003[\u0001!\u0011!Q\u0001\n\u0005\r\u0002\u0002CA\u0018\u0001\u0011\u0005Q&!\r\t\r\u0005}\u0004\u0001\"\u0001c\u0011\u001d\t\t\t\u0001C\u0001\u0003\u0007Cq!a#\u0001\t\u0003\t\u0019\t\u0003\u0004\u0002\u000e\u0002!\t!\u0016\u0005\u0007\u0003\u001f\u0003A\u0011A+\t\u000f\u0005E\u0005\u0001\"\u0011\u0002\u0014\"A\u0011Q\u0013\u0001\u0005\u0002-\n9j\u0002\u0005\u0002@&B\t!LAa\r\u001dA\u0013\u0006#\u0001.\u0003\u0007Dq!a\f#\t\u0003\ty\r\u0003\u0005\u0002R\n\u0002\u000b\u0011BAj\u0011!\t\u0019P\tC\u0001[\u0005U\b\u0002CA~E\u0011\u0005Q&!@\t\u0013\t\u0005!%!A\u0005\n\t\r!AF*ue\u0016\fW.\u001b8h#V,'/\u001f)s_\u001e\u0014Xm]:\u000b\u0005)Z\u0013!C:ue\u0016\fW.\u001b8h\u0015\taS&A\u0002tc2T!AL\u0018\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\n\u0014AB1qC\u000eDWMC\u00013\u0003\ry'oZ\u0002\u0001'\r\u0001Qg\u000f\t\u0003mej\u0011a\u000e\u0006\u0002q\u0005)1oY1mC&\u0011!h\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005q\"eBA\u001fC\u001d\tq\u0014)D\u0001@\u0015\t\u00015'\u0001\u0004=e>|GOP\u0005\u0002q%\u00111iN\u0001\ba\u0006\u001c7.Y4f\u0013\t)eI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Do\u0005\u0011\u0011\u000eZ\u000b\u0002\u0013B\u0011!jT\u0007\u0002\u0017*\u0011A*T\u0001\u0005kRLGNC\u0001O\u0003\u0011Q\u0017M^1\n\u0005A[%\u0001B+V\u0013\u0012\u000b1!\u001b3!\u0003\u0015\u0011XO\\%e\u0003\u0019\u0011XO\\%eA\u0005!a.Y7f+\u00051\u0006CA,\\\u001d\tA\u0016\f\u0005\u0002?o%\u0011!lN\u0001\u0007!J,G-\u001a4\n\u0005qk&AB*ue&twM\u0003\u0002[o\u0005)a.Y7fA\u0005IA/[7fgR\fW\u000e]\u0001\u000bi&lWm\u001d;b[B\u0004\u0013a\u00022bi\u000eD\u0017\nZ\u000b\u0002GB\u0011a\u0007Z\u0005\u0003K^\u0012A\u0001T8oO\u0006A!-\u0019;dQ&#\u0007%A\u0007cCR\u001c\u0007\u000eR;sCRLwN\\\u0001\u000fE\u0006$8\r\u001b#ve\u0006$\u0018n\u001c8!\u0003)!WO]1uS>tWj]\u000b\u0002WB!!\n\u001c,o\u0013\ti7JA\u0002NCB\u0004\"a\u001c:\u000e\u0003AT!!]'\u0002\t1\fgnZ\u0005\u0003KB\f1\u0002Z;sCRLwN\\'tA\u0005IQM^3oiRKW.Z\u000b\u0002mB!!\n\u001c,W\u0003))g/\u001a8u)&lW\rI\u0001\u000fgR\fG/Z(qKJ\fGo\u001c:t+\u0005Q\bc\u0001\u001c|{&\u0011Ap\u000e\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003}~l\u0011!K\u0005\u0004\u0003\u0003I#!F*uCR,w\n]3sCR|'\u000f\u0015:pOJ,7o]\u0001\u0010gR\fG/Z(qKJ\fGo\u001c:tA\u000591o\\;sG\u0016\u001cXCAA\u0005!\u0011140a\u0003\u0011\u0007y\fi!C\u0002\u0002\u0010%\u0012abU8ve\u000e,\u0007K]8he\u0016\u001c8/\u0001\u0005t_V\u00148-Z:!\u0003\u0011\u0019\u0018N\\6\u0016\u0005\u0005]\u0001c\u0001@\u0002\u001a%\u0019\u00111D\u0015\u0003\u0019MKgn\u001b)s_\u001e\u0014Xm]:\u0002\u000bMLgn\u001b\u0011\u0002\u001f=\u00147/\u001a:wK\u0012lU\r\u001e:jGN,\"!a\t\u0011\u000b)cg+!\n\u0011\t\u0005\u001d\u0012\u0011F\u0007\u0002W%\u0019\u00111F\u0016\u0003\u0007I{w/\u0001\tpEN,'O^3e\u001b\u0016$(/[2tA\u00051A(\u001b8jiz\"\"$a\r\u00026\u0005]\u0012\u0011HA\u001e\u0003{\ty$!\u0011\u0002D\u0005\u0015\u0013qIA%\u0003\u0017\u0002\"A \u0001\t\u000b\u001dK\u0002\u0019A%\t\u000bIK\u0002\u0019A%\t\u000bQK\u0002\u0019\u0001,\t\u000b}K\u0002\u0019\u0001,\t\u000b\u0005L\u0002\u0019A2\t\u000b\u001dL\u0002\u0019A2\t\u000b%L\u0002\u0019A6\t\u000bQL\u0002\u0019\u0001<\t\u000baL\u0002\u0019\u0001>\t\u000f\u0005\u0015\u0011\u00041\u0001\u0002\n!9\u00111C\rA\u0002\u0005]\u0001bBA\u00103\u0001\u0007\u00111\u0005\u0015\t\u0003\u0017\ny%a\u001b\u0002nA!\u0011\u0011KA4\u001b\t\t\u0019F\u0003\u0003\u0002V\u0005]\u0013AC1o]>$\u0018\r^5p]*!\u0011\u0011LA.\u0003!!\u0017\r^1cS:$'\u0002BA/\u0003?\nqA[1dWN|gN\u0003\u0003\u0002b\u0005\r\u0014!\u00034bgR,'\u000f_7m\u0015\t\t)'A\u0002d_6LA!!\u001b\u0002T\ty!j]8o\t\u0016\u001cXM]5bY&TX-A\u0005d_:$XM\u001c;Bg\u000e\u0012\u0011q\u000e\t\u0005\u0003c\nY(\u0004\u0002\u0002t)!\u0011QOA<\u0003-)\u0007\u0010\u001d:fgNLwN\\:\u000b\u0007\u0005e4&\u0001\u0005dCR\fG._:u\u0013\u0011\ti(a\u001d\u0003)\u001d+g.\u001a:jGJ{woV5uQN\u001b\u0007.Z7b\u00031qW/\\%oaV$(k\\<t\u0003IIg\u000e];u%><8\u000fU3s'\u0016\u001cwN\u001c3\u0016\u0005\u0005\u0015\u0005c\u0001\u001c\u0002\b&\u0019\u0011\u0011R\u001c\u0003\r\u0011{WO\u00197f\u0003Y\u0001(o\\2fgN,GMU8xgB+'oU3d_:$\u0017\u0001\u00026t_:\f!\u0002\u001d:fiRL(j]8o\u0003!!xn\u0015;sS:<G#\u0001,\u0002\u0013)\u001cxN\u001c,bYV,WCAAM!\u0011\tY*!,\u000f\t\u0005u\u0015q\u0015\b\u0005\u0003?\u000b\u0019KD\u0002?\u0003CK\u0011AM\u0005\u0004\u0003K\u000b\u0014A\u00026t_:$4/\u0003\u0003\u0002*\u0006-\u0016a\u0002&t_:\f5\u000b\u0016\u0006\u0004\u0003K\u000b\u0014\u0002BAX\u0003c\u0013aA\u0013,bYV,'\u0002BAU\u0003WC3\u0001AA[!\u0011\t9,a/\u000e\u0005\u0005e&bAA+[%!\u0011QXA]\u0005!)eo\u001c7wS:<\u0017AF*ue\u0016\fW.\u001b8h#V,'/\u001f)s_\u001e\u0014Xm]:\u0011\u0005y\u00143\u0003\u0002\u00126\u0003\u000b\u0004B!a2\u0002N6\u0011\u0011\u0011\u001a\u0006\u0004\u0003\u0017l\u0015AA5p\u0013\r)\u0015\u0011\u001a\u000b\u0003\u0003\u0003\fa!\\1qa\u0016\u0014(CBAk\u0003;\f)OB\u0004\u0002X\u0006e\u0007!a5\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \t\r\u0005mG\u0005AAj\u0003\r\u0011X\r\u001e\t\u0005\u0003?\f\t/\u0004\u0002\u0002X%!\u00111]A,\u00051y%M[3di6\u000b\u0007\u000f]3s!\u0011\t9/a<\u000e\u0005\u0005%(b\u0001\u001d\u0002l*!\u0011Q^A.\u0003\u0019iw\u000eZ;mK&!\u0011\u0011_Au\u0005I\u0019E.Y:t)\u0006<W\t\u001f;f]NLwN\\:\u0002\u0015)\u001cxN\\*ue&tw\rF\u0002W\u0003oDq!!?&\u0001\u0004\t\u0019$\u0001\u0005qe><'/Z:t\u0003!1'o\\7Kg>tG\u0003BA\u001a\u0003\u007fDa!!$'\u0001\u00041\u0016\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u0003!\ry'qA\u0005\u0004\u0005\u0013\u0001(AB(cU\u0016\u001cG\u000f"
)
public class StreamingQueryProgress implements Serializable {
   private final UUID id;
   private final UUID runId;
   private final String name;
   private final String timestamp;
   private final long batchId;
   private final long batchDuration;
   private final Map durationMs;
   private final Map eventTime;
   private final StateOperatorProgress[] stateOperators;
   private final SourceProgress[] sources;
   private final SinkProgress sink;
   private final Map observedMetrics;

   public UUID id() {
      return this.id;
   }

   public UUID runId() {
      return this.runId;
   }

   public String name() {
      return this.name;
   }

   public String timestamp() {
      return this.timestamp;
   }

   public long batchId() {
      return this.batchId;
   }

   public long batchDuration() {
      return this.batchDuration;
   }

   public Map durationMs() {
      return this.durationMs;
   }

   public Map eventTime() {
      return this.eventTime;
   }

   public StateOperatorProgress[] stateOperators() {
      return this.stateOperators;
   }

   public SourceProgress[] sources() {
      return this.sources;
   }

   public SinkProgress sink() {
      return this.sink;
   }

   public Map observedMetrics() {
      return this.observedMetrics;
   }

   public long numInputRows() {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.sources()), (x$3) -> BoxesRunTime.boxToLong($anonfun$numInputRows$1(x$3)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   public double inputRowsPerSecond() {
      return BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.sources()), (x$4) -> BoxesRunTime.boxToDouble($anonfun$inputRowsPerSecond$1(x$4)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   public double processedRowsPerSecond() {
      return BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.sources()), (x$5) -> BoxesRunTime.boxToDouble($anonfun$processedRowsPerSecond$1(x$5)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   public String json() {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public String prettyJson() {
      return org.json4s.jackson.JsonMethods..MODULE$.pretty(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public String toString() {
      return this.prettyJson();
   }

   public JValue jsonValue() {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("id"), new JString(this.id().toString())), .MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("runId"), new JString(this.runId().toString())), .MODULE$.$conforms(), .MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), new JString(this.name())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("timestamp"), new JString(this.timestamp())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("batchId"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.batchId()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("batchDuration"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.batchDuration()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("numInputRows"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numInputRows()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("inputRowsPerSecond"), SafeJsonSerializer$.MODULE$.safeDoubleToJValue(this.inputRowsPerSecond())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("processedRowsPerSecond"), SafeJsonSerializer$.MODULE$.safeDoubleToJValue(this.processedRowsPerSecond())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("durationMs"), SafeJsonSerializer$.MODULE$.safeMapToJValue(this.durationMs(), (v) -> new JInt(scala.math.BigInt..MODULE$.long2bigInt(.MODULE$.Long2long(v))))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("eventTime"), SafeJsonSerializer$.MODULE$.safeMapToJValue(this.eventTime(), (s) -> new JString(s))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("stateOperators"), new JArray(.MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.stateOperators()), (x$6) -> x$6.jsonValue(), scala.reflect.ClassTag..MODULE$.apply(JValue.class))).toList())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sources"), new JArray(.MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.sources()), (x$7) -> x$7.jsonValue(), scala.reflect.ClassTag..MODULE$.apply(JValue.class))).toList())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sink"), this.sink().jsonValue()))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("observedMetrics"), SafeJsonSerializer$.MODULE$.safeMapToJValue(this.observedMetrics(), (row) -> row.jsonValue())));
   }

   // $FF: synthetic method
   public static final long $anonfun$numInputRows$1(final SourceProgress x$3) {
      return x$3.numInputRows();
   }

   // $FF: synthetic method
   public static final double $anonfun$inputRowsPerSecond$1(final SourceProgress x$4) {
      return x$4.inputRowsPerSecond();
   }

   // $FF: synthetic method
   public static final double $anonfun$processedRowsPerSecond$1(final SourceProgress x$5) {
      return x$5.processedRowsPerSecond();
   }

   public StreamingQueryProgress(final UUID id, final UUID runId, final String name, final String timestamp, final long batchId, final long batchDuration, final Map durationMs, final Map eventTime, final StateOperatorProgress[] stateOperators, final SourceProgress[] sources, final SinkProgress sink, @JsonDeserialize(contentAs = GenericRowWithSchema.class) final Map observedMetrics) {
      this.id = id;
      this.runId = runId;
      this.name = name;
      this.timestamp = timestamp;
      this.batchId = batchId;
      this.batchDuration = batchDuration;
      this.durationMs = durationMs;
      this.eventTime = eventTime;
      this.stateOperators = stateOperators;
      this.sources = sources;
      this.sink = sink;
      this.observedMetrics = observedMetrics;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
