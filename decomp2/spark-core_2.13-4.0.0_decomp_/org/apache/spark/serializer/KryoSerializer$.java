package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.io.FileCommitProtocol;
import org.apache.spark.scheduler.CompressedMapStatus;
import org.apache.spark.scheduler.HighlyCompressedMapStatus;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.BoundedPriorityQueue;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.CompactBuffer;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.roaringbitmap.RoaringBitmap;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;

public final class KryoSerializer$ implements Serializable {
   public static final KryoSerializer$ MODULE$ = new KryoSerializer$();
   private static Seq org$apache$spark$serializer$KryoSerializer$$loadableSparkClasses;
   private static final Seq org$apache$spark$serializer$KryoSerializer$$toRegister;
   private static final Map org$apache$spark$serializer$KryoSerializer$$toRegisterSerializer;
   private static volatile boolean bitmap$0;

   static {
      org$apache$spark$serializer$KryoSerializer$$toRegister = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{ByteBuffer.allocate(1).getClass(), ByteBuffer[].class, StorageLevel.class, CompressedMapStatus.class, HighlyCompressedMapStatus.class, ChunkedByteBuffer.class, CompactBuffer.class, BlockManagerId.class, boolean[].class, byte[].class, short[].class, int[].class, long[].class, float[].class, double[].class, char[].class, String[].class, String[][].class, BoundedPriorityQueue.class, SparkConf.class, FileCommitProtocol.TaskCommitMessage.class, SerializedLambda.class, BitSet.class})));
      org$apache$spark$serializer$KryoSerializer$$toRegisterSerializer = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(RoaringBitmap.class), new com.esotericsoftware.kryo.Serializer() {
         public void write(final Kryo kryo, final Output output, final RoaringBitmap bitmap) {
            bitmap.serialize(new KryoOutputObjectOutputBridge(kryo, output));
         }

         public RoaringBitmap read(final Kryo kryo, final Input input, final Class cls) {
            RoaringBitmap ret = new RoaringBitmap();
            ret.deserialize(new KryoInputObjectInputBridge(kryo, input));
            return ret;
         }
      })})));
   }

   public Seq org$apache$spark$serializer$KryoSerializer$$toRegister() {
      return org$apache$spark$serializer$KryoSerializer$$toRegister;
   }

   public Map org$apache$spark$serializer$KryoSerializer$$toRegisterSerializer() {
      return org$apache$spark$serializer$KryoSerializer$$toRegisterSerializer;
   }

   private Seq loadableSparkClasses$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            org$apache$spark$serializer$KryoSerializer$$loadableSparkClasses = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"org.apache.spark.sql.catalyst.expressions.BoundReference", "org.apache.spark.sql.catalyst.expressions.SortOrder", "[Lorg.apache.spark.sql.catalyst.expressions.SortOrder;", "org.apache.spark.sql.catalyst.InternalRow", "org.apache.spark.sql.catalyst.InternalRow$", "[Lorg.apache.spark.sql.catalyst.InternalRow;", "org.apache.spark.sql.catalyst.expressions.UnsafeRow", "org.apache.spark.sql.catalyst.expressions.UnsafeArrayData", "org.apache.spark.sql.catalyst.expressions.UnsafeMapData", "org.apache.spark.sql.catalyst.expressions.codegen.LazilyGeneratedOrdering", "org.apache.spark.sql.catalyst.expressions.Ascending$", "org.apache.spark.sql.catalyst.expressions.NullsFirst$", "org.apache.spark.sql.catalyst.trees.Origin", "org.apache.spark.sql.types.IntegerType", "org.apache.spark.sql.types.IntegerType$", "org.apache.spark.sql.types.LongType$", "org.apache.spark.sql.types.DoubleType", "org.apache.spark.sql.types.DoubleType$", "org.apache.spark.sql.types.Metadata", "org.apache.spark.sql.types.StringType$", "org.apache.spark.sql.types.StructField", "[Lorg.apache.spark.sql.types.StructField;", "org.apache.spark.sql.types.StructType", "[Lorg.apache.spark.sql.types.StructType;", "org.apache.spark.sql.types.DateType$", "org.apache.spark.sql.types.DecimalType", "org.apache.spark.sql.types.Decimal$DecimalAsIfIntegral$", "org.apache.spark.sql.types.Decimal$DecimalIsFractional$", "org.apache.spark.sql.execution.command.PartitionStatistics", "org.apache.spark.sql.execution.datasources.BasicWriteTaskStats", "org.apache.spark.sql.execution.datasources.ExecutedWriteSummary", "org.apache.spark.sql.execution.datasources.WriteTaskResult", "org.apache.spark.sql.execution.datasources.v2.DataWritingSparkTaskResult", "org.apache.spark.sql.execution.joins.EmptyHashedRelation$", "org.apache.spark.sql.execution.joins.LongHashedRelation", "org.apache.spark.sql.execution.joins.LongToUnsafeRowMap", "org.apache.spark.sql.execution.joins.UnsafeHashedRelation", "org.apache.spark.ml.attribute.Attribute", "org.apache.spark.ml.attribute.AttributeGroup", "org.apache.spark.ml.attribute.BinaryAttribute", "org.apache.spark.ml.attribute.NominalAttribute", "org.apache.spark.ml.attribute.NumericAttribute", "org.apache.spark.ml.feature.Instance", "org.apache.spark.ml.feature.InstanceBlock", "org.apache.spark.ml.feature.LabeledPoint", "org.apache.spark.ml.feature.OffsetInstance", "org.apache.spark.ml.linalg.DenseMatrix", "org.apache.spark.ml.linalg.DenseVector", "org.apache.spark.ml.linalg.Matrix", "org.apache.spark.ml.linalg.SparseMatrix", "org.apache.spark.ml.linalg.SparseVector", "org.apache.spark.ml.linalg.Vector", "org.apache.spark.ml.stat.distribution.MultivariateGaussian", "org.apache.spark.ml.tree.impl.TreePoint", "org.apache.spark.mllib.clustering.VectorWithNorm", "org.apache.spark.mllib.linalg.DenseMatrix", "org.apache.spark.mllib.linalg.DenseVector", "org.apache.spark.mllib.linalg.Matrix", "org.apache.spark.mllib.linalg.SparseMatrix", "org.apache.spark.mllib.linalg.SparseVector", "org.apache.spark.mllib.linalg.Vector", "org.apache.spark.mllib.regression.LabeledPoint", "org.apache.spark.mllib.stat.distribution.MultivariateGaussian"}))).flatMap((name) -> {
               Object var10000;
               try {
                  var10000 = new Some(Utils$.MODULE$.classForName(name, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
               } catch (Throwable var5) {
                  if (var5 != null) {
                     Option var4 = scala.util.control.NonFatal..MODULE$.unapply(var5);
                     if (!var4.isEmpty()) {
                        var10000 = scala.None..MODULE$;
                        return (IterableOnce)var10000;
                     }
                  }

                  if (!(var5 instanceof NoClassDefFoundError) || !Utils$.MODULE$.isTesting()) {
                     throw var5;
                  }

                  var10000 = scala.None..MODULE$;
               }

               return (IterableOnce)var10000;
            });
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return org$apache$spark$serializer$KryoSerializer$$loadableSparkClasses;
   }

   public Seq org$apache$spark$serializer$KryoSerializer$$loadableSparkClasses() {
      return !bitmap$0 ? this.loadableSparkClasses$lzycompute() : org$apache$spark$serializer$KryoSerializer$$loadableSparkClasses;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KryoSerializer$.class);
   }

   private KryoSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
