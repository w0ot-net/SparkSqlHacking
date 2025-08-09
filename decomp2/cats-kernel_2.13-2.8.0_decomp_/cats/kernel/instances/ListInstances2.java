package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u000f\u0019&\u001cH/\u00138ti\u0006t7-Z:3\u0015\t)a!A\u0005j]N$\u0018M\\2fg*\u0011q\u0001C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003%\tAaY1ugN\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\u0006\t\u0003\u0019UI!AF\u0007\u0003\tUs\u0017\u000e^\u0001\u0017G\u0006$8oS3s]\u0016d7\u000b\u001e3Fc\u001a{'\u000fT5tiV\u0011\u0011\u0004\f\u000b\u00035U\u00022a\u0007\u000f\u001f\u001b\u00051\u0011BA\u000f\u0007\u0005\t)\u0015\u000fE\u0002 O)r!\u0001I\u0013\u000f\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\r\u0012\u0012A\u0002\u001fs_>$h(C\u0001\u000f\u0013\t1S\"A\u0004qC\u000e\\\u0017mZ3\n\u0005!J#\u0001\u0002'jgRT!AJ\u0007\u0011\u0005-bC\u0002\u0001\u0003\u0006[\t\u0011\rA\f\u0002\u0002\u0003F\u0011qF\r\t\u0003\u0019AJ!!M\u0007\u0003\u000f9{G\u000f[5oOB\u0011AbM\u0005\u0003i5\u00111!\u00118z\u0011\u001d1$!!AA\u0004]\n!\"\u001a<jI\u0016t7-\u001a\u00135!\rYBD\u000b"
)
public interface ListInstances2 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForList$(final ListInstances2 $this, final Eq evidence$4) {
      return $this.catsKernelStdEqForList(evidence$4);
   }

   default Eq catsKernelStdEqForList(final Eq evidence$4) {
      return new ListEq(evidence$4);
   }

   static void $init$(final ListInstances2 $this) {
   }
}
