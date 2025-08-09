package spire.algebra;

import algebra.lattice.Bool;
import algebra.package.;
import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeRng;
import algebra.ring.CommutativeSemiring;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final Eq Eq;
   private static final PartialOrder PartialOrder;
   private static final Order Order;
   private static final Semigroup Semigroup;
   private static final CommutativeSemigroup CSemigroup;
   private static final Monoid Monoid;
   private static final CommutativeMonoid CMonoid;
   private static final Group Group;
   private static final CommutativeGroup AbGroup;
   private static final AdditiveSemigroup AdditiveSemigroup;
   private static final AdditiveCommutativeSemigroup AdditiveCSemigroup;
   private static final AdditiveMonoid AdditiveMonoid;
   private static final AdditiveCommutativeMonoid AdditiveCMonoid;
   private static final AdditiveGroup AdditiveGroup;
   private static final AdditiveCommutativeGroup AdditiveAbGroup;
   private static final MultiplicativeSemigroup MultiplicativeSemigroup;
   private static final MultiplicativeCommutativeSemigroup MultiplicativeCSemigroup;
   private static final MultiplicativeMonoid MultiplicativeMonoid;
   private static final MultiplicativeCommutativeMonoid MultiplicativeCMonoid;
   private static final MultiplicativeGroup MultiplicativeGroup;
   private static final MultiplicativeCommutativeGroup MultiplicativeAbGroup;
   private static final Semiring Semiring;
   private static final CommutativeSemiring CSemiring;
   private static final Rig Rig;
   private static final CommutativeRig CRig;
   private static final Rng Rng;
   private static final CommutativeRng CRng;
   private static final Ring Ring;
   private static final CommutativeRing CRing;
   private static final GCDRing GCDRing;
   private static final EuclideanRing EuclideanRing;
   private static final Signed.Sign Sign;
   private static final Signed Signed;
   private static final TruncatedDivision TruncatedDivision;
   private static final DivisionRing DivisionRing;
   private static final Field Field;
   private static final Bool Bool;

   static {
      Eq = .MODULE$.Eq();
      PartialOrder = .MODULE$.PartialOrder();
      Order = .MODULE$.Order();
      Semigroup = .MODULE$.Semigroup();
      CSemigroup = .MODULE$.CommutativeSemigroup();
      Monoid = .MODULE$.Monoid();
      CMonoid = .MODULE$.CommutativeMonoid();
      Group = .MODULE$.Group();
      AbGroup = .MODULE$.CommutativeGroup();
      AdditiveSemigroup = algebra.ring.AdditiveSemigroup..MODULE$;
      AdditiveCSemigroup = algebra.ring.AdditiveCommutativeSemigroup..MODULE$;
      AdditiveMonoid = algebra.ring.AdditiveMonoid..MODULE$;
      AdditiveCMonoid = algebra.ring.AdditiveCommutativeMonoid..MODULE$;
      AdditiveGroup = algebra.ring.AdditiveGroup..MODULE$;
      AdditiveAbGroup = algebra.ring.AdditiveCommutativeGroup..MODULE$;
      MultiplicativeSemigroup = algebra.ring.MultiplicativeSemigroup..MODULE$;
      MultiplicativeCSemigroup = algebra.ring.MultiplicativeCommutativeSemigroup..MODULE$;
      MultiplicativeMonoid = algebra.ring.MultiplicativeMonoid..MODULE$;
      MultiplicativeCMonoid = algebra.ring.MultiplicativeCommutativeMonoid..MODULE$;
      MultiplicativeGroup = algebra.ring.MultiplicativeGroup..MODULE$;
      MultiplicativeAbGroup = algebra.ring.MultiplicativeCommutativeGroup..MODULE$;
      Semiring = algebra.ring.Semiring..MODULE$;
      CSemiring = algebra.ring.CommutativeSemiring..MODULE$;
      Rig = algebra.ring.Rig..MODULE$;
      CRig = algebra.ring.CommutativeRig..MODULE$;
      Rng = algebra.ring.Rng..MODULE$;
      CRng = algebra.ring.CommutativeRng..MODULE$;
      Ring = algebra.ring.Ring..MODULE$;
      CRing = algebra.ring.CommutativeRing..MODULE$;
      GCDRing = algebra.ring.GCDRing..MODULE$;
      EuclideanRing = algebra.ring.EuclideanRing..MODULE$;
      Sign = algebra.ring.Signed.Sign..MODULE$;
      Signed = algebra.ring.Signed..MODULE$;
      TruncatedDivision = algebra.ring.TruncatedDivision..MODULE$;
      DivisionRing = algebra.ring.DivisionRing..MODULE$;
      Field = algebra.ring.Field..MODULE$;
      Bool = algebra.lattice.Bool..MODULE$;
   }

   public Eq Eq() {
      return Eq;
   }

   public PartialOrder PartialOrder() {
      return PartialOrder;
   }

   public Order Order() {
      return Order;
   }

   public Semigroup Semigroup() {
      return Semigroup;
   }

   public CommutativeSemigroup CSemigroup() {
      return CSemigroup;
   }

   public Monoid Monoid() {
      return Monoid;
   }

   public CommutativeMonoid CMonoid() {
      return CMonoid;
   }

   public Group Group() {
      return Group;
   }

   public CommutativeGroup AbGroup() {
      return AbGroup;
   }

   public AdditiveSemigroup AdditiveSemigroup() {
      return AdditiveSemigroup;
   }

   public AdditiveCommutativeSemigroup AdditiveCSemigroup() {
      return AdditiveCSemigroup;
   }

   public AdditiveMonoid AdditiveMonoid() {
      return AdditiveMonoid;
   }

   public AdditiveCommutativeMonoid AdditiveCMonoid() {
      return AdditiveCMonoid;
   }

   public AdditiveGroup AdditiveGroup() {
      return AdditiveGroup;
   }

   public AdditiveCommutativeGroup AdditiveAbGroup() {
      return AdditiveAbGroup;
   }

   public MultiplicativeSemigroup MultiplicativeSemigroup() {
      return MultiplicativeSemigroup;
   }

   public MultiplicativeCommutativeSemigroup MultiplicativeCSemigroup() {
      return MultiplicativeCSemigroup;
   }

   public MultiplicativeMonoid MultiplicativeMonoid() {
      return MultiplicativeMonoid;
   }

   public MultiplicativeCommutativeMonoid MultiplicativeCMonoid() {
      return MultiplicativeCMonoid;
   }

   public MultiplicativeGroup MultiplicativeGroup() {
      return MultiplicativeGroup;
   }

   public MultiplicativeCommutativeGroup MultiplicativeAbGroup() {
      return MultiplicativeAbGroup;
   }

   public Semiring Semiring() {
      return Semiring;
   }

   public CommutativeSemiring CSemiring() {
      return CSemiring;
   }

   public Rig Rig() {
      return Rig;
   }

   public CommutativeRig CRig() {
      return CRig;
   }

   public Rng Rng() {
      return Rng;
   }

   public CommutativeRng CRng() {
      return CRng;
   }

   public Ring Ring() {
      return Ring;
   }

   public CommutativeRing CRing() {
      return CRing;
   }

   public GCDRing GCDRing() {
      return GCDRing;
   }

   public EuclideanRing EuclideanRing() {
      return EuclideanRing;
   }

   public Signed.Sign Sign() {
      return Sign;
   }

   public Signed Signed() {
      return Signed;
   }

   public TruncatedDivision TruncatedDivision() {
      return TruncatedDivision;
   }

   public DivisionRing DivisionRing() {
      return DivisionRing;
   }

   public Field Field() {
      return Field;
   }

   public Bool Bool() {
      return Bool;
   }

   private package$() {
   }
}
