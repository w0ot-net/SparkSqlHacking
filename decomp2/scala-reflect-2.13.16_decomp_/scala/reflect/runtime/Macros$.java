package scala.reflect.runtime;

import scala.collection.immutable.;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.blackbox.Context;

public final class Macros$ {
   public static final Macros$ MODULE$ = new Macros$();

   public Exprs.Expr currentMirror(final Context c) {
      Trees.TreeApi runtimeClass = c.reifyEnclosingRuntimeClass();
      if (runtimeClass.isEmpty()) {
         throw c.abort(c.enclosingPosition(), "call site does not have an enclosing class");
      } else {
         Trees.SelectApi scalaPackage = c.universe().Select().apply(c.universe().Ident().apply((Names.NameApi)c.universe().TermName().apply("_root_")), (Names.NameApi)c.universe().TermName().apply("scala"));
         Trees.SelectApi runtimeUniverse = c.universe().Select().apply(c.universe().Select().apply(c.universe().Select().apply(scalaPackage, (Names.NameApi)c.universe().TermName().apply("reflect")), (Names.NameApi)c.universe().TermName().apply("runtime")), (Names.NameApi)c.universe().TermName().apply("universe"));
         Trees.ApplyApi currentMirror = c.universe().Apply().apply(c.universe().Select().apply(runtimeUniverse, (Names.NameApi)c.universe().TermName().apply("runtimeMirror")), new .colon.colon(c.universe().Select().apply(runtimeClass, (Names.NameApi)c.universe().TermName().apply("getClassLoader")), scala.collection.immutable.Nil..MODULE$));
         return c.Expr(currentMirror, c.WeakTypeTag().Nothing());
      }
   }

   private Macros$() {
   }
}
