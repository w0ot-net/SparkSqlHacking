package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594qAB\u0004\u0011\u0002G\u0005AbB\u0003D\u000f!\u0005AIB\u0003\u0007\u000f!\u0005Q\tC\u0003Y\u0005\u0011\u0005\u0011\fC\u0003[\u0005\u0011\u00151\fC\u0004g\u0005\u0005\u0005I\u0011B4\u0003)\r{W.\\;uCRLg/Z*f[&4\u0017.\u001a7e\u0015\tA\u0011\"\u0001\u0003sS:<'\"\u0001\u0006\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001QCA\u0007\u001b'\u0015\u0001a\u0002F\u001fA!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\r\te.\u001f\t\u0004+YAR\"A\u0004\n\u0005]9!!C*f[&4\u0017.\u001a7e!\tI\"\u0004\u0004\u0001\u0005\u0013m\u0001\u0001\u0015!A\u0001\u0006\u0004a\"!A!\u0012\u0005uq\u0001CA\b\u001f\u0013\ty\u0002CA\u0004O_RD\u0017N\\4)\ri\tCEL\u001a9!\ty!%\u0003\u0002$!\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019SE\n\u0015(\u001d\tya%\u0003\u0002(!\u0005\u0019\u0011J\u001c;2\t\u0011JS&\u0005\b\u0003U5j\u0011a\u000b\u0006\u0003Y-\ta\u0001\u0010:p_Rt\u0014\"A\t2\u000b\rz\u0003GM\u0019\u000f\u0005=\u0001\u0014BA\u0019\u0011\u0003\u0011auN\\42\t\u0011JS&E\u0019\u0006GQ*tG\u000e\b\u0003\u001fUJ!A\u000e\t\u0002\u000b\u0019cw.\u0019;2\t\u0011JS&E\u0019\u0006GeRDh\u000f\b\u0003\u001fiJ!a\u000f\t\u0002\r\u0011{WO\u00197fc\u0011!\u0013&L\t\u0011\u0007Uq\u0004$\u0003\u0002@\u000f\tq1i\\7nkR\fG/\u001b<f%&<\u0007cA\u000bB1%\u0011!i\u0002\u0002\u001f\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u0007>lW.\u001e;bi&4Xm\u0012:pkB\fAcQ8n[V$\u0018\r^5wKN+W.\u001b4jK2$\u0007CA\u000b\u0003'\u0015\u0011a)S'Q!\tyq)\u0003\u0002I!\t1\u0011I\\=SK\u001a\u00042!\u0006&M\u0013\tYuAA\fBI\u0012LG/\u001b<f\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011Q\u0003\u0001\t\u0004+9c\u0015BA(\b\u0005qiU\u000f\u001c;ja2L7-\u0019;jm\u0016<%o\\;q\rVt7\r^5p]N\u0004\"!\u0015,\u000e\u0003IS!a\u0015+\u0002\u0005%|'\"A+\u0002\t)\fg/Y\u0005\u0003/J\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001#\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005q{FCA/a!\r)\u0002A\u0018\t\u00033}#Qa\u0007\u0003C\u0002qAQ!\u0019\u0003A\u0004u\u000b\u0011A\u001d\u0015\u0003\t\r\u0004\"a\u00043\n\u0005\u0015\u0004\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001i!\tIG.D\u0001k\u0015\tYG+\u0001\u0003mC:<\u0017BA7k\u0005\u0019y%M[3di\u0002"
)
public interface CommutativeSemifield extends Semifield, CommutativeRig, MultiplicativeCommutativeGroup {
   static CommutativeSemifield apply(final CommutativeSemifield r) {
      return CommutativeSemifield$.MODULE$.apply(r);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return CommutativeSemifield$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return CommutativeSemifield$.MODULE$.isAdditiveCommutative(ev);
   }
}
