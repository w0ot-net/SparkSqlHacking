package org.apache.spark.sql.internal;

import org.apache.spark.sql.types.AtomicType;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011<a\u0001F\u000b\t\u0002]ybAB\u0011\u0016\u0011\u00039\"\u0005C\u0003-\u0003\u0011\u0005a\u0006C\u00030\u0003\u0011\u0005\u0003\u0007C\u00035\u0003\u0011\u0005\u0003\u0007C\u00036\u0003\u0011\u0005c\u0007C\u0003;\u0003\u0011\u0005\u0003\u0007C\u0003<\u0003\u0011\u0005\u0003\u0007C\u0003=\u0003\u0011\u0005\u0003\u0007C\u0003>\u0003\u0011\u0005\u0003\u0007C\u0003?\u0003\u0011\u0005s\bC\u0003G\u0003\u0011\u0005\u0003\u0007C\u0003H\u0003\u0011\u0005\u0003\u0007C\u0003I\u0003\u0011\u0005\u0003\u0007C\u0003J\u0003\u0011\u0005\u0003\u0007C\u0003K\u0003\u0011\u00053\nC\u0003X\u0003\u0011\u0005\u0003\fC\u0003b\u0003\u0011\u0005c\u0007C\u0003c\u0003\u0011\u0005\u0003\u0007C\u0003d\u0003\u0011\u0005\u0003'A\tEK\u001a\fW\u000f\u001c;Tc2\f\u0005/[\"p]\u001aT!AF\f\u0002\u0011%tG/\u001a:oC2T!\u0001G\r\u0002\u0007M\fHN\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h!\t\u0001\u0013!D\u0001\u0016\u0005E!UMZ1vYR\u001c\u0016\u000f\\!qS\u000e{gNZ\n\u0004\u0003\rJ\u0003C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#AB!osJ+g\r\u0005\u0002!U%\u00111&\u0006\u0002\u000b'Fd\u0017\t]5D_:4\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003}\t1\"\u00198tS\u0016s\u0017M\u00197fIV\t\u0011\u0007\u0005\u0002%e%\u00111'\n\u0002\b\u0005>|G.Z1o\u0003U\u0019\u0017m]3TK:\u001c\u0018\u000e^5wK\u0006s\u0017\r\\=tSN\f\u0011#\\1y)>\u001cFO]5oO\u001aKW\r\u001c3t+\u00059\u0004C\u0001\u00139\u0013\tITEA\u0002J]R\f\u0001d]3u\u001fB\u001c\bK]3dK\u0012,gnY3F]\u001a|'oY3e\u0003})\u0007\u0010]8oK:$H*\u001b;fe\u0006d\u0017i\u001d#fG&l\u0017\r\\#oC\ndW\rZ\u0001\u0018K:4wN]2f%\u0016\u001cXM\u001d<fI.+\u0017p^8sIN\fq\u0003Z8vE2,\u0017+^8uK\u0012LE-\u001a8uS\u001aLWM]:\u0002\u001bQLW.Z:uC6\u0004H+\u001f9f+\u0005\u0001\u0005CA!E\u001b\u0005\u0011%BA\"\u0018\u0003\u0015!\u0018\u0010]3t\u0013\t)%I\u0001\u0006Bi>l\u0017n\u0019+za\u0016\f!%\u00197m_^tUmZ1uSZ,7kY1mK>3G)Z2j[\u0006dWI\\1cY\u0016$\u0017aE2iCJ4\u0016M]2iCJ\f5o\u0015;sS:<\u0017a\u00079sKN,'O^3DQ\u0006\u0014h+\u0019:dQ\u0006\u0014H+\u001f9f\u0013:4w.A\feCR,G/[7f\u0015\u00064\u0018\rO!qS\u0016s\u0017M\u00197fI\u0006!2/Z:tS>tGj\\2bYRKW.\u001a.p]\u0016,\u0012\u0001\u0014\t\u0003\u001bRs!A\u0014*\u0011\u0005=+S\"\u0001)\u000b\u0005Ek\u0013A\u0002\u001fs_>$h(\u0003\u0002TK\u00051\u0001K]3eK\u001aL!!\u0016,\u0003\rM#(/\u001b8h\u0015\t\u0019V%\u0001\fmK\u001e\f7-\u001f+j[\u0016\u0004\u0016M]:feB{G.[2z+\u0005I\u0006C\u0001.^\u001d\t\u00013,\u0003\u0002]+\u0005!B*Z4bGf\u0014U\r[1wS>\u0014\bk\u001c7jGfL!AX0\u0003\u000bY\u000bG.^3\n\u0005\u0001,#aC#ok6,'/\u0019;j_:\fQd\u001d;bG.$&/Y2fg&sG)\u0019;b\rJ\fW.Z\"p]R,\u0007\u0010^\u0001\u001dI\u0006$\u0018M\u0012:b[\u0016\fV/\u001a:z\u0007>tG/\u001a=u\u000b:\f'\r\\3e\u0003maWmZ1ds\u0006cGn\\<V]RL\b/\u001a3TG\u0006d\u0017-\u0016#Gg\u0002"
)
public final class DefaultSqlApiConf {
   public static boolean legacyAllowUntypedScalaUDFs() {
      return DefaultSqlApiConf$.MODULE$.legacyAllowUntypedScalaUDFs();
   }

   public static boolean dataFrameQueryContextEnabled() {
      return DefaultSqlApiConf$.MODULE$.dataFrameQueryContextEnabled();
   }

   public static int stackTracesInDataFrameContext() {
      return DefaultSqlApiConf$.MODULE$.stackTracesInDataFrameContext();
   }

   public static Enumeration.Value legacyTimeParserPolicy() {
      return DefaultSqlApiConf$.MODULE$.legacyTimeParserPolicy();
   }

   public static String sessionLocalTimeZone() {
      return DefaultSqlApiConf$.MODULE$.sessionLocalTimeZone();
   }

   public static boolean datetimeJava8ApiEnabled() {
      return DefaultSqlApiConf$.MODULE$.datetimeJava8ApiEnabled();
   }

   public static boolean preserveCharVarcharTypeInfo() {
      return DefaultSqlApiConf$.MODULE$.preserveCharVarcharTypeInfo();
   }

   public static boolean charVarcharAsString() {
      return DefaultSqlApiConf$.MODULE$.charVarcharAsString();
   }

   public static boolean allowNegativeScaleOfDecimalEnabled() {
      return DefaultSqlApiConf$.MODULE$.allowNegativeScaleOfDecimalEnabled();
   }

   public static AtomicType timestampType() {
      return DefaultSqlApiConf$.MODULE$.timestampType();
   }

   public static boolean doubleQuotedIdentifiers() {
      return DefaultSqlApiConf$.MODULE$.doubleQuotedIdentifiers();
   }

   public static boolean enforceReservedKeywords() {
      return DefaultSqlApiConf$.MODULE$.enforceReservedKeywords();
   }

   public static boolean exponentLiteralAsDecimalEnabled() {
      return DefaultSqlApiConf$.MODULE$.exponentLiteralAsDecimalEnabled();
   }

   public static boolean setOpsPrecedenceEnforced() {
      return DefaultSqlApiConf$.MODULE$.setOpsPrecedenceEnforced();
   }

   public static int maxToStringFields() {
      return DefaultSqlApiConf$.MODULE$.maxToStringFields();
   }

   public static boolean caseSensitiveAnalysis() {
      return DefaultSqlApiConf$.MODULE$.caseSensitiveAnalysis();
   }

   public static boolean ansiEnabled() {
      return DefaultSqlApiConf$.MODULE$.ansiEnabled();
   }
}
