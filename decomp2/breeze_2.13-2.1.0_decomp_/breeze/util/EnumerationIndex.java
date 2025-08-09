package breeze.util;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00055:Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQ\u0001F\u0001\u0005\u0002UAQAF\u0001\u0005\u0002]\t\u0001#\u00128v[\u0016\u0014\u0018\r^5p]&sG-\u001a=\u000b\u0005\u00199\u0011\u0001B;uS2T\u0011\u0001C\u0001\u0007EJ,WM_3\u0004\u0001A\u00111\"A\u0007\u0002\u000b\t\u0001RI\\;nKJ\fG/[8o\u0013:$W\r_\n\u0003\u00039\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u000b\u0003\u0015\t\u0007\u000f\u001d7z+\tA\"\u0005\u0006\u0002\u001a?A\u00191B\u0007\u000f\n\u0005m)!!B%oI\u0016D\bCA\u000f,\u001d\tqr\u0004\u0004\u0001\t\u000b\u0001\u001a\u0001\u0019A\u0011\u0002\u0003Q\u0004\"A\b\u0012\u0005\u000b\r\u001a!\u0019\u0001\u0013\u0003\u0003Q\u000b\"!\n\u0015\u0011\u0005=1\u0013BA\u0014\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0015\n\u0005)\u0002\"aC#ok6,'/\u0019;j_:L!\u0001L\u0015\u0003\u000bY\u000bG.^3"
)
public final class EnumerationIndex {
   public static Index apply(final Enumeration t) {
      return EnumerationIndex$.MODULE$.apply(t);
   }
}
