package breeze.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2a\u0001B\u0003!\u0004SQ\u0001\u0002C\t\u0001\u0005\u000b\u0007I\u0011\u0001\n\t\u0011y\u0001!\u0011!Q\u0001\nMAQa\b\u0001\u0005\u0002\u0001\u0012qa\u0015,E\u001b>$WM\u0003\u0002\u0007\u000f\u00051A.\u001b8bY\u001eT\u0011\u0001C\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\t){%IW\u000b\u0002'A\u0011Ac\u0007\b\u0003+e\u0001\"AF\u0007\u000e\u0003]Q!\u0001G\u0005\u0002\rq\u0012xn\u001c;?\u0013\tQR\"\u0001\u0004Qe\u0016$WMZ\u0005\u00039u\u0011aa\u0015;sS:<'B\u0001\u000e\u000e\u0003\u0015QuJ\u0011.!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011e\t\t\u0003E\u0001i\u0011!\u0002\u0005\u0006#\r\u0001\raE\u0015\u0004\u0001\u0015:#B\u0001\u0014\u0006\u0003-\u0019u.\u001c9mKR,7K\u0016#\u000b\u0005!*\u0011A\u0003*fIV\u001cW\rZ*W\t\u0002"
)
public abstract class SVDMode {
   private final String JOBZ;

   public String JOBZ() {
      return this.JOBZ;
   }

   public SVDMode(final String JOBZ) {
      this.JOBZ = JOBZ;
   }
}
