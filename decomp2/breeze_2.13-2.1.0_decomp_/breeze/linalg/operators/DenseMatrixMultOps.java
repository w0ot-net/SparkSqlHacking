package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Matrix;
import breeze.linalg.Vector;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005U4q\u0001D\u0007\u0011\u0002\u0007\u0005A\u0003C\u0003#\u0001\u0011\u00051\u0005C\u0004(\u0001\t\u0007I1\u0001\u0015\t\u000fq\u0002!\u0019!C\u0002{!9Q\t\u0001b\u0001\n\u00071\u0005b\u0002(\u0001\u0005\u0004%\u0019a\u0014\u0005\b/\u0002\u0011\r\u0011b\u0001Y\u0011\u001di\u0006A1A\u0005\u0004yCq!\u0019\u0001C\u0002\u0013\r!\rC\u0004f\u0001\t\u0007I1\u00014\t\u000f%\u0004!\u0019!C\u0002U\"9!\u000f\u0001b\u0001\n\u0007\u0019(A\u0005#f]N,W*\u0019;sSblU\u000f\u001c;PaNT!AD\b\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\t\u0012\u0003\u0019a\u0017N\\1mO*\t!#\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0011\u0001QcG\u0010\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g!\taR$D\u0001\u000e\u0013\tqRB\u0001\fEK:\u001cX-T1ue&DX\t\u001f9b]\u0012,Gm\u00149t!\ta\u0002%\u0003\u0002\"\u001b\t)B)\u001a8tK6\u000bGO]5y\u001fB\u001cHj\\<Qe&|\u0017A\u0002\u0013j]&$H\u0005F\u0001%!\t1R%\u0003\u0002'/\t!QK\\5u\u0003}IW\u000e\u001d7`\u001fBlU\u000f\\'biJL\u0007p\u0018#N?Z{V-]0E-~Ke\u000e^\u000b\u0002SA1AD\u000b\u00174meJ!aK\u0007\u0003\u001d\tKg.\u0019:z%\u0016<\u0017n\u001d;ssB\u0019QF\f\u0019\u000e\u0003=I!aL\b\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003-EJ!AM\f\u0003\u0007%sG\u000fE\u0002.iAJ!!N\b\u0003\rY+7\r^8s\u001d\tar'\u0003\u00029\u001b\u0005Yq\n]'vY6\u000bGO]5y!\ri#\bM\u0005\u0003w=\u00111\u0002R3og\u00164Vm\u0019;pe\u0006\u0001\u0013.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0E\u001b~3v,Z9`\tZ{Fj\u001c8h+\u0005q\u0004C\u0002\u000f+\u007f\r3D\tE\u0002.]\u0001\u0003\"AF!\n\u0005\t;\"\u0001\u0002'p]\u001e\u00042!\f\u001bA!\ri#\bQ\u0001\"S6\u0004HnX(q\u001bVdW*\u0019;sSb|F)T0W?\u0016\fx\f\u0012,`\r2|\u0017\r^\u000b\u0002\u000fB1AD\u000b%Mm5\u00032!\f\u0018J!\t1\"*\u0003\u0002L/\t)a\t\\8biB\u0019Q\u0006N%\u0011\u00075R\u0014*\u0001\u0012j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#Uj\u0018,`KF|FIV0E_V\u0014G.Z\u000b\u0002!B1ADK)VmY\u00032!\f\u0018S!\t12+\u0003\u0002U/\t1Ai\\;cY\u0016\u00042!\f\u001bS!\ri#HU\u0001 S6\u0004HnX(q\u001bVdW*\u0019;sSb|F)T0N?\u0016\fx\fR'`\u0013:$X#A-\u0011\rqQCF\u0017\u001c-!\ri3\fM\u0005\u00039>\u0011a!T1ue&D\u0018\u0001I5na2|v\n]'vY6\u000bGO]5y?\u0012ku,T0fc~#Uj\u0018'p]\u001e,\u0012a\u0018\t\u00079)z\u0004MN \u0011\u00075Z\u0006)A\u0011j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#UjX'`KF|F)T0GY>\fG/F\u0001d!\u0019a\"\u0006\u001337\u0011B\u0019QfW%\u0002E%l\u0007\u000f\\0Pa6+H.T1ue&Dx\fR'`\u001b~+\u0017o\u0018#N?\u0012{WO\u00197f+\u00059\u0007C\u0002\u000f+#\"4\u0014\u000bE\u0002.7J\u000b\u0001%[7qY~{\u0005/T;m\u001b\u0006$(/\u001b=`\t6{F)T0fc~#UjX%oiV\t1\u000eE\u00037Y2bC&\u0003\u0002n]\n)\u0011*\u001c9me%\u0011q\u000e\u001d\u0002\u0006+\u001a+hn\u0019\u0006\u0003cF\tqaZ3oKJL7-A\u0011j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#Uj\u0018#N?\u0016\fx\fR'`\u0019>tw-F\u0001u!\u00151DnP @\u0001"
)
public interface DenseMatrixMultOps extends DenseMatrixExpandedOps, DenseMatrixOpsLowPrio {
   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Int_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Long_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Float_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Double_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Int_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Long_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Float_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Double_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Long_$eq(final UFunc.UImpl2 x$1);

   BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Int();

   BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Long();

   BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Float();

   BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Double();

   BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Int();

   BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Long();

   BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Float();

   BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Double();

   UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Int();

   UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Long();

   static void $init$(final DenseMatrixMultOps $this) {
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Int_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mIc$sp(a.rows(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int index$macro$9 = 0;

               for(int limit$macro$11 = a.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     ((c, r) -> {
                        int v = a.apply$mcI$sp(r, c);
                        res.update$mcI$sp(r, res.apply$mcI$sp(r) + v * b.apply$mcII$sp(c));
                     }).apply$mcVII$sp(index$macro$9, index$macro$4);
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_V_Int())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Long_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mJc$sp(a.rows(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               int index$macro$9 = 0;

               for(int limit$macro$11 = a.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     ((c, r) -> {
                        long v = a.apply$mcJ$sp(r, c);
                        res.update$mcJ$sp(r, res.apply$mcJ$sp(r) + v * b.apply$mcIJ$sp(c));
                     }).apply$mcVII$sp(index$macro$9, index$macro$4);
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_V_Long())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Float_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mFc$sp(a.rows(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               int index$macro$9 = 0;

               for(int limit$macro$11 = a.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     ((c, r) -> {
                        float v = a.apply$mcF$sp(r, c);
                        res.update$mcF$sp(r, res.apply$mcF$sp(r) + v * b.apply$mcIF$sp(c));
                     }).apply$mcVII$sp(index$macro$9, index$macro$4);
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_V_Float())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Double_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mDc$sp(a.rows(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               int index$macro$9 = 0;

               for(int limit$macro$11 = a.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     ((c, r) -> {
                        double v = a.apply$mcD$sp(r, c);
                        res.update$mcD$sp(r, res.apply$mcD$sp(r) + v * b.apply$mcID$sp(c));
                     }).apply$mcVII$sp(index$macro$9, index$macro$4);
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_V_Double())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Int_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseMatrix bindingMissing(final DenseMatrix a, final Matrix b) {
            DenseMatrix res = DenseMatrix$.MODULE$.zeros$mIc$sp(a.rows(), b.cols(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int colsB = b.cols();
               int colsA = a.cols();
               int rowsA = a.rows();
               int index$macro$14 = 0;

               for(int limit$macro$16 = colsB; index$macro$14 < limit$macro$16; ++index$macro$14) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = colsA; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int v = b.apply$mcI$sp(index$macro$9, index$macro$14);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = rowsA; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcI$sp(index$macro$4, index$macro$14, res.apply$mcI$sp(index$macro$4, index$macro$14) + v * a.apply$mcI$sp(index$macro$4, index$macro$9));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_M_Int())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Matrix.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Long_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseMatrix bindingMissing(final DenseMatrix a, final Matrix b) {
            DenseMatrix res = DenseMatrix$.MODULE$.zeros$mJc$sp(a.rows(), b.cols(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int colsB = b.cols();
               int colsA = a.cols();
               int rowsA = a.rows();
               int index$macro$14 = 0;

               for(int limit$macro$16 = colsB; index$macro$14 < limit$macro$16; ++index$macro$14) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = colsA; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     long v = b.apply$mcJ$sp(index$macro$9, index$macro$14);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = rowsA; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcJ$sp(index$macro$4, index$macro$14, res.apply$mcJ$sp(index$macro$4, index$macro$14) + v * a.apply$mcJ$sp(index$macro$4, index$macro$9));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_M_Long())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Matrix.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Float_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseMatrix bindingMissing(final DenseMatrix a, final Matrix b) {
            DenseMatrix res = DenseMatrix$.MODULE$.zeros$mFc$sp(a.rows(), b.cols(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int colsB = b.cols();
               int colsA = a.cols();
               int rowsA = a.rows();
               int index$macro$14 = 0;

               for(int limit$macro$16 = colsB; index$macro$14 < limit$macro$16; ++index$macro$14) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = colsA; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     float v = b.apply$mcF$sp(index$macro$9, index$macro$14);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = rowsA; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcF$sp(index$macro$4, index$macro$14, res.apply$mcF$sp(index$macro$4, index$macro$14) + v * a.apply$mcF$sp(index$macro$4, index$macro$9));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_M_Float())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Matrix.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Double_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseMatrix bindingMissing(final DenseMatrix a, final Matrix b) {
            DenseMatrix res = DenseMatrix$.MODULE$.zeros$mDc$sp(a.rows(), b.cols(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int colsB = b.cols();
               int colsA = a.cols();
               int rowsA = a.rows();
               int index$macro$14 = 0;

               for(int limit$macro$16 = colsB; index$macro$14 < limit$macro$16; ++index$macro$14) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = colsA; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     double v = b.apply$mcD$sp(index$macro$9, index$macro$14);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = rowsA; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcD$sp(index$macro$4, index$macro$14, res.apply$mcD$sp(index$macro$4, index$macro$14) + v * a.apply$mcD$sp(index$macro$4, index$macro$9));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_M_Double())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(Matrix.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Int_$eq(new UFunc.UImpl2() {
         private final int blockSizeRow = 2000;
         private final int blockSizeInner = 2000;
         private final int blockSizeCol = 2000;

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

         private int blockSizeRow() {
            return this.blockSizeRow;
         }

         private int blockSizeInner() {
            return this.blockSizeInner;
         }

         private int blockSizeCol() {
            return this.blockSizeCol;
         }

         private void multBlock(final int M, final int N, final int K, final int[] aTrans, final int[] b, final DenseMatrix res, final int resRowOff, final int resColOff) {
            int[] rd = res.data$mcI$sp();
            int rOff = res.offset() + resRowOff + resColOff * res.majorStride();
            int index$macro$12 = 0;

            for(int limit$macro$14 = M; index$macro$12 < limit$macro$14; ++index$macro$12) {
               int index$macro$7 = 0;

               for(int limit$macro$9 = K; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  ((i, k) -> {
                     int sum = 0;
                     int index$macro$2 = 0;

                     for(int limit$macro$4 = N; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        sum += aTrans[i * N + index$macro$2] * b[k * N + index$macro$2];
                     }

                     int var11 = rOff + i + k * res.majorStride();
                     rd[var11] += sum;
                  }).apply$mcVII$sp(index$macro$12, index$macro$7);
               }
            }

         }

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix res = DenseMatrix$.MODULE$.zeros$mIc$sp(a.rows(), b.cols(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] aTrans = new int[scala.math.package..MODULE$.min(this.blockSizeRow() * this.blockSizeInner(), a.size())];
               int[] bBuf = new int[scala.math.package..MODULE$.min(this.blockSizeInner() * this.blockSizeCol(), b.size())];
               int numRowBlocks = (a.rows() + this.blockSizeRow() - 1) / this.blockSizeRow();
               int numInnerBlocks = (a.cols() + this.blockSizeCol() - 1) / this.blockSizeCol();
               int numColBlocks = (b.cols() + this.blockSizeInner() - 1) / this.blockSizeInner();
               int index$macro$39 = 0;

               for(int limit$macro$41 = numRowBlocks; index$macro$39 < limit$macro$41; ++index$macro$39) {
                  int mBegin = index$macro$39 * this.blockSizeRow();
                  int mEnd = scala.math.package..MODULE$.min(mBegin + this.blockSizeRow(), a.rows());
                  int M = mEnd - mBegin;
                  int index$macro$34 = 0;

                  for(int limit$macro$36 = numInnerBlocks; index$macro$34 < limit$macro$36; ++index$macro$34) {
                     int nBegin = index$macro$34 * this.blockSizeInner();
                     int nEnd = scala.math.package..MODULE$.min(nBegin + this.blockSizeInner(), a.cols());
                     int N = nEnd - nBegin;
                     if (a.isTranspose()) {
                        int index$macro$4 = 0;

                        for(int limit$macro$6 = M; index$macro$4 < limit$macro$6; ++index$macro$4) {
                           System.arraycopy(a.data$mcI$sp(), a.offset() + (index$macro$4 + mBegin) * a.majorStride() + nBegin, aTrans, index$macro$4 * N, N);
                        }
                     } else {
                        int index$macro$14 = 0;

                        for(int limit$macro$16 = N; index$macro$14 < limit$macro$16; ++index$macro$14) {
                           int index$macro$9 = 0;

                           for(int limit$macro$11 = M; index$macro$9 < limit$macro$11; ++index$macro$9) {
                              ((n, m) -> aTrans[m * N + n] = a.apply$mcI$sp(m + mBegin, n + nBegin)).apply$mcVII$sp(index$macro$14, index$macro$9);
                           }
                        }
                     }

                     int index$macro$29 = 0;

                     for(int limit$macro$31 = numColBlocks; index$macro$29 < limit$macro$31; ++index$macro$29) {
                        int oBegin = index$macro$29 * this.blockSizeCol();
                        int oEnd = scala.math.package..MODULE$.min(oBegin + this.blockSizeCol(), b.cols());
                        int O = oEnd - oBegin;
                        int index$macro$24 = 0;

                        for(int limit$macro$26 = O; index$macro$24 < limit$macro$26; ++index$macro$24) {
                           int index$macro$19 = 0;

                           for(int limit$macro$21 = N; index$macro$19 < limit$macro$21; ++index$macro$19) {
                              ((o, n) -> bBuf[o * N + n] = b.apply$mcI$sp(n + nBegin, o + oBegin)).apply$mcVII$sp(index$macro$24, index$macro$19);
                           }
                        }

                        this.multBlock(M, N, O, aTrans, bBuf, res, mBegin, oBegin);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_M_Int())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(DenseMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.impl_OpMulMatrix_DM_M_eq_DM_Int())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(DenseMatrix.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Long_$eq(new UFunc.UImpl2() {
         private final int blockSizeRow = 2000;
         private final int blockSizeInner = 2000;
         private final int blockSizeCol = 2000;

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

         private int blockSizeRow() {
            return this.blockSizeRow;
         }

         private int blockSizeInner() {
            return this.blockSizeInner;
         }

         private int blockSizeCol() {
            return this.blockSizeCol;
         }

         private void multBlock(final int M, final int N, final int K, final long[] aTrans, final long[] b, final DenseMatrix res, final int resRowOff, final int resColOff) {
            long[] rd = res.data$mcJ$sp();
            int rOff = res.offset() + resRowOff + resColOff * res.majorStride();
            int index$macro$12 = 0;

            for(int limit$macro$14 = M; index$macro$12 < limit$macro$14; ++index$macro$12) {
               int index$macro$7 = 0;

               for(int limit$macro$9 = K; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  ((i, k) -> {
                     long sum = 0L;
                     int index$macro$2 = 0;

                     for(int limit$macro$4 = N; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        sum += aTrans[i * N + index$macro$2] * b[k * N + index$macro$2];
                     }

                     int var12 = rOff + i + k * res.majorStride();
                     rd[var12] += sum;
                  }).apply$mcVII$sp(index$macro$12, index$macro$7);
               }
            }

         }

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix res = DenseMatrix$.MODULE$.zeros$mJc$sp(a.rows(), b.cols(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] aTrans = new long[scala.math.package..MODULE$.min(this.blockSizeRow() * this.blockSizeInner(), a.size())];
               long[] bBuf = new long[scala.math.package..MODULE$.min(this.blockSizeInner() * this.blockSizeCol(), b.size())];
               int numRowBlocks = (a.rows() + this.blockSizeRow() - 1) / this.blockSizeRow();
               int numInnerBlocks = (a.cols() + this.blockSizeCol() - 1) / this.blockSizeCol();
               int numColBlocks = (b.cols() + this.blockSizeInner() - 1) / this.blockSizeInner();
               int index$macro$39 = 0;

               for(int limit$macro$41 = numRowBlocks; index$macro$39 < limit$macro$41; ++index$macro$39) {
                  int mBegin = index$macro$39 * this.blockSizeRow();
                  int mEnd = scala.math.package..MODULE$.min(mBegin + this.blockSizeRow(), a.rows());
                  int M = mEnd - mBegin;
                  int index$macro$34 = 0;

                  for(int limit$macro$36 = numInnerBlocks; index$macro$34 < limit$macro$36; ++index$macro$34) {
                     int nBegin = index$macro$34 * this.blockSizeInner();
                     int nEnd = scala.math.package..MODULE$.min(nBegin + this.blockSizeInner(), a.cols());
                     int N = nEnd - nBegin;
                     if (a.isTranspose()) {
                        int index$macro$4 = 0;

                        for(int limit$macro$6 = M; index$macro$4 < limit$macro$6; ++index$macro$4) {
                           System.arraycopy(a.data$mcJ$sp(), a.offset() + (index$macro$4 + mBegin) * a.majorStride() + nBegin, aTrans, index$macro$4 * N, N);
                        }
                     } else {
                        int index$macro$14 = 0;

                        for(int limit$macro$16 = N; index$macro$14 < limit$macro$16; ++index$macro$14) {
                           int index$macro$9 = 0;

                           for(int limit$macro$11 = M; index$macro$9 < limit$macro$11; ++index$macro$9) {
                              ((n, m) -> aTrans[m * N + n] = a.apply$mcJ$sp(m + mBegin, n + nBegin)).apply$mcVII$sp(index$macro$14, index$macro$9);
                           }
                        }
                     }

                     int index$macro$29 = 0;

                     for(int limit$macro$31 = numColBlocks; index$macro$29 < limit$macro$31; ++index$macro$29) {
                        int oBegin = index$macro$29 * this.blockSizeCol();
                        int oEnd = scala.math.package..MODULE$.min(oBegin + this.blockSizeCol(), b.cols());
                        int O = oEnd - oBegin;
                        int index$macro$24 = 0;

                        for(int limit$macro$26 = O; index$macro$24 < limit$macro$26; ++index$macro$24) {
                           int index$macro$19 = 0;

                           for(int limit$macro$21 = N; index$macro$19 < limit$macro$21; ++index$macro$19) {
                              ((o, n) -> bBuf[o * N + n] = b.apply$mcJ$sp(n + nBegin, o + oBegin)).apply$mcVII$sp(index$macro$24, index$macro$19);
                           }
                        }

                        this.multBlock(M, N, O, aTrans, bBuf, res, mBegin, oBegin);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.op_M_M_Long())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(DenseMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultOps.this.impl_OpMulMatrix_DM_M_eq_DM_Long())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(DenseMatrix.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
