package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import scala.Enumeration;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015eaB\f\u0019!\u0003\r\n#\n\u0005\u0006q\u00011\t!\u000f\u0005\u0006\u000f\u00021\t\u0001\u0013\u0005\u0006\u000f\u00021\ta\u0013\u0005\u0006\u000f\u00021\t!\u0016\u0005\u0006=\u00021\taX\u0004\u0006QbA\t!\u001b\u0004\u0006/aA\ta\u001b\u0005\u0006c\u001e!\tA\u001d\u0005\bg\u001e\u0011\r\u0011\"\u0001u\u0011\u0019Ax\u0001)A\u0005k\"9\u0011p\u0002b\u0001\n\u0003Q\bBB>\bA\u0003%q\bC\u0003}\u000f\u0011%Q\u0010C\u0005\u0002(\u001d\t\n\u0011\"\u0003\u0002*!I\u0011qH\u0004\u0012\u0002\u0013%\u0011\u0011\t\u0005\b\u0003\u000b:A\u0011AA$\u0011\u001d\t\tf\u0002C\u0001\u0003'Bq!!\u0015\b\t\u0003\ti\u0006C\u0004\u0002R\u001d!\t!a\u001a\t\u0013\u00055t!%A\u0005\u0002\u0005=\u0004bBA)\u000f\u0011\u0005\u00111\u000f\u0005\n\u0003k:\u0011\u0011!C\u0005\u0003o\u0012Q\u0002R1uK\u001a{'/\\1ui\u0016\u0014(BA\r\u001b\u0003\u0011)H/\u001b7\u000b\u0005ma\u0012\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005uq\u0012aA:rY*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00011C\u0006\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005E\"\u0013A\u0002\u001fs_>$h(C\u0001*\u0013\t!\u0004&A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b)\u0003\u0015\u0001\u0018M]:f)\tQT\b\u0005\u0002(w%\u0011A\b\u000b\u0002\u0004\u0013:$\b\"\u0002 \u0002\u0001\u0004y\u0014!A:\u0011\u0005\u0001#eBA!C!\ty\u0003&\u0003\u0002DQ\u00051\u0001K]3eK\u001aL!!\u0012$\u0003\rM#(/\u001b8h\u0015\t\u0019\u0005&\u0001\u0004g_Jl\u0017\r\u001e\u000b\u0003\u007f%CQA\u0013\u0002A\u0002i\nA\u0001Z1zgR\u0011q\b\u0014\u0005\u0006\u001b\u000e\u0001\rAT\u0001\u0005I\u0006$X\r\u0005\u0002P'6\t\u0001K\u0003\u0002\u001a#*\t!+\u0001\u0003kCZ\f\u0017B\u0001+Q\u0005\u0011!\u0015\r^3\u0015\u0005}2\u0006\"B,\u0005\u0001\u0004A\u0016!\u00037pG\u0006dG)\u0019;f!\tIF,D\u0001[\u0015\tY\u0016+\u0001\u0003uS6,\u0017BA/[\u0005%aunY1m\t\u0006$X-A\u000bwC2LG-\u0019;f!\u0006$H/\u001a:o'R\u0014\u0018N\\4\u0015\u0003\u0001\u0004\"aJ1\n\u0005\tD#\u0001B+oSRL3\u0001\u00013g\u0013\t)\u0007D\u0001\u000bJg>Dd\u0007M\u0019ECR,gi\u001c:nCR$XM]\u0005\u0003Ob\u00111\u0003T3hC\u000eLH)\u0019;f\r>\u0014X.\u0019;uKJ\fQ\u0002R1uK\u001a{'/\\1ui\u0016\u0014\bC\u00016\b\u001b\u0005A2cA\u0004'YB\u0011Q\u000e]\u0007\u0002]*\u0011q.U\u0001\u0003S>L!A\u000e8\u0002\rqJg.\u001b;?)\u0005I\u0017!\u00043fM\u0006,H\u000e\u001e'pG\u0006dW-F\u0001v!\tye/\u0003\u0002x!\n1Aj\\2bY\u0016\fa\u0002Z3gCVdG\u000fT8dC2,\u0007%\u0001\beK\u001a\fW\u000f\u001c;QCR$XM\u001d8\u0016\u0003}\nq\u0002Z3gCVdG\u000fU1ui\u0016\u0014h\u000eI\u0001\rO\u0016$hi\u001c:nCR$XM\u001d\u000b\t}~\f9!a\u0003\u0002\u001eA\u0011!\u000e\u0001\u0005\u0007\u000f6\u0001\r!!\u0001\u0011\t\u001d\n\u0019aP\u0005\u0004\u0003\u000bA#AB(qi&|g\u000e\u0003\u0005\u0002\n5\u0001\n\u00111\u0001v\u0003\u0019awnY1mK\"I\u0011QB\u0007\u0011\u0002\u0003\u0007\u0011qB\u0001\rY\u0016<\u0017mY=G_Jl\u0017\r\u001e\t\u0005\u0003#\t9BD\u0002k\u0003'I1!!\u0006\u0019\u0003EaUmZ1ds\u0012\u000bG/\u001a$pe6\fGo]\u0005\u0005\u00033\tYB\u0001\tMK\u001e\f7-\u001f#bi\u00164uN]7bi*\u0019\u0011Q\u0003\r\t\u000f\u0005}Q\u00021\u0001\u0002\"\u0005I\u0011n\u001d)beNLgn\u001a\t\u0004O\u0005\r\u0012bAA\u0013Q\t9!i\\8mK\u0006t\u0017AF4fi\u001a{'/\\1ui\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005-\"fA;\u0002.-\u0012\u0011q\u0006\t\u0005\u0003c\tY$\u0004\u0002\u00024)!\u0011QGA\u001c\u0003%)hn\u00195fG.,GMC\u0002\u0002:!\n!\"\u00198o_R\fG/[8o\u0013\u0011\ti$a\r\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\fhKR4uN]7biR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t\t\u0019E\u000b\u0003\u0002\u0010\u00055\u0012AE4fi2+w-Y2z\r>\u0014X.\u0019;uKJ$rA`A%\u0003\u001b\ny\u0005\u0003\u0004\u0002LA\u0001\raP\u0001\ba\u0006$H/\u001a:o\u0011\u0019\tI\u0001\u0005a\u0001k\"9\u0011Q\u0002\tA\u0002\u0005=\u0011!B1qa2LH#\u0003@\u0002V\u0005]\u0013\u0011LA.\u0011\u00199\u0015\u00031\u0001\u0002\u0002!1\u0011\u0011B\tA\u0002UDq!!\u0004\u0012\u0001\u0004\ty\u0001C\u0004\u0002 E\u0001\r!!\t\u0015\u0013y\fy&!\u0019\u0002d\u0005\u0015\u0004\"B$\u0013\u0001\u0004y\u0004BBA\u0005%\u0001\u0007Q\u000fC\u0004\u0002\u000eI\u0001\r!a\u0004\t\u000f\u0005}!\u00031\u0001\u0002\"Q)a0!\u001b\u0002l!)qi\u0005a\u0001\u007f!I\u0011qD\n\u0011\u0002\u0003\u0007\u0011\u0011E\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\u000f\u0016\u0005\u0003C\ti\u0003F\u0001\u007f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\b\u0005\u0003\u0002|\u0005\u0005UBAA?\u0015\r\ty(U\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0004\u0006u$AB(cU\u0016\u001cG\u000f"
)
public interface DateFormatter extends Serializable {
   static DateFormatter apply() {
      return DateFormatter$.MODULE$.apply();
   }

   static boolean apply$default$2() {
      return DateFormatter$.MODULE$.apply$default$2();
   }

   static DateFormatter apply(final String format, final boolean isParsing) {
      return DateFormatter$.MODULE$.apply(format, isParsing);
   }

   static DateFormatter apply(final String format, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return DateFormatter$.MODULE$.apply(format, locale, legacyFormat, isParsing);
   }

   static DateFormatter apply(final Option format, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return DateFormatter$.MODULE$.apply(format, locale, legacyFormat, isParsing);
   }

   static DateFormatter getLegacyFormatter(final String pattern, final Locale locale, final Enumeration.Value legacyFormat) {
      return DateFormatter$.MODULE$.getLegacyFormatter(pattern, locale, legacyFormat);
   }

   static String defaultPattern() {
      return DateFormatter$.MODULE$.defaultPattern();
   }

   static Locale defaultLocale() {
      return DateFormatter$.MODULE$.defaultLocale();
   }

   int parse(final String s);

   String format(final int days);

   String format(final Date date);

   String format(final LocalDate localDate);

   void validatePatternString();
}
