package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcJ$sp;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class SparseArray$mcJ$sp extends SparseArray implements Storage$mcJ$sp {
   private static final long serialVersionUID = 1L;
   public long[] data$mcJ$sp;
   public final long default$mcJ$sp;
   private final int used;

   public long[] data$mcJ$sp() {
      return this.data$mcJ$sp;
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public void data$mcJ$sp_$eq(final long[] x$1) {
      this.data$mcJ$sp = x$1;
   }

   public void data_$eq(final long[] x$1) {
      this.data$mcJ$sp_$eq(x$1);
   }

   public long default$mcJ$sp() {
      return this.default$mcJ$sp;
   }

   public long default() {
      return this.default$mcJ$sp();
   }

   public final long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public final long apply$mcJ$sp(final int i) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : this.default();
   }

   public long getOrElse(final int i, final Function0 value) {
      return this.getOrElse$mcJ$sp(i, value);
   }

   public long getOrElse$mcJ$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : value.apply$mcJ$sp();
   }

   public long getOrElseUpdate(final int i, final Function0 value) {
      return this.getOrElseUpdate$mcJ$sp(i, value);
   }

   public long getOrElseUpdate$mcJ$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      long var10000;
      if (offset >= 0) {
         var10000 = this.data()[offset];
      } else {
         long v = value.apply$mcJ$sp();
         this.update$mcJ$sp(i, v);
         var10000 = v;
      }

      return var10000;
   }

   public SparseArray map(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map$mcJ$sp(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcJ$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      Object newZero = ((Zero).MODULE$.implicitly(evidence$2)).zero();
      SparseArray var10000;
      if (this.breeze$collection$mutable$SparseArray$$used() <= this.length() && BoxesRunTime.equals(f.apply(BoxesRunTime.boxToLong(this.default())), newZero)) {
         int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
         Object newData = evidence$1.newArray(this.breeze$collection$mutable$SparseArray$$used());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            newIndex[o] = this.index()[i];
            Object newValue = f.apply(BoxesRunTime.boxToLong(this.data()[i]));
            if (!BoxesRunTime.equals(newValue, newZero)) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, newValue);
               ++o;
            }
         }

         var10000 = new SparseArray(newIndex, newData, o, this.length(), newZero);
      } else {
         Object newDefault = f.apply(BoxesRunTime.boxToLong(this.default()));
         int[] newIndex = new int[this.length()];
         Object newData = evidence$1.newArray(this.length());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            while(o < this.index()[i]) {
               newIndex[o] = o;
               scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, newDefault);
               ++o;
            }

            newIndex[o] = o;
            scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, f.apply(BoxesRunTime.boxToLong(this.data()[i])));
            ++o;
         }

         while(o < this.length()) {
            newIndex[o] = o;
            scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, newDefault);
            ++o;
         }

         SparseArray rv = new SparseArray(newIndex, newData, o, this.length(), newDefault);
         rv.compact();
         var10000 = rv;
      }

      return var10000;
   }

   public SparseArray filter(final Function1 f) {
      return this.filter$mcJ$sp(f);
   }

   public SparseArray filter$mcJ$sp(final Function1 f) {
      int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
      long[] newData = (long[])ArrayUtil$.MODULE$.copyOf(this.data(), this.breeze$collection$mutable$SparseArray$$used());
      int i = 0;

      int o;
      for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (f.apply$mcZJ$sp(this.data()[i])) {
            newIndex[o] = this.index()[i] - (i - o);
            newData[o] = this.data()[i];
            ++o;
         }
      }

      SparseArray$mcJ$sp var10000;
      if (f.apply$mcZJ$sp(this.default())) {
         int newLength = this.length() - (i - o);
         var10000 = new SparseArray$mcJ$sp(newIndex, newData, o, newLength, this.default());
      } else {
         var10000 = new SparseArray$mcJ$sp(scala.Array..MODULE$.range(0, o), (long[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.genericArrayOps(newData), o), o, o, this.default());
      }

      return var10000;
   }

   public final long valueAt(final int i) {
      return this.valueAt$mcJ$sp(i);
   }

   public final long valueAt$mcJ$sp(final int i) {
      return this.data()[i];
   }

   public final void update(final int i, final long value) {
      this.update$mcJ$sp(i, value);
   }

   public final void update$mcJ$sp(final int i, final long value) {
      int offset = this.findOffset(i);
      if (offset >= 0) {
         this.data()[offset] = value;
      } else if (value != this.default()) {
         int insertPos = ~offset;
         this.breeze$collection$mutable$SparseArray$$used_$eq(this.breeze$collection$mutable$SparseArray$$used() + 1);
         if (this.breeze$collection$mutable$SparseArray$$used() > this.data().length) {
            int newLength = this.data().length == 0 ? 4 : (this.data().length < 1024 ? this.data().length * 2 : (this.data().length < 2048 ? this.data().length + 1024 : (this.data().length < 4096 ? this.data().length + 2048 : (this.data().length < 8192 ? this.data().length + 4096 : (this.data().length < 16384 ? this.data().length + 8192 : this.data().length + 16384)))));
            int[] newIndex = Arrays.copyOf(this.index(), newLength);
            long[] newData = (long[])ArrayUtil$.MODULE$.copyOf(this.data(), newLength);
            System.arraycopy(this.index(), insertPos, newIndex, insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
            System.arraycopy(this.data(), insertPos, newData, insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
            this.index_$eq(newIndex);
            this.data_$eq(newData);
         } else if (this.breeze$collection$mutable$SparseArray$$used() - insertPos > 1) {
            System.arraycopy(this.index(), insertPos, this.index(), insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
            System.arraycopy(this.data(), insertPos, this.data(), insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
         }

         this.index()[insertPos] = i;
         this.data()[insertPos] = value;
      }

   }

   public void use(final int[] index, final long[] data, final int used) {
      this.use$mcJ$sp(index, data, used);
   }

   public void use$mcJ$sp(final int[] index, final long[] data, final int used) {
      this.index_$eq(index);
      this.data_$eq(data);
      this.breeze$collection$mutable$SparseArray$$used_$eq(used);
   }

   public SparseArray concatenate(final SparseArray that, final ClassTag man) {
      return this.concatenate$mcJ$sp(that, man);
   }

   public SparseArray concatenate$mcJ$sp(final SparseArray that, final ClassTag man) {
      long left$macro$1 = this.default();
      long right$macro$2 = that.default$mcJ$sp();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(87)).append("requirement failed: default values should be equal: ").append("this.default == that.default (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         return new SparseArray$mcJ$sp((int[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(this.index()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(that.index()), 0, that.breeze$collection$mutable$SparseArray$$used())), (JFunction1.mcII.sp)(x$1) -> x$1 + this.size(), scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.Int())), scala.reflect.ClassTag..MODULE$.Int()), (long[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(this.data()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(that.data$mcJ$sp()), 0, that.breeze$collection$mutable$SparseArray$$used()), man)), man), this.breeze$collection$mutable$SparseArray$$used() + that.breeze$collection$mutable$SparseArray$$used(), this.size() + that.size(), this.default());
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseArray$mcJ$sp(final int[] index, final long[] data$mcJ$sp, final int used, final int size, final long default$mcJ$sp) {
      this.data$mcJ$sp = data$mcJ$sp;
      this.default$mcJ$sp = default$mcJ$sp;
      this.used = used;
      super(index, (Object)null, used, size, (Object)null);
   }

   public SparseArray$mcJ$sp(final int size, final long default$mcJ$sp, final ClassTag manElem) {
      this((int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (long[])scala.Array..MODULE$.empty(manElem), 0, size, default$mcJ$sp);
   }

   public SparseArray$mcJ$sp(final int size, final ClassTag manElem, final Zero zero$mcJ$sp) {
      this(size, BoxesRunTime.unboxToLong(ConfigurableDefault$.MODULE$.default().value(zero$mcJ$sp)), manElem);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
