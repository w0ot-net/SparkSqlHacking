package breeze.optimize;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\r\u001a\u0001zA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!)!\t\u0001C\u0001\u0007\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\t\u0011\"\u0011b\u0011\u001dQ\u0007!!A\u0005\u0002-Dqa\u001c\u0001\u0002\u0002\u0013\u0005\u0001\u000fC\u0004w\u0001\u0005\u0005I\u0011I<\t\u000fy\u0004\u0011\u0011!C\u0001\u007f\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131\u0002\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\b\u0013\u0005e\u0011$!A\t\u0002\u0005ma\u0001\u0003\r\u001a\u0003\u0003E\t!!\b\t\ry\u0002B\u0011AA\u001b\u0011%\t9\u0004EA\u0001\n\u000b\nI\u0004\u0003\u0005C!\u0005\u0005I\u0011QA\u001e\u0011!\ty\u0004EI\u0001\n\u0003)\u0006\"CA!!\u0005\u0005I\u0011QA\"\u0011!\ty\u0005EI\u0001\n\u0003)\u0006\"CA)!\u0005\u0005I\u0011BA*\u0005Aa%GU3hk2\f'/\u001b>bi&|gN\u0003\u0002\u001b7\u0005Aq\u000e\u001d;j[&TXMC\u0001\u001d\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0002\u0001 K%b\u0003C\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#AB!osJ+g\r\u0005\u0002'O5\t\u0011$\u0003\u0002)3\t\u0011r\n\u001d;j[&T\u0018\r^5p]>\u0003H/[8o!\t\u0001#&\u0003\u0002,C\t9\u0001K]8ek\u000e$\bCA\u00176\u001d\tq3G\u0004\u00020e5\t\u0001G\u0003\u00022;\u00051AH]8pizJ\u0011AI\u0005\u0003i\u0005\nq\u0001]1dW\u0006<W-\u0003\u00027o\ta1+\u001a:jC2L'0\u00192mK*\u0011A'I\u0001\u0006m\u0006dW/Z\u000b\u0002uA\u0011\u0001eO\u0005\u0003y\u0005\u0012a\u0001R8vE2,\u0017A\u0002<bYV,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\u0006\u0003\"A\n\u0001\t\u000fa\u001a\u0001\u0013!a\u0001u\u0005)\u0011\r\u001d9msR\u0011Ai\u0014\t\u0003\u000b2s!A\u0012&\u000f\u0005\u001dKeBA\u0018I\u0013\u0005a\u0012B\u0001\u000e\u001c\u0013\tY\u0015$A\nGSJ\u001cHo\u0014:eKJl\u0015N\\5nSj,'/\u0003\u0002N\u001d\nIq\n\u001d;QCJ\fWn\u001d\u0006\u0003\u0017fAQ\u0001\u0015\u0003A\u0002\u0011\u000ba\u0001]1sC6\u001c\u0018\u0001B2paf$\"\u0001Q*\t\u000fa*\u0001\u0013!a\u0001u\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001,+\u0005i:6&\u0001-\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016!C;oG\",7m[3e\u0015\ti\u0016%\u0001\u0006b]:|G/\u0019;j_:L!a\u0018.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002EB\u00111\r[\u0007\u0002I*\u0011QMZ\u0001\u0005Y\u0006twMC\u0001h\u0003\u0011Q\u0017M^1\n\u0005%$'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001m!\t\u0001S.\u0003\u0002oC\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0011\u000f\u001e\t\u0003AIL!a]\u0011\u0003\u0007\u0005s\u0017\u0010C\u0004v\u0013\u0005\u0005\t\u0019\u00017\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005A\bcA=}c6\t!P\u0003\u0002|C\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005uT(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0001\u0002\bA\u0019\u0001%a\u0001\n\u0007\u0005\u0015\u0011EA\u0004C_>dW-\u00198\t\u000fU\\\u0011\u0011!a\u0001c\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0011\u0017Q\u0002\u0005\bk2\t\t\u00111\u0001m\u0003!A\u0017m\u001d5D_\u0012,G#\u00017\u0002\r\u0015\fX/\u00197t)\u0011\t\t!a\u0006\t\u000fUt\u0011\u0011!a\u0001c\u0006\u0001BJ\r*fOVd\u0017M]5{CRLwN\u001c\t\u0003MA\u0019R\u0001EA\u0010\u0003W\u0001b!!\t\u0002(i\u0002UBAA\u0012\u0015\r\t)#I\u0001\beVtG/[7f\u0013\u0011\tI#a\t\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002.\u0005MRBAA\u0018\u0015\r\t\tDZ\u0001\u0003S>L1ANA\u0018)\t\tY\"\u0001\u0005u_N#(/\u001b8h)\u0005\u0011Gc\u0001!\u0002>!9\u0001h\u0005I\u0001\u0002\u0004Q\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u0019\u0002\u000fUt\u0017\r\u001d9msR!\u0011QIA&!\u0011\u0001\u0013q\t\u001e\n\u0007\u0005%\u0013E\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u001b*\u0012\u0011!a\u0001\u0001\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0006E\u0002d\u0003/J1!!\u0017e\u0005\u0019y%M[3di\u0002"
)
public class L2Regularization implements OptimizationOption, Product, Serializable {
   private final double value;

   public static double $lessinit$greater$default$1() {
      return L2Regularization$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final L2Regularization x$0) {
      return L2Regularization$.MODULE$.unapply(x$0);
   }

   public static double apply$default$1() {
      return L2Regularization$.MODULE$.apply$default$1();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public double value() {
      return this.value;
   }

   public FirstOrderMinimizer.OptParams apply(final FirstOrderMinimizer.OptParams params) {
      boolean x$1 = false;
      double x$2 = this.value();
      int x$3 = params.copy$default$1();
      double x$4 = params.copy$default$3();
      int x$5 = params.copy$default$4();
      double x$6 = params.copy$default$6();
      boolean x$7 = params.copy$default$7();
      int x$8 = params.copy$default$8();
      return params.copy(x$3, x$2, x$4, x$5, false, x$6, x$7, x$8);
   }

   public L2Regularization copy(final double value) {
      return new L2Regularization(value);
   }

   public double copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "L2Regularization";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.value());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof L2Regularization;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "value";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.value()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof L2Regularization) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               L2Regularization var4 = (L2Regularization)x$1;
               if (this.value() == var4.value() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public L2Regularization(final double value) {
      this.value = value;
      Function1.$init$(this);
      Product.$init$(this);
   }
}
