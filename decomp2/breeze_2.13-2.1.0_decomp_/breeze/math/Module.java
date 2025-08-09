package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZerosLike;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0019\rq\u0006C\u00034\u0001\u0019\rA\u0007C\u0003E\u0001\u0019\rQ\tC\u0003M\u0001\u0019\rQ\nC\u0003S\u0001\u0011\r1\u000bC\u0004Y\u0001\t\u0007i1A-\t\u000b9\u0004a\u0011A8\u0003\r5{G-\u001e7f\u0015\tYA\"\u0001\u0003nCRD'\"A\u0007\u0002\r\t\u0014X-\u001a>f\u0007\u0001)2\u0001E\u000f('\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\taI2DJ\u0007\u0002\u0015%\u0011!D\u0003\u0002\u001b\u0003\u0012$\u0017\u000e^5wKR+gn]8s\u0003\n,G.[1o\u000fJ|W\u000f\u001d\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qDA\u0001W#\t\u00013\u0005\u0005\u0002\u0013C%\u0011!e\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011B%\u0003\u0002&'\t\u0019\u0011I\\=\u0011\u0005q9C!\u0002\u0015\u0001\u0005\u0004y\"!A*\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0003C\u0001\n-\u0013\ti3C\u0001\u0003V]&$\u0018aB:dC2\f'o]\u000b\u0002aA\u0019\u0001$\r\u0014\n\u0005IR!\u0001\u0002*j]\u001e\fQa];c-Z+\u0012!\u000e\t\u0006myZ2d\u0007\b\u0003oqj\u0011\u0001\u000f\u0006\u0003si\n\u0011b\u001c9fe\u0006$xN]:\u000b\u0005mb\u0011A\u00027j]\u0006dw-\u0003\u0002>q\u0005)q\n]*vE&\u0011q\b\u0011\u0002\u0006\u00136\u0004HNM\u0005\u0003\u0003\n\u0013Q!\u0016$v]\u000eT!a\u0011\u0007\u0002\u000f\u001d,g.\u001a:jG\u0006A!0\u001a:p\u0019&\\W-F\u0001G!\u00119%jG\u000e\u000e\u0003!S!!\u0013\u001e\u0002\u000fM,\b\u000f]8si&\u00111\n\u0013\u0002\u0013\u0007\u0006t7I]3bi\u0016TVM]8t\u0019&\\W-A\u0003nk246+F\u0001O!\u0015yeh\u0007\u0014\u001c\u001d\t9\u0004+\u0003\u0002Rq\u0005Yq\n]'vYN\u001b\u0017\r\\1s\u0003\u001diW\u000f\u001c,T?6+\u0012\u0001\u0016\t\u0006+zZbe\u0007\b\u0003oYK!a\u0016\u001d\u0002\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e_\u0001\u0007Q\u0006\u001cx\n]:\u0016\u0003i\u0003BaW4\u001cU:\u0011A\f\u001a\b\u0003;\nt!AX1\u000e\u0003}S!\u0001\u0019\b\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0011BA2\r\u0003\u0019\u0019w.\u001c9bi&\u0011QMZ\u0001\r'\u000e\fG.Y\u001aD_6\u0004\u0018\r\u001e\u0006\u0003G2I!\u0001[5\u0003'\r{gN^3sg&|gn\u0014:Tk\n$\u0018\u0010]3\u000b\u0005\u00154\u0007cA6m75\t!(\u0003\u0002nu\tQa*^7fe&\u001cw\n]:\u0002\u000b\rdwn]3\u0015\tA\u001cXo\u001e\t\u0003%EL!A]\n\u0003\u000f\t{w\u000e\\3b]\")A\u000f\u0003a\u00017\u0005\t\u0011\rC\u0003w\u0011\u0001\u00071$A\u0001c\u0011\u0015A\b\u00021\u0001z\u0003%!x\u000e\\3sC:\u001cW\r\u0005\u0002\u0013u&\u00111p\u0005\u0002\u0007\t>,(\r\\3"
)
public interface Module extends AdditiveTensorAbelianGroup {
   Ring scalars();

   UFunc.UImpl2 subVV();

   CanCreateZerosLike zeroLike();

   UFunc.UImpl2 mulVS();

   // $FF: synthetic method
   static UFunc.UImpl2 mulVS_M$(final Module $this) {
      return $this.mulVS_M();
   }

   default UFunc.UImpl2 mulVS_M() {
      return this.mulVS();
   }

   Function1 hasOps();

   boolean close(final Object a, final Object b, final double tolerance);

   static void $init$(final Module $this) {
   }
}
