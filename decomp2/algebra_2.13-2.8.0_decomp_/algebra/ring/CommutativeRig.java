package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594qAB\u0004\u0011\u0002G\u0005AbB\u0003D\u000f!\u0005AIB\u0003\u0007\u000f!\u0005Q\tC\u0003Y\u0005\u0011\u0005\u0011\fC\u0003[\u0005\u0011\u00151\fC\u0004g\u0005\u0005\u0005I\u0011B4\u0003\u001d\r{W.\\;uCRLg/\u001a*jO*\u0011\u0001\"C\u0001\u0005e&twMC\u0001\u000b\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u000e5M)\u0001A\u0004\u000b>\u0001B\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t\u0019\u0011I\\=\u0011\u0007U1\u0002$D\u0001\b\u0013\t9rAA\u0002SS\u001e\u0004\"!\u0007\u000e\r\u0001\u0011I1\u0004\u0001Q\u0001\u0002\u0003\u0015\r\u0001\b\u0002\u0002\u0003F\u0011QD\u0004\t\u0003\u001fyI!a\b\t\u0003\u000f9{G\u000f[5oO\"2!$\t\u0013/ga\u0002\"a\u0004\u0012\n\u0005\r\u0002\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\u0013'Q\u001dr!a\u0004\u0014\n\u0005\u001d\u0002\u0012aA%oiF\"A%K\u0017\u0012\u001d\tQS&D\u0001,\u0015\ta3\"\u0001\u0004=e>|GOP\u0005\u0002#E*1e\f\u00193c9\u0011q\u0002M\u0005\u0003cA\tA\u0001T8oOF\"A%K\u0017\u0012c\u0015\u0019C'N\u001c7\u001d\tyQ'\u0003\u00027!\u0005)a\t\\8biF\"A%K\u0017\u0012c\u0015\u0019\u0013H\u000f\u001f<\u001d\ty!(\u0003\u0002<!\u00051Ai\\;cY\u0016\fD\u0001J\u0015.#A\u0019QC\u0010\r\n\u0005}:!aE\"p[6,H/\u0019;jm\u0016\u001cV-\\5sS:<\u0007cA\u000bB1%\u0011!i\u0002\u0002 \u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u0007>lW.\u001e;bi&4X-T8o_&$\u0017AD\"p[6,H/\u0019;jm\u0016\u0014\u0016n\u001a\t\u0003+\t\u0019RA\u0001$J\u001bB\u0003\"aD$\n\u0005!\u0003\"AB!osJ+g\rE\u0002\u0016\u00152K!aS\u0004\u0003/\u0005#G-\u001b;jm\u0016luN\\8jI\u001a+hn\u0019;j_:\u001c\bCA\u000b\u0001!\r)b\nT\u0005\u0003\u001f\u001e\u0011Q$T;mi&\u0004H.[2bi&4X-T8o_&$g)\u001e8di&|gn\u001d\t\u0003#Zk\u0011A\u0015\u0006\u0003'R\u000b!![8\u000b\u0003U\u000bAA[1wC&\u0011qK\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011\u000bQ!\u00199qYf,\"\u0001X0\u0015\u0005u\u0003\u0007cA\u000b\u0001=B\u0011\u0011d\u0018\u0003\u00067\u0011\u0011\r\u0001\b\u0005\u0006C\u0012\u0001\u001d!X\u0001\u0002e\"\u0012Aa\u0019\t\u0003\u001f\u0011L!!\u001a\t\u0003\r%tG.\u001b8f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005A\u0007CA5m\u001b\u0005Q'BA6U\u0003\u0011a\u0017M\\4\n\u00055T'AB(cU\u0016\u001cG\u000f"
)
public interface CommutativeRig extends Rig, CommutativeSemiring, MultiplicativeCommutativeMonoid {
   static CommutativeRig apply(final CommutativeRig r) {
      return CommutativeRig$.MODULE$.apply(r);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return CommutativeRig$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return CommutativeRig$.MODULE$.isAdditiveCommutative(ev);
   }
}
