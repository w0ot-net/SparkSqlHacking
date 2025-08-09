package breeze.linalg.operators;

import breeze.generic.MappingUFuncLowPrio;
import breeze.generic.MappingUFuncOps;
import breeze.generic.UFunc;
import breeze.generic.ZeroPreservingUFuncLowPrio;
import breeze.generic.ZeroPreservingUFuncOps;
import breeze.gymnastics.NotGiven;
import breeze.linalg.BroadcastedColumnsOps;
import breeze.linalg.BroadcastedOps;
import breeze.linalg.BroadcastedRowsOps;
import breeze.linalg.SliceVectorOps;
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
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import scala.;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class HasOps$ implements TransposeOps, HashVectorOps, DenseMatrixOps, CSCMatrixOps, SliceVectorOps, BitVectorOps, ZeroPreservingUFuncOps, BroadcastedOps {
   public static final HasOps$ MODULE$ = new HasOps$();
   private static volatile BitVectorOps.impl_any_BV_eq_Boolean$ impl_any_BV_eq_Boolean$module;
   private static volatile BitVectorOps.impl_all_BV_eq_Boolean$ impl_all_BV_eq_Boolean$module;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpAnd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpOr;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpXor;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_BV_BV;
   private static UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpAnd;
   private static UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpOr;
   private static UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpXor;
   private static UFunc.UImpl bv_OpNot;
   private static UFunc.UImpl2 bv_bv_OpNe;
   private static UFunc.UImpl2 bv_bv_OpEq;
   private static UFunc.UImpl2 canDot_BV_BV;
   private static UFunc.UImpl2 canDot_BV_DenseVector_Double;
   private static UFunc.UImpl2 canDot_BV_DenseVector_Float;
   private static UFunc.UImpl2 canDot_BV_DenseVector_Int;
   private static UFunc.UImpl2 canDot_BV_DenseVector_Long;
   private static UFunc.UImpl2 canDot_BV_SV_Int;
   private static UFunc.UImpl2 canDot_BV_SV_Long;
   private static UFunc.UImpl2 canDot_BV_SV_BigInt;
   private static UFunc.UImpl2 canDot_BV_SV_Complex;
   private static UFunc.UImpl csc_OpNeg_Int;
   private static UFunc.UImpl csc_OpNeg_Double;
   private static UFunc.UImpl csc_OpNeg_Float;
   private static UFunc.UImpl csc_OpNeg_Long;
   private static UFunc.InPlaceImpl3 cscScaleAdd_Int;
   private static UFunc.InPlaceImpl3 cscScaleAdd_Double;
   private static UFunc.InPlaceImpl3 cscScaleAdd_Float;
   private static UFunc.InPlaceImpl3 cscScaleAdd_Long;
   private static UFunc.UImpl2 csc_csc_BadOps_Int_OpPow;
   private static UFunc.UImpl2 csc_csc_BadOps_Double_OpPow;
   private static UFunc.UImpl2 csc_csc_BadOps_Float_OpPow;
   private static UFunc.UImpl2 csc_csc_BadOps_Long_OpPow;
   private static UFunc.UImpl2 csc_csc_BadOps_Int_OpDiv;
   private static UFunc.UImpl2 csc_csc_BadOps_Double_OpDiv;
   private static UFunc.UImpl2 csc_csc_BadOps_Float_OpDiv;
   private static UFunc.UImpl2 csc_csc_BadOps_Long_OpDiv;
   private static UFunc.UImpl2 csc_csc_BadOps_Int_OpMod;
   private static UFunc.UImpl2 csc_csc_BadOps_Double_OpMod;
   private static UFunc.UImpl2 csc_csc_BadOps_Float_OpMod;
   private static UFunc.UImpl2 csc_csc_BadOps_Long_OpMod;
   private static UFunc.UImpl2 csc_csc_OpAdd_Int;
   private static UFunc.UImpl2 csc_csc_OpAdd_Double;
   private static UFunc.UImpl2 csc_csc_OpAdd_Float;
   private static UFunc.UImpl2 csc_csc_OpAdd_Long;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Int;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Double;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Float;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Long;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Int;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Double;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Float;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Long;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Int;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Double;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Float;
   private static UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Long;
   private static UFunc.UImpl2 csc_dm_OpAdd_Int;
   private static UFunc.UImpl2 csc_dm_OpAdd_Double;
   private static UFunc.UImpl2 csc_dm_OpAdd_Float;
   private static UFunc.UImpl2 csc_dm_OpAdd_Long;
   private static UFunc.UImpl2 dm_csc_OpAdd_Int;
   private static UFunc.UImpl2 dm_csc_OpAdd_Double;
   private static UFunc.UImpl2 dm_csc_OpAdd_Float;
   private static UFunc.UImpl2 dm_csc_OpAdd_Long;
   private static UFunc.UImpl2 dm_csc_OpSub_Int;
   private static UFunc.UImpl2 dm_csc_OpSub_Double;
   private static UFunc.UImpl2 dm_csc_OpSub_Float;
   private static UFunc.UImpl2 dm_csc_OpSub_Long;
   private static UFunc.UImpl2 csc_dm_OpSub_Int;
   private static UFunc.UImpl2 csc_dm_OpSub_Double;
   private static UFunc.UImpl2 csc_dm_OpSub_Float;
   private static UFunc.UImpl2 csc_dm_OpSub_Long;
   private static UFunc.UImpl2 csc_csc_OpMulScalar_Int;
   private static UFunc.UImpl2 csc_csc_OpMulScalar_Double;
   private static UFunc.UImpl2 csc_csc_OpMulScalar_Float;
   private static UFunc.UImpl2 csc_csc_OpMulScalar_Long;
   private static UFunc.UImpl2 csc_csc_OpSub_Int;
   private static UFunc.UImpl2 csc_csc_OpSub_Double;
   private static UFunc.UImpl2 csc_csc_OpSub_Float;
   private static UFunc.UImpl2 csc_csc_OpSub_Long;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix;
   private static BinaryRegistry canMulM_V_Int;
   private static BinaryRegistry canMulM_V_Float;
   private static BinaryRegistry canMulM_V_Double;
   private static BinaryRegistry canMulM_V_Long;
   private static UFunc.UImpl2 canMulM_DV_Int;
   private static UFunc.UImpl2 canMulM_DV_Float;
   private static UFunc.UImpl2 canMulM_DV_Double;
   private static UFunc.UImpl2 canMulM_DV_Long;
   private static BinaryRegistry canMulM_SV_Int;
   private static BinaryRegistry canMulM_SV_Float;
   private static BinaryRegistry canMulM_SV_Double;
   private static BinaryRegistry canMulM_SV_Long;
   private static UFunc.UImpl2 canMulM_DM_Int;
   private static UFunc.UImpl2 canMulM_DM_Float;
   private static UFunc.UImpl2 canMulM_DM_Double;
   private static UFunc.UImpl2 canMulM_DM_Long;
   private static UFunc.UImpl2 canMulDM_M_Int;
   private static UFunc.UImpl2 canMulDM_M_Float;
   private static UFunc.UImpl2 canMulDM_M_Double;
   private static UFunc.UImpl2 canMulDM_M_Long;
   private static UFunc.UImpl2 canMulM_M_Int;
   private static UFunc.UImpl2 canMulM_M_Float;
   private static UFunc.UImpl2 canMulM_M_Double;
   private static UFunc.UImpl2 canMulM_M_Long;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpAdd;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpAdd;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpAdd;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpAdd;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpSub;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpSub;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpSub;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpSub;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpDiv;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpDiv;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpDiv;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpDiv;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpPow;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpPow;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpPow;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpPow;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMod;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMod;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMod;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMod;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMulScalar;
   private static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Int;
   private static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Float;
   private static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Double;
   private static UFunc.InPlaceImpl3 axpyCSC_DM_DM_Long;
   private static UFunc.UImpl2 impl_OpSolveMatrixBy_CSC_CSC_eq_CSC;
   private static volatile CSCMatrixOps_Ring.FrobeniusCSCProduct$ FrobeniusCSCProduct$module;
   private static transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;
   private static UFunc.InPlaceImpl2 setMV_D;
   private static UFunc.InPlaceImpl2 setMV_F;
   private static UFunc.InPlaceImpl2 setMV_I;
   private static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Double;
   private static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Float;
   private static DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Int;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Float;
   private static volatile DenseMatrixOps_FloatSpecialized.impl_OpMulMatrix_DMF_DVF_eq_DVF$ impl_OpMulMatrix_DMF_DVF_eq_DVF$module;
   private static UFunc.UImpl2 impl_OpMulMatrix_DVF_DMF_eq_DMF;
   private static volatile DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$ impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$module;
   private static volatile DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$ impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$module;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Int;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Double;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Float;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Long;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Int;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Double;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Float;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Long;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Int;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Double;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Float;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Long;
   private static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Int;
   private static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Long;
   private static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Float;
   private static UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Double;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpPow;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Int;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Double;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Float;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Long;
   private static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Int;
   private static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Double;
   private static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Float;
   private static UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Long;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Int;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Double;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Float;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Long;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpSub;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Int;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Double;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Float;
   private static UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Long;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpPow;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Int;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Double;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Float;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Long;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpSub;
   private static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Int;
   private static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Double;
   private static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Float;
   private static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Long;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Int;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Double;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Float;
   private static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Long;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow;
   private static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Int;
   private static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Long;
   private static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Double;
   private static UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Float;
   private static CanTraverseValues impl_CanTraverseValues_HV_Int;
   private static CanTraverseValues impl_CanTraverseValues_HV_Double;
   private static CanTraverseValues impl_CanTraverseValues_HV_Float;
   private static CanTraverseValues impl_CanTraverseValues_HV_Long;
   private static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_d;
   private static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_f;
   private static HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_i;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpPow;
   private static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Int;
   private static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Double;
   private static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Float;
   private static UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Long;
   private static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Int;
   private static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Double;
   private static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Float;
   private static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Long;
   private static UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Complex;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpDiv;
   private static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Int;
   private static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Double;
   private static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Float;
   private static UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Long;
   private static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Int;
   private static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Double;
   private static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Float;
   private static UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Long;
   private static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Int;
   private static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Double;
   private static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Float;
   private static UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Long;
   private static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Int;
   private static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Double;
   private static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Float;
   private static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Long;
   private static UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Complex;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSub;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Int;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Double;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Float;
   private static UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Long;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulMatrix;
   private static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Int;
   private static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Double;
   private static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Float;
   private static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Long;
   private static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Int;
   private static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Double;
   private static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Float;
   private static UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Long;
   private static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_d;
   private static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_f;
   private static SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_i;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Int;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Float;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Long;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Double;
   private static volatile DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DMD_eq_DMD$ impl_OpMulMatrix_DMD_DMD_eq_DMD$module;
   private static volatile DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DVD_eq_DVD$ impl_OpMulMatrix_DMD_DVD_eq_DVD$module;
   private static UFunc.UImpl2 impl_OpMulMatrix_DVD_DMD_eq_DMD;
   private static volatile DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$ impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$module;
   private static volatile DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$ impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$module;
   private static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Int;
   private static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Long;
   private static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Float;
   private static BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Double;
   private static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Int;
   private static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Long;
   private static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Float;
   private static BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Double;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Int;
   private static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Long;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpAdd;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpAdd;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpAdd;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpAdd;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpSub;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpSub;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpSub;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpSub;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpDiv;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpDiv;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpDiv;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpDiv;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpSet;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpSet;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpSet;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpSet;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpMod;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpMod;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpMod;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpMod;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpPow;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpPow;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpPow;
   private static UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpPow;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpAdd;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpAdd;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpAdd;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpAdd;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpSub;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpSub;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpSub;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpSub;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMulMatrix;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMulMatrix;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMulMatrix;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMulMatrix;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpDiv;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpDiv;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpDiv;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpDiv;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpSet;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpSet;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpSet;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpSet;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMod;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMod;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMod;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMod;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpPow;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpPow;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpPow;
   private static UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpPow;
   private static UFunc.UImpl2 op_DM_S_Int_OpAdd;
   private static UFunc.UImpl2 op_DM_S_Long_OpAdd;
   private static UFunc.UImpl2 op_DM_S_Float_OpAdd;
   private static UFunc.UImpl2 op_DM_S_Double_OpAdd;
   private static UFunc.UImpl2 op_DM_S_Int_OpSub;
   private static UFunc.UImpl2 op_DM_S_Long_OpSub;
   private static UFunc.UImpl2 op_DM_S_Float_OpSub;
   private static UFunc.UImpl2 op_DM_S_Double_OpSub;
   private static UFunc.UImpl2 op_DM_S_Int_OpMulScalar;
   private static UFunc.UImpl2 op_DM_S_Long_OpMulScalar;
   private static UFunc.UImpl2 op_DM_S_Float_OpMulScalar;
   private static UFunc.UImpl2 op_DM_S_Double_OpMulScalar;
   private static UFunc.UImpl2 op_DM_S_Int_OpMulMatrix;
   private static UFunc.UImpl2 op_DM_S_Long_OpMulMatrix;
   private static UFunc.UImpl2 op_DM_S_Float_OpMulMatrix;
   private static UFunc.UImpl2 op_DM_S_Double_OpMulMatrix;
   private static UFunc.UImpl2 op_DM_S_Int_OpMod;
   private static UFunc.UImpl2 op_DM_S_Long_OpMod;
   private static UFunc.UImpl2 op_DM_S_Float_OpMod;
   private static UFunc.UImpl2 op_DM_S_Double_OpMod;
   private static UFunc.UImpl2 op_DM_S_Int_OpDiv;
   private static UFunc.UImpl2 op_DM_S_Long_OpDiv;
   private static UFunc.UImpl2 op_DM_S_Float_OpDiv;
   private static UFunc.UImpl2 op_DM_S_Double_OpDiv;
   private static UFunc.UImpl2 op_DM_S_Int_OpPow;
   private static UFunc.UImpl2 op_DM_S_Long_OpPow;
   private static UFunc.UImpl2 op_DM_S_Float_OpPow;
   private static UFunc.UImpl2 op_DM_S_Double_OpPow;
   private static UFunc.UImpl2 s_dm_op_Int_OpAdd;
   private static UFunc.UImpl2 s_dm_op_Double_OpAdd;
   private static UFunc.UImpl2 s_dm_op_Float_OpAdd;
   private static UFunc.UImpl2 s_dm_op_Long_OpAdd;
   private static UFunc.UImpl2 s_dm_op_Int_OpSub;
   private static UFunc.UImpl2 s_dm_op_Double_OpSub;
   private static UFunc.UImpl2 s_dm_op_Float_OpSub;
   private static UFunc.UImpl2 s_dm_op_Long_OpSub;
   private static UFunc.UImpl2 s_dm_op_Int_OpMulScalar;
   private static UFunc.UImpl2 s_dm_op_Double_OpMulScalar;
   private static UFunc.UImpl2 s_dm_op_Float_OpMulScalar;
   private static UFunc.UImpl2 s_dm_op_Long_OpMulScalar;
   private static UFunc.UImpl2 s_dm_op_Int_OpMulMatrix;
   private static UFunc.UImpl2 s_dm_op_Double_OpMulMatrix;
   private static UFunc.UImpl2 s_dm_op_Float_OpMulMatrix;
   private static UFunc.UImpl2 s_dm_op_Long_OpMulMatrix;
   private static UFunc.UImpl2 s_dm_op_Int_OpDiv;
   private static UFunc.UImpl2 s_dm_op_Double_OpDiv;
   private static UFunc.UImpl2 s_dm_op_Float_OpDiv;
   private static UFunc.UImpl2 s_dm_op_Long_OpDiv;
   private static UFunc.UImpl2 s_dm_op_Int_OpMod;
   private static UFunc.UImpl2 s_dm_op_Double_OpMod;
   private static UFunc.UImpl2 s_dm_op_Float_OpMod;
   private static UFunc.UImpl2 s_dm_op_Long_OpMod;
   private static UFunc.UImpl2 s_dm_op_Int_OpPow;
   private static UFunc.UImpl2 s_dm_op_Double_OpPow;
   private static UFunc.UImpl2 s_dm_op_Float_OpPow;
   private static UFunc.UImpl2 s_dm_op_Long_OpPow;
   private static UFunc.UImpl2 op_DM_DM_Int_OpAdd;
   private static UFunc.UImpl2 op_DM_DM_Long_OpAdd;
   private static UFunc.UImpl2 op_DM_DM_Float_OpAdd;
   private static UFunc.UImpl2 op_DM_DM_Double_OpAdd;
   private static UFunc.UImpl2 op_DM_DM_Int_OpSub;
   private static UFunc.UImpl2 op_DM_DM_Long_OpSub;
   private static UFunc.UImpl2 op_DM_DM_Float_OpSub;
   private static UFunc.UImpl2 op_DM_DM_Double_OpSub;
   private static UFunc.UImpl2 op_DM_DM_Int_OpMulScalar;
   private static UFunc.UImpl2 op_DM_DM_Long_OpMulScalar;
   private static UFunc.UImpl2 op_DM_DM_Float_OpMulScalar;
   private static UFunc.UImpl2 op_DM_DM_Double_OpMulScalar;
   private static UFunc.UImpl2 op_DM_DM_Int_OpMod;
   private static UFunc.UImpl2 op_DM_DM_Long_OpMod;
   private static UFunc.UImpl2 op_DM_DM_Float_OpMod;
   private static UFunc.UImpl2 op_DM_DM_Double_OpMod;
   private static UFunc.UImpl2 op_DM_DM_Int_OpDiv;
   private static UFunc.UImpl2 op_DM_DM_Long_OpDiv;
   private static UFunc.UImpl2 op_DM_DM_Float_OpDiv;
   private static UFunc.UImpl2 op_DM_DM_Double_OpDiv;
   private static UFunc.UImpl2 op_DM_DM_Int_OpPow;
   private static UFunc.UImpl2 op_DM_DM_Long_OpPow;
   private static UFunc.UImpl2 op_DM_DM_Float_OpPow;
   private static UFunc.UImpl2 op_DM_DM_Double_OpPow;
   private static BinaryRegistry op_M_V_Int;
   private static BinaryRegistry op_M_V_Long;
   private static BinaryRegistry op_M_V_Float;
   private static BinaryRegistry op_M_V_Double;
   private static BinaryRegistry op_M_V_BigInt;
   private static BinaryRegistry op_M_V_Complex;
   private static BinaryRegistry op_M_M_Int;
   private static BinaryRegistry op_M_M_Long;
   private static BinaryRegistry op_M_M_Float;
   private static BinaryRegistry op_M_M_Double;
   private static BinaryRegistry op_M_M_BigInt;
   private static BinaryRegistry op_M_M_Complex;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpAdd;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpAdd;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpAdd;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpAdd;
   private static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpAdd;
   private static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpAdd;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpSub;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpSub;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpSub;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpSub;
   private static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpSub;
   private static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpSub;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpMulScalar;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpMulScalar;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpMulScalar;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpMulScalar;
   private static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpMulScalar;
   private static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpMulScalar;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpDiv;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpDiv;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpDiv;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpDiv;
   private static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpDiv;
   private static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpDiv;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpSet;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpSet;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpSet;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpSet;
   private static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpSet;
   private static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpSet;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpMod;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpMod;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpMod;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpMod;
   private static BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpMod;
   private static BinaryUpdateRegistry m_m_UpdateOp_Int_OpPow;
   private static BinaryUpdateRegistry m_m_UpdateOp_Double_OpPow;
   private static BinaryUpdateRegistry m_m_UpdateOp_Float_OpPow;
   private static BinaryUpdateRegistry m_m_UpdateOp_Long_OpPow;
   private static BinaryUpdateRegistry m_m_UpdateOp_Complex_OpPow;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpAdd;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpAdd;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpAdd;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpAdd;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpAdd;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpAdd;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpSub;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpSub;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpSub;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpSub;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpSub;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpSub;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpMulScalar;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpMulScalar;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpMulScalar;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpMulScalar;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMulScalar;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpMulScalar;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpMulMatrix;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpMulMatrix;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpMulMatrix;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpMulMatrix;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMulMatrix;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpMulMatrix;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpDiv;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpDiv;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpDiv;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpDiv;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpDiv;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpDiv;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpSet;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpSet;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpSet;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpSet;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpSet;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpSet;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpMod;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpMod;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpMod;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpMod;
   private static BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMod;
   private static BinaryUpdateRegistry m_s_UpdateOp_Int_OpPow;
   private static BinaryUpdateRegistry m_s_UpdateOp_Double_OpPow;
   private static BinaryUpdateRegistry m_s_UpdateOp_Float_OpPow;
   private static BinaryUpdateRegistry m_s_UpdateOp_Long_OpPow;
   private static BinaryUpdateRegistry m_s_UpdateOp_Complex_OpPow;
   private static BinaryRegistry op_M_S_Int_OpAdd;
   private static BinaryRegistry op_M_S_Long_OpAdd;
   private static BinaryRegistry op_M_S_Float_OpAdd;
   private static BinaryRegistry op_M_S_Double_OpAdd;
   private static BinaryRegistry op_M_S_BigInt_OpAdd;
   private static BinaryRegistry op_M_S_Complex_OpAdd;
   private static BinaryRegistry op_M_S_Int_OpSub;
   private static BinaryRegistry op_M_S_Long_OpSub;
   private static BinaryRegistry op_M_S_Float_OpSub;
   private static BinaryRegistry op_M_S_Double_OpSub;
   private static BinaryRegistry op_M_S_BigInt_OpSub;
   private static BinaryRegistry op_M_S_Complex_OpSub;
   private static BinaryRegistry op_M_S_Int_OpMulScalar;
   private static BinaryRegistry op_M_S_Long_OpMulScalar;
   private static BinaryRegistry op_M_S_Float_OpMulScalar;
   private static BinaryRegistry op_M_S_Double_OpMulScalar;
   private static BinaryRegistry op_M_S_BigInt_OpMulScalar;
   private static BinaryRegistry op_M_S_Complex_OpMulScalar;
   private static BinaryRegistry op_M_S_Int_OpMulMatrix;
   private static BinaryRegistry op_M_S_Long_OpMulMatrix;
   private static BinaryRegistry op_M_S_Float_OpMulMatrix;
   private static BinaryRegistry op_M_S_Double_OpMulMatrix;
   private static BinaryRegistry op_M_S_BigInt_OpMulMatrix;
   private static BinaryRegistry op_M_S_Complex_OpMulMatrix;
   private static BinaryRegistry op_M_S_Int_OpMod;
   private static BinaryRegistry op_M_S_Long_OpMod;
   private static BinaryRegistry op_M_S_Float_OpMod;
   private static BinaryRegistry op_M_S_Double_OpMod;
   private static BinaryRegistry op_M_S_BigInt_OpMod;
   private static BinaryRegistry op_M_S_Int_OpDiv;
   private static BinaryRegistry op_M_S_Long_OpDiv;
   private static BinaryRegistry op_M_S_Float_OpDiv;
   private static BinaryRegistry op_M_S_Double_OpDiv;
   private static BinaryRegistry op_M_S_BigInt_OpDiv;
   private static BinaryRegistry op_M_S_Complex_OpDiv;
   private static BinaryRegistry op_M_S_Int_OpPow;
   private static BinaryRegistry op_M_S_Long_OpPow;
   private static BinaryRegistry op_M_S_Float_OpPow;
   private static BinaryRegistry op_M_S_Double_OpPow;
   private static BinaryRegistry op_M_S_Complex_OpPow;
   private static BinaryRegistry op_S_M_Int_OpAdd;
   private static BinaryRegistry op_S_M_Long_OpAdd;
   private static BinaryRegistry op_S_M_Float_OpAdd;
   private static BinaryRegistry op_S_M_Double_OpAdd;
   private static BinaryRegistry op_S_M_BigInt_OpAdd;
   private static BinaryRegistry op_S_M_Complex_OpAdd;
   private static BinaryRegistry op_S_M_Int_OpSub;
   private static BinaryRegistry op_S_M_Long_OpSub;
   private static BinaryRegistry op_S_M_Float_OpSub;
   private static BinaryRegistry op_S_M_Double_OpSub;
   private static BinaryRegistry op_S_M_BigInt_OpSub;
   private static BinaryRegistry op_S_M_Complex_OpSub;
   private static BinaryRegistry op_S_M_Int_OpMulScalar;
   private static BinaryRegistry op_S_M_Long_OpMulScalar;
   private static BinaryRegistry op_S_M_Float_OpMulScalar;
   private static BinaryRegistry op_S_M_Double_OpMulScalar;
   private static BinaryRegistry op_S_M_BigInt_OpMulScalar;
   private static BinaryRegistry op_S_M_Complex_OpMulScalar;
   private static BinaryRegistry op_S_M_Int_OpMulMatrix;
   private static BinaryRegistry op_S_M_Long_OpMulMatrix;
   private static BinaryRegistry op_S_M_Float_OpMulMatrix;
   private static BinaryRegistry op_S_M_Double_OpMulMatrix;
   private static BinaryRegistry op_S_M_BigInt_OpMulMatrix;
   private static BinaryRegistry op_S_M_Complex_OpMulMatrix;
   private static BinaryRegistry op_S_M_Int_OpDiv;
   private static BinaryRegistry op_S_M_Long_OpDiv;
   private static BinaryRegistry op_S_M_Float_OpDiv;
   private static BinaryRegistry op_S_M_Double_OpDiv;
   private static BinaryRegistry op_S_M_BigInt_OpDiv;
   private static BinaryRegistry op_S_M_Complex_OpDiv;
   private static BinaryRegistry op_S_M_Int_OpMod;
   private static BinaryRegistry op_S_M_Long_OpMod;
   private static BinaryRegistry op_S_M_Float_OpMod;
   private static BinaryRegistry op_S_M_Double_OpMod;
   private static BinaryRegistry op_S_M_BigInt_OpMod;
   private static BinaryRegistry op_S_M_Int_OpPow;
   private static BinaryRegistry op_S_M_Long_OpPow;
   private static BinaryRegistry op_S_M_Float_OpPow;
   private static BinaryRegistry op_S_M_Double_OpPow;
   private static BinaryRegistry op_S_M_Complex_OpPow;
   private static BinaryRegistry op_M_DM_Int_OpAdd;
   private static BinaryRegistry op_M_DM_Long_OpAdd;
   private static BinaryRegistry op_M_DM_Float_OpAdd;
   private static BinaryRegistry op_M_DM_Double_OpAdd;
   private static BinaryRegistry op_M_DM_BigInt_OpAdd;
   private static BinaryRegistry op_M_DM_Complex_OpAdd;
   private static BinaryRegistry op_M_DM_Int_OpSub;
   private static BinaryRegistry op_M_DM_Long_OpSub;
   private static BinaryRegistry op_M_DM_Float_OpSub;
   private static BinaryRegistry op_M_DM_Double_OpSub;
   private static BinaryRegistry op_M_DM_BigInt_OpSub;
   private static BinaryRegistry op_M_DM_Complex_OpSub;
   private static BinaryRegistry op_M_DM_Int_OpMulScalar;
   private static BinaryRegistry op_M_DM_Long_OpMulScalar;
   private static BinaryRegistry op_M_DM_Float_OpMulScalar;
   private static BinaryRegistry op_M_DM_Double_OpMulScalar;
   private static BinaryRegistry op_M_DM_BigInt_OpMulScalar;
   private static BinaryRegistry op_M_DM_Complex_OpMulScalar;
   private static BinaryRegistry op_M_DM_Int_OpMod;
   private static BinaryRegistry op_M_DM_Long_OpMod;
   private static BinaryRegistry op_M_DM_Float_OpMod;
   private static BinaryRegistry op_M_DM_Double_OpMod;
   private static BinaryRegistry op_M_DM_BigInt_OpMod;
   private static BinaryRegistry op_M_DM_Int_OpDiv;
   private static BinaryRegistry op_M_DM_Long_OpDiv;
   private static BinaryRegistry op_M_DM_Float_OpDiv;
   private static BinaryRegistry op_M_DM_Double_OpDiv;
   private static BinaryRegistry op_M_DM_BigInt_OpDiv;
   private static BinaryRegistry op_M_DM_Complex_OpDiv;
   private static BinaryRegistry op_M_DM_Int_OpPow;
   private static BinaryRegistry op_M_DM_Long_OpPow;
   private static BinaryRegistry op_M_DM_Float_OpPow;
   private static BinaryRegistry op_M_DM_Double_OpPow;
   private static BinaryRegistry op_M_DM_Complex_OpPow;
   private static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Double;
   private static volatile DenseVector_DoubleOps.impl_scaleAdd_InPlace_DV_T_DV_Double$ impl_scaleAdd_InPlace_DV_T_DV_Double$module;
   private static UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Double;
   private static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Double;
   private static UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Double;
   private static volatile DenseVector_DoubleOps.canDotD$ canDotD$module;
   private static volatile DenseVector_FloatOps.impl_scaledAdd_InPlace_DV_S_DV_Float$ impl_scaledAdd_InPlace_DV_S_DV_Float$module;
   private static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Float;
   private static UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Float;
   private static UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Float;
   private static UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Float;
   private static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Float;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulMatrix;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpAdd;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSub;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMulScalar;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpDiv;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSet;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMod;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpPow;
   private static UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpPow;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpAdd;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSub;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulScalar;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulMatrix;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpDiv;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSet;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMod;
   private static UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMod;
   private static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Int;
   private static UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Long;
   private static UFunc.UImpl2 impl_zipValues_DV_DV_Int;
   private static UFunc.UImpl2 impl_zipValues_DV_DV_Double;
   private static UFunc.UImpl2 impl_zipValues_DV_DV_Float;
   private static UFunc.UImpl2 impl_zipValues_DV_DV_Long;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Int;
   private static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Long;
   private static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Int;
   private static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Double;
   private static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Float;
   private static UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Long;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpAdd;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpAdd;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpAdd;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpAdd;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSub;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSub;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSub;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSub;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMulScalar;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMulScalar;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMulScalar;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMulScalar;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpDiv;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpDiv;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpDiv;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpDiv;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSet;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSet;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSet;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSet;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMod;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMod;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMod;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMod;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Int_OpPow;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Double_OpPow;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Float_OpPow;
   private static BinaryRegistry impl_Op_DV_V_eq_V_Long_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Int_OpAdd;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Double_OpAdd;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Float_OpAdd;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Long_OpAdd;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Int_OpSub;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Double_OpSub;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Float_OpSub;
   private static BinaryRegistry impl_Op_V_V_eq_V_idempotent_Long_OpSub;
   private static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Int;
   private static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Double;
   private static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Float;
   private static BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Long;
   private static BinaryRegistry impl_Op_V_V_eq_V_Int_OpDiv;
   private static BinaryRegistry impl_Op_V_V_eq_V_Double_OpDiv;
   private static BinaryRegistry impl_Op_V_V_eq_V_Float_OpDiv;
   private static BinaryRegistry impl_Op_V_V_eq_V_Long_OpDiv;
   private static BinaryRegistry impl_Op_V_V_eq_V_Int_OpSet;
   private static BinaryRegistry impl_Op_V_V_eq_V_Double_OpSet;
   private static BinaryRegistry impl_Op_V_V_eq_V_Float_OpSet;
   private static BinaryRegistry impl_Op_V_V_eq_V_Long_OpSet;
   private static BinaryRegistry impl_Op_V_V_eq_V_Int_OpMod;
   private static BinaryRegistry impl_Op_V_V_eq_V_Double_OpMod;
   private static BinaryRegistry impl_Op_V_V_eq_V_Float_OpMod;
   private static BinaryRegistry impl_Op_V_V_eq_V_Long_OpMod;
   private static BinaryRegistry impl_Op_V_V_eq_V_Int_OpPow;
   private static BinaryRegistry impl_Op_V_V_eq_V_Double_OpPow;
   private static BinaryRegistry impl_Op_V_V_eq_V_Float_OpPow;
   private static BinaryRegistry impl_Op_V_V_eq_V_Long_OpPow;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpAdd;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpAdd;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpAdd;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpAdd;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpSub;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpSub;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpSub;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpSub;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpMulScalar;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpMulScalar;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpMulScalar;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpMulScalar;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpMulMatrix;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpMulMatrix;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpMulMatrix;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpMulMatrix;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpDiv;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpDiv;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpDiv;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpDiv;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpSet;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpSet;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpSet;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpSet;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpMod;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpMod;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpMod;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpMod;
   private static BinaryRegistry impl_Op_V_S_eq_V_Int_OpPow;
   private static BinaryRegistry impl_Op_V_S_eq_V_Double_OpPow;
   private static BinaryRegistry impl_Op_V_S_eq_V_Float_OpPow;
   private static BinaryRegistry impl_Op_V_S_eq_V_Long_OpPow;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpAdd;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpAdd;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpAdd;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpAdd;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpSub;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpSub;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpSub;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpSub;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpMulScalar;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpMulScalar;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpMulScalar;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpMulScalar;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpMulMatrix;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpMulMatrix;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpMulMatrix;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpMulMatrix;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpDiv;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpDiv;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpDiv;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpDiv;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpSet;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpSet;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpSet;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpSet;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpMod;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpMod;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpMod;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpMod;
   private static BinaryRegistry impl_Op_S_V_eq_V_Int_OpPow;
   private static BinaryRegistry impl_Op_S_V_eq_V_Double_OpPow;
   private static BinaryRegistry impl_Op_S_V_eq_V_Float_OpPow;
   private static BinaryRegistry impl_Op_S_V_eq_V_Long_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Int_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Double_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Float_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Long_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Int_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Double_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Float_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Long_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpAdd;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpSub;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMulScalar;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMulMatrix;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMulMatrix;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMulMatrix;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMulMatrix;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpDiv;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpSet;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMod;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpPow;
   private static BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpPow;
   private static BinaryRegistry impl_OpMulInner_V_V_eq_S_Int;
   private static BinaryRegistry impl_OpMulInner_V_V_eq_S_Long;
   private static BinaryRegistry impl_OpMulInner_V_V_eq_S_Float;
   private static BinaryRegistry impl_OpMulInner_V_V_eq_S_Double;
   private static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Int;
   private static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Double;
   private static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Float;
   private static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Long;
   private static BinaryRegistry zipValuesImpl_V_V_Int;
   private static BinaryRegistry zipValuesImpl_V_V_Double;
   private static BinaryRegistry zipValuesImpl_V_V_Float;
   private static BinaryRegistry zipValuesImpl_V_V_Long;
   private static volatile Vector_GenericOps.ZippedVectorValues$ ZippedVectorValues$module;

   static {
      GenericOpsLowPrio3.$init$(MODULE$);
      CastOps.$init$(MODULE$);
      GenericOpsLowPrio.$init$(MODULE$);
      GenericOps.$init$(MODULE$);
      Vector_TraversalOps.$init$(MODULE$);
      Vector_GenericOps.$init$(MODULE$);
      VectorExpandOps.$init$(MODULE$);
      TensorLowPrio.$init$(MODULE$);
      TransposeOps_LowPrio2.$init$(MODULE$);
      TransposeOps_LowPrio.$init$(MODULE$);
      TransposeOps_Generic.$init$(MODULE$);
      DenseMatrix_TransposeOps.$init$(MODULE$);
      TransposeOps_Complex.$init$(MODULE$);
      CSCMatrix_TransposeOps.$init$(MODULE$);
      DenseVector_GenericOps.$init$(MODULE$);
      DenseVector_TraversalOps.$init$(MODULE$);
      DenseVector_SlicingOps.$init$(MODULE$);
      DenseVector_Vector_ExpandOps.$init$(MODULE$);
      DenseVectorExpandOps.$init$(MODULE$);
      DenseVector_ComparisonOps.$init$(MODULE$);
      DenseVector_FloatOps.$init$(MODULE$);
      DenseVector_DoubleOps.$init$(MODULE$);
      MatrixGenericOps.$init$(MODULE$);
      MatrixExpandedOps.$init$(MODULE$);
      MatrixMultOps.$init$(MODULE$);
      DenseMatrix_GenericOps.$init$(MODULE$);
      DenseMatrix_TraversalOps.$init$(MODULE$);
      DenseMatrixExpandedOps.$init$(MODULE$);
      DenseMatrixMultOps.$init$(MODULE$);
      LowPriorityDenseMatrix1.$init$(MODULE$);
      DenseMatrix_SlicingOps_LowPrio.$init$(MODULE$);
      DenseMatrix_SlicingOps.$init$(MODULE$);
      DenseMatrix_SetOps.$init$(MODULE$);
      DenseMatrixMultiplyOps.$init$(MODULE$);
      SparseVector_DenseMatrixOps.$init$(MODULE$);
      SparseVector_GenericOps.$init$(MODULE$);
      SparseVectorExpandOps.$init$(MODULE$);
      DenseVector_SparseVector_Ops.$init$(MODULE$);
      SparseVector_TraversalOps.$init$(MODULE$);
      SparseVector_DenseVector_Ops.$init$(MODULE$);
      HashVector_GenericOps.$init$(MODULE$);
      HashVectorExpandOps.$init$(MODULE$);
      DenseVector_HashVector_Ops.$init$(MODULE$);
      HashVector_DenseVector_Ops.$init$(MODULE$);
      HashVector_SparseVector_Ops.$init$(MODULE$);
      SparseVector_HashVector_Ops.$init$(MODULE$);
      DenseMatrix_ComparisonOps.$init$(MODULE$);
      DenseMatrixOps_FloatSpecialized.$init$(MODULE$);
      DenseMatrixOps.$init$(MODULE$);
      SerializableLogging.$init$(MODULE$);
      CSCMatrixOpsLowPrio.$init$(MODULE$);
      CSCMatrixOps_Ring.$init$(MODULE$);
      CSCMatrixExpandedOps.$init$(MODULE$);
      CSCMatrixOps.$init$(MODULE$);
      BitVectorOps.$init$(MODULE$);
      MappingUFuncLowPrio.$init$(MODULE$);
      MappingUFuncOps.$init$(MODULE$);
      ZeroPreservingUFuncLowPrio.$init$(MODULE$);
      ZeroPreservingUFuncOps.$init$(MODULE$);
      BroadcastedColumnsOps.$init$(MODULE$);
      BroadcastedRowsOps.$init$(MODULE$);
   }

   public CanMapValues canMapValues_BRows(final CanCollapseAxis cc) {
      return BroadcastedRowsOps.canMapValues_BRows$(this, cc);
   }

   public UFunc.UImpl broadcastOp_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return BroadcastedRowsOps.broadcastOp_BRows$(this, handhold, op, cc);
   }

   public UFunc.InPlaceImpl broadcastInplaceOp_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return BroadcastedRowsOps.broadcastInplaceOp_BRows$(this, handhold, op, cc);
   }

   public UFunc.UImpl2 broadcastOp2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return BroadcastedRowsOps.broadcastOp2_BRows$(this, handhold, op, cc);
   }

   public UFunc.UImpl2 broadcastOp2_2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return BroadcastedRowsOps.broadcastOp2_2_BRows$(this, handhold, op, cc);
   }

   public UFunc.InPlaceImpl2 broadcastInplaceOp2_BRows(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return BroadcastedRowsOps.broadcastInplaceOp2_BRows$(this, handhold, op, cc);
   }

   public CanForeachValues canForeachRows_BRows(final CanTraverseAxis iter) {
      return BroadcastedRowsOps.canForeachRows_BRows$(this, iter);
   }

   public CanMapValues canMapValues_BCols(final CanCollapseAxis cc) {
      return BroadcastedColumnsOps.canMapValues_BCols$(this, cc);
   }

   public UFunc.UImpl broadcastOp_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl op, final CanCollapseAxis cc) {
      return BroadcastedColumnsOps.broadcastOp_BCols$(this, handhold, op, cc);
   }

   public UFunc.InPlaceImpl broadcastInplaceOp_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl op, final CanTraverseAxis cc) {
      return BroadcastedColumnsOps.broadcastInplaceOp_BCols$(this, handhold, op, cc);
   }

   public UFunc.UImpl2 broadcastOp2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return BroadcastedColumnsOps.broadcastOp2_BCols$(this, handhold, op, cc);
   }

   public UFunc.UImpl2 broadcastOp2_2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 op, final CanCollapseAxis cc) {
      return BroadcastedColumnsOps.broadcastOp2_2_BCols$(this, handhold, op, cc);
   }

   public UFunc.InPlaceImpl2 broadcastInplaceOp2_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl2 op, final CanTraverseAxis cc) {
      return BroadcastedColumnsOps.broadcastInplaceOp2_BCols$(this, handhold, op, cc);
   }

   public UFunc.UImpl3 broadcastOp3_1_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl3 op, final CanCollapseAxis cc) {
      return BroadcastedColumnsOps.broadcastOp3_1_BCols$(this, handhold, op, cc);
   }

   public UFunc.InPlaceImpl3 broadcastInplaceOp3_1_BCols(final CanCollapseAxis.HandHold handhold, final UFunc.InPlaceImpl3 op, final CanTraverseAxis cc) {
      return BroadcastedColumnsOps.broadcastInplaceOp3_1_BCols$(this, handhold, op, cc);
   }

   public CanForeachValues canForeachColumns_BCols(final CanTraverseAxis iter) {
      return BroadcastedColumnsOps.canForeachColumns_BCols$(this, iter);
   }

   public UFunc.UImpl fromLowOrderCanMapActiveValues(final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return ZeroPreservingUFuncOps.fromLowOrderCanMapActiveValues$(this, handhold, impl, canMapValues);
   }

   public UFunc.UImpl2 canMapActiveV1DV(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return ZeroPreservingUFuncOps.canMapActiveV1DV$(this, handhold, impl, canMapValues);
   }

   public UFunc.InPlaceImpl canTransformActiveValuesUFunc(final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return ZeroPreservingUFuncOps.canTransformActiveValuesUFunc$(this, canTransform, impl);
   }

   public UFunc.InPlaceImpl2 canTransformActiveValuesUFunc2_T(final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return ZeroPreservingUFuncOps.canTransformActiveValuesUFunc2_T$(this, canTransform, impl);
   }

   public UFunc.UImpl2 canMapV2ActiveValues(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return ZeroPreservingUFuncLowPrio.canMapV2ActiveValues$(this, handhold, impl, canMapValues);
   }

   public UFunc.UImpl2 canZipMapValuesImpl_T(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return MappingUFuncOps.canZipMapValuesImpl_T$(this, handhold, impl, canZipMapValues);
   }

   public UFunc.InPlaceImpl canTransformValuesUFunc_T(final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return MappingUFuncOps.canTransformValuesUFunc_T$(this, canTransform, impl);
   }

   public UFunc.InPlaceImpl2 canTransformValuesUFunc2_T(final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return MappingUFuncOps.canTransformValuesUFunc2_T$(this, canTransform, impl);
   }

   public UFunc.UImpl fromLowOrderCanMapValues(final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return MappingUFuncOps.fromLowOrderCanMapValues$(this, handhold, impl, canMapValues);
   }

   public UFunc.UImpl2 canMapV1DV(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return MappingUFuncOps.canMapV1DV$(this, handhold, impl, canMapValues);
   }

   public UFunc.UImpl2 canMapV2Values(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return MappingUFuncLowPrio.canMapV2Values$(this, handhold, impl, canMapValues);
   }

   public UFunc.InPlaceImpl3 axpy_Int(final .less.colon.less ev) {
      return BitVectorOps.axpy_Int$(this, ev);
   }

   public UFunc.InPlaceImpl3 axpy_Double(final .less.colon.less ev) {
      return BitVectorOps.axpy_Double$(this, ev);
   }

   public UFunc.InPlaceImpl3 axpy_Float(final .less.colon.less ev) {
      return BitVectorOps.axpy_Float$(this, ev);
   }

   public UFunc.InPlaceImpl3 axpy_Long(final .less.colon.less ev) {
      return BitVectorOps.axpy_Long$(this, ev);
   }

   public UFunc.InPlaceImpl3 axpyGen(final .less.colon.less ev, final Semiring semi) {
      return BitVectorOps.axpyGen$(this, ev, semi);
   }

   public UFunc.UImpl2 canDot_Other_BV(final UFunc.UImpl2 op) {
      return BitVectorOps.canDot_Other_BV$(this, op);
   }

   public CSCMatrixOps.CanCopyCSCMatrix CSC_canCopy(final ClassTag evidence$3, final Zero evidence$4) {
      return CSCMatrixOps.CSC_canCopy$(this, evidence$3, evidence$4);
   }

   public CSCMatrixOps.CanCopyCSCMatrix CSC_canCopy$mDc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return CSCMatrixOps.CSC_canCopy$mDc$sp$(this, evidence$3, evidence$4);
   }

   public CSCMatrixOps.CanCopyCSCMatrix CSC_canCopy$mFc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return CSCMatrixOps.CSC_canCopy$mFc$sp$(this, evidence$3, evidence$4);
   }

   public CSCMatrixOps.CanCopyCSCMatrix CSC_canCopy$mIc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return CSCMatrixOps.CSC_canCopy$mIc$sp$(this, evidence$3, evidence$4);
   }

   public CSCMatrixOps.CanCopyCSCMatrix CSC_canCopy$mJc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return CSCMatrixOps.CSC_canCopy$mJc$sp$(this, evidence$3, evidence$4);
   }

   public CanCreateZerosLike CSC_canCreateZerosLike(final ClassTag evidence$5, final Zero evidence$6) {
      return CSCMatrixOps.CSC_canCreateZerosLike$(this, evidence$5, evidence$6);
   }

   public CanMapValues CSC_canMapValues(final ClassTag evidence$7, final Semiring evidence$8) {
      return CSCMatrixOps.CSC_canMapValues$(this, evidence$7, evidence$8);
   }

   public ScalarOf CSC_scalarOf() {
      return CSCMatrixOps.CSC_scalarOf$(this);
   }

   public CanTraverseValues CSC_canIterateValues() {
      return CSCMatrixOps.CSC_canIterateValues$(this);
   }

   public CanTraverseKeyValuePairs CSC_canIterateKeysValues(final Zero evidence$9) {
      return CSCMatrixOps.CSC_canIterateKeysValues$(this, evidence$9);
   }

   public UFunc.UImpl2 canMul_SV_CSC_eq_CSC(final UFunc.UImpl2 op, final Zero zero) {
      return CSCMatrixExpandedOps.canMul_SV_CSC_eq_CSC$(this, op, zero);
   }

   public UFunc.UImpl2 canMul_SVt_CSC_eq_SVt(final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return CSCMatrixExpandedOps.canMul_SVt_CSC_eq_SVt$(this, op, zero, ct);
   }

   public UFunc.UImpl2 dm_csc_OpAdd_Semi(final Semiring evidence$10, final ClassTag evidence$11) {
      return CSCMatrixExpandedOps.dm_csc_OpAdd_Semi$(this, evidence$10, evidence$11);
   }

   public UFunc.UImpl2 csc_dm_Semi(final Semiring evidence$12, final ClassTag evidence$13) {
      return CSCMatrixExpandedOps.csc_dm_Semi$(this, evidence$12, evidence$13);
   }

   public UFunc.UImpl csc_OpNeg(final Ring evidence$14, final ClassTag evidence$15) {
      return CSCMatrixOps_Ring.csc_OpNeg$(this, evidence$14, evidence$15);
   }

   public UFunc.InPlaceImpl3 cscScaleAdd(final Semiring evidence$16, final ClassTag evidence$17) {
      return CSCMatrixOps_Ring.cscScaleAdd$(this, evidence$16, evidence$17);
   }

   public BinaryRegistry canMulM_V_Semiring(final Semiring evidence$18, final Zero evidence$19, final ClassTag evidence$20) {
      return CSCMatrixOps_Ring.canMulM_V_Semiring$(this, evidence$18, evidence$19, evidence$20);
   }

   public BinaryRegistry canMulM_SV_Semiring(final Semiring evidence$21, final Zero evidence$22, final ClassTag evidence$23) {
      return CSCMatrixOps_Ring.canMulM_SV_Semiring$(this, evidence$21, evidence$22, evidence$23);
   }

   public UFunc.UImpl2 canMulM_DM_Semiring(final Semiring evidence$24, final Zero evidence$25, final ClassTag evidence$26) {
      return CSCMatrixOps_Ring.canMulM_DM_Semiring$(this, evidence$24, evidence$25, evidence$26);
   }

   public UFunc.UImpl2 canMulDM_M_Semiring(final Semiring evidence$27, final Zero evidence$28, final ClassTag evidence$29) {
      return CSCMatrixOps_Ring.canMulDM_M_Semiring$(this, evidence$27, evidence$28, evidence$29);
   }

   public UFunc.UImpl2 canMulM_M_Semiring(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return CSCMatrixOps_Ring.canMulM_M_Semiring$(this, evidence$30, evidence$31, evidence$32);
   }

   public CanZipMapValues zipMapVals(final ClassTag evidence$33, final Semiring evidence$34, final Zero evidence$35) {
      return CSCMatrixOps_Ring.zipMapVals$(this, evidence$33, evidence$34, evidence$35);
   }

   public CanZipMapKeyValues zipMapKeyVals(final ClassTag evidence$36, final Semiring evidence$37, final Zero evidence$38) {
      return CSCMatrixOps_Ring.zipMapKeyVals$(this, evidence$36, evidence$37, evidence$38);
   }

   public UFunc.UImpl2 canAddM_S_Semiring(final Semiring evidence$39, final ClassTag evidence$40) {
      return CSCMatrixOps_Ring.canAddM_S_Semiring$(this, evidence$39, evidence$40);
   }

   public UFunc.UImpl2 canSubM_S_Ring(final Ring evidence$41, final ClassTag evidence$42) {
      return CSCMatrixOps_Ring.canSubM_S_Ring$(this, evidence$41, evidence$42);
   }

   public UFunc.UImpl2 canSetM_S_Semiring(final Semiring evidence$43, final ClassTag evidence$44) {
      return CSCMatrixOps_Ring.canSetM_S_Semiring$(this, evidence$43, evidence$44);
   }

   public UFunc.UImpl2 canMulM_S_Ring_OpMulMatrix(final Ring evidence$45, final ClassTag evidence$46) {
      return CSCMatrixOps_Ring.canMulM_S_Ring_OpMulMatrix$(this, evidence$45, evidence$46);
   }

   public UFunc.UImpl2 canMulM_S_Ring_OpMulScalar(final Ring evidence$47, final ClassTag evidence$48) {
      return CSCMatrixOps_Ring.canMulM_S_Ring_OpMulScalar$(this, evidence$47, evidence$48);
   }

   public UFunc.UImpl2 CSCMatrixCanMulScalarM_M_Semiring(final Semiring evidence$49, final ClassTag evidence$50, final Zero evidence$51) {
      return CSCMatrixOps_Ring.CSCMatrixCanMulScalarM_M_Semiring$(this, evidence$49, evidence$50, evidence$51);
   }

   public UFunc.UImpl2 CSCMatrixCanAdd_M_M_Semiring(final Semiring evidence$52, final Zero evidence$53, final ClassTag evidence$54) {
      return CSCMatrixOps_Ring.CSCMatrixCanAdd_M_M_Semiring$(this, evidence$52, evidence$53, evidence$54);
   }

   public UFunc.UImpl2 CSCMatrixCanSubM_M_Ring(final Ring evidence$55, final Zero evidence$56, final ClassTag evidence$57) {
      return CSCMatrixOps_Ring.CSCMatrixCanSubM_M_Ring$(this, evidence$55, evidence$56, evidence$57);
   }

   public UFunc.UImpl2 csc_T_Op_OpDiv(final Field evidence$58, final ClassTag evidence$59) {
      return CSCMatrixOps_Ring.csc_T_Op_OpDiv$(this, evidence$58, evidence$59);
   }

   public UFunc.UImpl2 csc_T_Op_OpMod(final Field evidence$60, final ClassTag evidence$61) {
      return CSCMatrixOps_Ring.csc_T_Op_OpMod$(this, evidence$60, evidence$61);
   }

   public UFunc.UImpl2 csc_T_Op_OpPow(final Field evidence$62, final ClassTag evidence$63) {
      return CSCMatrixOps_Ring.csc_T_Op_OpPow$(this, evidence$62, evidence$63);
   }

   public UFunc.UImpl2 csc_csc_BadOp_OpDiv(final Field evidence$64, final ClassTag evidence$65) {
      return CSCMatrixOps_Ring.csc_csc_BadOp_OpDiv$(this, evidence$64, evidence$65);
   }

   public UFunc.UImpl2 csc_csc_BadOp_OpMod(final Field evidence$66, final ClassTag evidence$67) {
      return CSCMatrixOps_Ring.csc_csc_BadOp_OpMod$(this, evidence$66, evidence$67);
   }

   public UFunc.UImpl2 csc_csc_BadOp_OpPow(final Field evidence$68, final ClassTag evidence$69) {
      return CSCMatrixOps_Ring.csc_csc_BadOp_OpPow$(this, evidence$68, evidence$69);
   }

   public UFunc.UImpl2 CSCMatrixCanSetM_M_Semiring(final Semiring evidence$70, final ClassTag evidence$71) {
      return CSCMatrixOps_Ring.CSCMatrixCanSetM_M_Semiring$(this, evidence$70, evidence$71);
   }

   public UFunc.InPlaceImpl2 updateFromPure_CSC(final UFunc.UImpl2 op) {
      return CSCMatrixOps_Ring.updateFromPure_CSC$(this, op);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(final Field evidence$72, final ClassTag evidence$73) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpAdd$(this, evidence$72, evidence$73);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSub(final Field evidence$74, final ClassTag evidence$75) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpSub$(this, evidence$74, evidence$75);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar(final Field evidence$76, final ClassTag evidence$77) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar$(this, evidence$76, evidence$77);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSet(final Field evidence$78, final ClassTag evidence$79) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpSet$(this, evidence$78, evidence$79);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpDiv(final Field evidence$80, final ClassTag evidence$81) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpDiv$(this, evidence$80, evidence$81);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpPow(final Field evidence$82, final ClassTag evidence$83) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpPow$(this, evidence$82, evidence$83);
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMod(final Field evidence$84, final ClassTag evidence$85) {
      return CSCMatrixOps_Ring.impl_Op_CSC_CSC_eq_CSC_lift_OpMod$(this, evidence$84, evidence$85);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulMatrix(final Field evidence$86, final ClassTag evidence$87) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpMulMatrix$(this, evidence$86, evidence$87);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSet(final Field evidence$88, final ClassTag evidence$89) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpSet$(this, evidence$88, evidence$89);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSub(final Field evidence$90, final ClassTag evidence$91) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpSub$(this, evidence$90, evidence$91);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpAdd(final Field evidence$92, final ClassTag evidence$93) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpAdd$(this, evidence$92, evidence$93);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulScalar(final Field evidence$94, final ClassTag evidence$95) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpMulScalar$(this, evidence$94, evidence$95);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpDiv(final Field evidence$96, final ClassTag evidence$97) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpDiv$(this, evidence$96, evidence$97);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMod(final Field evidence$98, final ClassTag evidence$99) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpMod$(this, evidence$98, evidence$99);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpPow(final Field evidence$100, final ClassTag evidence$101) {
      return CSCMatrixOps_Ring.impl_Op_InPlace_CSC_T_lift_OpPow$(this, evidence$100, evidence$101);
   }

   public UFunc.UImpl2 impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD(final UFunc.UImpl2 multMV, final MutableInnerProductVectorSpace ispace) {
      return CSCMatrixOps_Ring.impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD$(this, multMV, ispace);
   }

   public UFunc.UImpl2 canMulM_M_def(final .less.colon.less bb, final UFunc.UImpl2 op) {
      return CSCMatrixOpsLowPrio.canMulM_M_def$(this, bb, op);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public CanCollapseAxis canMapRows_DM(final ClassTag evidence$1, final Zero evidence$2, final UFunc.InPlaceImpl2 implSet) {
      return DenseMatrixOps.canMapRows_DM$(this, evidence$1, evidence$2, implSet);
   }

   public CanCollapseAxis.HandHold handholdCanMapRows_DM() {
      return DenseMatrixOps.handholdCanMapRows_DM$(this);
   }

   public CanCollapseAxis canMapRowsBitVector_DM(final ClassTag evidence$3, final Zero evidence$4) {
      return DenseMatrixOps.canMapRowsBitVector_DM$(this, evidence$3, evidence$4);
   }

   public CanCollapseAxis canMapCols_DM(final ClassTag evidence$5, final Zero evidence$6, final UFunc.InPlaceImpl2 implSet) {
      return DenseMatrixOps.canMapCols_DM$(this, evidence$5, evidence$6, implSet);
   }

   public CanCollapseAxis.HandHold handholdCanMapCols_DM() {
      return DenseMatrixOps.handholdCanMapCols_DM$(this);
   }

   public CanCollapseAxis canMapColsBitVector_DM(final ClassTag evidence$7, final Zero evidence$8) {
      return DenseMatrixOps.canMapColsBitVector_DM$(this, evidence$7, evidence$8);
   }

   public CanTraverseAxis canTraverseCols_DM() {
      return DenseMatrixOps.canTraverseCols_DM$(this);
   }

   public CanTraverseAxis canTraverseRows_DM() {
      return DenseMatrixOps.canTraverseRows_DM$(this);
   }

   public CanIterateAxis canIterateCols_DM() {
      return DenseMatrixOps.canIterateCols_DM$(this);
   }

   public CanIterateAxis canIterateRows_DM() {
      return DenseMatrixOps.canIterateRows_DM$(this);
   }

   public DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM(final ClassTag evidence$10) {
      return DenseMatrixOps.zipMap_DM$(this, evidence$10);
   }

   public DenseMatrixOps.CanZipMapKeyValuesDenseMatrix zipMapKV_DM(final ClassTag evidence$12) {
      return DenseMatrixOps.zipMapKV_DM$(this, evidence$12);
   }

   public UFunc.UImpl canDim_DM() {
      return DenseMatrixOps.canDim_DM$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Int_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Double_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Float_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Long_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Int_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Double_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Float_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Long_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Int_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Double_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Float_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Long_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Int_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Double_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Float_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Long_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Int_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Double_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Float_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Long_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Int_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Double_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Float_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_DM_eq_DMBool_Long_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe() {
      return DenseMatrix_ComparisonOps.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe$(this);
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_HV_eq_T(final UFunc.UImpl2 op) {
      return SparseVector_HashVector_Ops.impl_OpMulInner_SV_HV_eq_T$(this, op);
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Int() {
      return HashVector_DenseVector_Ops.impl_OpMulInner_HV_DV_eq_T_Int$(this);
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Float() {
      return HashVector_DenseVector_Ops.impl_OpMulInner_HV_DV_eq_T_Float$(this);
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Double() {
      return HashVector_DenseVector_Ops.impl_OpMulInner_HV_DV_eq_T_Double$(this);
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Long() {
      return HashVector_DenseVector_Ops.impl_OpMulInner_HV_DV_eq_T_Long$(this);
   }

   public CanTraverseValues impl_CanTraverseValues_HV_Generic() {
      return HashVectorExpandOps.impl_CanTraverseValues_HV_Generic$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Generic() {
      return HashVector_GenericOps.impl_OpSet_InPlace_HV_S_Generic$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_V_Generic() {
      return HashVector_GenericOps.impl_OpSet_InPlace_HV_V_Generic$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Generic() {
      return HashVector_GenericOps.impl_OpSet_InPlace_HV_HV_Generic$(this);
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_V_HV(final Semiring evidence$15) {
      return HashVector_GenericOps.impl_scaleAdd_InPlace_HV_V_HV$(this, evidence$15);
   }

   public UFunc.UImpl2 impl_OpAdd_HV_S_eq_HV_Generic(final Semiring semi) {
      return HashVector_GenericOps.impl_OpAdd_HV_S_eq_HV_Generic$(this, semi);
   }

   public UFunc.UImpl2 impl_OpSub_HV_S_eq_HV_Generic(final Ring ring) {
      return HashVector_GenericOps.impl_OpSub_HV_S_eq_HV_Generic$(this, ring);
   }

   public HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap(final ClassTag evidence$18, final Zero evidence$19) {
      return HashVector_GenericOps.HV_zipMap$(this, evidence$18, evidence$19);
   }

   public HashVector_GenericOps.CanZipMapKeyValuesHashVector HV_zipMapKV(final ClassTag evidence$22, final Zero evidence$23) {
      return HashVector_GenericOps.HV_zipMapKV$(this, evidence$22, evidence$23);
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_HV_Generic(final Field field, final ClassTag ct) {
      return HashVector_GenericOps.impl_OpMulScalar_InPlace_HV_HV_Generic$(this, field, ct);
   }

   public UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_HV_Generic(final Field field) {
      return HashVector_GenericOps.impl_OpDiv_InPlace_HV_HV_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Generic(final Semiring field) {
      return HashVector_GenericOps.impl_OpMulScalar_InPlace_HV_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_S_Generic(final Field field) {
      return HashVector_GenericOps.impl_OpDiv_InPlace_HV_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpPow_InPlace_HV_S_Generic(final UFunc.UImpl2 pow) {
      return HashVector_GenericOps.impl_OpPow_InPlace_HV_S_Generic$(this, pow);
   }

   public CanTraverseValues canIterateValues_SV() {
      return SparseVector_TraversalOps.canIterateValues_SV$(this);
   }

   public UFunc.UImpl2 liftCSCOpToSVransposeOp(final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return SparseVectorExpandOps.liftCSCOpToSVransposeOp$(this, op, zero, ct);
   }

   public UFunc.UImpl2 implSubOp_SV_SV_eq_SV(final Ring evidence$1, final ClassTag evidence$2) {
      return SparseVectorExpandOps.implSubOp_SV_SV_eq_SV$(this, evidence$1, evidence$2);
   }

   public UFunc.UImpl2 implAddOp_SV_SV_eq_SV(final Semiring evidence$3, final ClassTag evidence$4) {
      return SparseVectorExpandOps.implAddOp_SV_SV_eq_SV$(this, evidence$3, evidence$4);
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T(final ClassTag evidence$5, final Zero evidence$6, final Semiring evidence$7) {
      return SparseVectorExpandOps.impl_OpMulInner_SV_SV_eq_T$(this, evidence$5, evidence$6, evidence$7);
   }

   public SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap(final ClassTag evidence$11, final Zero evidence$12, final Semiring evidence$13) {
      return SparseVectorExpandOps.zipMap$(this, evidence$11, evidence$12, evidence$13);
   }

   public SparseVectorExpandOps.CanZipMapKeyValuesSparseVector zipMapKV(final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return SparseVectorExpandOps.zipMapKV$(this, evidence$17, evidence$18, evidence$19);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_SV_Generic() {
      return SparseVector_GenericOps.impl_OpSet_InPlace_SV_SV_Generic$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_S_Generic(final Zero evidence$4) {
      return SparseVector_GenericOps.impl_OpSet_InPlace_SV_S_Generic$(this, evidence$4);
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic(final Semiring evidence$5, final UFunc.UImpl2 op) {
      return SparseVector_GenericOps.impl_Op_SV_S_eq_SV_Generic$(this, evidence$5, op);
   }

   public UFunc.UImpl2 impl_OpAdd_SV_S_eq_SV_Generic(final Semiring evidence$6) {
      return SparseVector_GenericOps.impl_OpAdd_SV_S_eq_SV_Generic$(this, evidence$6);
   }

   public UFunc.UImpl2 impl_OpSub_SV_S_eq_SV_Generic(final Ring evidence$7) {
      return SparseVector_GenericOps.impl_OpSub_SV_S_eq_SV_Generic$(this, evidence$7);
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_S_eq_SV_Generic(final Semiring evidence$8, final ClassTag evidence$9) {
      return SparseVector_GenericOps.impl_OpMulScalar_SV_S_eq_SV_Generic$(this, evidence$8, evidence$9);
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic_OpMulMatrix(final Semiring evidence$10, final ClassTag evidence$11) {
      return SparseVector_GenericOps.impl_Op_SV_S_eq_SV_Generic_OpMulMatrix$(this, evidence$10, evidence$11);
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic_OpMulScalar(final Semiring ring) {
      return SparseVector_GenericOps.impl_Op_SV_SV_eq_SV_Generic_OpMulScalar$(this, ring);
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_SV_S_SV_InPlace_Generic(final Semiring evidence$12, final ClassTag evidence$13) {
      return SparseVector_GenericOps.impl_scaleAdd_SV_S_SV_InPlace_Generic$(this, evidence$12, evidence$13);
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic(final UFunc.UImpl2 op, final Semiring semiring) {
      return SparseVector_GenericOps.impl_Op_SV_SV_eq_SV_Generic$(this, op, semiring);
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DVTt_DMT_eq_DMT(final UFunc.UImpl2 op) {
      return DenseMatrixMultiplyOps.impl_OpMulMatrix_DVTt_DMT_eq_DMT$(this, op);
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DVT_DMT_eq_DMT(final UFunc.UImpl2 op) {
      return DenseMatrixMultiplyOps.impl_OpMulMatrix_DVT_DMT_eq_DMT$(this, op);
   }

   public UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_DM() {
      return DenseMatrix_SetOps.impl_OpMulSet_InPlace_DM_DM$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_S() {
      return DenseMatrix_SetOps.impl_OpMulSet_InPlace_DM_S$(this);
   }

   public CanSlice2 canSliceCol() {
      return DenseMatrix_SlicingOps.canSliceCol$(this);
   }

   public CanSlice2 canSliceRow() {
      return DenseMatrix_SlicingOps.canSliceRow$(this);
   }

   public CanSlice2 canSliceRows() {
      return DenseMatrix_SlicingOps.canSliceRows$(this);
   }

   public CanSlice2 canSliceCols() {
      return DenseMatrix_SlicingOps.canSliceCols$(this);
   }

   public CanSlice2 canSliceColsAndRows() {
      return DenseMatrix_SlicingOps.canSliceColsAndRows$(this);
   }

   public CanSlice2 canSlicePartOfCol() {
      return DenseMatrix_SlicingOps.canSlicePartOfCol$(this);
   }

   public CanSlice2 canSlicePartOfRow() {
      return DenseMatrix_SlicingOps.canSlicePartOfRow$(this);
   }

   public CanSlice2 canSliceWeirdRows(final Semiring evidence$1, final ClassTag evidence$2) {
      return DenseMatrix_SlicingOps_LowPrio.canSliceWeirdRows$(this, evidence$1, evidence$2);
   }

   public CanSlice2 canSliceWeirdCols(final Semiring evidence$3, final ClassTag evidence$4) {
      return DenseMatrix_SlicingOps_LowPrio.canSliceWeirdCols$(this, evidence$3, evidence$4);
   }

   public CanSlice2 canSliceTensorBooleanRows(final Semiring evidence$5, final ClassTag evidence$6) {
      return DenseMatrix_SlicingOps_LowPrio.canSliceTensorBooleanRows$(this, evidence$5, evidence$6);
   }

   public CanSlice2 canSliceTensorBooleanCols(final Semiring evidence$7, final ClassTag evidence$8) {
      return DenseMatrix_SlicingOps_LowPrio.canSliceTensorBooleanCols$(this, evidence$7, evidence$8);
   }

   public CanCollapseAxis canCollapseRows_DM(final ClassTag evidence$73) {
      return LowPriorityDenseMatrix1.canCollapseRows_DM$(this, evidence$73);
   }

   public CanCollapseAxis canCollapseCols_DM(final ClassTag evidence$74) {
      return LowPriorityDenseMatrix1.canCollapseCols_DM$(this, evidence$74);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M() {
      return LowPriorityDenseMatrix1.impl_OpSet_InPlace_DM_M$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mDc$sp() {
      return LowPriorityDenseMatrix1.impl_OpSet_InPlace_DM_M$mDc$sp$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mFc$sp() {
      return LowPriorityDenseMatrix1.impl_OpSet_InPlace_DM_M$mFc$sp$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mIc$sp() {
      return LowPriorityDenseMatrix1.impl_OpSet_InPlace_DM_M$mIc$sp$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mJc$sp() {
      return LowPriorityDenseMatrix1.impl_OpSet_InPlace_DM_M$mJc$sp$(this);
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpAdd(final Field evidence$13, final Zero evidence$14, final ClassTag evidence$15) {
      return DenseMatrixExpandedOps.dm_dm_UpdateOp_OpAdd$(this, evidence$13, evidence$14, evidence$15);
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpSub(final Field evidence$16, final Zero evidence$17, final ClassTag evidence$18) {
      return DenseMatrixExpandedOps.dm_dm_UpdateOp_OpSub$(this, evidence$16, evidence$17, evidence$18);
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpMulScalar(final Field evidence$19, final Zero evidence$20, final ClassTag evidence$21) {
      return DenseMatrixExpandedOps.dm_dm_UpdateOp_OpMulScalar$(this, evidence$19, evidence$20, evidence$21);
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpDiv(final Field evidence$22, final Zero evidence$23, final ClassTag evidence$24) {
      return DenseMatrixExpandedOps.dm_dm_UpdateOp_OpDiv$(this, evidence$22, evidence$23, evidence$24);
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpMod(final Field evidence$25, final Zero evidence$26, final ClassTag evidence$27) {
      return DenseMatrixExpandedOps.dm_dm_UpdateOp_OpMod$(this, evidence$25, evidence$26, evidence$27);
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_OpPow(final Field evidence$28, final Zero evidence$29, final ClassTag evidence$30) {
      return DenseMatrixExpandedOps.dm_dm_UpdateOp_OpPow$(this, evidence$28, evidence$29, evidence$30);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpAdd(final Field evidence$31, final Zero evidence$32, final ClassTag evidence$33) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpAdd$(this, evidence$31, evidence$32, evidence$33);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpSub(final Field evidence$34, final Zero evidence$35, final ClassTag evidence$36) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpSub$(this, evidence$34, evidence$35, evidence$36);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpMulScalar(final Field evidence$37, final Zero evidence$38, final ClassTag evidence$39) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpMulScalar$(this, evidence$37, evidence$38, evidence$39);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpMulMatrix(final Field evidence$40, final Zero evidence$41, final ClassTag evidence$42) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpMulMatrix$(this, evidence$40, evidence$41, evidence$42);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpDiv(final Field evidence$43, final Zero evidence$44, final ClassTag evidence$45) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpDiv$(this, evidence$43, evidence$44, evidence$45);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpMod(final Field evidence$46, final Zero evidence$47, final ClassTag evidence$48) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpMod$(this, evidence$46, evidence$47, evidence$48);
   }

   public UFunc.InPlaceImpl2 opUpdate_DM_S_OpPow(final Field evidence$49, final Zero evidence$50, final ClassTag evidence$51) {
      return DenseMatrixExpandedOps.opUpdate_DM_S_OpPow$(this, evidence$49, evidence$50, evidence$51);
   }

   public UFunc.UImpl2 op_DM_S_OpAdd(final Field evidence$52, final Zero evidence$53, final ClassTag evidence$54) {
      return DenseMatrixExpandedOps.op_DM_S_OpAdd$(this, evidence$52, evidence$53, evidence$54);
   }

   public UFunc.UImpl2 op_DM_S_OpSub(final Field evidence$55, final Zero evidence$56, final ClassTag evidence$57) {
      return DenseMatrixExpandedOps.op_DM_S_OpSub$(this, evidence$55, evidence$56, evidence$57);
   }

   public UFunc.UImpl2 op_DM_S_OpMulScalar(final Field evidence$58, final Zero evidence$59, final ClassTag evidence$60) {
      return DenseMatrixExpandedOps.op_DM_S_OpMulScalar$(this, evidence$58, evidence$59, evidence$60);
   }

   public UFunc.UImpl2 op_DM_S_OpMulMatrix(final Field evidence$61, final Zero evidence$62, final ClassTag evidence$63) {
      return DenseMatrixExpandedOps.op_DM_S_OpMulMatrix$(this, evidence$61, evidence$62, evidence$63);
   }

   public UFunc.UImpl2 op_DM_S_OpDiv(final Field evidence$64, final Zero evidence$65, final ClassTag evidence$66) {
      return DenseMatrixExpandedOps.op_DM_S_OpDiv$(this, evidence$64, evidence$65, evidence$66);
   }

   public UFunc.UImpl2 op_DM_S_OpMod(final Field evidence$67, final Zero evidence$68, final ClassTag evidence$69) {
      return DenseMatrixExpandedOps.op_DM_S_OpMod$(this, evidence$67, evidence$68, evidence$69);
   }

   public UFunc.UImpl2 op_DM_S_OpPow(final Field evidence$70, final Zero evidence$71, final ClassTag evidence$72) {
      return DenseMatrixExpandedOps.op_DM_S_OpPow$(this, evidence$70, evidence$71, evidence$72);
   }

   public UFunc.UImpl2 s_dm_op(final UFunc.UImpl2 opScalar, final ClassTag ct, final Zero zero) {
      return DenseMatrixExpandedOps.s_dm_op$(this, opScalar, ct, zero);
   }

   public CanTraverseValues canTraverseValues() {
      return DenseMatrix_TraversalOps.canTraverseValues$(this);
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs_DM() {
      return DenseMatrix_TraversalOps.canTraverseKeyValuePairs_DM$(this);
   }

   public CanTransformValues canTransformValues_DM() {
      return DenseMatrix_TraversalOps.canTransformValues_DM$(this);
   }

   public CanTransformValues canTransformValues_DM$mDc$sp() {
      return DenseMatrix_TraversalOps.canTransformValues_DM$mDc$sp$(this);
   }

   public CanTransformValues canTransformValues_DM$mFc$sp() {
      return DenseMatrix_TraversalOps.canTransformValues_DM$mFc$sp$(this);
   }

   public CanTransformValues canTransformValues_DM$mIc$sp() {
      return DenseMatrix_TraversalOps.canTransformValues_DM$mIc$sp$(this);
   }

   public CanMapKeyValuePairs canMapKeyValuePairs_DM(final ClassTag evidence$6) {
      return DenseMatrix_TraversalOps.canMapKeyValuePairs_DM$(this, evidence$6);
   }

   public CanMapValues canMapValues_DM(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mDDc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mDDc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mFDc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mFDc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mIDc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mIDc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mDFc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mDFc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mFFc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mFFc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mIFc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mIFc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mDIc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mDIc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mFIc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mFIc$sp$(this, evidence$7);
   }

   public CanMapValues canMapValues_DM$mIIc$sp(final ClassTag evidence$7) {
      return DenseMatrix_TraversalOps.canMapValues_DM$mIIc$sp$(this, evidence$7);
   }

   public CanCopy canCopy_DM(final ClassTag evidence$8) {
      return DenseMatrix_TraversalOps.canCopy_DM$(this, evidence$8);
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DM_T_DM(final Semiring evidence$1) {
      return DenseMatrix_GenericOps.impl_scaleAdd_InPlace_DM_T_DM$(this, evidence$1);
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Generic(final Semiring evidence$2) {
      return DenseMatrix_GenericOps.impl_OpMulMatrix_DM_DM_eq_DM_Generic$(this, evidence$2);
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_V_eq_DV_Generic(final Semiring ring) {
      return DenseMatrix_GenericOps.impl_OpMulMatrix_DM_V_eq_DV_Generic$(this, ring);
   }

   public UFunc.UImpl2 op_M_V_Semiring(final Semiring evidence$98, final Zero evidence$99, final ClassTag evidence$100) {
      return MatrixMultOps.op_M_V_Semiring$(this, evidence$98, evidence$99, evidence$100);
   }

   public UFunc.UImpl2 op_M_M_Semiring(final Semiring evidence$101, final Zero evidence$102, final ClassTag evidence$103) {
      return MatrixMultOps.op_M_M_Semiring$(this, evidence$101, evidence$102, evidence$103);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpAdd(final Field evidence$14, final Zero evidence$15, final ClassTag evidence$16) {
      return MatrixExpandedOps.m_m_UpdateOp_OpAdd$(this, evidence$14, evidence$15, evidence$16);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpSub(final Field evidence$17, final Zero evidence$18, final ClassTag evidence$19) {
      return MatrixExpandedOps.m_m_UpdateOp_OpSub$(this, evidence$17, evidence$18, evidence$19);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpMulScalar(final Field evidence$20, final Zero evidence$21, final ClassTag evidence$22) {
      return MatrixExpandedOps.m_m_UpdateOp_OpMulScalar$(this, evidence$20, evidence$21, evidence$22);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpDiv(final Field evidence$23, final Zero evidence$24, final ClassTag evidence$25) {
      return MatrixExpandedOps.m_m_UpdateOp_OpDiv$(this, evidence$23, evidence$24, evidence$25);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpSet(final Field evidence$26, final Zero evidence$27, final ClassTag evidence$28) {
      return MatrixExpandedOps.m_m_UpdateOp_OpSet$(this, evidence$26, evidence$27, evidence$28);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpMod(final Field evidence$29, final Zero evidence$30, final ClassTag evidence$31) {
      return MatrixExpandedOps.m_m_UpdateOp_OpMod$(this, evidence$29, evidence$30, evidence$31);
   }

   public BinaryUpdateRegistry m_m_UpdateOp_OpPow(final Field evidence$32, final Zero evidence$33, final ClassTag evidence$34) {
      return MatrixExpandedOps.m_m_UpdateOp_OpPow$(this, evidence$32, evidence$33, evidence$34);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpAdd(final Field evidence$35, final Zero evidence$36, final ClassTag evidence$37) {
      return MatrixExpandedOps.m_s_UpdateOp_OpAdd$(this, evidence$35, evidence$36, evidence$37);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpSub(final Field evidence$38, final Zero evidence$39, final ClassTag evidence$40) {
      return MatrixExpandedOps.m_s_UpdateOp_OpSub$(this, evidence$38, evidence$39, evidence$40);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpMulScalar(final Field evidence$41, final Zero evidence$42, final ClassTag evidence$43) {
      return MatrixExpandedOps.m_s_UpdateOp_OpMulScalar$(this, evidence$41, evidence$42, evidence$43);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpMulMatrix(final Field evidence$44, final Zero evidence$45, final ClassTag evidence$46) {
      return MatrixExpandedOps.m_s_UpdateOp_OpMulMatrix$(this, evidence$44, evidence$45, evidence$46);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpDiv(final Field evidence$47, final Zero evidence$48, final ClassTag evidence$49) {
      return MatrixExpandedOps.m_s_UpdateOp_OpDiv$(this, evidence$47, evidence$48, evidence$49);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpMod(final Field evidence$50, final Zero evidence$51, final ClassTag evidence$52) {
      return MatrixExpandedOps.m_s_UpdateOp_OpMod$(this, evidence$50, evidence$51, evidence$52);
   }

   public BinaryUpdateRegistry m_s_UpdateOp_OpPow(final Field evidence$53, final Zero evidence$54, final ClassTag evidence$55) {
      return MatrixExpandedOps.m_s_UpdateOp_OpPow$(this, evidence$53, evidence$54, evidence$55);
   }

   public BinaryRegistry op_M_S_OpAdd(final Field evidence$56, final Zero evidence$57, final ClassTag evidence$58) {
      return MatrixExpandedOps.op_M_S_OpAdd$(this, evidence$56, evidence$57, evidence$58);
   }

   public BinaryRegistry op_M_S_OpSub(final Field evidence$59, final Zero evidence$60, final ClassTag evidence$61) {
      return MatrixExpandedOps.op_M_S_OpSub$(this, evidence$59, evidence$60, evidence$61);
   }

   public BinaryRegistry op_M_S_OpMulScalar(final Field evidence$62, final Zero evidence$63, final ClassTag evidence$64) {
      return MatrixExpandedOps.op_M_S_OpMulScalar$(this, evidence$62, evidence$63, evidence$64);
   }

   public BinaryRegistry op_M_S_OpMulMatrix(final Field evidence$65, final Zero evidence$66, final ClassTag evidence$67) {
      return MatrixExpandedOps.op_M_S_OpMulMatrix$(this, evidence$65, evidence$66, evidence$67);
   }

   public BinaryRegistry op_M_S_OpDiv(final Field evidence$68, final Zero evidence$69, final ClassTag evidence$70) {
      return MatrixExpandedOps.op_M_S_OpDiv$(this, evidence$68, evidence$69, evidence$70);
   }

   public BinaryRegistry op_M_S_OpMod(final Field evidence$71, final Zero evidence$72, final ClassTag evidence$73) {
      return MatrixExpandedOps.op_M_S_OpMod$(this, evidence$71, evidence$72, evidence$73);
   }

   public BinaryRegistry op_M_S_OpPow(final Field evidence$74, final Zero evidence$75, final ClassTag evidence$76) {
      return MatrixExpandedOps.op_M_S_OpPow$(this, evidence$74, evidence$75, evidence$76);
   }

   public BinaryRegistry op_S_M_OpAdd(final Field evidence$77, final Zero evidence$78, final ClassTag evidence$79) {
      return MatrixExpandedOps.op_S_M_OpAdd$(this, evidence$77, evidence$78, evidence$79);
   }

   public BinaryRegistry op_S_M_OpSub(final Field evidence$80, final Zero evidence$81, final ClassTag evidence$82) {
      return MatrixExpandedOps.op_S_M_OpSub$(this, evidence$80, evidence$81, evidence$82);
   }

   public BinaryRegistry op_S_M_OpMulScalar(final Field evidence$83, final Zero evidence$84, final ClassTag evidence$85) {
      return MatrixExpandedOps.op_S_M_OpMulScalar$(this, evidence$83, evidence$84, evidence$85);
   }

   public BinaryRegistry op_S_M_OpMulMatrix(final Field evidence$86, final Zero evidence$87, final ClassTag evidence$88) {
      return MatrixExpandedOps.op_S_M_OpMulMatrix$(this, evidence$86, evidence$87, evidence$88);
   }

   public BinaryRegistry op_S_M_OpDiv(final Field evidence$89, final Zero evidence$90, final ClassTag evidence$91) {
      return MatrixExpandedOps.op_S_M_OpDiv$(this, evidence$89, evidence$90, evidence$91);
   }

   public BinaryRegistry op_S_M_OpMod(final Field evidence$92, final Zero evidence$93, final ClassTag evidence$94) {
      return MatrixExpandedOps.op_S_M_OpMod$(this, evidence$92, evidence$93, evidence$94);
   }

   public BinaryRegistry op_S_M_OpPow(final Field evidence$95, final Zero evidence$96, final ClassTag evidence$97) {
      return MatrixExpandedOps.op_S_M_OpPow$(this, evidence$95, evidence$96, evidence$97);
   }

   public CanCopy canCopyMatrix(final ClassTag evidence$1) {
      return MatrixGenericOps.canCopyMatrix$(this, evidence$1);
   }

   public UFunc.InPlaceImpl2 m_m_OpAdd_Update_Semi(final Semiring evidence$2, final ClassTag evidence$3, final Zero evidence$4) {
      return MatrixGenericOps.m_m_OpAdd_Update_Semi$(this, evidence$2, evidence$3, evidence$4);
   }

   public UFunc.InPlaceImpl2 m_m_OpMul_Update_Semi(final Semiring evidence$5, final ClassTag evidence$6, final Zero evidence$7) {
      return MatrixGenericOps.m_m_OpMul_Update_Semi$(this, evidence$5, evidence$6, evidence$7);
   }

   public UFunc.InPlaceImpl2 m_m_OpSub_Update_Ring(final Ring evidence$8, final ClassTag evidence$9, final Zero evidence$10) {
      return MatrixGenericOps.m_m_OpSub_Update_Ring$(this, evidence$8, evidence$9, evidence$10);
   }

   public UFunc.InPlaceImpl2 m_m_OpDiv_Update_Ring(final Field evidence$11, final ClassTag evidence$12, final Zero evidence$13) {
      return MatrixGenericOps.m_m_OpDiv_Update_Ring$(this, evidence$11, evidence$12, evidence$13);
   }

   public UFunc.UImpl impl_dim_DV_eq_I() {
      return DenseVector_DoubleOps.impl_dim_DV_eq_I$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Int_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Double_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Float_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Long_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Int_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Double_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Float_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Long_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Int_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Double_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Float_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Long_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Int_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Double_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Float_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Long_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Int_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Double_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Float_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Long_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Int_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Int_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Double_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Double_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Float_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Float_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_BV_comparison_Long_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_DV_eq_BV_comparison_Long_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Int_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Double_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Float_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Long_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Int_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Double_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Float_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Long_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Int_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Double_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Float_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Long_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Int_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Double_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Float_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Long_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Int_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Double_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Float_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Long_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Int_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Int_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Double_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Double_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Float_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Float_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_V_eq_BV_Comparison_Long_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_V_eq_BV_Comparison_Long_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Int_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Double_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Float_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpGT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Long_OpGT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Int_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Double_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Float_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpGTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Long_OpGTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Int_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Double_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Float_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpLTE() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Long_OpLTE$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Int_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Double_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Float_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpLT() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Long_OpLT$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Int_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Double_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Float_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpEq() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Long_OpEq$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Int_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Int_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Double_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Double_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Float_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Float_OpNe$(this);
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_BV_comparison_Long_OpNe() {
      return DenseVector_ComparisonOps.impl_Op_DV_S_eq_BV_comparison_Long_OpNe$(this);
   }

   public CanSlice canSlice_DV_Range_eq_DV() {
      return DenseVector_SlicingOps.canSlice_DV_Range_eq_DV$(this);
   }

   public CanTraverseValues DV_canIterateValues() {
      return DenseVector_TraversalOps.DV_canIterateValues$(this);
   }

   public CanZipAndTraverseValues DV_canTraverseZipValues() {
      return DenseVector_TraversalOps.DV_canTraverseZipValues$(this);
   }

   public CanTraverseKeyValuePairs DV_canTraverseKeyValuePairs() {
      return DenseVector_TraversalOps.DV_canTraverseKeyValuePairs$(this);
   }

   public CanTransformValues DV_canTransformValues() {
      return DenseVector_TraversalOps.DV_canTransformValues$(this);
   }

   public CanTransformValues DV_canTransformValues$mDc$sp() {
      return DenseVector_TraversalOps.DV_canTransformValues$mDc$sp$(this);
   }

   public CanTransformValues DV_canTransformValues$mFc$sp() {
      return DenseVector_TraversalOps.DV_canTransformValues$mFc$sp$(this);
   }

   public CanTransformValues DV_canTransformValues$mIc$sp() {
      return DenseVector_TraversalOps.DV_canTransformValues$mIc$sp$(this);
   }

   public CanMapKeyValuePairs canMapPairs(final ClassTag man) {
      return DenseVector_TraversalOps.canMapPairs$(this, man);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V_Generic() {
      return DenseVector_GenericOps.impl_OpSet_InPlace_DV_V_Generic$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_DV() {
      return DenseVector_GenericOps.impl_OpSet_InPlace_DV_DV$(this);
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Generic(final Semiring evidence$14) {
      return DenseVector_GenericOps.impl_scaleAdd_InPlace_DV_S_DV_Generic$(this, evidence$14);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_DV_V(final .less.colon.less ev) {
      return DenseVector_GenericOps.impl_OpSet_InPlace_DV_V$(this, ev);
   }

   public UFunc.UImpl2 impl_Op_LHS_DVt_eq_R_cast(final UFunc.UImpl2 op) {
      return DenseVector_GenericOps.impl_Op_LHS_DVt_eq_R_cast$(this, op);
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Generic(final Semiring field) {
      return DenseVector_GenericOps.impl_OpAdd_InPlace_DV_DV_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Generic(final Ring field) {
      return DenseVector_GenericOps.impl_OpSub_InPlace_DV_DV_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_DV_Generic(final Semiring field) {
      return DenseVector_GenericOps.impl_OpMulScalar_InPlace_DV_DV_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_DV_Generic(final Field field) {
      return DenseVector_GenericOps.impl_OpDiv_InPlace_DV_DV_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_DV_Generic(final UFunc.UImpl2 pow) {
      return DenseVector_GenericOps.impl_OpPow_InPlace_DV_DV_Generic$(this, pow);
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_S_Generic(final Semiring field) {
      return DenseVector_GenericOps.impl_OpAdd_InPlace_DV_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_S_Generic(final Ring field) {
      return DenseVector_GenericOps.impl_OpSub_InPlace_DV_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_DV_S_Generic(final Semiring field) {
      return DenseVector_GenericOps.impl_OpMulScalar_InPlace_DV_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpDiv_InPlace_DV_S_Generic(final Field field) {
      return DenseVector_GenericOps.impl_OpDiv_InPlace_DV_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpPow_InPlace_DV_S_Generic(final UFunc.UImpl2 pow) {
      return DenseVector_GenericOps.impl_OpPow_InPlace_DV_S_Generic$(this, pow);
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Generic(final Semiring field) {
      return DenseVector_GenericOps.impl_OpMulInner_DV_DV_eq_S_Generic$(this, field);
   }

   public CanTranspose canTranspose_CSC(final ClassTag evidence$1, final Zero evidence$2, final Semiring evidence$3) {
      return CSCMatrix_TransposeOps.canTranspose_CSC$(this, evidence$1, evidence$2, evidence$3);
   }

   public CanTranspose canTranspose_CSC_Complex() {
      return CSCMatrix_TransposeOps.canTranspose_CSC_Complex$(this);
   }

   public CanTranspose canTranspose_DV_Complex() {
      return TransposeOps_Complex.canTranspose_DV_Complex$(this);
   }

   public CanTranspose canTranspose_SV_Complex() {
      return TransposeOps_Complex.canTranspose_SV_Complex$(this);
   }

   public CanTranspose canTranspose_DM() {
      return DenseMatrix_TransposeOps.canTranspose_DM$(this);
   }

   public CanTranspose canTranspose_DM_Complex() {
      return DenseMatrix_TransposeOps.canTranspose_DM_Complex$(this);
   }

   public CanTranspose canUntranspose() {
      return TransposeOps_Generic.canUntranspose$(this);
   }

   public UFunc.UImpl2 transTimesNormalFromDot(final UFunc.UImpl2 dot) {
      return TransposeOps_Generic.transTimesNormalFromDot$(this, dot);
   }

   public CanTranspose transposeTensor(final .less.colon.less ev) {
      return TransposeOps_Generic.transposeTensor$(this, ev);
   }

   public UFunc.UImpl2 impl_OpMulMatrix_Ut_T_from_Tt_U(final CanTranspose transT, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return TransposeOps_LowPrio.impl_OpMulMatrix_Ut_T_from_Tt_U$(this, transT, op, canTranspose);
   }

   public UFunc.UImpl2 impl_Op_Tt_S_eq_RT_from_T_S(final ScalarOf ev, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return TransposeOps_LowPrio.impl_Op_Tt_S_eq_RT_from_T_S$(this, ev, op, canTranspose);
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_Tt_S_from_T_S(final ScalarOf ev, final UFunc.InPlaceImpl2 op) {
      return TransposeOps_LowPrio.impl_Op_InPlace_Tt_S_from_T_S$(this, ev, op);
   }

   public UFunc.UImpl2 impl_EOp_Tt_Ut_eq_Rt_from_T_U(final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return TransposeOps_LowPrio2.impl_EOp_Tt_Ut_eq_Rt_from_T_U$(this, op, canTranspose);
   }

   public UFunc.InPlaceImpl2 liftInPlaceOps(final NotGiven notScalar, final CanTranspose transU, final UFunc.InPlaceImpl2 op) {
      return TransposeOps_LowPrio2.liftInPlaceOps$(this, notScalar, transU, op);
   }

   public TransposeOps_LowPrio2.LiftApply LiftApply(final Transpose _trans) {
      return TransposeOps_LowPrio2.LiftApply$(this, _trans);
   }

   public CanSlice liftSlice(final CanSlice op, final CanTranspose trans) {
      return TransposeOps_LowPrio2.liftSlice$(this, op, trans);
   }

   public UFunc.UImpl liftUFunc(final UFunc.UImpl op, final CanTranspose trans) {
      return TransposeOps_LowPrio2.liftUFunc$(this, op, trans);
   }

   public UFunc.InPlaceImpl impl_Op_InPlace_Tt_from_Op_T(final UFunc.InPlaceImpl op) {
      return TransposeOps_LowPrio2.impl_Op_InPlace_Tt_from_Op_T$(this, op);
   }

   public UFunc.UImpl3 liftUFunc3_1(final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.UImpl3 op, final CanTranspose transR) {
      return TransposeOps_LowPrio2.liftUFunc3_1$(this, t2Trans, t3Trans, op, transR);
   }

   public UFunc.InPlaceImpl3 liftUFuncInplace3_1(final CanTranspose t2Trans, final CanTranspose t3Trans, final UFunc.InPlaceImpl3 op) {
      return TransposeOps_LowPrio2.liftUFuncInplace3_1$(this, t2Trans, t3Trans, op);
   }

   public CanSlice2 canSliceTensor_Seq_to_2(final CanSlice seqSlice) {
      return TensorLowPrio.canSliceTensor_Seq_to_2$(this, seqSlice);
   }

   public CanSlice canSliceTensor(final ClassTag evidence$1) {
      return TensorLowPrio.canSliceTensor$(this, evidence$1);
   }

   public CanSlice canSliceTensorBoolean(final ClassTag evidence$2) {
      return TensorLowPrio.canSliceTensorBoolean$(this, evidence$2);
   }

   public CanSlice2 canSliceTensor2(final Semiring evidence$3, final ClassTag evidence$4) {
      return TensorLowPrio.canSliceTensor2$(this, evidence$3, evidence$4);
   }

   public CanSlice2 canSliceTensor2_CRs(final Semiring evidence$5, final ClassTag evidence$6) {
      return TensorLowPrio.canSliceTensor2_CRs$(this, evidence$5, evidence$6);
   }

   public CanSlice2 canSliceTensor2_CsR(final Semiring evidence$7, final ClassTag evidence$8) {
      return TensorLowPrio.canSliceTensor2_CsR$(this, evidence$7, evidence$8);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpAdd(final Field evidence$1, final ClassTag evidence$2) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpAdd$(this, evidence$1, evidence$2);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpSub(final Field evidence$3, final ClassTag evidence$4) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpSub$(this, evidence$3, evidence$4);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpMulScalar(final Field evidence$5, final ClassTag evidence$6) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpMulScalar$(this, evidence$5, evidence$6);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpMulMatrix(final Field evidence$7, final ClassTag evidence$8) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpMulMatrix$(this, evidence$7, evidence$8);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpDiv(final Field evidence$9, final ClassTag evidence$10) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpDiv$(this, evidence$9, evidence$10);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpMod(final Field evidence$11, final ClassTag evidence$12) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpMod$(this, evidence$11, evidence$12);
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Generic_OpPow(final Field evidence$13, final ClassTag evidence$14) {
      return VectorExpandOps.impl_Op_V_S_eq_V_Generic_OpPow$(this, evidence$13, evidence$14);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpAdd(final Field evidence$15, final ClassTag evidence$16) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpAdd$(this, evidence$15, evidence$16);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpSub(final Field evidence$17, final ClassTag evidence$18) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpSub$(this, evidence$17, evidence$18);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpMulScalar(final Field evidence$19, final ClassTag evidence$20) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpMulScalar$(this, evidence$19, evidence$20);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpMulMatrix(final Field evidence$21, final ClassTag evidence$22) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpMulMatrix$(this, evidence$21, evidence$22);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpDiv(final Field evidence$23, final ClassTag evidence$24) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpDiv$(this, evidence$23, evidence$24);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpSet(final Field evidence$25, final ClassTag evidence$26) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpSet$(this, evidence$25, evidence$26);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpMod(final Field evidence$27, final ClassTag evidence$28) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpMod$(this, evidence$27, evidence$28);
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Generic_OpPow(final Field evidence$29, final ClassTag evidence$30) {
      return VectorExpandOps.impl_Op_InPlace_V_S_Generic_OpPow$(this, evidence$29, evidence$30);
   }

   public UFunc.UImpl2 impl_OpMulInner_V_V_eq_S_Generic(final Semiring s) {
      return VectorExpandOps.impl_OpMulInner_V_V_eq_S_Generic$(this, s);
   }

   public UFunc.UImpl2 zipValuesSubclass(final .less.colon.less view1, final .less.colon.less view2, final UFunc.UImpl2 op) {
      return Vector_GenericOps.zipValuesSubclass$(this, view1, view2, op);
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_V_Generic(final Semiring field) {
      return Vector_GenericOps.impl_OpMulScalar_InPlace_V_V_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_V_Generic(final Field field) {
      return Vector_GenericOps.impl_OpDiv_InPlace_V_V_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_V_Generic(final UFunc.UImpl2 pow) {
      return Vector_GenericOps.impl_OpPow_InPlace_V_V_Generic$(this, pow);
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_V_S_Generic(final Semiring field) {
      return Vector_GenericOps.impl_OpAdd_InPlace_V_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_V_S_Generic(final Ring field) {
      return Vector_GenericOps.impl_OpSub_InPlace_V_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_S_Generic(final Semiring field) {
      return Vector_GenericOps.impl_OpMulScalar_InPlace_V_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_S_Generic(final Field field) {
      return Vector_GenericOps.impl_OpDiv_InPlace_V_S_Generic$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_S_Generic(final UFunc.UImpl2 pow, final Zero zero) {
      return Vector_GenericOps.impl_OpPow_InPlace_V_S_Generic$(this, pow, zero);
   }

   public UFunc.UImpl2 impl_OpMulInner_V_V_eq_S(final Semiring field) {
      return Vector_GenericOps.impl_OpMulInner_V_V_eq_S$(this, field);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_V_V_InPlace() {
      return Vector_GenericOps.impl_OpSet_V_V_InPlace$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_V_S_InPlace() {
      return Vector_GenericOps.impl_OpSet_V_S_InPlace$(this);
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_V_T_V_Generic(final Semiring evidence$1) {
      return Vector_GenericOps.impl_scaleAdd_InPlace_V_T_V_Generic$(this, evidence$1);
   }

   public UFunc.UImpl2 impl_OpSub_V_V_eq_V_Generic(final Ring evidence$2) {
      return Vector_GenericOps.impl_OpSub_V_V_eq_V_Generic$(this, evidence$2);
   }

   public UFunc.UImpl2 impl_OpAdd_V_V_eq_V_Generic(final Semiring evidence$3) {
      return Vector_GenericOps.impl_OpAdd_V_V_eq_V_Generic$(this, evidence$3);
   }

   public CanMapValues canMapValues_V(final Zero evidence$2, final ClassTag man) {
      return Vector_TraversalOps.canMapValues_V$(this, evidence$2, man);
   }

   public Vector_TraversalOps.CanZipMapValuesVector canZipMapValues_V(final ClassTag evidence$3) {
      return Vector_TraversalOps.canZipMapValues_V$(this, evidence$3);
   }

   public Vector_TraversalOps.CanZipMapKeyValuesVector zipMapKV_V(final ClassTag evidence$5) {
      return Vector_TraversalOps.zipMapKV_V$(this, evidence$5);
   }

   public CanTraverseValues canIterateValues_V() {
      return Vector_TraversalOps.canIterateValues_V$(this);
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs_V() {
      return Vector_TraversalOps.canTraverseKeyValuePairs_V$(this);
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Semiring semi) {
      return GenericOps.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace$(this, sa, semi);
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Ring ring) {
      return GenericOps.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace$(this, sa, ring);
   }

   public UFunc.UImpl impl_OpNeg_T_Generic_from_OpMulScalar(final ScalarOf scalarOf, final Ring ring, final UFunc.UImpl2 scale) {
      return GenericOps.impl_OpNeg_T_Generic_from_OpMulScalar$(this, scalarOf, ring, scale);
   }

   public UFunc.UImpl2 pureFromUpdate(final UFunc.InPlaceImpl2 op, final CanCopy copy) {
      return GenericOpsLowPrio.pureFromUpdate$(this, op, copy);
   }

   public UFunc.UImpl2 castOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_V_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_V_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.UImpl2 castOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_V_S$(this, v2, v1lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_V_S$(this, v2, v1lt, v1ne, op);
   }

   public UFunc.UImpl2 castOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_M_M$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_M_M$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.UImpl2 castOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_M_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_M_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.UImpl2 impl_T_S_eq_U_from_ZipMap(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return GenericOpsLowPrio3.impl_T_S_eq_U_from_ZipMap$(this, handhold, impl, canZipMapValues);
   }

   public BitVectorOps.impl_any_BV_eq_Boolean$ impl_any_BV_eq_Boolean() {
      if (impl_any_BV_eq_Boolean$module == null) {
         this.impl_any_BV_eq_Boolean$lzycompute$1();
      }

      return impl_any_BV_eq_Boolean$module;
   }

   public BitVectorOps.impl_all_BV_eq_Boolean$ impl_all_BV_eq_Boolean() {
      if (impl_all_BV_eq_Boolean$module == null) {
         this.impl_all_BV_eq_Boolean$lzycompute$1();
      }

      return impl_all_BV_eq_Boolean$module;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpAnd() {
      return impl_Op_InPlace_BV_BV_OpAnd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpOr() {
      return impl_Op_InPlace_BV_BV_OpOr;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_BV_BV_OpXor() {
      return impl_Op_InPlace_BV_BV_OpXor;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_BV_BV() {
      return impl_OpSet_InPlace_BV_BV;
   }

   public UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpAnd() {
      return impl_Op_BV_BV_eq_BV_OpAnd;
   }

   public UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpOr() {
      return impl_Op_BV_BV_eq_BV_OpOr;
   }

   public UFunc.UImpl2 impl_Op_BV_BV_eq_BV_OpXor() {
      return impl_Op_BV_BV_eq_BV_OpXor;
   }

   public UFunc.UImpl bv_OpNot() {
      return bv_OpNot;
   }

   public UFunc.UImpl2 bv_bv_OpNe() {
      return bv_bv_OpNe;
   }

   public UFunc.UImpl2 bv_bv_OpEq() {
      return bv_bv_OpEq;
   }

   public UFunc.UImpl2 canDot_BV_BV() {
      return canDot_BV_BV;
   }

   public UFunc.UImpl2 canDot_BV_DenseVector_Double() {
      return canDot_BV_DenseVector_Double;
   }

   public UFunc.UImpl2 canDot_BV_DenseVector_Float() {
      return canDot_BV_DenseVector_Float;
   }

   public UFunc.UImpl2 canDot_BV_DenseVector_Int() {
      return canDot_BV_DenseVector_Int;
   }

   public UFunc.UImpl2 canDot_BV_DenseVector_Long() {
      return canDot_BV_DenseVector_Long;
   }

   public UFunc.UImpl2 canDot_BV_SV_Int() {
      return canDot_BV_SV_Int;
   }

   public UFunc.UImpl2 canDot_BV_SV_Long() {
      return canDot_BV_SV_Long;
   }

   public UFunc.UImpl2 canDot_BV_SV_BigInt() {
      return canDot_BV_SV_BigInt;
   }

   public UFunc.UImpl2 canDot_BV_SV_Complex() {
      return canDot_BV_SV_Complex;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpAnd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_BV_BV_OpAnd = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpOr_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_BV_BV_OpOr = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_InPlace_BV_BV_OpXor_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_BV_BV_OpXor = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_OpSet_InPlace_BV_BV_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_BV_BV = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpAnd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_BV_BV_eq_BV_OpAnd = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpOr_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_BV_BV_eq_BV_OpOr = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$impl_Op_BV_BV_eq_BV_OpXor_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_BV_BV_eq_BV_OpXor = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$bv_OpNot_$eq(final UFunc.UImpl x$1) {
      bv_OpNot = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$bv_bv_OpNe_$eq(final UFunc.UImpl2 x$1) {
      bv_bv_OpNe = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$bv_bv_OpEq_$eq(final UFunc.UImpl2 x$1) {
      bv_bv_OpEq = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_BV_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_BV = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Double_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_DenseVector_Double = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Float_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_DenseVector_Float = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Int_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_DenseVector_Int = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_DenseVector_Long_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_DenseVector_Long = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Int_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_SV_Int = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Long_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_SV_Long = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_BigInt_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_SV_BigInt = x$1;
   }

   public void breeze$linalg$operators$BitVectorOps$_setter_$canDot_BV_SV_Complex_$eq(final UFunc.UImpl2 x$1) {
      canDot_BV_SV_Complex = x$1;
   }

   public UFunc.UImpl csc_OpNeg_Int() {
      return csc_OpNeg_Int;
   }

   public UFunc.UImpl csc_OpNeg_Double() {
      return csc_OpNeg_Double;
   }

   public UFunc.UImpl csc_OpNeg_Float() {
      return csc_OpNeg_Float;
   }

   public UFunc.UImpl csc_OpNeg_Long() {
      return csc_OpNeg_Long;
   }

   public UFunc.InPlaceImpl3 cscScaleAdd_Int() {
      return cscScaleAdd_Int;
   }

   public UFunc.InPlaceImpl3 cscScaleAdd_Double() {
      return cscScaleAdd_Double;
   }

   public UFunc.InPlaceImpl3 cscScaleAdd_Float() {
      return cscScaleAdd_Float;
   }

   public UFunc.InPlaceImpl3 cscScaleAdd_Long() {
      return cscScaleAdd_Long;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Int_OpPow() {
      return csc_csc_BadOps_Int_OpPow;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Double_OpPow() {
      return csc_csc_BadOps_Double_OpPow;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Float_OpPow() {
      return csc_csc_BadOps_Float_OpPow;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Long_OpPow() {
      return csc_csc_BadOps_Long_OpPow;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Int_OpDiv() {
      return csc_csc_BadOps_Int_OpDiv;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Double_OpDiv() {
      return csc_csc_BadOps_Double_OpDiv;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Float_OpDiv() {
      return csc_csc_BadOps_Float_OpDiv;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Long_OpDiv() {
      return csc_csc_BadOps_Long_OpDiv;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Int_OpMod() {
      return csc_csc_BadOps_Int_OpMod;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Double_OpMod() {
      return csc_csc_BadOps_Double_OpMod;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Float_OpMod() {
      return csc_csc_BadOps_Float_OpMod;
   }

   public UFunc.UImpl2 csc_csc_BadOps_Long_OpMod() {
      return csc_csc_BadOps_Long_OpMod;
   }

   public UFunc.UImpl2 csc_csc_OpAdd_Int() {
      return csc_csc_OpAdd_Int;
   }

   public UFunc.UImpl2 csc_csc_OpAdd_Double() {
      return csc_csc_OpAdd_Double;
   }

   public UFunc.UImpl2 csc_csc_OpAdd_Float() {
      return csc_csc_OpAdd_Float;
   }

   public UFunc.UImpl2 csc_csc_OpAdd_Long() {
      return csc_csc_OpAdd_Long;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Int() {
      return dm_csc_InPlace_OpSet_Int;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Double() {
      return dm_csc_InPlace_OpSet_Double;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Float() {
      return dm_csc_InPlace_OpSet_Float;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Long() {
      return dm_csc_InPlace_OpSet_Long;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Int() {
      return dm_csc_InPlace_OpAdd_Int;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Double() {
      return dm_csc_InPlace_OpAdd_Double;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Float() {
      return dm_csc_InPlace_OpAdd_Float;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Long() {
      return dm_csc_InPlace_OpAdd_Long;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Int() {
      return dm_csc_InPlace_OpSub_Int;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Double() {
      return dm_csc_InPlace_OpSub_Double;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Float() {
      return dm_csc_InPlace_OpSub_Float;
   }

   public UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Long() {
      return dm_csc_InPlace_OpSub_Long;
   }

   public UFunc.UImpl2 csc_dm_OpAdd_Int() {
      return csc_dm_OpAdd_Int;
   }

   public UFunc.UImpl2 csc_dm_OpAdd_Double() {
      return csc_dm_OpAdd_Double;
   }

   public UFunc.UImpl2 csc_dm_OpAdd_Float() {
      return csc_dm_OpAdd_Float;
   }

   public UFunc.UImpl2 csc_dm_OpAdd_Long() {
      return csc_dm_OpAdd_Long;
   }

   public UFunc.UImpl2 dm_csc_OpAdd_Int() {
      return dm_csc_OpAdd_Int;
   }

   public UFunc.UImpl2 dm_csc_OpAdd_Double() {
      return dm_csc_OpAdd_Double;
   }

   public UFunc.UImpl2 dm_csc_OpAdd_Float() {
      return dm_csc_OpAdd_Float;
   }

   public UFunc.UImpl2 dm_csc_OpAdd_Long() {
      return dm_csc_OpAdd_Long;
   }

   public UFunc.UImpl2 dm_csc_OpSub_Int() {
      return dm_csc_OpSub_Int;
   }

   public UFunc.UImpl2 dm_csc_OpSub_Double() {
      return dm_csc_OpSub_Double;
   }

   public UFunc.UImpl2 dm_csc_OpSub_Float() {
      return dm_csc_OpSub_Float;
   }

   public UFunc.UImpl2 dm_csc_OpSub_Long() {
      return dm_csc_OpSub_Long;
   }

   public UFunc.UImpl2 csc_dm_OpSub_Int() {
      return csc_dm_OpSub_Int;
   }

   public UFunc.UImpl2 csc_dm_OpSub_Double() {
      return csc_dm_OpSub_Double;
   }

   public UFunc.UImpl2 csc_dm_OpSub_Float() {
      return csc_dm_OpSub_Float;
   }

   public UFunc.UImpl2 csc_dm_OpSub_Long() {
      return csc_dm_OpSub_Long;
   }

   public UFunc.UImpl2 csc_csc_OpMulScalar_Int() {
      return csc_csc_OpMulScalar_Int;
   }

   public UFunc.UImpl2 csc_csc_OpMulScalar_Double() {
      return csc_csc_OpMulScalar_Double;
   }

   public UFunc.UImpl2 csc_csc_OpMulScalar_Float() {
      return csc_csc_OpMulScalar_Float;
   }

   public UFunc.UImpl2 csc_csc_OpMulScalar_Long() {
      return csc_csc_OpMulScalar_Long;
   }

   public UFunc.UImpl2 csc_csc_OpSub_Int() {
      return csc_csc_OpSub_Int;
   }

   public UFunc.UImpl2 csc_csc_OpSub_Double() {
      return csc_csc_OpSub_Double;
   }

   public UFunc.UImpl2 csc_csc_OpSub_Float() {
      return csc_csc_OpSub_Float;
   }

   public UFunc.UImpl2 csc_csc_OpSub_Long() {
      return csc_csc_OpSub_Long;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar() {
      return impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar() {
      return impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar() {
      return impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar() {
      return impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix() {
      return impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix() {
      return impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix() {
      return impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix() {
      return impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix;
   }

   public BinaryRegistry canMulM_V_Int() {
      return canMulM_V_Int;
   }

   public BinaryRegistry canMulM_V_Float() {
      return canMulM_V_Float;
   }

   public BinaryRegistry canMulM_V_Double() {
      return canMulM_V_Double;
   }

   public BinaryRegistry canMulM_V_Long() {
      return canMulM_V_Long;
   }

   public UFunc.UImpl2 canMulM_DV_Int() {
      return canMulM_DV_Int;
   }

   public UFunc.UImpl2 canMulM_DV_Float() {
      return canMulM_DV_Float;
   }

   public UFunc.UImpl2 canMulM_DV_Double() {
      return canMulM_DV_Double;
   }

   public UFunc.UImpl2 canMulM_DV_Long() {
      return canMulM_DV_Long;
   }

   public BinaryRegistry canMulM_SV_Int() {
      return canMulM_SV_Int;
   }

   public BinaryRegistry canMulM_SV_Float() {
      return canMulM_SV_Float;
   }

   public BinaryRegistry canMulM_SV_Double() {
      return canMulM_SV_Double;
   }

   public BinaryRegistry canMulM_SV_Long() {
      return canMulM_SV_Long;
   }

   public UFunc.UImpl2 canMulM_DM_Int() {
      return canMulM_DM_Int;
   }

   public UFunc.UImpl2 canMulM_DM_Float() {
      return canMulM_DM_Float;
   }

   public UFunc.UImpl2 canMulM_DM_Double() {
      return canMulM_DM_Double;
   }

   public UFunc.UImpl2 canMulM_DM_Long() {
      return canMulM_DM_Long;
   }

   public UFunc.UImpl2 canMulDM_M_Int() {
      return canMulDM_M_Int;
   }

   public UFunc.UImpl2 canMulDM_M_Float() {
      return canMulDM_M_Float;
   }

   public UFunc.UImpl2 canMulDM_M_Double() {
      return canMulDM_M_Double;
   }

   public UFunc.UImpl2 canMulDM_M_Long() {
      return canMulDM_M_Long;
   }

   public UFunc.UImpl2 canMulM_M_Int() {
      return canMulM_M_Int;
   }

   public UFunc.UImpl2 canMulM_M_Float() {
      return canMulM_M_Float;
   }

   public UFunc.UImpl2 canMulM_M_Double() {
      return canMulM_M_Double;
   }

   public UFunc.UImpl2 canMulM_M_Long() {
      return canMulM_M_Long;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpSub() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpSub() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpSub() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpSub() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpPow() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpPow() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpPow() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpPow() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMod() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMod() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMod() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMod() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix() {
      return impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix() {
      return impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix() {
      return impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix() {
      return impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpAdd() {
      return csc_csc_InPlace_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpAdd() {
      return csc_csc_InPlace_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpAdd() {
      return csc_csc_InPlace_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpAdd() {
      return csc_csc_InPlace_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpSub() {
      return csc_csc_InPlace_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpSub() {
      return csc_csc_InPlace_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpSub() {
      return csc_csc_InPlace_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpSub() {
      return csc_csc_InPlace_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpDiv() {
      return csc_csc_InPlace_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpDiv() {
      return csc_csc_InPlace_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpDiv() {
      return csc_csc_InPlace_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpDiv() {
      return csc_csc_InPlace_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpPow() {
      return csc_csc_InPlace_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpPow() {
      return csc_csc_InPlace_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpPow() {
      return csc_csc_InPlace_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpPow() {
      return csc_csc_InPlace_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMod() {
      return csc_csc_InPlace_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMod() {
      return csc_csc_InPlace_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMod() {
      return csc_csc_InPlace_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMod() {
      return csc_csc_InPlace_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMulScalar() {
      return csc_csc_InPlace_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMulScalar() {
      return csc_csc_InPlace_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMulScalar() {
      return csc_csc_InPlace_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMulScalar() {
      return csc_csc_InPlace_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl3 axpyCSC_DM_DM_Int() {
      return axpyCSC_DM_DM_Int;
   }

   public UFunc.InPlaceImpl3 axpyCSC_DM_DM_Float() {
      return axpyCSC_DM_DM_Float;
   }

   public UFunc.InPlaceImpl3 axpyCSC_DM_DM_Double() {
      return axpyCSC_DM_DM_Double;
   }

   public UFunc.InPlaceImpl3 axpyCSC_DM_DM_Long() {
      return axpyCSC_DM_DM_Long;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Int_$eq(final UFunc.UImpl x$1) {
      csc_OpNeg_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Double_$eq(final UFunc.UImpl x$1) {
      csc_OpNeg_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Float_$eq(final UFunc.UImpl x$1) {
      csc_OpNeg_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Long_$eq(final UFunc.UImpl x$1) {
      csc_OpNeg_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      cscScaleAdd_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      cscScaleAdd_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      cscScaleAdd_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      cscScaleAdd_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_BadOps_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Int_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpAdd_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Double_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpAdd_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Float_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpAdd_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Long_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpAdd_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSet_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSet_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSet_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSet_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpAdd_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpAdd_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpAdd_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpAdd_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSub_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSub_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSub_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_csc_InPlace_OpSub_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Int_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpAdd_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Double_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpAdd_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Float_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpAdd_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Long_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpAdd_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Int_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpAdd_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Double_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpAdd_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Float_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpAdd_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Long_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpAdd_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Int_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpSub_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Double_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpSub_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Float_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpSub_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Long_$eq(final UFunc.UImpl2 x$1) {
      dm_csc_OpSub_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Int_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpSub_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Double_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpSub_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Float_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpSub_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Long_$eq(final UFunc.UImpl2 x$1) {
      csc_dm_OpSub_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Int_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpMulScalar_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Double_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpMulScalar_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Float_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpMulScalar_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Long_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpMulScalar_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Int_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpSub_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Double_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpSub_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Float_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpSub_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Long_$eq(final UFunc.UImpl2 x$1) {
      csc_csc_OpSub_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Int_$eq(final BinaryRegistry x$1) {
      canMulM_V_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Float_$eq(final BinaryRegistry x$1) {
      canMulM_V_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Double_$eq(final BinaryRegistry x$1) {
      canMulM_V_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Long_$eq(final BinaryRegistry x$1) {
      canMulM_V_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Int_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DV_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Float_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DV_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Double_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DV_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Long_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DV_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Int_$eq(final BinaryRegistry x$1) {
      canMulM_SV_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Float_$eq(final BinaryRegistry x$1) {
      canMulM_SV_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Double_$eq(final BinaryRegistry x$1) {
      canMulM_SV_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Long_$eq(final BinaryRegistry x$1) {
      canMulM_SV_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Int_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DM_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Float_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DM_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Double_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DM_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Long_$eq(final UFunc.UImpl2 x$1) {
      canMulM_DM_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Int_$eq(final UFunc.UImpl2 x$1) {
      canMulDM_M_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Float_$eq(final UFunc.UImpl2 x$1) {
      canMulDM_M_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Double_$eq(final UFunc.UImpl2 x$1) {
      canMulDM_M_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Long_$eq(final UFunc.UImpl2 x$1) {
      canMulDM_M_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Int_$eq(final UFunc.UImpl2 x$1) {
      canMulM_M_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Float_$eq(final UFunc.UImpl2 x$1) {
      canMulM_M_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Double_$eq(final UFunc.UImpl2 x$1) {
      canMulM_M_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Long_$eq(final UFunc.UImpl2 x$1) {
      canMulM_M_Long = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      csc_csc_InPlace_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      axpyCSC_DM_DM_Int = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      axpyCSC_DM_DM_Float = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      axpyCSC_DM_DM_Double = x$1;
   }

   public void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      axpyCSC_DM_DM_Long = x$1;
   }

   public UFunc.UImpl2 impl_OpSolveMatrixBy_CSC_CSC_eq_CSC() {
      return impl_OpSolveMatrixBy_CSC_CSC_eq_CSC;
   }

   public CSCMatrixOps_Ring.FrobeniusCSCProduct$ FrobeniusCSCProduct() {
      if (FrobeniusCSCProduct$module == null) {
         this.FrobeniusCSCProduct$lzycompute$1();
      }

      return FrobeniusCSCProduct$module;
   }

   public void breeze$linalg$operators$CSCMatrixOps_Ring$_setter_$impl_OpSolveMatrixBy_CSC_CSC_eq_CSC_$eq(final UFunc.UImpl2 x$1) {
      impl_OpSolveMatrixBy_CSC_CSC_eq_CSC = x$1;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public UFunc.InPlaceImpl2 setMV_D() {
      return setMV_D;
   }

   public UFunc.InPlaceImpl2 setMV_F() {
      return setMV_F;
   }

   public UFunc.InPlaceImpl2 setMV_I() {
      return setMV_I;
   }

   public DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Double() {
      return zipMap_DM_Double;
   }

   public DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Float() {
      return zipMap_DM_Float;
   }

   public DenseMatrixOps.CanZipMapValuesDenseMatrix zipMap_DM_Int() {
      return zipMap_DM_Int;
   }

   public void breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_D_$eq(final UFunc.InPlaceImpl2 x$1) {
      setMV_D = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_F_$eq(final UFunc.InPlaceImpl2 x$1) {
      setMV_F = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_I_$eq(final UFunc.InPlaceImpl2 x$1) {
      setMV_I = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Double_$eq(final DenseMatrixOps.CanZipMapValuesDenseMatrix x$1) {
      zipMap_DM_Double = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Float_$eq(final DenseMatrixOps.CanZipMapValuesDenseMatrix x$1) {
      zipMap_DM_Float = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Int_$eq(final DenseMatrixOps.CanZipMapValuesDenseMatrix x$1) {
      zipMap_DM_Int = x$1;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Float() {
      return impl_OpMulMatrix_DM_DM_eq_DM_Float;
   }

   public DenseMatrixOps_FloatSpecialized.impl_OpMulMatrix_DMF_DVF_eq_DVF$ impl_OpMulMatrix_DMF_DVF_eq_DVF() {
      if (impl_OpMulMatrix_DMF_DVF_eq_DVF$module == null) {
         this.impl_OpMulMatrix_DMF_DVF_eq_DVF$lzycompute$1();
      }

      return impl_OpMulMatrix_DMF_DVF_eq_DVF$module;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DVF_DMF_eq_DMF() {
      return impl_OpMulMatrix_DVF_DMF_eq_DMF;
   }

   public DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$ impl_OpSolveMatrixBy_DMF_DMF_eq_DMF() {
      if (impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$module == null) {
         this.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$lzycompute$1();
      }

      return impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$module;
   }

   public DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$ impl_OpSolveMatrixBy_DMF_DVF_eq_DVF() {
      if (impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$module == null) {
         this.impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$lzycompute$1();
      }

      return impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$module;
   }

   public void breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_DM_eq_DM_Float = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$_setter_$impl_OpMulMatrix_DVF_DMF_eq_DMF_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DVF_DMF_eq_DMF = x$1;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow() {
      return impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Int() {
      return impl_OpMulScalar_SV_HV_eq_SV_Int;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Double() {
      return impl_OpMulScalar_SV_HV_eq_SV_Double;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Float() {
      return impl_OpMulScalar_SV_HV_eq_SV_Float;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_HV_eq_SV_Long() {
      return impl_OpMulScalar_SV_HV_eq_SV_Long;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpAdd() {
      return impl_Op_SV_HV_eq_SV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpAdd() {
      return impl_Op_SV_HV_eq_SV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpAdd() {
      return impl_Op_SV_HV_eq_SV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpAdd() {
      return impl_Op_SV_HV_eq_SV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Int_OpSub() {
      return impl_Op_SV_HV_eq_SV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Double_OpSub() {
      return impl_Op_SV_HV_eq_SV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Float_OpSub() {
      return impl_Op_SV_HV_eq_SV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_HV_eq_SV_Long_OpSub() {
      return impl_Op_SV_HV_eq_SV_Long_OpSub;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_lhs_nilpotent_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_HV_eq_SV_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_HV_eq_SV_Double = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_HV_eq_SV_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_OpMulScalar_SV_HV_eq_SV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_HV_eq_SV_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_HashVector_Ops$_setter_$impl_Op_SV_HV_eq_SV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_HV_eq_SV_Long_OpSub = x$1;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow() {
      return impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Int() {
      return impl_OpMulScalar_HV_SV_eq_HV_Int;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Double() {
      return impl_OpMulScalar_HV_SV_eq_HV_Double;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Float() {
      return impl_OpMulScalar_HV_SV_eq_HV_Float;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_SV_eq_HV_Long() {
      return impl_OpMulScalar_HV_SV_eq_HV_Long;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Int() {
      return impl_scaleAdd_InPlace_HV_S_SV_Int;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Double() {
      return impl_scaleAdd_InPlace_HV_S_SV_Double;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Float() {
      return impl_scaleAdd_InPlace_HV_S_SV_Float;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_SV_Long() {
      return impl_scaleAdd_InPlace_HV_S_SV_Long;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Int() {
      return impl_OpMulInner_HV_SV_eq_S_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Long() {
      return impl_OpMulInner_HV_SV_eq_S_Long;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Float() {
      return impl_OpMulInner_HV_SV_eq_S_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_SV_eq_S_Double() {
      return impl_OpMulInner_HV_SV_eq_S_Double;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_SV_eq_HV_lhs_nilpotent_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_SV_eq_HV_Int = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_SV_eq_HV_Double = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_SV_eq_HV_Float = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulScalar_HV_SV_eq_HV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_SV_eq_HV_Long = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_SV_Int = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_SV_Double = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_SV_Float = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_scaleAdd_InPlace_HV_S_SV_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_SV_Long = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_SV_eq_S_Int = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_SV_eq_S_Long = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_SV_eq_S_Float = x$1;
   }

   public void breeze$linalg$operators$HashVector_SparseVector_Ops$_setter_$impl_OpMulInner_HV_SV_eq_S_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_SV_eq_S_Double = x$1;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpAdd() {
      return impl_Op_InPlace_HV_DV_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpAdd() {
      return impl_Op_InPlace_HV_DV_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpAdd() {
      return impl_Op_InPlace_HV_DV_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpAdd() {
      return impl_Op_InPlace_HV_DV_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSub() {
      return impl_Op_InPlace_HV_DV_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSub() {
      return impl_Op_InPlace_HV_DV_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSub() {
      return impl_Op_InPlace_HV_DV_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSub() {
      return impl_Op_InPlace_HV_DV_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMulScalar() {
      return impl_Op_InPlace_HV_DV_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMulScalar() {
      return impl_Op_InPlace_HV_DV_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMulScalar() {
      return impl_Op_InPlace_HV_DV_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMulScalar() {
      return impl_Op_InPlace_HV_DV_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpDiv() {
      return impl_Op_InPlace_HV_DV_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpDiv() {
      return impl_Op_InPlace_HV_DV_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpDiv() {
      return impl_Op_InPlace_HV_DV_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpDiv() {
      return impl_Op_InPlace_HV_DV_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSet() {
      return impl_Op_InPlace_HV_DV_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSet() {
      return impl_Op_InPlace_HV_DV_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSet() {
      return impl_Op_InPlace_HV_DV_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSet() {
      return impl_Op_InPlace_HV_DV_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMod() {
      return impl_Op_InPlace_HV_DV_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMod() {
      return impl_Op_InPlace_HV_DV_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMod() {
      return impl_Op_InPlace_HV_DV_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMod() {
      return impl_Op_InPlace_HV_DV_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpPow() {
      return impl_Op_InPlace_HV_DV_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpPow() {
      return impl_Op_InPlace_HV_DV_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpPow() {
      return impl_Op_InPlace_HV_DV_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpPow() {
      return impl_Op_InPlace_HV_DV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpAdd() {
      return impl_Op_HV_DV_eq_HV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpAdd() {
      return impl_Op_HV_DV_eq_HV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpAdd() {
      return impl_Op_HV_DV_eq_HV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpAdd() {
      return impl_Op_HV_DV_eq_HV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSub() {
      return impl_Op_HV_DV_eq_HV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSub() {
      return impl_Op_HV_DV_eq_HV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSub() {
      return impl_Op_HV_DV_eq_HV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSub() {
      return impl_Op_HV_DV_eq_HV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMulScalar() {
      return impl_Op_HV_DV_eq_HV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMulScalar() {
      return impl_Op_HV_DV_eq_HV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMulScalar() {
      return impl_Op_HV_DV_eq_HV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMulScalar() {
      return impl_Op_HV_DV_eq_HV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpDiv() {
      return impl_Op_HV_DV_eq_HV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpDiv() {
      return impl_Op_HV_DV_eq_HV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpDiv() {
      return impl_Op_HV_DV_eq_HV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpDiv() {
      return impl_Op_HV_DV_eq_HV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSet() {
      return impl_Op_HV_DV_eq_HV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSet() {
      return impl_Op_HV_DV_eq_HV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSet() {
      return impl_Op_HV_DV_eq_HV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSet() {
      return impl_Op_HV_DV_eq_HV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMod() {
      return impl_Op_HV_DV_eq_HV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMod() {
      return impl_Op_HV_DV_eq_HV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMod() {
      return impl_Op_HV_DV_eq_HV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMod() {
      return impl_Op_HV_DV_eq_HV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpPow() {
      return impl_Op_HV_DV_eq_HV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpPow() {
      return impl_Op_HV_DV_eq_HV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpPow() {
      return impl_Op_HV_DV_eq_HV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpPow() {
      return impl_Op_HV_DV_eq_HV_Long_OpPow;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_DV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_DV_eq_HV_Long_OpPow = x$1;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Int() {
      return impl_scaleAdd_InPlace_DV_T_HV_Int;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Double() {
      return impl_scaleAdd_InPlace_DV_T_HV_Double;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Float() {
      return impl_scaleAdd_InPlace_DV_T_HV_Float;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Long() {
      return impl_scaleAdd_InPlace_DV_T_HV_Long;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Int() {
      return impl_OpMulInner_DV_HV_eq_S_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Double() {
      return impl_OpMulInner_DV_HV_eq_S_Double;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Float() {
      return impl_OpMulInner_DV_HV_eq_S_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Long() {
      return impl_OpMulInner_DV_HV_eq_S_Long;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_DV_T_HV_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_DV_T_HV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_DV_T_HV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_DV_T_HV_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_HV_eq_S_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_HV_eq_S_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_HV_eq_S_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_HV_eq_S_Long = x$1;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Int() {
      return impl_scaleAdd_InPlace_HV_S_HV_Int;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Double() {
      return impl_scaleAdd_InPlace_HV_S_HV_Double;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Float() {
      return impl_scaleAdd_InPlace_HV_S_HV_Float;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Long() {
      return impl_scaleAdd_InPlace_HV_S_HV_Long;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpAdd() {
      return impl_Op_HV_HV_eq_HV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpAdd() {
      return impl_Op_HV_HV_eq_HV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpAdd() {
      return impl_Op_HV_HV_eq_HV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpAdd() {
      return impl_Op_HV_HV_eq_HV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpSub() {
      return impl_Op_HV_HV_eq_HV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpSub() {
      return impl_Op_HV_HV_eq_HV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpSub() {
      return impl_Op_HV_HV_eq_HV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpSub() {
      return impl_Op_HV_HV_eq_HV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Int() {
      return impl_OpMulScalar_HV_HV_eq_HV_Int;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Double() {
      return impl_OpMulScalar_HV_HV_eq_HV_Double;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Float() {
      return impl_OpMulScalar_HV_HV_eq_HV_Float;
   }

   public UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Long() {
      return impl_OpMulScalar_HV_HV_eq_HV_Long;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow() {
      return impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpAdd() {
      return impl_Op_HV_V_eq_HV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpAdd() {
      return impl_Op_HV_V_eq_HV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpAdd() {
      return impl_Op_HV_V_eq_HV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpAdd() {
      return impl_Op_HV_V_eq_HV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSub() {
      return impl_Op_HV_V_eq_HV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSub() {
      return impl_Op_HV_V_eq_HV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSub() {
      return impl_Op_HV_V_eq_HV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSub() {
      return impl_Op_HV_V_eq_HV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMulScalar() {
      return impl_Op_HV_V_eq_HV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMulScalar() {
      return impl_Op_HV_V_eq_HV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMulScalar() {
      return impl_Op_HV_V_eq_HV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMulScalar() {
      return impl_Op_HV_V_eq_HV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpDiv() {
      return impl_Op_HV_V_eq_HV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpDiv() {
      return impl_Op_HV_V_eq_HV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpDiv() {
      return impl_Op_HV_V_eq_HV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpDiv() {
      return impl_Op_HV_V_eq_HV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSet() {
      return impl_Op_HV_V_eq_HV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSet() {
      return impl_Op_HV_V_eq_HV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSet() {
      return impl_Op_HV_V_eq_HV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSet() {
      return impl_Op_HV_V_eq_HV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMod() {
      return impl_Op_HV_V_eq_HV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMod() {
      return impl_Op_HV_V_eq_HV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMod() {
      return impl_Op_HV_V_eq_HV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMod() {
      return impl_Op_HV_V_eq_HV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpPow() {
      return impl_Op_HV_V_eq_HV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpPow() {
      return impl_Op_HV_V_eq_HV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpPow() {
      return impl_Op_HV_V_eq_HV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpPow() {
      return impl_Op_HV_V_eq_HV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpAdd() {
      return impl_Op_HV_S_eq_HV_add_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpAdd() {
      return impl_Op_HV_S_eq_HV_add_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpAdd() {
      return impl_Op_HV_S_eq_HV_add_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpAdd() {
      return impl_Op_HV_S_eq_HV_add_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpSub() {
      return impl_Op_HV_S_eq_HV_add_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpSub() {
      return impl_Op_HV_S_eq_HV_add_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpSub() {
      return impl_Op_HV_S_eq_HV_add_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpSub() {
      return impl_Op_HV_S_eq_HV_add_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar() {
      return impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar() {
      return impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar() {
      return impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar() {
      return impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix() {
      return impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix() {
      return impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix() {
      return impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix() {
      return impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv() {
      return impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv() {
      return impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv() {
      return impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv() {
      return impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMod() {
      return impl_Op_HV_S_eq_HV_zeroy_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMod() {
      return impl_Op_HV_S_eq_HV_zeroy_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMod() {
      return impl_Op_HV_S_eq_HV_zeroy_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMod() {
      return impl_Op_HV_S_eq_HV_zeroy_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpPow() {
      return impl_Op_HV_S_eq_HV_zeroy_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpPow() {
      return impl_Op_HV_S_eq_HV_zeroy_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpPow() {
      return impl_Op_HV_S_eq_HV_zeroy_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpPow() {
      return impl_Op_HV_S_eq_HV_zeroy_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Int() {
      return impl_OpSet_InPlace_HV_HV_Int;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Double() {
      return impl_OpSet_InPlace_HV_HV_Double;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Float() {
      return impl_OpSet_InPlace_HV_HV_Float;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Long() {
      return impl_OpSet_InPlace_HV_HV_Long;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpAdd() {
      return impl_Op_InPlace_HV_S_idempotent_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpAdd() {
      return impl_Op_InPlace_HV_S_idempotent_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpAdd() {
      return impl_Op_InPlace_HV_S_idempotent_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpAdd() {
      return impl_Op_InPlace_HV_S_idempotent_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpSub() {
      return impl_Op_InPlace_HV_S_idempotent_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpSub() {
      return impl_Op_InPlace_HV_S_idempotent_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpSub() {
      return impl_Op_InPlace_HV_S_idempotent_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpSub() {
      return impl_Op_InPlace_HV_S_idempotent_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Int() {
      return impl_OpMulScalar_InPlace_HV_S_Int;
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Double() {
      return impl_OpMulScalar_InPlace_HV_S_Double;
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Float() {
      return impl_OpMulScalar_InPlace_HV_S_Float;
   }

   public UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Long() {
      return impl_OpMulScalar_InPlace_HV_S_Long;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Int() {
      return impl_OpSet_InPlace_HV_S_Int;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Double() {
      return impl_OpSet_InPlace_HV_S_Double;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Float() {
      return impl_OpSet_InPlace_HV_S_Float;
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Long() {
      return impl_OpSet_InPlace_HV_S_Long;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow() {
      return impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Int() {
      return impl_OpMulInner_HV_HV_eq_S_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Long() {
      return impl_OpMulInner_HV_HV_eq_S_Long;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Double() {
      return impl_OpMulInner_HV_HV_eq_S_Double;
   }

   public UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Float() {
      return impl_OpMulInner_HV_HV_eq_S_Float;
   }

   public CanTraverseValues impl_CanTraverseValues_HV_Int() {
      return impl_CanTraverseValues_HV_Int;
   }

   public CanTraverseValues impl_CanTraverseValues_HV_Double() {
      return impl_CanTraverseValues_HV_Double;
   }

   public CanTraverseValues impl_CanTraverseValues_HV_Float() {
      return impl_CanTraverseValues_HV_Float;
   }

   public CanTraverseValues impl_CanTraverseValues_HV_Long() {
      return impl_CanTraverseValues_HV_Long;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_HV_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_HV_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_HV_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_HV_S_HV_Long = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_HV_eq_HV_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_HV_eq_HV_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_HV_eq_HV_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_HV_HV_eq_HV_Long = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_V_eq_HV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_add_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_HV_S_eq_HV_zeroy_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_HV_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_HV_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_HV_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_HV_Long = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_idempotent_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpMulScalar_InPlace_HV_S_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpMulScalar_InPlace_HV_S_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpMulScalar_InPlace_HV_S_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpMulScalar_InPlace_HV_S_Long = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_S_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_S_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_S_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSet_InPlace_HV_S_Long = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_HV_eq_S_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_HV_eq_S_Long = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_HV_eq_S_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_HV_HV_eq_S_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Int_$eq(final CanTraverseValues x$1) {
      impl_CanTraverseValues_HV_Int = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Double_$eq(final CanTraverseValues x$1) {
      impl_CanTraverseValues_HV_Double = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Float_$eq(final CanTraverseValues x$1) {
      impl_CanTraverseValues_HV_Float = x$1;
   }

   public void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Long_$eq(final CanTraverseValues x$1) {
      impl_CanTraverseValues_HV_Long = x$1;
   }

   public HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_d() {
      return HV_zipMap_d;
   }

   public HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_f() {
      return HV_zipMap_f;
   }

   public HashVector_GenericOps.CanZipMapValuesHashVector HV_zipMap_i() {
      return HV_zipMap_i;
   }

   public void breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_d_$eq(final HashVector_GenericOps.CanZipMapValuesHashVector x$1) {
      HV_zipMap_d = x$1;
   }

   public void breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_f_$eq(final HashVector_GenericOps.CanZipMapValuesHashVector x$1) {
      HV_zipMap_f = x$1;
   }

   public void breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_i_$eq(final HashVector_GenericOps.CanZipMapValuesHashVector x$1) {
      HV_zipMap_i = x$1;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpAdd() {
      return impl_Op_SV_DV_InPlace_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpAdd() {
      return impl_Op_SV_DV_InPlace_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpAdd() {
      return impl_Op_SV_DV_InPlace_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpAdd() {
      return impl_Op_SV_DV_InPlace_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSub() {
      return impl_Op_SV_DV_InPlace_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSub() {
      return impl_Op_SV_DV_InPlace_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSub() {
      return impl_Op_SV_DV_InPlace_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSub() {
      return impl_Op_SV_DV_InPlace_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMulScalar() {
      return impl_Op_SV_DV_InPlace_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMulScalar() {
      return impl_Op_SV_DV_InPlace_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMulScalar() {
      return impl_Op_SV_DV_InPlace_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMulScalar() {
      return impl_Op_SV_DV_InPlace_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpDiv() {
      return impl_Op_SV_DV_InPlace_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpDiv() {
      return impl_Op_SV_DV_InPlace_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpDiv() {
      return impl_Op_SV_DV_InPlace_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpDiv() {
      return impl_Op_SV_DV_InPlace_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSet() {
      return impl_Op_SV_DV_InPlace_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSet() {
      return impl_Op_SV_DV_InPlace_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSet() {
      return impl_Op_SV_DV_InPlace_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSet() {
      return impl_Op_SV_DV_InPlace_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMod() {
      return impl_Op_SV_DV_InPlace_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMod() {
      return impl_Op_SV_DV_InPlace_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMod() {
      return impl_Op_SV_DV_InPlace_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMod() {
      return impl_Op_SV_DV_InPlace_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpPow() {
      return impl_Op_SV_DV_InPlace_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpPow() {
      return impl_Op_SV_DV_InPlace_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpPow() {
      return impl_Op_SV_DV_InPlace_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpPow() {
      return impl_Op_SV_DV_InPlace_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpMulScalar() {
      return impl_Op_SV_DV_eq_SV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpMulScalar() {
      return impl_Op_SV_DV_eq_SV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpMulScalar() {
      return impl_Op_SV_DV_eq_SV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpMulScalar() {
      return impl_Op_SV_DV_eq_SV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpDiv() {
      return impl_Op_SV_DV_eq_SV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpDiv() {
      return impl_Op_SV_DV_eq_SV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpDiv() {
      return impl_Op_SV_DV_eq_SV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpDiv() {
      return impl_Op_SV_DV_eq_SV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpAdd() {
      return impl_Op_SV_DV_eq_DV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpAdd() {
      return impl_Op_SV_DV_eq_DV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpAdd() {
      return impl_Op_SV_DV_eq_DV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpAdd() {
      return impl_Op_SV_DV_eq_DV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSub() {
      return impl_Op_SV_DV_eq_DV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSub() {
      return impl_Op_SV_DV_eq_DV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSub() {
      return impl_Op_SV_DV_eq_DV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSub() {
      return impl_Op_SV_DV_eq_DV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSet() {
      return impl_Op_SV_DV_eq_DV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSet() {
      return impl_Op_SV_DV_eq_DV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSet() {
      return impl_Op_SV_DV_eq_DV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSet() {
      return impl_Op_SV_DV_eq_DV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpMod() {
      return impl_Op_SV_DV_eq_DV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpMod() {
      return impl_Op_SV_DV_eq_DV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpMod() {
      return impl_Op_SV_DV_eq_DV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpMod() {
      return impl_Op_SV_DV_eq_DV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpPow() {
      return impl_Op_SV_DV_eq_DV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpPow() {
      return impl_Op_SV_DV_eq_DV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpPow() {
      return impl_Op_SV_DV_eq_DV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpPow() {
      return impl_Op_SV_DV_eq_DV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Int() {
      return impl_OpMulInner_SV_DV_eq_T_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Double() {
      return impl_OpMulInner_SV_DV_eq_T_Double;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Float() {
      return impl_OpMulInner_SV_DV_eq_T_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Long() {
      return impl_OpMulInner_SV_DV_eq_T_Long;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Int() {
      return impl_OpMulMatrix_SV_DVt_eq_SMT_Int;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Double() {
      return impl_OpMulMatrix_SV_DVt_eq_SMT_Double;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Float() {
      return impl_OpMulMatrix_SV_DVt_eq_SMT_Float;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Long() {
      return impl_OpMulMatrix_SV_DVt_eq_SMT_Long;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Complex() {
      return impl_OpMulMatrix_SV_DVt_eq_SMT_Complex;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_SV_DV_InPlace_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_SV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_DV_eq_DV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_DV_eq_T_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_DV_eq_T_Double = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_DV_eq_T_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_DV_eq_T_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_SV_DVt_eq_SMT_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_SV_DVt_eq_SMT_Double = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_SV_DVt_eq_SMT_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_SV_DVt_eq_SMT_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Complex_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_SV_DVt_eq_SMT_Complex = x$1;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMulScalar() {
      return impl_Op_DV_SV_InPlace_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMulScalar() {
      return impl_Op_DV_SV_InPlace_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMulScalar() {
      return impl_Op_DV_SV_InPlace_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMulScalar() {
      return impl_Op_DV_SV_InPlace_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpDiv() {
      return impl_Op_DV_SV_InPlace_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpDiv() {
      return impl_Op_DV_SV_InPlace_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpDiv() {
      return impl_Op_DV_SV_InPlace_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpDiv() {
      return impl_Op_DV_SV_InPlace_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSet() {
      return impl_Op_DV_SV_InPlace_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSet() {
      return impl_Op_DV_SV_InPlace_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSet() {
      return impl_Op_DV_SV_InPlace_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSet() {
      return impl_Op_DV_SV_InPlace_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMod() {
      return impl_Op_DV_SV_InPlace_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMod() {
      return impl_Op_DV_SV_InPlace_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMod() {
      return impl_Op_DV_SV_InPlace_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMod() {
      return impl_Op_DV_SV_InPlace_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpPow() {
      return impl_Op_DV_SV_InPlace_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpPow() {
      return impl_Op_DV_SV_InPlace_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpPow() {
      return impl_Op_DV_SV_InPlace_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpPow() {
      return impl_Op_DV_SV_InPlace_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpAdd() {
      return impl_Op_DV_SV_InPlace_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpAdd() {
      return impl_Op_DV_SV_InPlace_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpAdd() {
      return impl_Op_DV_SV_InPlace_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpAdd() {
      return impl_Op_DV_SV_InPlace_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSub() {
      return impl_Op_DV_SV_InPlace_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSub() {
      return impl_Op_DV_SV_InPlace_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSub() {
      return impl_Op_DV_SV_InPlace_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSub() {
      return impl_Op_DV_SV_InPlace_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpMulScalar() {
      return impl_Op_DV_SV_eq_SV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpMulScalar() {
      return impl_Op_DV_SV_eq_SV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpMulScalar() {
      return impl_Op_DV_SV_eq_SV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpMulScalar() {
      return impl_Op_DV_SV_eq_SV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpDiv() {
      return impl_Op_DV_SV_eq_SV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpDiv() {
      return impl_Op_DV_SV_eq_SV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpDiv() {
      return impl_Op_DV_SV_eq_SV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpDiv() {
      return impl_Op_DV_SV_eq_SV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Int() {
      return impl_OpMulInner_DV_SV_eq_T_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Double() {
      return impl_OpMulInner_DV_SV_eq_T_Double;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Float() {
      return impl_OpMulInner_DV_SV_eq_T_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Long() {
      return impl_OpMulInner_DV_SV_eq_T_Long;
   }

   public UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Int() {
      return impl_zipValues_DV_SV_eq_ZV_Int;
   }

   public UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Double() {
      return impl_zipValues_DV_SV_eq_ZV_Double;
   }

   public UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Float() {
      return impl_zipValues_DV_SV_eq_ZV_Float;
   }

   public UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Long() {
      return impl_zipValues_DV_SV_eq_ZV_Long;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Int() {
      return implScaleAdd_DV_S_SV_InPlace_Int;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Double() {
      return implScaleAdd_DV_S_SV_InPlace_Double;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Float() {
      return implScaleAdd_DV_S_SV_InPlace_Float;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Long() {
      return implScaleAdd_DV_S_SV_InPlace_Long;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Int() {
      return impl_OpMulMatrix_DV_SVt_eq_CSC_Int;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Double() {
      return impl_OpMulMatrix_DV_SVt_eq_CSC_Double;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Float() {
      return impl_OpMulMatrix_DV_SVt_eq_CSC_Float;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Long() {
      return impl_OpMulMatrix_DV_SVt_eq_CSC_Long;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Complex() {
      return impl_OpMulMatrix_DV_SVt_eq_CSC_Complex;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_DV_SV_InPlace_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_SV_eq_SV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_SV_eq_T_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_SV_eq_T_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_SV_eq_T_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_SV_eq_T_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_SV_eq_ZV_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_SV_eq_ZV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_SV_eq_ZV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_SV_eq_ZV_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_DV_S_SV_InPlace_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_DV_S_SV_InPlace_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_DV_S_SV_InPlace_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_DV_S_SV_InPlace_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DV_SVt_eq_CSC_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DV_SVt_eq_CSC_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DV_SVt_eq_CSC_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DV_SVt_eq_CSC_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Complex_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DV_SVt_eq_CSC_Complex = x$1;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpAdd() {
      return impl_Op_SV_SV_eq_SV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpAdd() {
      return impl_Op_SV_SV_eq_SV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpAdd() {
      return impl_Op_SV_SV_eq_SV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpAdd() {
      return impl_Op_SV_SV_eq_SV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSub() {
      return impl_Op_SV_SV_eq_SV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSub() {
      return impl_Op_SV_SV_eq_SV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSub() {
      return impl_Op_SV_SV_eq_SV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSub() {
      return impl_Op_SV_SV_eq_SV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Int() {
      return impl_OpMulScalar_SV_SV_eq_SV_Int;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Double() {
      return impl_OpMulScalar_SV_SV_eq_SV_Double;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Float() {
      return impl_OpMulScalar_SV_SV_eq_SV_Float;
   }

   public UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Long() {
      return impl_OpMulScalar_SV_SV_eq_SV_Long;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpDiv() {
      return impl_Op_SV_SV_eq_SV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpDiv() {
      return impl_Op_SV_SV_eq_SV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpDiv() {
      return impl_Op_SV_SV_eq_SV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpDiv() {
      return impl_Op_SV_SV_eq_SV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSet() {
      return impl_Op_SV_SV_eq_SV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSet() {
      return impl_Op_SV_SV_eq_SV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSet() {
      return impl_Op_SV_SV_eq_SV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSet() {
      return impl_Op_SV_SV_eq_SV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpMod() {
      return impl_Op_SV_SV_eq_SV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpMod() {
      return impl_Op_SV_SV_eq_SV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpMod() {
      return impl_Op_SV_SV_eq_SV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpMod() {
      return impl_Op_SV_SV_eq_SV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpPow() {
      return impl_Op_SV_SV_eq_SV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpPow() {
      return impl_Op_SV_SV_eq_SV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpPow() {
      return impl_Op_SV_SV_eq_SV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpPow() {
      return impl_Op_SV_SV_eq_SV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpDiv() {
      return impl_Op_SV_V_eq_SV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpDiv() {
      return impl_Op_SV_V_eq_SV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpDiv() {
      return impl_Op_SV_V_eq_SV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpDiv() {
      return impl_Op_SV_V_eq_SV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpSet() {
      return impl_Op_SV_V_eq_SV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpSet() {
      return impl_Op_SV_V_eq_SV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpSet() {
      return impl_Op_SV_V_eq_SV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpSet() {
      return impl_Op_SV_V_eq_SV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpMod() {
      return impl_Op_SV_V_eq_SV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpMod() {
      return impl_Op_SV_V_eq_SV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpMod() {
      return impl_Op_SV_V_eq_SV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpMod() {
      return impl_Op_SV_V_eq_SV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpPow() {
      return impl_Op_SV_V_eq_SV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpPow() {
      return impl_Op_SV_V_eq_SV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpPow() {
      return impl_Op_SV_V_eq_SV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpPow() {
      return impl_Op_SV_V_eq_SV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpAdd() {
      return impl_Op_SV_S_eq_SV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpAdd() {
      return impl_Op_SV_S_eq_SV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpAdd() {
      return impl_Op_SV_S_eq_SV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpAdd() {
      return impl_Op_SV_S_eq_SV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSub() {
      return impl_Op_SV_S_eq_SV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSub() {
      return impl_Op_SV_S_eq_SV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSub() {
      return impl_Op_SV_S_eq_SV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSub() {
      return impl_Op_SV_S_eq_SV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSet() {
      return impl_Op_SV_S_eq_SV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSet() {
      return impl_Op_SV_S_eq_SV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSet() {
      return impl_Op_SV_S_eq_SV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSet() {
      return impl_Op_SV_S_eq_SV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpPow() {
      return impl_Op_SV_S_eq_SV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpPow() {
      return impl_Op_SV_S_eq_SV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpPow() {
      return impl_Op_SV_S_eq_SV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpPow() {
      return impl_Op_SV_S_eq_SV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpDiv() {
      return impl_Op_SV_S_eq_SV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpDiv() {
      return impl_Op_SV_S_eq_SV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpDiv() {
      return impl_Op_SV_S_eq_SV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpDiv() {
      return impl_Op_SV_S_eq_SV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMod() {
      return impl_Op_SV_S_eq_SV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMod() {
      return impl_Op_SV_S_eq_SV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMod() {
      return impl_Op_SV_S_eq_SV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMod() {
      return impl_Op_SV_S_eq_SV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulScalar() {
      return impl_Op_SV_S_eq_SV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulScalar() {
      return impl_Op_SV_S_eq_SV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulScalar() {
      return impl_Op_SV_S_eq_SV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulScalar() {
      return impl_Op_SV_S_eq_SV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulMatrix() {
      return impl_Op_SV_S_eq_SV_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulMatrix() {
      return impl_Op_SV_S_eq_SV_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulMatrix() {
      return impl_Op_SV_S_eq_SV_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulMatrix() {
      return impl_Op_SV_S_eq_SV_Long_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Int() {
      return impl_OpMulInner_SV_SV_eq_T_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Double() {
      return impl_OpMulInner_SV_SV_eq_T_Double;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Float() {
      return impl_OpMulInner_SV_SV_eq_T_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Long() {
      return impl_OpMulInner_SV_SV_eq_T_Long;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Int() {
      return implScaleAdd_SV_S_SV_InPlace_Int;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Double() {
      return implScaleAdd_SV_S_SV_InPlace_Double;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Float() {
      return implScaleAdd_SV_S_SV_InPlace_Float;
   }

   public UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Long() {
      return implScaleAdd_SV_S_SV_InPlace_Long;
   }

   public SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_d() {
      return zipMap_d;
   }

   public SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_f() {
      return zipMap_f;
   }

   public SparseVectorExpandOps.CanZipMapValuesSparseVector zipMap_i() {
      return zipMap_i;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_SV_eq_SV_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_SV_eq_SV_Double = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_SV_eq_SV_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulScalar_SV_SV_eq_SV_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_SV_eq_SV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_V_eq_SV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_SV_S_eq_SV_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_SV_eq_T_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_SV_eq_T_Double = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_SV_eq_T_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_SV_SV_eq_T_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_SV_S_SV_InPlace_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_SV_S_SV_InPlace_Double = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_SV_S_SV_InPlace_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      implScaleAdd_SV_S_SV_InPlace_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_d_$eq(final SparseVectorExpandOps.CanZipMapValuesSparseVector x$1) {
      zipMap_d = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_f_$eq(final SparseVectorExpandOps.CanZipMapValuesSparseVector x$1) {
      zipMap_f = x$1;
   }

   public void breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_i_$eq(final SparseVectorExpandOps.CanZipMapValuesSparseVector x$1) {
      zipMap_i = x$1;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Int() {
      return impl_OpMulMatrix_DM_SV_eq_DV_Int;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Float() {
      return impl_OpMulMatrix_DM_SV_eq_DV_Float;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Long() {
      return impl_OpMulMatrix_DM_SV_eq_DV_Long;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Double() {
      return impl_OpMulMatrix_DM_SV_eq_DV_Double;
   }

   public void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_SV_eq_DV_Int = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_SV_eq_DV_Float = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_SV_eq_DV_Long = x$1;
   }

   public void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_SV_eq_DV_Double = x$1;
   }

   public DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DMD_eq_DMD$ impl_OpMulMatrix_DMD_DMD_eq_DMD() {
      if (impl_OpMulMatrix_DMD_DMD_eq_DMD$module == null) {
         this.impl_OpMulMatrix_DMD_DMD_eq_DMD$lzycompute$1();
      }

      return impl_OpMulMatrix_DMD_DMD_eq_DMD$module;
   }

   public DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DVD_eq_DVD$ impl_OpMulMatrix_DMD_DVD_eq_DVD() {
      if (impl_OpMulMatrix_DMD_DVD_eq_DVD$module == null) {
         this.impl_OpMulMatrix_DMD_DVD_eq_DVD$lzycompute$1();
      }

      return impl_OpMulMatrix_DMD_DVD_eq_DVD$module;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DVD_DMD_eq_DMD() {
      return impl_OpMulMatrix_DVD_DMD_eq_DMD;
   }

   public DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$ impl_OpSolveMatrixBy_DMD_DMD_eq_DMD() {
      if (impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$module == null) {
         this.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$lzycompute$1();
      }

      return impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$module;
   }

   public DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$ impl_OpSolveMatrixBy_DMD_DVD_eq_DVD() {
      if (impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$module == null) {
         this.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$lzycompute$1();
      }

      return impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$module;
   }

   public void breeze$linalg$operators$DenseMatrixMultiplyOps$_setter_$impl_OpMulMatrix_DVD_DMD_eq_DMD_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DVD_DMD_eq_DMD = x$1;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Int() {
      return impl_OpMulMatrix_DM_V_eq_DV_Int;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Long() {
      return impl_OpMulMatrix_DM_V_eq_DV_Long;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Float() {
      return impl_OpMulMatrix_DM_V_eq_DV_Float;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_V_eq_DV_Double() {
      return impl_OpMulMatrix_DM_V_eq_DV_Double;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Int() {
      return impl_OpMulMatrix_DM_M_eq_DM_Int;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Long() {
      return impl_OpMulMatrix_DM_M_eq_DM_Long;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Float() {
      return impl_OpMulMatrix_DM_M_eq_DM_Float;
   }

   public BinaryRegistry impl_OpMulMatrix_DM_M_eq_DM_Double() {
      return impl_OpMulMatrix_DM_M_eq_DM_Double;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Int() {
      return impl_OpMulMatrix_DM_DM_eq_DM_Int;
   }

   public UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Long() {
      return impl_OpMulMatrix_DM_DM_eq_DM_Long;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Int_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_V_eq_DV_Int = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Long_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_V_eq_DV_Long = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Float_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_V_eq_DV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_V_eq_DV_Double_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_V_eq_DV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Int_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_M_eq_DM_Int = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Long_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_M_eq_DM_Long = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Float_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_M_eq_DM_Float = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_M_eq_DM_Double_$eq(final BinaryRegistry x$1) {
      impl_OpMulMatrix_DM_M_eq_DM_Double = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_DM_eq_DM_Int = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixMultOps$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulMatrix_DM_DM_eq_DM_Long = x$1;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpAdd() {
      return dm_dm_UpdateOp_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpAdd() {
      return dm_dm_UpdateOp_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpAdd() {
      return dm_dm_UpdateOp_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpAdd() {
      return dm_dm_UpdateOp_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpSub() {
      return dm_dm_UpdateOp_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpSub() {
      return dm_dm_UpdateOp_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpSub() {
      return dm_dm_UpdateOp_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpSub() {
      return dm_dm_UpdateOp_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpMulScalar() {
      return dm_dm_UpdateOp_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpMulScalar() {
      return dm_dm_UpdateOp_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpMulScalar() {
      return dm_dm_UpdateOp_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpMulScalar() {
      return dm_dm_UpdateOp_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpDiv() {
      return dm_dm_UpdateOp_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpDiv() {
      return dm_dm_UpdateOp_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpDiv() {
      return dm_dm_UpdateOp_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpDiv() {
      return dm_dm_UpdateOp_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpSet() {
      return dm_dm_UpdateOp_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpSet() {
      return dm_dm_UpdateOp_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpSet() {
      return dm_dm_UpdateOp_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpSet() {
      return dm_dm_UpdateOp_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpMod() {
      return dm_dm_UpdateOp_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpMod() {
      return dm_dm_UpdateOp_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpMod() {
      return dm_dm_UpdateOp_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpMod() {
      return dm_dm_UpdateOp_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Int_OpPow() {
      return dm_dm_UpdateOp_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Double_OpPow() {
      return dm_dm_UpdateOp_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Float_OpPow() {
      return dm_dm_UpdateOp_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_dm_UpdateOp_Long_OpPow() {
      return dm_dm_UpdateOp_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpAdd() {
      return dm_s_UpdateOp_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpAdd() {
      return dm_s_UpdateOp_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpAdd() {
      return dm_s_UpdateOp_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpAdd() {
      return dm_s_UpdateOp_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpSub() {
      return dm_s_UpdateOp_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpSub() {
      return dm_s_UpdateOp_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpSub() {
      return dm_s_UpdateOp_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpSub() {
      return dm_s_UpdateOp_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMulScalar() {
      return dm_s_UpdateOp_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMulScalar() {
      return dm_s_UpdateOp_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMulScalar() {
      return dm_s_UpdateOp_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMulScalar() {
      return dm_s_UpdateOp_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMulMatrix() {
      return dm_s_UpdateOp_Int_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMulMatrix() {
      return dm_s_UpdateOp_Double_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMulMatrix() {
      return dm_s_UpdateOp_Float_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMulMatrix() {
      return dm_s_UpdateOp_Long_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpDiv() {
      return dm_s_UpdateOp_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpDiv() {
      return dm_s_UpdateOp_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpDiv() {
      return dm_s_UpdateOp_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpDiv() {
      return dm_s_UpdateOp_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpSet() {
      return dm_s_UpdateOp_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpSet() {
      return dm_s_UpdateOp_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpSet() {
      return dm_s_UpdateOp_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpSet() {
      return dm_s_UpdateOp_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpMod() {
      return dm_s_UpdateOp_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpMod() {
      return dm_s_UpdateOp_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpMod() {
      return dm_s_UpdateOp_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpMod() {
      return dm_s_UpdateOp_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Int_OpPow() {
      return dm_s_UpdateOp_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Double_OpPow() {
      return dm_s_UpdateOp_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Float_OpPow() {
      return dm_s_UpdateOp_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 dm_s_UpdateOp_Long_OpPow() {
      return dm_s_UpdateOp_Long_OpPow;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpAdd() {
      return op_DM_S_Int_OpAdd;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpAdd() {
      return op_DM_S_Long_OpAdd;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpAdd() {
      return op_DM_S_Float_OpAdd;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpAdd() {
      return op_DM_S_Double_OpAdd;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpSub() {
      return op_DM_S_Int_OpSub;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpSub() {
      return op_DM_S_Long_OpSub;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpSub() {
      return op_DM_S_Float_OpSub;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpSub() {
      return op_DM_S_Double_OpSub;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpMulScalar() {
      return op_DM_S_Int_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpMulScalar() {
      return op_DM_S_Long_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpMulScalar() {
      return op_DM_S_Float_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpMulScalar() {
      return op_DM_S_Double_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpMulMatrix() {
      return op_DM_S_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpMulMatrix() {
      return op_DM_S_Long_OpMulMatrix;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpMulMatrix() {
      return op_DM_S_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpMulMatrix() {
      return op_DM_S_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpMod() {
      return op_DM_S_Int_OpMod;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpMod() {
      return op_DM_S_Long_OpMod;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpMod() {
      return op_DM_S_Float_OpMod;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpMod() {
      return op_DM_S_Double_OpMod;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpDiv() {
      return op_DM_S_Int_OpDiv;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpDiv() {
      return op_DM_S_Long_OpDiv;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpDiv() {
      return op_DM_S_Float_OpDiv;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpDiv() {
      return op_DM_S_Double_OpDiv;
   }

   public UFunc.UImpl2 op_DM_S_Int_OpPow() {
      return op_DM_S_Int_OpPow;
   }

   public UFunc.UImpl2 op_DM_S_Long_OpPow() {
      return op_DM_S_Long_OpPow;
   }

   public UFunc.UImpl2 op_DM_S_Float_OpPow() {
      return op_DM_S_Float_OpPow;
   }

   public UFunc.UImpl2 op_DM_S_Double_OpPow() {
      return op_DM_S_Double_OpPow;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpAdd() {
      return s_dm_op_Int_OpAdd;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpAdd() {
      return s_dm_op_Double_OpAdd;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpAdd() {
      return s_dm_op_Float_OpAdd;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpAdd() {
      return s_dm_op_Long_OpAdd;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpSub() {
      return s_dm_op_Int_OpSub;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpSub() {
      return s_dm_op_Double_OpSub;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpSub() {
      return s_dm_op_Float_OpSub;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpSub() {
      return s_dm_op_Long_OpSub;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpMulScalar() {
      return s_dm_op_Int_OpMulScalar;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpMulScalar() {
      return s_dm_op_Double_OpMulScalar;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpMulScalar() {
      return s_dm_op_Float_OpMulScalar;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpMulScalar() {
      return s_dm_op_Long_OpMulScalar;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpMulMatrix() {
      return s_dm_op_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpMulMatrix() {
      return s_dm_op_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpMulMatrix() {
      return s_dm_op_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpMulMatrix() {
      return s_dm_op_Long_OpMulMatrix;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpDiv() {
      return s_dm_op_Int_OpDiv;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpDiv() {
      return s_dm_op_Double_OpDiv;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpDiv() {
      return s_dm_op_Float_OpDiv;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpDiv() {
      return s_dm_op_Long_OpDiv;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpMod() {
      return s_dm_op_Int_OpMod;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpMod() {
      return s_dm_op_Double_OpMod;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpMod() {
      return s_dm_op_Float_OpMod;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpMod() {
      return s_dm_op_Long_OpMod;
   }

   public UFunc.UImpl2 s_dm_op_Int_OpPow() {
      return s_dm_op_Int_OpPow;
   }

   public UFunc.UImpl2 s_dm_op_Double_OpPow() {
      return s_dm_op_Double_OpPow;
   }

   public UFunc.UImpl2 s_dm_op_Float_OpPow() {
      return s_dm_op_Float_OpPow;
   }

   public UFunc.UImpl2 s_dm_op_Long_OpPow() {
      return s_dm_op_Long_OpPow;
   }

   public UFunc.UImpl2 op_DM_DM_Int_OpAdd() {
      return op_DM_DM_Int_OpAdd;
   }

   public UFunc.UImpl2 op_DM_DM_Long_OpAdd() {
      return op_DM_DM_Long_OpAdd;
   }

   public UFunc.UImpl2 op_DM_DM_Float_OpAdd() {
      return op_DM_DM_Float_OpAdd;
   }

   public UFunc.UImpl2 op_DM_DM_Double_OpAdd() {
      return op_DM_DM_Double_OpAdd;
   }

   public UFunc.UImpl2 op_DM_DM_Int_OpSub() {
      return op_DM_DM_Int_OpSub;
   }

   public UFunc.UImpl2 op_DM_DM_Long_OpSub() {
      return op_DM_DM_Long_OpSub;
   }

   public UFunc.UImpl2 op_DM_DM_Float_OpSub() {
      return op_DM_DM_Float_OpSub;
   }

   public UFunc.UImpl2 op_DM_DM_Double_OpSub() {
      return op_DM_DM_Double_OpSub;
   }

   public UFunc.UImpl2 op_DM_DM_Int_OpMulScalar() {
      return op_DM_DM_Int_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_DM_Long_OpMulScalar() {
      return op_DM_DM_Long_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_DM_Float_OpMulScalar() {
      return op_DM_DM_Float_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_DM_Double_OpMulScalar() {
      return op_DM_DM_Double_OpMulScalar;
   }

   public UFunc.UImpl2 op_DM_DM_Int_OpMod() {
      return op_DM_DM_Int_OpMod;
   }

   public UFunc.UImpl2 op_DM_DM_Long_OpMod() {
      return op_DM_DM_Long_OpMod;
   }

   public UFunc.UImpl2 op_DM_DM_Float_OpMod() {
      return op_DM_DM_Float_OpMod;
   }

   public UFunc.UImpl2 op_DM_DM_Double_OpMod() {
      return op_DM_DM_Double_OpMod;
   }

   public UFunc.UImpl2 op_DM_DM_Int_OpDiv() {
      return op_DM_DM_Int_OpDiv;
   }

   public UFunc.UImpl2 op_DM_DM_Long_OpDiv() {
      return op_DM_DM_Long_OpDiv;
   }

   public UFunc.UImpl2 op_DM_DM_Float_OpDiv() {
      return op_DM_DM_Float_OpDiv;
   }

   public UFunc.UImpl2 op_DM_DM_Double_OpDiv() {
      return op_DM_DM_Double_OpDiv;
   }

   public UFunc.UImpl2 op_DM_DM_Int_OpPow() {
      return op_DM_DM_Int_OpPow;
   }

   public UFunc.UImpl2 op_DM_DM_Long_OpPow() {
      return op_DM_DM_Long_OpPow;
   }

   public UFunc.UImpl2 op_DM_DM_Float_OpPow() {
      return op_DM_DM_Float_OpPow;
   }

   public UFunc.UImpl2 op_DM_DM_Double_OpPow() {
      return op_DM_DM_Double_OpPow;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_dm_UpdateOp_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_dm_UpdateOp_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$dm_s_UpdateOp_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      dm_s_UpdateOp_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_S_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_S_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$s_dm_op_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      s_dm_op_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseMatrixExpandedOps$_setter_$op_DM_DM_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      op_DM_DM_Double_OpPow = x$1;
   }

   public BinaryRegistry op_M_V_Int() {
      return op_M_V_Int;
   }

   public BinaryRegistry op_M_V_Long() {
      return op_M_V_Long;
   }

   public BinaryRegistry op_M_V_Float() {
      return op_M_V_Float;
   }

   public BinaryRegistry op_M_V_Double() {
      return op_M_V_Double;
   }

   public BinaryRegistry op_M_V_BigInt() {
      return op_M_V_BigInt;
   }

   public BinaryRegistry op_M_V_Complex() {
      return op_M_V_Complex;
   }

   public BinaryRegistry op_M_M_Int() {
      return op_M_M_Int;
   }

   public BinaryRegistry op_M_M_Long() {
      return op_M_M_Long;
   }

   public BinaryRegistry op_M_M_Float() {
      return op_M_M_Float;
   }

   public BinaryRegistry op_M_M_Double() {
      return op_M_M_Double;
   }

   public BinaryRegistry op_M_M_BigInt() {
      return op_M_M_BigInt;
   }

   public BinaryRegistry op_M_M_Complex() {
      return op_M_M_Complex;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Int_$eq(final BinaryRegistry x$1) {
      op_M_V_Int = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Long_$eq(final BinaryRegistry x$1) {
      op_M_V_Long = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Float_$eq(final BinaryRegistry x$1) {
      op_M_V_Float = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Double_$eq(final BinaryRegistry x$1) {
      op_M_V_Double = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_BigInt_$eq(final BinaryRegistry x$1) {
      op_M_V_BigInt = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_V_Complex_$eq(final BinaryRegistry x$1) {
      op_M_V_Complex = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Int_$eq(final BinaryRegistry x$1) {
      op_M_M_Int = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Long_$eq(final BinaryRegistry x$1) {
      op_M_M_Long = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Float_$eq(final BinaryRegistry x$1) {
      op_M_M_Float = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Double_$eq(final BinaryRegistry x$1) {
      op_M_M_Double = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_BigInt_$eq(final BinaryRegistry x$1) {
      op_M_M_BigInt = x$1;
   }

   public void breeze$linalg$operators$MatrixMultOps$_setter_$op_M_M_Complex_$eq(final BinaryRegistry x$1) {
      op_M_M_Complex = x$1;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpAdd() {
      return m_m_UpdateOp_Int_OpAdd;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpAdd() {
      return m_m_UpdateOp_Double_OpAdd;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpAdd() {
      return m_m_UpdateOp_Float_OpAdd;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpAdd() {
      return m_m_UpdateOp_Long_OpAdd;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpAdd() {
      return m_m_UpdateOp_BigInt_OpAdd;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Complex_OpAdd() {
      return m_m_UpdateOp_Complex_OpAdd;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpSub() {
      return m_m_UpdateOp_Int_OpSub;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpSub() {
      return m_m_UpdateOp_Double_OpSub;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpSub() {
      return m_m_UpdateOp_Float_OpSub;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpSub() {
      return m_m_UpdateOp_Long_OpSub;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpSub() {
      return m_m_UpdateOp_BigInt_OpSub;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Complex_OpSub() {
      return m_m_UpdateOp_Complex_OpSub;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpMulScalar() {
      return m_m_UpdateOp_Int_OpMulScalar;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpMulScalar() {
      return m_m_UpdateOp_Double_OpMulScalar;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpMulScalar() {
      return m_m_UpdateOp_Float_OpMulScalar;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpMulScalar() {
      return m_m_UpdateOp_Long_OpMulScalar;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpMulScalar() {
      return m_m_UpdateOp_BigInt_OpMulScalar;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Complex_OpMulScalar() {
      return m_m_UpdateOp_Complex_OpMulScalar;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpDiv() {
      return m_m_UpdateOp_Int_OpDiv;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpDiv() {
      return m_m_UpdateOp_Double_OpDiv;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpDiv() {
      return m_m_UpdateOp_Float_OpDiv;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpDiv() {
      return m_m_UpdateOp_Long_OpDiv;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpDiv() {
      return m_m_UpdateOp_BigInt_OpDiv;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Complex_OpDiv() {
      return m_m_UpdateOp_Complex_OpDiv;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpSet() {
      return m_m_UpdateOp_Int_OpSet;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpSet() {
      return m_m_UpdateOp_Double_OpSet;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpSet() {
      return m_m_UpdateOp_Float_OpSet;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpSet() {
      return m_m_UpdateOp_Long_OpSet;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpSet() {
      return m_m_UpdateOp_BigInt_OpSet;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Complex_OpSet() {
      return m_m_UpdateOp_Complex_OpSet;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpMod() {
      return m_m_UpdateOp_Int_OpMod;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpMod() {
      return m_m_UpdateOp_Double_OpMod;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpMod() {
      return m_m_UpdateOp_Float_OpMod;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpMod() {
      return m_m_UpdateOp_Long_OpMod;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_BigInt_OpMod() {
      return m_m_UpdateOp_BigInt_OpMod;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Int_OpPow() {
      return m_m_UpdateOp_Int_OpPow;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Double_OpPow() {
      return m_m_UpdateOp_Double_OpPow;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Float_OpPow() {
      return m_m_UpdateOp_Float_OpPow;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Long_OpPow() {
      return m_m_UpdateOp_Long_OpPow;
   }

   public BinaryUpdateRegistry m_m_UpdateOp_Complex_OpPow() {
      return m_m_UpdateOp_Complex_OpPow;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpAdd() {
      return m_s_UpdateOp_Int_OpAdd;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpAdd() {
      return m_s_UpdateOp_Double_OpAdd;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpAdd() {
      return m_s_UpdateOp_Float_OpAdd;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpAdd() {
      return m_s_UpdateOp_Long_OpAdd;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpAdd() {
      return m_s_UpdateOp_BigInt_OpAdd;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpAdd() {
      return m_s_UpdateOp_Complex_OpAdd;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpSub() {
      return m_s_UpdateOp_Int_OpSub;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpSub() {
      return m_s_UpdateOp_Double_OpSub;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpSub() {
      return m_s_UpdateOp_Float_OpSub;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpSub() {
      return m_s_UpdateOp_Long_OpSub;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpSub() {
      return m_s_UpdateOp_BigInt_OpSub;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpSub() {
      return m_s_UpdateOp_Complex_OpSub;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpMulScalar() {
      return m_s_UpdateOp_Int_OpMulScalar;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpMulScalar() {
      return m_s_UpdateOp_Double_OpMulScalar;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpMulScalar() {
      return m_s_UpdateOp_Float_OpMulScalar;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpMulScalar() {
      return m_s_UpdateOp_Long_OpMulScalar;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMulScalar() {
      return m_s_UpdateOp_BigInt_OpMulScalar;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpMulScalar() {
      return m_s_UpdateOp_Complex_OpMulScalar;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpMulMatrix() {
      return m_s_UpdateOp_Int_OpMulMatrix;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpMulMatrix() {
      return m_s_UpdateOp_Double_OpMulMatrix;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpMulMatrix() {
      return m_s_UpdateOp_Float_OpMulMatrix;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpMulMatrix() {
      return m_s_UpdateOp_Long_OpMulMatrix;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMulMatrix() {
      return m_s_UpdateOp_BigInt_OpMulMatrix;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpMulMatrix() {
      return m_s_UpdateOp_Complex_OpMulMatrix;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpDiv() {
      return m_s_UpdateOp_Int_OpDiv;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpDiv() {
      return m_s_UpdateOp_Double_OpDiv;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpDiv() {
      return m_s_UpdateOp_Float_OpDiv;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpDiv() {
      return m_s_UpdateOp_Long_OpDiv;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpDiv() {
      return m_s_UpdateOp_BigInt_OpDiv;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpDiv() {
      return m_s_UpdateOp_Complex_OpDiv;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpSet() {
      return m_s_UpdateOp_Int_OpSet;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpSet() {
      return m_s_UpdateOp_Double_OpSet;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpSet() {
      return m_s_UpdateOp_Float_OpSet;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpSet() {
      return m_s_UpdateOp_Long_OpSet;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpSet() {
      return m_s_UpdateOp_BigInt_OpSet;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpSet() {
      return m_s_UpdateOp_Complex_OpSet;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpMod() {
      return m_s_UpdateOp_Int_OpMod;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpMod() {
      return m_s_UpdateOp_Double_OpMod;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpMod() {
      return m_s_UpdateOp_Float_OpMod;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpMod() {
      return m_s_UpdateOp_Long_OpMod;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_BigInt_OpMod() {
      return m_s_UpdateOp_BigInt_OpMod;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Int_OpPow() {
      return m_s_UpdateOp_Int_OpPow;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Double_OpPow() {
      return m_s_UpdateOp_Double_OpPow;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Float_OpPow() {
      return m_s_UpdateOp_Float_OpPow;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Long_OpPow() {
      return m_s_UpdateOp_Long_OpPow;
   }

   public BinaryUpdateRegistry m_s_UpdateOp_Complex_OpPow() {
      return m_s_UpdateOp_Complex_OpPow;
   }

   public BinaryRegistry op_M_S_Int_OpAdd() {
      return op_M_S_Int_OpAdd;
   }

   public BinaryRegistry op_M_S_Long_OpAdd() {
      return op_M_S_Long_OpAdd;
   }

   public BinaryRegistry op_M_S_Float_OpAdd() {
      return op_M_S_Float_OpAdd;
   }

   public BinaryRegistry op_M_S_Double_OpAdd() {
      return op_M_S_Double_OpAdd;
   }

   public BinaryRegistry op_M_S_BigInt_OpAdd() {
      return op_M_S_BigInt_OpAdd;
   }

   public BinaryRegistry op_M_S_Complex_OpAdd() {
      return op_M_S_Complex_OpAdd;
   }

   public BinaryRegistry op_M_S_Int_OpSub() {
      return op_M_S_Int_OpSub;
   }

   public BinaryRegistry op_M_S_Long_OpSub() {
      return op_M_S_Long_OpSub;
   }

   public BinaryRegistry op_M_S_Float_OpSub() {
      return op_M_S_Float_OpSub;
   }

   public BinaryRegistry op_M_S_Double_OpSub() {
      return op_M_S_Double_OpSub;
   }

   public BinaryRegistry op_M_S_BigInt_OpSub() {
      return op_M_S_BigInt_OpSub;
   }

   public BinaryRegistry op_M_S_Complex_OpSub() {
      return op_M_S_Complex_OpSub;
   }

   public BinaryRegistry op_M_S_Int_OpMulScalar() {
      return op_M_S_Int_OpMulScalar;
   }

   public BinaryRegistry op_M_S_Long_OpMulScalar() {
      return op_M_S_Long_OpMulScalar;
   }

   public BinaryRegistry op_M_S_Float_OpMulScalar() {
      return op_M_S_Float_OpMulScalar;
   }

   public BinaryRegistry op_M_S_Double_OpMulScalar() {
      return op_M_S_Double_OpMulScalar;
   }

   public BinaryRegistry op_M_S_BigInt_OpMulScalar() {
      return op_M_S_BigInt_OpMulScalar;
   }

   public BinaryRegistry op_M_S_Complex_OpMulScalar() {
      return op_M_S_Complex_OpMulScalar;
   }

   public BinaryRegistry op_M_S_Int_OpMulMatrix() {
      return op_M_S_Int_OpMulMatrix;
   }

   public BinaryRegistry op_M_S_Long_OpMulMatrix() {
      return op_M_S_Long_OpMulMatrix;
   }

   public BinaryRegistry op_M_S_Float_OpMulMatrix() {
      return op_M_S_Float_OpMulMatrix;
   }

   public BinaryRegistry op_M_S_Double_OpMulMatrix() {
      return op_M_S_Double_OpMulMatrix;
   }

   public BinaryRegistry op_M_S_BigInt_OpMulMatrix() {
      return op_M_S_BigInt_OpMulMatrix;
   }

   public BinaryRegistry op_M_S_Complex_OpMulMatrix() {
      return op_M_S_Complex_OpMulMatrix;
   }

   public BinaryRegistry op_M_S_Int_OpMod() {
      return op_M_S_Int_OpMod;
   }

   public BinaryRegistry op_M_S_Long_OpMod() {
      return op_M_S_Long_OpMod;
   }

   public BinaryRegistry op_M_S_Float_OpMod() {
      return op_M_S_Float_OpMod;
   }

   public BinaryRegistry op_M_S_Double_OpMod() {
      return op_M_S_Double_OpMod;
   }

   public BinaryRegistry op_M_S_BigInt_OpMod() {
      return op_M_S_BigInt_OpMod;
   }

   public BinaryRegistry op_M_S_Int_OpDiv() {
      return op_M_S_Int_OpDiv;
   }

   public BinaryRegistry op_M_S_Long_OpDiv() {
      return op_M_S_Long_OpDiv;
   }

   public BinaryRegistry op_M_S_Float_OpDiv() {
      return op_M_S_Float_OpDiv;
   }

   public BinaryRegistry op_M_S_Double_OpDiv() {
      return op_M_S_Double_OpDiv;
   }

   public BinaryRegistry op_M_S_BigInt_OpDiv() {
      return op_M_S_BigInt_OpDiv;
   }

   public BinaryRegistry op_M_S_Complex_OpDiv() {
      return op_M_S_Complex_OpDiv;
   }

   public BinaryRegistry op_M_S_Int_OpPow() {
      return op_M_S_Int_OpPow;
   }

   public BinaryRegistry op_M_S_Long_OpPow() {
      return op_M_S_Long_OpPow;
   }

   public BinaryRegistry op_M_S_Float_OpPow() {
      return op_M_S_Float_OpPow;
   }

   public BinaryRegistry op_M_S_Double_OpPow() {
      return op_M_S_Double_OpPow;
   }

   public BinaryRegistry op_M_S_Complex_OpPow() {
      return op_M_S_Complex_OpPow;
   }

   public BinaryRegistry op_S_M_Int_OpAdd() {
      return op_S_M_Int_OpAdd;
   }

   public BinaryRegistry op_S_M_Long_OpAdd() {
      return op_S_M_Long_OpAdd;
   }

   public BinaryRegistry op_S_M_Float_OpAdd() {
      return op_S_M_Float_OpAdd;
   }

   public BinaryRegistry op_S_M_Double_OpAdd() {
      return op_S_M_Double_OpAdd;
   }

   public BinaryRegistry op_S_M_BigInt_OpAdd() {
      return op_S_M_BigInt_OpAdd;
   }

   public BinaryRegistry op_S_M_Complex_OpAdd() {
      return op_S_M_Complex_OpAdd;
   }

   public BinaryRegistry op_S_M_Int_OpSub() {
      return op_S_M_Int_OpSub;
   }

   public BinaryRegistry op_S_M_Long_OpSub() {
      return op_S_M_Long_OpSub;
   }

   public BinaryRegistry op_S_M_Float_OpSub() {
      return op_S_M_Float_OpSub;
   }

   public BinaryRegistry op_S_M_Double_OpSub() {
      return op_S_M_Double_OpSub;
   }

   public BinaryRegistry op_S_M_BigInt_OpSub() {
      return op_S_M_BigInt_OpSub;
   }

   public BinaryRegistry op_S_M_Complex_OpSub() {
      return op_S_M_Complex_OpSub;
   }

   public BinaryRegistry op_S_M_Int_OpMulScalar() {
      return op_S_M_Int_OpMulScalar;
   }

   public BinaryRegistry op_S_M_Long_OpMulScalar() {
      return op_S_M_Long_OpMulScalar;
   }

   public BinaryRegistry op_S_M_Float_OpMulScalar() {
      return op_S_M_Float_OpMulScalar;
   }

   public BinaryRegistry op_S_M_Double_OpMulScalar() {
      return op_S_M_Double_OpMulScalar;
   }

   public BinaryRegistry op_S_M_BigInt_OpMulScalar() {
      return op_S_M_BigInt_OpMulScalar;
   }

   public BinaryRegistry op_S_M_Complex_OpMulScalar() {
      return op_S_M_Complex_OpMulScalar;
   }

   public BinaryRegistry op_S_M_Int_OpMulMatrix() {
      return op_S_M_Int_OpMulMatrix;
   }

   public BinaryRegistry op_S_M_Long_OpMulMatrix() {
      return op_S_M_Long_OpMulMatrix;
   }

   public BinaryRegistry op_S_M_Float_OpMulMatrix() {
      return op_S_M_Float_OpMulMatrix;
   }

   public BinaryRegistry op_S_M_Double_OpMulMatrix() {
      return op_S_M_Double_OpMulMatrix;
   }

   public BinaryRegistry op_S_M_BigInt_OpMulMatrix() {
      return op_S_M_BigInt_OpMulMatrix;
   }

   public BinaryRegistry op_S_M_Complex_OpMulMatrix() {
      return op_S_M_Complex_OpMulMatrix;
   }

   public BinaryRegistry op_S_M_Int_OpDiv() {
      return op_S_M_Int_OpDiv;
   }

   public BinaryRegistry op_S_M_Long_OpDiv() {
      return op_S_M_Long_OpDiv;
   }

   public BinaryRegistry op_S_M_Float_OpDiv() {
      return op_S_M_Float_OpDiv;
   }

   public BinaryRegistry op_S_M_Double_OpDiv() {
      return op_S_M_Double_OpDiv;
   }

   public BinaryRegistry op_S_M_BigInt_OpDiv() {
      return op_S_M_BigInt_OpDiv;
   }

   public BinaryRegistry op_S_M_Complex_OpDiv() {
      return op_S_M_Complex_OpDiv;
   }

   public BinaryRegistry op_S_M_Int_OpMod() {
      return op_S_M_Int_OpMod;
   }

   public BinaryRegistry op_S_M_Long_OpMod() {
      return op_S_M_Long_OpMod;
   }

   public BinaryRegistry op_S_M_Float_OpMod() {
      return op_S_M_Float_OpMod;
   }

   public BinaryRegistry op_S_M_Double_OpMod() {
      return op_S_M_Double_OpMod;
   }

   public BinaryRegistry op_S_M_BigInt_OpMod() {
      return op_S_M_BigInt_OpMod;
   }

   public BinaryRegistry op_S_M_Int_OpPow() {
      return op_S_M_Int_OpPow;
   }

   public BinaryRegistry op_S_M_Long_OpPow() {
      return op_S_M_Long_OpPow;
   }

   public BinaryRegistry op_S_M_Float_OpPow() {
      return op_S_M_Float_OpPow;
   }

   public BinaryRegistry op_S_M_Double_OpPow() {
      return op_S_M_Double_OpPow;
   }

   public BinaryRegistry op_S_M_Complex_OpPow() {
      return op_S_M_Complex_OpPow;
   }

   public BinaryRegistry op_M_DM_Int_OpAdd() {
      return op_M_DM_Int_OpAdd;
   }

   public BinaryRegistry op_M_DM_Long_OpAdd() {
      return op_M_DM_Long_OpAdd;
   }

   public BinaryRegistry op_M_DM_Float_OpAdd() {
      return op_M_DM_Float_OpAdd;
   }

   public BinaryRegistry op_M_DM_Double_OpAdd() {
      return op_M_DM_Double_OpAdd;
   }

   public BinaryRegistry op_M_DM_BigInt_OpAdd() {
      return op_M_DM_BigInt_OpAdd;
   }

   public BinaryRegistry op_M_DM_Complex_OpAdd() {
      return op_M_DM_Complex_OpAdd;
   }

   public BinaryRegistry op_M_DM_Int_OpSub() {
      return op_M_DM_Int_OpSub;
   }

   public BinaryRegistry op_M_DM_Long_OpSub() {
      return op_M_DM_Long_OpSub;
   }

   public BinaryRegistry op_M_DM_Float_OpSub() {
      return op_M_DM_Float_OpSub;
   }

   public BinaryRegistry op_M_DM_Double_OpSub() {
      return op_M_DM_Double_OpSub;
   }

   public BinaryRegistry op_M_DM_BigInt_OpSub() {
      return op_M_DM_BigInt_OpSub;
   }

   public BinaryRegistry op_M_DM_Complex_OpSub() {
      return op_M_DM_Complex_OpSub;
   }

   public BinaryRegistry op_M_DM_Int_OpMulScalar() {
      return op_M_DM_Int_OpMulScalar;
   }

   public BinaryRegistry op_M_DM_Long_OpMulScalar() {
      return op_M_DM_Long_OpMulScalar;
   }

   public BinaryRegistry op_M_DM_Float_OpMulScalar() {
      return op_M_DM_Float_OpMulScalar;
   }

   public BinaryRegistry op_M_DM_Double_OpMulScalar() {
      return op_M_DM_Double_OpMulScalar;
   }

   public BinaryRegistry op_M_DM_BigInt_OpMulScalar() {
      return op_M_DM_BigInt_OpMulScalar;
   }

   public BinaryRegistry op_M_DM_Complex_OpMulScalar() {
      return op_M_DM_Complex_OpMulScalar;
   }

   public BinaryRegistry op_M_DM_Int_OpMod() {
      return op_M_DM_Int_OpMod;
   }

   public BinaryRegistry op_M_DM_Long_OpMod() {
      return op_M_DM_Long_OpMod;
   }

   public BinaryRegistry op_M_DM_Float_OpMod() {
      return op_M_DM_Float_OpMod;
   }

   public BinaryRegistry op_M_DM_Double_OpMod() {
      return op_M_DM_Double_OpMod;
   }

   public BinaryRegistry op_M_DM_BigInt_OpMod() {
      return op_M_DM_BigInt_OpMod;
   }

   public BinaryRegistry op_M_DM_Int_OpDiv() {
      return op_M_DM_Int_OpDiv;
   }

   public BinaryRegistry op_M_DM_Long_OpDiv() {
      return op_M_DM_Long_OpDiv;
   }

   public BinaryRegistry op_M_DM_Float_OpDiv() {
      return op_M_DM_Float_OpDiv;
   }

   public BinaryRegistry op_M_DM_Double_OpDiv() {
      return op_M_DM_Double_OpDiv;
   }

   public BinaryRegistry op_M_DM_BigInt_OpDiv() {
      return op_M_DM_BigInt_OpDiv;
   }

   public BinaryRegistry op_M_DM_Complex_OpDiv() {
      return op_M_DM_Complex_OpDiv;
   }

   public BinaryRegistry op_M_DM_Int_OpPow() {
      return op_M_DM_Int_OpPow;
   }

   public BinaryRegistry op_M_DM_Long_OpPow() {
      return op_M_DM_Long_OpPow;
   }

   public BinaryRegistry op_M_DM_Float_OpPow() {
      return op_M_DM_Float_OpPow;
   }

   public BinaryRegistry op_M_DM_Double_OpPow() {
      return op_M_DM_Double_OpPow;
   }

   public BinaryRegistry op_M_DM_Complex_OpPow() {
      return op_M_DM_Complex_OpPow;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_BigInt_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_BigInt_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Complex_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Complex_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_BigInt_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_BigInt_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Complex_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Complex_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_BigInt_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_BigInt_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Complex_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Complex_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_BigInt_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_BigInt_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Complex_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Complex_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_BigInt_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_BigInt_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Complex_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Complex_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_BigInt_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_BigInt_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Int_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Double_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Float_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Long_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_m_UpdateOp_Complex_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_m_UpdateOp_Complex_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpSet = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_BigInt_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_BigInt_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Int_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Double_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Float_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Long_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$m_s_UpdateOp_Complex_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      m_s_UpdateOp_Complex_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_BigInt_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_S_BigInt_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Complex_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_S_Complex_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_BigInt_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_S_BigInt_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Complex_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_S_Complex_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_BigInt_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_S_BigInt_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Complex_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_S_Complex_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_BigInt_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_M_S_BigInt_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Complex_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_M_S_Complex_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_BigInt_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_S_BigInt_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_BigInt_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_S_BigInt_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Complex_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_S_Complex_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Int_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_S_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Long_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_S_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Float_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_S_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Double_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_S_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_S_Complex_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_S_Complex_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_BigInt_OpAdd_$eq(final BinaryRegistry x$1) {
      op_S_M_BigInt_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Complex_OpAdd_$eq(final BinaryRegistry x$1) {
      op_S_M_Complex_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpSub_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpSub_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpSub_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpSub_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_BigInt_OpSub_$eq(final BinaryRegistry x$1) {
      op_S_M_BigInt_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Complex_OpSub_$eq(final BinaryRegistry x$1) {
      op_S_M_Complex_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_BigInt_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_S_M_BigInt_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Complex_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_S_M_Complex_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_BigInt_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_S_M_BigInt_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Complex_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      op_S_M_Complex_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_BigInt_OpDiv_$eq(final BinaryRegistry x$1) {
      op_S_M_BigInt_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Complex_OpDiv_$eq(final BinaryRegistry x$1) {
      op_S_M_Complex_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpMod_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpMod_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpMod_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpMod_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_BigInt_OpMod_$eq(final BinaryRegistry x$1) {
      op_S_M_BigInt_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Int_OpPow_$eq(final BinaryRegistry x$1) {
      op_S_M_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Long_OpPow_$eq(final BinaryRegistry x$1) {
      op_S_M_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Float_OpPow_$eq(final BinaryRegistry x$1) {
      op_S_M_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Double_OpPow_$eq(final BinaryRegistry x$1) {
      op_S_M_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_S_M_Complex_OpPow_$eq(final BinaryRegistry x$1) {
      op_S_M_Complex_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_DM_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_DM_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_DM_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_DM_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_BigInt_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_DM_BigInt_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Complex_OpAdd_$eq(final BinaryRegistry x$1) {
      op_M_DM_Complex_OpAdd = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Int_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_DM_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Long_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_DM_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Float_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_DM_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Double_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_DM_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_BigInt_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_DM_BigInt_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Complex_OpSub_$eq(final BinaryRegistry x$1) {
      op_M_DM_Complex_OpSub = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Int_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_DM_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Long_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_DM_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Float_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_DM_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Double_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_DM_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_BigInt_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_DM_BigInt_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Complex_OpMulScalar_$eq(final BinaryRegistry x$1) {
      op_M_DM_Complex_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Int_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_DM_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Long_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_DM_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Float_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_DM_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Double_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_DM_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_BigInt_OpMod_$eq(final BinaryRegistry x$1) {
      op_M_DM_BigInt_OpMod = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_DM_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_DM_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_DM_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_DM_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_BigInt_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_DM_BigInt_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Complex_OpDiv_$eq(final BinaryRegistry x$1) {
      op_M_DM_Complex_OpDiv = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Int_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_DM_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Long_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_DM_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Float_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_DM_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Double_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_DM_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$MatrixExpandedOps$_setter_$op_M_DM_Complex_OpPow_$eq(final BinaryRegistry x$1) {
      op_M_DM_Complex_OpPow = x$1;
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Double() {
      return impl_OpAdd_InPlace_DV_DV_Double;
   }

   public DenseVector_DoubleOps.impl_scaleAdd_InPlace_DV_T_DV_Double$ impl_scaleAdd_InPlace_DV_T_DV_Double() {
      if (impl_scaleAdd_InPlace_DV_T_DV_Double$module == null) {
         this.impl_scaleAdd_InPlace_DV_T_DV_Double$lzycompute$1();
      }

      return impl_scaleAdd_InPlace_DV_T_DV_Double$module;
   }

   public UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Double() {
      return impl_OpAdd_DV_DV_eq_DV_Double;
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Double() {
      return impl_OpSub_InPlace_DV_DV_Double;
   }

   public UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Double() {
      return impl_OpSub_DV_DV_eq_DV_Double;
   }

   public DenseVector_DoubleOps.canDotD$ canDotD() {
      if (canDotD$module == null) {
         this.canDotD$lzycompute$1();
      }

      return canDotD$module;
   }

   public void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpAdd_InPlace_DV_DV_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpAdd_InPlace_DV_DV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpAdd_DV_DV_eq_DV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpAdd_DV_DV_eq_DV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpSub_InPlace_DV_DV_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSub_InPlace_DV_DV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpSub_DV_DV_eq_DV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpSub_DV_DV_eq_DV_Double = x$1;
   }

   public DenseVector_FloatOps.impl_scaledAdd_InPlace_DV_S_DV_Float$ impl_scaledAdd_InPlace_DV_S_DV_Float() {
      if (impl_scaledAdd_InPlace_DV_S_DV_Float$module == null) {
         this.impl_scaledAdd_InPlace_DV_S_DV_Float$lzycompute$1();
      }

      return impl_scaledAdd_InPlace_DV_S_DV_Float$module;
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Float() {
      return impl_OpAdd_InPlace_DV_DV_Float;
   }

   public UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Float() {
      return impl_OpAdd_DV_DV_eq_DV_Float;
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Float() {
      return impl_OpSub_InPlace_DV_DV_Float;
   }

   public UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Float() {
      return impl_OpSub_DV_DV_eq_DV_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Float() {
      return impl_OpMulInner_DV_DV_eq_S_Float;
   }

   public void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpAdd_InPlace_DV_DV_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpAdd_InPlace_DV_DV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpAdd_DV_DV_eq_DV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpAdd_DV_DV_eq_DV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpSub_InPlace_DV_DV_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_OpSub_InPlace_DV_DV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpSub_DV_DV_eq_DV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpSub_DV_DV_eq_DV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_DV_eq_S_Float = x$1;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpAdd() {
      return impl_Op_DV_S_eq_DV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpAdd() {
      return impl_Op_DV_S_eq_DV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpAdd() {
      return impl_Op_DV_S_eq_DV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpAdd() {
      return impl_Op_DV_S_eq_DV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSub() {
      return impl_Op_DV_S_eq_DV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSub() {
      return impl_Op_DV_S_eq_DV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSub() {
      return impl_Op_DV_S_eq_DV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSub() {
      return impl_Op_DV_S_eq_DV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulScalar() {
      return impl_Op_DV_S_eq_DV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulScalar() {
      return impl_Op_DV_S_eq_DV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulScalar() {
      return impl_Op_DV_S_eq_DV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulScalar() {
      return impl_Op_DV_S_eq_DV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulMatrix() {
      return impl_Op_DV_S_eq_DV_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulMatrix() {
      return impl_Op_DV_S_eq_DV_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulMatrix() {
      return impl_Op_DV_S_eq_DV_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulMatrix() {
      return impl_Op_DV_S_eq_DV_Long_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpDiv() {
      return impl_Op_DV_S_eq_DV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpDiv() {
      return impl_Op_DV_S_eq_DV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpDiv() {
      return impl_Op_DV_S_eq_DV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpDiv() {
      return impl_Op_DV_S_eq_DV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSet() {
      return impl_Op_DV_S_eq_DV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSet() {
      return impl_Op_DV_S_eq_DV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSet() {
      return impl_Op_DV_S_eq_DV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSet() {
      return impl_Op_DV_S_eq_DV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMod() {
      return impl_Op_DV_S_eq_DV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMod() {
      return impl_Op_DV_S_eq_DV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMod() {
      return impl_Op_DV_S_eq_DV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMod() {
      return impl_Op_DV_S_eq_DV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpPow() {
      return impl_Op_DV_S_eq_DV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpPow() {
      return impl_Op_DV_S_eq_DV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpPow() {
      return impl_Op_DV_S_eq_DV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpPow() {
      return impl_Op_DV_S_eq_DV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpAdd() {
      return impl_Op_S_DV_eq_DV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpAdd() {
      return impl_Op_S_DV_eq_DV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpAdd() {
      return impl_Op_S_DV_eq_DV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpAdd() {
      return impl_Op_S_DV_eq_DV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSub() {
      return impl_Op_S_DV_eq_DV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSub() {
      return impl_Op_S_DV_eq_DV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSub() {
      return impl_Op_S_DV_eq_DV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSub() {
      return impl_Op_S_DV_eq_DV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulScalar() {
      return impl_Op_S_DV_eq_DV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulScalar() {
      return impl_Op_S_DV_eq_DV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulScalar() {
      return impl_Op_S_DV_eq_DV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulScalar() {
      return impl_Op_S_DV_eq_DV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulMatrix() {
      return impl_Op_S_DV_eq_DV_Int_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulMatrix() {
      return impl_Op_S_DV_eq_DV_Double_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulMatrix() {
      return impl_Op_S_DV_eq_DV_Float_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulMatrix() {
      return impl_Op_S_DV_eq_DV_Long_OpMulMatrix;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpDiv() {
      return impl_Op_S_DV_eq_DV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpDiv() {
      return impl_Op_S_DV_eq_DV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpDiv() {
      return impl_Op_S_DV_eq_DV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpDiv() {
      return impl_Op_S_DV_eq_DV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSet() {
      return impl_Op_S_DV_eq_DV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSet() {
      return impl_Op_S_DV_eq_DV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSet() {
      return impl_Op_S_DV_eq_DV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSet() {
      return impl_Op_S_DV_eq_DV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMod() {
      return impl_Op_S_DV_eq_DV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMod() {
      return impl_Op_S_DV_eq_DV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMod() {
      return impl_Op_S_DV_eq_DV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMod() {
      return impl_Op_S_DV_eq_DV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpPow() {
      return impl_Op_S_DV_eq_DV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpPow() {
      return impl_Op_S_DV_eq_DV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpPow() {
      return impl_Op_S_DV_eq_DV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpPow() {
      return impl_Op_S_DV_eq_DV_Long_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpAdd() {
      return impl_Op_DV_DV_eq_DV_Int_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpAdd() {
      return impl_Op_DV_DV_eq_DV_Double_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpAdd() {
      return impl_Op_DV_DV_eq_DV_Float_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpAdd() {
      return impl_Op_DV_DV_eq_DV_Long_OpAdd;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSub() {
      return impl_Op_DV_DV_eq_DV_Int_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSub() {
      return impl_Op_DV_DV_eq_DV_Double_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSub() {
      return impl_Op_DV_DV_eq_DV_Float_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSub() {
      return impl_Op_DV_DV_eq_DV_Long_OpSub;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMulScalar() {
      return impl_Op_DV_DV_eq_DV_Int_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMulScalar() {
      return impl_Op_DV_DV_eq_DV_Double_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMulScalar() {
      return impl_Op_DV_DV_eq_DV_Float_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMulScalar() {
      return impl_Op_DV_DV_eq_DV_Long_OpMulScalar;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpDiv() {
      return impl_Op_DV_DV_eq_DV_Int_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpDiv() {
      return impl_Op_DV_DV_eq_DV_Double_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpDiv() {
      return impl_Op_DV_DV_eq_DV_Float_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpDiv() {
      return impl_Op_DV_DV_eq_DV_Long_OpDiv;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSet() {
      return impl_Op_DV_DV_eq_DV_Int_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSet() {
      return impl_Op_DV_DV_eq_DV_Double_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSet() {
      return impl_Op_DV_DV_eq_DV_Float_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSet() {
      return impl_Op_DV_DV_eq_DV_Long_OpSet;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMod() {
      return impl_Op_DV_DV_eq_DV_Int_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMod() {
      return impl_Op_DV_DV_eq_DV_Double_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMod() {
      return impl_Op_DV_DV_eq_DV_Float_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMod() {
      return impl_Op_DV_DV_eq_DV_Long_OpMod;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpPow() {
      return impl_Op_DV_DV_eq_DV_Int_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpPow() {
      return impl_Op_DV_DV_eq_DV_Double_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpPow() {
      return impl_Op_DV_DV_eq_DV_Float_OpPow;
   }

   public UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpPow() {
      return impl_Op_DV_DV_eq_DV_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpAdd() {
      return impl_Op_InPlace_DV_DV_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpAdd() {
      return impl_Op_InPlace_DV_DV_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpAdd() {
      return impl_Op_InPlace_DV_DV_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpAdd() {
      return impl_Op_InPlace_DV_DV_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSub() {
      return impl_Op_InPlace_DV_DV_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSub() {
      return impl_Op_InPlace_DV_DV_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSub() {
      return impl_Op_InPlace_DV_DV_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSub() {
      return impl_Op_InPlace_DV_DV_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMulScalar() {
      return impl_Op_InPlace_DV_DV_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMulScalar() {
      return impl_Op_InPlace_DV_DV_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMulScalar() {
      return impl_Op_InPlace_DV_DV_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMulScalar() {
      return impl_Op_InPlace_DV_DV_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpDiv() {
      return impl_Op_InPlace_DV_DV_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpDiv() {
      return impl_Op_InPlace_DV_DV_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpDiv() {
      return impl_Op_InPlace_DV_DV_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpDiv() {
      return impl_Op_InPlace_DV_DV_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSet() {
      return impl_Op_InPlace_DV_DV_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSet() {
      return impl_Op_InPlace_DV_DV_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSet() {
      return impl_Op_InPlace_DV_DV_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSet() {
      return impl_Op_InPlace_DV_DV_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMod() {
      return impl_Op_InPlace_DV_DV_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMod() {
      return impl_Op_InPlace_DV_DV_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMod() {
      return impl_Op_InPlace_DV_DV_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMod() {
      return impl_Op_InPlace_DV_DV_Long_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpPow() {
      return impl_Op_InPlace_DV_DV_Int_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpPow() {
      return impl_Op_InPlace_DV_DV_Double_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpPow() {
      return impl_Op_InPlace_DV_DV_Float_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpPow() {
      return impl_Op_InPlace_DV_DV_Long_OpPow;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpAdd() {
      return impl_Op_InPlace_DV_S_Int_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpAdd() {
      return impl_Op_InPlace_DV_S_Double_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpAdd() {
      return impl_Op_InPlace_DV_S_Float_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpAdd() {
      return impl_Op_InPlace_DV_S_Long_OpAdd;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSub() {
      return impl_Op_InPlace_DV_S_Int_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSub() {
      return impl_Op_InPlace_DV_S_Double_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSub() {
      return impl_Op_InPlace_DV_S_Float_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSub() {
      return impl_Op_InPlace_DV_S_Long_OpSub;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulScalar() {
      return impl_Op_InPlace_DV_S_Int_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulScalar() {
      return impl_Op_InPlace_DV_S_Double_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulScalar() {
      return impl_Op_InPlace_DV_S_Float_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulScalar() {
      return impl_Op_InPlace_DV_S_Long_OpMulScalar;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulMatrix() {
      return impl_Op_InPlace_DV_S_Int_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulMatrix() {
      return impl_Op_InPlace_DV_S_Double_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulMatrix() {
      return impl_Op_InPlace_DV_S_Float_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulMatrix() {
      return impl_Op_InPlace_DV_S_Long_OpMulMatrix;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpDiv() {
      return impl_Op_InPlace_DV_S_Int_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpDiv() {
      return impl_Op_InPlace_DV_S_Double_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpDiv() {
      return impl_Op_InPlace_DV_S_Float_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpDiv() {
      return impl_Op_InPlace_DV_S_Long_OpDiv;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSet() {
      return impl_Op_InPlace_DV_S_Int_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSet() {
      return impl_Op_InPlace_DV_S_Double_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSet() {
      return impl_Op_InPlace_DV_S_Float_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSet() {
      return impl_Op_InPlace_DV_S_Long_OpSet;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMod() {
      return impl_Op_InPlace_DV_S_Int_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMod() {
      return impl_Op_InPlace_DV_S_Double_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMod() {
      return impl_Op_InPlace_DV_S_Float_OpMod;
   }

   public UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMod() {
      return impl_Op_InPlace_DV_S_Long_OpMod;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Int() {
      return impl_OpMulInner_DV_DV_eq_S_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Long() {
      return impl_OpMulInner_DV_DV_eq_S_Long;
   }

   public UFunc.UImpl2 impl_zipValues_DV_DV_Int() {
      return impl_zipValues_DV_DV_Int;
   }

   public UFunc.UImpl2 impl_zipValues_DV_DV_Double() {
      return impl_zipValues_DV_DV_Double;
   }

   public UFunc.UImpl2 impl_zipValues_DV_DV_Float() {
      return impl_zipValues_DV_DV_Float;
   }

   public UFunc.UImpl2 impl_zipValues_DV_DV_Long() {
      return impl_zipValues_DV_DV_Long;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Int() {
      return impl_scaleAdd_InPlace_DV_S_DV_Int;
   }

   public UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Long() {
      return impl_scaleAdd_InPlace_DV_S_DV_Long;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_S_eq_DV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_S_DV_eq_DV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1) {
      impl_Op_DV_DV_eq_DV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_DV_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1) {
      impl_Op_InPlace_DV_S_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_DV_eq_S_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_DV_eq_S_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_DV_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_DV_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_DV_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_zipValues_DV_DV_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_scaleAdd_InPlace_DV_S_DV_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_DV_S_DV_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_scaleAdd_InPlace_DV_S_DV_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      impl_scaleAdd_InPlace_DV_S_DV_Long = x$1;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Int() {
      return impl_OpMulInner_DV_V_eq_S_Int;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Double() {
      return impl_OpMulInner_DV_V_eq_S_Double;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Float() {
      return impl_OpMulInner_DV_V_eq_S_Float;
   }

   public UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Long() {
      return impl_OpMulInner_DV_V_eq_S_Long;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpAdd() {
      return impl_Op_DV_V_eq_V_Int_OpAdd;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpAdd() {
      return impl_Op_DV_V_eq_V_Double_OpAdd;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpAdd() {
      return impl_Op_DV_V_eq_V_Float_OpAdd;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpAdd() {
      return impl_Op_DV_V_eq_V_Long_OpAdd;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSub() {
      return impl_Op_DV_V_eq_V_Int_OpSub;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSub() {
      return impl_Op_DV_V_eq_V_Double_OpSub;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSub() {
      return impl_Op_DV_V_eq_V_Float_OpSub;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSub() {
      return impl_Op_DV_V_eq_V_Long_OpSub;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMulScalar() {
      return impl_Op_DV_V_eq_V_Int_OpMulScalar;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMulScalar() {
      return impl_Op_DV_V_eq_V_Double_OpMulScalar;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMulScalar() {
      return impl_Op_DV_V_eq_V_Float_OpMulScalar;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMulScalar() {
      return impl_Op_DV_V_eq_V_Long_OpMulScalar;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpDiv() {
      return impl_Op_DV_V_eq_V_Int_OpDiv;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpDiv() {
      return impl_Op_DV_V_eq_V_Double_OpDiv;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpDiv() {
      return impl_Op_DV_V_eq_V_Float_OpDiv;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpDiv() {
      return impl_Op_DV_V_eq_V_Long_OpDiv;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSet() {
      return impl_Op_DV_V_eq_V_Int_OpSet;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSet() {
      return impl_Op_DV_V_eq_V_Double_OpSet;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSet() {
      return impl_Op_DV_V_eq_V_Float_OpSet;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSet() {
      return impl_Op_DV_V_eq_V_Long_OpSet;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMod() {
      return impl_Op_DV_V_eq_V_Int_OpMod;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMod() {
      return impl_Op_DV_V_eq_V_Double_OpMod;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMod() {
      return impl_Op_DV_V_eq_V_Float_OpMod;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMod() {
      return impl_Op_DV_V_eq_V_Long_OpMod;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Int_OpPow() {
      return impl_Op_DV_V_eq_V_Int_OpPow;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Double_OpPow() {
      return impl_Op_DV_V_eq_V_Double_OpPow;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Float_OpPow() {
      return impl_Op_DV_V_eq_V_Float_OpPow;
   }

   public BinaryRegistry impl_Op_DV_V_eq_V_Long_OpPow() {
      return impl_Op_DV_V_eq_V_Long_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMulScalar() {
      return impl_Op_InPlace_DV_V_Int_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMulScalar() {
      return impl_Op_InPlace_DV_V_Double_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMulScalar() {
      return impl_Op_InPlace_DV_V_Float_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMulScalar() {
      return impl_Op_InPlace_DV_V_Long_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpDiv() {
      return impl_Op_InPlace_DV_V_Int_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpDiv() {
      return impl_Op_InPlace_DV_V_Double_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpDiv() {
      return impl_Op_InPlace_DV_V_Float_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpDiv() {
      return impl_Op_InPlace_DV_V_Long_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpSet() {
      return impl_Op_InPlace_DV_V_Int_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpSet() {
      return impl_Op_InPlace_DV_V_Double_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpSet() {
      return impl_Op_InPlace_DV_V_Float_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpSet() {
      return impl_Op_InPlace_DV_V_Long_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMod() {
      return impl_Op_InPlace_DV_V_Int_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMod() {
      return impl_Op_InPlace_DV_V_Double_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMod() {
      return impl_Op_InPlace_DV_V_Float_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMod() {
      return impl_Op_InPlace_DV_V_Long_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpPow() {
      return impl_Op_InPlace_DV_V_Int_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpPow() {
      return impl_Op_InPlace_DV_V_Double_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpPow() {
      return impl_Op_InPlace_DV_V_Float_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpPow() {
      return impl_Op_InPlace_DV_V_Long_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub() {
      return impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Int_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_V_eq_S_Int = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Double_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_V_eq_S_Double = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Float_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_V_eq_S_Float = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Long_$eq(final UFunc.UImpl2 x$1) {
      impl_OpMulInner_DV_V_eq_S_Long = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_DV_V_eq_V_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub = x$1;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Int_OpAdd() {
      return impl_Op_V_V_eq_V_idempotent_Int_OpAdd;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Double_OpAdd() {
      return impl_Op_V_V_eq_V_idempotent_Double_OpAdd;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Float_OpAdd() {
      return impl_Op_V_V_eq_V_idempotent_Float_OpAdd;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Long_OpAdd() {
      return impl_Op_V_V_eq_V_idempotent_Long_OpAdd;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Int_OpSub() {
      return impl_Op_V_V_eq_V_idempotent_Int_OpSub;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Double_OpSub() {
      return impl_Op_V_V_eq_V_idempotent_Double_OpSub;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Float_OpSub() {
      return impl_Op_V_V_eq_V_idempotent_Float_OpSub;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_idempotent_Long_OpSub() {
      return impl_Op_V_V_eq_V_idempotent_Long_OpSub;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Int() {
      return impl_Op_V_V_eq_V_nilpotent_Int;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Double() {
      return impl_Op_V_V_eq_V_nilpotent_Double;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Float() {
      return impl_Op_V_V_eq_V_nilpotent_Float;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_nilpotent_Long() {
      return impl_Op_V_V_eq_V_nilpotent_Long;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Int_OpDiv() {
      return impl_Op_V_V_eq_V_Int_OpDiv;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Double_OpDiv() {
      return impl_Op_V_V_eq_V_Double_OpDiv;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Float_OpDiv() {
      return impl_Op_V_V_eq_V_Float_OpDiv;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Long_OpDiv() {
      return impl_Op_V_V_eq_V_Long_OpDiv;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Int_OpSet() {
      return impl_Op_V_V_eq_V_Int_OpSet;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Double_OpSet() {
      return impl_Op_V_V_eq_V_Double_OpSet;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Float_OpSet() {
      return impl_Op_V_V_eq_V_Float_OpSet;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Long_OpSet() {
      return impl_Op_V_V_eq_V_Long_OpSet;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Int_OpMod() {
      return impl_Op_V_V_eq_V_Int_OpMod;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Double_OpMod() {
      return impl_Op_V_V_eq_V_Double_OpMod;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Float_OpMod() {
      return impl_Op_V_V_eq_V_Float_OpMod;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Long_OpMod() {
      return impl_Op_V_V_eq_V_Long_OpMod;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Int_OpPow() {
      return impl_Op_V_V_eq_V_Int_OpPow;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Double_OpPow() {
      return impl_Op_V_V_eq_V_Double_OpPow;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Float_OpPow() {
      return impl_Op_V_V_eq_V_Float_OpPow;
   }

   public BinaryRegistry impl_Op_V_V_eq_V_Long_OpPow() {
      return impl_Op_V_V_eq_V_Long_OpPow;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpAdd() {
      return impl_Op_V_S_eq_V_Int_OpAdd;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpAdd() {
      return impl_Op_V_S_eq_V_Double_OpAdd;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpAdd() {
      return impl_Op_V_S_eq_V_Float_OpAdd;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpAdd() {
      return impl_Op_V_S_eq_V_Long_OpAdd;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpSub() {
      return impl_Op_V_S_eq_V_Int_OpSub;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpSub() {
      return impl_Op_V_S_eq_V_Double_OpSub;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpSub() {
      return impl_Op_V_S_eq_V_Float_OpSub;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpSub() {
      return impl_Op_V_S_eq_V_Long_OpSub;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpMulScalar() {
      return impl_Op_V_S_eq_V_Int_OpMulScalar;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpMulScalar() {
      return impl_Op_V_S_eq_V_Double_OpMulScalar;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpMulScalar() {
      return impl_Op_V_S_eq_V_Float_OpMulScalar;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpMulScalar() {
      return impl_Op_V_S_eq_V_Long_OpMulScalar;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpMulMatrix() {
      return impl_Op_V_S_eq_V_Int_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpMulMatrix() {
      return impl_Op_V_S_eq_V_Double_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpMulMatrix() {
      return impl_Op_V_S_eq_V_Float_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpMulMatrix() {
      return impl_Op_V_S_eq_V_Long_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpDiv() {
      return impl_Op_V_S_eq_V_Int_OpDiv;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpDiv() {
      return impl_Op_V_S_eq_V_Double_OpDiv;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpDiv() {
      return impl_Op_V_S_eq_V_Float_OpDiv;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpDiv() {
      return impl_Op_V_S_eq_V_Long_OpDiv;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpSet() {
      return impl_Op_V_S_eq_V_Int_OpSet;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpSet() {
      return impl_Op_V_S_eq_V_Double_OpSet;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpSet() {
      return impl_Op_V_S_eq_V_Float_OpSet;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpSet() {
      return impl_Op_V_S_eq_V_Long_OpSet;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpMod() {
      return impl_Op_V_S_eq_V_Int_OpMod;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpMod() {
      return impl_Op_V_S_eq_V_Double_OpMod;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpMod() {
      return impl_Op_V_S_eq_V_Float_OpMod;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpMod() {
      return impl_Op_V_S_eq_V_Long_OpMod;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Int_OpPow() {
      return impl_Op_V_S_eq_V_Int_OpPow;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Double_OpPow() {
      return impl_Op_V_S_eq_V_Double_OpPow;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Float_OpPow() {
      return impl_Op_V_S_eq_V_Float_OpPow;
   }

   public BinaryRegistry impl_Op_V_S_eq_V_Long_OpPow() {
      return impl_Op_V_S_eq_V_Long_OpPow;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpAdd() {
      return impl_Op_S_V_eq_V_Int_OpAdd;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpAdd() {
      return impl_Op_S_V_eq_V_Double_OpAdd;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpAdd() {
      return impl_Op_S_V_eq_V_Float_OpAdd;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpAdd() {
      return impl_Op_S_V_eq_V_Long_OpAdd;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpSub() {
      return impl_Op_S_V_eq_V_Int_OpSub;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpSub() {
      return impl_Op_S_V_eq_V_Double_OpSub;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpSub() {
      return impl_Op_S_V_eq_V_Float_OpSub;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpSub() {
      return impl_Op_S_V_eq_V_Long_OpSub;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpMulScalar() {
      return impl_Op_S_V_eq_V_Int_OpMulScalar;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpMulScalar() {
      return impl_Op_S_V_eq_V_Double_OpMulScalar;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpMulScalar() {
      return impl_Op_S_V_eq_V_Float_OpMulScalar;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpMulScalar() {
      return impl_Op_S_V_eq_V_Long_OpMulScalar;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpMulMatrix() {
      return impl_Op_S_V_eq_V_Int_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpMulMatrix() {
      return impl_Op_S_V_eq_V_Double_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpMulMatrix() {
      return impl_Op_S_V_eq_V_Float_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpMulMatrix() {
      return impl_Op_S_V_eq_V_Long_OpMulMatrix;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpDiv() {
      return impl_Op_S_V_eq_V_Int_OpDiv;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpDiv() {
      return impl_Op_S_V_eq_V_Double_OpDiv;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpDiv() {
      return impl_Op_S_V_eq_V_Float_OpDiv;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpDiv() {
      return impl_Op_S_V_eq_V_Long_OpDiv;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpSet() {
      return impl_Op_S_V_eq_V_Int_OpSet;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpSet() {
      return impl_Op_S_V_eq_V_Double_OpSet;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpSet() {
      return impl_Op_S_V_eq_V_Float_OpSet;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpSet() {
      return impl_Op_S_V_eq_V_Long_OpSet;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpMod() {
      return impl_Op_S_V_eq_V_Int_OpMod;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpMod() {
      return impl_Op_S_V_eq_V_Double_OpMod;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpMod() {
      return impl_Op_S_V_eq_V_Float_OpMod;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpMod() {
      return impl_Op_S_V_eq_V_Long_OpMod;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Int_OpPow() {
      return impl_Op_S_V_eq_V_Int_OpPow;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Double_OpPow() {
      return impl_Op_S_V_eq_V_Double_OpPow;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Float_OpPow() {
      return impl_Op_S_V_eq_V_Float_OpPow;
   }

   public BinaryRegistry impl_Op_S_V_eq_V_Long_OpPow() {
      return impl_Op_S_V_eq_V_Long_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpMulScalar() {
      return impl_Op_InPlace_V_V_Int_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpMulScalar() {
      return impl_Op_InPlace_V_V_Double_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpMulScalar() {
      return impl_Op_InPlace_V_V_Float_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpMulScalar() {
      return impl_Op_InPlace_V_V_Long_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpDiv() {
      return impl_Op_InPlace_V_V_Int_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpDiv() {
      return impl_Op_InPlace_V_V_Double_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpDiv() {
      return impl_Op_InPlace_V_V_Float_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpDiv() {
      return impl_Op_InPlace_V_V_Long_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpSet() {
      return impl_Op_InPlace_V_V_Int_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpSet() {
      return impl_Op_InPlace_V_V_Double_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpSet() {
      return impl_Op_InPlace_V_V_Float_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpSet() {
      return impl_Op_InPlace_V_V_Long_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpMod() {
      return impl_Op_InPlace_V_V_Int_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpMod() {
      return impl_Op_InPlace_V_V_Double_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpMod() {
      return impl_Op_InPlace_V_V_Float_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpMod() {
      return impl_Op_InPlace_V_V_Long_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Int_OpPow() {
      return impl_Op_InPlace_V_V_Int_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Double_OpPow() {
      return impl_Op_InPlace_V_V_Double_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Float_OpPow() {
      return impl_Op_InPlace_V_V_Float_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Long_OpPow() {
      return impl_Op_InPlace_V_V_Long_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Int_OpAdd() {
      return impl_Op_InPlace_V_V_Idempotent_Int_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Double_OpAdd() {
      return impl_Op_InPlace_V_V_Idempotent_Double_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Float_OpAdd() {
      return impl_Op_InPlace_V_V_Idempotent_Float_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Long_OpAdd() {
      return impl_Op_InPlace_V_V_Idempotent_Long_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Int_OpSub() {
      return impl_Op_InPlace_V_V_Idempotent_Int_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Double_OpSub() {
      return impl_Op_InPlace_V_V_Idempotent_Double_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Float_OpSub() {
      return impl_Op_InPlace_V_V_Idempotent_Float_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_V_Idempotent_Long_OpSub() {
      return impl_Op_InPlace_V_V_Idempotent_Long_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpAdd() {
      return impl_Op_InPlace_V_S_Int_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpAdd() {
      return impl_Op_InPlace_V_S_Double_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpAdd() {
      return impl_Op_InPlace_V_S_Float_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpAdd() {
      return impl_Op_InPlace_V_S_Long_OpAdd;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpSub() {
      return impl_Op_InPlace_V_S_Int_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpSub() {
      return impl_Op_InPlace_V_S_Double_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpSub() {
      return impl_Op_InPlace_V_S_Float_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpSub() {
      return impl_Op_InPlace_V_S_Long_OpSub;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMulScalar() {
      return impl_Op_InPlace_V_S_Int_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMulScalar() {
      return impl_Op_InPlace_V_S_Double_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMulScalar() {
      return impl_Op_InPlace_V_S_Float_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMulScalar() {
      return impl_Op_InPlace_V_S_Long_OpMulScalar;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMulMatrix() {
      return impl_Op_InPlace_V_S_Int_OpMulMatrix;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMulMatrix() {
      return impl_Op_InPlace_V_S_Double_OpMulMatrix;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMulMatrix() {
      return impl_Op_InPlace_V_S_Float_OpMulMatrix;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMulMatrix() {
      return impl_Op_InPlace_V_S_Long_OpMulMatrix;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpDiv() {
      return impl_Op_InPlace_V_S_Int_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpDiv() {
      return impl_Op_InPlace_V_S_Double_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpDiv() {
      return impl_Op_InPlace_V_S_Float_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpDiv() {
      return impl_Op_InPlace_V_S_Long_OpDiv;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpSet() {
      return impl_Op_InPlace_V_S_Int_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpSet() {
      return impl_Op_InPlace_V_S_Double_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpSet() {
      return impl_Op_InPlace_V_S_Float_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpSet() {
      return impl_Op_InPlace_V_S_Long_OpSet;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpMod() {
      return impl_Op_InPlace_V_S_Int_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpMod() {
      return impl_Op_InPlace_V_S_Double_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpMod() {
      return impl_Op_InPlace_V_S_Float_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpMod() {
      return impl_Op_InPlace_V_S_Long_OpMod;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Int_OpPow() {
      return impl_Op_InPlace_V_S_Int_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Double_OpPow() {
      return impl_Op_InPlace_V_S_Double_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Float_OpPow() {
      return impl_Op_InPlace_V_S_Float_OpPow;
   }

   public BinaryUpdateRegistry impl_Op_InPlace_V_S_Long_OpPow() {
      return impl_Op_InPlace_V_S_Long_OpPow;
   }

   public BinaryRegistry impl_OpMulInner_V_V_eq_S_Int() {
      return impl_OpMulInner_V_V_eq_S_Int;
   }

   public BinaryRegistry impl_OpMulInner_V_V_eq_S_Long() {
      return impl_OpMulInner_V_V_eq_S_Long;
   }

   public BinaryRegistry impl_OpMulInner_V_V_eq_S_Float() {
      return impl_OpMulInner_V_V_eq_S_Float;
   }

   public BinaryRegistry impl_OpMulInner_V_V_eq_S_Double() {
      return impl_OpMulInner_V_V_eq_S_Double;
   }

   public TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Int() {
      return impl_scaleAdd_InPlace_V_S_V_Int;
   }

   public TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Double() {
      return impl_scaleAdd_InPlace_V_S_V_Double;
   }

   public TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Float() {
      return impl_scaleAdd_InPlace_V_S_V_Float;
   }

   public TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_V_Long() {
      return impl_scaleAdd_InPlace_V_S_V_Long;
   }

   public BinaryRegistry zipValuesImpl_V_V_Int() {
      return zipValuesImpl_V_V_Int;
   }

   public BinaryRegistry zipValuesImpl_V_V_Double() {
      return zipValuesImpl_V_V_Double;
   }

   public BinaryRegistry zipValuesImpl_V_V_Float() {
      return zipValuesImpl_V_V_Float;
   }

   public BinaryRegistry zipValuesImpl_V_V_Long() {
      return zipValuesImpl_V_V_Long;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Int_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Double_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Float_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_idempotent_Long_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_idempotent_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_nilpotent_Int_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_nilpotent_Int = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_nilpotent_Double_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_nilpotent_Double = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_nilpotent_Float_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_nilpotent_Float = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_nilpotent_Long_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_nilpotent_Long = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Int_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Double_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Float_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Long_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Int_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Double_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Float_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Long_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Int_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Double_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Float_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_V_eq_V_Long_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_V_eq_V_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Int_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Double_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Float_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_V_S_eq_V_Long_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_V_S_eq_V_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpAdd_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpSub_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpMulScalar_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpMulMatrix_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpDiv_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpSet_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpMod_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Int_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Double_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Float_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_S_V_eq_V_Long_OpPow_$eq(final BinaryRegistry x$1) {
      impl_Op_S_V_eq_V_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Int_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Double_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Float_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Long_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Int_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Double_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Float_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Long_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Int_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Double_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Float_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Long_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Int_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Double_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Float_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Long_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Int_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Double_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Float_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Long_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Int_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Double_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Float_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Long_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Int_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Double_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Float_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_V_Idempotent_Long_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_V_Idempotent_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpAdd_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpAdd = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpSub_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpSub = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpMulScalar_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpMulScalar = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpMulMatrix_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpMulMatrix = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpDiv_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpDiv = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpSet_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpSet = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpMod_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpMod = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Int_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Int_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Double_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Double_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Float_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Float_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_Op_InPlace_V_S_Long_OpPow_$eq(final BinaryUpdateRegistry x$1) {
      impl_Op_InPlace_V_S_Long_OpPow = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_OpMulInner_V_V_eq_S_Int_$eq(final BinaryRegistry x$1) {
      impl_OpMulInner_V_V_eq_S_Int = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_OpMulInner_V_V_eq_S_Long_$eq(final BinaryRegistry x$1) {
      impl_OpMulInner_V_V_eq_S_Long = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_OpMulInner_V_V_eq_S_Float_$eq(final BinaryRegistry x$1) {
      impl_OpMulInner_V_V_eq_S_Float = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_OpMulInner_V_V_eq_S_Double_$eq(final BinaryRegistry x$1) {
      impl_OpMulInner_V_V_eq_S_Double = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_scaleAdd_InPlace_V_S_V_Int_$eq(final TernaryUpdateRegistry x$1) {
      impl_scaleAdd_InPlace_V_S_V_Int = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_scaleAdd_InPlace_V_S_V_Double_$eq(final TernaryUpdateRegistry x$1) {
      impl_scaleAdd_InPlace_V_S_V_Double = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_scaleAdd_InPlace_V_S_V_Float_$eq(final TernaryUpdateRegistry x$1) {
      impl_scaleAdd_InPlace_V_S_V_Float = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$impl_scaleAdd_InPlace_V_S_V_Long_$eq(final TernaryUpdateRegistry x$1) {
      impl_scaleAdd_InPlace_V_S_V_Long = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$zipValuesImpl_V_V_Int_$eq(final BinaryRegistry x$1) {
      zipValuesImpl_V_V_Int = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$zipValuesImpl_V_V_Double_$eq(final BinaryRegistry x$1) {
      zipValuesImpl_V_V_Double = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$zipValuesImpl_V_V_Float_$eq(final BinaryRegistry x$1) {
      zipValuesImpl_V_V_Float = x$1;
   }

   public void breeze$linalg$operators$VectorExpandOps$_setter_$zipValuesImpl_V_V_Long_$eq(final BinaryRegistry x$1) {
      zipValuesImpl_V_V_Long = x$1;
   }

   public Vector_GenericOps.ZippedVectorValues$ ZippedVectorValues() {
      if (ZippedVectorValues$module == null) {
         this.ZippedVectorValues$lzycompute$1();
      }

      return ZippedVectorValues$module;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HasOps$.class);
   }

   private final void impl_any_BV_eq_Boolean$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_any_BV_eq_Boolean$module == null) {
            impl_any_BV_eq_Boolean$module = new BitVectorOps.impl_any_BV_eq_Boolean$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_all_BV_eq_Boolean$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_all_BV_eq_Boolean$module == null) {
            impl_all_BV_eq_Boolean$module = new BitVectorOps.impl_all_BV_eq_Boolean$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void FrobeniusCSCProduct$lzycompute$1() {
      synchronized(this){}

      try {
         if (FrobeniusCSCProduct$module == null) {
            FrobeniusCSCProduct$module = new CSCMatrixOps_Ring.FrobeniusCSCProduct$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpMulMatrix_DMF_DVF_eq_DVF$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpMulMatrix_DMF_DVF_eq_DVF$module == null) {
            impl_OpMulMatrix_DMF_DVF_eq_DVF$module = new DenseMatrixOps_FloatSpecialized.impl_OpMulMatrix_DMF_DVF_eq_DVF$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$module == null) {
            impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$module = new DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$module == null) {
            impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$module = new DenseMatrixOps_FloatSpecialized.impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpMulMatrix_DMD_DMD_eq_DMD$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpMulMatrix_DMD_DMD_eq_DMD$module == null) {
            impl_OpMulMatrix_DMD_DMD_eq_DMD$module = new DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DMD_eq_DMD$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpMulMatrix_DMD_DVD_eq_DVD$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpMulMatrix_DMD_DVD_eq_DVD$module == null) {
            impl_OpMulMatrix_DMD_DVD_eq_DVD$module = new DenseMatrixMultiplyOps.impl_OpMulMatrix_DMD_DVD_eq_DVD$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$module == null) {
            impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$module = new DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$module == null) {
            impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$module = new DenseMatrixMultiplyOps.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_scaleAdd_InPlace_DV_T_DV_Double$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_scaleAdd_InPlace_DV_T_DV_Double$module == null) {
            impl_scaleAdd_InPlace_DV_T_DV_Double$module = new DenseVector_DoubleOps.impl_scaleAdd_InPlace_DV_T_DV_Double$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void canDotD$lzycompute$1() {
      synchronized(this){}

      try {
         if (canDotD$module == null) {
            canDotD$module = new DenseVector_DoubleOps.canDotD$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void impl_scaledAdd_InPlace_DV_S_DV_Float$lzycompute$1() {
      synchronized(this){}

      try {
         if (impl_scaledAdd_InPlace_DV_S_DV_Float$module == null) {
            impl_scaledAdd_InPlace_DV_S_DV_Float$module = new DenseVector_FloatOps.impl_scaledAdd_InPlace_DV_S_DV_Float$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void ZippedVectorValues$lzycompute$1() {
      synchronized(this){}

      try {
         if (ZippedVectorValues$module == null) {
            ZippedVectorValues$module = new Vector_GenericOps.ZippedVectorValues$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private HasOps$() {
   }
}
