package spire.algebra;

public interface CoordinateSpace$mcF$sp extends CoordinateSpace, InnerProductSpace$mcF$sp {
   // $FF: synthetic method
   static float _x$(final CoordinateSpace$mcF$sp $this, final Object v) {
      return $this._x(v);
   }

   default float _x(final Object v) {
      return this._x$mcF$sp(v);
   }

   // $FF: synthetic method
   static float _x$mcF$sp$(final CoordinateSpace$mcF$sp $this, final Object v) {
      return $this._x$mcF$sp(v);
   }

   default float _x$mcF$sp(final Object v) {
      return this.coord$mcF$sp(v, 0);
   }

   // $FF: synthetic method
   static float _y$(final CoordinateSpace$mcF$sp $this, final Object v) {
      return $this._y(v);
   }

   default float _y(final Object v) {
      return this._y$mcF$sp(v);
   }

   // $FF: synthetic method
   static float _y$mcF$sp$(final CoordinateSpace$mcF$sp $this, final Object v) {
      return $this._y$mcF$sp(v);
   }

   default float _y$mcF$sp(final Object v) {
      return this.coord$mcF$sp(v, 1);
   }

   // $FF: synthetic method
   static float _z$(final CoordinateSpace$mcF$sp $this, final Object v) {
      return $this._z(v);
   }

   default float _z(final Object v) {
      return this._z$mcF$sp(v);
   }

   // $FF: synthetic method
   static float _z$mcF$sp$(final CoordinateSpace$mcF$sp $this, final Object v) {
      return $this._z$mcF$sp(v);
   }

   default float _z$mcF$sp(final Object v) {
      return this.coord$mcF$sp(v, 2);
   }

   // $FF: synthetic method
   static float dot$(final CoordinateSpace$mcF$sp $this, final Object v, final Object w) {
      return $this.dot(v, w);
   }

   default float dot(final Object v, final Object w) {
      return this.dot$mcF$sp(v, w);
   }

   // $FF: synthetic method
   static float dot$mcF$sp$(final CoordinateSpace$mcF$sp $this, final Object v, final Object w) {
      return $this.dot$mcF$sp(v, w);
   }

   default float dot$mcF$sp(final Object v, final Object w) {
      return this.loop$3(this.scalar$mcF$sp().zero$mcF$sp(), 0, v, w);
   }

   private float loop$3(final float sum, final int i, final Object v$3, final Object w$3) {
      while(i < this.dimensions()) {
         float var10000 = this.scalar$mcF$sp().plus$mcF$sp(sum, this.scalar$mcF$sp().times$mcF$sp(this.coord$mcF$sp(v$3, i), this.coord$mcF$sp(w$3, i)));
         ++i;
         sum = var10000;
      }

      return sum;
   }
}
