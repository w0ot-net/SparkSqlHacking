package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcI$sp;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class OpenAddressHashArray$mcI$sp extends OpenAddressHashArray implements Storage$mcI$sp {
   private static final long serialVersionUID = 1L;
   public int[] _data$mcI$sp;
   public final ConfigurableDefault default$mcI$sp;
   public final Zero zero$mcI$sp;

   public int[] _data$mcI$sp() {
      return this._data$mcI$sp;
   }

   public int[] _data() {
      return this._data$mcI$sp();
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data$mcI$sp = x$1;
   }

   public void _data_$eq(final int[] x$1) {
      this._data$mcI$sp_$eq(x$1);
   }

   public ConfigurableDefault default$mcI$sp() {
      return this.default$mcI$sp;
   }

   public ConfigurableDefault default() {
      return this.default$mcI$sp();
   }

   public Zero zero$mcI$sp() {
      return this.zero$mcI$sp;
   }

   public Zero zero() {
      return this.zero$mcI$sp();
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public int[] data$mcI$sp() {
      return this._data();
   }

   public int defaultValue() {
      return this.defaultValue$mcI$sp();
   }

   public int defaultValue$mcI$sp() {
      return this.default().value$mcI$sp(this.zero());
   }

   public int valueAt(final int i) {
      return this.valueAt$mcI$sp(i);
   }

   public int valueAt$mcI$sp(final int i) {
      return this.data$mcI$sp()[i];
   }

   public final int apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public final int apply$mcI$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.index().length == 0 ? this.default().value$mcI$sp(this.zero()) : this.data$mcI$sp()[this.breeze$collection$mutable$OpenAddressHashArray$$locate(i)];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final void update(final int i, final int v) {
      this.update$mcI$sp(i, v);
   }

   public final void update$mcI$sp(final int i, final int v) {
      while(true) {
         if (i >= 0 && i < this.size()) {
            int pos = this.breeze$collection$mutable$OpenAddressHashArray$$locate(i);
            this._data()[pos] = v;
            if (this._index()[pos] != i && v != this.defaultValue$mcI$sp()) {
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
      return this.copy$mcI$sp();
   }

   public OpenAddressHashArray copy$mcI$sp() {
      return new OpenAddressHashArray$mcI$sp(Arrays.copyOf(this._index(), this._index().length), (int[])ArrayUtil$.MODULE$.copyOf(this._data(), this._data().length), this.load(), this.size(), this.default(), this.manElem(), this.zero());
   }

   public void copyTo(final OpenAddressHashArray other) {
      this.copyTo$mcI$sp(other);
   }

   public void copyTo$mcI$sp(final OpenAddressHashArray other) {
      .MODULE$.require(other.length() == other.length(), () -> "vectors must have the same length");
      .MODULE$.require(this.defaultValue$mcI$sp() == other.defaultValue$mcI$sp(), () -> "vectors must have the same default");
      other._index_$eq((int[])this._index().clone());
      other._data$mcI$sp_$eq((int[])this._data().clone());
      other.load_$eq(this.load());
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenAddressHashArray$mcI$sp(final int[] _index, final int[] _data$mcI$sp, final int load, final int size, final ConfigurableDefault default$mcI$sp, final ClassTag manElem, final Zero zero$mcI$sp) {
      this._data$mcI$sp = _data$mcI$sp;
      this.default$mcI$sp = default$mcI$sp;
      this.zero$mcI$sp = zero$mcI$sp;
      super(_index, (Object)null, load, size, (ConfigurableDefault)null, manElem, (Zero)null);
   }

   public OpenAddressHashArray$mcI$sp(final int size, final ConfigurableDefault default$mcI$sp, final int initialSize, final ClassTag manElem, final Zero zero$mcI$sp) {
      this(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize)), (int[])default$mcI$sp.makeArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize), zero$mcI$sp, manElem), 0, size, default$mcI$sp, manElem, zero$mcI$sp);
   }

   public OpenAddressHashArray$mcI$sp(final int size, final ConfigurableDefault default$mcI$sp, final ClassTag manElem, final Zero zero$mcI$sp) {
      this(size, default$mcI$sp, 16, manElem, zero$mcI$sp);
   }

   public OpenAddressHashArray$mcI$sp(final int size, final ClassTag manElem, final Zero zero$mcI$sp) {
      this(size, ConfigurableDefault$.MODULE$.default(), manElem, zero$mcI$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
