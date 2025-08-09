package spire.std;

import algebra.lattice.Bool;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00030\u0001\u0011\u0005a\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003;\u0001\u0011\u00051\bC\u0003>\u0001\u0011\u0005cHA\u0007C_>dW-\u00198Jg\n{w\u000e\u001c\u0006\u0003\u0015-\t1a\u001d;e\u0015\u0005a\u0011!B:qSJ,7\u0001A\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017E\u0015r!aF\u0010\u000f\u0005aibBA\r\u001d\u001b\u0005Q\"BA\u000e\u000e\u0003\u0019a$o\\8u}%\tA\"\u0003\u0002\u001f\u0017\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0011\"\u0003\u001d\u0001\u0018mY6bO\u0016T!AH\u0006\n\u0005\r\"#\u0001\u0002\"p_2T!\u0001I\u0011\u0011\u0005A1\u0013BA\u0014\u0012\u0005\u001d\u0011un\u001c7fC:\fa\u0001J5oSR$C#\u0001\u0016\u0011\u0005AY\u0013B\u0001\u0017\u0012\u0005\u0011)f.\u001b;\u0002\u0007=tW-F\u0001&\u0003\u0011QXM]8\u0002\u0007\u0005tG\rF\u0002&eQBQa\r\u0003A\u0002\u0015\n\u0011!\u0019\u0005\u0006k\u0011\u0001\r!J\u0001\u0002E\u0006\u0011qN\u001d\u000b\u0004KaJ\u0004\"B\u001a\u0006\u0001\u0004)\u0003\"B\u001b\u0006\u0001\u0004)\u0013AC2p[BdW-\\3oiR\u0011Q\u0005\u0010\u0005\u0006g\u0019\u0001\r!J\u0001\u0004q>\u0014HcA\u0013@\u0001\")1g\u0002a\u0001K!)Qg\u0002a\u0001K\u0001"
)
public interface BooleanIsBool extends Bool {
   // $FF: synthetic method
   static boolean one$(final BooleanIsBool $this) {
      return $this.one();
   }

   default boolean one() {
      return true;
   }

   // $FF: synthetic method
   static boolean zero$(final BooleanIsBool $this) {
      return $this.zero();
   }

   default boolean zero() {
      return false;
   }

   // $FF: synthetic method
   static boolean and$(final BooleanIsBool $this, final boolean a, final boolean b) {
      return $this.and(a, b);
   }

   default boolean and(final boolean a, final boolean b) {
      return a & b;
   }

   // $FF: synthetic method
   static boolean or$(final BooleanIsBool $this, final boolean a, final boolean b) {
      return $this.or(a, b);
   }

   default boolean or(final boolean a, final boolean b) {
      return a | b;
   }

   // $FF: synthetic method
   static boolean complement$(final BooleanIsBool $this, final boolean a) {
      return $this.complement(a);
   }

   default boolean complement(final boolean a) {
      return !a;
   }

   // $FF: synthetic method
   static boolean xor$(final BooleanIsBool $this, final boolean a, final boolean b) {
      return $this.xor(a, b);
   }

   default boolean xor(final boolean a, final boolean b) {
      return a ^ b;
   }

   static void $init$(final BooleanIsBool $this) {
   }
}
