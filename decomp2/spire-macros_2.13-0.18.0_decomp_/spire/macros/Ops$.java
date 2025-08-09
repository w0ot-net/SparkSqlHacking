package spire.macros;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.api.Exprs;
import scala.reflect.api.Internals;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.blackbox.Context;
import scala.runtime.BoxesRunTime;
import spire.macros.machinist.DefaultOps$;

public final class Ops$ implements spire.macros.machinist.Ops {
   public static final Ops$ MODULE$ = new Ops$();
   private static final Map operatorNames;

   static {
      spire.macros.machinist.Ops.$init$(MODULE$);
      operatorNames = (Map)DefaultOps$.MODULE$.operatorNames().$plus$plus((IterableOnce).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2("$bar$plus$bar$qmark$qmark", "opIsDefined"), new Tuple2("$bar$minus$bar$qmark$qmark", "opInverseIsDefined"), new Tuple2("$bar$plus$bar$qmark", "partialOp"), new Tuple2("$bar$minus$bar$qmark", "partialOpInverse"), new Tuple2("$qmark$bar$plus$bar$greater", "partialActl"), new Tuple2("$qmark$qmark$bar$plus$bar$greater", "actlIsDefined"), new Tuple2("$less$bar$plus$bar$qmark", "partialActr"), new Tuple2("$less$bar$plus$bar$qmark$qmark", "actrIsDefined"), new Tuple2(MODULE$.uesc('√'), "sqrt"), new Tuple2(MODULE$.uesc('≡'), "eqv"), new Tuple2(MODULE$.uesc('≠'), "neqv"), new Tuple2(MODULE$.uesc('≤'), "lteqv"), new Tuple2(MODULE$.uesc('≥'), "gteqv"), new Tuple2(MODULE$.uesc('∧'), "meet"), new Tuple2(MODULE$.uesc('∨'), "join"), new Tuple2(MODULE$.uesc('⊃'), "imp"), new Tuple2(MODULE$.uesc('¬'), "complement"), new Tuple2(MODULE$.uesc('⊻'), "xor"), new Tuple2(MODULE$.uesc('⊼'), "nand"), new Tuple2(MODULE$.uesc('⊽'), "nor")}))));
   }

   public Exprs.Expr unop(final Context c) {
      return spire.macros.machinist.Ops.unop$(this, c);
   }

   public Exprs.Expr unop0(final Context c) {
      return spire.macros.machinist.Ops.unop0$(this, c);
   }

   public Exprs.Expr unopWithEv(final Context c, final Exprs.Expr ev) {
      return spire.macros.machinist.Ops.unopWithEv$(this, c, ev);
   }

   public Exprs.Expr unopWithEv2(final Context c, final Exprs.Expr ev1) {
      return spire.macros.machinist.Ops.unopWithEv2$(this, c, ev1);
   }

   public Exprs.Expr binop(final Context c, final Exprs.Expr rhs) {
      return spire.macros.machinist.Ops.binop$(this, c, rhs);
   }

   public Exprs.Expr rbinop(final Context c, final Exprs.Expr lhs) {
      return spire.macros.machinist.Ops.rbinop$(this, c, lhs);
   }

   public Exprs.Expr unopWithScalar(final Context c) {
      return spire.macros.machinist.Ops.unopWithScalar$(this, c);
   }

   public Exprs.Expr unopWithScalar0(final Context c) {
      return spire.macros.machinist.Ops.unopWithScalar0$(this, c);
   }

   public Exprs.Expr handleUnopWithChild(final Context c, final String childName) {
      return spire.macros.machinist.Ops.handleUnopWithChild$(this, c, childName);
   }

   public Exprs.Expr binopWithScalar(final Context c, final Exprs.Expr rhs) {
      return spire.macros.machinist.Ops.binopWithScalar$(this, c, rhs);
   }

   public Exprs.Expr handleBinopWithChild(final Context c, final Exprs.Expr rhs, final String childName) {
      return spire.macros.machinist.Ops.handleBinopWithChild$(this, c, rhs, childName);
   }

   public Exprs.Expr binopWithEv(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return spire.macros.machinist.Ops.binopWithEv$(this, c, rhs, ev);
   }

   public Exprs.Expr rbinopWithEv(final Context c, final Exprs.Expr lhs, final Exprs.Expr ev) {
      return spire.macros.machinist.Ops.rbinopWithEv$(this, c, lhs, ev);
   }

   public Exprs.Expr binopWithLift(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$1) {
      return spire.macros.machinist.Ops.binopWithLift$(this, c, rhs, ev1, evidence$1);
   }

   public Exprs.Expr binopWithSelfLift(final Context c, final Exprs.Expr rhs, final TypeTags.WeakTypeTag evidence$2) {
      return spire.macros.machinist.Ops.binopWithSelfLift$(this, c, rhs, evidence$2);
   }

   public Exprs.Expr flip(final Context c, final Exprs.Expr rhs) {
      return spire.macros.machinist.Ops.flip$(this, c, rhs);
   }

   public Tuple2 unpack(final Context c) {
      return spire.macros.machinist.Ops.unpack$(this, c);
   }

   public Trees.TreeApi unpackWithoutEv(final Context c) {
      return spire.macros.machinist.Ops.unpackWithoutEv$(this, c);
   }

   public Names.TermNameApi findMethodName(final Context c) {
      return spire.macros.machinist.Ops.findMethodName$(this, c);
   }

   public String uesc(final char c) {
      return scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString("$u%04X"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(c)}));
   }

   public Map operatorNames() {
      return operatorNames;
   }

   public Exprs.Expr eqv(final scala.reflect.macros.whitebox.Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      Tuple2 var8 = this.unpack(c);
      if (var8 != null) {
         Trees.TreeApi e = (Trees.TreeApi)var8._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var8._2();
         Tuple2 var4 = new Tuple2(e, lhs);
         Trees.TreeApi e = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         return c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(e, c.universe().TermName().apply("eqv")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(lhs, new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(rhs), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Boolean());
      } else {
         throw new MatchError(var8);
      }
   }

   public Exprs.Expr neqv(final scala.reflect.macros.whitebox.Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      Tuple2 var8 = this.unpack(c);
      if (var8 != null) {
         Trees.TreeApi e = (Trees.TreeApi)var8._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var8._2();
         Tuple2 var4 = new Tuple2(e, lhs);
         Trees.TreeApi e = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         return c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(e, c.universe().TermName().apply("neqv")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(lhs, new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(rhs), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Boolean());
      } else {
         throw new MatchError(var8);
      }
   }

   public Exprs.Expr binopWithEv2(final scala.reflect.macros.whitebox.Context c, final Exprs.Expr rhs, final Exprs.Expr ev1) {
      Tuple2 var8 = this.unpack(c);
      if (var8 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var8._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var8._2();
         Tuple2 var4 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Apply().apply(c.universe().Select().apply(ev, (Names.NameApi)this.findMethodName(c)), (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs, rhs.tree()})))), (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{ev1.tree()}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by binopWithEv2 in Syntax.scala:91:28");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$1()));
      } else {
         throw new MatchError(var8);
      }
   }

   private Ops$() {
   }
}
