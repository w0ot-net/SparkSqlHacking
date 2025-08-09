package breeze.math;

import breeze.generic.UFunc;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003%\u0001\u0011\u0005Q\u0005C\u0003*\u0001\u0019\r!\u0006C\u0003;\u0001\u0011\r1H\u0001\nJ]:,'\u000f\u0015:pIV\u001cG/T8ek2,'B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\taA\u0019:fKj,7\u0001A\u000b\u0004\u0017a\u00113c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004Ba\u0005\u000b\u0017C5\tQ!\u0003\u0002\u0016\u000b\taaj\u001c:nK\u0012lu\u000eZ;mKB\u0011q\u0003\u0007\u0007\u0001\t\u0015I\u0002A1\u0001\u001b\u0005\u00051\u0016CA\u000e\u001f!\tiA$\u0003\u0002\u001e\u001d\t9aj\u001c;iS:<\u0007CA\u0007 \u0013\t\u0001cBA\u0002B]f\u0004\"a\u0006\u0012\u0005\u000b\r\u0002!\u0019\u0001\u000e\u0003\u0003M\u000ba\u0001J5oSR$C#\u0001\u0014\u0011\u000559\u0013B\u0001\u0015\u000f\u0005\u0011)f.\u001b;\u0002\u000b\u0011|GO\u0016,\u0016\u0003-\u0002R\u0001\f\u001b\u0017-\u0005r!!\f\u001a\u000e\u00039R!a\f\u0019\u0002\u0013=\u0004XM]1u_J\u001c(BA\u0019\b\u0003\u0019a\u0017N\\1mO&\u00111GL\u0001\u000b\u001fBlU\u000f\\%o]\u0016\u0014\u0018BA\u001b7\u0005\u0015IU\u000e\u001d73\u0013\t9\u0004HA\u0003V\rVt7M\u0003\u0002:\u000f\u00059q-\u001a8fe&\u001c\u0017\u0001\u00038pe6LU\u000e\u001d7\u0016\u0003q\u0002B!P!\u0017\u0007:\u0011ahP\u0007\u0002a%\u0011\u0001\tM\u0001\u0005]>\u0014X.\u0003\u0002Cm\t!\u0011*\u001c9m!\tiA)\u0003\u0002F\u001d\t1Ai\\;cY\u0016\u0004"
)
public interface InnerProductModule extends NormedModule {
   UFunc.UImpl2 dotVV();

   // $FF: synthetic method
   static UFunc.UImpl normImpl$(final InnerProductModule $this) {
      return $this.normImpl();
   }

   default UFunc.UImpl normImpl() {
      return new UFunc.UImpl() {
         // $FF: synthetic field
         private final InnerProductModule $outer;

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

         public double apply(final Object v) {
            return .MODULE$.sqrt(this.$outer.scalars().sNorm(this.$outer.dotVV().apply(v, v)));
         }

         public {
            if (InnerProductModule.this == null) {
               throw null;
            } else {
               this.$outer = InnerProductModule.this;
            }
         }
      };
   }

   static void $init$(final InnerProductModule $this) {
   }
}
