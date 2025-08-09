package breeze.storage;

import java.util.Arrays;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public interface ConfigurableDefault$mcF$sp extends ConfigurableDefault {
   // $FF: synthetic method
   static void fillArray$(final ConfigurableDefault$mcF$sp $this, final float[] arr, final float v) {
      $this.fillArray(arr, v);
   }

   default void fillArray(final float[] arr, final float v) {
      this.fillArray$mcF$sp(arr, v);
   }

   // $FF: synthetic method
   static void fillArray$mcF$sp$(final ConfigurableDefault$mcF$sp $this, final float[] arr, final float v) {
      $this.fillArray$mcF$sp(arr, v);
   }

   default void fillArray$mcF$sp(final float[] arr, final float v) {
      if (arr instanceof int[]) {
         Arrays.fill((int[])arr, (int)v);
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else if (arr instanceof long[]) {
         Arrays.fill((long[])arr, (long)v);
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else if (arr instanceof short[]) {
         Arrays.fill((short[])arr, (short)((int)v));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (arr instanceof double[]) {
         Arrays.fill((double[])arr, (double)v);
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (arr instanceof float[]) {
         Arrays.fill(arr, v);
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (arr instanceof char[]) {
         Arrays.fill((char[])arr, (char)((int)v));
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (arr instanceof byte[]) {
         Arrays.fill((byte[])arr, (byte)((int)v));
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else {
         if (!.MODULE$.isArray(arr, 1)) {
            throw new RuntimeException("shouldn't be here!");
         }

         Arrays.fill((Object[])arr, BoxesRunTime.boxToFloat(v));
         BoxedUnit var11 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   static float[] makeArray$(final ConfigurableDefault$mcF$sp $this, final int size, final Zero zero, final ClassTag man) {
      return $this.makeArray(size, zero, man);
   }

   default float[] makeArray(final int size, final Zero zero, final ClassTag man) {
      return this.makeArray$mcF$sp(size, zero, man);
   }

   // $FF: synthetic method
   static float[] makeArray$mcF$sp$(final ConfigurableDefault$mcF$sp $this, final int size, final Zero zero, final ClassTag man) {
      return $this.makeArray$mcF$sp(size, zero, man);
   }

   default float[] makeArray$mcF$sp(final int size, final Zero zero, final ClassTag man) {
      float[] arr = (float[])man.newArray(size);
      this.fillArray$mcF$sp(arr, this.value$mcF$sp(zero));
      return arr;
   }

   // $FF: synthetic method
   static ConfigurableDefault map$(final ConfigurableDefault$mcF$sp $this, final Function1 f, final Zero zero) {
      return $this.map(f, zero);
   }

   default ConfigurableDefault map(final Function1 f, final Zero zero) {
      return this.map$mcF$sp(f, zero);
   }

   // $FF: synthetic method
   static ConfigurableDefault map$mcF$sp$(final ConfigurableDefault$mcF$sp $this, final Function1 f, final Zero zero) {
      return $this.map$mcF$sp(f, zero);
   }

   default ConfigurableDefault map$mcF$sp(final Function1 f, final Zero zero) {
      return new ConfigurableDefault(f, zero) {
         // $FF: synthetic field
         private final ConfigurableDefault$mcF$sp $outer;
         private final Function1 f$5;
         private final Zero zero$5;

         public boolean value$mcZ$sp(final Zero zero) {
            return ConfigurableDefault.value$mcZ$sp$(this, zero);
         }

         public byte value$mcB$sp(final Zero zero) {
            return ConfigurableDefault.value$mcB$sp$(this, zero);
         }

         public char value$mcC$sp(final Zero zero) {
            return ConfigurableDefault.value$mcC$sp$(this, zero);
         }

         public double value$mcD$sp(final Zero zero) {
            return ConfigurableDefault.value$mcD$sp$(this, zero);
         }

         public float value$mcF$sp(final Zero zero) {
            return ConfigurableDefault.value$mcF$sp$(this, zero);
         }

         public int value$mcI$sp(final Zero zero) {
            return ConfigurableDefault.value$mcI$sp$(this, zero);
         }

         public long value$mcJ$sp(final Zero zero) {
            return ConfigurableDefault.value$mcJ$sp$(this, zero);
         }

         public short value$mcS$sp(final Zero zero) {
            return ConfigurableDefault.value$mcS$sp$(this, zero);
         }

         public void value$mcV$sp(final Zero zero) {
            ConfigurableDefault.value$mcV$sp$(this, zero);
         }

         public void fillArray(final Object arr, final Object v) {
            ConfigurableDefault.fillArray$(this, arr, v);
         }

         public void fillArray$mcZ$sp(final boolean[] arr, final boolean v) {
            ConfigurableDefault.fillArray$mcZ$sp$(this, arr, v);
         }

         public void fillArray$mcB$sp(final byte[] arr, final byte v) {
            ConfigurableDefault.fillArray$mcB$sp$(this, arr, v);
         }

         public void fillArray$mcC$sp(final char[] arr, final char v) {
            ConfigurableDefault.fillArray$mcC$sp$(this, arr, v);
         }

         public void fillArray$mcD$sp(final double[] arr, final double v) {
            ConfigurableDefault.fillArray$mcD$sp$(this, arr, v);
         }

         public void fillArray$mcF$sp(final float[] arr, final float v) {
            ConfigurableDefault.fillArray$mcF$sp$(this, arr, v);
         }

         public void fillArray$mcI$sp(final int[] arr, final int v) {
            ConfigurableDefault.fillArray$mcI$sp$(this, arr, v);
         }

         public void fillArray$mcJ$sp(final long[] arr, final long v) {
            ConfigurableDefault.fillArray$mcJ$sp$(this, arr, v);
         }

         public void fillArray$mcS$sp(final short[] arr, final short v) {
            ConfigurableDefault.fillArray$mcS$sp$(this, arr, v);
         }

         public void fillArray$mcV$sp(final BoxedUnit[] arr, final BoxedUnit v) {
            ConfigurableDefault.fillArray$mcV$sp$(this, arr, v);
         }

         public Object makeArray(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$(this, size, zero, man);
         }

         public boolean[] makeArray$mcZ$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcZ$sp$(this, size, zero, man);
         }

         public byte[] makeArray$mcB$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcB$sp$(this, size, zero, man);
         }

         public char[] makeArray$mcC$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcC$sp$(this, size, zero, man);
         }

         public double[] makeArray$mcD$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcD$sp$(this, size, zero, man);
         }

         public float[] makeArray$mcF$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcF$sp$(this, size, zero, man);
         }

         public int[] makeArray$mcI$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcI$sp$(this, size, zero, man);
         }

         public long[] makeArray$mcJ$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcJ$sp$(this, size, zero, man);
         }

         public short[] makeArray$mcS$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcS$sp$(this, size, zero, man);
         }

         public BoxedUnit[] makeArray$mcV$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.makeArray$mcV$sp$(this, size, zero, man);
         }

         public ConfigurableDefault map(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$(this, f, zero);
         }

         public ConfigurableDefault map$mcZ$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcZ$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcB$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcB$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcC$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcC$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcD$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcD$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcF$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcF$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcI$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcI$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcJ$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcJ$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcS$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcS$sp$(this, f, zero);
         }

         public ConfigurableDefault map$mcV$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.map$mcV$sp$(this, f, zero);
         }

         public Object value(final Zero default) {
            return this.f$5.apply(BoxesRunTime.boxToFloat(this.$outer.value$mcF$sp(this.zero$5)));
         }

         public {
            if (ConfigurableDefault$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = ConfigurableDefault$mcF$sp.this;
               this.f$5 = f$5;
               this.zero$5 = zero$5;
               ConfigurableDefault.$init$(this);
            }
         }
      };
   }
}
