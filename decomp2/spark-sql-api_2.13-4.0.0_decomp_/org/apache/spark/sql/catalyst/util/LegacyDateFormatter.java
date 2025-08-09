package org.apache.spark.sql.catalyst.util;

import java.time.LocalDate;
import java.util.Date;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553qAB\u0004\u0011\u0002\u0007\u0005A\u0003C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003%\u0001\u0019\u0005Q\u0005C\u0003;\u0001\u0011\u00053\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003A\u0001\u0011\u0005CIA\nMK\u001e\f7-\u001f#bi\u00164uN]7biR,'O\u0003\u0002\t\u0013\u0005!Q\u000f^5m\u0015\tQ1\"\u0001\u0005dCR\fG._:u\u0015\taQ\"A\u0002tc2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001'\r\u0001Qc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005qiR\"A\u0004\n\u0005y9!!\u0004#bi\u00164uN]7biR,'/\u0001\u0004%S:LG\u000f\n\u000b\u0002CA\u0011aCI\u0005\u0003G]\u0011A!\u00168ji\u0006Y\u0001/\u0019:tKR{G)\u0019;f)\t1S\u0006\u0005\u0002(W5\t\u0001F\u0003\u0002\tS)\t!&\u0001\u0003kCZ\f\u0017B\u0001\u0017)\u0005\u0011!\u0015\r^3\t\u000b9\u0012\u0001\u0019A\u0018\u0002\u0003M\u0004\"\u0001M\u001c\u000f\u0005E*\u0004C\u0001\u001a\u0018\u001b\u0005\u0019$B\u0001\u001b\u0014\u0003\u0019a$o\\8u}%\u0011agF\u0001\u0007!J,G-\u001a4\n\u0005aJ$AB*ue&twM\u0003\u00027/\u0005)\u0001/\u0019:tKR\u0011Ah\u0010\t\u0003-uJ!AP\f\u0003\u0007%sG\u000fC\u0003/\u0007\u0001\u0007q&\u0001\u0004g_Jl\u0017\r\u001e\u000b\u0003_\tCQa\u0011\u0003A\u0002q\nA\u0001Z1zgR\u0011q&\u0012\u0005\u0006\r\u0016\u0001\raR\u0001\nY>\u001c\u0017\r\u001c#bi\u0016\u0004\"\u0001S&\u000e\u0003%S!AS\u0015\u0002\tQLW.Z\u0005\u0003\u0019&\u0013\u0011\u0002T8dC2$\u0015\r^3"
)
public interface LegacyDateFormatter extends DateFormatter {
   Date parseToDate(final String s);

   // $FF: synthetic method
   static int parse$(final LegacyDateFormatter $this, final String s) {
      return $this.parse(s);
   }

   default int parse(final String s) {
      return SparkDateTimeUtils$.MODULE$.fromJavaDate(new java.sql.Date(this.parseToDate(s).getTime()));
   }

   // $FF: synthetic method
   static String format$(final LegacyDateFormatter $this, final int days) {
      return $this.format(days);
   }

   default String format(final int days) {
      return this.format(SparkDateTimeUtils$.MODULE$.toJavaDate(days));
   }

   // $FF: synthetic method
   static String format$(final LegacyDateFormatter $this, final LocalDate localDate) {
      return $this.format(localDate);
   }

   default String format(final LocalDate localDate) {
      return this.format(SparkDateTimeUtils$.MODULE$.localDateToDays(localDate));
   }

   static void $init$(final LegacyDateFormatter $this) {
   }
}
