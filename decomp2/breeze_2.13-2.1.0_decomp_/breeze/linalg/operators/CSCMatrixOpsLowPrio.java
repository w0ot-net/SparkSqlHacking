package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.util.SerializableLogging;
import scala.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003\"\u0001\u0011\r!EA\nD'\u000ek\u0015\r\u001e:jq>\u00038\u000fT8x!JLwN\u0003\u0002\u0006\r\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003\u000f!\ta\u0001\\5oC2<'\"A\u0005\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019B\u0001\u0001\u0007\u0013-A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004\"a\u0005\u000b\u000e\u0003\u0011I!!\u0006\u0003\u0003)\r\u001b6)T1ue&Dx\n]:`\u000f\u0016tWM]5d!\t9\"$D\u0001\u0019\u0015\tI\u0002\"\u0001\u0003vi&d\u0017BA\u000e\u0019\u0005M\u0019VM]5bY&T\u0018M\u00197f\u0019><w-\u001b8h\u0003\u0019!\u0013N\\5uIQ\ta\u0004\u0005\u0002\u000e?%\u0011\u0001E\u0004\u0002\u0005+:LG/A\u0007dC:lU\u000f\\'`\u001b~#WMZ\u000b\u0004GQrDc\u0001\u0013E\u0013B)Q\u0005\u000b\u0018>]9\u00111CJ\u0005\u0003O\u0011\t1b\u00149Nk2l\u0015\r\u001e:jq&\u0011\u0011F\u000b\u0002\u0006\u00136\u0004HNM\u0005\u0003W1\u0012Q!\u0016$v]\u000eT!!\f\u0005\u0002\u000f\u001d,g.\u001a:jGB\u0019q\u0006\r\u001a\u000e\u0003\u0019I!!\r\u0004\u0003\u0013\r\u001b6)T1ue&D\bCA\u001a5\u0019\u0001!Q!\u000e\u0002C\u0002Y\u0012\u0011\u0001V\t\u0003oi\u0002\"!\u0004\u001d\n\u0005er!a\u0002(pi\"Lgn\u001a\t\u0003\u001bmJ!\u0001\u0010\b\u0003\u0007\u0005s\u0017\u0010\u0005\u00024}\u0011)qH\u0001b\u0001\u0001\n\t!)\u0005\u00028\u0003B\u0019qF\u0011\u001a\n\u0005\r3!AB'biJL\u0007\u0010C\u0003F\u0005\u0001\u000fa)\u0001\u0002cEB!QbR\u001fB\u0013\tAeB\u0001\t%Y\u0016\u001c8\u000fJ2pY>tG\u0005\\3tg\")!J\u0001a\u0002\u0017\u0006\u0011q\u000e\u001d\t\u0006K!r\u0013I\f"
)
public interface CSCMatrixOpsLowPrio extends CSCMatrixOps_Generic, SerializableLogging {
   // $FF: synthetic method
   static UFunc.UImpl2 canMulM_M_def$(final CSCMatrixOpsLowPrio $this, final .less.colon.less bb, final UFunc.UImpl2 op) {
      return $this.canMulM_M_def(bb, op);
   }

   default UFunc.UImpl2 canMulM_M_def(final .less.colon.less bb, final UFunc.UImpl2 op) {
      return op;
   }

   static void $init$(final CSCMatrixOpsLowPrio $this) {
   }
}
