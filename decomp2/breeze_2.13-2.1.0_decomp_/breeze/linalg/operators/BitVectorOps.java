package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.BitVector;
import breeze.linalg.BitVector$;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.SparseVector;
import breeze.linalg.TensorLike;
import breeze.linalg.Vector;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import scala.;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tMdaB\u0012%!\u0003\r\ta\u000b\u0005\u0006m\u0001!\taN\u0004\u0006w\u0001A\u0019\u0001\u0010\u0004\u0006}\u0001A\ta\u0010\u0005\u0006#\u000e!\tA\u0015\u0005\u0006'\u000e!\t\u0005V\u0004\u0006/\u0002A\u0019\u0001\u0017\u0004\u00063\u0002A\tA\u0017\u0005\u0006#\u001e!\ta\u0018\u0005\u0006'\u001e!\t\u0005\u0019\u0005\bE\u0002\u0011\r\u0011b\u0001d\u0011\u001dQ\u0007A1A\u0005\u0004-Dq\u0001\u001d\u0001C\u0002\u0013\r\u0011\u000fC\u0004w\u0001\t\u0007I1A<\t\u000fq\u0004!\u0019!C\u0002{\"I\u00111\u0001\u0001C\u0002\u0013\r\u0011Q\u0001\u0005\n\u0003\u0013\u0001!\u0019!C\u0002\u0003\u0017A\u0011\"a\u0004\u0001\u0005\u0004%\u0019!!\u0005\t\u0013\u0005m\u0001A1A\u0005\u0004\u0005u\u0001\"CA\u0014\u0001\t\u0007I1AA\u0015\u0011\u001d\t\u0019\u0004\u0001C\u0002\u0003kAq!!\u001d\u0001\t\u0007\t\u0019\bC\u0004\u0002\n\u0002!\u0019!a#\t\u000f\u0005\u0005\u0006\u0001b\u0001\u0002$\"9\u0011\u0011\u0018\u0001\u0005\u0004\u0005m\u0006\"CAq\u0001\t\u0007I1AAr\u0011%\ti\u000f\u0001b\u0001\n\u0007\ty\u000fC\u0005\u0003\u000e\u0001\u0011\r\u0011b\u0001\u0003\u0010!I!Q\u0003\u0001C\u0002\u0013\r!q\u0003\u0005\n\u0005;\u0001!\u0019!C\u0002\u0005?A\u0011B!\n\u0001\u0005\u0004%\u0019Aa\n\t\u0013\tE\u0002A1A\u0005\u0004\tM\u0002\"\u0003B\u001d\u0001\t\u0007I1\u0001B\u001e\u0011%\u0011Y\u0005\u0001b\u0001\n\u0007\u0011i\u0005C\u0004\u0003Z\u0001!\u0019Aa\u0017\u0003\u0019\tKGOV3di>\u0014x\n]:\u000b\u0005\u00152\u0013!C8qKJ\fGo\u001c:t\u0015\t9\u0003&\u0001\u0004mS:\fGn\u001a\u0006\u0002S\u00051!M]3fu\u0016\u001c\u0001aE\u0002\u0001YI\u0002\"!\f\u0019\u000e\u00039R\u0011aL\u0001\u0006g\u000e\fG.Y\u0005\u0003c9\u0012a!\u00118z%\u00164\u0007CA\u001a5\u001b\u0005!\u0013BA\u001b%\u0005)9UM\\3sS\u000e|\u0005o]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003a\u0002\"!L\u001d\n\u0005ir#\u0001B+oSR\fa#[7qY~\u000bg._0C-~+\u0017o\u0018\"p_2,\u0017M\u001c\t\u0003{\ri\u0011\u0001\u0001\u0002\u0017S6\u0004HnX1os~\u0013ekX3r?\n{w\u000e\\3b]N\u00191\u0001\f!\u0011\t\u0005+5J\u0014\b\u0003\u0005\u000ek\u0011AJ\u0005\u0003\t\u001a\n1!\u00198z\u0013\t1uI\u0001\u0003J[Bd\u0017B\u0001%J\u0005\u0015)f)\u001e8d\u0015\tQ\u0005&A\u0004hK:,'/[2\u0011\u0005\tc\u0015BA''\u0005%\u0011\u0015\u000e\u001e,fGR|'\u000f\u0005\u0002.\u001f&\u0011\u0001K\f\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q\tA(A\u0003baBd\u0017\u0010\u0006\u0002O+\")a+\u0002a\u0001\u0017\u0006\ta/\u0001\fj[Bdw,\u00197m?\n3v,Z9`\u0005>|G.Z1o!\titA\u0001\fj[Bdw,\u00197m?\n3v,Z9`\u0005>|G.Z1o'\r9Af\u0017\t\u00059\u0016[eJ\u0004\u0002C;&\u0011aLJ\u0001\u0004C2dG#\u0001-\u0015\u00059\u000b\u0007\"\u0002,\n\u0001\u0004Y\u0015aG5na2|v\n]0J]Bc\u0017mY3`\u0005Z{&IV0Pa\u0006sG-F\u0001e!\u0011)\u0007nS&\u000f\u0005M2\u0017BA4%\u0003\u0015y\u0005/\u00118e\u0013\tIwI\u0001\u0007J]Bc\u0017mY3J[Bd''\u0001\u000ej[Bdwl\u00149`\u0013:\u0004F.Y2f?\n3vL\u0011,`\u001fB|%/F\u0001m!\u0011i\u0007nS&\u000f\u0005Mr\u0017BA8%\u0003\u0011y\u0005o\u0014:\u00027%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|&IV0C-~{\u0005\u000fW8s+\u0005\u0011\b\u0003B:i\u0017.s!a\r;\n\u0005U$\u0013!B(q1>\u0014\u0018\u0001G5na2|v\n]*fi~Ke\u000e\u00157bG\u0016|&IV0C-V\t\u0001\u0010\u0005\u0003zQ.[eBA\u001a{\u0013\tYH%A\u0003PaN+G/A\rj[Bdwl\u00149`\u0005Z{&IV0fc~\u0013ekX(q\u0003:$W#\u0001@\u0011\u000b\u0015|8jS&\n\u0007\u0005\u0005qIA\u0003J[Bd''\u0001\rj[Bdwl\u00149`\u0005Z{&IV0fc~\u0013ekX(q\u001fJ,\"!a\u0002\u0011\u000b5|8jS&\u00023%l\u0007\u000f\\0Pa~\u0013ek\u0018\"W?\u0016\fxL\u0011,`\u001fBDvN]\u000b\u0003\u0003\u001b\u0001Ra]@L\u0017.\u000b\u0001B\u0019<`\u001fBtu\u000e^\u000b\u0003\u0003'\u0001R!!\u0006F\u0017.s1aMA\f\u0013\r\tI\u0002J\u0001\u0006\u001fBtu\u000e^\u0001\u000bEZ|&M^0Pa:+WCAA\u0010!\u0019\t\tc`&L\u0017:\u00191'a\t\n\u0007\u0005\u0015B%\u0001\u0003Pa:+\u0017A\u00032w?\n4xl\u00149FcV\u0011\u00111\u0006\t\u0007\u0003[y8jS&\u000f\u0007M\ny#C\u0002\u00022\u0011\nAa\u00149Fc\u0006A\u0011\r\u001f9z?&sG/\u0006\u0003\u00028\u0005%C\u0003BA\u001d\u0003C\u0002\u0012\"a\u000f\u0002B\u0005\u0015\u00131L&\u000f\u0007\t\u000bi$C\u0002\u0002@\u0019\n\u0001b]2bY\u0016\fE\rZ\u0005\u0004\u0003\u0007:%\u0001D%o!2\f7-Z%na2\u001c\u0004\u0003BA$\u0003\u0013b\u0001\u0001B\u0004\u0002LQ\u0011\r!!\u0014\u0003\u0007Y+7-\u0005\u0003\u0002P\u0005U\u0003cA\u0017\u0002R%\u0019\u00111\u000b\u0018\u0003\u000f9{G\u000f[5oOB\u0019Q&a\u0016\n\u0007\u0005ecFA\u0002B]f\u00042!LA/\u0013\r\tyF\f\u0002\u0004\u0013:$\bbBA2)\u0001\u000f\u0011QM\u0001\u0003KZ\u0004r!LA4\u0003\u000b\nY'C\u0002\u0002j9\u0012\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\u000b\t\u000bi'a\u0017\n\u0007\u0005=dE\u0001\u0004WK\u000e$xN]\u0001\fCb\u0004\u0018p\u0018#pk\ndW-\u0006\u0003\u0002v\u0005mD\u0003BA<\u0003\u0007\u0003\u0012\"a\u000f\u0002B\u0005e\u0014QP&\u0011\t\u0005\u001d\u00131\u0010\u0003\b\u0003\u0017*\"\u0019AA'!\ri\u0013qP\u0005\u0004\u0003\u0003s#A\u0002#pk\ndW\rC\u0004\u0002dU\u0001\u001d!!\"\u0011\u000f5\n9'!\u001f\u0002\bB)!)!\u001c\u0002~\u0005Q\u0011\r\u001f9z?\u001acw.\u0019;\u0016\t\u00055\u00151\u0013\u000b\u0005\u0003\u001f\u000bY\nE\u0005\u0002<\u0005\u0005\u0013\u0011SAK\u0017B!\u0011qIAJ\t\u001d\tYE\u0006b\u0001\u0003\u001b\u00022!LAL\u0013\r\tIJ\f\u0002\u0006\r2|\u0017\r\u001e\u0005\b\u0003G2\u00029AAO!\u001di\u0013qMAI\u0003?\u0003RAQA7\u0003+\u000b\u0011\"\u0019=qs~cuN\\4\u0016\t\u0005\u0015\u00161\u0016\u000b\u0005\u0003O\u000b\u0019\fE\u0005\u0002<\u0005\u0005\u0013\u0011VAW\u0017B!\u0011qIAV\t\u001d\tYe\u0006b\u0001\u0003\u001b\u00022!LAX\u0013\r\t\tL\f\u0002\u0005\u0019>tw\rC\u0004\u0002d]\u0001\u001d!!.\u0011\u000f5\n9'!+\u00028B)!)!\u001c\u0002.\u00069\u0011\r\u001f9z\u000f\u0016tWCBA_\u0003\u000f\f\u0019\r\u0006\u0004\u0002@\u0006-\u0017\u0011\u001b\t\n\u0003w\t\t%!1\u0002F.\u0003B!a\u0012\u0002D\u00129\u00111\n\rC\u0002\u00055\u0003\u0003BA$\u0003\u000f$q!!3\u0019\u0005\u0004\tiEA\u0001W\u0011\u001d\t\u0019\u0007\u0007a\u0002\u0003\u001b\u0004r!LA4\u0003\u0003\fy\rE\u0003C\u0003[\n)\rC\u0004\u0002Tb\u0001\u001d!!6\u0002\tM,W.\u001b\t\u0007\u0003/\fi.!2\u000e\u0005\u0005e'bAAnQ\u0005!Q.\u0019;i\u0013\u0011\ty.!7\u0003\u0011M+W.\u001b:j]\u001e\fAbY1o\t>$xL\u0011,`\u0005Z+\"!!:\u0011\r\u0005\u001dxpS&O\u001d\r\u0019\u0014\u0011^\u0005\u0004\u0003W$\u0013AC(q\u001bVd\u0017J\u001c8fe\u0006a2-\u00198E_R|&IV0EK:\u001cXMV3di>\u0014x\fR8vE2,WCAAy!!\t\u0019p`&\u0003\b\u0005ud\u0002BA{\u0003StA!a>\u0003\u00069!\u0011\u0011 B\u0002\u001d\u0011\tYP!\u0001\u000e\u0005\u0005u(bAA\u0000U\u00051AH]8pizJ\u0011!K\u0005\u0003O!J!!\n\u0014\u0011\u000b\t\u0013I!! \n\u0007\t-aEA\u0006EK:\u001cXMV3di>\u0014\u0018aG2b]\u0012{Go\u0018\"W?\u0012+gn]3WK\u000e$xN]0GY>\fG/\u0006\u0002\u0003\u0012AA\u00111_@L\u0005'\t)\nE\u0003C\u0005\u0013\t)*A\rdC:$u\u000e^0C-~#UM\\:f-\u0016\u001cGo\u001c:`\u0013:$XC\u0001B\r!!\t\u0019p`&\u0003\u001c\u0005m\u0003#\u0002\"\u0003\n\u0005m\u0013AG2b]\u0012{Go\u0018\"W?\u0012+gn]3WK\u000e$xN]0M_:<WC\u0001B\u0011!!\t\u0019p`&\u0003$\u00055\u0006#\u0002\"\u0003\n\u00055\u0016\u0001E2b]\u0012{Go\u0018\"W?N3v,\u00138u+\t\u0011I\u0003\u0005\u0005\u0002t~\\%1FA.!\u0015\u0011%QFA.\u0013\r\u0011yC\n\u0002\r'B\f'o]3WK\u000e$xN]\u0001\u0012G\u0006tGi\u001c;`\u0005Z{6KV0M_:<WC\u0001B\u001b!!\t\u0019p`&\u00038\u00055\u0006#\u0002\"\u0003.\u00055\u0016aE2b]\u0012{Go\u0018\"W?N3vLQ5h\u0013:$XC\u0001B\u001f!!\t\u0019p`&\u0003@\t\u0005\u0003#\u0002\"\u0003.\t\u0005\u0003\u0003\u0002B\"\u0005\u000fj!A!\u0012\u000b\u0007\u0005mg&\u0003\u0003\u0003J\t\u0015#A\u0002\"jO&sG/\u0001\u000bdC:$u\u000e^0C-~\u001bfkX\"p[BdW\r_\u000b\u0003\u0005\u001f\u0002\u0002\"a=\u0000\u0017\nE#1\u000b\t\u0006\u0005\n5\"1\u000b\t\u0005\u0003/\u0014)&\u0003\u0003\u0003X\u0005e'aB\"p[BdW\r_\u0001\u0010G\u0006tGi\u001c;`\u001fRDWM]0C-V1!Q\fB5\u0005G\"BAa\u0018\u0003nAA\u0011q]@\u0003b-\u00139\u0007\u0005\u0003\u0002H\t\rDa\u0002B3E\t\u0007\u0011Q\n\u0002\u0006\u001fRDWM\u001d\t\u0005\u0003\u000f\u0012I\u0007B\u0004\u0003l\t\u0012\r!!\u0014\u0003\u0003QCqAa\u001c#\u0001\b\u0011\t(\u0001\u0002paBA\u0011q]@L\u0005C\u00129\u0007"
)
public interface BitVectorOps extends GenericOps {
   impl_any_BV_eq_Boolean$ impl_any_BV_eq_Boolean();

   impl_all_BV_eq_Boolean$ impl_all_BV_eq_Boolean();

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpAnd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpOr_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpXor_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_OpSet_InPlace_BV_BV_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpAnd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpOr_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpXor_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$bv_OpNot_$eq(final UFunc.UImpl x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$bv_bv_OpNe_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$bv_bv_OpEq_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_BV_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_BigInt_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Complex_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpAnd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpOr();

   UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpXor();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_BV_BV();

   UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpAnd();

   UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpOr();

   UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpXor();

   UFunc.UImpl bv_OpNot();

   UFunc.UImpl2 bv_bv_OpNe();

   UFunc.UImpl2 bv_bv_OpEq();

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 axpy_Int$(final BitVectorOps $this, final .less.colon.less ev) {
      return $this.axpy_Int(ev);
   }

   default UFunc.InPlaceImpl3 axpy_Int(final .less.colon.less ev) {
      return new UFunc.InPlaceImpl3(ev) {
         private final .less.colon.less ev$1;

         public void apply(final Object a, final int s, final BitVector b) {
            scala.Predef..MODULE$.require(b.lengthsMatch((Vector)this.ev$1.apply(a)), () -> "Vectors must be the Same length!");
            BitSet bd = b.data();

            for(int i = bd.nextSetBit(0); i >= 0; i = bd.nextSetBit(i + 1)) {
               Vector var6 = (Vector)this.ev$1.apply(a);
               var6.update$mcII$sp(i, var6.apply$mcII$sp(i) + s);
            }

         }

         public {
            this.ev$1 = ev$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 axpy_Double$(final BitVectorOps $this, final .less.colon.less ev) {
      return $this.axpy_Double(ev);
   }

   default UFunc.InPlaceImpl3 axpy_Double(final .less.colon.less ev) {
      return new UFunc.InPlaceImpl3(ev) {
         private final .less.colon.less ev$2;

         public void apply(final Object a, final double s, final BitVector b) {
            scala.Predef..MODULE$.require(b.lengthsMatch((Vector)this.ev$2.apply(a)), () -> "Vectors must be the Same length!");
            BitSet bd = b.data();

            for(int i = bd.nextSetBit(0); i >= 0; i = bd.nextSetBit(i + 1)) {
               Vector var7 = (Vector)this.ev$2.apply(a);
               var7.update$mcID$sp(i, var7.apply$mcID$sp(i) + s);
            }

         }

         public {
            this.ev$2 = ev$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 axpy_Float$(final BitVectorOps $this, final .less.colon.less ev) {
      return $this.axpy_Float(ev);
   }

   default UFunc.InPlaceImpl3 axpy_Float(final .less.colon.less ev) {
      return new UFunc.InPlaceImpl3(ev) {
         private final .less.colon.less ev$3;

         public void apply(final Object a, final float s, final BitVector b) {
            scala.Predef..MODULE$.require(b.lengthsMatch((Vector)this.ev$3.apply(a)), () -> "Vectors must be the Same length!");
            BitSet bd = b.data();

            for(int i = bd.nextSetBit(0); i >= 0; i = bd.nextSetBit(i + 1)) {
               Vector var6 = (Vector)this.ev$3.apply(a);
               var6.update$mcIF$sp(i, var6.apply$mcIF$sp(i) + s);
            }

         }

         public {
            this.ev$3 = ev$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 axpy_Long$(final BitVectorOps $this, final .less.colon.less ev) {
      return $this.axpy_Long(ev);
   }

   default UFunc.InPlaceImpl3 axpy_Long(final .less.colon.less ev) {
      return new UFunc.InPlaceImpl3(ev) {
         private final .less.colon.less ev$4;

         public void apply(final Object a, final long s, final BitVector b) {
            scala.Predef..MODULE$.require(b.lengthsMatch((Vector)this.ev$4.apply(a)), () -> "Vectors must be the Same length!");
            BitSet bd = b.data();

            for(int i = bd.nextSetBit(0); i >= 0; i = bd.nextSetBit(i + 1)) {
               Vector var7 = (Vector)this.ev$4.apply(a);
               var7.update$mcIJ$sp(i, var7.apply$mcIJ$sp(i) + s);
            }

         }

         public {
            this.ev$4 = ev$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 axpyGen$(final BitVectorOps $this, final .less.colon.less ev, final Semiring semi) {
      return $this.axpyGen(ev, semi);
   }

   default UFunc.InPlaceImpl3 axpyGen(final .less.colon.less ev, final Semiring semi) {
      return new UFunc.InPlaceImpl3(ev, semi) {
         private final .less.colon.less ev$5;
         private final Semiring semi$1;

         public void apply(final Object a, final Object s, final BitVector b) {
            scala.Predef..MODULE$.require(b.lengthsMatch((Vector)this.ev$5.apply(a)), () -> "Vectors must be the same length!");
            BitSet bd = b.data();

            for(int i = bd.nextSetBit(0); i >= 0; i = bd.nextSetBit(i + 1)) {
               ((TensorLike)this.ev$5.apply(a)).update(BoxesRunTime.boxToInteger(i), this.semi$1.$plus(((TensorLike)this.ev$5.apply(a)).apply(BoxesRunTime.boxToInteger(i)), s));
            }

         }

         public {
            this.ev$5 = ev$5;
            this.semi$1 = semi$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   UFunc.UImpl2 canDot_BV_BV();

   UFunc.UImpl2 canDot_BV_DenseVector_Double();

   UFunc.UImpl2 canDot_BV_DenseVector_Float();

   UFunc.UImpl2 canDot_BV_DenseVector_Int();

   UFunc.UImpl2 canDot_BV_DenseVector_Long();

   UFunc.UImpl2 canDot_BV_SV_Int();

   UFunc.UImpl2 canDot_BV_SV_Long();

   UFunc.UImpl2 canDot_BV_SV_BigInt();

   UFunc.UImpl2 canDot_BV_SV_Complex();

   // $FF: synthetic method
   static UFunc.UImpl2 canDot_Other_BV$(final BitVectorOps $this, final UFunc.UImpl2 op) {
      return $this.canDot_Other_BV(op);
   }

   default UFunc.UImpl2 canDot_Other_BV(final UFunc.UImpl2 op) {
      return new UFunc.UImpl2(op) {
         private final UFunc.UImpl2 op$1;

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

         public Object apply(final Object a, final BitVector b) {
            return this.op$1.apply(b, a);
         }

         public {
            this.op$1 = op$1;
         }
      };
   }

   static void $init$(final BitVectorOps $this) {
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpAnd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               a.data().and(b.data());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpOr_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               a.data().or(b.data());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpXor_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               a.data().xor(b.data());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_OpSet_InPlace_BV_BV_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               a.data().clear();
               a.data().or(b.data());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpAnd_$eq(new UFunc.UImpl2() {
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

         public BitVector apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               BitSet result = (BitSet)a.data().clone();
               result.and(b.data());
               return new BitVector(result, scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(a.length()), b.length()), a.enforceLength() && b.enforceLength());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpOr_$eq(new UFunc.UImpl2() {
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

         public BitVector apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               BitSet result = (BitSet)a.data().clone();
               result.or(b.data());
               return new BitVector(result, scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(a.length()), b.length()), a.enforceLength() && b.enforceLength());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpXor_$eq(new UFunc.UImpl2() {
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

         public BitVector apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               BitSet result = (BitSet)a.data().clone();
               result.xor(b.data());
               return new BitVector(result, scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(a.length()), b.length()), a.enforceLength() && b.enforceLength());
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$bv_OpNot_$eq(new UFunc.UImpl() {
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

         public BitVector apply(final BitVector a) {
            BitVector ones = BitVector$.MODULE$.ones(a.length(), a.enforceLength());
            ones.data().andNot(a.data());
            return ones;
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$bv_bv_OpNe_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final BitVectorOps $outer;

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

         public BitVector apply(final BitVector a, final BitVector b) {
            return (BitVector)a.$up$up(b, this.$outer.impl_Op_BV_BV_eq_BV_OpXor());
         }

         public {
            if (BitVectorOps.this == null) {
               throw null;
            } else {
               this.$outer = BitVectorOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$bv_bv_OpEq_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final BitVectorOps $outer;

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

         public BitVector apply(final BitVector a, final BitVector b) {
            if (!a.lengthsMatch(b)) {
               throw new IllegalArgumentException((new StringBuilder(22)).append("Lengths don't match: ").append(a.length()).append(" ").append(b.length()).toString());
            } else {
               return (BitVector)((ImmutableNumericOps)a.$colon$bang$eq(b, this.$outer.bv_bv_OpNe())).unary_$bang(this.$outer.bv_OpNot());
            }
         }

         public {
            if (BitVectorOps.this == null) {
               throw null;
            } else {
               this.$outer = BitVectorOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_BV_$eq(new UFunc.UImpl2() {
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

         public boolean apply(final BitVector a, final BitVector b) {
            scala.Predef..MODULE$.require(a.lengthsMatch(b), () -> "Vectors must be the same length!");
            return a.data().intersects(b.data());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final BitVector a, final DenseVector b) {
            BitSet ad = a.data();
            int boff = b.offset();
            double[] bd = b.data$mcD$sp();
            int bstride = b.stride();
            double result = (double)0.0F;

            for(int i = ad.nextSetBit(0); i >= 0; i = ad.nextSetBit(i + 1)) {
               result += bd[boff + bstride * i];
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final BitVector a, final DenseVector b) {
            BitSet ad = a.data();
            int boff = b.offset();
            float[] bd = b.data$mcF$sp();
            int bstride = b.stride();
            float result = 0.0F;

            for(int i = ad.nextSetBit(0); i >= 0; i = ad.nextSetBit(i + 1)) {
               result += bd[boff + bstride * i];
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final BitVector a, final DenseVector b) {
            BitSet ad = a.data();
            int boff = b.offset();
            int[] bd = b.data$mcI$sp();
            int bstride = b.stride();
            int result = 0;

            for(int i = ad.nextSetBit(0); i >= 0; i = ad.nextSetBit(i + 1)) {
               result += bd[boff + bstride * i];
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final BitVector a, final DenseVector b) {
            BitSet ad = a.data();
            int boff = b.offset();
            long[] bd = b.data$mcJ$sp();
            int bstride = b.stride();
            long result = 0L;

            for(int i = ad.nextSetBit(0); i >= 0; i = ad.nextSetBit(i + 1)) {
               result += bd[boff + bstride * i];
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final BitVector a, final SparseVector b) {
            scala.Predef..MODULE$.require(a.lengthsMatch(b), () -> "Vectors must be the same length!");
            if (b.activeSize() == 0) {
               return 0;
            } else {
               BitSet ad = a.data();
               int boff = 0;

               int result;
               for(result = 0; boff < b.activeSize(); ++boff) {
                  if (ad.get(b.indexAt(boff))) {
                     result += b.valueAt$mcI$sp(boff);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final BitVector a, final SparseVector b) {
            scala.Predef..MODULE$.require(a.lengthsMatch(b), () -> "Vectors must be the same length!");
            if (b.activeSize() == 0) {
               return 0L;
            } else {
               BitSet ad = a.data();
               int boff = 0;

               long result;
               for(result = 0L; boff < b.activeSize(); ++boff) {
                  if (ad.get(b.indexAt(boff))) {
                     result += b.valueAt$mcJ$sp(boff);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_BigInt_$eq(new UFunc.UImpl2() {
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

         public BigInt apply(final BitVector a, final SparseVector b) {
            scala.Predef..MODULE$.require(a.lengthsMatch(b), () -> "Vectors must be the same length!");
            if (b.activeSize() == 0) {
               return scala.math.BigInt..MODULE$.apply(0);
            } else {
               BitSet ad = a.data();
               int boff = 0;

               BigInt result;
               for(result = scala.math.BigInt..MODULE$.apply(0); boff < b.activeSize(); ++boff) {
                  if (ad.get(b.indexAt(boff))) {
                     result = result.$plus((BigInt)b.valueAt(boff));
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Complex_$eq(new UFunc.UImpl2() {
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

         public Complex apply(final BitVector a, final SparseVector b) {
            scala.Predef..MODULE$.require(a.lengthsMatch(b), () -> "Vectors must be the same length!");
            if (b.activeSize() == 0) {
               return Complex$.MODULE$.zero();
            } else {
               BitSet ad = a.data();
               int boff = 0;

               Complex result;
               for(result = Complex$.MODULE$.zero(); boff < b.activeSize(); ++boff) {
                  if (ad.get(b.indexAt(boff))) {
                     result = result.$plus((Complex)b.valueAt(boff));
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public class impl_any_BV_eq_Boolean$ implements UFunc.UImpl {
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

      public boolean apply(final BitVector v) {
         return v.data().cardinality() != 0;
      }
   }

   public class impl_all_BV_eq_Boolean$ implements UFunc.UImpl {
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

      public boolean apply(final BitVector v) {
         return v.data().cardinality() == v.size();
      }
   }
}
