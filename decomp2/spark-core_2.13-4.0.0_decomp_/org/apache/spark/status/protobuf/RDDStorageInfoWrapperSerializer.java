package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.RDDStorageInfoWrapper;
import org.apache.spark.status.api.v1.RDDDataDistribution;
import org.apache.spark.status.api.v1.RDDPartitionInfo;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005e3Q\u0001C\u0005\u0001\u0013MAQA\t\u0001\u0005\u0002\u0011BQA\n\u0001\u0005B\u001dBQ\u0001\r\u0001\u0005\u0002EBQ\u0001\u000e\u0001\u0005\nUBQA\u0012\u0001\u0005\n\u001dCQ!\u0013\u0001\u0005\n)CQ!\u0015\u0001\u0005\nI\u0013qD\u0015#E'R|'/Y4f\u0013:4wn\u0016:baB,'oU3sS\u0006d\u0017N_3s\u0015\tQ1\"\u0001\u0005qe>$xNY;g\u0015\taQ\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sON\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\rYBDH\u0007\u0002\u0013%\u0011Q$\u0003\u0002\u000e!J|Go\u001c2vMN+'\u000fR3\u0011\u0005}\u0001S\"A\u0006\n\u0005\u0005Z!!\u0006*E\tN#xN]1hK&sgm\\,sCB\u0004XM]\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tQ\u0005\u0005\u0002\u001c\u0001\u0005I1/\u001a:jC2L'0\u001a\u000b\u0003Q9\u00022!F\u0015,\u0013\tQcCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0016Y%\u0011QF\u0006\u0002\u0005\u0005f$X\rC\u00030\u0005\u0001\u0007a$A\u0003j]B,H/A\u0006eKN,'/[1mSj,GC\u0001\u00103\u0011\u0015\u00194\u00011\u0001)\u0003\u0015\u0011\u0017\u0010^3t\u0003]\u0019XM]5bY&TXM\u0015#E'R|'/Y4f\u0013:4w\u000e\u0006\u00027{A\u0011qG\u000f\b\u00037aJ!!O\u0005\u0002\u0015M#xN]3UsB,7/\u0003\u0002<y\tq!\u000b\u0012#Ti>\u0014\u0018mZ3J]\u001a|'BA\u001d\n\u0011\u0015qD\u00011\u0001@\u0003\u0011IgNZ8\u0011\u0005\u0001+U\"A!\u000b\u0005\t\u001b\u0015A\u0001<2\u0015\t!5\"A\u0002ba&L!aO!\u00023\u0011,7/\u001a:jC2L'0\u001a*E\tN#xN]1hK&sgm\u001c\u000b\u0003\u007f!CQAP\u0003A\u0002Y\na\u0004Z3tKJL\u0017\r\\5{KJ#E\tR1uC\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u0015\u0005-s\u0005C\u0001!M\u0013\ti\u0015IA\nS\t\u0012#\u0015\r^1ESN$(/\u001b2vi&|g\u000eC\u0003?\r\u0001\u0007q\n\u0005\u00028!&\u0011Q\nP\u0001\u001cI\u0016\u001cXM]5bY&TXM\u0015#E!\u0006\u0014H/\u001b;j_:LeNZ8\u0015\u0005M3\u0006C\u0001!U\u0013\t)\u0016I\u0001\tS\t\u0012\u0003\u0016M\u001d;ji&|g.\u00138g_\")ah\u0002a\u0001/B\u0011q\u0007W\u0005\u0003+r\u0002"
)
public class RDDStorageInfoWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final RDDStorageInfoWrapper input) {
      StoreTypes.RDDStorageInfoWrapper.Builder builder = StoreTypes.RDDStorageInfoWrapper.newBuilder();
      builder.setInfo(this.serializeRDDStorageInfo(input.info()));
      return builder.build().toByteArray();
   }

   public RDDStorageInfoWrapper deserialize(final byte[] bytes) {
      StoreTypes.RDDStorageInfoWrapper wrapper = StoreTypes.RDDStorageInfoWrapper.parseFrom(bytes);
      return new RDDStorageInfoWrapper(this.deserializeRDDStorageInfo(wrapper.getInfo()));
   }

   private StoreTypes.RDDStorageInfo serializeRDDStorageInfo(final RDDStorageInfo info) {
      StoreTypes.RDDStorageInfo.Builder builder = StoreTypes.RDDStorageInfo.newBuilder();
      builder.setId(info.id());
      Utils$.MODULE$.setStringField(info.name(), (value) -> builder.setName(value));
      builder.setNumPartitions(info.numPartitions());
      builder.setNumCachedPartitions(info.numCachedPartitions());
      Utils$.MODULE$.setStringField(info.storageLevel(), (value) -> builder.setStorageLevel(value));
      builder.setMemoryUsed(info.memoryUsed());
      builder.setDiskUsed(info.diskUsed());
      if (info.dataDistribution().isDefined()) {
         ((IterableOnceOps)info.dataDistribution().get()).foreach((dd) -> {
            StoreTypes.RDDDataDistribution.Builder dataDistributionBuilder = StoreTypes.RDDDataDistribution.newBuilder();
            Utils$.MODULE$.setStringField(dd.address(), (value) -> dataDistributionBuilder.setAddress(value));
            dataDistributionBuilder.setMemoryUsed(dd.memoryUsed());
            dataDistributionBuilder.setMemoryRemaining(dd.memoryRemaining());
            dataDistributionBuilder.setDiskUsed(dd.diskUsed());
            dd.onHeapMemoryUsed().foreach((value) -> $anonfun$serializeRDDStorageInfo$5(dataDistributionBuilder, BoxesRunTime.unboxToLong(value)));
            dd.offHeapMemoryUsed().foreach((value) -> $anonfun$serializeRDDStorageInfo$6(dataDistributionBuilder, BoxesRunTime.unboxToLong(value)));
            dd.onHeapMemoryRemaining().foreach((value) -> $anonfun$serializeRDDStorageInfo$7(dataDistributionBuilder, BoxesRunTime.unboxToLong(value)));
            dd.offHeapMemoryRemaining().foreach((value) -> $anonfun$serializeRDDStorageInfo$8(dataDistributionBuilder, BoxesRunTime.unboxToLong(value)));
            return builder.addDataDistribution(dataDistributionBuilder.build());
         });
      }

      if (info.partitions().isDefined()) {
         ((IterableOnceOps)info.partitions().get()).foreach((p) -> {
            StoreTypes.RDDPartitionInfo.Builder partitionsBuilder = StoreTypes.RDDPartitionInfo.newBuilder();
            Utils$.MODULE$.setStringField(p.blockName(), (value) -> partitionsBuilder.setBlockName(value));
            Utils$.MODULE$.setStringField(p.storageLevel(), (value) -> partitionsBuilder.setStorageLevel(value));
            partitionsBuilder.setMemoryUsed(p.memoryUsed());
            partitionsBuilder.setDiskUsed(p.diskUsed());
            p.executors().foreach((value) -> partitionsBuilder.addExecutors(value));
            return builder.addPartitions(partitionsBuilder.build());
         });
      }

      return builder.build();
   }

   private RDDStorageInfo deserializeRDDStorageInfo(final StoreTypes.RDDStorageInfo info) {
      return new RDDStorageInfo(info.getId(), Utils$.MODULE$.getStringField(info.hasName(), () -> info.getName()), info.getNumPartitions(), info.getNumCachedPartitions(), Utils$.MODULE$.getStringField(info.hasStorageLevel(), () -> info.getStorageLevel()), info.getMemoryUsed(), info.getDiskUsed(), (Option)(info.getDataDistributionList().isEmpty() ? .MODULE$ : new Some(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(info.getDataDistributionList()).asScala().map((infox) -> this.deserializeRDDDataDistribution(infox)))), new Some(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(info.getPartitionsList()).asScala().map((infox) -> this.deserializeRDDPartitionInfo(infox))));
   }

   private RDDDataDistribution deserializeRDDDataDistribution(final StoreTypes.RDDDataDistribution info) {
      return new RDDDataDistribution(Utils$.MODULE$.getStringField(info.hasAddress(), () -> info.getAddress()), info.getMemoryUsed(), info.getMemoryRemaining(), info.getDiskUsed(), Utils$.MODULE$.getOptional(info.hasOnHeapMemoryUsed(), (JFunction0.mcJ.sp)() -> info.getOnHeapMemoryUsed()), Utils$.MODULE$.getOptional(info.hasOffHeapMemoryUsed(), (JFunction0.mcJ.sp)() -> info.getOffHeapMemoryUsed()), Utils$.MODULE$.getOptional(info.hasOnHeapMemoryRemaining(), (JFunction0.mcJ.sp)() -> info.getOnHeapMemoryRemaining()), Utils$.MODULE$.getOptional(info.hasOffHeapMemoryRemaining(), (JFunction0.mcJ.sp)() -> info.getOffHeapMemoryRemaining()));
   }

   private RDDPartitionInfo deserializeRDDPartitionInfo(final StoreTypes.RDDPartitionInfo info) {
      return new RDDPartitionInfo(Utils$.MODULE$.getStringField(info.hasBlockName(), () -> info.getBlockName()), Utils$.MODULE$.getStringField(info.hasStorageLevel(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(info.getStorageLevel())), info.getMemoryUsed(), info.getDiskUsed(), scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(info.getExecutorsList()).asScala());
   }

   // $FF: synthetic method
   public static final StoreTypes.RDDDataDistribution.Builder $anonfun$serializeRDDStorageInfo$5(final StoreTypes.RDDDataDistribution.Builder dataDistributionBuilder$1, final long value) {
      return dataDistributionBuilder$1.setOnHeapMemoryUsed(value);
   }

   // $FF: synthetic method
   public static final StoreTypes.RDDDataDistribution.Builder $anonfun$serializeRDDStorageInfo$6(final StoreTypes.RDDDataDistribution.Builder dataDistributionBuilder$1, final long value) {
      return dataDistributionBuilder$1.setOffHeapMemoryUsed(value);
   }

   // $FF: synthetic method
   public static final StoreTypes.RDDDataDistribution.Builder $anonfun$serializeRDDStorageInfo$7(final StoreTypes.RDDDataDistribution.Builder dataDistributionBuilder$1, final long value) {
      return dataDistributionBuilder$1.setOnHeapMemoryRemaining(value);
   }

   // $FF: synthetic method
   public static final StoreTypes.RDDDataDistribution.Builder $anonfun$serializeRDDStorageInfo$8(final StoreTypes.RDDDataDistribution.Builder dataDistributionBuilder$1, final long value) {
      return dataDistributionBuilder$1.setOffHeapMemoryRemaining(value);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
