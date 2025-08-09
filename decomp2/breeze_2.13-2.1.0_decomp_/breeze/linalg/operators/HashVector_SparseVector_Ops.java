package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.HashVector;
import breeze.linalg.SparseVector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.VectorBuilder$mcF$sp;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.VectorBuilder$mcJ$sp;
import breeze.math.PowImplicits$;
import breeze.math.Semiring$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eea\u0002\u0010 !\u0003\r\tA\n\u0005\u0006i\u0001!\t!\u000e\u0005\bs\u0001\u0011\r\u0011b\u0001;\u0011\u001dy\u0005A1A\u0005\u0004ACqa\u0016\u0001C\u0002\u0013\r\u0001\fC\u0004`\u0001\t\u0007I1\u00011\t\u000f\u001d\u0004!\u0019!C\u0002Q\"9Q\u000e\u0001b\u0001\n\u0007q\u0007b\u00029\u0001\u0005\u0004%\u0019!\u001d\u0005\bg\u0002\u0011\r\u0011b\u0001u\u0011\u001d1\bA1A\u0005\u0004]Dq\u0001 \u0001C\u0002\u0013\rQ\u0010\u0003\u0005\u0000\u0001\t\u0007I1AA\u0001\u0011%\t)\u0001\u0001b\u0001\n\u0007\t9\u0001C\u0005\u0002\f\u0001\u0011\r\u0011b\u0001\u0002\u000e!I\u0011q\u0003\u0001C\u0002\u0013\r\u0011\u0011\u0004\u0005\n\u0003;\u0001!\u0019!C\u0002\u0003?A\u0011\"a\t\u0001\u0005\u0004%\u0019!!\n\t\u0013\u0005%\u0002A1A\u0005\u0004\u0005-\u0002\"CA\u001b\u0001\t\u0007I1AA\u001c\u0011%\tY\u0004\u0001b\u0001\n\u0007\ti\u0004C\u0005\u0002B\u0001\u0011\r\u0011b\u0001\u0002D!I\u0011q\t\u0001C\u0002\u0013\r\u0011\u0011\n\u0005\n\u0003/\u0002!\u0019!C\u0002\u00033B\u0011\"!\u0018\u0001\u0005\u0004%\u0019!a\u0018\t\u0013\u0005\r\u0004A1A\u0005\u0004\u0005\u0015\u0004\"CA5\u0001\t\u0007I1AA6\u0011%\t9\t\u0001b\u0001\n\u0007\tI\tC\u0005\u0002\u000e\u0002\u0011\r\u0011b\u0001\u0002\u0010\"I\u00111\u0013\u0001C\u0002\u0013\r\u0011Q\u0013\u0002\u001c\u0011\u0006\u001c\bNV3di>\u0014xl\u00159beN,g+Z2u_J|v\n]:\u000b\u0005\u0001\n\u0013!C8qKJ\fGo\u001c:t\u0015\t\u00113%\u0001\u0004mS:\fGn\u001a\u0006\u0002I\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0003\u0001O5\n\u0004C\u0001\u0015,\u001b\u0005I#\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051J#AB!osJ+g\r\u0005\u0002/_5\tq$\u0003\u00021?\t\u0019\u0002*Y:i-\u0016\u001cGo\u001c:FqB\fg\u000eZ(qgB\u0011aFM\u0005\u0003g}\u0011qb\u00159beN,g+Z2u_J|\u0005o]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Y\u0002\"\u0001K\u001c\n\u0005aJ#\u0001B+oSR\f1&[7qY~{\u0005o\u0018%W?N3v,Z9`\u0011Z{F\u000e[:`]&d\u0007o\u001c;f]R|\u0016J\u001c;`\u001fB$\u0015N^\u000b\u0002wA)AhP#M\u000b:\u0011a&P\u0005\u0003}}\tQa\u00149ESZL!\u0001Q!\u0003\u000b%k\u0007\u000f\u001c\u001a\n\u0005\t\u001b%!B+Gk:\u001c'B\u0001#$\u0003\u001d9WM\\3sS\u000e\u00042AR$J\u001b\u0005\t\u0013B\u0001%\"\u0005)A\u0015m\u001d5WK\u000e$xN\u001d\t\u0003Q)K!aS\u0015\u0003\u0007%sG\u000fE\u0002G\u001b&K!AT\u0011\u0003\u0019M\u0003\u0018M]:f-\u0016\u001cGo\u001c:\u0002]%l\u0007\u000f\\0Pa~CekX*W?\u0016\fx\f\u0013,`Y\"\u001cxL\\5ma>$XM\u001c;`\t>,(\r\\3`\u001fB$\u0015N^\u000b\u0002#B)Ah\u0010*W%B\u0019aiR*\u0011\u0005!\"\u0016BA+*\u0005\u0019!u.\u001e2mKB\u0019a)T*\u0002[%l\u0007\u000f\\0Pa~CekX*W?\u0016\fx\f\u0013,`Y\"\u001cxL\\5ma>$XM\u001c;`\r2|\u0017\r^0Pa\u0012Kg/F\u0001Z!\u0015atH\u00170[!\r1ui\u0017\t\u0003QqK!!X\u0015\u0003\u000b\u0019cw.\u0019;\u0011\u0007\u0019k5,\u0001\u0017j[Bdwl\u00149`\u0011Z{6KV0fc~Cek\u00187ig~s\u0017\u000e\u001c9pi\u0016tGo\u0018'p]\u001e|v\n\u001d#jmV\t\u0011\rE\u0003=\u007f\t4'\rE\u0002G\u000f\u000e\u0004\"\u0001\u000b3\n\u0005\u0015L#\u0001\u0002'p]\u001e\u00042AR'd\u0003-JW\u000e\u001d7`\u001fB|\u0006JV0T-~+\u0017o\u0018%W?2D7o\u00188jYB|G/\u001a8u?&sGoX(q'\u0016$X#A5\u0011\u000b)|T\tT#\u000f\u00059Z\u0017B\u00017 \u0003\u0015y\u0005oU3u\u00039JW\u000e\u001d7`\u001fB|\u0006JV0T-~+\u0017o\u0018%W?2D7o\u00188jYB|G/\u001a8u?\u0012{WO\u00197f?>\u00038+\u001a;\u0016\u0003=\u0004RA[ S-J\u000bQ&[7qY~{\u0005o\u0018%W?N3v,Z9`\u0011Z{F\u000e[:`]&d\u0007o\u001c;f]R|f\t\\8bi~{\u0005oU3u+\u0005\u0011\b#\u00026@5zS\u0016\u0001L5na2|v\n]0I-~\u001bfkX3r?\"3v\f\u001c5t?:LG\u000e]8uK:$x\fT8oO~{\u0005oU3u+\u0005)\b#\u00026@E\u001a\u0014\u0017aK5na2|v\n]0I-~\u001bfkX3r?\"3v\f\u001c5t?:LG\u000e]8uK:$x,\u00138u?>\u0003Xj\u001c3\u0016\u0003a\u0004R!_ F\u0019\u0016s!A\f>\n\u0005m|\u0012!B(q\u001b>$\u0017AL5na2|v\n]0I-~\u001bfkX3r?\"3v\f\u001c5t?:LG\u000e]8uK:$x\fR8vE2,wl\u00149N_\u0012,\u0012A \t\u0006s~\u0012fKU\u0001.S6\u0004HnX(q?\"3vl\u0015,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0GY>\fGoX(q\u001b>$WCAA\u0002!\u0015IxH\u00170[\u00031JW\u000e\u001d7`\u001fB|\u0006JV0T-~+\u0017o\u0018%W?2D7o\u00188jYB|G/\u001a8u?2{gnZ0Pa6{G-\u0006\u0002\u0002\nA)\u0011p\u00102gE\u0006Y\u0013.\u001c9m?>\u0003x\f\u0013,`'Z{V-]0I-~c\u0007n]0oS2\u0004x\u000e^3oi~Ke\u000e^0PaB{w/\u0006\u0002\u0002\u0010A1\u0011\u0011C F\u0019\u0016s1ALA\n\u0013\r\t)bH\u0001\u0006\u001fB\u0004vn^\u0001/S6\u0004HnX(q?\"3vl\u0015,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0E_V\u0014G.Z0PaB{w/\u0006\u0002\u0002\u001cA1\u0011\u0011C S-J\u000bQ&[7qY~{\u0005o\u0018%W?N3v,Z9`\u0011Z{F\u000e[:`]&d\u0007o\u001c;f]R|f\t\\8bi~{\u0005\u000fU8x+\t\t\t\u0003\u0005\u0004\u0002\u0012}RfLW\u0001-S6\u0004HnX(q?\"3vl\u0015,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0M_:<wl\u00149Q_^,\"!a\n\u0011\r\u0005EqH\u00194c\u0003\u0001JW\u000e\u001d7`\u001fBlU\u000f\\*dC2\f'o\u0018%W?N3v,Z9`\u0011Z{\u0016J\u001c;\u0016\u0005\u00055\u0002CBA\u0018\u007f\u0015cUID\u0002/\u0003cI1!a\r \u0003-y\u0005/T;m'\u000e\fG.\u0019:\u0002G%l\u0007\u000f\\0Pa6+HnU2bY\u0006\u0014x\f\u0013,`'Z{V-]0I-~#u.\u001e2mKV\u0011\u0011\u0011\b\t\u0007\u0003_y$K\u0016*\u0002E%l\u0007\u000f\\0Pa6+HnU2bY\u0006\u0014x\f\u0013,`'Z{V-]0I-~3En\\1u+\t\ty\u0004\u0005\u0004\u00020}RfLW\u0001\"S6\u0004HnX(q\u001bVd7kY1mCJ|\u0006JV0T-~+\u0017o\u0018%W?2{gnZ\u000b\u0003\u0003\u000b\u0002b!a\f@E\u001a\u0014\u0017!I5na2|6oY1mK\u0006#GmX%o!2\f7-Z0I-~\u001bvl\u0015,`\u0013:$XCAA&!\u001d\ti%a\u0015F\u00132s1ARA(\u0013\r\t\t&I\u0001\tg\u000e\fG.Z!eI&\u0019\u0011QK!\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0002I%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~CekX*`'Z{Fi\\;cY\u0016,\"!a\u0017\u0011\u000f\u00055\u00131\u000b*T-\u0006\u0019\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,w\f\u0013,`'~\u001bfk\u0018$m_\u0006$XCAA1!\u001d\ti%a\u0015[7z\u000b!%[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018%W?N{6KV0M_:<WCAA4!\u001d\ti%a\u0015cG\u001a\fa$[7qY~{\u0005/T;m\u0013:tWM]0I-~\u001bfkX3r?N{\u0016J\u001c;\u0016\u0005\u00055\u0004CBA8\u007f\u0015c\u0015J\u0004\u0003\u0002r\u0005\re\u0002BA:\u0003\u0003sA!!\u001e\u0002\u00009!\u0011qOA?\u001b\t\tIHC\u0002\u0002|\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0013\n\u0005\t\u001a\u0013B\u0001\u0011\"\u0013\r\t)iH\u0001\u000b\u001fBlU\u000f\\%o]\u0016\u0014\u0018aH5na2|v\n]'vY&sg.\u001a:`\u0011Z{6KV0fc~\u001bv\fT8oOV\u0011\u00111\u0012\t\u0007\u0003_z$MZ2\u0002A%l\u0007\u000f\\0Pa6+H.\u00138oKJ|\u0006JV0T-~+\u0017oX*`\r2|\u0017\r^\u000b\u0003\u0003#\u0003b!a\u001c@5z[\u0016!I5na2|v\n]'vY&sg.\u001a:`\u0011Z{6KV0fc~\u001bv\fR8vE2,WCAAL!\u0019\tyg\u0010*W'\u0002"
)
public interface HashVector_SparseVector_Ops extends HashVectorExpandOps, SparseVectorOps {
   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Double_$eq(final UFunc.UImpl2 x$1);

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow();

   UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow();

   UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Int();

   UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Double();

   UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Float();

   UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Long();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Int();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Double();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Float();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Long();

   UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Int();

   UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Long();

   UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Float();

   UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Double();

   static void $init$(final HashVector_SparseVector_Ops $this) {
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$89) -> BoxesRunTime.boxToBoolean($anonfun$apply$177(check$ifrefutable$89))).foreach((x$89) -> {
                        $anonfun$apply$178(b, builder, x$89);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcI$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = a.apply$mcI$sp(index$macro$4) / b.otherApply$mcI$sp(index$macro$4);
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$177(final Tuple2 check$ifrefutable$89) {
            boolean var1;
            if (check$ifrefutable$89 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$178(final SparseVector b$53, final VectorBuilder builder$5, final Tuple2 x$89) {
            if (x$89 != null) {
               int k = x$89._1$mcI$sp();
               int v = x$89._2$mcI$sp();
               int r = v / b$53.otherApply$mcI$sp(k);
               if (r != 0) {
                  builder$5.add$mcI$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$89);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$90) -> BoxesRunTime.boxToBoolean($anonfun$apply$179(check$ifrefutable$90))).foreach((x$90) -> {
                        $anonfun$apply$180(b, builder, x$90);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcD$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = a.apply$mcD$sp(index$macro$4) / b.otherApply$mcD$sp(index$macro$4);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$179(final Tuple2 check$ifrefutable$90) {
            boolean var1;
            if (check$ifrefutable$90 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$180(final SparseVector b$54, final VectorBuilder builder$6, final Tuple2 x$90) {
            if (x$90 != null) {
               int k = x$90._1$mcI$sp();
               double v = x$90._2$mcD$sp();
               double r = v / b$54.otherApply$mcD$sp(k);
               if (r != (double)0.0F) {
                  builder$6.add$mcD$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$90);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$91) -> BoxesRunTime.boxToBoolean($anonfun$apply$181(check$ifrefutable$91))).foreach((x$91) -> {
                        $anonfun$apply$182(b, builder, x$91);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcF$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = a.apply$mcF$sp(index$macro$4) / b.otherApply$mcF$sp(index$macro$4);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$181(final Tuple2 check$ifrefutable$91) {
            boolean var1;
            if (check$ifrefutable$91 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$182(final SparseVector b$55, final VectorBuilder builder$7, final Tuple2 x$91) {
            if (x$91 != null) {
               int k = x$91._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$91._2());
               float r = v / b$55.otherApply$mcF$sp(k);
               if (r != 0.0F) {
                  builder$7.add$mcF$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$91);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$92) -> BoxesRunTime.boxToBoolean($anonfun$apply$183(check$ifrefutable$92))).foreach((x$92) -> {
                        $anonfun$apply$184(b, builder, x$92);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcJ$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = a.apply$mcJ$sp(index$macro$4) / b.otherApply$mcJ$sp(index$macro$4);
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$183(final Tuple2 check$ifrefutable$92) {
            boolean var1;
            if (check$ifrefutable$92 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$184(final SparseVector b$56, final VectorBuilder builder$8, final Tuple2 x$92) {
            if (x$92 != null) {
               int k = x$92._1$mcI$sp();
               long v = x$92._2$mcJ$sp();
               long r = v / b$56.otherApply$mcJ$sp(k);
               if (r != 0L) {
                  builder$8.add$mcJ$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$92);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$93) -> BoxesRunTime.boxToBoolean($anonfun$apply$185(check$ifrefutable$93))).foreach((x$93) -> {
                        $anonfun$apply$186(b, builder, x$93);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcI$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = b.otherApply$mcI$sp(index$macro$4);
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$185(final Tuple2 check$ifrefutable$93) {
            boolean var1;
            if (check$ifrefutable$93 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$186(final SparseVector b$57, final VectorBuilder builder$9, final Tuple2 x$93) {
            if (x$93 != null) {
               int k = x$93._1$mcI$sp();
               int r = b$57.otherApply$mcI$sp(k);
               if (r != 0) {
                  builder$9.add$mcI$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var7 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$93);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$94) -> BoxesRunTime.boxToBoolean($anonfun$apply$187(check$ifrefutable$94))).foreach((x$94) -> {
                        $anonfun$apply$188(b, builder, x$94);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcD$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = b.otherApply$mcD$sp(index$macro$4);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$187(final Tuple2 check$ifrefutable$94) {
            boolean var1;
            if (check$ifrefutable$94 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$188(final SparseVector b$58, final VectorBuilder builder$10, final Tuple2 x$94) {
            if (x$94 != null) {
               int k = x$94._1$mcI$sp();
               double r = b$58.otherApply$mcD$sp(k);
               if (r != (double)0.0F) {
                  builder$10.add$mcD$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$94);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$95) -> BoxesRunTime.boxToBoolean($anonfun$apply$189(check$ifrefutable$95))).foreach((x$95) -> {
                        $anonfun$apply$190(b, builder, x$95);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcF$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = b.otherApply$mcF$sp(index$macro$4);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$189(final Tuple2 check$ifrefutable$95) {
            boolean var1;
            if (check$ifrefutable$95 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$190(final SparseVector b$59, final VectorBuilder builder$11, final Tuple2 x$95) {
            if (x$95 != null) {
               int k = x$95._1$mcI$sp();
               float r = b$59.otherApply$mcF$sp(k);
               if (r != 0.0F) {
                  builder$11.add$mcF$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var7 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$95);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$96) -> BoxesRunTime.boxToBoolean($anonfun$apply$191(check$ifrefutable$96))).foreach((x$96) -> {
                        $anonfun$apply$192(b, builder, x$96);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcJ$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = b.otherApply$mcJ$sp(index$macro$4);
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$191(final Tuple2 check$ifrefutable$96) {
            boolean var1;
            if (check$ifrefutable$96 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$192(final SparseVector b$60, final VectorBuilder builder$12, final Tuple2 x$96) {
            if (x$96 != null) {
               int k = x$96._1$mcI$sp();
               long r = b$60.otherApply$mcJ$sp(k);
               if (r != 0L) {
                  builder$12.add$mcJ$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$96);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$97) -> BoxesRunTime.boxToBoolean($anonfun$apply$193(check$ifrefutable$97))).foreach((x$97) -> {
                        $anonfun$apply$194(b, builder, x$97);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcI$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = a.apply$mcI$sp(index$macro$4) % b.otherApply$mcI$sp(index$macro$4);
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$193(final Tuple2 check$ifrefutable$97) {
            boolean var1;
            if (check$ifrefutable$97 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$194(final SparseVector b$61, final VectorBuilder builder$13, final Tuple2 x$97) {
            if (x$97 != null) {
               int k = x$97._1$mcI$sp();
               int v = x$97._2$mcI$sp();
               int r = v % b$61.otherApply$mcI$sp(k);
               if (r != 0) {
                  builder$13.add$mcI$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$97);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$98) -> BoxesRunTime.boxToBoolean($anonfun$apply$195(check$ifrefutable$98))).foreach((x$98) -> {
                        $anonfun$apply$196(b, builder, x$98);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcD$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = a.apply$mcD$sp(index$macro$4) % b.otherApply$mcD$sp(index$macro$4);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$195(final Tuple2 check$ifrefutable$98) {
            boolean var1;
            if (check$ifrefutable$98 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$196(final SparseVector b$62, final VectorBuilder builder$14, final Tuple2 x$98) {
            if (x$98 != null) {
               int k = x$98._1$mcI$sp();
               double v = x$98._2$mcD$sp();
               double r = v % b$62.otherApply$mcD$sp(k);
               if (r != (double)0.0F) {
                  builder$14.add$mcD$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$98);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$99) -> BoxesRunTime.boxToBoolean($anonfun$apply$197(check$ifrefutable$99))).foreach((x$99) -> {
                        $anonfun$apply$198(b, builder, x$99);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcF$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = a.apply$mcF$sp(index$macro$4) % b.otherApply$mcF$sp(index$macro$4);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$197(final Tuple2 check$ifrefutable$99) {
            boolean var1;
            if (check$ifrefutable$99 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$198(final SparseVector b$63, final VectorBuilder builder$15, final Tuple2 x$99) {
            if (x$99 != null) {
               int k = x$99._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$99._2());
               float r = v % b$63.otherApply$mcF$sp(k);
               if (r != 0.0F) {
                  builder$15.add$mcF$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$99);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$100) -> BoxesRunTime.boxToBoolean($anonfun$apply$199(check$ifrefutable$100))).foreach((x$100) -> {
                        $anonfun$apply$200(b, builder, x$100);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcJ$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = a.apply$mcJ$sp(index$macro$4) % b.otherApply$mcJ$sp(index$macro$4);
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$199(final Tuple2 check$ifrefutable$100) {
            boolean var1;
            if (check$ifrefutable$100 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$200(final SparseVector b$64, final VectorBuilder builder$16, final Tuple2 x$100) {
            if (x$100 != null) {
               int k = x$100._1$mcI$sp();
               long v = x$100._2$mcJ$sp();
               long r = v % b$64.otherApply$mcJ$sp(k);
               if (r != 0L) {
                  builder$16.add$mcJ$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$100);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$101) -> BoxesRunTime.boxToBoolean($anonfun$apply$201(check$ifrefutable$101))).foreach((x$101) -> {
                        $anonfun$apply$202(b, builder, x$101);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcI$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(index$macro$4)).pow(b.otherApply$mcI$sp(index$macro$4));
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$201(final Tuple2 check$ifrefutable$101) {
            boolean var1;
            if (check$ifrefutable$101 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$202(final SparseVector b$65, final VectorBuilder builder$17, final Tuple2 x$101) {
            if (x$101 != null) {
               int k = x$101._1$mcI$sp();
               int v = x$101._2$mcI$sp();
               int r = PowImplicits$.MODULE$.IntPow(v).pow(b$65.otherApply$mcI$sp(k));
               if (r != 0) {
                  builder$17.add$mcI$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$101);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$102) -> BoxesRunTime.boxToBoolean($anonfun$apply$203(check$ifrefutable$102))).foreach((x$102) -> {
                        $anonfun$apply$204(b, builder, x$102);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcD$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(index$macro$4)).pow(b.otherApply$mcD$sp(index$macro$4));
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$203(final Tuple2 check$ifrefutable$102) {
            boolean var1;
            if (check$ifrefutable$102 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$204(final SparseVector b$66, final VectorBuilder builder$18, final Tuple2 x$102) {
            if (x$102 != null) {
               int k = x$102._1$mcI$sp();
               double v = x$102._2$mcD$sp();
               double r = PowImplicits$.MODULE$.DoublePow(v).pow(b$66.otherApply$mcD$sp(k));
               if (r != (double)0.0F) {
                  builder$18.add$mcD$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$102);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$103) -> BoxesRunTime.boxToBoolean($anonfun$apply$205(check$ifrefutable$103))).foreach((x$103) -> {
                        $anonfun$apply$206(b, builder, x$103);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcF$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(index$macro$4)).pow(b.otherApply$mcF$sp(index$macro$4));
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$205(final Tuple2 check$ifrefutable$103) {
            boolean var1;
            if (check$ifrefutable$103 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$206(final SparseVector b$67, final VectorBuilder builder$19, final Tuple2 x$103) {
            if (x$103 != null) {
               int k = x$103._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$103._2());
               float r = PowImplicits$.MODULE$.FloatPow(v).pow(b$67.otherApply$mcF$sp(k));
               if (r != 0.0F) {
                  builder$19.add$mcF$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$103);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label33: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var10000.equals(var6)) {
                     break label33;
                  }

                  if (b.activeSize() == b.length()) {
                     a.activeIterator().withFilter((check$ifrefutable$104) -> BoxesRunTime.boxToBoolean($anonfun$apply$207(check$ifrefutable$104))).foreach((x$104) -> {
                        $anonfun$apply$208(b, builder, x$104);
                        return BoxedUnit.UNIT;
                     });
                     return builder.toHashVector$mcJ$sp();
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = b.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(index$macro$4)).pow(b.otherApply$mcJ$sp(index$macro$4));
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toHashVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$207(final Tuple2 check$ifrefutable$104) {
            boolean var1;
            if (check$ifrefutable$104 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$208(final SparseVector b$68, final VectorBuilder builder$20, final Tuple2 x$104) {
            if (x$104 != null) {
               int k = x$104._1$mcI$sp();
               long v = x$104._2$mcJ$sp();
               long r = PowImplicits$.MODULE$.LongPow(v).pow(b$68.otherApply$mcJ$sp(k));
               if (r != 0L) {
                  builder$20.add$mcJ$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$104);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Int_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               if (b.activeSize() < a.iterableSize()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int i = b.indexAt(index$macro$4);
                     int v = b.valueAt$mcI$sp(index$macro$4);
                     int r = a.apply$mcI$sp(i) * v;
                     if (r != 0) {
                        builder.add$mcI$sp(i, r);
                     }
                  }
               } else {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.iterableSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     if (a.isActive(index$macro$9)) {
                        int i = a.index()[index$macro$9];
                        int v = a.data$mcI$sp()[index$macro$9];
                        int r = v * b.apply$mcI$sp(i);
                        if (r != 0) {
                           builder.add$mcI$sp(i, r);
                        }
                     }
                  }
               }

               return builder.toHashVector$mcI$sp();
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Double_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               if (b.activeSize() < a.iterableSize()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int i = b.indexAt(index$macro$4);
                     double v = b.valueAt$mcD$sp(index$macro$4);
                     double r = a.apply$mcD$sp(i) * v;
                     if (r != (double)0.0F) {
                        builder.add$mcD$sp(i, r);
                     }
                  }
               } else {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.iterableSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     if (a.isActive(index$macro$9)) {
                        int i = a.index()[index$macro$9];
                        double v = a.data$mcD$sp()[index$macro$9];
                        double r = v * b.apply$mcD$sp(i);
                        if (r != (double)0.0F) {
                           builder.add$mcD$sp(i, r);
                        }
                     }
                  }
               }

               return builder.toHashVector$mcD$sp();
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Float_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               if (b.activeSize() < a.iterableSize()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int i = b.indexAt(index$macro$4);
                     float v = b.valueAt$mcF$sp(index$macro$4);
                     float r = a.apply$mcF$sp(i) * v;
                     if (r != 0.0F) {
                        builder.add$mcF$sp(i, r);
                     }
                  }
               } else {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.iterableSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     if (a.isActive(index$macro$9)) {
                        int i = a.index()[index$macro$9];
                        float v = a.data$mcF$sp()[index$macro$9];
                        float r = v * b.apply$mcF$sp(i);
                        if (r != 0.0F) {
                           builder.add$mcF$sp(i, r);
                        }
                     }
                  }
               }

               return builder.toHashVector$mcF$sp();
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Long_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               if (b.activeSize() < a.iterableSize()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int i = b.indexAt(index$macro$4);
                     long v = b.valueAt$mcJ$sp(index$macro$4);
                     long r = a.apply$mcJ$sp(i) * v;
                     if (r != 0L) {
                        builder.add$mcJ$sp(i, r);
                     }
                  }
               } else {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.iterableSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     if (a.isActive(index$macro$9)) {
                        int i = a.index()[index$macro$9];
                        long v = a.data$mcJ$sp()[index$macro$9];
                        long r = v * b.apply$mcJ$sp(i);
                        if (r != 0L) {
                           builder.add$mcJ$sp(i, r);
                        }
                     }
                  }
               }

               return builder.toHashVector$mcJ$sp();
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector a, final int scale, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               if (scale != 0) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int k = b.indexAt(index$macro$4);
                     int v = b.valueAt$mcI$sp(index$macro$4);
                     a.update$mcI$sp(k, a.apply$mcI$sp(k) + scale * v);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Int())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.Int(), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector a, final double scale, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               if (scale != (double)0) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int k = b.indexAt(index$macro$4);
                     double v = b.valueAt$mcD$sp(index$macro$4);
                     a.update$mcD$sp(k, a.apply$mcD$sp(k) + scale * v);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Double())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.Double(), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector a, final float scale, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               if (scale != (float)0) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int k = b.indexAt(index$macro$4);
                     float v = b.valueAt$mcF$sp(index$macro$4);
                     a.update$mcF$sp(k, a.apply$mcF$sp(k) + scale * v);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Float())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.Float(), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector a, final long scale, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               if (scale != 0L) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int k = b.indexAt(index$macro$4);
                     long v = b.valueAt$mcJ$sp(index$macro$4);
                     a.update$mcJ$sp(k, a.apply$mcJ$sp(k) + scale * v);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Long())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.Long(), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int result = 0;
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += a.apply$mcI$sp(b.indexAt(index$macro$4)) * b.valueAt$mcI$sp(index$macro$4);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long result = 0L;
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += a.apply$mcJ$sp(b.indexAt(index$macro$4)) * b.valueAt$mcJ$sp(index$macro$4);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float result = 0.0F;
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += a.apply$mcF$sp(b.indexAt(index$macro$4)) * b.valueAt$mcF$sp(index$macro$4);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final HashVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double result = (double)0.0F;
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += a.apply$mcD$sp(b.indexAt(index$macro$4)) * b.valueAt$mcD$sp(index$macro$4);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, .MODULE$.apply(HashVector.class), .MODULE$.apply(SparseVector.class));
         }
      });
   }
}
