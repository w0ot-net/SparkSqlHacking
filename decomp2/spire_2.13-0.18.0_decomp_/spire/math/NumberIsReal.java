package spire.math;

import scala.reflect.ScalaSignature;
import spire.algebra.IsRational;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0011\"\u0004\u0005\u0006C\u0001!\ta\t\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0006]\u0001!\ta\f\u0005\u0006e\u0001!\ta\r\u0005\u0006k\u0001!\tA\u000e\u0005\u0006q\u0001!\t!\u000f\u0005\u0006}\u0001!\ta\u0010\u0002\r\u001dVl'-\u001a:JgJ+\u0017\r\u001c\u0006\u0003\u0015-\tA!\\1uQ*\tA\"A\u0003ta&\u0014Xm\u0005\u0003\u0001\u001dQq\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u00161ii\u0011A\u0006\u0006\u0003/-\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001a-\tQ\u0011j\u001d*bi&|g.\u00197\u0011\u0005maR\"A\u0005\n\u0005uI!A\u0002(v[\n,'\u000f\u0005\u0002\u001c?%\u0011\u0001%\u0003\u0002\u0018\u001dVl'-\u001a:UeVt7-\u0019;fI\u0012Kg/[:j_:\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002IA\u0011q\"J\u0005\u0003MA\u0011A!\u00168ji\u0006AAo\u001c#pk\ndW\r\u0006\u0002*YA\u0011qBK\u0005\u0003WA\u0011a\u0001R8vE2,\u0007\"B\u0017\u0003\u0001\u0004Q\u0012!\u0001=\u0002\t\r,\u0017\u000e\u001c\u000b\u00035ABQ!M\u0002A\u0002i\t\u0011!Y\u0001\u0006M2|wN\u001d\u000b\u00035QBQ!\r\u0003A\u0002i\tQA]8v]\u0012$\"AG\u001c\t\u000bE*\u0001\u0019\u0001\u000e\u0002\u000f%\u001cx\u000b[8mKR\u0011!(\u0010\t\u0003\u001fmJ!\u0001\u0010\t\u0003\u000f\t{w\u000e\\3b]\")\u0011G\u0002a\u00015\u0005QAo\u001c*bi&|g.\u00197\u0015\u0005\u0001\u001b\u0005CA\u000eB\u0013\t\u0011\u0015B\u0001\u0005SCRLwN\\1m\u0011\u0015\tt\u00011\u0001\u001b\u0001"
)
public interface NumberIsReal extends IsRational, NumberTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final NumberIsReal $this, final Number x) {
      return $this.toDouble(x);
   }

   default double toDouble(final Number x) {
      return x.toDouble();
   }

   // $FF: synthetic method
   static Number ceil$(final NumberIsReal $this, final Number a) {
      return $this.ceil(a);
   }

   default Number ceil(final Number a) {
      return a.ceil();
   }

   // $FF: synthetic method
   static Number floor$(final NumberIsReal $this, final Number a) {
      return $this.floor(a);
   }

   default Number floor(final Number a) {
      return a.floor();
   }

   // $FF: synthetic method
   static Number round$(final NumberIsReal $this, final Number a) {
      return $this.round(a);
   }

   default Number round(final Number a) {
      return a.round();
   }

   // $FF: synthetic method
   static boolean isWhole$(final NumberIsReal $this, final Number a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final Number a) {
      return a.isWhole();
   }

   // $FF: synthetic method
   static Rational toRational$(final NumberIsReal $this, final Number a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Number a) {
      return a.toRational();
   }

   static void $init$(final NumberIsReal $this) {
   }
}
