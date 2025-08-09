package spire.macros.machinist;

import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.blackbox.Context;

public final class DefaultOps$ implements Ops, DefaultOperatorNames {
   public static final DefaultOps$ MODULE$ = new DefaultOps$();
   private static Map operatorNames;

   static {
      Ops.$init$(MODULE$);
      DefaultOperatorNames.$init$(MODULE$);
   }

   public Exprs.Expr unop(final Context c) {
      return Ops.unop$(this, c);
   }

   public Exprs.Expr unop0(final Context c) {
      return Ops.unop0$(this, c);
   }

   public Exprs.Expr unopWithEv(final Context c, final Exprs.Expr ev) {
      return Ops.unopWithEv$(this, c, ev);
   }

   public Exprs.Expr unopWithEv2(final Context c, final Exprs.Expr ev1) {
      return Ops.unopWithEv2$(this, c, ev1);
   }

   public Exprs.Expr binop(final Context c, final Exprs.Expr rhs) {
      return Ops.binop$(this, c, rhs);
   }

   public Exprs.Expr rbinop(final Context c, final Exprs.Expr lhs) {
      return Ops.rbinop$(this, c, lhs);
   }

   public Exprs.Expr unopWithScalar(final Context c) {
      return Ops.unopWithScalar$(this, c);
   }

   public Exprs.Expr unopWithScalar0(final Context c) {
      return Ops.unopWithScalar0$(this, c);
   }

   public Exprs.Expr handleUnopWithChild(final Context c, final String childName) {
      return Ops.handleUnopWithChild$(this, c, childName);
   }

   public Exprs.Expr binopWithScalar(final Context c, final Exprs.Expr rhs) {
      return Ops.binopWithScalar$(this, c, rhs);
   }

   public Exprs.Expr handleBinopWithChild(final Context c, final Exprs.Expr rhs, final String childName) {
      return Ops.handleBinopWithChild$(this, c, rhs, childName);
   }

   public Exprs.Expr binopWithEv(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return Ops.binopWithEv$(this, c, rhs, ev);
   }

   public Exprs.Expr rbinopWithEv(final Context c, final Exprs.Expr lhs, final Exprs.Expr ev) {
      return Ops.rbinopWithEv$(this, c, lhs, ev);
   }

   public Exprs.Expr binopWithLift(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$1) {
      return Ops.binopWithLift$(this, c, rhs, ev1, evidence$1);
   }

   public Exprs.Expr binopWithSelfLift(final Context c, final Exprs.Expr rhs, final TypeTags.WeakTypeTag evidence$2) {
      return Ops.binopWithSelfLift$(this, c, rhs, evidence$2);
   }

   public Exprs.Expr flip(final Context c, final Exprs.Expr rhs) {
      return Ops.flip$(this, c, rhs);
   }

   public Tuple2 unpack(final Context c) {
      return Ops.unpack$(this, c);
   }

   public Trees.TreeApi unpackWithoutEv(final Context c) {
      return Ops.unpackWithoutEv$(this, c);
   }

   public Names.TermNameApi findMethodName(final Context c) {
      return Ops.findMethodName$(this, c);
   }

   public Map operatorNames() {
      return operatorNames;
   }

   public void spire$macros$machinist$DefaultOperatorNames$_setter_$operatorNames_$eq(final Map x$1) {
      operatorNames = x$1;
   }

   private DefaultOps$() {
   }
}
