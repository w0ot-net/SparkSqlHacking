package org.apache.spark.sql.streaming;

import java.util.UUID;
import java.util.concurrent.TimeoutException;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=ca\u0002\t\u0012!\u0003\r\n\u0001\b\u0005\u0006G\u00011\t\u0001\n\u0005\u0006a\u00011\t!\r\u0005\u0006u\u00011\t!\r\u0005\u0006w\u00011\t\u0001\u0010\u0005\u0006\u0003\u00021\tA\u0011\u0005\u0006\r\u00021\ta\u0012\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u0006)\u00021\t!\u0016\u0005\u00069\u00021\t!\u0018\u0005\u0006=\u00021\ta\u0018\u0005\u0007=\u00021\t!a\u0001\t\r\u0005m\u0001A\"\u0001`\u0011\u0019\ti\u0002\u0001D\u0001?\"1\u0011\u0011\b\u0001\u0007\u0002}Cq!!\u000f\u0001\r\u0003\tYD\u0001\bTiJ,\u0017-\\5oOF+XM]=\u000b\u0005I\u0019\u0012!C:ue\u0016\fW.\u001b8h\u0015\t!R#A\u0002tc2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0004\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VMZ\u0001\u0005]\u0006lW-F\u0001&!\t1SF\u0004\u0002(WA\u0011\u0001fH\u0007\u0002S)\u0011!fG\u0001\u0007yI|w\u000e\u001e \n\u00051z\u0012A\u0002)sK\u0012,g-\u0003\u0002/_\t11\u000b\u001e:j]\u001eT!\u0001L\u0010\u0002\u0005%$W#\u0001\u001a\u0011\u0005MBT\"\u0001\u001b\u000b\u0005U2\u0014\u0001B;uS2T\u0011aN\u0001\u0005U\u00064\u0018-\u0003\u0002:i\t!Q+V%E\u0003\u0015\u0011XO\\%e\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o+\u0005i\u0004C\u0001 @\u001b\u0005\u0019\u0012B\u0001!\u0014\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0003!I7/Q2uSZ,W#A\"\u0011\u0005y!\u0015BA# \u0005\u001d\u0011un\u001c7fC:\f\u0011\"\u001a=dKB$\u0018n\u001c8\u0016\u0003!\u00032AH%L\u0013\tQuD\u0001\u0004PaRLwN\u001c\t\u0003\u00196k\u0011!E\u0005\u0003\u001dF\u0011qc\u0015;sK\u0006l\u0017N\\4Rk\u0016\u0014\u00180\u0012=dKB$\u0018n\u001c8\u0002\rM$\u0018\r^;t+\u0005\t\u0006C\u0001'S\u0013\t\u0019\u0016C\u0001\u000bTiJ,\u0017-\\5oOF+XM]=Ti\u0006$Xo]\u0001\u000fe\u0016\u001cWM\u001c;Qe><'/Z:t+\u00051\u0006c\u0001\u0010X3&\u0011\u0001l\b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\u0019jK!aW\t\u0003-M#(/Z1nS:<\u0017+^3ssB\u0013xn\u001a:fgN\fA\u0002\\1tiB\u0013xn\u001a:fgN,\u0012!W\u0001\u0011C^\f\u0017\u000e\u001e+fe6Lg.\u0019;j_:$\u0012\u0001\u0019\t\u0003=\u0005L!AY\u0010\u0003\tUs\u0017\u000e\u001e\u0015\u0004\u0015\u0011<\u0007c\u0001\u0010f\u0017&\u0011am\b\u0002\u0007i\"\u0014xn^:2\u000by)\u0003.!\u00012\u000b\rJGn_7\u0016\u0005\u0011RG!B6\u001c\u0005\u0004\u0001(!\u0001+\n\u00055t\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013G\u0003\u0002p?\u00051A\u000f\u001b:poN\f\"!\u001d;\u0011\u0005y\u0011\u0018BA: \u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u001e=\u000f\u0005y1\u0018BA< \u0003\u001d\u0001\u0018mY6bO\u0016L!!\u001f>\u0003\u0013QC'o\\<bE2,'BA< c\u0015\u0019C0 @p\u001d\tqR0\u0003\u0002p?E\"!EH\u0010\u0000\u0005\u0015\u00198-\u00197bc\t13\nF\u0002D\u0003\u000bAq!a\u0002\f\u0001\u0004\tI!A\u0005uS6,w.\u001e;NgB\u0019a$a\u0003\n\u0007\u00055qD\u0001\u0003M_:<\u0007\u0006B\u0006e\u0003#\tdAH\u0013\u0002\u0014\u0005e\u0011GB\u0012jY\u0006UQ.\r\u0004$yv\f9b\\\u0019\u0005Eyyr0\r\u0002'\u0017\u0006\u0019\u0002O]8dKN\u001c\u0018\t\u001c7Bm\u0006LG.\u00192mK\u0006!1\u000f^8qQ\u0015i\u0011\u0011EA\u0018!\u0011qR-a\t\u0011\t\u0005\u0015\u00121F\u0007\u0003\u0003OQ1!!\u000b5\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\u0003[\t9C\u0001\tUS6,w.\u001e;Fq\u000e,\u0007\u000f^5p]F2a$JA\u0019\u0003o\tdaI5m\u0003gi\u0017GB\u0012}{\u0006Ur.\r\u0003#=}y\u0018g\u0001\u0014\u0002$\u00059Q\r\u001f9mC&tGc\u00011\u0002>!1\u0011qH\bA\u0002\r\u000b\u0001\"\u001a=uK:$W\r\u001a\u0015\u0004\u0001\u0005\r\u0003\u0003BA#\u0003\u0017j!!a\u0012\u000b\u0007\u0005%S#\u0001\u0006b]:|G/\u0019;j_:LA!!\u0014\u0002H\tAQI^8mm&tw\r"
)
public interface StreamingQuery {
   String name();

   UUID id();

   UUID runId();

   SparkSession sparkSession();

   boolean isActive();

   Option exception();

   StreamingQueryStatus status();

   StreamingQueryProgress[] recentProgress();

   StreamingQueryProgress lastProgress();

   void awaitTermination() throws StreamingQueryException;

   boolean awaitTermination(final long timeoutMs) throws StreamingQueryException;

   void processAllAvailable();

   void stop() throws TimeoutException;

   void explain();

   void explain(final boolean extended);
}
