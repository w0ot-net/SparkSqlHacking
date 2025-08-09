package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function1;

public final class MutableInnerProductVectorSpace$ {
   public static final MutableInnerProductVectorSpace$ MODULE$ = new MutableInnerProductVectorSpace$();

   public MutableInnerProductVectorSpace make(final Field _field, final .less.colon.less _ops, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl2 _dotVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _divIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV) {
      return new MutableInnerProductVectorSpace(_field, _ops, _zeroLike, _mulVS, _divVS, _addVV, _subVV, _dotVV, _copy, _mulIntoVS, _divIntoVS, _addIntoVV, _subIntoVV, _setIntoVV, _scaleAddVSV) {
         private final Function1 hasOps;
         private final Field _field$2;
         private final CanCreateZerosLike _zeroLike$3;
         private final UFunc.UImpl2 _mulVS$3;
         private final UFunc.UImpl2 _divVS$2;
         private final UFunc.UImpl2 _addVV$3;
         private final UFunc.UImpl2 _subVV$3;
         private final UFunc.UImpl2 _dotVV$2;
         private final CanCopy _copy$2;
         private final UFunc.InPlaceImpl2 _mulIntoVS$2;
         private final UFunc.InPlaceImpl2 _divIntoVS$1;
         private final UFunc.InPlaceImpl2 _addIntoVV$2;
         private final UFunc.InPlaceImpl2 _subIntoVV$2;
         private final UFunc.InPlaceImpl2 _setIntoVV$2;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$2;

         public UFunc.UImpl normImpl() {
            return InnerProductModule.normImpl$(this);
         }

         public UFunc.UImpl scalarNorm() {
            return NormedModule.scalarNorm$(this);
         }

         public boolean close(final Object a, final Object b, final double tolerance) {
            return NormedModule.close$(this, a, b, tolerance);
         }

         public UFunc.InPlaceImpl2 mulIntoVS_M() {
            return MutableModule.mulIntoVS_M$(this);
         }

         public UFunc.UImpl2 mulVS_M() {
            return Module.mulVS_M$(this);
         }

         public Field scalars() {
            return this._field$2;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$3;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$3;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$2;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$3;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$3;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$2;
         }

         public CanCopy copy() {
            return this._copy$2;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$2;
         }

         public UFunc.InPlaceImpl2 divIntoVS() {
            return this._divIntoVS$1;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$2;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$2;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$2;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$2;
         }

         public {
            this._field$2 = _field$2;
            this._zeroLike$3 = _zeroLike$3;
            this._mulVS$3 = _mulVS$3;
            this._divVS$2 = _divVS$2;
            this._addVV$3 = _addVV$3;
            this._subVV$3 = _subVV$3;
            this._dotVV$2 = _dotVV$2;
            this._copy$2 = _copy$2;
            this._mulIntoVS$2 = _mulIntoVS$2;
            this._divIntoVS$1 = _divIntoVS$1;
            this._addIntoVV$2 = _addIntoVV$2;
            this._subIntoVV$2 = _subIntoVV$2;
            this._setIntoVV$2 = _setIntoVV$2;
            this._scaleAddVSV$2 = _scaleAddVSV$2;
            Module.$init$(this);
            MutableModule.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$3);
         }
      };
   }

   private MutableInnerProductVectorSpace$() {
   }
}
