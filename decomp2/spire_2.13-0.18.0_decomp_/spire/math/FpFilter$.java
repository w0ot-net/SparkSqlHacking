package spire.math;

import scala.Function0;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import spire.macros.fpf.Cmp;
import spire.macros.fpf.Fuser;
import spire.macros.fpf.Fuser$;

public final class FpFilter$ {
   public static final FpFilter$ MODULE$ = new FpFilter$();
   private static final double Eps = Double.longBitsToDouble(4372995238176751616L);

   public final double Eps() {
      return Eps;
   }

   public final double exact(final double value) {
      return value;
   }

   public final Object approx(final Object exact) {
      return exact;
   }

   public final FpFilter apply(final double apx, final double mes, final int ind, final Function0 exact) {
      return new FpFilter(apx, mes, ind, exact);
   }

   public FpFilter apply(final double approx, final Function0 exact) {
      return new FpFilter(approx, package$.MODULE$.abs(approx), 1, exact);
   }

   public Exprs.Expr negateImpl(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$3) {
      Fuser var6 = Fuser$.MODULE$.apply(c, evidence$3);
      Trees.TreeApi var10001 = var6.negate(c.prefix().tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$1 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$3$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$3$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$1(final TypeTags.WeakTypeTag evidence$3$1$1) {
            this.evidence$3$1$1 = evidence$3$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$1(evidence$3)));
   }

   public Exprs.Expr absImpl(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$4) {
      Trees.TreeApi var10001 = Fuser$.MODULE$.apply(c, evidence$4).abs(c.prefix().tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$2 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$4$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$4$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$2(final TypeTags.WeakTypeTag evidence$4$1$1) {
            this.evidence$4$1$1 = evidence$4$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$2(evidence$4)));
   }

   public Exprs.Expr sqrtImpl(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$5) {
      Trees.TreeApi var10001 = Fuser$.MODULE$.apply(c, evidence$5).sqrt(c.prefix().tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$3 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$5$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$5$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$3(final TypeTags.WeakTypeTag evidence$5$1$1) {
            this.evidence$5$1$1 = evidence$5$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$3(evidence$5)));
   }

   public Exprs.Expr plusImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$6) {
      Fuser var8 = Fuser$.MODULE$.apply(c, evidence$6);
      Trees.TreeApi var10001 = var8.plus(c.prefix().tree(), rhs.tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$4 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$6$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$6$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$4(final TypeTags.WeakTypeTag evidence$6$1$1) {
            this.evidence$6$1$1 = evidence$6$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$4(evidence$6)));
   }

   public Exprs.Expr minusImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$7) {
      Fuser var8 = Fuser$.MODULE$.apply(c, evidence$7);
      Trees.TreeApi var10001 = var8.minus(c.prefix().tree(), rhs.tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$5 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$7$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$7$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$5(final TypeTags.WeakTypeTag evidence$7$1$1) {
            this.evidence$7$1$1 = evidence$7$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$5(evidence$7)));
   }

   public Exprs.Expr timesImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$8) {
      Fuser var8 = Fuser$.MODULE$.apply(c, evidence$8);
      Trees.TreeApi var10001 = var8.times(c.prefix().tree(), rhs.tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$6 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$8$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$8$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$6(final TypeTags.WeakTypeTag evidence$8$1$1) {
            this.evidence$8$1$1 = evidence$8$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$6(evidence$8)));
   }

   public Exprs.Expr divideImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$9) {
      Fuser var8 = Fuser$.MODULE$.apply(c, evidence$9);
      Trees.TreeApi var10001 = var8.divide(c.prefix().tree(), rhs.tree(), ev.tree()).expr();
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$7 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$9$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.evidence$9$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$7(final TypeTags.WeakTypeTag evidence$9$1$1) {
            this.evidence$9$1$1 = evidence$9$1$1;
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$7(evidence$9)));
   }

   public Exprs.Expr signImpl(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$10) {
      Fuser var6 = Fuser$.MODULE$.apply(c, evidence$10);
      return c.Expr(var6.sign(c.prefix().tree(), ev.tree()), c.universe().WeakTypeTag().Int());
   }

   public Exprs.Expr ltImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev0, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$11) {
      Fuser var10 = Fuser$.MODULE$.apply(c, evidence$11);
      return c.Expr(var10.comp(c.prefix().tree(), rhs.tree(), ev0.tree(), ev1.tree(), Cmp.Lt$.MODULE$), c.universe().WeakTypeTag().Boolean());
   }

   public Exprs.Expr gtImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev0, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$12) {
      Fuser var10 = Fuser$.MODULE$.apply(c, evidence$12);
      return c.Expr(var10.comp(c.prefix().tree(), rhs.tree(), ev0.tree(), ev1.tree(), Cmp.Gt$.MODULE$), c.universe().WeakTypeTag().Boolean());
   }

   public Exprs.Expr ltEqImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev0, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$13) {
      Fuser var10 = Fuser$.MODULE$.apply(c, evidence$13);
      return c.Expr(var10.comp(c.prefix().tree(), rhs.tree(), ev0.tree(), ev1.tree(), Cmp.LtEq$.MODULE$), c.universe().WeakTypeTag().Boolean());
   }

   public Exprs.Expr gtEqImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev0, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$14) {
      Fuser var10 = Fuser$.MODULE$.apply(c, evidence$14);
      return c.Expr(var10.comp(c.prefix().tree(), rhs.tree(), ev0.tree(), ev1.tree(), Cmp.GtEq$.MODULE$), c.universe().WeakTypeTag().Boolean());
   }

   public Exprs.Expr eqImpl(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev0, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$15) {
      Fuser var10 = Fuser$.MODULE$.apply(c, evidence$15);
      return c.Expr(var10.comp(c.prefix().tree(), rhs.tree(), ev0.tree(), ev1.tree(), Cmp.Eq$.MODULE$), c.universe().WeakTypeTag().Boolean());
   }

   private FpFilter$() {
   }
}
