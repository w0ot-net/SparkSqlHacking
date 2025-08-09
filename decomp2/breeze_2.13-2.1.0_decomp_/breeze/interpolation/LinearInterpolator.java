package breeze.interpolation;

import breeze.linalg.Vector;
import breeze.math.Field;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a\u0001\u0002\t\u0012\u0001YA\u0001B\f\u0001\u0003\u0002\u0003\u0006Ia\f\u0005\tk\u0001\u0011\t\u0011)A\u0005_!Aa\u0007\u0001B\u0002B\u0003-q\u0007\u0003\u0005>\u0001\t\r\t\u0015a\u0003?\u0011!!\u0005AaA!\u0002\u0017)\u0005\"\u0002)\u0001\t\u0003\t\u0006bB-\u0001\u0005\u0004%IA\u0017\u0005\u00077\u0002\u0001\u000b\u0011B#\t\u000bq\u0003A\u0011K/\t\u000b\u0001\u0004A\u0011K1\t\u000bq\u0003A\u0011B2\b\u000b)\f\u0002\u0012A6\u0007\u000bA\t\u0002\u0012\u00017\t\u000bAkA\u0011\u00019\t\u000bElA\u0011\u0001:\u0003%1Kg.Z1s\u0013:$XM\u001d9pY\u0006$xN\u001d\u0006\u0003%M\tQ\"\u001b8uKJ\u0004x\u000e\\1uS>t'\"\u0001\u000b\u0002\r\t\u0014X-\u001a>f\u0007\u0001)\"a\u0006\u0012\u0014\u0005\u0001A\u0002cA\r\u001eA9\u0011!dG\u0007\u0002#%\u0011A$E\u0001\ba\u0006\u001c7.Y4f\u0013\tqrDA\u000eIC:$\u00170\u00168jm\u0006\u0014\u0018.\u0019;f\u0013:$XM\u001d9pY\u0006$xN\u001d\u0006\u00039E\u0001\"!\t\u0012\r\u0001\u0011)1\u0005\u0001b\u0001I\t\tA+\u0005\u0002&WA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t9aj\u001c;iS:<\u0007C\u0001\u0014-\u0013\tisEA\u0002B]f\f\u0001\u0002_0d_>\u0014Hm\u001d\t\u0004aM\u0002S\"A\u0019\u000b\u0005I\u001a\u0012A\u00027j]\u0006dw-\u0003\u00025c\t1a+Z2u_J\f\u0001\"_0d_>\u0014Hm]\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001\u001d<A5\t\u0011H\u0003\u0002;O\u00059!/\u001a4mK\u000e$\u0018B\u0001\u001f:\u0005!\u0019E.Y:t)\u0006<\u0017AC3wS\u0012,gnY3%eA\u0019qH\u0011\u0011\u000e\u0003\u0001S!!Q\n\u0002\t5\fG\u000f[\u0005\u0003\u0007\u0002\u0013QAR5fY\u0012\f!\"\u001a<jI\u0016t7-\u001a\u00134!\r1U\n\t\b\u0003\u000f2s!\u0001S&\u000e\u0003%S!AS\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013B\u0001\u000f(\u0013\tquJ\u0001\u0005Pe\u0012,'/\u001b8h\u0015\tar%\u0001\u0004=S:LGO\u0010\u000b\u0004%^CF\u0003B*U+Z\u00032A\u0007\u0001!\u0011\u00151d\u0001q\u00018\u0011\u0015id\u0001q\u0001?\u0011\u0015!e\u0001q\u0001F\u0011\u0015qc\u00011\u00010\u0011\u0015)d\u00011\u00010\u0003\ry'\u000fZ\u000b\u0002\u000b\u0006!qN\u001d3!\u0003-Ig\u000e^3sa>d\u0017\r^3\u0015\u0005\u0001r\u0006\"B0\n\u0001\u0004\u0001\u0013!\u0001=\u0002\u0017\u0015DHO]1q_2\fG/\u001a\u000b\u0003A\tDQa\u0018\u0006A\u0002\u0001\"2\u0001\t3j\u0011\u0015)7\u00021\u0001g\u0003\u0015Ig\u000eZ3y!\t1s-\u0003\u0002iO\t\u0019\u0011J\u001c;\t\u000b}[\u0001\u0019\u0001\u0011\u0002%1Kg.Z1s\u0013:$XM\u001d9pY\u0006$xN\u001d\t\u000355\u0019\"!D7\u0011\u0005\u0019r\u0017BA8(\u0005\u0019\te.\u001f*fMR\t1.A\u0003baBd\u00170\u0006\u0002toR)A/a\u0001\u0002\bQ!Q\u000f_>\u007f!\rQ\u0002A\u001e\t\u0003C]$QaI\bC\u0002\u0011Bq!_\b\u0002\u0002\u0003\u000f!0\u0001\u0006fm&$WM\\2fIQ\u00022\u0001O\u001ew\u0011\u001dax\"!AA\u0004u\f!\"\u001a<jI\u0016t7-\u001a\u00136!\ry$I\u001e\u0005\t\u007f>\t\t\u0011q\u0001\u0002\u0002\u0005QQM^5eK:\u001cW\r\n\u001c\u0011\u0007\u0019ke\u000f\u0003\u0004/\u001f\u0001\u0007\u0011Q\u0001\t\u0004aM2\bBB\u001b\u0010\u0001\u0004\t)\u0001"
)
public class LinearInterpolator extends package.HandyUnivariateInterpolator {
   private final Field evidence$2;
   private final Ordering ord;

   private Ordering ord() {
      return this.ord;
   }

   public Object interpolate(final Object x) {
      int index = this.bisearch(x);
      return index == 0 ? .MODULE$.array_apply(this.Y(), 0) : this.interpolate(index, x);
   }

   public Object extrapolate(final Object x) {
      if (.MODULE$.array_length(this.X()) < 2) {
         throw new IndexOutOfBoundsException("Cannot extrapolate linearly when given less than two points.");
      } else {
         int index = this.ord().mkOrderingOps(x).$less(.MODULE$.array_apply(this.X(), 0)) ? 1 : .MODULE$.array_length(this.X()) - 1;
         return this.interpolate(index, x);
      }
   }

   private Object interpolate(final int index, final Object x) {
      scala.Predef..MODULE$.assert(index > 0);
      Object x1 = .MODULE$.array_apply(this.X(), index - 1);
      Object x2 = .MODULE$.array_apply(this.X(), index);
      Object y1 = .MODULE$.array_apply(this.Y(), index - 1);
      Object y2 = .MODULE$.array_apply(this.Y(), index);
      Field f = (Field)scala.Predef..MODULE$.implicitly(this.evidence$2);
      Object w = f.$div(f.$minus(x, x1), f.$minus(x2, x1));
      Object u = f.$minus(f.one(), w);
      return f.$plus(f.$times(y1, u), f.$times(y2, w));
   }

   public LinearInterpolator(final Vector x_coords, final Vector y_coords, final ClassTag evidence$1, final Field evidence$2, final Ordering evidence$3) {
      super(x_coords, y_coords, evidence$1, evidence$2, evidence$3);
      this.evidence$2 = evidence$2;
      this.ord = (Ordering)scala.Predef..MODULE$.implicitly(evidence$3);
   }
}
