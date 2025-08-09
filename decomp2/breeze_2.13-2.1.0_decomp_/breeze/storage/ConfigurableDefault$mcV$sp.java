package breeze.storage;

import java.util.Arrays;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public interface ConfigurableDefault$mcV$sp extends ConfigurableDefault {
   // $FF: synthetic method
   static void fillArray$(final ConfigurableDefault$mcV$sp $this, final BoxedUnit[] arr, final BoxedUnit v) {
      $this.fillArray(arr, v);
   }

   default void fillArray(final BoxedUnit[] arr, final BoxedUnit v) {
      this.fillArray$mcV$sp(arr, v);
   }

   // $FF: synthetic method
   static void fillArray$mcV$sp$(final ConfigurableDefault$mcV$sp $this, final BoxedUnit[] arr, final BoxedUnit v) {
      $this.fillArray$mcV$sp(arr, v);
   }

   default void fillArray$mcV$sp(final BoxedUnit[] arr, final BoxedUnit v) {
      if (arr instanceof int[]) {
         Arrays.fill((int[])arr, BoxesRunTime.unboxToInt(v));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else if (arr instanceof long[]) {
         Arrays.fill((long[])arr, BoxesRunTime.unboxToLong(v));
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else if (arr instanceof short[]) {
         Arrays.fill((short[])arr, BoxesRunTime.unboxToShort(v));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (arr instanceof double[]) {
         Arrays.fill((double[])arr, BoxesRunTime.unboxToDouble(v));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (arr instanceof float[]) {
         Arrays.fill((float[])arr, BoxesRunTime.unboxToFloat(v));
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (arr instanceof char[]) {
         Arrays.fill((char[])arr, BoxesRunTime.unboxToChar(v));
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (arr instanceof byte[]) {
         Arrays.fill((byte[])arr, BoxesRunTime.unboxToByte(v));
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else {
         if (!.MODULE$.isArray(arr, 1)) {
            throw new RuntimeException("shouldn't be here!");
         }

         Arrays.fill((Object[])arr, v);
         BoxedUnit var11 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   static BoxedUnit[] makeArray$(final ConfigurableDefault$mcV$sp $this, final int size, final Zero zero, final ClassTag man) {
      return $this.makeArray(size, zero, man);
   }

   default BoxedUnit[] makeArray(final int size, final Zero zero, final ClassTag man) {
      return this.makeArray$mcV$sp(size, zero, man);
   }

   // $FF: synthetic method
   static BoxedUnit[] makeArray$mcV$sp$(final ConfigurableDefault$mcV$sp $this, final int size, final Zero zero, final ClassTag man) {
      return $this.makeArray$mcV$sp(size, zero, man);
   }

   default BoxedUnit[] makeArray$mcV$sp(final int size, final Zero zero, final ClassTag man) {
      BoxedUnit[] arr = (BoxedUnit[])man.newArray(size);
      this.value$mcV$sp(zero);
      this.fillArray$mcV$sp(arr, BoxedUnit.UNIT);
      return arr;
   }

   // $FF: synthetic method
   static ConfigurableDefault map$(final ConfigurableDefault$mcV$sp $this, final Function1 f, final Zero zero) {
      return $this.map(f, zero);
   }

   default ConfigurableDefault map(final Function1 f, final Zero zero) {
      return this.map$mcV$sp(f, zero);
   }

   // $FF: synthetic method
   static ConfigurableDefault map$mcV$sp$(final ConfigurableDefault$mcV$sp $this, final Function1 f, final Zero zero) {
      return $this.map$mcV$sp(f, zero);
   }

   default ConfigurableDefault map$mcV$sp(final Function1 f, final Zero zero) {
      return new ConfigurableDefault(f, zero) {
         // $FF: synthetic field
         private final ConfigurableDefault$mcV$sp $outer;
         private final Function1 f$9;
         private final Zero zero$9;

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
            Function1 var10000 = this.f$9;
            this.$outer.value$mcV$sp(this.zero$9);
            return var10000.apply(BoxedUnit.UNIT);
         }

         public {
            if (ConfigurableDefault$mcV$sp.this == null) {
               throw null;
            } else {
               this.$outer = ConfigurableDefault$mcV$sp.this;
               this.f$9 = f$9;
               this.zero$9 = zero$9;
               ConfigurableDefault.$init$(this);
            }
         }
      };
   }
}
