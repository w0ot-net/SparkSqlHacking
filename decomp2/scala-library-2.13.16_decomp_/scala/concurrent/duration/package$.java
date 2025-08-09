package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.Tuple2;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public final TimeUnit DAYS() {
      return TimeUnit.DAYS;
   }

   public final TimeUnit HOURS() {
      return TimeUnit.HOURS;
   }

   public final TimeUnit MICROSECONDS() {
      return TimeUnit.MICROSECONDS;
   }

   public final TimeUnit MILLISECONDS() {
      return TimeUnit.MILLISECONDS;
   }

   public final TimeUnit MINUTES() {
      return TimeUnit.MINUTES;
   }

   public final TimeUnit NANOSECONDS() {
      return TimeUnit.NANOSECONDS;
   }

   public final TimeUnit SECONDS() {
      return TimeUnit.SECONDS;
   }

   public Duration pairIntToDuration(final Tuple2 p) {
      Duration$ var10000 = Duration$.MODULE$;
      long var5 = (long)p._1$mcI$sp();
      TimeUnit apply_unit = (TimeUnit)p._2();
      long apply_length = var5;
      return new FiniteDuration(apply_length, apply_unit);
   }

   public FiniteDuration pairLongToDuration(final Tuple2 p) {
      Duration$ var10000 = Duration$.MODULE$;
      long var5 = p._1$mcJ$sp();
      TimeUnit apply_unit = (TimeUnit)p._2();
      long apply_length = var5;
      return new FiniteDuration(apply_length, apply_unit);
   }

   public Tuple2 durationToPair(final Duration d) {
      return new Tuple2(d.length(), d.unit());
   }

   public final int DurationInt(final int n) {
      return n;
   }

   public final long DurationLong(final long n) {
      return n;
   }

   public final double DurationDouble(final double d) {
      return d;
   }

   public final int IntMult(final int i) {
      return i;
   }

   public final long LongMult(final long i) {
      return i;
   }

   public final double DoubleMult(final double f) {
      return f;
   }

   private package$() {
   }
}
