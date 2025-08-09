package breeze.storage;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014qAC\u0006\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0019\u0005a\u0004C\u0003K\u0001\u0019E1\nC\u0003P\u0001\u0019\u00051\nC\u0003Q\u0001\u0011\u00051\nC\u0003R\u0001\u0019\u0005!\u000bC\u0003V\u0001\u0019\u0005a\u000bC\u0003Y\u0001\u0019\u0005\u0011\fC\u0003_\u0001\u0019\u0005qLA\u0004Ti>\u0014\u0018mZ3\u000b\u00051i\u0011aB:u_J\fw-\u001a\u0006\u0002\u001d\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u0012IM\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0002CA\n\u001c\u0013\taBC\u0001\u0003V]&$\u0018\u0001\u00023bi\u0006,\u0012a\b\t\u0004'\u0001\u0012\u0013BA\u0011\u0015\u0005\u0015\t%O]1z!\t\u0019C\u0005\u0004\u0001\u0005\u0013\u0015\u0002\u0001\u0015!A\u0001\u0006\u00041#!\u0001,\u0012\u0005\u001dR\u0003CA\n)\u0013\tICCA\u0004O_RD\u0017N\\4\u0011\u0005MY\u0013B\u0001\u0017\u0015\u0005\r\te.\u001f\u0015\u0007I9\n4\bQ#\u0011\u0005My\u0013B\u0001\u0019\u0015\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u00124'\u000e\u001b\u000f\u0005M\u0019\u0014B\u0001\u001b\u0015\u0003\u0019!u.\u001e2mKF\"AE\u000e\u001e\u0016\u001d\t9$(D\u00019\u0015\tIt\"\u0001\u0004=e>|GOP\u0005\u0002+E*1\u0005P\u001f@}9\u00111#P\u0005\u0003}Q\t1!\u00138uc\u0011!cGO\u000b2\u000b\r\n%\tR\"\u000f\u0005M\u0011\u0015BA\"\u0015\u0003\u00151En\\1uc\u0011!cGO\u000b2\u000b\r2u)\u0013%\u000f\u0005M9\u0015B\u0001%\u0015\u0003\u0011auN\\42\t\u00112$(F\u0001\u0005g&TX-F\u0001M!\t\u0019R*\u0003\u0002O)\t\u0019\u0011J\u001c;\u0002\u0015\u0005\u001cG/\u001b<f'&TX-\u0001\u0007ji\u0016\u0014\u0018M\u00197f'&TX-A\u0004wC2,X-\u0011;\u0015\u0005\t\u001a\u0006\"\u0002+\u0007\u0001\u0004a\u0015!A5\u0002\u000f%tG-\u001a=BiR\u0011Aj\u0016\u0005\u0006)\u001e\u0001\r\u0001T\u0001\tSN\f5\r^5wKR\u0011!,\u0018\t\u0003'mK!\u0001\u0018\u000b\u0003\u000f\t{w\u000e\\3b]\")A\u000b\u0003a\u0001\u0019\u0006I\u0012\r\u001c7WSNLG/\u00192mK&sG-[2fg\u0006\u001bG/\u001b<f+\u0005Q\u0006"
)
public interface Storage {
   Object data();

   int size();

   int activeSize();

   // $FF: synthetic method
   static int iterableSize$(final Storage $this) {
      return $this.iterableSize();
   }

   default int iterableSize() {
      return this.activeSize();
   }

   Object valueAt(final int i);

   int indexAt(final int i);

   boolean isActive(final int i);

   boolean allVisitableIndicesActive();

   // $FF: synthetic method
   static double[] data$mcD$sp$(final Storage $this) {
      return $this.data$mcD$sp();
   }

   default double[] data$mcD$sp() {
      return (double[])this.data();
   }

   // $FF: synthetic method
   static float[] data$mcF$sp$(final Storage $this) {
      return $this.data$mcF$sp();
   }

   default float[] data$mcF$sp() {
      return (float[])this.data();
   }

   // $FF: synthetic method
   static int[] data$mcI$sp$(final Storage $this) {
      return $this.data$mcI$sp();
   }

   default int[] data$mcI$sp() {
      return (int[])this.data();
   }

   // $FF: synthetic method
   static long[] data$mcJ$sp$(final Storage $this) {
      return $this.data$mcJ$sp();
   }

   default long[] data$mcJ$sp() {
      return (long[])this.data();
   }

   // $FF: synthetic method
   static double valueAt$mcD$sp$(final Storage $this, final int i) {
      return $this.valueAt$mcD$sp(i);
   }

   default double valueAt$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.valueAt(i));
   }

   // $FF: synthetic method
   static float valueAt$mcF$sp$(final Storage $this, final int i) {
      return $this.valueAt$mcF$sp(i);
   }

   default float valueAt$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.valueAt(i));
   }

   // $FF: synthetic method
   static int valueAt$mcI$sp$(final Storage $this, final int i) {
      return $this.valueAt$mcI$sp(i);
   }

   default int valueAt$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.valueAt(i));
   }

   // $FF: synthetic method
   static long valueAt$mcJ$sp$(final Storage $this, final int i) {
      return $this.valueAt$mcJ$sp(i);
   }

   default long valueAt$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.valueAt(i));
   }

   static void $init$(final Storage $this) {
   }
}
