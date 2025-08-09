package org.apache.spark.api.r;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.spark.SparkConf;
import org.apache.spark.security.SocketAuthHelper;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153Q!\u0002\u0004\u0001\u0015AA\u0011b\u0006\u0001\u0003\u0002\u0003\u0006I!G\u000f\t\u000by\u0001A\u0011A\u0010\t\u000b\r\u0002A\u0011\u000b\u0013\t\u000bq\u0002A\u0011K\u001f\u0003\u0017I\u000bU\u000f\u001e5IK2\u0004XM\u001d\u0006\u0003\u000f!\t\u0011A\u001d\u0006\u0003\u0013)\t1!\u00199j\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7C\u0001\u0001\u0012!\t\u0011R#D\u0001\u0014\u0015\t!\"\"\u0001\u0005tK\u000e,(/\u001b;z\u0013\t12C\u0001\tT_\u000e\\W\r^!vi\"DU\r\u001c9fe\u0006!1m\u001c8g\u0007\u0001\u0001\"AG\u000e\u000e\u0003)I!\u0001\b\u0006\u0003\u0013M\u0003\u0018M]6D_:4\u0017BA\f\u0016\u0003\u0019a\u0014N\\5u}Q\u0011\u0001E\t\t\u0003C\u0001i\u0011A\u0002\u0005\u0006/\t\u0001\r!G\u0001\te\u0016\fG-\u0016;gqQ\u0011QE\r\t\u0003M=r!aJ\u0017\u0011\u0005!ZS\"A\u0015\u000b\u0005)B\u0012A\u0002\u001fs_>$hHC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3&\u0001\u0004Qe\u0016$WMZ\u0005\u0003aE\u0012aa\u0015;sS:<'B\u0001\u0018,\u0011\u0015\u00194\u00011\u00015\u0003\u0005\u0019\bCA\u001b;\u001b\u00051$BA\u001c9\u0003\rqW\r\u001e\u0006\u0002s\u0005!!.\u0019<b\u0013\tYdG\u0001\u0004T_\u000e\\W\r^\u0001\noJLG/Z+uMb\"2A\u0010\"E!\ty\u0004)D\u0001,\u0013\t\t5F\u0001\u0003V]&$\b\"B\"\u0005\u0001\u0004)\u0013aA:ue\")1\u0007\u0002a\u0001i\u0001"
)
public class RAuthHelper extends SocketAuthHelper {
   public String readUtf8(final Socket s) {
      return SerDe$.MODULE$.readString(new DataInputStream(s.getInputStream()));
   }

   public void writeUtf8(final String str, final Socket s) {
      OutputStream out = s.getOutputStream();
      SerDe$.MODULE$.writeString(new DataOutputStream(out), str);
      out.flush();
   }

   public RAuthHelper(final SparkConf conf) {
      super(conf);
   }
}
