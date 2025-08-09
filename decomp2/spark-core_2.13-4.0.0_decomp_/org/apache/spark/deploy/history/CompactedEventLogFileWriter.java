package org.apache.spark.deploy.history;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3AAC\u0006\u0005-!A1\u0004\u0001B\u0001B\u0003%A\u0004\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003&\u0011!\u0011\u0004A!A!\u0002\u0013\u0019\u0004\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u0011\u0001\u0003!\u0011!Q\u0001\n\u0005C\u0001\"\u0012\u0001\u0003\u0002\u0003\u0006IA\u0012\u0005\u0006\u0019\u0002!\t!\u0014\u0005\b+\u0002\u0011\r\u0011\"\u0011W\u0011\u00199\u0006\u0001)A\u0005K\tY2i\\7qC\u000e$X\rZ#wK:$Hj\\4GS2,wK]5uKJT!\u0001D\u0007\u0002\u000f!L7\u000f^8ss*\u0011abD\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001/A\u0011\u0001$G\u0007\u0002\u0017%\u0011!d\u0003\u0002\u0019'&tw\r\\3Fm\u0016tG\u000fT8h\r&dWm\u0016:ji\u0016\u0014\u0018\u0001E8sS\u001eLg.\u00197GS2,\u0007+\u0019;i!\ti\"%D\u0001\u001f\u0015\ty\u0002%\u0001\u0002gg*\u0011\u0011%E\u0001\u0007Q\u0006$wn\u001c9\n\u0005\rr\"\u0001\u0002)bi\"\fQ!\u00199q\u0013\u0012\u0004\"AJ\u0018\u000f\u0005\u001dj\u0003C\u0001\u0015,\u001b\u0005I#B\u0001\u0016\u0016\u0003\u0019a$o\\8u})\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\rM#(/\u001b8h\u0015\tq3&\u0001\u0007baB\fE\u000f^3naRLE\rE\u00025k\u0015j\u0011aK\u0005\u0003m-\u0012aa\u00149uS>t\u0017A\u00037pO\n\u000b7/\u001a#jeB\u0011\u0011HP\u0007\u0002u)\u00111\bP\u0001\u0004]\u0016$(\"A\u001f\u0002\t)\fg/Y\u0005\u0003\u007fi\u00121!\u0016*J\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0002C\u00076\tq\"\u0003\u0002E\u001f\tI1\u000b]1sW\u000e{gNZ\u0001\u000bQ\u0006$wn\u001c9D_:4\u0007CA$K\u001b\u0005A%BA%!\u0003\u0011\u0019wN\u001c4\n\u0005-C%!D\"p]\u001aLw-\u001e:bi&|g.\u0001\u0004=S:LGO\u0010\u000b\b\u001d>\u0003\u0016KU*U!\tA\u0002\u0001C\u0003\u001c\u000f\u0001\u0007A\u0004C\u0003%\u000f\u0001\u0007Q\u0005C\u00033\u000f\u0001\u00071\u0007C\u00038\u000f\u0001\u0007\u0001\bC\u0003A\u000f\u0001\u0007\u0011\tC\u0003F\u000f\u0001\u0007a)A\u0004m_\u001e\u0004\u0016\r\u001e5\u0016\u0003\u0015\n\u0001\u0002\\8h!\u0006$\b\u000e\t"
)
public class CompactedEventLogFileWriter extends SingleEventLogFileWriter {
   private final String logPath;

   public String logPath() {
      return this.logPath;
   }

   public CompactedEventLogFileWriter(final Path originalFilePath, final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      super(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf);
      String var10001 = originalFilePath.toUri().toString();
      this.logPath = var10001 + EventLogFileWriter$.MODULE$.COMPACTED();
   }
}
