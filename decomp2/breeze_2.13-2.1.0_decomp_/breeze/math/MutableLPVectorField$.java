package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function1;

public final class MutableLPVectorField$ {
   public static final MutableLPVectorField$ MODULE$ = new MutableLPVectorField$();

   public MutableLPVectorField make(final UFunc.UImpl _norm, final UFunc.UImpl2 _norm2, final Field _field, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _mulIntoVV, final UFunc.InPlaceImpl2 _divIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl _neg, final .less.colon.less _ops, final UFunc.UImpl2 _dotVV) {
      return new MutableLPVectorField(_field, _ops, _norm, _norm2, _zeroLike, _mulVV, _divVV, _copy, _mulIntoVS, _divIntoVS, _addIntoVV, _subIntoVV, _mulIntoVV, _divIntoVV, _mulVS, _divVS, _addVV, _subVV, _neg, _dotVV, _setIntoVV, _scaleAddVSV) {
         private final Function1 hasOps;
         private final Field _field$4;
         private final UFunc.UImpl _norm$3;
         private final UFunc.UImpl2 _norm2$1;
         private final CanCreateZerosLike _zeroLike$6;
         private final UFunc.UImpl2 _mulVV$3;
         private final UFunc.UImpl2 _divVV$3;
         private final CanCopy _copy$5;
         private final UFunc.InPlaceImpl2 _mulIntoVS$5;
         private final UFunc.InPlaceImpl2 _divIntoVS$3;
         private final UFunc.InPlaceImpl2 _addIntoVV$5;
         private final UFunc.InPlaceImpl2 _subIntoVV$5;
         private final UFunc.InPlaceImpl2 _mulIntoVV$2;
         private final UFunc.InPlaceImpl2 _divIntoVV$2;
         private final UFunc.UImpl2 _mulVS$6;
         private final UFunc.UImpl2 _divVS$4;
         private final UFunc.UImpl2 _addVV$6;
         private final UFunc.UImpl2 _subVV$6;
         private final UFunc.UImpl _neg$3;
         private final UFunc.UImpl2 _dotVV$5;
         private final UFunc.InPlaceImpl2 _setIntoVV$5;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$5;

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
            return this._field$4;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl normImpl() {
            return this._norm$3;
         }

         public UFunc.UImpl2 normImpl2() {
            return this._norm2$1;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$6;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$3;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$3;
         }

         public CanCopy copy() {
            return this._copy$5;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$5;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$3;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$5;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$5;
         }

         public UFunc.InPlaceImpl2 mulIntoVV() {
            return this._mulIntoVV$2;
         }

         public UFunc.InPlaceImpl2 divIntoVV() {
            return this._divIntoVV$2;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$6;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$4;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$6;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$6;
         }

         public UFunc.UImpl neg() {
            return this._neg$3;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$5;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$5;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$5;
         }

         public {
            this._field$4 = _field$4;
            this._norm$3 = _norm$3;
            this._norm2$1 = _norm2$1;
            this._zeroLike$6 = _zeroLike$6;
            this._mulVV$3 = _mulVV$3;
            this._divVV$3 = _divVV$3;
            this._copy$5 = _copy$5;
            this._mulIntoVS$5 = _mulIntoVS$5;
            this._divIntoVS$3 = _divIntoVS$3;
            this._addIntoVV$5 = _addIntoVV$5;
            this._subIntoVV$5 = _subIntoVV$5;
            this._mulIntoVV$2 = _mulIntoVV$2;
            this._divIntoVV$2 = _divIntoVV$2;
            this._mulVS$6 = _mulVS$6;
            this._divVS$4 = _divVS$4;
            this._addVV$6 = _addVV$6;
            this._subVV$6 = _subVV$6;
            this._neg$3 = _neg$3;
            this._dotVV$5 = _dotVV$5;
            this._setIntoVV$5 = _setIntoVV$5;
            this._scaleAddVSV$5 = _scaleAddVSV$5;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$6);
         }
      };
   }

   private MutableLPVectorField$() {
   }
}
