package scala.collection.immutable;

import java.util.Arrays;
import scala.Function1;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001\u0002\t\u0012\raA\u0011B\u000b\u0001\u0003\u0002\u0003\u0006IaK\u001e\t\u000b}\u0002A\u0011\u0001!\t\u000b\r\u0003A\u0011\u0001#\t\u000b9\u0003A\u0011I(\t\u000be\u0003A\u0011\t.\t\u000b\u0001\u0004A\u0011I1\t\u000b\u001d\u0004A\u0011\t5\t\rI\u0004\u0001\u0015\"\u0005t\u0011\u0015I\b\u0001\"\u0011{\u0011\u0015Y\b\u0001\"\u0011{\u0011\u0019a\b\u0001\"\u0005\u0012{\"1a\u0010\u0001C\t#}D\u0001\"a\u0007\u0001\t#\t\u0012Q\u0004\u0005\t\u0003C\u0001\u0001\u0015\"\u0015\u0002$!A\u0011Q\b\u0001!\n#\nyDA\u0004WK\u000e$xN]\u0019\u000b\u0005I\u0019\u0012!C5n[V$\u0018M\u00197f\u0015\t!R#\u0001\u0006d_2dWm\u0019;j_:T\u0011AF\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tI\u0002e\u0005\u0002\u00015A\u00191\u0004\b\u0010\u000e\u0003EI!!H\t\u0003\u0015Y+7\r^8s\u00136\u0004H\u000e\u0005\u0002 A1\u0001AAB\u0011\u0001\t\u000b\u0007!EA\u0001B#\t\u0019s\u0005\u0005\u0002%K5\tQ#\u0003\u0002'+\t9aj\u001c;iS:<\u0007C\u0001\u0013)\u0013\tISCA\u0002B]f\faa\u00183bi\u0006\f\u0004C\u0001\u00179\u001d\ticG\u0004\u0002/k9\u0011q\u0006\u000e\b\u0003aMj\u0011!\r\u0006\u0003e]\ta\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u0005Q)\u0012B\u0001\n\u0014\u0013\t9\u0014#\u0001\u0007WK\u000e$xN]%oY&tW-\u0003\u0002:u\t!\u0011I\u001d:2\u0015\t9\u0014#\u0003\u0002={\u00059\u0001O]3gSb\f\u0014B\u0001 \u0012\u0005\u00191Vm\u0019;pe\u00061A(\u001b8jiz\"\"!\u0011\"\u0011\u0007m\u0001a\u0004C\u0003+\u0005\u0001\u00071&A\u0003baBd\u0017\u0010\u0006\u0002\u001f\u000b\")ai\u0001a\u0001\u000f\u0006)\u0011N\u001c3fqB\u0011A\u0005S\u0005\u0003\u0013V\u00111!\u00138uQ\t\u00191\n\u0005\u0002%\u0019&\u0011Q*\u0006\u0002\u0007S:d\u0017N\\3\u0002\u000fU\u0004H-\u0019;fIV\u0011\u0001k\u0015\u000b\u0004#Z;\u0006cA\u000e>%B\u0011qd\u0015\u0003\u0006)\u0012\u0011\r!\u0016\u0002\u0002\u0005F\u0011ad\n\u0005\u0006\r\u0012\u0001\ra\u0012\u0005\u00061\u0012\u0001\rAU\u0001\u0005K2,W.\u0001\u0005baB,g\u000eZ3e+\tYf\f\u0006\u0002]?B\u00191$P/\u0011\u0005}qF!\u0002+\u0006\u0005\u0004)\u0006\"\u0002-\u0006\u0001\u0004i\u0016!\u00039sKB,g\u000eZ3e+\t\u0011W\r\u0006\u0002dMB\u00191$\u00103\u0011\u0005})G!\u0002+\u0007\u0005\u0004)\u0006\"\u0002-\u0007\u0001\u0004!\u0017aA7baV\u0011\u0011\u000e\u001c\u000b\u0003U6\u00042aG\u001fl!\tyB\u000eB\u0003U\u000f\t\u0007!\u0005C\u0003o\u000f\u0001\u0007q.A\u0001g!\u0011!\u0003OH6\n\u0005E,\"!\u0003$v]\u000e$\u0018n\u001c82\u0003\u0019\u0019H.[2faQ\u0019A/^<\u0011\u0007mid\u0004C\u0003w\u0011\u0001\u0007q)\u0001\u0002m_\")\u0001\u0010\u0003a\u0001\u000f\u0006\u0011\u0001.[\u0001\u0005i\u0006LG.F\u0001u\u0003\u0011Ig.\u001b;\u0002!Y,7\r^8s'2L7-Z\"pk:$X#A$\u0002\u0017Y,7\r^8s'2L7-\u001a\u000b\u0005\u0003\u0003\t9\u0002\r\u0003\u0002\u0004\u0005-\u0001#\u0002\u0013\u0002\u0006\u0005%\u0011bAA\u0004+\t)\u0011I\u001d:bsB\u0019q$a\u0003\u0005\u0017\u00055A\"!A\u0001\u0002\u000b\u0005\u0011q\u0002\u0002\u0005?\u0012\n4'E\u0002$\u0003#\u00012\u0001JA\n\u0013\r\t)\"\u0006\u0002\u0007\u0003:L(+\u001a4\t\r\u0005eA\u00021\u0001H\u0003\rIG\r_\u0001\u0018m\u0016\u001cGo\u001c:TY&\u001cW\r\u0015:fM&DH*\u001a8hi\"$2aRA\u0010\u0011\u0019\tI\"\u0004a\u0001\u000f\u0006i\u0001O]3qK:$W\rZ!mYB*B!!\n\u0002,Q1\u0011qEA\u0017\u0003s\u0001BaG\u001f\u0002*A\u0019q$a\u000b\u0005\u000bQs!\u0019A+\t\u000f\u0005=b\u00021\u0001\u00022\u00051\u0001O]3gSb\u0004b!a\r\u00026\u0005%R\"A\n\n\u0007\u0005]2C\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW\r\u0003\u0004\u0002<9\u0001\raR\u0001\u0002W\u0006a\u0011\r\u001d9f]\u0012,G-\u00117maU!\u0011\u0011IA$)\u0019\t\u0019%!\u0013\u0002PA!1$PA#!\ry\u0012q\t\u0003\u0006)>\u0011\r!\u0016\u0005\b\u0003\u0017z\u0001\u0019AA'\u0003\u0019\u0019XO\u001a4jqB1\u00111GA\u001b\u0003\u000bBa!a\u000f\u0010\u0001\u00049\u0005"
)
public final class Vector1 extends VectorImpl {
   public Object apply(final int index) {
      if (index >= 0 && index < this.prefix1().length) {
         return this.prefix1()[index];
      } else {
         throw this.ioob(index);
      }
   }

   public Vector updated(final int index, final Object elem) {
      if (index >= 0 && index < this.prefix1().length) {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] copyUpdate_a1c = this.prefix1().clone();
         copyUpdate_a1c[index] = elem;
         var10002 = copyUpdate_a1c;
         copyUpdate_a1c = null;
         return new Vector1(var10002);
      } else {
         throw this.ioob(index);
      }
   }

   public Vector appended(final Object elem) {
      if (this.prefix1().length < 32) {
         return new Vector1(VectorStatics$.MODULE$.copyAppend1(this.prefix1(), elem));
      } else {
         Object[] var10002 = this.prefix1();
         Object[][] var10004 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var10005 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10005 = wrap1_a;
         wrap1_a = null;
         return new Vector2(var10002, 32, var10004, var10005, 33);
      }
   }

   public Vector prepended(final Object elem) {
      int len1 = this.prefix1().length;
      if (len1 < 32) {
         return new Vector1(VectorStatics$.MODULE$.copyPrepend1(elem, this.prefix1()));
      } else {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10002 = wrap1_a;
         wrap1_a = null;
         return new Vector2(var10002, 1, VectorStatics$.MODULE$.empty2(), this.prefix1(), len1 + 1);
      }
   }

   public Vector map(final Function1 f) {
      Vector1 var10000 = new Vector1;
      VectorStatics$ var10002 = VectorStatics$.MODULE$;
      Object[] mapElems1_a = this.prefix1();
      int mapElems1_i = 0;

      while(true) {
         if (mapElems1_i >= mapElems1_a.length) {
            var10002 = mapElems1_a;
            break;
         }

         Object mapElems1_v1 = mapElems1_a[mapElems1_i];
         Object mapElems1_v2 = f.apply(mapElems1_v1);
         if (mapElems1_v1 != mapElems1_v2) {
            Object[] mapElems1_mapElems1Rest_ac = new Object[mapElems1_a.length];
            if (mapElems1_i > 0) {
               System.arraycopy(mapElems1_a, 0, mapElems1_mapElems1Rest_ac, 0, mapElems1_i);
            }

            mapElems1_mapElems1Rest_ac[mapElems1_i] = mapElems1_v2;

            for(int mapElems1_mapElems1Rest_i = mapElems1_i + 1; mapElems1_mapElems1Rest_i < mapElems1_a.length; ++mapElems1_mapElems1Rest_i) {
               mapElems1_mapElems1Rest_ac[mapElems1_mapElems1Rest_i] = f.apply(mapElems1_a[mapElems1_mapElems1Rest_i]);
            }

            var10002 = mapElems1_mapElems1Rest_ac;
            mapElems1_mapElems1Rest_ac = null;
            break;
         }

         ++mapElems1_i;
      }

      mapElems1_a = null;
      Object var9 = null;
      Object var10 = null;
      Object var12 = null;
      var10000.<init>(var10002);
      return var10000;
   }

   public Vector slice0(final int lo, final int hi) {
      return new Vector1(Arrays.copyOfRange(this.prefix1(), lo, hi));
   }

   public Vector tail() {
      if (this.prefix1().length == 1) {
         return Vector0$.MODULE$;
      } else {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.prefix1();
         var10002 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         return new Vector1(var10002);
      }
   }

   public Vector init() {
      if (this.prefix1().length == 1) {
         return Vector0$.MODULE$;
      } else {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] copyInit_a = this.prefix1();
         var10002 = Arrays.copyOfRange(copyInit_a, 0, copyInit_a.length - 1);
         copyInit_a = null;
         return new Vector1(var10002);
      }
   }

   public int vectorSliceCount() {
      return 1;
   }

   public Object[] vectorSlice(final int idx) {
      return this.prefix1();
   }

   public int vectorSlicePrefixLength(final int idx) {
      return this.prefix1().length;
   }

   public Vector prependedAll0(final IterableOnce prefix, final int k) {
      Object[] var3 = VectorStatics$.MODULE$.prepend1IfSpace(this.prefix1(), prefix);
      return (Vector)(var3 == null ? super.prependedAll0(prefix, k) : new Vector1(var3));
   }

   public Vector appendedAll0(final IterableOnce suffix, final int k) {
      Object[] data1b = VectorStatics$.MODULE$.append1IfSpace(this.prefix1(), suffix);
      return (Vector)(data1b != null ? new Vector1(data1b) : super.appendedAll0(suffix, k));
   }

   public Vector1(final Object[] _data1) {
      super(_data1);
   }
}
