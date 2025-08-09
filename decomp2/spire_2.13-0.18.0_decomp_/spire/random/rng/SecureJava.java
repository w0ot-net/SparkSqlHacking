package spire.random.rng;

import java.security.SecureRandom;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005U3A\u0001D\u0007\u0001)!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003*\u0001\u0011\u0005#\u0006C\u00035\u0001\u0011\u0005Q\u0007C\u0003<\u0001\u0011\u0005AhB\u0003A\u001b!\u0005\u0011IB\u0003\r\u001b!\u0005!\tC\u0003#\u0011\u0011\u0005a\tC\u0003H\u0011\u0011\u0005\u0001\nC\u0003U\u0011\u0011\u0005\u0001F\u0001\u0006TK\u000e,(/\u001a&bm\u0006T!AD\b\u0002\u0007ItwM\u0003\u0002\u0011#\u00051!/\u00198e_6T\u0011AE\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u0017/5\tq\"\u0003\u0002\u0019\u001f\t\t\u0012J\u001c;CCN,GmR3oKJ\fGo\u001c:\u0002\tI\fg\u000e\u001a\t\u00037\u0001j\u0011\u0001\b\u0006\u0003;y\t\u0001b]3dkJLG/\u001f\u0006\u0002?\u0005!!.\u0019<b\u0013\t\tCD\u0001\u0007TK\u000e,(/\u001a*b]\u0012|W.\u0001\u0004=S:LGO\u0010\u000b\u0003I\u0019\u0002\"!\n\u0001\u000e\u00035AQ!\u0007\u0002A\u0002i\t\u0001bY8qs&s\u0017\u000e^\u000b\u0002I\u0005aq-\u001a;TK\u0016$')\u001f;fgV\t1\u0006E\u0002-_Ej\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003YIJ!aM\u0017\u0003\t\tKH/Z\u0001\rg\u0016$8+Z3e\u0005f$Xm\u001d\u000b\u0003me\u0002\"\u0001L\u001c\n\u0005aj#\u0001B+oSRDQAO\u0003A\u0002-\nQAY=uKN\fqA\\3yi&sG\u000fF\u0001>!\tac(\u0003\u0002@[\t\u0019\u0011J\u001c;\u0002\u0015M+7-\u001e:f\u0015\u00064\u0018\r\u0005\u0002&\u0011M\u0011\u0001b\u0011\t\u0003Y\u0011K!!R\u0017\u0003\r\u0005s\u0017PU3g)\u0005\t\u0015!\u00034s_6\u0014\u0015\u0010^3t)\t!\u0013\nC\u0003;\u0015\u0001\u00071\u0006\u000b\u0004\u000b\u0017:{\u0015K\u0015\t\u0003Y1K!!T\u0017\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0003A\u000bQh]3fI\u0002J7\u000fI5h]>\u0014X\r\u001a\u0011fq\u000e,\u0007\u000f\u001e\u0011p]\u0002:\u0018N\u001c3poNt\u0003e^5mY\u0002\u0012W\r\t:f[>4X\r\u001a\u0011cK\u001a|'/\u001a\u00112]A\nQa]5oG\u0016\f\u0013aU\u0001\u0007a9\n$G\f\u0019\u0002\u000b\u0005\u0004\b\u000f\\="
)
public class SecureJava extends IntBasedGenerator {
   private final SecureRandom rand;

   public static SecureJava apply() {
      return SecureJava$.MODULE$.apply();
   }

   /** @deprecated */
   public static SecureJava fromBytes(final byte[] bytes) {
      return SecureJava$.MODULE$.fromBytes(bytes);
   }

   public SecureJava copyInit() {
      return new SecureJava(this.rand);
   }

   public byte[] getSeedBytes() {
      throw new UnsupportedOperationException("getSeedBytes");
   }

   public void setSeedBytes(final byte[] bytes) {
      throw new UnsupportedOperationException("setSeedBytes");
   }

   public int nextInt() {
      return this.rand.nextInt();
   }

   public SecureJava(final SecureRandom rand) {
      this.rand = rand;
   }
}
