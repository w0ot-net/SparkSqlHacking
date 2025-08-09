package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.NextIterator;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.Iterator;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001da\u0001\u0002\f\u0018\u0001\u0001B\u0001B\u000f\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\"Aa\t\u0001B\u0001B\u0003%1\n\u0003\u0005W\u0001\t\u0005\t\u0015!\u0003X\u0011!Q\u0006A!A!\u0002\u00139\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0011\u0002/\t\u0011}\u0003!\u0011!Q\u0001\n\u0001D\u0001B\u001a\u0001\u0003\u0004\u0003\u0006Ya\u001a\u0005\u0006[\u0002!\tA\u001c\u0005\u0006s\u0002!\tE\u001f\u0005\b\u0003\u0007\u0001A\u0011IA\u0003\u000f\u001d\t9c\u0006E\u0001\u0003S1aAF\f\t\u0002\u0005-\u0002BB7\u000e\t\u0003\ty\u0004C\u0004\u0002B5!\t!a\u0011\u0007\u0013\u0005]S\u0002%A\u0012\u0002\u0005e\u0003BB \u0011\r\u0003\ty\u0006C\u0004\u0002\u00186!\t!!'\t\u000f\u0005]U\u0002\"\u0001\u0002V\"I\u0011Q]\u0007\u0012\u0002\u0013\u0005\u0011q\u001d\u0005\n\u0005\u0007i\u0011\u0011!C\u0005\u0005\u000b\u0011qA\u00133cGJ#EI\u0003\u0002\u00193\u0005\u0019!\u000f\u001a3\u000b\u0005iY\u0012!B:qCJ\\'B\u0001\u000f\u001e\u0003\u0019\t\u0007/Y2iK*\ta$A\u0002pe\u001e\u001c\u0001!\u0006\u0002\"QM\u0019\u0001A\t\u001b\u0011\u0007\r\"c%D\u0001\u0018\u0013\t)sCA\u0002S\t\u0012\u0003\"a\n\u0015\r\u0001\u0011)\u0011\u0006\u0001b\u0001U\t\tA+\u0005\u0002,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t9aj\u001c;iS:<\u0007C\u0001\u00173\u0013\t\u0019TFA\u0002B]f\u0004\"!\u000e\u001d\u000e\u0003YR!aN\r\u0002\u0011%tG/\u001a:oC2L!!\u000f\u001c\u0003\u000f1{wmZ5oO\u0006\u00111o\u0019\t\u0003yuj\u0011!G\u0005\u0003}e\u0011Ab\u00159be.\u001cuN\u001c;fqR\fQbZ3u\u0007>tg.Z2uS>t\u0007c\u0001\u0017B\u0007&\u0011!)\f\u0002\n\rVt7\r^5p]B\u0002\"\u0001R%\u000e\u0003\u0015S!AR$\u0002\u0007M\fHNC\u0001I\u0003\u0011Q\u0017M^1\n\u0005)+%AC\"p]:,7\r^5p]B\u0011Aj\u0015\b\u0003\u001bF\u0003\"AT\u0017\u000e\u0003=S!\u0001U\u0010\u0002\rq\u0012xn\u001c;?\u0013\t\u0011V&\u0001\u0004Qe\u0016$WMZ\u0005\u0003)V\u0013aa\u0015;sS:<'B\u0001*.\u0003)awn^3s\u0005>,h\u000e\u001a\t\u0003YaK!!W\u0017\u0003\t1{gnZ\u0001\u000bkB\u0004XM\u001d\"pk:$\u0017!\u00048v[B\u000b'\u000f^5uS>t7\u000f\u0005\u0002-;&\u0011a,\f\u0002\u0004\u0013:$\u0018AB7baJ{w\u000f\u0005\u0003-C\u000e4\u0013B\u00012.\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002EI&\u0011Q-\u0012\u0002\n%\u0016\u001cX\u000f\u001c;TKR\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rA7NJ\u0007\u0002S*\u0011!.L\u0001\be\u00164G.Z2u\u0013\ta\u0017N\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}QAqN]:ukZ<\b\u0010\u0006\u0002qcB\u00191\u0005\u0001\u0014\t\u000b\u0019L\u00019A4\t\u000biJ\u0001\u0019A\u001e\t\u000b}J\u0001\u0019\u0001!\t\u000b\u0019K\u0001\u0019A&\t\u000bYK\u0001\u0019A,\t\u000biK\u0001\u0019A,\t\u000bmK\u0001\u0019\u0001/\t\u000f}K\u0001\u0013!a\u0001A\u0006iq-\u001a;QCJ$\u0018\u000e^5p]N,\u0012a\u001f\t\u0004Yqt\u0018BA?.\u0005\u0015\t%O]1z!\tat0C\u0002\u0002\u0002e\u0011\u0011\u0002U1si&$\u0018n\u001c8\u0002\u000f\r|W\u000e];uKR1\u0011qAA\r\u0003;\u0001R!!\u0003\u0002\u0014\u0019rA!a\u0003\u0002\u00109\u0019a*!\u0004\n\u00039J1!!\u0005.\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0006\u0002\u0018\tA\u0011\n^3sCR|'OC\u0002\u0002\u00125Ba!a\u0007\f\u0001\u0004q\u0018a\u0002;iKB\u000b'\u000f\u001e\u0005\b\u0003?Y\u0001\u0019AA\u0011\u0003\u001d\u0019wN\u001c;fqR\u00042\u0001PA\u0012\u0013\r\t)#\u0007\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u0004KI\n\u001c'\u000b\u0012#\u0011\u0005\rj1#B\u0007\u0002.\u0005M\u0002c\u0001\u0017\u00020%\u0019\u0011\u0011G\u0017\u0003\r\u0005s\u0017PU3g!\u0011\t)$a\u000f\u000e\u0005\u0005]\"bAA\u001d\u000f\u0006\u0011\u0011n\\\u0005\u0005\u0003{\t9D\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002*\u00051\"/Z:vYR\u001cV\r\u001e+p\u001f\nTWm\u0019;BeJ\f\u0017\u0010\u0006\u0003\u0002F\u0005M\u0003\u0003\u0002\u0017}\u0003\u000f\u0002B!!\u0013\u0002P5\u0011\u00111\n\u0006\u0004\u0003\u001b:\u0015\u0001\u00027b]\u001eLA!!\u0015\u0002L\t1qJ\u00196fGRDa!!\u0016\u0010\u0001\u0004\u0019\u0017A\u0001:t\u0005E\u0019uN\u001c8fGRLwN\u001c$bGR|'/_\n\u0006!\u00055\u00121\f\t\u0005\u0003\u0013\ti&\u0003\u0003\u0002>\u0005]Q#A\")\u000bE\t\u0019'a\u001c\u0011\u000b1\n)'!\u001b\n\u0007\u0005\u001dTF\u0001\u0004uQJ|wo\u001d\t\u0005\u0003\u0013\tY'\u0003\u0003\u0002n\u0005]!!C#yG\u0016\u0004H/[8oc\u0019q2*!\u001d\u0002\u0016FJ1%a\u001d\u0002z\u0005-\u00151P\u000b\u0005\u0003k\n9(F\u0001L\t\u0019IsD1\u0001\u0002\u0002&!\u00111PA?\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%c)\u0019\u0011qP\u0017\u0002\rQD'o\\<t#\rY\u00131\u0011\t\u0005\u0003\u000b\u000b9ID\u0002-\u0003\u001fIA!!#\u0002\u0018\tIA\u000b\u001b:po\u0006\u0014G.Z\u0019\nG\u00055\u0015qRAI\u0003\u007fr1\u0001LAH\u0013\r\ty(L\u0019\u0006E1j\u00131\u0013\u0002\u0006g\u000e\fG.Y\u0019\u0004M\u0005%\u0014AB2sK\u0006$X-\u0006\u0003\u0002\u001c\u00065F\u0003EAO\u0003_\u000b9,a0\u0002B\u0006\r\u0017QYAd!\u0019\ty*a*\u0002,6\u0011\u0011\u0011\u0015\u0006\u0004\u0011\u0006\r&bAAS3\u0005\u0019\u0011\r]5\n\t\u0005%\u0016\u0011\u0015\u0002\b\u0015\u00064\u0018M\u0015#E!\r9\u0013Q\u0016\u0003\u0006SI\u0011\rA\u000b\u0005\u0007uI\u0001\r!!-\u0011\t\u0005}\u00151W\u0005\u0005\u0003k\u000b\tK\u0001\tKCZ\f7\u000b]1sW\u000e{g\u000e^3yi\"9\u0011\u0011\u0018\nA\u0002\u0005m\u0016!E2p]:,7\r^5p]\u001a\u000b7\r^8ssB\u0019\u0011Q\u0018\t\u000e\u00035AQA\u0012\nA\u0002-CQA\u0016\nA\u0002]CQA\u0017\nA\u0002]CQa\u0017\nA\u0002qCaa\u0018\nA\u0002\u0005%\u0007cBAf\u0003#\u001c\u00171V\u0007\u0003\u0003\u001bTA!a4\u0002\"\u0006Aa-\u001e8di&|g.\u0003\u0003\u0002T\u00065'\u0001\u0003$v]\u000e$\u0018n\u001c8\u0015\u001d\u0005]\u0017\u0011\\An\u0003;\fy.!9\u0002dB1\u0011qTAT\u0003\u000bBaAO\nA\u0002\u0005E\u0006bBA]'\u0001\u0007\u00111\u0018\u0005\u0006\rN\u0001\ra\u0013\u0005\u0006-N\u0001\ra\u0016\u0005\u00065N\u0001\ra\u0016\u0005\u00067N\u0001\r\u0001X\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001c\u0016\t\u0005%(\u0011A\u000b\u0003\u0003WTC!!<\u0002pB)A&Y2\u0002F-\u0012\u0011\u0011\u001f\t\u0005\u0003g\fi0\u0004\u0002\u0002v*!\u0011q_A}\u0003%)hn\u00195fG.,GMC\u0002\u0002|6\n!\"\u00198o_R\fG/[8o\u0013\u0011\ty0!>\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003*)\t\u0007!&\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002H\u0001"
)
public class JdbcRDD extends RDD {
   public final Function0 org$apache$spark$rdd$JdbcRDD$$getConnection;
   public final String org$apache$spark$rdd$JdbcRDD$$sql;
   private final long lowerBound;
   private final long upperBound;
   private final int numPartitions;
   public final Function1 org$apache$spark$rdd$JdbcRDD$$mapRow;

   public static Function1 $lessinit$greater$default$7() {
      return JdbcRDD$.MODULE$.$lessinit$greater$default$7();
   }

   public static JavaRDD create(final JavaSparkContext sc, final ConnectionFactory connectionFactory, final String sql, final long lowerBound, final long upperBound, final int numPartitions) {
      return JdbcRDD$.MODULE$.create(sc, connectionFactory, sql, lowerBound, upperBound, numPartitions);
   }

   public static JavaRDD create(final JavaSparkContext sc, final ConnectionFactory connectionFactory, final String sql, final long lowerBound, final long upperBound, final int numPartitions, final Function mapRow) {
      return JdbcRDD$.MODULE$.create(sc, connectionFactory, sql, lowerBound, upperBound, numPartitions, mapRow);
   }

   public static Object[] resultSetToObjectArray(final ResultSet rs) {
      return JdbcRDD$.MODULE$.resultSetToObjectArray(rs);
   }

   public Partition[] getPartitions() {
      BigInt length = .MODULE$.BigInt().apply(1).$plus(scala.math.BigInt..MODULE$.long2bigInt(this.upperBound)).$minus(scala.math.BigInt..MODULE$.long2bigInt(this.lowerBound));
      return (Partition[])scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.numPartitions).map((i) -> $anonfun$getPartitions$1(this, length, BoxesRunTime.unboxToInt(i))).toArray(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition thePart, final TaskContext context) {
      return new NextIterator(context, thePart) {
         private final JdbcPartition part;
         private final Connection conn;
         private final PreparedStatement stmt;
         private final String url;
         private final ResultSet rs;
         // $FF: synthetic field
         private final JdbcRDD $outer;

         private JdbcPartition part() {
            return this.part;
         }

         private Connection conn() {
            return this.conn;
         }

         private PreparedStatement stmt() {
            return this.stmt;
         }

         private String url() {
            return this.url;
         }

         private ResultSet rs() {
            return this.rs;
         }

         public Object getNext() {
            if (this.rs().next()) {
               return this.$outer.org$apache$spark$rdd$JdbcRDD$$mapRow.apply(this.rs());
            } else {
               this.finished_$eq(true);
               return null;
            }
         }

         public void close() {
            try {
               if (this.rs() != null) {
                  this.rs().close();
               }
            } catch (Exception var6) {
               this.$outer.logWarning(() -> "Exception closing resultset", var6);
            }

            try {
               if (this.stmt() != null) {
                  this.stmt().close();
               }
            } catch (Exception var5) {
               this.$outer.logWarning(() -> "Exception closing statement", var5);
            }

            try {
               if (this.conn() != null) {
                  this.conn().close();
               }

               this.$outer.logInfo(() -> "closed connection");
            } catch (Exception var4) {
               this.$outer.logWarning(() -> "Exception closing connection", var4);
            }

         }

         // $FF: synthetic method
         public static final void $anonfun$new$1(final Object $this, final TaskContext context) {
            $this.closeIfNeeded();
         }

         public {
            if (JdbcRDD.this == null) {
               throw null;
            } else {
               this.$outer = JdbcRDD.this;
               context$1.addTaskCompletionListener((Function1)((context) -> {
                  $anonfun$new$1(this, context);
                  return BoxedUnit.UNIT;
               }));
               this.part = (JdbcPartition)thePart$1;
               this.conn = (Connection)JdbcRDD.this.org$apache$spark$rdd$JdbcRDD$$getConnection.apply();
               this.stmt = this.conn().prepareStatement(JdbcRDD.this.org$apache$spark$rdd$JdbcRDD$$sql, 1003, 1007);
               this.url = this.conn().getMetaData().getURL();
               if (this.url().startsWith("jdbc:mysql:")) {
                  this.stmt().setFetchSize(Integer.MIN_VALUE);
               } else {
                  this.stmt().setFetchSize(100);
               }

               JdbcRDD.this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"statement fetch size set to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FETCH_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.stmt().getFetchSize()))})))));
               this.stmt().setLong(1, this.part().lower());
               this.stmt().setLong(2, this.part().upper());
               this.rs = this.stmt().executeQuery();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   public static final JdbcPartition $anonfun$getPartitions$1(final JdbcRDD $this, final BigInt length$1, final int i) {
      BigInt start = scala.math.BigInt..MODULE$.long2bigInt($this.lowerBound).$plus(scala.math.BigInt..MODULE$.int2bigInt(i).$times(length$1).$div(scala.math.BigInt..MODULE$.int2bigInt($this.numPartitions)));
      BigInt end = scala.math.BigInt..MODULE$.long2bigInt($this.lowerBound).$plus(scala.math.BigInt..MODULE$.int2bigInt(i + 1).$times(length$1).$div(scala.math.BigInt..MODULE$.int2bigInt($this.numPartitions))).$minus(scala.math.BigInt..MODULE$.int2bigInt(1));
      return new JdbcPartition(i, start.toLong(), end.toLong());
   }

   public JdbcRDD(final SparkContext sc, final Function0 getConnection, final String sql, final long lowerBound, final long upperBound, final int numPartitions, final Function1 mapRow, final ClassTag evidence$1) {
      super(sc, scala.collection.immutable.Nil..MODULE$, evidence$1);
      this.org$apache$spark$rdd$JdbcRDD$$getConnection = getConnection;
      this.org$apache$spark$rdd$JdbcRDD$$sql = sql;
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
      this.numPartitions = numPartitions;
      this.org$apache$spark$rdd$JdbcRDD$$mapRow = mapRow;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface ConnectionFactory extends Serializable {
      Connection getConnection() throws Exception;
   }
}
