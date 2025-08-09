package org.apache.spark.internal.io;

import java.util.Date;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobID;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.executor.OutputMetrics;
import scala.Function0;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.util.DynamicVariable;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005UsA\u0002\n\u0014\u0011\u00039RD\u0002\u0004 '!\u0005q\u0003\t\u0005\u0006O\u0005!\t!\u000b\u0005\bU\u0005\u0011\r\u0011\"\u0003,\u0011\u0019y\u0013\u0001)A\u0005Y!9\u0001'\u0001b\u0001\n\u0013\t\u0004B\u0002\u001d\u0002A\u0003%!\u0007C\u0004:\u0003\t\u0007I\u0011\u0002\u001e\t\r\u0015\u000b\u0001\u0015!\u0003<\u0011\u00151\u0015\u0001\"\u0001H\u0011\u00151\u0015\u0001\"\u0001Y\u0011\u00159\u0017\u0001\"\u0001i\u0011\u0015Q\u0017\u0001\"\u0001l\u0011\u0015I\u0018\u0001\"\u0001{\u0011\u001d\t9!\u0001C\u0001\u0003\u0013Aq!a\r\u0002\t\u0003\t)\u0004C\u0005\u0002J\u0005\u0011\r\u0011\"\u0001\u0002L!A\u00111K\u0001!\u0002\u0013\ti%\u0001\fTa\u0006\u00148\u000eS1e_>\u0004xK]5uKJ,F/\u001b7t\u0015\t!R#\u0001\u0002j_*\u0011acF\u0001\tS:$XM\u001d8bY*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014x\r\u0005\u0002\u001f\u00035\t1C\u0001\fTa\u0006\u00148\u000eS1e_>\u0004xK]5uKJ,F/\u001b7t'\t\t\u0011\u0005\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13E\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tQ$\u0001\u0017S\u000b\u000e{%\u000bR*`\u0005\u0016#v+R#O?\nKF+R*`/JKE\u000bV#O?6+EKU%D?V\u0003F)\u0011+F'V\tA\u0006\u0005\u0002#[%\u0011af\t\u0002\u0004\u0013:$\u0018!\f*F\u0007>\u0013FiU0C\u000bR;V)\u0012(`\u0005f#ViU0X%&#F+\u0012(`\u001b\u0016#&+S\"`+B#\u0015\tV#TA\u0005!!+\u0011(E+\u0005\u0011\u0004CA\u001a7\u001b\u0005!$BA\u001b$\u0003\u0011)H/\u001b7\n\u0005]\"$A\u0002*b]\u0012|W.A\u0003S\u0003:#\u0005%A\nE\u0003R+u\fV%N\u000b~3uJU'B)R+%+F\u0001<!\ta4)D\u0001>\u0015\tqt(\u0001\u0004g_Jl\u0017\r\u001e\u0006\u0003\u0001\u0006\u000bA\u0001^5nK*\t!)\u0001\u0003kCZ\f\u0017B\u0001#>\u0005E!\u0015\r^3US6,gi\u001c:nCR$XM]\u0001\u0015\t\u0006#Vi\u0018+J\u001b\u0016{fi\u0014*N\u0003R#VI\u0015\u0011\u0002\u0017\r\u0014X-\u0019;f\u0015>\u0014\u0017\n\u0012\u000b\u0004\u0011B3\u0006CA%O\u001b\u0005Q%BA&M\u0003\u0019i\u0017\r\u001d:fI*\u0011Q*G\u0001\u0007Q\u0006$wn\u001c9\n\u0005=S%!\u0002&pE&#\u0005\"\u0002!\n\u0001\u0004\t\u0006C\u0001*U\u001b\u0005\u0019&BA\u001bB\u0013\t)6K\u0001\u0003ECR,\u0007\"B,\n\u0001\u0004a\u0013AA5e)\rA\u0015L\u001a\u0005\u00065*\u0001\raW\u0001\rU>\u0014GK]1dW\u0016\u0014\u0018\n\u0012\t\u00039\u000et!!X1\u0011\u0005y\u001bS\"A0\u000b\u0005\u0001D\u0013A\u0002\u001fs_>$h(\u0003\u0002cG\u00051\u0001K]3eK\u001aL!\u0001Z3\u0003\rM#(/\u001b8h\u0015\t\u00117\u0005C\u0003X\u0015\u0001\u0007A&\u0001\nde\u0016\fG/\u001a&pER\u0013\u0018mY6fe&#ECA.j\u0011\u0015\u00015\u00021\u0001R\u0003Q\u0019'/Z1uKB\u000bG\u000f\u001b$s_6\u001cFO]5oOR\u0019AN\u001d;\u0011\u00055\u0004X\"\u00018\u000b\u0005=d\u0015A\u00014t\u0013\t\thN\u0001\u0003QCRD\u0007\"B:\r\u0001\u0004Y\u0016\u0001\u00029bi\"DQ!\u001e\u0007A\u0002Y\fAaY8oMB\u0011\u0011j^\u0005\u0003q*\u0013qAS8c\u0007>tg-A\u000fjg>+H\u000f];u'B,7MV1mS\u0012\fG/[8o\u000b:\f'\r\\3e)\tYh\u0010\u0005\u0002#y&\u0011Qp\t\u0002\b\u0005>|G.Z1o\u0011\u0015)X\u00021\u0001\u0000!\u0011\t\t!a\u0001\u000e\u0003]I1!!\u0002\u0018\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\fj]&$\b*\u00193p_B|U\u000f\u001e9vi6+GO]5dgR!\u00111BA\u0015!\u001d\u0011\u0013QBA\t\u0003;I1!a\u0004$\u0005\u0019!V\u000f\u001d7feA!\u00111CA\r\u001b\t\t)BC\u0002\u0002\u0018]\t\u0001\"\u001a=fGV$xN]\u0005\u0005\u00037\t)BA\u0007PkR\u0004X\u000f^'fiJL7m\u001d\t\u0006E\u0005}\u00111E\u0005\u0004\u0003C\u0019#!\u0003$v]\u000e$\u0018n\u001c81!\r\u0011\u0013QE\u0005\u0004\u0003O\u0019#\u0001\u0002'p]\u001eDq!a\u000b\u000f\u0001\u0004\ti#A\u0004d_:$X\r\u001f;\u0011\t\u0005\u0005\u0011qF\u0005\u0004\u0003c9\"a\u0003+bg.\u001cuN\u001c;fqR\f\u0001$\\1zE\u0016,\u0006\u000fZ1uK>+H\u000f];u\u001b\u0016$(/[2t)!\t9$!\u0010\u0002B\u0005\u0015\u0003c\u0001\u0012\u0002:%\u0019\u00111H\u0012\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003\u007fy\u0001\u0019AA\t\u00035yW\u000f\u001e9vi6+GO]5dg\"9\u00111I\bA\u0002\u0005u\u0011\u0001C2bY2\u0014\u0017mY6\t\u000f\u0005\u001ds\u00021\u0001\u0002$\u0005q!/Z2pe\u0012\u001cxK]5ui\u0016t\u0017a\u00073jg\u0006\u0014G.Z(viB,Ho\u00159fGZ\u000bG.\u001b3bi&|g.\u0006\u0002\u0002NA!1'a\u0014|\u0013\r\t\t\u0006\u000e\u0002\u0010\tft\u0017-\\5d-\u0006\u0014\u0018.\u00192mK\u0006aB-[:bE2,w*\u001e;qkR\u001c\u0006/Z2WC2LG-\u0019;j_:\u0004\u0003"
)
public final class SparkHadoopWriterUtils {
   public static DynamicVariable disableOutputSpecValidation() {
      return SparkHadoopWriterUtils$.MODULE$.disableOutputSpecValidation();
   }

   public static void maybeUpdateOutputMetrics(final OutputMetrics outputMetrics, final Function0 callback, final long recordsWritten) {
      SparkHadoopWriterUtils$.MODULE$.maybeUpdateOutputMetrics(outputMetrics, callback, recordsWritten);
   }

   public static Tuple2 initHadoopOutputMetrics(final TaskContext context) {
      return SparkHadoopWriterUtils$.MODULE$.initHadoopOutputMetrics(context);
   }

   public static boolean isOutputSpecValidationEnabled(final SparkConf conf) {
      return SparkHadoopWriterUtils$.MODULE$.isOutputSpecValidationEnabled(conf);
   }

   public static Path createPathFromString(final String path, final JobConf conf) {
      return SparkHadoopWriterUtils$.MODULE$.createPathFromString(path, conf);
   }

   public static String createJobTrackerID(final Date time) {
      return SparkHadoopWriterUtils$.MODULE$.createJobTrackerID(time);
   }

   public static JobID createJobID(final String jobTrackerID, final int id) {
      return SparkHadoopWriterUtils$.MODULE$.createJobID(jobTrackerID, id);
   }

   public static JobID createJobID(final Date time, final int id) {
      return SparkHadoopWriterUtils$.MODULE$.createJobID(time, id);
   }
}
