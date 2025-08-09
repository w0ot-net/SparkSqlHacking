package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SerializedLambda;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.DateType$;
import org.apache.spark.sql.types.TimestampType$;
import org.apache.spark.unsafe.types.UTF8String;
import org.apache.spark.util.SparkClassUtils.;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tMeaB\u0017/!\u0003\r\ta\u000f\u0005\u0006\u0005\u0002!\ta\u0011\u0005\b\u000f\u0002\u0011\r\u0011\"\u0002I\u0011\u001d\u0001\u0006A1A\u0005\u0006ECq\u0001\u0017\u0001C\u0002\u0013\u0015\u0011\u000bC\u0003Z\u0001\u0011\u0005!\fC\u0003o\u0001\u0011\u0005q\u000eC\u0003r\u0001\u0011\u0005!\u000fC\u0003|\u0001\u0011\u0005A\u0010C\u0004\u0002\u0004\u0001!\t!!\u0002\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!I\u00111\u0003\u0001C\u0002\u0013%\u0011Q\u0003\u0005\b\u0003/\u0001A\u0011AA\r\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!a\u000b\u0001\t\u0003\ti\u0003C\u0004\u0002:\u0001!\t\"a\u000f\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u0011q\n\u0001\u0005\u0002\u0005E\u0003bBA,\u0001\u0011\u0005\u0011\u0011\f\u0005\b\u0003K\u0002A\u0011AA4\u0011\u001d\ti\u0007\u0001C\u0001\u0003_Bq!!\u001e\u0001\t\u0003\t9\bC\u0004\u0002~\u0001!\t!a \t\u0013\u0005=\u0005A1A\u0005\n\u0005E\u0005BCAO\u0001!\u0015\r\u0011\"\u0003\u0002 \"9\u0011Q\u0016\u0001\u0005\u0002\u0005=\u0006bBAZ\u0001\u0011\u0005\u0011Q\u0017\u0005\b\u0003g\u0003A\u0011AA`\u0011\u001d\t)\r\u0001C\u0001\u0003\u000fDq!a3\u0001\t\u0003\ti\rC\u0004\u0002L\u0002!\t!a5\t\u000f\u0005e\u0007\u0001\"\u0001\u0002\\\"9\u0011q\u001c\u0001\u0005\u0002\u0005\u0005\bbBA\u007f\u0001\u0011\u0005\u0011q \u0005\n\u0005\u001f\u0001\u0011\u0013!C\u0001\u0005#AqAa\n\u0001\t\u0003\u0011I\u0003C\u0004\u0003B\u0001!\tAa\u0011\t\u000f\t-\u0003\u0001\"\u0001\u0003N!I!Q\u000b\u0001\u0012\u0002\u0013\u0005!\u0011\u0003\u0005\b\u0005/\u0002A\u0011\u0001B-\u0011\u001d\u0011\t\u0007\u0001C\u0005\u0005GBqA!\u001f\u0001\t\u0013\u0011YhB\u0004\u0003\u0006:B\tAa\"\u0007\r5r\u0003\u0012\u0001BF\u0011\u001d\u0011yi\u000bC\u0001\u0005#\u0013!c\u00159be.$\u0015\r^3US6,W\u000b^5mg*\u0011q\u0006M\u0001\u0005kRLGN\u0003\u00022e\u0005A1-\u0019;bYf\u001cHO\u0003\u00024i\u0005\u00191/\u001d7\u000b\u0005U2\u0014!B:qCJ\\'BA\u001c9\u0003\u0019\t\u0007/Y2iK*\t\u0011(A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001yA\u0011Q\bQ\u0007\u0002})\tq(A\u0003tG\u0006d\u0017-\u0003\u0002B}\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001#\u0011\u0005u*\u0015B\u0001$?\u0005\u0011)f.\u001b;\u0002\u0017QKW.\u001a.p]\u0016,FkQ\u000b\u0002\u0013B\u0011!JT\u0007\u0002\u0017*\u0011q\u0006\u0014\u0006\u0002\u001b\u0006!!.\u0019<b\u0013\ty5J\u0001\u0005US6,'l\u001c8f\u00031\u0019\u0018N\\4mK\"{WO\u001d+{+\u0005\u0011\u0006CA*W\u001b\u0005!&BA+L\u0003\u0015\u0011XmZ3y\u0013\t9FKA\u0004QCR$XM\u001d8\u0002\u001dMLgn\u001a7f\u001b&tW\u000f^3Uu\u0006Iq-\u001a;[_:,\u0017\n\u001a\u000b\u00037\u0006\u0004\"\u0001X0\u000e\u0003uS!A\u0018'\u0002\tQLW.Z\u0005\u0003Av\u0013aAW8oK&#\u0007\"\u00022\u0006\u0001\u0004\u0019\u0017A\u0003;j[\u0016TvN\\3JIB\u0011Am\u001b\b\u0003K&\u0004\"A\u001a \u000e\u0003\u001dT!\u0001\u001b\u001e\u0002\rq\u0012xn\u001c;?\u0013\tQg(\u0001\u0004Qe\u0016$WMZ\u0005\u0003Y6\u0014aa\u0015;sS:<'B\u00016?\u0003-9W\r\u001e+j[\u0016TvN\\3\u0015\u0005%\u0003\b\"\u00022\u0007\u0001\u0004\u0019\u0017!C1osR{G)Y=t)\t\u0019h\u000f\u0005\u0002>i&\u0011QO\u0010\u0002\u0004\u0013:$\b\"B<\b\u0001\u0004A\u0018aA8cUB\u0011Q(_\u0005\u0003uz\u00121!\u00118z\u0003-\tg.\u001f+p\u001b&\u001c'o\\:\u0015\u0007u\f\t\u0001\u0005\u0002>}&\u0011qP\u0010\u0002\u0005\u0019>tw\rC\u0003x\u0011\u0001\u0007\u00010\u0001\bnS\u000e\u0014xn\u001d+p\u001b&dG.[:\u0015\u0007u\f9\u0001\u0003\u0004\u0002\n%\u0001\r!`\u0001\u0007[&\u001c'o\\:\u0002\u001d5LG\u000e\\5t)>l\u0015n\u0019:pgR\u0019Q0a\u0004\t\r\u0005E!\u00021\u0001~\u0003\u0019i\u0017\u000e\u001c7jg\u0006YQ*\u0013(`'\u0016\u001buJ\u0014#T+\u0005i\u0018aD7jGJ|7\u000fV8J]N$\u0018M\u001c;\u0015\t\u0005m\u0011\u0011\u0005\t\u00049\u0006u\u0011bAA\u0010;\n9\u0011J\\:uC:$\bBBA\u0005\u0019\u0001\u0007Q0A\bj]N$\u0018M\u001c;U_6K7M]8t)\ri\u0018q\u0005\u0005\b\u0003Si\u0001\u0019AA\u000e\u0003\u001dIgn\u001d;b]R\f\u0011bY8om\u0016\u0014H\u000f\u0016>\u0015\u000fu\fy#!\r\u00026!1\u0011\u0011\u0002\bA\u0002uDa!a\r\u000f\u0001\u0004Y\u0016\u0001\u00034s_6TvN\\3\t\r\u0005]b\u00021\u0001\\\u0003\u0019!xNW8oK\u0006\u0001r-\u001a;M_\u000e\fG\u000eR1uKRKW.\u001a\u000b\u0007\u0003{\t\u0019%!\u0012\u0011\u0007q\u000by$C\u0002\u0002Bu\u0013Q\u0002T8dC2$\u0015\r^3US6,\u0007BBA\u0005\u001f\u0001\u0007Q\u0010\u0003\u0004\u0002H=\u0001\raW\u0001\u0007u>tW-\u00133\u0002+5L7M]8t)>dunY1m\t\u0006$X\rV5nKR!\u0011QHA'\u0011\u0019\tI\u0001\u0005a\u0001{\u0006)Bn\\2bY\u0012\u000bG/\u001a+j[\u0016$v.T5de>\u001cHcA?\u0002T!9\u0011QK\tA\u0002\u0005u\u0012!\u00047pG\u0006dG)\u0019;f)&lW-A\bm_\u000e\fG\u000eR1uKR{G)Y=t)\r\u0019\u00181\f\u0005\b\u0003;\u0012\u0002\u0019AA0\u0003%awnY1m\t\u0006$X\rE\u0002]\u0003CJ1!a\u0019^\u0005%aunY1m\t\u0006$X-A\beCf\u001cHk\u001c'pG\u0006dG)\u0019;f)\u0011\ty&!\u001b\t\r\u0005-4\u00031\u0001t\u0003\u0011!\u0017-_:\u0002\u00195L7M]8t)>$\u0015-_:\u0015\u000bM\f\t(a\u001d\t\r\u0005%A\u00031\u0001~\u0011\u0019\t9\u0005\u0006a\u00017\u0006aA-Y=t)>l\u0015n\u0019:pgR)Q0!\u001f\u0002|!1\u00111N\u000bA\u0002MDa!a\u0012\u0016\u0001\u0004Y\u0016\u0001\u00044s_6T\u0015M^1ECR,GcA:\u0002\u0002\"9\u00111\u0011\fA\u0002\u0005\u0015\u0015\u0001\u00023bi\u0016\u0004B!a\"\u0002\f6\u0011\u0011\u0011\u0012\u0006\u0003g1KA!!$\u0002\n\n!A)\u0019;f\u0003EQxN\\3J]\u001a|7\t\\1tg:\u000bW.Z\u000b\u0003\u0003'\u0003B!!&\u0002\u001c6\u0011\u0011q\u0013\u0006\u0004\u00033c\u0015\u0001\u00027b]\u001eL1\u0001\\AL\u0003Y9W\r^(gMN,Go\u001d\"z/\u0006dG\u000eS1oI2,WCAAQ!\u0011\t\u0019+!+\u000e\u0005\u0005\u0015&\u0002BAT\u0003/\u000ba!\u001b8w_.,\u0017\u0002BAV\u0003K\u0013A\"T3uQ>$\u0007*\u00198eY\u0016\f!\u0002^8KCZ\fG)\u0019;f)\u0011\t))!-\t\r\u0005-\u0014\u00041\u0001t\u0003=!xNS1wCRKW.Z:uC6\u0004H\u0003BA\\\u0003{\u0003B!a\"\u0002:&!\u00111XAE\u0005%!\u0016.\\3ti\u0006l\u0007\u000f\u0003\u0004\u0002\ni\u0001\r! \u000b\u0007\u0003o\u000b\t-a1\t\u000b\t\\\u0002\u0019A2\t\r\u0005%1\u00041\u0001~\u0003]!xNS1wCRKW.Z:uC6\u0004hj\u001c*fE\u0006\u001cX\r\u0006\u0003\u00028\u0006%\u0007BBA\u00059\u0001\u0007Q0A\tge>l'*\u0019<b)&lWm\u001d;b[B$2!`Ah\u0011\u001d\t\t.\ba\u0001\u0003o\u000b\u0011\u0001\u001e\u000b\u0006{\u0006U\u0017q\u001b\u0005\u0006Ez\u0001\ra\u0019\u0005\b\u0003#t\u0002\u0019AA\\\u0003e1'o\\7KCZ\fG+[7fgR\fW\u000e\u001d(p%\u0016\u0014\u0017m]3\u0015\u0007u\fi\u000eC\u0004\u0002R~\u0001\r!a.\u0002\u0019M$(/\u001b8h)>$\u0015\r^3\u0015\t\u0005\r\u0018\u0011\u001e\t\u0005{\u0005\u00158/C\u0002\u0002hz\u0012aa\u00149uS>t\u0007bBAvA\u0001\u0007\u0011Q^\u0001\u0002gB!\u0011q^A}\u001b\t\t\tP\u0003\u0003\u0002t\u0006U\u0018!\u0002;za\u0016\u001c(bAA|i\u00051QO\\:bM\u0016LA!a?\u0002r\nQQ\u000b\u0016$9'R\u0014\u0018N\\4\u0002!M$(/\u001b8h)>$\u0015\r^3B]NLG#B:\u0003\u0002\t\r\u0001bBAvC\u0001\u0007\u0011Q\u001e\u0005\n\u0005\u000b\t\u0003\u0013!a\u0001\u0005\u000f\tqaY8oi\u0016DH\u000f\u0005\u0003\u0003\n\t-Q\"\u0001\u001b\n\u0007\t5AG\u0001\u0007Rk\u0016\u0014\u0018pQ8oi\u0016DH/\u0001\u000etiJLgn\u001a+p\t\u0006$X-\u00118tS\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u0014)\"!q\u0001B\u000bW\t\u00119\u0002\u0005\u0003\u0003\u001a\t\rRB\u0001B\u000e\u0015\u0011\u0011iBa\b\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B\u0011}\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t\u0015\"1\u0004\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001\u00069beN,G+[7fgR\fW\u000e]*ue&tw\r\u0006\u0003\u0003,\t}\u0002#C\u001f\u0003.\tE\"q\u0007B\u001d\u0013\r\u0011yC\u0010\u0002\u0007)V\u0004H.Z\u001a\u0011\tu\u0012\u0019d]\u0005\u0004\u0005kq$!B!se\u0006L\b\u0003B\u001f\u0002fn\u00032!\u0010B\u001e\u0013\r\u0011iD\u0010\u0002\b\u0005>|G.Z1o\u0011\u001d\tYo\ta\u0001\u0003[\f\u0011c\u001d;sS:<Gk\u001c+j[\u0016\u001cH/Y7q)\u0019\u0011)Ea\u0012\u0003JA!Q(!:~\u0011\u001d\tY\u000f\na\u0001\u0003[DQA\u0019\u0013A\u0002m\u000bQc\u001d;sS:<Gk\u001c+j[\u0016\u001cH/Y7q\u0003:\u001c\u0018\u000eF\u0004~\u0005\u001f\u0012\tFa\u0015\t\u000f\u0005-X\u00051\u0001\u0002n\")!-\na\u00017\"I!QA\u0013\u0011\u0002\u0003\u0007!qA\u0001 gR\u0014\u0018N\\4U_RKW.Z:uC6\u0004\u0018I\\:jI\u0011,g-Y;mi\u0012\u001a\u0014\u0001I:ue&tw\rV8US6,7\u000f^1na^KG\u000f[8viRKW.\u001a.p]\u0016$bA!\u0012\u0003\\\tu\u0003bBAvO\u0001\u0007\u0011Q\u001e\u0005\b\u0005?:\u0003\u0019\u0001B\u001d\u00035\tG\u000e\\8x)&lWMW8oK\u0006yq-\u001a;Ue&lW.\u001a3Ti\u0006\u0014H\u000fF\u0002t\u0005KBqAa\u001a)\u0001\u0004\u0011I'A\u0003csR,7\u000fE\u0003>\u0005g\u0011Y\u0007E\u0002>\u0005[J1Aa\u001c?\u0005\u0011\u0011\u0015\u0010^3)\u0007!\u0012\u0019\bE\u0002>\u0005kJ1Aa\u001e?\u0005\u0019Ig\u000e\\5oK\u0006iq-\u001a;Ue&lW.\u001a3F]\u0012$Ra\u001dB?\u0005\u0003CaAa *\u0001\u0004\u0019\u0018!B:uCJ$\bb\u0002B4S\u0001\u0007!\u0011\u000e\u0015\u0004S\tM\u0014AE*qCJ\\G)\u0019;f)&lW-\u0016;jYN\u00042A!#,\u001b\u0005q3\u0003B\u0016=\u0005\u001b\u00032A!#\u0001\u0003\u0019a\u0014N\\5u}Q\u0011!q\u0011"
)
public interface SparkDateTimeUtils {
   void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$TimeZoneUTC_$eq(final TimeZone x$1);

   void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$singleHourTz_$eq(final Pattern x$1);

   void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$singleMinuteTz_$eq(final Pattern x$1);

   void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName_$eq(final String x$1);

   TimeZone TimeZoneUTC();

   Pattern singleHourTz();

   Pattern singleMinuteTz();

   // $FF: synthetic method
   static ZoneId getZoneId$(final SparkDateTimeUtils $this, final String timeZoneId) {
      return $this.getZoneId(timeZoneId);
   }

   default ZoneId getZoneId(final String timeZoneId) {
      try {
         String formattedZoneId = this.singleHourTz().matcher(timeZoneId).replaceFirst("$10$2:");
         formattedZoneId = this.singleMinuteTz().matcher(formattedZoneId).replaceFirst("$1$2:0$3");
         return ZoneId.of(formattedZoneId, ZoneId.SHORT_IDS);
      } catch (DateTimeException var4) {
         throw ExecutionErrors$.MODULE$.zoneOffsetError(timeZoneId, var4);
      }
   }

   // $FF: synthetic method
   static TimeZone getTimeZone$(final SparkDateTimeUtils $this, final String timeZoneId) {
      return $this.getTimeZone(timeZoneId);
   }

   default TimeZone getTimeZone(final String timeZoneId) {
      return TimeZone.getTimeZone(this.getZoneId(timeZoneId));
   }

   // $FF: synthetic method
   static int anyToDays$(final SparkDateTimeUtils $this, final Object obj) {
      return $this.anyToDays(obj);
   }

   default int anyToDays(final Object obj) {
      if (obj instanceof Date var4) {
         return this.fromJavaDate(var4);
      } else if (obj instanceof LocalDate var5) {
         return this.localDateToDays(var5);
      } else {
         throw new MatchError(obj);
      }
   }

   // $FF: synthetic method
   static long anyToMicros$(final SparkDateTimeUtils $this, final Object obj) {
      return $this.anyToMicros(obj);
   }

   default long anyToMicros(final Object obj) {
      if (obj instanceof Timestamp var5) {
         return this.fromJavaTimestamp(var5);
      } else if (obj instanceof Instant var6) {
         return this.instantToMicros(var6);
      } else if (obj instanceof LocalDateTime var7) {
         return this.localDateTimeToMicros(var7);
      } else {
         throw new MatchError(obj);
      }
   }

   // $FF: synthetic method
   static long microsToMillis$(final SparkDateTimeUtils $this, final long micros) {
      return $this.microsToMillis(micros);
   }

   default long microsToMillis(final long micros) {
      return Math.floorDiv(micros, 1000L);
   }

   // $FF: synthetic method
   static long millisToMicros$(final SparkDateTimeUtils $this, final long millis) {
      return $this.millisToMicros(millis);
   }

   default long millisToMicros(final long millis) {
      return Math.multiplyExact(millis, 1000L);
   }

   long org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS();

   // $FF: synthetic method
   static Instant microsToInstant$(final SparkDateTimeUtils $this, final long micros) {
      return $this.microsToInstant(micros);
   }

   default Instant microsToInstant(final long micros) {
      long secs = Math.floorDiv(micros, 1000000L);
      long mos = micros - secs * 1000000L;
      return Instant.ofEpochSecond(secs, mos * 1000L);
   }

   // $FF: synthetic method
   static long instantToMicros$(final SparkDateTimeUtils $this, final Instant instant) {
      return $this.instantToMicros(instant);
   }

   default long instantToMicros(final Instant instant) {
      long secs = instant.getEpochSecond();
      if (secs == this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS()) {
         long us = Math.multiplyExact(secs + 1L, 1000000L);
         return Math.addExact(us, TimeUnit.NANOSECONDS.toMicros((long)instant.getNano()) - 1000000L);
      } else {
         long us = Math.multiplyExact(secs, 1000000L);
         return Math.addExact(us, TimeUnit.NANOSECONDS.toMicros((long)instant.getNano()));
      }
   }

   // $FF: synthetic method
   static long convertTz$(final SparkDateTimeUtils $this, final long micros, final ZoneId fromZone, final ZoneId toZone) {
      return $this.convertTz(micros, fromZone, toZone);
   }

   default long convertTz(final long micros, final ZoneId fromZone, final ZoneId toZone) {
      ZonedDateTime rebasedDateTime = this.getLocalDateTime(micros, toZone).atZone(fromZone);
      return this.instantToMicros(rebasedDateTime.toInstant());
   }

   // $FF: synthetic method
   static LocalDateTime getLocalDateTime$(final SparkDateTimeUtils $this, final long micros, final ZoneId zoneId) {
      return $this.getLocalDateTime(micros, zoneId);
   }

   default LocalDateTime getLocalDateTime(final long micros, final ZoneId zoneId) {
      return this.microsToInstant(micros).atZone(zoneId).toLocalDateTime();
   }

   // $FF: synthetic method
   static LocalDateTime microsToLocalDateTime$(final SparkDateTimeUtils $this, final long micros) {
      return $this.microsToLocalDateTime(micros);
   }

   default LocalDateTime microsToLocalDateTime(final long micros) {
      return this.getLocalDateTime(micros, ZoneOffset.UTC);
   }

   // $FF: synthetic method
   static long localDateTimeToMicros$(final SparkDateTimeUtils $this, final LocalDateTime localDateTime) {
      return $this.localDateTimeToMicros(localDateTime);
   }

   default long localDateTimeToMicros(final LocalDateTime localDateTime) {
      return this.instantToMicros(localDateTime.toInstant(ZoneOffset.UTC));
   }

   // $FF: synthetic method
   static int localDateToDays$(final SparkDateTimeUtils $this, final LocalDate localDate) {
      return $this.localDateToDays(localDate);
   }

   default int localDateToDays(final LocalDate localDate) {
      return MathUtils$.MODULE$.toIntExact(localDate.toEpochDay());
   }

   // $FF: synthetic method
   static LocalDate daysToLocalDate$(final SparkDateTimeUtils $this, final int days) {
      return $this.daysToLocalDate(days);
   }

   default LocalDate daysToLocalDate(final int days) {
      return LocalDate.ofEpochDay((long)days);
   }

   // $FF: synthetic method
   static int microsToDays$(final SparkDateTimeUtils $this, final long micros, final ZoneId zoneId) {
      return $this.microsToDays(micros, zoneId);
   }

   default int microsToDays(final long micros, final ZoneId zoneId) {
      return this.localDateToDays(this.getLocalDateTime(micros, zoneId).toLocalDate());
   }

   // $FF: synthetic method
   static long daysToMicros$(final SparkDateTimeUtils $this, final int days, final ZoneId zoneId) {
      return $this.daysToMicros(days, zoneId);
   }

   default long daysToMicros(final int days, final ZoneId zoneId) {
      Instant instant = this.daysToLocalDate(days).atStartOfDay(zoneId).toInstant();
      return this.instantToMicros(instant);
   }

   // $FF: synthetic method
   static int fromJavaDate$(final SparkDateTimeUtils $this, final Date date) {
      return $this.fromJavaDate(date);
   }

   default int fromJavaDate(final Date date) {
      long millisUtc = date.getTime();
      long millisLocal = millisUtc + (long)TimeZone.getDefault().getOffset(millisUtc);
      int julianDays = Math.toIntExact(Math.floorDiv(millisLocal, 86400000L));
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianDays(julianDays);
   }

   String org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName();

   // $FF: synthetic method
   static MethodHandle org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle$(final SparkDateTimeUtils $this) {
      return $this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle();
   }

   default MethodHandle org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle() {
      MethodHandles.Lookup lookup = MethodHandles.lookup();
      Class classType = .MODULE$.classForName(this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName(), .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3());
      String methodName = "getOffsetsByWall";
      MethodType methodType = MethodType.methodType(Integer.TYPE, Long.TYPE, int[].class);
      return lookup.findVirtual(classType, methodName, methodType);
   }

   // $FF: synthetic method
   static Date toJavaDate$(final SparkDateTimeUtils $this, final int days) {
      return $this.toJavaDate(days);
   }

   default Date toJavaDate(final int days) {
      long localMillis;
      int var11;
      label26: {
         TimeZone var7;
         label28: {
            int rebasedDays = RebaseDateTime$.MODULE$.rebaseGregorianToJulianDays(days);
            localMillis = Math.multiplyExact((long)rebasedDays, 86400000L);
            var7 = TimeZone.getDefault();
            if (var7 != null) {
               String var10000 = var7.getClass().getName();
               String var9 = this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName();
               if (var10000 == null) {
                  if (var9 == null) {
                     break label28;
                  }
               } else if (var10000.equals(var9)) {
                  break label28;
               }
            }

            if (var7 == null) {
               throw new MatchError(var7);
            }

            var11 = var7.getOffset(localMillis - (long)var7.getRawOffset());
            break label26;
         }

         var11 = BoxesRunTime.unboxToInt(this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle().invoke(var7, localMillis, (Null)null));
      }

      int timeZoneOffset = var11;
      return new Date(localMillis - (long)timeZoneOffset);
   }

   // $FF: synthetic method
   static Timestamp toJavaTimestamp$(final SparkDateTimeUtils $this, final long micros) {
      return $this.toJavaTimestamp(micros);
   }

   default Timestamp toJavaTimestamp(final long micros) {
      return this.toJavaTimestampNoRebase(RebaseDateTime$.MODULE$.rebaseGregorianToJulianMicros(micros));
   }

   // $FF: synthetic method
   static Timestamp toJavaTimestamp$(final SparkDateTimeUtils $this, final String timeZoneId, final long micros) {
      return $this.toJavaTimestamp(timeZoneId, micros);
   }

   default Timestamp toJavaTimestamp(final String timeZoneId, final long micros) {
      return this.toJavaTimestampNoRebase(RebaseDateTime$.MODULE$.rebaseGregorianToJulianMicros(timeZoneId, micros));
   }

   // $FF: synthetic method
   static Timestamp toJavaTimestampNoRebase$(final SparkDateTimeUtils $this, final long micros) {
      return $this.toJavaTimestampNoRebase(micros);
   }

   default Timestamp toJavaTimestampNoRebase(final long micros) {
      long seconds = Math.floorDiv(micros, 1000000L);
      Timestamp ts = new Timestamp(seconds * 1000L);
      long nanos = (micros - seconds * 1000000L) * 1000L;
      ts.setNanos((int)nanos);
      return ts;
   }

   // $FF: synthetic method
   static long fromJavaTimestamp$(final SparkDateTimeUtils $this, final Timestamp t) {
      return $this.fromJavaTimestamp(t);
   }

   default long fromJavaTimestamp(final Timestamp t) {
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianMicros(this.fromJavaTimestampNoRebase(t));
   }

   // $FF: synthetic method
   static long fromJavaTimestamp$(final SparkDateTimeUtils $this, final String timeZoneId, final Timestamp t) {
      return $this.fromJavaTimestamp(timeZoneId, t);
   }

   default long fromJavaTimestamp(final String timeZoneId, final Timestamp t) {
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianMicros(timeZoneId, this.fromJavaTimestampNoRebase(t));
   }

   // $FF: synthetic method
   static long fromJavaTimestampNoRebase$(final SparkDateTimeUtils $this, final Timestamp t) {
      return $this.fromJavaTimestampNoRebase(t);
   }

   default long fromJavaTimestampNoRebase(final Timestamp t) {
      return this.millisToMicros(t.getTime()) + (long)t.getNanos() / 1000L % 1000L;
   }

   // $FF: synthetic method
   static Option stringToDate$(final SparkDateTimeUtils $this, final UTF8String s) {
      return $this.stringToDate(s);
   }

   default Option stringToDate(final UTF8String s) {
      if (s == null) {
         return scala.None..MODULE$;
      } else {
         int[] segments = new int[]{1, 1, 1};
         int sign = 1;
         int i = 0;
         int currentSegmentValue = 0;
         int currentSegmentDigits = 0;
         byte[] bytes = s.getBytes();
         int j = this.getTrimmedStart(bytes);
         int strEndTrimmed = this.getTrimmedEnd(j, bytes);
         if (j == strEndTrimmed) {
            return scala.None..MODULE$;
         } else {
            if (bytes[j] == 45 || bytes[j] == 43) {
               sign = bytes[j] == 45 ? -1 : 1;
               ++j;
            }

            for(; j < strEndTrimmed && i < 3 && bytes[j] != 32 && bytes[j] != 84; ++j) {
               byte b = bytes[j];
               if (i < 2 && b == 45) {
                  if (!isValidDigits$1(i, currentSegmentDigits)) {
                     return scala.None..MODULE$;
                  }

                  segments[i] = currentSegmentValue;
                  currentSegmentValue = 0;
                  currentSegmentDigits = 0;
                  ++i;
               } else {
                  int parsedValue = b - (byte)48;
                  if (parsedValue < 0 || parsedValue > 9) {
                     return scala.None..MODULE$;
                  }

                  currentSegmentValue = currentSegmentValue * 10 + parsedValue;
                  ++currentSegmentDigits;
               }
            }

            if (!isValidDigits$1(i, currentSegmentDigits)) {
               return scala.None..MODULE$;
            } else if (i < 2 && j < strEndTrimmed) {
               return scala.None..MODULE$;
            } else {
               segments[i] = currentSegmentValue;

               Object var10000;
               try {
                  LocalDate localDate = LocalDate.of(sign * segments[0], segments[1], segments[2]);
                  var10000 = new Some(BoxesRunTime.boxToInteger(this.localDateToDays(localDate)));
               } catch (Throwable var17) {
                  if (var17 != null) {
                     Option var16 = scala.util.control.NonFatal..MODULE$.unapply(var17);
                     if (!var16.isEmpty()) {
                        var10000 = scala.None..MODULE$;
                        return (Option)var10000;
                     }
                  }

                  throw var17;
               }

               return (Option)var10000;
            }
         }
      }
   }

   // $FF: synthetic method
   static int stringToDateAnsi$(final SparkDateTimeUtils $this, final UTF8String s, final QueryContext context) {
      return $this.stringToDateAnsi(s, context);
   }

   default int stringToDateAnsi(final UTF8String s, final QueryContext context) {
      return BoxesRunTime.unboxToInt(this.stringToDate(s).getOrElse(() -> {
         throw ExecutionErrors$.MODULE$.invalidInputInCastToDatetimeError(s, DateType$.MODULE$, context);
      }));
   }

   // $FF: synthetic method
   static QueryContext stringToDateAnsi$default$2$(final SparkDateTimeUtils $this) {
      return $this.stringToDateAnsi$default$2();
   }

   default QueryContext stringToDateAnsi$default$2() {
      return null;
   }

   // $FF: synthetic method
   static Tuple3 parseTimestampString$(final SparkDateTimeUtils $this, final UTF8String s) {
      return $this.parseTimestampString(s);
   }

   default Tuple3 parseTimestampString(final UTF8String s) {
      if (s == null) {
         return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
      } else {
         Option tz = scala.None..MODULE$;
         int[] segments = new int[]{1, 1, 1, 0, 0, 0, 0, 0, 0};
         int i = 0;
         int currentSegmentValue = 0;
         int currentSegmentDigits = 0;
         byte[] bytes = s.getBytes();
         int j = this.getTrimmedStart(bytes);
         int strEndTrimmed = this.getTrimmedEnd(j, bytes);
         if (j == strEndTrimmed) {
            return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
         } else {
            int digitsMilli = 0;
            boolean justTime = false;
            Option yearSign = scala.None..MODULE$;
            if (bytes[j] == 45 || bytes[j] == 43) {
               yearSign = bytes[j] == 45 ? new Some(BoxesRunTime.boxToInteger(-1)) : new Some(BoxesRunTime.boxToInteger(1));
               ++j;
            }

            for(; j < strEndTrimmed; ++j) {
               byte b = bytes[j];
               int parsedValue = b - (byte)48;
               if (parsedValue >= 0 && parsedValue <= 9) {
                  if (i == 6) {
                     ++digitsMilli;
                  }

                  if (i != 6 || currentSegmentDigits < 6) {
                     currentSegmentValue = currentSegmentValue * 10 + parsedValue;
                  }

                  ++currentSegmentDigits;
               } else if (j == 0 && b == 84) {
                  justTime = true;
                  i += 3;
               } else if (i < 2) {
                  if (b == 45) {
                     if (!isValidDigits$2(i, currentSegmentDigits)) {
                        return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                     }

                     segments[i] = currentSegmentValue;
                     currentSegmentValue = 0;
                     currentSegmentDigits = 0;
                     ++i;
                  } else {
                     if (i != 0 || b != 58 || !yearSign.isEmpty()) {
                        return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                     }

                     justTime = true;
                     if (!isValidDigits$2(3, currentSegmentDigits)) {
                        return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                     }

                     segments[3] = currentSegmentValue;
                     currentSegmentValue = 0;
                     currentSegmentDigits = 0;
                     i = 4;
                  }
               } else if (i == 2) {
                  if (b != 32 && b != 84) {
                     return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                  }

                  if (!isValidDigits$2(i, currentSegmentDigits)) {
                     return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                  }

                  segments[i] = currentSegmentValue;
                  currentSegmentValue = 0;
                  currentSegmentDigits = 0;
                  ++i;
               } else if (i != 3 && i != 4) {
                  if (i != 5 && i != 6) {
                     if (i >= segments.length || b != 58 && b != 32) {
                        return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                     }

                     if (!isValidDigits$2(i, currentSegmentDigits)) {
                        return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                     }

                     segments[i] = currentSegmentValue;
                     currentSegmentValue = 0;
                     currentSegmentDigits = 0;
                     ++i;
                  } else {
                     if (b == 46 && i == 5) {
                        if (!isValidDigits$2(i, currentSegmentDigits)) {
                           return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                        }

                        segments[i] = currentSegmentValue;
                        currentSegmentValue = 0;
                        currentSegmentDigits = 0;
                        ++i;
                     } else {
                        if (!isValidDigits$2(i, currentSegmentDigits)) {
                           return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                        }

                        segments[i] = currentSegmentValue;
                        currentSegmentValue = 0;
                        currentSegmentDigits = 0;
                        ++i;
                        tz = new Some(new String(bytes, j, strEndTrimmed - j));
                        j = strEndTrimmed - 1;
                     }

                     if (i == 6 && b != 46) {
                        ++i;
                     }
                  }
               } else {
                  if (b != 58) {
                     return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                  }

                  if (!isValidDigits$2(i, currentSegmentDigits)) {
                     return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
                  }

                  segments[i] = currentSegmentValue;
                  currentSegmentValue = 0;
                  currentSegmentDigits = 0;
                  ++i;
               }
            }

            if (!isValidDigits$2(i, currentSegmentDigits)) {
               return new Tuple3(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.None..MODULE$, BoxesRunTime.boxToBoolean(false));
            } else {
               for(segments[i] = currentSegmentValue; digitsMilli < 6; ++digitsMilli) {
                  segments[6] *= 10;
               }

               Option zoneId = tz.map((zoneName) -> this.getZoneId(zoneName.trim()));
               segments[0] *= BoxesRunTime.unboxToInt(yearSign.getOrElse((JFunction0.mcI.sp)() -> 1));
               return new Tuple3(segments, zoneId, BoxesRunTime.boxToBoolean(justTime));
            }
         }
      }
   }

   // $FF: synthetic method
   static Option stringToTimestamp$(final SparkDateTimeUtils $this, final UTF8String s, final ZoneId timeZoneId) {
      return $this.stringToTimestamp(s, timeZoneId);
   }

   default Option stringToTimestamp(final UTF8String s, final ZoneId timeZoneId) {
      Object var10000;
      try {
         Tuple3 var6 = this.parseTimestampString(s);
         if (var6 == null) {
            throw new MatchError(var6);
         }

         int[] segments = (int[])var6._1();
         Option parsedZoneId = (Option)var6._2();
         boolean justTime = BoxesRunTime.unboxToBoolean(var6._3());
         Tuple3 var5 = new Tuple3(segments, parsedZoneId, BoxesRunTime.boxToBoolean(justTime));
         int[] segments = (int[])var5._1();
         Option parsedZoneId = (Option)var5._2();
         boolean justTime = BoxesRunTime.unboxToBoolean(var5._3());
         if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.intArrayOps(segments))) {
            return scala.None..MODULE$;
         }

         ZoneId zoneId = (ZoneId)parsedZoneId.getOrElse(() -> timeZoneId);
         long nanoseconds = TimeUnit.MICROSECONDS.toNanos((long)segments[6]);
         LocalTime localTime = LocalTime.of(segments[3], segments[4], segments[5], (int)nanoseconds);
         LocalDate localDate = justTime ? LocalDate.now(zoneId) : LocalDate.of(segments[0], segments[1], segments[2]);
         LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
         ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
         Instant instant = Instant.from(zonedDateTime);
         var10000 = new Some(BoxesRunTime.boxToLong(this.instantToMicros(instant)));
      } catch (Throwable var24) {
         if (var24 != null) {
            Option var23 = scala.util.control.NonFatal..MODULE$.unapply(var24);
            if (!var23.isEmpty()) {
               var10000 = scala.None..MODULE$;
               return (Option)var10000;
            }
         }

         throw var24;
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   static long stringToTimestampAnsi$(final SparkDateTimeUtils $this, final UTF8String s, final ZoneId timeZoneId, final QueryContext context) {
      return $this.stringToTimestampAnsi(s, timeZoneId, context);
   }

   default long stringToTimestampAnsi(final UTF8String s, final ZoneId timeZoneId, final QueryContext context) {
      return BoxesRunTime.unboxToLong(this.stringToTimestamp(s, timeZoneId).getOrElse(() -> {
         throw ExecutionErrors$.MODULE$.invalidInputInCastToDatetimeError(s, TimestampType$.MODULE$, context);
      }));
   }

   // $FF: synthetic method
   static QueryContext stringToTimestampAnsi$default$3$(final SparkDateTimeUtils $this) {
      return $this.stringToTimestampAnsi$default$3();
   }

   default QueryContext stringToTimestampAnsi$default$3() {
      return null;
   }

   // $FF: synthetic method
   static Option stringToTimestampWithoutTimeZone$(final SparkDateTimeUtils $this, final UTF8String s, final boolean allowTimeZone) {
      return $this.stringToTimestampWithoutTimeZone(s, allowTimeZone);
   }

   default Option stringToTimestampWithoutTimeZone(final UTF8String s, final boolean allowTimeZone) {
      Object var10000;
      try {
         Tuple3 var6 = this.parseTimestampString(s);
         if (var6 == null) {
            throw new MatchError(var6);
         }

         int[] segments = (int[])var6._1();
         Option zoneIdOpt = (Option)var6._2();
         boolean justTime = BoxesRunTime.unboxToBoolean(var6._3());
         Tuple3 var5 = new Tuple3(segments, zoneIdOpt, BoxesRunTime.boxToBoolean(justTime));
         int[] segments = (int[])var5._1();
         Option zoneIdOpt = (Option)var5._2();
         boolean justTime = BoxesRunTime.unboxToBoolean(var5._3());
         if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.intArrayOps(segments)) || justTime || !allowTimeZone && zoneIdOpt.isDefined()) {
            return scala.None..MODULE$;
         }

         long nanoseconds = TimeUnit.MICROSECONDS.toNanos((long)segments[6]);
         LocalTime localTime = LocalTime.of(segments[3], segments[4], segments[5], (int)nanoseconds);
         LocalDate localDate = LocalDate.of(segments[0], segments[1], segments[2]);
         LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
         var10000 = new Some(BoxesRunTime.boxToLong(this.localDateTimeToMicros(localDateTime)));
      } catch (Throwable var21) {
         if (var21 == null) {
            throw var21;
         }

         Option var20 = scala.util.control.NonFatal..MODULE$.unapply(var21);
         if (var20.isEmpty()) {
            throw var21;
         }

         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private int getTrimmedStart(final byte[] bytes) {
      int start;
      for(start = 0; start < bytes.length && UTF8String.isWhitespaceOrISOControl(bytes[start]); ++start) {
      }

      return start;
   }

   private int getTrimmedEnd(final int start, final byte[] bytes) {
      int end;
      for(end = bytes.length - 1; end > start && UTF8String.isWhitespaceOrISOControl(bytes[end]); --end) {
      }

      return end + 1;
   }

   private static boolean isValidDigits$1(final int segment, final int digits) {
      int maxDigitsYear = 7;
      return segment == 0 && digits >= 4 && digits <= maxDigitsYear || segment != 0 && digits > 0 && digits <= 2;
   }

   private static boolean isValidDigits$2(final int segment, final int digits) {
      int maxDigitsYear = 6;
      return segment == 6 || segment == 0 && digits >= 4 && digits <= maxDigitsYear || segment == 7 && digits <= 2 || segment != 0 && segment != 6 && segment != 7 && digits > 0 && digits <= 2;
   }

   static void $init$(final SparkDateTimeUtils $this) {
      $this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$TimeZoneUTC_$eq(TimeZone.getTimeZone("UTC"));
      $this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$singleHourTz_$eq(Pattern.compile("(\\+|\\-)(\\d):"));
      $this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$singleMinuteTz_$eq(Pattern.compile("(\\+|\\-)(\\d\\d):(\\d)$"));
      $this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS_$eq(Math.floorDiv(Long.MIN_VALUE, 1000000L));
      $this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName_$eq("sun.util.calendar.ZoneInfo");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
