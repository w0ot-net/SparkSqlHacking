package org.apache.spark.api.r;

import java.io.Serializable;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaPairRDD$;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005=4AAC\u0006\u0005-!Aa\u0007\u0001B\u0001B\u0003%q\u0007\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003.\u0011!q\u0004A!A!\u0002\u0013\u0001\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u0011-\u0003!\u0011!Q\u0001\nAB\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t-\u0002\u0011\u0019\u0011)A\u0006/\")Q\f\u0001C\u0001=\"A\u0001\u000e\u0001EC\u0002\u0013\u0005\u0011N\u0001\u0007QC&\u0014x/[:f%J#EI\u0003\u0002\r\u001b\u0005\t!O\u0003\u0002\u000f\u001f\u0005\u0019\u0011\r]5\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u0018=M\u0011\u0001\u0001\u0007\t\u00053ia\"&D\u0001\f\u0013\tY2B\u0001\u0005CCN,'K\u0015#E!\tib\u0004\u0004\u0001\u0005\u000b}\u0001!\u0019\u0001\u0011\u0003\u0003Q\u000b\"!I\u0014\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\u000f9{G\u000f[5oOB\u0011!\u0005K\u0005\u0003S\r\u00121!\u00118z!\u0011\u00113&\f\u0019\n\u00051\u001a#A\u0002+va2,'\u0007\u0005\u0002#]%\u0011qf\t\u0002\u0004\u0013:$\bc\u0001\u00122g%\u0011!g\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003EQJ!!N\u0012\u0003\t\tKH/Z\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007aZD$D\u0001:\u0015\tQt\"A\u0002sI\u0012L!\u0001P\u001d\u0003\u0007I#E)A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\tQ\u0006\u001c\bNR;oG\u0006aA-Z:fe&\fG.\u001b>feB\u0011\u0011\t\u0013\b\u0003\u0005\u001a\u0003\"aQ\u0012\u000e\u0003\u0011S!!R\u000b\u0002\rq\u0012xn\u001c;?\u0013\t95%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0013*\u0013aa\u0015;sS:<'BA$$\u00031\u0001\u0018mY6bO\u0016t\u0015-\\3t\u00035\u0011'o\\1eG\u0006\u001cHOV1sgB\u0019!%\r(\u0011\u0005=#V\"\u0001)\u000b\u0005E\u0013\u0016\u0001\u00027b]\u001eT\u0011aU\u0001\u0005U\u00064\u0018-\u0003\u0002V!\n1qJ\u00196fGR\f!\"\u001a<jI\u0016t7-\u001a\u00134!\rA6\fH\u0007\u00023*\u0011!lI\u0001\be\u00164G.Z2u\u0013\ta\u0016L\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q9qLY2eK\u001a<GC\u00011b!\rI\u0002\u0001\b\u0005\u0006-\"\u0001\u001da\u0016\u0005\u0006m!\u0001\ra\u000e\u0005\u0006{!\u0001\r!\f\u0005\u0006}!\u0001\r\u0001\r\u0005\u0006\u007f!\u0001\r\u0001\u0011\u0005\u0006\u0017\"\u0001\r\u0001\r\u0005\u0006\u0019\"\u0001\r!T\u0001\u000eCNT\u0015M^1QC&\u0014(\u000b\u0012#\u0016\u0003)\u0004Ba[7.a5\tAN\u0003\u0002T\u001b%\u0011a\u000e\u001c\u0002\f\u0015\u00064\u0018\rU1jeJ#E\t"
)
public class PairwiseRRDD extends BaseRRDD {
   private JavaPairRDD asJavaPairRDD;
   private volatile boolean bitmap$0;

   private JavaPairRDD asJavaPairRDD$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.asJavaPairRDD = JavaPairRDD$.MODULE$.fromRDD(this, .MODULE$.Int(), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.asJavaPairRDD;
   }

   public JavaPairRDD asJavaPairRDD() {
      return !this.bitmap$0 ? this.asJavaPairRDD$lzycompute() : this.asJavaPairRDD;
   }

   public PairwiseRRDD(final RDD parent, final int numPartitions, final byte[] hashFunc, final String deserializer, final byte[] packageNames, final Object[] broadcastVars, final ClassTag evidence$3) {
      super(parent, numPartitions, hashFunc, deserializer, SerializationFormats$.MODULE$.BYTE(), packageNames, (Broadcast[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(broadcastVars), new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Broadcast apply(final Object x) {
            return (Broadcast)x;
         }
      }, .MODULE$.apply(Broadcast.class)), evidence$3, .MODULE$.apply(Tuple2.class));
   }
}
