package spire.std;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00032\u0001\u0011\u0005#\u0007C\u00039\u0001\u0011\u0005\u0013H\u0001\u0006M_:<7+[4oK\u0012T!a\u0002\u0005\u0002\u0007M$HMC\u0001\n\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019B\u0001\u0001\u0007\u0013KA\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042aE\u0010#\u001d\t!BD\u0004\u0002\u001659\u0011a#G\u0007\u0002/)\u0011\u0001DC\u0001\u0007yI|w\u000e\u001e \n\u0003%I!a\u0007\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011QDH\u0001\ba\u0006\u001c7.Y4f\u0015\tY\u0002\"\u0003\u0002!C\t11+[4oK\u0012T!!\b\u0010\u0011\u00055\u0019\u0013B\u0001\u0013\u000f\u0005\u0011auN\\4\u0011\u0005\u0019:S\"\u0001\u0004\n\u0005!2!!\u0003'p]\u001e|%\u000fZ3s\u0003\u0019!\u0013N\\5uIQ\t1\u0006\u0005\u0002\u000eY%\u0011QF\u0004\u0002\u0005+:LG/A\u0003pe\u0012,'/F\u00011!\t1\u0003!\u0001\u0004tS\u001etW/\u001c\u000b\u0003gY\u0002\"!\u0004\u001b\n\u0005Ur!aA%oi\")qg\u0001a\u0001E\u0005\t\u0011-A\u0002bEN$\"A\t\u001e\t\u000b]\"\u0001\u0019\u0001\u0012"
)
public interface LongSigned extends Signed.mcJ.sp, LongOrder {
   // $FF: synthetic method
   static LongSigned order$(final LongSigned $this) {
      return $this.order();
   }

   default LongSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final LongSigned $this, final long a) {
      return $this.signum(a);
   }

   default int signum(final long a) {
      return this.signum$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long abs$(final LongSigned $this, final long a) {
      return $this.abs(a);
   }

   default long abs(final long a) {
      return this.abs$mcJ$sp(a);
   }

   // $FF: synthetic method
   static int signum$mcJ$sp$(final LongSigned $this, final long a) {
      return $this.signum$mcJ$sp(a);
   }

   default int signum$mcJ$sp(final long a) {
      return Long.signum(a);
   }

   // $FF: synthetic method
   static long abs$mcJ$sp$(final LongSigned $this, final long a) {
      return $this.abs$mcJ$sp(a);
   }

   default long abs$mcJ$sp(final long a) {
      return a < 0L ? -a : a;
   }

   static void $init$(final LongSigned $this) {
   }
}
