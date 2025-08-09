package org.apache.spark.streaming.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E<a!\u0003\u0006\t\u00021!bA\u0002\f\u000b\u0011\u0003aq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0003\"\u0003\u0011\u0005!\u0005C\u0003@\u0003\u0011\u0005\u0001\tC\u0003G\u0003\u0011\u0005q\tC\u0003V\u0003\u0011\u0005a\u000bC\u0003d\u0003\u0011\u0005A\rC\u0003n\u0003\u0011\u0005a.A\u0005II\u001a\u001cX\u000b^5mg*\u00111\u0002D\u0001\u0005kRLGN\u0003\u0002\u000e\u001d\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sOB\u0011Q#A\u0007\u0002\u0015\tI\u0001\n\u001a4t+RLGn]\n\u0003\u0003a\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Q\tqbZ3u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u000b\u0004G-B\u0004C\u0001\u0013*\u001b\u0005)#B\u0001\u0014(\u0003\t17O\u0003\u0002)!\u00051\u0001.\u00193p_BL!AK\u0013\u0003%\u0019\u001bF)\u0019;b\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\u0006Y\r\u0001\r!L\u0001\u0005a\u0006$\b\u000e\u0005\u0002/k9\u0011qf\r\t\u0003aii\u0011!\r\u0006\u0003e}\ta\u0001\u0010:p_Rt\u0014B\u0001\u001b\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011ag\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005QR\u0002\"B\u001d\u0004\u0001\u0004Q\u0014\u0001B2p]\u001a\u0004\"aO\u001f\u000e\u0003qR!!O\u0014\n\u0005yb$!D\"p]\u001aLw-\u001e:bi&|g.\u0001\bhKRLe\u000e];u'R\u0014X-Y7\u0015\u0007\u0005#U\t\u0005\u0002%\u0005&\u00111)\n\u0002\u0012\rN#\u0015\r^1J]B,Ho\u0015;sK\u0006l\u0007\"\u0002\u0017\u0005\u0001\u0004i\u0003\"B\u001d\u0005\u0001\u0004Q\u0014AC2iK\u000e\\7\u000b^1uKR\u0019\u0001j\u0013)\u0011\u0005eI\u0015B\u0001&\u001b\u0005\u0011)f.\u001b;\t\u000b1+\u0001\u0019A'\u0002\u000bM$\u0018\r^3\u0011\u0005eq\u0015BA(\u001b\u0005\u001d\u0011un\u001c7fC:Da!U\u0003\u0005\u0002\u0004\u0011\u0016\u0001C3se>\u0014Xj]4\u0011\u0007e\u0019V&\u0003\u0002U5\tAAHY=oC6,g(A\fhKR4\u0015\u000e\\3TK\u001elWM\u001c;M_\u000e\fG/[8ogR)qKW.aEB\u0019\u0011\u0004W\u0017\n\u0005eS\"!B!se\u0006L\b\"\u0002\u0017\u0007\u0001\u0004i\u0003\"\u0002/\u0007\u0001\u0004i\u0016AB8gMN,G\u000f\u0005\u0002\u001a=&\u0011qL\u0007\u0002\u0005\u0019>tw\rC\u0003b\r\u0001\u0007Q,\u0001\u0004mK:<G\u000f\u001b\u0005\u0006s\u0019\u0001\rAO\u0001\u0015O\u0016$h)\u001b7f'f\u001cH/Z7G_J\u0004\u0016\r\u001e5\u0015\u0007\u0015DG\u000e\u0005\u0002%M&\u0011q-\n\u0002\u000b\r&dWmU=ti\u0016l\u0007\"\u0002\u0017\b\u0001\u0004I\u0007C\u0001\u0013k\u0013\tYWE\u0001\u0003QCRD\u0007\"B\u001d\b\u0001\u0004Q\u0014aD2iK\u000e\\g)\u001b7f\u000bbL7\u000f^:\u0015\u00075{\u0007\u000fC\u0003-\u0011\u0001\u0007Q\u0006C\u0003:\u0011\u0001\u0007!\b"
)
public final class HdfsUtils {
   public static boolean checkFileExists(final String path, final Configuration conf) {
      return HdfsUtils$.MODULE$.checkFileExists(path, conf);
   }

   public static FileSystem getFileSystemForPath(final Path path, final Configuration conf) {
      return HdfsUtils$.MODULE$.getFileSystemForPath(path, conf);
   }

   public static String[] getFileSegmentLocations(final String path, final long offset, final long length, final Configuration conf) {
      return HdfsUtils$.MODULE$.getFileSegmentLocations(path, offset, length, conf);
   }

   public static void checkState(final boolean state, final Function0 errorMsg) {
      HdfsUtils$.MODULE$.checkState(state, errorMsg);
   }

   public static FSDataInputStream getInputStream(final String path, final Configuration conf) {
      return HdfsUtils$.MODULE$.getInputStream(path, conf);
   }

   public static FSDataOutputStream getOutputStream(final String path, final Configuration conf) {
      return HdfsUtils$.MODULE$.getOutputStream(path, conf);
   }
}
