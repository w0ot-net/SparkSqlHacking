package spire.random;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2A\u0001D\u0007\u0001%!I\u0011\u0004\u0001BC\u0002\u0013\u0005qB\u0007\u0005\tC\u0001\u0011\t\u0011)A\u00057!1!\u0005\u0001C\u0001\u001f\r:QaJ\u0007\t\u0002!2Q\u0001D\u0007\t\u0002%BQAI\u0003\u0005\u0002)BqaK\u0003C\u0002\u0013\u0005A\u0006\u0003\u0004.\u000b\u0001\u0006I\u0001\n\u0005\u0006]\u0015!\ta\f\u0005\u0006]\u0015!\t!\u000e\u0005\u0006]\u0015!\tA\u000f\u0002\u0005'\u0016,GM\u0003\u0002\u000f\u001f\u00051!/\u00198e_6T\u0011\u0001E\u0001\u0006gBL'/Z\u0002\u0001'\t\u00011\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0006Ef$Xm]\u000b\u00027A\u0019A\u0003\b\u0010\n\u0005u)\"!B!se\u0006L\bC\u0001\u000b \u0013\t\u0001SC\u0001\u0003CsR,\u0017A\u00022zi\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003I\u0019\u0002\"!\n\u0001\u000e\u00035AQ!G\u0002A\u0002m\tAaU3fIB\u0011Q%B\n\u0003\u000bM!\u0012\u0001K\u0001\u0005u\u0016\u0014x.F\u0001%\u0003\u0015QXM]8!\u0003\u0015\t\u0007\u000f\u001d7z)\t!\u0003\u0007C\u00032\u0013\u0001\u0007!'A\u0001o!\t!2'\u0003\u00025+\t\u0019\u0011J\u001c;\u0015\u0005\u00112\u0004\"B\u0019\u000b\u0001\u00049\u0004C\u0001\u000b9\u0013\tITC\u0001\u0003M_:<GC\u0001\u0013<\u0011\u0015I2\u00021\u0001\u001c\u0001"
)
public class Seed {
   private final byte[] bytes;

   public static Seed apply(final byte[] bytes) {
      return Seed$.MODULE$.apply(bytes);
   }

   public static Seed apply(final long n) {
      return Seed$.MODULE$.apply(n);
   }

   public static Seed apply(final int n) {
      return Seed$.MODULE$.apply(n);
   }

   public static Seed zero() {
      return Seed$.MODULE$.zero();
   }

   public byte[] bytes() {
      return this.bytes;
   }

   public Seed(final byte[] bytes) {
      this.bytes = bytes;
   }
}
