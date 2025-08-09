package org.apache.spark.scheduler;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerId$;
import org.apache.spark.util.Utils$;
import org.roaringbitmap.RoaringBitmap;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!\u0002\u000b\u0016\u0001]i\u0002\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0015\u0002\u001a\t\u0011a\u0002!\u0011!Q!\neB\u0001\u0002\u0010\u0001\u0003\u0002\u0003\u0006K!\u0010\u0005\t\u0007\u0002\u0011\t\u0011)Q\u0005\t\"Aq\t\u0001B\u0001B\u0003&\u0001\n\u0003\u0005R\u0001\t\u0005\t\u0015)\u0003E\u0011\u0015\u0011\u0006\u0001\"\u0003T\u0011\u0015\u0011\u0006\u0001\"\u0005\\\u0011\u0015a\u0006\u0001\"\u0011^\u0011\u0015q\u0006\u0001\"\u0011`\u0011\u0015)\u0007\u0001\"\u0011g\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u0015Y\u0007\u0001\"\u0011m\u0011\u0015\u0011\b\u0001\"\u0011t\u000f\u0019IX\u0003#\u0001\u0018u\u001a1A#\u0006E\u0001/mDQA\u0015\t\u0005\u0002}Dq!!\u0001\u0011\t\u0003\t\u0019\u0001C\u0005\u0002\u0016A\t\t\u0011\"\u0003\u0002\u0018\tI\u0002*[4iYf\u001cu.\u001c9sKN\u001cX\rZ'baN#\u0018\r^;t\u0015\t1r#A\u0005tG\",G-\u001e7fe*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0005\u0003\u0001=\u0011B\u0003CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\r\u0005\u0002&M5\tQ#\u0003\u0002(+\tIQ*\u00199Ti\u0006$Xo\u001d\t\u0003S9j\u0011A\u000b\u0006\u0003W1\n!![8\u000b\u00035\nAA[1wC&\u0011qF\u000b\u0002\u000f\u000bb$XM\u001d8bY&T\u0018M\u00197f\u0003\rawnY\u0002\u0001!\t\u0019d'D\u00015\u0015\t)t#A\u0004ti>\u0014\u0018mZ3\n\u0005]\"$A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0001\u0012]Vlgj\u001c8F[B$\u0018P\u00117pG.\u001c\bCA\u0010;\u0013\tY\u0004EA\u0002J]R\f1\"Z7qif\u0014En\\2lgB\u0011a(Q\u0007\u0002\u007f)\u0011\u0001iG\u0001\u000ee>\f'/\u001b8hE&$X.\u00199\n\u0005\t{$!\u0004*pCJLgn\u001a\"ji6\f\u0007/A\u0004bm\u001e\u001c\u0016N_3\u0011\u0005})\u0015B\u0001$!\u0005\u0011auN\\4\u0002\u001d!,x-\u001a\"m_\u000e\\7+\u001b>fgB!\u0011\nT\u001dO\u001b\u0005Q%BA&!\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u001b*\u00131!T1q!\tyr*\u0003\u0002QA\t!!)\u001f;f\u0003)yV.\u00199UCN\\\u0017\nZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000fQ+fk\u0016-Z5B\u0011Q\u0005\u0001\u0005\u0006a\u001d\u0001\rA\r\u0005\u0006q\u001d\u0001\r!\u000f\u0005\u0006y\u001d\u0001\r!\u0010\u0005\u0006\u0007\u001e\u0001\r\u0001\u0012\u0005\u0006\u000f\u001e\u0001\r\u0001\u0013\u0005\u0006#\u001e\u0001\r\u0001\u0012\u000b\u0002)\u0006AAn\\2bi&|g.F\u00013\u00039)\b\u000fZ1uK2{7-\u0019;j_:$\"\u0001Y2\u0011\u0005}\t\u0017B\u00012!\u0005\u0011)f.\u001b;\t\u000b\u0011T\u0001\u0019\u0001\u001a\u0002\r9,w\u000fT8d\u0003=9W\r^*ju\u00164uN\u001d\"m_\u000e\\GC\u0001#h\u0011\u0015A7\u00021\u0001:\u0003!\u0011X\rZ;dK&#\u0017!B7ba&#W#\u0001#\u0002\u001b]\u0014\u0018\u000e^3FqR,'O\\1m)\t\u0001W\u000eC\u0003o\u001b\u0001\u0007q.A\u0002pkR\u0004\"!\u000b9\n\u0005ET#\u0001D(cU\u0016\u001cGoT;uaV$\u0018\u0001\u0004:fC\u0012,\u0005\u0010^3s]\u0006dGC\u00011u\u0011\u0015)h\u00021\u0001w\u0003\tIg\u000e\u0005\u0002*o&\u0011\u0001P\u000b\u0002\f\u001f\nTWm\u0019;J]B,H/A\rIS\u001eDG._\"p[B\u0014Xm]:fI6\u000b\u0007o\u0015;biV\u001c\bCA\u0013\u0011'\r\u0001b\u0004 \t\u0003SuL!A \u0016\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0003i\fQ!\u00199qYf$r\u0001VA\u0003\u0003\u000f\t\t\u0002C\u00031%\u0001\u0007!\u0007C\u0004\u0002\nI\u0001\r!a\u0003\u0002#Ut7m\\7qe\u0016\u001c8/\u001a3TSj,7\u000f\u0005\u0003 \u0003\u001b!\u0015bAA\bA\t)\u0011I\u001d:bs\"1\u00111\u0003\nA\u0002\u0011\u000b\u0011\"\\1q)\u0006\u001c8.\u00133\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005e\u0001\u0003BA\u000e\u0003Ci!!!\b\u000b\u0007\u0005}A&\u0001\u0003mC:<\u0017\u0002BA\u0012\u0003;\u0011aa\u00142kK\u000e$\b"
)
public class HighlyCompressedMapStatus implements MapStatus, Externalizable {
   private BlockManagerId loc;
   private int numNonEmptyBlocks;
   private RoaringBitmap emptyBlocks;
   private long avgSize;
   private Map hugeBlockSizes;
   private long _mapTaskId;

   public static HighlyCompressedMapStatus apply(final BlockManagerId loc, final long[] uncompressedSizes, final long mapTaskId) {
      return HighlyCompressedMapStatus$.MODULE$.apply(loc, uncompressedSizes, mapTaskId);
   }

   public BlockManagerId location() {
      return this.loc;
   }

   public void updateLocation(final BlockManagerId newLoc) {
      this.loc = newLoc;
   }

   public long getSizeForBlock(final int reduceId) {
      .MODULE$.assert(this.hugeBlockSizes != null);
      if (this.emptyBlocks.contains(reduceId)) {
         return 0L;
      } else {
         Option var4 = this.hugeBlockSizes.get(BoxesRunTime.boxToInteger(reduceId));
         if (var4 instanceof Some) {
            Some var5 = (Some)var4;
            byte size = BoxesRunTime.unboxToByte(var5.value());
            return MapStatus$.MODULE$.decompressSize(size);
         } else if (scala.None..MODULE$.equals(var4)) {
            return this.avgSize;
         } else {
            throw new MatchError(var4);
         }
      }
   }

   public long mapId() {
      return this._mapTaskId;
   }

   public void writeExternal(final ObjectOutput out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.loc.writeExternal(out);
         this.emptyBlocks.serialize(out);
         out.writeLong(this.avgSize);
         out.writeInt(this.hugeBlockSizes.size());
         this.hugeBlockSizes.foreach((kv) -> {
            $anonfun$writeExternal$3(out, kv);
            return BoxedUnit.UNIT;
         });
         out.writeLong(this._mapTaskId);
      });
   }

   public void readExternal(final ObjectInput in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.loc = BlockManagerId$.MODULE$.apply(in);
         this.numNonEmptyBlocks = -1;
         this.emptyBlocks = new RoaringBitmap();
         this.emptyBlocks.deserialize(in);
         this.avgSize = in.readLong();
         int count = in.readInt();
         scala.collection.mutable.Map hugeBlockSizesImpl = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), count).foreach$mVc$sp((JFunction1.mcVI.sp)(x$2) -> {
            int block = in.readInt();
            byte size = in.readByte();
            hugeBlockSizesImpl.update(BoxesRunTime.boxToInteger(block), BoxesRunTime.boxToByte(size));
         });
         this.hugeBlockSizes = hugeBlockSizesImpl;
         this._mapTaskId = in.readLong();
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$writeExternal$3(final ObjectOutput out$2, final Tuple2 kv) {
      out$2.writeInt(kv._1$mcI$sp());
      out$2.writeByte(BoxesRunTime.unboxToByte(kv._2()));
   }

   public HighlyCompressedMapStatus(final BlockManagerId loc, final int numNonEmptyBlocks, final RoaringBitmap emptyBlocks, final long avgSize, final Map hugeBlockSizes, final long _mapTaskId) {
      this.loc = loc;
      this.numNonEmptyBlocks = numNonEmptyBlocks;
      this.emptyBlocks = emptyBlocks;
      this.avgSize = avgSize;
      this.hugeBlockSizes = hugeBlockSizes;
      this._mapTaskId = _mapTaskId;
      super();
      .MODULE$.require(this.loc == null || this.avgSize > 0L || this.hugeBlockSizes.size() > 0 || this.numNonEmptyBlocks == 0 || this._mapTaskId > 0L, () -> "Average size can only be zero for map stages that produced no output");
   }

   public HighlyCompressedMapStatus() {
      this((BlockManagerId)null, -1, (RoaringBitmap)null, -1L, (Map)null, -1L);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
