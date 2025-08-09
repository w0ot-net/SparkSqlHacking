package org.apache.spark.sql.internal.types;

import java.io.Serializable;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StringType$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4Q!\u0004\b\u0002\u0002mA\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\u0006i\u0001!\t!\u000e\u0005\u0007s\u0001!\tE\u0005\u001e\t\ry\u0002A\u0011\t\n@\u0011\u0019A\u0005\u0001\"\u0011\u0013\u0013\"1A\n\u0001C\u0001%5CQA\u0015\u0001\u0007\u0002M;q!\u0016\b\u0002\u0002#\u0005aKB\u0004\u000e\u001d\u0005\u0005\t\u0012A,\t\u000bQJA\u0011\u00012\t\u000f\rL\u0011\u0013!C\u0001I\"9q.CA\u0001\n\u0013\u0001(AE!cgR\u0014\u0018m\u0019;TiJLgn\u001a+za\u0016T!a\u0004\t\u0002\u000bQL\b/Z:\u000b\u0005E\u0011\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005M!\u0012aA:rY*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001a\u0012\u0005\u0005\u0002\u001e?5\taD\u0003\u0002\u0010%%\u0011\u0001E\b\u0002\u0011\u0003\n\u001cHO]1di\u0012\u000bG/\u0019+za\u0016\u0004\"A\t\u0017\u000f\u0005\rJcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u001b\u0003\u0019a$o\\8u}%\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+W\u00059\u0001/Y2lC\u001e,'\"\u0001\u0015\n\u00055r#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0016,\u0003U\u0019X\u000f\u001d9peR\u001cHK]5n\u0007>dG.\u0019;j_:\u0004\"!\r\u001a\u000e\u0003-J!aM\u0016\u0003\u000f\t{w\u000e\\3b]\u00061A(\u001b8jiz\"\"A\u000e\u001d\u0011\u0005]\u0002Q\"\u0001\b\t\u000f=\u0012\u0001\u0013!a\u0001a\u0005\u0019B-\u001a4bk2$8i\u001c8de\u0016$X\rV=qKV\t1\b\u0005\u0002\u001ey%\u0011QH\b\u0002\t\t\u0006$\u0018\rV=qK\u0006a1/[7qY\u0016\u001cFO]5oOV\t\u0001\t\u0005\u0002B\u000b:\u0011!i\u0011\t\u0003I-J!\u0001R\u0016\u0002\rA\u0013X\rZ3g\u0013\t1uI\u0001\u0004TiJLgn\u001a\u0006\u0003\t.\n1\"Y2dKB$8\u000fV=qKR\u0011\u0001G\u0013\u0005\u0006\u0017\u0016\u0001\raO\u0001\u0006_RDWM]\u0001\u0014G\u0006tWk]3Ue&l7i\u001c7mCRLwN\u001c\u000b\u0003a9CQa\u0013\u0004A\u0002=\u0003\"!\b)\n\u0005Es\"AC*ue&tw\rV=qK\u0006\t\u0012mY2faR\u001c8\u000b\u001e:j]\u001e$\u0016\u0010]3\u0015\u0005A\"\u0006\"B&\b\u0001\u0004y\u0015AE!cgR\u0014\u0018m\u0019;TiJLgn\u001a+za\u0016\u0004\"aN\u0005\u0014\u0007%A6\f\u0005\u000223&\u0011!l\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016AA5p\u0015\u0005\u0001\u0017\u0001\u00026bm\u0006L!!L/\u0015\u0003Y\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nT#A3+\u0005A27&A4\u0011\u0005!lW\"A5\u000b\u0005)\\\u0017!C;oG\",7m[3e\u0015\ta7&\u0001\u0006b]:|G/\u0019;j_:L!A\\5\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001r!\t\u0011X/D\u0001t\u0015\t!x,\u0001\u0003mC:<\u0017B\u0001<t\u0005\u0019y%M[3di\u0002"
)
public abstract class AbstractStringType extends AbstractDataType implements Serializable {
   private final boolean supportsTrimCollation;

   public static boolean $lessinit$greater$default$1() {
      return AbstractStringType$.MODULE$.$lessinit$greater$default$1();
   }

   public DataType defaultConcreteType() {
      return StringType$.MODULE$;
   }

   public String simpleString() {
      return "string";
   }

   public boolean acceptsType(final DataType other) {
      if (!(other instanceof StringType var4)) {
         return false;
      } else {
         return this.canUseTrimCollation(var4) && this.acceptsStringType(var4);
      }
   }

   public boolean canUseTrimCollation(final StringType other) {
      return this.supportsTrimCollation || !other.usesTrimCollation();
   }

   public abstract boolean acceptsStringType(final StringType other);

   public AbstractStringType(final boolean supportsTrimCollation) {
      this.supportsTrimCollation = supportsTrimCollation;
   }
}
