package spire.macros.machinist;

import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.blackbox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQaG\u0001\u0005\u0002q\t!\u0002R3gCVdGo\u00149t\u0015\t)a!A\u0005nC\u000eD\u0017N\\5ti*\u0011q\u0001C\u0001\u0007[\u0006\u001c'o\\:\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001\u0001\u0005\u0002\r\u00035\tAA\u0001\u0006EK\u001a\fW\u000f\u001c;PaN\u001cB!A\b\u00161A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"\u0001\u0004\f\n\u0005]!!aA(qgB\u0011A\"G\u0005\u00035\u0011\u0011A\u0003R3gCVdGo\u00149fe\u0006$xN\u001d(b[\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u0001\f\u0001"
)
public final class DefaultOps {
   public static Map operatorNames() {
      return DefaultOps$.MODULE$.operatorNames();
   }

   public static Names.TermNameApi findMethodName(final Context c) {
      return DefaultOps$.MODULE$.findMethodName(c);
   }

   public static Trees.TreeApi unpackWithoutEv(final Context c) {
      return DefaultOps$.MODULE$.unpackWithoutEv(c);
   }

   public static Tuple2 unpack(final Context c) {
      return DefaultOps$.MODULE$.unpack(c);
   }

   public static Exprs.Expr flip(final Context c, final Exprs.Expr rhs) {
      return DefaultOps$.MODULE$.flip(c, rhs);
   }

   public static Exprs.Expr binopWithSelfLift(final Context c, final Exprs.Expr rhs, final TypeTags.WeakTypeTag evidence$2) {
      return DefaultOps$.MODULE$.binopWithSelfLift(c, rhs, evidence$2);
   }

   public static Exprs.Expr binopWithLift(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$1) {
      return DefaultOps$.MODULE$.binopWithLift(c, rhs, ev1, evidence$1);
   }

   public static Exprs.Expr rbinopWithEv(final Context c, final Exprs.Expr lhs, final Exprs.Expr ev) {
      return DefaultOps$.MODULE$.rbinopWithEv(c, lhs, ev);
   }

   public static Exprs.Expr binopWithEv(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return DefaultOps$.MODULE$.binopWithEv(c, rhs, ev);
   }

   public static Exprs.Expr handleBinopWithChild(final Context c, final Exprs.Expr rhs, final String childName) {
      return DefaultOps$.MODULE$.handleBinopWithChild(c, rhs, childName);
   }

   public static Exprs.Expr binopWithScalar(final Context c, final Exprs.Expr rhs) {
      return DefaultOps$.MODULE$.binopWithScalar(c, rhs);
   }

   public static Exprs.Expr handleUnopWithChild(final Context c, final String childName) {
      return DefaultOps$.MODULE$.handleUnopWithChild(c, childName);
   }

   public static Exprs.Expr unopWithScalar0(final Context c) {
      return DefaultOps$.MODULE$.unopWithScalar0(c);
   }

   public static Exprs.Expr unopWithScalar(final Context c) {
      return DefaultOps$.MODULE$.unopWithScalar(c);
   }

   public static Exprs.Expr rbinop(final Context c, final Exprs.Expr lhs) {
      return DefaultOps$.MODULE$.rbinop(c, lhs);
   }

   public static Exprs.Expr binop(final Context c, final Exprs.Expr rhs) {
      return DefaultOps$.MODULE$.binop(c, rhs);
   }

   public static Exprs.Expr unopWithEv2(final Context c, final Exprs.Expr ev1) {
      return DefaultOps$.MODULE$.unopWithEv2(c, ev1);
   }

   public static Exprs.Expr unopWithEv(final Context c, final Exprs.Expr ev) {
      return DefaultOps$.MODULE$.unopWithEv(c, ev);
   }

   public static Exprs.Expr unop0(final Context c) {
      return DefaultOps$.MODULE$.unop0(c);
   }

   public static Exprs.Expr unop(final Context c) {
      return DefaultOps$.MODULE$.unop(c);
   }
}
