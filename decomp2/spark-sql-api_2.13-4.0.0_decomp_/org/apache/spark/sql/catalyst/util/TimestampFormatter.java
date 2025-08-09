package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t]daB\u0011#!\u0003\r\tc\f\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u000f\u00021\t\u0001\u0013\u0005\u0006k\u0002!\tA\u001e\u0005\u0006}\u0002!\ta \u0005\b\u0003K\u0001A\u0011AA\u0014\u0011\u0019q\b\u0001\"\u0002\u00026!1\u0001\u000e\u0001D\u0001\u0003\u0003Ba\u0001\u001b\u0001\u0007\u0002\u0005\u001d\u0003B\u00025\u0001\r\u0003\t9\u0006\u0003\u0004i\u0001\u0011\u0005\u00111\r\u0005\b\u0003c\u0002a\u0011AA:\u000f\u001d\t9I\tE\u0001\u0003\u00133a!\t\u0012\t\u0002\u00055\u0005bBAM\u001b\u0011\u0005\u00111\u0014\u0005\n\u0003;k!\u0019!C\u0001\u0003?C\u0001\"a+\u000eA\u0003%\u0011\u0011\u0015\u0005\b\u0003[kA\u0011AAX\u0011\u001d\t\t,\u0004C\u0005\u0003gC\u0011\"a9\u000e#\u0003%I!!:\t\u0013\u0005mX\"%A\u0005\n\u0005u\b\"\u0003B\u0001\u001bE\u0005I\u0011\u0002B\u0002\u0011\u001d\u00119!\u0004C\u0001\u0005\u0013AqA!\u0006\u000e\t\u0003\u00119\u0002C\u0004\u0003\u00165!\tAa\t\t\u000f\tUQ\u0002\"\u0001\u00030!9!QC\u0007\u0005\u0002\te\u0002b\u0002B\u000b\u001b\u0011\u0005!Q\t\u0005\b\u0005+iA\u0011\u0001B)\u0011\u001d\u0011)\"\u0004C\u0001\u00053BqA!\u0006\u000e\t\u0003\u0011\u0019\u0007C\u0004\u0003h5!\tA!\u001b\t\u0013\t5T\"!A\u0005\n\t=$A\u0005+j[\u0016\u001cH/Y7q\r>\u0014X.\u0019;uKJT!a\t\u0013\u0002\tU$\u0018\u000e\u001c\u0006\u0003K\u0019\n\u0001bY1uC2L8\u000f\u001e\u0006\u0003O!\n1a]9m\u0015\tI#&A\u0003ta\u0006\u00148N\u0003\u0002,Y\u00051\u0011\r]1dQ\u0016T\u0011!L\u0001\u0004_J<7\u0001A\n\u0004\u0001A2\u0004CA\u00195\u001b\u0005\u0011$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012$AB!osJ+g\r\u0005\u00028\u007f9\u0011\u0001(\u0010\b\u0003sqj\u0011A\u000f\u0006\u0003w9\na\u0001\u0010:p_Rt\u0014\"A\u001a\n\u0005y\u0012\u0014a\u00029bG.\fw-Z\u0005\u0003\u0001\u0006\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0010\u001a\u0002\r\u0011Jg.\u001b;%)\u0005!\u0005CA\u0019F\u0013\t1%G\u0001\u0003V]&$\u0018!\u00029beN,GCA%M!\t\t$*\u0003\u0002Le\t!Aj\u001c8h\u0011\u0015i%\u00011\u0001O\u0003\u0005\u0019\bCA(T\u001d\t\u0001\u0016\u000b\u0005\u0002:e%\u0011!KM\u0001\u0007!J,G-\u001a4\n\u0005Q+&AB*ue&twM\u0003\u0002Se!\u001a!a\u00162\u0011\u0007EB&,\u0003\u0002Ze\t1A\u000f\u001b:poN\u0004\"a\u00171\u000e\u0003qS!!\u00180\u0002\tQ,\u0007\u0010\u001e\u0006\u0002?\u0006!!.\u0019<b\u0013\t\tGL\u0001\bQCJ\u001cX-\u0012=dKB$\u0018n\u001c8$\u0003iC3A\u00013n!\r\t\u0004,\u001a\t\u0003M.l\u0011a\u001a\u0006\u0003Q&\faAZ8s[\u0006$(B\u00016_\u0003\u0011!\u0018.\\3\n\u00051<'A\u0006#bi\u0016$\u0016.\\3QCJ\u001cX-\u0012=dKB$\u0018n\u001c8$\u0003\u0015D3AA8u!\r\t\u0004\f\u001d\t\u0003cJl\u0011![\u0005\u0003g&\u0014\u0011\u0003R1uKRKW.Z#yG\u0016\u0004H/[8oG\u0005\u0001\u0018!\u00049beN,w\n\u001d;j_:\fG\u000e\u0006\u0002xuB\u0019\u0011\u0007_%\n\u0005e\u0014$AB(qi&|g\u000eC\u0003N\u0007\u0001\u0007a\nK\u0002\u0004/\nD3a\u00013nQ\r\u0019q\u000e^\u0001\u0015a\u0006\u00148/Z,ji\"|W\u000f\u001e+j[\u0016TvN\\3\u0015\u000b%\u000b\t!a\u0001\t\u000b5#\u0001\u0019\u0001(\t\u000f\u0005\u0015A\u00011\u0001\u0002\b\u0005i\u0011\r\u001c7poRKW.\u001a.p]\u0016\u00042!MA\u0005\u0013\r\tYA\r\u0002\b\u0005>|G.Z1oQ\r!qK\u0019\u0015\u0004\t\u0011l\u0007f\u0001\u0003pi\"*A!!\u0006\u0002$A!\u0011\u0007WA\f!\u0011\tI\"a\b\u000e\u0005\u0005m!bAA\u000f=\u0006!A.\u00198h\u0013\u0011\t\t#a\u0007\u0003+%cG.Z4bYN#\u0018\r^3Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u0011qC\u0001\u001da\u0006\u00148/Z,ji\"|W\u000f\u001e+j[\u0016TvN\\3PaRLwN\\1m)\u00159\u0018\u0011FA\u0016\u0011\u0015iU\u00011\u0001O\u0011\u001d\t)!\u0002a\u0001\u0003\u000fA3!B,cQ\r)A-\u001c\u0015\u0004\u000b=$\b&B\u0003\u0002\u0016\u0005\rBcA%\u00028!)QJ\u0002a\u0001\u001d\"\u001aaa\u00162)\u0007\u0019!W\u000eK\u0002\u0007_RDSABA\u000b\u0003G!2ATA\"\u0011\u0019\t)e\u0002a\u0001\u0013\u0006\u0011Qo\u001d\u000b\u0004\u001d\u0006%\u0003bBA&\u0011\u0001\u0007\u0011QJ\u0001\u0003iN\u0004B!a\u0014\u0002T5\u0011\u0011\u0011\u000b\u0006\u0003OyKA!!\u0016\u0002R\tIA+[7fgR\fW\u000e\u001d\u000b\u0004\u001d\u0006e\u0003bBA.\u0013\u0001\u0007\u0011QL\u0001\bS:\u001cH/\u00198u!\r\t\u0018qL\u0005\u0004\u0003CJ'aB%ogR\fg\u000e\u001e\u000b\u0004\u001d\u0006\u0015\u0004bBA4\u0015\u0001\u0007\u0011\u0011N\u0001\u000eY>\u001c\u0017\r\u001c#bi\u0016$\u0016.\\3\u0011\u0007E\fY'C\u0002\u0002n%\u0014Q\u0002T8dC2$\u0015\r^3US6,\u0007&\u0002\u0006\u0002\u0016\u0005\r\u0012!\u0006<bY&$\u0017\r^3QCR$XM\u001d8TiJLgn\u001a\u000b\u0004\t\u0006U\u0004bBA<\u0017\u0001\u0007\u0011qA\u0001\fG\",7m\u001b'fO\u0006\u001c\u00170K\u0004\u0001\u0003w\ny(a!\n\u0007\u0005u$EA\rJg>Dd\u0007M\u0019US6,7\u000f^1na\u001a{'/\\1ui\u0016\u0014\u0018bAAAE\taB*Z4bGf4\u0015m\u001d;US6,7\u000f^1na\u001a{'/\\1ui\u0016\u0014\u0018bAACE\tqB*Z4bGf\u001c\u0016.\u001c9mKRKW.Z:uC6\u0004hi\u001c:nCR$XM]\u0001\u0013)&lWm\u001d;b[B4uN]7biR,'\u000fE\u0002\u0002\f6i\u0011AI\n\u0005\u001bA\ny\t\u0005\u0003\u0002\u0012\u0006]UBAAJ\u0015\r\t)JX\u0001\u0003S>L1\u0001QAJ\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u0011R\u0001\u000eI\u00164\u0017-\u001e7u\u0019>\u001c\u0017\r\\3\u0016\u0005\u0005\u0005\u0006\u0003BAR\u0003Ok!!!*\u000b\u0005\rr\u0016\u0002BAU\u0003K\u0013a\u0001T8dC2,\u0017A\u00043fM\u0006,H\u000e\u001e'pG\u0006dW\rI\u0001\u000fI\u00164\u0017-\u001e7u!\u0006$H/\u001a:o)\u0005q\u0015\u0001D4fi\u001a{'/\\1ui\u0016\u0014HCDA[\u0003o\u000bY,!2\u0002J\u0006m\u0017q\u001c\t\u0004\u0003\u0017\u0003\u0001B\u00025\u0013\u0001\u0004\tI\fE\u00022q:Cq!!0\u0013\u0001\u0004\ty,\u0001\u0004{_:,\u0017\n\u001a\t\u0004c\u0006\u0005\u0017bAAbS\n1!l\u001c8f\u0013\u0012D\u0011\"a2\u0013!\u0003\u0005\r!!)\u0002\r1|7-\u00197f\u0011%\tYM\u0005I\u0001\u0002\u0004\ti-\u0001\u0007mK\u001e\f7-\u001f$pe6\fG\u000f\u0005\u0003\u0002P\u0006Ug\u0002BAF\u0003#L1!a5#\u0003EaUmZ1ds\u0012\u000bG/\u001a$pe6\fGo]\u0005\u0005\u0003/\fIN\u0001\tMK\u001e\f7-\u001f#bi\u00164uN]7bi*\u0019\u00111\u001b\u0012\t\u000f\u0005u'\u00031\u0001\u0002\b\u0005I\u0011n\u001d)beNLgn\u001a\u0005\n\u0003C\u0014\u0002\u0013!a\u0001\u0003\u000f\tqBZ8s)&lWm\u001d;b[BtEKW\u0001\u0017O\u0016$hi\u001c:nCR$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011q\u001d\u0016\u0005\u0003C\u000bIo\u000b\u0002\u0002lB!\u0011Q^A|\u001b\t\tyO\u0003\u0003\u0002r\u0006M\u0018!C;oG\",7m[3e\u0015\r\t)PM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA}\u0003_\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003Y9W\r\u001e$pe6\fG\u000f^3sI\u0011,g-Y;mi\u0012\"TCAA\u0000U\u0011\ti-!;\u0002-\u001d,GOR8s[\u0006$H/\u001a:%I\u00164\u0017-\u001e7uIY*\"A!\u0002+\t\u0005\u001d\u0011\u0011^\u0001\u0013O\u0016$H*Z4bGf4uN]7biR,'\u000f\u0006\u0006\u00026\n-!q\u0002B\t\u0005'AaA!\u0004\u0017\u0001\u0004q\u0015a\u00029biR,'O\u001c\u0005\b\u0003{3\u0002\u0019AA`\u0011\u001d\t9M\u0006a\u0001\u0003CCq!a3\u0017\u0001\u0004\ti-A\u0003baBd\u0017\u0010\u0006\u0007\u00026\ne!1\u0004B\u000f\u0005?\u0011\t\u0003\u0003\u0004i/\u0001\u0007\u0011\u0011\u0018\u0005\b\u0003{;\u0002\u0019AA`\u0011\u001d\t9m\u0006a\u0001\u0003CCq!a3\u0018\u0001\u0004\ti\rC\u0004\u0002^^\u0001\r!a\u0002\u0015\u0019\u0005U&Q\u0005B\u0014\u0005S\u0011YC!\f\t\u000b!D\u0002\u0019\u0001(\t\u000f\u0005u\u0006\u00041\u0001\u0002@\"9\u0011q\u0019\rA\u0002\u0005\u0005\u0006bBAf1\u0001\u0007\u0011Q\u001a\u0005\b\u0003;D\u0002\u0019AA\u0004))\t)L!\r\u00034\tU\"q\u0007\u0005\u0006Qf\u0001\rA\u0014\u0005\b\u0003{K\u0002\u0019AA`\u0011\u001d\tY-\u0007a\u0001\u0003\u001bDq!!8\u001a\u0001\u0004\t9\u0001\u0006\u0007\u00026\nm\"Q\bB \u0005\u0003\u0012\u0019\u0005\u0003\u0004i5\u0001\u0007\u0011\u0011\u0018\u0005\b\u0003{S\u0002\u0019AA`\u0011\u001d\tYM\u0007a\u0001\u0003\u001bDq!!8\u001b\u0001\u0004\t9\u0001C\u0004\u0002bj\u0001\r!a\u0002\u0015\u0019\u0005U&q\tB%\u0005\u0017\u0012iEa\u0014\t\u000b!\\\u0002\u0019\u0001(\t\u000f\u0005u6\u00041\u0001\u0002@\"9\u00111Z\u000eA\u0002\u00055\u0007bBAo7\u0001\u0007\u0011q\u0001\u0005\b\u0003C\\\u0002\u0019AA\u0004)!\t)La\u0015\u0003V\t]\u0003\"\u00025\u001d\u0001\u0004q\u0005bBA_9\u0001\u0007\u0011q\u0018\u0005\b\u0003;d\u0002\u0019AA\u0004))\t)La\u0017\u0003^\t}#\u0011\r\u0005\u0006Qv\u0001\rA\u0014\u0005\b\u0003{k\u0002\u0019AA`\u0011\u001d\ti.\ba\u0001\u0003\u000fAq!!9\u001e\u0001\u0004\t9\u0001\u0006\u0003\u00026\n\u0015\u0004bBA_=\u0001\u0007\u0011qX\u0001\u0015O\u0016$hI]1di&|gNR8s[\u0006$H/\u001a:\u0015\t\u0005U&1\u000e\u0005\b\u0003{{\u0002\u0019AA`\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\t\b\u0005\u0003\u0002\u001a\tM\u0014\u0002\u0002B;\u00037\u0011aa\u00142kK\u000e$\b"
)
public interface TimestampFormatter extends Serializable {
   static TimestampFormatter getFractionFormatter(final ZoneId zoneId) {
      return TimestampFormatter$.MODULE$.getFractionFormatter(zoneId);
   }

   static TimestampFormatter apply(final ZoneId zoneId) {
      return TimestampFormatter$.MODULE$.apply(zoneId);
   }

   static TimestampFormatter apply(final String format, final ZoneId zoneId, final boolean isParsing, final boolean forTimestampNTZ) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, isParsing, forTimestampNTZ);
   }

   static TimestampFormatter apply(final String format, final ZoneId zoneId, final boolean isParsing) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, isParsing);
   }

   static TimestampFormatter apply(final String format, final ZoneId zoneId, final Enumeration.Value legacyFormat, final boolean isParsing, final boolean forTimestampNTZ) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, legacyFormat, isParsing, forTimestampNTZ);
   }

   static TimestampFormatter apply(final Option format, final ZoneId zoneId, final Enumeration.Value legacyFormat, final boolean isParsing, final boolean forTimestampNTZ) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, legacyFormat, isParsing, forTimestampNTZ);
   }

   static TimestampFormatter apply(final String format, final ZoneId zoneId, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, legacyFormat, isParsing);
   }

   static TimestampFormatter apply(final String format, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, locale, legacyFormat, isParsing);
   }

   static TimestampFormatter apply(final Option format, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return TimestampFormatter$.MODULE$.apply(format, zoneId, locale, legacyFormat, isParsing);
   }

   static TimestampFormatter getLegacyFormatter(final String pattern, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat) {
      return TimestampFormatter$.MODULE$.getLegacyFormatter(pattern, zoneId, locale, legacyFormat);
   }

   static String defaultPattern() {
      return TimestampFormatter$.MODULE$.defaultPattern();
   }

   static Locale defaultLocale() {
      return TimestampFormatter$.MODULE$.defaultLocale();
   }

   long parse(final String s) throws ParseException, DateTimeParseException, DateTimeException;

   // $FF: synthetic method
   static Option parseOptional$(final TimestampFormatter $this, final String s) {
      return $this.parseOptional(s);
   }

   default Option parseOptional(final String s) throws ParseException, DateTimeParseException, DateTimeException {
      Object var10000;
      try {
         var10000 = new Some(BoxesRunTime.boxToLong(this.parse(s)));
      } catch (Exception var2) {
         var10000 = .MODULE$;
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   static long parseWithoutTimeZone$(final TimestampFormatter $this, final String s, final boolean allowTimeZone) {
      return $this.parseWithoutTimeZone(s, allowTimeZone);
   }

   default long parseWithoutTimeZone(final String s, final boolean allowTimeZone) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      throw org.apache.spark.SparkException..MODULE$.internalError("The method `parseWithoutTimeZone(s: String, allowTimeZone: Boolean)` should be implemented in the formatter of timestamp without time zone");
   }

   // $FF: synthetic method
   static Option parseWithoutTimeZoneOptional$(final TimestampFormatter $this, final String s, final boolean allowTimeZone) {
      return $this.parseWithoutTimeZoneOptional(s, allowTimeZone);
   }

   default Option parseWithoutTimeZoneOptional(final String s, final boolean allowTimeZone) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      Object var10000;
      try {
         var10000 = new Some(BoxesRunTime.boxToLong(this.parseWithoutTimeZone(s, allowTimeZone)));
      } catch (Exception var3) {
         var10000 = .MODULE$;
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   static long parseWithoutTimeZone$(final TimestampFormatter $this, final String s) {
      return $this.parseWithoutTimeZone(s);
   }

   default long parseWithoutTimeZone(final String s) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      return this.parseWithoutTimeZone(s, true);
   }

   String format(final long us);

   String format(final Timestamp ts);

   String format(final Instant instant);

   // $FF: synthetic method
   static String format$(final TimestampFormatter $this, final LocalDateTime localDateTime) {
      return $this.format(localDateTime);
   }

   default String format(final LocalDateTime localDateTime) throws IllegalStateException {
      throw org.apache.spark.SparkException..MODULE$.internalError("The method `format(localDateTime: LocalDateTime)` should be implemented in the formatter of timestamp without time zone");
   }

   void validatePatternString(final boolean checkLegacy);

   static void $init$(final TimestampFormatter $this) {
   }
}
