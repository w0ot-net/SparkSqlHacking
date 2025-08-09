package org.apache.spark.sql.catalyst.util;

import java.util.ArrayList;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%4A\u0001E\t\u0001=!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003(\u0011\u0015Y\u0003\u0001\"\u0001-\u0011\u001d\u0001\u0004A1A\u0005\u0012EBa\u0001\u0012\u0001!\u0002\u0013\u0011\u0004bB#\u0001\u0001\u0004%\tB\n\u0005\b\r\u0002\u0001\r\u0011\"\u0005H\u0011\u0019i\u0005\u0001)Q\u0005O!)a\n\u0001C\u0001\u001f\")1\u000b\u0001C\u0001)\")q\u000b\u0001C!1\u001e9\u0011,EA\u0001\u0012\u0003Qfa\u0002\t\u0012\u0003\u0003E\ta\u0017\u0005\u0006W5!\t\u0001\u0018\u0005\b;6\t\n\u0011\"\u0001_\u00051\u0019FO]5oO\u000e{gnY1u\u0015\t\u00112#\u0001\u0003vi&d'B\u0001\u000b\u0016\u0003!\u0019\u0017\r^1msN$(B\u0001\f\u0018\u0003\r\u0019\u0018\u000f\u001c\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sO\u000e\u00011C\u0001\u0001 !\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u0019\te.\u001f*fM\u0006IQ.\u0019=MK:<G\u000f[\u000b\u0002OA\u0011\u0001\u0005K\u0005\u0003S\u0005\u00121!\u00138u\u0003)i\u0017\r\u001f'f]\u001e$\b\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00055z\u0003C\u0001\u0018\u0001\u001b\u0005\t\u0002bB\u0013\u0004!\u0003\u0005\raJ\u0001\bgR\u0014\u0018N\\4t+\u0005\u0011\u0004cA\u001a8s5\tAG\u0003\u0002\u0013k)\ta'\u0001\u0003kCZ\f\u0017B\u0001\u001d5\u0005%\t%O]1z\u0019&\u001cH\u000f\u0005\u0002;\u0003:\u00111h\u0010\t\u0003y\u0005j\u0011!\u0010\u0006\u0003}u\ta\u0001\u0010:p_Rt\u0014B\u0001!\"\u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001\u000b\u0013\u0001C:ue&twm\u001d\u0011\u0002\r1,gn\u001a;i\u0003)aWM\\4uQ~#S-\u001d\u000b\u0003\u0011.\u0003\"\u0001I%\n\u0005)\u000b#\u0001B+oSRDq\u0001T\u0004\u0002\u0002\u0003\u0007q%A\u0002yIE\nq\u0001\\3oORD\u0007%A\u0004bi2KW.\u001b;\u0016\u0003A\u0003\"\u0001I)\n\u0005I\u000b#a\u0002\"p_2,\u0017M\\\u0001\u0007CB\u0004XM\u001c3\u0015\u0005!+\u0006\"\u0002,\u000b\u0001\u0004I\u0014!A:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!O\u0001\r'R\u0014\u0018N\\4D_:\u001c\u0017\r\u001e\t\u0003]5\u0019\"!D\u0010\u0015\u0003i\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nT#A0+\u0005\u001d\u00027&A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017!C;oG\",7m[3e\u0015\t1\u0017%\u0001\u0006b]:|G/\u0019;j_:L!\u0001[2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class StringConcat {
   private final int maxLength;
   private final ArrayList strings;
   private int length;

   public static int $lessinit$greater$default$1() {
      return StringConcat$.MODULE$.$lessinit$greater$default$1();
   }

   public int maxLength() {
      return this.maxLength;
   }

   public ArrayList strings() {
      return this.strings;
   }

   public int length() {
      return this.length;
   }

   public void length_$eq(final int x$1) {
      this.length = x$1;
   }

   public boolean atLimit() {
      return this.length() >= this.maxLength();
   }

   public void append(final String s) {
      if (s != null) {
         int sLen = s.length();
         if (!this.atLimit()) {
            int available = this.maxLength() - this.length();
            String stringToAppend = available >= sLen ? s : s.substring(0, available);
            BoxesRunTime.boxToBoolean(this.strings().add(stringToAppend));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.length_$eq((int)Math.min((long)this.length() + (long)sLen, 2147483632L));
      }
   }

   public String toString() {
      int finalLength = this.atLimit() ? this.maxLength() : this.length();
      StringBuilder result = new StringBuilder(finalLength);
      this.strings().forEach((s) -> result.append(s));
      return result.toString();
   }

   public StringConcat(final int maxLength) {
      this.maxLength = maxLength;
      this.strings = new ArrayList();
      this.length = 0;
   }
}
