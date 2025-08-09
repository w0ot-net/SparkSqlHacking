package spire.math.interval;

import cats.kernel.Eq;
import cats.kernel.Order;
import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public final class Bound$ {
   public static final Bound$ MODULE$ = new Bound$();

   public Bound minLower(final Bound lhs, final Bound rhs, final boolean emptyIsMin, final Order evidence$1) {
      Tuple2 var7 = new Tuple2(lhs, rhs);
      Object var5;
      if (var7 != null) {
         Bound var8 = (Bound)var7._1();
         if (var8 instanceof EmptyBound) {
            var5 = emptyIsMin ? lhs : rhs;
            return (Bound)var5;
         }
      }

      if (var7 != null) {
         Bound var9 = (Bound)var7._2();
         if (var9 instanceof EmptyBound) {
            var5 = emptyIsMin ? rhs : lhs;
            return (Bound)var5;
         }
      }

      boolean var6;
      label161: {
         if (var7 != null) {
            Bound var10 = (Bound)var7._1();
            if (var10 instanceof Unbound) {
               var6 = true;
               break label161;
            }
         }

         if (var7 != null) {
            Bound var11 = (Bound)var7._2();
            if (var11 instanceof Unbound) {
               var6 = true;
               break label161;
            }
         }

         var6 = false;
      }

      if (var6) {
         var5 = new Unbound();
      } else {
         if (var7 != null) {
            Bound var12 = (Bound)var7._1();
            Bound var13 = (Bound)var7._2();
            if (var12 instanceof Closed) {
               Closed var14 = (Closed)var12;
               Object lv = var14.a();
               if (var13 instanceof Closed) {
                  Closed var16 = (Closed)var13;
                  Object rv = var16.a();
                  if (evidence$1.lteqv(lv, rv)) {
                     var5 = lhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 != null) {
            Bound var18 = (Bound)var7._1();
            Bound var19 = (Bound)var7._2();
            if (var18 instanceof Closed && var19 instanceof Closed) {
               var5 = rhs;
               return (Bound)var5;
            }
         }

         if (var7 != null) {
            Bound var20 = (Bound)var7._1();
            Bound var21 = (Bound)var7._2();
            if (var20 instanceof Open) {
               Open var22 = (Open)var20;
               Object lv = var22.a();
               if (var21 instanceof Open) {
                  Open var24 = (Open)var21;
                  Object rv = var24.a();
                  if (evidence$1.lteqv(lv, rv)) {
                     var5 = lhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 != null) {
            Bound var26 = (Bound)var7._1();
            Bound var27 = (Bound)var7._2();
            if (var26 instanceof Open && var27 instanceof Open) {
               var5 = rhs;
               return (Bound)var5;
            }
         }

         if (var7 != null) {
            Bound var28 = (Bound)var7._1();
            Bound var29 = (Bound)var7._2();
            if (var28 instanceof Closed) {
               Closed var30 = (Closed)var28;
               Object lv = var30.a();
               if (var29 instanceof Open) {
                  Open var32 = (Open)var29;
                  Object rv = var32.a();
                  if (evidence$1.lteqv(lv, rv)) {
                     var5 = lhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 != null) {
            Bound var34 = (Bound)var7._1();
            Bound var35 = (Bound)var7._2();
            if (var34 instanceof Closed && var35 instanceof Open) {
               var5 = rhs;
               return (Bound)var5;
            }
         }

         if (var7 != null) {
            Bound var36 = (Bound)var7._1();
            Bound var37 = (Bound)var7._2();
            if (var36 instanceof Open) {
               Open var38 = (Open)var36;
               Object lv = var38.a();
               if (var37 instanceof Closed) {
                  Closed var40 = (Closed)var37;
                  Object rv = var40.a();
                  if (evidence$1.lteqv(rv, lv)) {
                     var5 = rhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 == null) {
            throw new MatchError(var7);
         }

         Bound var42 = (Bound)var7._1();
         Bound var43 = (Bound)var7._2();
         if (!(var42 instanceof Open) || !(var43 instanceof Closed)) {
            throw new MatchError(var7);
         }

         var5 = lhs;
      }

      return (Bound)var5;
   }

   public Bound maxLower(final Bound lhs, final Bound rhs, final boolean emptyIsMax, final Order evidence$2) {
      Tuple2 var6 = new Tuple2(lhs, rhs);
      Bound var5;
      if (var6 != null) {
         Bound var7 = (Bound)var6._1();
         if (var7 instanceof EmptyBound) {
            var5 = emptyIsMax ? lhs : rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var8 = (Bound)var6._2();
         if (var8 instanceof EmptyBound) {
            var5 = emptyIsMax ? rhs : lhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var9 = (Bound)var6._1();
         if (var9 instanceof Unbound) {
            var5 = rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var10 = (Bound)var6._2();
         if (var10 instanceof Unbound) {
            var5 = lhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var11 = (Bound)var6._1();
         Bound var12 = (Bound)var6._2();
         if (var11 instanceof Closed) {
            Closed var13 = (Closed)var11;
            Object lv = var13.a();
            if (var12 instanceof Closed) {
               Closed var15 = (Closed)var12;
               Object rv = var15.a();
               if (evidence$2.gteqv(lv, rv)) {
                  var5 = lhs;
                  return var5;
               }
            }
         }
      }

      if (var6 != null) {
         Bound var17 = (Bound)var6._1();
         Bound var18 = (Bound)var6._2();
         if (var17 instanceof Closed && var18 instanceof Closed) {
            var5 = rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var19 = (Bound)var6._1();
         Bound var20 = (Bound)var6._2();
         if (var19 instanceof Open) {
            Open var21 = (Open)var19;
            Object lv = var21.a();
            if (var20 instanceof Open) {
               Open var23 = (Open)var20;
               Object rv = var23.a();
               if (evidence$2.gteqv(lv, rv)) {
                  var5 = lhs;
                  return var5;
               }
            }
         }
      }

      if (var6 != null) {
         Bound var25 = (Bound)var6._1();
         Bound var26 = (Bound)var6._2();
         if (var25 instanceof Open && var26 instanceof Open) {
            var5 = rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var27 = (Bound)var6._1();
         Bound var28 = (Bound)var6._2();
         if (var27 instanceof Closed) {
            Closed var29 = (Closed)var27;
            Object lv = var29.a();
            if (var28 instanceof Open) {
               Open var31 = (Open)var28;
               Object rv = var31.a();
               if (evidence$2.gteqv(rv, lv)) {
                  var5 = rhs;
                  return var5;
               }
            }
         }
      }

      if (var6 != null) {
         Bound var33 = (Bound)var6._1();
         Bound var34 = (Bound)var6._2();
         if (var33 instanceof Closed && var34 instanceof Open) {
            var5 = lhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var35 = (Bound)var6._1();
         Bound var36 = (Bound)var6._2();
         if (var35 instanceof Open) {
            Open var37 = (Open)var35;
            Object lv = var37.a();
            if (var36 instanceof Closed) {
               Closed var39 = (Closed)var36;
               Object rv = var39.a();
               if (evidence$2.gteqv(lv, rv)) {
                  var5 = lhs;
                  return var5;
               }
            }
         }
      }

      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         Bound var41 = (Bound)var6._1();
         Bound var42 = (Bound)var6._2();
         if (!(var41 instanceof Open) || !(var42 instanceof Closed)) {
            throw new MatchError(var6);
         } else {
            var5 = rhs;
            return var5;
         }
      }
   }

   public Bound minUpper(final Bound lhs, final Bound rhs, final boolean emptyIsMin, final Order evidence$3) {
      Tuple2 var6 = new Tuple2(lhs, rhs);
      Bound var5;
      if (var6 != null) {
         Bound var7 = (Bound)var6._1();
         if (var7 instanceof EmptyBound) {
            var5 = emptyIsMin ? lhs : rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var8 = (Bound)var6._2();
         if (var8 instanceof EmptyBound) {
            var5 = emptyIsMin ? rhs : lhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var9 = (Bound)var6._1();
         if (var9 instanceof Unbound) {
            var5 = rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var10 = (Bound)var6._2();
         if (var10 instanceof Unbound) {
            var5 = lhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var11 = (Bound)var6._1();
         Bound var12 = (Bound)var6._2();
         if (var11 instanceof Closed) {
            Closed var13 = (Closed)var11;
            Object lv = var13.a();
            if (var12 instanceof Closed) {
               Closed var15 = (Closed)var12;
               Object rv = var15.a();
               if (evidence$3.lteqv(lv, rv)) {
                  var5 = lhs;
                  return var5;
               }
            }
         }
      }

      if (var6 != null) {
         Bound var17 = (Bound)var6._1();
         Bound var18 = (Bound)var6._2();
         if (var17 instanceof Closed && var18 instanceof Closed) {
            var5 = rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var19 = (Bound)var6._1();
         Bound var20 = (Bound)var6._2();
         if (var19 instanceof Open) {
            Open var21 = (Open)var19;
            Object lv = var21.a();
            if (var20 instanceof Open) {
               Open var23 = (Open)var20;
               Object rv = var23.a();
               if (evidence$3.lteqv(lv, rv)) {
                  var5 = lhs;
                  return var5;
               }
            }
         }
      }

      if (var6 != null) {
         Bound var25 = (Bound)var6._1();
         Bound var26 = (Bound)var6._2();
         if (var25 instanceof Open && var26 instanceof Open) {
            var5 = rhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var27 = (Bound)var6._1();
         Bound var28 = (Bound)var6._2();
         if (var27 instanceof Closed) {
            Closed var29 = (Closed)var27;
            Object lv = var29.a();
            if (var28 instanceof Open) {
               Open var31 = (Open)var28;
               Object rv = var31.a();
               if (evidence$3.lteqv(rv, lv)) {
                  var5 = rhs;
                  return var5;
               }
            }
         }
      }

      if (var6 != null) {
         Bound var33 = (Bound)var6._1();
         Bound var34 = (Bound)var6._2();
         if (var33 instanceof Closed && var34 instanceof Open) {
            var5 = lhs;
            return var5;
         }
      }

      if (var6 != null) {
         Bound var35 = (Bound)var6._1();
         Bound var36 = (Bound)var6._2();
         if (var35 instanceof Open) {
            Open var37 = (Open)var35;
            Object lv = var37.a();
            if (var36 instanceof Closed) {
               Closed var39 = (Closed)var36;
               Object rv = var39.a();
               if (evidence$3.lteqv(lv, rv)) {
                  var5 = lhs;
                  return var5;
               }
            }
         }
      }

      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         Bound var41 = (Bound)var6._1();
         Bound var42 = (Bound)var6._2();
         if (!(var41 instanceof Open) || !(var42 instanceof Closed)) {
            throw new MatchError(var6);
         } else {
            var5 = rhs;
            return var5;
         }
      }
   }

   public Bound maxUpper(final Bound lhs, final Bound rhs, final boolean emptyIsMax, final Order evidence$4) {
      Tuple2 var7 = new Tuple2(lhs, rhs);
      Object var5;
      if (var7 != null) {
         Bound var8 = (Bound)var7._1();
         if (var8 instanceof EmptyBound) {
            var5 = emptyIsMax ? lhs : rhs;
            return (Bound)var5;
         }
      }

      if (var7 != null) {
         Bound var9 = (Bound)var7._2();
         if (var9 instanceof EmptyBound) {
            var5 = emptyIsMax ? rhs : lhs;
            return (Bound)var5;
         }
      }

      boolean var6;
      label161: {
         if (var7 != null) {
            Bound var10 = (Bound)var7._1();
            if (var10 instanceof Unbound) {
               var6 = true;
               break label161;
            }
         }

         if (var7 != null) {
            Bound var11 = (Bound)var7._2();
            if (var11 instanceof Unbound) {
               var6 = true;
               break label161;
            }
         }

         var6 = false;
      }

      if (var6) {
         var5 = new Unbound();
      } else {
         if (var7 != null) {
            Bound var12 = (Bound)var7._1();
            Bound var13 = (Bound)var7._2();
            if (var12 instanceof Closed) {
               Closed var14 = (Closed)var12;
               Object lv = var14.a();
               if (var13 instanceof Closed) {
                  Closed var16 = (Closed)var13;
                  Object rv = var16.a();
                  if (evidence$4.gteqv(lv, rv)) {
                     var5 = lhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 != null) {
            Bound var18 = (Bound)var7._1();
            Bound var19 = (Bound)var7._2();
            if (var18 instanceof Closed && var19 instanceof Closed) {
               var5 = rhs;
               return (Bound)var5;
            }
         }

         if (var7 != null) {
            Bound var20 = (Bound)var7._1();
            Bound var21 = (Bound)var7._2();
            if (var20 instanceof Open) {
               Open var22 = (Open)var20;
               Object lv = var22.a();
               if (var21 instanceof Open) {
                  Open var24 = (Open)var21;
                  Object rv = var24.a();
                  if (evidence$4.gteqv(lv, rv)) {
                     var5 = lhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 != null) {
            Bound var26 = (Bound)var7._1();
            Bound var27 = (Bound)var7._2();
            if (var26 instanceof Open && var27 instanceof Open) {
               var5 = rhs;
               return (Bound)var5;
            }
         }

         if (var7 != null) {
            Bound var28 = (Bound)var7._1();
            Bound var29 = (Bound)var7._2();
            if (var28 instanceof Closed) {
               Closed var30 = (Closed)var28;
               Object lv = var30.a();
               if (var29 instanceof Open) {
                  Open var32 = (Open)var29;
                  Object rv = var32.a();
                  if (evidence$4.gteqv(lv, rv)) {
                     var5 = lhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 != null) {
            Bound var34 = (Bound)var7._1();
            Bound var35 = (Bound)var7._2();
            if (var34 instanceof Closed && var35 instanceof Open) {
               var5 = rhs;
               return (Bound)var5;
            }
         }

         if (var7 != null) {
            Bound var36 = (Bound)var7._1();
            Bound var37 = (Bound)var7._2();
            if (var36 instanceof Open) {
               Open var38 = (Open)var36;
               Object lv = var38.a();
               if (var37 instanceof Closed) {
                  Closed var40 = (Closed)var37;
                  Object rv = var40.a();
                  if (evidence$4.gteqv(rv, lv)) {
                     var5 = rhs;
                     return (Bound)var5;
                  }
               }
            }
         }

         if (var7 == null) {
            throw new MatchError(var7);
         }

         Bound var42 = (Bound)var7._1();
         Bound var43 = (Bound)var7._2();
         if (!(var42 instanceof Open) || !(var43 instanceof Closed)) {
            throw new MatchError(var7);
         }

         var5 = lhs;
      }

      return (Bound)var5;
   }

   public Eq eq(final Eq ev) {
      return new Eq(ev) {
         private final Eq ev$11;

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

         public boolean eqv(final Bound x, final Bound y) {
            Tuple2 var4 = new Tuple2(x, y);
            boolean var3;
            if (var4 != null) {
               Bound var5 = (Bound)var4._1();
               Bound var6 = (Bound)var4._2();
               if (var5 instanceof EmptyBound && var6 instanceof EmptyBound) {
                  var3 = true;
                  return var3;
               }
            }

            if (var4 != null) {
               Bound var7 = (Bound)var4._1();
               Bound var8 = (Bound)var4._2();
               if (var7 instanceof Unbound && var8 instanceof Unbound) {
                  var3 = true;
                  return var3;
               }
            }

            if (var4 != null) {
               Bound var9 = (Bound)var4._1();
               Bound var10 = (Bound)var4._2();
               if (var9 instanceof Closed) {
                  Closed var11 = (Closed)var9;
                  Object a = var11.a();
                  if (var10 instanceof Closed) {
                     Closed var13 = (Closed)var10;
                     Object b = var13.a();
                     var3 = this.ev$11.eqv(a, b);
                     return var3;
                  }
               }
            }

            if (var4 != null) {
               Bound var15 = (Bound)var4._1();
               Bound var16 = (Bound)var4._2();
               if (var15 instanceof Open) {
                  Open var17 = (Open)var15;
                  Object a = var17.a();
                  if (var16 instanceof Open) {
                     Open var19 = (Open)var16;
                     Object b = var19.a();
                     var3 = this.ev$11.eqv(a, b);
                     return var3;
                  }
               }
            }

            var3 = false;
            return var3;
         }

         public {
            this.ev$11 = ev$11;
            Eq.$init$(this);
         }
      };
   }

   private Bound$() {
   }
}
