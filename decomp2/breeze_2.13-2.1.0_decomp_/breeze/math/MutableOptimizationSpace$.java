package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import scala.;
import scala.Function1;
import scala.runtime.BoxesRunTime;

public final class MutableOptimizationSpace$ {
   public static final MutableOptimizationSpace$ MODULE$ = new MutableOptimizationSpace$();

   public MutableOptimizationSpace make(final Function1 toMat, final Function1 toVec, final UFunc.UImpl2 _norm2, final UFunc.UImpl _norm, final Field _field, final UFunc.UImpl2 _mulMSMat, final UFunc.UImpl2 _addVS, final UFunc.UImpl2 _subVS, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _addIntoVS, final UFunc.InPlaceImpl2 _subIntoVS, final UFunc.InPlaceImpl2 _mulIntoVV, final UFunc.InPlaceImpl2 _divIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl2 _setIntoVS, final UFunc.InPlaceImpl3 _scaleAddVSV, final CanCreateZerosLike _zeroLike, final CanCreateZeros _zero, final UFunc.UImpl _dim, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl _neg, final .less.colon.less _ops, final UFunc.UImpl2 _dotVV, final CanZipMapValues _zipMapVals, final CanTraverseValues _traverseVals, final CanMapValues _mapVals, final ScalarOf _scalarOf, final UFunc.UImpl2 _norm2M, final UFunc.UImpl _normM, final UFunc.UImpl2 _addMS, final UFunc.UImpl2 _subMS, final UFunc.UImpl2 _mulMM, final UFunc.UImpl2 _divMM, final CanCopy _copyM, final UFunc.InPlaceImpl2 _mulIntoMS, final UFunc.InPlaceImpl2 _divIntoMS, final UFunc.InPlaceImpl2 _addIntoMM, final UFunc.InPlaceImpl2 _subIntoMM, final UFunc.InPlaceImpl2 _addIntoMS, final UFunc.InPlaceImpl2 _subIntoMS, final UFunc.InPlaceImpl2 _mulIntoMM, final UFunc.InPlaceImpl2 _divIntoMM, final UFunc.InPlaceImpl2 _setIntoMM, final UFunc.InPlaceImpl2 _setIntoMS, final UFunc.InPlaceImpl3 _scaleAddMSM, final CanCreateZerosLike _zeroLikeM, final CanCreateZeros _zeroM, final UFunc.UImpl _dimM, final UFunc.UImpl2 _mulMS, final UFunc.UImpl2 _divMS, final UFunc.UImpl2 _addMM, final UFunc.UImpl2 _subMM, final UFunc.UImpl _negM, final .less.colon.less _opsM, final UFunc.UImpl2 _dotMM, final CanZipMapValues _zipMapValsM, final CanZipMapKeyValues _zipMapKeyVals, final CanTraverseValues _traverseValsM, final CanMapValues _mapValsM, final ScalarOf _scalarOfM, final UFunc.UImpl2 _mulMMM, final UFunc.UImpl2 _mulMVV, final UFunc.UImpl2 _mulVTM, final CanTranspose _canTrans) {
      return new MutableOptimizationSpace(toMat, toVec, _field, _setIntoMM, _mulMSMat, _divMS, _norm2M, _normM, _divMM, _zeroLikeM, _scaleAddMSM, _addIntoMS, _negM, _subIntoMS, _addIntoMM, _dimM, _subIntoMM, _copyM, _mulMS, _dotMM, _subMS, _mulMM, _mulIntoMS, _subMM, _divIntoMM, _mulIntoMM, _opsM, _zeroM, _addMM, _divIntoMS, _addMS, _setIntoMS, _ops, _neg, _mulVV, _dim, _zero, _norm2, _divIntoVV, _mulIntoVV, _setIntoVS, _zipMapVals, _zipMapKeyVals, _traverseVals, _mapVals, _divVV, _subVS, _subVV, _mulVS, _zeroLike, _addVS, _addVV, _divVS, _dotVV, _divIntoVS, _addIntoVV, _addIntoVS, _subIntoVS, _subIntoVV, _mulIntoVS, _copy, _setIntoVV, _scaleAddVSV, _mapValsM, _traverseValsM, _zipMapValsM, _mulMMM, _mulMVV, _mulVTM, _canTrans, _scalarOfM, _scalarOf) {
         private final Function1 hasOps;
         private final Function1 toMat$1;
         private final Function1 toVec$1;
         private final Field _field$8;
         private final UFunc.InPlaceImpl2 _setIntoMM$1;
         private final UFunc.UImpl2 _mulMSMat$1;
         private final UFunc.UImpl2 _divMS$1;
         private final UFunc.UImpl2 _norm2M$1;
         private final UFunc.UImpl _normM$1;
         private final UFunc.UImpl2 _divMM$1;
         private final CanCreateZerosLike _zeroLikeM$1;
         private final UFunc.InPlaceImpl3 _scaleAddMSM$1;
         private final UFunc.InPlaceImpl2 _addIntoMS$1;
         private final UFunc.UImpl _negM$1;
         private final UFunc.InPlaceImpl2 _subIntoMS$1;
         private final UFunc.InPlaceImpl2 _addIntoMM$1;
         private final UFunc.UImpl _dimM$1;
         private final UFunc.InPlaceImpl2 _subIntoMM$1;
         private final CanCopy _copyM$1;
         private final UFunc.UImpl2 _mulMS$1;
         private final UFunc.UImpl2 _dotMM$1;
         private final UFunc.UImpl2 _subMS$1;
         private final UFunc.UImpl2 _mulMM$1;
         private final UFunc.InPlaceImpl2 _mulIntoMS$1;
         private final UFunc.UImpl2 _subMM$1;
         private final UFunc.InPlaceImpl2 _divIntoMM$1;
         private final UFunc.InPlaceImpl2 _mulIntoMM$1;
         private final .less.colon.less _opsM$1;
         private final CanCreateZeros _zeroM$1;
         private final UFunc.UImpl2 _addMM$1;
         private final UFunc.InPlaceImpl2 _divIntoMS$1;
         private final UFunc.UImpl2 _addMS$1;
         private final UFunc.InPlaceImpl2 _setIntoMS$1;
         private final UFunc.UImpl _neg$7;
         private final UFunc.UImpl2 _mulVV$7;
         private final UFunc.UImpl _dim$2;
         private final CanCreateZeros _zero$2;
         private final UFunc.UImpl2 _norm2$4;
         private final UFunc.InPlaceImpl2 _divIntoVV$6;
         private final UFunc.InPlaceImpl2 _mulIntoVV$6;
         private final UFunc.InPlaceImpl2 _setIntoVS$2;
         private final CanZipMapValues _zipMapVals$4;
         private final CanZipMapKeyValues _zipMapKeyVals$3;
         private final CanTraverseValues _traverseVals$4;
         private final CanMapValues _mapVals$4;
         private final UFunc.UImpl2 _divVV$7;
         private final UFunc.UImpl2 _subVS$2;
         private final UFunc.UImpl2 _subVV$10;
         private final UFunc.UImpl2 _mulVS$10;
         private final CanCreateZerosLike _zeroLike$10;
         private final UFunc.UImpl2 _addVS$2;
         private final UFunc.UImpl2 _addVV$10;
         private final UFunc.UImpl2 _divVS$8;
         private final UFunc.UImpl2 _dotVV$9;
         private final UFunc.InPlaceImpl2 _divIntoVS$7;
         private final UFunc.InPlaceImpl2 _addIntoVV$9;
         private final UFunc.InPlaceImpl2 _addIntoVS$2;
         private final UFunc.InPlaceImpl2 _subIntoVS$2;
         private final UFunc.InPlaceImpl2 _subIntoVV$9;
         private final UFunc.InPlaceImpl2 _mulIntoVS$9;
         private final CanCopy _copy$9;
         private final UFunc.InPlaceImpl2 _setIntoVV$9;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$9;
         private final CanMapValues _mapValsM$1;
         private final CanTraverseValues _traverseValsM$1;
         private final CanZipMapValues _zipMapValsM$1;
         private final UFunc.UImpl2 _mulMMM$1;
         private final UFunc.UImpl2 _mulMVV$1;
         private final UFunc.UImpl2 _mulVTM$1;
         private final CanTranspose _canTrans$1;
         private final ScalarOf _scalarOfM$1;
         private final ScalarOf _scalarOf$4;

         public UFunc.InPlaceImpl2 mulIntoVS_M() {
            return MutableModule.mulIntoVS_M$(this);
         }

         public UFunc.UImpl normImpl() {
            return InnerProductModule.normImpl$(this);
         }

         public UFunc.UImpl scalarNorm() {
            return NormedModule.scalarNorm$(this);
         }

         public boolean close(final Object a, final Object b, final double tolerance) {
            return NormedModule.close$(this, a, b, tolerance);
         }

         public UFunc.UImpl2 mulVS_M() {
            return Module.mulVS_M$(this);
         }

         public Object toMatrix(final Object v) {
            return this.toMat$1.apply(v);
         }

         public Object toVector(final Object m) {
            return this.toVec$1.apply(m);
         }

         public boolean closeM(final Object a, final Object b, final double tolerance) {
            return BoxesRunTime.unboxToDouble(this.normM().apply(this.subMM().apply(a, b))) <= tolerance * scala.math.package..MODULE$.max(BoxesRunTime.unboxToDouble(this.normM().apply(a)), BoxesRunTime.unboxToDouble(this.normM().apply(b)));
         }

         public UFunc.UImpl fieldNorm() {
            return this._field$8.normImpl();
         }

         public UFunc.InPlaceImpl2 setIntoMM() {
            return this._setIntoMM$1;
         }

         public UFunc.UImpl2 mulMSMat() {
            return this._mulMSMat$1;
         }

         public UFunc.UImpl2 divMS() {
            return this._divMS$1;
         }

         public UFunc.UImpl2 normMImpl2() {
            return this._norm2M$1;
         }

         public UFunc.UImpl normM() {
            return this._normM$1;
         }

         public UFunc.UImpl2 divMM() {
            return this._divMM$1;
         }

         public CanCreateZerosLike zeroLikeM() {
            return this._zeroLikeM$1;
         }

         public UFunc.InPlaceImpl3 scaleAddMM() {
            return this._scaleAddMSM$1;
         }

         public UFunc.InPlaceImpl2 addIntoMS() {
            return this._addIntoMS$1;
         }

         public UFunc.UImpl negM() {
            return this._negM$1;
         }

         public UFunc.InPlaceImpl2 subIntoMS() {
            return this._subIntoMS$1;
         }

         public UFunc.InPlaceImpl2 addIntoMM() {
            return this._addIntoMM$1;
         }

         public UFunc.UImpl canDimM() {
            return this._dimM$1;
         }

         public UFunc.InPlaceImpl2 subIntoMM() {
            return this._subIntoMM$1;
         }

         public CanCopy copyM() {
            return this._copyM$1;
         }

         public UFunc.UImpl2 mulMS() {
            return this._mulMS$1;
         }

         public UFunc.UImpl2 dotMM() {
            return this._dotMM$1;
         }

         public UFunc.UImpl2 subMS() {
            return this._subMS$1;
         }

         public UFunc.UImpl2 mulMMS() {
            return this._mulMM$1;
         }

         public UFunc.InPlaceImpl2 mulIntoMS() {
            return this._mulIntoMS$1;
         }

         public UFunc.UImpl2 subMM() {
            return this._subMM$1;
         }

         public UFunc.InPlaceImpl2 divIntoMM() {
            return this._divIntoMM$1;
         }

         public UFunc.InPlaceImpl2 mulIntoMM() {
            return this._mulIntoMM$1;
         }

         public Function1 hasMOps() {
            return (Function1)scala.Predef..MODULE$.implicitly(this._opsM$1);
         }

         public CanCreateZeros zeroM() {
            return this._zeroM$1;
         }

         public UFunc.UImpl2 addMM() {
            return this._addMM$1;
         }

         public UFunc.InPlaceImpl2 divIntoMS() {
            return this._divIntoMS$1;
         }

         public UFunc.UImpl2 addMS() {
            return this._addMS$1;
         }

         public UFunc.InPlaceImpl2 setIntoMS() {
            return this._setIntoMS$1;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl neg() {
            return this._neg$7;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$7;
         }

         public UFunc.UImpl canDim() {
            return this._dim$2;
         }

         public CanCreateZeros zero() {
            return this._zero$2;
         }

         public UFunc.UImpl2 normImpl2() {
            return this._norm2$4;
         }

         public UFunc.InPlaceImpl2 divIntoVV() {
            return this._divIntoVV$6;
         }

         public UFunc.InPlaceImpl2 mulIntoVV() {
            return this._mulIntoVV$6;
         }

         public UFunc.InPlaceImpl2 setIntoVS() {
            return this._setIntoVS$2;
         }

         public CanZipMapValues zipMapValues() {
            return this._zipMapVals$4;
         }

         public CanZipMapKeyValues zipMapKeyValues() {
            return this._zipMapKeyVals$3;
         }

         public CanTraverseValues iterateValues() {
            return this._traverseVals$4;
         }

         public CanMapValues mapValues() {
            return this._mapVals$4;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$7;
         }

         public UFunc.UImpl2 subVS() {
            return this._subVS$2;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$10;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$10;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$10;
         }

         public UFunc.UImpl2 addVS() {
            return this._addVS$2;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$10;
         }

         public Field scalars() {
            return this._field$8;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$8;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$9;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$7;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$9;
         }

         public UFunc.InPlaceImpl2 addIntoVS() {
            return this._addIntoVS$2;
         }

         public UFunc.InPlaceImpl2 subIntoVS() {
            return this._subIntoVS$2;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$9;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$9;
         }

         public CanCopy copy() {
            return this._copy$9;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$9;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$9;
         }

         public CanMapValues mapValuesM() {
            return this._mapValsM$1;
         }

         public CanTraverseValues iterateValuesM() {
            return this._traverseValsM$1;
         }

         public CanZipMapValues zipMapValuesM() {
            return this._zipMapValsM$1;
         }

         public UFunc.UImpl2 mulMMM() {
            return this._mulMMM$1;
         }

         public UFunc.UImpl2 mulMVV() {
            return this._mulMVV$1;
         }

         public UFunc.UImpl2 mulVTM() {
            return this._mulVTM$1;
         }

         public CanTranspose canTrans() {
            return this._canTrans$1;
         }

         public ScalarOf scalarOfM() {
            return this._scalarOfM$1;
         }

         public ScalarOf scalarOf() {
            return this._scalarOf$4;
         }

         public {
            this.toMat$1 = toMat$1;
            this.toVec$1 = toVec$1;
            this._field$8 = _field$8;
            this._setIntoMM$1 = _setIntoMM$1;
            this._mulMSMat$1 = _mulMSMat$1;
            this._divMS$1 = _divMS$1;
            this._norm2M$1 = _norm2M$1;
            this._normM$1 = _normM$1;
            this._divMM$1 = _divMM$1;
            this._zeroLikeM$1 = _zeroLikeM$1;
            this._scaleAddMSM$1 = _scaleAddMSM$1;
            this._addIntoMS$1 = _addIntoMS$1;
            this._negM$1 = _negM$1;
            this._subIntoMS$1 = _subIntoMS$1;
            this._addIntoMM$1 = _addIntoMM$1;
            this._dimM$1 = _dimM$1;
            this._subIntoMM$1 = _subIntoMM$1;
            this._copyM$1 = _copyM$1;
            this._mulMS$1 = _mulMS$1;
            this._dotMM$1 = _dotMM$1;
            this._subMS$1 = _subMS$1;
            this._mulMM$1 = _mulMM$1;
            this._mulIntoMS$1 = _mulIntoMS$1;
            this._subMM$1 = _subMM$1;
            this._divIntoMM$1 = _divIntoMM$1;
            this._mulIntoMM$1 = _mulIntoMM$1;
            this._opsM$1 = _opsM$1;
            this._zeroM$1 = _zeroM$1;
            this._addMM$1 = _addMM$1;
            this._divIntoMS$1 = _divIntoMS$1;
            this._addMS$1 = _addMS$1;
            this._setIntoMS$1 = _setIntoMS$1;
            this._neg$7 = _neg$7;
            this._mulVV$7 = _mulVV$7;
            this._dim$2 = _dim$2;
            this._zero$2 = _zero$2;
            this._norm2$4 = _norm2$4;
            this._divIntoVV$6 = _divIntoVV$6;
            this._mulIntoVV$6 = _mulIntoVV$6;
            this._setIntoVS$2 = _setIntoVS$2;
            this._zipMapVals$4 = _zipMapVals$4;
            this._zipMapKeyVals$3 = _zipMapKeyVals$3;
            this._traverseVals$4 = _traverseVals$4;
            this._mapVals$4 = _mapVals$4;
            this._divVV$7 = _divVV$7;
            this._subVS$2 = _subVS$2;
            this._subVV$10 = _subVV$10;
            this._mulVS$10 = _mulVS$10;
            this._zeroLike$10 = _zeroLike$10;
            this._addVS$2 = _addVS$2;
            this._addVV$10 = _addVV$10;
            this._divVS$8 = _divVS$8;
            this._dotVV$9 = _dotVV$9;
            this._divIntoVS$7 = _divIntoVS$7;
            this._addIntoVV$9 = _addIntoVV$9;
            this._addIntoVS$2 = _addIntoVS$2;
            this._subIntoVS$2 = _subIntoVS$2;
            this._subIntoVV$9 = _subIntoVV$9;
            this._mulIntoVS$9 = _mulIntoVS$9;
            this._copy$9 = _copy$9;
            this._setIntoVV$9 = _setIntoVV$9;
            this._scaleAddVSV$9 = _scaleAddVSV$9;
            this._mapValsM$1 = _mapValsM$1;
            this._traverseValsM$1 = _traverseValsM$1;
            this._zipMapValsM$1 = _zipMapValsM$1;
            this._mulMMM$1 = _mulMMM$1;
            this._mulMVV$1 = _mulMVV$1;
            this._mulVTM$1 = _mulVTM$1;
            this._canTrans$1 = _canTrans$1;
            this._scalarOfM$1 = _scalarOfM$1;
            this._scalarOf$4 = _scalarOf$4;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$10);
         }
      };
   }

   private MutableOptimizationSpace$() {
   }
}
