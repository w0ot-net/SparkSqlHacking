package org.apache.spark.internal.plugin;

import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv;
import org.apache.spark.TaskFailedReason;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4Qa\u0004\t\u0002\"mAQA\t\u0001\u0005\u0002\rBQA\n\u0001\u0007\u0002\u001dBQa\u000b\u0001\u0007\u00021BQA\u000f\u0001\u0007\u0002\u001dBQa\u000f\u0001\u0007\u0002\u001dBQ\u0001\u0010\u0001\u0007\u0002u:Q!\u0013\t\t\u0002)3Qa\u0004\t\t\u0002-CQA\t\u0005\u0005\u00021Cq!\u0014\u0005C\u0002\u0013\u0005a\n\u0003\u0004W\u0011\u0001\u0006Ia\u0014\u0005\u0006/\"!\t\u0001\u0017\u0005\u0006/\"!\ta\u001c\u0005\u0006/\"!IA\u001e\u0002\u0010!2,x-\u001b8D_:$\u0018-\u001b8fe*\u0011\u0011CE\u0001\u0007a2,x-\u001b8\u000b\u0005M!\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00019A\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0013\u0011\u0005\u0015\u0002Q\"\u0001\t\u0002\u0011MDW\u000f\u001e3po:$\u0012\u0001\u000b\t\u0003;%J!A\u000b\u0010\u0003\tUs\u0017\u000e^\u0001\u0010e\u0016<\u0017n\u001d;fe6+GO]5dgR\u0011\u0001&\f\u0005\u0006]\r\u0001\raL\u0001\u0006CB\u0004\u0018\n\u001a\t\u0003a]r!!M\u001b\u0011\u0005IrR\"A\u001a\u000b\u0005QR\u0012A\u0002\u001fs_>$h(\u0003\u00027=\u00051\u0001K]3eK\u001aL!\u0001O\u001d\u0003\rM#(/\u001b8h\u0015\t1d$A\u0006p]R\u000b7o[*uCJ$\u0018aD8o)\u0006\u001c8nU;dG\u0016,G-\u001a3\u0002\u0019=tG+Y:l\r\u0006LG.\u001a3\u0015\u0005!r\u0004\"B \u0007\u0001\u0004\u0001\u0015!\u00044bS2,(/\u001a*fCN|g\u000e\u0005\u0002B\u00056\tA#\u0003\u0002D)\t\u0001B+Y:l\r\u0006LG.\u001a3SK\u0006\u001cxN\\\u0015\u0004\u0001\u0015;\u0015B\u0001$\u0011\u0005U!%/\u001b<feBcWoZ5o\u0007>tG/Y5oKJL!\u0001\u0013\t\u0003/\u0015CXmY;u_J\u0004F.^4j]\u000e{g\u000e^1j]\u0016\u0014\u0018a\u0004)mk\u001eLgnQ8oi\u0006Lg.\u001a:\u0011\u0005\u0015B1C\u0001\u0005\u001d)\u0005Q\u0015!E#Y)J\u000bulQ(O\r~\u0003&+\u0012$J1V\tq\n\u0005\u0002Q+6\t\u0011K\u0003\u0002S'\u0006!A.\u00198h\u0015\u0005!\u0016\u0001\u00026bm\u0006L!\u0001O)\u0002%\u0015CFKU!`\u0007>sei\u0018)S\u000b\u001aK\u0005\fI\u0001\u0006CB\u0004H.\u001f\u000b\u00043r\u000b\u0007cA\u000f[I%\u00111L\b\u0002\u0007\u001fB$\u0018n\u001c8\t\u000buc\u0001\u0019\u00010\u0002\u0005M\u001c\u0007CA!`\u0013\t\u0001GC\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0003c\u0019\u0001\u00071-A\u0005sKN|WO]2fgB!AmZ\u0018j\u001b\u0005)'B\u00014T\u0003\u0011)H/\u001b7\n\u0005!,'aA'baB\u0011!.\\\u0007\u0002W*\u0011A\u000eF\u0001\te\u0016\u001cx.\u001e:dK&\u0011an\u001b\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\u001c\u000b\u00043B,\b\"B9\u000e\u0001\u0004\u0011\u0018aA3omB\u0011\u0011i]\u0005\u0003iR\u0011\u0001b\u00159be.,eN\u001e\u0005\u0006E6\u0001\ra\u0019\u000b\u00043^t\b\"\u0002=\u000f\u0001\u0004I\u0018aA2uqB!!\u0010 0s\u001b\u0005Y(B\u00014\u001f\u0013\ti8P\u0001\u0004FSRDWM\u001d\u0005\u0006E:\u0001\ra\u0019"
)
public abstract class PluginContainer {
   public static Option apply(final SparkEnv env, final Map resources) {
      return PluginContainer$.MODULE$.apply(env, resources);
   }

   public static Option apply(final SparkContext sc, final Map resources) {
      return PluginContainer$.MODULE$.apply(sc, resources);
   }

   public static String EXTRA_CONF_PREFIX() {
      return PluginContainer$.MODULE$.EXTRA_CONF_PREFIX();
   }

   public abstract void shutdown();

   public abstract void registerMetrics(final String appId);

   public abstract void onTaskStart();

   public abstract void onTaskSucceeded();

   public abstract void onTaskFailed(final TaskFailedReason failureReason);
}
