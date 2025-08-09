package scala.xml;

import scala.Equals;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005U<QAE\n\t\u0002a1QAG\n\t\u0002mAQ\u0001I\u0001\u0005\u0002\u0005BQAI\u0001\u0005\u0002\rBQ!K\u0001\u0005\u0002)BQ!K\u0001\u0005\u0002uBQ!K\u0001\u0005\u0002\r3qAG\n\u0011\u0002\u0007\u0005a\tC\u0003K\u000f\u0011\u00051\nC\u0003P\u000f\u0019E\u0001\u000bC\u0003X\u000f\u0019\u0005\u0001\fC\u0003]\u000f\u0011\u0005Q\fC\u0003`\u000f\u0011\u0005\u0003\rC\u0003c\u000f\u0011\u00053\rC\u0003h\u000f\u0011\u0005\u0003\u000eC\u0003k\u000f\u0011\u00151\u000eC\u0003n\u000f\u0011\u0015a\u000eC\u0003q\u000f\u0011%\u0011/\u0001\u0005FcV\fG.\u001b;z\u0015\t!R#A\u0002y[2T\u0011AF\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tI\u0012!D\u0001\u0014\u0005!)\u0015/^1mSRL8CA\u0001\u001d!\tib$D\u0001\u0016\u0013\tyRC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\tQ!Y:SK\u001a$\"\u0001\b\u0013\t\u000b\u0015\u001a\u0001\u0019\u0001\u0014\u0002\u0003a\u0004\"!H\u0014\n\u0005!*\"aA!os\u0006y1m\\7qCJ,'\t\\5uQ\u0016d\u0017\u0010F\u0002,]A\u0002\"!\b\u0017\n\u00055*\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006_\u0011\u0001\r\u0001H\u0001\u0003qFBQ!\r\u0003A\u0002I\n!\u0001\u001f\u001a\u0011\u0005MRdB\u0001\u001b9!\t)T#D\u00017\u0015\t9t#\u0001\u0004=e>|GOP\u0005\u0003sU\ta\u0001\u0015:fI\u00164\u0017BA\u001e=\u0005\u0019\u0019FO]5oO*\u0011\u0011(\u0006\u000b\u0004Wyz\u0004\"B\u0018\u0006\u0001\u0004a\u0002\"B\u0019\u0006\u0001\u0004\u0001\u0005CA\rB\u0013\t\u00115C\u0001\u0003O_\u0012,GcA\u0016E\u000b\")qF\u0002a\u00019!)\u0011G\u0002a\u00019M\u0019q\u0001H$\u0011\u0005uA\u0015BA%\u0016\u0005\u0019)\u0015/^1mg\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0014\t\u0003;5K!AT\u000b\u0003\tUs\u0017\u000e^\u0001\u0011E\u0006\u001c\u0018n\u001d$pe\"\u000b7\u000f[\"pI\u0016,\u0012!\u0015\t\u0004%V3S\"A*\u000b\u0005Q+\u0012AC2pY2,7\r^5p]&\u0011ak\u0015\u0002\u0004'\u0016\f\u0018!D:ue&\u001cGo\u0018\u0013fc\u0012*\u0017\u000f\u0006\u0002,3\")!L\u0003a\u00017\u0006)q\u000e\u001e5feB\u0011\u0011dB\u0001\u0010gR\u0014\u0018n\u0019;`I\t\fgn\u001a\u0013fcR\u00111F\u0018\u0005\u00065.\u0001\raW\u0001\tG\u0006tW)];bYR\u00111&\u0019\u0005\u000652\u0001\rAJ\u0001\tQ\u0006\u001c\bnQ8eKR\tA\r\u0005\u0002\u001eK&\u0011a-\u0006\u0002\u0004\u0013:$\u0018AB3rk\u0006d7\u000f\u0006\u0002,S\")!L\u0004a\u0001M\u0005Q\u00010\u001c7`I\u0015\fH%Z9\u0015\u0005-b\u0007\"\u0002.\u0010\u0001\u00041\u0013\u0001\u0004=nY~##-\u00198hI\u0015\fHCA\u0016p\u0011\u0015Q\u0006\u00031\u0001'\u00031!wnQ8na\u0006\u0014\u0018n]8o)\rY#o\u001d\u0005\u00065F\u0001\rA\n\u0005\u0006iF\u0001\raK\u0001\u0007E2LG\u000f[3"
)
public interface Equality extends Equals {
   static boolean compareBlithely(final Object x1, final Object x2) {
      return Equality$.MODULE$.compareBlithely(x1, x2);
   }

   static boolean compareBlithely(final Object x1, final Node x2) {
      return Equality$.MODULE$.compareBlithely(x1, x2);
   }

   static boolean compareBlithely(final Object x1, final String x2) {
      return Equality$.MODULE$.compareBlithely(x1, x2);
   }

   static Object asRef(final Object x) {
      return Equality$.MODULE$.asRef(x);
   }

   Seq basisForHashCode();

   boolean strict_$eq$eq(final Equality other);

   // $FF: synthetic method
   static boolean strict_$bang$eq$(final Equality $this, final Equality other) {
      return $this.strict_$bang$eq(other);
   }

   default boolean strict_$bang$eq(final Equality other) {
      return !this.strict_$eq$eq(other);
   }

   // $FF: synthetic method
   static boolean canEqual$(final Equality $this, final Object other) {
      return $this.canEqual(other);
   }

   default boolean canEqual(final Object other) {
      return other instanceof Equality;
   }

   // $FF: synthetic method
   static int hashCode$(final Equality $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return Statics.anyHash(this.basisForHashCode());
   }

   // $FF: synthetic method
   static boolean equals$(final Equality $this, final Object other) {
      return $this.equals(other);
   }

   default boolean equals(final Object other) {
      return this.doComparison(other, false);
   }

   // $FF: synthetic method
   static boolean xml_$eq$eq$(final Equality $this, final Object other) {
      return $this.xml_$eq$eq(other);
   }

   default boolean xml_$eq$eq(final Object other) {
      return this.doComparison(other, true);
   }

   // $FF: synthetic method
   static boolean xml_$bang$eq$(final Equality $this, final Object other) {
      return $this.xml_$bang$eq(other);
   }

   default boolean xml_$bang$eq(final Object other) {
      return !this.xml_$eq$eq(other);
   }

   private boolean doComparison(final Object other, final boolean blithe) {
      boolean var10000;
      if (other instanceof Object && this == other) {
         var10000 = true;
      } else if (other instanceof Equality) {
         Equality var7 = (Equality)other;
         var10000 = var7.canEqual(this) && this.strict_$eq$eq(var7);
      } else {
         var10000 = false;
      }

      boolean strictlyEqual = var10000;
      return strictlyEqual || blithe && Equality$.MODULE$.compareBlithely(this, (Object)Equality$.MODULE$.asRef(other));
   }

   static void $init$(final Equality $this) {
   }
}
