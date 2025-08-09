package spire.macros.fpf;

import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.whitebox.Context;

public final class Fuser$ {
   public static final Fuser$ MODULE$ = new Fuser$();

   public Fuser apply(final Context ctx, final TypeTags.WeakTypeTag evidence$1) {
      return new Fuser(ctx, evidence$1) {
         private final Context c;
         private final TypeTags.WeakTypeTag A;
         private volatile Fuser.Approx$ Approx$module;
         private volatile Fuser.Fused$ Fused$module;

         public Trees.TreeApi intLit(final int n) {
            return Fuser.intLit$(this, n);
         }

         public Fuser.Fused negate(final Trees.TreeApi sub, final Trees.TreeApi ev) {
            return Fuser.negate$(this, sub, ev);
         }

         public Fuser.Fused abs(final Trees.TreeApi sub, final Trees.TreeApi ev) {
            return Fuser.abs$(this, sub, ev);
         }

         public Fuser.Fused sqrt(final Trees.TreeApi tree, final Trees.TreeApi ev) {
            return Fuser.sqrt$(this, tree, ev);
         }

         public Fuser.Fused plus(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
            return Fuser.plus$(this, lhs, rhs, ev);
         }

         public Fuser.Fused minus(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
            return Fuser.minus$(this, lhs, rhs, ev);
         }

         public Fuser.Fused times(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
            return Fuser.times$(this, lhs, rhs, ev);
         }

         public Fuser.Fused divide(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
            return Fuser.divide$(this, lhs, rhs, ev);
         }

         public Trees.TreeApi sign(final Trees.TreeApi tree, final Trees.TreeApi signed) {
            return Fuser.sign$(this, tree, signed);
         }

         public Trees.TreeApi comp(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi rng, final Trees.TreeApi signed, final Cmp cmp) {
            return Fuser.comp$(this, lhs, rhs, rng, signed, cmp);
         }

         public Fuser.Approx$ Approx() {
            if (this.Approx$module == null) {
               this.Approx$lzycompute$1();
            }

            return this.Approx$module;
         }

         public Fuser.Fused$ Fused() {
            if (this.Fused$module == null) {
               this.Fused$lzycompute$1();
            }

            return this.Fused$module;
         }

         public Context c() {
            return this.c;
         }

         public TypeTags.WeakTypeTag A() {
            return this.A;
         }

         private final void Approx$lzycompute$1() {
            synchronized(this){}

            try {
               if (this.Approx$module == null) {
                  this.Approx$module = new Fuser.Approx$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         private final void Fused$lzycompute$1() {
            synchronized(this){}

            try {
               if (this.Fused$module == null) {
                  this.Fused$module = new Fuser.Fused$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         public {
            Fuser.$init$(this);
            this.c = ctx$1$1;
            this.A = this.c().weakTypeTag(evidence$1$1$1.in(this.c().universe().rootMirror()));
         }
      };
   }

   private Fuser$() {
   }
}
