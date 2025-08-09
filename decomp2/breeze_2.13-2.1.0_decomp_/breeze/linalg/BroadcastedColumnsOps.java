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
   bytes = "\u0006\u0005\t]baB\u0006\r!\u0003\r\t!\u0005\u0005\u00061\u0001!\t!\u0007\u0005\u0006;\u0001!\u0019A\b\u0005\u0006\u0015\u0002!\u0019a\u0013\u0005\u0006q\u0002!\u0019!\u001f\u0005\b\u0003C\u0001A1AA\u0012\u0011\u001d\t\u0019\u0006\u0001C\u0002\u0003+Bq!a!\u0001\t\u0007\t)\tC\u0004\u00020\u0002!\u0019!!-\t\u000f\u0005\u001d\b\u0001b\u0001\u0002j\"9!q\u0003\u0001\u0005\u0004\te!!\u0006\"s_\u0006$7-Y:uK\u0012\u001cu\u000e\\;n]N|\u0005o\u001d\u0006\u0003\u001b9\ta\u0001\\5oC2<'\"A\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001\u0001\n\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0004\u0005\u0002\u00147%\u0011A\u0004\u0006\u0002\u0005+:LG/\u0001\ndC:l\u0015\r\u001d,bYV,7o\u0018\"D_2\u001cX#B\u0010-mebDC\u0001\u0011?!\u0019\tCEJ\u001b9w5\t!E\u0003\u0002$\u0019\u000591/\u001e9q_J$\u0018BA\u0013#\u00051\u0019\u0015M\\'baZ\u000bG.^3t!\u00119\u0003FK\u001b\u000e\u00031I!!\u000b\u0007\u0003%\t\u0013x.\u00193dCN$X\rZ\"pYVlgn\u001d\t\u0003W1b\u0001\u0001B\u0003.\u0005\t\u0007aFA\u0001U#\ty#\u0007\u0005\u0002\u0014a%\u0011\u0011\u0007\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u00192'\u0003\u00025)\t\u0019\u0011I\\=\u0011\u0005-2D!B\u001c\u0003\u0005\u0004q#AC\"pYVlg\u000eV=qKB\u00111&\u000f\u0003\u0006u\t\u0011\rA\f\u0002\r%\u0016\u001cX\u000f\u001c;D_2,XN\u001c\t\u0003Wq\"Q!\u0010\u0002C\u00029\u0012aAU3tk2$\b\"B \u0003\u0001\b\u0001\u0015AA2d!\u001d\t\u0013IK\"6qmJ!A\u0011\u0012\u0003\u001f\r\u000bgnQ8mY\u0006\u00048/Z!ySNt!\u0001R$\u000f\u0005\u001d*\u0015B\u0001$\r\u0003\u0011\t\u00050[:\n\u0005!K\u0015AA01\u0015\t1E\"A\tce>\fGmY1ti>\u0003xLQ\"pYN,b\u0001\u00140cIR4G\u0003B'haZ\u0004RA\u0014.^A\u0016t!aT,\u000f\u0005A+fBA)U\u001b\u0005\u0011&BA*\u0011\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002W\u001d\u00059q-\u001a8fe&\u001c\u0017B\u0001-Z\u0003\u0015)f)\u001e8d\u0015\t1f\"\u0003\u0002\\9\n)Q+S7qY*\u0011\u0001,\u0017\t\u0003Wy#QaX\u0002C\u00029\u0012!a\u00149\u0011\t\u001dB\u0013m\u0019\t\u0003W\t$Q!L\u0002C\u00029\u0002\"a\u000b3\u0005\u000b]\u001a!\u0019\u0001\u0018\u0011\u0005-2G!B\u001f\u0004\u0005\u0004q\u0003\"\u00025\u0004\u0001\bI\u0017\u0001\u00035b]\u0012Dw\u000e\u001c3\u0011\u000b)l\u0017mQ2\u000f\u0005\u0005Z\u0017B\u00017#\u0003=\u0019\u0015M\\\"pY2\f\u0007o]3Bq&\u001c\u0018B\u00018p\u0005!A\u0015M\u001c3I_2$'B\u00017#\u0011\u0015\t8\u0001q\u0001s\u0003\ty\u0007\u000fE\u0003O5v\u001b7\u000f\u0005\u0002,i\u0012)Qo\u0001b\u0001]\tAq\n\u001d*fgVdG\u000fC\u0003@\u0007\u0001\u000fq\u000fE\u0004\"\u0003\u0006\u001c5m]3\u00021\t\u0014x.\u00193dCN$\u0018J\u001c9mC\u000e,w\n]0C\u0007>d7/\u0006\u0006{\u007f\u0006\u0015\u0011\u0011BA\u000e\u0003?!ra_A\u0006\u0003\u001f\t\u0019\u0002E\u0003Oyz\f\t!\u0003\u0002~9\nY\u0011J\u001c)mC\u000e,\u0017*\u001c9m!\tYs\u0010B\u0003`\t\t\u0007a\u0006\u0005\u0004(Q\u0005\r\u0011q\u0001\t\u0004W\u0005\u0015A!B\u0017\u0005\u0005\u0004q\u0003cA\u0016\u0002\n\u0011)q\u0007\u0002b\u0001]!1\u0001\u000e\u0002a\u0002\u0003\u001b\u0001rA[7\u0002\u0004\r\u000b9\u0001\u0003\u0004r\t\u0001\u000f\u0011\u0011\u0003\t\u0006\u001drt\u0018q\u0001\u0005\u0007\u007f\u0011\u0001\u001d!!\u0006\u0011\u0011\u0005\n9\"a\u0001D\u0003\u000fI1!!\u0007#\u0005=\u0019\u0015M\u001c+sCZ,'o]3Bq&\u001cHABA\u000f\t\t\u0007aFA\u0002S\u0011N#Q!\u001e\u0003C\u00029\n!C\u0019:pC\u0012\u001c\u0017m\u001d;PaJz&iQ8mgVq\u0011QEA\u0018\u0003k\tI$!\u0010\u0002N\u0005\u0005C\u0003CA\u0014\u0003\u0007\n9%a\u0014\u0011\u00179\u000bI#!\f\u00022\u0005m\u0012qH\u0005\u0004\u0003Wa&AB+J[Bd'\u0007E\u0002,\u0003_!QaX\u0003C\u00029\u0002ba\n\u0015\u00024\u0005]\u0002cA\u0016\u00026\u0011)Q&\u0002b\u0001]A\u00191&!\u000f\u0005\u000b]*!\u0019\u0001\u0018\u0011\u0007-\ni\u0004\u0002\u0004\u0002\u001e\u0015\u0011\rA\f\t\u0004W\u0005\u0005C!B\u001f\u0006\u0005\u0004q\u0003B\u00025\u0006\u0001\b\t)\u0005E\u0004k[\u0006M2)a\u000e\t\rE,\u00019AA%!-q\u0015\u0011FA\u0017\u0003o\tY$a\u0013\u0011\u0007-\ni\u0005B\u0003v\u000b\t\u0007a\u0006\u0003\u0004@\u000b\u0001\u000f\u0011\u0011\u000b\t\fC\u0005\u000b\u0019dQA\u001c\u0003\u0017\ny$\u0001\u000bce>\fGmY1ti>\u0003(g\u0018\u001a`\u0005\u000e{Gn]\u000b\u000f\u0003/\ni&!\u001b\u0002n\u0005\u0005\u0014QPA9)!\tI&a\u001d\u0002x\u0005}\u0004c\u0003(\u0002*\u0005m\u0013qLA3\u0003_\u00022aKA/\t\u0015yfA1\u0001/!\rY\u0013\u0011\r\u0003\u0007\u0003G2!\u0019\u0001\u0018\u0003\u00071C5\u000b\u0005\u0004(Q\u0005\u001d\u00141\u000e\t\u0004W\u0005%D!B\u0017\u0007\u0005\u0004q\u0003cA\u0016\u0002n\u0011)qG\u0002b\u0001]A\u00191&!\u001d\u0005\u000bu2!\u0019\u0001\u0018\t\r!4\u00019AA;!\u001dQW.a\u001aD\u0003WBa!\u001d\u0004A\u0004\u0005e\u0004c\u0003(\u0002*\u0005m\u0013qLA6\u0003w\u00022aKA?\t\u0015)hA1\u0001/\u0011\u0019yd\u0001q\u0001\u0002\u0002BY\u0011%QA4\u0007\u0006-\u00141PA8\u0003e\u0011'o\\1eG\u0006\u001cH/\u00138qY\u0006\u001cWm\u001493?\n\u001bu\u000e\\:\u0016\u0019\u0005\u001d\u0015\u0011SAL\u00037\u000by*!,\u0015\u0011\u0005%\u0015\u0011UAS\u0003S\u0003\u0012BTAF\u0003\u001f\u000b\u0019*!(\n\u0007\u00055EL\u0001\u0007J]Bc\u0017mY3J[Bd'\u0007E\u0002,\u0003##QaX\u0004C\u00029\u0002ba\n\u0015\u0002\u0016\u0006e\u0005cA\u0016\u0002\u0018\u0012)Qf\u0002b\u0001]A\u00191&a'\u0005\u000b]:!\u0019\u0001\u0018\u0011\u0007-\ny\n\u0002\u0004\u0002\u001e\u001d\u0011\rA\f\u0005\u0007Q\u001e\u0001\u001d!a)\u0011\u000f)l\u0017QS\"\u0002\u001a\"1\u0011o\u0002a\u0002\u0003O\u0003\u0012BTAF\u0003\u001f\u000bI*!(\t\r}:\u00019AAV!!\t\u0013qCAK\u0007\u0006eE!B;\b\u0005\u0004q\u0013\u0001\u00062s_\u0006$7-Y:u\u001fB\u001ct,M0C\u0007>d7/\u0006\t\u00024\u0006u\u0016\u0011ZAa\u0003\u001b\f\t.!9\u0002VRA\u0011QWAl\u00037\f\u0019\u000fE\u0007O\u0003o\u000bY,a0\u0002F\u0006=\u00171[\u0005\u0004\u0003sc&AB+J[Bd7\u0007E\u0002,\u0003{#Qa\u0018\u0005C\u00029\u00022aKAa\t\u0019\t\u0019\r\u0003b\u0001]\t\u0011\u0011)\r\t\u0007O!\n9-a3\u0011\u0007-\nI\rB\u0003.\u0011\t\u0007a\u0006E\u0002,\u0003\u001b$Qa\u000e\u0005C\u00029\u00022aKAi\t\u0019\ti\u0002\u0003b\u0001]A\u00191&!6\u0005\u000buB!\u0019\u0001\u0018\t\r!D\u00019AAm!\u001dQW.a2D\u0003\u0017Da!\u001d\u0005A\u0004\u0005u\u0007#\u0004(\u00028\u0006m\u0016qXAf\u0003\u001f\fy\u000eE\u0002,\u0003C$Q!\u001e\u0005C\u00029Baa\u0010\u0005A\u0004\u0005\u0015\bcC\u0011B\u0003\u000f\u001c\u00151ZAp\u0003'\f1D\u0019:pC\u0012\u001c\u0017m\u001d;J]Bd\u0017mY3PaNz\u0016g\u0018\"D_2\u001cXCDAv\u0003k\fI0a@\u0003\u0004\t\u001d!Q\u0003\u000b\t\u0003[\u0014IA!\u0004\u0003\u0012AYa*a<\u0002t\u0006]\u00181 B\u0003\u0013\r\t\t\u0010\u0018\u0002\r\u0013:\u0004F.Y2f\u00136\u0004Hn\r\t\u0004W\u0005UH!B0\n\u0005\u0004q\u0003cA\u0016\u0002z\u00121\u00111Y\u0005C\u00029\u0002ba\n\u0015\u0002~\n\u0005\u0001cA\u0016\u0002\u0000\u0012)Q&\u0003b\u0001]A\u00191Fa\u0001\u0005\u000b]J!\u0019\u0001\u0018\u0011\u0007-\u00129\u0001\u0002\u0004\u0002\u001e%\u0011\rA\f\u0005\u0007Q&\u0001\u001dAa\u0003\u0011\u000f)l\u0017Q`\"\u0003\u0002!1\u0011/\u0003a\u0002\u0005\u001f\u00012BTAx\u0003g\f9P!\u0001\u0003\u0006!1q(\u0003a\u0002\u0005'\u0001\u0002\"IA\f\u0003{\u001c%\u0011\u0001\u0003\u0006k&\u0011\rAL\u0001\u0018G\u0006tgi\u001c:fC\u000eD7i\u001c7v[:\u001cxLQ\"pYN,\"Ba\u0007\u0003(\t-\"1\u0007B\u001b)\u0011\u0011iB!\f\u0011\u000f\u0005\u0012yBa\t\u0003*%\u0019!\u0011\u0005\u0012\u0003!\r\u000bgNR8sK\u0006\u001c\u0007NV1mk\u0016\u001c\bCB\u0014)\u0005K\u0011I\u0003E\u0002,\u0005O!Q!\f\u0006C\u00029\u00022a\u000bB\u0016\t\u00159$B1\u0001/\u0011\u001d\u0011yC\u0003a\u0002\u0005c\tA!\u001b;feBA\u0011%a\u0006\u0003&\r\u0013I\u0003B\u0003;\u0015\t\u0007a\u0006B\u0003>\u0015\t\u0007a\u0006"
)
public interface BroadcastedColumnsOps {
   // $FF: synthetic method
   static CanMapValues canMapValues_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis cc) {
      return $this.canMapValues_BCols(cc);
   }

   default CanMapValues canMapValues_BCols(final CanCollapseAxis cc) {
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

         public Object map(final BroadcastedColumns from, final Function1 fn) {
            return this.cc$1.apply(from.underlying(), Axis._0$.MODULE$, fn);
         }

         public Object mapActive(final BroadcastedColumns from, final Function1 fn) {
            return this.map(from, fn);
         }

         public {
            this.cc$1 = cc$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl broadcastOp_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return $this.broadcastOp_BCols(handhold, op, cc);
   }

   default UFunc.UImpl broadcastOp_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
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

         public Object apply(final BroadcastedColumns v) {
            return this.cc$2.apply(v.underlying(), Axis._0$.MODULE$, (x$1) -> this.op$1.apply(x$1));
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
   static UFunc.InPlaceImpl broadcastInplaceOp_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return $this.broadcastInplaceOp_BCols(handhold, op, cc);
   }

   default UFunc.InPlaceImpl broadcastInplaceOp_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return new UFunc.InPlaceImpl(cc, op) {
         private final CanTraverseAxis cc$3;
         private final UFunc.InPlaceImpl op$2;

         public void apply(final BroadcastedColumns v) {
            this.cc$3.apply(v.underlying(), Axis._0$.MODULE$, (x$2) -> {
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
   static UFunc.UImpl2 broadcastOp2_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return $this.broadcastOp2_BCols(handhold, op, cc);
   }

   default UFunc.UImpl2 broadcastOp2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
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

         public Object apply(final BroadcastedColumns v, final Object v2) {
            return this.cc$4.apply(v.underlying(), Axis._0$.MODULE$, (x$3) -> this.op$3.apply(x$3, v2));
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
   static UFunc.UImpl2 broadcastOp2_2_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return $this.broadcastOp2_2_BCols(handhold, op, cc);
   }

   default UFunc.UImpl2 broadcastOp2_2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
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

         public Object apply(final Object v, final BroadcastedColumns v2) {
            return this.cc$5.apply(v2.underlying(), Axis._0$.MODULE$, (x$4) -> this.op$4.apply(v, x$4));
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
   static UFunc.InPlaceImpl2 broadcastInplaceOp2_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return $this.broadcastInplaceOp2_BCols(handhold, op, cc);
   }

   default UFunc.InPlaceImpl2 broadcastInplaceOp2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
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

         public void apply(final BroadcastedColumns v, final Object v2) {
            this.cc$6.apply(v.underlying(), Axis._0$.MODULE$, (x$5) -> {
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
   static UFunc.UImpl3 broadcastOp3_1_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.UImpl3 op, final CanCollapseAxis cc) {
      return $this.broadcastOp3_1_BCols(handhold, op, cc);
   }

   default UFunc.UImpl3 broadcastOp3_1_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl3 op, final CanCollapseAxis cc) {
      return new UFunc.UImpl3(cc, op) {
         private final CanCollapseAxis cc$7;
         private final UFunc.UImpl3 op$6;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public Object apply(final Object v, final BroadcastedColumns v2, final Object v3) {
            return this.cc$7.apply(v2.underlying(), Axis._0$.MODULE$, (x$6) -> this.op$6.apply(v, x$6, v3));
         }

         public {
            this.cc$7 = cc$7;
            this.op$6 = op$6;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 broadcastInplaceOp3_1_BCols$(final BroadcastedColumnsOps $this, final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl3 op, final CanTraverseAxis cc) {
      return $this.broadcastInplaceOp3_1_BCols(handhold, op, cc);
   }

   default UFunc.InPlaceImpl3 broadcastInplaceOp3_1_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl3 op, final CanTraverseAxis cc) {
      return new UFunc.InPlaceImpl3(cc, op) {
         private final CanTraverseAxis cc$8;
         private final UFunc.InPlaceImpl3 op$7;

         public void apply(final Object v, final BroadcastedColumns v2, final Object v3) {
            this.cc$8.apply(v2.underlying(), Axis._0$.MODULE$, (x$7) -> {
               $anonfun$apply$7(this, v, v3, x$7);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$7(final Object $this, final Object v$3, final Object v3$2, final Object x$7) {
            $this.op$7.apply(v$3, x$7, v3$2);
         }

         public {
            this.cc$8 = cc$8;
            this.op$7 = op$7;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanForeachValues canForeachColumns_BCols$(final BroadcastedColumnsOps $this, final CanTraverseAxis iter) {
      return $this.canForeachColumns_BCols(iter);
   }

   default CanForeachValues canForeachColumns_BCols(final CanTraverseAxis iter) {
      return new CanForeachValues(iter) {
         private final CanTraverseAxis iter$1;

         public void foreach(final BroadcastedColumns from, final Function1 fn) {
            this.iter$1.apply(from.underlying(), Axis._0$.MODULE$, fn);
         }

         public {
            this.iter$1 = iter$1;
         }
      };
   }

   static void $init$(final BroadcastedColumnsOps $this) {
   }
}
