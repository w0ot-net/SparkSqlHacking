package org.apache.spark.deploy.history;

import java.util.zip.ZipOutputStream;
import org.apache.spark.SparkException;
import org.apache.spark.ui.SparkUI;
import scala.Option;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dcA\u0002\b\u0010\u0003\u0003y\u0011\u0004C\u0003!\u0001\u0011\u0005!\u0005C\u0003&\u0001\u0011\u0005a\u0005C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\u0005\u0001\u0007C\u0003H\u0001\u0019\u0005\u0001\nC\u0003]\u0001\u0011\u0005Q\fC\u0003b\u0001\u0011\u0005Q\fC\u0003c\u0001\u0011\u00051\rC\u0003h\u0001\u0019\u0005\u0001\u000eC\u0004\u0002\u0002\u00011\t!a\u0001\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u0011q\u0004\u0001\u0005\u0002\u0005\u0005\u0002bBA\u001b\u0001\u0019\u0005\u0011q\u0007\u0002\u001b\u0003B\u0004H.[2bi&|g\u000eS5ti>\u0014\u0018\u0010\u0015:pm&$WM\u001d\u0006\u0003!E\tq\u0001[5ti>\u0014\u0018P\u0003\u0002\u0013'\u00051A-\u001a9m_fT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\n\u0003\u0001i\u0001\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\r\u0002\"\u0001\n\u0001\u000e\u0003=\t\u0001dZ3u\u000bZ,g\u000e\u001e'pON,f\u000eZ3s!J|7-Z:t)\u00059\u0003CA\u000e)\u0013\tICDA\u0002J]R\f!cZ3u\u0019\u0006\u001cH/\u00169eCR,G\rV5nKR\tA\u0006\u0005\u0002\u001c[%\u0011a\u0006\b\u0002\u0005\u0019>tw-\u0001\u0006hKRd\u0015n\u001d;j]\u001e$\u0012!\r\t\u0004eijdBA\u001a9\u001d\t!t'D\u00016\u0015\t1\u0014%\u0001\u0004=e>|GOP\u0005\u0002;%\u0011\u0011\bH\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tID\u0004\u0005\u0002?\u000b6\tqH\u0003\u0002A\u0003\u0006\u0011a/\r\u0006\u0003\u0005\u000e\u000b1!\u00199j\u0015\t!5#\u0001\u0004ti\u0006$Xo]\u0005\u0003\r~\u0012q\"\u00119qY&\u001c\u0017\r^5p]&sgm\\\u0001\tO\u0016$\u0018\t\u001d9V\u0013R\u0019\u0011jT-\u0011\u0007mQE*\u0003\u0002L9\t1q\n\u001d;j_:\u0004\"\u0001J'\n\u00059{!a\u0003'pC\u0012,G-\u00119q+&CQ\u0001U\u0003A\u0002E\u000bQ!\u00199q\u0013\u0012\u0004\"A\u0015,\u000f\u0005M#\u0006C\u0001\u001b\u001d\u0013\t)F$\u0001\u0004Qe\u0016$WMZ\u0005\u0003/b\u0013aa\u0015;sS:<'BA+\u001d\u0011\u0015QV\u00011\u0001\\\u0003%\tG\u000f^3naRLE\rE\u0002\u001c\u0015F\u000bAa\u001d;paR\ta\f\u0005\u0002\u001c?&\u0011\u0001\r\b\u0002\u0005+:LG/A\u0003ti\u0006\u0014H/A\u0005hKR\u001cuN\u001c4jOR\tA\r\u0005\u0003SKF\u000b\u0016B\u00014Y\u0005\ri\u0015\r]\u0001\u000foJLG/Z#wK:$Hj\\4t)\u0011q\u0016N[6\t\u000bAK\u0001\u0019A)\t\u000biK\u0001\u0019A.\t\u000b1L\u0001\u0019A7\u0002\u0013iL\u0007o\u0015;sK\u0006l\u0007C\u00018v\u001b\u0005y'B\u00019r\u0003\rQ\u0018\u000e\u001d\u0006\u0003eN\fA!\u001e;jY*\tA/\u0001\u0003kCZ\f\u0017B\u0001<p\u0005=Q\u0016\u000e](viB,Ho\u0015;sK\u0006l\u0007fA\u0005y\u007fB\u00191$_>\n\u0005id\"A\u0002;ie><8\u000f\u0005\u0002}{6\t1#\u0003\u0002\u007f'\tq1\u000b]1sW\u0016C8-\u001a9uS>t7%A>\u0002%\u001d,G/\u00119qY&\u001c\u0017\r^5p]&sgm\u001c\u000b\u0005\u0003\u000b\t9\u0001E\u0002\u001c\u0015vBQ\u0001\u0015\u0006A\u0002E\u000b1cZ3u\u000b6\u0004H/\u001f'jgRLgn\u001a%u[2$\"!!\u0004\u0011\u000bI\ny!a\u0005\n\u0007\u0005EAHA\u0002TKF\u0004B!!\u0006\u0002\u001c5\u0011\u0011q\u0003\u0006\u0004\u00033a\u0012a\u0001=nY&!\u0011QDA\f\u0005\u0011qu\u000eZ3\u0002\u0019=tW+\u0013#fi\u0006\u001c\u0007.\u001a3\u0015\u000fy\u000b\u0019#!\n\u0002(!)\u0001\u000b\u0004a\u0001#\")!\f\u0004a\u00017\"9\u0011\u0011\u0006\u0007A\u0002\u0005-\u0012AA;j!\u0011\ti#!\r\u000e\u0005\u0005=\"bAA\u0015'%!\u00111GA\u0018\u0005\u001d\u0019\u0006/\u0019:l+&\u000bac\u00195fG.,\u0016JV5foB+'/\\5tg&|gn\u001d\u000b\t\u0003s\ty$!\u0011\u0002DA\u00191$a\u000f\n\u0007\u0005uBDA\u0004C_>dW-\u00198\t\u000bAk\u0001\u0019A)\t\u000bik\u0001\u0019A.\t\r\u0005\u0015S\u00021\u0001R\u0003\u0011)8/\u001a:"
)
public abstract class ApplicationHistoryProvider {
   public int getEventLogsUnderProcess() {
      return 0;
   }

   public long getLastUpdatedTime() {
      return 0L;
   }

   public abstract Iterator getListing();

   public abstract Option getAppUI(final String appId, final Option attemptId);

   public void stop() {
   }

   public void start() {
   }

   public Map getConfig() {
      return (Map).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public abstract void writeEventLogs(final String appId, final Option attemptId, final ZipOutputStream zipStream) throws SparkException;

   public abstract Option getApplicationInfo(final String appId);

   public Seq getEmptyListingHtml() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public void onUIDetached(final String appId, final Option attemptId, final SparkUI ui) {
   }

   public abstract boolean checkUIViewPermissions(final String appId, final Option attemptId, final String user);
}
