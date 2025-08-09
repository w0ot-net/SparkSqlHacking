package cats.kernel;

import cats.kernel.instances.function.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0005\u0006e\u0001!\u0019a\r\u0005\u0006\u0003\u0002!\u0019A\u0011\u0002\u001b\u0007>lW.\u001e;bi&4X-T8o_&$\u0017J\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\taa[3s]\u0016d'\"A\u0005\u0002\t\r\fGo]\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u0013'5\ta!\u0003\u0002\u0015\r\tyQj\u001c8pS\u0012Len\u001d;b]\u000e,7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005A\u0002C\u0001\u0007\u001a\u0013\tQRB\u0001\u0003V]&$\u0018aJ2biN\\UM\u001d8fY\u000e{W.\\;uCRLg/Z'p]>LGMR8s\rVt7\r^5p]B*\"!\b\u0014\u0015\u0005yy\u0003c\u0001\n C%\u0011\u0001E\u0002\u0002\u0012\u0007>lW.\u001e;bi&4X-T8o_&$\u0007c\u0001\u0007#I%\u00111%\u0004\u0002\n\rVt7\r^5p]B\u0002\"!\n\u0014\r\u0001\u0011)qE\u0001b\u0001Q\t\t\u0011)\u0005\u0002*YA\u0011ABK\u0005\u0003W5\u0011qAT8uQ&tw\r\u0005\u0002\r[%\u0011a&\u0004\u0002\u0004\u0003:L\bb\u0002\u0019\u0003\u0003\u0003\u0005\u001d!M\u0001\fKZLG-\u001a8dK\u0012\nD\u0007E\u0002\u0013?\u0011\nqeY1ug.+'O\\3m\u0007>lW.\u001e;bi&4X-T8o_&$gi\u001c:Gk:\u001cG/[8ocU\u0019AG\u000f\u001f\u0015\u0005Ur\u0004c\u0001\n mA!AbN\u001d<\u0013\tATBA\u0005Gk:\u001cG/[8ocA\u0011QE\u000f\u0003\u0006O\r\u0011\r\u0001\u000b\t\u0003Kq\"Q!P\u0002C\u0002!\u0012\u0011A\u0011\u0005\b\u007f\r\t\t\u0011q\u0001A\u0003-)g/\u001b3f]\u000e,G%M\u001b\u0011\u0007Iy2(\u0001\u0013dCR\u001c8*\u001a:oK2\u001cu.\\7vi\u0006$\u0018N^3N_:|\u0017\u000e\u001a$pe>\u0003H/[8o+\t\u0019\u0015\n\u0006\u0002E\u0015B\u0019!cH#\u0011\u000711\u0005*\u0003\u0002H\u001b\t1q\n\u001d;j_:\u0004\"!J%\u0005\u000b\u001d\"!\u0019\u0001\u0015\t\u000f-#\u0011\u0011!a\u0002\u0019\u0006YQM^5eK:\u001cW\rJ\u00197!\r\u0011R\nS\u0005\u0003\u001d\u001a\u0011AcQ8n[V$\u0018\r^5wKN+W.[4s_V\u0004\b"
)
public interface CommutativeMonoidInstances extends MonoidInstances {
   // $FF: synthetic method
   static CommutativeMonoid catsKernelCommutativeMonoidForFunction0$(final CommutativeMonoidInstances $this, final CommutativeMonoid evidence$14) {
      return $this.catsKernelCommutativeMonoidForFunction0(evidence$14);
   }

   default CommutativeMonoid catsKernelCommutativeMonoidForFunction0(final CommutativeMonoid evidence$14) {
      return package$.MODULE$.catsKernelCommutativeMonoidForFunction0(evidence$14);
   }

   // $FF: synthetic method
   static CommutativeMonoid catsKernelCommutativeMonoidForFunction1$(final CommutativeMonoidInstances $this, final CommutativeMonoid evidence$15) {
      return $this.catsKernelCommutativeMonoidForFunction1(evidence$15);
   }

   default CommutativeMonoid catsKernelCommutativeMonoidForFunction1(final CommutativeMonoid evidence$15) {
      return package$.MODULE$.catsKernelCommutativeMonoidForFunction1(evidence$15);
   }

   // $FF: synthetic method
   static CommutativeMonoid catsKernelCommutativeMonoidForOption$(final CommutativeMonoidInstances $this, final CommutativeSemigroup evidence$16) {
      return $this.catsKernelCommutativeMonoidForOption(evidence$16);
   }

   default CommutativeMonoid catsKernelCommutativeMonoidForOption(final CommutativeSemigroup evidence$16) {
      return cats.kernel.instances.option.package$.MODULE$.catsKernelStdCommutativeMonoidForOption(evidence$16);
   }

   static void $init$(final CommutativeMonoidInstances $this) {
   }
}
