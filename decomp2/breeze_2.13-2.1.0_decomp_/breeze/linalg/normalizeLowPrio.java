package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0012b\u000e\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0011]>\u0014X.\u00197ju\u0016dun\u001e)sS>T!!\u0002\u0004\u0002\r1Lg.\u00197h\u0015\u00059\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006)bn\u001c:nC2L'0Z%na24uN\u001d$m_\u0006$XcA\f#YQ\u0011\u0001d\f\t\u00053i\u00013&D\u0001\u0001\u0013\tYBD\u0001\u0003J[Bd\u0017BA\u000f\u001f\u0005\u0015)f)\u001e8d\u0015\tyb!A\u0004hK:,'/[2\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\t\u0011\r\u0001\n\u0002\u0002)F\u0011Q\u0005\u000b\t\u0003\u0017\u0019J!a\n\u0007\u0003\u000f9{G\u000f[5oOB\u00111\"K\u0005\u0003U1\u00111!\u00118z!\t\tC\u0006B\u0003.\u0005\t\u0007aFA\u0001V#\t\u0001\u0003\u0006C\u00031\u0005\u0001\u000f\u0011'\u0001\u0003j[Bd\u0007#B\r3AQZ\u0013BA\u001a\u001d\u0005\u0015IU\u000e\u001d73!\tYQ'\u0003\u00027\u0019\t)a\t\\8bi:\u0011\u0001(O\u0007\u0002\t%\u0011!\bB\u0001\n]>\u0014X.\u00197ju\u0016L#\u0001\u0001\u001f\u000b\u0005i\"\u0001"
)
public interface normalizeLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl normalizeImplForFloat$(final normalizeLowPrio $this, final UFunc.UImpl2 impl) {
      return $this.normalizeImplForFloat(impl);
   }

   default UFunc.UImpl normalizeImplForFloat(final UFunc.UImpl2 impl) {
      return new UFunc.UImpl(impl) {
         private final UFunc.UImpl2 impl$3;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Object apply(final Object v) {
            return this.impl$3.apply(v, BoxesRunTime.boxToFloat(2.0F));
         }

         public {
            this.impl$3 = impl$3;
         }
      };
   }

   static void $init$(final normalizeLowPrio $this) {
   }
}
