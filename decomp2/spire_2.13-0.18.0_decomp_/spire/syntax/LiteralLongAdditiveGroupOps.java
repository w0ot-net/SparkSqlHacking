package spire.syntax;

import algebra.ring.Ring;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005e4A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001d\u0001\u0006!!A\u0005BECq!\u0016\u0001\u0002\u0002\u0013\u0005ckB\u0004]\u001d\u0005\u0005\t\u0012A/\u0007\u000f5q\u0011\u0011!E\u0001=\")\u0001\u0005\u0003C\u0001E\")1\r\u0003C\u0003I\"9\u0011\u000fCA\u0001\n\u000b\u0011\bb\u0002;\t\u0003\u0003%)!\u001e\u0002\u001c\u0019&$XM]1m\u0019>tw-\u00113eSRLg/Z$s_V\u0004x\n]:\u000b\u0005=\u0001\u0012AB:z]R\f\u0007PC\u0001\u0012\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PV1m\u0003\ra\u0007n]\u000b\u00029A\u0011Q#H\u0005\u0003=Y\u0011A\u0001T8oO\u0006!A\u000e[:!\u0003\u0019a\u0014N\\5u}Q\u0011!\u0005\n\t\u0003G\u0001i\u0011A\u0004\u0005\u00065\r\u0001\r\u0001H\u0001\u0007I5Lg.^:\u0016\u0005\u001dZCC\u0001\u0015O)\rICG\u0012\t\u0003U-b\u0001\u0001B\u0003-\t\t\u0007QFA\u0001B#\tq\u0013\u0007\u0005\u0002\u0016_%\u0011\u0001G\u0006\u0002\b\u001d>$\b.\u001b8h!\t)\"'\u0003\u00024-\t\u0019\u0011I\\=\t\u000bU\"\u00019\u0001\u001c\u0002\u0005\u00154\bcA\u001cDS9\u0011\u0001\b\u0011\b\u0003syr!AO\u001f\u000e\u0003mR!\u0001\u0010\n\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012BA \u0011\u0003\u001d\tGnZ3ce\u0006L!!\u0011\"\u0002\u000fA\f7m[1hK*\u0011q\bE\u0005\u0003\t\u0016\u0013AAU5oO*\u0011\u0011I\u0011\u0005\u0006\u000f\u0012\u0001\u001d\u0001S\u0001\u0002GB\u0019\u0011\nT\u0015\u000e\u0003)S!a\u0013\t\u0002\t5\fG\u000f[\u0005\u0003\u001b*\u0013QbQ8om\u0016\u0014H/\u00192mKR{\u0007\"B(\u0005\u0001\u0004I\u0013a\u0001:ig\u0006A\u0001.Y:i\u0007>$W\rF\u0001S!\t)2+\u0003\u0002U-\t\u0019\u0011J\u001c;\u0002\r\u0015\fX/\u00197t)\t9&\f\u0005\u0002\u00161&\u0011\u0011L\u0006\u0002\b\u0005>|G.Z1o\u0011\u001dYf!!AA\u0002E\n1\u0001\u001f\u00132\u0003ma\u0015\u000e^3sC2duN\\4BI\u0012LG/\u001b<f\u000fJ|W\u000f](qgB\u00111\u0005C\n\u0003\u0011}\u0003\"!\u00061\n\u0005\u00054\"AB!osJ+g\rF\u0001^\u0003A!S.\u001b8vg\u0012*\u0007\u0010^3og&|g.\u0006\u0002fSR\u0011am\u001c\u000b\u0003O:$2\u0001\u001b6m!\tQ\u0013\u000eB\u0003-\u0015\t\u0007Q\u0006C\u00036\u0015\u0001\u000f1\u000eE\u00028\u0007\"DQa\u0012\u0006A\u00045\u00042!\u0013'i\u0011\u0015y%\u00021\u0001i\u0011\u0015\u0001(\u00021\u0001#\u0003\u0015!C\u000f[5t\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005E\u001b\b\"\u00029\f\u0001\u0004\u0011\u0013\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\t1\b\u0010\u0006\u0002Xo\"91\fDA\u0001\u0002\u0004\t\u0004\"\u00029\r\u0001\u0004\u0011\u0003"
)
public final class LiteralLongAdditiveGroupOps {
   private final long lhs;

   public static boolean equals$extension(final long $this, final Object x$1) {
      return LiteralLongAdditiveGroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final long $this) {
      return LiteralLongAdditiveGroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $minus$extension(final long $this, final Object rhs, final Ring ev, final ConvertableTo c) {
      return LiteralLongAdditiveGroupOps$.MODULE$.$minus$extension($this, rhs, ev, c);
   }

   public long lhs() {
      return this.lhs;
   }

   public Object $minus(final Object rhs, final Ring ev, final ConvertableTo c) {
      return LiteralLongAdditiveGroupOps$.MODULE$.$minus$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralLongAdditiveGroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralLongAdditiveGroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralLongAdditiveGroupOps(final long lhs) {
      this.lhs = lhs;
   }
}
