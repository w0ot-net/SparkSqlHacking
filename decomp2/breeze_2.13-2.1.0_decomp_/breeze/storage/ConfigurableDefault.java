package breeze.storage;

import java.io.Serializable;
import java.util.Arrays;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dea\u0002\u001c8!\u0003\r\t\u0001\u0010\u0005\u0006!\u0002!\t!\u0015\u0005\u0006+\u00021\tA\u0016\u0005\u0006Y\u0002!\t!\u001c\u0005\u0006k\u0002!\tA\u001e\u0005\b\u0003\u001b\u0001A\u0011AA\b\u000f\u001d\tyc\u000eE\u0001\u0003c1aAN\u001c\t\u0002\u0005M\u0002bBA%\u000f\u0011\u0005\u00111\n\u0004\b\u0003\u001b:\u0001jNA(\u0011\u001d\tI%\u0003C\u0001\u0003?Ba!V\u0005\u0005\u0002\u0005\u0015\u0004\"CA7\u0013\u0005\u0005I\u0011AA8\u0011%\tI(CA\u0001\n\u0003\nY\bC\u0005\u0002\n&\t\t\u0011\"\u0001\u0002\f\"I\u0011QR\u0005\u0002\u0002\u0013\u0005\u0011q\u0012\u0005\n\u0003+K\u0011\u0011!C!\u0003/C\u0011\"!*\n\u0003\u0003%\t!a*\t\u0013\u0005E\u0016\"!A\u0005B\u0005M\u0006\"CA\\\u0013\u0005\u0005I\u0011IA]\u0011%\tY,CA\u0001\n\u0003\ni\fC\u0005\u0002@&\t\t\u0011\"\u0011\u0002B\u001eQ\u0011qZ\u0004\u0002\u0002#Eq'!5\u0007\u0015\u00055s!!A\t\u0012]\n\u0019\u000eC\u0004\u0002J]!\t!!6\t\u0013\u0005mv#!A\u0005F\u0005u\u0006\"CAl/\u0005\u0005I\u0011QAm\u0011%\t\u0019oFA\u0001\n\u0003\u000b)\u000fC\u0005\u0002t^\t\t\u0011\"\u0003\u0002v\u001a9\u0011Q`\u0004Io\u0005}\b\"\u0003;\u001e\u0005+\u0007I\u0011\u0001B\u0005\u0011)\u0011Y!\bB\tB\u0003%!Q\u0001\u0005\b\u0003\u0013jB\u0011\u0001B\u0007\u0011\u0019)V\u0004\"\u0001\u0003\u0014!I\u0011QN\u000f\u0002\u0002\u0013\u0005!\u0011\u0004\u0005\n\u0005Ki\u0012\u0013!C\u0001\u0005OA\u0011\"!\u001f\u001e\u0003\u0003%\t%a\u001f\t\u0013\u0005%U$!A\u0005\u0002\u0005-\u0005\"CAG;\u0005\u0005I\u0011\u0001B!\u0011%\t)*HA\u0001\n\u0003\n9\nC\u0005\u0002&v\t\t\u0011\"\u0001\u0003F!I\u0011\u0011W\u000f\u0002\u0002\u0013\u0005#\u0011\n\u0005\n\u0003ok\u0012\u0011!C!\u0003sC\u0011\"a/\u001e\u0003\u0003%\t%!0\t\u0013\u0005}V$!A\u0005B\t5sA\u0003B*\u000f\u0005\u0005\t\u0012C\u001c\u0003V\u0019Q\u0011Q`\u0004\u0002\u0002#EqGa\u0016\t\u000f\u0005%c\u0006\"\u0001\u0003Z!I\u00111\u0018\u0018\u0002\u0002\u0013\u0015\u0013Q\u0018\u0005\n\u0003/t\u0013\u0011!CA\u00057B\u0011\"a9/\u0003\u0003%\tIa\u001a\t\u0013\u0005Mh&!A\u0005\n\u0005U\bb\u0002B=\u000f\u0011\r!1\u0010\u0005\n\u0003g<\u0011\u0011!C\u0005\u0003k\u00141cQ8oM&<WO]1cY\u0016$UMZ1vYRT!\u0001O\u001d\u0002\u000fM$xN]1hK*\t!(\u0001\u0004ce\u0016,'0Z\u0002\u0001+\ti\u0014lE\u0002\u0001}\u0011\u0003\"a\u0010\"\u000e\u0003\u0001S\u0011!Q\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0007\u0002\u0013a!\u00118z%\u00164\u0007CA#N\u001d\t15J\u0004\u0002H\u00156\t\u0001J\u0003\u0002Jw\u00051AH]8pizJ\u0011!Q\u0005\u0003\u0019\u0002\u000bq\u0001]1dW\u0006<W-\u0003\u0002O\u001f\na1+\u001a:jC2L'0\u00192mK*\u0011A\nQ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0003\"aP*\n\u0005Q\u0003%\u0001B+oSR\fQA^1mk\u0016$\"a\u00164\u0011\u0005aKF\u0002\u0001\u0003\n5\u0002\u0001\u000b\u0011!AC\u0002m\u0013\u0011AV\t\u00039~\u0003\"aP/\n\u0005y\u0003%a\u0002(pi\"Lgn\u001a\t\u0003\u007f\u0001L!!\u0019!\u0003\u0007\u0005s\u0017\u0010\u000b\u0002ZGB\u0011q\bZ\u0005\u0003K\u0002\u00131b\u001d9fG&\fG.\u001b>fI\")qM\u0001a\u0002Q\u0006!!0\u001a:p!\rI'nV\u0007\u0002o%\u00111n\u000e\u0002\u00055\u0016\u0014x.A\u0005gS2d\u0017I\u001d:bsR\u0019!K\\:\t\u000b=\u001c\u0001\u0019\u00019\u0002\u0007\u0005\u0014(\u000fE\u0002@c^K!A\u001d!\u0003\u000b\u0005\u0013(/Y=\t\u000bQ\u001c\u0001\u0019A,\u0002\u0003Y\f\u0011\"\\1lK\u0006\u0013(/Y=\u0015\u0007]\f\u0019\u0001F\u0002qqfDQa\u001a\u0003A\u0004!DQA\u001f\u0003A\u0004m\f1!\\1o!\raxpV\u0007\u0002{*\u0011a\u0010Q\u0001\be\u00164G.Z2u\u0013\r\t\t! \u0002\t\u00072\f7o\u001d+bO\"9\u0011Q\u0001\u0003A\u0002\u0005\u001d\u0011\u0001B:ju\u0016\u00042aPA\u0005\u0013\r\tY\u0001\u0011\u0002\u0004\u0013:$\u0018aA7baV!\u0011\u0011CA\u0010)\u0011\t\u0019\"!\n\u0015\t\u0005U\u00111\u0005\n\u0006\u0003/q\u00141\u0004\u0004\u0007\u00033)\u0001!!\u0006\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\t%\u0004\u0011Q\u0004\t\u00041\u0006}AABA\u0011\u000b\t\u00071LA\u0001V\u0011\u00159W\u0001q\u0001i\u0011\u001d\t9#\u0002a\u0001\u0003S\t\u0011A\u001a\t\u0007\u007f\u0005-r+!\b\n\u0007\u00055\u0002IA\u0005Gk:\u001cG/[8oc\u0005\u00192i\u001c8gS\u001e,(/\u00192mK\u0012+g-Y;miB\u0011\u0011nB\n\u0007\u000fy\n)$a\u000f\u0011\u0007%\f9$C\u0002\u0002:]\u0012\u0001\u0005T8x!JLwN]5us\u000e{gNZ5hkJ\f'\r\\3J[Bd\u0017nY5ugB!\u0011QHA$\u001b\t\tyD\u0003\u0003\u0002B\u0005\r\u0013AA5p\u0015\t\t)%\u0001\u0003kCZ\f\u0017b\u0001(\u0002@\u00051A(\u001b8jiz\"\"!!\r\u00035\u0011+g-Y;mi\u000e{gNZ5hkJ\f'\r\\3EK\u001a\fW\u000f\u001c;\u0016\t\u0005E\u0013qK\n\b\u0013y\n\u0019&!\u0017E!\u0011I\u0007!!\u0016\u0011\u0007a\u000b9\u0006B\u0003[\u0013\t\u00071\fE\u0002@\u00037J1!!\u0018A\u0005\u001d\u0001&o\u001c3vGR$\"!!\u0019\u0011\u000b\u0005\r\u0014\"!\u0016\u000e\u0003\u001d!B!!\u0016\u0002h!9\u0011\u0011N\u0006A\u0004\u0005-\u0014a\u00023fM\u0006,H\u000e\u001e\t\u0005S*\f)&\u0001\u0003d_BLX\u0003BA9\u0003o\"\"!a\u001d\u0011\u000b\u0005\r\u0014\"!\u001e\u0011\u0007a\u000b9\bB\u0003[\u0019\t\u00071,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003{\u0002B!a \u0002\u00066\u0011\u0011\u0011\u0011\u0006\u0005\u0003\u0007\u000b\u0019%\u0001\u0003mC:<\u0017\u0002BAD\u0003\u0003\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0004\u00039\u0001(o\u001c3vGR,E.Z7f]R$2aXAI\u0011%\t\u0019jDA\u0001\u0002\u0004\t9!A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00033\u0003R!a'\u0002\"~k!!!(\u000b\u0007\u0005}\u0005)\u0001\u0006d_2dWm\u0019;j_:LA!a)\u0002\u001e\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI+a,\u0011\u0007}\nY+C\u0002\u0002.\u0002\u0013qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0014F\t\t\u00111\u0001`\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005u\u0014Q\u0017\u0005\n\u0003'\u0013\u0012\u0011!a\u0001\u0003\u000f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000f\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003{\na!Z9vC2\u001cH\u0003BAU\u0003\u0007D\u0001\"a%\u0016\u0003\u0003\u0005\ra\u0018\u0015\u0007\u0013\u0005\u001dW+!4\u0011\u0007}\nI-C\u0002\u0002L\u0002\u0013\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u001f\u0003\u0005\t!\u0004R3gCVdGoQ8oM&<WO]1cY\u0016$UMZ1vYR\u00042!a\u0019\u0018'\u00119b(a\u000f\u0015\u0005\u0005E\u0017!B1qa2LX\u0003BAn\u0003C$\"!!8\u0011\u000b\u0005\r\u0014\"a8\u0011\u0007a\u000b\t\u000fB\u0003[5\t\u00071,A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005\u001d\u0018\u0011\u001f\u000b\u0005\u0003S\u000bI\u000fC\u0005\u0002ln\t\t\u00111\u0001\u0002n\u0006\u0019\u0001\u0010\n\u0019\u0011\u000b\u0005\r\u0014\"a<\u0011\u0007a\u000b\t\u0010B\u0003[7\t\u00071,\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002xB!\u0011qPA}\u0013\u0011\tY0!!\u0003\r=\u0013'.Z2u\u000551\u0016\r\\;fI\u0012+g-Y;miV!!\u0011\u0001B\u0004'\u001dibHa\u0001\u0002Z\u0011\u0003B!\u001b\u0001\u0003\u0006A\u0019\u0001La\u0002\u0005\u000bik\"\u0019A.\u0016\u0005\t\u0015\u0011A\u0001<!)\u0011\u0011yA!\u0005\u0011\u000b\u0005\rTD!\u0002\t\rQ\u0004\u0003\u0019\u0001B\u0003)\u0011\u0011)A!\u0006\t\u000f\u0005%\u0014\u0005q\u0001\u0003\u0018A!\u0011N\u001bB\u0003+\u0011\u0011YB!\t\u0015\t\tu!1\u0005\t\u0006\u0003Gj\"q\u0004\t\u00041\n\u0005B!\u0002.#\u0005\u0004Y\u0006\u0002\u0003;#!\u0003\u0005\rAa\b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU!!\u0011\u0006B +\t\u0011YC\u000b\u0003\u0003\u0006\t52F\u0001B\u0018!\u0011\u0011\tDa\u000f\u000e\u0005\tM\"\u0002\u0002B\u001b\u0005o\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\te\u0002)\u0001\u0006b]:|G/\u0019;j_:LAA!\u0010\u00034\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bi\u001b#\u0019A.\u0015\u0007}\u0013\u0019\u0005C\u0005\u0002\u0014\u001a\n\t\u00111\u0001\u0002\bQ!\u0011\u0011\u0016B$\u0011!\t\u0019\nKA\u0001\u0002\u0004yF\u0003BA?\u0005\u0017B\u0011\"a%*\u0003\u0003\u0005\r!a\u0002\u0015\t\u0005%&q\n\u0005\t\u0003'c\u0013\u0011!a\u0001?\"2Q$a2V\u0003\u001b\fQBV1mk\u0016$G)\u001a4bk2$\bcAA2]M!aFPA\u001e)\t\u0011)&\u0006\u0003\u0003^\t\rD\u0003\u0002B0\u0005K\u0002R!a\u0019\u001e\u0005C\u00022\u0001\u0017B2\t\u0015Q\u0016G1\u0001\\\u0011\u0019!\u0018\u00071\u0001\u0003bU!!\u0011\u000eB:)\u0011\u0011YG!\u001e\u0011\u000b}\u0012iG!\u001d\n\u0007\t=\u0004I\u0001\u0004PaRLwN\u001c\t\u00041\nMD!\u0002.3\u0005\u0004Y\u0006\"CAve\u0005\u0005\t\u0019\u0001B<!\u0015\t\u0019'\bB9\u0003\u00151'o\\7W+\u0011\u0011iHa!\u0015\t\t}$Q\u0011\t\u0005S\u0002\u0011\t\tE\u0002Y\u0005\u0007#QA\u0017\u001bC\u0002mCa\u0001\u001e\u001bA\u0002\t\u0005\u0005"
)
public interface ConfigurableDefault extends Serializable {
   static ConfigurableDefault fromV(final Object v) {
      return ConfigurableDefault$.MODULE$.fromV(v);
   }

   static ConfigurableDefault default() {
      return ConfigurableDefault$.MODULE$.default();
   }

   Object value(final Zero zero);

   default void fillArray(final Object arr, final Object v) {
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

         Arrays.fill(arr, v);
         BoxedUnit var11 = BoxedUnit.UNIT;
      }

   }

   default Object makeArray(final int size, final Zero zero, final ClassTag man) {
      Object arr = man.newArray(size);
      this.fillArray(arr, this.value(zero));
      return arr;
   }

   default ConfigurableDefault map(final Function1 f, final Zero zero) {
      return new ConfigurableDefault(f, zero) {
         // $FF: synthetic field
         private final ConfigurableDefault $outer;
         private final Function1 f$1;
         private final Zero zero$1;

         public boolean value$mcZ$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcZ$sp(zero);
         }

         public byte value$mcB$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcB$sp(zero);
         }

         public char value$mcC$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcC$sp(zero);
         }

         public double value$mcD$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcD$sp(zero);
         }

         public float value$mcF$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcF$sp(zero);
         }

         public int value$mcI$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcI$sp(zero);
         }

         public long value$mcJ$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcJ$sp(zero);
         }

         public short value$mcS$sp(final Zero zero) {
            return ConfigurableDefault.super.value$mcS$sp(zero);
         }

         public void value$mcV$sp(final Zero zero) {
            ConfigurableDefault.super.value$mcV$sp(zero);
         }

         public void fillArray(final Object arr, final Object v) {
            ConfigurableDefault.super.fillArray(arr, v);
         }

         public void fillArray$mcZ$sp(final boolean[] arr, final boolean v) {
            ConfigurableDefault.super.fillArray$mcZ$sp(arr, v);
         }

         public void fillArray$mcB$sp(final byte[] arr, final byte v) {
            ConfigurableDefault.super.fillArray$mcB$sp(arr, v);
         }

         public void fillArray$mcC$sp(final char[] arr, final char v) {
            ConfigurableDefault.super.fillArray$mcC$sp(arr, v);
         }

         public void fillArray$mcD$sp(final double[] arr, final double v) {
            ConfigurableDefault.super.fillArray$mcD$sp(arr, v);
         }

         public void fillArray$mcF$sp(final float[] arr, final float v) {
            ConfigurableDefault.super.fillArray$mcF$sp(arr, v);
         }

         public void fillArray$mcI$sp(final int[] arr, final int v) {
            ConfigurableDefault.super.fillArray$mcI$sp(arr, v);
         }

         public void fillArray$mcJ$sp(final long[] arr, final long v) {
            ConfigurableDefault.super.fillArray$mcJ$sp(arr, v);
         }

         public void fillArray$mcS$sp(final short[] arr, final short v) {
            ConfigurableDefault.super.fillArray$mcS$sp(arr, v);
         }

         public void fillArray$mcV$sp(final BoxedUnit[] arr, final BoxedUnit v) {
            ConfigurableDefault.super.fillArray$mcV$sp(arr, v);
         }

         public Object makeArray(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray(size, zero, man);
         }

         public boolean[] makeArray$mcZ$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcZ$sp(size, zero, man);
         }

         public byte[] makeArray$mcB$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcB$sp(size, zero, man);
         }

         public char[] makeArray$mcC$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcC$sp(size, zero, man);
         }

         public double[] makeArray$mcD$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcD$sp(size, zero, man);
         }

         public float[] makeArray$mcF$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcF$sp(size, zero, man);
         }

         public int[] makeArray$mcI$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcI$sp(size, zero, man);
         }

         public long[] makeArray$mcJ$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcJ$sp(size, zero, man);
         }

         public short[] makeArray$mcS$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcS$sp(size, zero, man);
         }

         public BoxedUnit[] makeArray$mcV$sp(final int size, final Zero zero, final ClassTag man) {
            return ConfigurableDefault.super.makeArray$mcV$sp(size, zero, man);
         }

         public ConfigurableDefault map(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map(f, zero);
         }

         public ConfigurableDefault map$mcZ$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcZ$sp(f, zero);
         }

         public ConfigurableDefault map$mcB$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcB$sp(f, zero);
         }

         public ConfigurableDefault map$mcC$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcC$sp(f, zero);
         }

         public ConfigurableDefault map$mcD$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcD$sp(f, zero);
         }

         public ConfigurableDefault map$mcF$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcF$sp(f, zero);
         }

         public ConfigurableDefault map$mcI$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcI$sp(f, zero);
         }

         public ConfigurableDefault map$mcJ$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcJ$sp(f, zero);
         }

         public ConfigurableDefault map$mcS$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcS$sp(f, zero);
         }

         public ConfigurableDefault map$mcV$sp(final Function1 f, final Zero zero) {
            return ConfigurableDefault.super.map$mcV$sp(f, zero);
         }

         public Object value(final Zero default) {
            return this.f$1.apply(this.$outer.value(this.zero$1));
         }

         public {
            if (ConfigurableDefault.this == null) {
               throw null;
            } else {
               this.$outer = ConfigurableDefault.this;
               this.f$1 = f$1;
               this.zero$1 = zero$1;
               ConfigurableDefault.$init$(this);
            }
         }
      };
   }

   default boolean value$mcZ$sp(final Zero zero) {
      return BoxesRunTime.unboxToBoolean(this.value(zero));
   }

   default byte value$mcB$sp(final Zero zero) {
      return BoxesRunTime.unboxToByte(this.value(zero));
   }

   default char value$mcC$sp(final Zero zero) {
      return BoxesRunTime.unboxToChar(this.value(zero));
   }

   default double value$mcD$sp(final Zero zero) {
      return BoxesRunTime.unboxToDouble(this.value(zero));
   }

   default float value$mcF$sp(final Zero zero) {
      return BoxesRunTime.unboxToFloat(this.value(zero));
   }

   default int value$mcI$sp(final Zero zero) {
      return BoxesRunTime.unboxToInt(this.value(zero));
   }

   default long value$mcJ$sp(final Zero zero) {
      return BoxesRunTime.unboxToLong(this.value(zero));
   }

   default short value$mcS$sp(final Zero zero) {
      return BoxesRunTime.unboxToShort(this.value(zero));
   }

   default void value$mcV$sp(final Zero zero) {
      this.value(zero);
   }

   default void fillArray$mcZ$sp(final boolean[] arr, final boolean v) {
      this.fillArray(arr, BoxesRunTime.boxToBoolean(v));
   }

   default void fillArray$mcB$sp(final byte[] arr, final byte v) {
      this.fillArray(arr, BoxesRunTime.boxToByte(v));
   }

   default void fillArray$mcC$sp(final char[] arr, final char v) {
      this.fillArray(arr, BoxesRunTime.boxToCharacter(v));
   }

   default void fillArray$mcD$sp(final double[] arr, final double v) {
      this.fillArray(arr, BoxesRunTime.boxToDouble(v));
   }

   default void fillArray$mcF$sp(final float[] arr, final float v) {
      this.fillArray(arr, BoxesRunTime.boxToFloat(v));
   }

   default void fillArray$mcI$sp(final int[] arr, final int v) {
      this.fillArray(arr, BoxesRunTime.boxToInteger(v));
   }

   default void fillArray$mcJ$sp(final long[] arr, final long v) {
      this.fillArray(arr, BoxesRunTime.boxToLong(v));
   }

   default void fillArray$mcS$sp(final short[] arr, final short v) {
      this.fillArray(arr, BoxesRunTime.boxToShort(v));
   }

   default void fillArray$mcV$sp(final BoxedUnit[] arr, final BoxedUnit v) {
      this.fillArray(arr, v);
   }

   default boolean[] makeArray$mcZ$sp(final int size, final Zero zero, final ClassTag man) {
      return (boolean[])this.makeArray(size, zero, man);
   }

   default byte[] makeArray$mcB$sp(final int size, final Zero zero, final ClassTag man) {
      return (byte[])this.makeArray(size, zero, man);
   }

   default char[] makeArray$mcC$sp(final int size, final Zero zero, final ClassTag man) {
      return (char[])this.makeArray(size, zero, man);
   }

   default double[] makeArray$mcD$sp(final int size, final Zero zero, final ClassTag man) {
      return (double[])this.makeArray(size, zero, man);
   }

   default float[] makeArray$mcF$sp(final int size, final Zero zero, final ClassTag man) {
      return (float[])this.makeArray(size, zero, man);
   }

   default int[] makeArray$mcI$sp(final int size, final Zero zero, final ClassTag man) {
      return (int[])this.makeArray(size, zero, man);
   }

   default long[] makeArray$mcJ$sp(final int size, final Zero zero, final ClassTag man) {
      return (long[])this.makeArray(size, zero, man);
   }

   default short[] makeArray$mcS$sp(final int size, final Zero zero, final ClassTag man) {
      return (short[])this.makeArray(size, zero, man);
   }

   default BoxedUnit[] makeArray$mcV$sp(final int size, final Zero zero, final ClassTag man) {
      return (BoxedUnit[])this.makeArray(size, zero, man);
   }

   default ConfigurableDefault map$mcZ$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcB$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcC$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcD$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcF$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcI$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcJ$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcS$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   default ConfigurableDefault map$mcV$sp(final Function1 f, final Zero zero) {
      return this.map(f, zero);
   }

   static void $init$(final ConfigurableDefault $this) {
   }

   public static class DefaultConfigurableDefault implements ConfigurableDefault, Product {
      private static final long serialVersionUID = 1L;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean value$mcZ$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcZ$sp(zero);
      }

      public byte value$mcB$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcB$sp(zero);
      }

      public char value$mcC$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcC$sp(zero);
      }

      public double value$mcD$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcD$sp(zero);
      }

      public float value$mcF$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcF$sp(zero);
      }

      public int value$mcI$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcI$sp(zero);
      }

      public long value$mcJ$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcJ$sp(zero);
      }

      public short value$mcS$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcS$sp(zero);
      }

      public void value$mcV$sp(final Zero zero) {
         ConfigurableDefault.super.value$mcV$sp(zero);
      }

      public void fillArray(final Object arr, final Object v) {
         ConfigurableDefault.super.fillArray(arr, v);
      }

      public void fillArray$mcZ$sp(final boolean[] arr, final boolean v) {
         ConfigurableDefault.super.fillArray$mcZ$sp(arr, v);
      }

      public void fillArray$mcB$sp(final byte[] arr, final byte v) {
         ConfigurableDefault.super.fillArray$mcB$sp(arr, v);
      }

      public void fillArray$mcC$sp(final char[] arr, final char v) {
         ConfigurableDefault.super.fillArray$mcC$sp(arr, v);
      }

      public void fillArray$mcD$sp(final double[] arr, final double v) {
         ConfigurableDefault.super.fillArray$mcD$sp(arr, v);
      }

      public void fillArray$mcF$sp(final float[] arr, final float v) {
         ConfigurableDefault.super.fillArray$mcF$sp(arr, v);
      }

      public void fillArray$mcI$sp(final int[] arr, final int v) {
         ConfigurableDefault.super.fillArray$mcI$sp(arr, v);
      }

      public void fillArray$mcJ$sp(final long[] arr, final long v) {
         ConfigurableDefault.super.fillArray$mcJ$sp(arr, v);
      }

      public void fillArray$mcS$sp(final short[] arr, final short v) {
         ConfigurableDefault.super.fillArray$mcS$sp(arr, v);
      }

      public void fillArray$mcV$sp(final BoxedUnit[] arr, final BoxedUnit v) {
         ConfigurableDefault.super.fillArray$mcV$sp(arr, v);
      }

      public Object makeArray(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray(size, zero, man);
      }

      public boolean[] makeArray$mcZ$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcZ$sp(size, zero, man);
      }

      public byte[] makeArray$mcB$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcB$sp(size, zero, man);
      }

      public char[] makeArray$mcC$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcC$sp(size, zero, man);
      }

      public double[] makeArray$mcD$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcD$sp(size, zero, man);
      }

      public float[] makeArray$mcF$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcF$sp(size, zero, man);
      }

      public int[] makeArray$mcI$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcI$sp(size, zero, man);
      }

      public long[] makeArray$mcJ$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcJ$sp(size, zero, man);
      }

      public short[] makeArray$mcS$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcS$sp(size, zero, man);
      }

      public BoxedUnit[] makeArray$mcV$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcV$sp(size, zero, man);
      }

      public ConfigurableDefault map(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map(f, zero);
      }

      public ConfigurableDefault map$mcZ$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcZ$sp(f, zero);
      }

      public ConfigurableDefault map$mcB$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcB$sp(f, zero);
      }

      public ConfigurableDefault map$mcC$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcC$sp(f, zero);
      }

      public ConfigurableDefault map$mcD$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcD$sp(f, zero);
      }

      public ConfigurableDefault map$mcF$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcF$sp(f, zero);
      }

      public ConfigurableDefault map$mcI$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcI$sp(f, zero);
      }

      public ConfigurableDefault map$mcJ$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcJ$sp(f, zero);
      }

      public ConfigurableDefault map$mcS$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcS$sp(f, zero);
      }

      public ConfigurableDefault map$mcV$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcV$sp(f, zero);
      }

      public Object value(final Zero default) {
         return default.zero();
      }

      public DefaultConfigurableDefault copy() {
         return new DefaultConfigurableDefault();
      }

      public String productPrefix() {
         return "DefaultConfigurableDefault";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof DefaultConfigurableDefault;
      }

      public String productElementName(final int x$1) {
         String var2 = (String)Statics.ioobe(x$1);
         return var2;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var2;
         if (x$1 instanceof DefaultConfigurableDefault) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2 && ((DefaultConfigurableDefault)x$1).canEqual(this);
      }

      public DefaultConfigurableDefault() {
         ConfigurableDefault.$init$(this);
         Product.$init$(this);
      }
   }

   public static class DefaultConfigurableDefault$ implements Serializable {
      public static final DefaultConfigurableDefault$ MODULE$ = new DefaultConfigurableDefault$();

      public final String toString() {
         return "DefaultConfigurableDefault";
      }

      public DefaultConfigurableDefault apply() {
         return new DefaultConfigurableDefault();
      }

      public boolean unapply(final DefaultConfigurableDefault x$0) {
         return x$0 != null;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DefaultConfigurableDefault$.class);
      }
   }

   public static class ValuedDefault implements ConfigurableDefault, Product {
      private static final long serialVersionUID = 1L;
      private final Object v;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean value$mcZ$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcZ$sp(zero);
      }

      public byte value$mcB$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcB$sp(zero);
      }

      public char value$mcC$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcC$sp(zero);
      }

      public double value$mcD$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcD$sp(zero);
      }

      public float value$mcF$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcF$sp(zero);
      }

      public int value$mcI$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcI$sp(zero);
      }

      public long value$mcJ$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcJ$sp(zero);
      }

      public short value$mcS$sp(final Zero zero) {
         return ConfigurableDefault.super.value$mcS$sp(zero);
      }

      public void value$mcV$sp(final Zero zero) {
         ConfigurableDefault.super.value$mcV$sp(zero);
      }

      public void fillArray(final Object arr, final Object v) {
         ConfigurableDefault.super.fillArray(arr, v);
      }

      public void fillArray$mcZ$sp(final boolean[] arr, final boolean v) {
         ConfigurableDefault.super.fillArray$mcZ$sp(arr, v);
      }

      public void fillArray$mcB$sp(final byte[] arr, final byte v) {
         ConfigurableDefault.super.fillArray$mcB$sp(arr, v);
      }

      public void fillArray$mcC$sp(final char[] arr, final char v) {
         ConfigurableDefault.super.fillArray$mcC$sp(arr, v);
      }

      public void fillArray$mcD$sp(final double[] arr, final double v) {
         ConfigurableDefault.super.fillArray$mcD$sp(arr, v);
      }

      public void fillArray$mcF$sp(final float[] arr, final float v) {
         ConfigurableDefault.super.fillArray$mcF$sp(arr, v);
      }

      public void fillArray$mcI$sp(final int[] arr, final int v) {
         ConfigurableDefault.super.fillArray$mcI$sp(arr, v);
      }

      public void fillArray$mcJ$sp(final long[] arr, final long v) {
         ConfigurableDefault.super.fillArray$mcJ$sp(arr, v);
      }

      public void fillArray$mcS$sp(final short[] arr, final short v) {
         ConfigurableDefault.super.fillArray$mcS$sp(arr, v);
      }

      public void fillArray$mcV$sp(final BoxedUnit[] arr, final BoxedUnit v) {
         ConfigurableDefault.super.fillArray$mcV$sp(arr, v);
      }

      public Object makeArray(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray(size, zero, man);
      }

      public boolean[] makeArray$mcZ$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcZ$sp(size, zero, man);
      }

      public byte[] makeArray$mcB$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcB$sp(size, zero, man);
      }

      public char[] makeArray$mcC$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcC$sp(size, zero, man);
      }

      public double[] makeArray$mcD$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcD$sp(size, zero, man);
      }

      public float[] makeArray$mcF$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcF$sp(size, zero, man);
      }

      public int[] makeArray$mcI$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcI$sp(size, zero, man);
      }

      public long[] makeArray$mcJ$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcJ$sp(size, zero, man);
      }

      public short[] makeArray$mcS$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcS$sp(size, zero, man);
      }

      public BoxedUnit[] makeArray$mcV$sp(final int size, final Zero zero, final ClassTag man) {
         return ConfigurableDefault.super.makeArray$mcV$sp(size, zero, man);
      }

      public ConfigurableDefault map(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map(f, zero);
      }

      public ConfigurableDefault map$mcZ$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcZ$sp(f, zero);
      }

      public ConfigurableDefault map$mcB$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcB$sp(f, zero);
      }

      public ConfigurableDefault map$mcC$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcC$sp(f, zero);
      }

      public ConfigurableDefault map$mcD$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcD$sp(f, zero);
      }

      public ConfigurableDefault map$mcF$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcF$sp(f, zero);
      }

      public ConfigurableDefault map$mcI$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcI$sp(f, zero);
      }

      public ConfigurableDefault map$mcJ$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcJ$sp(f, zero);
      }

      public ConfigurableDefault map$mcS$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcS$sp(f, zero);
      }

      public ConfigurableDefault map$mcV$sp(final Function1 f, final Zero zero) {
         return ConfigurableDefault.super.map$mcV$sp(f, zero);
      }

      public Object v() {
         return this.v;
      }

      public Object value(final Zero default) {
         return this.v();
      }

      public ValuedDefault copy(final Object v) {
         return new ValuedDefault(v);
      }

      public Object copy$default$1() {
         return this.v();
      }

      public String productPrefix() {
         return "ValuedDefault";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.v();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ValuedDefault;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "v";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof ValuedDefault) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  ValuedDefault var4 = (ValuedDefault)x$1;
                  if (BoxesRunTime.equals(this.v(), var4.v()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public ValuedDefault(final Object v) {
         this.v = v;
         ConfigurableDefault.$init$(this);
         Product.$init$(this);
      }
   }

   public static class ValuedDefault$ implements Serializable {
      public static final ValuedDefault$ MODULE$ = new ValuedDefault$();

      public final String toString() {
         return "ValuedDefault";
      }

      public ValuedDefault apply(final Object v) {
         return new ValuedDefault(v);
      }

      public Option unapply(final ValuedDefault x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.v()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ValuedDefault$.class);
      }
   }
}
