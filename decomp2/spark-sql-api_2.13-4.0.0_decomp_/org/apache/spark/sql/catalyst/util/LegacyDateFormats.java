package org.apache.spark.sql.catalyst.util;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:Qa\u0003\u0007\t\u0002e1Qa\u0007\u0007\t\u0002qAQaI\u0001\u0005\u0002\u0011*A!J\u0001\u0001M!9!&\u0001b\u0001\n\u0003Y\u0003B\u0002\u0017\u0002A\u0003%a\u0005C\u0004.\u0003\t\u0007I\u0011A\u0016\t\r9\n\u0001\u0015!\u0003'\u0011\u001dy\u0013A1A\u0005\u0002-Ba\u0001M\u0001!\u0002\u00131\u0003bB\u0019\u0002\u0003\u0003%IAM\u0001\u0012\u0019\u0016<\u0017mY=ECR,gi\u001c:nCR\u001c(BA\u0007\u000f\u0003\u0011)H/\u001b7\u000b\u0005=\u0001\u0012\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005E\u0011\u0012aA:rY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005i\tQ\"\u0001\u0007\u0003#1+w-Y2z\t\u0006$XMR8s[\u0006$8o\u0005\u0002\u0002;A\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\tYQI\\;nKJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q\t\u0011D\u0001\tMK\u001e\f7-\u001f#bi\u00164uN]7biB\u0011q\u0005K\u0007\u0002\u0003%\u0011\u0011&\t\u0002\u0006-\u0006dW/Z\u0001\u0011\r\u0006\u001bFk\u0018#B)\u0016{fi\u0014*N\u0003R+\u0012AJ\u0001\u0012\r\u0006\u001bFk\u0018#B)\u0016{fi\u0014*N\u0003R\u0003\u0013AE*J\u001bBcUi\u0018#B)\u0016{fi\u0014*N\u0003R\u000b1cU%N!2+u\fR!U\u000b~3uJU'B)\u0002\n!\u0004T#O\u0013\u0016sEkX*J\u001bBcUi\u0018#B)\u0016{fi\u0014*N\u0003R\u000b1\u0004T#O\u0013\u0016sEkX*J\u001bBcUi\u0018#B)\u0016{fi\u0014*N\u0003R\u0003\u0013\u0001D<sSR,'+\u001a9mC\u000e,G#A\u001a\u0011\u0005QJT\"A\u001b\u000b\u0005Y:\u0014\u0001\u00027b]\u001eT\u0011\u0001O\u0001\u0005U\u00064\u0018-\u0003\u0002;k\t1qJ\u00196fGR\u0004"
)
public final class LegacyDateFormats {
   public static Enumeration.Value LENIENT_SIMPLE_DATE_FORMAT() {
      return LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
   }

   public static Enumeration.Value SIMPLE_DATE_FORMAT() {
      return LegacyDateFormats$.MODULE$.SIMPLE_DATE_FORMAT();
   }

   public static Enumeration.Value FAST_DATE_FORMAT() {
      return LegacyDateFormats$.MODULE$.FAST_DATE_FORMAT();
   }

   public static Enumeration.ValueSet ValueSet() {
      return LegacyDateFormats$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return LegacyDateFormats$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return LegacyDateFormats$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return LegacyDateFormats$.MODULE$.apply(x);
   }

   public static int maxId() {
      return LegacyDateFormats$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return LegacyDateFormats$.MODULE$.values();
   }

   public static String toString() {
      return LegacyDateFormats$.MODULE$.toString();
   }
}
