package breeze.stats;

import breeze.linalg.support.CanTraverseValues;
import java.lang.invoke.SerializedLambda;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u000594AAD\b\u0005)!A1\b\u0001B\u0001B\u0003%\u0001\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0004B\u0001\t\u0007I\u0011\u0001\"\t\r9\u0003\u0001\u0015!\u0003D\u0011\u001dy\u0005\u00011A\u0005\u0002ACq!\u0015\u0001A\u0002\u0013\u0005!\u000b\u0003\u0004Y\u0001\u0001\u0006Ka\u0013\u0005\b3\u0002\u0001\r\u0011\"\u0001[\u0011\u001dY\u0006\u00011A\u0005\u0002qCaA\u0018\u0001!B\u0013\u0001\u0004\"B0\u0001\t\u0003\u0001\u0007\"B2\u0001\t\u0003!\u0007\"B5\u0001\t\u0013Q'aC'pI\u00164\u0016n]5u_JT!\u0001E\t\u0002\u000bM$\u0018\r^:\u000b\u0003I\taA\u0019:fKj,7\u0001A\u000b\u0003+I\u001a2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0019Q$\f\u0019\u000f\u0005yQcBA\u0010(\u001d\t\u0001SE\u0004\u0002\"I5\t!E\u0003\u0002$'\u00051AH]8pizJ\u0011AE\u0005\u0003ME\ta\u0001\\5oC2<\u0017B\u0001\u0015*\u0003\u001d\u0019X\u000f\u001d9peRT!AJ\t\n\u0005-b\u0013!E\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fg*\u0011\u0001&K\u0005\u0003]=\u0012QBV1mk\u0016\u001ch+[:ji>\u0014(BA\u0016-!\t\t$\u0007\u0004\u0001\u0005\u000bM\u0002!\u0019\u0001\u001b\u0003\rM\u001b\u0017\r\\1s#\t)\u0004\b\u0005\u0002\u0018m%\u0011q\u0007\u0007\u0002\b\u001d>$\b.\u001b8h!\t9\u0012(\u0003\u0002;1\t\u0019\u0011I\\=\u0002\u0019%t\u0017\u000e^5bYZ\u000bG.^3\u0002\rqJg.\u001b;?)\tq\u0004\tE\u0002@\u0001Aj\u0011a\u0004\u0005\u0006w\t\u0001\r\u0001M\u0001\u0010MJ,\u0017/^3oGf\u001cu.\u001e8ugV\t1\t\u0005\u0003E\u0013BZU\"A#\u000b\u0005\u0019;\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003\u0011b\t!bY8mY\u0016\u001cG/[8o\u0013\tQUIA\u0002NCB\u0004\"a\u0006'\n\u00055C\"aA%oi\u0006\u0001bM]3rk\u0016t7-_\"pk:$8\u000fI\u0001\r[\u0006DhI]3rk\u0016t7-_\u000b\u0002\u0017\u0006\u0001R.\u0019=Ge\u0016\fX/\u001a8ds~#S-\u001d\u000b\u0003'Z\u0003\"a\u0006+\n\u0005UC\"\u0001B+oSRDqa\u0016\u0004\u0002\u0002\u0003\u00071*A\u0002yIE\nQ\"\\1y\rJ,\u0017/^3oGf\u0004\u0013a\u0003:v]:LgnZ'pI\u0016,\u0012\u0001M\u0001\u0010eVtg.\u001b8h\u001b>$Wm\u0018\u0013fcR\u00111+\u0018\u0005\b/&\t\t\u00111\u00011\u00031\u0011XO\u001c8j]\u001elu\u000eZ3!\u0003\u00151\u0018n]5u)\t\u0019\u0016\rC\u0003c\u0017\u0001\u0007\u0001'A\u0003wC2,X-A\u0003{KJ|7\u000fF\u0002TK\u001eDQA\u001a\u0007A\u0002-\u000b\u0001B\\;n5\u0016\u0014xn\u001d\u0005\u0006Q2\u0001\r\u0001M\u0001\nu\u0016\u0014xNV1mk\u0016\f\u0011C]3d_J$wjY2veJ,gnY3t)\r\u00196\u000e\u001c\u0005\u0006E6\u0001\r\u0001\r\u0005\u0006[6\u0001\raS\u0001\u0006G>,h\u000e\u001e"
)
public class ModeVisitor implements CanTraverseValues.ValuesVisitor {
   private final Map frequencyCounts;
   private int maxFrequency;
   private Object runningMode;

   public void visit$mcZ$sp(final boolean a) {
      CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
   }

   public void visit$mcB$sp(final byte a) {
      CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
   }

   public void visit$mcC$sp(final char a) {
      CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
   }

   public void visit$mcD$sp(final double a) {
      CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
   }

   public void visit$mcF$sp(final float a) {
      CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
   }

   public void visit$mcI$sp(final int a) {
      CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
   }

   public void visit$mcJ$sp(final long a) {
      CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
   }

   public void visit$mcS$sp(final short a) {
      CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
   }

   public void visit$mcV$sp(final BoxedUnit a) {
      CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
   }

   public void visitArray(final Object arr) {
      CanTraverseValues.ValuesVisitor.visitArray$(this, arr);
   }

   public void visitArray$mcZ$sp(final boolean[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
   }

   public void visitArray$mcB$sp(final byte[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
   }

   public void visitArray$mcC$sp(final char[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
   }

   public void visitArray$mcD$sp(final double[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
   }

   public void visitArray$mcF$sp(final float[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
   }

   public void visitArray$mcI$sp(final int[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
   }

   public void visitArray$mcJ$sp(final long[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
   }

   public void visitArray$mcS$sp(final short[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
   }

   public void visitArray$mcV$sp(final BoxedUnit[] arr) {
      CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
   }

   public void visitArray(final Object arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$(this, arr, offset, length, stride);
   }

   public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
   }

   public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
   }

   public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcC$sp(final int numZero, final char zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcD$sp(final int numZero, final double zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcF$sp(final int numZero, final float zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcI$sp(final int numZero, final int zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcS$sp(final int numZero, final short zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
   }

   public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
      CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
   }

   public Map frequencyCounts() {
      return this.frequencyCounts;
   }

   public int maxFrequency() {
      return this.maxFrequency;
   }

   public void maxFrequency_$eq(final int x$1) {
      this.maxFrequency = x$1;
   }

   public Object runningMode() {
      return this.runningMode;
   }

   public void runningMode_$eq(final Object x$1) {
      this.runningMode = x$1;
   }

   public void visit(final Object value) {
      this.recordOccurrences(value, 1);
   }

   public void zeros(final int numZeros, final Object zeroValue) {
      this.recordOccurrences(zeroValue, numZeros);
   }

   private void recordOccurrences(final Object value, final int count) {
      this.frequencyCounts().update(value, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.frequencyCounts().getOrElse(value, (JFunction0.mcI.sp)() -> 0)) + count));
      if (BoxesRunTime.unboxToInt(this.frequencyCounts().apply(value)) > this.maxFrequency()) {
         this.maxFrequency_$eq(BoxesRunTime.unboxToInt(this.frequencyCounts().apply(value)));
         this.runningMode_$eq(value);
      }

   }

   public ModeVisitor(final Object initialValue) {
      CanTraverseValues.ValuesVisitor.$init$(this);
      this.frequencyCounts = (Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.maxFrequency = 0;
      this.runningMode = initialValue;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
