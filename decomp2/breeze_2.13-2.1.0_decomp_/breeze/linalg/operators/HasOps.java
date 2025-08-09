package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.gymnastics.NotGiven;
import breeze.linalg.Transpose;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanForeachValues;
import breeze.linalg.support.CanIterateAxis;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseAxis;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipAndTraverseValues;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import breeze.math.Field;
import breeze.math.MutableInnerProductVectorSpace;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3q!\u0002\u0004\u0011\u0002G\u0005QbB\u0003\u0015\r!\u0005QCB\u0003\u0006\r!\u0005q\u0003C\u0003M\u0005\u0011\u0005Q\nC\u0004O\u0005\u0005\u0005I\u0011B(\u0003\r!\u000b7o\u00149t\u0015\t9\u0001\"A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011\u0011BC\u0001\u0007Y&t\u0017\r\\4\u000b\u0003-\taA\u0019:fKj,7\u0001A\n\u0003\u00019\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u00111!\u00118z\u0003\u0019A\u0015m](qgB\u0011aCA\u0007\u0002\rM\t\"\u0001G\u000e\u001fC\u0011:#&\f\u00194mej\u0004IR%\u0011\u0005=I\u0012B\u0001\u000e\u0011\u0005\u0019\te.\u001f*fMB\u0011a\u0003H\u0005\u0003;\u0019\u0011!bR3oKJL7m\u00149t!\t1r$\u0003\u0002!\r\tIa+Z2u_J|\u0005o\u001d\t\u0003-\tJ!a\t\u0004\u0003\u001bQ+gn]8s\u0019><\bK]5p!\t1R%\u0003\u0002'\r\taAK]1ogB|7/Z(qgB\u0011a\u0003K\u0005\u0003S\u0019\u0011a\u0002R3og\u00164Vm\u0019;pe>\u00038\u000f\u0005\u0002\u0017W%\u0011AF\u0002\u0002\u0010'B\f'o]3WK\u000e$xN](qgB\u0011aCL\u0005\u0003_\u0019\u0011Q\u0002S1tQZ+7\r^8s\u001fB\u001c\bC\u0001\f2\u0013\t\u0011dAA\u0005NCR\u0014\u0018\u000e_(qgB\u0011a\u0003N\u0005\u0003k\u0019\u0011a\u0002R3og\u0016l\u0015\r\u001e:jq>\u00038\u000f\u0005\u0002\u0017o%\u0011\u0001H\u0002\u0002\r\u0007N\u001bU*\u0019;sSb|\u0005o\u001d\t\u0003umj\u0011\u0001C\u0005\u0003y!\u0011ab\u00157jG\u00164Vm\u0019;pe>\u00038\u000f\u0005\u0002\u0017}%\u0011qH\u0002\u0002\r\u0005&$h+Z2u_J|\u0005o\u001d\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007*\tqaZ3oKJL7-\u0003\u0002F\u0005\nyQ*\u00199qS:<WKR;oG>\u00038\u000f\u0005\u0002B\u000f&\u0011\u0001J\u0011\u0002\u00175\u0016\u0014x\u000e\u0015:fg\u0016\u0014h/\u001b8h+\u001a+hnY(qgB\u0011!HS\u0005\u0003\u0017\"\u0011aB\u0011:pC\u0012\u001c\u0017m\u001d;fI>\u00038/\u0001\u0004=S:LGO\u0010\u000b\u0002+\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t\u0001\u000b\u0005\u0002R-6\t!K\u0003\u0002T)\u0006!A.\u00198h\u0015\u0005)\u0016\u0001\u00026bm\u0006L!a\u0016*\u0003\r=\u0013'.Z2u\u0001"
)
public interface HasOps {
   static CanForeachValues canForeachRows_BRows(final CanTraverseAxis iter) {
      return HasOps$.MODULE$.canForeachRows_BRows(iter);
   }

   static UFunc.InPlaceImpl2 broadcastInplaceOp2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return HasOps$.MODULE$.broadcastInplaceOp2_BRows(handhold, op, cc);
   }

   static UFunc.UImpl2 broadcastOp2_2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp2_2_BRows(handhold, op, cc);
   }

   static UFunc.UImpl2 broadcastOp2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp2_BRows(handhold, op, cc);
   }

   static UFunc.InPlaceImpl broadcastInplaceOp_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return HasOps$.MODULE$.broadcastInplaceOp_BRows(handhold, op, cc);
   }

   static UFunc.UImpl broadcastOp_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp_BRows(handhold, op, cc);
   }

   static CanMapValues canMapValues_BRows(final CanCollapseAxis cc) {
      return HasOps$.MODULE$.canMapValues_BRows(cc);
   }

   static CanForeachValues canForeachColumns_BCols(final CanTraverseAxis iter) {
      return HasOps$.MODULE$.canForeachColumns_BCols(iter);
   }

   static UFunc.InPlaceImpl3 broadcastInplaceOp3_1_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl3 op, final CanTraverseAxis cc) {
      return HasOps$.MODULE$.broadcastInplaceOp3_1_BCols(handhold, op, cc);
   }

   static UFunc.UImpl3 broadcastOp3_1_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl3 op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp3_1_BCols(handhold, op, cc);
   }

   static UFunc.InPlaceImpl2 broadcastInplaceOp2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return HasOps$.MODULE$.broadcastInplaceOp2_BCols(handhold, op, cc);
   }

   static UFunc.UImpl2 broadcastOp2_2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp2_2_BCols(handhold, op, cc);
   }

   static UFunc.UImpl2 broadcastOp2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp2_BCols(handhold, op, cc);
   }

   static UFunc.InPlaceImpl broadcastInplaceOp_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return HasOps$.MODULE$.broadcastInplaceOp_BCols(handhold, op, cc);
   }

   static UFunc.UImpl broadcastOp_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return HasOps$.MODULE$.broadcastOp_BCols(handhold, op, cc);
   }

   static CanMapValues canMapValues_BCols(final CanCollapseAxis cc) {
      return HasOps$.MODULE$.canMapValues_BCols(cc);
   }

   static UFunc.InPlaceImpl2 canTransformActiveValuesUFunc2_T(final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return HasOps$.MODULE$.canTransformActiveValuesUFunc2_T(canTransform, impl);
   }

   static UFunc.InPlaceImpl canTransformActiveValuesUFunc(final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return HasOps$.MODULE$.canTransformActiveValuesUFunc(canTransform, impl);
   }

   static UFunc.UImpl2 canMapActiveV1DV(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return HasOps$.MODULE$.canMapActiveV1DV(handhold, impl, canMapValues);
   }

   static UFunc.UImpl fromLowOrderCanMapActiveValues(final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return HasOps$.MODULE$.fromLowOrderCanMapActiveValues(handhold, impl, canMapValues);
   }

   static UFunc.UImpl2 canMapV2ActiveValues(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return HasOps$.MODULE$.canMapV2ActiveValues(handhold, impl, canMapValues);
   }

   static UFunc.UImpl2 canMapV1DV(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return HasOps$.MODULE$.canMapV1DV(handhold, impl, canMapValues);
   }

   static UFunc.UImpl fromLowOrderCanMapValues(final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return HasOps$.MODULE$.fromLowOrderCanMapValues(handhold, impl, canMapValues);
   }

   static UFunc.InPlaceImpl2 canTransformValuesUFunc2_T(final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return HasOps$.MODULE$.canTransformValuesUFunc2_T(canTransform, impl);
   }

   static UFunc.InPlaceImpl canTransformValuesUFunc_T(final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return HasOps$.MODULE$.canTransformValuesUFunc_T(canTransform, impl);
   }

   static UFunc.UImpl2 canZipMapValuesImpl_T(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return HasOps$.MODULE$.canZipMapValuesImpl_T(handhold, impl, canZipMapValues);
   }

   static UFunc.UImpl2 canMapV2Values(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return HasOps$.MODULE$.canMapV2Values(handhold, impl, canMapValues);
   }

   static UFunc.UImpl2 canDot_Other_BV(final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.canDot_Other_BV(op);
   }

   static UFunc.UImpl2 canDot_BV_SV_Complex() {
      return HasOps$.MODULE$.canDot_BV_SV_Complex();
   }

   static UFunc.UImpl2 canDot_BV_SV_BigInt() {
      return HasOps$.MODULE$.canDot_BV_SV_BigInt();
   }

   static UFunc.UImpl2 canDot_BV_SV_Long() {
      return HasOps$.MODULE$.canDot_BV_SV_Long();
   }

   static UFunc.UImpl2 canDot_BV_SV_Int() {
      return HasOps$.MODULE$.canDot_BV_SV_Int();
   }

   static UFunc.UImpl2 canDot_BV_DenseVector_Long() {
      return HasOps$.MODULE$.canDot_BV_DenseVector_Long();
   }

   static UFunc.UImpl2 canDot_BV_DenseVector_Int() {
      return HasOps$.MODULE$.canDot_BV_DenseVector_Int();
   }

   static UFunc.UImpl2 canDot_BV_DenseVector_Float() {
      return HasOps$.MODULE$.canDot_BV_DenseVector_Float();
   }

   static UFunc.UImpl2 canDot_BV_DenseVector_Double() {
      return HasOps$.MODULE$.canDot_BV_DenseVector_Double();
   }

   static UFunc.UImpl2 canDot_BV_BV() {
      return HasOps$.MODULE$.canDot_BV_BV();
   }

   static UFunc.InPlaceImpl3 axpyGen(final .less.colon.less ev, final Semiring semi) {
      return HasOps$.MODULE$.axpyGen(ev, semi);
   }

   static UFunc.InPlaceImpl3 axpy_Long(final .less.colon.less ev) {
      return HasOps$.MODULE$.axpy_Long(ev);
   }

   static UFunc.InPlaceImpl3 axpy_Float(final .less.colon.less ev) {
      return HasOps$.MODULE$.axpy_Float(ev);
   }

   static UFunc.InPlaceImpl3 axpy_Double(final .less.colon.less ev) {
      return HasOps$.MODULE$.axpy_Double(ev);
   }

   static UFunc.InPlaceImpl3 axpy_Int(final .less.colon.less ev) {
      return HasOps$.MODULE$.axpy_Int(ev);
   }

   static UFunc.UImpl2 bv_bv_OpEq() {
      return HasOps$.MODULE$.bv_bv_OpEq();
   }

   static UFunc.UImpl2 bv_bv_OpNe() {
      return HasOps$.MODULE$.bv_bv_OpNe();
   }

   static UFunc.UImpl bv_OpNot() {
      return HasOps$.MODULE$.bv_OpNot();
   }

   static UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpXor() {
      return HasOps$.MODULE$.impl_Op_BV_BV_eq_BV_OpXor();
   }

   static UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpOr() {
      return HasOps$.MODULE$.impl_Op_BV_BV_eq_BV_OpOr();
   }

   static UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpAnd() {
      return HasOps$.MODULE$.impl_Op_BV_BV_eq_BV_OpAnd();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_BV_BV() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_BV_BV();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpXor() {
      return HasOps$.MODULE$.impl_Op_InPlace_BV_BV_OpXor();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpOr() {
      return HasOps$.MODULE$.impl_Op_InPlace_BV_BV_OpOr();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpAnd() {
      return HasOps$.MODULE$.impl_Op_InPlace_BV_BV_OpAnd();
   }

   static BitVectorOps.impl_all_BV_eq_Boolean$ impl_all_BV_eq_Boolean() {
      return HasOps$.MODULE$.impl_all_BV_eq_Boolean();
   }

   static BitVectorOps.impl_any_BV_eq_Boolean$ impl_any_BV_eq_Boolean() {
      return HasOps$.MODULE$.impl_any_BV_eq_Boolean();
   }

   static CanTraverseKeyValuePairs CSC_canIterateKeysValues(final Zero evidence$9) {
      return HasOps$.MODULE$.CSC_canIterateKeysValues(evidence$9);
   }

   static CanTraverseValues CSC_canIterateValues() {
      return HasOps$.MODULE$.CSC_canIterateValues();
   }

   static ScalarOf CSC_scalarOf() {
      return HasOps$.MODULE$.CSC_scalarOf();
   }

   static CanMapValues CSC_canMapValues(final ClassTag evidence$7, final Semiring evidence$8) {
      return HasOps$.MODULE$.CSC_canMapValues(evidence$7, evidence$8);
   }

   static CanCreateZerosLike CSC_canCreateZerosLike(final ClassTag evidence$5, final Zero evidence$6) {
      return HasOps$.MODULE$.CSC_canCreateZerosLike(evidence$5, evidence$6);
   }

   static CSCMatrixOps.CanCopyCSCMatrix CSC_canCopy(final ClassTag evidence$3, final Zero evidence$4) {
      return HasOps$.MODULE$.CSC_canCopy(evidence$3, evidence$4);
   }

   static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Long() {
      return HasOps$.MODULE$.axpyCSC_DM_DM_Long();
   }

   static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Double() {
      return HasOps$.MODULE$.axpyCSC_DM_DM_Double();
   }

   static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Float() {
      return HasOps$.MODULE$.axpyCSC_DM_DM_Float();
   }

   static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Int() {
      return HasOps$.MODULE$.axpyCSC_DM_DM_Int();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMulScalar() {
      return HasOps$.MODULE$.csc_csc_InPlace_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMulScalar() {
      return HasOps$.MODULE$.csc_csc_InPlace_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMulScalar() {
      return HasOps$.MODULE$.csc_csc_InPlace_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMulScalar() {
      return HasOps$.MODULE$.csc_csc_InPlace_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMod() {
      return HasOps$.MODULE$.csc_csc_InPlace_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMod() {
      return HasOps$.MODULE$.csc_csc_InPlace_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMod() {
      return HasOps$.MODULE$.csc_csc_InPlace_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMod() {
      return HasOps$.MODULE$.csc_csc_InPlace_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpPow() {
      return HasOps$.MODULE$.csc_csc_InPlace_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpPow() {
      return HasOps$.MODULE$.csc_csc_InPlace_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpPow() {
      return HasOps$.MODULE$.csc_csc_InPlace_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpPow() {
      return HasOps$.MODULE$.csc_csc_InPlace_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpDiv() {
      return HasOps$.MODULE$.csc_csc_InPlace_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpDiv() {
      return HasOps$.MODULE$.csc_csc_InPlace_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpDiv() {
      return HasOps$.MODULE$.csc_csc_InPlace_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpDiv() {
      return HasOps$.MODULE$.csc_csc_InPlace_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpSub() {
      return HasOps$.MODULE$.csc_csc_InPlace_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpSub() {
      return HasOps$.MODULE$.csc_csc_InPlace_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpSub() {
      return HasOps$.MODULE$.csc_csc_InPlace_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpSub() {
      return HasOps$.MODULE$.csc_csc_InPlace_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpAdd() {
      return HasOps$.MODULE$.csc_csc_InPlace_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpAdd() {
      return HasOps$.MODULE$.csc_csc_InPlace_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpAdd() {
      return HasOps$.MODULE$.csc_csc_InPlace_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpAdd() {
      return HasOps$.MODULE$.csc_csc_InPlace_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd();
   }

   static UFunc.UImpl2 canMulM_M_Long() {
      return HasOps$.MODULE$.canMulM_M_Long();
   }

   static UFunc.UImpl2 canMulM_M_Double() {
      return HasOps$.MODULE$.canMulM_M_Double();
   }

   static UFunc.UImpl2 canMulM_M_Float() {
      return HasOps$.MODULE$.canMulM_M_Float();
   }

   static UFunc.UImpl2 canMulM_M_Int() {
      return HasOps$.MODULE$.canMulM_M_Int();
   }

   static UFunc.UImpl2 canMulDM_M_Long() {
      return HasOps$.MODULE$.canMulDM_M_Long();
   }

   static UFunc.UImpl2 canMulDM_M_Double() {
      return HasOps$.MODULE$.canMulDM_M_Double();
   }

   static UFunc.UImpl2 canMulDM_M_Float() {
      return HasOps$.MODULE$.canMulDM_M_Float();
   }

   static UFunc.UImpl2 canMulDM_M_Int() {
      return HasOps$.MODULE$.canMulDM_M_Int();
   }

   static UFunc.UImpl2 canMulM_DM_Long() {
      return HasOps$.MODULE$.canMulM_DM_Long();
   }

   static UFunc.UImpl2 canMulM_DM_Double() {
      return HasOps$.MODULE$.canMulM_DM_Double();
   }

   static UFunc.UImpl2 canMulM_DM_Float() {
      return HasOps$.MODULE$.canMulM_DM_Float();
   }

   static UFunc.UImpl2 canMulM_DM_Int() {
      return HasOps$.MODULE$.canMulM_DM_Int();
   }

   static BinaryRegistry canMulM_SV_Long() {
      return HasOps$.MODULE$.canMulM_SV_Long();
   }

   static BinaryRegistry canMulM_SV_Double() {
      return HasOps$.MODULE$.canMulM_SV_Double();
   }

   static BinaryRegistry canMulM_SV_Float() {
      return HasOps$.MODULE$.canMulM_SV_Float();
   }

   static BinaryRegistry canMulM_SV_Int() {
      return HasOps$.MODULE$.canMulM_SV_Int();
   }

   static UFunc.UImpl2 canMulM_DV_Long() {
      return HasOps$.MODULE$.canMulM_DV_Long();
   }

   static UFunc.UImpl2 canMulM_DV_Double() {
      return HasOps$.MODULE$.canMulM_DV_Double();
   }

   static UFunc.UImpl2 canMulM_DV_Float() {
      return HasOps$.MODULE$.canMulM_DV_Float();
   }

   static UFunc.UImpl2 canMulM_DV_Int() {
      return HasOps$.MODULE$.canMulM_DV_Int();
   }

   static BinaryRegistry canMulM_V_Long() {
      return HasOps$.MODULE$.canMulM_V_Long();
   }

   static BinaryRegistry canMulM_V_Double() {
      return HasOps$.MODULE$.canMulM_V_Double();
   }

   static BinaryRegistry canMulM_V_Float() {
      return HasOps$.MODULE$.canMulM_V_Float();
   }

   static BinaryRegistry canMulM_V_Int() {
      return HasOps$.MODULE$.canMulM_V_Int();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar();
   }

   static UFunc.UImpl2 csc_csc_OpSub_Long() {
      return HasOps$.MODULE$.csc_csc_OpSub_Long();
   }

   static UFunc.UImpl2 csc_csc_OpSub_Float() {
      return HasOps$.MODULE$.csc_csc_OpSub_Float();
   }

   static UFunc.UImpl2 csc_csc_OpSub_Double() {
      return HasOps$.MODULE$.csc_csc_OpSub_Double();
   }

   static UFunc.UImpl2 csc_csc_OpSub_Int() {
      return HasOps$.MODULE$.csc_csc_OpSub_Int();
   }

   static UFunc.UImpl2 csc_csc_OpMulScalar_Long() {
      return HasOps$.MODULE$.csc_csc_OpMulScalar_Long();
   }

   static UFunc.UImpl2 csc_csc_OpMulScalar_Float() {
      return HasOps$.MODULE$.csc_csc_OpMulScalar_Float();
   }

   static UFunc.UImpl2 csc_csc_OpMulScalar_Double() {
      return HasOps$.MODULE$.csc_csc_OpMulScalar_Double();
   }

   static UFunc.UImpl2 csc_csc_OpMulScalar_Int() {
      return HasOps$.MODULE$.csc_csc_OpMulScalar_Int();
   }

   static UFunc.UImpl2 csc_dm_Semi(final Semiring evidence$12, final ClassTag evidence$13) {
      return HasOps$.MODULE$.csc_dm_Semi(evidence$12, evidence$13);
   }

   static UFunc.UImpl2 dm_csc_OpAdd_Semi(final Semiring evidence$10, final ClassTag evidence$11) {
      return HasOps$.MODULE$.dm_csc_OpAdd_Semi(evidence$10, evidence$11);
   }

   static UFunc.UImpl2 csc_dm_OpSub_Long() {
      return HasOps$.MODULE$.csc_dm_OpSub_Long();
   }

   static UFunc.UImpl2 csc_dm_OpSub_Float() {
      return HasOps$.MODULE$.csc_dm_OpSub_Float();
   }

   static UFunc.UImpl2 csc_dm_OpSub_Double() {
      return HasOps$.MODULE$.csc_dm_OpSub_Double();
   }

   static UFunc.UImpl2 csc_dm_OpSub_Int() {
      return HasOps$.MODULE$.csc_dm_OpSub_Int();
   }

   static UFunc.UImpl2 dm_csc_OpSub_Long() {
      return HasOps$.MODULE$.dm_csc_OpSub_Long();
   }

   static UFunc.UImpl2 dm_csc_OpSub_Float() {
      return HasOps$.MODULE$.dm_csc_OpSub_Float();
   }

   static UFunc.UImpl2 dm_csc_OpSub_Double() {
      return HasOps$.MODULE$.dm_csc_OpSub_Double();
   }

   static UFunc.UImpl2 dm_csc_OpSub_Int() {
      return HasOps$.MODULE$.dm_csc_OpSub_Int();
   }

   static UFunc.UImpl2 dm_csc_OpAdd_Long() {
      return HasOps$.MODULE$.dm_csc_OpAdd_Long();
   }

   static UFunc.UImpl2 dm_csc_OpAdd_Float() {
      return HasOps$.MODULE$.dm_csc_OpAdd_Float();
   }

   static UFunc.UImpl2 dm_csc_OpAdd_Double() {
      return HasOps$.MODULE$.dm_csc_OpAdd_Double();
   }

   static UFunc.UImpl2 dm_csc_OpAdd_Int() {
      return HasOps$.MODULE$.dm_csc_OpAdd_Int();
   }

   static UFunc.UImpl2 csc_dm_OpAdd_Long() {
      return HasOps$.MODULE$.csc_dm_OpAdd_Long();
   }

   static UFunc.UImpl2 csc_dm_OpAdd_Float() {
      return HasOps$.MODULE$.csc_dm_OpAdd_Float();
   }

   static UFunc.UImpl2 csc_dm_OpAdd_Double() {
      return HasOps$.MODULE$.csc_dm_OpAdd_Double();
   }

   static UFunc.UImpl2 csc_dm_OpAdd_Int() {
      return HasOps$.MODULE$.csc_dm_OpAdd_Int();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Long() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSub_Long();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Float() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSub_Float();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Double() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSub_Double();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Int() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSub_Int();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Long() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpAdd_Long();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Float() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpAdd_Float();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Double() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpAdd_Double();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Int() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpAdd_Int();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Long() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSet_Long();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Float() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSet_Float();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Double() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSet_Double();
   }

   static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Int() {
      return HasOps$.MODULE$.dm_csc_InPlace_OpSet_Int();
   }

   static UFunc.UImpl2 csc_csc_OpAdd_Long() {
      return HasOps$.MODULE$.csc_csc_OpAdd_Long();
   }

   static UFunc.UImpl2 csc_csc_OpAdd_Float() {
      return HasOps$.MODULE$.csc_csc_OpAdd_Float();
   }

   static UFunc.UImpl2 csc_csc_OpAdd_Double() {
      return HasOps$.MODULE$.csc_csc_OpAdd_Double();
   }

   static UFunc.UImpl2 csc_csc_OpAdd_Int() {
      return HasOps$.MODULE$.csc_csc_OpAdd_Int();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Long_OpMod() {
      return HasOps$.MODULE$.csc_csc_BadOps_Long_OpMod();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Float_OpMod() {
      return HasOps$.MODULE$.csc_csc_BadOps_Float_OpMod();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Double_OpMod() {
      return HasOps$.MODULE$.csc_csc_BadOps_Double_OpMod();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Int_OpMod() {
      return HasOps$.MODULE$.csc_csc_BadOps_Int_OpMod();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Long_OpDiv() {
      return HasOps$.MODULE$.csc_csc_BadOps_Long_OpDiv();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Float_OpDiv() {
      return HasOps$.MODULE$.csc_csc_BadOps_Float_OpDiv();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Double_OpDiv() {
      return HasOps$.MODULE$.csc_csc_BadOps_Double_OpDiv();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Int_OpDiv() {
      return HasOps$.MODULE$.csc_csc_BadOps_Int_OpDiv();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Long_OpPow() {
      return HasOps$.MODULE$.csc_csc_BadOps_Long_OpPow();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Float_OpPow() {
      return HasOps$.MODULE$.csc_csc_BadOps_Float_OpPow();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Double_OpPow() {
      return HasOps$.MODULE$.csc_csc_BadOps_Double_OpPow();
   }

   static UFunc.UImpl2 csc_csc_BadOps_Int_OpPow() {
      return HasOps$.MODULE$.csc_csc_BadOps_Int_OpPow();
   }

   static UFunc.InPlaceImpl3 cscScaleAdd_Long() {
      return HasOps$.MODULE$.cscScaleAdd_Long();
   }

   static UFunc.InPlaceImpl3 cscScaleAdd_Float() {
      return HasOps$.MODULE$.cscScaleAdd_Float();
   }

   static UFunc.InPlaceImpl3 cscScaleAdd_Double() {
      return HasOps$.MODULE$.cscScaleAdd_Double();
   }

   static UFunc.InPlaceImpl3 cscScaleAdd_Int() {
      return HasOps$.MODULE$.cscScaleAdd_Int();
   }

   static UFunc.UImpl csc_OpNeg_Long() {
      return HasOps$.MODULE$.csc_OpNeg_Long();
   }

   static UFunc.UImpl csc_OpNeg_Float() {
      return HasOps$.MODULE$.csc_OpNeg_Float();
   }

   static UFunc.UImpl csc_OpNeg_Double() {
      return HasOps$.MODULE$.csc_OpNeg_Double();
   }

   static UFunc.UImpl csc_OpNeg_Int() {
      return HasOps$.MODULE$.csc_OpNeg_Int();
   }

   static UFunc.UImpl2 canMul_SVt_CSC_eq_SVt(final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return HasOps$.MODULE$.canMul_SVt_CSC_eq_SVt(op, zero, ct);
   }

   static UFunc.UImpl2 canMul_SV_CSC_eq_CSC(final UFunc.UImpl2 op, final Zero zero) {
      return HasOps$.MODULE$.canMul_SV_CSC_eq_CSC(op, zero);
   }

   static CSCMatrixOps_Ring.FrobeniusCSCProduct$ FrobeniusCSCProduct() {
      return HasOps$.MODULE$.FrobeniusCSCProduct();
   }

   static UFunc.UImpl2 impl_OpSolveMatrixBy_CSC_CSC_eq_CSC() {
      return HasOps$.MODULE$.impl_OpSolveMatrixBy_CSC_CSC_eq_CSC();
   }

   static UFunc.UImpl2 impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD(final UFunc.UImpl2 multMV, final MutableInnerProductVectorSpace ispace) {
      return HasOps$.MODULE$.impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD(multMV, ispace);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpPow(final Field evidence$100, final ClassTag evidence$101) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpPow(evidence$100, evidence$101);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMod(final Field evidence$98, final ClassTag evidence$99) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpMod(evidence$98, evidence$99);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpDiv(final Field evidence$96, final ClassTag evidence$97) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpDiv(evidence$96, evidence$97);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulScalar(final Field evidence$94, final ClassTag evidence$95) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpMulScalar(evidence$94, evidence$95);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpAdd(final Field evidence$92, final ClassTag evidence$93) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpAdd(evidence$92, evidence$93);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSub(final Field evidence$90, final ClassTag evidence$91) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpSub(evidence$90, evidence$91);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSet(final Field evidence$88, final ClassTag evidence$89) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpSet(evidence$88, evidence$89);
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulMatrix(final Field evidence$86, final ClassTag evidence$87) {
      return HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpMulMatrix(evidence$86, evidence$87);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMod(final Field evidence$84, final ClassTag evidence$85) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpMod(evidence$84, evidence$85);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpPow(final Field evidence$82, final ClassTag evidence$83) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpPow(evidence$82, evidence$83);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpDiv(final Field evidence$80, final ClassTag evidence$81) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpDiv(evidence$80, evidence$81);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSet(final Field evidence$78, final ClassTag evidence$79) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(evidence$78, evidence$79);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar(final Field evidence$76, final ClassTag evidence$77) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar(evidence$76, evidence$77);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSub(final Field evidence$74, final ClassTag evidence$75) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpSub(evidence$74, evidence$75);
   }

   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(final Field evidence$72, final ClassTag evidence$73) {
      return HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(evidence$72, evidence$73);
   }

   static UFunc.UImpl2 CSCMatrixCanSetM_M_Semiring(final Semiring evidence$70, final ClassTag evidence$71) {
      return HasOps$.MODULE$.CSCMatrixCanSetM_M_Semiring(evidence$70, evidence$71);
   }

   static UFunc.UImpl2 csc_csc_BadOp_OpPow(final Field evidence$68, final ClassTag evidence$69) {
      return HasOps$.MODULE$.csc_csc_BadOp_OpPow(evidence$68, evidence$69);
   }

   static UFunc.UImpl2 csc_csc_BadOp_OpMod(final Field evidence$66, final ClassTag evidence$67) {
      return HasOps$.MODULE$.csc_csc_BadOp_OpMod(evidence$66, evidence$67);
   }

   static UFunc.UImpl2 csc_csc_BadOp_OpDiv(final Field evidence$64, final ClassTag evidence$65) {
      return HasOps$.MODULE$.csc_csc_BadOp_OpDiv(evidence$64, evidence$65);
   }

   static UFunc.UImpl2 csc_T_Op_OpPow(final Field evidence$62, final ClassTag evidence$63) {
      return HasOps$.MODULE$.csc_T_Op_OpPow(evidence$62, evidence$63);
   }

   static UFunc.UImpl2 csc_T_Op_OpMod(final Field evidence$60, final ClassTag evidence$61) {
      return HasOps$.MODULE$.csc_T_Op_OpMod(evidence$60, evidence$61);
   }

   static UFunc.UImpl2 csc_T_Op_OpDiv(final Field evidence$58, final ClassTag evidence$59) {
      return HasOps$.MODULE$.csc_T_Op_OpDiv(evidence$58, evidence$59);
   }

   static UFunc.UImpl2 CSCMatrixCanSubM_M_Ring(final Ring evidence$55, final Zero evidence$56, final ClassTag evidence$57) {
      return HasOps$.MODULE$.CSCMatrixCanSubM_M_Ring(evidence$55, evidence$56, evidence$57);
   }

   static UFunc.UImpl2 CSCMatrixCanAdd_M_M_Semiring(final Semiring evidence$52, final Zero evidence$53, final ClassTag evidence$54) {
      return HasOps$.MODULE$.CSCMatrixCanAdd_M_M_Semiring(evidence$52, evidence$53, evidence$54);
   }

   static UFunc.UImpl2 CSCMatrixCanMulScalarM_M_Semiring(final Semiring evidence$49, final ClassTag evidence$50, final Zero evidence$51) {
      return HasOps$.MODULE$.CSCMatrixCanMulScalarM_M_Semiring(evidence$49, evidence$50, evidence$51);
   }

   static UFunc.UImpl2 canMulM_S_Ring_OpMulScalar(final Ring evidence$47, final ClassTag evidence$48) {
      return HasOps$.MODULE$.canMulM_S_Ring_OpMulScalar(evidence$47, evidence$48);
   }

   static UFunc.UImpl2 canMulM_S_Ring_OpMulMatrix(final Ring evidence$45, final ClassTag evidence$46) {
      return HasOps$.MODULE$.canMulM_S_Ring_OpMulMatrix(evidence$45, evidence$46);
   }

   static UFunc.UImpl2 canSetM_S_Semiring(final Semiring evidence$43, final ClassTag evidence$44) {
      return HasOps$.MODULE$.canSetM_S_Semiring(evidence$43, evidence$44);
   }

   static UFunc.UImpl2 canSubM_S_Ring(final Ring evidence$41, final ClassTag evidence$42) {
      return HasOps$.MODULE$.canSubM_S_Ring(evidence$41, evidence$42);
   }

   static UFunc.UImpl2 canAddM_S_Semiring(final Semiring evidence$39, final ClassTag evidence$40) {
      return HasOps$.MODULE$.canAddM_S_Semiring(evidence$39, evidence$40);
   }

   static CanZipMapKeyValues zipMapKeyVals(final ClassTag evidence$36, final Semiring evidence$37, final Zero evidence$38) {
      return HasOps$.MODULE$.zipMapKeyVals(evidence$36, evidence$37, evidence$38);
   }

   static CanZipMapValues zipMapVals(final ClassTag evidence$33, final Semiring evidence$34, final Zero evidence$35) {
      return HasOps$.MODULE$.zipMapVals(evidence$33, evidence$34, evidence$35);
   }

   static UFunc.UImpl2 canMulM_M_Semiring(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return HasOps$.MODULE$.canMulM_M_Semiring(evidence$30, evidence$31, evidence$32);
   }

   static UFunc.UImpl2 canMulDM_M_Semiring(final Semiring evidence$27, final Zero evidence$28, final ClassTag evidence$29) {
      return HasOps$.MODULE$.canMulDM_M_Semiring(evidence$27, evidence$28, evidence$29);
   }

   static UFunc.UImpl2 canMulM_DM_Semiring(final Semiring evidence$24, final Zero evidence$25, final ClassTag evidence$26) {
      return HasOps$.MODULE$.canMulM_DM_Semiring(evidence$24, evidence$25, evidence$26);
   }

   static BinaryRegistry canMulM_SV_Semiring(final Semiring evidence$21, final Zero evidence$22, final ClassTag evidence$23) {
      return HasOps$.MODULE$.canMulM_SV_Semiring(evidence$21, evidence$22, evidence$23);
   }

   static BinaryRegistry canMulM_V_Semiring(final Semiring evidence$18, final Zero evidence$19, final ClassTag evidence$20) {
      return HasOps$.MODULE$.canMulM_V_Semiring(evidence$18, evidence$19, evidence$20);
   }

   static UFunc.InPlaceImpl3 cscScaleAdd(final Semiring evidence$16, final ClassTag evidence$17) {
      return HasOps$.MODULE$.cscScaleAdd(evidence$16, evidence$17);
   }

   static UFunc.UImpl csc_OpNeg(final Ring evidence$14, final ClassTag evidence$15) {
      return HasOps$.MODULE$.csc_OpNeg(evidence$14, evidence$15);
   }

   static UFunc.UImpl2 canMulM_M_def(final .less.colon.less bb, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.canMulM_M_def(bb, op);
   }

   static UFunc.UImpl canDim_DM() {
      return HasOps$.MODULE$.canDim_DM();
   }

   static DenseMatrixOps.CanZipMapKeyValuesDenseMatrix zipMapKV_DM(final ClassTag evidence$12) {
      return HasOps$.MODULE$.zipMapKV_DM(evidence$12);
   }

   static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Int() {
      return HasOps$.MODULE$.zipMap_DM_Int();
   }

   static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Float() {
      return HasOps$.MODULE$.zipMap_DM_Float();
   }

   static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Double() {
      return HasOps$.MODULE$.zipMap_DM_Double();
   }

   static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM(final ClassTag evidence$10) {
      return HasOps$.MODULE$.zipMap_DM(evidence$10);
   }

   static CanIterateAxis canIterateRows_DM() {
      return HasOps$.MODULE$.canIterateRows_DM();
   }

   static CanIterateAxis canIterateCols_DM() {
      return HasOps$.MODULE$.canIterateCols_DM();
   }

   static CanTraverseAxis canTraverseRows_DM() {
      return HasOps$.MODULE$.canTraverseRows_DM();
   }

   static CanTraverseAxis canTraverseCols_DM() {
      return HasOps$.MODULE$.canTraverseCols_DM();
   }

   static CanCollapseAxis canMapColsBitVector_DM(final ClassTag evidence$7, final Zero evidence$8) {
      return HasOps$.MODULE$.canMapColsBitVector_DM(evidence$7, evidence$8);
   }

   static CanCollapseAxis.HandHold handholdCanMapCols_DM() {
      return HasOps$.MODULE$.handholdCanMapCols_DM();
   }

   static CanCollapseAxis canMapCols_DM(final ClassTag evidence$5, final Zero evidence$6, final UFunc.InPlaceImpl2 implSet) {
      return HasOps$.MODULE$.canMapCols_DM(evidence$5, evidence$6, implSet);
   }

   static CanCollapseAxis canMapRowsBitVector_DM(final ClassTag evidence$3, final Zero evidence$4) {
      return HasOps$.MODULE$.canMapRowsBitVector_DM(evidence$3, evidence$4);
   }

   static CanCollapseAxis.HandHold handholdCanMapRows_DM() {
      return HasOps$.MODULE$.handholdCanMapRows_DM();
   }

   static CanCollapseAxis canMapRows_DM(final ClassTag evidence$1, final Zero evidence$2, final UFunc.InPlaceImpl2 implSet) {
      return HasOps$.MODULE$.canMapRows_DM(evidence$1, evidence$2, implSet);
   }

   static UFunc.InPlaceImpl2 setMV_I() {
      return HasOps$.MODULE$.setMV_I();
   }

   static UFunc.InPlaceImpl2 setMV_F() {
      return HasOps$.MODULE$.setMV_F();
   }

   static UFunc.InPlaceImpl2 setMV_D() {
      return HasOps$.MODULE$.setMV_D();
   }

   static DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$ impl_OpSolveMatrixBy_DMF_DVF_eq_DVF() {
      return HasOps$.MODULE$.impl_OpSolveMatrixBy_DMF_DVF_eq_DVF();
   }

   static DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$ impl_OpSolveMatrixBy_DMF_DMF_eq_DMF() {
      return HasOps$.MODULE$.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DVF_DMF_eq_DMF() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DVF_DMF_eq_DMF();
   }

   static DenseMatrixOps_FloatSpecialized.impl_OpMulMatrix_DMF_DVF_eq_DVF$ impl_OpMulMatrix_DMF_DVF_eq_DVF() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DMF_DVF_eq_DVF();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Float() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_DM_eq_DM_Float();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Long_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Float_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Double_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpNe() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Int_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Long_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Float_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Double_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpEq() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Int_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Long_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Float_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Double_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Int_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Long_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Float_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Double_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Int_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Long_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Float_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Double_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Int_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Long_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Float_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Double_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGT() {
      return HasOps$.MODULE$.impl_Op_DM_DM_eq_DMBool_Int_OpGT();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_HV_eq_T(final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.impl_OpMulInner_SV_HV_eq_T(op);
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Long() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_HV_eq_SV_Long();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Float() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_HV_eq_SV_Float();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Double() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_HV_eq_SV_Double();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Int() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_HV_eq_SV_Int();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_SV_eq_S_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_SV_eq_S_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_SV_eq_S_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_SV_eq_S_Int();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Long() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_SV_Long();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Float() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_SV_Float();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Double() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_SV_Double();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Int() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_SV_Int();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Long() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_SV_eq_HV_Long();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Float() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_SV_eq_HV_Float();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Double() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_SV_eq_HV_Double();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Int() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_SV_eq_HV_Int();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_DV_eq_T_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_DV_eq_T_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_DV_eq_T_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_DV_eq_T_Int();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_DV_eq_HV_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_DV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_HV_eq_S_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_HV_eq_S_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_HV_eq_S_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_HV_eq_S_Int();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Long() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_HV_Long();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Float() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_HV_Float();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Double() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_HV_Double();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Int() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_HV_Int();
   }

   static CanTraverseValues impl_CanTraverseValues_HV_Generic() {
      return HasOps$.MODULE$.impl_CanTraverseValues_HV_Generic();
   }

   static CanTraverseValues impl_CanTraverseValues_HV_Long() {
      return HasOps$.MODULE$.impl_CanTraverseValues_HV_Long();
   }

   static CanTraverseValues impl_CanTraverseValues_HV_Float() {
      return HasOps$.MODULE$.impl_CanTraverseValues_HV_Float();
   }

   static CanTraverseValues impl_CanTraverseValues_HV_Double() {
      return HasOps$.MODULE$.impl_CanTraverseValues_HV_Double();
   }

   static CanTraverseValues impl_CanTraverseValues_HV_Int() {
      return HasOps$.MODULE$.impl_CanTraverseValues_HV_Int();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_HV_eq_S_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_HV_eq_S_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_HV_eq_S_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_HV_HV_eq_S_Int();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Long() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_S_Long();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Float() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_S_Float();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Double() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_S_Double();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Int() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_S_Int();
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Long() {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Long();
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Float() {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Float();
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Double() {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Double();
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Int() {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Int();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_HV_S_idempotent_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Long() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_HV_Long();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Float() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_HV_Float();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Double() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_HV_Double();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Int() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_HV_Int();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_S_eq_HV_add_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_V_eq_HV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Long() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_HV_eq_HV_Long();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Float() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_HV_eq_HV_Float();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Double() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_HV_eq_HV_Double();
   }

   static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Int() {
      return HasOps$.MODULE$.impl_OpMulScalar_HV_HV_eq_HV_Int();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_HV_HV_eq_HV_Int_OpAdd();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Long() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_HV_Long();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Float() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_HV_Float();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Double() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_HV_Double();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Int() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_S_HV_Int();
   }

   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_HV_S_Generic(final UFunc.UImpl2 pow) {
      return HasOps$.MODULE$.impl_OpPow_InPlace_HV_S_Generic(pow);
   }

   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_S_Generic(final Field field) {
      return HasOps$.MODULE$.impl_OpDiv_InPlace_HV_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_HV_Generic(final Field field) {
      return HasOps$.MODULE$.impl_OpDiv_InPlace_HV_HV_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_HV_Generic(final Field field, final ClassTag ct) {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_HV_Generic(field, ct);
   }

   static HashVector_GenericOps.CanZipMapKeyValuesHashVector HV_zipMapKV(final ClassTag evidence$22, final Zero evidence$23) {
      return HasOps$.MODULE$.HV_zipMapKV(evidence$22, evidence$23);
   }

   static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_i() {
      return HasOps$.MODULE$.HV_zipMap_i();
   }

   static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_f() {
      return HasOps$.MODULE$.HV_zipMap_f();
   }

   static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_d() {
      return HasOps$.MODULE$.HV_zipMap_d();
   }

   static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap(final ClassTag evidence$18, final Zero evidence$19) {
      return HasOps$.MODULE$.HV_zipMap(evidence$18, evidence$19);
   }

   static UFunc.UImpl2 impl_OpSub_HV_S_eq_HV_Generic(final Ring ring) {
      return HasOps$.MODULE$.impl_OpSub_HV_S_eq_HV_Generic(ring);
   }

   static UFunc.UImpl2 impl_OpAdd_HV_S_eq_HV_Generic(final Semiring semi) {
      return HasOps$.MODULE$.impl_OpAdd_HV_S_eq_HV_Generic(semi);
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_V_HV(final Semiring evidence$15) {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_V_HV(evidence$15);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Generic() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_HV_Generic();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_V_Generic() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_V_Generic();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Generic() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_HV_S_Generic();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Complex() {
      return HasOps$.MODULE$.impl_OpMulMatrix_SV_DVt_eq_SMT_Complex();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Long() {
      return HasOps$.MODULE$.impl_OpMulMatrix_SV_DVt_eq_SMT_Long();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Float() {
      return HasOps$.MODULE$.impl_OpMulMatrix_SV_DVt_eq_SMT_Float();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Double() {
      return HasOps$.MODULE$.impl_OpMulMatrix_SV_DVt_eq_SMT_Double();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Int() {
      return HasOps$.MODULE$.impl_OpMulMatrix_SV_DVt_eq_SMT_Int();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_DV_eq_T_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_DV_eq_T_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_DV_eq_T_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_DV_eq_T_Int();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_DV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_eq_SV_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_DV_InPlace_Int_OpAdd();
   }

   static CanTraverseValues canIterateValues_SV() {
      return HasOps$.MODULE$.canIterateValues_SV();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Complex() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DV_SVt_eq_CSC_Complex();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Long() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DV_SVt_eq_CSC_Long();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Float() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DV_SVt_eq_CSC_Float();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Double() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DV_SVt_eq_CSC_Double();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Int() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DV_SVt_eq_CSC_Int();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Long() {
      return HasOps$.MODULE$.implScaleAdd_DV_S_SV_InPlace_Long();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Float() {
      return HasOps$.MODULE$.implScaleAdd_DV_S_SV_InPlace_Float();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Double() {
      return HasOps$.MODULE$.implScaleAdd_DV_S_SV_InPlace_Double();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Int() {
      return HasOps$.MODULE$.implScaleAdd_DV_S_SV_InPlace_Int();
   }

   static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Long() {
      return HasOps$.MODULE$.impl_zipValues_DV_SV_eq_ZV_Long();
   }

   static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Float() {
      return HasOps$.MODULE$.impl_zipValues_DV_SV_eq_ZV_Float();
   }

   static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Double() {
      return HasOps$.MODULE$.impl_zipValues_DV_SV_eq_ZV_Double();
   }

   static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Int() {
      return HasOps$.MODULE$.impl_zipValues_DV_SV_eq_ZV_Int();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_SV_eq_T_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_SV_eq_T_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_SV_eq_T_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_SV_eq_T_Int();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_eq_SV_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_SV_InPlace_Int_OpMulScalar();
   }

   static SparseVectorExpandOps.CanZipMapKeyValuesSparseVector zipMapKV(final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return HasOps$.MODULE$.zipMapKV(evidence$17, evidence$18, evidence$19);
   }

   static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_i() {
      return HasOps$.MODULE$.zipMap_i();
   }

   static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_f() {
      return HasOps$.MODULE$.zipMap_f();
   }

   static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_d() {
      return HasOps$.MODULE$.zipMap_d();
   }

   static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap(final ClassTag evidence$11, final Zero evidence$12, final Semiring evidence$13) {
      return HasOps$.MODULE$.zipMap(evidence$11, evidence$12, evidence$13);
   }

   static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Long() {
      return HasOps$.MODULE$.implScaleAdd_SV_S_SV_InPlace_Long();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Float() {
      return HasOps$.MODULE$.implScaleAdd_SV_S_SV_InPlace_Float();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Double() {
      return HasOps$.MODULE$.implScaleAdd_SV_S_SV_InPlace_Double();
   }

   static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Int() {
      return HasOps$.MODULE$.implScaleAdd_SV_S_SV_InPlace_Int();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T(final ClassTag evidence$5, final Zero evidence$6, final Semiring evidence$7) {
      return HasOps$.MODULE$.impl_OpMulInner_SV_SV_eq_T(evidence$5, evidence$6, evidence$7);
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_SV_eq_T_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_SV_eq_T_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_SV_eq_T_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_SV_SV_eq_T_Int();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_V_eq_SV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Long() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_SV_eq_SV_Long();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Float() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_SV_eq_SV_Float();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Double() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_SV_eq_SV_Double();
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Int() {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_SV_eq_SV_Int();
   }

   static UFunc.UImpl2 implAddOp_SV_SV_eq_SV(final Semiring evidence$3, final ClassTag evidence$4) {
      return HasOps$.MODULE$.implAddOp_SV_SV_eq_SV(evidence$3, evidence$4);
   }

   static UFunc.UImpl2 implSubOp_SV_SV_eq_SV(final Ring evidence$1, final ClassTag evidence$2) {
      return HasOps$.MODULE$.implSubOp_SV_SV_eq_SV(evidence$1, evidence$2);
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Int_OpAdd();
   }

   static UFunc.UImpl2 liftCSCOpToSVransposeOp(final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return HasOps$.MODULE$.liftCSCOpToSVransposeOp(op, zero, ct);
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic(final UFunc.UImpl2 op, final Semiring semiring) {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Generic(op, semiring);
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_SV_S_SV_InPlace_Generic(final Semiring evidence$12, final ClassTag evidence$13) {
      return HasOps$.MODULE$.impl_scaleAdd_SV_S_SV_InPlace_Generic(evidence$12, evidence$13);
   }

   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic_OpMulScalar(final Semiring ring) {
      return HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Generic_OpMulScalar(ring);
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic_OpMulMatrix(final Semiring evidence$10, final ClassTag evidence$11) {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Generic_OpMulMatrix(evidence$10, evidence$11);
   }

   static UFunc.UImpl2 impl_OpMulScalar_SV_S_eq_SV_Generic(final Semiring evidence$8, final ClassTag evidence$9) {
      return HasOps$.MODULE$.impl_OpMulScalar_SV_S_eq_SV_Generic(evidence$8, evidence$9);
   }

   static UFunc.UImpl2 impl_OpSub_SV_S_eq_SV_Generic(final Ring evidence$7) {
      return HasOps$.MODULE$.impl_OpSub_SV_S_eq_SV_Generic(evidence$7);
   }

   static UFunc.UImpl2 impl_OpAdd_SV_S_eq_SV_Generic(final Semiring evidence$6) {
      return HasOps$.MODULE$.impl_OpAdd_SV_S_eq_SV_Generic(evidence$6);
   }

   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic(final Semiring evidence$5, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Generic(evidence$5, op);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_S_Generic(final Zero evidence$4) {
      return HasOps$.MODULE$.impl_OpSet_InPlace_SV_S_Generic(evidence$4);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_SV_Generic() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_SV_SV_Generic();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Double() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_SV_eq_DV_Double();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Long() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_SV_eq_DV_Long();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Float() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_SV_eq_DV_Float();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Int() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_SV_eq_DV_Int();
   }

   static DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$ impl_OpSolveMatrixBy_DMD_DVD_eq_DVD() {
      return HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD();
   }

   static DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$ impl_OpSolveMatrixBy_DMD_DMD_eq_DMD() {
      return HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DVD_DMD_eq_DMD() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DVD_DMD_eq_DMD();
   }

   static DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DVD_eq_DVD$ impl_OpMulMatrix_DMD_DVD_eq_DVD() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD();
   }

   static DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DMD_eq_DMD$ impl_OpMulMatrix_DMD_DMD_eq_DMD() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DVT_DMT_eq_DMT(final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.impl_OpMulMatrix_DVT_DMT_eq_DMT(op);
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DVTt_DMT_eq_DMT(final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.impl_OpMulMatrix_DVTt_DMT_eq_DMT(op);
   }

   static UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_S() {
      return HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_S();
   }

   static UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_DM() {
      return HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM();
   }

   static CanSlice2 canSlicePartOfRow() {
      return HasOps$.MODULE$.canSlicePartOfRow();
   }

   static CanSlice2 canSlicePartOfCol() {
      return HasOps$.MODULE$.canSlicePartOfCol();
   }

   static CanSlice2 canSliceColsAndRows() {
      return HasOps$.MODULE$.canSliceColsAndRows();
   }

   static CanSlice2 canSliceCols() {
      return HasOps$.MODULE$.canSliceCols();
   }

   static CanSlice2 canSliceRows() {
      return HasOps$.MODULE$.canSliceRows();
   }

   static CanSlice2 canSliceRow() {
      return HasOps$.MODULE$.canSliceRow();
   }

   static CanSlice2 canSliceCol() {
      return HasOps$.MODULE$.canSliceCol();
   }

   static CanSlice2 canSliceTensorBooleanCols(final Semiring evidence$7, final ClassTag evidence$8) {
      return HasOps$.MODULE$.canSliceTensorBooleanCols(evidence$7, evidence$8);
   }

   static CanSlice2 canSliceTensorBooleanRows(final Semiring evidence$5, final ClassTag evidence$6) {
      return HasOps$.MODULE$.canSliceTensorBooleanRows(evidence$5, evidence$6);
   }

   static CanSlice2 canSliceWeirdCols(final Semiring evidence$3, final ClassTag evidence$4) {
      return HasOps$.MODULE$.canSliceWeirdCols(evidence$3, evidence$4);
   }

   static CanSlice2 canSliceWeirdRows(final Semiring evidence$1, final ClassTag evidence$2) {
      return HasOps$.MODULE$.canSliceWeirdRows(evidence$1, evidence$2);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_DM_M();
   }

   static CanCollapseAxis canCollapseCols_DM(final ClassTag evidence$74) {
      return HasOps$.MODULE$.canCollapseCols_DM(evidence$74);
   }

   static CanCollapseAxis canCollapseRows_DM(final ClassTag evidence$73) {
      return HasOps$.MODULE$.canCollapseRows_DM(evidence$73);
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Long() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_DM_eq_DM_Long();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Int() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_DM_eq_DM_Int();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Double() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Double();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Float() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Float();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Long() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Long();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Int() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Int();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Double() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Double();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Float() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Float();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Long() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Long();
   }

   static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Int() {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Int();
   }

   static UFunc.UImpl2 op_DM_DM_Double_OpPow() {
      return HasOps$.MODULE$.op_DM_DM_Double_OpPow();
   }

   static UFunc.UImpl2 op_DM_DM_Float_OpPow() {
      return HasOps$.MODULE$.op_DM_DM_Float_OpPow();
   }

   static UFunc.UImpl2 op_DM_DM_Long_OpPow() {
      return HasOps$.MODULE$.op_DM_DM_Long_OpPow();
   }

   static UFunc.UImpl2 op_DM_DM_Int_OpPow() {
      return HasOps$.MODULE$.op_DM_DM_Int_OpPow();
   }

   static UFunc.UImpl2 op_DM_DM_Double_OpDiv() {
      return HasOps$.MODULE$.op_DM_DM_Double_OpDiv();
   }

   static UFunc.UImpl2 op_DM_DM_Float_OpDiv() {
      return HasOps$.MODULE$.op_DM_DM_Float_OpDiv();
   }

   static UFunc.UImpl2 op_DM_DM_Long_OpDiv() {
      return HasOps$.MODULE$.op_DM_DM_Long_OpDiv();
   }

   static UFunc.UImpl2 op_DM_DM_Int_OpDiv() {
      return HasOps$.MODULE$.op_DM_DM_Int_OpDiv();
   }

   static UFunc.UImpl2 op_DM_DM_Double_OpMod() {
      return HasOps$.MODULE$.op_DM_DM_Double_OpMod();
   }

   static UFunc.UImpl2 op_DM_DM_Float_OpMod() {
      return HasOps$.MODULE$.op_DM_DM_Float_OpMod();
   }

   static UFunc.UImpl2 op_DM_DM_Long_OpMod() {
      return HasOps$.MODULE$.op_DM_DM_Long_OpMod();
   }

   static UFunc.UImpl2 op_DM_DM_Int_OpMod() {
      return HasOps$.MODULE$.op_DM_DM_Int_OpMod();
   }

   static UFunc.UImpl2 op_DM_DM_Double_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_DM_Double_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_DM_Float_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_DM_Float_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_DM_Long_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_DM_Long_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_DM_Int_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_DM_Int_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_DM_Double_OpSub() {
      return HasOps$.MODULE$.op_DM_DM_Double_OpSub();
   }

   static UFunc.UImpl2 op_DM_DM_Float_OpSub() {
      return HasOps$.MODULE$.op_DM_DM_Float_OpSub();
   }

   static UFunc.UImpl2 op_DM_DM_Long_OpSub() {
      return HasOps$.MODULE$.op_DM_DM_Long_OpSub();
   }

   static UFunc.UImpl2 op_DM_DM_Int_OpSub() {
      return HasOps$.MODULE$.op_DM_DM_Int_OpSub();
   }

   static UFunc.UImpl2 op_DM_DM_Double_OpAdd() {
      return HasOps$.MODULE$.op_DM_DM_Double_OpAdd();
   }

   static UFunc.UImpl2 op_DM_DM_Float_OpAdd() {
      return HasOps$.MODULE$.op_DM_DM_Float_OpAdd();
   }

   static UFunc.UImpl2 op_DM_DM_Long_OpAdd() {
      return HasOps$.MODULE$.op_DM_DM_Long_OpAdd();
   }

   static UFunc.UImpl2 op_DM_DM_Int_OpAdd() {
      return HasOps$.MODULE$.op_DM_DM_Int_OpAdd();
   }

   static UFunc.UImpl2 s_dm_op(final UFunc.UImpl2 opScalar, final ClassTag ct, final Zero zero) {
      return HasOps$.MODULE$.s_dm_op(opScalar, ct, zero);
   }

   static UFunc.UImpl2 s_dm_op_Long_OpPow() {
      return HasOps$.MODULE$.s_dm_op_Long_OpPow();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpPow() {
      return HasOps$.MODULE$.s_dm_op_Float_OpPow();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpPow() {
      return HasOps$.MODULE$.s_dm_op_Double_OpPow();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpPow() {
      return HasOps$.MODULE$.s_dm_op_Int_OpPow();
   }

   static UFunc.UImpl2 s_dm_op_Long_OpMod() {
      return HasOps$.MODULE$.s_dm_op_Long_OpMod();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpMod() {
      return HasOps$.MODULE$.s_dm_op_Float_OpMod();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpMod() {
      return HasOps$.MODULE$.s_dm_op_Double_OpMod();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpMod() {
      return HasOps$.MODULE$.s_dm_op_Int_OpMod();
   }

   static UFunc.UImpl2 s_dm_op_Long_OpDiv() {
      return HasOps$.MODULE$.s_dm_op_Long_OpDiv();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpDiv() {
      return HasOps$.MODULE$.s_dm_op_Float_OpDiv();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpDiv() {
      return HasOps$.MODULE$.s_dm_op_Double_OpDiv();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpDiv() {
      return HasOps$.MODULE$.s_dm_op_Int_OpDiv();
   }

   static UFunc.UImpl2 s_dm_op_Long_OpMulMatrix() {
      return HasOps$.MODULE$.s_dm_op_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpMulMatrix() {
      return HasOps$.MODULE$.s_dm_op_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpMulMatrix() {
      return HasOps$.MODULE$.s_dm_op_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpMulMatrix() {
      return HasOps$.MODULE$.s_dm_op_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 s_dm_op_Long_OpMulScalar() {
      return HasOps$.MODULE$.s_dm_op_Long_OpMulScalar();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpMulScalar() {
      return HasOps$.MODULE$.s_dm_op_Float_OpMulScalar();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpMulScalar() {
      return HasOps$.MODULE$.s_dm_op_Double_OpMulScalar();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpMulScalar() {
      return HasOps$.MODULE$.s_dm_op_Int_OpMulScalar();
   }

   static UFunc.UImpl2 s_dm_op_Long_OpSub() {
      return HasOps$.MODULE$.s_dm_op_Long_OpSub();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpSub() {
      return HasOps$.MODULE$.s_dm_op_Float_OpSub();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpSub() {
      return HasOps$.MODULE$.s_dm_op_Double_OpSub();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpSub() {
      return HasOps$.MODULE$.s_dm_op_Int_OpSub();
   }

   static UFunc.UImpl2 s_dm_op_Long_OpAdd() {
      return HasOps$.MODULE$.s_dm_op_Long_OpAdd();
   }

   static UFunc.UImpl2 s_dm_op_Float_OpAdd() {
      return HasOps$.MODULE$.s_dm_op_Float_OpAdd();
   }

   static UFunc.UImpl2 s_dm_op_Double_OpAdd() {
      return HasOps$.MODULE$.s_dm_op_Double_OpAdd();
   }

   static UFunc.UImpl2 s_dm_op_Int_OpAdd() {
      return HasOps$.MODULE$.s_dm_op_Int_OpAdd();
   }

   static UFunc.UImpl2 op_DM_S_OpPow(final Field evidence$70, final Zero evidence$71, final ClassTag evidence$72) {
      return HasOps$.MODULE$.op_DM_S_OpPow(evidence$70, evidence$71, evidence$72);
   }

   static UFunc.UImpl2 op_DM_S_OpMod(final Field evidence$67, final Zero evidence$68, final ClassTag evidence$69) {
      return HasOps$.MODULE$.op_DM_S_OpMod(evidence$67, evidence$68, evidence$69);
   }

   static UFunc.UImpl2 op_DM_S_OpDiv(final Field evidence$64, final Zero evidence$65, final ClassTag evidence$66) {
      return HasOps$.MODULE$.op_DM_S_OpDiv(evidence$64, evidence$65, evidence$66);
   }

   static UFunc.UImpl2 op_DM_S_OpMulMatrix(final Field evidence$61, final Zero evidence$62, final ClassTag evidence$63) {
      return HasOps$.MODULE$.op_DM_S_OpMulMatrix(evidence$61, evidence$62, evidence$63);
   }

   static UFunc.UImpl2 op_DM_S_OpMulScalar(final Field evidence$58, final Zero evidence$59, final ClassTag evidence$60) {
      return HasOps$.MODULE$.op_DM_S_OpMulScalar(evidence$58, evidence$59, evidence$60);
   }

   static UFunc.UImpl2 op_DM_S_OpSub(final Field evidence$55, final Zero evidence$56, final ClassTag evidence$57) {
      return HasOps$.MODULE$.op_DM_S_OpSub(evidence$55, evidence$56, evidence$57);
   }

   static UFunc.UImpl2 op_DM_S_OpAdd(final Field evidence$52, final Zero evidence$53, final ClassTag evidence$54) {
      return HasOps$.MODULE$.op_DM_S_OpAdd(evidence$52, evidence$53, evidence$54);
   }

   static UFunc.UImpl2 op_DM_S_Double_OpPow() {
      return HasOps$.MODULE$.op_DM_S_Double_OpPow();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpPow() {
      return HasOps$.MODULE$.op_DM_S_Float_OpPow();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpPow() {
      return HasOps$.MODULE$.op_DM_S_Long_OpPow();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpPow() {
      return HasOps$.MODULE$.op_DM_S_Int_OpPow();
   }

   static UFunc.UImpl2 op_DM_S_Double_OpDiv() {
      return HasOps$.MODULE$.op_DM_S_Double_OpDiv();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpDiv() {
      return HasOps$.MODULE$.op_DM_S_Float_OpDiv();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpDiv() {
      return HasOps$.MODULE$.op_DM_S_Long_OpDiv();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpDiv() {
      return HasOps$.MODULE$.op_DM_S_Int_OpDiv();
   }

   static UFunc.UImpl2 op_DM_S_Double_OpMod() {
      return HasOps$.MODULE$.op_DM_S_Double_OpMod();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpMod() {
      return HasOps$.MODULE$.op_DM_S_Float_OpMod();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpMod() {
      return HasOps$.MODULE$.op_DM_S_Long_OpMod();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpMod() {
      return HasOps$.MODULE$.op_DM_S_Int_OpMod();
   }

   static UFunc.UImpl2 op_DM_S_Double_OpMulMatrix() {
      return HasOps$.MODULE$.op_DM_S_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpMulMatrix() {
      return HasOps$.MODULE$.op_DM_S_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpMulMatrix() {
      return HasOps$.MODULE$.op_DM_S_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpMulMatrix() {
      return HasOps$.MODULE$.op_DM_S_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 op_DM_S_Double_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_S_Double_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_S_Float_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_S_Long_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpMulScalar() {
      return HasOps$.MODULE$.op_DM_S_Int_OpMulScalar();
   }

   static UFunc.UImpl2 op_DM_S_Double_OpSub() {
      return HasOps$.MODULE$.op_DM_S_Double_OpSub();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpSub() {
      return HasOps$.MODULE$.op_DM_S_Float_OpSub();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpSub() {
      return HasOps$.MODULE$.op_DM_S_Long_OpSub();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpSub() {
      return HasOps$.MODULE$.op_DM_S_Int_OpSub();
   }

   static UFunc.UImpl2 op_DM_S_Double_OpAdd() {
      return HasOps$.MODULE$.op_DM_S_Double_OpAdd();
   }

   static UFunc.UImpl2 op_DM_S_Float_OpAdd() {
      return HasOps$.MODULE$.op_DM_S_Float_OpAdd();
   }

   static UFunc.UImpl2 op_DM_S_Long_OpAdd() {
      return HasOps$.MODULE$.op_DM_S_Long_OpAdd();
   }

   static UFunc.UImpl2 op_DM_S_Int_OpAdd() {
      return HasOps$.MODULE$.op_DM_S_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpPow(final Field evidence$49, final Zero evidence$50, final ClassTag evidence$51) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpPow(evidence$49, evidence$50, evidence$51);
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpMod(final Field evidence$46, final Zero evidence$47, final ClassTag evidence$48) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpMod(evidence$46, evidence$47, evidence$48);
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpDiv(final Field evidence$43, final Zero evidence$44, final ClassTag evidence$45) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpDiv(evidence$43, evidence$44, evidence$45);
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpMulMatrix(final Field evidence$40, final Zero evidence$41, final ClassTag evidence$42) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpMulMatrix(evidence$40, evidence$41, evidence$42);
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpMulScalar(final Field evidence$37, final Zero evidence$38, final ClassTag evidence$39) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpMulScalar(evidence$37, evidence$38, evidence$39);
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpSub(final Field evidence$34, final Zero evidence$35, final ClassTag evidence$36) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpSub(evidence$34, evidence$35, evidence$36);
   }

   static UFunc.InPlaceImpl2 opUpdate_DM_S_OpAdd(final Field evidence$31, final Zero evidence$32, final ClassTag evidence$33) {
      return HasOps$.MODULE$.opUpdate_DM_S_OpAdd(evidence$31, evidence$32, evidence$33);
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpPow() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpPow() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpPow() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpPow() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMod() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMod() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMod() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMod() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpSet() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpSet() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpSet() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpSet() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpDiv() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpDiv() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpDiv() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpDiv() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMulMatrix() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMulMatrix() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMulMatrix() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMulMatrix() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMulScalar() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMulScalar() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMulScalar() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMulScalar() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpSub() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpSub() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpSub() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpSub() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpAdd() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpAdd() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpAdd() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpAdd() {
      return HasOps$.MODULE$.dm_s_UpdateOp_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpPow(final Field evidence$28, final Zero evidence$29, final ClassTag evidence$30) {
      return HasOps$.MODULE$.dm_dm_UpdateOp_OpPow(evidence$28, evidence$29, evidence$30);
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpMod(final Field evidence$25, final Zero evidence$26, final ClassTag evidence$27) {
      return HasOps$.MODULE$.dm_dm_UpdateOp_OpMod(evidence$25, evidence$26, evidence$27);
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpDiv(final Field evidence$22, final Zero evidence$23, final ClassTag evidence$24) {
      return HasOps$.MODULE$.dm_dm_UpdateOp_OpDiv(evidence$22, evidence$23, evidence$24);
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpMulScalar(final Field evidence$19, final Zero evidence$20, final ClassTag evidence$21) {
      return HasOps$.MODULE$.dm_dm_UpdateOp_OpMulScalar(evidence$19, evidence$20, evidence$21);
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpSub(final Field evidence$16, final Zero evidence$17, final ClassTag evidence$18) {
      return HasOps$.MODULE$.dm_dm_UpdateOp_OpSub(evidence$16, evidence$17, evidence$18);
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpAdd(final Field evidence$13, final Zero evidence$14, final ClassTag evidence$15) {
      return HasOps$.MODULE$.dm_dm_UpdateOp_OpAdd(evidence$13, evidence$14, evidence$15);
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpPow() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpPow() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpPow() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpPow() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpMod() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpMod() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpMod() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpMod() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpSet() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpSet() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpSet() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpSet() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpDiv() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpDiv() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpDiv() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpDiv() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpMulScalar() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpMulScalar() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpMulScalar() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpMulScalar() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpSub() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpSub() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpSub() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpSub() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpAdd() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpAdd() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpAdd() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpAdd() {
      return HasOps$.MODULE$.dm_dm_UpdateOp_Int_OpAdd();
   }

   static CanCopy canCopy_DM(final ClassTag evidence$8) {
      return HasOps$.MODULE$.canCopy_DM(evidence$8);
   }

   static CanMapValues canMapValues_DM(final ClassTag evidence$7) {
      return HasOps$.MODULE$.canMapValues_DM(evidence$7);
   }

   static CanMapKeyValuePairs canMapKeyValuePairs_DM(final ClassTag evidence$6) {
      return HasOps$.MODULE$.canMapKeyValuePairs_DM(evidence$6);
   }

   static CanTransformValues canTransformValues_DM() {
      return HasOps$.MODULE$.canTransformValues_DM();
   }

   static CanTraverseKeyValuePairs canTraverseKeyValuePairs_DM() {
      return HasOps$.MODULE$.canTraverseKeyValuePairs_DM();
   }

   static CanTraverseValues canTraverseValues() {
      return HasOps$.MODULE$.canTraverseValues();
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_V_eq_DV_Generic(final Semiring ring) {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Generic(ring);
   }

   static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Generic(final Semiring evidence$2) {
      return HasOps$.MODULE$.impl_OpMulMatrix_DM_DM_eq_DM_Generic(evidence$2);
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DM_T_DM(final Semiring evidence$1) {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DM_T_DM(evidence$1);
   }

   static UFunc.UImpl2 op_M_M_Semiring(final Semiring evidence$101, final Zero evidence$102, final ClassTag evidence$103) {
      return HasOps$.MODULE$.op_M_M_Semiring(evidence$101, evidence$102, evidence$103);
   }

   static BinaryRegistry op_M_M_Complex() {
      return HasOps$.MODULE$.op_M_M_Complex();
   }

   static BinaryRegistry op_M_M_BigInt() {
      return HasOps$.MODULE$.op_M_M_BigInt();
   }

   static BinaryRegistry op_M_M_Double() {
      return HasOps$.MODULE$.op_M_M_Double();
   }

   static BinaryRegistry op_M_M_Float() {
      return HasOps$.MODULE$.op_M_M_Float();
   }

   static BinaryRegistry op_M_M_Long() {
      return HasOps$.MODULE$.op_M_M_Long();
   }

   static BinaryRegistry op_M_M_Int() {
      return HasOps$.MODULE$.op_M_M_Int();
   }

   static UFunc.UImpl2 op_M_V_Semiring(final Semiring evidence$98, final Zero evidence$99, final ClassTag evidence$100) {
      return HasOps$.MODULE$.op_M_V_Semiring(evidence$98, evidence$99, evidence$100);
   }

   static BinaryRegistry op_M_V_Complex() {
      return HasOps$.MODULE$.op_M_V_Complex();
   }

   static BinaryRegistry op_M_V_BigInt() {
      return HasOps$.MODULE$.op_M_V_BigInt();
   }

   static BinaryRegistry op_M_V_Double() {
      return HasOps$.MODULE$.op_M_V_Double();
   }

   static BinaryRegistry op_M_V_Float() {
      return HasOps$.MODULE$.op_M_V_Float();
   }

   static BinaryRegistry op_M_V_Long() {
      return HasOps$.MODULE$.op_M_V_Long();
   }

   static BinaryRegistry op_M_V_Int() {
      return HasOps$.MODULE$.op_M_V_Int();
   }

   static BinaryRegistry op_M_DM_Complex_OpPow() {
      return HasOps$.MODULE$.op_M_DM_Complex_OpPow();
   }

   static BinaryRegistry op_M_DM_Double_OpPow() {
      return HasOps$.MODULE$.op_M_DM_Double_OpPow();
   }

   static BinaryRegistry op_M_DM_Float_OpPow() {
      return HasOps$.MODULE$.op_M_DM_Float_OpPow();
   }

   static BinaryRegistry op_M_DM_Long_OpPow() {
      return HasOps$.MODULE$.op_M_DM_Long_OpPow();
   }

   static BinaryRegistry op_M_DM_Int_OpPow() {
      return HasOps$.MODULE$.op_M_DM_Int_OpPow();
   }

   static BinaryRegistry op_M_DM_Complex_OpDiv() {
      return HasOps$.MODULE$.op_M_DM_Complex_OpDiv();
   }

   static BinaryRegistry op_M_DM_BigInt_OpDiv() {
      return HasOps$.MODULE$.op_M_DM_BigInt_OpDiv();
   }

   static BinaryRegistry op_M_DM_Double_OpDiv() {
      return HasOps$.MODULE$.op_M_DM_Double_OpDiv();
   }

   static BinaryRegistry op_M_DM_Float_OpDiv() {
      return HasOps$.MODULE$.op_M_DM_Float_OpDiv();
   }

   static BinaryRegistry op_M_DM_Long_OpDiv() {
      return HasOps$.MODULE$.op_M_DM_Long_OpDiv();
   }

   static BinaryRegistry op_M_DM_Int_OpDiv() {
      return HasOps$.MODULE$.op_M_DM_Int_OpDiv();
   }

   static BinaryRegistry op_M_DM_BigInt_OpMod() {
      return HasOps$.MODULE$.op_M_DM_BigInt_OpMod();
   }

   static BinaryRegistry op_M_DM_Double_OpMod() {
      return HasOps$.MODULE$.op_M_DM_Double_OpMod();
   }

   static BinaryRegistry op_M_DM_Float_OpMod() {
      return HasOps$.MODULE$.op_M_DM_Float_OpMod();
   }

   static BinaryRegistry op_M_DM_Long_OpMod() {
      return HasOps$.MODULE$.op_M_DM_Long_OpMod();
   }

   static BinaryRegistry op_M_DM_Int_OpMod() {
      return HasOps$.MODULE$.op_M_DM_Int_OpMod();
   }

   static BinaryRegistry op_M_DM_Complex_OpMulScalar() {
      return HasOps$.MODULE$.op_M_DM_Complex_OpMulScalar();
   }

   static BinaryRegistry op_M_DM_BigInt_OpMulScalar() {
      return HasOps$.MODULE$.op_M_DM_BigInt_OpMulScalar();
   }

   static BinaryRegistry op_M_DM_Double_OpMulScalar() {
      return HasOps$.MODULE$.op_M_DM_Double_OpMulScalar();
   }

   static BinaryRegistry op_M_DM_Float_OpMulScalar() {
      return HasOps$.MODULE$.op_M_DM_Float_OpMulScalar();
   }

   static BinaryRegistry op_M_DM_Long_OpMulScalar() {
      return HasOps$.MODULE$.op_M_DM_Long_OpMulScalar();
   }

   static BinaryRegistry op_M_DM_Int_OpMulScalar() {
      return HasOps$.MODULE$.op_M_DM_Int_OpMulScalar();
   }

   static BinaryRegistry op_M_DM_Complex_OpSub() {
      return HasOps$.MODULE$.op_M_DM_Complex_OpSub();
   }

   static BinaryRegistry op_M_DM_BigInt_OpSub() {
      return HasOps$.MODULE$.op_M_DM_BigInt_OpSub();
   }

   static BinaryRegistry op_M_DM_Double_OpSub() {
      return HasOps$.MODULE$.op_M_DM_Double_OpSub();
   }

   static BinaryRegistry op_M_DM_Float_OpSub() {
      return HasOps$.MODULE$.op_M_DM_Float_OpSub();
   }

   static BinaryRegistry op_M_DM_Long_OpSub() {
      return HasOps$.MODULE$.op_M_DM_Long_OpSub();
   }

   static BinaryRegistry op_M_DM_Int_OpSub() {
      return HasOps$.MODULE$.op_M_DM_Int_OpSub();
   }

   static BinaryRegistry op_M_DM_Complex_OpAdd() {
      return HasOps$.MODULE$.op_M_DM_Complex_OpAdd();
   }

   static BinaryRegistry op_M_DM_BigInt_OpAdd() {
      return HasOps$.MODULE$.op_M_DM_BigInt_OpAdd();
   }

   static BinaryRegistry op_M_DM_Double_OpAdd() {
      return HasOps$.MODULE$.op_M_DM_Double_OpAdd();
   }

   static BinaryRegistry op_M_DM_Float_OpAdd() {
      return HasOps$.MODULE$.op_M_DM_Float_OpAdd();
   }

   static BinaryRegistry op_M_DM_Long_OpAdd() {
      return HasOps$.MODULE$.op_M_DM_Long_OpAdd();
   }

   static BinaryRegistry op_M_DM_Int_OpAdd() {
      return HasOps$.MODULE$.op_M_DM_Int_OpAdd();
   }

   static BinaryRegistry op_S_M_OpPow(final Field evidence$95, final Zero evidence$96, final ClassTag evidence$97) {
      return HasOps$.MODULE$.op_S_M_OpPow(evidence$95, evidence$96, evidence$97);
   }

   static BinaryRegistry op_S_M_OpMod(final Field evidence$92, final Zero evidence$93, final ClassTag evidence$94) {
      return HasOps$.MODULE$.op_S_M_OpMod(evidence$92, evidence$93, evidence$94);
   }

   static BinaryRegistry op_S_M_OpDiv(final Field evidence$89, final Zero evidence$90, final ClassTag evidence$91) {
      return HasOps$.MODULE$.op_S_M_OpDiv(evidence$89, evidence$90, evidence$91);
   }

   static BinaryRegistry op_S_M_OpMulMatrix(final Field evidence$86, final Zero evidence$87, final ClassTag evidence$88) {
      return HasOps$.MODULE$.op_S_M_OpMulMatrix(evidence$86, evidence$87, evidence$88);
   }

   static BinaryRegistry op_S_M_OpMulScalar(final Field evidence$83, final Zero evidence$84, final ClassTag evidence$85) {
      return HasOps$.MODULE$.op_S_M_OpMulScalar(evidence$83, evidence$84, evidence$85);
   }

   static BinaryRegistry op_S_M_OpSub(final Field evidence$80, final Zero evidence$81, final ClassTag evidence$82) {
      return HasOps$.MODULE$.op_S_M_OpSub(evidence$80, evidence$81, evidence$82);
   }

   static BinaryRegistry op_S_M_OpAdd(final Field evidence$77, final Zero evidence$78, final ClassTag evidence$79) {
      return HasOps$.MODULE$.op_S_M_OpAdd(evidence$77, evidence$78, evidence$79);
   }

   static BinaryRegistry op_S_M_Complex_OpPow() {
      return HasOps$.MODULE$.op_S_M_Complex_OpPow();
   }

   static BinaryRegistry op_S_M_Double_OpPow() {
      return HasOps$.MODULE$.op_S_M_Double_OpPow();
   }

   static BinaryRegistry op_S_M_Float_OpPow() {
      return HasOps$.MODULE$.op_S_M_Float_OpPow();
   }

   static BinaryRegistry op_S_M_Long_OpPow() {
      return HasOps$.MODULE$.op_S_M_Long_OpPow();
   }

   static BinaryRegistry op_S_M_Int_OpPow() {
      return HasOps$.MODULE$.op_S_M_Int_OpPow();
   }

   static BinaryRegistry op_S_M_BigInt_OpMod() {
      return HasOps$.MODULE$.op_S_M_BigInt_OpMod();
   }

   static BinaryRegistry op_S_M_Double_OpMod() {
      return HasOps$.MODULE$.op_S_M_Double_OpMod();
   }

   static BinaryRegistry op_S_M_Float_OpMod() {
      return HasOps$.MODULE$.op_S_M_Float_OpMod();
   }

   static BinaryRegistry op_S_M_Long_OpMod() {
      return HasOps$.MODULE$.op_S_M_Long_OpMod();
   }

   static BinaryRegistry op_S_M_Int_OpMod() {
      return HasOps$.MODULE$.op_S_M_Int_OpMod();
   }

   static BinaryRegistry op_S_M_Complex_OpDiv() {
      return HasOps$.MODULE$.op_S_M_Complex_OpDiv();
   }

   static BinaryRegistry op_S_M_BigInt_OpDiv() {
      return HasOps$.MODULE$.op_S_M_BigInt_OpDiv();
   }

   static BinaryRegistry op_S_M_Double_OpDiv() {
      return HasOps$.MODULE$.op_S_M_Double_OpDiv();
   }

   static BinaryRegistry op_S_M_Float_OpDiv() {
      return HasOps$.MODULE$.op_S_M_Float_OpDiv();
   }

   static BinaryRegistry op_S_M_Long_OpDiv() {
      return HasOps$.MODULE$.op_S_M_Long_OpDiv();
   }

   static BinaryRegistry op_S_M_Int_OpDiv() {
      return HasOps$.MODULE$.op_S_M_Int_OpDiv();
   }

   static BinaryRegistry op_S_M_Complex_OpMulMatrix() {
      return HasOps$.MODULE$.op_S_M_Complex_OpMulMatrix();
   }

   static BinaryRegistry op_S_M_BigInt_OpMulMatrix() {
      return HasOps$.MODULE$.op_S_M_BigInt_OpMulMatrix();
   }

   static BinaryRegistry op_S_M_Double_OpMulMatrix() {
      return HasOps$.MODULE$.op_S_M_Double_OpMulMatrix();
   }

   static BinaryRegistry op_S_M_Float_OpMulMatrix() {
      return HasOps$.MODULE$.op_S_M_Float_OpMulMatrix();
   }

   static BinaryRegistry op_S_M_Long_OpMulMatrix() {
      return HasOps$.MODULE$.op_S_M_Long_OpMulMatrix();
   }

   static BinaryRegistry op_S_M_Int_OpMulMatrix() {
      return HasOps$.MODULE$.op_S_M_Int_OpMulMatrix();
   }

   static BinaryRegistry op_S_M_Complex_OpMulScalar() {
      return HasOps$.MODULE$.op_S_M_Complex_OpMulScalar();
   }

   static BinaryRegistry op_S_M_BigInt_OpMulScalar() {
      return HasOps$.MODULE$.op_S_M_BigInt_OpMulScalar();
   }

   static BinaryRegistry op_S_M_Double_OpMulScalar() {
      return HasOps$.MODULE$.op_S_M_Double_OpMulScalar();
   }

   static BinaryRegistry op_S_M_Float_OpMulScalar() {
      return HasOps$.MODULE$.op_S_M_Float_OpMulScalar();
   }

   static BinaryRegistry op_S_M_Long_OpMulScalar() {
      return HasOps$.MODULE$.op_S_M_Long_OpMulScalar();
   }

   static BinaryRegistry op_S_M_Int_OpMulScalar() {
      return HasOps$.MODULE$.op_S_M_Int_OpMulScalar();
   }

   static BinaryRegistry op_S_M_Complex_OpSub() {
      return HasOps$.MODULE$.op_S_M_Complex_OpSub();
   }

   static BinaryRegistry op_S_M_BigInt_OpSub() {
      return HasOps$.MODULE$.op_S_M_BigInt_OpSub();
   }

   static BinaryRegistry op_S_M_Double_OpSub() {
      return HasOps$.MODULE$.op_S_M_Double_OpSub();
   }

   static BinaryRegistry op_S_M_Float_OpSub() {
      return HasOps$.MODULE$.op_S_M_Float_OpSub();
   }

   static BinaryRegistry op_S_M_Long_OpSub() {
      return HasOps$.MODULE$.op_S_M_Long_OpSub();
   }

   static BinaryRegistry op_S_M_Int_OpSub() {
      return HasOps$.MODULE$.op_S_M_Int_OpSub();
   }

   static BinaryRegistry op_S_M_Complex_OpAdd() {
      return HasOps$.MODULE$.op_S_M_Complex_OpAdd();
   }

   static BinaryRegistry op_S_M_BigInt_OpAdd() {
      return HasOps$.MODULE$.op_S_M_BigInt_OpAdd();
   }

   static BinaryRegistry op_S_M_Double_OpAdd() {
      return HasOps$.MODULE$.op_S_M_Double_OpAdd();
   }

   static BinaryRegistry op_S_M_Float_OpAdd() {
      return HasOps$.MODULE$.op_S_M_Float_OpAdd();
   }

   static BinaryRegistry op_S_M_Long_OpAdd() {
      return HasOps$.MODULE$.op_S_M_Long_OpAdd();
   }

   static BinaryRegistry op_S_M_Int_OpAdd() {
      return HasOps$.MODULE$.op_S_M_Int_OpAdd();
   }

   static BinaryRegistry op_M_S_OpPow(final Field evidence$74, final Zero evidence$75, final ClassTag evidence$76) {
      return HasOps$.MODULE$.op_M_S_OpPow(evidence$74, evidence$75, evidence$76);
   }

   static BinaryRegistry op_M_S_OpMod(final Field evidence$71, final Zero evidence$72, final ClassTag evidence$73) {
      return HasOps$.MODULE$.op_M_S_OpMod(evidence$71, evidence$72, evidence$73);
   }

   static BinaryRegistry op_M_S_OpDiv(final Field evidence$68, final Zero evidence$69, final ClassTag evidence$70) {
      return HasOps$.MODULE$.op_M_S_OpDiv(evidence$68, evidence$69, evidence$70);
   }

   static BinaryRegistry op_M_S_OpMulMatrix(final Field evidence$65, final Zero evidence$66, final ClassTag evidence$67) {
      return HasOps$.MODULE$.op_M_S_OpMulMatrix(evidence$65, evidence$66, evidence$67);
   }

   static BinaryRegistry op_M_S_OpMulScalar(final Field evidence$62, final Zero evidence$63, final ClassTag evidence$64) {
      return HasOps$.MODULE$.op_M_S_OpMulScalar(evidence$62, evidence$63, evidence$64);
   }

   static BinaryRegistry op_M_S_OpSub(final Field evidence$59, final Zero evidence$60, final ClassTag evidence$61) {
      return HasOps$.MODULE$.op_M_S_OpSub(evidence$59, evidence$60, evidence$61);
   }

   static BinaryRegistry op_M_S_OpAdd(final Field evidence$56, final Zero evidence$57, final ClassTag evidence$58) {
      return HasOps$.MODULE$.op_M_S_OpAdd(evidence$56, evidence$57, evidence$58);
   }

   static BinaryRegistry op_M_S_Complex_OpPow() {
      return HasOps$.MODULE$.op_M_S_Complex_OpPow();
   }

   static BinaryRegistry op_M_S_Double_OpPow() {
      return HasOps$.MODULE$.op_M_S_Double_OpPow();
   }

   static BinaryRegistry op_M_S_Float_OpPow() {
      return HasOps$.MODULE$.op_M_S_Float_OpPow();
   }

   static BinaryRegistry op_M_S_Long_OpPow() {
      return HasOps$.MODULE$.op_M_S_Long_OpPow();
   }

   static BinaryRegistry op_M_S_Int_OpPow() {
      return HasOps$.MODULE$.op_M_S_Int_OpPow();
   }

   static BinaryRegistry op_M_S_Complex_OpDiv() {
      return HasOps$.MODULE$.op_M_S_Complex_OpDiv();
   }

   static BinaryRegistry op_M_S_BigInt_OpDiv() {
      return HasOps$.MODULE$.op_M_S_BigInt_OpDiv();
   }

   static BinaryRegistry op_M_S_Double_OpDiv() {
      return HasOps$.MODULE$.op_M_S_Double_OpDiv();
   }

   static BinaryRegistry op_M_S_Float_OpDiv() {
      return HasOps$.MODULE$.op_M_S_Float_OpDiv();
   }

   static BinaryRegistry op_M_S_Long_OpDiv() {
      return HasOps$.MODULE$.op_M_S_Long_OpDiv();
   }

   static BinaryRegistry op_M_S_Int_OpDiv() {
      return HasOps$.MODULE$.op_M_S_Int_OpDiv();
   }

   static BinaryRegistry op_M_S_BigInt_OpMod() {
      return HasOps$.MODULE$.op_M_S_BigInt_OpMod();
   }

   static BinaryRegistry op_M_S_Double_OpMod() {
      return HasOps$.MODULE$.op_M_S_Double_OpMod();
   }

   static BinaryRegistry op_M_S_Float_OpMod() {
      return HasOps$.MODULE$.op_M_S_Float_OpMod();
   }

   static BinaryRegistry op_M_S_Long_OpMod() {
      return HasOps$.MODULE$.op_M_S_Long_OpMod();
   }

   static BinaryRegistry op_M_S_Int_OpMod() {
      return HasOps$.MODULE$.op_M_S_Int_OpMod();
   }

   static BinaryRegistry op_M_S_Complex_OpMulMatrix() {
      return HasOps$.MODULE$.op_M_S_Complex_OpMulMatrix();
   }

   static BinaryRegistry op_M_S_BigInt_OpMulMatrix() {
      return HasOps$.MODULE$.op_M_S_BigInt_OpMulMatrix();
   }

   static BinaryRegistry op_M_S_Double_OpMulMatrix() {
      return HasOps$.MODULE$.op_M_S_Double_OpMulMatrix();
   }

   static BinaryRegistry op_M_S_Float_OpMulMatrix() {
      return HasOps$.MODULE$.op_M_S_Float_OpMulMatrix();
   }

   static BinaryRegistry op_M_S_Long_OpMulMatrix() {
      return HasOps$.MODULE$.op_M_S_Long_OpMulMatrix();
   }

   static BinaryRegistry op_M_S_Int_OpMulMatrix() {
      return HasOps$.MODULE$.op_M_S_Int_OpMulMatrix();
   }

   static BinaryRegistry op_M_S_Complex_OpMulScalar() {
      return HasOps$.MODULE$.op_M_S_Complex_OpMulScalar();
   }

   static BinaryRegistry op_M_S_BigInt_OpMulScalar() {
      return HasOps$.MODULE$.op_M_S_BigInt_OpMulScalar();
   }

   static BinaryRegistry op_M_S_Double_OpMulScalar() {
      return HasOps$.MODULE$.op_M_S_Double_OpMulScalar();
   }

   static BinaryRegistry op_M_S_Float_OpMulScalar() {
      return HasOps$.MODULE$.op_M_S_Float_OpMulScalar();
   }

   static BinaryRegistry op_M_S_Long_OpMulScalar() {
      return HasOps$.MODULE$.op_M_S_Long_OpMulScalar();
   }

   static BinaryRegistry op_M_S_Int_OpMulScalar() {
      return HasOps$.MODULE$.op_M_S_Int_OpMulScalar();
   }

   static BinaryRegistry op_M_S_Complex_OpSub() {
      return HasOps$.MODULE$.op_M_S_Complex_OpSub();
   }

   static BinaryRegistry op_M_S_BigInt_OpSub() {
      return HasOps$.MODULE$.op_M_S_BigInt_OpSub();
   }

   static BinaryRegistry op_M_S_Double_OpSub() {
      return HasOps$.MODULE$.op_M_S_Double_OpSub();
   }

   static BinaryRegistry op_M_S_Float_OpSub() {
      return HasOps$.MODULE$.op_M_S_Float_OpSub();
   }

   static BinaryRegistry op_M_S_Long_OpSub() {
      return HasOps$.MODULE$.op_M_S_Long_OpSub();
   }

   static BinaryRegistry op_M_S_Int_OpSub() {
      return HasOps$.MODULE$.op_M_S_Int_OpSub();
   }

   static BinaryRegistry op_M_S_Complex_OpAdd() {
      return HasOps$.MODULE$.op_M_S_Complex_OpAdd();
   }

   static BinaryRegistry op_M_S_BigInt_OpAdd() {
      return HasOps$.MODULE$.op_M_S_BigInt_OpAdd();
   }

   static BinaryRegistry op_M_S_Double_OpAdd() {
      return HasOps$.MODULE$.op_M_S_Double_OpAdd();
   }

   static BinaryRegistry op_M_S_Float_OpAdd() {
      return HasOps$.MODULE$.op_M_S_Float_OpAdd();
   }

   static BinaryRegistry op_M_S_Long_OpAdd() {
      return HasOps$.MODULE$.op_M_S_Long_OpAdd();
   }

   static BinaryRegistry op_M_S_Int_OpAdd() {
      return HasOps$.MODULE$.op_M_S_Int_OpAdd();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpPow(final Field evidence$53, final Zero evidence$54, final ClassTag evidence$55) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpPow(evidence$53, evidence$54, evidence$55);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpMod(final Field evidence$50, final Zero evidence$51, final ClassTag evidence$52) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpMod(evidence$50, evidence$51, evidence$52);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpDiv(final Field evidence$47, final Zero evidence$48, final ClassTag evidence$49) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpDiv(evidence$47, evidence$48, evidence$49);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpMulMatrix(final Field evidence$44, final Zero evidence$45, final ClassTag evidence$46) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpMulMatrix(evidence$44, evidence$45, evidence$46);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpMulScalar(final Field evidence$41, final Zero evidence$42, final ClassTag evidence$43) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpMulScalar(evidence$41, evidence$42, evidence$43);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpSub(final Field evidence$38, final Zero evidence$39, final ClassTag evidence$40) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpSub(evidence$38, evidence$39, evidence$40);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_OpAdd(final Field evidence$35, final Zero evidence$36, final ClassTag evidence$37) {
      return HasOps$.MODULE$.m_s_UpdateOp_OpAdd(evidence$35, evidence$36, evidence$37);
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpPow() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpPow();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpPow() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpPow();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpPow() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpPow();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpPow() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpPow();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpPow() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpPow();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMod() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpMod();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpMod() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpMod();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpMod() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpMod();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpMod() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpMod();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpMod() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpMod();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpSet() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpSet();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpSet() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpSet();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpSet() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpSet();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpSet() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpSet();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpSet() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpSet();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpSet() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpSet();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpDiv() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpDiv();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpDiv() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpDiv();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpDiv() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpDiv();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpDiv() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpDiv();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpDiv() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpDiv();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpDiv() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpDiv();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpMulMatrix() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpMulMatrix();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMulMatrix() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpMulMatrix();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpMulMatrix() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpMulMatrix();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpMulMatrix() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpMulMatrix();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpMulMatrix() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpMulMatrix();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpMulMatrix() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpMulMatrix();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpMulScalar() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpMulScalar();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMulScalar() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpMulScalar();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpMulScalar() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpMulScalar();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpMulScalar() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpMulScalar();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpMulScalar() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpMulScalar();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpMulScalar() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpMulScalar();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpSub() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpSub();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpSub() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpSub();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpSub() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpSub();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpSub() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpSub();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpSub() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpSub();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpSub() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpSub();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpAdd() {
      return HasOps$.MODULE$.m_s_UpdateOp_Complex_OpAdd();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpAdd() {
      return HasOps$.MODULE$.m_s_UpdateOp_BigInt_OpAdd();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Long_OpAdd() {
      return HasOps$.MODULE$.m_s_UpdateOp_Long_OpAdd();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Float_OpAdd() {
      return HasOps$.MODULE$.m_s_UpdateOp_Float_OpAdd();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Double_OpAdd() {
      return HasOps$.MODULE$.m_s_UpdateOp_Double_OpAdd();
   }

   static BinaryUpdateRegistry m_s_UpdateOp_Int_OpAdd() {
      return HasOps$.MODULE$.m_s_UpdateOp_Int_OpAdd();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpPow(final Field evidence$32, final Zero evidence$33, final ClassTag evidence$34) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpPow(evidence$32, evidence$33, evidence$34);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpMod(final Field evidence$29, final Zero evidence$30, final ClassTag evidence$31) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpMod(evidence$29, evidence$30, evidence$31);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpSet(final Field evidence$26, final Zero evidence$27, final ClassTag evidence$28) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpSet(evidence$26, evidence$27, evidence$28);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpDiv(final Field evidence$23, final Zero evidence$24, final ClassTag evidence$25) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpDiv(evidence$23, evidence$24, evidence$25);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpMulScalar(final Field evidence$20, final Zero evidence$21, final ClassTag evidence$22) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpMulScalar(evidence$20, evidence$21, evidence$22);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpSub(final Field evidence$17, final Zero evidence$18, final ClassTag evidence$19) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpSub(evidence$17, evidence$18, evidence$19);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_OpAdd(final Field evidence$14, final Zero evidence$15, final ClassTag evidence$16) {
      return HasOps$.MODULE$.m_m_UpdateOp_OpAdd(evidence$14, evidence$15, evidence$16);
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpPow() {
      return HasOps$.MODULE$.m_m_UpdateOp_Complex_OpPow();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpPow() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpPow();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpPow() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpPow();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpPow() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpPow();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpPow() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpPow();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpMod() {
      return HasOps$.MODULE$.m_m_UpdateOp_BigInt_OpMod();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpMod() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpMod();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpMod() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpMod();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpMod() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpMod();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpMod() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpMod();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpSet() {
      return HasOps$.MODULE$.m_m_UpdateOp_Complex_OpSet();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpSet() {
      return HasOps$.MODULE$.m_m_UpdateOp_BigInt_OpSet();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpSet() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpSet();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpSet() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpSet();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpSet() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpSet();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpSet() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpSet();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpDiv() {
      return HasOps$.MODULE$.m_m_UpdateOp_Complex_OpDiv();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpDiv() {
      return HasOps$.MODULE$.m_m_UpdateOp_BigInt_OpDiv();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpDiv() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpDiv();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpDiv() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpDiv();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpDiv() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpDiv();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpDiv() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpDiv();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpMulScalar() {
      return HasOps$.MODULE$.m_m_UpdateOp_Complex_OpMulScalar();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpMulScalar() {
      return HasOps$.MODULE$.m_m_UpdateOp_BigInt_OpMulScalar();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpMulScalar() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpMulScalar();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpMulScalar() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpMulScalar();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpMulScalar() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpMulScalar();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpMulScalar() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpMulScalar();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpSub() {
      return HasOps$.MODULE$.m_m_UpdateOp_Complex_OpSub();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpSub() {
      return HasOps$.MODULE$.m_m_UpdateOp_BigInt_OpSub();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpSub() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpSub();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpSub() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpSub();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpSub() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpSub();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpSub() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpSub();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpAdd() {
      return HasOps$.MODULE$.m_m_UpdateOp_Complex_OpAdd();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpAdd() {
      return HasOps$.MODULE$.m_m_UpdateOp_BigInt_OpAdd();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Long_OpAdd() {
      return HasOps$.MODULE$.m_m_UpdateOp_Long_OpAdd();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Float_OpAdd() {
      return HasOps$.MODULE$.m_m_UpdateOp_Float_OpAdd();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Double_OpAdd() {
      return HasOps$.MODULE$.m_m_UpdateOp_Double_OpAdd();
   }

   static BinaryUpdateRegistry m_m_UpdateOp_Int_OpAdd() {
      return HasOps$.MODULE$.m_m_UpdateOp_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 m_m_OpDiv_Update_Ring(final Field evidence$11, final ClassTag evidence$12, final Zero evidence$13) {
      return HasOps$.MODULE$.m_m_OpDiv_Update_Ring(evidence$11, evidence$12, evidence$13);
   }

   static UFunc.InPlaceImpl2 m_m_OpSub_Update_Ring(final Ring evidence$8, final ClassTag evidence$9, final Zero evidence$10) {
      return HasOps$.MODULE$.m_m_OpSub_Update_Ring(evidence$8, evidence$9, evidence$10);
   }

   static UFunc.InPlaceImpl2 m_m_OpMul_Update_Semi(final Semiring evidence$5, final ClassTag evidence$6, final Zero evidence$7) {
      return HasOps$.MODULE$.m_m_OpMul_Update_Semi(evidence$5, evidence$6, evidence$7);
   }

   static UFunc.InPlaceImpl2 m_m_OpAdd_Update_Semi(final Semiring evidence$2, final ClassTag evidence$3, final Zero evidence$4) {
      return HasOps$.MODULE$.m_m_OpAdd_Update_Semi(evidence$2, evidence$3, evidence$4);
   }

   static CanCopy canCopyMatrix(final ClassTag evidence$1) {
      return HasOps$.MODULE$.canCopyMatrix(evidence$1);
   }

   static UFunc.UImpl impl_dim_DV_eq_I() {
      return HasOps$.MODULE$.impl_dim_DV_eq_I();
   }

   static DenseVector_DoubleOps.canDotD$ canDotD() {
      return HasOps$.MODULE$.canDotD();
   }

   static UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Double() {
      return HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double();
   }

   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Double() {
      return HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double();
   }

   static UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Double() {
      return HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double();
   }

   static DenseVector_DoubleOps.impl_scaleAdd_InPlace_DV_T_DV_Double$ impl_scaleAdd_InPlace_DV_T_DV_Double() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double();
   }

   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Double() {
      return HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Float();
   }

   static UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Float() {
      return HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Float();
   }

   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Float() {
      return HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Float();
   }

   static UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Float() {
      return HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Float();
   }

   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Float() {
      return HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Float();
   }

   static DenseVector_FloatOps.impl_scaledAdd_InPlace_DV_S_DV_Float$ impl_scaledAdd_InPlace_DV_S_DV_Float() {
      return HasOps$.MODULE$.impl_scaledAdd_InPlace_DV_S_DV_Float();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Long_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Float_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Int_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Long_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Float_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Int_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Long_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Float_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Int_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Long_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Float_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Int_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Long_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Float_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Int_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Long_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Float_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Int_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Long_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Float_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Double_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Int_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Long_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Float_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Double_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Int_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Long_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Float_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Double_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Int_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Long_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Float_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Double_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Int_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Long_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Float_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Double_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Int_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Long_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Float_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Double_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_BV_Comparison_Int_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Long_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Float_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Double_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpNe() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Int_OpNe();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Long_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Float_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Double_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpEq() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Int_OpEq();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Long_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Float_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Double_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpLT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Int_OpLT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Long_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Float_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Double_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpLTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Int_OpLTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Long_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Float_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Double_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpGTE() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Int_OpGTE();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Long_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Float_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Double_OpGT();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpGT() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_BV_comparison_Int_OpGT();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Long() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Long();
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Int() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Int();
   }

   static UFunc.UImpl2 impl_zipValues_DV_DV_Long() {
      return HasOps$.MODULE$.impl_zipValues_DV_DV_Long();
   }

   static UFunc.UImpl2 impl_zipValues_DV_DV_Float() {
      return HasOps$.MODULE$.impl_zipValues_DV_DV_Float();
   }

   static UFunc.UImpl2 impl_zipValues_DV_DV_Double() {
      return HasOps$.MODULE$.impl_zipValues_DV_DV_Double();
   }

   static UFunc.UImpl2 impl_zipValues_DV_DV_Int() {
      return HasOps$.MODULE$.impl_zipValues_DV_DV_Int();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Int();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpMulMatrix();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpPow();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpMod();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSet();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpDiv();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpMulScalar();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSub();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpAdd();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_DV_eq_DV_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpPow();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMod();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpSet();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpDiv();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulMatrix();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulScalar();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpSub();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd();
   }

   static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Long_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Float_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Double_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Int_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Long_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Float_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Double_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Int_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Long_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Float_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Double_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Int_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Long_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Float_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Double_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Int_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Long_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Float_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Double_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_DV_V_Int_OpMulScalar();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpPow();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpPow();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpPow();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpPow();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpMod();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpMod();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpMod();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpMod();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpSet();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpSet();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpSet();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpSet();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpDiv();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpDiv();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpDiv();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpDiv();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpMulScalar();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpMulScalar();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpMulScalar();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpMulScalar();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpSub();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpSub();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpSub();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpSub();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Long_OpAdd();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Float_OpAdd();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpAdd();
   }

   static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_DV_V_eq_V_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_V_eq_S_Long();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_V_eq_S_Float();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_V_eq_S_Double();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_DV_V_eq_S_Int();
   }

   static CanSlice canSlice_DV_Range_eq_DV() {
      return HasOps$.MODULE$.canSlice_DV_Range_eq_DV();
   }

   static CanMapKeyValuePairs canMapPairs(final ClassTag man) {
      return HasOps$.MODULE$.canMapPairs(man);
   }

   static CanTransformValues DV_canTransformValues() {
      return HasOps$.MODULE$.DV_canTransformValues();
   }

   static CanTraverseKeyValuePairs DV_canTraverseKeyValuePairs() {
      return HasOps$.MODULE$.DV_canTraverseKeyValuePairs();
   }

   static CanZipAndTraverseValues DV_canTraverseZipValues() {
      return HasOps$.MODULE$.DV_canTraverseZipValues();
   }

   static CanTraverseValues DV_canIterateValues() {
      return HasOps$.MODULE$.DV_canIterateValues();
   }

   static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_S_Generic(final UFunc.UImpl2 pow) {
      return HasOps$.MODULE$.impl_OpPow_InPlace_DV_S_Generic(pow);
   }

   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_S_Generic(final Field field) {
      return HasOps$.MODULE$.impl_OpDiv_InPlace_DV_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_S_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_S_Generic(final Ring field) {
      return HasOps$.MODULE$.impl_OpSub_InPlace_DV_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_S_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpAdd_InPlace_DV_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_DV_Generic(final UFunc.UImpl2 pow) {
      return HasOps$.MODULE$.impl_OpPow_InPlace_DV_DV_Generic(pow);
   }

   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_DV_Generic(final Field field) {
      return HasOps$.MODULE$.impl_OpDiv_InPlace_DV_DV_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_DV_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_DV_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Generic(final Ring field) {
      return HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Generic(field);
   }

   static UFunc.UImpl2 impl_Op_LHS_DVt_eq_R_cast(final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.impl_Op_LHS_DVt_eq_R_cast(op);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V(final .less.colon.less ev) {
      return HasOps$.MODULE$.impl_OpSet_InPlace_DV_V(ev);
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Generic(final Semiring evidence$14) {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Generic(evidence$14);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_DV() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V_Generic() {
      return HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic();
   }

   static CanTranspose canTranspose_CSC_Complex() {
      return HasOps$.MODULE$.canTranspose_CSC_Complex();
   }

   static CanTranspose canTranspose_CSC(final ClassTag evidence$1, final Zero evidence$2, final Semiring evidence$3) {
      return HasOps$.MODULE$.canTranspose_CSC(evidence$1, evidence$2, evidence$3);
   }

   static CanTranspose canTranspose_SV_Complex() {
      return HasOps$.MODULE$.canTranspose_SV_Complex();
   }

   static CanTranspose canTranspose_DV_Complex() {
      return HasOps$.MODULE$.canTranspose_DV_Complex();
   }

   static CanTranspose canTranspose_DM_Complex() {
      return HasOps$.MODULE$.canTranspose_DM_Complex();
   }

   static CanTranspose canTranspose_DM() {
      return HasOps$.MODULE$.canTranspose_DM();
   }

   static CanTranspose transposeTensor(final .less.colon.less ev) {
      return HasOps$.MODULE$.transposeTensor(ev);
   }

   static UFunc.UImpl2 transTimesNormalFromDot(final UFunc.UImpl2 dot) {
      return HasOps$.MODULE$.transTimesNormalFromDot(dot);
   }

   static CanTranspose canUntranspose() {
      return HasOps$.MODULE$.canUntranspose();
   }

   static UFunc.InPlaceImpl2 impl_Op_InPlace_Tt_S_from_T_S(final ScalarOf ev, final UFunc.InPlaceImpl2 op) {
      return HasOps$.MODULE$.impl_Op_InPlace_Tt_S_from_T_S(ev, op);
   }

   static UFunc.UImpl2 impl_Op_Tt_S_eq_RT_from_T_S(final ScalarOf ev, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return HasOps$.MODULE$.impl_Op_Tt_S_eq_RT_from_T_S(ev, op, canTranspose);
   }

   static UFunc.UImpl2 impl_OpMulMatrix_Ut_T_from_Tt_U(final CanTranspose transT, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return HasOps$.MODULE$.impl_OpMulMatrix_Ut_T_from_Tt_U(transT, op, canTranspose);
   }

   static UFunc.InPlaceImpl3 liftUFuncInplace3_1(final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.InPlaceImpl3 op) {
      return HasOps$.MODULE$.liftUFuncInplace3_1(t2Trans, t3Trans, op);
   }

   static UFunc.UImpl3 liftUFunc3_1(final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.UImpl3 op, final CanTranspose transR) {
      return HasOps$.MODULE$.liftUFunc3_1(t2Trans, t3Trans, op, transR);
   }

   static UFunc.InPlaceImpl impl_Op_InPlace_Tt_from_Op_T(final UFunc.InPlaceImpl op) {
      return HasOps$.MODULE$.impl_Op_InPlace_Tt_from_Op_T(op);
   }

   static UFunc.UImpl liftUFunc(final UFunc.UImpl op, final CanTranspose trans) {
      return HasOps$.MODULE$.liftUFunc(op, trans);
   }

   static CanSlice liftSlice(final CanSlice op, final CanTranspose trans) {
      return HasOps$.MODULE$.liftSlice(op, trans);
   }

   static TransposeOps_LowPrio2.LiftApply LiftApply(final Transpose _trans) {
      return HasOps$.MODULE$.LiftApply(_trans);
   }

   static UFunc.InPlaceImpl2 liftInPlaceOps(final NotGiven notScalar, final CanTranspose transU, final UFunc.InPlaceImpl2 op) {
      return HasOps$.MODULE$.liftInPlaceOps(notScalar, transU, op);
   }

   static UFunc.UImpl2 impl_EOp_Tt_Ut_eq_Rt_from_T_U(final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return HasOps$.MODULE$.impl_EOp_Tt_Ut_eq_Rt_from_T_U(op, canTranspose);
   }

   static CanSlice2 canSliceTensor2_CsR(final Semiring evidence$7, final ClassTag evidence$8) {
      return HasOps$.MODULE$.canSliceTensor2_CsR(evidence$7, evidence$8);
   }

   static CanSlice2 canSliceTensor2_CRs(final Semiring evidence$5, final ClassTag evidence$6) {
      return HasOps$.MODULE$.canSliceTensor2_CRs(evidence$5, evidence$6);
   }

   static CanSlice2 canSliceTensor2(final Semiring evidence$3, final ClassTag evidence$4) {
      return HasOps$.MODULE$.canSliceTensor2(evidence$3, evidence$4);
   }

   static CanSlice canSliceTensorBoolean(final ClassTag evidence$2) {
      return HasOps$.MODULE$.canSliceTensorBoolean(evidence$2);
   }

   static CanSlice canSliceTensor(final ClassTag evidence$1) {
      return HasOps$.MODULE$.canSliceTensor(evidence$1);
   }

   static CanSlice2 canSliceTensor_Seq_to_2(final CanSlice seqSlice) {
      return HasOps$.MODULE$.canSliceTensor_Seq_to_2(seqSlice);
   }

   static BinaryRegistry zipValuesImpl_V_V_Long() {
      return HasOps$.MODULE$.zipValuesImpl_V_V_Long();
   }

   static BinaryRegistry zipValuesImpl_V_V_Float() {
      return HasOps$.MODULE$.zipValuesImpl_V_V_Float();
   }

   static BinaryRegistry zipValuesImpl_V_V_Double() {
      return HasOps$.MODULE$.zipValuesImpl_V_V_Double();
   }

   static BinaryRegistry zipValuesImpl_V_V_Int() {
      return HasOps$.MODULE$.zipValuesImpl_V_V_Int();
   }

   static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Long() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_V_S_V_Long();
   }

   static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Float() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_V_S_V_Float();
   }

   static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Double() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_V_S_V_Double();
   }

   static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Int() {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_V_S_V_Int();
   }

   static UFunc.UImpl2 impl_OpMulInner_V_V_eq_S_Generic(final Semiring s) {
      return HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Generic(s);
   }

   static BinaryRegistry impl_OpMulInner_V_V_eq_S_Double() {
      return HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Double();
   }

   static BinaryRegistry impl_OpMulInner_V_V_eq_S_Float() {
      return HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Float();
   }

   static BinaryRegistry impl_OpMulInner_V_V_eq_S_Long() {
      return HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Long();
   }

   static BinaryRegistry impl_OpMulInner_V_V_eq_S_Int() {
      return HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Int();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpPow(final Field evidence$29, final ClassTag evidence$30) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpPow(evidence$29, evidence$30);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpMod(final Field evidence$27, final ClassTag evidence$28) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpMod(evidence$27, evidence$28);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpSet(final Field evidence$25, final ClassTag evidence$26) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpSet(evidence$25, evidence$26);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpDiv(final Field evidence$23, final ClassTag evidence$24) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpDiv(evidence$23, evidence$24);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpMulMatrix(final Field evidence$21, final ClassTag evidence$22) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpMulMatrix(evidence$21, evidence$22);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpMulScalar(final Field evidence$19, final ClassTag evidence$20) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpMulScalar(evidence$19, evidence$20);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpSub(final Field evidence$17, final ClassTag evidence$18) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpSub(evidence$17, evidence$18);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpAdd(final Field evidence$15, final ClassTag evidence$16) {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpAdd(evidence$15, evidence$16);
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpMulMatrix();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpMulMatrix();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpMulMatrix();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpMulMatrix();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Long_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Float_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_S_Int_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Long_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Float_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Int_OpSub();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Long_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Int_OpAdd();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Long_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Float_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Double_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Int_OpPow();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Long_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Float_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Double_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Int_OpMod();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Long_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Float_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Double_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Int_OpSet();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Long_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Float_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Double_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Int_OpDiv();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Long_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Float_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Double_OpMulScalar();
   }

   static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_InPlace_V_V_Int_OpMulScalar();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpPow(final Field evidence$13, final ClassTag evidence$14) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpPow(evidence$13, evidence$14);
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpMod(final Field evidence$11, final ClassTag evidence$12) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpMod(evidence$11, evidence$12);
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpDiv(final Field evidence$9, final ClassTag evidence$10) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpDiv(evidence$9, evidence$10);
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpMulMatrix(final Field evidence$7, final ClassTag evidence$8) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpMulMatrix(evidence$7, evidence$8);
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpMulScalar(final Field evidence$5, final ClassTag evidence$6) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpMulScalar(evidence$5, evidence$6);
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpSub(final Field evidence$3, final ClassTag evidence$4) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpSub(evidence$3, evidence$4);
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Generic_OpAdd(final Field evidence$1, final ClassTag evidence$2) {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpAdd(evidence$1, evidence$2);
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpPow();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpPow();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpPow();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpPow();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpMod();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpMod();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpMod();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpMod();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpSet();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpSet();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpSet();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpSet();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpDiv();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpDiv();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpDiv();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpDiv();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpMulScalar();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpMulScalar();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpMulScalar();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpMulScalar();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpSub();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpSub();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpSub();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpSub();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Long_OpAdd();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Float_OpAdd();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Double_OpAdd();
   }

   static BinaryRegistry impl_Op_S_V_eq_V_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_S_V_eq_V_Int_OpAdd();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpPow();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpPow();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpPow();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpPow();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpMod();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpMod();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMod();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpMod();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpSet();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpSet();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpSet();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpSet();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpDiv();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpDiv();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpDiv();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpDiv();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpMulMatrix() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpMulMatrix();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpMulScalar();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpMulScalar();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpMulScalar() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpMulScalar();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpSub();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpSub();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpSub();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpSub();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Long_OpAdd();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Float_OpAdd();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpAdd();
   }

   static BinaryRegistry impl_Op_V_S_eq_V_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_S_eq_V_Int_OpAdd();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Long_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Long_OpPow();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Float_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Float_OpPow();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Double_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Double_OpPow();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Int_OpPow() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Int_OpPow();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Long_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Long_OpMod();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Float_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Float_OpMod();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Double_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Double_OpMod();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Int_OpMod() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Int_OpMod();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Long_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Long_OpSet();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Float_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Float_OpSet();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Double_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Double_OpSet();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Int_OpSet() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Int_OpSet();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Long_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Long_OpDiv();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Float_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Float_OpDiv();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Double_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Double_OpDiv();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_Int_OpDiv() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_Int_OpDiv();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Long() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_nilpotent_Long();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Float() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_nilpotent_Float();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Double() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_nilpotent_Double();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Int() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_nilpotent_Int();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Long_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Long_OpSub();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Float_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Float_OpSub();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Double_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpSub();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Int_OpSub() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Int_OpSub();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Long_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Long_OpAdd();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Float_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Float_OpAdd();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Double_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpAdd();
   }

   static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Int_OpAdd() {
      return HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Int_OpAdd();
   }

   static UFunc.UImpl2 impl_OpAdd_V_V_eq_V_Generic(final Semiring evidence$3) {
      return HasOps$.MODULE$.impl_OpAdd_V_V_eq_V_Generic(evidence$3);
   }

   static UFunc.UImpl2 impl_OpSub_V_V_eq_V_Generic(final Ring evidence$2) {
      return HasOps$.MODULE$.impl_OpSub_V_V_eq_V_Generic(evidence$2);
   }

   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_V_T_V_Generic(final Semiring evidence$1) {
      return HasOps$.MODULE$.impl_scaleAdd_InPlace_V_T_V_Generic(evidence$1);
   }

   static UFunc.InPlaceImpl2 impl_OpSet_V_S_InPlace() {
      return HasOps$.MODULE$.impl_OpSet_V_S_InPlace();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_V_V_InPlace() {
      return HasOps$.MODULE$.impl_OpSet_V_V_InPlace();
   }

   static UFunc.UImpl2 impl_OpMulInner_V_V_eq_S(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S(field);
   }

   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_S_Generic(final UFunc.UImpl2 pow, final Zero zero) {
      return HasOps$.MODULE$.impl_OpPow_InPlace_V_S_Generic(pow, zero);
   }

   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_S_Generic(final Field field) {
      return HasOps$.MODULE$.impl_OpDiv_InPlace_V_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_S_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_V_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_V_S_Generic(final Ring field) {
      return HasOps$.MODULE$.impl_OpSub_InPlace_V_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_V_S_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpAdd_InPlace_V_S_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_V_Generic(final UFunc.UImpl2 pow) {
      return HasOps$.MODULE$.impl_OpPow_InPlace_V_V_Generic(pow);
   }

   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_V_Generic(final Field field) {
      return HasOps$.MODULE$.impl_OpDiv_InPlace_V_V_Generic(field);
   }

   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_V_Generic(final Semiring field) {
      return HasOps$.MODULE$.impl_OpMulScalar_InPlace_V_V_Generic(field);
   }

   static Vector_GenericOps.ZippedVectorValues$ ZippedVectorValues() {
      return HasOps$.MODULE$.ZippedVectorValues();
   }

   static UFunc.UImpl2 zipValuesSubclass(final .less.colon.less view1, final .less.colon.less view2, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.zipValuesSubclass(view1, view2, op);
   }

   static CanTraverseKeyValuePairs canTraverseKeyValuePairs_V() {
      return HasOps$.MODULE$.canTraverseKeyValuePairs_V();
   }

   static CanTraverseValues canIterateValues_V() {
      return HasOps$.MODULE$.canIterateValues_V();
   }

   static Vector_TraversalOps.CanZipMapKeyValuesVector zipMapKV_V(final ClassTag evidence$5) {
      return HasOps$.MODULE$.zipMapKV_V(evidence$5);
   }

   static Vector_TraversalOps.CanZipMapValuesVector canZipMapValues_V(final ClassTag evidence$3) {
      return HasOps$.MODULE$.canZipMapValues_V(evidence$3);
   }

   static CanMapValues canMapValues_V(final Zero evidence$2, final ClassTag man) {
      return HasOps$.MODULE$.canMapValues_V(evidence$2, man);
   }

   static UFunc.UImpl impl_OpNeg_T_Generic_from_OpMulScalar(final ScalarOf scalarOf, final Ring ring, final UFunc.UImpl2 scale) {
      return HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(scalarOf, ring, scale);
   }

   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Ring ring) {
      return HasOps$.MODULE$.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(sa, ring);
   }

   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Semiring semi) {
      return HasOps$.MODULE$.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(sa, semi);
   }

   static UFunc.UImpl2 pureFromUpdate(final UFunc.InPlaceImpl2 op, final CanCopy copy) {
      return HasOps$.MODULE$.pureFromUpdate(op, copy);
   }

   static UFunc.InPlaceImpl2 castUpdateOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return HasOps$.MODULE$.castUpdateOps_M_V(v1lt, v2lt, v1ne, op);
   }

   static UFunc.UImpl2 castOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.castOps_M_V(v1lt, v2lt, v1ne, op);
   }

   static UFunc.InPlaceImpl2 castUpdateOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return HasOps$.MODULE$.castUpdateOps_M_M(v1lt, v2lt, v1ne, op);
   }

   static UFunc.UImpl2 castOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.castOps_M_M(v1lt, v2lt, v1ne, op);
   }

   static UFunc.InPlaceImpl2 castUpdateOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return HasOps$.MODULE$.castUpdateOps_V_S(v2, v1lt, v1ne, op);
   }

   static UFunc.UImpl2 castOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.castOps_V_S(v2, v1lt, v1ne, op);
   }

   static UFunc.InPlaceImpl2 castUpdateOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return HasOps$.MODULE$.castUpdateOps_V_V(v1lt, v2lt, v1ne, op);
   }

   static UFunc.UImpl2 castOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return HasOps$.MODULE$.castOps_V_V(v1lt, v2lt, v1ne, op);
   }

   static UFunc.UImpl2 impl_T_S_eq_U_from_ZipMap(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return HasOps$.MODULE$.impl_T_S_eq_U_from_ZipMap(handhold, impl, canZipMapValues);
   }
}
