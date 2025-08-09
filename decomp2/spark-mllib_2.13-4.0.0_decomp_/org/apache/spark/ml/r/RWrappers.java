package org.apache.spark.ml.r;

import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.SparkSession;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U:a\u0001B\u0003\t\u0002\u0015yaAB\t\u0006\u0011\u0003)!\u0003C\u0003\"\u0003\u0011\u00051\u0005C\u0003%\u0003\u0011\u0005S%A\u0005S/J\f\u0007\u000f]3sg*\u0011aaB\u0001\u0002e*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'o\u001a\t\u0003!\u0005i\u0011!\u0002\u0002\n%^\u0013\u0018\r\u001d9feN\u001c\"!A\n\u0011\u0007Q9\u0012$D\u0001\u0016\u0015\t1r!\u0001\u0003vi&d\u0017B\u0001\r\u0016\u0005!iEJU3bI\u0016\u0014\bC\u0001\u000e \u001b\u0005Y\"B\u0001\u000f\u001e\u0003\u0011a\u0017M\\4\u000b\u0003y\tAA[1wC&\u0011\u0001e\u0007\u0002\u0007\u001f\nTWm\u0019;\u0002\rqJg.\u001b;?\u0007\u0001!\u0012aD\u0001\u0005Y>\fG\r\u0006\u0002\u001aM!)qe\u0001a\u0001Q\u0005!\u0001/\u0019;i!\tI#G\u0004\u0002+aA\u00111FL\u0007\u0002Y)\u0011QFI\u0001\u0007yI|w\u000e\u001e \u000b\u0003=\nQa]2bY\u0006L!!\r\u0018\u0002\rA\u0013X\rZ3g\u0013\t\u0019DG\u0001\u0004TiJLgn\u001a\u0006\u0003c9\u0002"
)
public final class RWrappers {
   public static Object load(final String path) {
      return RWrappers$.MODULE$.load(path);
   }

   public static MLReader session(final SparkSession sparkSession) {
      return RWrappers$.MODULE$.session(sparkSession);
   }
}
