package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcD$sp;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class OpenAddressHashArray$mcD$sp extends OpenAddressHashArray implements Storage$mcD$sp {
   private static final long serialVersionUID = 1L;
   public double[] _data$mcD$sp;
   public final ConfigurableDefault default$mcD$sp;
   public final Zero zero$mcD$sp;

   public double[] _data$mcD$sp() {
      return this._data$mcD$sp;
   }

   public double[] _data() {
      return this._data$mcD$sp();
   }

   public void _data$mcD$sp_$eq(final double[] x$1) {
      this._data$mcD$sp = x$1;
   }

   public void _data_$eq(final double[] x$1) {
      this._data$mcD$sp_$eq(x$1);
   }

   public ConfigurableDefault default$mcD$sp() {
      return this.default$mcD$sp;
   }

   public ConfigurableDefault default() {
      return this.default$mcD$sp();
   }

   public Zero zero$mcD$sp() {
      return this.zero$mcD$sp;
   }

   public Zero zero() {
      return this.zero$mcD$sp();
   }

   public double[] data() {
      return this.data$mcD$sp();
   }

   public double[] data$mcD$sp() {
      return this._data();
   }

   public double defaultValue() {
      return this.defaultValue$mcD$sp();
   }

   public double defaultValue$mcD$sp() {
      return this.default().value$mcD$sp(this.zero());
   }

   public double valueAt(final int i) {
      return this.valueAt$mcD$sp(i);
   }

   public double valueAt$mcD$sp(final int i) {
      return this.data$mcD$sp()[i];
   }

   public final double apply(final int i) {
      return this.apply$mcD$sp(i);
   }

   public final double apply$mcD$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.index().length == 0 ? this.default().value$mcD$sp(this.zero()) : this.data$mcD$sp()[this.breeze$collection$mutable$OpenAddressHashArray$$locate(i)];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final void update(final int i, final double v) {
      this.update$mcD$sp(i, v);
   }

   public final void update$mcD$sp(final int i, final double v) {
      while(true) {
         if (i >= 0 && i < this.size()) {
            int pos = this.breeze$collection$mutable$OpenAddressHashArray$$locate(i);
            this._data()[pos] = v;
            if (this._index()[pos] != i && v != this.defaultValue$mcD$sp()) {
               this.load_$eq(this.load() + 1);
               if (this.load() * 4 > this._index().length * 3) {
                  this.rehash();
                  v = v;
                  i = i;
                  continue;
               }

               this._index()[pos] = i;
               BoxedUnit var6 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return;
         }

         throw new IndexOutOfBoundsException((new StringBuilder(27)).append(i).append(" is out of bounds for size ").append(this.size()).toString());
      }
   }

   public OpenAddressHashArray copy() {
      return this.copy$mcD$sp();
   }

   public OpenAddressHashArray copy$mcD$sp() {
      return new OpenAddressHashArray$mcD$sp(Arrays.copyOf(this._index(), this._index().length), (double[])ArrayUtil$.MODULE$.copyOf(this._data(), this._data().length), this.load(), this.size(), this.default(), this.manElem(), this.zero());
   }

   public void copyTo(final OpenAddressHashArray other) {
      this.copyTo$mcD$sp(other);
   }

   public void copyTo$mcD$sp(final OpenAddressHashArray other) {
      .MODULE$.require(other.length() == other.length(), () -> "vectors must have the same length");
      .MODULE$.require(this.defaultValue$mcD$sp() == other.defaultValue$mcD$sp(), () -> "vectors must have the same default");
      other._index_$eq((int[])this._index().clone());
      other._data$mcD$sp_$eq((double[])this._data().clone());
      other.load_$eq(this.load());
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenAddressHashArray$mcD$sp(final int[] _index, final double[] _data$mcD$sp, final int load, final int size, final ConfigurableDefault default$mcD$sp, final ClassTag manElem, final Zero zero$mcD$sp) {
      this._data$mcD$sp = _data$mcD$sp;
      this.default$mcD$sp = default$mcD$sp;
      this.zero$mcD$sp = zero$mcD$sp;
      super(_index, (Object)null, load, size, (ConfigurableDefault)null, manElem, (Zero)null);
   }

   public OpenAddressHashArray$mcD$sp(final int size, final ConfigurableDefault default$mcD$sp, final int initialSize, final ClassTag manElem, final Zero zero$mcD$sp) {
      this(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize)), (double[])default$mcD$sp.makeArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize), zero$mcD$sp, manElem), 0, size, default$mcD$sp, manElem, zero$mcD$sp);
   }

   public OpenAddressHashArray$mcD$sp(final int size, final ConfigurableDefault default$mcD$sp, final ClassTag manElem, final Zero zero$mcD$sp) {
      this(size, default$mcD$sp, 16, manElem, zero$mcD$sp);
   }

   public OpenAddressHashArray$mcD$sp(final int size, final ClassTag manElem, final Zero zero$mcD$sp) {
      this(size, ConfigurableDefault$.MODULE$.default(), manElem, zero$mcD$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
