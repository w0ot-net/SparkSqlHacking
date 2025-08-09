package org.apache.spark.scheduler;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerId$;
import org.apache.spark.util.Utils$;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005)4Q!\u0004\b\u0001!YA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006Ka\u000b\u0005\tc\u0001\u0011\t\u0011)Q\u0005e!A\u0001\b\u0001B\u0001B\u0003&\u0011\bC\u0003=\u0001\u0011\u0005Q\bC\u0003=\u0001\u0011E!\tC\u0003=\u0001\u0011\u00051\tC\u0003K\u0001\u0011\u00053\nC\u0003M\u0001\u0011\u0005S\nC\u0003T\u0001\u0011\u0005C\u000bC\u0003[\u0001\u0011\u00053\fC\u0003]\u0001\u0011\u0005S\fC\u0003d\u0001\u0011\u0005CMA\nD_6\u0004(/Z:tK\u0012l\u0015\r]*uCR,8O\u0003\u0002\u0010!\u0005I1o\u00195fIVdWM\u001d\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sON!\u0001aF\u000f\"!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011adH\u0007\u0002\u001d%\u0011\u0001E\u0004\u0002\n\u001b\u0006\u00048\u000b^1ukN\u0004\"AI\u0014\u000e\u0003\rR!\u0001J\u0013\u0002\u0005%|'\"\u0001\u0014\u0002\t)\fg/Y\u0005\u0003Q\r\u0012a\"\u0012=uKJt\u0017\r\\5{C\ndW-A\u0002m_\u000e\u001c\u0001\u0001\u0005\u0002-_5\tQF\u0003\u0002/!\u000591\u000f^8sC\u001e,\u0017B\u0001\u0019.\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\fqbY8naJ,7o]3e'&TXm\u001d\t\u00041M*\u0014B\u0001\u001b\u001a\u0005\u0015\t%O]1z!\tAb'\u0003\u000283\t!!)\u001f;f\u0003)yV.\u00199UCN\\\u0017\n\u001a\t\u00031iJ!aO\r\u0003\t1{gnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\tyz\u0004)\u0011\t\u0003=\u0001AQ!\u000b\u0003A\u0002-BQ!\r\u0003A\u0002IBQ\u0001\u000f\u0003A\u0002e\"\u0012A\u0010\u000b\u0005}\u0011+\u0005\nC\u0003*\r\u0001\u00071\u0006C\u0003G\r\u0001\u0007q)A\tv]\u000e|W\u000e\u001d:fgN,GmU5{KN\u00042\u0001G\u001a:\u0011\u0015Ie\u00011\u0001:\u0003%i\u0017\r\u001d+bg.LE-\u0001\u0005m_\u000e\fG/[8o+\u0005Y\u0013AD;qI\u0006$X\rT8dCRLwN\u001c\u000b\u0003\u001dF\u0003\"\u0001G(\n\u0005AK\"\u0001B+oSRDQA\u0015\u0005A\u0002-\naA\\3x\u0019>\u001c\u0017aD4fiNK'0\u001a$pe\ncwnY6\u0015\u0005e*\u0006\"\u0002,\n\u0001\u00049\u0016\u0001\u0003:fIV\u001cW-\u00133\u0011\u0005aA\u0016BA-\u001a\u0005\rIe\u000e^\u0001\u0006[\u0006\u0004\u0018\nZ\u000b\u0002s\u0005iqO]5uK\u0016CH/\u001a:oC2$\"A\u00140\t\u000b}[\u0001\u0019\u00011\u0002\u0007=,H\u000f\u0005\u0002#C&\u0011!m\t\u0002\r\u001f\nTWm\u0019;PkR\u0004X\u000f^\u0001\re\u0016\fG-\u0012=uKJt\u0017\r\u001c\u000b\u0003\u001d\u0016DQA\u001a\u0007A\u0002\u001d\f!!\u001b8\u0011\u0005\tB\u0017BA5$\u0005-y%M[3di&s\u0007/\u001e;"
)
public class CompressedMapStatus implements MapStatus, Externalizable {
   private BlockManagerId loc;
   private byte[] compressedSizes;
   private long _mapTaskId;

   public BlockManagerId location() {
      return this.loc;
   }

   public void updateLocation(final BlockManagerId newLoc) {
      this.loc = newLoc;
   }

   public long getSizeForBlock(final int reduceId) {
      return MapStatus$.MODULE$.decompressSize(this.compressedSizes[reduceId]);
   }

   public long mapId() {
      return this._mapTaskId;
   }

   public void writeExternal(final ObjectOutput out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.loc.writeExternal(out);
         out.writeInt(this.compressedSizes.length);
         out.write(this.compressedSizes);
         out.writeLong(this._mapTaskId);
      });
   }

   public void readExternal(final ObjectInput in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.loc = BlockManagerId$.MODULE$.apply(in);
         int len = in.readInt();
         this.compressedSizes = new byte[len];
         in.readFully(this.compressedSizes);
         this._mapTaskId = in.readLong();
      });
   }

   public CompressedMapStatus(final BlockManagerId loc, final byte[] compressedSizes, final long _mapTaskId) {
      this.loc = loc;
      this.compressedSizes = compressedSizes;
      this._mapTaskId = _mapTaskId;
      super();
   }

   public CompressedMapStatus() {
      this((BlockManagerId)null, (byte[])null, -1L);
   }

   public CompressedMapStatus(final BlockManagerId loc, final long[] uncompressedSizes, final long mapTaskId) {
      this(loc, (byte[]).MODULE$.map$extension(scala.Predef..MODULE$.longArrayOps(uncompressedSizes), new Serializable() {
         private static final long serialVersionUID = 0L;

         public final byte apply(final long size) {
            return MapStatus$.MODULE$.compressSize(size);
         }
      }, scala.reflect.ClassTag..MODULE$.Byte()), mapTaskId);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
