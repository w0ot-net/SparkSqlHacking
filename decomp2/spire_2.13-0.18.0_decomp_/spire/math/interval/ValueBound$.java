package spire.math.interval;

import cats.kernel.Order;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;
import spire.math.Interval;
import spire.math.Interval$;

public final class ValueBound$ {
   public static final ValueBound$ MODULE$ = new ValueBound$();

   public Option unapply(final Bound b) {
      Object var2;
      if (b instanceof Open) {
         Open var4 = (Open)b;
         Object a = var4.a();
         var2 = new Some(a);
      } else if (b instanceof Closed) {
         Closed var6 = (Closed)b;
         Object a = var6.a();
         var2 = new Some(a);
      } else {
         var2 = .MODULE$;
      }

      return (Option)var2;
   }

   public Interval union2(final ValueBound v1, final ValueBound v2, final Order evidence$5) {
      int var4 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$5.compare(v1.a(), v2.a())))).sign());
      Interval var10000;
      switch (var4) {
         case -1:
            var10000 = Interval$.MODULE$.fromOrderedBounds(v1, v2, evidence$5);
            break;
         case 0:
            var10000 = !v1.isClosed() && !v2.isClosed() ? Interval$.MODULE$.empty(evidence$5) : Interval$.MODULE$.point(v1.a(), evidence$5);
            break;
         case 1:
            var10000 = Interval$.MODULE$.fromOrderedBounds(v2, v1, evidence$5);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var4));
      }

      return var10000;
   }

   public Interval union3_1approx2_2less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$6) {
      return v1.isClosed() ? Interval$.MODULE$.fromOrderedBounds(v1, v3, evidence$6) : Interval$.MODULE$.fromOrderedBounds(v2, v3, evidence$6);
   }

   public Interval union3_1less2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$7) {
      return v2.isClosed() ? Interval$.MODULE$.fromOrderedBounds(v1, v2, evidence$7) : Interval$.MODULE$.fromOrderedBounds(v1, v3, evidence$7);
   }

   public Interval union3_1approx2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$8) {
      return !v1.isClosed() && !v2.isClosed() && !v3.isClosed() ? Interval$.MODULE$.empty(evidence$8) : Interval$.MODULE$.point(v1.a(), evidence$8);
   }

   public Interval union3_1approx2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$9) {
      int var5 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$9.compare(v2.a(), v3.a())))).sign());
      Interval var10000;
      switch (var5) {
         case -1:
            var10000 = this.union3_1approx2_2less3(v1, v2, v3, evidence$9);
            break;
         case 0:
            var10000 = this.union3_1approx2_2approx3(v1, v2, v3, evidence$9);
            break;
         case 1:
            var10000 = this.union3_1less2_2approx3(v3, v1, v2, evidence$9);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var5));
      }

      return var10000;
   }

   public Interval union3_1less2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$10) {
      int var5 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$10.compare(v2.a(), v3.a())))).sign());
      Interval var10000;
      switch (var5) {
         case -1:
            var10000 = Interval$.MODULE$.fromOrderedBounds(v1, v3, evidence$10);
            break;
         case 0:
            var10000 = this.union3_1less2_2approx3(v1, v2, v3, evidence$10);
            break;
         case 1:
            int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$10.compare(v1.a(), v3.a())))).sign());
            switch (var6) {
               case -1:
                  var10000 = Interval$.MODULE$.fromOrderedBounds(v1, v2, evidence$10);
                  return var10000;
               case 0:
                  var10000 = this.union3_1approx2_2less3(v1, v3, v2, evidence$10);
                  return var10000;
               case 1:
                  var10000 = Interval$.MODULE$.fromOrderedBounds(v3, v2, evidence$10);
                  return var10000;
               default:
                  throw new MatchError(BoxesRunTime.boxToInteger(var6));
            }
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var5));
      }

      return var10000;
   }

   public Interval union3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$11) {
      int var5 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$11.compare(v1.a(), v2.a())))).sign());
      Interval var10000;
      switch (var5) {
         case -1:
            var10000 = this.union3_1less2(v1, v2, v3, evidence$11);
            break;
         case 0:
            var10000 = this.union3_1approx2(v1, v2, v3, evidence$11);
            break;
         case 1:
            var10000 = this.union3_1less2(v2, v1, v3, evidence$11);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var5));
      }

      return var10000;
   }

   public Interval union4_1approx2_2approx3_3less4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$12) {
      return v1.isClosed() ? Interval$.MODULE$.fromOrderedBounds(v1, v4, evidence$12) : (v2.isClosed() ? Interval$.MODULE$.fromOrderedBounds(v2, v4, evidence$12) : Interval$.MODULE$.fromOrderedBounds(v3, v4, evidence$12));
   }

   public Interval union4_1approx2_2less3_3approx4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$13) {
      Tuple2.mcZZ.sp var7 = new Tuple2.mcZZ.sp(v1.isClosed(), v3.isClosed());
      Interval var6;
      if (var7 != null) {
         boolean var8 = ((Tuple2)var7)._1$mcZ$sp();
         boolean var9 = ((Tuple2)var7)._2$mcZ$sp();
         if (var8 && var9) {
            var6 = Interval$.MODULE$.fromOrderedBounds(v1, v3, evidence$13);
            return var6;
         }
      }

      if (var7 != null) {
         boolean var10 = ((Tuple2)var7)._1$mcZ$sp();
         boolean var11 = ((Tuple2)var7)._2$mcZ$sp();
         if (!var10 && var11) {
            var6 = Interval$.MODULE$.fromOrderedBounds(v2, v3, evidence$13);
            return var6;
         }
      }

      if (var7 != null) {
         boolean var12 = ((Tuple2)var7)._1$mcZ$sp();
         boolean var13 = ((Tuple2)var7)._2$mcZ$sp();
         if (var12 && !var13) {
            var6 = Interval$.MODULE$.fromOrderedBounds(v1, v4, evidence$13);
            return var6;
         }
      }

      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         boolean var14 = ((Tuple2)var7)._1$mcZ$sp();
         boolean var15 = ((Tuple2)var7)._2$mcZ$sp();
         if (var14 || var15) {
            throw new MatchError(var7);
         } else {
            var6 = Interval$.MODULE$.fromOrderedBounds(v2, v4, evidence$13);
            return var6;
         }
      }
   }

   public Interval union4_1less2_2approx3_3approx4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$14) {
      return v2.isClosed() ? Interval$.MODULE$.fromOrderedBounds(v1, v2, evidence$14) : (v3.isClosed() ? Interval$.MODULE$.fromOrderedBounds(v1, v3, evidence$14) : Interval$.MODULE$.fromOrderedBounds(v1, v4, evidence$14));
   }

   public Interval union4_1less3_2less3_3approx4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$15) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$15.compare(v1.a(), v2.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union3_1less2_2approx3(v1, v3, v4, evidence$15);
            break;
         case 0:
            var10000 = this.union4_1approx2_2less3_3approx4(v1, v2, v3, v4, evidence$15);
            break;
         case 1:
            var10000 = this.union3_1less2_2approx3(v2, v3, v4, evidence$15);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4_1less2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$16) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$16.compare(v3.a(), v4.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = Interval$.MODULE$.fromOrderedBounds(v1, v4, evidence$16);
            break;
         case 0:
            var10000 = this.union4_1less2_2approx3_3approx4(v1, v2, v3, v4, evidence$16);
            break;
         case 1:
            var10000 = this.union4_1less3_2less3_3approx4(v1, v4, v2, v3, evidence$16);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4_1approx2_2less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$17) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$17.compare(v3.a(), v4.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union3_1approx2_2less3(v1, v2, v4, evidence$17);
            break;
         case 0:
            var10000 = this.union4_1approx2_2less3_3approx4(v1, v2, v3, v4, evidence$17);
            break;
         case 1:
            int var7 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$17.compare(v1.a(), v4.a())))).sign());
            switch (var7) {
               case -1:
                  var10000 = this.union3_1approx2_2less3(v1, v2, v3, evidence$17);
                  return var10000;
               case 0:
                  var10000 = this.union4_1approx2_2approx3_3less4(v1, v2, v4, v3, evidence$17);
                  return var10000;
               case 1:
                  var10000 = Interval$.MODULE$.fromOrderedBounds(v4, v3, evidence$17);
                  return var10000;
               default:
                  throw new MatchError(BoxesRunTime.boxToInteger(var7));
            }
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4_1approx2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$18) {
      return v1.isClosed() == v2.isClosed() ? this.union3_1approx2(v2, v3, v4, evidence$18) : this.union3_1approx2(v1, v2, v4, evidence$18);
   }

   public Interval union4_1approx2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$19) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$19.compare(v2.a(), v3.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union4_1approx2_2less3(v1, v2, v3, v4, evidence$19);
            break;
         case 0:
            var10000 = this.union4_1approx2_2approx3(v1, v2, v3, v4, evidence$19);
            break;
         case 1:
            var10000 = this.union4_1less2_2approx3(v3, v1, v2, v4, evidence$19);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4_1less2_1less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$20) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$20.compare(v2.a(), v3.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union3_1less2(v1, v3, v4, evidence$20);
            break;
         case 0:
            var10000 = this.union4_1less2_2approx3(v1, v2, v3, v4, evidence$20);
            break;
         case 1:
            var10000 = this.union3_1less2(v1, v2, v4, evidence$20);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4_1less3_2less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$21) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$21.compare(v1.a(), v2.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union3_1less2(v1, v3, v4, evidence$21);
            break;
         case 0:
            var10000 = this.union4_1approx2_2less3(v1, v2, v3, v4, evidence$21);
            break;
         case 1:
            var10000 = this.union3_1less2(v2, v3, v4, evidence$21);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4_1less2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$22) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$22.compare(v2.a(), v3.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union3_1less2(v1, v3, v4, evidence$22);
            break;
         case 0:
            var10000 = this.union4_1less2_2approx3(v1, v2, v3, v4, evidence$22);
            break;
         case 1:
            var10000 = this.union4_1less3_2less3(v1, v3, v2, v4, evidence$22);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   public Interval union4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$23) {
      int var6 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(evidence$23.compare(v1.a(), v2.a())))).sign());
      Interval var10000;
      switch (var6) {
         case -1:
            var10000 = this.union4_1less2(v1, v2, v3, v4, evidence$23);
            break;
         case 0:
            var10000 = this.union4_1approx2(v1, v2, v3, v4, evidence$23);
            break;
         case 1:
            var10000 = this.union4_1less2(v2, v1, v3, v4, evidence$23);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var6));
      }

      return var10000;
   }

   private ValueBound$() {
   }
}
