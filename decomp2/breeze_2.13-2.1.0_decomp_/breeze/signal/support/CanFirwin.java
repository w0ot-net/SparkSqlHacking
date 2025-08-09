package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.signal.OptWindowFunction;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4qAC\u0006\u0011\u0002G\u0005!\u0003C\u0003\u001b\u0001\u0019\u00051dB\u0003M\u0017!\u0005QJB\u0003\u000b\u0017!\u0005a\nC\u0003P\u0007\u0011\u0005\u0001\u000bC\u0003R\u0007\u0011\r!\u000bC\u0003U\u0007\u0011\rQ\u000bC\u0003X\u0007\u0011\r\u0001\fC\u0003^\u0007\u0011\ra\fC\u0003d\u0007\u0011\u0005AMA\u0005DC:4\u0015N]<j]*\u0011A\"D\u0001\bgV\u0004\bo\u001c:u\u0015\tqq\"\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002!\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u0014EM\u0011\u0001\u0001\u0006\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0011qY\u0003gO\u001fC\t\u001a\u00032!\b\u0010!\u001b\u0005Y\u0011BA\u0010\f\u0005-1\u0015JU&fe:,G.\r#\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\u0001\u0011\r\u0001\n\u0002\u0007\u001fV$\b/\u001e;\u0012\u0005\u0015B\u0003CA\u000b'\u0013\t9cCA\u0004O_RD\u0017N\\4\u0011\u0005UI\u0013B\u0001\u0016\u0017\u0005\r\te.\u001f\u0005\u0006Y\u0005\u0001\r!L\u0001\u0005i\u0006\u00048\u000f\u0005\u0002\u0016]%\u0011qF\u0006\u0002\u0004\u0013:$\b\"B\u0019\u0002\u0001\u0004\u0011\u0014AB8nK\u001e\f7\u000fE\u00024maj\u0011\u0001\u000e\u0006\u0003k=\ta\u0001\\5oC2<\u0017BA\u001c5\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005UI\u0014B\u0001\u001e\u0017\u0005\u0019!u.\u001e2mK\")A(\u0001a\u0001q\u00059a._9vSN$\b\"\u0002 \u0002\u0001\u0004y\u0014\u0001\u0003>fe>\u0004\u0016m]:\u0011\u0005U\u0001\u0015BA!\u0017\u0005\u001d\u0011un\u001c7fC:DQaQ\u0001A\u0002}\nQa]2bY\u0016DQ!R\u0001A\u0002a\n!\"\\;mi&\u0004H.[3s\u0011\u00159\u0015\u00011\u0001I\u0003%y\u0007\u000f^,j]\u0012|w\u000f\u0005\u0002J\u00156\tQ\"\u0003\u0002L\u001b\t\tr\n\u001d;XS:$wn\u001e$v]\u000e$\u0018n\u001c8\u0002\u0013\r\u000bgNR5so&t\u0007CA\u000f\u0004'\t\u0019A#\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u0006aa-\u001b:xS:$u.\u001e2mKV\t1\u000bE\u0002\u001e\u0001a\n1BZ5so&tGkX%oiV\ta\u000bE\u0002\u001e\u00015\nABZ5so&tGk\u0018'p]\u001e,\u0012!\u0017\t\u0004;\u0001Q\u0006CA\u000b\\\u0013\tafC\u0001\u0003M_:<\u0017!\u00044je^Lg\u000eV0GY>\fG/F\u0001`!\ri\u0002\u0001\u0019\t\u0003+\u0005L!A\u0019\f\u0003\u000b\u0019cw.\u0019;\u0002!\u0019L'o^5o\t>,(\r\\3J[BdGc\u0002\u001afM\u001eD\u0017N\u001b\u0005\u0006Y%\u0001\r!\f\u0005\u0006c%\u0001\rA\r\u0005\u0006y%\u0001\r\u0001\u000f\u0005\u0006}%\u0001\ra\u0010\u0005\u0006\u0007&\u0001\ra\u0010\u0005\u0006\u000f&\u0001\r\u0001\u0013"
)
public interface CanFirwin {
   static DenseVector firwinDoubleImpl(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final OptWindowFunction optWindow) {
      return CanFirwin$.MODULE$.firwinDoubleImpl(taps, omegas, nyquist, zeroPass, scale, optWindow);
   }

   static CanFirwin firwinT_Float() {
      return CanFirwin$.MODULE$.firwinT_Float();
   }

   static CanFirwin firwinT_Long() {
      return CanFirwin$.MODULE$.firwinT_Long();
   }

   static CanFirwin firwinT_Int() {
      return CanFirwin$.MODULE$.firwinT_Int();
   }

   static CanFirwin firwinDouble() {
      return CanFirwin$.MODULE$.firwinDouble();
   }

   FIRKernel1D apply(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow);
}
