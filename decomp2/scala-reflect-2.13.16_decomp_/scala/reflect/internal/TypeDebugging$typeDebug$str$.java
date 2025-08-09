package scala.reflect.internal;

import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.runtime.Statics;

public class TypeDebugging$typeDebug$str$ {
   // $FF: synthetic field
   private final TypeDebugging.typeDebug$ $outer;

   public String parentheses(final List xs) {
      String mkString_end = ")";
      String mkString_sep = ", ";
      String mkString_start = "(";
      if (xs == null) {
         throw null;
      } else {
         return IterableOnceOps.mkString$(xs, mkString_start, mkString_sep, mkString_end);
      }
   }

   public String params(final List params) {
      String paramsStrPre = params.nonEmpty() && ((HasFlags)params.head()).isImplicit() ? "(implicit " : "(";
      Object var10000;
      if (params == .MODULE$) {
         var10000 = .MODULE$;
      } else {
         scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)params.head()).defStringWithoutImplicit(), .MODULE$);
         scala.collection.immutable..colon.colon map_t = map_h;

         for(List map_rest = (List)params.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
            scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).defStringWithoutImplicit(), .MODULE$);
            map_t.next_$eq(map_nx);
            map_t = map_nx;
         }

         Statics.releaseFence();
         var10000 = map_h;
      }

      Object var9 = null;
      Object var10 = null;
      Object var11 = null;
      Object var12 = null;
      String mkString_end = ")";
      String mkString_sep = ", ";
      return IterableOnceOps.mkString$((IterableOnceOps)var10000, paramsStrPre, mkString_sep, mkString_end);
   }

   public String brackets(final List xs) {
      if (xs.isEmpty()) {
         return "";
      } else {
         String mkString_end = "]";
         String mkString_sep = ", ";
         String mkString_start = "[";
         return IterableOnceOps.mkString$(xs, mkString_start, mkString_sep, mkString_end);
      }
   }

   public String tparams(final List tparams) {
      if (tparams == null) {
         throw null;
      } else {
         Object var10001;
         if (tparams == .MODULE$) {
            var10001 = .MODULE$;
         } else {
            Types.Type var6 = (Types.Type)tparams.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$tparams$1(this, var6), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)tparams.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var6 = (Types.Type)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$tparams$1(this, var6), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10001 = map_h;
         }

         Object var7 = null;
         Object var8 = null;
         Object var9 = null;
         Object var10 = null;
         return this.brackets((List)var10001);
      }
   }

   public String parents(final List ps) {
      if (ps == null) {
         throw null;
      } else {
         Object var10000;
         if (ps == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            Types.Type var7 = (Types.Type)ps.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$parents$1(this, var7), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)ps.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var7 = (Types.Type)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$parents$1(this, var7), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var8 = null;
         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         String mkString_sep = " with ";
         return ((IterableOnceOps)var10000).mkString("", mkString_sep, "");
      }
   }

   public String refine(final Scopes.Scope defs) {
      List var10000 = defs.toList();
      String mkString_end = "}";
      String mkString_sep = " ;\n ";
      String mkString_start = "{";
      if (var10000 == null) {
         throw null;
      } else {
         return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
      }
   }

   public String bounds(final Types.Type lo, final Types.Type hi) {
      String lo_s = lo.isNothing() ? "" : (new StringBuilder(4)).append(" >: ").append(lo).toString();
      SymbolTable var10000 = this.$outer.scala$reflect$internal$TypeDebugging$typeDebug$$$outer();
      if (var10000 == null) {
         throw null;
      } else {
         String hi_s = Types.typeIsAnyOrJavaObject$(var10000, hi) ? "" : (new StringBuilder(4)).append(" <: ").append(hi).toString();
         return (new StringBuilder(0)).append(lo_s).append(hi_s).toString();
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$params$1(final Symbols.Symbol x$3) {
      return x$3.defStringWithoutImplicit();
   }

   // $FF: synthetic method
   public static final String $anonfun$tparams$1(final TypeDebugging$typeDebug$str$ $this, final Types.Type tp) {
      return $this.$outer.scala$reflect$internal$TypeDebugging$typeDebug$$debug(tp);
   }

   // $FF: synthetic method
   public static final String $anonfun$parents$1(final TypeDebugging$typeDebug$str$ $this, final Types.Type tp) {
      return $this.$outer.scala$reflect$internal$TypeDebugging$typeDebug$$debug(tp);
   }

   public TypeDebugging$typeDebug$str$(final TypeDebugging.typeDebug$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
