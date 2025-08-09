package spire.algebra;

public interface CoordinateSpace$mcD$sp extends CoordinateSpace, InnerProductSpace$mcD$sp {
   // $FF: synthetic method
   static double _x$(final CoordinateSpace$mcD$sp $this, final Object v) {
      return $this._x(v);
   }

   default double _x(final Object v) {
      return this._x$mcD$sp(v);
   }

   // $FF: synthetic method
   static double _x$mcD$sp$(final CoordinateSpace$mcD$sp $this, final Object v) {
      return $this._x$mcD$sp(v);
   }

   default double _x$mcD$sp(final Object v) {
      return this.coord$mcD$sp(v, 0);
   }

   // $FF: synthetic method
   static double _y$(final CoordinateSpace$mcD$sp $this, final Object v) {
      return $this._y(v);
   }

   default double _y(final Object v) {
      return this._y$mcD$sp(v);
   }

   // $FF: synthetic method
   static double _y$mcD$sp$(final CoordinateSpace$mcD$sp $this, final Object v) {
      return $this._y$mcD$sp(v);
   }

   default double _y$mcD$sp(final Object v) {
      return this.coord$mcD$sp(v, 1);
   }

   // $FF: synthetic method
   static double _z$(final CoordinateSpace$mcD$sp $this, final Object v) {
      return $this._z(v);
   }

   default double _z(final Object v) {
      return this._z$mcD$sp(v);
   }

   // $FF: synthetic method
   static double _z$mcD$sp$(final CoordinateSpace$mcD$sp $this, final Object v) {
      return $this._z$mcD$sp(v);
   }

   default double _z$mcD$sp(final Object v) {
      return this.coord$mcD$sp(v, 2);
   }

   // $FF: synthetic method
   static double dot$(final CoordinateSpace$mcD$sp $this, final Object v, final Object w) {
      return $this.dot(v, w);
   }

   default double dot(final Object v, final Object w) {
      return this.dot$mcD$sp(v, w);
   }

   // $FF: synthetic method
   static double dot$mcD$sp$(final CoordinateSpace$mcD$sp $this, final Object v, final Object w) {
      return $this.dot$mcD$sp(v, w);
   }

   default double dot$mcD$sp(final Object v, final Object w) {
      return this.loop$2(this.scalar$mcD$sp().zero$mcD$sp(), 0, v, w);
   }

   private double loop$2(final double sum, final int i, final Object v$2, final Object w$2) {
      while(i < this.dimensions()) {
         double var10000 = this.scalar$mcD$sp().plus$mcD$sp(sum, this.scalar$mcD$sp().times$mcD$sp(this.coord$mcD$sp(v$2, i), this.coord$mcD$sp(w$2, i)));
         ++i;
         sum = var10000;
      }

      return sum;
   }
}
