package org.apache.spark.deploy;

import org.apache.curator.framework.CuratorFramework;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00055<aa\u0004\t\t\u0002IAbA\u0002\u000e\u0011\u0011\u0003\u00112\u0004C\u0003)\u0003\u0011\u0005!\u0006C\u0004,\u0003\t\u0007I\u0011\u0002\u0017\t\rA\n\u0001\u0015!\u0003.\u0011\u001d\t\u0014A1A\u0005\n1BaAM\u0001!\u0002\u0013i\u0003bB\u001a\u0002\u0005\u0004%I\u0001\f\u0005\u0007i\u0005\u0001\u000b\u0011B\u0017\t\u000fU\n!\u0019!C\u0005Y!1a'\u0001Q\u0001\n5BQaN\u0001\u0005\u0002aBq\u0001V\u0001\u0012\u0002\u0013\u0005Q\u000bC\u0003a\u0003\u0011\u0005\u0011\rC\u0003j\u0003\u0011\u0005!.\u0001\tTa\u0006\u00148nQ;sCR|'/\u0016;jY*\u0011\u0011CE\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u0004\"!G\u0001\u000e\u0003A\u0011\u0001c\u00159be.\u001cUO]1u_J,F/\u001b7\u0014\u0007\u0005a\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VM\u001a\t\u0003G\u0019j\u0011\u0001\n\u0006\u0003KI\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003O\u0011\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005A\u0012\u0001\b.L?\u000e{eJT#D)&{ej\u0018+J\u001b\u0016{U\u000bV0N\u00132c\u0015jU\u000b\u0002[A\u0011QDL\u0005\u0003_y\u00111!\u00138u\u0003uQ6jX\"P\u001d:+5\tV%P\u001d~#\u0016*T#P+R{V*\u0013'M\u0013N\u0003\u0013!\u0007.L?N+5kU%P\u001d~#\u0016*T#P+R{V*\u0013'M\u0013N\u000b!DW&`'\u0016\u001b6+S(O?RKU*R(V)~k\u0015\n\u0014'J'\u0002\n\u0011CU#U%f{v+Q%U?6KE\nT%T\u0003I\u0011V\t\u0016*Z?^\u000b\u0015\nV0N\u00132c\u0015j\u0015\u0011\u0002-5\u000b\u0005l\u0018*F\u0007>se*R\"U?\u0006#F+R'Q)N\u000bq#T!Y?J+5i\u0014(O\u000b\u000e#v,\u0011+U\u000b6\u0003Fk\u0015\u0011\u0002\u00139,wo\u00117jK:$HcA\u001dB\u000fB\u0011!hP\u0007\u0002w)\u0011A(P\u0001\nMJ\fW.Z<pe.T!A\u0010\u000b\u0002\u000f\r,(/\u0019;pe&\u0011\u0001i\u000f\u0002\u0011\u0007V\u0014\u0018\r^8s\rJ\fW.Z<pe.DQAQ\u0006A\u0002\r\u000bAaY8oMB\u0011A)R\u0007\u0002%%\u0011aI\u0005\u0002\n'B\f'o[\"p]\u001aDq\u0001S\u0006\u0011\u0002\u0003\u0007\u0011*A\u0005{WV\u0013HnQ8oMB\u0011!*\u0015\b\u0003\u0017>\u0003\"\u0001\u0014\u0010\u000e\u00035S!AT\u0015\u0002\rq\u0012xn\u001c;?\u0013\t\u0001f$\u0001\u0004Qe\u0016$WMZ\u0005\u0003%N\u0013aa\u0015;sS:<'B\u0001)\u001f\u0003MqWm^\"mS\u0016tG\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u00051&FA%XW\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003%)hn\u00195fG.,GM\u0003\u0002^=\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005}S&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006)Qn\u001b3jeR\u0019!-Z4\u0011\u0005u\u0019\u0017B\u00013\u001f\u0005\u0011)f.\u001b;\t\u000b\u0019l\u0001\u0019A\u001d\u0002\u0005i\\\u0007\"\u00025\u000e\u0001\u0004I\u0015\u0001\u00029bi\"\fq\u0002Z3mKR,'+Z2veNLg/\u001a\u000b\u0004E.d\u0007\"\u00024\u000f\u0001\u0004I\u0004\"\u00025\u000f\u0001\u0004I\u0005"
)
public final class SparkCuratorUtil {
   public static void deleteRecursive(final CuratorFramework zk, final String path) {
      SparkCuratorUtil$.MODULE$.deleteRecursive(zk, path);
   }

   public static void mkdir(final CuratorFramework zk, final String path) {
      SparkCuratorUtil$.MODULE$.mkdir(zk, path);
   }

   public static String newClient$default$2() {
      return SparkCuratorUtil$.MODULE$.newClient$default$2();
   }

   public static CuratorFramework newClient(final SparkConf conf, final String zkUrlConf) {
      return SparkCuratorUtil$.MODULE$.newClient(conf, zkUrlConf);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SparkCuratorUtil$.MODULE$.LogStringContext(sc);
   }
}
