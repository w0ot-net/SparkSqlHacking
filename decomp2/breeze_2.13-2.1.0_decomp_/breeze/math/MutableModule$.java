package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function1;
import scala.Function3;
import scala.runtime.BoxesRunTime;

public final class MutableModule$ {
   public static final MutableModule$ MODULE$ = new MutableModule$();

   public MutableModule make(final Function3 closeTo, final Ring _ring, final CanCreateZerosLike _zeroLike, final .less.colon.less _ops, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV) {
      return new MutableModule(_ring, closeTo, _ops, _zeroLike, _mulVS, _addVV, _subVV, _copy, _mulIntoVS, _addIntoVV, _subIntoVV, _setIntoVV, _scaleAddVSV) {
         private final Function1 hasOps;
         private final Ring _ring$1;
         private final Function3 closeTo$1;
         private final CanCreateZerosLike _zeroLike$2;
         private final UFunc.UImpl2 _mulVS$2;
         private final UFunc.UImpl2 _addVV$2;
         private final UFunc.UImpl2 _subVV$2;
         private final CanCopy _copy$1;
         private final UFunc.InPlaceImpl2 _mulIntoVS$1;
         private final UFunc.InPlaceImpl2 _addIntoVV$1;
         private final UFunc.InPlaceImpl2 _subIntoVV$1;
         private final UFunc.InPlaceImpl2 _setIntoVV$1;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$1;

         public UFunc.InPlaceImpl2 mulIntoVS_M() {
            return MutableModule.mulIntoVS_M$(this);
         }

         public UFunc.UImpl2 mulVS_M() {
            return Module.mulVS_M$(this);
         }

         public Ring scalars() {
            return this._ring$1;
         }

         public boolean close(final Object a, final Object b, final double tolerance) {
            return BoxesRunTime.unboxToBoolean(this.closeTo$1.apply(a, b, BoxesRunTime.boxToDouble(tolerance)));
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$2;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$2;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$2;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$2;
         }

         public CanCopy copy() {
            return this._copy$1;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$1;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$1;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$1;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$1;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$1;
         }

         public {
            this._ring$1 = _ring$1;
            this.closeTo$1 = closeTo$1;
            this._zeroLike$2 = _zeroLike$2;
            this._mulVS$2 = _mulVS$2;
            this._addVV$2 = _addVV$2;
            this._subVV$2 = _subVV$2;
            this._copy$1 = _copy$1;
            this._mulIntoVS$1 = _mulIntoVS$1;
            this._addIntoVV$1 = _addIntoVV$1;
            this._subIntoVV$1 = _subIntoVV$1;
            this._setIntoVV$1 = _setIntoVV$1;
            this._scaleAddVSV$1 = _scaleAddVSV$1;
            Module.$init$(this);
            MutableModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$2);
         }
      };
   }

   private MutableModule$() {
   }
}
