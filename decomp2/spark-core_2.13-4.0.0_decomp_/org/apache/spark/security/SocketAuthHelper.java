package org.apache.spark.security;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.apache.spark.SparkConf;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.Utils$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3QAC\u0006\u0001\u001bMA\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tC\u0001\u0011\t\u0011)A\u0005;!)!\u0005\u0001C\u0001G!9q\u0005\u0001b\u0001\n\u0003A\u0003B\u0002\u001b\u0001A\u0003%\u0011\u0006C\u00036\u0001\u0011\u0005a\u0007C\u0003E\u0001\u0011\u0005Q\tC\u0003H\u0001\u0011E\u0001\nC\u0003K\u0001\u0011E1J\u0001\tT_\u000e\\W\r^!vi\"DU\r\u001c9fe*\u0011A\"D\u0001\tg\u0016\u001cWO]5us*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\fAaY8oM\u000e\u0001Q#A\u000f\u0011\u0005yyR\"A\u0007\n\u0005\u0001j!!C*qCJ\\7i\u001c8g\u0003\u0015\u0019wN\u001c4!\u0003\u0019a\u0014N\\5u}Q\u0011AE\n\t\u0003K\u0001i\u0011a\u0003\u0005\u00065\r\u0001\r!H\u0001\u0007g\u0016\u001c'/\u001a;\u0016\u0003%\u0002\"AK\u0019\u000f\u0005-z\u0003C\u0001\u0017\u0017\u001b\u0005i#B\u0001\u0018\u001c\u0003\u0019a$o\\8u}%\u0011\u0001GF\u0001\u0007!J,G-\u001a4\n\u0005I\u001a$AB*ue&twM\u0003\u00021-\u000591/Z2sKR\u0004\u0013AC1vi\"\u001cE.[3oiR\u0011qG\u000f\t\u0003+aJ!!\u000f\f\u0003\tUs\u0017\u000e\u001e\u0005\u0006w\u0019\u0001\r\u0001P\u0001\u0002gB\u0011QHQ\u0007\u0002})\u0011q\bQ\u0001\u0004]\u0016$(\"A!\u0002\t)\fg/Y\u0005\u0003\u0007z\u0012aaU8dW\u0016$\u0018\u0001D1vi\"$vnU3sm\u0016\u0014HCA\u001cG\u0011\u0015Yt\u00011\u0001=\u0003!\u0011X-\u00193Vi\u001aDDCA\u0015J\u0011\u0015Y\u0004\u00021\u0001=\u0003%9(/\u001b;f+R4\u0007\bF\u00028\u0019:CQ!T\u0005A\u0002%\n1a\u001d;s\u0011\u0015Y\u0014\u00021\u0001=\u0001"
)
public class SocketAuthHelper {
   private final SparkConf conf;
   private final String secret;

   public SparkConf conf() {
      return this.conf;
   }

   public String secret() {
      return this.secret;
   }

   public void authClient(final Socket s) {
      boolean shouldClose = true;

      try {
         int currentTimeout = s.getSoTimeout();

         try {
            label110: {
               s.setSoTimeout(10000);
               String clientSecret = this.readUtf8(s);
               String var10000 = this.secret();
               if (var10000 == null) {
                  if (clientSecret == null) {
                     break label110;
                  }
               } else if (var10000.equals(clientSecret)) {
                  break label110;
               }

               this.writeUtf8("err", s);
               throw new IllegalArgumentException("Authentication failed.");
            }

            this.writeUtf8("ok", s);
            shouldClose = false;
         } finally {
            s.setSoTimeout(currentTimeout);
         }
      } finally {
         if (shouldClose) {
            JavaUtils.closeQuietly(s);
         }

      }

   }

   public void authToServer(final Socket s) {
      boolean shouldClose = true;

      try {
         label58: {
            this.writeUtf8(this.secret(), s);
            String reply = this.readUtf8(s);
            String var4 = "ok";
            if (reply == null) {
               if (var4 == null) {
                  break label58;
               }
            } else if (reply.equals(var4)) {
               break label58;
            }

            throw new IllegalArgumentException("Authentication failed.");
         }

         shouldClose = false;
      } finally {
         if (shouldClose) {
            JavaUtils.closeQuietly(s);
         }

      }

   }

   public String readUtf8(final Socket s) {
      DataInputStream din = new DataInputStream(s.getInputStream());
      int len = din.readInt();
      byte[] bytes = new byte[len];
      din.readFully(bytes);
      return new String(bytes, StandardCharsets.UTF_8);
   }

   public void writeUtf8(final String str, final Socket s) {
      byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      dout.writeInt(bytes.length);
      dout.write(bytes, 0, bytes.length);
      dout.flush();
   }

   public SocketAuthHelper(final SparkConf conf) {
      this.conf = conf;
      this.secret = Utils$.MODULE$.createSecret(conf);
   }
}
