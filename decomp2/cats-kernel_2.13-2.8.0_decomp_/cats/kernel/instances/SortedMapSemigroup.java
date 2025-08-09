package cats.kernel.instances;

import cats.kernel.Semigroup;
import cats.kernel.Semigroup$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.SortedMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2A\u0001B\u0003\u0001\u0019!Aa\u0006\u0001B\u0001B\u0003-q\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00037\u0001\u0011\u0005qG\u0001\nT_J$X\rZ'baN+W.[4s_V\u0004(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u00075\u0011CfE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\u001715\tq!\u0003\u0002\u0018\u000f\tI1+Z7jOJ|W\u000f\u001d\t\u00053y\u00013&D\u0001\u001b\u0015\tYB$A\u0005j[6,H/\u00192mK*\u0011Q\u0004E\u0001\u000bG>dG.Z2uS>t\u0017BA\u0010\u001b\u0005%\u0019vN\u001d;fI6\u000b\u0007\u000f\u0005\u0002\"E1\u0001A!B\u0012\u0001\u0005\u0004!#!A&\u0012\u0005\u0015B\u0003CA\b'\u0013\t9\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=I\u0013B\u0001\u0016\u0011\u0005\r\te.\u001f\t\u0003C1\"Q!\f\u0001C\u0002\u0011\u0012\u0011AV\u0001\u0002-B\u0019QCF\u0016\u0002\rqJg.\u001b;?)\u0005\u0011DCA\u001a6!\u0011!\u0004\u0001I\u0016\u000e\u0003\u0015AQA\f\u0002A\u0004=\nqaY8nE&tW\rF\u0002\u0019qiBQ!O\u0002A\u0002a\t!\u0001_:\t\u000bm\u001a\u0001\u0019\u0001\r\u0002\u0005e\u001c\b"
)
public class SortedMapSemigroup implements Semigroup {
   private final Semigroup V;

   public double combine$mcD$sp(final double x, final double y) {
      return Semigroup.combine$mcD$sp$(this, x, y);
   }

   public float combine$mcF$sp(final float x, final float y) {
      return Semigroup.combine$mcF$sp$(this, x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return Semigroup.combine$mcI$sp$(this, x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public Object combineN(final Object a, final int n) {
      return Semigroup.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Semigroup.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Semigroup.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Semigroup.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Semigroup.combineN$mcJ$sp$(this, a, n);
   }

   public Object repeatedCombineN(final Object a, final int n) {
      return Semigroup.repeatedCombineN$(this, a, n);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public Option combineAllOption(final IterableOnce as) {
      return Semigroup.combineAllOption$(this, as);
   }

   public Semigroup reverse() {
      return Semigroup.reverse$(this);
   }

   public Semigroup reverse$mcD$sp() {
      return Semigroup.reverse$mcD$sp$(this);
   }

   public Semigroup reverse$mcF$sp() {
      return Semigroup.reverse$mcF$sp$(this);
   }

   public Semigroup reverse$mcI$sp() {
      return Semigroup.reverse$mcI$sp$(this);
   }

   public Semigroup reverse$mcJ$sp() {
      return Semigroup.reverse$mcJ$sp$(this);
   }

   public Semigroup intercalate(final Object middle) {
      return Semigroup.intercalate$(this, middle);
   }

   public Semigroup intercalate$mcD$sp(final double middle) {
      return Semigroup.intercalate$mcD$sp$(this, middle);
   }

   public Semigroup intercalate$mcF$sp(final float middle) {
      return Semigroup.intercalate$mcF$sp$(this, middle);
   }

   public Semigroup intercalate$mcI$sp(final int middle) {
      return Semigroup.intercalate$mcI$sp$(this, middle);
   }

   public Semigroup intercalate$mcJ$sp(final long middle) {
      return Semigroup.intercalate$mcJ$sp$(this, middle);
   }

   public SortedMap combine(final SortedMap xs, final SortedMap ys) {
      return xs.size() <= ys.size() ? (SortedMap)xs.foldLeft(ys, (x0$1, x1$1) -> {
         Tuple2 var4 = new Tuple2(x0$1, x1$1);
         if (var4 != null) {
            SortedMap my = (SortedMap)var4._1();
            Tuple2 var6 = (Tuple2)var4._2();
            if (var6 != null) {
               Object k = var6._1();
               Object x = var6._2();
               SortedMap var3 = (SortedMap)my.updated(k, Semigroup$.MODULE$.maybeCombine(x, my.get(k), this.V));
               return var3;
            }
         }

         throw new MatchError(var4);
      }) : (SortedMap)ys.foldLeft(xs, (x0$2, x1$2) -> {
         Tuple2 var4 = new Tuple2(x0$2, x1$2);
         if (var4 != null) {
            SortedMap mx = (SortedMap)var4._1();
            Tuple2 var6 = (Tuple2)var4._2();
            if (var6 != null) {
               Object k = var6._1();
               Object y = var6._2();
               SortedMap var3 = (SortedMap)mx.updated(k, Semigroup$.MODULE$.maybeCombine(mx.get(k), y, this.V));
               return var3;
            }
         }

         throw new MatchError(var4);
      });
   }

   public SortedMapSemigroup(final Semigroup V) {
      this.V = V;
      Semigroup.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
