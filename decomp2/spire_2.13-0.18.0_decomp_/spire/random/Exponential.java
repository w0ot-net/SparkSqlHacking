package spire.random;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054qa\u0002\u0005\u0011\u0002G\u0005Q\u0002C\u0003\u0016\u0001\u0019\u0005acB\u00039\u0011!\u0005\u0011HB\u0003\b\u0011!\u0005!\bC\u0003B\u0007\u0011\u0005!\tC\u0003\u0016\u0007\u0011\u00151\tC\u0003\u0016\u0007\u0011\u00051KA\u0006FqB|g.\u001a8uS\u0006d'BA\u0005\u000b\u0003\u0019\u0011\u0018M\u001c3p[*\t1\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00059i2C\u0001\u0001\u0010!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\r\te._\u0001\u0006CB\u0004H.\u001f\u000b\u0003/Y\u00022\u0001G\r\u001c\u001b\u0005A\u0011B\u0001\u000e\t\u0005\u0011!\u0015n\u001d;\u0011\u0005qiB\u0002\u0001\u0003\n=\u0001\u0001\u000b\u0011!AC\u0002}\u0011\u0011!Q\t\u0003A=\u0001\"\u0001E\u0011\n\u0005\t\n\"a\u0002(pi\"Lgn\u001a\u0015\u0005;\u0011:\u0013\u0007\u0005\u0002\u0011K%\u0011a%\u0005\u0002\fgB,7-[1mSj,G-M\u0003$Q%Z#F\u0004\u0002\u0011S%\u0011!&E\u0001\u0006\r2|\u0017\r^\u0019\u0005I1\u0002$C\u0004\u0002.a5\taF\u0003\u00020\u0019\u00051AH]8pizJ\u0011AE\u0019\u0006GI\u001aT\u0007\u000e\b\u0003!MJ!\u0001N\t\u0002\r\u0011{WO\u00197fc\u0011!C\u0006\r\n\t\u000b]\n\u0001\u0019A\u000e\u0002\tI\fG/Z\u0001\f\u000bb\u0004xN\\3oi&\fG\u000e\u0005\u0002\u0019\u0007M\u00191a\u000f \u0011\u0005Aa\u0014BA\u001f\u0012\u0005\u0019\te.\u001f*fMB\u0011\u0001dP\u0005\u0003\u0001\"\u0011A#\u0012=q_:,g\u000e^5bY&s7\u000f^1oG\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u0001:+\t!u\t\u0006\u0002F\u001bB\u0019\u0001\u0004\u0001$\u0011\u0005q9E!\u0003\u0010\u0006A\u0003\u0005\tQ1\u0001 Q\u00119E%S&2\u000b\rB\u0013F\u0013\u00162\t\u0011b\u0003GE\u0019\u0006GI\u001aD\nN\u0019\u0005I1\u0002$\u0003C\u0003O\u000b\u0001\u000fQ)A\u0001fQ\t)\u0001\u000b\u0005\u0002\u0011#&\u0011!+\u0005\u0002\u0007S:d\u0017N\\3\u0016\u0005QCFCA+a)\t1f\fE\u0002\u00193]\u0003\"\u0001\b-\u0005\u0013y1\u0001\u0015!A\u0001\u0006\u0004y\u0002\u0006\u0002-%5r\u000bTa\t\u0015*7*\nD\u0001\n\u00171%E*1EM\u001a^iE\"A\u0005\f\u0019\u0013\u0011\u0015qe\u0001q\u0001`!\rA\u0002a\u0016\u0005\u0006o\u0019\u0001\ra\u0016"
)
public interface Exponential {
   static Exponential double() {
      return Exponential$.MODULE$.double();
   }

   static Exponential float() {
      return Exponential$.MODULE$.float();
   }

   Dist apply(final Object rate);

   // $FF: synthetic method
   static Dist apply$mcD$sp$(final Exponential $this, final double rate) {
      return $this.apply$mcD$sp(rate);
   }

   default Dist apply$mcD$sp(final double rate) {
      return this.apply(BoxesRunTime.boxToDouble(rate));
   }

   // $FF: synthetic method
   static Dist apply$mcF$sp$(final Exponential $this, final float rate) {
      return $this.apply$mcF$sp(rate);
   }

   default Dist apply$mcF$sp(final float rate) {
      return this.apply(BoxesRunTime.boxToFloat(rate));
   }
}
