package org.apache.spark.deploy.yarn;

import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.resource.ExecutorResourceRequest;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-t!B\n\u0015\u0011\u0003yb!B\u0011\u0015\u0011\u0003\u0011\u0003\"B\u0015\u0002\t\u0003Q\u0003bB\u0016\u0002\u0005\u0004%\t\u0001\f\u0005\u0007a\u0005\u0001\u000b\u0011B\u0017\t\u000fE\n!\u0019!C\u0001e!11(\u0001Q\u0001\nMBq\u0001P\u0001C\u0002\u0013\u0005Q\b\u0003\u0004J\u0003\u0001\u0006IA\u0010\u0005\u0006\u0015\u0006!\ta\u0013\u0005\bO\u0006\u0011\r\u0011\"\u0003i\u0011\u0019I\u0017\u0001)A\u00053\")!.\u0001C\u0001W\"9!0AI\u0001\n\u0003Y\b\u0002CA\u0007\u0003\u0011\u0005A#a\u0004\t\u000f\u0005m\u0011\u0001\"\u0001\u0002\u001e!9\u00111E\u0001\u0005\u0002\u0005\u0015\u0002bBA\u001f\u0003\u0011\u0005\u0011q\b\u0005\b\u0003\u000f\nA\u0011AA%\u0003MI\u0016M\u001d8Ta\u0006\u00148\u000eS1e_>\u0004X\u000b^5m\u0015\t)b#\u0001\u0003zCJt'BA\f\u0019\u0003\u0019!W\r\u001d7ps*\u0011\u0011DG\u0001\u0006gB\f'o\u001b\u0006\u00037q\ta!\u00199bG\",'\"A\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005\u0001\nQ\"\u0001\u000b\u0003'e\u000b'O\\*qCJ\\\u0007*\u00193p_B,F/\u001b7\u0014\u0005\u0005\u0019\u0003C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002?\u0005I\u0012)T0N\u000b6{%+W0P-\u0016\u0013\u0006*R!E?\u001a\u000b5\tV(S+\u0005i\u0003C\u0001\u0013/\u0013\tySE\u0001\u0004E_V\u0014G.Z\u0001\u001b\u00036{V*R'P%f{vJV#S\u0011\u0016\u000bEi\u0018$B\u0007R{%\u000bI\u0001\t\u0003:Kv\fS(T)V\t1\u0007\u0005\u00025s5\tQG\u0003\u00027o\u0005!A.\u00198h\u0015\u0005A\u0014\u0001\u00026bm\u0006L!AO\u001b\u0003\rM#(/\u001b8h\u0003%\te*W0I\u001fN#\u0006%A\nS\u001b~\u0013V)U+F'R{\u0006KU%P%&#\u0016,F\u0001?!\tyt)D\u0001A\u0015\t\t%)A\u0004sK\u000e|'\u000fZ:\u000b\u0005\r#\u0015aA1qS*\u0011Q#\u0012\u0006\u0003\rj\ta\u0001[1e_>\u0004\u0018B\u0001%A\u0005!\u0001&/[8sSRL\u0018\u0001\u0006*N?J+\u0015+V#T)~\u0003&+S(S\u0013RK\u0006%\u0001\u000bbI\u0012\u0004\u0016\r\u001e5U_\u0016sg/\u001b:p]6,g\u000e\u001e\u000b\u0005\u0019>\u001bW\r\u0005\u0002%\u001b&\u0011a*\n\u0002\u0005+:LG\u000fC\u0003Q\u0013\u0001\u0007\u0011+A\u0002f]Z\u0004BAU,Z36\t1K\u0003\u0002U+\u00069Q.\u001e;bE2,'B\u0001,&\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u00031N\u0013q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0002[C:\u00111l\u0018\t\u00039\u0016j\u0011!\u0018\u0006\u0003=z\ta\u0001\u0010:p_Rt\u0014B\u00011&\u0003\u0019\u0001&/\u001a3fM&\u0011!H\u0019\u0006\u0003A\u0016BQ\u0001Z\u0005A\u0002e\u000b1a[3z\u0011\u00151\u0017\u00021\u0001Z\u0003\u00151\u0018\r\\;f\u0003=)gN\u001e,be:\u000bW.\u001a*fO\u0016DX#A-\u0002!\u0015tgOV1s\u001d\u0006lWMU3hKb\u0004\u0013A\u0004:fa2\f7-Z#omZ\u000b'o\u001d\u000b\u000532tW\u000fC\u0003n\u0019\u0001\u0007\u0011,\u0001\tv]J,7o\u001c7wK\u0012\u001cFO]5oO\")\u0001\u000b\u0004a\u0001_B!\u0001o]-Z\u001b\u0005\t(B\u0001:V\u0003%IW.\\;uC\ndW-\u0003\u0002uc\n\u0019Q*\u00199\t\u000fYd\u0001\u0013!a\u0001o\u0006I\u0011n],j]\u0012|wo\u001d\t\u0003IaL!!_\u0013\u0003\u000f\t{w\u000e\\3b]\u0006A\"/\u001a9mC\u000e,WI\u001c<WCJ\u001cH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003qT#a^?,\u0003y\u00042a`A\u0005\u001b\t\t\tA\u0003\u0003\u0002\u0004\u0005\u0015\u0011!C;oG\",7m[3e\u0015\r\t9!J\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0006\u0003\u0003\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003m\tG\rZ(vi>3W*Z7pef,%O]8s\u0003J<W/\\3oiR\u0019A*!\u0005\t\u000f\u0005Ma\u00021\u0001\u0002\u0016\u0005A!.\u0019<b\u001fB$8\u000f\u0005\u0003S\u0003/I\u0016bAA\r'\nQA*[:u\u0005V4g-\u001a:\u0002\u001d\u0015\u001c8-\u00199f\r>\u00148\u000b[3mYR\u0019\u0011,a\b\t\r\u0005\u0005r\u00021\u0001Z\u0003\r\t'oZ\u0001\u001aO\u0016$\u0018\t\u001d9mS\u000e\fG/[8o\u0003\u000ed7OR8s3\u0006\u0014h\u000e\u0006\u0003\u0002(\u0005E\u0002C\u0002.\u0002*\u0005-\u0012,\u0003\u0002uEB\u0019q(!\f\n\u0007\u0005=\u0002IA\u000bBaBd\u0017nY1uS>t\u0017iY2fgN$\u0016\u0010]3\t\u000f\u0005M\u0002\u00031\u0001\u00026\u0005Y1/Z2ve&$\u00180T4s!\u0011\t9$!\u000f\u000e\u0003aI1!a\u000f\u0019\u0005=\u0019VmY;sSRLX*\u00198bO\u0016\u0014\u0018AD4fi\u000e{g\u000e^1j]\u0016\u0014\u0018\nZ\u000b\u0003\u0003\u0003\u00022aPA\"\u0013\r\t)\u0005\u0011\u0002\f\u0007>tG/Y5oKJLE-A\u000ffq\u0016\u001cW\u000f^8s\u001f\u001a4\u0007*Z1q\u001b\u0016lwN]=TSj,\u0017i]'c)\u0019\tY%!\u0015\u0002\\A\u0019A%!\u0014\n\u0007\u0005=SE\u0001\u0003M_:<\u0007bBA*%\u0001\u0007\u0011QK\u0001\ngB\f'o[\"p]\u001a\u0004B!a\u000e\u0002X%\u0019\u0011\u0011\f\r\u0003\u0013M\u0003\u0018M]6D_:4\u0007bBA/%\u0001\u0007\u0011qL\u0001\fKb,7MU3rk\u0016\u001cH\u000f\u0005\u0003\u0002b\u0005\u001dTBAA2\u0015\r\t)\u0007G\u0001\te\u0016\u001cx.\u001e:dK&!\u0011\u0011NA2\u0005])\u00050Z2vi>\u0014(+Z:pkJ\u001cWMU3rk\u0016\u001cH\u000f"
)
public final class YarnSparkHadoopUtil {
   public static long executorOffHeapMemorySizeAsMb(final SparkConf sparkConf, final ExecutorResourceRequest execRequest) {
      return YarnSparkHadoopUtil$.MODULE$.executorOffHeapMemorySizeAsMb(sparkConf, execRequest);
   }

   public static ContainerId getContainerId() {
      return YarnSparkHadoopUtil$.MODULE$.getContainerId();
   }

   public static Map getApplicationAclsForYarn(final SecurityManager securityMgr) {
      return YarnSparkHadoopUtil$.MODULE$.getApplicationAclsForYarn(securityMgr);
   }

   public static String escapeForShell(final String arg) {
      return YarnSparkHadoopUtil$.MODULE$.escapeForShell(arg);
   }

   public static boolean replaceEnvVars$default$3() {
      return YarnSparkHadoopUtil$.MODULE$.replaceEnvVars$default$3();
   }

   public static String replaceEnvVars(final String unresolvedString, final Map env, final boolean isWindows) {
      return YarnSparkHadoopUtil$.MODULE$.replaceEnvVars(unresolvedString, env, isWindows);
   }

   public static void addPathToEnvironment(final HashMap env, final String key, final String value) {
      YarnSparkHadoopUtil$.MODULE$.addPathToEnvironment(env, key, value);
   }

   public static Priority RM_REQUEST_PRIORITY() {
      return YarnSparkHadoopUtil$.MODULE$.RM_REQUEST_PRIORITY();
   }

   public static String ANY_HOST() {
      return YarnSparkHadoopUtil$.MODULE$.ANY_HOST();
   }

   public static double AM_MEMORY_OVERHEAD_FACTOR() {
      return YarnSparkHadoopUtil$.MODULE$.AM_MEMORY_OVERHEAD_FACTOR();
   }
}
