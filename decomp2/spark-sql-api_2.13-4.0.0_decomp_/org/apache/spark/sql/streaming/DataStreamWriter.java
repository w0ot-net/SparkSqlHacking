package org.apache.spark.sql.streaming;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeoutException;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.ForeachWriter;
import org.apache.spark.sql.WriteConfigMethods;
import scala.Function2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005%g!\u0002\n\u0014\u0003\u0003q\u0002\"B\u001c\u0001\t\u0003A\u0004\"B\u001d\u0001\r\u0003Q\u0004\"B\u001d\u0001\r\u0003\u0001\u0005\"B'\u0001\r\u0003q\u0005\"B*\u0001\r\u0003!\u0006\"\u0002,\u0001\r\u00039\u0006\"\u0002.\u0001\r\u0003Y\u0006\"\u00025\u0001\r\u0003I\u0007\"\u00027\u0001\r\u0003i\u0007\"B:\u0001\r\u0003!\bBB:\u0001\t\u0003\t\u0019\u0002C\u0004\u00028\u00011\t!!\u000f\t\u000f\u0005]\u0002A\"\u0001\u0002F!9\u0011Q\u0012\u0001\u0007\u0002\u0005=\u0005bBAR\u0001\u0011\u0005\u0013Q\u0015\u0005\b\u0003G\u0003A\u0011IA[\u0011\u001d\t\u0019\u000b\u0001C!\u0003w\u0013\u0001\u0003R1uCN#(/Z1n/JLG/\u001a:\u000b\u0005Q)\u0012!C:ue\u0016\fW.\u001b8h\u0015\t1r#A\u0002tc2T!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001+\tybfE\u0002\u0001A\u0019\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0007cA\u0014)U5\tQ#\u0003\u0002*+\t\u0011rK]5uK\u000e{gNZ5h\u001b\u0016$\bn\u001c3t!\rY\u0003\u0001L\u0007\u0002'A\u0011QF\f\u0007\u0001\t\u0015y\u0003A1\u00011\u0005\u0005!\u0016CA\u00195!\t\t#'\u0003\u00024E\t9aj\u001c;iS:<\u0007CA\u00116\u0013\t1$EA\u0002B]f\fa\u0001P5oSRtD#\u0001\u0016\u0002\u0015=,H\u000f];u\u001b>$W\r\u0006\u0002<y5\t\u0001\u0001C\u0003:\u0005\u0001\u0007Q\b\u0005\u0002,}%\u0011qh\u0005\u0002\u000b\u001fV$\b/\u001e;N_\u0012,GCA\u001eB\u0011\u0015I4\u00011\u0001C!\t\u0019%J\u0004\u0002E\u0011B\u0011QII\u0007\u0002\r*\u0011q)H\u0001\u0007yI|w\u000e\u001e \n\u0005%\u0013\u0013A\u0002)sK\u0012,g-\u0003\u0002L\u0019\n11\u000b\u001e:j]\u001eT!!\u0013\u0012\u0002\u000fQ\u0014\u0018nZ4feR\u00111h\u0014\u0005\u0006\u001b\u0012\u0001\r\u0001\u0015\t\u0003WEK!AU\n\u0003\u000fQ\u0013\u0018nZ4fe\u0006I\u0011/^3ss:\u000bW.\u001a\u000b\u0003wUCQaU\u0003A\u0002\t\u000baAZ8s[\u0006$HCA\u001eY\u0011\u0015If\u00011\u0001C\u0003\u0019\u0019x.\u001e:dK\u0006Y\u0001/\u0019:uSRLwN\u001c\"z)\tYD\fC\u0003^\u000f\u0001\u0007a,\u0001\u0005d_2t\u0015-\\3t!\r\tsLQ\u0005\u0003A\n\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?Q\t9!\r\u0005\u0002dM6\tAM\u0003\u0002fE\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u001d$'a\u0002<be\u0006\u0014xm]\u0001\nG2,8\u000f^3s\u0005f$\"a\u000f6\t\u000buC\u0001\u0019\u00010)\u0005!\u0011\u0017a\u00024pe\u0016\f7\r\u001b\u000b\u0003w9DQa\\\u0005A\u0002A\faa\u001e:ji\u0016\u0014\bcA\u0014rY%\u0011!/\u0006\u0002\u000e\r>\u0014X-Y2i/JLG/\u001a:\u0002\u0019\u0019|'/Z1dQ\n\u000bGo\u00195\u0015\u0005m*\b\"\u0002<\u000b\u0001\u00049\u0018\u0001\u00034v]\u000e$\u0018n\u001c8\u0011\r\u0005B(0`A\u0001\u0013\tI(EA\u0005Gk:\u001cG/[8oeA\u0019qe\u001f\u0017\n\u0005q,\"a\u0002#bi\u0006\u001cX\r\u001e\t\u0003CyL!a \u0012\u0003\t1{gn\u001a\t\u0004C\u0005\r\u0011bAA\u0003E\t!QK\\5uQ\rQ\u0011\u0011\u0002\t\u0005\u0003\u0017\ty!\u0004\u0002\u0002\u000e)\u0011QmF\u0005\u0005\u0003#\tiA\u0001\u0005Fm>dg/\u001b8h)\rY\u0014Q\u0003\u0005\u0007m.\u0001\r!a\u0006\u0011\u000f\u0005e\u0011Q\u0005>\u0002*5\u0011\u00111\u0004\u0006\u0004m\u0006u!\u0002BA\u0010\u0003C\tAA[1wC*\u0019\u00111E\f\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002(\u0005m!!\u0004,pS\u00124UO\\2uS>t'\u0007\u0005\u0003\u0002,\u0005MRBAA\u0017\u0015\u0011\ty#!\r\u0002\t1\fgn\u001a\u0006\u0003\u0003?I1a`A\u0017Q\rY\u0011\u0011B\u0001\u0006gR\f'\u000f\u001e\u000b\u0005\u0003w\t\t\u0005E\u0002,\u0003{I1!a\u0010\u0014\u00059\u0019FO]3b[&tw-U;fefDa!a\u0011\r\u0001\u0004\u0011\u0015\u0001\u00029bi\"$\"!a\u000f)\u000b5\tI%a\u0018\u0011\u000b\u0005\nY%a\u0014\n\u0007\u00055#E\u0001\u0004uQJ|wo\u001d\t\u0005\u0003#\nY&\u0004\u0002\u0002T)!\u0011QKA,\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u00033\n\t$\u0001\u0003vi&d\u0017\u0002BA/\u0003'\u0012\u0001\u0003V5nK>,H/\u0012=dKB$\u0018n\u001c82\ry\u0011\u0015\u0011MAFc%\u0019\u00131MA5\u0003\u0003\u000bY'\u0006\u0003\u0002f\u0005\u001dT#\u0001\"\u0005\r=j\"\u0019AA9\u0013\u0011\tY'!\u001c\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0015\r\tyGI\u0001\u0007i\"\u0014xn^:\u0012\u0007E\n\u0019\b\u0005\u0003\u0002v\u0005mdbA\u0011\u0002x%\u0019\u0011\u0011\u0010\u0012\u0002\u000fA\f7m[1hK&!\u0011QPA@\u0005%!\u0006N]8xC\ndWMC\u0002\u0002z\t\n\u0014bIAB\u0003\u000b\u000b9)a\u001c\u000f\u0007\u0005\n))C\u0002\u0002p\t\nTAI\u0011#\u0003\u0013\u0013Qa]2bY\u0006\f4AJA(\u0003\u001d!x\u000eV1cY\u0016$B!a\u000f\u0002\u0012\"1\u00111\u0013\bA\u0002\t\u000b\u0011\u0002^1cY\u0016t\u0015-\\3)\u00079\tI\u0001K\u0003\u000f\u0003\u0013\nI*\r\u0004\u001f\u0005\u0006m\u0015\u0011U\u0019\nG\u0005\r\u0014\u0011NAO\u0003W\n\u0014bIAB\u0003\u000b\u000by*a\u001c2\u000b\t\n#%!#2\u0007\u0019\ny%\u0001\u0004paRLwN\u001c\u000b\u0006w\u0005\u001d\u00161\u0016\u0005\u0007\u0003S{\u0001\u0019\u0001\"\u0002\u0007-,\u0017\u0010C\u0004\u0002.>\u0001\r!a,\u0002\u000bY\fG.^3\u0011\u0007\u0005\n\t,C\u0002\u00024\n\u0012qAQ8pY\u0016\fg\u000eF\u0003<\u0003o\u000bI\f\u0003\u0004\u0002*B\u0001\rA\u0011\u0005\u0007\u0003[\u0003\u0002\u0019A?\u0015\u000bm\ni,a0\t\r\u0005%\u0016\u00031\u0001C\u0011\u001d\ti+\u0005a\u0001\u0003\u0003\u00042!IAb\u0013\r\t)M\t\u0002\u0007\t>,(\r\\3)\u0007\u0001\tI\u0001"
)
public abstract class DataStreamWriter implements WriteConfigMethods {
   public DataStreamWriter partitionBy(final String... colNames) {
      return this.partitionBy((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public DataStreamWriter clusterBy(final String... colNames) {
      return this.clusterBy((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public abstract DataStreamWriter outputMode(final OutputMode outputMode);

   public abstract DataStreamWriter outputMode(final String outputMode);

   public abstract DataStreamWriter trigger(final Trigger trigger);

   public abstract DataStreamWriter queryName(final String queryName);

   public abstract DataStreamWriter format(final String source);

   public abstract DataStreamWriter partitionBy(final Seq colNames);

   public abstract DataStreamWriter clusterBy(final Seq colNames);

   public abstract DataStreamWriter foreach(final ForeachWriter writer);

   @Evolving
   public abstract DataStreamWriter foreachBatch(final Function2 function);

   @Evolving
   public DataStreamWriter foreachBatch(final VoidFunction2 function) {
      return this.foreachBatch((Function2)((batchDs, batchId) -> {
         $anonfun$foreachBatch$1(function, batchDs, BoxesRunTime.unboxToLong(batchId));
         return BoxedUnit.UNIT;
      }));
   }

   public abstract StreamingQuery start(final String path);

   public abstract StreamingQuery start() throws TimeoutException;

   @Evolving
   public abstract StreamingQuery toTable(final String tableName) throws TimeoutException;

   public DataStreamWriter option(final String key, final boolean value) {
      return (DataStreamWriter)WriteConfigMethods.option$(this, key, value);
   }

   public DataStreamWriter option(final String key, final long value) {
      return (DataStreamWriter)WriteConfigMethods.option$(this, key, value);
   }

   public DataStreamWriter option(final String key, final double value) {
      return (DataStreamWriter)WriteConfigMethods.option$(this, key, value);
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachBatch$1(final VoidFunction2 function$1, final Dataset batchDs, final long batchId) {
      function$1.call(batchDs, scala.Predef..MODULE$.long2Long(batchId));
   }

   public DataStreamWriter() {
      WriteConfigMethods.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
