package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcI$sp;
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

public final class SparseArray$mcI$sp extends SparseArray implements Storage$mcI$sp {
   private static final long serialVersionUID = 1L;
   public int[] data$mcI$sp;
   public final int default$mcI$sp;
   private final int used;

   public int[] data$mcI$sp() {
      return this.data$mcI$sp;
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public void data$mcI$sp_$eq(final int[] x$1) {
      this.data$mcI$sp = x$1;
   }

   public void data_$eq(final int[] x$1) {
      this.data$mcI$sp_$eq(x$1);
   }

   public int default$mcI$sp() {
      return this.default$mcI$sp;
   }

   public int default() {
      return this.default$mcI$sp();
   }

   public final int apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public final int apply$mcI$sp(final int i) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : this.default();
   }

   public int getOrElse(final int i, final Function0 value) {
      return this.getOrElse$mcI$sp(i, value);
   }

   public int getOrElse$mcI$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : value.apply$mcI$sp();
   }

   public int getOrElseUpdate(final int i, final Function0 value) {
      return this.getOrElseUpdate$mcI$sp(i, value);
   }

   public int getOrElseUpdate$mcI$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      int var10000;
      if (offset >= 0) {
         var10000 = this.data()[offset];
      } else {
         int v = value.apply$mcI$sp();
         this.update$mcI$sp(i, v);
         var10000 = v;
      }

      return var10000;
   }

   public SparseArray map(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map$mcI$sp(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcI$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      Object newZero = ((Zero).MODULE$.implicitly(evidence$2)).zero();
      SparseArray var10000;
      if (this.breeze$collection$mutable$SparseArray$$used() <= this.length() && BoxesRunTime.equals(f.apply(BoxesRunTime.boxToInteger(this.default())), newZero)) {
         int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
         Object newData = evidence$1.newArray(this.breeze$collection$mutable$SparseArray$$used());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            newIndex[o] = this.index()[i];
            Object newValue = f.apply(BoxesRunTime.boxToInteger(this.data()[i]));
            if (!BoxesRunTime.equals(newValue, newZero)) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, newValue);
               ++o;
            }
         }

         var10000 = new SparseArray(newIndex, newData, o, this.length(), newZero);
      } else {
         Object newDefault = f.apply(BoxesRunTime.boxToInteger(this.default()));
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
            scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, f.apply(BoxesRunTime.boxToInteger(this.data()[i])));
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
      return this.filter$mcI$sp(f);
   }

   public SparseArray filter$mcI$sp(final Function1 f) {
      int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
      int[] newData = (int[])ArrayUtil$.MODULE$.copyOf(this.data(), this.breeze$collection$mutable$SparseArray$$used());
      int i = 0;

      int o;
      for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (f.apply$mcZI$sp(this.data()[i])) {
            newIndex[o] = this.index()[i] - (i - o);
            newData[o] = this.data()[i];
            ++o;
         }
      }

      SparseArray$mcI$sp var10000;
      if (f.apply$mcZI$sp(this.default())) {
         int newLength = this.length() - (i - o);
         var10000 = new SparseArray$mcI$sp(newIndex, newData, o, newLength, this.default());
      } else {
         var10000 = new SparseArray$mcI$sp(scala.Array..MODULE$.range(0, o), (int[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.genericArrayOps(newData), o), o, o, this.default());
      }

      return var10000;
   }

   public final int valueAt(final int i) {
      return this.valueAt$mcI$sp(i);
   }

   public final int valueAt$mcI$sp(final int i) {
      return this.data()[i];
   }

   public final void update(final int i, final int value) {
      this.update$mcI$sp(i, value);
   }

   public final void update$mcI$sp(final int i, final int value) {
      int offset = this.findOffset(i);
      if (offset >= 0) {
         this.data()[offset] = value;
      } else if (value != this.default()) {
         int insertPos = ~offset;
         this.breeze$collection$mutable$SparseArray$$used_$eq(this.breeze$collection$mutable$SparseArray$$used() + 1);
         if (this.breeze$collection$mutable$SparseArray$$used() > this.data().length) {
            int newLength = this.data().length == 0 ? 4 : (this.data().length < 1024 ? this.data().length * 2 : (this.data().length < 2048 ? this.data().length + 1024 : (this.data().length < 4096 ? this.data().length + 2048 : (this.data().length < 8192 ? this.data().length + 4096 : (this.data().length < 16384 ? this.data().length + 8192 : this.data().length + 16384)))));
            int[] newIndex = Arrays.copyOf(this.index(), newLength);
            int[] newData = (int[])ArrayUtil$.MODULE$.copyOf(this.data(), newLength);
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

   public void use(final int[] index, final int[] data, final int used) {
      this.use$mcI$sp(index, data, used);
   }

   public void use$mcI$sp(final int[] index, final int[] data, final int used) {
      this.index_$eq(index);
      this.data_$eq(data);
      this.breeze$collection$mutable$SparseArray$$used_$eq(used);
   }

   public SparseArray concatenate(final SparseArray that, final ClassTag man) {
      return this.concatenate$mcI$sp(that, man);
   }

   public SparseArray concatenate$mcI$sp(final SparseArray that, final ClassTag man) {
      int left$macro$1 = this.default();
      int right$macro$2 = that.default$mcI$sp();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(87)).append("requirement failed: default values should be equal: ").append("this.default == that.default (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         return new SparseArray$mcI$sp((int[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(this.index()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(that.index()), 0, that.breeze$collection$mutable$SparseArray$$used())), (JFunction1.mcII.sp)(x$1) -> x$1 + this.size(), scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.Int())), scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(this.data()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(that.data$mcI$sp()), 0, that.breeze$collection$mutable$SparseArray$$used()), man)), man), this.breeze$collection$mutable$SparseArray$$used() + that.breeze$collection$mutable$SparseArray$$used(), this.size() + that.size(), this.default());
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseArray$mcI$sp(final int[] index, final int[] data$mcI$sp, final int used, final int size, final int default$mcI$sp) {
      this.data$mcI$sp = data$mcI$sp;
      this.default$mcI$sp = default$mcI$sp;
      this.used = used;
      super(index, (Object)null, used, size, (Object)null);
   }

   public SparseArray$mcI$sp(final int size, final int default$mcI$sp, final ClassTag manElem) {
      this((int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.Array..MODULE$.empty(manElem), 0, size, default$mcI$sp);
   }

   public SparseArray$mcI$sp(final int size, final ClassTag manElem, final Zero zero$mcI$sp) {
      this(size, BoxesRunTime.unboxToInt(ConfigurableDefault$.MODULE$.default().value(zero$mcI$sp)), manElem);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
