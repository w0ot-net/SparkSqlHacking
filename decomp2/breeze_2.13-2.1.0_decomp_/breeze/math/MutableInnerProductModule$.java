package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function1;

public final class MutableInnerProductModule$ {
   public static final MutableInnerProductModule$ MODULE$ = new MutableInnerProductModule$();

   public MutableInnerProductModule make(final Ring _ring, final .less.colon.less _ops, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl2 _dotVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV) {
      return new MutableInnerProductModule(_ring, _ops, _zeroLike, _mulVS, _addVV, _subVV, _dotVV, _copy, _mulIntoVS, _addIntoVV, _subIntoVV, _setIntoVV, _scaleAddVSV) {
         private final Function1 hasOps;
         private final Ring _ring$2;
         private final CanCreateZerosLike _zeroLike$4;
         private final UFunc.UImpl2 _mulVS$4;
         private final UFunc.UImpl2 _addVV$4;
         private final UFunc.UImpl2 _subVV$4;
         private final UFunc.UImpl2 _dotVV$3;
         private final CanCopy _copy$3;
         private final UFunc.InPlaceImpl2 _mulIntoVS$3;
         private final UFunc.InPlaceImpl2 _addIntoVV$3;
         private final UFunc.InPlaceImpl2 _subIntoVV$3;
         private final UFunc.InPlaceImpl2 _setIntoVV$3;
         private final UFunc.InPlaceImpl3 _scaleAddVSV$3;

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

         public Ring scalars() {
            return this._ring$2;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$4;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$4;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$4;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$4;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$3;
         }

         public CanCopy copy() {
            return this._copy$3;
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return this._mulIntoVS$3;
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this._addIntoVV$3;
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this._subIntoVV$3;
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this._setIntoVV$3;
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return this._scaleAddVSV$3;
         }

         public {
            this._ring$2 = _ring$2;
            this._zeroLike$4 = _zeroLike$4;
            this._mulVS$4 = _mulVS$4;
            this._addVV$4 = _addVV$4;
            this._subVV$4 = _subVV$4;
            this._dotVV$3 = _dotVV$3;
            this._copy$3 = _copy$3;
            this._mulIntoVS$3 = _mulIntoVS$3;
            this._addIntoVV$3 = _addIntoVV$3;
            this._subIntoVV$3 = _subIntoVV$3;
            this._setIntoVV$3 = _setIntoVV$3;
            this._scaleAddVSV$3 = _scaleAddVSV$3;
            Module.$init$(this);
            MutableModule.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$4);
         }
      };
   }

   public MutableInnerProductModule liftFloat(final MutableInnerProductModule vs) {
      return new MutableInnerProductModule(vs) {
         private final Function1 hasOps;
         private final MutableInnerProductModule vs$1;

         public UFunc.UImpl normImpl() {
            return InnerProductModule.normImpl$(this);
         }

         public UFunc.UImpl scalarNorm() {
            return NormedModule.scalarNorm$(this);
         }

         public UFunc.InPlaceImpl2 mulIntoVS_M() {
            return MutableModule.mulIntoVS_M$(this);
         }

         public UFunc.UImpl2 mulVS_M() {
            return Module.mulVS_M$(this);
         }

         public Field scalars() {
            return Field.fieldDouble$.MODULE$;
         }

         public CanCreateZerosLike zeroLike() {
            return this.vs$1.zeroLike();
         }

         public UFunc.UImpl2 mulVS() {
            return FloatDoubleOperatorAdaptors$.MODULE$.liftOp2(this.vs$1.mulVS());
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public boolean close(final Object a, final Object b, final double tolerance) {
            return this.vs$1.close(a, b, tolerance);
         }

         public UFunc.UImpl2 subVV() {
            return this.vs$1.subVV();
         }

         public UFunc.UImpl2 addVV() {
            return this.vs$1.addVV();
         }

         public UFunc.UImpl2 dotVV() {
            return FloatDoubleOperatorAdaptors$.MODULE$.liftOpReturnFloat(this.vs$1.dotVV());
         }

         public CanCopy copy() {
            return this.vs$1.copy();
         }

         public UFunc.InPlaceImpl2 addIntoVV() {
            return this.vs$1.addIntoVV();
         }

         public UFunc.InPlaceImpl2 subIntoVV() {
            return this.vs$1.subIntoVV();
         }

         public UFunc.InPlaceImpl2 setIntoVV() {
            return this.vs$1.setIntoVV();
         }

         public UFunc.InPlaceImpl2 mulIntoVS() {
            return FloatDoubleOperatorAdaptors$.MODULE$.liftInPlaceOp2(this.vs$1.mulIntoVS());
         }

         public UFunc.InPlaceImpl3 scaleAddVV() {
            return FloatDoubleOperatorAdaptors$.MODULE$.liftInPlaceOp3(this.vs$1.scaleAddVV());
         }

         public {
            this.vs$1 = vs$1;
            Module.$init$(this);
            MutableModule.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            this.hasOps = vs$1.hasOps();
         }
      };
   }

   private MutableInnerProductModule$() {
   }
}
