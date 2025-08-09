package breeze.math;

import scala.math.package.;

public final class LogDouble$ {
   public static final LogDouble$ MODULE$ = new LogDouble$();

   public LogDouble.DoubleExtra DoubleExtra(final double d) {
      return new LogDouble.DoubleExtra(d);
   }

   public double logDoubleToDouble(final LogDouble d) {
      return d.value();
   }

   public LogDouble log(final LogDouble d) {
      return new LogDouble(.MODULE$.log(d.logValue()));
   }

   public LogDouble exp(final LogDouble d) {
      return new LogDouble(d.value());
   }

   public LogDouble pow(final LogDouble d, final double p) {
      return new LogDouble(d.logValue() * p);
   }

   public LogDouble pow(final LogDouble d, final LogDouble p) {
      return new LogDouble(d.logValue() * p.value());
   }

   private LogDouble$() {
   }
}
