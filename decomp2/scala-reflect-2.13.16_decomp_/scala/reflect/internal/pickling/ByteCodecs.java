package scala.reflect.internal.pickling;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:Q!\u0003\u0006\t\u0002M1Q!\u0006\u0006\t\u0002YAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQaJ\u0001\u0005\u0002!BQ!L\u0001\u0005\u00029BQ\u0001M\u0001\u0005\u0002EBQ!N\u0001\u0005\u0002YBQ!O\u0001\u0005\u0002i\n!BQ=uK\u000e{G-Z2t\u0015\tYA\"\u0001\u0005qS\u000e\\G.\u001b8h\u0015\tia\"\u0001\u0005j]R,'O\\1m\u0015\ty\u0001#A\u0004sK\u001adWm\u0019;\u000b\u0003E\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u0015\u00035\t!B\u0001\u0006CsR,7i\u001c3fGN\u001c\"!A\f\u0011\u0005aIR\"\u0001\t\n\u0005i\u0001\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002'\u0005I\u0011M^8jIj+'o\u001c\u000b\u0003?\u0015\u00022\u0001\u0007\u0011#\u0013\t\t\u0003CA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0019G%\u0011A\u0005\u0005\u0002\u0005\u0005f$X\rC\u0003'\u0007\u0001\u0007q$A\u0002te\u000e\faB]3hK:,'/\u0019;f5\u0016\u0014x\u000e\u0006\u0002*YA\u0011\u0001DK\u0005\u0003WA\u00111!\u00138u\u0011\u00151C\u00011\u0001 \u0003))gnY8eKb\"xn\u000e\u000b\u0003?=BQAJ\u0003A\u0002}\t!\u0002Z3d_\u0012,w\u0007^89)\rI#g\r\u0005\u0006M\u0019\u0001\ra\b\u0005\u0006i\u0019\u0001\r!K\u0001\u0007gJ\u001cG.\u001a8\u0002\r\u0015t7m\u001c3f)\tyr\u0007C\u00039\u000f\u0001\u0007q$\u0001\u0002yg\u00061A-Z2pI\u0016$\"!K\u001e\t\u000baB\u0001\u0019A\u0010"
)
public final class ByteCodecs {
   public static int decode(final byte[] xs) {
      return ByteCodecs$.MODULE$.decode(xs);
   }

   public static byte[] encode(final byte[] xs) {
      return ByteCodecs$.MODULE$.encode(xs);
   }

   public static int decode7to8(final byte[] src, final int srclen) {
      return ByteCodecs$.MODULE$.decode7to8(src, srclen);
   }

   public static byte[] encode8to7(final byte[] src) {
      return ByteCodecs$.MODULE$.encode8to7(src);
   }

   public static int regenerateZero(final byte[] src) {
      return ByteCodecs$.MODULE$.regenerateZero(src);
   }

   public static byte[] avoidZero(final byte[] src) {
      return ByteCodecs$.MODULE$.avoidZero(src);
   }
}
