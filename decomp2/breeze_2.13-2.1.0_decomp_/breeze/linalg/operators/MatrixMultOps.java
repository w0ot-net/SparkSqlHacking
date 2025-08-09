package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import breeze.linalg.Matrix;
import breeze.linalg.Matrix$;
import breeze.linalg.Vector;
import breeze.linalg.Vector$;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.util.concurrent.ConcurrentHashMap;
import scala.Predef.;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eda\u0002\t\u0012!\u0003\r\t\u0001\u0007\u0005\u0006M\u0001!\ta\n\u0005\bW\u0001\u0011\r\u0011b\u0001-\u0011\u001di\u0004A1A\u0005\u0004yBq!\u0012\u0001C\u0002\u0013\ra\tC\u0004N\u0001\t\u0007I1\u0001(\t\u000fU\u0003!\u0019!C\u0002-\"9\u0001\r\u0001b\u0001\n\u0007\t\u0007\"\u00026\u0001\t\u0007Y\u0007\"CA\u0017\u0001\t\u0007I1AA\u0018\u0011%\t\u0019\u0004\u0001b\u0001\n\u0007\t)\u0004C\u0005\u0002:\u0001\u0011\r\u0011b\u0001\u0002<!I\u0011q\b\u0001C\u0002\u0013\r\u0011\u0011\t\u0005\n\u0003\u000b\u0002!\u0019!C\u0002\u0003\u000fB\u0011\"a\u0013\u0001\u0005\u0004%\u0019!!\u0014\t\u000f\u0005E\u0003\u0001b\u0001\u0002T\tiQ*\u0019;sSblU\u000f\u001c;PaNT!AE\n\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u000b\u0016\u0003\u0019a\u0017N\\1mO*\ta#\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0011\u0001\u0011dH\u0012\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g!\t\u0001\u0013%D\u0001\u0012\u0013\t\u0011\u0013CA\tNCR\u0014\u0018\u000e_#ya\u0006tG-\u001a3PaN\u0004\"\u0001\t\u0013\n\u0005\u0015\n\"\u0001G'biJL\u00070\u0012=qC:$W\rZ(qg2{w\u000f\u0015:j_\u00061A%\u001b8ji\u0012\"\u0012\u0001\u000b\t\u00035%J!AK\u000e\u0003\tUs\u0017\u000e^\u0001\u000b_B|Vj\u0018,`\u0013:$X#A\u0017\u0011\r\u0001r\u0003g\u000e\u001e8\u0013\ty\u0013C\u0001\bCS:\f'/\u001f*fO&\u001cHO]=\u0011\u0007E\u0012D'D\u0001\u0014\u0013\t\u00194C\u0001\u0004NCR\u0014\u0018\u000e\u001f\t\u00035UJ!AN\u000e\u0003\u0007%sG\u000fE\u00022qQJ!!O\n\u0003\rY+7\r^8s\u001d\t\u00013(\u0003\u0002=#\u0005Yq\n]'vY6\u000bGO]5y\u0003-y\u0007oX'`-~cuN\\4\u0016\u0003}\u0002b\u0001\t\u0018A\tj\"\u0005cA\u00193\u0003B\u0011!DQ\u0005\u0003\u0007n\u0011A\u0001T8oOB\u0019\u0011\u0007O!\u0002\u0019=\u0004x,T0W?\u001acw.\u0019;\u0016\u0003\u001d\u0003b\u0001\t\u0018I\u0019jb\u0005cA\u00193\u0013B\u0011!DS\u0005\u0003\u0017n\u0011QA\u00127pCR\u00042!\r\u001dJ\u00035y\u0007oX'`-~#u.\u001e2mKV\tq\n\u0005\u0004!]A#&\b\u0016\t\u0004cI\n\u0006C\u0001\u000eS\u0013\t\u00196D\u0001\u0004E_V\u0014G.\u001a\t\u0004ca\n\u0016!D8q?6{fk\u0018\"jO&sG/F\u0001X!\u0019\u0001c\u0006W0;?B\u0019\u0011GM-\u0011\u0005ikV\"A.\u000b\u0005q[\u0012\u0001B7bi\"L!AX.\u0003\r\tKw-\u00138u!\r\t\u0004(W\u0001\u000f_B|Vj\u0018,`\u0007>l\u0007\u000f\\3y+\u0005\u0011\u0007C\u0002\u0011/G&T\u0014\u000eE\u00022e\u0011\u0004\"!Z4\u000e\u0003\u0019T!\u0001X\u000b\n\u0005!4'aB\"p[BdW\r\u001f\t\u0004ca\"\u0017aD8q?6{fkX*f[&\u0014\u0018N\\4\u0016\u00051<HcB7\u0002\u0004\u00055\u0011Q\u0004\t\bu9$\u0018\u0011AA\u0001\u0013\ty\u0007OA\u0003J[Bd''\u0003\u0002re\n)QKR;oG*\u00111/F\u0001\bO\u0016tWM]5d!\r\t$'\u001e\t\u0003m^d\u0001\u0001B\u0003y\u0011\t\u0007\u0011PA\u0001U#\tQX\u0010\u0005\u0002\u001bw&\u0011Ap\u0007\u0002\b\u001d>$\b.\u001b8h!\tQb0\u0003\u0002\u00007\t\u0019\u0011I\\=\u0011\u0007EBT\u000fC\u0005\u0002\u0006!\t\t\u0011q\u0001\u0002\b\u0005YQM^5eK:\u001cW\rJ\u001d9!\u0011)\u0017\u0011B;\n\u0007\u0005-aM\u0001\u0005TK6L'/\u001b8h\u0011%\ty\u0001CA\u0001\u0002\b\t\t\"A\u0006fm&$WM\\2fIeJ\u0004#BA\n\u00033)XBAA\u000b\u0015\r\t9\"F\u0001\bgR|'/Y4f\u0013\u0011\tY\"!\u0006\u0003\ti+'o\u001c\u0005\n\u0003?A\u0011\u0011!a\u0002\u0003C\tA\"\u001a<jI\u0016t7-\u001a\u00132aA\u0002R!a\t\u0002*Ul!!!\n\u000b\u0007\u0005\u001d2$A\u0004sK\u001adWm\u0019;\n\t\u0005-\u0012Q\u0005\u0002\t\u00072\f7o\u001d+bO\u0006Qq\u000e]0N?6{\u0016J\u001c;\u0016\u0005\u0005E\u0002C\u0002\u0011/aAR\u0004'A\u0006pa~ku,T0M_:<WCAA\u001c!\u0019\u0001c\u0006\u0011!;\u0001\u0006aq\u000e]0N?6{f\t\\8biV\u0011\u0011Q\b\t\u0007A9B\u0005J\u000f%\u0002\u001b=\u0004x,T0N?\u0012{WO\u00197f+\t\t\u0019\u0005\u0005\u0004!]A\u0003&\bU\u0001\u000e_B|VjX'`\u0005&<\u0017J\u001c;\u0016\u0005\u0005%\u0003C\u0002\u0011/1bS\u0004,\u0001\bpa~ku,T0D_6\u0004H.\u001a=\u0016\u0005\u0005=\u0003C\u0002\u0011/G\u000eT4-A\bpa~ku,T0TK6L'/\u001b8h+\u0011\t)&!\u0018\u0015\u0011\u0005]\u0013qLA3\u0003W\u0002\u0002B\u000f8\u0002Z\u0005e\u0013\u0011\f\t\u0005cI\nY\u0006E\u0002w\u0003;\"Q\u0001_\bC\u0002eD\u0011\"!\u0019\u0010\u0003\u0003\u0005\u001d!a\u0019\u0002\u0019\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007M\u0019\u0011\u000b\u0015\fI!a\u0017\t\u0013\u0005\u001dt\"!AA\u0004\u0005%\u0014\u0001D3wS\u0012,gnY3%cA\u0012\u0004CBA\n\u00033\tY\u0006C\u0005\u0002n=\t\t\u0011q\u0001\u0002p\u0005aQM^5eK:\u001cW\rJ\u00191gA1\u00111EA\u0015\u00037\u0002"
)
public interface MatrixMultOps extends MatrixExpandedOps, MatrixExpandedOpsLowPrio {
   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Int_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Long_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Float_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Double_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_BigInt_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Complex_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Int_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Long_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Float_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Double_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_BigInt_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Complex_$eq(final BinaryRegistry x$1);

   BinaryRegistry op_M_V_Int();

   BinaryRegistry op_M_V_Long();

   BinaryRegistry op_M_V_Float();

   BinaryRegistry op_M_V_Double();

   BinaryRegistry op_M_V_BigInt();

   BinaryRegistry op_M_V_Complex();

   // $FF: synthetic method
   static UFunc.UImpl2 op_M_V_Semiring$(final MatrixMultOps $this, final Semiring evidence$98, final Zero evidence$99, final ClassTag evidence$100) {
      return $this.op_M_V_Semiring(evidence$98, evidence$99, evidence$100);
   }

   default UFunc.UImpl2 op_M_V_Semiring(final Semiring evidence$98, final Zero evidence$99, final ClassTag evidence$100) {
      return new UFunc.UImpl2(evidence$98, evidence$100, evidence$99) {
         private final Semiring evidence$98$1;
         private final ClassTag evidence$100$1;
         private final Zero evidence$99$1;

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

         public Vector apply(final Matrix a, final Vector b) {
            Semiring ring = (Semiring).MODULE$.implicitly(this.evidence$98$1);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), this.evidence$100$1, this.evidence$99$1);

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     Object v = a.apply(r, c);
                     res.update(BoxesRunTime.boxToInteger(r), ring.$plus(res.apply(BoxesRunTime.boxToInteger(r)), ring.$times(v, b.apply(BoxesRunTime.boxToInteger(c)))));
                  }
               }

               return res;
            }
         }

         public {
            this.evidence$98$1 = evidence$98$1;
            this.evidence$100$1 = evidence$100$1;
            this.evidence$99$1 = evidence$99$1;
         }
      };
   }

   BinaryRegistry op_M_M_Int();

   BinaryRegistry op_M_M_Long();

   BinaryRegistry op_M_M_Float();

   BinaryRegistry op_M_M_Double();

   BinaryRegistry op_M_M_BigInt();

   BinaryRegistry op_M_M_Complex();

   // $FF: synthetic method
   static UFunc.UImpl2 op_M_M_Semiring$(final MatrixMultOps $this, final Semiring evidence$101, final Zero evidence$102, final ClassTag evidence$103) {
      return $this.op_M_M_Semiring(evidence$101, evidence$102, evidence$103);
   }

   default UFunc.UImpl2 op_M_M_Semiring(final Semiring evidence$101, final Zero evidence$102, final ClassTag evidence$103) {
      return new UFunc.UImpl2(evidence$101, evidence$103, evidence$102) {
         private final Semiring evidence$101$1;
         private final ClassTag evidence$103$1;
         private final Zero evidence$102$1;

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

         public Matrix apply(final Matrix a, final Matrix b) {
            Semiring ring = (Semiring).MODULE$.implicitly(this.evidence$101$1);
            Matrix res = Matrix$.MODULE$.zeros(a.rows(), b.cols(), this.evidence$103$1, this.evidence$102$1);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     Object v = a.apply(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update(r, j, ring.$plus(res.apply(r, j), ring.$times(v, b.apply(c, j))));
                     }
                  }
               }

               return res;
            }
         }

         public {
            this.evidence$101$1 = evidence$101$1;
            this.evidence$103$1 = evidence$103$1;
            this.evidence$102$1 = evidence$102$1;
         }
      };
   }

   static void $init$(final MatrixMultOps $this) {
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Int_$eq(new BinaryRegistry() {
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

         public Vector bindingMissing(final Matrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     int v = a.apply$mcI$sp(r, c);
                     res.update$mcII$sp(r, res.apply$mcII$sp(r) + v * b.apply$mcII$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Long_$eq(new BinaryRegistry() {
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

         public Vector bindingMissing(final Matrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     long v = a.apply$mcJ$sp(r, c);
                     res.update$mcIJ$sp(r, res.apply$mcIJ$sp(r) + v * b.apply$mcIJ$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Float_$eq(new BinaryRegistry() {
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

         public Vector bindingMissing(final Matrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     float v = a.apply$mcF$sp(r, c);
                     res.update$mcIF$sp(r, res.apply$mcIF$sp(r) + v * b.apply$mcIF$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Double_$eq(new BinaryRegistry() {
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

         public Vector bindingMissing(final Matrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     double v = a.apply$mcD$sp(r, c);
                     res.update$mcID$sp(r, res.apply$mcID$sp(r) + v * b.apply$mcID$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_BigInt_$eq(new BinaryRegistry() {
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

         public Vector bindingMissing(final Matrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class), Zero$.MODULE$.BigIntZero());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     BigInt v = (BigInt)a.apply(r, c);
                     res.update(BoxesRunTime.boxToInteger(r), ((BigInt)res.apply(BoxesRunTime.boxToInteger(r))).$plus(v.$times((BigInt)b.apply(BoxesRunTime.boxToInteger(c)))));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Complex_$eq(new BinaryRegistry() {
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

         public Vector bindingMissing(final Matrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector res = Vector$.MODULE$.zeros(a.rows(), scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     Complex v = (Complex)a.apply(r, c);
                     res.update(BoxesRunTime.boxToInteger(r), ((Complex)res.apply(BoxesRunTime.boxToInteger(r))).$plus(v.$times((Complex)b.apply(BoxesRunTime.boxToInteger(c)))));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Int_$eq(new BinaryRegistry() {
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

         public Matrix bindingMissing(final Matrix a, final Matrix b) {
            Matrix res = Matrix$.MODULE$.zeros$mIc$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     int v = a.apply$mcI$sp(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update$mcI$sp(r, j, res.apply$mcI$sp(r, j) + v * b.apply$mcI$sp(c, j));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Long_$eq(new BinaryRegistry() {
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

         public Matrix bindingMissing(final Matrix a, final Matrix b) {
            Matrix res = Matrix$.MODULE$.zeros$mJc$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     long v = a.apply$mcJ$sp(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update$mcJ$sp(r, j, res.apply$mcJ$sp(r, j) + v * b.apply$mcJ$sp(c, j));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Float_$eq(new BinaryRegistry() {
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

         public Matrix bindingMissing(final Matrix a, final Matrix b) {
            Matrix res = Matrix$.MODULE$.zeros$mFc$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     float v = a.apply$mcF$sp(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update$mcF$sp(r, j, res.apply$mcF$sp(r, j) + v * b.apply$mcF$sp(c, j));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Double_$eq(new BinaryRegistry() {
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

         public Matrix bindingMissing(final Matrix a, final Matrix b) {
            Matrix res = Matrix$.MODULE$.zeros$mDc$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     double v = a.apply$mcD$sp(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update$mcD$sp(r, j, res.apply$mcD$sp(r, j) + v * b.apply$mcD$sp(c, j));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_BigInt_$eq(new BinaryRegistry() {
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

         public Matrix bindingMissing(final Matrix a, final Matrix b) {
            Matrix res = Matrix$.MODULE$.zeros(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class), Zero$.MODULE$.BigIntZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     BigInt v = (BigInt)a.apply(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update(r, j, ((BigInt)res.apply(r, j)).$plus(v.$times((BigInt)b.apply(c, j))));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Complex_$eq(new BinaryRegistry() {
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

         public Matrix bindingMissing(final Matrix a, final Matrix b) {
            Matrix res = Matrix$.MODULE$.zeros(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int c = 0; c < a.cols(); ++c) {
                  for(int r = 0; r < a.rows(); ++r) {
                     Complex v = (Complex)a.apply(r, c);

                     for(int j = 0; j < b.cols(); ++j) {
                        res.update(r, j, ((Complex)res.apply(r, j)).$plus(v.$times((Complex)b.apply(c, j))));
                     }
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      });
   }
}
