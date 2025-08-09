package cats.kernel.instances;

import cats.kernel.Hash;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0017\u0001\u0011\u0005q\u0003C\u0003\u001c\u0001\u0011\rAD\u0001\u0007TKRLen\u001d;b]\u000e,7O\u0003\u0002\u0006\r\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\taa[3s]\u0016d'\"A\u0005\u0002\t\r\fGo]\u0002\u0001'\r\u0001AB\u0005\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005M!R\"\u0001\u0003\n\u0005U!!!D*fi&s7\u000f^1oG\u0016\u001c\u0018'\u0001\u0004%S:LG\u000f\n\u000b\u00021A\u0011Q\"G\u0005\u000359\u0011A!\u00168ji\u000692-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012D\u0015m\u001d5G_J\u001cV\r^\u000b\u0003;=*\u0012A\b\t\u0004?\u0001\u0012S\"\u0001\u0004\n\u0005\u00052!\u0001\u0002%bg\"\u00042a\t\u0016.\u001d\t!\u0003\u0006\u0005\u0002&\u001d5\taE\u0003\u0002(\u0015\u00051AH]8pizJ!!\u000b\b\u0002\rA\u0013X\rZ3g\u0013\tYCFA\u0002TKRT!!\u000b\b\u0011\u00059zC\u0002\u0001\u0003\u0006a\t\u0011\r!\r\u0002\u0002\u0003F\u0011!'\u000e\t\u0003\u001bMJ!\u0001\u000e\b\u0003\u000f9{G\u000f[5oOB\u0011QBN\u0005\u0003o9\u00111!\u00118z\u0001"
)
public interface SetInstances extends SetInstances1 {
   // $FF: synthetic method
   static Hash catsKernelStdHashForSet$(final SetInstances $this) {
      return $this.catsKernelStdHashForSet();
   }

   default Hash catsKernelStdHashForSet() {
      return new SetHash();
   }

   static void $init$(final SetInstances $this) {
   }
}
