package org.apache.spark.api.python;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!<aAB\u0004\t\u0002\u001d\tbAB\n\b\u0011\u00039A\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011\u0005q\u0004C\u0003>\u0003\u0011\u0005a\bC\u0003D\u0003\u0011\u0005A)\u0001\tQsRDwN\u001c%bI>|\u0007/\u0016;jY*\u0011\u0001\"C\u0001\u0007af$\bn\u001c8\u000b\u0005)Y\u0011aA1qS*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014x\r\u0005\u0002\u0013\u00035\tqA\u0001\tQsRDwN\u001c%bI>|\u0007/\u0016;jYN\u0011\u0011!\u0006\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!E\u0001\n[\u0006\u0004Hk\\\"p]\u001a$\"\u0001\t\u0015\u0011\u0005\u00052S\"\u0001\u0012\u000b\u0005\r\"\u0013\u0001B2p]\u001aT!!J\u0007\u0002\r!\fGm\\8q\u0013\t9#EA\u0007D_:4\u0017nZ;sCRLwN\u001c\u0005\u0006S\r\u0001\rAK\u0001\u0004[\u0006\u0004\b\u0003B\u00161eIj\u0011\u0001\f\u0006\u0003[9\nA!\u001e;jY*\tq&\u0001\u0003kCZ\f\u0017BA\u0019-\u0005\ri\u0015\r\u001d\t\u0003gir!\u0001\u000e\u001d\u0011\u0005U:R\"\u0001\u001c\u000b\u0005]b\u0012A\u0002\u001fs_>$h(\u0003\u0002:/\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\rM#(/\u001b8h\u0015\tIt#\u0001\u0006nKJ<WmQ8oMN$2\u0001I B\u0011\u0015\u0001E\u00011\u0001!\u0003\u0011aWM\u001a;\t\u000b\t#\u0001\u0019\u0001\u0011\u0002\u000bILw\r\u001b;\u0002\u0015\r|gN^3siJ#E)F\u0002F/z#BA\u0012*aKB\u0019qI\u0013'\u000e\u0003!S!!S\u0006\u0002\u0007I$G-\u0003\u0002L\u0011\n\u0019!\u000b\u0012#\u0011\tYiujT\u0005\u0003\u001d^\u0011a\u0001V;qY\u0016\u0014\u0004C\u0001\fQ\u0013\t\tvCA\u0002B]fDQ!S\u0003A\u0002M\u00032a\u0012&U!\u00111R*V/\u0011\u0005Y;F\u0002\u0001\u0003\u00061\u0016\u0011\r!\u0017\u0002\u0002\u0017F\u0011!l\u0014\t\u0003-mK!\u0001X\f\u0003\u000f9{G\u000f[5oOB\u0011aK\u0018\u0003\u0006?\u0016\u0011\r!\u0017\u0002\u0002-\")\u0011-\u0002a\u0001E\u0006a1.Z=D_:4XM\u001d;feB!!cY+P\u0013\t!wAA\u0005D_:4XM\u001d;fe\")a-\u0002a\u0001O\u0006qa/\u00197vK\u000e{gN^3si\u0016\u0014\b\u0003\u0002\nd;>\u0003"
)
public final class PythonHadoopUtil {
   public static RDD convertRDD(final RDD rdd, final Converter keyConverter, final Converter valueConverter) {
      return PythonHadoopUtil$.MODULE$.convertRDD(rdd, keyConverter, valueConverter);
   }

   public static Configuration mergeConfs(final Configuration left, final Configuration right) {
      return PythonHadoopUtil$.MODULE$.mergeConfs(left, right);
   }

   public static Configuration mapToConf(final Map map) {
      return PythonHadoopUtil$.MODULE$.mapToConf(map);
   }
}
