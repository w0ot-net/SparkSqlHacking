package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function1;

public final class MutableVectorField$ {
   public static final MutableVectorField$ MODULE$ = new MutableVectorField$();

   public MutableVectorField make(final UFunc.UImpl _norm, final Field _field, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _mulIntoVV, final UFunc.InPlaceImpl2 _divIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl _neg, final .less.colon.less _ops, final UFunc.UImpl2 _dotVV) {
      return new MutableVectorField(_field, _ops, _norm, _zeroLike, _mulVV, _divVV, _copy, _mulIntoVS, _divIntoVS, _addIntoVV, _subIntoVV, _mulIntoVV, _divIntoVV, _mulVS, _divVS, _addVV, _subVV, _neg, _dotVV, _setIntoVV, _scaleAddVSV) {
         private final Function1 hasOps;
         private final Field _field$3;
         private final UFunc.UImpl _norm$2;
         private final CanCreateZerosLike _zeroLike$5;
         private final UFunc.UImpl2 _mulVV$2;
         private final UFunc.UImpl2 _divVV$2;
         private final CanCopy _copy$4;
         private final UFunc.InPlaceImpl2 _mulIntoVS$4;
         private final UFunc.InPlaceImpl2 _divIntoVS$2;
         private final UFunc.InPlaceImpl2 _addIntoVV$4;
         private final UFunc.InPlaceImpl2 _subIntoVV$4;
         private final UFunc.InPlaceImpl2 _mulIntoVV$1;
         private final UFunc.InPlaceImpl2 _divIntoVV$1;
         private final UFunc.UImpl2 _mulVS$5;
         private final UFunc.UImpl2 _divVS$3;
         private final UFunc.UImpl2 _addVV$5;
         private final UFunc.UImpl2 _subVV$5;
         private final UFunc.UImpl _neg$2;
         private final UFunc.UImpl2 _dotVV$4;
         private final UFunc.InPlaceImpl2 _setIntoVV$4;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$4;

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
            return this._field$3;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl normImpl() {
            return this._norm$2;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$5;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$2;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$2;
         }

         public CanCopy copy() {
            return this._copy$4;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$4;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$2;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$4;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$4;
         }

         public UFunc.InPlaceImpl2 mulIntoVV() {
            return this._mulIntoVV$1;
         }

         public UFunc.InPlaceImpl2 divIntoVV() {
            return this._divIntoVV$1;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$5;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$3;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$5;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$5;
         }

         public UFunc.UImpl neg() {
            return this._neg$2;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$4;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$4;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$4;
         }

         public {
            this._field$3 = _field$3;
            this._norm$2 = _norm$2;
            this._zeroLike$5 = _zeroLike$5;
            this._mulVV$2 = _mulVV$2;
            this._divVV$2 = _divVV$2;
            this._copy$4 = _copy$4;
            this._mulIntoVS$4 = _mulIntoVS$4;
            this._divIntoVS$2 = _divIntoVS$2;
            this._addIntoVV$4 = _addIntoVV$4;
            this._subIntoVV$4 = _subIntoVV$4;
            this._mulIntoVV$1 = _mulIntoVV$1;
            this._divIntoVV$1 = _divIntoVV$1;
            this._mulVS$5 = _mulVS$5;
            this._divVS$3 = _divVS$3;
            this._addVV$5 = _addVV$5;
            this._subVV$5 = _subVV$5;
            this._neg$2 = _neg$2;
            this._dotVV$4 = _dotVV$4;
            this._setIntoVV$4 = _setIntoVV$4;
            this._scaleAddVSV$4 = _scaleAddVSV$4;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$5);
         }
      };
   }

   private MutableVectorField$() {
   }
}
