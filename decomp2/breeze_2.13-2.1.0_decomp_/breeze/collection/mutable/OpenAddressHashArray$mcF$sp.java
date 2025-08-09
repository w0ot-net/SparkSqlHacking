package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcF$sp;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class OpenAddressHashArray$mcF$sp extends OpenAddressHashArray implements Storage$mcF$sp {
   private static final long serialVersionUID = 1L;
   public float[] _data$mcF$sp;
   public final ConfigurableDefault default$mcF$sp;
   public final Zero zero$mcF$sp;

   public float[] _data$mcF$sp() {
      return this._data$mcF$sp;
   }

   public float[] _data() {
      return this._data$mcF$sp();
   }

   public void _data$mcF$sp_$eq(final float[] x$1) {
      this._data$mcF$sp = x$1;
   }

   public void _data_$eq(final float[] x$1) {
      this._data$mcF$sp_$eq(x$1);
   }

   public ConfigurableDefault default$mcF$sp() {
      return this.default$mcF$sp;
   }

   public ConfigurableDefault default() {
      return this.default$mcF$sp();
   }

   public Zero zero$mcF$sp() {
      return this.zero$mcF$sp;
   }

   public Zero zero() {
      return this.zero$mcF$sp();
   }

   public float[] data() {
      return this.data$mcF$sp();
   }

   public float[] data$mcF$sp() {
      return this._data();
   }

   public float defaultValue() {
      return this.defaultValue$mcF$sp();
   }

   public float defaultValue$mcF$sp() {
      return this.default().value$mcF$sp(this.zero());
   }

   public float valueAt(final int i) {
      return this.valueAt$mcF$sp(i);
   }

   public float valueAt$mcF$sp(final int i) {
      return this.data$mcF$sp()[i];
   }

   public final float apply(final int i) {
      return this.apply$mcF$sp(i);
   }

   public final float apply$mcF$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.index().length == 0 ? this.default().value$mcF$sp(this.zero()) : this.data$mcF$sp()[this.breeze$collection$mutable$OpenAddressHashArray$$locate(i)];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final void update(final int i, final float v) {
      this.update$mcF$sp(i, v);
   }

   public final void update$mcF$sp(final int i, final float v) {
      while(true) {
         if (i >= 0 && i < this.size()) {
            int pos = this.breeze$collection$mutable$OpenAddressHashArray$$locate(i);
            this._data()[pos] = v;
            if (this._index()[pos] != i && v != this.defaultValue$mcF$sp()) {
               this.load_$eq(this.load() + 1);
               if (this.load() * 4 > this._index().length * 3) {
                  this.rehash();
                  v = v;
                  i = i;
                  continue;
               }

               this._index()[pos] = i;
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return;
         }

         throw new IndexOutOfBoundsException((new StringBuilder(27)).append(i).append(" is out of bounds for size ").append(this.size()).toString());
      }
   }

   public OpenAddressHashArray copy() {
      return this.copy$mcF$sp();
   }

   public OpenAddressHashArray copy$mcF$sp() {
      return new OpenAddressHashArray$mcF$sp(Arrays.copyOf(this._index(), this._index().length), (float[])ArrayUtil$.MODULE$.copyOf(this._data(), this._data().length), this.load(), this.size(), this.default(), this.manElem(), this.zero());
   }

   public void copyTo(final OpenAddressHashArray other) {
      this.copyTo$mcF$sp(other);
   }

   public void copyTo$mcF$sp(final OpenAddressHashArray other) {
      .MODULE$.require(other.length() == other.length(), () -> "vectors must have the same length");
      .MODULE$.require(this.defaultValue$mcF$sp() == other.defaultValue$mcF$sp(), () -> "vectors must have the same default");
      other._index_$eq((int[])this._index().clone());
      other._data$mcF$sp_$eq((float[])this._data().clone());
      other.load_$eq(this.load());
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenAddressHashArray$mcF$sp(final int[] _index, final float[] _data$mcF$sp, final int load, final int size, final ConfigurableDefault default$mcF$sp, final ClassTag manElem, final Zero zero$mcF$sp) {
      this._data$mcF$sp = _data$mcF$sp;
      this.default$mcF$sp = default$mcF$sp;
      this.zero$mcF$sp = zero$mcF$sp;
      super(_index, (Object)null, load, size, (ConfigurableDefault)null, manElem, (Zero)null);
   }

   public OpenAddressHashArray$mcF$sp(final int size, final ConfigurableDefault default$mcF$sp, final int initialSize, final ClassTag manElem, final Zero zero$mcF$sp) {
      this(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize)), (float[])default$mcF$sp.makeArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize), zero$mcF$sp, manElem), 0, size, default$mcF$sp, manElem, zero$mcF$sp);
   }

   public OpenAddressHashArray$mcF$sp(final int size, final ConfigurableDefault default$mcF$sp, final ClassTag manElem, final Zero zero$mcF$sp) {
      this(size, default$mcF$sp, 16, manElem, zero$mcF$sp);
   }

   public OpenAddressHashArray$mcF$sp(final int size, final ClassTag manElem, final Zero zero$mcF$sp) {
      this(size, ConfigurableDefault$.MODULE$.default(), manElem, zero$mcF$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
