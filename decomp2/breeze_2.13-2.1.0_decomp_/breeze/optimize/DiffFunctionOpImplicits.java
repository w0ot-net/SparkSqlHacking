package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.util.Isomorphism;
import scala.;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c!C\u0005\u000b!\u0003\r\tcDA'\u0011\u00151\u0002\u0001\"\u0001\u0018\u0011\u0015Y\u0002\u0001b\u0001\u001d\u0011\u0015y\u0004\u0001b\u0001A\u0011\u0015a\u0005\u0001b\u0001N\u0011\u0015a\u0006\u0001b\u0001^\u0011\u0015)\u0007\u0001b\u0001g\u0011\u0015\u0011\b\u0001b\u0001t\u0011\u0015Y\b\u0001b\u0001}\u0005]!\u0015N\u001a4Gk:\u001cG/[8o\u001fBLU\u000e\u001d7jG&$8O\u0003\u0002\f\u0019\u0005Aq\u000e\u001d;j[&TXMC\u0001\u000e\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0001\u0001\u0011!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0007\t\u0003#eI!A\u0007\n\u0003\tUs\u0017\u000e^\u0001\u0012_B\fE\r\u001a#jM\u001a4UO\\2uS>tWCA\u000f4)\tqB\bE\u0003 O5jSF\u0004\u0002!K5\t\u0011E\u0003\u0002#G\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003I1\ta\u0001\\5oC2<\u0017B\u0001\u0014\"\u0003\u0015y\u0005/\u00113e\u0013\tA\u0013FA\u0003J[Bd''\u0003\u0002+W\t)QKR;oG*\u0011A\u0006D\u0001\bO\u0016tWM]5d!\rqs&M\u0007\u0002\u0015%\u0011\u0001G\u0003\u0002\r\t&4gMR;oGRLwN\u001c\t\u0003eMb\u0001\u0001B\u00035\u0005\t\u0007QGA\u0001U#\t1\u0014\b\u0005\u0002\u0012o%\u0011\u0001H\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t\"(\u0003\u0002<%\t\u0019\u0011I\\=\t\u000bu\u0012\u00019\u0001 \u0002\u000b=\u0004\u0018\t\u001a3\u0011\u000b}9\u0013'M\u0019\u0002#=\u00048+\u001e2ES\u001a4g)\u001e8di&|g.\u0006\u0002B\u0011R\u0011!)\u0013\t\u0006\u0007\u001e2eI\u0012\b\u0003A\u0011K!!R\u0011\u0002\u000b=\u00038+\u001e2\u0011\u00079zs\t\u0005\u00023\u0011\u0012)Ag\u0001b\u0001k!)!j\u0001a\u0002\u0017\u0006)q\u000e]*vEB)1iJ$H\u000f\u0006\tr\u000e]'vY\u0012KgM\u001a$v]\u000e$\u0018n\u001c8\u0016\u00059+FCA(Z!\u0015\u0001ve\u0015,T\u001d\t\u0001\u0013+\u0003\u0002SC\u0005Yq\n]'vY6\u000bGO]5y!\rqs\u0006\u0016\t\u0003eU#Q\u0001\u000e\u0003C\u0002U\u0002\"!E,\n\u0005a\u0013\"A\u0002#pk\ndW\rC\u0003[\t\u0001\u000f1,A\u0003pa6+H\u000eE\u0003QOQ3F+\u0001\u000bpa6+H\u000e\u0014%T\t&4gMR;oGRLwN\\\u000b\u0003=\n$\"aX2\u0011\u000bA;c\u000b\u00191\u0011\u00079z\u0013\r\u0005\u00023E\u0012)A'\u0002b\u0001k!)!,\u0002a\u0002IB)\u0001k\n,bC\u0006\tr\u000e\u001d#jm\u0012KgM\u001a$v]\u000e$\u0018n\u001c8\u0016\u0005\u001dtGC\u00015p!\u0015Iw\u0005\u001c,m\u001d\t\u0001#.\u0003\u0002lC\u0005)q\n\u001d#jmB\u0019afL7\u0011\u0005IrG!\u0002\u001b\u0007\u0005\u0004)\u0004\"\u00029\u0007\u0001\b\t\u0018!B8q\t&4\b#B5([Zk\u0017\u0001F8q\t&4H\nS*ES\u001a4g)\u001e8di&|g.\u0006\u0002uqR\u0011Q/\u001f\t\u0006S\u001e2fO\u001e\t\u0004]=:\bC\u0001\u001ay\t\u0015!tA1\u00016\u0011\u0015Qv\u0001q\u0001{!\u0015\u0001vEV<x\u0003\u001d\u0019\u0017m\u001d;PaN,2\"`A\u0011\u0003O\ty$a\u0007\u0002.Q9a0!\r\u0002B\u0005\u001d\u0003cC@\u0002\u0014\u0005e\u0011qDA\u0013\u0003WqA!!\u0001\u0002\u00109!\u00111AA\u0007\u001d\u0011\t)!a\u0003\u000e\u0005\u0005\u001d!bAA\u0005\u001d\u00051AH]8pizJ\u0011!D\u0005\u0003Y1I1!!\u0005,\u0003\u0015)f)\u001e8d\u0013\u0011\t)\"a\u0006\u0003\rUKU\u000e\u001d73\u0015\r\t\tb\u000b\t\u0004e\u0005mAABA\u000f\u0011\t\u0007QG\u0001\u0002PaB\u0019!'!\t\u0005\r\u0005\r\u0002B1\u00016\u0005\t1\u0016\u0007E\u00023\u0003O!a!!\u000b\t\u0005\u0004)$A\u0001,3!\r\u0011\u0014Q\u0006\u0003\u0007\u0003_A!\u0019A\u001b\u0003\u0005Y\u0013\u0006bBA\u001a\u0011\u0001\u000f\u0011QG\u0001\u0005mF*g\u000fE\u0004\u0012\u0003o\ty\"a\u000f\n\u0007\u0005e\"C\u0001\t%Y\u0016\u001c8\u000fJ2pY>tG\u0005\\3tgB!afLA\u001f!\r\u0011\u0014q\b\u0003\u0006i!\u0011\r!\u000e\u0005\b\u0003\u0007B\u00019AA#\u0003\u00111&'\u001a<\u0011\u000fE\t9$!\n\u0002<!9\u0011\u0011\n\u0005A\u0004\u0005-\u0013AA8q!-y\u00181CA\r\u0003w\tY$a\u000b\u000f\u00079\ny%C\u0002\u0002R)\tA\u0002R5gM\u001a+hn\u0019;j_:L3\u0001AA+\u0015\r\t\tF\u0003"
)
public interface DiffFunctionOpImplicits {
   // $FF: synthetic method
   static UFunc.UImpl2 opAddDiffFunction$(final DiffFunctionOpImplicits $this, final UFunc.UImpl2 opAdd) {
      return $this.opAddDiffFunction(opAdd);
   }

   default UFunc.UImpl2 opAddDiffFunction(final UFunc.UImpl2 opAdd) {
      return new UFunc.UImpl2(opAdd) {
         public final UFunc.UImpl2 opAdd$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DiffFunction apply(final DiffFunction f, final DiffFunction f2) {
            return new DiffFunction(f, f2) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final DiffFunction f$1;
               private final DiffFunction f2$1;

               public DiffFunction repr() {
                  return DiffFunction.repr$(this);
               }

               public DiffFunction cached(final CanCopy copy) {
                  return DiffFunction.cached$(this, copy);
               }

               public DiffFunction throughLens(final Isomorphism l) {
                  return DiffFunction.throughLens$(this, l);
               }

               public Object gradientAt(final Object x) {
                  return StochasticDiffFunction.gradientAt$(this, x);
               }

               public double valueAt(final Object x) {
                  return StochasticDiffFunction.valueAt$(this, x);
               }

               public final double apply(final Object x) {
                  return StochasticDiffFunction.apply$(this, x);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
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

               public Tuple2 calculate(final Object x) {
                  Tuple2 var5 = this.f$1.calculate(x);
                  if (var5 != null) {
                     double v1 = var5._1$mcD$sp();
                     Object g1 = var5._2();
                     Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(v1), g1);
                     double v1x = var3._1$mcD$sp();
                     Object g1 = var3._2();
                     Tuple2 var13 = this.f2$1.calculate(x);
                     if (var13 != null) {
                        double v2 = var13._1$mcD$sp();
                        Object g2 = var13._2();
                        Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v2), g2);
                        double v2x = var2._1$mcD$sp();
                        Object g2x = var2._2();
                        return new Tuple2(BoxesRunTime.boxToDouble(v1x + v2x), this.$outer.opAdd$1.apply(g1, g2x));
                     } else {
                        throw new MatchError(var13);
                     }
                  } else {
                     throw new MatchError(var5);
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.f$1 = f$1;
                     this.f2$1 = f2$1;
                     Function1.$init$(this);
                     ImmutableNumericOps.$init$(this);
                     NumericOps.$init$(this);
                     StochasticDiffFunction.$init$(this);
                     DiffFunction.$init$(this);
                  }
               }
            };
         }

         public {
            this.opAdd$1 = opAdd$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 opSubDiffFunction$(final DiffFunctionOpImplicits $this, final UFunc.UImpl2 opSub) {
      return $this.opSubDiffFunction(opSub);
   }

   default UFunc.UImpl2 opSubDiffFunction(final UFunc.UImpl2 opSub) {
      return new UFunc.UImpl2(opSub) {
         public final UFunc.UImpl2 opSub$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DiffFunction apply(final DiffFunction f, final DiffFunction f2) {
            return new DiffFunction(f, f2) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final DiffFunction f$2;
               private final DiffFunction f2$2;

               public DiffFunction repr() {
                  return DiffFunction.repr$(this);
               }

               public DiffFunction cached(final CanCopy copy) {
                  return DiffFunction.cached$(this, copy);
               }

               public DiffFunction throughLens(final Isomorphism l) {
                  return DiffFunction.throughLens$(this, l);
               }

               public Object gradientAt(final Object x) {
                  return StochasticDiffFunction.gradientAt$(this, x);
               }

               public double valueAt(final Object x) {
                  return StochasticDiffFunction.valueAt$(this, x);
               }

               public final double apply(final Object x) {
                  return StochasticDiffFunction.apply$(this, x);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
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

               public Tuple2 calculate(final Object x) {
                  Tuple2 var5 = this.f$2.calculate(x);
                  if (var5 != null) {
                     double v1 = var5._1$mcD$sp();
                     Object g1 = var5._2();
                     Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(v1), g1);
                     double v1x = var3._1$mcD$sp();
                     Object g1 = var3._2();
                     Tuple2 var13 = this.f2$2.calculate(x);
                     if (var13 != null) {
                        double v2 = var13._1$mcD$sp();
                        Object g2 = var13._2();
                        Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v2), g2);
                        double v2x = var2._1$mcD$sp();
                        Object g2x = var2._2();
                        return new Tuple2(BoxesRunTime.boxToDouble(v1x - v2x), this.$outer.opSub$1.apply(g1, g2x));
                     } else {
                        throw new MatchError(var13);
                     }
                  } else {
                     throw new MatchError(var5);
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.f$2 = f$2;
                     this.f2$2 = f2$2;
                     Function1.$init$(this);
                     ImmutableNumericOps.$init$(this);
                     NumericOps.$init$(this);
                     StochasticDiffFunction.$init$(this);
                     DiffFunction.$init$(this);
                  }
               }
            };
         }

         public {
            this.opSub$1 = opSub$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 opMulDiffFunction$(final DiffFunctionOpImplicits $this, final UFunc.UImpl2 opMul) {
      return $this.opMulDiffFunction(opMul);
   }

   default UFunc.UImpl2 opMulDiffFunction(final UFunc.UImpl2 opMul) {
      return new UFunc.UImpl2(opMul) {
         public final UFunc.UImpl2 opMul$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DiffFunction apply(final DiffFunction f, final double v) {
            return new DiffFunction(f, v) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final DiffFunction f$3;
               private final double v$1;

               public DiffFunction repr() {
                  return DiffFunction.repr$(this);
               }

               public DiffFunction cached(final CanCopy copy) {
                  return DiffFunction.cached$(this, copy);
               }

               public DiffFunction throughLens(final Isomorphism l) {
                  return DiffFunction.throughLens$(this, l);
               }

               public Object gradientAt(final Object x) {
                  return StochasticDiffFunction.gradientAt$(this, x);
               }

               public double valueAt(final Object x) {
                  return StochasticDiffFunction.valueAt$(this, x);
               }

               public final double apply(final Object x) {
                  return StochasticDiffFunction.apply$(this, x);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
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

               public Tuple2 calculate(final Object x) {
                  Tuple2 var4 = this.f$3.calculate(x);
                  if (var4 != null) {
                     double v1 = var4._1$mcD$sp();
                     Object g1 = var4._2();
                     Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v1), g1);
                     double v1 = var2._1$mcD$sp();
                     Object g1x = var2._2();
                     return new Tuple2(BoxesRunTime.boxToDouble(v1 * this.v$1), this.$outer.opMul$1.apply(g1x, BoxesRunTime.boxToDouble(this.v$1)));
                  } else {
                     throw new MatchError(var4);
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.f$3 = f$3;
                     this.v$1 = v$1;
                     Function1.$init$(this);
                     ImmutableNumericOps.$init$(this);
                     NumericOps.$init$(this);
                     StochasticDiffFunction.$init$(this);
                     DiffFunction.$init$(this);
                  }
               }
            };
         }

         public {
            this.opMul$1 = opMul$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 opMulLHSDiffFunction$(final DiffFunctionOpImplicits $this, final UFunc.UImpl2 opMul) {
      return $this.opMulLHSDiffFunction(opMul);
   }

   default UFunc.UImpl2 opMulLHSDiffFunction(final UFunc.UImpl2 opMul) {
      return new UFunc.UImpl2(opMul) {
         public final UFunc.UImpl2 opMul$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DiffFunction apply(final double v, final DiffFunction f) {
            return new DiffFunction(f, v) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final DiffFunction f$4;
               private final double v$2;

               public DiffFunction repr() {
                  return DiffFunction.repr$(this);
               }

               public DiffFunction cached(final CanCopy copy) {
                  return DiffFunction.cached$(this, copy);
               }

               public DiffFunction throughLens(final Isomorphism l) {
                  return DiffFunction.throughLens$(this, l);
               }

               public Object gradientAt(final Object x) {
                  return StochasticDiffFunction.gradientAt$(this, x);
               }

               public double valueAt(final Object x) {
                  return StochasticDiffFunction.valueAt$(this, x);
               }

               public final double apply(final Object x) {
                  return StochasticDiffFunction.apply$(this, x);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
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

               public Tuple2 calculate(final Object x) {
                  Tuple2 var4 = this.f$4.calculate(x);
                  if (var4 != null) {
                     double v1 = var4._1$mcD$sp();
                     Object g1 = var4._2();
                     Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v1), g1);
                     double v1 = var2._1$mcD$sp();
                     Object g1x = var2._2();
                     return new Tuple2(BoxesRunTime.boxToDouble(v1 * this.v$2), this.$outer.opMul$2.apply(BoxesRunTime.boxToDouble(this.v$2), g1x));
                  } else {
                     throw new MatchError(var4);
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.f$4 = f$4;
                     this.v$2 = v$2;
                     Function1.$init$(this);
                     ImmutableNumericOps.$init$(this);
                     NumericOps.$init$(this);
                     StochasticDiffFunction.$init$(this);
                     DiffFunction.$init$(this);
                  }
               }
            };
         }

         public {
            this.opMul$2 = opMul$2;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 opDivDiffFunction$(final DiffFunctionOpImplicits $this, final UFunc.UImpl2 opDiv) {
      return $this.opDivDiffFunction(opDiv);
   }

   default UFunc.UImpl2 opDivDiffFunction(final UFunc.UImpl2 opDiv) {
      return new UFunc.UImpl2(opDiv) {
         public final UFunc.UImpl2 opDiv$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DiffFunction apply(final DiffFunction f, final double v) {
            return new DiffFunction(f, v) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final DiffFunction f$5;
               private final double v$3;

               public DiffFunction repr() {
                  return DiffFunction.repr$(this);
               }

               public DiffFunction cached(final CanCopy copy) {
                  return DiffFunction.cached$(this, copy);
               }

               public DiffFunction throughLens(final Isomorphism l) {
                  return DiffFunction.throughLens$(this, l);
               }

               public Object gradientAt(final Object x) {
                  return StochasticDiffFunction.gradientAt$(this, x);
               }

               public double valueAt(final Object x) {
                  return StochasticDiffFunction.valueAt$(this, x);
               }

               public final double apply(final Object x) {
                  return StochasticDiffFunction.apply$(this, x);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
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

               public Tuple2 calculate(final Object x) {
                  Tuple2 var4 = this.f$5.calculate(x);
                  if (var4 != null) {
                     double v1 = var4._1$mcD$sp();
                     Object g1 = var4._2();
                     Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v1), g1);
                     double v1 = var2._1$mcD$sp();
                     Object g1x = var2._2();
                     return new Tuple2(BoxesRunTime.boxToDouble(v1 / this.v$3), this.$outer.opDiv$1.apply(g1x, BoxesRunTime.boxToDouble(this.v$3)));
                  } else {
                     throw new MatchError(var4);
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.f$5 = f$5;
                     this.v$3 = v$3;
                     Function1.$init$(this);
                     ImmutableNumericOps.$init$(this);
                     NumericOps.$init$(this);
                     StochasticDiffFunction.$init$(this);
                     DiffFunction.$init$(this);
                  }
               }
            };
         }

         public {
            this.opDiv$1 = opDiv$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 opDivLHSDiffFunction$(final DiffFunctionOpImplicits $this, final UFunc.UImpl2 opMul) {
      return $this.opDivLHSDiffFunction(opMul);
   }

   default UFunc.UImpl2 opDivLHSDiffFunction(final UFunc.UImpl2 opMul) {
      return new UFunc.UImpl2(opMul) {
         public final UFunc.UImpl2 opMul$3;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DiffFunction apply(final double v, final DiffFunction f) {
            return new DiffFunction(f, v) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final DiffFunction f$6;
               private final double v$4;

               public DiffFunction repr() {
                  return DiffFunction.repr$(this);
               }

               public DiffFunction cached(final CanCopy copy) {
                  return DiffFunction.cached$(this, copy);
               }

               public DiffFunction throughLens(final Isomorphism l) {
                  return DiffFunction.throughLens$(this, l);
               }

               public Object gradientAt(final Object x) {
                  return StochasticDiffFunction.gradientAt$(this, x);
               }

               public double valueAt(final Object x) {
                  return StochasticDiffFunction.valueAt$(this, x);
               }

               public final double apply(final Object x) {
                  return StochasticDiffFunction.apply$(this, x);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
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

               public Tuple2 calculate(final Object x) {
                  Tuple2 var4 = this.f$6.calculate(x);
                  if (var4 != null) {
                     double v1 = var4._1$mcD$sp();
                     Object g1 = var4._2();
                     Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v1), g1);
                     double v1 = var2._1$mcD$sp();
                     Object g1x = var2._2();
                     return new Tuple2(BoxesRunTime.boxToDouble(this.v$4 / v1), this.$outer.opMul$3.apply(BoxesRunTime.boxToDouble(-this.v$4 / (v1 * v1)), g1x));
                  } else {
                     throw new MatchError(var4);
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.f$6 = f$6;
                     this.v$4 = v$4;
                     Function1.$init$(this);
                     ImmutableNumericOps.$init$(this);
                     NumericOps.$init$(this);
                     StochasticDiffFunction.$init$(this);
                     DiffFunction.$init$(this);
                  }
               }
            };
         }

         public {
            this.opMul$3 = opMul$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 castOps$(final DiffFunctionOpImplicits $this, final .less.colon.less v1ev, final .less.colon.less V2ev, final UFunc.UImpl2 op) {
      return $this.castOps(v1ev, V2ev, op);
   }

   default UFunc.UImpl2 castOps(final .less.colon.less v1ev, final .less.colon.less V2ev, final UFunc.UImpl2 op) {
      return op;
   }

   static void $init$(final DiffFunctionOpImplicits $this) {
   }
}
