package org.apache.spark.streaming.rdd;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.BlockRDD;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.util.FileBasedWriteAheadLogSegment;
import org.apache.spark.streaming.util.HdfsUtils$;
import org.apache.spark.streaming.util.WriteAheadLog;
import org.apache.spark.streaming.util.WriteAheadLogRecordHandle;
import org.apache.spark.streaming.util.WriteAheadLogUtils$;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Option;
import scala.StringContext;
import scala.Array.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg!B\u000e\u001d\u0001y1\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011\u0005\u0003!Q1A\u0005\n\tC\u0011\u0002\u0014\u0001\u0003\u0002\u0003\u0006IaQ'\t\u0011M\u0003!Q1A\u0005\u0002QC\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I!\u0016\u0005\t=\u0002\u0011)\u0019!C\u0005?\"AA\r\u0001B\u0001B\u0003%\u0001\r\u0003\u0005g\u0001\t\u0005\t\u0015!\u0003b\u0011!9\u0007A!A!\u0002\u0013A\u0007\u0002C6\u0001\u0005\u0007\u0005\u000b1\u00027\t\u000bI\u0004A\u0011A:\t\u000fy\u0004!\u0019!C\u0005\u007f\"A\u0011\u0011\u0003\u0001!\u0002\u0013\t\t\u0001C\u0005\u0002\u0016\u0001\u0011\r\u0011\"\u0003\u0002\u0018!A\u00111\u0005\u0001!\u0002\u0013\tI\u0002C\u0004\u0002&\u0001!\t%a\n\t\u000f\u0005%\u0002\u0001\"\u0011\u0002,!9\u0011Q\u0007\u0001\u0005B\u0005]\u0002bBA0\u0001\u0011\u0005\u0013\u0011M\u0004\u000b\u0003wb\u0012\u0011!E\u0001=\u0005ud!C\u000e\u001d\u0003\u0003E\tAHA@\u0011\u0019\u0011X\u0003\"\u0001\u0002\u0018\"I\u0011\u0011T\u000b\u0012\u0002\u0013\u0005\u00111\u0014\u0005\n\u0003k+\u0012\u0013!C\u0001\u0003oC\u0011\"a0\u0016#\u0003%\t!!1\t\u0013\u0005%W#!A\u0005\n\u0005-'aG,sSR,\u0017\t[3bI2{wMQ1dW\u0016$'\t\\8dWJ#EI\u0003\u0002\u001e=\u0005\u0019!\u000f\u001a3\u000b\u0005}\u0001\u0013!C:ue\u0016\fW.\u001b8h\u0015\t\t#%A\u0003ta\u0006\u00148N\u0003\u0002$I\u00051\u0011\r]1dQ\u0016T\u0011!J\u0001\u0004_J<WCA\u00140'\t\u0001\u0001\u0006E\u0002*W5j\u0011A\u000b\u0006\u0003;\u0001J!\u0001\f\u0016\u0003\u0011\tcwnY6S\t\u0012\u0003\"AL\u0018\r\u0001\u0011)\u0001\u0007\u0001b\u0001e\t\tAk\u0001\u0001\u0012\u0005MJ\u0004C\u0001\u001b8\u001b\u0005)$\"\u0001\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005a*$a\u0002(pi\"Lgn\u001a\t\u0003iiJ!aO\u001b\u0003\u0007\u0005s\u00170\u0001\u0002tGB\u0011ahP\u0007\u0002A%\u0011\u0001\t\t\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\n?\ndwnY6JIN,\u0012a\u0011\t\u0004i\u00113\u0015BA#6\u0005\u0015\t%O]1z!\t9%*D\u0001I\u0015\tI\u0005%A\u0004ti>\u0014\u0018mZ3\n\u0005-C%a\u0002\"m_\u000e\\\u0017\nZ\u0001\u000b?\ndwnY6JIN\u0004\u0013B\u0001(,\u0003!\u0011Gn\\2l\u0013\u0012\u001c\bFA\u0002Q!\t!\u0014+\u0003\u0002Sk\tIAO]1og&,g\u000e^\u0001\u0011o\u0006d'+Z2pe\u0012D\u0015M\u001c3mKN,\u0012!\u0016\t\u0004i\u00113\u0006CA,[\u001b\u0005A&BA-\u001f\u0003\u0011)H/\u001b7\n\u0005mC&!G,sSR,\u0017\t[3bI2{wMU3d_J$\u0007*\u00198eY\u0016\f\u0011c^1m%\u0016\u001cwN\u001d3IC:$G.Z:!Q\t)\u0001+\u0001\bjg\ncwnY6JIZ\u000bG.\u001b3\u0016\u0003\u0001\u00042\u0001\u000e#b!\t!$-\u0003\u0002dk\t9!i\\8mK\u0006t\u0017aD5t\u00052|7m[%e-\u0006d\u0017\u000e\u001a\u0011)\u0005\u001d\u0001\u0016aE:u_J,\u0017J\u001c\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\u0001D:u_J\fw-\u001a'fm\u0016d\u0007CA$j\u0013\tQ\u0007J\u0001\u0007Ti>\u0014\u0018mZ3MKZ,G.\u0001\u0006fm&$WM\\2fIE\u00022!\u001c9.\u001b\u0005q'BA86\u0003\u001d\u0011XM\u001a7fGRL!!\u001d8\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDc\u0002;ysj\\H0 \u000b\u0003k^\u00042A\u001e\u0001.\u001b\u0005a\u0002\"B6\f\u0001\ba\u0007\"\u0002\u001f\f\u0001\u0004i\u0004\"B!\f\u0001\u0004\u0019\u0005\"B*\f\u0001\u0004)\u0006b\u00020\f!\u0003\u0005\r\u0001\u0019\u0005\bM.\u0001\n\u00111\u0001b\u0011\u001d97\u0002%AA\u0002!\fA\u0002[1e_>\u00048i\u001c8gS\u001e,\"!!\u0001\u0011\t\u0005\r\u0011QB\u0007\u0003\u0003\u000bQA!a\u0002\u0002\n\u0005!1m\u001c8g\u0015\r\tYAI\u0001\u0007Q\u0006$wn\u001c9\n\t\u0005=\u0011Q\u0001\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u001b!\fGm\\8q\u0007>tg-[4!Q\ti\u0001+A\u000bce>\fGmY1ti\u0016$\u0007*\u00193p_B\u001cuN\u001c4\u0016\u0005\u0005e\u0001\u0003BA\u000e\u0003?i!!!\b\u000b\u0005e\u0003\u0013\u0002BA\u0011\u0003;\u0011\u0011dU3sS\u0006d\u0017N_1cY\u0016\u001cuN\u001c4jOV\u0014\u0018\r^5p]\u00061\"M]8bI\u000e\f7\u000f^3e\u0011\u0006$wn\u001c9D_:4\u0007%A\u0004jgZ\u000bG.\u001b3\u0016\u0003\u0005\fQbZ3u!\u0006\u0014H/\u001b;j_:\u001cXCAA\u0017!\u0011!D)a\f\u0011\u0007y\n\t$C\u0002\u00024\u0001\u0012\u0011\u0002U1si&$\u0018n\u001c8\u0002\u000f\r|W\u000e];uKR1\u0011\u0011HA)\u0003+\u0002R!a\u000f\u0002L5rA!!\u0010\u0002H9!\u0011qHA#\u001b\t\t\tEC\u0002\u0002DE\na\u0001\u0010:p_Rt\u0014\"\u0001\u001c\n\u0007\u0005%S'A\u0004qC\u000e\\\u0017mZ3\n\t\u00055\u0013q\n\u0002\t\u0013R,'/\u0019;pe*\u0019\u0011\u0011J\u001b\t\u000f\u0005M#\u00031\u0001\u00020\u0005)1\u000f\u001d7ji\"9\u0011q\u000b\nA\u0002\u0005e\u0013aB2p]R,\u0007\u0010\u001e\t\u0004}\u0005m\u0013bAA/A\tYA+Y:l\u0007>tG/\u001a=u\u0003U9W\r\u001e)sK\u001a,'O]3e\u0019>\u001c\u0017\r^5p]N$B!a\u0019\u0002zA1\u00111HA3\u0003SJA!a\u001a\u0002P\t\u00191+Z9\u0011\t\u0005-\u00141\u000f\b\u0005\u0003[\ny\u0007E\u0002\u0002@UJ1!!\u001d6\u0003\u0019\u0001&/\u001a3fM&!\u0011QOA<\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011O\u001b\t\u000f\u0005M3\u00031\u0001\u00020\u0005YrK]5uK\u0006CW-\u00193M_\u001e\u0014\u0015mY6fI\ncwnY6S\t\u0012\u0003\"A^\u000b\u0014\u000bU\t\t)a\"\u0011\u0007Q\n\u0019)C\u0002\u0002\u0006V\u0012a!\u00118z%\u00164\u0007\u0003BAE\u0003'k!!a#\u000b\t\u00055\u0015qR\u0001\u0003S>T!!!%\u0002\t)\fg/Y\u0005\u0005\u0003+\u000bYI\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002~\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*B!!(\u00024V\u0011\u0011q\u0014\u0016\u0004A\u0006\u00056FAAR!\u0011\t)+a,\u000e\u0005\u0005\u001d&\u0002BAU\u0003W\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u00055V'\u0001\u0006b]:|G/\u0019;j_:LA!!-\u0002(\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bA:\"\u0019\u0001\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136+\u0011\tI,!0\u0016\u0005\u0005m&fA1\u0002\"\u0012)\u0001\u0007\u0007b\u0001e\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY*B!a1\u0002HV\u0011\u0011Q\u0019\u0016\u0004Q\u0006\u0005F!\u0002\u0019\u001a\u0005\u0004\u0011\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAg!\u0011\ty-!6\u000e\u0005\u0005E'\u0002BAj\u0003\u001f\u000bA\u0001\\1oO&!\u0011q[Ai\u0005\u0019y%M[3di\u0002"
)
public class WriteAheadLogBackedBlockRDD extends BlockRDD {
   private final transient WriteAheadLogRecordHandle[] walRecordHandles;
   private final transient boolean[] isBlockIdValid;
   private final boolean storeInBlockManager;
   private final StorageLevel storageLevel;
   private final ClassTag evidence$1;
   private final transient Configuration hadoopConfig;
   private final SerializableConfiguration broadcastedHadoopConf;

   public static StorageLevel $lessinit$greater$default$6() {
      return WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$6();
   }

   public static boolean $lessinit$greater$default$5() {
      return WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean[] $lessinit$greater$default$4() {
      return WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$4();
   }

   private BlockId[] _blockIds() {
      return super.blockIds();
   }

   public WriteAheadLogRecordHandle[] walRecordHandles() {
      return this.walRecordHandles;
   }

   private boolean[] isBlockIdValid() {
      return this.isBlockIdValid;
   }

   private Configuration hadoopConfig() {
      return this.hadoopConfig;
   }

   private SerializableConfiguration broadcastedHadoopConf() {
      return this.broadcastedHadoopConf;
   }

   public boolean isValid() {
      return true;
   }

   public Partition[] getPartitions() {
      this.assertValid();
      return (Partition[]).MODULE$.tabulate(this._blockIds().length, (i) -> $anonfun$getPartitions$1(this, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      this.assertValid();
      Configuration hadoopConf = this.broadcastedHadoopConf().value();
      BlockManager blockManager = org.apache.spark.SparkEnv..MODULE$.get().blockManager();
      SerializerManager serializerManager = org.apache.spark.SparkEnv..MODULE$.get().serializerManager();
      WriteAheadLogBackedBlockRDDPartition partition = (WriteAheadLogBackedBlockRDDPartition)split;
      BlockId blockId = partition.blockId();
      return partition.isBlockIdValid() ? (Iterator)this.getBlockFromBlockManager$1(blockManager, blockId).getOrElse(() -> this.getBlockFromWriteAheadLog$1(hadoopConf, partition, blockManager, blockId, serializerManager)) : this.getBlockFromWriteAheadLog$1(hadoopConf, partition, blockManager, blockId, serializerManager);
   }

   public Seq getPreferredLocations(final Partition split) {
      WriteAheadLogBackedBlockRDDPartition partition = (WriteAheadLogBackedBlockRDDPartition)split;
      Option blockLocations = (Option)(partition.isBlockIdValid() ? this.getBlockIdLocations().get(partition.blockId()) : scala.None..MODULE$);
      return (Seq)blockLocations.getOrElse(() -> {
         WriteAheadLogRecordHandle var4 = partition.walRecordHandle();
         if (var4 instanceof FileBasedWriteAheadLogSegment var5) {
            Object var10000;
            try {
               var10000 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(HdfsUtils$.MODULE$.getFileSegmentLocations(var5.path(), var5.offset(), (long)var5.length(), this.hadoopConfig())).toImmutableArraySeq();
            } catch (Throwable var9) {
               if (var9 == null || !scala.util.control.NonFatal..MODULE$.apply(var9)) {
                  throw var9;
               }

               this.logError(() -> "Error getting WAL file segment locations", var9);
               var10000 = (Seq)scala.package..MODULE$.Seq().empty();
            }

            return (Seq)var10000;
         } else {
            return (Seq)scala.package..MODULE$.Seq().empty();
         }
      });
   }

   // $FF: synthetic method
   public static final WriteAheadLogBackedBlockRDDPartition $anonfun$getPartitions$1(final WriteAheadLogBackedBlockRDD $this, final int i) {
      boolean isValid = $this.isBlockIdValid().length == 0 ? true : $this.isBlockIdValid()[i];
      return new WriteAheadLogBackedBlockRDDPartition(i, $this._blockIds()[i], isValid, $this.walRecordHandles()[i]);
   }

   private final Option getBlockFromBlockManager$1(final BlockManager blockManager$1, final BlockId blockId$1) {
      return blockManager$1.get(blockId$1, this.evidence$1).map((x$1) -> x$1.data());
   }

   private final Iterator getBlockFromWriteAheadLog$1(final Configuration hadoopConf$1, final WriteAheadLogBackedBlockRDDPartition partition$1, final BlockManager blockManager$1, final BlockId blockId$1, final SerializerManager serializerManager$1) {
      ByteBuffer dataRead = null;
      WriteAheadLog writeAheadLog = null;

      try {
         String nonExistentDirectory = (new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString())).toURI().toString();
         writeAheadLog = WriteAheadLogUtils$.MODULE$.createLogForReceiver(org.apache.spark.SparkEnv..MODULE$.get().conf(), nonExistentDirectory, hadoopConf$1);
         dataRead = writeAheadLog.read(partition$1.walRecordHandle());
      } catch (Throwable var18) {
         if (var18 != null && scala.util.control.NonFatal..MODULE$.apply(var18)) {
            throw new SparkException("Could not read data from write ahead log record " + partition$1.walRecordHandle(), var18);
         }

         throw var18;
      } finally {
         if (writeAheadLog != null) {
            writeAheadLog.close();
            WriteAheadLog var21 = null;
         }

      }

      if (dataRead == null) {
         throw new SparkException("Could not read data from write ahead log record " + partition$1.walRecordHandle() + ", read returned null");
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Read partition data of ", " from write ahead log, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD..MODULE$, this)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"record handle ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WRITE_AHEAD_LOG_RECORD_HANDLE..MODULE$, partition$1.walRecordHandle())}))))));
         if (this.storeInBlockManager) {
            blockManager$1.putBytes(blockId$1, new ChunkedByteBuffer(dataRead.duplicate()), this.storageLevel, blockManager$1.putBytes$default$4(), this.evidence$1);
            this.logDebug(() -> "Stored partition data of " + this + " into block manager with level " + this.storageLevel);
            dataRead.rewind();
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         ChunkedByteBuffer qual$1 = new ChunkedByteBuffer(dataRead);
         boolean x$1 = qual$1.toInputStream$default$1();
         return serializerManager$1.dataDeserializeStream(blockId$1, qual$1.toInputStream(x$1), this.elementClassTag());
      }
   }

   public WriteAheadLogBackedBlockRDD(final SparkContext sc, final BlockId[] _blockIds, final WriteAheadLogRecordHandle[] walRecordHandles, final boolean[] isBlockIdValid, final boolean storeInBlockManager, final StorageLevel storageLevel, final ClassTag evidence$1) {
      super(sc, _blockIds, evidence$1);
      this.walRecordHandles = walRecordHandles;
      this.isBlockIdValid = isBlockIdValid;
      this.storeInBlockManager = storeInBlockManager;
      this.storageLevel = storageLevel;
      this.evidence$1 = evidence$1;
      scala.Predef..MODULE$.require(_blockIds.length == walRecordHandles.length, () -> {
         int var10000 = this._blockIds().length;
         return "Number of block Ids (" + var10000 + ") must be  same as number of WAL record handles (" + this.walRecordHandles().length + ")";
      });
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.booleanArrayOps(isBlockIdValid)) || isBlockIdValid.length == _blockIds.length, () -> {
         int var10000 = this.isBlockIdValid().length;
         return "Number of elements in isBlockIdValid (" + var10000 + ") must be  same as number of block Ids (" + this._blockIds().length + ")";
      });
      this.hadoopConfig = sc.hadoopConfiguration();
      this.broadcastedHadoopConf = new SerializableConfiguration(this.hadoopConfig());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
