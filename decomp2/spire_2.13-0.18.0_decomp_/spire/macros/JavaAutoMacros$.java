package spire.macros;

import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Symbols;
import scala.reflect.api.TreeCreator;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxedUnit;

public final class JavaAutoMacros$ {
   public static final JavaAutoMacros$ MODULE$ = new JavaAutoMacros$();

   public Exprs.Expr semiringImpl(final Context c, final TypeTags.WeakTypeTag evidence$50) {
      JavaAlgebra var4 = new JavaAlgebra(c);
      return var4.Semiring(evidence$50);
   }

   public Exprs.Expr rigImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$51) {
      JavaAlgebra var8 = new JavaAlgebra(c);
      return var8.Rig(z, o, evidence$51);
   }

   public Exprs.Expr rngImpl(final Context c, final Exprs.Expr z, final TypeTags.WeakTypeTag evidence$52) {
      JavaAlgebra var6 = new JavaAlgebra(c);
      return var6.Rng(z, evidence$52);
   }

   public Exprs.Expr ringImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$53) {
      JavaAlgebra var8 = new JavaAlgebra(c);
      return var8.Ring(z, o, evidence$53);
   }

   public Exprs.Expr euclideanRingImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$54) {
      JavaAlgebra var10 = new JavaAlgebra(c);
      return var10.EuclideanRing(z, o, ev, evidence$54);
   }

   public Exprs.Expr fieldImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$55) {
      JavaAlgebra var10 = new JavaAlgebra(c);
      return var10.Field(z, o, ev, evidence$55);
   }

   public Exprs.Expr eqImpl(final Context c, final TypeTags.WeakTypeTag evidence$56) {
      JavaAlgebra var4 = new JavaAlgebra(c);
      return var4.Eq(evidence$56);
   }

   public Exprs.Expr orderImpl(final Context c, final TypeTags.WeakTypeTag evidence$57) {
      JavaAlgebra var4 = new JavaAlgebra(c);
      return var4.Order(evidence$57);
   }

   public Exprs.Expr collectionMonoidImpl(final Context c, final Exprs.Expr empty, final TypeTags.WeakTypeTag evidence$58) {
      JavaAlgebra ops = new JavaAlgebra(c);
      Exprs.Expr addx = ops.binop("addAll", "z", "x");
      Exprs.Expr addy = ops.binop("addAll", "z", "y");
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $treecreator1$11 extends TreeCreator {
         private final TypeTags.WeakTypeTag evidence$58$1$1;
         private final Exprs.Expr z$17;
         private final Exprs.Expr addx$1;
         private final Exprs.Expr addy$1;

         public Trees.TreeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.Block().apply((List)(new .colon.colon($u.ClassDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(32L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TypeName().apply("$anon"), scala.collection.immutable.Nil..MODULE$, $u.Template().apply((List)(new .colon.colon($u.AppliedTypeTree().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("spire.algebra.package")), (Names.NameApi)$u.TypeName().apply("Monoid")), (List)(new .colon.colon($u.internal().reificationSupport().mkTypeTree(this.evidence$58$1$1.in($m$untyped).tpe()), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), $u.noSelfType(), (List)(new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("<init>"), scala.collection.immutable.Nil..MODULE$, (List)(new .colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$)), $u.TypeTree().apply(), $u.Block().apply((List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Super().apply($u.This().apply($u.TypeName().apply("")), $u.TypeName().apply("")), (Names.NameApi)$u.TermName().apply("<init>")), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)))), new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("empty"), scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, $u.internal().reificationSupport().mkTypeTree(this.evidence$58$1$1.in($m$untyped).tpe()), this.z$17.in($m$untyped).tree()), new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("combine"), scala.collection.immutable.Nil..MODULE$, (List)(new .colon.colon((List)(new .colon.colon($u.ValDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TermName().apply("x"), $u.internal().reificationSupport().mkTypeTree(this.evidence$58$1$1.in($m$untyped).tpe()), $u.EmptyTree()), new .colon.colon($u.ValDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TermName().apply("y"), $u.internal().reificationSupport().mkTypeTree(this.evidence$58$1$1.in($m$untyped).tpe()), $u.EmptyTree()), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), $u.internal().reificationSupport().mkTypeTree(this.evidence$58$1$1.in($m$untyped).tpe()), $u.Block().apply((List)(new .colon.colon($u.ValDef().apply($u.NoMods(), $u.TermName().apply("z"), $u.TypeTree().apply(), $u.Select().apply($u.This().apply($u.TypeName().apply("$anon")), (Names.NameApi)$u.TermName().apply("empty"))), new .colon.colon(this.addx$1.in($m$untyped).tree(), new .colon.colon(this.addy$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$)))), $u.Ident().apply((Names.NameApi)$u.TermName().apply("z")))), scala.collection.immutable.Nil..MODULE$)))))), scala.collection.immutable.Nil..MODULE$)), $u.Apply().apply($u.Select().apply($u.New().apply($u.Ident().apply((Names.NameApi)$u.TypeName().apply("$anon"))), (Names.NameApi)$u.TermName().apply("<init>")), scala.collection.immutable.Nil..MODULE$));
         }

         public $treecreator1$11(final TypeTags.WeakTypeTag evidence$58$1$1, final Exprs.Expr z$17, final Exprs.Expr addx$1, final Exprs.Expr addy$1) {
            this.evidence$58$1$1 = evidence$58$1$1;
            this.z$17 = z$17;
            this.addx$1 = addx$1;
            this.addy$1 = addy$1;
         }
      }


      final class $typecreator2$11 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$58$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Symbols.SymbolApi symdef$$lessrefinement$greater1 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectTerm($m$untyped.staticModule("spire.macros.JavaAutoMacros").asModule().moduleClass(), "collectionMonoidImpl"), (Names.NameApi)$u.TypeName().apply("<refinement>"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(0L), true);
            $u.internal().reificationSupport().setInfo(symdef$$lessrefinement$greater1, (Types.TypeApi)$u.internal().reificationSupport().RefinedType((List)(new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $u.internal().reificationSupport().selectType($m$untyped.staticPackage("scala").asModule().moduleClass(), "AnyRef"), scala.collection.immutable.Nil..MODULE$), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("spire")), $m$untyped.staticPackage("spire.algebra")), $m$untyped.staticModule("spire.algebra.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("spire.algebra.package").asModule().moduleClass(), "Monoid"), (List)(new .colon.colon(this.evidence$58$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), $u.internal().reificationSupport().newScopeWith(scala.collection.immutable.Nil..MODULE$), symdef$$lessrefinement$greater1));
            return (Types.TypeApi)$u.internal().reificationSupport().RefinedType((List)(new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $u.internal().reificationSupport().selectType($m$untyped.staticPackage("scala").asModule().moduleClass(), "AnyRef"), scala.collection.immutable.Nil..MODULE$), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("spire")), $m$untyped.staticPackage("spire.algebra")), $m$untyped.staticModule("spire.algebra.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("spire.algebra.package").asModule().moduleClass(), "Monoid"), (List)(new .colon.colon(this.evidence$58$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), $u.internal().reificationSupport().newScopeWith(scala.collection.immutable.Nil..MODULE$), symdef$$lessrefinement$greater1);
         }

         public $typecreator2$11(final TypeTags.WeakTypeTag evidence$58$1$1) {
            this.evidence$58$1$1 = evidence$58$1$1;
         }
      }

      return $u.Expr().apply($m, new $treecreator1$11(evidence$58, empty, addx, addy), $u.WeakTypeTag().apply($m, new $typecreator2$11(evidence$58)));
   }

   private JavaAutoMacros$() {
   }
}
