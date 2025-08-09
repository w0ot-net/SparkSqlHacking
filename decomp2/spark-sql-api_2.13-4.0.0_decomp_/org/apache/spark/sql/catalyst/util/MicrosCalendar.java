package org.apache.spark.sql.catalyst.util;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.spark.sql.types.Decimal$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2AAB\u0004\u0001)!AA\u0004\u0001B\u0001B\u0003%Q\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\"\u0011\u00159\u0003\u0001\"\u0001)\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u0015\u0011\u0004\u0001\"\u00014\u00059i\u0015n\u0019:pg\u000e\u000bG.\u001a8eCJT!\u0001C\u0005\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0015-\t\u0001bY1uC2L8\u000f\u001e\u0006\u0003\u00195\t1a]9m\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7\u0001A\n\u0003\u0001U\u0001\"A\u0006\u000e\u000e\u0003]Q!\u0001\u0003\r\u000b\u0003e\tAA[1wC&\u00111d\u0006\u0002\u0012\u000fJ,wm\u001c:jC:\u001c\u0015\r\\3oI\u0006\u0014\u0018A\u0001;{!\t1b$\u0003\u0002 /\tAA+[7f5>tW-\u0001\teS\u001eLGo]%o\rJ\f7\r^5p]B\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t\u0019\u0011J\u001c;\u0002\rqJg.\u001b;?)\rI3\u0006\f\t\u0003U\u0001i\u0011a\u0002\u0005\u00069\r\u0001\r!\b\u0005\u0006A\r\u0001\r!I\u0001\nO\u0016$X*[2s_N$\u0012a\f\t\u0003EAJ!!M\u0012\u0003\t1{gnZ\u0001\ng\u0016$X*[2s_N$\"\u0001N\u001c\u0011\u0005\t*\u0014B\u0001\u001c$\u0005\u0011)f.\u001b;\t\u000ba*\u0001\u0019A\u0018\u0002\r5L7M]8t\u0001"
)
public class MicrosCalendar extends GregorianCalendar {
   private final int digitsInFraction;

   public long getMicros() {
      long d = (long)this.fields[14] * 1000000L;
      return d / Decimal$.MODULE$.POW_10()[this.digitsInFraction];
   }

   public void setMicros(final long micros) {
      long d = micros * Decimal$.MODULE$.POW_10()[this.digitsInFraction];
      this.fields[14] = (int)(d / 1000000L);
   }

   public MicrosCalendar(final TimeZone tz, final int digitsInFraction) {
      super(tz, Locale.US);
      this.digitsInFraction = digitsInFraction;
   }
}
