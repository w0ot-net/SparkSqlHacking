package breeze.optimize;

import breeze.math.MutableInnerProductModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053q!\u0002\u0004\u0011\u0002G\u00051bB\u0003\u0017\r!\u0005qCB\u0003\u0006\r!\u0005\u0001\u0004C\u0003\u001a\u0005\u0011\u0005!\u0004C\u0003\u001c\u0005\u0011\u0005AD\u0001\u0006MS:,7+Z1sG\"T!a\u0002\u0005\u0002\u0011=\u0004H/[7ju\u0016T\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001\u0001\u0004\n\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\t\u0019B#D\u0001\u0007\u0013\t)bAA\u000bBaB\u0014x\u000e_5nCR,G*\u001b8f'\u0016\f'o\u00195\u0002\u00151Kg.Z*fCJ\u001c\u0007\u000e\u0005\u0002\u0014\u0005M\u0011!\u0001D\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003]\t1DZ;oGRLwN\u001c$s_6\u001cV-\u0019:dQ\u0012K'/Z2uS>tWcA\u000f0\u007fQ!a\u0004O\u001e>)\tyR\u0005E\u0002\u0014A\tJ!!\t\u0004\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\u0011\u00055\u0019\u0013B\u0001\u0013\u000f\u0005\u0019!u.\u001e2mK\")a\u0005\u0002a\u0002O\u0005!\u0001O]8e!\u0011A3&\f\u0012\u000e\u0003%R!A\u000b\u0005\u0002\t5\fG\u000f[\u0005\u0003Y%\u0012\u0011$T;uC\ndW-\u00138oKJ\u0004&o\u001c3vGRlu\u000eZ;mKB\u0011af\f\u0007\u0001\t\u0015\u0001DA1\u00012\u0005\u0005!\u0016C\u0001\u001a6!\ti1'\u0003\u00025\u001d\t9aj\u001c;iS:<\u0007CA\u00077\u0013\t9dBA\u0002B]fDQ!\u000f\u0003A\u0002i\n\u0011A\u001a\t\u0004'\u0001j\u0003\"\u0002\u001f\u0005\u0001\u0004i\u0013!\u0001=\t\u000by\"\u0001\u0019A\u0017\u0002\u0013\u0011L'/Z2uS>tG!\u0002!\u0005\u0005\u0004\t$!A%"
)
public interface LineSearch extends ApproximateLineSearch {
   static DiffFunction functionFromSearchDirection(final DiffFunction f, final Object x, final Object direction, final MutableInnerProductModule prod) {
      return LineSearch$.MODULE$.functionFromSearchDirection(f, x, direction, prod);
   }
}
