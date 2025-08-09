package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.gymnastics.NotGiven;
import breeze.linalg.TensorLike;
import breeze.linalg.Transpose;
import breeze.linalg.package;
import breeze.linalg.package$;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanTranspose;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t-ba\u0002\b\u0010!\u0003\r\tA\u0006\u0005\u0006C\u0001!\tA\t\u0005\u0006M\u0001!\u0019a\n\u0005\u0006=\u0002!\u0019a\u0018\u0004\u0005}\u0002\tq\u0010\u0003\u0006\u0002\u0004\u0011\u0011\t\u0011)A\u0005\u0003\u000bAq!a\u0006\u0005\t\u0003\tI\u0002C\u0004\u0002\"\u0011!\t!a\t\t\u0013\u0005%\u0002!!A\u0005\u0004\u0005-\u0002bBA \u0001\u0011\r\u0011\u0011\t\u0005\b\u0003W\u0002A1AA7\u0011\u001d\t\t\n\u0001C\u0002\u0003'Cq!!,\u0001\t\u0007\ty\u000bC\u0004\u0002z\u0002!\u0019!a?\u0003+Q\u0013\u0018M\\:q_N,w\n]:`\u0019><\bK]5pe)\u0011\u0001#E\u0001\n_B,'/\u0019;peNT!AE\n\u0002\r1Lg.\u00197h\u0015\u0005!\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u00019R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VM\u001a\t\u0003=}i\u0011aD\u0005\u0003A=\u0011!bR3oKJL7m\u00149t\u0003\u0019!\u0013N\\5uIQ\t1\u0005\u0005\u0002\u0019I%\u0011Q%\u0007\u0002\u0005+:LG/A\u000fj[Bdw,R(q?R#x,\u0016;`KF|&\u000b^0ge>lw\fV0V+\u0019ASgQ&U\u001dR\u0019\u0011\u0006\u0015,\u0011\r)\u00024GP%N\u001d\tYc&D\u0001-\u0015\ti3#A\u0004hK:,'/[2\n\u0005=b\u0013!B+Gk:\u001c\u0017BA\u00193\u0005\u0019)\u0016*\u001c9me)\u0011q\u0006\f\t\u0003iUb\u0001\u0001B\u00037\u0005\t\u0007qG\u0001\u0002PaF\u0011\u0001h\u000f\t\u00031eJ!AO\r\u0003\u000f9{G\u000f[5oOB\u00111\u0006P\u0005\u0003{1\u0012\u0001#\u00127f[\u0016tGo^5tKV3UO\\2\u0011\u0007}\u0002%)D\u0001\u0012\u0013\t\t\u0015CA\u0005Ue\u0006t7\u000f]8tKB\u0011Ag\u0011\u0003\u0006\t\n\u0011\r!\u0012\u0002\u0002)F\u0011\u0001H\u0012\t\u00031\u001dK!\u0001S\r\u0003\u0007\u0005s\u0017\u0010E\u0002@\u0001*\u0003\"\u0001N&\u0005\u000b1\u0013!\u0019A#\u0003\u0003U\u0003\"\u0001\u000e(\u0005\u000b=\u0013!\u0019A#\u0003\u0005I#\u0006\"B)\u0003\u0001\b\u0011\u0016AA8q!\u0019Q\u0003g\r\"K'B\u0011A\u0007\u0016\u0003\u0006+\n\u0011\r!\u0012\u0002\u0002%\")qK\u0001a\u00021\u0006a1-\u00198Ue\u0006t7\u000f]8tKB!\u0011\fX*N\u001b\u0005Q&BA.\u0012\u0003\u001d\u0019X\u000f\u001d9peRL!!\u0018.\u0003\u0019\r\u000bg\u000e\u0016:b]N\u0004xn]3\u0002\u001d1Lg\r^%o!2\f7-Z(qgV)\u0001-\u001a5kuR!\u0011m\u001b<}!\u0015Q#\r\u001a4j\u0013\t\u0019'G\u0001\u0007J]Bc\u0017mY3J[Bd'\u0007\u0005\u00025K\u0012)ag\u0001b\u0001oA\u0019q\bQ4\u0011\u0005QBG!\u0002#\u0004\u0005\u0004)\u0005C\u0001\u001bk\t\u0015a5A1\u0001F\u0011\u0015a7\u0001q\u0001n\u0003%qw\u000e^*dC2\f'\u000fE\u0002ocNl\u0011a\u001c\u0006\u0003aN\t!bZ=n]\u0006\u001cH/[2t\u0013\t\u0011xN\u0001\u0005O_R<\u0015N^3o!\u0011IFoZ5\n\u0005UT&\u0001C*dC2\f'o\u00144\t\u000b]\u001c\u00019\u0001=\u0002\rQ\u0014\u0018M\\:V!\u0011IF,[=\u0011\u0005QRH!B>\u0004\u0005\u0004)%AA+U\u0011\u0015\t6\u0001q\u0001~!\u0015Q#\rZ4z\u0005%a\u0015N\u001a;BaBd\u00170\u0006\u0004\u0002\u0002\u0005=\u0011QC\n\u0003\t]\taa\u0018;sC:\u001c\b\u0003B A\u0003\u000f\u0001raPA\u0005\u0003\u001b\t\u0019\"C\u0002\u0002\fE\u0011a\u0001V3og>\u0014\bc\u0001\u001b\u0002\u0010\u00111\u0011\u0011\u0003\u0003C\u0002\u0015\u0013\u0011a\u0013\t\u0004i\u0005UA!\u0002#\u0005\u0005\u0004)\u0015A\u0002\u001fj]&$h\b\u0006\u0003\u0002\u001c\u0005}\u0001cBA\u000f\t\u00055\u00111C\u0007\u0002\u0001!9\u00111\u0001\u0004A\u0002\u0005\u0015\u0011!B1qa2LH\u0003BA\n\u0003KAq!a\n\b\u0001\u0004\ti!A\u0001j\u0003%a\u0015N\u001a;BaBd\u00170\u0006\u0004\u0002.\u0005M\u0012q\u0007\u000b\u0005\u0003_\tI\u0004E\u0004\u0002\u001e\u0011\t\t$!\u000e\u0011\u0007Q\n\u0019\u0004\u0002\u0004\u0002\u0012!\u0011\r!\u0012\t\u0004i\u0005]B!\u0002#\t\u0005\u0004)\u0005bBA\u0002\u0011\u0001\u0007\u00111\b\t\u0005\u007f\u0001\u000bi\u0004E\u0004@\u0003\u0013\t\t$!\u000e\u0002\u00131Lg\r^*mS\u000e,W\u0003DA\"\u0003S\ny%a\u0015\u0002b\u0005eCCBA#\u00037\n\u0019\u0007E\u0005Z\u0003\u000f\nY%!\u0015\u0002X%\u0019\u0011\u0011\n.\u0003\u0011\r\u000bgn\u00157jG\u0016\u0004Ba\u0010!\u0002NA\u0019A'a\u0014\u0005\u000b\u0011K!\u0019A#\u0011\u0007Q\n\u0019\u0006\u0002\u0004\u0002V%\u0011\r!\u0012\u0002\u0002'B\u0019A'!\u0017\u0005\u000bmL!\u0019A#\t\rEK\u00019AA/!%I\u0016qIA'\u0003#\ny\u0006E\u00025\u0003C\"Q\u0001T\u0005C\u0002\u0015Cq!!\u001a\n\u0001\b\t9'A\u0003ue\u0006t7\u000f\u0005\u0004Z9\u0006}\u0013q\u000b\u0003\u0006m%\u0011\r!R\u0001\nY&4G/\u0016$v]\u000e,\"\"a\u001c\u0002z\u0005}\u00141RAB)\u0019\t\t(!\"\u0002\u000eBI!&a\u001d\u0002x\u0005m\u0014\u0011Q\u0005\u0004\u0003k\u0012$!B+J[Bd\u0007c\u0001\u001b\u0002z\u0011)aG\u0003b\u0001\u000bB!q\bQA?!\r!\u0014q\u0010\u0003\u0006\t*\u0011\r!\u0012\t\u0004i\u0005\rE!B>\u000b\u0005\u0004)\u0005BB)\u000b\u0001\b\t9\tE\u0005+\u0003g\n9(! \u0002\nB\u0019A'a#\u0005\u000b1S!\u0019A#\t\u000f\u0005\u0015$\u0002q\u0001\u0002\u0010B1\u0011\fXAE\u0003\u0003\u000bA$[7qY~{\u0005oX%o!2\f7-Z0Ui~3'o\\7`\u001fB|F+\u0006\u0005\u0002\u0016\u0006}\u0015QUAV)\u0011\t9*a*\u0011\u000f)\nI*!(\u0002\"&\u0019\u00111\u0014\u001a\u0003\u0017%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\t\u0004i\u0005}E!\u0002\u001c\f\u0005\u0004)\u0005\u0003B A\u0003G\u00032\u0001NAS\t\u0015!5B1\u0001F\u0011\u0019\t6\u0002q\u0001\u0002*B9!&!'\u0002\u001e\u0006\rF!\u0002'\f\u0005\u0004)\u0015\u0001\u00047jMR,f)\u001e8dg}\u000bTCEAY\u0003w\u000b\t-!2\u0002\\\u0006-\u0017q]Ay\u0003#$\"\"a-\u0002T\u0006}\u00171^Az!5Q\u0013QWA]\u0003{\u000b\u0019-!3\u0002P&\u0019\u0011q\u0017\u001a\u0003\rUKU\u000e\u001d74!\r!\u00141\u0018\u0003\u0006m1\u0011\ra\u000e\t\u0005\u007f\u0001\u000by\fE\u00025\u0003\u0003$Q\u0001\u0012\u0007C\u0002\u0015\u00032\u0001NAc\t\u0019\t9\r\u0004b\u0001\u000b\n\u0011AK\r\t\u0004i\u0005-GABAg\u0019\t\u0007QI\u0001\u0002UgA\u0019A'!5\u0005\u000b=c!\u0019A#\t\u000f\u0005UG\u0002q\u0001\u0002X\u00069AO\r+sC:\u001c\bCB-]\u0003\u0007\fI\u000eE\u00025\u00037$a!!8\r\u0005\u0004)%AA+3\u0011\u001d\t\t\u000f\u0004a\u0002\u0003G\fq\u0001^\u001aUe\u0006t7\u000f\u0005\u0004Z9\u0006%\u0017Q\u001d\t\u0004i\u0005\u001dHABAu\u0019\t\u0007QI\u0001\u0002Vg!1\u0011\u000b\u0004a\u0002\u0003[\u0004RBKA[\u0003s\u000by,!7\u0002f\u0006=\bc\u0001\u001b\u0002r\u0012)Q\u000b\u0004b\u0001\u000b\"9\u0011Q\u001f\u0007A\u0004\u0005]\u0018A\u0002;sC:\u001c(\u000b\u0005\u0004Z9\u0006=\u0018qZ\u0001\u0014Y&4G/\u0016$v]\u000eLe\u000e\u001d7bG\u0016\u001ct,M\u000b\u000f\u0003{\u00149A!\u0004\u0003\u0012\tu!Q\u0005B\u000b)!\tyPa\u0006\u0003 \t\u001d\u0002c\u0003\u0016\u0003\u0002\t\u0015!\u0011\u0002B\b\u0005'I1Aa\u00013\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d74!\r!$q\u0001\u0003\u0006m5\u0011\r!\u0012\t\u0005\u007f\u0001\u0013Y\u0001E\u00025\u0005\u001b!Q\u0001R\u0007C\u0002\u0015\u00032\u0001\u000eB\t\t\u0019\t9-\u0004b\u0001\u000bB\u0019AG!\u0006\u0005\r\u00055WB1\u0001F\u0011\u001d\t).\u0004a\u0002\u00053\u0001b!\u0017/\u0003\u0010\tm\u0001c\u0001\u001b\u0003\u001e\u00111\u0011Q\\\u0007C\u0002\u0015Cq!!9\u000e\u0001\b\u0011\t\u0003\u0005\u0004Z9\nM!1\u0005\t\u0004i\t\u0015BABAu\u001b\t\u0007Q\t\u0003\u0004R\u001b\u0001\u000f!\u0011\u0006\t\fU\t\u0005!Q\u0001B\u0006\u00057\u0011\u0019\u0003"
)
public interface TransposeOps_LowPrio2 extends GenericOps {
   // $FF: synthetic method
   static UFunc.UImpl2 impl_EOp_Tt_Ut_eq_Rt_from_T_U$(final TransposeOps_LowPrio2 $this, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return $this.impl_EOp_Tt_Ut_eq_Rt_from_T_U(op, canTranspose);
   }

   default UFunc.UImpl2 impl_EOp_Tt_Ut_eq_Rt_from_T_U(final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return new UFunc.UImpl2(canTranspose, op) {
         private final CanTranspose canTranspose$3;
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

         public Object apply(final Transpose a, final Transpose b) {
            return this.canTranspose$3.apply(this.op$4.apply(a.inner(), b.inner()));
         }

         public {
            this.canTranspose$3 = canTranspose$3;
            this.op$4 = op$4;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 liftInPlaceOps$(final TransposeOps_LowPrio2 $this, final NotGiven notScalar, final CanTranspose transU, final UFunc.InPlaceImpl2 op) {
      return $this.liftInPlaceOps(notScalar, transU, op);
   }

   default UFunc.InPlaceImpl2 liftInPlaceOps(final NotGiven notScalar, final CanTranspose transU, final UFunc.InPlaceImpl2 op) {
      return new UFunc.InPlaceImpl2(op, transU) {
         private final UFunc.InPlaceImpl2 op$5;
         private final CanTranspose transU$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Transpose a, final Object b) {
            this.op$5.apply(a.inner(), this.transU$1.apply(b));
         }

         public {
            this.op$5 = op$5;
            this.transU$1 = transU$1;
         }
      };
   }

   // $FF: synthetic method
   static LiftApply LiftApply$(final TransposeOps_LowPrio2 $this, final Transpose _trans) {
      return $this.LiftApply(_trans);
   }

   default LiftApply LiftApply(final Transpose _trans) {
      return new LiftApply(_trans);
   }

   // $FF: synthetic method
   static CanSlice liftSlice$(final TransposeOps_LowPrio2 $this, final CanSlice op, final CanTranspose trans) {
      return $this.liftSlice(op, trans);
   }

   default CanSlice liftSlice(final CanSlice op, final CanTranspose trans) {
      return new CanSlice(op, trans) {
         private final CanSlice op$6;
         private final CanTranspose trans$1;

         public Object apply(final Transpose from, final Object slice) {
            return (new package.InjectNumericOps(package$.MODULE$.InjectNumericOps(this.op$6.apply(from.inner(), slice)))).t(this.trans$1);
         }

         public {
            this.op$6 = op$6;
            this.trans$1 = trans$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl liftUFunc$(final TransposeOps_LowPrio2 $this, final UFunc.UImpl op, final CanTranspose trans) {
      return $this.liftUFunc(op, trans);
   }

   default UFunc.UImpl liftUFunc(final UFunc.UImpl op, final CanTranspose trans) {
      return new UFunc.UImpl(trans, op) {
         private final CanTranspose trans$2;
         private final UFunc.UImpl op$7;

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

         public Object apply(final Transpose v) {
            return this.trans$2.apply(this.op$7.apply(v.inner()));
         }

         public {
            this.trans$2 = trans$2;
            this.op$7 = op$7;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl impl_Op_InPlace_Tt_from_Op_T$(final TransposeOps_LowPrio2 $this, final UFunc.InPlaceImpl op) {
      return $this.impl_Op_InPlace_Tt_from_Op_T(op);
   }

   default UFunc.InPlaceImpl impl_Op_InPlace_Tt_from_Op_T(final UFunc.InPlaceImpl op) {
      return new UFunc.InPlaceImpl(op) {
         private final UFunc.InPlaceImpl op$8;

         public void apply(final Transpose v) {
            this.op$8.apply(v.inner());
         }

         public {
            this.op$8 = op$8;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl3 liftUFunc3_1$(final TransposeOps_LowPrio2 $this, final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.UImpl3 op, final CanTranspose transR) {
      return $this.liftUFunc3_1(t2Trans, t3Trans, op, transR);
   }

   default UFunc.UImpl3 liftUFunc3_1(final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.UImpl3 op, final CanTranspose transR) {
      return new UFunc.UImpl3(transR, op, t2Trans, t3Trans) {
         private final CanTranspose transR$1;
         private final UFunc.UImpl3 op$9;
         private final CanTranspose t2Trans$1;
         private final CanTranspose t3Trans$1;

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

         public Object apply(final Transpose v, final Object v2, final Object v3) {
            return this.transR$1.apply(this.op$9.apply(v.inner(), this.t2Trans$1.apply(v2), this.t3Trans$1.apply(v3)));
         }

         public {
            this.transR$1 = transR$1;
            this.op$9 = op$9;
            this.t2Trans$1 = t2Trans$1;
            this.t3Trans$1 = t3Trans$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 liftUFuncInplace3_1$(final TransposeOps_LowPrio2 $this, final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.InPlaceImpl3 op) {
      return $this.liftUFuncInplace3_1(t2Trans, t3Trans, op);
   }

   default UFunc.InPlaceImpl3 liftUFuncInplace3_1(final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.InPlaceImpl3 op) {
      return new UFunc.InPlaceImpl3(op, t2Trans, t3Trans) {
         private final UFunc.InPlaceImpl3 op$10;
         private final CanTranspose t2Trans$2;
         private final CanTranspose t3Trans$2;

         public void apply(final Transpose v, final Object v2, final Object v3) {
            this.op$10.apply(v.inner(), this.t2Trans$2.apply(v2), this.t3Trans$2.apply(v3));
         }

         public {
            this.op$10 = op$10;
            this.t2Trans$2 = t2Trans$2;
            this.t3Trans$2 = t3Trans$2;
         }
      };
   }

   static void $init$(final TransposeOps_LowPrio2 $this) {
   }

   public class LiftApply {
      private final Transpose _trans;
      // $FF: synthetic field
      public final TransposeOps_LowPrio2 $outer;

      public Object apply(final Object i) {
         return ((TensorLike)this._trans.inner()).apply(i);
      }

      // $FF: synthetic method
      public TransposeOps_LowPrio2 breeze$linalg$operators$TransposeOps_LowPrio2$LiftApply$$$outer() {
         return this.$outer;
      }

      public LiftApply(final Transpose _trans) {
         this._trans = _trans;
         if (TransposeOps_LowPrio2.this == null) {
            throw null;
         } else {
            this.$outer = TransposeOps_LowPrio2.this;
            super();
         }
      }
   }
}
