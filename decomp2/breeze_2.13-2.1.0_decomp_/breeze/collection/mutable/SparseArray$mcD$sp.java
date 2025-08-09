package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage$mcD$sp;
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

public final class SparseArray$mcD$sp extends SparseArray implements Storage$mcD$sp {
   private static final long serialVersionUID = 1L;
   public double[] data$mcD$sp;
   public final double default$mcD$sp;
   private final int used;

   public double[] data$mcD$sp() {
      return this.data$mcD$sp;
   }

   public double[] data() {
      return this.data$mcD$sp();
   }

   public void data$mcD$sp_$eq(final double[] x$1) {
      this.data$mcD$sp = x$1;
   }

   public void data_$eq(final double[] x$1) {
      this.data$mcD$sp_$eq(x$1);
   }

   public double default$mcD$sp() {
      return this.default$mcD$sp;
   }

   public double default() {
      return this.default$mcD$sp();
   }

   public final double apply(final int i) {
      return this.apply$mcD$sp(i);
   }

   public final double apply$mcD$sp(final int i) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : this.default();
   }

   public double getOrElse(final int i, final Function0 value) {
      return this.getOrElse$mcD$sp(i, value);
   }

   public double getOrElse$mcD$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      return offset >= 0 ? this.data()[offset] : value.apply$mcD$sp();
   }

   public double getOrElseUpdate(final int i, final Function0 value) {
      return this.getOrElseUpdate$mcD$sp(i, value);
   }

   public double getOrElseUpdate$mcD$sp(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      double var10000;
      if (offset >= 0) {
         var10000 = this.data()[offset];
      } else {
         double v = value.apply$mcD$sp();
         this.update$mcD$sp(i, v);
         var10000 = v;
      }

      return var10000;
   }

   public SparseArray map(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map$mcD$sp(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcD$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      Object newZero = ((Zero).MODULE$.implicitly(evidence$2)).zero();
      SparseArray var10000;
      if (this.breeze$collection$mutable$SparseArray$$used() <= this.length() && BoxesRunTime.equals(f.apply(BoxesRunTime.boxToDouble(this.default())), newZero)) {
         int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
         Object newData = evidence$1.newArray(this.breeze$collection$mutable$SparseArray$$used());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            newIndex[o] = this.index()[i];
            Object newValue = f.apply(BoxesRunTime.boxToDouble(this.data()[i]));
            if (!BoxesRunTime.equals(newValue, newZero)) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, newValue);
               ++o;
            }
         }

         var10000 = new SparseArray(newIndex, newData, o, this.length(), newZero);
      } else {
         Object newDefault = f.apply(BoxesRunTime.boxToDouble(this.default()));
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
            scala.runtime.ScalaRunTime..MODULE$.array_update(newData, o, f.apply(BoxesRunTime.boxToDouble(this.data()[i])));
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
      return this.filter$mcD$sp(f);
   }

   public SparseArray filter$mcD$sp(final Function1 f) {
      int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
      double[] newData = (double[])ArrayUtil$.MODULE$.copyOf(this.data(), this.breeze$collection$mutable$SparseArray$$used());
      int i = 0;

      int o;
      for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (f.apply$mcZD$sp(this.data()[i])) {
            newIndex[o] = this.index()[i] - (i - o);
            newData[o] = this.data()[i];
            ++o;
         }
      }

      SparseArray$mcD$sp var10000;
      if (f.apply$mcZD$sp(this.default())) {
         int newLength = this.length() - (i - o);
         var10000 = new SparseArray$mcD$sp(newIndex, newData, o, newLength, this.default());
      } else {
         var10000 = new SparseArray$mcD$sp(scala.Array..MODULE$.range(0, o), (double[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.genericArrayOps(newData), o), o, o, this.default());
      }

      return var10000;
   }

   public final double valueAt(final int i) {
      return this.valueAt$mcD$sp(i);
   }

   public final double valueAt$mcD$sp(final int i) {
      return this.data()[i];
   }

   public final void update(final int i, final double value) {
      this.update$mcD$sp(i, value);
   }

   public final void update$mcD$sp(final int i, final double value) {
      int offset = this.findOffset(i);
      if (offset >= 0) {
         this.data()[offset] = value;
      } else if (value != this.default()) {
         int insertPos = ~offset;
         this.breeze$collection$mutable$SparseArray$$used_$eq(this.breeze$collection$mutable$SparseArray$$used() + 1);
         if (this.breeze$collection$mutable$SparseArray$$used() > this.data().length) {
            int newLength = this.data().length == 0 ? 4 : (this.data().length < 1024 ? this.data().length * 2 : (this.data().length < 2048 ? this.data().length + 1024 : (this.data().length < 4096 ? this.data().length + 2048 : (this.data().length < 8192 ? this.data().length + 4096 : (this.data().length < 16384 ? this.data().length + 8192 : this.data().length + 16384)))));
            int[] newIndex = Arrays.copyOf(this.index(), newLength);
            double[] newData = (double[])ArrayUtil$.MODULE$.copyOf(this.data(), newLength);
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

   public void use(final int[] index, final double[] data, final int used) {
      this.use$mcD$sp(index, data, used);
   }

   public void use$mcD$sp(final int[] index, final double[] data, final int used) {
      this.index_$eq(index);
      this.data_$eq(data);
      this.breeze$collection$mutable$SparseArray$$used_$eq(used);
   }

   public SparseArray concatenate(final SparseArray that, final ClassTag man) {
      return this.concatenate$mcD$sp(that, man);
   }

   public SparseArray concatenate$mcD$sp(final SparseArray that, final ClassTag man) {
      double left$macro$1 = this.default();
      double right$macro$2 = that.default$mcD$sp();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(87)).append("requirement failed: default values should be equal: ").append("this.default == that.default (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         return new SparseArray$mcD$sp((int[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(this.index()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(that.index()), 0, that.breeze$collection$mutable$SparseArray$$used())), (JFunction1.mcII.sp)(x$1) -> x$1 + this.size(), scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.Int())), scala.reflect.ClassTag..MODULE$.Int()), (double[])scala.collection.ArrayOps..MODULE$.toArray$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(this.data()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(that.data$mcD$sp()), 0, that.breeze$collection$mutable$SparseArray$$used()), man)), man), this.breeze$collection$mutable$SparseArray$$used() + that.breeze$collection$mutable$SparseArray$$used(), this.size() + that.size(), this.default());
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseArray$mcD$sp(final int[] index, final double[] data$mcD$sp, final int used, final int size, final double default$mcD$sp) {
      this.data$mcD$sp = data$mcD$sp;
      this.default$mcD$sp = default$mcD$sp;
      this.used = used;
      super(index, (Object)null, used, size, (Object)null);
   }

   public SparseArray$mcD$sp(final int size, final double default$mcD$sp, final ClassTag manElem) {
      this((int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (double[])scala.Array..MODULE$.empty(manElem), 0, size, default$mcD$sp);
   }

   public SparseArray$mcD$sp(final int size, final ClassTag manElem, final Zero zero$mcD$sp) {
      this(size, BoxesRunTime.unboxToDouble(ConfigurableDefault$.MODULE$.default().value(zero$mcD$sp)), manElem);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
