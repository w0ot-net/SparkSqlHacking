package breeze.linalg.support;

import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%<Qa\u0004\t\t\u0002]1Q!\u0007\t\t\u0002iAQ!I\u0001\u0005\u0002\tBqaI\u0001C\u0002\u0013\u0005A\u0005\u0003\u00042\u0003\u0001\u0006I!\n\u0005\u0006e\u0005!)a\r\u0005\bC\u0006\t\t\u0011\"\u0002c\u0011\u001d!\u0017!!A\u0005\u0006\u00154A!\u0007\t\u0003\u0013\"AQ\n\u0003BC\u0002\u0013\u0005a\n\u0003\u0005P\u0011\t\u0005\t\u0015!\u00036\u0011\u0015\t\u0003\u0002\"\u0001Q\u0011\u0015\u0011\u0006\u0002\"\u0001T\u0011\u001d)\u0006\"!A\u0005BYCqa\u0016\u0005\u0002\u0002\u0013\u0005\u0003,A\u0007SC:<W-\u0012=uK:$WM\u001d\u0006\u0003#I\tqa];qa>\u0014HO\u0003\u0002\u0014)\u00051A.\u001b8bY\u001eT\u0011!F\u0001\u0007EJ,WM_3\u0004\u0001A\u0011\u0001$A\u0007\u0002!\ti!+\u00198hK\u0016CH/\u001a8eKJ\u001c\"!A\u000e\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tq#A\u0002BY2,\u0012!\n\t\u0003M9r!a\n\u0017\u000e\u0003!R!!\u000b\u0016\u0002\u0013%lW.\u001e;bE2,'BA\u0016\u001e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003[!\nQAU1oO\u0016L!a\f\u0019\u0003\u0013\u0015C8\r\\;tSZ,'BA\u0017)\u0003\u0011\tE\u000e\u001c\u0011\u0002Q\u001d,GOU1oO\u0016<\u0016\u000e\u001e5pkRtUmZ1uSZ,\u0017J\u001c3fq\u0016\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005Q2ECA\u001bB!\t1dH\u0004\u00028y9\u0011\u0001hO\u0007\u0002s)\u0011!HF\u0001\u0007yI|w\u000e\u001e \n\u0003yI!!P\u000f\u0002\u000fA\f7m[1hK&\u0011q\b\u0011\u0002\u0006%\u0006tw-\u001a\u0006\u0003{uAQAQ\u0003A\u0002\r\u000b1\u0002^8uC2dUM\\4uQB\u0011A\u0004R\u0005\u0003\u000bv\u00111!\u00138u\u0011\u00159U\u00011\u0001I\u0003\u0015!C\u000f[5t!\tA\u0002b\u0005\u0002\t\u0015B\u0011AdS\u0005\u0003\u0019v\u0011a!\u00118z-\u0006d\u0017A\u0001:f+\u0005)\u0014a\u0001:fAQ\u0011\u0001*\u0015\u0005\u0006\u001b.\u0001\r!N\u0001\u001fO\u0016$(+\u00198hK^KG\u000f[8vi:+w-\u0019;jm\u0016Le\u000eZ3yKN$\"!\u000e+\t\u000b\tc\u0001\u0019A\"\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aQ\u0001\u0007KF,\u0018\r\\:\u0015\u0005ec\u0006C\u0001\u000f[\u0013\tYVDA\u0004C_>dW-\u00198\t\u000fus\u0011\u0011!a\u0001=\u0006\u0019\u0001\u0010J\u0019\u0011\u0005qy\u0016B\u00011\u001e\u0005\r\te._\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0002WG\")qI\u0002a\u0001\u0011\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\u001c\u000b\u0003M\"$\"!W4\t\u000fu;\u0011\u0011!a\u0001=\")qi\u0002a\u0001\u0011\u0002"
)
public final class RangeExtender {
   private final Range re;

   public static boolean equals$extension(final Range $this, final Object x$1) {
      return RangeExtender$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Range $this) {
      return RangeExtender$.MODULE$.hashCode$extension($this);
   }

   public static Range getRangeWithoutNegativeIndexes$extension(final Range $this, final int totalLength) {
      return RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension($this, totalLength);
   }

   public static Range.Exclusive All() {
      return RangeExtender$.MODULE$.All();
   }

   public Range re() {
      return this.re;
   }

   public Range getRangeWithoutNegativeIndexes(final int totalLength) {
      return RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(this.re(), totalLength);
   }

   public int hashCode() {
      return RangeExtender$.MODULE$.hashCode$extension(this.re());
   }

   public boolean equals(final Object x$1) {
      return RangeExtender$.MODULE$.equals$extension(this.re(), x$1);
   }

   public RangeExtender(final Range re) {
      this.re = re;
   }
}
