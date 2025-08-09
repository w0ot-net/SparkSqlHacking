package org.apache.spark.sql.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E:Q\u0001B\u0003\t\u0002A1QAE\u0003\t\u0002MAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002u\tQ\u0002\u0015:pi>\u0014WOZ+uS2\u001c(B\u0001\u0004\b\u0003\u0011)H/\u001b7\u000b\u0005!I\u0011aA:rY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005E\tQ\"A\u0003\u0003\u001bA\u0013x\u000e^8ck\u001a,F/\u001b7t'\t\tA\u0003\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\t\u0011D]3bI\u0012+7o\u0019:jaR|'OR5mK\u000e{g\u000e^3oiR\u0011a\u0004\n\t\u0004+}\t\u0013B\u0001\u0011\u0017\u0005\u0015\t%O]1z!\t)\"%\u0003\u0002$-\t!!)\u001f;f\u0011\u0015)3\u00011\u0001'\u0003!1\u0017\u000e\\3QCRD\u0007CA\u0014/\u001d\tAC\u0006\u0005\u0002*-5\t!F\u0003\u0002,\u001f\u00051AH]8pizJ!!\f\f\u0002\rA\u0013X\rZ3g\u0013\ty\u0003G\u0001\u0004TiJLgn\u001a\u0006\u0003[Y\u0001"
)
public final class ProtobufUtils {
   public static byte[] readDescriptorFileContent(final String filePath) {
      return ProtobufUtils$.MODULE$.readDescriptorFileContent(filePath);
   }
}
