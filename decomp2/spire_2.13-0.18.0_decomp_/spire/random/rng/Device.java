package spire.random.rng;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import spire.random.Generator;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A\u0001E\t\u00011!AQ\u0004\u0001B\u0001B\u0003%a\u0004C\u0003'\u0001\u0011\u0005q\u0005C\u0004,\u0001\t\u0007I\u0011\u0002\u0017\t\rA\u0002\u0001\u0015!\u0003.\u0011\u0015\t\u0004\u0001\"\u00013\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015q\u0004\u0001\"\u0001@\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0015Q\u0005\u0001\"\u0001L\u000f\u0015y\u0015\u0003#\u0001Q\r\u0015\u0001\u0012\u0003#\u0001R\u0011\u001513\u0002\"\u0001V\u0011\u001516\u0002\"\u0001X\u0011\u0015!2\u0002\"\u0001f\u0011\u001517\u0002\"\u0001f\u0005\u0019!UM^5dK*\u0011!cE\u0001\u0004e:<'B\u0001\u000b\u0016\u0003\u0019\u0011\u0018M\u001c3p[*\ta#A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001I\u0002C\u0001\u000e\u001c\u001b\u0005\u0019\u0012B\u0001\u000f\u0014\u0005%9UM\\3sCR|'/A\u0001g!\tyB%D\u0001!\u0015\t\t#%\u0001\u0002j_*\t1%\u0001\u0003kCZ\f\u0017BA\u0013!\u0005\u00111\u0015\u000e\\3\u0002\rqJg.\u001b;?)\tA#\u0006\u0005\u0002*\u00015\t\u0011\u0003C\u0003\u001e\u0005\u0001\u0007a$A\u0002eSN,\u0012!\f\t\u0003?9J!a\f\u0011\u0003\u001f\u0011\u000bG/Y%oaV$8\u000b\u001e:fC6\fA\u0001Z5tA\u0005A1m\u001c9z\u0013:LG/F\u0001\u001a\u000319W\r^*fK\u0012\u0014\u0015\u0010^3t+\u0005)\u0004c\u0001\u001c:w5\tqGC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQtGA\u0003BeJ\f\u0017\u0010\u0005\u00027y%\u0011Qh\u000e\u0002\u0005\u0005f$X-\u0001\u0007tKR\u001cV-\u001a3CsR,7\u000f\u0006\u0002A\u0007B\u0011a'Q\u0005\u0003\u0005^\u0012A!\u00168ji\")Ai\u0002a\u0001k\u0005)!-\u001f;fg\u00069a.\u001a=u\u0013:$H#A$\u0011\u0005YB\u0015BA%8\u0005\rIe\u000e^\u0001\t]\u0016DH\u000fT8oOR\tA\n\u0005\u00027\u001b&\u0011aj\u000e\u0002\u0005\u0019>tw-\u0001\u0004EKZL7-\u001a\t\u0003S-\u0019\"a\u0003*\u0011\u0005Y\u001a\u0016B\u0001+8\u0005\u0019\te.\u001f*fMR\t\u0001+A\u0003baBd\u0017\u0010\u0006\u0002)1\")\u0011,\u0004a\u00015\u0006!\u0001/\u0019;i!\tY&M\u0004\u0002]AB\u0011QlN\u0007\u0002=*\u0011qlF\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005<\u0014A\u0002)sK\u0012,g-\u0003\u0002dI\n11\u000b\u001e:j]\u001eT!!Y\u001c\u0016\u0003!\nq!\u001e:b]\u0012|W\u000e"
)
public class Device extends Generator {
   private final File f;
   private final DataInputStream dis;

   public static Device urandom() {
      return Device$.MODULE$.urandom();
   }

   public static Device random() {
      return Device$.MODULE$.random();
   }

   public static Device apply(final String path) {
      return Device$.MODULE$.apply(path);
   }

   private DataInputStream dis() {
      return this.dis;
   }

   public Generator copyInit() {
      return new Device(this.f);
   }

   public byte[] getSeedBytes() {
      throw new UnsupportedOperationException("getSeedBytes");
   }

   public void setSeedBytes(final byte[] bytes) {
      throw new UnsupportedOperationException("setSeedBytes");
   }

   public int nextInt() {
      return this.dis().readInt();
   }

   public long nextLong() {
      return this.dis().readLong();
   }

   public Device(final File f) {
      this.f = f;
      if (!f.canRead()) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("can't read %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{f})));
      } else {
         this.dis = new DataInputStream(new FileInputStream(f));
      }
   }
}
