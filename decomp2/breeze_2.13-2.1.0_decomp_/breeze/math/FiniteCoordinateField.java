package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZeros;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3q!\u0002\u0004\u0011\u0002G\u00051\u0002C\u0003)\u0001\u0019\r\u0011\u0006C\u00033\u0001\u0019\r1\u0007C\u0003@\u0001\u0019\r\u0001\tC\u0003K\u0001\u0019\r1JA\u000bGS:LG/Z\"p_J$\u0017N\\1uK\u001aKW\r\u001c3\u000b\u0005\u001dA\u0011\u0001B7bi\"T\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001U!A\"G\u0012''\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u000bQ)rCI\u0013\u000e\u0003\u0019I!A\u0006\u0004\u00033\u0015sW/\\3sCR,GmQ8pe\u0012Lg.\u0019;f\r&,G\u000e\u001a\t\u00031ea\u0001\u0001B\u0003\u001b\u0001\t\u00071DA\u0001W#\tar\u0004\u0005\u0002\u000f;%\u0011ad\u0004\u0002\b\u001d>$\b.\u001b8h!\tq\u0001%\u0003\u0002\"\u001f\t\u0019\u0011I\\=\u0011\u0005a\u0019C!\u0002\u0013\u0001\u0005\u0004Y\"!A%\u0011\u0005a1C!B\u0014\u0001\u0005\u0004Y\"!A*\u0002\ti,'o\\\u000b\u0002UA!1\u0006M\f#\u001b\u0005a#BA\u0017/\u0003\u001d\u0019X\u000f\u001d9peRT!a\f\u0005\u0002\r1Lg.\u00197h\u0013\t\tDF\u0001\bDC:\u001c%/Z1uKj+'o\\:\u0002\r\r\fg\u000eR5n+\u0005!\u0004\u0003B\u001b:/\tr!AN\u001c\u000e\u00039J!\u0001\u000f\u0018\u0002\u0007\u0011LW.\u0003\u0002;w\t!\u0011*\u001c9m\u0013\taTHA\u0003V\rVt7M\u0003\u0002?\u0011\u00059q-\u001a8fe&\u001c\u0017!B1eIZ\u001bV#A!\u0011\u000b\tCu#J\f\u000f\u0005\r3U\"\u0001#\u000b\u0005\u0015s\u0013!C8qKJ\fGo\u001c:t\u0013\t9E)A\u0003Pa\u0006#G-\u0003\u0002Jw\t)\u0011*\u001c9me\u0005)1/\u001e2W'V\tA\nE\u0003N\u0011^)sC\u0004\u0002D\u001d&\u0011q\nR\u0001\u0006\u001fB\u001cVO\u0019"
)
public interface FiniteCoordinateField extends EnumeratedCoordinateField {
   CanCreateZeros zero();

   UFunc.UImpl canDim();

   UFunc.UImpl2 addVS();

   UFunc.UImpl2 subVS();
}
