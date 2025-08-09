package spire.macros;

import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;

public final class compat$ {
   public static final compat$ MODULE$ = new compat$();

   public Names.TermNameApi freshTermName(final Context c, final String s) {
      return c.universe().TermName().apply(c.freshName(s));
   }

   public Names.TermNameApi termName(final Context c, final String s) {
      return c.universe().TermName().apply(s);
   }

   public Trees.TreeApi typeCheck(final Context c, final Trees.TreeApi t) {
      return c.typecheck(t, c.typecheck$default$2(), c.typecheck$default$3(), c.typecheck$default$4(), c.typecheck$default$5(), c.typecheck$default$6());
   }

   public Trees.TreeApi resetLocalAttrs(final Context c, final Trees.TreeApi t) {
      return c.untypecheck(t);
   }

   public Trees.TypeTreeApi setOrig(final Context c, final Trees.TypeTreeApi tt, final Trees.TreeApi t) {
      return ((Universe.MacroInternalApi)c.universe().internal()).setOriginal(tt, t);
   }

   public Trees.TreeApi predef(final Context c) {
      return c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("scala")), c.universe().TermName().apply("Predef"));
   }

   private compat$() {
   }
}
