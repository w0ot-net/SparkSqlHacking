package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.storage.RDDInfo;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.Map;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c\u0001B\u000e\u001d\t\u0015B\u0001B\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\te\u0001\u0011\t\u0011)A\u0005Y!A1\u0007\u0001B\u0001B\u0003%A\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0004=\u0001\u0001\u0007I\u0011A\u001f\t\u000f\u0011\u0003\u0001\u0019!C\u0001\u000b\"11\n\u0001Q!\nyBq\u0001\u0014\u0001A\u0002\u0013\u0005Q\bC\u0004N\u0001\u0001\u0007I\u0011\u0001(\t\rA\u0003\u0001\u0015)\u0003?\u0011\u001d\t\u0006A1A\u0005\nICaA\u0018\u0001!\u0002\u0013\u0019\u0006bB0\u0001\u0005\u0004%I\u0001\u0019\u0005\u0007Y\u0002\u0001\u000b\u0011B1\t\u000f5\u0004!\u0019!C\u0005]\"1!\u000f\u0001Q\u0001\n=Dqa\u001d\u0001C\u0002\u0013%A\u000f\u0003\u0004z\u0001\u0001\u0006I!\u001e\u0005\u0006u\u0002!\ta\u001f\u0005\u0006}\u0002!\ta \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\t\t\u0002\u0001C\u0001\u0003'Aq!!\b\u0001\t\u0003\ty\u0002C\u0004\u0002*\u0001!\t!a\u000b\t\u000f\u0005U\u0002\u0001\"\u0001\u00028!9\u00111\b\u0001\u0005R\u0005u\"a\u0002'jm\u0016\u0014F\t\u0012\u0006\u0003;y\taa\u001d;biV\u001c(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\n\t\u0003O!j\u0011\u0001H\u0005\u0003Sq\u0011!\u0002T5wK\u0016sG/\u001b;z\u0003\u0011IgNZ8\u0016\u00031\u0002\"!\f\u0019\u000e\u00039R!a\f\u0010\u0002\u000fM$xN]1hK&\u0011\u0011G\f\u0002\b%\u0012#\u0015J\u001c4p\u0003\u0015IgNZ8!\u00031\u0019Ho\u001c:bO\u0016dUM^3m!\tiS'\u0003\u00027]\ta1\u000b^8sC\u001e,G*\u001a<fY\u00061A(\u001b8jiz\"2!\u000f\u001e<!\t9\u0003\u0001C\u0003+\t\u0001\u0007A\u0006C\u00034\t\u0001\u0007A'\u0001\u0006nK6|'/_+tK\u0012,\u0012A\u0010\t\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0002\u0003\u0006)1oY1mC&\u00111\t\u0011\u0002\u0005\u0019>tw-\u0001\bnK6|'/_+tK\u0012|F%Z9\u0015\u0005\u0019K\u0005CA H\u0013\tA\u0005I\u0001\u0003V]&$\bb\u0002&\u0007\u0003\u0003\u0005\rAP\u0001\u0004q\u0012\n\u0014aC7f[>\u0014\u00180V:fI\u0002\n\u0001\u0002Z5tWV\u001bX\rZ\u0001\rI&\u001c8.V:fI~#S-\u001d\u000b\u0003\r>CqAS\u0005\u0002\u0002\u0003\u0007a(A\u0005eSN\\Wk]3eA\u0005\u0001B.\u001a<fY\u0012+7o\u0019:jaRLwN\\\u000b\u0002'B\u0011Ak\u0017\b\u0003+f\u0003\"A\u0016!\u000e\u0003]S!\u0001\u0017\u0013\u0002\rq\u0012xn\u001c;?\u0013\tQ\u0006)\u0001\u0004Qe\u0016$WMZ\u0005\u00039v\u0013aa\u0015;sS:<'B\u0001.A\u0003EaWM^3m\t\u0016\u001c8M]5qi&|g\u000eI\u0001\u000ba\u0006\u0014H/\u001b;j_:\u001cX#A1\u0011\t\t<7+[\u0007\u0002G*\u0011A-Z\u0001\b[V$\u0018M\u00197f\u0015\t1\u0007)\u0001\u0006d_2dWm\u0019;j_:L!\u0001[2\u0003\u000f!\u000b7\u000f['baB\u0011qE[\u0005\u0003Wr\u0011\u0001\u0003T5wKJ#E\tU1si&$\u0018n\u001c8\u0002\u0017A\f'\u000f^5uS>t7\u000fI\u0001\ra\u0006\u0014H/\u001b;j_:\u001cV-]\u000b\u0002_B\u0011q\u0005]\u0005\u0003cr\u0011qB\u0015#E!\u0006\u0014H/\u001b;j_:\u001cV-]\u0001\u000ea\u0006\u0014H/\u001b;j_:\u001cV-\u001d\u0011\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t+\u0005)\b\u0003\u00022h'Z\u0004\"aJ<\n\u0005ad\"a\u0005'jm\u0016\u0014F\t\u0012#jgR\u0014\u0018NY;uS>t\u0017A\u00043jgR\u0014\u0018NY;uS>t7\u000fI\u0001\na\u0006\u0014H/\u001b;j_:$\"!\u001b?\t\u000bu\u001c\u0002\u0019A*\u0002\u0013\tdwnY6OC6,\u0017a\u0004:f[>4X\rU1si&$\u0018n\u001c8\u0015\u0007\u0019\u000b\t\u0001C\u0003~)\u0001\u00071+\u0001\u0007eSN$(/\u001b2vi&|g\u000eF\u0002w\u0003\u000fAq!!\u0003\u0016\u0001\u0004\tY!\u0001\u0003fq\u0016\u001c\u0007cA\u0014\u0002\u000e%\u0019\u0011q\u0002\u000f\u0003\u00191Kg/Z#yK\u000e,Ho\u001c:\u0002%I,Wn\u001c<f\t&\u001cHO]5ckRLwN\u001c\u000b\u0005\u0003+\tY\u0002E\u0002@\u0003/I1!!\u0007A\u0005\u001d\u0011un\u001c7fC:Dq!!\u0003\u0017\u0001\u0004\tY!A\beSN$(/\u001b2vi&|gn\u00149u)\u0011\t\t#a\n\u0011\t}\n\u0019C^\u0005\u0004\u0003K\u0001%AB(qi&|g\u000eC\u0004\u0002\n]\u0001\r!a\u0003\u0002\u001b\u001d,G\u000fU1si&$\u0018n\u001c8t)\t\ti\u0003\u0005\u0004\u00020\u0005E2+[\u0007\u0002K&\u0019\u00111G3\u0003\u00075\u000b\u0007/\u0001\thKR$\u0015n\u001d;sS\n,H/[8ogR\u0011\u0011\u0011\b\t\u0007\u0003_\t\td\u0015<\u0002\u0011\u0011|W\u000b\u001d3bi\u0016$\"!a\u0010\u0011\u0007}\n\t%C\u0002\u0002D\u0001\u00131!\u00118z\u0001"
)
public class LiveRDD extends LiveEntity {
   private final RDDInfo info;
   private final StorageLevel storageLevel;
   private long memoryUsed;
   private long diskUsed;
   private final String levelDescription;
   private final HashMap partitions;
   private final RDDPartitionSeq partitionSeq;
   private final HashMap distributions;

   public RDDInfo info() {
      return this.info;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public void memoryUsed_$eq(final long x$1) {
      this.memoryUsed = x$1;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public void diskUsed_$eq(final long x$1) {
      this.diskUsed = x$1;
   }

   private String levelDescription() {
      return this.levelDescription;
   }

   private HashMap partitions() {
      return this.partitions;
   }

   private RDDPartitionSeq partitionSeq() {
      return this.partitionSeq;
   }

   private HashMap distributions() {
      return this.distributions;
   }

   public LiveRDDPartition partition(final String blockName) {
      return (LiveRDDPartition)this.partitions().getOrElseUpdate(blockName, () -> {
         LiveRDDPartition part = new LiveRDDPartition(blockName, this.storageLevel);
         part.update(.MODULE$, 0L, 0L);
         this.partitionSeq().addPartition(part);
         return part;
      });
   }

   public void removePartition(final String blockName) {
      this.partitions().remove(blockName).foreach((part) -> {
         $anonfun$removePartition$1(this, part);
         return BoxedUnit.UNIT;
      });
   }

   public LiveRDDDistribution distribution(final LiveExecutor exec) {
      return (LiveRDDDistribution)this.distributions().getOrElseUpdate(exec.executorId(), () -> new LiveRDDDistribution(exec));
   }

   public boolean removeDistribution(final LiveExecutor exec) {
      return this.distributions().remove(exec.executorId()).isDefined();
   }

   public Option distributionOpt(final LiveExecutor exec) {
      return this.distributions().get(exec.executorId());
   }

   public Map getPartitions() {
      return this.partitions();
   }

   public Map getDistributions() {
      return this.distributions();
   }

   public Object doUpdate() {
      Option dists = (Option)(this.distributions().nonEmpty() ? new Some(((IterableOnceOps)this.distributions().values().map((x$7) -> x$7.toApi())).toSeq()) : scala.None..MODULE$);
      RDDStorageInfo rdd = new RDDStorageInfo(this.info().id(), this.info().name(), this.info().numPartitions(), this.partitions().size(), this.levelDescription(), this.memoryUsed(), this.diskUsed(), dists, new Some(this.partitionSeq()));
      return new RDDStorageInfoWrapper(rdd);
   }

   // $FF: synthetic method
   public static final void $anonfun$removePartition$1(final LiveRDD $this, final LiveRDDPartition part) {
      $this.partitionSeq().removePartition(part);
   }

   public LiveRDD(final RDDInfo info, final StorageLevel storageLevel) {
      this.info = info;
      this.storageLevel = storageLevel;
      this.memoryUsed = 0L;
      this.diskUsed = 0L;
      this.levelDescription = Utils$.MODULE$.weakIntern(storageLevel.description());
      this.partitions = new HashMap();
      this.partitionSeq = new RDDPartitionSeq();
      this.distributions = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
