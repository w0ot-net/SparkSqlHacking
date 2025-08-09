package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanForeachValues;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseAxis;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-gaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006-\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0005\u0006\u0011\u0002!\u0019!\u0013\u0005\u0006m\u0002!\u0019a\u001e\u0005\b\u0003;\u0001A1AA\u0010\u0011\u001d\ty\u0005\u0001C\u0002\u0003#Bq!a \u0001\t\u0007\t\t\tC\u0004\u0002,\u0002!\u0019!!,\u0003%\t\u0013x.\u00193dCN$X\r\u001a*poN|\u0005o\u001d\u0006\u0003\u00171\ta\u0001\\5oC2<'\"A\u0007\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001\u0001\t\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0004\u0005\u0002\u00123%\u0011!D\u0005\u0002\u0005+:LG/\u0001\ndC:l\u0015\r\u001d,bYV,7o\u0018\"S_^\u001cX#B\u000f+i]RDC\u0001\u0010=!\u0019y\"\u0005J\u001a7s5\t\u0001E\u0003\u0002\"\u0015\u000591/\u001e9q_J$\u0018BA\u0012!\u00051\u0019\u0015M\\'baZ\u000bG.^3t!\u0011)c\u0005K\u001a\u000e\u0003)I!a\n\u0006\u0003\u001f\t\u0013x.\u00193dCN$X\r\u001a*poN\u0004\"!\u000b\u0016\r\u0001\u0011)1F\u0001b\u0001Y\t\tA+\u0005\u0002.aA\u0011\u0011CL\u0005\u0003_I\u0011qAT8uQ&tw\r\u0005\u0002\u0012c%\u0011!G\u0005\u0002\u0004\u0003:L\bCA\u00155\t\u0015)$A1\u0001-\u0005\u001d\u0011vn\u001e+za\u0016\u0004\"!K\u001c\u0005\u000ba\u0012!\u0019\u0001\u0017\u0003\u0013I+7/\u001e7u%><\bCA\u0015;\t\u0015Y$A1\u0001-\u0005\u0019\u0011Vm];mi\")QH\u0001a\u0002}\u0005\u00111m\u0019\t\b?}B\u0013i\r\u001c:\u0013\t\u0001\u0005EA\bDC:\u001cu\u000e\u001c7baN,\u0017\t_5t\u001d\t\u0011UI\u0004\u0002&\u0007&\u0011AIC\u0001\u0005\u0003bL7/\u0003\u0002G\u000f\u0006\u0011q,\r\u0006\u0003\t*\t\u0011C\u0019:pC\u0012\u001c\u0017m\u001d;Pa~\u0013%k\\<t+\u0019QE\f\u00192sIR!1*\u001a8u!\u0015a\u0005l\u00170d\u001d\tiUK\u0004\u0002O':\u0011qJU\u0007\u0002!*\u0011\u0011KD\u0001\u0007yI|w\u000e\u001e \n\u00035I!\u0001\u0016\u0007\u0002\u000f\u001d,g.\u001a:jG&\u0011akV\u0001\u0006+\u001a+hn\u0019\u0006\u0003)2I!!\u0017.\u0003\u000bUKU\u000e\u001d7\u000b\u0005Y;\u0006CA\u0015]\t\u0015i6A1\u0001-\u0005\ty\u0005\u000f\u0005\u0003&M}\u000b\u0007CA\u0015a\t\u0015Y3A1\u0001-!\tI#\rB\u00036\u0007\t\u0007A\u0006\u0005\u0002*I\u0012)1h\u0001b\u0001Y!)am\u0001a\u0002O\u0006A\u0001.\u00198eQ>dG\rE\u0003iW~\u000b\u0015M\u0004\u0002 S&\u0011!\u000eI\u0001\u0010\u0007\u0006t7i\u001c7mCB\u001cX-\u0011=jg&\u0011A.\u001c\u0002\t\u0011\u0006tG\rS8mI*\u0011!\u000e\t\u0005\u0006_\u000e\u0001\u001d\u0001]\u0001\u0003_B\u0004R\u0001\u0014-\\CF\u0004\"!\u000b:\u0005\u000bM\u001c!\u0019\u0001\u0017\u0003\u0011=\u0003(+Z:vYRDQ!P\u0002A\u0004U\u0004raH `\u0003\u0006\f8-\u0001\rce>\fGmY1ti&s\u0007\u000f\\1dK>\u0003xL\u0011*poN,\"\u0002_?\u0002\u0002\u0005\u0015\u0011qCA\u000e)\u001dI\u0018qAA\u0006\u0003\u001f\u0001B\u0001\u0014>}}&\u00111P\u0017\u0002\f\u0013:\u0004F.Y2f\u00136\u0004H\u000e\u0005\u0002*{\u0012)Q\f\u0002b\u0001YA)QEJ@\u0002\u0004A\u0019\u0011&!\u0001\u0005\u000b-\"!\u0019\u0001\u0017\u0011\u0007%\n)\u0001B\u00036\t\t\u0007A\u0006\u0003\u0004g\t\u0001\u000f\u0011\u0011\u0002\t\u0007Q.|\u0018)a\u0001\t\r=$\u00019AA\u0007!\u0015a%\u0010`A\u0002\u0011\u0019iD\u0001q\u0001\u0002\u0012A9q$a\u0005\u0000\u0003\u0006\r\u0011bAA\u000bA\ty1)\u00198Ue\u00064XM]:f\u0003bL7\u000f\u0002\u0004\u0002\u001a\u0011\u0011\r\u0001\f\u0002\u0004%\"\u001bF!B:\u0005\u0005\u0004a\u0013A\u00052s_\u0006$7-Y:u\u001fB\u0014tL\u0011*poN,b\"!\t\u0002,\u0005E\u0012QGA\u001d\u0003\u0013\ni\u0004\u0006\u0005\u0002$\u0005}\u00121IA&!-a\u0015QEA\u0015\u0003[\t9$a\u000f\n\u0007\u0005\u001d\"L\u0001\u0004V\u00136\u0004HN\r\t\u0004S\u0005-B!B/\u0006\u0005\u0004a\u0003CB\u0013'\u0003_\t\u0019\u0004E\u0002*\u0003c!QaK\u0003C\u00021\u00022!KA\u001b\t\u0015)TA1\u0001-!\rI\u0013\u0011\b\u0003\u0007\u00033)!\u0019\u0001\u0017\u0011\u0007%\ni\u0004B\u0003<\u000b\t\u0007A\u0006\u0003\u0004g\u000b\u0001\u000f\u0011\u0011\t\t\bQ.\fy#QA\u001a\u0011\u0019yW\u0001q\u0001\u0002FAYA*!\n\u0002*\u0005M\u0012qGA$!\rI\u0013\u0011\n\u0003\u0006g\u0016\u0011\r\u0001\f\u0005\u0007{\u0015\u0001\u001d!!\u0014\u0011\u0017}y\u0014qF!\u00024\u0005\u001d\u00131H\u0001\u0015EJ|\u0017\rZ2bgR|\u0005OM03?\n\u0013vn^:\u0016\u001d\u0005M\u0013\u0011LA3\u0003S\ni&!\u001f\u0002nQA\u0011QKA8\u0003g\nY\bE\u0006M\u0003K\t9&a\u0017\u0002b\u0005-\u0004cA\u0015\u0002Z\u0011)QL\u0002b\u0001YA\u0019\u0011&!\u0018\u0005\r\u0005}cA1\u0001-\u0005\ra\u0005j\u0015\t\u0007K\u0019\n\u0019'a\u001a\u0011\u0007%\n)\u0007B\u0003,\r\t\u0007A\u0006E\u0002*\u0003S\"Q!\u000e\u0004C\u00021\u00022!KA7\t\u0015YdA1\u0001-\u0011\u00191g\u0001q\u0001\u0002rA9\u0001n[A2\u0003\u0006\u001d\u0004BB8\u0007\u0001\b\t)\bE\u0006M\u0003K\t9&a\u0017\u0002h\u0005]\u0004cA\u0015\u0002z\u0011)1O\u0002b\u0001Y!1QH\u0002a\u0002\u0003{\u00022bH \u0002d\u0005\u000b9'a\u001e\u0002l\u0005I\"M]8bI\u000e\f7\u000f^%oa2\f7-Z(qe}\u0013%k\\<t+1\t\u0019)!$\u0002\u0014\u0006]\u00151TAU)!\t))!(\u0002\"\u0006\u0015\u0006#\u0003'\u0002\b\u0006-\u0015qRAM\u0013\r\tII\u0017\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HN\r\t\u0004S\u00055E!B/\b\u0005\u0004a\u0003CB\u0013'\u0003#\u000b)\nE\u0002*\u0003'#QaK\u0004C\u00021\u00022!KAL\t\u0015)tA1\u0001-!\rI\u00131\u0014\u0003\u0007\u000339!\u0019\u0001\u0017\t\r\u0019<\u00019AAP!\u001dA7.!%B\u0003+Caa\\\u0004A\u0004\u0005\r\u0006#\u0003'\u0002\b\u0006-\u0015QSAM\u0011\u0019it\u0001q\u0001\u0002(BAq$a\u0005\u0002\u0012\u0006\u000b)\nB\u0003t\u000f\t\u0007A&\u0001\u000bdC:4uN]3bG\"\u0014vn^:`\u0005J{wo]\u000b\u000b\u0003_\u000bY,a0\u0002H\u0006%G\u0003BAY\u0003\u0003\u0004raHAZ\u0003o\u000bi,C\u0002\u00026\u0002\u0012\u0001cQ1o\r>\u0014X-Y2i-\u0006dW/Z:\u0011\r\u00152\u0013\u0011XA_!\rI\u00131\u0018\u0003\u0006W!\u0011\r\u0001\f\t\u0004S\u0005}F!B\u001b\t\u0005\u0004a\u0003bBAb\u0011\u0001\u000f\u0011QY\u0001\u0005SR,'\u000f\u0005\u0005 \u0003'\tI,QA_\t\u0015A\u0004B1\u0001-\t\u0015Y\u0004B1\u0001-\u0001"
)
public interface BroadcastedRowsOps {
   // $FF: synthetic method
   static CanMapValues canMapValues_BRows$(final BroadcastedRowsOps $this, final CanCollapseAxis cc) {
      return $this.canMapValues_BRows(cc);
   }

   default CanMapValues canMapValues_BRows(final CanCollapseAxis cc) {
      return new CanMapValues(cc) {
         private final CanCollapseAxis cc$1;

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public Object map(final BroadcastedRows from, final Function1 fn) {
            return this.cc$1.apply(from.underlying(), Axis._1$.MODULE$, fn);
         }

         public Object mapActive(final BroadcastedRows from, final Function1 fn) {
            return this.map(from, fn);
         }

         public {
            this.cc$1 = cc$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl broadcastOp_BRows$(final BroadcastedRowsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return $this.broadcastOp_BRows(handhold, op, cc);
   }

   default UFunc.UImpl broadcastOp_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return new UFunc.UImpl(cc, op) {
         private final CanCollapseAxis cc$2;
         private final UFunc.UImpl op$1;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Object apply(final BroadcastedRows v) {
            return this.cc$2.apply(v.underlying(), Axis._1$.MODULE$, (x$1) -> this.op$1.apply(x$1));
         }

         public {
            this.cc$2 = cc$2;
            this.op$1 = op$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl broadcastInplaceOp_BRows$(final BroadcastedRowsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return $this.broadcastInplaceOp_BRows(handhold, op, cc);
   }

   default UFunc.InPlaceImpl broadcastInplaceOp_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return new UFunc.InPlaceImpl(cc, op) {
         private final CanTraverseAxis cc$3;
         private final UFunc.InPlaceImpl op$2;

         public void apply(final BroadcastedRows v) {
            this.cc$3.apply(v.underlying(), Axis._1$.MODULE$, (x$2) -> {
               $anonfun$apply$2(this, x$2);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$2(final Object $this, final Object x$2) {
            $this.op$2.apply(x$2);
         }

         public {
            this.cc$3 = cc$3;
            this.op$2 = op$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 broadcastOp2_BRows$(final BroadcastedRowsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return $this.broadcastOp2_BRows(handhold, op, cc);
   }

   default UFunc.UImpl2 broadcastOp2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return new UFunc.UImpl2(cc, op) {
         private final CanCollapseAxis cc$4;
         private final UFunc.UImpl2 op$3;

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

         public Object apply(final BroadcastedRows v, final Object v2) {
            return this.cc$4.apply(v.underlying(), Axis._1$.MODULE$, (x$3) -> this.op$3.apply(x$3, v2));
         }

         public {
            this.cc$4 = cc$4;
            this.op$3 = op$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 broadcastOp2_2_BRows$(final BroadcastedRowsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return $this.broadcastOp2_2_BRows(handhold, op, cc);
   }

   default UFunc.UImpl2 broadcastOp2_2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return new UFunc.UImpl2(cc, op) {
         private final CanCollapseAxis cc$5;
         private final UFunc.UImpl2 op$4;

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

         public Object apply(final Object v, final BroadcastedRows v2) {
            return this.cc$5.apply(v2.underlying(), Axis._1$.MODULE$, (x$4) -> this.op$4.apply(v, x$4));
         }

         public {
            this.cc$5 = cc$5;
            this.op$4 = op$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 broadcastInplaceOp2_BRows$(final BroadcastedRowsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return $this.broadcastInplaceOp2_BRows(handhold, op, cc);
   }

   default UFunc.InPlaceImpl2 broadcastInplaceOp2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return new UFunc.InPlaceImpl2(cc, op) {
         private final CanTraverseAxis cc$6;
         private final UFunc.InPlaceImpl2 op$5;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final BroadcastedRows v, final Object v2) {
            this.cc$6.apply(v.underlying(), Axis._1$.MODULE$, (x$5) -> {
               $anonfun$apply$5(this, v2, x$5);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$5(final Object $this, final Object v2$2, final Object x$5) {
            $this.op$5.apply(x$5, v2$2);
         }

         public {
            this.cc$6 = cc$6;
            this.op$5 = op$5;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanForeachValues canForeachRows_BRows$(final BroadcastedRowsOps $this, final CanTraverseAxis iter) {
      return $this.canForeachRows_BRows(iter);
   }

   default CanForeachValues canForeachRows_BRows(final CanTraverseAxis iter) {
      return new CanForeachValues(iter) {
         private final CanTraverseAxis iter$1;

         public void foreach(final BroadcastedRows from, final Function1 fn) {
            this.iter$1.apply(from.underlying(), Axis._1$.MODULE$, fn);
         }

         public {
            this.iter$1 = iter$1;
         }
      };
   }

   static void $init$(final BroadcastedRowsOps $this) {
   }
}
