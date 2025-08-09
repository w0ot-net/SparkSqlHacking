package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQAF\u0001\u0005\u0002]AQ\u0001G\u0001\u0005\u0002e\t\u0001BQ5oCJLx\n\u001d\u0006\u0003\r\u001d\t\u0011b\u001c9fe\u0006$xN]:\u000b\u0005!I\u0011A\u00027j]\u0006dwMC\u0001\u000b\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001CA\u0007\u0002\u001b\u0005)!\u0001\u0003\"j]\u0006\u0014\u0018p\u00149\u0014\u0005\u0005\u0001\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0019\u0005\tbM]8n\u0007>\u0004\u00180\u00118e+B$\u0017\r^3\u0016\ti\tDg\n\u000b\u00047YZ\u0004C\u0002\u000f#KA\u001a\u0004G\u0004\u0002\u001eA5\taD\u0003\u0002 \u0013\u00059q-\u001a8fe&\u001c\u0017BA\u0011\u001f\u0003\u0015)f)\u001e8d\u0013\t\u0019CE\u0001\u0004V\u00136\u0004HN\r\u0006\u0003Cy\u0001\"AJ\u0014\r\u0001\u0011)\u0001f\u0001b\u0001S\t\u0011q\n]\t\u0003U5\u0002\"!E\u0016\n\u00051\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#9J!a\f\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002'c\u0011)!g\u0001b\u0001S\t\t\u0011\t\u0005\u0002'i\u0011)Qg\u0001b\u0001S\t\t!\tC\u00038\u0007\u0001\u000f\u0001(\u0001\u0002paB)A$O\u00131g%\u0011!\b\n\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HN\r\u0005\u0006y\r\u0001\u001d!P\u0001\u0005G>\u0004\u0018\u0010E\u0002?\u0003Bj\u0011a\u0010\u0006\u0003\u0001\u001e\tqa];qa>\u0014H/\u0003\u0002C\u007f\t91)\u00198D_BL\b"
)
public final class BinaryOp {
   public static UFunc.UImpl2 fromCopyAndUpdate(final UFunc.InPlaceImpl2 op, final CanCopy copy) {
      return BinaryOp$.MODULE$.fromCopyAndUpdate(op, copy);
   }
}
