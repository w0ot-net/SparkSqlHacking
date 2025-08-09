package breeze.optimize.linear;

import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512qAA\u0002\u0011\u0002G\u0005!\u0002C\u0003\u0012\u0001\u0019\u0005!CA\tCSB\f'\u000f^5uK6\u000bGo\u00195j]\u001eT!\u0001B\u0003\u0002\r1Lg.Z1s\u0015\t1q!\u0001\u0005paRLW.\u001b>f\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001Y\u0001C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g-A\bfqR\u0014\u0018m\u0019;NCR\u001c\u0007.\u001b8h)\t\u0019\u0002\u0006\u0005\u0003\r)Y)\u0013BA\u000b\u000e\u0005\u0019!V\u000f\u001d7feA\u0019qc\b\u0012\u000f\u0005aibBA\r\u001d\u001b\u0005Q\"BA\u000e\n\u0003\u0019a$o\\8u}%\ta\"\u0003\u0002\u001f\u001b\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0011\"\u0005\r\u0019V-\u001d\u0006\u0003=5\u0001\"\u0001D\u0012\n\u0005\u0011j!aA%oiB\u0011ABJ\u0005\u0003O5\u0011a\u0001R8vE2,\u0007\"B\u0015\u0002\u0001\u0004Q\u0013aB<fS\u001eDGo\u001d\t\u0004/}Y\u0003cA\f K\u0001"
)
public interface BipartiteMatching {
   Tuple2 extractMatching(final Seq weights);
}
