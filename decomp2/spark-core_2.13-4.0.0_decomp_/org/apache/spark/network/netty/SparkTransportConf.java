package org.apache.spark.network.netty;

import org.apache.spark.SparkConf;
import org.apache.spark.network.util.TransportConf;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q;Qa\u0002\u0005\t\u0002M1Q!\u0006\u0005\t\u0002YAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001BqAS\u0001\u0012\u0002\u0013\u00051\nC\u0004W\u0003E\u0005I\u0011A,\t\u000fe\u000b\u0011\u0013!C\u00015\u0006\u00112\u000b]1sWR\u0013\u0018M\\:q_J$8i\u001c8g\u0015\tI!\"A\u0003oKR$\u0018P\u0003\u0002\f\u0019\u00059a.\u001a;x_J\\'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001A\u0011A#A\u0007\u0002\u0011\t\u00112\u000b]1sWR\u0013\u0018M\\:q_J$8i\u001c8g'\t\tq\u0003\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\tQB\u001a:p[N\u0003\u0018M]6D_:4GCB\u0011([izD\t\u0005\u0002#K5\t1E\u0003\u0002%\u0015\u0005!Q\u000f^5m\u0013\t13EA\u0007Ue\u0006t7\u000f]8si\u000e{gN\u001a\u0005\u0006Q\r\u0001\r!K\u0001\u0006?\u000e|gN\u001a\t\u0003U-j\u0011\u0001D\u0005\u0003Y1\u0011\u0011b\u00159be.\u001cuN\u001c4\t\u000b9\u001a\u0001\u0019A\u0018\u0002\r5|G-\u001e7f!\t\u0001tG\u0004\u00022kA\u0011!'G\u0007\u0002g)\u0011AGE\u0001\u0007yI|w\u000e\u001e \n\u0005YJ\u0012A\u0002)sK\u0012,g-\u0003\u00029s\t11\u000b\u001e:j]\u001eT!AN\r\t\u000fm\u001a\u0001\u0013!a\u0001y\u0005qa.^7Vg\u0006\u0014G.Z\"pe\u0016\u001c\bC\u0001\r>\u0013\tq\u0014DA\u0002J]RDq\u0001Q\u0002\u0011\u0002\u0003\u0007\u0011)\u0001\u0003s_2,\u0007c\u0001\rC_%\u00111)\u0007\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0015\u001b\u0001\u0013!a\u0001\r\u0006Q1o\u001d7PaRLwN\\:\u0011\u0007a\u0011u\t\u0005\u0002+\u0011&\u0011\u0011\n\u0004\u0002\u000b'Ncu\n\u001d;j_:\u001c\u0018a\u00064s_6\u001c\u0006/\u0019:l\u0007>tg\r\n3fM\u0006,H\u000e\u001e\u00134+\u0005a%F\u0001\u001fNW\u0005q\u0005CA(U\u001b\u0005\u0001&BA)S\u0003%)hn\u00195fG.,GM\u0003\u0002T3\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005U\u0003&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00069bM]8n'B\f'o[\"p]\u001a$C-\u001a4bk2$H\u0005N\u000b\u00021*\u0012\u0011)T\u0001\u0018MJ|Wn\u00159be.\u001cuN\u001c4%I\u00164\u0017-\u001e7uIU*\u0012a\u0017\u0016\u0003\r6\u0003"
)
public final class SparkTransportConf {
   public static Option fromSparkConf$default$5() {
      return SparkTransportConf$.MODULE$.fromSparkConf$default$5();
   }

   public static Option fromSparkConf$default$4() {
      return SparkTransportConf$.MODULE$.fromSparkConf$default$4();
   }

   public static int fromSparkConf$default$3() {
      return SparkTransportConf$.MODULE$.fromSparkConf$default$3();
   }

   public static TransportConf fromSparkConf(final SparkConf _conf, final String module, final int numUsableCores, final Option role, final Option sslOptions) {
      return SparkTransportConf$.MODULE$.fromSparkConf(_conf, module, numUsableCores, role, sslOptions);
   }
}
