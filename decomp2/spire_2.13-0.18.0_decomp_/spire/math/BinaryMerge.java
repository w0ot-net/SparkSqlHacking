package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud!\u0002\u000f\u001e\u0003\u0003\u0011\u0003\"B\u0015\u0001\t\u0003Q\u0003BB\u0017\u0001A\u00135a\u0006C\u00039\u0001\u0019\u0005\u0011\bC\u0003>\u0001\u0019\u0005a\bC\u0003E\u0001\u0019\u0005Q\tC\u0003L\u0001\u0019\u0005A\nC\u0003Q\u0001\u0011\u0005\u0011kB\u0003W;!\u0005qKB\u0003\u001d;!\u0005\u0001\fC\u0003*\u0013\u0011\u0005A\fC\u0003^\u0013\u0011\u0005aL\u0002\u0004\u0002&%!\u0011q\u0005\u0005\u000b\u0003?a!\u0011!Q\u0001\n\u0005-\u0002BCA\u0012\u0019\t\u0005\t\u0015!\u0003\u0002,!Q\u00111\u0007\u0007\u0003\u0002\u0003\u0006Y!!\u000e\t\u0015\u0005]BB!A!\u0002\u0017\tI\u0004\u0003\u0004*\u0019\u0011\u0005\u00111\b\u0005\u0007q1!\t!a\u0013\t\r\u0011cA\u0011AA)\u0011\u0019YE\u0002\"\u0001\u0002Z!1Q\b\u0004C\u0001\u0003CB\u0011\"a\u001a\r\u0005\u0004%\t!!\u001b\t\u0011\u0005-D\u0002)A\u0005\u0003WA\u0011\"!\u001c\r\u0001\u0004%\t!a\u001c\t\u0013\u0005ED\u00021A\u0005\u0002\u0005M\u0004bBA=\u0019\u0001\u0006Ka\f\u0005\b\u0003wbA\u0011AA5\u0005-\u0011\u0015N\\1ss6+'oZ3\u000b\u0005yy\u0012\u0001B7bi\"T\u0011\u0001I\u0001\u0006gBL'/Z\u0002\u0001'\t\u00011\u0005\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0002\"\u0001\f\u0001\u000e\u0003u\tQBY5oCJL8+Z1sG\"\u0014E\u0003B\u00183iY\u0002\"\u0001\n\u0019\n\u0005E*#aA%oi\")1G\u0001a\u0001_\u0005\u0011\u0011-\u001b\u0005\u0006k\t\u0001\raL\u0001\u0003EBBQa\u000e\u0002A\u0002=\n!AY\u0019\u0002\u000f\r|W\u000e]1sKR\u0019qFO\u001e\t\u000bM\u001a\u0001\u0019A\u0018\t\u000bq\u001a\u0001\u0019A\u0018\u0002\u0005\tL\u0017!C2pY2L7/[8o)\ry$i\u0011\t\u0003I\u0001K!!Q\u0013\u0003\tUs\u0017\u000e\u001e\u0005\u0006g\u0011\u0001\ra\f\u0005\u0006y\u0011\u0001\raL\u0001\u0006MJ|W.\u0011\u000b\u0005\u007f\u0019C%\nC\u0003H\u000b\u0001\u0007q&\u0001\u0002ba!)\u0011*\u0002a\u0001_\u0005\u0011\u0011-\r\u0005\u0006y\u0015\u0001\raL\u0001\u0006MJ|WN\u0011\u000b\u0005\u007f5su\nC\u00034\r\u0001\u0007q\u0006C\u00036\r\u0001\u0007q\u0006C\u00038\r\u0001\u0007q&\u0001\u0004nKJ<W\r\r\u000b\u0006\u007fI\u001bF+\u0016\u0005\u0006\u000f\u001e\u0001\ra\f\u0005\u0006\u0013\u001e\u0001\ra\f\u0005\u0006k\u001d\u0001\ra\f\u0005\u0006o\u001d\u0001\raL\u0001\f\u0005&t\u0017M]=NKJ<W\r\u0005\u0002-\u0013M\u0019\u0011bI-\u0011\u00051R\u0016BA.\u001e\u0005\u0015iUM]4f)\u00059\u0016!B7fe\u001e,WCA0g)\u0015\u0001\u0017QDA\u0011)\u0011\t7/a\u0003\u0011\u0007\u0011\u0012G-\u0003\u0002dK\t)\u0011I\u001d:bsB\u0011QM\u001a\u0007\u0001\t%97\u0002)A\u0001\u0002\u000b\u0007\u0001NA\u0001U#\tIG\u000e\u0005\u0002%U&\u00111.\n\u0002\b\u001d>$\b.\u001b8h!\t!S.\u0003\u0002oK\t\u0019\u0011I\\=)\u0005\u0019\u0004\bC\u0001\u0013r\u0013\t\u0011XEA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007b\u0002;\f\u0003\u0003\u0005\u001d!^\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003\u0002<\u0002\u0006\u0011t!a^@\u000f\u0005alhBA=}\u001b\u0005Q(BA>\"\u0003\u0019a$o\\8u}%\t\u0001%\u0003\u0002\u007f?\u00059\u0011\r\\4fEJ\f\u0017\u0002BA\u0001\u0003\u0007\tq\u0001]1dW\u0006<WM\u0003\u0002\u007f?%!\u0011qAA\u0005\u0005\u0015y%\u000fZ3s\u0015\u0011\t\t!a\u0001\t\u0013\u000551\"!AA\u0004\u0005=\u0011AC3wS\u0012,gnY3%iA)\u0011\u0011CA\fI:!\u00111CA\u000b\u001b\u0005y\u0012bAA\u0001?%!\u0011\u0011DA\u000e\u0005!\u0019E.Y:t)\u0006<'bAA\u0001?!1\u0011qD\u0006A\u0002\u0005\f\u0011!\u0019\u0005\u0007\u0003GY\u0001\u0019A1\u0002\u0003\t\u0014\u0001#\u0011:sCf\u0014\u0015N\\1ss6+'oZ3\u0016\t\u0005%\u0012qF\n\u0003\u0019-\u0002B\u0001\n2\u0002.A\u0019Q-a\f\u0005\u0013\u001dd\u0001\u0015!A\u0001\u0006\u0004A\u0007fAA\u0018a\u0006\tq\u000eE\u0003w\u0003\u000b\ti#A\u0001d!\u0019\t\t\"a\u0006\u0002.Q1\u0011QHA$\u0003\u0013\"b!a\u0010\u0002D\u0005\u0015\u0003#BA!\u0019\u00055R\"A\u0005\t\u000f\u0005M\u0012\u0003q\u0001\u00026!9\u0011qG\tA\u0004\u0005e\u0002bBA\u0010#\u0001\u0007\u00111\u0006\u0005\b\u0003G\t\u0002\u0019AA\u0016)\u0015y\u0013QJA(\u0011\u0015\u0019$\u00031\u00010\u0011\u0015a$\u00031\u00010)\u001dy\u00141KA+\u0003/BQaR\nA\u0002=BQ!S\nA\u0002=BQ\u0001P\nA\u0002=\"raPA.\u0003;\ny\u0006C\u00034)\u0001\u0007q\u0006C\u00036)\u0001\u0007q\u0006C\u00038)\u0001\u0007q\u0006F\u0003@\u0003G\n)\u0007C\u00034+\u0001\u0007q\u0006C\u0003=+\u0001\u0007q&A\u0001s+\t\tY#\u0001\u0002sA\u0005\u0011!/[\u000b\u0002_\u00051!/[0%KF$2aPA;\u0011!\t9(GA\u0001\u0002\u0004y\u0013a\u0001=%c\u0005\u0019!/\u001b\u0011\u0002\rI,7/\u001e7u\u0001"
)
public abstract class BinaryMerge {
   public static Object merge(final Object a, final Object b, final Order evidence$3, final ClassTag evidence$4) {
      return BinaryMerge$.MODULE$.merge(a, b, evidence$3, evidence$4);
   }

   private final int binarySearchB(final int ai, final int b0, final int b1) {
      return this.binarySearch0$1(b0, b1 - 1, ai);
   }

   public abstract int compare(final int ai, final int bi);

   public abstract void collision(final int ai, final int bi);

   public abstract void fromA(final int a0, final int a1, final int bi);

   public abstract void fromB(final int ai, final int b0, final int b1);

   public void merge0(final int a0, final int a1, final int b0, final int b1) {
      if (a0 == a1) {
         if (b0 != b1) {
            this.fromB(a0, b0, b1);
         }
      } else if (b0 == b1) {
         this.fromA(a0, a1, b0);
      } else {
         int am = (a0 + a1) / 2;
         int res = this.binarySearchB(am, b0, b1);
         if (res >= 0) {
            this.merge0(a0, am, b0, res);
            this.collision(am, res);
            this.merge0(am + 1, a1, res + 1, b1);
         } else {
            int bm = -res - 1;
            this.merge0(a0, am, b0, bm);
            this.fromA(am, am + 1, bm);
            this.merge0(am + 1, a1, bm, b1);
         }
      }

   }

   private final int binarySearch0$1(final int low, final int high, final int ai$1) {
      while(true) {
         int var10000;
         if (low <= high) {
            int mid = low + high >>> 1;
            int c = this.compare(ai$1, mid);
            if (c > 0) {
               var10000 = mid + 1;
               high = high;
               low = var10000;
               continue;
            }

            if (c < 0) {
               high = mid - 1;
               low = low;
               continue;
            }

            var10000 = mid;
         } else {
            var10000 = -(low + 1);
         }

         return var10000;
      }
   }

   private static class ArrayBinaryMerge extends BinaryMerge {
      public final Object a;
      public final Object b;
      public final Order o;
      public final Object r;
      public int spire$math$BinaryMerge$ArrayBinaryMerge$$ri;

      public int compare(final int ai, final int bi) {
         return this.o.compare(.MODULE$.array_apply(this.a, ai), .MODULE$.array_apply(this.b, bi));
      }

      public void fromA(final int a0, final int a1, final int bi) {
         System.arraycopy(this.a, a0, this.r(), this.ri(), a1 - a0);
         this.ri_$eq(this.ri() + (a1 - a0));
      }

      public void fromB(final int ai, final int b0, final int b1) {
         System.arraycopy(this.b, b0, this.r(), this.ri(), b1 - b0);
         this.ri_$eq(this.ri() + (b1 - b0));
      }

      public void collision(final int ai, final int bi) {
         .MODULE$.array_update(this.r(), this.ri(), .MODULE$.array_apply(this.a, ai));
         this.ri_$eq(this.ri() + 1);
         .MODULE$.array_update(this.r(), this.ri(), .MODULE$.array_apply(this.b, bi));
         this.ri_$eq(this.ri() + 1);
      }

      public Object r() {
         return this.r;
      }

      public int ri() {
         return this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri;
      }

      public void ri_$eq(final int x$1) {
         this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = x$1;
      }

      public Object result() {
         return this.r();
      }

      public boolean[] r$mcZ$sp() {
         return (boolean[])this.r();
      }

      public byte[] r$mcB$sp() {
         return (byte[])this.r();
      }

      public char[] r$mcC$sp() {
         return (char[])this.r();
      }

      public double[] r$mcD$sp() {
         return (double[])this.r();
      }

      public float[] r$mcF$sp() {
         return (float[])this.r();
      }

      public int[] r$mcI$sp() {
         return (int[])this.r();
      }

      public long[] r$mcJ$sp() {
         return (long[])this.r();
      }

      public short[] r$mcS$sp() {
         return (short[])this.r();
      }

      public BoxedUnit[] r$mcV$sp() {
         return (BoxedUnit[])this.r();
      }

      public boolean[] result$mcZ$sp() {
         return (boolean[])this.result();
      }

      public byte[] result$mcB$sp() {
         return (byte[])this.result();
      }

      public char[] result$mcC$sp() {
         return (char[])this.result();
      }

      public double[] result$mcD$sp() {
         return (double[])this.result();
      }

      public float[] result$mcF$sp() {
         return (float[])this.result();
      }

      public int[] result$mcI$sp() {
         return (int[])this.result();
      }

      public long[] result$mcJ$sp() {
         return (long[])this.result();
      }

      public short[] result$mcS$sp() {
         return (short[])this.result();
      }

      public BoxedUnit[] result$mcV$sp() {
         return (BoxedUnit[])this.result();
      }

      public boolean specInstance$() {
         return false;
      }

      public ArrayBinaryMerge(final Object a, final Object b, final Order o, final ClassTag c) {
         this.a = a;
         this.b = b;
         this.o = o;
         if (!this.specInstance$()) {
            this.r = scala.Array..MODULE$.ofDim(.MODULE$.array_length(this.a) + .MODULE$.array_length(this.b), c);
            this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
            this.merge0(0, .MODULE$.array_length(this.a), 0, .MODULE$.array_length(this.b));
         }

      }
   }
}
