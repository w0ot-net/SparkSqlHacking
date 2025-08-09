package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Matrix;
import breeze.linalg.support.CanCopy;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005caB\u0004\t!\u0003\r\ta\u0004\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u0001!\u0019\u0001\t\u0005\u0006\u007f\u0001!\u0019\u0001\u0011\u0005\u0006G\u0002!\u0019\u0001\u001a\u0005\u0006m\u0002!\u0019a\u001e\u0005\b\u0003/\u0001A1AA\r\u0005Ai\u0015\r\u001e:jq\u001e+g.\u001a:jG>\u00038O\u0003\u0002\n\u0015\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003\u00171\ta\u0001\\5oC2<'\"A\u0007\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0011q\u0003G\u0007\u0002\u0011%\u0011\u0011\u0004\u0003\u0002\u000e)\u0016t7o\u001c:M_^\u0004&/[8\u0002\r\u0011Jg.\u001b;%)\u0005a\u0002CA\t\u001e\u0013\tq\"C\u0001\u0003V]&$\u0018!D2b]\u000e{\u0007/_'biJL\u00070\u0006\u0002\"]Q\u0011!e\u000e\t\u0004G\u0019BS\"\u0001\u0013\u000b\u0005\u0015R\u0011aB:vaB|'\u000f^\u0005\u0003O\u0011\u0012qaQ1o\u0007>\u0004\u0018\u0010E\u0002*U1j\u0011AC\u0005\u0003W)\u0011a!T1ue&D\bCA\u0017/\u0019\u0001!Qa\f\u0002C\u0002A\u0012\u0011AV\t\u0003cQ\u0002\"!\u0005\u001a\n\u0005M\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#UJ!A\u000e\n\u0003\u0007\u0005s\u0017\u0010C\u00049\u0005\u0005\u0005\t9A\u001d\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002;{1j\u0011a\u000f\u0006\u0003yI\tqA]3gY\u0016\u001cG/\u0003\u0002?w\tA1\t\\1tgR\u000bw-A\u000bn?6|v\n]!eI~+\u0006\u000fZ1uK~\u001bV-\\5\u0016\u0005\u0005sE\u0003\u0002\"Q1n\u0003Ba\u0011$M\u0019:\u0011q\u0003R\u0005\u0003\u000b\"\tQa\u00149BI\u0012L!a\u0012%\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\u001a\n\u0005%S%!B+Gk:\u001c'BA&\r\u0003\u001d9WM\\3sS\u000e\u00042!\u000b\u0016N!\tic\nB\u0003P\u0007\t\u0007\u0001GA\u0001U\u0011\u001d\t6!!AA\u0004I\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u0019f+T\u0007\u0002)*\u0011Q\u000bD\u0001\u0005[\u0006$\b.\u0003\u0002X)\nA1+Z7je&tw\rC\u0004Z\u0007\u0005\u0005\t9\u0001.\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002;{5Cq\u0001X\u0002\u0002\u0002\u0003\u000fQ,\u0001\u0006fm&$WM\\2fIQ\u00022AX1N\u001b\u0005y&B\u00011\r\u0003\u001d\u0019Ho\u001c:bO\u0016L!AY0\u0003\ti+'o\\\u0001\u0016[~kwl\u00149Nk2|V\u000b\u001d3bi\u0016|6+Z7j+\t)G\u000e\u0006\u0003g[B\u001c\b\u0003B4GU*t!a\u00065\n\u0005%D\u0011aC(q\u001bVd7kY1mCJ\u00042!\u000b\u0016l!\tiC\u000eB\u0003P\t\t\u0007\u0001\u0007C\u0004o\t\u0005\u0005\t9A8\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0002T-.Dq!\u001d\u0003\u0002\u0002\u0003\u000f!/\u0001\u0006fm&$WM\\2fIY\u00022AO\u001fl\u0011\u001d!H!!AA\u0004U\f!\"\u001a<jI\u0016t7-\u001a\u00138!\rq\u0016m[\u0001\u0016[~kwl\u00149Tk\n|V\u000b\u001d3bi\u0016|&+\u001b8h+\tAx\u0010F\u0004z\u0003\u0003\tY!!\u0005\u0011\ti4U0 \b\u0003/mL!\u0001 \u0005\u0002\u000b=\u00038+\u001e2\u0011\u0007%Rc\u0010\u0005\u0002.\u007f\u0012)q*\u0002b\u0001a!I\u00111A\u0003\u0002\u0002\u0003\u000f\u0011QA\u0001\u000bKZLG-\u001a8dK\u0012B\u0004\u0003B*\u0002\byL1!!\u0003U\u0005\u0011\u0011\u0016N\\4\t\u0013\u00055Q!!AA\u0004\u0005=\u0011AC3wS\u0012,gnY3%sA\u0019!(\u0010@\t\u0013\u0005MQ!!AA\u0004\u0005U\u0011aC3wS\u0012,gnY3%cA\u00022AX1\u007f\u0003Uiw,\\0Pa\u0012KgoX+qI\u0006$Xm\u0018*j]\u001e,B!a\u0007\u0002*QA\u0011QDA\u0016\u0003k\tY\u0004E\u0004\u0002 \u0019\u000b)#!\n\u000f\u0007]\t\t#C\u0002\u0002$!\tQa\u00149ESZ\u0004B!\u000b\u0016\u0002(A\u0019Q&!\u000b\u0005\u000b=3!\u0019\u0001\u0019\t\u0013\u00055b!!AA\u0004\u0005=\u0012aC3wS\u0012,gnY3%cE\u0002RaUA\u0019\u0003OI1!a\rU\u0005\u00151\u0015.\u001a7e\u0011%\t9DBA\u0001\u0002\b\tI$A\u0006fm&$WM\\2fIE\u0012\u0004\u0003\u0002\u001e>\u0003OA\u0011\"!\u0010\u0007\u0003\u0003\u0005\u001d!a\u0010\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\r\t\u0005=\u0006\f9\u0003"
)
public interface MatrixGenericOps extends TensorLowPrio {
   // $FF: synthetic method
   static CanCopy canCopyMatrix$(final MatrixGenericOps $this, final ClassTag evidence$1) {
      return $this.canCopyMatrix(evidence$1);
   }

   default CanCopy canCopyMatrix(final ClassTag evidence$1) {
      return new CanCopy() {
         public Matrix apply(final Matrix v1) {
            return v1.copy();
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 m_m_OpAdd_Update_Semi$(final MatrixGenericOps $this, final Semiring evidence$2, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.m_m_OpAdd_Update_Semi(evidence$2, evidence$3, evidence$4);
   }

   default UFunc.InPlaceImpl2 m_m_OpAdd_Update_Semi(final Semiring evidence$2, final ClassTag evidence$3, final Zero evidence$4) {
      return new UFunc.InPlaceImpl2(evidence$2) {
         private final Semiring evidence$2$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Matrix a, final Matrix b) {
            Semiring ring = (Semiring).MODULE$.implicitly(this.evidence$2$1);

            for(int c = 0; c < a.cols(); ++c) {
               for(int r = 0; r < a.rows(); ++r) {
                  a.update(r, c, ring.$plus(a.apply(r, c), b.apply(r, c)));
               }
            }

         }

         public {
            this.evidence$2$1 = evidence$2$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 m_m_OpMul_Update_Semi$(final MatrixGenericOps $this, final Semiring evidence$5, final ClassTag evidence$6, final Zero evidence$7) {
      return $this.m_m_OpMul_Update_Semi(evidence$5, evidence$6, evidence$7);
   }

   default UFunc.InPlaceImpl2 m_m_OpMul_Update_Semi(final Semiring evidence$5, final ClassTag evidence$6, final Zero evidence$7) {
      return new UFunc.InPlaceImpl2(evidence$5) {
         private final Semiring evidence$5$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Matrix a, final Matrix b) {
            Semiring ring = (Semiring).MODULE$.implicitly(this.evidence$5$1);

            for(int c = 0; c < a.cols(); ++c) {
               for(int r = 0; r < a.rows(); ++r) {
                  a.update(r, c, ring.$times(a.apply(r, c), b.apply(r, c)));
               }
            }

         }

         public {
            this.evidence$5$1 = evidence$5$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 m_m_OpSub_Update_Ring$(final MatrixGenericOps $this, final Ring evidence$8, final ClassTag evidence$9, final Zero evidence$10) {
      return $this.m_m_OpSub_Update_Ring(evidence$8, evidence$9, evidence$10);
   }

   default UFunc.InPlaceImpl2 m_m_OpSub_Update_Ring(final Ring evidence$8, final ClassTag evidence$9, final Zero evidence$10) {
      return new UFunc.InPlaceImpl2(evidence$8) {
         private final Ring evidence$8$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Matrix a, final Matrix b) {
            Ring ring = (Ring).MODULE$.implicitly(this.evidence$8$1);

            for(int c = 0; c < a.cols(); ++c) {
               for(int r = 0; r < a.rows(); ++r) {
                  a.update(r, c, ring.$minus(a.apply(r, c), b.apply(r, c)));
               }
            }

         }

         public {
            this.evidence$8$1 = evidence$8$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 m_m_OpDiv_Update_Ring$(final MatrixGenericOps $this, final Field evidence$11, final ClassTag evidence$12, final Zero evidence$13) {
      return $this.m_m_OpDiv_Update_Ring(evidence$11, evidence$12, evidence$13);
   }

   default UFunc.InPlaceImpl2 m_m_OpDiv_Update_Ring(final Field evidence$11, final ClassTag evidence$12, final Zero evidence$13) {
      return new UFunc.InPlaceImpl2(evidence$11) {
         private final Field evidence$11$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Matrix a, final Matrix b) {
            Field ring = (Field).MODULE$.implicitly(this.evidence$11$1);

            for(int c = 0; c < a.cols(); ++c) {
               for(int r = 0; r < a.rows(); ++r) {
                  a.update(r, c, ring.$div(a.apply(r, c), b.apply(r, c)));
               }
            }

         }

         public {
            this.evidence$11$1 = evidence$11$1;
         }
      };
   }

   static void $init$(final MatrixGenericOps $this) {
   }
}
