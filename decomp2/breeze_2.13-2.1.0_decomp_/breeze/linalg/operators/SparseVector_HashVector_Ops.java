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
   bytes = "\u0006\u0005\u00055gaB\u0010!!\u0003\r\ta\n\u0005\u0006q\u0001!\t!\u000f\u0005\b{\u0001\u0011\r\u0011b\u0001?\u0011\u001d\u0019\u0006A1A\u0005\u0004QCqa\u0017\u0001C\u0002\u0013\rA\fC\u0004d\u0001\t\u0007I1\u00013\t\u000f-\u0004!\u0019!C\u0002Y\"9\u0011\u000f\u0001b\u0001\n\u0007\u0011\bb\u0002;\u0001\u0005\u0004%\u0019!\u001e\u0005\bo\u0002\u0011\r\u0011b\u0001y\u0011\u001dQ\bA1A\u0005\u0004mD\u0011\"!\u0001\u0001\u0005\u0004%\u0019!a\u0001\t\u0013\u0005\u001d\u0001A1A\u0005\u0004\u0005%\u0001\"CA\u0007\u0001\t\u0007I1AA\b\u0011%\t\u0019\u0002\u0001b\u0001\n\u0007\t)\u0002C\u0005\u0002 \u0001\u0011\r\u0011b\u0001\u0002\"!I\u0011Q\u0005\u0001C\u0002\u0013\r\u0011q\u0005\u0005\n\u0003W\u0001!\u0019!C\u0002\u0003[A\u0011\"!\r\u0001\u0005\u0004%\u0019!a\r\t\u0013\u0005u\u0002A1A\u0005\u0004\u0005}\u0002\"CA\"\u0001\t\u0007I1AA#\u0011%\tI\u0005\u0001b\u0001\n\u0007\tY\u0005C\u0005\u0002P\u0001\u0011\r\u0011b\u0001\u0002R!I\u00111\f\u0001C\u0002\u0013\r\u0011Q\f\u0005\n\u0003C\u0002!\u0019!C\u0002\u0003GB\u0011\"a\u001a\u0001\u0005\u0004%\u0019!!\u001b\t\u0013\u00055\u0004A1A\u0005\u0004\u0005=\u0004\"CA=\u0001\t\u0007I1AA>\u0011%\ty\b\u0001b\u0001\n\u0007\t\t\tC\u0005\u0002\u0006\u0002\u0011\r\u0011b\u0001\u0002\b\"9\u00111\u0012\u0001\u0005\u0004\u00055%aG*qCJ\u001cXMV3di>\u0014x\fS1tQZ+7\r^8s?>\u00038O\u0003\u0002\"E\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003G\u0011\na\u0001\\5oC2<'\"A\u0013\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019R\u0001\u0001\u0015/eU\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00181\u001b\u0005\u0001\u0013BA\u0019!\u0005MA\u0015m\u001d5WK\u000e$xN]#ya\u0006tGm\u00149t!\ty3'\u0003\u00025A\tY\u0002*Y:i-\u0016\u001cGo\u001c:`'B\f'o]3WK\u000e$xN]0PaN\u0004\"a\f\u001c\n\u0005]\u0002#aD*qCJ\u001cXMV3di>\u0014x\n]:\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0004CA\u0015<\u0013\ta$F\u0001\u0003V]&$\u0018aK5na2|v\n]0T-~CekX3r?N3v\f\u001c5t?:LG\u000e]8uK:$x,\u00138u?>\u0003H)\u001b<\u0016\u0003}\u0002R\u0001Q\"J!&s!aL!\n\u0005\t\u0003\u0013!B(q\t&4\u0018B\u0001#F\u0005\u0015IU\u000e\u001d73\u0013\t1uIA\u0003V\rVt7M\u0003\u0002II\u00059q-\u001a8fe&\u001c\u0007c\u0001&L\u001b6\t!%\u0003\u0002ME\ta1\u000b]1sg\u00164Vm\u0019;peB\u0011\u0011FT\u0005\u0003\u001f*\u00121!\u00138u!\rQ\u0015+T\u0005\u0003%\n\u0012!\u0002S1tQZ+7\r^8s\u00039JW\u000e\u001d7`\u001fB|6KV0I-~+\u0017oX*W?2D7o\u00188jYB|G/\u001a8u?\u0012{WO\u00197f?>\u0003H)\u001b<\u0016\u0003U\u0003R\u0001Q\"W5Z\u00032AS&X!\tI\u0003,\u0003\u0002ZU\t1Ai\\;cY\u0016\u00042AS)X\u00035JW\u000e\u001d7`\u001fB|6KV0I-~+\u0017oX*W?2D7o\u00188jYB|G/\u001a8u?\u001acw.\u0019;`\u001fB$\u0015N^\u000b\u0002;B)\u0001i\u00110c=B\u0019!jS0\u0011\u0005%\u0002\u0017BA1+\u0005\u00151En\\1u!\rQ\u0015kX\u0001-S6\u0004HnX(q?N3v\f\u0013,`KF|6KV0mQN|f.\u001b7q_R,g\u000e^0M_:<wl\u00149ESZ,\u0012!\u001a\t\u0006\u0001\u000e3'N\u001a\t\u0004\u0015.;\u0007CA\u0015i\u0013\tI'F\u0001\u0003M_:<\u0007c\u0001&RO\u0006Y\u0013.\u001c9m?>\u0003xl\u0015,`\u0011Z{V-]0T-~c\u0007n]0oS2\u0004x\u000e^3oi~Ke\u000e^0PaN+G/F\u0001n!\u0015q7)\u0013)J\u001d\tys.\u0003\u0002qA\u0005)q\n]*fi\u0006q\u0013.\u001c9m?>\u0003xl\u0015,`\u0011Z{V-]0T-~c\u0007n]0oS2\u0004x\u000e^3oi~#u.\u001e2mK~{\u0005oU3u+\u0005\u0019\b#\u00028D-j3\u0016!L5na2|v\n]0T-~CekX3r?N3v\f\u001c5t?:LG\u000e]8uK:$xL\u00127pCR|v\n]*fiV\ta\u000fE\u0003o\u0007z\u0013g,\u0001\u0017j[Bdwl\u00149`'Z{\u0006JV0fc~\u001bfk\u00187ig~s\u0017\u000e\u001c9pi\u0016tGo\u0018'p]\u001e|v\n]*fiV\t\u0011\u0010E\u0003o\u0007\u001aTg-A\u0016j[Bdwl\u00149`'Z{\u0006JV0fc~\u001bfk\u00187ig~s\u0017\u000e\u001c9pi\u0016tGoX%oi~{\u0005/T8e+\u0005a\b#B?D\u0013BKeBA\u0018\u007f\u0013\ty\b%A\u0003Pa6{G-\u0001\u0018j[Bdwl\u00149`'Z{\u0006JV0fc~\u001bfk\u00187ig~s\u0017\u000e\u001c9pi\u0016tGo\u0018#pk\ndWmX(q\u001b>$WCAA\u0003!\u0015i8I\u0016.W\u00035JW\u000e\u001d7`\u001fB|6KV0I-~+\u0017oX*W?2D7o\u00188jYB|G/\u001a8u?\u001acw.\u0019;`\u001fBlu\u000eZ\u000b\u0003\u0003\u0017\u0001R!`\"_Ez\u000bA&[7qY~{\u0005oX*W?\"3v,Z9`'Z{F\u000e[:`]&d\u0007o\u001c;f]R|Fj\u001c8h?>\u0003Xj\u001c3\u0016\u0005\u0005E\u0001#B?DM*4\u0017aK5na2|v\n]0T-~CekX3r?N3v\f\u001c5t?:LG\u000e]8uK:$x,\u00138u?>\u0003\bk\\<\u0016\u0005\u0005]\u0001CBA\r\u0007&\u0003\u0016JD\u00020\u00037I1!!\b!\u0003\u0015y\u0005\u000fU8x\u00039JW\u000e\u001d7`\u001fB|6KV0I-~+\u0017oX*W?2D7o\u00188jYB|G/\u001a8u?\u0012{WO\u00197f?>\u0003\bk\\<\u0016\u0005\u0005\r\u0002CBA\r\u0007ZSf+A\u0017j[Bdwl\u00149`'Z{\u0006JV0fc~\u001bfk\u00187ig~s\u0017\u000e\u001c9pi\u0016tGo\u0018$m_\u0006$xl\u00149Q_^,\"!!\u000b\u0011\r\u0005e1I\u00182_\u00031JW\u000e\u001d7`\u001fB|6KV0I-~+\u0017oX*W?2D7o\u00188jYB|G/\u001a8u?2{gnZ0PaB{w/\u0006\u0002\u00020A1\u0011\u0011D\"gU\u001a\f\u0001%[7qY~{\u0005/T;m'\u000e\fG.\u0019:`'Z{\u0006JV0fc~\u001bfkX%oiV\u0011\u0011Q\u0007\t\u0007\u0003o\u0019\u0015\nU%\u000f\u0007=\nI$C\u0002\u0002<\u0001\n1b\u00149Nk2\u001c6-\u00197be\u0006\u0019\u0013.\u001c9m?>\u0003X*\u001e7TG\u0006d\u0017M]0T-~CekX3r?N3v\fR8vE2,WCAA!!\u0019\t9d\u0011,[-\u0006\u0011\u0013.\u001c9m?>\u0003X*\u001e7TG\u0006d\u0017M]0T-~CekX3r?N3vL\u00127pCR,\"!a\u0012\u0011\r\u0005]2I\u00182_\u0003\u0005JW\u000e\u001d7`\u001fBlU\u000f\\*dC2\f'oX*W?\"3v,Z9`'Z{Fj\u001c8h+\t\ti\u0005\u0005\u0004\u00028\r3'NZ\u0001\u001eS6\u0004HnX(q?N3v\f\u0013,`KF|6KV0J]R|v\n]!eIV\u0011\u00111\u000b\t\u0007\u0003+\u001a\u0015\nU%\u000f\u0007=\n9&C\u0002\u0002Z\u0001\nQa\u00149BI\u0012\f\u0001%[7qY~{\u0005oX*W?\"3v,Z9`'Z{Fi\\;cY\u0016|v\n]!eIV\u0011\u0011q\f\t\u0007\u0003+\u001aeK\u0017,\u0002?%l\u0007\u000f\\0Pa~\u001bfk\u0018%W?\u0016\fxl\u0015,`\r2|\u0017\r^0Pa\u0006#G-\u0006\u0002\u0002fA1\u0011QK\"_Ez\u000ba$[7qY~{\u0005oX*W?\"3v,Z9`'Z{Fj\u001c8h?>\u0003\u0018\t\u001a3\u0016\u0005\u0005-\u0004CBA+\u0007\u001aTg-A\u000fj[Bdwl\u00149`'Z{\u0006JV0fc~\u001bfkX%oi~{\u0005oU;c+\t\t\t\b\u0005\u0004\u0002t\rK\u0005+\u0013\b\u0004_\u0005U\u0014bAA<A\u0005)q\n]*vE\u0006\u0001\u0013.\u001c9m?>\u0003xl\u0015,`\u0011Z{V-]0T-~#u.\u001e2mK~{\u0005oU;c+\t\ti\b\u0005\u0004\u0002t\r3&LV\u0001 S6\u0004HnX(q?N3v\f\u0013,`KF|6KV0GY>\fGoX(q'V\u0014WCAAB!\u0019\t\u0019h\u00110c=\u0006q\u0012.\u001c9m?>\u0003xl\u0015,`\u0011Z{V-]0T-~cuN\\4`\u001fB\u001cVOY\u000b\u0003\u0003\u0013\u0003b!a\u001dDM*4\u0017AG5na2|v\n]'vY&sg.\u001a:`'Z{\u0006JV0fc~#V\u0003BAH\u0003c#B!!%\u0002FBI\u00111S\"\u0002,\u0006\r\u0017Q\u0016\b\u0005\u0003+\u000b9K\u0004\u0003\u0002\u0018\u0006\u0015f\u0002BAM\u0003GsA!a'\u0002\"6\u0011\u0011Q\u0014\u0006\u0004\u0003?3\u0013A\u0002\u001fs_>$h(C\u0001&\u0013\t\u0019C%\u0003\u0002\"E%\u0019\u0011\u0011\u0016\u0011\u0002\u0015=\u0003X*\u001e7J]:,'\u000f\u0005\u0003K\u0017\u00065\u0006\u0003BAX\u0003cc\u0001\u0001B\u0004\u00024z\u0011\r!!.\u0003\u0003Q\u000bB!a.\u0002>B\u0019\u0011&!/\n\u0007\u0005m&FA\u0004O_RD\u0017N\\4\u0011\u0007%\ny,C\u0002\u0002B*\u00121!\u00118z!\u0011Q\u0015+!,\t\u000f\u0005\u001dg\u0004q\u0001\u0002J\u0006\u0011q\u000e\u001d\t\n\u0003\u0017\u001c\u00151YAV\u0003[s1aLAT\u0001"
)
public interface SparseVector_HashVector_Ops extends HashVector_SparseVector_Ops {
   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow();

   UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Int();

   UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Double();

   UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Float();

   UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Long();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpSub();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpSub();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpSub();

   UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpSub();

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_SV_HV_eq_T$(final SparseVector_HashVector_Ops $this, final UFunc.UImpl2 op) {
      return $this.impl_OpMulInner_SV_HV_eq_T(op);
   }

   default UFunc.UImpl2 impl_OpMulInner_SV_HV_eq_T(final UFunc.UImpl2 op) {
      return (a, b) -> b.dot(a, op);
   }

   static void $init$(final SparseVector_HashVector_Ops $this) {
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        int v = a.valueAt$mcI$sp(index$macro$9);
                        int r = v / b.apply$mcI$sp(k);
                        if (r != 0) {
                           builder.add$mcI$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcI$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = a.otherApply$mcI$sp(index$macro$4) / b.apply$mcI$sp(index$macro$4);
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        double v = a.valueAt$mcD$sp(index$macro$9);
                        double r = v / b.apply$mcD$sp(k);
                        if (r != (double)0.0F) {
                           builder.add$mcD$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcD$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = a.otherApply$mcD$sp(index$macro$4) / b.apply$mcD$sp(index$macro$4);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        float v = a.valueAt$mcF$sp(index$macro$9);
                        float r = v / b.apply$mcF$sp(k);
                        if (r != 0.0F) {
                           builder.add$mcF$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcF$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = a.otherApply$mcF$sp(index$macro$4) / b.apply$mcF$sp(index$macro$4);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        long v = a.valueAt$mcJ$sp(index$macro$9);
                        long r = v / b.apply$mcJ$sp(k);
                        if (r != 0L) {
                           builder.add$mcJ$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcJ$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = a.otherApply$mcJ$sp(index$macro$4) / b.apply$mcJ$sp(index$macro$4);
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        a.valueAt$mcI$sp(index$macro$9);
                        int r = b.apply$mcI$sp(k);
                        if (r != 0) {
                           builder.add$mcI$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcI$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = b.apply$mcI$sp(index$macro$4);
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        a.valueAt$mcD$sp(index$macro$9);
                        double r = b.apply$mcD$sp(k);
                        if (r != (double)0.0F) {
                           builder.add$mcD$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcD$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = b.apply$mcD$sp(index$macro$4);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        a.valueAt$mcF$sp(index$macro$9);
                        float r = b.apply$mcF$sp(k);
                        if (r != 0.0F) {
                           builder.add$mcF$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcF$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = b.apply$mcF$sp(index$macro$4);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpSet$ var6;
                  OpSet$ var10000 = var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        a.valueAt$mcJ$sp(index$macro$9);
                        long r = b.apply$mcJ$sp(k);
                        if (r != 0L) {
                           builder.add$mcJ$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcJ$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = b.apply$mcJ$sp(index$macro$4);
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        int v = a.valueAt$mcI$sp(index$macro$9);
                        int r = v % b.apply$mcI$sp(k);
                        if (r != 0) {
                           builder.add$mcI$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcI$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = a.otherApply$mcI$sp(index$macro$4) % b.apply$mcI$sp(index$macro$4);
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        double v = a.valueAt$mcD$sp(index$macro$9);
                        double r = v % b.apply$mcD$sp(k);
                        if (r != (double)0.0F) {
                           builder.add$mcD$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcD$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = a.otherApply$mcD$sp(index$macro$4) % b.apply$mcD$sp(index$macro$4);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        float v = a.valueAt$mcF$sp(index$macro$9);
                        float r = v % b.apply$mcF$sp(k);
                        if (r != 0.0F) {
                           builder.add$mcF$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcF$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = a.otherApply$mcF$sp(index$macro$4) % b.apply$mcF$sp(index$macro$4);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        long v = a.valueAt$mcJ$sp(index$macro$9);
                        long r = v % b.apply$mcJ$sp(k);
                        if (r != 0L) {
                           builder.add$mcJ$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcJ$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = a.otherApply$mcJ$sp(index$macro$4) % b.apply$mcJ$sp(index$macro$4);
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        int v = a.valueAt$mcI$sp(index$macro$9);
                        int r = PowImplicits$.MODULE$.IntPow(v).pow(b.apply$mcI$sp(k));
                        if (r != 0) {
                           builder.add$mcI$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcI$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int r = PowImplicits$.MODULE$.IntPow(a.otherApply$mcI$sp(index$macro$4)).pow(b.apply$mcI$sp(index$macro$4));
                  if (r != 0) {
                     builder.add$mcI$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        double v = a.valueAt$mcD$sp(index$macro$9);
                        double r = PowImplicits$.MODULE$.DoublePow(v).pow(b.apply$mcD$sp(k));
                        if (r != (double)0.0F) {
                           builder.add$mcD$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcD$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  double r = PowImplicits$.MODULE$.DoublePow(a.otherApply$mcD$sp(index$macro$4)).pow(b.apply$mcD$sp(index$macro$4));
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        float v = a.valueAt$mcF$sp(index$macro$9);
                        float r = PowImplicits$.MODULE$.FloatPow(v).pow(b.apply$mcF$sp(k));
                        if (r != 0.0F) {
                           builder.add$mcF$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcF$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  float r = PowImplicits$.MODULE$.FloatPow(a.otherApply$mcF$sp(index$macro$4)).pow(b.apply$mcF$sp(index$macro$4));
                  if (r != 0.0F) {
                     builder.add$mcF$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder;
               label44: {
                  builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpSet$ var6 = OpSet$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (var10000.equals(var6)) {
                     break label44;
                  }

                  if (b.activeSize() == b.length()) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = a.activeSize(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        int k = a.indexAt(index$macro$9);
                        long v = a.valueAt$mcJ$sp(index$macro$9);
                        long r = PowImplicits$.MODULE$.LongPow(v).pow(b.apply$mcJ$sp(k));
                        if (r != 0L) {
                           builder.add$mcJ$sp(k, r);
                        }
                     }

                     return builder.toSparseVector$mcJ$sp(true, true);
                  }
               }

               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  long r = PowImplicits$.MODULE$.LongPow(a.otherApply$mcJ$sp(index$macro$4)).pow(b.apply$mcJ$sp(index$macro$4));
                  if (r != 0L) {
                     builder.add$mcJ$sp(index$macro$4, r);
                  }
               }

               return builder.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Int_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int k = a.indexAt(index$macro$4);
                  int v = a.valueAt$mcI$sp(index$macro$4);
                  int r = v * b.apply$mcI$sp(k);
                  if (r != 0) {
                     builder.add$mcI$sp(k, r);
                  }
               }

               return builder.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Double_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int k = a.indexAt(index$macro$4);
                  double v = a.valueAt$mcD$sp(index$macro$4);
                  double r = v * b.apply$mcD$sp(k);
                  if (r != (double)0.0F) {
                     builder.add$mcD$sp(k, r);
                  }
               }

               return builder.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Float_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int k = a.indexAt(index$macro$4);
                  float v = a.valueAt$mcF$sp(index$macro$4);
                  float r = v * b.apply$mcF$sp(k);
                  if (r != 0.0F) {
                     builder.add$mcF$sp(k, r);
                  }
               }

               return builder.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Long_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int k = a.indexAt(index$macro$4);
                  long v = a.valueAt$mcJ$sp(index$macro$4);
                  long r = v * b.apply$mcJ$sp(k);
                  if (r != 0L) {
                     builder.add$mcJ$sp(k, r);
                  }
               }

               return builder.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  int v = a.valueAt$mcI$sp(aoff);
                  builder.add$mcI$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$105) -> BoxesRunTime.boxToBoolean($anonfun$apply$209(check$ifrefutable$105))).foreach((x$105) -> {
                  $anonfun$apply$210(builder, x$105);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$209(final Tuple2 check$ifrefutable$105) {
            boolean var1;
            if (check$ifrefutable$105 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$210(final VectorBuilder builder$21, final Tuple2 x$105) {
            if (x$105 != null) {
               int k = x$105._1$mcI$sp();
               int v = x$105._2$mcI$sp();
               builder$21.add$mcI$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$105);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  double v = a.valueAt$mcD$sp(aoff);
                  builder.add$mcD$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$106) -> BoxesRunTime.boxToBoolean($anonfun$apply$211(check$ifrefutable$106))).foreach((x$106) -> {
                  $anonfun$apply$212(builder, x$106);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$211(final Tuple2 check$ifrefutable$106) {
            boolean var1;
            if (check$ifrefutable$106 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$212(final VectorBuilder builder$22, final Tuple2 x$106) {
            if (x$106 != null) {
               int k = x$106._1$mcI$sp();
               double v = x$106._2$mcD$sp();
               builder$22.add$mcD$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$106);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  float v = a.valueAt$mcF$sp(aoff);
                  builder.add$mcF$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$107) -> BoxesRunTime.boxToBoolean($anonfun$apply$213(check$ifrefutable$107))).foreach((x$107) -> {
                  $anonfun$apply$214(builder, x$107);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$213(final Tuple2 check$ifrefutable$107) {
            boolean var1;
            if (check$ifrefutable$107 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$214(final VectorBuilder builder$23, final Tuple2 x$107) {
            if (x$107 != null) {
               int k = x$107._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$107._2());
               builder$23.add$mcF$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$107);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  long v = a.valueAt$mcJ$sp(aoff);
                  builder.add$mcJ$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$108) -> BoxesRunTime.boxToBoolean($anonfun$apply$215(check$ifrefutable$108))).foreach((x$108) -> {
                  $anonfun$apply$216(builder, x$108);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$215(final Tuple2 check$ifrefutable$108) {
            boolean var1;
            if (check$ifrefutable$108 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$216(final VectorBuilder builder$24, final Tuple2 x$108) {
            if (x$108 != null) {
               int k = x$108._1$mcI$sp();
               long v = x$108._2$mcJ$sp();
               builder$24.add$mcJ$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$108);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  int v = a.valueAt$mcI$sp(aoff);
                  builder.add$mcI$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$109) -> BoxesRunTime.boxToBoolean($anonfun$apply$217(check$ifrefutable$109))).foreach((x$109) -> {
                  $anonfun$apply$218(builder, x$109);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$217(final Tuple2 check$ifrefutable$109) {
            boolean var1;
            if (check$ifrefutable$109 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$218(final VectorBuilder builder$25, final Tuple2 x$109) {
            if (x$109 != null) {
               int k = x$109._1$mcI$sp();
               int v = x$109._2$mcI$sp();
               builder$25.add$mcI$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$109);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  double v = a.valueAt$mcD$sp(aoff);
                  builder.add$mcD$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$110) -> BoxesRunTime.boxToBoolean($anonfun$apply$219(check$ifrefutable$110))).foreach((x$110) -> {
                  $anonfun$apply$220(builder, x$110);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$219(final Tuple2 check$ifrefutable$110) {
            boolean var1;
            if (check$ifrefutable$110 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$220(final VectorBuilder builder$26, final Tuple2 x$110) {
            if (x$110 != null) {
               int k = x$110._1$mcI$sp();
               double v = x$110._2$mcD$sp();
               builder$26.add$mcD$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$110);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  float v = a.valueAt$mcF$sp(aoff);
                  builder.add$mcF$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$111) -> BoxesRunTime.boxToBoolean($anonfun$apply$221(check$ifrefutable$111))).foreach((x$111) -> {
                  $anonfun$apply$222(builder, x$111);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$221(final Tuple2 check$ifrefutable$111) {
            boolean var1;
            if (check$ifrefutable$111 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$222(final VectorBuilder builder$27, final Tuple2 x$111) {
            if (x$111 != null) {
               int k = x$111._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$111._2());
               builder$27.add$mcF$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$111);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());

               for(int aoff = 0; aoff < a.activeSize(); ++aoff) {
                  int k = a.indexAt(aoff);
                  long v = a.valueAt$mcJ$sp(aoff);
                  builder.add$mcJ$sp(k, v);
               }

               b.activeIterator().withFilter((check$ifrefutable$112) -> BoxesRunTime.boxToBoolean($anonfun$apply$223(check$ifrefutable$112))).foreach((x$112) -> {
                  $anonfun$apply$224(builder, x$112);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$223(final Tuple2 check$ifrefutable$112) {
            boolean var1;
            if (check$ifrefutable$112 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$224(final VectorBuilder builder$28, final Tuple2 x$112) {
            if (x$112 != null) {
               int k = x$112._1$mcI$sp();
               long v = x$112._2$mcJ$sp();
               builder$28.add$mcJ$sp(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$112);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_HashVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
