package scala.reflect.internal;

import scala.Function1;
import scala.collection.StringOps.;
import scala.runtime.OrderedProxy;
import scala.runtime.RichInt;

public final class Precedence$ implements Function1 {
   public static final Precedence$ MODULE$ = new Precedence$();
   private static final String ErrorName;

   static {
      Precedence$ var10000 = MODULE$;
      ErrorName = "<error>";
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   private boolean isAssignmentOp(final String name) {
      switch (name == null ? 0 : name.hashCode()) {
         case 0:
            if ("".equals(name)) {
               return false;
            }
            break;
         case 1084:
            if ("!=".equals(name)) {
               return false;
            }
            break;
         case 1921:
            if ("<=".equals(name)) {
               return false;
            }
            break;
         case 1983:
            if (">=".equals(name)) {
               return false;
            }
      }

      if (.MODULE$.last$extension(name) == '=' && .MODULE$.head$extension(name) != '=' && Chars.isOperatorPart$(Chars$.MODULE$, (int)name.codePointAt(0))) {
         return true;
      } else {
         return false;
      }
   }

   private int firstChar(final int c) {
      switch (c) {
         case 33:
         case 61:
            return 5;
         case 37:
         case 42:
         case 47:
            return 9;
         case 38:
            return 4;
         case 43:
         case 45:
            return 8;
         case 58:
            return 7;
         case 60:
         case 62:
            return 6;
         case 94:
            return 3;
         case 124:
            return 2;
         default:
            return Chars.isScalaLetter$(Chars$.MODULE$, (int)c) ? 1 : 10;
      }
   }

   public int apply(final int level) {
      return level;
   }

   public int apply(final String name) {
      boolean var10000;
      if ("".equals(name)) {
         var10000 = true;
      } else {
         label27: {
            label26: {
               String var2 = ErrorName;
               if (var2 == null) {
                  if (name == null) {
                     break label26;
                  }
               } else if (var2.equals(name)) {
                  break label26;
               }

               var10000 = false;
               break label27;
            }

            var10000 = true;
         }
      }

      if (var10000) {
         return -1;
      } else {
         return this.isAssignmentOp(name) ? 0 : this.firstChar(name.codePointAt(0));
      }
   }

   public final int compare$extension(final int $this, final int that) {
      return OrderedProxy.compare$(new RichInt($this), that);
   }

   public final String toString$extension(final int $this) {
      return (new StringBuilder(12)).append("Precedence(").append($this).append(")").toString();
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof Precedence) {
         int var3 = ((Precedence)x$1).level();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private Precedence$() {
   }
}
