package spire.std;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3A!\u0002\u0004\u0001\u0017!)!\u0007\u0001C\u0001g!)a\u0007\u0001C\u0001o!)\u0001\b\u0001C\u0001s!)a\b\u0001C!\u007f\ta1\u000b\u001e:j]\u001eluN\\8jI*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001\u0001\u0004\n+!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191c\b\u0012\u000f\u0005QabBA\u000b\u001b\u001d\t1\u0012$D\u0001\u0018\u0015\tA\"\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u00111\u0004C\u0001\bC2<WM\u0019:b\u0013\tib$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005mA\u0011B\u0001\u0011\"\u0005\u0019iuN\\8jI*\u0011QD\b\t\u0003G\u001dr!\u0001J\u0013\u0011\u0005Yq\u0011B\u0001\u0014\u000f\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001&\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019r\u0001CA\u00160\u001d\tacF\u0004\u0002\u0017[%\tq\"\u0003\u0002\u001e\u001d%\u0011\u0001'\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003;9\ta\u0001P5oSRtD#\u0001\u001b\u0011\u0005U\u0002Q\"\u0001\u0004\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003\t\nqaY8nE&tW\rF\u0002#uqBQaO\u0002A\u0002\t\n\u0011\u0001\u001f\u0005\u0006{\r\u0001\rAI\u0001\u0002s\u0006Q1m\\7cS:,\u0017\t\u001c7\u0015\u0005\t\u0002\u0005\"B!\u0005\u0001\u0004\u0011\u0015A\u0001=t!\rY3II\u0005\u0003\tF\u0012A\"\u0013;fe\u0006\u0014G.Z(oG\u0016DC\u0001\u0001$J\u0015B\u0011QbR\u0005\u0003\u0011:\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0001\u0001"
)
public class StringMonoid implements Monoid {
   private static final long serialVersionUID = 0L;

   public double empty$mcD$sp() {
      return Monoid.empty$mcD$sp$(this);
   }

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public int empty$mcI$sp() {
      return Monoid.empty$mcI$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
   }

   public boolean isEmpty(final Object a, final Eq ev) {
      return Monoid.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid.isEmpty$mcI$sp$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid.isEmpty$mcJ$sp$(this, a, ev);
   }

   public Object combineN(final Object a, final int n) {
      return Monoid.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Monoid.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Monoid.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Monoid.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Monoid.combineN$mcJ$sp$(this, a, n);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid.combineAll$mcD$sp$(this, as);
   }

   public float combineAll$mcF$sp(final IterableOnce as) {
      return Monoid.combineAll$mcF$sp$(this, as);
   }

   public int combineAll$mcI$sp(final IterableOnce as) {
      return Monoid.combineAll$mcI$sp$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid.combineAll$mcJ$sp$(this, as);
   }

   public Option combineAllOption(final IterableOnce as) {
      return Monoid.combineAllOption$(this, as);
   }

   public Monoid reverse() {
      return Monoid.reverse$(this);
   }

   public Monoid reverse$mcD$sp() {
      return Monoid.reverse$mcD$sp$(this);
   }

   public Monoid reverse$mcF$sp() {
      return Monoid.reverse$mcF$sp$(this);
   }

   public Monoid reverse$mcI$sp() {
      return Monoid.reverse$mcI$sp$(this);
   }

   public Monoid reverse$mcJ$sp() {
      return Monoid.reverse$mcJ$sp$(this);
   }

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

   public String empty() {
      return "";
   }

   public String combine(final String x, final String y) {
      return (new StringBuilder(0)).append(x).append(y).toString();
   }

   public String combineAll(final IterableOnce xs) {
      scala.collection.mutable.StringBuilder sb = new scala.collection.mutable.StringBuilder();
      xs.iterator().foreach((x$1) -> sb.$plus$plus$eq(x$1));
      return sb.result();
   }

   public StringMonoid() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
