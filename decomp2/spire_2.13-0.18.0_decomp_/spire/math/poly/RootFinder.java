package spire.math.poly;

import java.math.MathContext;
import scala.reflect.ScalaSignature;
import spire.math.Polynomial;

@ScalaSignature(
   bytes = "\u0006\u000514q\u0001D\u0007\u0011\u0002G\u0005A\u0003C\u0003\u001d\u0001\u0019\u0005QdB\u00033\u001b!\u00051GB\u0003\r\u001b!\u0005A\u0007C\u00036\u0007\u0011\u0005a\u0007C\u00038\u0007\u0011\u0015\u0001\bC\u0003@\u0007\u0011\r\u0001\tC\u0003T\u0007\u0011\rA\u000bC\u0004_\u0007\t\u0007I1A0\t\r\u0011\u001c\u0001\u0015!\u0003a\u0011\u001d)7A1A\u0005\u0004\u0019Daa[\u0002!\u0002\u00139'A\u0003*p_R4\u0015N\u001c3fe*\u0011abD\u0001\u0005a>d\u0017P\u0003\u0002\u0011#\u0005!Q.\u0019;i\u0015\u0005\u0011\u0012!B:qSJ,7\u0001A\u000b\u0003+\u0011\u001a\"\u0001\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003%1\u0017N\u001c3S_>$8\u000f\u0006\u0002\u001f[A\u0019q\u0004\t\u0012\u000e\u00035I!!I\u0007\u0003\u000bI{w\u000e^:\u0011\u0005\r\"C\u0002\u0001\u0003\u0006K\u0001\u0011\rA\n\u0002\u0002\u0003F\u0011qE\u000b\t\u0003/!J!!\u000b\r\u0003\u000f9{G\u000f[5oOB\u0011qcK\u0005\u0003Ya\u00111!\u00118z\u0011\u0015q\u0011\u00011\u0001/!\ry\u0003GI\u0007\u0002\u001f%\u0011\u0011g\u0004\u0002\u000b!>d\u0017P\\8nS\u0006d\u0017A\u0003*p_R4\u0015N\u001c3feB\u0011qdA\n\u0003\u0007Y\ta\u0001P5oSRtD#A\u001a\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005ebDC\u0001\u001e>!\ry\u0002a\u000f\t\u0003Gq\"Q!J\u0003C\u0002\u0019BQAP\u0003A\u0004i\naAZ5oI\u0016\u0014\u0018!\u0007\"jO\u0012+7-[7bYN\u001b\u0017\r\\3S_>$h)\u001b8eKJ$\"!\u0011(\u0011\u0007}\u0001!\t\u0005\u0002D\u0017:\u0011A)\u0013\b\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000fN\ta\u0001\u0010:p_Rt\u0014\"A\r\n\u0005)C\u0012a\u00029bG.\fw-Z\u0005\u0003\u00196\u0013!BQ5h\t\u0016\u001c\u0017.\\1m\u0015\tQ\u0005\u0004C\u0003P\r\u0001\u0007\u0001+A\u0003tG\u0006dW\r\u0005\u0002\u0018#&\u0011!\u000b\u0007\u0002\u0004\u0013:$\u0018a\b\"jO\u0012+7-[7bY6\u000bG\u000f[\"p]R,\u0007\u0010\u001e*p_R4\u0015N\u001c3feR\u0011\u0011)\u0016\u0005\u0006-\u001e\u0001\raV\u0001\u0003[\u000e\u0004\"\u0001\u0017/\u000e\u0003eS!\u0001\u0005.\u000b\u0003m\u000bAA[1wC&\u0011Q,\u0017\u0002\f\u001b\u0006$\bnQ8oi\u0016DH/\u0001\bSK\u0006d'k\\8u\r&tG-\u001a:\u0016\u0003\u0001\u00042a\b\u0001b!\ty#-\u0003\u0002d\u001f\t!!+Z1m\u0003=\u0011V-\u00197S_>$h)\u001b8eKJ\u0004\u0013\u0001\u0005(v[\n,'OU8pi\u001aKg\u000eZ3s+\u00059\u0007cA\u0010\u0001QB\u0011q&[\u0005\u0003U>\u0011aAT;nE\u0016\u0014\u0018!\u0005(v[\n,'OU8pi\u001aKg\u000eZ3sA\u0001"
)
public interface RootFinder {
   static RootFinder NumberRootFinder() {
      return RootFinder$.MODULE$.NumberRootFinder();
   }

   static RootFinder RealRootFinder() {
      return RootFinder$.MODULE$.RealRootFinder();
   }

   static RootFinder BigDecimalMathContextRootFinder(final MathContext mc) {
      return RootFinder$.MODULE$.BigDecimalMathContextRootFinder(mc);
   }

   static RootFinder BigDecimalScaleRootFinder(final int scale) {
      return RootFinder$.MODULE$.BigDecimalScaleRootFinder(scale);
   }

   static RootFinder apply(final RootFinder finder) {
      return RootFinder$.MODULE$.apply(finder);
   }

   Roots findRoots(final Polynomial poly);
}
