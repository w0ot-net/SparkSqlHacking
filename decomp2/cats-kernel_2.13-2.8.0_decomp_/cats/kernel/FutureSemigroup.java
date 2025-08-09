package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2A!\u0002\u0004\u0005\u0017!A\u0001\u0006\u0001B\u0001B\u0003%\u0011\u0006\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003,\u0011\u0015q\u0003\u0001\"\u00010\u0011\u0015\u0019\u0004\u0001\"\u00015\u0005=1U\u000f^;sKN+W.[4s_V\u0004(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u0019}\u00192\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#F\f\u000e\u0003\u0019I!A\u0006\u0004\u0003\u0013M+W.[4s_V\u0004\bc\u0001\r\u001c;5\t\u0011D\u0003\u0002\u001b\u001f\u0005Q1m\u001c8dkJ\u0014XM\u001c;\n\u0005qI\"A\u0002$viV\u0014X\r\u0005\u0002\u001f?1\u0001A!\u0002\u0011\u0001\u0005\u0004\t#!A!\u0012\u0005\t*\u0003C\u0001\b$\u0013\t!sBA\u0004O_RD\u0017N\\4\u0011\u000591\u0013BA\u0014\u0010\u0005\r\te._\u0001\u0002\u0003B\u0019A#F\u000f\u0002\u0005\u0015\u001c\u0007C\u0001\r-\u0013\ti\u0013D\u0001\tFq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yi\u00061A(\u001b8jiz\"2\u0001M\u00193!\r!\u0002!\b\u0005\u0006Q\r\u0001\r!\u000b\u0005\u0006U\r\u0001\raK\u0001\bG>l'-\u001b8f)\r9Rg\u000e\u0005\u0006m\u0011\u0001\raF\u0001\u0002q\")\u0001\b\u0002a\u0001/\u0005\t\u0011\u0010"
)
public class FutureSemigroup implements Semigroup {
   private final Semigroup A;
   private final ExecutionContext ec;

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

   public Future combine(final Future x, final Future y) {
      return x.flatMap((xv) -> y.map((x$1) -> this.A.combine(xv, x$1), this.ec), this.ec);
   }

   public FutureSemigroup(final Semigroup A, final ExecutionContext ec) {
      this.A = A;
      this.ec = ec;
      Semigroup.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
