package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.annotation.Stable;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005uf\u0001\u0002\u0012$!9B\u0011\"\u0011\u0001\u0003\u0006\u0004%\ta\t\"\t\u0011E\u0003!\u0011!Q\u0001\n\rCaA\u0015\u0001\u0005\u0002\r\u001a\u0006\"\u0002*\u0001\t#9\u0006\"\u0002-\u0001\t\u0003I\u0006\"B0\u0001\t\u0003\u0001\u0007\"B1\u0001\t\u0003\u0011\u0007\"B4\u0001\t\u0003A\u0007\"B7\u0001\t\u0003q\u0007\"\u00029\u0001\t\u0003\t\b\"B:\u0001\t\u0003!\b\"\u0002<\u0001\t\u00039\b\"\u0002?\u0001\t\u0003i\bbBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u0013\u0001A\u0011AA\u0006\u0011\u001d\t\t\u0002\u0001C\u0001\u0003'Aq!!\u0007\u0001\t\u0003\tY\u0002C\u0004\u0002\u001e\u0001!\t%a\b\t\u000f\u0005\u0005\u0002\u0001\"\u0011\u0002$!Q\u0011\u0011\u0006\u0001\t\u0006\u0004%I!a\u000b\t\u000f\u0005M\u0002\u0001\"\u0011\u00026!9\u0011q\u0007\u0001\u0005\n\u0005e\u0002\u0002CA(\u0001\u0011\u0005Q%!\u0015\b\u000f\u000554\u0005#\u0001\u0002p\u00191!e\tE\u0001\u0003cBaAU\r\u0005\u0002\u0005\u0005\u0005bBAB3\u0001\u0006I\u0001\u0016\u0005\b\u0003\u000bKB\u0011AAD\u0011\u001d\tI)\u0007C\u0001\u0003\u0017C\u0001\"a$\u001a\t\u0003)\u0013\u0011\u0013\u0005\b\u0003;KB\u0011BAP\u0011\u001d\t\u0019+\u0007C\u0005\u0003KC\u0011\"!+\u001a\u0003\u0003%I!a+\u0003\u00115+G/\u00193bi\u0006T!\u0001J\u0013\u0002\u000bQL\b/Z:\u000b\u0005\u0019:\u0013aA:rY*\u0011\u0001&K\u0001\u0006gB\f'o\u001b\u0006\u0003U-\na!\u00199bG\",'\"\u0001\u0017\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001yS\u0007\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0003myr!a\u000e\u001f\u000f\u0005aZT\"A\u001d\u000b\u0005ij\u0013A\u0002\u001fs_>$h(C\u00013\u0013\ti\u0014'A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0002%\u0001D*fe&\fG.\u001b>bE2,'BA\u001f2\u0003\ri\u0017\r]\u000b\u0002\u0007B!A\tS&O\u001d\t)e\t\u0005\u00029c%\u0011q)M\u0001\u0007!J,G-\u001a4\n\u0005%S%aA'ba*\u0011q)\r\t\u0003\t2K!!\u0014&\u0003\rM#(/\u001b8h!\t\u0001t*\u0003\u0002Qc\t\u0019\u0011I\\=\u0002\t5\f\u0007\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005Q3\u0006CA+\u0001\u001b\u0005\u0019\u0003\"B!\u0004\u0001\u0004\u0019E#\u0001+\u0002\u0011\r|g\u000e^1j]N$\"AW/\u0011\u0005AZ\u0016B\u0001/2\u0005\u001d\u0011un\u001c7fC:DQAX\u0003A\u0002-\u000b1a[3z\u0003\u001dI7/R7qif,\u0012AW\u0001\bO\u0016$Hj\u001c8h)\t\u0019g\r\u0005\u00021I&\u0011Q-\r\u0002\u0005\u0019>tw\rC\u0003_\u000f\u0001\u00071*A\u0005hKR$u.\u001e2mKR\u0011\u0011\u000e\u001c\t\u0003a)L!a[\u0019\u0003\r\u0011{WO\u00197f\u0011\u0015q\u0006\u00021\u0001L\u0003)9W\r\u001e\"p_2,\u0017M\u001c\u000b\u00035>DQAX\u0005A\u0002-\u000b\u0011bZ3u'R\u0014\u0018N\\4\u0015\u0005-\u0013\b\"\u00020\u000b\u0001\u0004Y\u0015aC4fi6+G/\u00193bi\u0006$\"\u0001V;\t\u000by[\u0001\u0019A&\u0002\u0019\u001d,G\u000fT8oO\u0006\u0013(/Y=\u0015\u0005a\\\bc\u0001\u0019zG&\u0011!0\r\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u0006=2\u0001\raS\u0001\u000fO\u0016$Hi\\;cY\u0016\f%O]1z)\tqx\u0010E\u00021s&DQAX\u0007A\u0002-\u000bqbZ3u\u0005>|G.Z1o\u0003J\u0014\u0018-\u001f\u000b\u0005\u0003\u000b\t9\u0001E\u00021sjCQA\u0018\bA\u0002-\u000babZ3u'R\u0014\u0018N\\4BeJ\f\u0017\u0010\u0006\u0003\u0002\u000e\u0005=\u0001c\u0001\u0019z\u0017\")al\u0004a\u0001\u0017\u0006\u0001r-\u001a;NKR\fG-\u0019;b\u0003J\u0014\u0018-\u001f\u000b\u0005\u0003+\t9\u0002E\u00021sRCQA\u0018\tA\u0002-\u000bAA[:p]V\t1*\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0015AB3rk\u0006d7\u000fF\u0002[\u0003KAa!a\n\u0014\u0001\u0004q\u0015aA8cU\u0006Iq\f[1tQ\u000e{G-Z\u000b\u0003\u0003[\u00012\u0001MA\u0018\u0013\r\t\t$\r\u0002\u0004\u0013:$\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u00055\u0012aA4fiV!\u00111HA!)\u0011\ti$!\u0014\u0011\t\u0005}\u0012\u0011\t\u0007\u0001\t\u001d\t\u0019E\u0006b\u0001\u0003\u000b\u0012\u0011\u0001V\t\u0004\u0003\u000fr\u0005c\u0001\u0019\u0002J%\u0019\u00111J\u0019\u0003\u000f9{G\u000f[5oO\")aL\u0006a\u0001\u0017\u0006I!n]8o-\u0006dW/Z\u000b\u0003\u0003'\u0002B!!\u0016\u0002\\5\u0011\u0011q\u000b\u0006\u0004\u00033Z\u0013A\u00026t_:$4/\u0003\u0003\u0002^\u0005]#A\u0002&WC2,X\rK\u0002\u0001\u0003C\u0002B!a\u0019\u0002j5\u0011\u0011Q\r\u0006\u0004\u0003O:\u0013AC1o]>$\u0018\r^5p]&!\u00111NA3\u0005\u0019\u0019F/\u00192mK\u0006AQ*\u001a;bI\u0006$\u0018\r\u0005\u0002V3M!\u0011dLA:!\u0011\t)(a \u000e\u0005\u0005]$\u0002BA=\u0003w\n!![8\u000b\u0005\u0005u\u0014\u0001\u00026bm\u0006L1aPA<)\t\ty'\u0001\u0004`K6\u0004H/_\u0001\u0006K6\u0004H/_\u000b\u0002)\u0006AaM]8n\u0015N|g\u000eF\u0002U\u0003\u001bCa!!\u0007\u001e\u0001\u0004Y\u0015a\u00034s_6TuJ\u00196fGR$2\u0001VAJ\u0011\u001d\t)J\ba\u0001\u0003/\u000bAA[(cUB!\u0011QKAM\u0013\u0011\tY*a\u0016\u0003\u000f){%M[3di\u0006YAo\u001c&t_:4\u0016\r\\;f)\u0011\t\u0019&!)\t\r\u0005\u001dr\u00041\u0001O\u0003\u0011A\u0017m\u001d5\u0015\t\u00055\u0012q\u0015\u0005\u0007\u0003O\u0001\u0003\u0019\u0001(\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\u0006\u0003BAX\u0003kk!!!-\u000b\t\u0005M\u00161P\u0001\u0005Y\u0006tw-\u0003\u0003\u00028\u0006E&AB(cU\u0016\u001cG\u000fK\u0002\u001a\u0003CB3\u0001GA1\u0001"
)
public class Metadata implements Serializable {
   private int _hashCode;
   private final Map map;
   private volatile boolean bitmap$0;

   public static Metadata fromJson(final String json) {
      return Metadata$.MODULE$.fromJson(json);
   }

   public static Metadata empty() {
      return Metadata$.MODULE$.empty();
   }

   public Map map() {
      return this.map;
   }

   public boolean contains(final String key) {
      return this.map().contains(key);
   }

   public boolean isEmpty() {
      return this.map().isEmpty();
   }

   public long getLong(final String key) {
      return BoxesRunTime.unboxToLong(this.get(key));
   }

   public double getDouble(final String key) {
      return BoxesRunTime.unboxToDouble(this.get(key));
   }

   public boolean getBoolean(final String key) {
      return BoxesRunTime.unboxToBoolean(this.get(key));
   }

   public String getString(final String key) {
      return (String)this.get(key);
   }

   public Metadata getMetadata(final String key) {
      return (Metadata)this.get(key);
   }

   public long[] getLongArray(final String key) {
      return (long[])this.get(key);
   }

   public double[] getDoubleArray(final String key) {
      return (double[])this.get(key);
   }

   public boolean[] getBooleanArray(final String key) {
      return (boolean[])this.get(key);
   }

   public String[] getStringArray(final String key) {
      return (String[])this.get(key);
   }

   public Metadata[] getMetadataArray(final String key) {
      return (Metadata[])this.get(key);
   }

   public String json() {
      return .MODULE$.compact(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String toString() {
      return this.json();
   }

   public boolean equals(final Object obj) {
      if (obj instanceof Metadata var4) {
         if (this.map().size() == var4.map().size()) {
            return this.map().keysIterator().forall((key) -> BoxesRunTime.boxToBoolean($anonfun$equals$1(this, var4, key)));
         }
      }

      return false;
   }

   private int _hashCode$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this._hashCode = Metadata$.MODULE$.org$apache$spark$sql$types$Metadata$$hash(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this._hashCode;
   }

   private int _hashCode() {
      return !this.bitmap$0 ? this._hashCode$lzycompute() : this._hashCode;
   }

   public int hashCode() {
      return this._hashCode();
   }

   private Object get(final String key) {
      return this.map().apply(key);
   }

   public JValue jsonValue() {
      return Metadata$.MODULE$.org$apache$spark$sql$types$Metadata$$toJsonValue(this);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equals$1(final Metadata $this, final Metadata x2$1, final String key) {
      Option var5 = x2$1.map().get(key);
      if (var5 instanceof Some var6) {
         Object otherValue = var6.value();
         Object ourValue = $this.map().apply(key);
         Tuple2 var9 = new Tuple2(ourValue, otherValue);
         if (var9 != null) {
            Object v0 = var9._1();
            Object v1 = var9._2();
            if (v0 instanceof long[]) {
               long[] var12 = (long[])v0;
               if (v1 instanceof long[]) {
                  long[] var13 = (long[])v1;
                  return Arrays.equals(var12, var13);
               }
            }
         }

         if (var9 != null) {
            Object v0 = var9._1();
            Object v1 = var9._2();
            if (v0 instanceof double[]) {
               double[] var16 = (double[])v0;
               if (v1 instanceof double[]) {
                  double[] var17 = (double[])v1;
                  return Arrays.equals(var16, var17);
               }
            }
         }

         if (var9 != null) {
            Object v0 = var9._1();
            Object v1 = var9._2();
            if (v0 instanceof boolean[]) {
               boolean[] var20 = (boolean[])v0;
               if (v1 instanceof boolean[]) {
                  boolean[] var21 = (boolean[])v1;
                  return Arrays.equals(var20, var21);
               }
            }
         }

         if (var9 != null) {
            Object v0 = var9._1();
            Object v1 = var9._2();
            if (v0 instanceof Object[]) {
               Object[] var24 = v0;
               if (v1 instanceof Object[]) {
                  Object[] var25 = v1;
                  return Arrays.equals(var24, var25);
               }
            }
         }

         if (var9 != null) {
            Object v0 = var9._1();
            Object v1 = var9._2();
            return BoxesRunTime.equals(v0, v1);
         } else {
            throw new MatchError(var9);
         }
      } else if (scala.None..MODULE$.equals(var5)) {
         return false;
      } else {
         throw new MatchError(var5);
      }
   }

   public Metadata(final Map map) {
      this.map = map;
   }

   public Metadata() {
      this((Map)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
