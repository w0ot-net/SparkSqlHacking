package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcF$sp;
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

public final class SparseArray$mcF$sp extends SparseArray implements Storage$mcF$sp {
   private static final long serialVersionUID = 1L;
   public float[] data$mcF$sp;
   public final float default$mcF$sp;
   private final int used;

   public float[] data$mcF$sp() {
      return this.data$mcF$sp;
   }

   public float[] data() {
      return this.data$mcF$sp();
   }

   public void data$mcF$sp_$eq(final float[] x$1) {
      this.data$mcF$sp = x$1;
   }

   public void data_$eq(final float[] x$1) {
      this.data$mcF$sp_$eq(x$1);
   }

   public float default$mcF$sp() {
      return this.default$mcF$sp;
   }

   public float default() {
      return this.default$mcF$sp();
   }

   public final float apply(final int i) {
      return this.apply$mcF$sp(i);
   }

   public final float apply$mcF$sp(final int i) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : this.default();
   }

   public float getOrElse(final int i, final Function0 value) {
      return this.getOrElse$mcF$sp(i, value);
   }

   public float getOrElse$mcF$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : value.apply$mcF$sp();
   }

   public float getOrElseUpdate(final int i, final Function0 value) {
      return this.getOrElseUpdate$mcF$sp(i, value);
   }

   public float getOrElseUpdate$mcF$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      float var10000;
      if (offset >= 0) {
         var10000 = this.data()[offset];
      } else {
         float v = value.apply$mcF$sp();
         this.update$mcF$sp(i, v);
         var10000 = v;
      }

      return var10000;
   }

   public SparseArray map(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map$mcF$sp(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcF$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      Object newZero = ((Zero).MODULE$.implicitly(evidence$2)).zero();
      SparseArray var10000;
      if (this.breeze$collection$mutable$SparseArray$$used() <= this.length() && BoxesRunTime.equals(f.apply(BoxesRunTime.boxToFloat(this.default())), newZero)) {
         int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
         Object newData = evidence$1.newArray(this.breeze$collection$mutable$SparseArray$$used());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            newIndex[o] = this.index()[i];
            Object newValue = f.apply(BoxesRunTime.boxToFloat(this.data()[i]));
            if (!BoxesRunTime.equals(newValue, newZero)) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, newValue);
               ++o;
            }
         }

         var10000 = new SparseArray(newIndex, newData, o, this.length(), newZero);
      } else {
         Object newDefault = f.apply(BoxesRunTime.boxToFloat(this.default()));
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
            scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, f.apply(BoxesRunTime.boxToFloat(this.data()[i])));
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
      return this.filter$mcF$sp(f);
   }

   public SparseArray filter$mcF$sp(final Function1 f) {
      int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
      float[] newData = (float[])ArrayUtil$.MODULE$.copyOf(this.data(), this.breeze$collection$mutable$SparseArray$$used());
      int i = 0;

      int o;
      for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (f.apply$mcZF$sp(this.data()[i])) {
            newIndex[o] = this.index()[i] - (i - o);
            newData[o] = this.data()[i];
            ++o;
         }
      }

      SparseArray$mcF$sp var10000;
      if (f.apply$mcZF$sp(this.default())) {
         int newLength = this.length() - (i - o);
         var10000 = new SparseArray$mcF$sp(newIndex, newData, o, newLength, this.default());
      } else {
         var10000 = new SparseArray$mcF$sp(scala.Array..MODULE$.range(0, o), (float[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.genericArrayOps(newData), o), o, o, this.default());
      }

      return var10000;
   }

   public final float valueAt(final int i) {
      return this.valueAt$mcF$sp(i);
   }

   public final float valueAt$mcF$sp(final int i) {
      return this.data()[i];
   }

   public final void update(final int i, final float value) {
      this.update$mcF$sp(i, value);
   }

   public final void update$mcF$sp(final int i, final float value) {
      int offset = this.findOffset(i);
      if (offset >= 0) {
         this.data()[offset] = value;
      } else if (value != this.default()) {
         int insertPos = ~offset;
         this.breeze$collection$mutable$SparseArray$$used_$eq(this.breeze$collection$mutable$SparseArray$$used() + 1);
         if (this.breeze$collection$mutable$SparseArray$$used() > this.data().length) {
            int newLength = this.data().length == 0 ? 4 : (this.data().length < 1024 ? this.data().length * 2 : (this.data().length < 2048 ? this.data().length + 1024 : (this.data().length < 4096 ? this.data().length + 2048 : (this.data().length < 8192 ? this.data().length + 4096 : (this.data().length < 16384 ? this.data().length + 8192 : this.data().length + 16384)))));
            int[] newIndex = Arrays.copyOf(this.index(), newLength);
            float[] newData = (float[])ArrayUtil$.MODULE$.copyOf(this.data(), newLength);
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

   public void use(final int[] index, final float[] data, final int used) {
      this.use$mcF$sp(index, data, used);
   }

   public void use$mcF$sp(final int[] index, final float[] data, final int used) {
      this.index_$eq(index);
      this.data_$eq(data);
      this.breeze$collection$mutable$SparseArray$$used_$eq(used);
   }

   public SparseArray concatenate(final SparseArray that, final ClassTag man) {
      return this.concatenate$mcF$sp(that, man);
   }

   public SparseArray concatenate$mcF$sp(final SparseArray that, final ClassTag man) {
      float left$macro$1 = this.default();
      float right$macro$2 = that.default$mcF$sp();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(87)).append("requirement failed: default values should be equal: ").append("this.default == that.default (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         return new SparseArray$mcF$sp((int[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(this.index()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(that.index()), 0, that.breeze$collection$mutable$SparseArray$$used())), (JFunction1.mcII.sp)(x$1) -> x$1 + this.size(), scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.Int())), scala.reflect.ClassTag..MODULE$.Int()), (float[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(this.data()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(that.data$mcF$sp()), 0, that.breeze$collection$mutable$SparseArray$$used()), man)), man), this.breeze$collection$mutable$SparseArray$$used() + that.breeze$collection$mutable$SparseArray$$used(), this.size() + that.size(), this.default());
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseArray$mcF$sp(final int[] index, final float[] data$mcF$sp, final int used, final int size, final float default$mcF$sp) {
      this.data$mcF$sp = data$mcF$sp;
      this.default$mcF$sp = default$mcF$sp;
      this.used = used;
      super(index, (Object)null, used, size, (Object)null);
   }

   public SparseArray$mcF$sp(final int size, final float default$mcF$sp, final ClassTag manElem) {
      this((int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (float[])scala.Array..MODULE$.empty(manElem), 0, size, default$mcF$sp);
   }

   public SparseArray$mcF$sp(final int size, final ClassTag manElem, final Zero zero$mcF$sp) {
      this(size, BoxesRunTime.unboxToFloat(ConfigurableDefault$.MODULE$.default().value(zero$mcF$sp)), manElem);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
