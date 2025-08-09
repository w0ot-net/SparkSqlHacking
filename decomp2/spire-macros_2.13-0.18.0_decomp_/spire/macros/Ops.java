package spire.macros;

import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.whitebox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015r!B\u0005\u000b\u0011\u0003ya!B\t\u000b\u0011\u0003\u0011\u0002\"\u0002\u0010\u0002\t\u0003y\u0002\"\u0002\u0011\u0002\t\u0003\t\u0003b\u0002\u001a\u0002\u0005\u0004%\ta\r\u0005\u0007o\u0005\u0001\u000b\u0011\u0002\u001b\t\u000ba\nA\u0011A\u001d\t\u000b1\fA\u0011A7\t\u000bu\fA\u0011\u0001@\u0002\u0007=\u00038O\u0003\u0002\f\u0019\u00051Q.Y2s_NT\u0011!D\u0001\u0006gBL'/Z\u0002\u0001!\t\u0001\u0012!D\u0001\u000b\u0005\ry\u0005o]\n\u0004\u0003MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001b;5\t1D\u0003\u0002\u001d\u0015\u0005IQ.Y2iS:L7\u000f^\u0005\u0003#m\ta\u0001P5oSRtD#A\b\u0002\tU,7o\u0019\u000b\u0003E5\u0002\"a\t\u0016\u000f\u0005\u0011B\u0003CA\u0013\u0016\u001b\u00051#BA\u0014\u000f\u0003\u0019a$o\\8u}%\u0011\u0011&F\u0001\u0007!J,G-\u001a4\n\u0005-b#AB*ue&twM\u0003\u0002*+!)af\u0001a\u0001_\u0005\t1\r\u0005\u0002\u0015a%\u0011\u0011'\u0006\u0002\u0005\u0007\"\f'/A\u0007pa\u0016\u0014\u0018\r^8s\u001d\u0006lWm]\u000b\u0002iA!1%\u000e\u0012#\u0013\t1DFA\u0002NCB\fab\u001c9fe\u0006$xN\u001d(b[\u0016\u001c\b%A\u0002fcZ,2AO/h)\tY\u0004\t\u0006\u0002=SR\u0011QH\u0016\t\u0004}1\u001bfBA A\u0019\u0001AQA\f\u0004A\u0002\u0005\u0003\"AQ%\u000f\u0005\r;eB\u0001#G\u001d\t)S)C\u0001\u000e\u0013\tYA\"\u0003\u0002I\u0015\u000511m\\7qCRL!AS&\u0003\u000f\r{g\u000e^3yi*\u0011\u0001JC\u0005\u0003\u001b:\u0013A!\u0012=qe&\u0011q\n\u0015\u0002\b\u00032L\u0017m]3t\u0015\tY\u0011K\u0003\u0002S+\u00059!/\u001a4mK\u000e$\bC\u0001\u000bU\u0013\t)VCA\u0004C_>dW-\u00198\t\u000b]3\u0001\u0019\u0001-\u0002\u0005\u00154\bc\u0001 M3B!AC\u0017/g\u0013\tYVC\u0001\u0007%KF$3m\u001c7p]\u0012*\u0017\u000f\u0005\u0002@;\u0012)aL\u0002b\u0001?\n\t\u0011)\u0005\u0002aGB\u0011A#Y\u0005\u0003EV\u0011qAT8uQ&tw\r\u0005\u0002\u0015I&\u0011Q-\u0006\u0002\u0004\u0003:L\bCA h\t\u0015AgA1\u0001`\u0005\u0005\u0011\u0005\"\u00026\u0007\u0001\u0004Y\u0017a\u0001:igB\u0019a\b\u00144\u0002\t9,\u0017O^\u000b\u0004]bTHCA8t)\t\u00018\u0010\u0006\u0002riB\u0019!\u000fT*\u000f\u0005}\u001a\b\"\u0002\u0018\b\u0001\u0004\t\u0005\"B,\b\u0001\u0004)\bc\u0001:MmB!ACW<z!\ty\u0004\u0010B\u0003_\u000f\t\u0007q\f\u0005\u0002@u\u0012)\u0001n\u0002b\u0001?\")!n\u0002a\u0001yB\u0019!\u000fT=\u0002\u0019\tLgn\u001c9XSRDWI\u001e\u001a\u0016\u000f}\f\u0019#!\u0007\u0002\u000eQ!\u0011\u0011AA\u0005)\u0011\t\u0019!!\b\u0015\t\u0005\u0015\u0011\u0011\u0003\t\u0006\u0003\u000fa\u00151\u0002\b\u0004\u007f\u0005%\u0001\"\u0002\u0018\t\u0001\u0004\t\u0005cA \u0002\u000e\u00111\u0011q\u0002\u0005C\u0002}\u0013\u0011A\u0015\u0005\b\u0003'A\u0001\u0019AA\u000b\u0003\r)g/\r\t\u0006\u0003\u000fa\u0015q\u0003\t\u0004\u007f\u0005eAABA\u000e\u0011\t\u0007qLA\u0002FmFBaA\u001b\u0005A\u0002\u0005}\u0001#BA\u0004\u0019\u0006\u0005\u0002cA \u0002$\u0011)a\f\u0003b\u0001?\u0002"
)
public final class Ops {
   public static Exprs.Expr binopWithEv2(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev1) {
      return Ops$.MODULE$.binopWithEv2(c, rhs, ev1);
   }

   public static Exprs.Expr neqv(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return Ops$.MODULE$.neqv(c, rhs, ev);
   }

   public static Exprs.Expr eqv(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return Ops$.MODULE$.eqv(c, rhs, ev);
   }

   public static Map operatorNames() {
      return Ops$.MODULE$.operatorNames();
   }

   public static String uesc(final char c) {
      return Ops$.MODULE$.uesc(c);
   }

   public static Names.TermNameApi findMethodName(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.findMethodName(c);
   }

   public static Trees.TreeApi unpackWithoutEv(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.unpackWithoutEv(c);
   }

   public static Tuple2 unpack(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.unpack(c);
   }

   public static Exprs.Expr flip(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs) {
      return Ops$.MODULE$.flip(c, rhs);
   }

   public static Exprs.Expr binopWithSelfLift(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs, final TypeTags.WeakTypeTag evidence$2) {
      return Ops$.MODULE$.binopWithSelfLift(c, rhs, evidence$2);
   }

   public static Exprs.Expr binopWithLift(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$1) {
      return Ops$.MODULE$.binopWithLift(c, rhs, ev1, evidence$1);
   }

   public static Exprs.Expr rbinopWithEv(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr lhs, final Exprs.Expr ev) {
      return Ops$.MODULE$.rbinopWithEv(c, lhs, ev);
   }

   public static Exprs.Expr binopWithEv(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return Ops$.MODULE$.binopWithEv(c, rhs, ev);
   }

   public static Exprs.Expr handleBinopWithChild(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs, final String childName) {
      return Ops$.MODULE$.handleBinopWithChild(c, rhs, childName);
   }

   public static Exprs.Expr binopWithScalar(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs) {
      return Ops$.MODULE$.binopWithScalar(c, rhs);
   }

   public static Exprs.Expr handleUnopWithChild(final scala.reflect.macros.blackbox.Context c, final String childName) {
      return Ops$.MODULE$.handleUnopWithChild(c, childName);
   }

   public static Exprs.Expr unopWithScalar0(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.unopWithScalar0(c);
   }

   public static Exprs.Expr unopWithScalar(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.unopWithScalar(c);
   }

   public static Exprs.Expr rbinop(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr lhs) {
      return Ops$.MODULE$.rbinop(c, lhs);
   }

   public static Exprs.Expr binop(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr rhs) {
      return Ops$.MODULE$.binop(c, rhs);
   }

   public static Exprs.Expr unopWithEv2(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr ev1) {
      return Ops$.MODULE$.unopWithEv2(c, ev1);
   }

   public static Exprs.Expr unopWithEv(final scala.reflect.macros.blackbox.Context c, final Exprs.Expr ev) {
      return Ops$.MODULE$.unopWithEv(c, ev);
   }

   public static Exprs.Expr unop0(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.unop0(c);
   }

   public static Exprs.Expr unop(final scala.reflect.macros.blackbox.Context c) {
      return Ops$.MODULE$.unop(c);
   }
}
