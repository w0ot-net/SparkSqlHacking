package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.ConfigMap;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ut!\u0002\t\u0012\u0011\u0003qb!\u0002\u0011\u0012\u0011\u0003\t\u0003\"\u0002\u0018\u0002\t\u0003y\u0003\"\u0002\u0019\u0002\t\u0003\t\u0004b\u0002%\u0002\u0005\u0004%\t!\u0013\u0005\u0007\u001b\u0006\u0001\u000b\u0011\u0002\u001a\t\u000f=\u000b!\u0019!C\u0001\u0013\"1\u0011+\u0001Q\u0001\nIBQaU\u0001\u0005\nQCQaW\u0001\u0005\u0002qCQ![\u0001\u0005\u0002)Dq!a\u0003\u0002\t\u0003\ti\u0001C\u0005\u0002\"\u0005\t\n\u0011\"\u0001\u0002$!9\u0011qG\u0001\u0005\n\u0005e\u0002\u0002CA(\u0003\u0011\u0005\u0011#!\u0015\t\u000f\u0005]\u0013\u0001\"\u0003\u0002Z\u0005)2*\u001e2fe:,G/Z:DY&,g\u000e^+uS2\u001c(B\u0001\n\u0014\u0003\u0019\u0019XOY7ji*\u0011A#F\u0001\u0004Wb\u001a(B\u0001\f\u0018\u0003\u0019!W\r\u001d7ps*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005}\tQ\"A\t\u0003+-+(-\u001a:oKR,7o\u00117jK:$X\u000b^5mgN\u0019\u0011A\t\u0015\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\tIC&D\u0001+\u0015\tYs#\u0001\u0005j]R,'O\\1m\u0013\ti#FA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?)\u0005q\u0012!D2p]\u001aLw-T1q\u001d\u0006lW\r\u0006\u00023{A\u00111G\u000f\b\u0003ia\u0002\"!\u000e\u0013\u000e\u0003YR!aN\u000f\u0002\rq\u0012xn\u001c;?\u0013\tID%\u0001\u0004Qe\u0016$WMZ\u0005\u0003wq\u0012aa\u0015;sS:<'BA\u001d%\u0011\u0015q4\u00011\u00013\u0003\u0019\u0001(/\u001a4jq\"\u001a1\u0001\u0011$\u0011\u0005\u0005#U\"\u0001\"\u000b\u0005\r;\u0012AC1o]>$\u0018\r^5p]&\u0011QI\u0011\u0002\u0006'&t7-Z\u0011\u0002\u000f\u0006)1GL\u001a/a\u0005)2m\u001c8gS\u001el\u0015\r\u001d(b[\u0016,\u00050Z2vi>\u0014X#\u0001\u001a)\u0007\u0011\u00015*I\u0001M\u0003\u0015\u0019d&\r\u00181\u0003Y\u0019wN\u001c4jO6\u000b\u0007OT1nK\u0016CXmY;u_J\u0004\u0003fA\u0003A\u0017\u0006\u00192m\u001c8gS\u001el\u0015\r\u001d(b[\u0016$%/\u001b<fe\"\u001aa\u0001Q&\u0002)\r|gNZ5h\u001b\u0006\u0004h*Y7f\tJLg/\u001a:!Q\r9\u0001iS\u0001\u001dEVLG\u000eZ*ue&twM\u0012:p[B\u0013x\u000e]3si&,7/T1q)\r\u0011TK\u0016\u0005\u0006a!\u0001\rA\r\u0005\u0006/\"\u0001\r\u0001W\u0001\u000eaJ|\u0007/\u001a:uS\u0016\u001cX*\u00199\u0011\tMJ&GM\u0005\u00035r\u00121!T1q\u0003e\u0011W/\u001b7e'B\f'o[\"p]\u001a$\u0015N\u001d$jY\u0016\u001cX*\u00199\u0015\takf\f\u001a\u0005\u0006a%\u0001\rA\r\u0005\u0006?&\u0001\r\u0001Y\u0001\ngB\f'o[\"p]\u001a\u0004\"!\u00192\u000e\u0003]I!aY\f\u0003\u0013M\u0003\u0018M]6D_:4\u0007\"B3\n\u0001\u0004A\u0016!\u0006:fg>dg/\u001a3Qe>\u0004XM\u001d;jKNl\u0015\r\u001d\u0015\u0004\u0013\u0001;\u0017%\u00015\u0002\u000bMr\u0013GL\u0019\u0002+\t,\u0018\u000e\u001c3LKf$v\u000eU1uQ>\u0013'.Z2ugR\u00191.!\u0002\u0011\u00071\fHO\u0004\u0002n_:\u0011QG\\\u0005\u0002K%\u0011\u0001\u000fJ\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00118OA\u0002TKFT!\u0001\u001d\u0013\u0011\u0007U\f\t!D\u0001w\u0015\t9\b0A\u0003n_\u0012,GN\u0003\u0002zu\u0006\u0019\u0011\r]5\u000b\u0005md\u0018AC6vE\u0016\u0014h.\u001a;fg*\u0011QP`\u0001\bM\u0006\u0014'/[29\u0015\u0005y\u0018AA5p\u0013\r\t\u0019A\u001e\u0002\n\u0017\u0016LHk\u001c)bi\"Da!a\u0002\u000b\u0001\u0004A\u0016\u0001D2p]\u001a4\u0015\u000e\\3t\u001b\u0006\u0004\bf\u0001\u0006A\u0017\u0006q!-^5mI\u000e{gNZ5h\u001b\u0006\u0004H\u0003CA\b\u0003+\t9\"a\u0007\u0011\u0007U\f\t\"C\u0002\u0002\u0014Y\u0014\u0011bQ8oM&<W*\u00199\t\u000bAZ\u0001\u0019\u0001\u001a\t\r\u0005e1\u00021\u0001Y\u0003-\u0019wN\u001c4GS2,W*\u00199\t\u0011\u0005u1\u0002%AA\u0002a\u000b!b^5uQ2\u000b'-\u001a7tQ\rY\u0001iS\u0001\u0019EVLG\u000eZ\"p]\u001aLw-T1qI\u0011,g-Y;mi\u0012\u001aTCAA\u0013U\rA\u0016qE\u0016\u0003\u0003S\u0001B!a\u000b\u000245\u0011\u0011Q\u0006\u0006\u0005\u0003_\t\t$A\u0005v]\u000eDWmY6fI*\u00111\tJ\u0005\u0005\u0003k\tiCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001c\u001c:eKJ4\u0015\u000e\\3t\u0005f\u001c\u0016N_3\u0015\t\u0005m\u00121\n\t\u0005YF\fi\u0004\u0005\u0003\u0002@\u0005\u001dSBAA!\u0015\ry\u00181\t\u0006\u0003\u0003\u000b\nAA[1wC&!\u0011\u0011JA!\u0005\u00111\u0015\u000e\\3\t\u000f\u00055S\u00021\u0001\u0002<\u0005I1m\u001c8g\r&dWm]\u0001\u0016Y>\fGm\u00159be.\u001cuN\u001c4ESJ4\u0015\u000e\\3t)\rA\u00161\u000b\u0005\u0007\u0003+r\u0001\u0019\u00011\u0002\t\r|gNZ\u0001\u000eY&\u001cHoQ8oM\u001aKG.Z:\u0015\r\u0005m\u00121LA0\u0011\u0019\tif\u0004a\u0001e\u000591m\u001c8g\t&\u0014\bbBA1\u001f\u0001\u0007\u00111M\u0001\b[\u0006D8+\u001b>f!\r\u0019\u0013QM\u0005\u0004\u0003O\"#\u0001\u0002'p]\u001eD3!AA6!\r\t\u0015QN\u0005\u0004\u0003_\u0012%\u0001C+ogR\f'\r\\3)\u0007\u0005\t\u0019\bE\u0002B\u0003kJ1!a\u001eC\u00051!UM^3m_B,'/\u00119jQ\r\u0001\u00111\u000e\u0015\u0004\u0001\u0005M\u0004"
)
public final class KubernetesClientUtils {
   public static Map buildConfigMap$default$3() {
      return KubernetesClientUtils$.MODULE$.buildConfigMap$default$3();
   }

   public static ConfigMap buildConfigMap(final String configMapName, final Map confFileMap, final Map withLabels) {
      return KubernetesClientUtils$.MODULE$.buildConfigMap(configMapName, confFileMap, withLabels);
   }

   public static Seq buildKeyToPathObjects(final Map confFilesMap) {
      return KubernetesClientUtils$.MODULE$.buildKeyToPathObjects(confFilesMap);
   }

   public static Map buildSparkConfDirFilesMap(final String configMapName, final SparkConf sparkConf, final Map resolvedPropertiesMap) {
      return KubernetesClientUtils$.MODULE$.buildSparkConfDirFilesMap(configMapName, sparkConf, resolvedPropertiesMap);
   }

   public static String configMapNameDriver() {
      return KubernetesClientUtils$.MODULE$.configMapNameDriver();
   }

   public static String configMapNameExecutor() {
      return KubernetesClientUtils$.MODULE$.configMapNameExecutor();
   }

   public static String configMapName(final String prefix) {
      return KubernetesClientUtils$.MODULE$.configMapName(prefix);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return KubernetesClientUtils$.MODULE$.LogStringContext(sc);
   }
}
