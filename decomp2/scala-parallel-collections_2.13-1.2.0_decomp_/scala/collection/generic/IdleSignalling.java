package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQAE\u0001\u0005\u0002M\ta\"\u00133mKNKwM\\1mY&twM\u0003\u0002\u0006\r\u00059q-\u001a8fe&\u001c'BA\u0004\t\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0013\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u0005!!AD%eY\u0016\u001c\u0016n\u001a8bY2LgnZ\n\u0003\u0003=\u0001\"\u0001\u0004\t\n\u0005E!!!\u0005#fM\u0006,H\u000e^*jO:\fG\u000e\\5oO\u00061A(\u001b8jiz\"\u0012a\u0003"
)
public final class IdleSignalling {
   public static int tag() {
      return IdleSignalling$.MODULE$.tag();
   }

   public static void setIndexFlagIfLesser(final int f) {
      IdleSignalling$.MODULE$.setIndexFlagIfLesser(f);
   }

   public static void setIndexFlagIfGreater(final int f) {
      IdleSignalling$.MODULE$.setIndexFlagIfGreater(f);
   }

   public static void setIndexFlag(final int f) {
      IdleSignalling$.MODULE$.setIndexFlag(f);
   }

   public static int indexFlag() {
      return IdleSignalling$.MODULE$.indexFlag();
   }

   public static void abort() {
      IdleSignalling$.MODULE$.abort();
   }

   public static boolean isAborted() {
      return IdleSignalling$.MODULE$.isAborted();
   }
}
