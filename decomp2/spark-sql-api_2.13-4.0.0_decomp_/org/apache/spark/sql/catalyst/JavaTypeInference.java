package org.apache.spark.sql.catalyst;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoder;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001ds!B\u0005\u000b\u0011\u0003)b!B\f\u000b\u0011\u0003A\u0002\"B\u0010\u0002\t\u0003\u0001\u0003\"B\u0011\u0002\t\u0003\u0011\u0003\"B\u001e\u0002\t\u0003a\u0004\"B\u001e\u0002\t\u0003a\u0006\"B\u001e\u0002\t\u0013\u0011\u0007\"CA\u0001\u0003E\u0005I\u0011BA\u0002\u0011\u001d\t\u0019#\u0001C\u0001\u0003K\t\u0011CS1wCRK\b/Z%oM\u0016\u0014XM\\2f\u0015\tYA\"\u0001\u0005dCR\fG._:u\u0015\tia\"A\u0002tc2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\u0002\u0001!\t1\u0012!D\u0001\u000b\u0005EQ\u0015M^1UsB,\u0017J\u001c4fe\u0016t7-Z\n\u0003\u0003e\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0016\u00035IgNZ3s\t\u0006$\u0018\rV=qKR\u00111e\f\t\u00055\u00112C&\u0003\u0002&7\t1A+\u001e9mKJ\u0002\"a\n\u0016\u000e\u0003!R!!\u000b\u0007\u0002\u000bQL\b/Z:\n\u0005-B#\u0001\u0003#bi\u0006$\u0016\u0010]3\u0011\u0005ii\u0013B\u0001\u0018\u001c\u0005\u001d\u0011un\u001c7fC:DQ\u0001M\u0002A\u0002E\n\u0001BY3b]RK\b/\u001a\t\u0003eej\u0011a\r\u0006\u0003iU\nqA]3gY\u0016\u001cGO\u0003\u00027o\u0005!A.\u00198h\u0015\u0005A\u0014\u0001\u00026bm\u0006L!AO\u001a\u0003\tQK\b/Z\u0001\u000bK:\u001cw\u000eZ3s\r>\u0014XCA\u001fG)\tqt\nE\u0002@\u0005\u0012k\u0011\u0001\u0011\u0006\u0003\u0003*\t\u0001\"\u001a8d_\u0012,'o]\u0005\u0003\u0007\u0002\u0013q\"Q4o_N$\u0018nY#oG>$WM\u001d\t\u0003\u000b\u001ac\u0001\u0001B\u0003H\t\t\u0007\u0001JA\u0001U#\tIE\n\u0005\u0002\u001b\u0015&\u00111j\u0007\u0002\b\u001d>$\b.\u001b8h!\tQR*\u0003\u0002O7\t\u0019\u0011I\\=\t\u000bA#\u0001\u0019A)\u0002\u0007\rd7\u000fE\u0002S3\u0012s!aU,\u0011\u0005Q[R\"A+\u000b\u0005Y#\u0012A\u0002\u001fs_>$h(\u0003\u0002Y7\u00051\u0001K]3eK\u001aL!AW.\u0003\u000b\rc\u0017m]:\u000b\u0005a[RCA/a)\tq\u0016\rE\u0002@\u0005~\u0003\"!\u00121\u0005\u000b\u001d+!\u0019\u0001%\t\u000bA*\u0001\u0019A\u0019\u0015\t\rD'\u000e\u001e\u0019\u0003I\u001a\u00042a\u0010\"f!\t)e\rB\u0005h\r\u0005\u0005\t\u0011!B\u0001\u0011\n\u0019q\fJ\u001a\t\u000b%4\u0001\u0019A\u0019\u0002\u0003QDQa\u001b\u0004A\u00021\f1b]3f]RK\b/Z*fiB\u0019!+\\8\n\u00059\\&aA*fiB\u0012\u0001O\u001d\t\u0004%f\u000b\bCA#s\t%\u0019(.!A\u0001\u0002\u000b\u0005\u0001JA\u0002`IEBq!\u001e\u0004\u0011\u0002\u0003\u0007a/A\u0007usB,g+\u0019:jC\ndWm\u001d\t\u0005%^L\u0018'\u0003\u0002y7\n\u0019Q*\u001991\u0005it\bc\u0001\u001a|{&\u0011Ap\r\u0002\r)f\u0004XMV1sS\u0006\u0014G.\u001a\t\u0003\u000bz$\u0011b ;\u0002\u0002\u0003\u0005)\u0011\u0001%\u0003\u0007}##'\u0001\u000bf]\u000e|G-\u001a:G_J$C-\u001a4bk2$HeM\u000b\u0003\u0003\u000bQC!a\u0002\u0002\u0012A)!k^A\u0005cA\"\u00111BA\b!\u0011\u001140!\u0004\u0011\u0007\u0015\u000by\u0001B\u0005\u0000\u000f\u0005\u0005\t\u0011!B\u0001\u0011.\u0012\u00111\u0003\t\u0005\u0003+\ty\"\u0004\u0002\u0002\u0018)!\u0011\u0011DA\u000e\u0003%)hn\u00195fG.,GMC\u0002\u0002\u001em\t!\"\u00198o_R\fG/[8o\u0013\u0011\t\t#a\u0006\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000fhKRT\u0015M^1CK\u0006t'+Z1eC\ndW\r\u0015:pa\u0016\u0014H/[3t)\u0011\t9#!\u000f\u0011\u000bi\tI#!\f\n\u0007\u0005-2DA\u0003BeJ\f\u0017\u0010\u0005\u0003\u00020\u0005URBAA\u0019\u0015\r\t\u0019dN\u0001\u0006E\u0016\fgn]\u0005\u0005\u0003o\t\tD\u0001\nQe>\u0004XM\u001d;z\t\u0016\u001c8M]5qi>\u0014\bbBA\u001e\u0011\u0001\u0007\u0011QH\u0001\nE\u0016\fgn\u00117bgN\u0004D!a\u0010\u0002DA!!+WA!!\r)\u00151\t\u0003\f\u0003\u000b\nI$!A\u0001\u0002\u000b\u0005\u0001JA\u0002`Ia\u0002"
)
public final class JavaTypeInference {
   public static PropertyDescriptor[] getJavaBeanReadableProperties(final Class beanClass) {
      return JavaTypeInference$.MODULE$.getJavaBeanReadableProperties(beanClass);
   }

   public static AgnosticEncoder encoderFor(final Type beanType) {
      return JavaTypeInference$.MODULE$.encoderFor(beanType);
   }

   public static AgnosticEncoder encoderFor(final Class cls) {
      return JavaTypeInference$.MODULE$.encoderFor(cls);
   }

   public static Tuple2 inferDataType(final Type beanType) {
      return JavaTypeInference$.MODULE$.inferDataType(beanType);
   }
}
