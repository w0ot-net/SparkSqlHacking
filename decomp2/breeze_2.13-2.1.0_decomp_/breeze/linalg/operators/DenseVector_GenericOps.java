package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.TensorLike;
import breeze.linalg.Transpose;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rba\u0002\n\u0014!\u0003\r\tA\u0007\u0005\u0006K\u0001!\tA\n\u0005\u0006U\u0001!\u0019a\u000b\u0005\u0006\r\u0002!\u0019a\u0012\u0005\u0006\u001b\u0002!\u0019A\u0014\u0005\u0006E\u0002!\u0019a\u0019\u0005\u0006i\u0002!\u0019!\u001e\u0005\b\u0003S\u0001A1AA\u0016\u0011\u001d\t\u0019\u0005\u0001C\u0002\u0003\u000bBq!a\u0018\u0001\t\u0007\t\t\u0007C\u0004\u0002x\u0001!\u0019!!\u001f\t\u000f\u0005M\u0005\u0001b\u0001\u0002\u0016\"9\u0011\u0011\u0017\u0001\u0005\u0004\u0005M\u0006bBAb\u0001\u0011\r\u0011Q\u0019\u0005\b\u0003+\u0004A1AAl\u0011\u001d\t9\u000f\u0001C\u0002\u0003SDq!!?\u0001\t\u0007\tY\u0010C\u0004\u0003\f\u0001!\u0019A!\u0004\u0003-\u0011+gn]3WK\u000e$xN]0HK:,'/[2PaNT!\u0001F\u000b\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\f\u0018\u0003\u0019a\u0017N\\1mO*\t\u0001$\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u00011$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\t\u001aS\"A\n\n\u0005\u0011\u001a\"!\u0003,fGR|'o\u00149t\u0003\u0019!\u0013N\\5uIQ\tq\u0005\u0005\u0002\u001dQ%\u0011\u0011&\b\u0002\u0005+:LG/A\u0010j[Bdwl\u00149TKR|\u0016J\u001c)mC\u000e,w\f\u0012,`-~;UM\\3sS\u000e,\"\u0001L\u001f\u0016\u00035\u0002BAL\u00198w9\u0011!eL\u0005\u0003aM\tQa\u00149TKRL!AM\u001a\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\u001a\n\u0005Q*$!B+Gk:\u001c'B\u0001\u001c\u0018\u0003\u001d9WM\\3sS\u000e\u00042\u0001O\u001d<\u001b\u0005)\u0012B\u0001\u001e\u0016\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005qjD\u0002\u0001\u0003\u0006}\t\u0011\ra\u0010\u0002\u0002-F\u0011\u0001i\u0011\t\u00039\u0005K!AQ\u000f\u0003\u000f9{G\u000f[5oOB\u0011A\u0004R\u0005\u0003\u000bv\u00111!\u00118z\u0003aIW\u000e\u001d7`\u001fB\u001cV\r^0J]Bc\u0017mY3`\tZ{FIV\u000b\u0003\u00112+\u0012!\u0013\t\u0005]ER%\nE\u00029s-\u0003\"\u0001\u0010'\u0005\u000by\u001a!\u0019A \u0002K%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~#ekX*`\tZ{v)\u001a8fe&\u001cWCA(Y)\t\u0001&\fE\u0003R)Z;fK\u0004\u00029%&\u00111+F\u0001\tg\u000e\fG.Z!eI&\u0011Qk\r\u0002\r\u0013:\u0004F.Y2f\u00136\u0004Hn\r\t\u0004qe:\u0006C\u0001\u001fY\t\u0015IFA1\u0001@\u0005\u0005!\u0006bB.\u0005\u0003\u0003\u0005\u001d\u0001X\u0001\fKZLG-\u001a8dK\u0012\nD\u0007E\u0002^A^k\u0011A\u0018\u0006\u0003?^\tA!\\1uQ&\u0011\u0011M\u0018\u0002\t'\u0016l\u0017N]5oO\u00069\u0012.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?\u00123vLV\u000b\u0004I\"TGCA3m!\u0011q\u0013GZ5\u0011\u0007aJt\r\u0005\u0002=Q\u0012)\u0011,\u0002b\u0001\u007fA\u0011AH\u001b\u0003\u0006W\u0016\u0011\ra\u0010\u0002\u0004-\u0016\u001c\u0007\"B7\u0006\u0001\bq\u0017AA3w!\u0011ar.[9\n\u0005Al\"\u0001\u0005\u0013mKN\u001cHeY8m_:$C.Z:t!\rA$oZ\u0005\u0003gV\u0011aAV3di>\u0014\u0018!G5na2|v\n]0M\u0011N{FI\u0016;`KF|&kX2bgR,\u0012B^A\u0001\u0003+\t9!!\u0007\u0015\u0007]\fi\u0002E\u0005yy~\f)!a\u0003\u0002\u00189\u0011\u0011P_\u0007\u0002k%\u001110N\u0001\u0006+\u001a+hnY\u0005\u0003{z\u0014a!V%na2\u0014$BA>6!\ra\u0014\u0011\u0001\u0003\u0007\u0003\u00071!\u0019A \u0003\u0007Q\u000bw\rE\u0002=\u0003\u000f!a!!\u0003\u0007\u0005\u0004y$a\u0001'I'B)\u0001(!\u0004\u0002\u0012%\u0019\u0011qB\u000b\u0003\u0013Q\u0013\u0018M\\:q_N,\u0007\u0003\u0002\u001d:\u0003'\u00012\u0001PA\u000b\t\u0015qdA1\u0001@!\ra\u0014\u0011\u0004\u0003\u0007\u000371!\u0019A \u0003\u0003ICq!a\b\u0007\u0001\b\t\t#\u0001\u0002paBI\u0001\u0010`@\u0002\u0006\u0005\r\u0012q\u0003\t\u0006q\u0005\u0015\u00121C\u0005\u0004\u0003O)\"a\u0003#f]N,W*\u0019;sSb\f\u0001%[7qY~{\u0005/\u00113e?&s\u0007\u000b\\1dK~#ek\u0018#W?\u001e+g.\u001a:jGV!\u0011QFA\u001e)\u0011\ty#!\u0010\u0011\u000f\u0005E\u0012'a\u000e\u000289\u0019!%a\r\n\u0007\u0005U2#A\u0003Pa\u0006#G\r\u0005\u00039s\u0005e\u0002c\u0001\u001f\u0002<\u0011)\u0011l\u0002b\u0001\u007f!9\u0011qH\u0004A\u0004\u0005\u0005\u0013!\u00024jK2$\u0007\u0003B/a\u0003s\t\u0001%[7qY~{\u0005oU;c?&s\u0007\u000b\\1dK~#ek\u0018#W?\u001e+g.\u001a:jGV!\u0011qIA+)\u0011\tI%a\u0016\u0011\u000f\u0005-\u0013'!\u0015\u0002R9\u0019!%!\u0014\n\u0007\u0005=3#A\u0003PaN+(\r\u0005\u00039s\u0005M\u0003c\u0001\u001f\u0002V\u0011)\u0011\f\u0003b\u0001\u007f!9\u0011q\b\u0005A\u0004\u0005e\u0003#B/\u0002\\\u0005M\u0013bAA/=\n!!+\u001b8h\u0003\u0019JW\u000e\u001d7`\u001fBlU\u000f\\*dC2\f'oX%o!2\f7-Z0E-~#ekX$f]\u0016\u0014\u0018nY\u000b\u0005\u0003G\n\t\b\u0006\u0003\u0002f\u0005M\u0004cBA4c\u00055\u0014Q\u000e\b\u0004E\u0005%\u0014bAA6'\u0005Yq\n]'vYN\u001b\u0017\r\\1s!\u0011A\u0014(a\u001c\u0011\u0007q\n\t\bB\u0003Z\u0013\t\u0007q\bC\u0004\u0002@%\u0001\u001d!!\u001e\u0011\tu\u0003\u0017qN\u0001!S6\u0004HnX(q\t&4x,\u00138QY\u0006\u001cWm\u0018#W?\u00123vlR3oKJL7-\u0006\u0003\u0002|\u0005%E\u0003BA?\u0003\u0017\u0003r!a 2\u0003\u000b\u000b)ID\u0002#\u0003\u0003K1!a!\u0014\u0003\u0015y\u0005\u000fR5w!\u0011A\u0014(a\"\u0011\u0007q\nI\tB\u0003Z\u0015\t\u0007q\bC\u0004\u0002@)\u0001\u001d!!$\u0011\u000bu\u000by)a\"\n\u0007\u0005EeLA\u0003GS\u0016dG-\u0001\u0011j[Bdwl\u00149Q_^|\u0016J\u001c)mC\u000e,w\f\u0012,`\tZ{v)\u001a8fe&\u001cW\u0003BAL\u0003K#B!!'\u0002(B9\u00111T\u0019\u0002\"\u0006\u0005fb\u0001\u0012\u0002\u001e&\u0019\u0011qT\n\u0002\u000b=\u0003\bk\\<\u0011\taJ\u00141\u0015\t\u0004y\u0005\u0015F!B-\f\u0005\u0004y\u0004bBAU\u0017\u0001\u000f\u00111V\u0001\u0004a><\bCCAN\u0003[\u000b\u0019+a)\u0002$&\u0019\u0011qV\u001a\u0003\u000b%k\u0007\u000f\u001c\u001a\u0002?%l\u0007\u000f\\0Pa\u0006#GmX%o!2\f7-Z0E-~\u001bvlR3oKJL7-\u0006\u0003\u00026\u0006uF\u0003BA\\\u0003\u007f\u0003r!!\r2\u0003s\u000bY\f\u0005\u00039s\u0005m\u0006c\u0001\u001f\u0002>\u0012)\u0011\f\u0004b\u0001\u007f!9\u0011q\b\u0007A\u0004\u0005\u0005\u0007\u0003B/a\u0003w\u000bq$[7qY~{\u0005oU;c?&s\u0007\u000b\\1dK~#ekX*`\u000f\u0016tWM]5d+\u0011\t9-a4\u0015\t\u0005%\u0017\u0011\u001b\t\b\u0003\u0017\n\u00141ZAg!\u0011A\u0014(!4\u0011\u0007q\ny\rB\u0003Z\u001b\t\u0007q\bC\u0004\u0002@5\u0001\u001d!a5\u0011\u000bu\u000bY&!4\u0002K%l\u0007\u000f\\0Pa6+HnU2bY\u0006\u0014x,\u00138QY\u0006\u001cWm\u0018#W?N{v)\u001a8fe&\u001cW\u0003BAm\u0003C$B!a7\u0002dB9\u0011qM\u0019\u0002^\u0006}\u0007\u0003\u0002\u001d:\u0003?\u00042\u0001PAq\t\u0015IfB1\u0001@\u0011\u001d\tyD\u0004a\u0002\u0003K\u0004B!\u00181\u0002`\u0006y\u0012.\u001c9m?>\u0003H)\u001b<`\u0013:\u0004F.Y2f?\u00123vlU0HK:,'/[2\u0016\t\u0005-\u00181\u001f\u000b\u0005\u0003[\f)\u0010E\u0004\u0002\u0000E\ny/!=\u0011\taJ\u0014\u0011\u001f\t\u0004y\u0005MH!B-\u0010\u0005\u0004y\u0004bBA \u001f\u0001\u000f\u0011q\u001f\t\u0006;\u0006=\u0015\u0011_\u0001 S6\u0004HnX(q!><x,\u00138QY\u0006\u001cWm\u0018#W?N{v)\u001a8fe&\u001cW\u0003BA\u007f\u0005\u000b!B!a@\u0003\bA9\u00111T\u0019\u0003\u0002\t\r\u0001\u0003\u0002\u001d:\u0005\u0007\u00012\u0001\u0010B\u0003\t\u0015I\u0006C1\u0001@\u0011\u001d\tI\u000b\u0005a\u0002\u0005\u0013\u0001\"\"a'\u0002.\n\r!1\u0001B\u0002\u0003\tJW\u000e\u001d7`\u001fBlU\u000f\\%o]\u0016\u0014x\f\u0012,`\tZ{V-]0T?\u001e+g.\u001a:jGV!!q\u0002B\u000f)\u0011\u0011\tBa\b\u0011\u0015\tM\u0011Q\u0016B\r\u00053\u0011YBD\u0002#\u0005+I1Aa\u0006\u0014\u0003)y\u0005/T;m\u0013:tWM\u001d\t\u0005qe\u0012Y\u0002E\u0002=\u0005;!Q!W\tC\u0002}Bq!a\u0010\u0012\u0001\b\u0011\t\u0003\u0005\u0003^A\nm\u0001"
)
public interface DenseVector_GenericOps extends VectorOps {
   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V_Generic$(final DenseVector_GenericOps $this) {
      return $this.impl_OpSet_InPlace_DV_V_Generic();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V_Generic() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final Object b) {
            Object ad = a.data();
            if (a.stride() == 1) {
               ArrayUtil$.MODULE$.fill(ad, a.offset(), a.length(), b);
            } else {
               int aoff = a.offset();
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  .MODULE$.array_update(ad, aoff, b);
                  aoff += a.stride();
               }
            }

         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_DV$(final DenseVector_GenericOps $this) {
      return $this.impl_OpSet_InPlace_DV_DV();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_DV() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (!a.overlaps(b)) {
                  if (a.stride() == b.stride() && a.stride() == 1) {
                     System.arraycopy(b.data(), b.offset(), a.data(), a.offset(), a.length());
                     BoxedUnit var12 = BoxedUnit.UNIT;
                  } else {
                     Object ad = a.data();
                     Object bd = b.data();
                     int aoff = a.offset();
                     int boff = b.offset();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        .MODULE$.array_update(ad, aoff, .MODULE$.array_apply(bd, boff));
                        aoff += a.stride();
                        boff += b.stride();
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy();
               a = a;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Generic$(final DenseVector_GenericOps $this, final Semiring evidence$14) {
      return $this.impl_scaleAdd_InPlace_DV_S_DV_Generic(evidence$14);
   }

   default UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Generic(final Semiring evidence$14) {
      return new UFunc.InPlaceImpl3(evidence$14) {
         private final Semiring ring;

         private Semiring ring() {
            return this.ring;
         }

         public void apply(final DenseVector a, final Object s, final DenseVector b) {
            while(a.overlaps(b)) {
               b = b.copy();
               s = s;
               a = a;
            }

            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Object ad = a.data();
               Object bd = b.data();
               int aoff = a.offset();
               int boff = b.offset();

               for(int i = 0; i < a.length(); ++i) {
                  .MODULE$.array_update(ad, aoff, this.ring().$plus(.MODULE$.array_apply(ad, aoff), this.ring().$times(s, .MODULE$.array_apply(bd, boff))));
                  aoff += a.stride();
                  boff += b.stride();
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         public {
            this.ring = (Semiring)scala.Predef..MODULE$.implicitly(evidence$14$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V$(final DenseVector_GenericOps $this, final scala..less.colon.less ev) {
      return $this.impl_OpSet_InPlace_DV_V(ev);
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V(final scala..less.colon.less ev) {
      return new UFunc.InPlaceImpl2(ev) {
         private final scala..less.colon.less ev$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final Object b) {
            Object ad = a.data();
            int aoff = a.offset();

            for(int i = 0; i < a.length(); ++i) {
               .MODULE$.array_update(ad, aoff, ((TensorLike)this.ev$1.apply(b)).apply(BoxesRunTime.boxToInteger(i)));
               aoff += a.stride();
            }

         }

         public {
            this.ev$1 = ev$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_LHS_DVt_eq_R_cast$(final DenseVector_GenericOps $this, final UFunc.UImpl2 op) {
      return $this.impl_Op_LHS_DVt_eq_R_cast(op);
   }

   default UFunc.UImpl2 impl_Op_LHS_DVt_eq_R_cast(final UFunc.UImpl2 op) {
      return new UFunc.UImpl2(op) {
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

         public Object apply(final Object v, final Transpose v2) {
            DenseVector dv = (DenseVector)v2.inner();
            Object x$1 = dv.data();
            int x$2 = dv.offset();
            int x$3 = dv.length();
            int x$4 = 1;
            int x$5 = dv.stride();
            boolean x$6 = DenseMatrix$.MODULE$.create$default$6();
            DenseMatrix dm = DenseMatrix$.MODULE$.create(1, x$3, x$1, x$2, x$5, x$6);
            return this.op$3.apply(v, dm);
         }

         public {
            this.op$3 = op$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Generic$(final DenseVector_GenericOps $this, final Semiring field) {
      return $this.impl_OpAdd_InPlace_DV_DV_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$8;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final DenseVector v2) {
            while(v.overlaps(v2)) {
               v2 = v2.copy();
               v = v;
            }

            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$8.$plus(v.apply(index$macro$2), v2.apply(index$macro$2)));
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public {
            this.field$8 = field$8;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Generic$(final DenseVector_GenericOps $this, final Ring field) {
      return $this.impl_OpSub_InPlace_DV_DV_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Generic(final Ring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Ring field$9;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final DenseVector v2) {
            while(v.overlaps(v2)) {
               v2 = v2.copy();
               v = v;
            }

            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$9.$minus(v.apply(index$macro$2), v2.apply(index$macro$2)));
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public {
            this.field$9 = field$9;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_DV_Generic$(final DenseVector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulScalar_InPlace_DV_DV_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_DV_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$10;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final DenseVector v2) {
            while(v.overlaps(v2)) {
               v2 = v2.copy();
               v = v;
            }

            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$10.$times(v.apply(index$macro$2), v2.apply(index$macro$2)));
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public {
            this.field$10 = field$10;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_DV_Generic$(final DenseVector_GenericOps $this, final Field field) {
      return $this.impl_OpDiv_InPlace_DV_DV_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_DV_Generic(final Field field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$11;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final DenseVector v2) {
            while(v.overlaps(v2)) {
               v2 = v2.copy();
               v = v;
            }

            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$11.$div(v.apply(index$macro$2), v2.apply(index$macro$2)));
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public {
            this.field$11 = field$11;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_DV_Generic$(final DenseVector_GenericOps $this, final UFunc.UImpl2 pow) {
      return $this.impl_OpPow_InPlace_DV_DV_Generic(pow);
   }

   default UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_DV_Generic(final UFunc.UImpl2 pow) {
      return new UFunc.InPlaceImpl2(pow) {
         private final UFunc.UImpl2 pow$3;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final DenseVector v2) {
            while(v.overlaps(v2)) {
               v2 = v2.copy();
               v = v;
            }

            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.pow$3.apply(v.apply(index$macro$2), v2.apply(index$macro$2)));
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public {
            this.pow$3 = pow$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_S_Generic$(final DenseVector_GenericOps $this, final Semiring field) {
      return $this.impl_OpAdd_InPlace_DV_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_S_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$12;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$12.$plus(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.field$12 = field$12;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_S_Generic$(final DenseVector_GenericOps $this, final Ring field) {
      return $this.impl_OpSub_InPlace_DV_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_S_Generic(final Ring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Ring field$13;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$13.$minus(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.field$13 = field$13;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_S_Generic$(final DenseVector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulScalar_InPlace_DV_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_S_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$14;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$14.$times(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.field$14 = field$14;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_S_Generic$(final DenseVector_GenericOps $this, final Field field) {
      return $this.impl_OpDiv_InPlace_DV_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_S_Generic(final Field field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$15;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$15.$div(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.field$15 = field$15;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_S_Generic$(final DenseVector_GenericOps $this, final UFunc.UImpl2 pow) {
      return $this.impl_OpPow_InPlace_DV_S_Generic(pow);
   }

   default UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_S_Generic(final UFunc.UImpl2 pow) {
      return new UFunc.InPlaceImpl2(pow) {
         private final UFunc.UImpl2 pow$4;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.pow$4.apply(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.pow$4 = pow$4;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Generic$(final DenseVector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulInner_DV_DV_eq_S_Generic(field);
   }

   default UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Generic(final Semiring field) {
      return new UFunc.UImpl2(field) {
         private final Semiring field$16;

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

         public Object apply(final DenseVector v, final DenseVector v2) {
            Object acc = this.field$16.zero();
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               acc = this.field$16.$plus(acc, this.field$16.$times(v.apply(index$macro$2), v2.apply(index$macro$2)));
            }

            return acc;
         }

         public {
            this.field$16 = field$16;
         }
      };
   }

   static void $init$(final DenseVector_GenericOps $this) {
   }
}
