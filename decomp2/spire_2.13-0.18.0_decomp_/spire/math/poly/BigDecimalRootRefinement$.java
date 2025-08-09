package spire.math.poly;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Signed;
import algebra.ring.Signed.forAdditiveCommutativeGroup;
import algebra.ring.Signed.forAdditiveCommutativeMonoid;
import cats.kernel.CommutativeGroup;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.Ordering;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import spire.math.Polynomial;
import spire.math.Polynomial$;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.package$;
import spire.std.package;

public final class BigDecimalRootRefinement$ implements Serializable {
   public static final BigDecimalRootRefinement$ MODULE$ = new BigDecimalRootRefinement$();
   private static final Order JBigDecimalOrder = new Order() {
      public CommutativeGroup additive() {
         return AdditiveCommutativeGroup.additive$(this);
      }

      public CommutativeGroup additive$mcD$sp() {
         return AdditiveCommutativeGroup.additive$mcD$sp$(this);
      }

      public CommutativeGroup additive$mcF$sp() {
         return AdditiveCommutativeGroup.additive$mcF$sp$(this);
      }

      public CommutativeGroup additive$mcI$sp() {
         return AdditiveCommutativeGroup.additive$mcI$sp$(this);
      }

      public CommutativeGroup additive$mcJ$sp() {
         return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
      }

      public double negate$mcD$sp(final double x) {
         return AdditiveGroup.negate$mcD$sp$(this, x);
      }

      public float negate$mcF$sp(final float x) {
         return AdditiveGroup.negate$mcF$sp$(this, x);
      }

      public int negate$mcI$sp(final int x) {
         return AdditiveGroup.negate$mcI$sp$(this, x);
      }

      public long negate$mcJ$sp(final long x) {
         return AdditiveGroup.negate$mcJ$sp$(this, x);
      }

      public Object minus(final Object x, final Object y) {
         return AdditiveGroup.minus$(this, x, y);
      }

      public double minus$mcD$sp(final double x, final double y) {
         return AdditiveGroup.minus$mcD$sp$(this, x, y);
      }

      public float minus$mcF$sp(final float x, final float y) {
         return AdditiveGroup.minus$mcF$sp$(this, x, y);
      }

      public int minus$mcI$sp(final int x, final int y) {
         return AdditiveGroup.minus$mcI$sp$(this, x, y);
      }

      public long minus$mcJ$sp(final long x, final long y) {
         return AdditiveGroup.minus$mcJ$sp$(this, x, y);
      }

      public Object sumN(final Object a, final int n) {
         return AdditiveGroup.sumN$(this, a, n);
      }

      public double sumN$mcD$sp(final double a, final int n) {
         return AdditiveGroup.sumN$mcD$sp$(this, a, n);
      }

      public float sumN$mcF$sp(final float a, final int n) {
         return AdditiveGroup.sumN$mcF$sp$(this, a, n);
      }

      public int sumN$mcI$sp(final int a, final int n) {
         return AdditiveGroup.sumN$mcI$sp$(this, a, n);
      }

      public long sumN$mcJ$sp(final long a, final int n) {
         return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
      }

      public final Signed.forAdditiveCommutativeMonoid additiveCommutativeMonoid() {
         return forAdditiveCommutativeMonoid.additiveCommutativeMonoid$(this);
      }

      public double zero$mcD$sp() {
         return AdditiveMonoid.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return AdditiveMonoid.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return AdditiveMonoid.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return AdditiveMonoid.zero$mcJ$sp$(this);
      }

      public boolean isZero(final Object a, final Eq ev) {
         return AdditiveMonoid.isZero$(this, a, ev);
      }

      public boolean isZero$mcD$sp(final double a, final Eq ev) {
         return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
      }

      public boolean isZero$mcF$sp(final float a, final Eq ev) {
         return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
      }

      public boolean isZero$mcI$sp(final int a, final Eq ev) {
         return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
      }

      public boolean isZero$mcJ$sp(final long a, final Eq ev) {
         return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
      }

      public Object sum(final IterableOnce as) {
         return AdditiveMonoid.sum$(this, as);
      }

      public double sum$mcD$sp(final IterableOnce as) {
         return AdditiveMonoid.sum$mcD$sp$(this, as);
      }

      public float sum$mcF$sp(final IterableOnce as) {
         return AdditiveMonoid.sum$mcF$sp$(this, as);
      }

      public int sum$mcI$sp(final IterableOnce as) {
         return AdditiveMonoid.sum$mcI$sp$(this, as);
      }

      public long sum$mcJ$sp(final IterableOnce as) {
         return AdditiveMonoid.sum$mcJ$sp$(this, as);
      }

      public Option trySum(final IterableOnce as) {
         return AdditiveMonoid.trySum$(this, as);
      }

      public double plus$mcD$sp(final double x, final double y) {
         return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
      }

      public float plus$mcF$sp(final float x, final float y) {
         return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
      }

      public int plus$mcI$sp(final int x, final int y) {
         return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
      }

      public long plus$mcJ$sp(final long x, final long y) {
         return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
      }

      public Object positiveSumN(final Object a, final int n) {
         return AdditiveSemigroup.positiveSumN$(this, a, n);
      }

      public double positiveSumN$mcD$sp(final double a, final int n) {
         return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
      }

      public float positiveSumN$mcF$sp(final float a, final int n) {
         return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
      }

      public int positiveSumN$mcI$sp(final int a, final int n) {
         return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
      }

      public long positiveSumN$mcJ$sp(final long a, final int n) {
         return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcB$sp() {
         return Signed.additiveCommutativeMonoid$mcB$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcD$sp() {
         return Signed.additiveCommutativeMonoid$mcD$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcF$sp() {
         return Signed.additiveCommutativeMonoid$mcF$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcI$sp() {
         return Signed.additiveCommutativeMonoid$mcI$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcJ$sp() {
         return Signed.additiveCommutativeMonoid$mcJ$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcS$sp() {
         return Signed.additiveCommutativeMonoid$mcS$sp$(this);
      }

      public Order order$mcB$sp() {
         return Signed.order$mcB$sp$(this);
      }

      public Order order$mcD$sp() {
         return Signed.order$mcD$sp$(this);
      }

      public Order order$mcF$sp() {
         return Signed.order$mcF$sp$(this);
      }

      public Order order$mcI$sp() {
         return Signed.order$mcI$sp$(this);
      }

      public Order order$mcJ$sp() {
         return Signed.order$mcJ$sp$(this);
      }

      public Order order$mcS$sp() {
         return Signed.order$mcS$sp$(this);
      }

      public Signed.Sign sign(final Object a) {
         return Signed.sign$(this, a);
      }

      public Signed.Sign sign$mcB$sp(final byte a) {
         return Signed.sign$mcB$sp$(this, a);
      }

      public Signed.Sign sign$mcD$sp(final double a) {
         return Signed.sign$mcD$sp$(this, a);
      }

      public Signed.Sign sign$mcF$sp(final float a) {
         return Signed.sign$mcF$sp$(this, a);
      }

      public Signed.Sign sign$mcI$sp(final int a) {
         return Signed.sign$mcI$sp$(this, a);
      }

      public Signed.Sign sign$mcJ$sp(final long a) {
         return Signed.sign$mcJ$sp$(this, a);
      }

      public Signed.Sign sign$mcS$sp(final short a) {
         return Signed.sign$mcS$sp$(this, a);
      }

      public int signum$mcB$sp(final byte a) {
         return Signed.signum$mcB$sp$(this, a);
      }

      public int signum$mcD$sp(final double a) {
         return Signed.signum$mcD$sp$(this, a);
      }

      public int signum$mcF$sp(final float a) {
         return Signed.signum$mcF$sp$(this, a);
      }

      public int signum$mcI$sp(final int a) {
         return Signed.signum$mcI$sp$(this, a);
      }

      public int signum$mcJ$sp(final long a) {
         return Signed.signum$mcJ$sp$(this, a);
      }

      public int signum$mcS$sp(final short a) {
         return Signed.signum$mcS$sp$(this, a);
      }

      public byte abs$mcB$sp(final byte a) {
         return Signed.abs$mcB$sp$(this, a);
      }

      public double abs$mcD$sp(final double a) {
         return Signed.abs$mcD$sp$(this, a);
      }

      public float abs$mcF$sp(final float a) {
         return Signed.abs$mcF$sp$(this, a);
      }

      public int abs$mcI$sp(final int a) {
         return Signed.abs$mcI$sp$(this, a);
      }

      public long abs$mcJ$sp(final long a) {
         return Signed.abs$mcJ$sp$(this, a);
      }

      public short abs$mcS$sp(final short a) {
         return Signed.abs$mcS$sp$(this, a);
      }

      public boolean isSignZero(final Object a) {
         return Signed.isSignZero$(this, a);
      }

      public boolean isSignZero$mcB$sp(final byte a) {
         return Signed.isSignZero$mcB$sp$(this, a);
      }

      public boolean isSignZero$mcD$sp(final double a) {
         return Signed.isSignZero$mcD$sp$(this, a);
      }

      public boolean isSignZero$mcF$sp(final float a) {
         return Signed.isSignZero$mcF$sp$(this, a);
      }

      public boolean isSignZero$mcI$sp(final int a) {
         return Signed.isSignZero$mcI$sp$(this, a);
      }

      public boolean isSignZero$mcJ$sp(final long a) {
         return Signed.isSignZero$mcJ$sp$(this, a);
      }

      public boolean isSignZero$mcS$sp(final short a) {
         return Signed.isSignZero$mcS$sp$(this, a);
      }

      public boolean isSignPositive(final Object a) {
         return Signed.isSignPositive$(this, a);
      }

      public boolean isSignPositive$mcB$sp(final byte a) {
         return Signed.isSignPositive$mcB$sp$(this, a);
      }

      public boolean isSignPositive$mcD$sp(final double a) {
         return Signed.isSignPositive$mcD$sp$(this, a);
      }

      public boolean isSignPositive$mcF$sp(final float a) {
         return Signed.isSignPositive$mcF$sp$(this, a);
      }

      public boolean isSignPositive$mcI$sp(final int a) {
         return Signed.isSignPositive$mcI$sp$(this, a);
      }

      public boolean isSignPositive$mcJ$sp(final long a) {
         return Signed.isSignPositive$mcJ$sp$(this, a);
      }

      public boolean isSignPositive$mcS$sp(final short a) {
         return Signed.isSignPositive$mcS$sp$(this, a);
      }

      public boolean isSignNegative(final Object a) {
         return Signed.isSignNegative$(this, a);
      }

      public boolean isSignNegative$mcB$sp(final byte a) {
         return Signed.isSignNegative$mcB$sp$(this, a);
      }

      public boolean isSignNegative$mcD$sp(final double a) {
         return Signed.isSignNegative$mcD$sp$(this, a);
      }

      public boolean isSignNegative$mcF$sp(final float a) {
         return Signed.isSignNegative$mcF$sp$(this, a);
      }

      public boolean isSignNegative$mcI$sp(final int a) {
         return Signed.isSignNegative$mcI$sp$(this, a);
      }

      public boolean isSignNegative$mcJ$sp(final long a) {
         return Signed.isSignNegative$mcJ$sp$(this, a);
      }

      public boolean isSignNegative$mcS$sp(final short a) {
         return Signed.isSignNegative$mcS$sp$(this, a);
      }

      public boolean isSignNonZero(final Object a) {
         return Signed.isSignNonZero$(this, a);
      }

      public boolean isSignNonZero$mcB$sp(final byte a) {
         return Signed.isSignNonZero$mcB$sp$(this, a);
      }

      public boolean isSignNonZero$mcD$sp(final double a) {
         return Signed.isSignNonZero$mcD$sp$(this, a);
      }

      public boolean isSignNonZero$mcF$sp(final float a) {
         return Signed.isSignNonZero$mcF$sp$(this, a);
      }

      public boolean isSignNonZero$mcI$sp(final int a) {
         return Signed.isSignNonZero$mcI$sp$(this, a);
      }

      public boolean isSignNonZero$mcJ$sp(final long a) {
         return Signed.isSignNonZero$mcJ$sp$(this, a);
      }

      public boolean isSignNonZero$mcS$sp(final short a) {
         return Signed.isSignNonZero$mcS$sp$(this, a);
      }

      public boolean isSignNonPositive(final Object a) {
         return Signed.isSignNonPositive$(this, a);
      }

      public boolean isSignNonPositive$mcB$sp(final byte a) {
         return Signed.isSignNonPositive$mcB$sp$(this, a);
      }

      public boolean isSignNonPositive$mcD$sp(final double a) {
         return Signed.isSignNonPositive$mcD$sp$(this, a);
      }

      public boolean isSignNonPositive$mcF$sp(final float a) {
         return Signed.isSignNonPositive$mcF$sp$(this, a);
      }

      public boolean isSignNonPositive$mcI$sp(final int a) {
         return Signed.isSignNonPositive$mcI$sp$(this, a);
      }

      public boolean isSignNonPositive$mcJ$sp(final long a) {
         return Signed.isSignNonPositive$mcJ$sp$(this, a);
      }

      public boolean isSignNonPositive$mcS$sp(final short a) {
         return Signed.isSignNonPositive$mcS$sp$(this, a);
      }

      public boolean isSignNonNegative(final Object a) {
         return Signed.isSignNonNegative$(this, a);
      }

      public boolean isSignNonNegative$mcB$sp(final byte a) {
         return Signed.isSignNonNegative$mcB$sp$(this, a);
      }

      public boolean isSignNonNegative$mcD$sp(final double a) {
         return Signed.isSignNonNegative$mcD$sp$(this, a);
      }

      public boolean isSignNonNegative$mcF$sp(final float a) {
         return Signed.isSignNonNegative$mcF$sp$(this, a);
      }

      public boolean isSignNonNegative$mcI$sp(final int a) {
         return Signed.isSignNonNegative$mcI$sp$(this, a);
      }

      public boolean isSignNonNegative$mcJ$sp(final long a) {
         return Signed.isSignNonNegative$mcJ$sp$(this, a);
      }

      public boolean isSignNonNegative$mcS$sp(final short a) {
         return Signed.isSignNonNegative$mcS$sp$(this, a);
      }

      public int compare$mcZ$sp(final boolean x, final boolean y) {
         return Order.compare$mcZ$sp$(this, x, y);
      }

      public int compare$mcB$sp(final byte x, final byte y) {
         return Order.compare$mcB$sp$(this, x, y);
      }

      public int compare$mcC$sp(final char x, final char y) {
         return Order.compare$mcC$sp$(this, x, y);
      }

      public int compare$mcD$sp(final double x, final double y) {
         return Order.compare$mcD$sp$(this, x, y);
      }

      public int compare$mcF$sp(final float x, final float y) {
         return Order.compare$mcF$sp$(this, x, y);
      }

      public int compare$mcI$sp(final int x, final int y) {
         return Order.compare$mcI$sp$(this, x, y);
      }

      public int compare$mcJ$sp(final long x, final long y) {
         return Order.compare$mcJ$sp$(this, x, y);
      }

      public int compare$mcS$sp(final short x, final short y) {
         return Order.compare$mcS$sp$(this, x, y);
      }

      public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.compare$mcV$sp$(this, x, y);
      }

      public Comparison comparison(final Object x, final Object y) {
         return Order.comparison$(this, x, y);
      }

      public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
         return Order.comparison$mcZ$sp$(this, x, y);
      }

      public Comparison comparison$mcB$sp(final byte x, final byte y) {
         return Order.comparison$mcB$sp$(this, x, y);
      }

      public Comparison comparison$mcC$sp(final char x, final char y) {
         return Order.comparison$mcC$sp$(this, x, y);
      }

      public Comparison comparison$mcD$sp(final double x, final double y) {
         return Order.comparison$mcD$sp$(this, x, y);
      }

      public Comparison comparison$mcF$sp(final float x, final float y) {
         return Order.comparison$mcF$sp$(this, x, y);
      }

      public Comparison comparison$mcI$sp(final int x, final int y) {
         return Order.comparison$mcI$sp$(this, x, y);
      }

      public Comparison comparison$mcJ$sp(final long x, final long y) {
         return Order.comparison$mcJ$sp$(this, x, y);
      }

      public Comparison comparison$mcS$sp(final short x, final short y) {
         return Order.comparison$mcS$sp$(this, x, y);
      }

      public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.comparison$mcV$sp$(this, x, y);
      }

      public double partialCompare(final Object x, final Object y) {
         return Order.partialCompare$(this, x, y);
      }

      public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
         return Order.partialCompare$mcZ$sp$(this, x, y);
      }

      public double partialCompare$mcB$sp(final byte x, final byte y) {
         return Order.partialCompare$mcB$sp$(this, x, y);
      }

      public double partialCompare$mcC$sp(final char x, final char y) {
         return Order.partialCompare$mcC$sp$(this, x, y);
      }

      public double partialCompare$mcD$sp(final double x, final double y) {
         return Order.partialCompare$mcD$sp$(this, x, y);
      }

      public double partialCompare$mcF$sp(final float x, final float y) {
         return Order.partialCompare$mcF$sp$(this, x, y);
      }

      public double partialCompare$mcI$sp(final int x, final int y) {
         return Order.partialCompare$mcI$sp$(this, x, y);
      }

      public double partialCompare$mcJ$sp(final long x, final long y) {
         return Order.partialCompare$mcJ$sp$(this, x, y);
      }

      public double partialCompare$mcS$sp(final short x, final short y) {
         return Order.partialCompare$mcS$sp$(this, x, y);
      }

      public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.partialCompare$mcV$sp$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Order.min$(this, x, y);
      }

      public boolean min$mcZ$sp(final boolean x, final boolean y) {
         return Order.min$mcZ$sp$(this, x, y);
      }

      public byte min$mcB$sp(final byte x, final byte y) {
         return Order.min$mcB$sp$(this, x, y);
      }

      public char min$mcC$sp(final char x, final char y) {
         return Order.min$mcC$sp$(this, x, y);
      }

      public double min$mcD$sp(final double x, final double y) {
         return Order.min$mcD$sp$(this, x, y);
      }

      public float min$mcF$sp(final float x, final float y) {
         return Order.min$mcF$sp$(this, x, y);
      }

      public int min$mcI$sp(final int x, final int y) {
         return Order.min$mcI$sp$(this, x, y);
      }

      public long min$mcJ$sp(final long x, final long y) {
         return Order.min$mcJ$sp$(this, x, y);
      }

      public short min$mcS$sp(final short x, final short y) {
         return Order.min$mcS$sp$(this, x, y);
      }

      public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         Order.min$mcV$sp$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Order.max$(this, x, y);
      }

      public boolean max$mcZ$sp(final boolean x, final boolean y) {
         return Order.max$mcZ$sp$(this, x, y);
      }

      public byte max$mcB$sp(final byte x, final byte y) {
         return Order.max$mcB$sp$(this, x, y);
      }

      public char max$mcC$sp(final char x, final char y) {
         return Order.max$mcC$sp$(this, x, y);
      }

      public double max$mcD$sp(final double x, final double y) {
         return Order.max$mcD$sp$(this, x, y);
      }

      public float max$mcF$sp(final float x, final float y) {
         return Order.max$mcF$sp$(this, x, y);
      }

      public int max$mcI$sp(final int x, final int y) {
         return Order.max$mcI$sp$(this, x, y);
      }

      public long max$mcJ$sp(final long x, final long y) {
         return Order.max$mcJ$sp$(this, x, y);
      }

      public short max$mcS$sp(final short x, final short y) {
         return Order.max$mcS$sp$(this, x, y);
      }

      public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         Order.max$mcV$sp$(this, x, y);
      }

      public boolean eqv(final Object x, final Object y) {
         return Order.eqv$(this, x, y);
      }

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return Order.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return Order.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return Order.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return Order.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return Order.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return Order.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return Order.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.eqv$mcV$sp$(this, x, y);
      }

      public boolean neqv(final Object x, final Object y) {
         return Order.neqv$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Order.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Order.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Order.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Order.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Order.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Order.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Order.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.neqv$mcV$sp$(this, x, y);
      }

      public boolean lteqv(final Object x, final Object y) {
         return Order.lteqv$(this, x, y);
      }

      public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.lteqv$mcZ$sp$(this, x, y);
      }

      public boolean lteqv$mcB$sp(final byte x, final byte y) {
         return Order.lteqv$mcB$sp$(this, x, y);
      }

      public boolean lteqv$mcC$sp(final char x, final char y) {
         return Order.lteqv$mcC$sp$(this, x, y);
      }

      public boolean lteqv$mcD$sp(final double x, final double y) {
         return Order.lteqv$mcD$sp$(this, x, y);
      }

      public boolean lteqv$mcF$sp(final float x, final float y) {
         return Order.lteqv$mcF$sp$(this, x, y);
      }

      public boolean lteqv$mcI$sp(final int x, final int y) {
         return Order.lteqv$mcI$sp$(this, x, y);
      }

      public boolean lteqv$mcJ$sp(final long x, final long y) {
         return Order.lteqv$mcJ$sp$(this, x, y);
      }

      public boolean lteqv$mcS$sp(final short x, final short y) {
         return Order.lteqv$mcS$sp$(this, x, y);
      }

      public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.lteqv$mcV$sp$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Order.lt$(this, x, y);
      }

      public boolean lt$mcZ$sp(final boolean x, final boolean y) {
         return Order.lt$mcZ$sp$(this, x, y);
      }

      public boolean lt$mcB$sp(final byte x, final byte y) {
         return Order.lt$mcB$sp$(this, x, y);
      }

      public boolean lt$mcC$sp(final char x, final char y) {
         return Order.lt$mcC$sp$(this, x, y);
      }

      public boolean lt$mcD$sp(final double x, final double y) {
         return Order.lt$mcD$sp$(this, x, y);
      }

      public boolean lt$mcF$sp(final float x, final float y) {
         return Order.lt$mcF$sp$(this, x, y);
      }

      public boolean lt$mcI$sp(final int x, final int y) {
         return Order.lt$mcI$sp$(this, x, y);
      }

      public boolean lt$mcJ$sp(final long x, final long y) {
         return Order.lt$mcJ$sp$(this, x, y);
      }

      public boolean lt$mcS$sp(final short x, final short y) {
         return Order.lt$mcS$sp$(this, x, y);
      }

      public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.lt$mcV$sp$(this, x, y);
      }

      public boolean gteqv(final Object x, final Object y) {
         return Order.gteqv$(this, x, y);
      }

      public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.gteqv$mcZ$sp$(this, x, y);
      }

      public boolean gteqv$mcB$sp(final byte x, final byte y) {
         return Order.gteqv$mcB$sp$(this, x, y);
      }

      public boolean gteqv$mcC$sp(final char x, final char y) {
         return Order.gteqv$mcC$sp$(this, x, y);
      }

      public boolean gteqv$mcD$sp(final double x, final double y) {
         return Order.gteqv$mcD$sp$(this, x, y);
      }

      public boolean gteqv$mcF$sp(final float x, final float y) {
         return Order.gteqv$mcF$sp$(this, x, y);
      }

      public boolean gteqv$mcI$sp(final int x, final int y) {
         return Order.gteqv$mcI$sp$(this, x, y);
      }

      public boolean gteqv$mcJ$sp(final long x, final long y) {
         return Order.gteqv$mcJ$sp$(this, x, y);
      }

      public boolean gteqv$mcS$sp(final short x, final short y) {
         return Order.gteqv$mcS$sp$(this, x, y);
      }

      public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.gteqv$mcV$sp$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Order.gt$(this, x, y);
      }

      public boolean gt$mcZ$sp(final boolean x, final boolean y) {
         return Order.gt$mcZ$sp$(this, x, y);
      }

      public boolean gt$mcB$sp(final byte x, final byte y) {
         return Order.gt$mcB$sp$(this, x, y);
      }

      public boolean gt$mcC$sp(final char x, final char y) {
         return Order.gt$mcC$sp$(this, x, y);
      }

      public boolean gt$mcD$sp(final double x, final double y) {
         return Order.gt$mcD$sp$(this, x, y);
      }

      public boolean gt$mcF$sp(final float x, final float y) {
         return Order.gt$mcF$sp$(this, x, y);
      }

      public boolean gt$mcI$sp(final int x, final int y) {
         return Order.gt$mcI$sp$(this, x, y);
      }

      public boolean gt$mcJ$sp(final long x, final long y) {
         return Order.gt$mcJ$sp$(this, x, y);
      }

      public boolean gt$mcS$sp(final short x, final short y) {
         return Order.gt$mcS$sp$(this, x, y);
      }

      public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.gt$mcV$sp$(this, x, y);
      }

      public Ordering toOrdering() {
         return Order.toOrdering$(this);
      }

      public Option partialComparison(final Object x, final Object y) {
         return PartialOrder.partialComparison$(this, x, y);
      }

      public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
      }

      public Option partialComparison$mcB$sp(final byte x, final byte y) {
         return PartialOrder.partialComparison$mcB$sp$(this, x, y);
      }

      public Option partialComparison$mcC$sp(final char x, final char y) {
         return PartialOrder.partialComparison$mcC$sp$(this, x, y);
      }

      public Option partialComparison$mcD$sp(final double x, final double y) {
         return PartialOrder.partialComparison$mcD$sp$(this, x, y);
      }

      public Option partialComparison$mcF$sp(final float x, final float y) {
         return PartialOrder.partialComparison$mcF$sp$(this, x, y);
      }

      public Option partialComparison$mcI$sp(final int x, final int y) {
         return PartialOrder.partialComparison$mcI$sp$(this, x, y);
      }

      public Option partialComparison$mcJ$sp(final long x, final long y) {
         return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
      }

      public Option partialComparison$mcS$sp(final short x, final short y) {
         return PartialOrder.partialComparison$mcS$sp$(this, x, y);
      }

      public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.partialComparison$mcV$sp$(this, x, y);
      }

      public Option tryCompare(final Object x, final Object y) {
         return PartialOrder.tryCompare$(this, x, y);
      }

      public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
      }

      public Option tryCompare$mcB$sp(final byte x, final byte y) {
         return PartialOrder.tryCompare$mcB$sp$(this, x, y);
      }

      public Option tryCompare$mcC$sp(final char x, final char y) {
         return PartialOrder.tryCompare$mcC$sp$(this, x, y);
      }

      public Option tryCompare$mcD$sp(final double x, final double y) {
         return PartialOrder.tryCompare$mcD$sp$(this, x, y);
      }

      public Option tryCompare$mcF$sp(final float x, final float y) {
         return PartialOrder.tryCompare$mcF$sp$(this, x, y);
      }

      public Option tryCompare$mcI$sp(final int x, final int y) {
         return PartialOrder.tryCompare$mcI$sp$(this, x, y);
      }

      public Option tryCompare$mcJ$sp(final long x, final long y) {
         return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
      }

      public Option tryCompare$mcS$sp(final short x, final short y) {
         return PartialOrder.tryCompare$mcS$sp$(this, x, y);
      }

      public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.tryCompare$mcV$sp$(this, x, y);
      }

      public Option pmin(final Object x, final Object y) {
         return PartialOrder.pmin$(this, x, y);
      }

      public Option pmin$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmin$mcZ$sp$(this, x, y);
      }

      public Option pmin$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmin$mcB$sp$(this, x, y);
      }

      public Option pmin$mcC$sp(final char x, final char y) {
         return PartialOrder.pmin$mcC$sp$(this, x, y);
      }

      public Option pmin$mcD$sp(final double x, final double y) {
         return PartialOrder.pmin$mcD$sp$(this, x, y);
      }

      public Option pmin$mcF$sp(final float x, final float y) {
         return PartialOrder.pmin$mcF$sp$(this, x, y);
      }

      public Option pmin$mcI$sp(final int x, final int y) {
         return PartialOrder.pmin$mcI$sp$(this, x, y);
      }

      public Option pmin$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmin$mcJ$sp$(this, x, y);
      }

      public Option pmin$mcS$sp(final short x, final short y) {
         return PartialOrder.pmin$mcS$sp$(this, x, y);
      }

      public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmin$mcV$sp$(this, x, y);
      }

      public Option pmax(final Object x, final Object y) {
         return PartialOrder.pmax$(this, x, y);
      }

      public Option pmax$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmax$mcZ$sp$(this, x, y);
      }

      public Option pmax$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmax$mcB$sp$(this, x, y);
      }

      public Option pmax$mcC$sp(final char x, final char y) {
         return PartialOrder.pmax$mcC$sp$(this, x, y);
      }

      public Option pmax$mcD$sp(final double x, final double y) {
         return PartialOrder.pmax$mcD$sp$(this, x, y);
      }

      public Option pmax$mcF$sp(final float x, final float y) {
         return PartialOrder.pmax$mcF$sp$(this, x, y);
      }

      public Option pmax$mcI$sp(final int x, final int y) {
         return PartialOrder.pmax$mcI$sp$(this, x, y);
      }

      public Option pmax$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmax$mcJ$sp$(this, x, y);
      }

      public Option pmax$mcS$sp(final short x, final short y) {
         return PartialOrder.pmax$mcS$sp$(this, x, y);
      }

      public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmax$mcV$sp$(this, x, y);
      }

      public <undefinedtype> order() {
         return this;
      }

      public BigDecimal negate(final BigDecimal x) {
         return x.negate();
      }

      public BigDecimal zero() {
         return BigDecimal.ZERO;
      }

      public BigDecimal plus(final BigDecimal x, final BigDecimal y) {
         return x.add(y);
      }

      public int signum(final BigDecimal a) {
         return a.signum();
      }

      public BigDecimal abs(final BigDecimal a) {
         return a.abs();
      }

      public int compare(final BigDecimal x, final BigDecimal y) {
         return x.compareTo(y);
      }

      public {
         Eq.$init$(this);
         PartialOrder.$init$(this);
         Order.$init$(this);
         Signed.$init$(this);
         AdditiveSemigroup.$init$(this);
         AdditiveMonoid.$init$(this);
         AdditiveCommutativeSemigroup.$init$(this);
         AdditiveCommutativeMonoid.$init$(this);
         forAdditiveCommutativeMonoid.$init$(this);
         AdditiveGroup.$init$(this);
         AdditiveCommutativeGroup.$init$(this);
         forAdditiveCommutativeGroup.$init$(this);
      }
   };
   private static final double spire$math$poly$BigDecimalRootRefinement$$bits2dec;

   static {
      spire$math$poly$BigDecimalRootRefinement$$bits2dec = package$.MODULE$.log((double)2.0F, 10);
   }

   public BigDecimalRootRefinement apply(final Polynomial poly, final Rational lowerBound, final Rational upperBound) {
      Polynomial upoly = poly.map((n) -> new scala.math.BigDecimal(n.bigDecimal(), MathContext.UNLIMITED), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), .MODULE$.apply(scala.math.BigDecimal.class));
      return new BigDecimalRootRefinement(BigDecimalRootRefinement.AbsoluteContext$.MODULE$.apply(upoly, BigDecimalRootRefinement.AbsoluteContext$.MODULE$.apply$default$2()), new BigDecimalRootRefinement.Unbounded(lowerBound, upperBound));
   }

   public BigDecimalRootRefinement apply(final Polynomial poly, final Rational lowerBound, final Rational upperBound, final int scale) {
      return this.apply(poly, lowerBound, upperBound).refine(scale);
   }

   public BigDecimalRootRefinement apply(final Polynomial poly, final Rational lowerBound, final Rational upperBound, final MathContext mc) {
      return this.apply(poly, lowerBound, upperBound).refine(mc);
   }

   private Order JBigDecimalOrder() {
      return JBigDecimalOrder;
   }

   public double spire$math$poly$BigDecimalRootRefinement$$bits2dec() {
      return spire$math$poly$BigDecimalRootRefinement$$bits2dec;
   }

   public BigDecimalRootRefinement.Approximation spire$math$poly$BigDecimalRootRefinement$$QIR(final BigDecimalRootRefinement.ApproximationContext context, final Rational lowerBound, final Rational upperBound, final BigDecimal lb, final BigDecimal ub) {
      return this.adjust$1(lb, scala.None..MODULE$, ub, scala.None..MODULE$, context, lowerBound, upperBound);
   }

   public BigDecimalRootRefinement.Approximation spire$math$poly$BigDecimalRootRefinement$$QIR(final BigDecimalRootRefinement.ApproximationContext context, final BigDecimal lowerBound, final BigDecimal lowerBoundValue, final BigDecimal upperBound, final BigDecimal upperBoundValue, final int n0) {
      return n0 <= 0 ? this.loop0$1(lowerBound, lowerBoundValue, upperBound, upperBoundValue, context) : this.loop$1(lowerBound, lowerBoundValue, upperBound, upperBoundValue, n0, context);
   }

   private int QIR$default$6() {
      return 0;
   }

   public BigDecimalRootRefinement apply(final BigDecimalRootRefinement.ApproximationContext context, final BigDecimalRootRefinement.Approximation approximation) {
      return new BigDecimalRootRefinement(context, approximation);
   }

   public Option unapply(final BigDecimalRootRefinement x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.context(), x$0.approximation())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BigDecimalRootRefinement$.class);
   }

   private static final Polynomial shift$1(final Polynomial poly, final Rational h) {
      int n = poly.degree();
      return poly.mapTerms((x0$1) -> {
         if (x0$1 != null) {
            scala.math.BigDecimal coeff = (scala.math.BigDecimal)x0$1.coeff();
            int k = x0$1.exp();
            scala.math.BigDecimal a = scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(h.denominator().toBigInteger().pow(n - k)), MathContext.UNLIMITED);
            Term var3 = new Term(coeff.$times(a), k);
            return var3;
         } else {
            throw new MatchError(x0$1);
         }
      }, package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), .MODULE$.apply(scala.math.BigDecimal.class)).compose(Polynomial$.MODULE$.linear(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(h.denominator().toBigInteger()), MathContext.UNLIMITED), scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(h.numerator().toBigInteger()), MathContext.UNLIMITED), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), .MODULE$.apply(scala.math.BigDecimal.class)), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra()).removeZeroRoots(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra());
   }

   private static final Polynomial mult$1(final Polynomial poly, final Rational s) {
      int n = poly.degree();
      return poly.mapTerms((x0$1) -> {
         if (x0$1 != null) {
            scala.math.BigDecimal coeff = (scala.math.BigDecimal)x0$1.coeff();
            int k = x0$1.exp();
            scala.math.BigDecimal a = scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(s.denominator().toBigInteger().pow(n - k)), MathContext.UNLIMITED);
            Term var3 = new Term(coeff.$times(a), k);
            return var3;
         } else {
            throw new MatchError(x0$1);
         }
      }, package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), .MODULE$.apply(scala.math.BigDecimal.class)).compose(Polynomial$.MODULE$.linear(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(s.numerator().toBigInteger()), MathContext.UNLIMITED), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), .MODULE$.apply(scala.math.BigDecimal.class)), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra()).removeZeroRoots(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra());
   }

   private static final boolean hasRoot$1(final Rational l, final Rational r, final BigDecimalRootRefinement.ApproximationContext context$1) {
      boolean var10000;
      if (!BoxesRunTime.equalsNumNum(l, r)) {
         Polynomial poly0 = mult$1(shift$1(context$1.poly(), l), r.$minus(l)).reciprocal(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra()).shift(scala.math.BigDecimal..MODULE$.int2bigDecimal(1), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra()).removeZeroRoots(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra());
         var10000 = poly0.signVariations(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Order)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Signed)package.bigDecimal$.MODULE$.BigDecimalAlgebra()) % 2 == 1;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   private static final Rational qlx$1(final BigDecimal lx$1) {
      return Rational$.MODULE$.apply(new scala.math.BigDecimal(lx$1, MathContext.UNLIMITED));
   }

   private static final Rational qrx$1(final BigDecimal rx$1) {
      return Rational$.MODULE$.apply(new scala.math.BigDecimal(rx$1, MathContext.UNLIMITED));
   }

   private final BigDecimalRootRefinement.Approximation adjust$1(final BigDecimal lx, final Option lyOpt, final BigDecimal rx, final Option ryOpt, final BigDecimalRootRefinement.ApproximationContext context$1, final Rational lowerBound$1, final Rational upperBound$1) {
      while(true) {
         Object var10000;
         if (lx.compareTo(rx) < 0) {
            BigDecimal ly = (BigDecimal)lyOpt.getOrElse(() -> context$1.evalExact(lx));
            BigDecimal ry = (BigDecimal)ryOpt.getOrElse(() -> context$1.evalExact(rx));
            if (ly.signum() == 0) {
               if (!qlx$1(lx).$greater(lowerBound$1)) {
                  BigDecimal var11 = lx.add(BigDecimal.valueOf(1L, context$1.getEps(lx)));
                  None var10001 = scala.None..MODULE$;
                  ryOpt = new Some(ry);
                  rx = rx;
                  lyOpt = var10001;
                  lx = var11;
                  continue;
               }

               var10000 = new BigDecimalRootRefinement.ExactRoot(lx);
            } else if (ry.signum() == 0) {
               if (!qrx$1(rx).$less(upperBound$1)) {
                  Some var12 = new Some(ly);
                  BigDecimal var10002 = rx.subtract(BigDecimal.valueOf(1L, context$1.getEps(rx)));
                  ryOpt = scala.None..MODULE$;
                  rx = var10002;
                  lyOpt = var12;
                  lx = lx;
                  continue;
               }

               var10000 = new BigDecimalRootRefinement.ExactRoot(rx);
            } else {
               var10000 = ry.signum() == ly.signum() ? (hasRoot$1(lowerBound$1, qlx$1(lx), context$1) ? new BigDecimalRootRefinement.BoundedLeft(lowerBound$1, lx) : new BigDecimalRootRefinement.BoundedRight(rx, upperBound$1)) : this.spire$math$poly$BigDecimalRootRefinement$$QIR(context$1, lx, ly, rx, ry, this.QIR$default$6());
            }
         } else {
            var10000 = new BigDecimalRootRefinement.Unbounded(lowerBound$1, upperBound$1);
         }

         return (BigDecimalRootRefinement.Approximation)var10000;
      }
   }

   private final BigDecimalRootRefinement.Approximation loop$1(final BigDecimal lx, final BigDecimal ly, final BigDecimal rx, final BigDecimal ry, final int n, final BigDecimalRootRefinement.ApproximationContext context$2) {
      while(true) {
         BigDecimal x1;
         BigDecimal y1;
         BigDecimal x2;
         BigDecimal y2;
         while(true) {
            BigDecimal x0;
            BigDecimal y0;
            label92: {
               BigDecimal dx = rx.subtract(lx);
               int scale = package$.MODULE$.max(context$2.getEps(lx), context$2.getEps(rx));
               BigDecimal eps = BigDecimal.valueOf(1L, scale);
               Object var10000;
               if (dx.compareTo(eps) <= 0) {
                  var10000 = new BigDecimalRootRefinement.Bounded(lx, ly, rx, ry, n);
               } else {
                  BigDecimal delta;
                  Signed.Sign s1;
                  label105: {
                     BigDecimal dy = ly.subtract(ry);
                     BigDecimal s = ly.divide(dy, n, RoundingMode.HALF_UP);
                     delta = dx.multiply(s.ulp());
                     BigInteger k = s.unscaledValue();
                     x1 = lx.add(delta.multiply(new BigDecimal(k)));
                     y1 = context$2.evalExact(x1);
                     s1 = ((Signed)this.JBigDecimalOrder()).sign(y1);
                     Signed.Sign var18 = ((Signed)this.JBigDecimalOrder()).sign(ly);
                     if (s1 == null) {
                        if (var18 == null) {
                           break label105;
                        }
                     } else if (s1.equals(var18)) {
                        break label105;
                     }

                     label106: {
                        Signed.Sign var24 = ((Signed)this.JBigDecimalOrder()).sign(ry);
                        if (s1 == null) {
                           if (var24 != null) {
                              break label106;
                           }
                        } else if (!s1.equals(var24)) {
                           break label106;
                        }

                        label107: {
                           x0 = x1.subtract(delta);
                           y0 = context$2.evalExact(x0);
                           Signed.Sign s0 = ((Signed)this.JBigDecimalOrder()).sign(y0);
                           if (s0 == null) {
                              if (s1 == null) {
                                 break label107;
                              }
                           } else if (s0.equals(s1)) {
                              break label107;
                           }

                           Signed.Sign var29 = ((Signed)this.JBigDecimalOrder()).sign(ly);
                           if (s0 == null) {
                              if (var29 == null) {
                                 break label92;
                              }
                           } else if (s0.equals(var29)) {
                              break label92;
                           }

                           var10000 = new BigDecimalRootRefinement.ExactRoot(x0);
                           return (BigDecimalRootRefinement.Approximation)var10000;
                        }

                        var10000 = this.loop0$1(lx, ly, rx, ry, context$2);
                        return (BigDecimalRootRefinement.Approximation)var10000;
                     }

                     var10000 = new BigDecimalRootRefinement.ExactRoot(x1);
                     return (BigDecimalRootRefinement.Approximation)var10000;
                  }

                  label108: {
                     x2 = x1.add(delta);
                     y2 = context$2.evalExact(x2);
                     Signed.Sign s2 = ((Signed)this.JBigDecimalOrder()).sign(y2);
                     if (s2 == null) {
                        if (s1 == null) {
                           break label108;
                        }
                     } else if (s2.equals(s1)) {
                        break label108;
                     }

                     Signed.Sign var23 = ((Signed)this.JBigDecimalOrder()).sign(ry);
                     if (s2 == null) {
                        if (var23 == null) {
                           break;
                        }
                     } else if (s2.equals(var23)) {
                        break;
                     }

                     var10000 = new BigDecimalRootRefinement.ExactRoot(x2);
                     return (BigDecimalRootRefinement.Approximation)var10000;
                  }

                  var10000 = this.loop0$1(lx, ly, rx, ry, context$2);
               }

               return (BigDecimalRootRefinement.Approximation)var10000;
            }

            n = 2 * n;
            ry = y1;
            rx = x1;
            ly = y0;
            lx = x0;
         }

         n = 2 * n;
         ry = y2;
         rx = x2;
         ly = y1;
         lx = x1;
      }
   }

   private final BigDecimalRootRefinement.Approximation bisect$1(final BigDecimal x0, final BigDecimal y0, final BigDecimal x1, final BigDecimal y1, final BigDecimal x2, final BigDecimal y2, final BigDecimalRootRefinement.ApproximationContext context$2) {
      Object var10000;
      if (y0.signum() == 0) {
         var10000 = new BigDecimalRootRefinement.ExactRoot(x0);
      } else if (y1.signum() == 0) {
         var10000 = new BigDecimalRootRefinement.ExactRoot(x1);
      } else if (y2.signum() == 0) {
         var10000 = new BigDecimalRootRefinement.ExactRoot(x2);
      } else {
         label31: {
            Signed.Sign var9 = ((Signed)this.JBigDecimalOrder()).sign(y0);
            Signed.Sign var8 = ((Signed)this.JBigDecimalOrder()).sign(y1);
            if (var9 == null) {
               if (var8 != null) {
                  break label31;
               }
            } else if (!var9.equals(var8)) {
               break label31;
            }

            var10000 = this.loop$1(x1, y1, x2, y2, 1, context$2);
            return (BigDecimalRootRefinement.Approximation)var10000;
         }

         var10000 = this.loop$1(x0, y0, x1, y1, 1, context$2);
      }

      return (BigDecimalRootRefinement.Approximation)var10000;
   }

   private static final Tuple2 eval$1(final int k, final BigDecimal eps$1, final BigDecimal x0$1, final BigDecimalRootRefinement.ApproximationContext context$2) {
      BigDecimal x = (new BigDecimal(k)).multiply(eps$1).add(x0$1);
      BigDecimal y = context$2.evalExact(x);
      return new Tuple2(x, y);
   }

   private final BigDecimalRootRefinement.Approximation loop0$1(final BigDecimal x0, final BigDecimal y0, final BigDecimal x5, final BigDecimal y5, final BigDecimalRootRefinement.ApproximationContext context$2) {
      BigDecimal dy = y0.subtract(y5);
      int k = y0.divide(dy, 1, RoundingMode.HALF_UP).unscaledValue().intValue();
      BigDecimal eps = x5.subtract(x0).divide(new BigDecimal(5));
      BigDecimalRootRefinement.Approximation var70;
      if (k < 5) {
         Tuple2 var18 = eval$1(2, eps, x0, context$2);
         if (var18 == null) {
            throw new MatchError(var18);
         }

         BigDecimal x2;
         BigDecimal y2;
         label125: {
            BigDecimal x2 = (BigDecimal)var18._1();
            BigDecimal y2 = (BigDecimal)var18._2();
            Tuple2 var13 = new Tuple2(x2, y2);
            x2 = (BigDecimal)var13._1();
            y2 = (BigDecimal)var13._2();
            Signed.Sign var10000 = ((Signed)this.JBigDecimalOrder()).sign(y2);
            Signed.Sign var23 = ((Signed)this.JBigDecimalOrder()).sign(y0);
            if (var10000 == null) {
               if (var23 != null) {
                  break label125;
               }
            } else if (!var10000.equals(var23)) {
               break label125;
            }

            Tuple2 var31 = eval$1(3, eps, x0, context$2);
            if (var31 == null) {
               throw new MatchError(var31);
            }

            BigDecimal x3;
            BigDecimal y3;
            label126: {
               BigDecimal x3 = (BigDecimal)var31._1();
               BigDecimal y3 = (BigDecimal)var31._2();
               Tuple2 var11 = new Tuple2(x3, y3);
               x3 = (BigDecimal)var11._1();
               y3 = (BigDecimal)var11._2();
               var10000 = ((Signed)this.JBigDecimalOrder()).sign(y3);
               Signed.Sign var36 = ((Signed)this.JBigDecimalOrder()).sign(y5);
               if (var10000 == null) {
                  if (var36 == null) {
                     break label126;
                  }
               } else if (var10000.equals(var36)) {
                  break label126;
               }

               Tuple2 var38 = eval$1(4, eps, x0, context$2);
               if (var38 == null) {
                  throw new MatchError(var38);
               }

               BigDecimal x4 = (BigDecimal)var38._1();
               BigDecimal y4 = (BigDecimal)var38._2();
               Tuple2 var10 = new Tuple2(x4, y4);
               BigDecimal x4 = (BigDecimal)var10._1();
               BigDecimal y4 = (BigDecimal)var10._2();
               var70 = this.bisect$1(x3, y3, x4, y4, x5, y5, context$2);
               return var70;
            }

            var70 = this.loop$1(x2, y2, x3, y3, 1, context$2);
            return var70;
         }

         Tuple2 var25 = eval$1(1, eps, x0, context$2);
         if (var25 == null) {
            throw new MatchError(var25);
         }

         BigDecimal x1 = (BigDecimal)var25._1();
         BigDecimal y1 = (BigDecimal)var25._2();
         Tuple2 var12 = new Tuple2(x1, y1);
         BigDecimal x1 = (BigDecimal)var12._1();
         BigDecimal y1 = (BigDecimal)var12._2();
         var70 = this.bisect$1(x0, y0, x1, y1, x2, y2, context$2);
      } else {
         Tuple2 var44 = eval$1(3, eps, x0, context$2);
         if (var44 == null) {
            throw new MatchError(var44);
         }

         BigDecimal x3;
         BigDecimal y3;
         label127: {
            BigDecimal x3 = (BigDecimal)var44._1();
            BigDecimal y3 = (BigDecimal)var44._2();
            Tuple2 var9 = new Tuple2(x3, y3);
            x3 = (BigDecimal)var9._1();
            y3 = (BigDecimal)var9._2();
            Signed.Sign var71 = ((Signed)this.JBigDecimalOrder()).sign(y3);
            Signed.Sign var49 = ((Signed)this.JBigDecimalOrder()).sign(y5);
            if (var71 == null) {
               if (var49 != null) {
                  break label127;
               }
            } else if (!var71.equals(var49)) {
               break label127;
            }

            Tuple2 var57 = eval$1(2, eps, x0, context$2);
            if (var57 == null) {
               throw new MatchError(var57);
            }

            BigDecimal x2;
            BigDecimal y2;
            label128: {
               BigDecimal x2 = (BigDecimal)var57._1();
               BigDecimal y2 = (BigDecimal)var57._2();
               Tuple2 var7 = new Tuple2(x2, y2);
               x2 = (BigDecimal)var7._1();
               y2 = (BigDecimal)var7._2();
               var71 = ((Signed)this.JBigDecimalOrder()).sign(y2);
               Signed.Sign var62 = ((Signed)this.JBigDecimalOrder()).sign(y0);
               if (var71 == null) {
                  if (var62 == null) {
                     break label128;
                  }
               } else if (var71.equals(var62)) {
                  break label128;
               }

               Tuple2 var64 = eval$1(1, eps, x0, context$2);
               if (var64 == null) {
                  throw new MatchError(var64);
               }

               BigDecimal x1 = (BigDecimal)var64._1();
               BigDecimal y1 = (BigDecimal)var64._2();
               Tuple2 var6 = new Tuple2(x1, y1);
               BigDecimal x1 = (BigDecimal)var6._1();
               BigDecimal y1 = (BigDecimal)var6._2();
               var70 = this.bisect$1(x0, y0, x1, y1, x2, y2, context$2);
               return var70;
            }

            var70 = this.loop$1(x2, y2, x3, y3, 1, context$2);
            return var70;
         }

         Tuple2 var51 = eval$1(4, eps, x0, context$2);
         if (var51 == null) {
            throw new MatchError(var51);
         }

         BigDecimal x4 = (BigDecimal)var51._1();
         BigDecimal y4 = (BigDecimal)var51._2();
         Tuple2 var8 = new Tuple2(x4, y4);
         BigDecimal x4 = (BigDecimal)var8._1();
         BigDecimal y4 = (BigDecimal)var8._2();
         var70 = this.bisect$1(x3, y3, x4, y4, x5, y5, context$2);
      }

      return var70;
   }

   private BigDecimalRootRefinement$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
