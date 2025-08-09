package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import scala.;
import scala.Function1;

public final class MutableEnumeratedCoordinateField$ {
   public static final MutableEnumeratedCoordinateField$ MODULE$ = new MutableEnumeratedCoordinateField$();

   public MutableEnumeratedCoordinateField make(final UFunc.UImpl2 _norm2, final UFunc.UImpl _norm, final Field _field, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _mulIntoVV, final UFunc.InPlaceImpl2 _divIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl _neg, final .less.colon.less _ops, final UFunc.UImpl2 _dotVV, final CanZipMapValues _zipMapVals, final CanZipMapKeyValues _zipMapKeyVals, final CanTraverseValues _traverseVals, final CanMapValues _mapVals, final ScalarOf _scalarOf) {
      return new MutableEnumeratedCoordinateField(_field, _ops, _norm, _norm2, _zeroLike, _mulVV, _divVV, _copy, _mulIntoVS, _divIntoVS, _addIntoVV, _subIntoVV, _mulIntoVV, _divIntoVV, _mulVS, _divVS, _addVV, _subVV, _neg, _dotVV, _setIntoVV, _scaleAddVSV, _mapVals, _scalarOf, _zipMapVals, _zipMapKeyVals, _traverseVals) {
         private final Function1 hasOps;
         private final Field _field$7;
         private final UFunc.UImpl _norm$6;
         private final UFunc.UImpl2 _norm2$3;
         private final CanCreateZerosLike _zeroLike$9;
         private final UFunc.UImpl2 _mulVV$6;
         private final UFunc.UImpl2 _divVV$6;
         private final CanCopy _copy$8;
         private final UFunc.InPlaceImpl2 _mulIntoVS$8;
         private final UFunc.InPlaceImpl2 _divIntoVS$6;
         private final UFunc.InPlaceImpl2 _addIntoVV$8;
         private final UFunc.InPlaceImpl2 _subIntoVV$8;
         private final UFunc.InPlaceImpl2 _mulIntoVV$5;
         private final UFunc.InPlaceImpl2 _divIntoVV$5;
         private final UFunc.UImpl2 _mulVS$9;
         private final UFunc.UImpl2 _divVS$7;
         private final UFunc.UImpl2 _addVV$9;
         private final UFunc.UImpl2 _subVV$9;
         private final UFunc.UImpl _neg$6;
         private final UFunc.UImpl2 _dotVV$8;
         private final UFunc.InPlaceImpl2 _setIntoVV$8;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$8;
         private final CanMapValues _mapVals$3;
         private final ScalarOf _scalarOf$3;
         private final CanZipMapValues _zipMapVals$3;
         private final CanZipMapKeyValues _zipMapKeyVals$2;
         private final CanTraverseValues _traverseVals$3;

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
            return this._field$7;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl normImpl() {
            return this._norm$6;
         }

         public UFunc.UImpl2 normImpl2() {
            return this._norm2$3;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$9;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$6;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$6;
         }

         public CanCopy copy() {
            return this._copy$8;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$8;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$6;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$8;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$8;
         }

         public UFunc.InPlaceImpl2 mulIntoVV() {
            return this._mulIntoVV$5;
         }

         public UFunc.InPlaceImpl2 divIntoVV() {
            return this._divIntoVV$5;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$9;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$7;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$9;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$9;
         }

         public UFunc.UImpl neg() {
            return this._neg$6;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$8;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$8;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$8;
         }

         public CanMapValues mapValues() {
            return this._mapVals$3;
         }

         public ScalarOf scalarOf() {
            return this._scalarOf$3;
         }

         public CanZipMapValues zipMapValues() {
            return this._zipMapVals$3;
         }

         public CanZipMapKeyValues zipMapKeyValues() {
            return this._zipMapKeyVals$2;
         }

         public CanTraverseValues iterateValues() {
            return this._traverseVals$3;
         }

         public {
            this._field$7 = _field$7;
            this._norm$6 = _norm$6;
            this._norm2$3 = _norm2$3;
            this._zeroLike$9 = _zeroLike$9;
            this._mulVV$6 = _mulVV$6;
            this._divVV$6 = _divVV$6;
            this._copy$8 = _copy$8;
            this._mulIntoVS$8 = _mulIntoVS$8;
            this._divIntoVS$6 = _divIntoVS$6;
            this._addIntoVV$8 = _addIntoVV$8;
            this._subIntoVV$8 = _subIntoVV$8;
            this._mulIntoVV$5 = _mulIntoVV$5;
            this._divIntoVV$5 = _divIntoVV$5;
            this._mulVS$9 = _mulVS$9;
            this._divVS$7 = _divVS$7;
            this._addVV$9 = _addVV$9;
            this._subVV$9 = _subVV$9;
            this._neg$6 = _neg$6;
            this._dotVV$8 = _dotVV$8;
            this._setIntoVV$8 = _setIntoVV$8;
            this._scaleAddVSV$8 = _scaleAddVSV$8;
            this._mapVals$3 = _mapVals$3;
            this._scalarOf$3 = _scalarOf$3;
            this._zipMapVals$3 = _zipMapVals$3;
            this._zipMapKeyVals$2 = _zipMapKeyVals$2;
            this._traverseVals$3 = _traverseVals$3;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$9);
         }
      };
   }

   private MutableEnumeratedCoordinateField$() {
   }
}
