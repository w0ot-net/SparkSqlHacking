package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005q!\u0003\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u00011\t!\r\u0005\u0006\u0005\u0002!\ta\u0011\u0002\u0017'\u000e\fG.\u0019$sC\u000e$\u0018n\u001c8bY^\u0013\u0018\r\u001d9fe*\u0011aaB\u0001\u0005[\u0006$\bNC\u0001\t\u0003\u0015\u0019\b/\u001b:f+\tQ\u0011d\u0005\u0003\u0001\u0017M1\u0003C\u0001\u0007\u0012\u001b\u0005i!B\u0001\b\u0010\u0003\u0011a\u0017M\\4\u000b\u0003A\tAA[1wC&\u0011!#\u0004\u0002\u0007\u001f\nTWm\u0019;\u0011\u0007Q)r#D\u0001\u0006\u0013\t1RAA\nTG\u0006d\u0017MT;nKJL7m\u0016:baB,'\u000f\u0005\u0002\u001931\u0001A!\u0002\u000e\u0001\u0005\u0004a\"!A!\u0004\u0001E\u0011Qd\t\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\b\u001d>$\b.\u001b8h!\tqB%\u0003\u0002&?\t\u0019\u0011I\\=\u0011\u0007\u001dJs#D\u0001)\u0015\t1q$\u0003\u0002+Q\tQaI]1di&|g.\u00197\u0002\r\u0011Jg.\u001b;%)\u0005i\u0003C\u0001\u0010/\u0013\tysD\u0001\u0003V]&$\u0018!C:ueV\u001cG/\u001e:f+\u0005\u0011\u0004cA\u001a@/9\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001O\u000e\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0011BA\u001e\b\u0003\u001d\tGnZ3ce\u0006L!!\u0010 \u0002\u000fA\f7m[1hK*\u00111hB\u0005\u0003\u0001\u0006\u0013QAR5fY\u0012T!!\u0010 \u0002\u0007\u0011Lg\u000fF\u0002\u0018\t\u001aCQ!R\u0002A\u0002]\t\u0011\u0001\u001f\u0005\u0006\u000f\u000e\u0001\raF\u0001\u0002s\u0002"
)
public interface ScalaFractionalWrapper extends ScalaNumericWrapper, scala.math.Fractional {
   Field structure();

   // $FF: synthetic method
   static Object div$(final ScalaFractionalWrapper $this, final Object x, final Object y) {
      return $this.div(x, y);
   }

   default Object div(final Object x, final Object y) {
      return this.structure().div(x, y);
   }

   static void $init$(final ScalaFractionalWrapper $this) {
   }
}
