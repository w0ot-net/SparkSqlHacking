package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.Evolving;
import org.json4s.JInt;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.JsonListAssoc;
import org.json4s.jackson.JsonMethods.;
import scala.Predef;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d\u0001\u0002\u0013&\u0001AB\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005\u000b\"Aa\n\u0001BC\u0002\u0013\u0005q\n\u0003\u0005T\u0001\t\u0005\t\u0015!\u0003Q\u0011!!\u0006A!b\u0001\n\u0003y\u0005\u0002C+\u0001\u0005\u0003\u0005\u000b\u0011\u0002)\t\u0011Y\u0003!Q1A\u0005\u0002=C\u0001b\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\t1\u0002\u0011)\u0019!C\u0001\u001f\"A\u0011\f\u0001B\u0001B\u0003%\u0001\u000b\u0003\u0005[\u0001\t\u0015\r\u0011\"\u0001P\u0011!Y\u0006A!A!\u0002\u0013\u0001\u0006\u0002\u0003/\u0001\u0005\u000b\u0007I\u0011A(\t\u0011u\u0003!\u0011!Q\u0001\nAC\u0001B\u0018\u0001\u0003\u0006\u0004%\ta\u0014\u0005\t?\u0002\u0011\t\u0011)A\u0005!\"A\u0001\r\u0001BC\u0002\u0013\u0005q\n\u0003\u0005b\u0001\t\u0005\t\u0015!\u0003Q\u0011!\u0011\u0007A!b\u0001\n\u0003y\u0005\u0002C2\u0001\u0005\u0003\u0005\u000b\u0011\u0002)\t\u0011\u0011\u0004!Q1A\u0005\u0002=C\u0001\"\u001a\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\tM\u0002\u0011)\u0019!C\u0001O\"AQ\u000f\u0001B\u0001B\u0003%\u0001\u000e\u0003\u0004w\u0001\u0011\u0005\u0011f\u001e\u0005\u0007\u0003\u001b\u0001A\u0011\u0001#\t\r\u0005=\u0001\u0001\"\u0001E\u0011!\t\t\u0002\u0001C\u0001O\u0005M\u0001\u0002CA\u000f\u0001\u0011\u0005q%a\b\t\u000f\u0005m\u0002\u0001\"\u0011\u0002>\u001dI\u0011QJ\u0013\u0002\u0002#\u0005\u0011q\n\u0004\tI\u0015\n\t\u0011#\u0001\u0002R!1a\u000f\tC\u0001\u0003;B!\"a\u0018!#\u0003%\t!KA1\u0011%\t)\bIA\u0001\n\u0013\t9HA\u000bTi\u0006$Xm\u00149fe\u0006$xN\u001d)s_\u001e\u0014Xm]:\u000b\u0005\u0019:\u0013!C:ue\u0016\fW.\u001b8h\u0015\tA\u0013&A\u0002tc2T!AK\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u00051j\u0013AB1qC\u000eDWMC\u0001/\u0003\ry'oZ\u0002\u0001'\r\u0001\u0011g\u000e\t\u0003eUj\u0011a\r\u0006\u0002i\u0005)1oY1mC&\u0011ag\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005a\u0002eBA\u001d?\u001d\tQT(D\u0001<\u0015\tat&\u0001\u0004=e>|GOP\u0005\u0002i%\u0011qhM\u0001\ba\u0006\u001c7.Y4f\u0013\t\t%I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002@g\u0005aq\u000e]3sCR|'OT1nKV\tQ\t\u0005\u0002G\u0015:\u0011q\t\u0013\t\u0003uMJ!!S\u001a\u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013N\nQb\u001c9fe\u0006$xN\u001d(b[\u0016\u0004\u0013\u0001\u00048v[J{wo\u001d+pi\u0006dW#\u0001)\u0011\u0005I\n\u0016B\u0001*4\u0005\u0011auN\\4\u0002\u001b9,XNU8xgR{G/\u00197!\u00039qW/\u001c*poN,\u0006\u000fZ1uK\u0012\fqB\\;n%><8/\u00169eCR,G\rI\u0001\u0011C2dW\u000b\u001d3bi\u0016\u001cH+[7f\u001bN\f\u0011#\u00197m+B$\u0017\r^3t)&lW-T:!\u00039qW/\u001c*poN\u0014V-\\8wK\u0012\fqB\\;n%><8OU3n_Z,G\rI\u0001\u0012C2d'+Z7pm\u0006d7\u000fV5nK6\u001b\u0018AE1mYJ+Wn\u001c<bYN$\u0016.\\3Ng\u0002\nAbY8n[&$H+[7f\u001bN\fQbY8n[&$H+[7f\u001bN\u0004\u0013aD7f[>\u0014\u00180V:fI\nKH/Z:\u0002!5,Wn\u001c:z+N,GMQ=uKN\u0004\u0013!\u00078v[J{wo\u001d#s_B\u0004X\r\u001a\"z/\u0006$XM]7be.\f!D\\;n%><8\u000f\u0012:paB,GMQ=XCR,'/\\1sW\u0002\nAC\\;n'\",hM\u001a7f!\u0006\u0014H/\u001b;j_:\u001c\u0018!\u00068v[NCWO\u001a4mKB\u000b'\u000f^5uS>t7\u000fI\u0001\u0017]Vl7\u000b^1uKN#xN]3J]N$\u0018M\\2fg\u00069b.^7Ti\u0006$Xm\u0015;pe\u0016Len\u001d;b]\u000e,7\u000fI\u0001\u000eGV\u001cHo\\7NKR\u0014\u0018nY:\u0016\u0003!\u0004B!\u001b8Fa6\t!N\u0003\u0002lY\u0006!Q\u000f^5m\u0015\u0005i\u0017\u0001\u00026bm\u0006L!a\u001c6\u0003\u00075\u000b\u0007\u000f\u0005\u0002ri6\t!O\u0003\u0002tY\u0006!A.\u00198h\u0013\t\u0011&/\u0001\bdkN$x.\\'fiJL7m\u001d\u0011\u0002\rqJg.\u001b;?)MA(p\u001f?~}~\f\t!a\u0001\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006!\tI\b!D\u0001&\u0011\u0015\u0019\u0015\u00041\u0001F\u0011\u0015q\u0015\u00041\u0001Q\u0011\u0015!\u0016\u00041\u0001Q\u0011\u00151\u0016\u00041\u0001Q\u0011\u0015A\u0016\u00041\u0001Q\u0011\u0015Q\u0016\u00041\u0001Q\u0011\u0015a\u0016\u00041\u0001Q\u0011\u0015q\u0016\u00041\u0001Q\u0011\u0015\u0001\u0017\u00041\u0001Q\u0011\u0015\u0011\u0017\u00041\u0001Q\u0011\u0015!\u0017\u00041\u0001Q\u0011\u001d1\u0017\u0004%AA\u0002!\fAA[:p]\u0006Q\u0001O]3uifT5o\u001c8\u0002\t\r|\u0007/\u001f\u000b\u0006q\u0006U\u0011\u0011\u0004\u0005\u0007\u0003/a\u0002\u0019\u0001)\u0002#9,wOT;n%><8/\u00169eCR,G\r\u0003\u0004\u0002\u001cq\u0001\r\u0001U\u0001\u001d]\u0016<h*^7S_^\u001cHI]8qa\u0016$')_,bi\u0016\u0014X.\u0019:l\u0003%Q7o\u001c8WC2,X-\u0006\u0002\u0002\"A!\u00111EA\u001b\u001d\u0011\t)#a\f\u000f\t\u0005\u001d\u00121\u0006\b\u0004u\u0005%\u0012\"\u0001\u0018\n\u0007\u00055R&\u0001\u0004kg>tGg]\u0005\u0005\u0003c\t\u0019$A\u0004Kg>t\u0017i\u0015+\u000b\u0007\u00055R&\u0003\u0003\u00028\u0005e\"A\u0002&WC2,XM\u0003\u0003\u00022\u0005M\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015C3\u0001AA!!\u0011\t\u0019%!\u0013\u000e\u0005\u0005\u0015#bAA$S\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005-\u0013Q\t\u0002\t\u000bZ|GN^5oO\u0006)2\u000b^1uK>\u0003XM]1u_J\u0004&o\\4sKN\u001c\bCA=!'\u0011\u0001\u0013'a\u0015\u0011\t\u0005U\u00131L\u0007\u0003\u0003/R1!!\u0017m\u0003\tIw.C\u0002B\u0003/\"\"!a\u0014\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132eU\u0011\u00111\r\u0016\u0004Q\u0006\u00154FAA4!\u0011\tI'!\u001d\u000e\u0005\u0005-$\u0002BA7\u0003_\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u001d3'\u0003\u0003\u0002t\u0005-$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0010\t\u0004c\u0006m\u0014bAA?e\n1qJ\u00196fGR\u0004"
)
public class StateOperatorProgress implements Serializable {
   private final String operatorName;
   private final long numRowsTotal;
   private final long numRowsUpdated;
   private final long allUpdatesTimeMs;
   private final long numRowsRemoved;
   private final long allRemovalsTimeMs;
   private final long commitTimeMs;
   private final long memoryUsedBytes;
   private final long numRowsDroppedByWatermark;
   private final long numShufflePartitions;
   private final long numStateStoreInstances;
   private final Map customMetrics;

   public String operatorName() {
      return this.operatorName;
   }

   public long numRowsTotal() {
      return this.numRowsTotal;
   }

   public long numRowsUpdated() {
      return this.numRowsUpdated;
   }

   public long allUpdatesTimeMs() {
      return this.allUpdatesTimeMs;
   }

   public long numRowsRemoved() {
      return this.numRowsRemoved;
   }

   public long allRemovalsTimeMs() {
      return this.allRemovalsTimeMs;
   }

   public long commitTimeMs() {
      return this.commitTimeMs;
   }

   public long memoryUsedBytes() {
      return this.memoryUsedBytes;
   }

   public long numRowsDroppedByWatermark() {
      return this.numRowsDroppedByWatermark;
   }

   public long numShufflePartitions() {
      return this.numShufflePartitions;
   }

   public long numStateStoreInstances() {
      return this.numStateStoreInstances;
   }

   public Map customMetrics() {
      return this.customMetrics;
   }

   public String json() {
      return .MODULE$.compact(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String prettyJson() {
      return .MODULE$.pretty(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public StateOperatorProgress copy(final long newNumRowsUpdated, final long newNumRowsDroppedByWatermark) {
      return new StateOperatorProgress(this.operatorName(), this.numRowsTotal(), newNumRowsUpdated, this.allUpdatesTimeMs(), this.numRowsRemoved(), this.allRemovalsTimeMs(), this.commitTimeMs(), this.memoryUsedBytes(), newNumRowsDroppedByWatermark, this.numShufflePartitions(), this.numStateStoreInstances(), this.customMetrics());
   }

   public JValue jsonValue() {
      JsonListAssoc var10000 = org.json4s.JsonListAssoc..MODULE$;
      List var10001 = org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("operatorName"), new JString(this.operatorName())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numRowsTotal"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numRowsTotal()))), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numRowsUpdated"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numRowsUpdated()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("allUpdatesTimeMs"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.allUpdatesTimeMs()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numRowsRemoved"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numRowsRemoved()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("allRemovalsTimeMs"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.allRemovalsTimeMs()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("commitTimeMs"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.commitTimeMs()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryUsedBytes"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.memoryUsedBytes()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numRowsDroppedByWatermark"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numRowsDroppedByWatermark()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numShufflePartitions"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numShufflePartitions()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numStateStoreInstances"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numStateStoreInstances())))));
      Predef.ArrowAssoc var10002 = scala.Predef.ArrowAssoc..MODULE$;
      Object var10003 = scala.Predef..MODULE$.ArrowAssoc("customMetrics");
      Object var10004;
      if (!this.customMetrics().isEmpty()) {
         Seq keys = (Seq)scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(this.customMetrics().keySet()).asScala().toSeq().sorted(scala.math.Ordering.String..MODULE$);
         var10004 = ((IterableOnceOps)keys.map((k) -> org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), new JInt(scala.math.BigInt..MODULE$.long2bigInt(scala.Predef..MODULE$.Long2long((Long)this.customMetrics().get(k))))), scala.Predef..MODULE$.$conforms()))).reduce((x$1, x$2) -> org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(x$1), x$2));
      } else {
         var10004 = org.json4s.JNothing..MODULE$;
      }

      return var10000.$tilde$extension(var10001, var10002.$minus$greater$extension(var10003, var10004));
   }

   public String toString() {
      return this.prettyJson();
   }

   public StateOperatorProgress(final String operatorName, final long numRowsTotal, final long numRowsUpdated, final long allUpdatesTimeMs, final long numRowsRemoved, final long allRemovalsTimeMs, final long commitTimeMs, final long memoryUsedBytes, final long numRowsDroppedByWatermark, final long numShufflePartitions, final long numStateStoreInstances, final Map customMetrics) {
      this.operatorName = operatorName;
      this.numRowsTotal = numRowsTotal;
      this.numRowsUpdated = numRowsUpdated;
      this.allUpdatesTimeMs = allUpdatesTimeMs;
      this.numRowsRemoved = numRowsRemoved;
      this.allRemovalsTimeMs = allRemovalsTimeMs;
      this.commitTimeMs = commitTimeMs;
      this.memoryUsedBytes = memoryUsedBytes;
      this.numRowsDroppedByWatermark = numRowsDroppedByWatermark;
      this.numShufflePartitions = numShufflePartitions;
      this.numStateStoreInstances = numStateStoreInstances;
      this.customMetrics = customMetrics;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
