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

public final class ScalaAutoMacros$ {
   public static final ScalaAutoMacros$ MODULE$ = new ScalaAutoMacros$();

   public Exprs.Expr semiringImpl(final Context c, final TypeTags.WeakTypeTag evidence$40) {
      ScalaAlgebra var4 = new ScalaAlgebra(c);
      return var4.Semiring(evidence$40);
   }

   public Exprs.Expr rigImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$41) {
      ScalaAlgebra var8 = new ScalaAlgebra(c);
      return var8.Rig(z, o, evidence$41);
   }

   public Exprs.Expr rngImpl(final Context c, final Exprs.Expr z, final TypeTags.WeakTypeTag evidence$42) {
      ScalaAlgebra var6 = new ScalaAlgebra(c);
      return var6.Rng(z, evidence$42);
   }

   public Exprs.Expr ringImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$43) {
      ScalaAlgebra var8 = new ScalaAlgebra(c);
      return var8.Ring(z, o, evidence$43);
   }

   public Exprs.Expr euclideanRingImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$44) {
      ScalaAlgebra var10 = new ScalaAlgebra(c);
      return var10.EuclideanRing(z, o, ev, evidence$44);
   }

   public Exprs.Expr fieldImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$45) {
      ScalaAlgebra var10 = new ScalaAlgebra(c);
      return var10.Field(z, o, ev, evidence$45);
   }

   public Exprs.Expr eqImpl(final Context c, final TypeTags.WeakTypeTag evidence$46) {
      ScalaAlgebra var4 = new ScalaAlgebra(c);
      return var4.Eq(evidence$46);
   }

   public Exprs.Expr orderImpl(final Context c, final TypeTags.WeakTypeTag evidence$47) {
      ScalaAlgebra var4 = new ScalaAlgebra(c);
      return var4.Order(evidence$47);
   }

   public Exprs.Expr collectionSemigroupImpl(final Context c, final TypeTags.WeakTypeTag evidence$48) {
      ScalaAlgebra ops = new ScalaAlgebra(c);
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $treecreator1$9 extends TreeCreator {
         private final TypeTags.WeakTypeTag evidence$48$1$1;
         private final ScalaAlgebra ops$1;

         public Trees.TreeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.Block().apply((List)(new .colon.colon($u.ClassDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(32L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TypeName().apply("$anon"), scala.collection.immutable.Nil..MODULE$, $u.Template().apply((List)(new .colon.colon($u.AppliedTypeTree().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("spire.algebra.package")), (Names.NameApi)$u.TypeName().apply("Semigroup")), (List)(new .colon.colon($u.internal().reificationSupport().mkTypeTree(this.evidence$48$1$1.in($m$untyped).tpe()), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), $u.noSelfType(), (List)(new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("<init>"), scala.collection.immutable.Nil..MODULE$, (List)(new .colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$)), $u.TypeTree().apply(), $u.Block().apply((List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Super().apply($u.This().apply($u.TypeName().apply("")), $u.TypeName().apply("")), (Names.NameApi)$u.TermName().apply("<init>")), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)))), new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("combine"), scala.collection.immutable.Nil..MODULE$, (List)(new .colon.colon((List)(new .colon.colon($u.ValDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TermName().apply("x"), $u.internal().reificationSupport().mkTypeTree(this.evidence$48$1$1.in($m$untyped).tpe()), $u.EmptyTree()), new .colon.colon($u.ValDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TermName().apply("y"), $u.internal().reificationSupport().mkTypeTree(this.evidence$48$1$1.in($m$untyped).tpe()), $u.EmptyTree()), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), $u.internal().reificationSupport().mkTypeTree(this.evidence$48$1$1.in($m$untyped).tpe()), this.ops$1.plusplus().in($m$untyped).tree()), scala.collection.immutable.Nil..MODULE$))))), scala.collection.immutable.Nil..MODULE$)), $u.Apply().apply($u.Select().apply($u.New().apply($u.Ident().apply((Names.NameApi)$u.TypeName().apply("$anon"))), (Names.NameApi)$u.TermName().apply("<init>")), scala.collection.immutable.Nil..MODULE$));
         }

         public $treecreator1$9(final TypeTags.WeakTypeTag evidence$48$1$1, final ScalaAlgebra ops$1) {
            this.evidence$48$1$1 = evidence$48$1$1;
            this.ops$1 = ops$1;
         }
      }


      final class $typecreator2$9 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$48$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Symbols.SymbolApi symdef$$lessrefinement$greater1 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectTerm($m$untyped.staticModule("spire.macros.ScalaAutoMacros").asModule().moduleClass(), "collectionSemigroupImpl"), (Names.NameApi)$u.TypeName().apply("<refinement>"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(0L), true);
            $u.internal().reificationSupport().setInfo(symdef$$lessrefinement$greater1, (Types.TypeApi)$u.internal().reificationSupport().RefinedType((List)(new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $u.internal().reificationSupport().selectType($m$untyped.staticPackage("scala").asModule().moduleClass(), "AnyRef"), scala.collection.immutable.Nil..MODULE$), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("spire")), $m$untyped.staticPackage("spire.algebra")), $m$untyped.staticModule("spire.algebra.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("spire.algebra.package").asModule().moduleClass(), "Semigroup"), (List)(new .colon.colon(this.evidence$48$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), $u.internal().reificationSupport().newScopeWith(scala.collection.immutable.Nil..MODULE$), symdef$$lessrefinement$greater1));
            return (Types.TypeApi)$u.internal().reificationSupport().RefinedType((List)(new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $u.internal().reificationSupport().selectType($m$untyped.staticPackage("scala").asModule().moduleClass(), "AnyRef"), scala.collection.immutable.Nil..MODULE$), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("spire")), $m$untyped.staticPackage("spire.algebra")), $m$untyped.staticModule("spire.algebra.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("spire.algebra.package").asModule().moduleClass(), "Semigroup"), (List)(new .colon.colon(this.evidence$48$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), $u.internal().reificationSupport().newScopeWith(scala.collection.immutable.Nil..MODULE$), symdef$$lessrefinement$greater1);
         }

         public $typecreator2$9(final TypeTags.WeakTypeTag evidence$48$1$1) {
            this.evidence$48$1$1 = evidence$48$1$1;
         }
      }

      return $u.Expr().apply($m, new $treecreator1$9(evidence$48, ops), $u.WeakTypeTag().apply($m, new $typecreator2$9(evidence$48)));
   }

   public Exprs.Expr collectionMonoidImpl(final Context c, final Exprs.Expr z, final TypeTags.WeakTypeTag evidence$49) {
      ScalaAlgebra ops = new ScalaAlgebra(c);
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $treecreator1$10 extends TreeCreator {
         private final TypeTags.WeakTypeTag evidence$49$1$1;
         private final Exprs.Expr z$6$1;
         private final ScalaAlgebra ops$2;

         public Trees.TreeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.Block().apply((List)(new .colon.colon($u.ClassDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(32L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TypeName().apply("$anon"), scala.collection.immutable.Nil..MODULE$, $u.Template().apply((List)(new .colon.colon($u.AppliedTypeTree().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("spire.algebra.package")), (Names.NameApi)$u.TypeName().apply("Monoid")), (List)(new .colon.colon($u.internal().reificationSupport().mkTypeTree(this.evidence$49$1$1.in($m$untyped).tpe()), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), $u.noSelfType(), (List)(new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("<init>"), scala.collection.immutable.Nil..MODULE$, (List)(new .colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$)), $u.TypeTree().apply(), $u.Block().apply((List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Super().apply($u.This().apply($u.TypeName().apply("")), $u.TypeName().apply("")), (Names.NameApi)$u.TermName().apply("<init>")), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)))), new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("empty"), scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, $u.internal().reificationSupport().mkTypeTree(this.evidence$49$1$1.in($m$untyped).tpe()), this.z$6$1.in($m$untyped).tree()), new .colon.colon($u.DefDef().apply($u.NoMods(), $u.TermName().apply("combine"), scala.collection.immutable.Nil..MODULE$, (List)(new .colon.colon((List)(new .colon.colon($u.ValDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TermName().apply("x"), $u.internal().reificationSupport().mkTypeTree(this.evidence$49$1$1.in($m$untyped).tpe()), $u.EmptyTree()), new .colon.colon($u.ValDef().apply($u.Modifiers().apply($u.internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)$u.TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), $u.TermName().apply("y"), $u.internal().reificationSupport().mkTypeTree(this.evidence$49$1$1.in($m$untyped).tpe()), $u.EmptyTree()), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), $u.internal().reificationSupport().mkTypeTree(this.evidence$49$1$1.in($m$untyped).tpe()), this.ops$2.plusplus().in($m$untyped).tree()), scala.collection.immutable.Nil..MODULE$)))))), scala.collection.immutable.Nil..MODULE$)), $u.Apply().apply($u.Select().apply($u.New().apply($u.Ident().apply((Names.NameApi)$u.TypeName().apply("$anon"))), (Names.NameApi)$u.TermName().apply("<init>")), scala.collection.immutable.Nil..MODULE$));
         }

         public $treecreator1$10(final TypeTags.WeakTypeTag evidence$49$1$1, final Exprs.Expr z$6$1, final ScalaAlgebra ops$2) {
            this.evidence$49$1$1 = evidence$49$1$1;
            this.z$6$1 = z$6$1;
            this.ops$2 = ops$2;
         }
      }


      final class $typecreator2$10 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$49$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Symbols.SymbolApi symdef$$lessrefinement$greater1 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectTerm($m$untyped.staticModule("spire.macros.ScalaAutoMacros").asModule().moduleClass(), "collectionMonoidImpl"), (Names.NameApi)$u.TypeName().apply("<refinement>"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(0L), true);
            $u.internal().reificationSupport().setInfo(symdef$$lessrefinement$greater1, (Types.TypeApi)$u.internal().reificationSupport().RefinedType((List)(new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $u.internal().reificationSupport().selectType($m$untyped.staticPackage("scala").asModule().moduleClass(), "AnyRef"), scala.collection.immutable.Nil..MODULE$), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("spire")), $m$untyped.staticPackage("spire.algebra")), $m$untyped.staticModule("spire.algebra.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("spire.algebra.package").asModule().moduleClass(), "Monoid"), (List)(new .colon.colon(this.evidence$49$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), $u.internal().reificationSupport().newScopeWith(scala.collection.immutable.Nil..MODULE$), symdef$$lessrefinement$greater1));
            return (Types.TypeApi)$u.internal().reificationSupport().RefinedType((List)(new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $u.internal().reificationSupport().selectType($m$untyped.staticPackage("scala").asModule().moduleClass(), "AnyRef"), scala.collection.immutable.Nil..MODULE$), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("spire")), $m$untyped.staticPackage("spire.algebra")), $m$untyped.staticModule("spire.algebra.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("spire.algebra.package").asModule().moduleClass(), "Monoid"), (List)(new .colon.colon(this.evidence$49$1$1.in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), $u.internal().reificationSupport().newScopeWith(scala.collection.immutable.Nil..MODULE$), symdef$$lessrefinement$greater1);
         }

         public $typecreator2$10(final TypeTags.WeakTypeTag evidence$49$1$1) {
            this.evidence$49$1$1 = evidence$49$1$1;
         }
      }

      return $u.Expr().apply($m, new $treecreator1$10(evidence$49, z, ops), $u.WeakTypeTag().apply($m, new $typecreator2$10(evidence$49)));
   }

   private ScalaAutoMacros$() {
   }
}
