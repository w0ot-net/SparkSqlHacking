package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function1;

public final class VectorField$ {
   public static final VectorField$ MODULE$ = new VectorField$();

   public VectorField make(final UFunc.UImpl _norm, final Field _field, final UFunc.UImpl2 _mulVV, final UFunc.UImpl2 _divVV, final CanCreateZerosLike _zeroLike, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _divVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final UFunc.UImpl2 _dotVV, final UFunc.UImpl _neg, final .less.colon.less _ops) {
      return new VectorField(_field, _ops, _norm, _dotVV, _zeroLike, _mulVV, _divVV, _mulVS, _divVS, _addVV, _subVV, _neg) {
         private final Function1 hasOps;
         private final Field _field$1;
         private final UFunc.UImpl _norm$1;
         private final UFunc.UImpl2 _dotVV$1;
         private final CanCreateZerosLike _zeroLike$1;
         private final UFunc.UImpl2 _mulVV$1;
         private final UFunc.UImpl2 _divVV$1;
         private final UFunc.UImpl2 _mulVS$1;
         private final UFunc.UImpl2 _divVS$1;
         private final UFunc.UImpl2 _addVV$1;
         private final UFunc.UImpl2 _subVV$1;
         private final UFunc.UImpl _neg$1;

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
            return this._field$1;
         }

         public Function1 hasOps() {
            return this.hasOps;
         }

         public UFunc.UImpl normImpl() {
            return this._norm$1;
         }

         public UFunc.UImpl2 dotVV() {
            return this._dotVV$1;
         }

         public CanCreateZerosLike zeroLike() {
            return this._zeroLike$1;
         }

         public UFunc.UImpl2 mulVV() {
            return this._mulVV$1;
         }

         public UFunc.UImpl2 divVV() {
            return this._divVV$1;
         }

         public UFunc.UImpl2 mulVS() {
            return this._mulVS$1;
         }

         public UFunc.UImpl2 divVS() {
            return this._divVS$1;
         }

         public UFunc.UImpl2 addVV() {
            return this._addVV$1;
         }

         public UFunc.UImpl2 subVV() {
            return this._subVV$1;
         }

         public UFunc.UImpl neg() {
            return this._neg$1;
         }

         public {
            this._field$1 = _field$1;
            this._norm$1 = _norm$1;
            this._dotVV$1 = _dotVV$1;
            this._zeroLike$1 = _zeroLike$1;
            this._mulVV$1 = _mulVV$1;
            this._divVV$1 = _divVV$1;
            this._mulVS$1 = _mulVS$1;
            this._divVS$1 = _divVS$1;
            this._addVV$1 = _addVV$1;
            this._subVV$1 = _subVV$1;
            this._neg$1 = _neg$1;
            Module.$init$(this);
            NormedModule.$init$(this);
            InnerProductModule.$init$(this);
            this.hasOps = (Function1)scala.Predef..MODULE$.implicitly(_ops$1);
         }
      };
   }

   private VectorField$() {
   }
}
