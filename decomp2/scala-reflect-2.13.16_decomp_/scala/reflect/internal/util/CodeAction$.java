package scala.reflect.internal.util;

import java.io.Serializable;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.runtime.ModuleSerializationProxy;
import scala.util.matching.Regex;

public final class CodeAction$ implements Serializable {
   public static final CodeAction$ MODULE$ = new CodeAction$();
   private static Regex parens;
   private static volatile boolean bitmap$0;

   public List apply(final String title, final Position pos, final String newText, final String desc, final Function0 check) {
      return (List)(check.apply$mcZ$sp() ? new .colon.colon(new CodeAction(title, new Some(desc), new .colon.colon(new TextEdit(pos, newText), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$);
   }

   public boolean apply$default$5() {
      return true;
   }

   private Regex parens$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            String augmentString_x = "\\(.*\\)";
            String var10000 = augmentString_x;
            Object var5 = null;
            String r$extension_$this = var10000;
            Regex var7 = new Regex(r$extension_$this, scala.collection.immutable.Nil..MODULE$);
            Object var6 = null;
            parens = var7;
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return parens;
   }

   private Regex parens() {
      return !bitmap$0 ? this.parens$lzycompute() : parens;
   }

   public String maybeWrapInParens(final String s) {
      return s.contains(" ") && !this.parens().matches(s) ? (new StringBuilder(2)).append("(").append(s).append(")").toString() : s;
   }

   public String wrapInParens(final String s) {
      return !this.parens().matches(s) ? (new StringBuilder(2)).append("(").append(s).append(")").toString() : s;
   }

   public CodeAction apply(final String title, final Option description, final List edits) {
      return new CodeAction(title, description, edits);
   }

   public Option unapply(final CodeAction x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.title(), x$0.description(), x$0.edits())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CodeAction$.class);
   }

   private CodeAction$() {
   }
}
