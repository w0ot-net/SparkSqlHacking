package org.apache.spark.storage;

import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005a2Q\u0001B\u0003\u0002\u00029A\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u00011\t\u0001\t\u0002\u000f)>\u0004x\u000e\\8hs6\u000b\u0007\u000f]3s\u0015\t1q!A\u0004ti>\u0014\u0018mZ3\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001fA\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\fAaY8oMB\u0011q\u0003G\u0007\u0002\u000f%\u0011\u0011d\u0002\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDC\u0001\u000f\u001f!\ti\u0002!D\u0001\u0006\u0011\u0015)\"\u00011\u0001\u0017\u0003I9W\r\u001e+pa>dwnZ=G_JDun\u001d;\u0015\u0005\u0005z\u0003c\u0001\t#I%\u00111%\u0005\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0015bcB\u0001\u0014+!\t9\u0013#D\u0001)\u0015\tIS\"\u0001\u0004=e>|GOP\u0005\u0003WE\ta\u0001\u0015:fI\u00164\u0017BA\u0017/\u0005\u0019\u0019FO]5oO*\u00111&\u0005\u0005\u0006a\r\u0001\r\u0001J\u0001\tQ>\u001cHO\\1nK\"\u0012\u0001A\r\t\u0003gYj\u0011\u0001\u000e\u0006\u0003k\u001d\t!\"\u00198o_R\fG/[8o\u0013\t9DG\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public abstract class TopologyMapper {
   public abstract Option getTopologyForHost(final String hostname);

   public TopologyMapper(final SparkConf conf) {
   }
}
