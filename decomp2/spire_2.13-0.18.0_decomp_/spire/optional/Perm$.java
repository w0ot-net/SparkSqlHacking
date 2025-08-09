package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Group;
import java.lang.invoke.SerializedLambda;
import scala.Predef;
import scala.Predef.;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import spire.algebra.Action;
import spire.algebra.partial.PartialAction;

public final class Perm$ {
   public static final Perm$ MODULE$ = new Perm$();
   private static final Eq PermEq = new Eq() {
      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return Eq.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return Eq.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return Eq.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return Eq.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return Eq.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return Eq.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return Eq.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.eqv$mcV$sp$(this, x, y);
      }

      public boolean neqv(final Object x, final Object y) {
         return Eq.neqv$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Eq.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Eq.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Eq.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Eq.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Eq.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Eq.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Eq.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.neqv$mcV$sp$(this, x, y);
      }

      public boolean eqv(final Perm p, final Perm r) {
         boolean var4;
         label23: {
            Map var10000 = p.spire$optional$Perm$$mapping();
            Map var3 = r.spire$optional$Perm$$mapping();
            if (var10000 == null) {
               if (var3 == null) {
                  break label23;
               }
            } else if (var10000.equals(var3)) {
               break label23;
            }

            var4 = false;
            return var4;
         }

         var4 = true;
         return var4;
      }

      public {
         Eq.$init$(this);
      }
   };
   private static final Action PermIntAction = new PermIntAction();
   private static final Group PermGroup = new PermGroup();

   public Perm apply(final Map mapping) {
      boolean var3;
      Predef var10000;
      label17: {
         label16: {
            var10000 = .MODULE$;
            Set var10001 = mapping.values().toSet();
            Set var2 = mapping.keySet();
            if (var10001 == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (var10001.equals(var2)) {
               break label16;
            }

            var3 = false;
            break label17;
         }

         var3 = true;
      }

      var10000.require(var3, () -> "Image and preimage must be the same.");
      .MODULE$.require(mapping.keys().forall((JFunction1.mcZI.sp)(x$4) -> x$4 >= 0), () -> "Perm indices must be non-negative.");
      return new Perm((Map)mapping.filter(scala.Function..MODULE$.tupled((JFunction2.mcZII.sp)(x$5, x$6) -> x$5 != x$6)));
   }

   public Perm apply(final Seq pairs) {
      return this.apply((Map).MODULE$.Map().apply(pairs));
   }

   public Perm apply(final int n0, final int n1, final Seq ns) {
      Seq cycles = (Seq)((SeqOps)ns.$plus$colon(BoxesRunTime.boxToInteger(n1))).$plus$colon(BoxesRunTime.boxToInteger(n0));
      .MODULE$.require(cycles.size() == ((SeqOps)cycles.distinct()).size(), () -> "Cycle must not repeat elements");
      return this.apply(((IterableOnceOps)((IterableOps)((SeqOps)ns.$plus$colon(BoxesRunTime.boxToInteger(n1))).$plus$colon(BoxesRunTime.boxToInteger(n0))).zip((IterableOnce)((SeqOps)ns.$plus$colon(BoxesRunTime.boxToInteger(n1))).$colon$plus(BoxesRunTime.boxToInteger(n0)))).toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Eq PermEq() {
      return PermEq;
   }

   public Action PermIntAction() {
      return PermIntAction;
   }

   public Group PermGroup() {
      return PermGroup;
   }

   public PartialAction PermSeqPartialAction(final Factory cbf) {
      return new PermSeqPartialAction(cbf);
   }

   private Perm$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
