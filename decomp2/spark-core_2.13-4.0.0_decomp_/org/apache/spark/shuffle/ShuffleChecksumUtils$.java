package org.apache.spark.shuffle;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.invoke.SerializedLambda;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.spark.network.shuffle.checksum.ShuffleChecksumHelper;
import org.apache.spark.network.util.LimitedInputStream;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.ShuffleChecksumBlockId;
import org.apache.spark.storage.ShuffleDataBlockId;
import scala.Array.;
import scala.runtime.LongRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

public final class ShuffleChecksumUtils$ {
   public static final ShuffleChecksumUtils$ MODULE$ = new ShuffleChecksumUtils$();

   public String getChecksumFileName(final BlockId blockId, final String algorithm) {
      if (blockId instanceof ShuffleDataBlockId var5) {
         int shuffleId = var5.shuffleId();
         long mapId = var5.mapId();
         return ShuffleChecksumHelper.getChecksumFileName((new ShuffleChecksumBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID())).name(), algorithm);
      } else {
         return null;
      }
   }

   public boolean compareChecksums(final int numPartition, final String algorithm, final File checksum, final File data, final File index) {
      Object var6 = new Object();

      boolean var10000;
      try {
         ObjectRef checksumIn = ObjectRef.create((Object)null);
         long[] expectChecksums = (long[]).MODULE$.ofDim(numPartition, scala.reflect.ClassTag..MODULE$.Long());

         try {
            checksumIn.elem = new DataInputStream(new FileInputStream(checksum));
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numPartition).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> expectChecksums[i] = ((DataInputStream)checksumIn.elem).readLong());
         } finally {
            if ((DataInputStream)checksumIn.elem != null) {
               ((DataInputStream)checksumIn.elem).close();
            }

         }

         ObjectRef dataIn = ObjectRef.create((Object)null);
         ObjectRef indexIn = ObjectRef.create((Object)null);
         ObjectRef checkedIn = ObjectRef.create((Object)null);

         try {
            dataIn.elem = new FileInputStream(data);
            indexIn.elem = new DataInputStream(new FileInputStream(index));
            LongRef prevOffset = LongRef.create(((DataInputStream)indexIn.elem).readLong());
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numPartition).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
               long curOffset = ((DataInputStream)indexIn.elem).readLong();
               int limit = (int)(curOffset - prevOffset.elem);
               byte[] bytes = new byte[limit];
               Checksum checksumCal = ShuffleChecksumHelper.getChecksumByAlgorithm(algorithm);
               checkedIn.elem = new CheckedInputStream(new LimitedInputStream((FileInputStream)dataIn.elem, curOffset - prevOffset.elem), checksumCal);
               ((CheckedInputStream)checkedIn.elem).read(bytes, 0, limit);
               prevOffset.elem = curOffset;
               if (((CheckedInputStream)checkedIn.elem).getChecksum().getValue() != expectChecksums[i]) {
                  throw new NonLocalReturnControl.mcZ.sp(var6, false);
               }
            });
         } finally {
            if ((FileInputStream)dataIn.elem != null) {
               ((FileInputStream)dataIn.elem).close();
            }

            if ((DataInputStream)indexIn.elem != null) {
               ((DataInputStream)indexIn.elem).close();
            }

            if ((CheckedInputStream)checkedIn.elem != null) {
               ((CheckedInputStream)checkedIn.elem).close();
            }

         }

         var10000 = true;
      } catch (NonLocalReturnControl var24) {
         if (var24.key() != var6) {
            throw var24;
         }

         var10000 = var24.value$mcZ$sp();
      }

      return var10000;
   }

   private ShuffleChecksumUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
