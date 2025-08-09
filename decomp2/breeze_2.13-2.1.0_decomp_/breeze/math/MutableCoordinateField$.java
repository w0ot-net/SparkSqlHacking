package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import scala.;
import scala.Function1;

public final class MutableCoordinateField$ {
   public static final MutableCoordinateField$ MODULE$ = new MutableCoordinateField$();

   public MutableCoordinateField make(final .less.colon.less _ops, final UFunc.UImpl2 _normImpl2, final UFunc.UImpl _norm, final Field _field, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _mulIntoVV, final UFunc.InPlaceImpl2 _divIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl _neg, final UFunc.UImpl2 _dotVV, final CanZipMapValues _zipMapVals, final CanTraverseValues _traverseVals, final CanMapValues _mapVals, final ScalarOf _scalarOf) {
      return new MutableCoordinateField(_field, _ops, _norm, _normImpl2, _zeroLike, _mulVV, _divVV, _copy, _mulIntoVS, _divIntoVS, _addIntoVV, _subIntoVV, _mulIntoVV, _divIntoVV, _mulVS, _divVS, _addVV, _subVV, _neg, _dotVV, _setIntoVV, _scaleAddVSV, _mapVals, _scalarOf, _zipMapVals, _traverseVals) {
         private final Function1 hasOps;
         private final Field _field$5;
         private final UFunc.UImpl _norm$4;
         private final UFunc.UImpl2 _normImpl2$1;
         private final CanCreateZerosLike _zeroLike$7;
         private final UFunc.UImpl2 _mulVV$4;
         private final UFunc.UImpl2 _divVV$4;
         private final CanCopy _copy$6;
         private final UFunc.InPlaceImpl2 _mulIntoVS$6;
         private final UFunc.InPlaceImpl2 _divIntoVS$4;
         private final UFunc.InPlaceImpl2 _addIntoVV$6;
         private final UFunc.InPlaceImpl2 _subIntoVV$6;
         private final UFunc.InPlaceImpl2 _mulIntoVV$3;
         private final UFunc.InPlaceImpl2 _divIntoVV$3;
         private final UFunc.UImpl2 _mulVS$7;
         private final UFunc.UImpl2 _divVS$5;
         private final UFunc.UImpl2 _addVV$7;
         private final UFunc.UImpl2 _subVV$7;
         private final UFunc.UImpl _neg$4;
         private final UFunc.UImpl2 _dotVV$6;
         private final UFunc.InPlaceImpl2 _setIntoVV$6;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$6;
         private final CanMapValues _mapVals$1;
         private final ScalarOf _scalarOf$1;
         private final CanZipMapValues _zipMapVals$1;
         private final CanTraverseValues _traverseVals$1;

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
            return this._field$5;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl normImpl() {
            return this._norm$4;
         }

         public UFunc.UImpl2 normImpl2() {
            return this._normImpl2$1;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$7;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$4;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$4;
         }

         public CanCopy copy() {
            return this._copy$6;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$6;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$4;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$6;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$6;
         }

         public UFunc.InPlaceImpl2 mulIntoVV() {
            return this._mulIntoVV$3;
         }

         public UFunc.InPlaceImpl2 divIntoVV() {
            return this._divIntoVV$3;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$7;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$5;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$7;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$7;
         }

         public UFunc.UImpl neg() {
            return this._neg$4;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$6;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$6;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$6;
         }

         public CanMapValues mapValues() {
            return this._mapVals$1;
         }

         public ScalarOf scalarOf() {
            return this._scalarOf$1;
         }

         public CanZipMapValues zipMapValues() {
            return this._zipMapVals$1;
         }

         public CanTraverseValues iterateValues() {
            return this._traverseVals$1;
         }

         public {
            this._field$5 = _field$5;
            this._norm$4 = _norm$4;
            this._normImpl2$1 = _normImpl2$1;
            this._zeroLike$7 = _zeroLike$7;
            this._mulVV$4 = _mulVV$4;
            this._divVV$4 = _divVV$4;
            this._copy$6 = _copy$6;
            this._mulIntoVS$6 = _mulIntoVS$6;
            this._divIntoVS$4 = _divIntoVS$4;
            this._addIntoVV$6 = _addIntoVV$6;
            this._subIntoVV$6 = _subIntoVV$6;
            this._mulIntoVV$3 = _mulIntoVV$3;
            this._divIntoVV$3 = _divIntoVV$3;
            this._mulVS$7 = _mulVS$7;
            this._divVS$5 = _divVS$5;
            this._addVV$7 = _addVV$7;
            this._subVV$7 = _subVV$7;
            this._neg$4 = _neg$4;
            this._dotVV$6 = _dotVV$6;
            this._setIntoVV$6 = _setIntoVV$6;
            this._scaleAddVSV$6 = _scaleAddVSV$6;
            this._mapVals$1 = _mapVals$1;
            this._scalarOf$1 = _scalarOf$1;
            this._zipMapVals$1 = _zipMapVals$1;
            this._traverseVals$1 = _traverseVals$1;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$7);
         }
      };
   }

   private MutableCoordinateField$() {
   }
}
