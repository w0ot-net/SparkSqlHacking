package spire.macros;

import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;

public final class Checked$ {
   public static final Checked$ MODULE$ = new Checked$();

   public Exprs.Expr tryOrElseImpl(final Context c, final Exprs.Expr n, final Exprs.Expr orElse, final TypeTags.WeakTypeTag evidence$1) {
      CheckedRewriter var9 = new CheckedRewriter(c);
      Trees.TreeApi tree = var9.rewriteSafe(n.tree(), orElse.tree(), evidence$1);
      Trees.TreeApi resetTree = compat$.MODULE$.resetLocalAttrs(c, tree);
      return c.Expr(resetTree, evidence$1);
   }

   public Exprs.Expr checkedImpl(final Context c, final Exprs.Expr n, final TypeTags.WeakTypeTag evidence$2) {
      return this.tryOrElseImpl(c, n, c.Expr(c.universe().Throw().apply(c.universe().internal().reificationSupport().SyntacticNew().apply(.MODULE$, (List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticSelectType().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("spire"), false), c.universe().TermName().apply("macros")), c.universe().TypeName().apply("ArithmeticOverflowException")), .MODULE$)), c.universe().noSelfType(), .MODULE$)), evidence$2), evidence$2);
   }

   public Exprs.Expr optionImpl(final Context c, final Exprs.Expr n, final TypeTags.WeakTypeTag evidence$3) {
      Trees.TreeApi var10003 = c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("Option"), false), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(n.tree(), .MODULE$)), .MODULE$)));
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$1 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$3$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Option"), (List)(new scala.collection.immutable..colon.colon(this.evidence$3$1$1.in($m$untyped).tpe(), .MODULE$)));
         }

         public $typecreator1$1(final TypeTags.WeakTypeTag evidence$3$1$1) {
            this.evidence$3$1$1 = evidence$3$1$1;
         }
      }

      Exprs.Expr var10002 = c.Expr(var10003, $u.WeakTypeTag().apply($m, new $typecreator1$1(evidence$3)));
      Trees.IdentApi var10004 = c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("None"), false);
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator2$1 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$3$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Option"), (List)(new scala.collection.immutable..colon.colon(this.evidence$3$1$1.in($m$untyped).tpe(), .MODULE$)));
         }

         public $typecreator2$1(final TypeTags.WeakTypeTag evidence$3$1$1) {
            this.evidence$3$1$1 = evidence$3$1$1;
         }
      }

      Exprs.Expr var12 = c.Expr(var10004, $u.WeakTypeTag().apply($m, new $typecreator2$1(evidence$3)));
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator3$1 extends TypeCreator {
         private final TypeTags.WeakTypeTag evidence$3$1$1;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Option"), (List)(new scala.collection.immutable..colon.colon(this.evidence$3$1$1.in($m$untyped).tpe(), .MODULE$)));
         }

         public $typecreator3$1(final TypeTags.WeakTypeTag evidence$3$1$1) {
            this.evidence$3$1$1 = evidence$3$1$1;
         }
      }

      return this.tryOrElseImpl(c, var10002, var12, $u.WeakTypeTag().apply($m, new $typecreator3$1(evidence$3)));
   }

   public Exprs.Expr tryOrReturnImpl(final Context c, final Exprs.Expr n, final Exprs.Expr orElse, final TypeTags.WeakTypeTag evidence$4) {
      CheckedRewriter var9 = new CheckedRewriter(c);
      Trees.TreeApi tree = var9.rewriteFast(n.tree(), orElse.tree(), evidence$4);
      Trees.TreeApi resetTree = compat$.MODULE$.resetLocalAttrs(c, tree);
      return c.Expr(resetTree, evidence$4);
   }

   private Checked$() {
   }
}
