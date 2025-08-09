package spire.syntax;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u0007TS\u001etW\rZ*z]R\f\u0007P\u0003\u0002\u0006\r\u000511/\u001f8uCbT\u0011aB\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"\u0001\u0003\n\u0005M!!aC(sI\u0016\u00148+\u001f8uCb\fa\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002\u0013MLwM\\3e\u001fB\u001cXCA\u000e#)\taR\b\u0006\u0002\u001eWA\u0019\u0011C\b\u0011\n\u0005}!!!C*jO:,Gm\u00149t!\t\t#\u0005\u0004\u0001\u0005\u000b\r\u0012!\u0019\u0001\u0013\u0003\u0003\u0005\u000b\"!\n\u0015\u0011\u0005-1\u0013BA\u0014\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aC\u0015\n\u0005)b!aA!os\"9AFAA\u0001\u0002\bi\u0013AC3wS\u0012,gnY3%iA\u0019aF\u000f\u0011\u000f\u0005=:dB\u0001\u00196\u001d\t\tD'D\u00013\u0015\t\u0019\u0004\"\u0001\u0004=e>|GOP\u0005\u0002\u000f%\u0011aGB\u0001\bC2<WM\u0019:b\u0013\tA\u0014(A\u0004qC\u000e\\\u0017mZ3\u000b\u0005Y2\u0011BA\u001e=\u0005\u0019\u0019\u0016n\u001a8fI*\u0011\u0001(\u000f\u0005\u0006}\t\u0001\r\u0001I\u0001\u0002C\u0002"
)
public interface SignedSyntax extends OrderSyntax {
   // $FF: synthetic method
   static SignedOps signedOps$(final SignedSyntax $this, final Object a, final Signed evidence$4) {
      return $this.signedOps(a, evidence$4);
   }

   default SignedOps signedOps(final Object a, final Signed evidence$4) {
      return new SignedOps(a, evidence$4);
   }

   static void $init$(final SignedSyntax $this) {
   }
}
