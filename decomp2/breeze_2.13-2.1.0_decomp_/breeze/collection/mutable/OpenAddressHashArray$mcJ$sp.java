package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcJ$sp;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class OpenAddressHashArray$mcJ$sp extends OpenAddressHashArray implements Storage$mcJ$sp {
   private static final long serialVersionUID = 1L;
   public long[] _data$mcJ$sp;
   public final ConfigurableDefault default$mcJ$sp;
   public final Zero zero$mcJ$sp;

   public long[] _data$mcJ$sp() {
      return this._data$mcJ$sp;
   }

   public long[] _data() {
      return this._data$mcJ$sp();
   }

   public void _data$mcJ$sp_$eq(final long[] x$1) {
      this._data$mcJ$sp = x$1;
   }

   public void _data_$eq(final long[] x$1) {
      this._data$mcJ$sp_$eq(x$1);
   }

   public ConfigurableDefault default$mcJ$sp() {
      return this.default$mcJ$sp;
   }

   public ConfigurableDefault default() {
      return this.default$mcJ$sp();
   }

   public Zero zero$mcJ$sp() {
      return this.zero$mcJ$sp;
   }

   public Zero zero() {
      return this.zero$mcJ$sp();
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public long[] data$mcJ$sp() {
      return this._data();
   }

   public long defaultValue() {
      return this.defaultValue$mcJ$sp();
   }

   public long defaultValue$mcJ$sp() {
      return this.default().value$mcJ$sp(this.zero());
   }

   public long valueAt(final int i) {
      return this.valueAt$mcJ$sp(i);
   }

   public long valueAt$mcJ$sp(final int i) {
      return this.data$mcJ$sp()[i];
   }

   public final long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public final long apply$mcJ$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.index().length == 0 ? this.default().value$mcJ$sp(this.zero()) : this.data$mcJ$sp()[this.breeze$collection$mutable$OpenAddressHashArray$$locate(i)];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final void update(final int i, final long v) {
      this.update$mcJ$sp(i, v);
   }

   public final void update$mcJ$sp(final int i, final long v) {
      while(true) {
         if (i >= 0 && i < this.size()) {
            int pos = this.breeze$collection$mutable$OpenAddressHashArray$$locate(i);
            this._data()[pos] = v;
            if (this._index()[pos] != i && v != this.defaultValue$mcJ$sp()) {
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
      return this.copy$mcJ$sp();
   }

   public OpenAddressHashArray copy$mcJ$sp() {
      return new OpenAddressHashArray$mcJ$sp(Arrays.copyOf(this._index(), this._index().length), (long[])ArrayUtil$.MODULE$.copyOf(this._data(), this._data().length), this.load(), this.size(), this.default(), this.manElem(), this.zero());
   }

   public void copyTo(final OpenAddressHashArray other) {
      this.copyTo$mcJ$sp(other);
   }

   public void copyTo$mcJ$sp(final OpenAddressHashArray other) {
      .MODULE$.require(other.length() == other.length(), () -> "vectors must have the same length");
      .MODULE$.require(this.defaultValue$mcJ$sp() == other.defaultValue$mcJ$sp(), () -> "vectors must have the same default");
      other._index_$eq((int[])this._index().clone());
      other._data$mcJ$sp_$eq((long[])this._data().clone());
      other.load_$eq(this.load());
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenAddressHashArray$mcJ$sp(final int[] _index, final long[] _data$mcJ$sp, final int load, final int size, final ConfigurableDefault default$mcJ$sp, final ClassTag manElem, final Zero zero$mcJ$sp) {
      this._data$mcJ$sp = _data$mcJ$sp;
      this.default$mcJ$sp = default$mcJ$sp;
      this.zero$mcJ$sp = zero$mcJ$sp;
      super(_index, (Object)null, load, size, (ConfigurableDefault)null, manElem, (Zero)null);
   }

   public OpenAddressHashArray$mcJ$sp(final int size, final ConfigurableDefault default$mcJ$sp, final int initialSize, final ClassTag manElem, final Zero zero$mcJ$sp) {
      this(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize)), (long[])default$mcJ$sp.makeArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize), zero$mcJ$sp, manElem), 0, size, default$mcJ$sp, manElem, zero$mcJ$sp);
   }

   public OpenAddressHashArray$mcJ$sp(final int size, final ConfigurableDefault default$mcJ$sp, final ClassTag manElem, final Zero zero$mcJ$sp) {
      this(size, default$mcJ$sp, 16, manElem, zero$mcJ$sp);
   }

   public OpenAddressHashArray$mcJ$sp(final int size, final ClassTag manElem, final Zero zero$mcJ$sp) {
      this(size, ConfigurableDefault$.MODULE$.default(), manElem, zero$mcJ$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
