package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.BlockManager$;
import org.apache.spark.storage.BlockResult;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!B\t\u0013\u0001QQ\u0002\u0002C\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\t\u0011Q\u0002!Q1A\u0005\u0002UB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\t\t\u0002\u0011\u0019\u0011)A\u0006\u000b\")1\n\u0001C\u0001\u0019\"A!\u000b\u0001EC\u0002\u0013\u00051\u000bC\u0004m\u0001\u0001\u0007I\u0011B7\t\u000fE\u0004\u0001\u0019!C\u0005e\"1\u0001\u0010\u0001Q!\n9DQ! \u0001\u0005ByDq!a\u0002\u0001\t\u0003\nI\u0001C\u0004\u0002 \u0001!\t%!\t\t\u0011\u0005\u0015\u0002\u0001\"\u0001\u0015\u0003OAq!!\u000b\u0001\t\u0003!R\u000e\u0003\u0005\u0002,\u0001!\t\u0001FA\u0014\u0011\u001d\ti\u0003\u0001C\t\u0003_\u0011\u0001B\u00117pG.\u0014F\t\u0012\u0006\u0003'Q\t1A\u001d3e\u0015\t)b#A\u0003ta\u0006\u00148N\u0003\u0002\u00181\u00051\u0011\r]1dQ\u0016T\u0011!G\u0001\u0004_J<WCA\u000e#'\t\u0001A\u0004E\u0002\u001e=\u0001j\u0011AE\u0005\u0003?I\u00111A\u0015#E!\t\t#\u0005\u0004\u0001\u0005\u000b\r\u0002!\u0019A\u0013\u0003\u0003Q\u001b\u0001!\u0005\u0002'YA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t9aj\u001c;iS:<\u0007CA\u0014.\u0013\tq\u0003FA\u0002B]f\f!a]2\u0011\u0005E\u0012T\"\u0001\u000b\n\u0005M\"\"\u0001D*qCJ\\7i\u001c8uKb$\u0018\u0001\u00032m_\u000e\\\u0017\nZ:\u0016\u0003Y\u00022aJ\u001c:\u0013\tA\u0004FA\u0003BeJ\f\u0017\u0010\u0005\u0002;{5\t1H\u0003\u0002=)\u000591\u000f^8sC\u001e,\u0017B\u0001 <\u0005\u001d\u0011En\\2l\u0013\u0012\f\u0011B\u00197pG.LEm\u001d\u0011)\u0005\r\t\u0005CA\u0014C\u0013\t\u0019\u0005FA\u0005ue\u0006t7/[3oi\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u0019K\u0005%D\u0001H\u0015\tA\u0005&A\u0004sK\u001adWm\u0019;\n\u0005);%\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\ri\u0005+\u0015\u000b\u0003\u001d>\u00032!\b\u0001!\u0011\u0015!U\u0001q\u0001F\u0011\u0015yS\u00011\u00011\u0011\u0015!T\u00011\u00017\u0003)yFn\\2bi&|gn]\u000b\u0002)B!Q\u000bX\u001d`\u001d\t1&\f\u0005\u0002XQ5\t\u0001L\u0003\u0002ZI\u00051AH]8pizJ!a\u0017\u0015\u0002\rA\u0013X\rZ3g\u0013\tifLA\u0002NCBT!a\u0017\u0015\u0011\u0007\u0001,\u0007N\u0004\u0002bG:\u0011qKY\u0005\u0002S%\u0011A\rK\u0001\ba\u0006\u001c7.Y4f\u0013\t1wMA\u0002TKFT!\u0001\u001a\u0015\u0011\u0005UK\u0017B\u00016_\u0005\u0019\u0019FO]5oO\"\u0012a!Q\u0001\t?&\u001ch+\u00197jIV\ta\u000e\u0005\u0002(_&\u0011\u0001\u000f\u000b\u0002\b\u0005>|G.Z1o\u00031y\u0016n\u001d,bY&$w\fJ3r)\t\u0019h\u000f\u0005\u0002(i&\u0011Q\u000f\u000b\u0002\u0005+:LG\u000fC\u0004x\u0011\u0005\u0005\t\u0019\u00018\u0002\u0007a$\u0013'A\u0005`SN4\u0016\r\\5eA!\u0012\u0011B\u001f\t\u0003OmL!\u0001 \u0015\u0003\u0011Y|G.\u0019;jY\u0016\fQbZ3u!\u0006\u0014H/\u001b;j_:\u001cX#A@\u0011\t\u001d:\u0014\u0011\u0001\t\u0004c\u0005\r\u0011bAA\u0003)\tI\u0001+\u0019:uSRLwN\\\u0001\bG>l\u0007/\u001e;f)\u0019\tY!!\u0005\u0002\u0016A!\u0001-!\u0004!\u0013\r\tya\u001a\u0002\t\u0013R,'/\u0019;pe\"9\u00111C\u0006A\u0002\u0005\u0005\u0011!B:qY&$\bbBA\f\u0017\u0001\u0007\u0011\u0011D\u0001\bG>tG/\u001a=u!\r\t\u00141D\u0005\u0004\u0003;!\"a\u0003+bg.\u001cuN\u001c;fqR\fQcZ3u!J,g-\u001a:sK\u0012dunY1uS>t7\u000fF\u0002`\u0003GAq!a\u0005\r\u0001\u0004\t\t!\u0001\u0007sK6|g/\u001a\"m_\u000e\\7\u000fF\u0001t\u0003\u001dI7OV1mS\u0012\f1\"Y:tKJ$h+\u00197jI\u0006\u0019r-\u001a;CY>\u001c7.\u00133M_\u000e\fG/[8ogR\tA\u000b"
)
public class BlockRDD extends RDD {
   private transient Map _locations;
   private final transient BlockId[] blockIds;
   private final ClassTag evidence$1;
   private volatile boolean _isValid;
   private transient volatile boolean bitmap$trans$0;

   public BlockId[] blockIds() {
      return this.blockIds;
   }

   private Map _locations$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this._locations = BlockManager$.MODULE$.blockIdsToLocations(this.blockIds(), SparkEnv$.MODULE$.get(), BlockManager$.MODULE$.blockIdsToLocations$default$3());
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this._locations;
   }

   public Map _locations() {
      return !this.bitmap$trans$0 ? this._locations$lzycompute() : this._locations;
   }

   private boolean _isValid() {
      return this._isValid;
   }

   private void _isValid_$eq(final boolean x$1) {
      this._isValid = x$1;
   }

   public Partition[] getPartitions() {
      this.assertValid();
      return (Partition[]).MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.blockIds())).map((i) -> $anonfun$getPartitions$1(this, BoxesRunTime.unboxToInt(i))).toArray(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      this.assertValid();
      BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
      BlockId blockId = ((BlockRDDPartition)split).blockId();
      Option var6 = blockManager.get(blockId, this.evidence$1);
      if (var6 instanceof Some var7) {
         BlockResult block = (BlockResult)var7.value();
         return block.data();
      } else if (scala.None..MODULE$.equals(var6)) {
         throw SparkCoreErrors$.MODULE$.rddBlockNotFoundError(blockId, this.id());
      } else {
         throw new MatchError(var6);
      }
   }

   public Seq getPreferredLocations(final Partition split) {
      this.assertValid();
      return (Seq)this._locations().apply(((BlockRDDPartition)split).blockId());
   }

   public void removeBlocks() {
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.blockIds()), (blockId) -> {
         $anonfun$removeBlocks$1(this, blockId);
         return BoxedUnit.UNIT;
      });
      this._isValid_$eq(false);
   }

   public boolean isValid() {
      return this._isValid();
   }

   public void assertValid() {
      if (!this.isValid()) {
         throw SparkCoreErrors$.MODULE$.blockHaveBeenRemovedError(this.toString());
      }
   }

   public Map getBlockIdLocations() {
      return this._locations();
   }

   // $FF: synthetic method
   public static final Partition $anonfun$getPartitions$1(final BlockRDD $this, final int i) {
      return new BlockRDDPartition($this.blockIds()[i], i);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeBlocks$1(final BlockRDD $this, final BlockId blockId) {
      $this.sparkContext().env().blockManager().master().removeBlock(blockId);
   }

   public BlockRDD(final SparkContext sc, final BlockId[] blockIds, final ClassTag evidence$1) {
      super(sc, scala.collection.immutable.Nil..MODULE$, evidence$1);
      this.blockIds = blockIds;
      this.evidence$1 = evidence$1;
      this._isValid = true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
