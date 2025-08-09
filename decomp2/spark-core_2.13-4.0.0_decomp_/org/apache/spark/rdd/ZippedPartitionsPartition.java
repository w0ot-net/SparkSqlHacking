package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.util.Utils$;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma!\u0002\b\u0010\u0001E9\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u0011\u001d\u0002!Q1A\u0005\n!B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\t\u0015\u0002\u0011)\u0019!C\u0001\u0017\"AQ\u000b\u0001B\u0001B\u0003%A\nC\u0003X\u0001\u0011\u0005\u0001\fC\u0004c\u0001\t\u0007I\u0011I2\t\r\u0011\u0004\u0001\u0015!\u0003%\u0011\u001d)\u0007\u00011A\u0005\u0002\u0019DqA\u001c\u0001A\u0002\u0013\u0005q\u000e\u0003\u0004v\u0001\u0001\u0006Ka\u001a\u0005\u0006m\u0002!\ta\u001e\u0005\u0006s\u0002!IA\u001f\u0002\u001a5&\u0004\b/\u001a3QCJ$\u0018\u000e^5p]N\u0004\u0016M\u001d;ji&|gN\u0003\u0002\u0011#\u0005\u0019!\u000f\u001a3\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c2\u0001\u0001\r\u001f!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019\te.\u001f*fMB\u0011q\u0004I\u0007\u0002#%\u0011\u0011%\u0005\u0002\n!\u0006\u0014H/\u001b;j_:\f1!\u001b3y\u0007\u0001\u0001\"!G\u0013\n\u0005\u0019R\"aA%oi\u0006!!\u000f\u001a3t+\u0005I\u0003c\u0001\u00163k9\u00111\u0006\r\b\u0003Y=j\u0011!\f\u0006\u0003]\r\na\u0001\u0010:p_Rt\u0014\"A\u000e\n\u0005ER\u0012a\u00029bG.\fw-Z\u0005\u0003gQ\u00121aU3r\u0015\t\t$\u0004\r\u00027yA\u0019q\u0007\u000f\u001e\u000e\u0003=I!!O\b\u0003\u0007I#E\t\u0005\u0002<y1\u0001A!C\u001f\u0004\u0003\u0003\u0005\tQ!\u0001D\u0005\ryF%M\u0001\u0006e\u0012$7\u000f\t\u0015\u0003\u0007\u0001\u0003\"!G!\n\u0005\tS\"!\u0003;sC:\u001c\u0018.\u001a8u#\t!u\t\u0005\u0002\u001a\u000b&\u0011aI\u0007\u0002\b\u001d>$\b.\u001b8h!\tI\u0002*\u0003\u0002J5\t\u0019\u0011I\\=\u0002%A\u0014XMZ3se\u0016$Gj\\2bi&|gn]\u000b\u0002\u0019B\u0019!FM'\u0011\u00059\u0013fBA(Q!\ta#$\u0003\u0002R5\u00051\u0001K]3eK\u001aL!a\u0015+\u0003\rM#(/\u001b8h\u0015\t\t&$A\nqe\u00164WM\u001d:fI2{7-\u0019;j_:\u001c\b\u0005\u000b\u0002\u0006\u0001\u00061A(\u001b8jiz\"B!\u0017.\\CB\u0011q\u0007\u0001\u0005\u0006E\u0019\u0001\r\u0001\n\u0005\u0006O\u0019\u0001\r\u0001\u0018\t\u0004UIj\u0006G\u00010a!\r9\u0004h\u0018\t\u0003w\u0001$\u0011\"P.\u0002\u0002\u0003\u0005)\u0011A\"\t\u000b)3\u0001\u0019\u0001'\u0002\u000b%tG-\u001a=\u0016\u0003\u0011\na!\u001b8eKb\u0004\u0013a\u00049beRLG/[8o-\u0006dW/Z:\u0016\u0003\u001d\u00042\u0001[7\u001f\u001b\u0005I'B\u00016l\u0003%IW.\\;uC\ndWM\u0003\u0002m5\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005MJ\u0017a\u00059beRLG/[8o-\u0006dW/Z:`I\u0015\fHC\u00019t!\tI\u0012/\u0003\u0002s5\t!QK\\5u\u0011\u001d!(\"!AA\u0002\u001d\f1\u0001\u001f\u00132\u0003A\u0001\u0018M\u001d;ji&|gNV1mk\u0016\u001c\b%\u0001\u0006qCJ$\u0018\u000e^5p]N,\u0012\u0001\u001f\t\u0004UIr\u0012aC<sSR,wJ\u00196fGR$\"\u0001]>\t\u000bql\u0001\u0019A?\u0002\u0007=|7\u000fE\u0002\u007f\u0003\u000fi\u0011a \u0006\u0005\u0003\u0003\t\u0019!\u0001\u0002j_*\u0011\u0011QA\u0001\u0005U\u00064\u0018-C\u0002\u0002\n}\u0014!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\"*Q\"!\u0004\u0002\u001aA)\u0011$a\u0004\u0002\u0014%\u0019\u0011\u0011\u0003\u000e\u0003\rQD'o\\<t!\rq\u0018QC\u0005\u0004\u0003/y(aC%P\u000bb\u001cW\r\u001d;j_:\u001c#!a\u0005"
)
public class ZippedPartitionsPartition implements Partition {
   private final int idx;
   private final transient Seq rdds;
   private final transient Seq preferredLocations;
   private final int index;
   private Seq partitionValues;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   private Seq rdds() {
      return this.rdds;
   }

   public Seq preferredLocations() {
      return this.preferredLocations;
   }

   public int index() {
      return this.index;
   }

   public Seq partitionValues() {
      return this.partitionValues;
   }

   public void partitionValues_$eq(final Seq x$1) {
      this.partitionValues = x$1;
   }

   public Seq partitions() {
      return this.partitionValues();
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.partitionValues_$eq((Seq)this.rdds().map((rdd) -> rdd.partitions()[this.idx]));
         oos.defaultWriteObject();
      });
   }

   public ZippedPartitionsPartition(final int idx, final Seq rdds, final Seq preferredLocations) {
      this.idx = idx;
      this.rdds = rdds;
      this.preferredLocations = preferredLocations;
      Partition.$init$(this);
      this.index = idx;
      this.partitionValues = (Seq)rdds.map((rdd) -> rdd.partitions()[this.idx]);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
