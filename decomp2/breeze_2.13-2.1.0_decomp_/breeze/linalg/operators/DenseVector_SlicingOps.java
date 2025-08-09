package breeze.linalg.operators;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.package$;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.RangeExtender$;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0017\u0001\u0011\u0005q\u0003C\u0003\u001c\u0001\u0011\rAD\u0001\fEK:\u001cXMV3di>\u0014xl\u00157jG&twm\u00149t\u0015\t)a!A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011q\u0001C\u0001\u0007Y&t\u0017\r\\4\u000b\u0003%\taA\u0019:fKj,7\u0001A\n\u0004\u00011\u0011\u0002CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\r\u0005\u0002\u0014)5\tA!\u0003\u0002\u0016\t\tiA+\u001a8t_Jdun\u001e)sS>\fa\u0001J5oSR$C#\u0001\r\u0011\u00055I\u0012B\u0001\u000e\u000f\u0005\u0011)f.\u001b;\u0002/\r\fgn\u00157jG\u0016|FIV0SC:<WmX3r?\u00123VCA\u000f++\u0005q\u0002#B\u0010#IA#S\"\u0001\u0011\u000b\u0005\u00052\u0011aB:vaB|'\u000f^\u0005\u0003G\u0001\u0012\u0001bQ1o'2L7-\u001a\t\u0004K\u0019BS\"\u0001\u0004\n\u0005\u001d2!a\u0003#f]N,g+Z2u_J\u0004\"!\u000b\u0016\r\u0001\u0011I1F\u0001Q\u0001\u0002\u0003\u0015\r\u0001\f\u0002\u0002-F\u0011Q\u0006\r\t\u0003\u001b9J!a\f\b\u0003\u000f9{G\u000f[5oOB\u0011Q\"M\u0005\u0003e9\u00111!\u00118zQ\u0019QCgN!G\u0017B\u0011Q\"N\u0005\u0003m9\u00111b\u001d9fG&\fG.\u001b>fIF*1\u0005O\u001d<u9\u0011Q\"O\u0005\u0003u9\t1!\u00138uc\u0011!C\bQ\b\u000f\u0005u\u0002U\"\u0001 \u000b\u0005}R\u0011A\u0002\u001fs_>$h(C\u0001\u0010c\u0015\u0019#iQ#E\u001d\ti1)\u0003\u0002E\u001d\u0005)a\t\\8biF\"A\u0005\u0010!\u0010c\u0015\u0019s\t\u0013&J\u001d\ti\u0001*\u0003\u0002J\u001d\u0005!Aj\u001c8hc\u0011!C\bQ\b2\u000b\rbUj\u0014(\u000f\u00055i\u0015B\u0001(\u000f\u0003\u0019!u.\u001e2mKF\"A\u0005\u0010!\u0010!\t\tFK\u0004\u0002=%&\u00111KD\u0001\ba\u0006\u001c7.Y4f\u0013\t)fKA\u0003SC:<WM\u0003\u0002T\u001d\u0001"
)
public interface DenseVector_SlicingOps extends TensorLowPrio {
   // $FF: synthetic method
   static CanSlice canSlice_DV_Range_eq_DV$(final DenseVector_SlicingOps $this) {
      return $this.canSlice_DV_Range_eq_DV();
   }

   default CanSlice canSlice_DV_Range_eq_DV() {
      return new CanSlice() {
         public DenseVector apply(final DenseVector v, final Range re) {
            Range range = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(re), v.length());
            boolean cond$macro$1 = range.isEmpty() || range.last() < v.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: range.isEmpty.||(range.last.<(v.length))");
            } else {
               boolean cond$macro$2 = range.isEmpty() || range.start() >= 0;
               if (!cond$macro$2) {
                  throw new IllegalArgumentException("requirement failed: range.isEmpty.||(range.start.>=(0))");
               } else {
                  return DenseVector$.MODULE$.create(v.data(), v.offset() + v.stride() * range.start(), v.stride() * range.step(), range.length());
               }
            }
         }
      };
   }

   static void $init$(final DenseVector_SlicingOps $this) {
   }
}
