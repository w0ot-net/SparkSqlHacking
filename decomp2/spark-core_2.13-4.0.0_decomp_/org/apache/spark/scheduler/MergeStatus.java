package org.apache.spark.scheduler;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerId$;
import org.apache.spark.util.Utils$;
import org.roaringbitmap.RoaringBitmap;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!\u0002\f\u0018\u0001ey\u0002\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0015\u0002\u001b\t\u0011i\u0002!\u0011!Q!\nmB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006KA\u0011\u0005\t\u0011\u0002\u0011\t\u0011)Q\u0005\u0013\")A\n\u0001C\u0001\u001b\")A\n\u0001C\t'\")A\u000b\u0001C\u0001+\")a\u000b\u0001C\u0001/\")\u0001\f\u0001C\u00013\")!\f\u0001C\u00017\")A\f\u0001C\u0001;\")\u0001\r\u0001C!C\")!\u000e\u0001C!W\u001e1\u0011o\u0006E\u00013I4aAF\f\t\u0002e\u0019\b\"\u0002'\u0010\t\u0003Q\bbB>\u0010\u0005\u0004%\ta\u0016\u0005\u0007y>\u0001\u000b\u0011B\u001e\t\u000bu|A\u0011\u0001@\t\u000f\u0005]r\u0002\"\u0001\u0002:!I\u0011QI\b\u0002\u0002\u0013%\u0011q\t\u0002\f\u001b\u0016\u0014x-Z*uCR,8O\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sON!\u0001\u0001\t\u0015/!\t\tc%D\u0001#\u0015\t\u0019C%\u0001\u0003mC:<'\"A\u0013\u0002\t)\fg/Y\u0005\u0003O\t\u0012aa\u00142kK\u000e$\bCA\u0015-\u001b\u0005Q#BA\u0016%\u0003\tIw.\u0003\u0002.U\tqQ\t\u001f;fe:\fG.\u001b>bE2,\u0007CA\u00181\u001b\u00059\u0012BA\u0019\u0018\u0005M\u0019\u0006.\u001e4gY\u0016|U\u000f\u001e9viN#\u0018\r^;t\u0003\rawnY\u0002\u0001!\t)\u0004(D\u00017\u0015\t9\u0014$A\u0004ti>\u0014\u0018mZ3\n\u0005e2$A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0001\u0010?NDWO\u001a4mK6+'oZ3JIB\u0011AhP\u0007\u0002{)\ta(A\u0003tG\u0006d\u0017-\u0003\u0002A{\t\u0019\u0011J\u001c;\u0002\u00155\f\u0007\u000f\u0016:bG.,'\u000f\u0005\u0002D\r6\tAI\u0003\u0002F;\u0005i!o\\1sS:<'-\u001b;nCBL!a\u0012#\u0003\u001bI{\u0017M]5oO\nKG/\\1q\u0003\u0011\u0019\u0018N_3\u0011\u0005qR\u0015BA&>\u0005\u0011auN\\4\u0002\rqJg.\u001b;?)\u0015qu\nU)S!\ty\u0003\u0001C\u00033\u000b\u0001\u0007A\u0007C\u0003;\u000b\u0001\u00071\bC\u0003B\u000b\u0001\u0007!\tC\u0003I\u000b\u0001\u0007\u0011\nF\u0001O\u0003!awnY1uS>tW#\u0001\u001b\u0002\u001dMDWO\u001a4mK6+'oZ3JIV\t1(A\u0005u_R\fGnU5{KV\t\u0011*A\u0004ue\u0006\u001c7.\u001a:\u0016\u0003\t\u000bqcZ3u\u001dVlW*[:tS:<W*\u00199PkR\u0004X\u000f^:\u0015\u0005mr\u0006\"B0\f\u0001\u0004Y\u0014a\u00028v[6\u000b\u0007o]\u0001\u000eoJLG/Z#yi\u0016\u0014h.\u00197\u0015\u0005\t,\u0007C\u0001\u001fd\u0013\t!WH\u0001\u0003V]&$\b\"\u00024\r\u0001\u00049\u0017aA8viB\u0011\u0011\u0006[\u0005\u0003S*\u0012Ab\u00142kK\u000e$x*\u001e;qkR\fAB]3bI\u0016CH/\u001a:oC2$\"A\u00197\t\u000b5l\u0001\u0019\u00018\u0002\u0005%t\u0007CA\u0015p\u0013\t\u0001(FA\u0006PE*,7\r^%oaV$\u0018aC'fe\u001e,7\u000b^1ukN\u0004\"aL\b\u0014\u0007=!x\u000f\u0005\u0002=k&\u0011a/\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%B\u0018BA=+\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005\u0011\u0018AH*I+\u001a3E*R0Q+NCu\fR+N\u001bf{f*V'`%\u0016#UkQ#T\u0003}\u0019\u0006*\u0016$G\u0019\u0016{\u0006+V*I?\u0012+V*T-`\u001dVkuLU#E+\u000e+5\u000bI\u0001%G>tg/\u001a:u\u001b\u0016\u0014x-Z*uCR,8/Z:U_6+'oZ3Ti\u0006$Xo]!seR)q0!\b\u00026A1\u0011\u0011AA\t\u0003/qA!a\u0001\u0002\u000e9!\u0011QAA\u0006\u001b\t\t9AC\u0002\u0002\nM\na\u0001\u0010:p_Rt\u0014\"\u0001 \n\u0007\u0005=Q(A\u0004qC\u000e\\\u0017mZ3\n\t\u0005M\u0011Q\u0003\u0002\u0004'\u0016\f(bAA\b{A)A(!\u0007<\u001d&\u0019\u00111D\u001f\u0003\rQ+\b\u000f\\33\u0011\u001d\tyb\u0005a\u0001\u0003C\tQ\"\\3sO\u0016\u001cF/\u0019;vg\u0016\u001c\b\u0003BA\u0012\u0003ci!!!\n\u000b\t\u0005\u001d\u0012\u0011F\u0001\taJ|Go\\2pY*!\u00111FA\u0017\u0003\u001d\u0019\b.\u001e4gY\u0016T1!a\f\u001a\u0003\u001dqW\r^<pe.LA!a\r\u0002&\tiQ*\u001a:hKN#\u0018\r^;tKNDQAM\nA\u0002Q\nQ!\u00199qYf$\u0012BTA\u001e\u0003{\ty$a\u0011\t\u000bI\"\u0002\u0019\u0001\u001b\t\u000bY#\u0002\u0019A\u001e\t\r\u0005\u0005C\u00031\u0001C\u0003\u0019\u0011\u0017\u000e^7ba\")\u0001\n\u0006a\u0001\u0013\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t\u0001\u0005"
)
public class MergeStatus implements Externalizable, ShuffleOutputStatus {
   private BlockManagerId loc;
   private int _shuffleMergeId;
   private RoaringBitmap mapTracker;
   private long size;

   public static MergeStatus apply(final BlockManagerId loc, final int shuffleMergeId, final RoaringBitmap bitmap, final long size) {
      return MergeStatus$.MODULE$.apply(loc, shuffleMergeId, bitmap, size);
   }

   public static Seq convertMergeStatusesToMergeStatusArr(final MergeStatuses mergeStatuses, final BlockManagerId loc) {
      return MergeStatus$.MODULE$.convertMergeStatusesToMergeStatusArr(mergeStatuses, loc);
   }

   public static int SHUFFLE_PUSH_DUMMY_NUM_REDUCES() {
      return MergeStatus$.MODULE$.SHUFFLE_PUSH_DUMMY_NUM_REDUCES();
   }

   public BlockManagerId location() {
      return this.loc;
   }

   public int shuffleMergeId() {
      return this._shuffleMergeId;
   }

   public long totalSize() {
      return this.size;
   }

   public RoaringBitmap tracker() {
      return this.mapTracker;
   }

   public int getNumMissingMapOutputs(final int numMaps) {
      return .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numMaps).count((JFunction1.mcZI.sp)(i) -> !this.mapTracker.contains(i));
   }

   public void writeExternal(final ObjectOutput out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.loc.writeExternal(out);
         out.writeInt(this._shuffleMergeId);
         this.mapTracker.writeExternal(out);
         out.writeLong(this.size);
      });
   }

   public void readExternal(final ObjectInput in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.loc = BlockManagerId$.MODULE$.apply(in);
         this._shuffleMergeId = in.readInt();
         this.mapTracker = new RoaringBitmap();
         this.mapTracker.readExternal(in);
         this.size = in.readLong();
      });
   }

   public MergeStatus(final BlockManagerId loc, final int _shuffleMergeId, final RoaringBitmap mapTracker, final long size) {
      this.loc = loc;
      this._shuffleMergeId = _shuffleMergeId;
      this.mapTracker = mapTracker;
      this.size = size;
      super();
   }

   public MergeStatus() {
      this((BlockManagerId)null, -1, (RoaringBitmap)null, -1L);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
