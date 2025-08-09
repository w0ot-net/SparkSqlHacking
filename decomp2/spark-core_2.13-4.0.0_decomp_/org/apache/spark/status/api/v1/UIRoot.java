package org.apache.spark.status.api.v1;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.zip.ZipOutputStream;
import org.apache.spark.SecurityManager;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a\u0001\u0003\u0005\n!\u0003\r\taD\u000b\t\u000bq\u0001A\u0011\u0001\u0010\t\u000b\t\u0002a\u0011A\u0012\t\u000b9\u0003a\u0011A(\t\u000bu\u0003a\u0011\u00010\t\u000b\u0005\u0004A\u0011\u00012\t\u000bE\u0004a\u0011\u0001:\t\u000b]\u0004a\u0011\u0001=\u0003\rUK%k\\8u\u0015\tQ1\"\u0001\u0002wc)\u0011A\"D\u0001\u0004CBL'B\u0001\b\u0010\u0003\u0019\u0019H/\u0019;vg*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0005\u0002\u0001-A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002?A\u0011q\u0003I\u0005\u0003Ca\u0011A!\u00168ji\u0006Yq/\u001b;i'B\f'o[+J+\t!\u0003\u0006F\u0002&y%#\"AJ\u0019\u0011\u0005\u001dBC\u0002\u0001\u0003\u0006S\t\u0011\rA\u000b\u0002\u0002)F\u00111F\f\t\u0003/1J!!\f\r\u0003\u000f9{G\u000f[5oOB\u0011qcL\u0005\u0003aa\u00111!\u00118z\u0011\u0015\u0011$\u00011\u00014\u0003\t1g\u000e\u0005\u0003\u0018iY2\u0013BA\u001b\u0019\u0005%1UO\\2uS>t\u0017\u0007\u0005\u00028u5\t\u0001H\u0003\u0002:\u001f\u0005\u0011Q/[\u0005\u0003wa\u0012qa\u00159be.,\u0016\nC\u0003>\u0005\u0001\u0007a(A\u0003baBLE\r\u0005\u0002@\r:\u0011\u0001\t\u0012\t\u0003\u0003bi\u0011A\u0011\u0006\u0003\u0007v\ta\u0001\u0010:p_Rt\u0014BA#\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011q\t\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015C\u0002\"\u0002&\u0003\u0001\u0004Y\u0015!C1ui\u0016l\u0007\u000f^%e!\r9BJP\u0005\u0003\u001bb\u0011aa\u00149uS>t\u0017AF4fi\u0006\u0003\b\u000f\\5dCRLwN\\%oM>d\u0015n\u001d;\u0016\u0003A\u00032!\u0015,Z\u001d\t\u0011FK\u0004\u0002B'&\t\u0011$\u0003\u0002V1\u00059\u0001/Y2lC\u001e,\u0017BA,Y\u0005!IE/\u001a:bi>\u0014(BA+\u0019!\tQ6,D\u0001\n\u0013\ta\u0016BA\bBaBd\u0017nY1uS>t\u0017J\u001c4p\u0003I9W\r^!qa2L7-\u0019;j_:LeNZ8\u0015\u0005}\u0003\u0007cA\fM3\")Q\b\u0002a\u0001}\u0005qqO]5uK\u00163XM\u001c;M_\u001e\u001cH\u0003B\u0010dI\u0016DQ!P\u0003A\u0002yBQAS\u0003A\u0002-CQAZ\u0003A\u0002\u001d\f\u0011B_5q'R\u0014X-Y7\u0011\u0005!|W\"A5\u000b\u0005)\\\u0017a\u0001>ja*\u0011A.\\\u0001\u0005kRLGNC\u0001o\u0003\u0011Q\u0017M^1\n\u0005AL'a\u0004.ja>+H\u000f];u'R\u0014X-Y7\u0002\u001fM,7-\u001e:jifl\u0015M\\1hKJ,\u0012a\u001d\t\u0003iVl\u0011aD\u0005\u0003m>\u0011qbU3dkJLG/_'b]\u0006<WM]\u0001\u0017G\",7m[+J-&,w\u000fU3s[&\u001c8/[8ogR!\u0011\u0010`?\u007f!\t9\"0\u0003\u0002|1\t9!i\\8mK\u0006t\u0007\"B\u001f\b\u0001\u0004q\u0004\"\u0002&\b\u0001\u0004Y\u0005\"B@\b\u0001\u0004q\u0014\u0001B;tKJ\u0004"
)
public interface UIRoot {
   Object withSparkUI(final String appId, final Option attemptId, final Function1 fn);

   Iterator getApplicationInfoList();

   Option getApplicationInfo(final String appId);

   // $FF: synthetic method
   static void writeEventLogs$(final UIRoot $this, final String appId, final Option attemptId, final ZipOutputStream zipStream) {
      $this.writeEventLogs(appId, attemptId, zipStream);
   }

   default void writeEventLogs(final String appId, final Option attemptId, final ZipOutputStream zipStream) {
      Response.serverError().entity("Event logs are only available through the history server.").status(Status.SERVICE_UNAVAILABLE).build();
   }

   SecurityManager securityManager();

   boolean checkUIViewPermissions(final String appId, final Option attemptId, final String user);

   static void $init$(final UIRoot $this) {
   }
}
