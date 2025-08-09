package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import scala.;
import scala.Function1;

public final class MutableFiniteCoordinateField$ {
   public static final MutableFiniteCoordinateField$ MODULE$ = new MutableFiniteCoordinateField$();

   public MutableFiniteCoordinateField make(final UFunc.UImpl2 _norm2, final UFunc.UImpl _norm, final Field _field, final UFunc.UImpl2 _addVS, final UFunc.UImpl2 _subVS, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _addIntoVS, final UFunc.InPlaceImpl2 _subIntoVS, final UFunc.InPlaceImpl2 _mulIntoVV, final UFunc.InPlaceImpl2 _divIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl2 _setIntoVS, final UFunc.InPlaceImpl3 _scaleAddVSV, final CanCreateZerosLike _zeroLike, final CanCreateZeros _zero, final UFunc.UImpl _dim, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl _neg, final .less.colon.less _ops, final UFunc.UImpl2 _dotVV, final CanZipMapValues _zipMapVals, final CanZipMapKeyValues _zipMapKeyVals, final CanTraverseValues _traverseVals, final CanMapValues _mapVals, final ScalarOf _scalarOf) {
      return new MutableFiniteCoordinateField(_field, _ops, _norm, _norm2, _addVS, _zeroLike, _subVS, _mulVV, _divVV, _copy, _mulIntoVS, _divIntoVS, _addIntoVV, _subIntoVV, _addIntoVS, _subIntoVS, _mulIntoVV, _divIntoVV, _mulVS, _divVS, _addVV, _subVV, _neg, _dotVV, _setIntoVV, _setIntoVS, _scaleAddVSV, _scalarOf, _mapVals, _zipMapVals, _zipMapKeyVals, _traverseVals, _zero, _dim) {
         private final Function1 hasOps;
         private final Field _field$6;
         private final UFunc.UImpl _norm$5;
         private final UFunc.UImpl2 _norm2$2;
         private final UFunc.UImpl2 _addVS$1;
         private final CanCreateZerosLike _zeroLike$8;
         private final UFunc.UImpl2 _subVS$1;
         private final UFunc.UImpl2 _mulVV$5;
         private final UFunc.UImpl2 _divVV$5;
         private final CanCopy _copy$7;
         private final UFunc.InPlaceImpl2 _mulIntoVS$7;
         private final UFunc.InPlaceImpl2 _divIntoVS$5;
         private final UFunc.InPlaceImpl2 _addIntoVV$7;
         private final UFunc.InPlaceImpl2 _subIntoVV$7;
         private final UFunc.InPlaceImpl2 _addIntoVS$1;
         private final UFunc.InPlaceImpl2 _subIntoVS$1;
         private final UFunc.InPlaceImpl2 _mulIntoVV$4;
         private final UFunc.InPlaceImpl2 _divIntoVV$4;
         private final UFunc.UImpl2 _mulVS$8;
         private final UFunc.UImpl2 _divVS$6;
         private final UFunc.UImpl2 _addVV$8;
         private final UFunc.UImpl2 _subVV$8;
         private final UFunc.UImpl _neg$5;
         private final UFunc.UImpl2 _dotVV$7;
         private final UFunc.InPlaceImpl2 _setIntoVV$7;
         private final UFunc.InPlaceImpl2 _setIntoVS$1;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$7;
         private final ScalarOf _scalarOf$2;
         private final CanMapValues _mapVals$2;
         private final CanZipMapValues _zipMapVals$2;
         private final CanZipMapKeyValues _zipMapKeyVals$1;
         private final CanTraverseValues _traverseVals$2;
         private final CanCreateZeros _zero$1;
         private final UFunc.UImpl _dim$1;

         public UFunc.InPlaceImpl2 mulIntoVS_M() {
            return MutableModule.mulIntoVS_M$(this);
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

         public Field scalars() {
            return this._field$6;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl normImpl() {
            return this._norm$5;
         }

         public UFunc.UImpl2 normImpl2() {
            return this._norm2$2;
         }

         public UFunc.UImpl2 addVS() {
            return this._addVS$1;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$8;
         }

         public UFunc.UImpl2 subVS() {
            return this._subVS$1;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$5;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$5;
         }

         public CanCopy copy() {
            return this._copy$7;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$7;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$5;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$7;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$7;
         }

         public UFunc.InPlaceImpl2 addIntoVS() {
            return this._addIntoVS$1;
         }

         public UFunc.InPlaceImpl2 subIntoVS() {
            return this._subIntoVS$1;
         }

         public UFunc.InPlaceImpl2 mulIntoVV() {
            return this._mulIntoVV$4;
         }

         public UFunc.InPlaceImpl2 divIntoVV() {
            return this._divIntoVV$4;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$8;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$6;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$8;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$8;
         }

         public UFunc.UImpl neg() {
            return this._neg$5;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$7;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$7;
         }

         public UFunc.InPlaceImpl2 setIntoVS() {
            return this._setIntoVS$1;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$7;
         }

         public ScalarOf scalarOf() {
            return this._scalarOf$2;
         }

         public CanMapValues mapValues() {
            return this._mapVals$2;
         }

         public CanZipMapValues zipMapValues() {
            return this._zipMapVals$2;
         }

         public CanZipMapKeyValues zipMapKeyValues() {
            return this._zipMapKeyVals$1;
         }

         public CanTraverseValues iterateValues() {
            return this._traverseVals$2;
         }

         public CanCreateZeros zero() {
            return this._zero$1;
         }

         public UFunc.UImpl canDim() {
            return this._dim$1;
         }

         public {
            this._field$6 = _field$6;
            this._norm$5 = _norm$5;
            this._norm2$2 = _norm2$2;
            this._addVS$1 = _addVS$1;
            this._zeroLike$8 = _zeroLike$8;
            this._subVS$1 = _subVS$1;
            this._mulVV$5 = _mulVV$5;
            this._divVV$5 = _divVV$5;
            this._copy$7 = _copy$7;
            this._mulIntoVS$7 = _mulIntoVS$7;
            this._divIntoVS$5 = _divIntoVS$5;
            this._addIntoVV$7 = _addIntoVV$7;
            this._subIntoVV$7 = _subIntoVV$7;
            this._addIntoVS$1 = _addIntoVS$1;
            this._subIntoVS$1 = _subIntoVS$1;
            this._mulIntoVV$4 = _mulIntoVV$4;
            this._divIntoVV$4 = _divIntoVV$4;
            this._mulVS$8 = _mulVS$8;
            this._divVS$6 = _divVS$6;
            this._addVV$8 = _addVV$8;
            this._subVV$8 = _subVV$8;
            this._neg$5 = _neg$5;
            this._dotVV$7 = _dotVV$7;
            this._setIntoVV$7 = _setIntoVV$7;
            this._setIntoVS$1 = _setIntoVS$1;
            this._scaleAddVSV$7 = _scaleAddVSV$7;
            this._scalarOf$2 = _scalarOf$2;
            this._mapVals$2 = _mapVals$2;
            this._zipMapVals$2 = _zipMapVals$2;
            this._zipMapKeyVals$1 = _zipMapKeyVals$1;
            this._traverseVals$2 = _traverseVals$2;
            this._zero$1 = _zero$1;
            this._dim$1 = _dim$1;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$8);
         }
      };
   }

   private MutableFiniteCoordinateField$() {
   }
}
