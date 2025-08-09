package org.apache.spark.streaming;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4Qa\u0005\u000b\u0001)qA\u0001b\t\u0001\u0003\u0006\u0004%\t!\n\u0005\tU\u0001\u0011\t\u0011)A\u0005M!A1\u0006\u0001BC\u0002\u0013\u0005Q\u0005\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003'\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u0015i\u0003\u0001\"\u00013\u0011\u0015Q\u0004\u0001\"\u0001<\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u0015\u0019\u0005\u0001\"\u0001E\u0011\u00151\u0005\u0001\"\u0001H\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u00151\u0006\u0001\"\u0011X\u000f\u0019\u0019G\u0003#\u0001\u0015I\u001a11\u0003\u0006E\u0001)\u0015DQ!\f\t\u0005\u0002\u0019DQa\u001a\t\u0005\u0002!\u0014\u0001\"\u00138uKJ4\u0018\r\u001c\u0006\u0003+Y\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u001c\"\u0001A\u000f\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g\u0003%\u0011WmZ5o)&lWm\u0001\u0001\u0016\u0003\u0019\u0002\"a\n\u0015\u000e\u0003QI!!\u000b\u000b\u0003\tQKW.Z\u0001\u000bE\u0016<\u0017N\u001c+j[\u0016\u0004\u0013aB3oIRKW.Z\u0001\tK:$G+[7fA\u00051A(\u001b8jiz\"2a\f\u00192!\t9\u0003\u0001C\u0003$\u000b\u0001\u0007a\u0005C\u0003,\u000b\u0001\u0007a\u0005F\u00020gaBQ\u0001\u000e\u0004A\u0002U\nqAY3hS:l5\u000f\u0005\u0002\u001fm%\u0011qg\b\u0002\u0005\u0019>tw\rC\u0003:\r\u0001\u0007Q'A\u0003f]\u0012l5/\u0001\u0005ekJ\fG/[8o)\u0005a\u0004CA\u0014>\u0013\tqDC\u0001\u0005EkJ\fG/[8o\u0003\u0015!\u0003\u000f\\;t)\ty\u0013\tC\u0003C\u0011\u0001\u0007A(\u0001\u0003uS6,\u0017A\u0002\u0013nS:,8\u000f\u0006\u00020\u000b\")!)\u0003a\u0001y\u0005)A\u0005\\3tgR\u0011\u0001j\u0013\t\u0003=%K!AS\u0010\u0003\u000f\t{w\u000e\\3b]\")AJ\u0003a\u0001_\u0005!A\u000f[1u\u0003!!C.Z:tI\u0015\fHC\u0001%P\u0011\u0015a5\u00021\u00010\u0003!!sM]3bi\u0016\u0014HC\u0001%S\u0011\u0015aE\u00021\u00010\u0003-!sM]3bi\u0016\u0014H%Z9\u0015\u0005!+\u0006\"\u0002'\u000e\u0001\u0004y\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003a\u0003\"!\u00171\u000f\u0005is\u0006CA. \u001b\u0005a&BA/%\u0003\u0019a$o\\8u}%\u0011qlH\u0001\u0007!J,G-\u001a4\n\u0005\u0005\u0014'AB*ue&twM\u0003\u0002`?\u0005A\u0011J\u001c;feZ\fG\u000e\u0005\u0002(!M\u0011\u0001#\b\u000b\u0002I\u0006y1-\u001e:sK:$\u0018J\u001c;feZ\fG\u000e\u0006\u00020S\")!H\u0005a\u0001y\u0001"
)
public class Interval {
   private final Time beginTime;
   private final Time endTime;

   public static Interval currentInterval(final Duration duration) {
      return Interval$.MODULE$.currentInterval(duration);
   }

   public Time beginTime() {
      return this.beginTime;
   }

   public Time endTime() {
      return this.endTime;
   }

   public Duration duration() {
      return this.endTime().$minus(this.beginTime());
   }

   public Interval $plus(final Duration time) {
      return new Interval(this.beginTime().$plus(time), this.endTime().$plus(time));
   }

   public Interval $minus(final Duration time) {
      return new Interval(this.beginTime().$minus(time), this.endTime().$minus(time));
   }

   public boolean $less(final Interval that) {
      Duration var10000 = this.duration();
      Duration var2 = that.duration();
      if (var10000 == null) {
         if (var2 != null) {
            throw new Exception("Comparing two intervals with different durations [" + this + ", " + that + "]");
         }
      } else if (!var10000.equals(var2)) {
         throw new Exception("Comparing two intervals with different durations [" + this + ", " + that + "]");
      }

      return this.endTime().$less(that.endTime());
   }

   public boolean $less$eq(final Interval that) {
      boolean var10000;
      if (!this.$less(that)) {
         label29: {
            if (this == null) {
               if (that == null) {
                  break label29;
               }
            } else if (this.equals(that)) {
               break label29;
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean $greater(final Interval that) {
      return !this.$less$eq(that);
   }

   public boolean $greater$eq(final Interval that) {
      return !this.$less(that);
   }

   public String toString() {
      Time var10000 = this.beginTime();
      return "[" + var10000 + ", " + this.endTime() + "]";
   }

   public Interval(final Time beginTime, final Time endTime) {
      this.beginTime = beginTime;
      this.endTime = endTime;
   }

   public Interval(final long beginMs, final long endMs) {
      this(new Time(beginMs), new Time(endMs));
   }
}
