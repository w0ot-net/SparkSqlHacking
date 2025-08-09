package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.generic.DefaultSerializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015faB\t\u0013!\u0003\r\t!\u0007\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006)\u00021\t\"\u0016\u0005\u00065\u00021\ta\u0017\u0005\u0006=\u0002!)a\u0018\u0005\u0006=\u0002!\t!\u001a\u0005\u0006s\u0002!\tA\u001f\u0005\u0007\u007f\u0002!)%!\u0001\t\u000f\u0005\u001d\u0001A\"\u0001\u0002\n!9\u0011Q\u0004\u0001\u0005\u0002\u0005}\u0001bBA \u0001\u0011\u0005\u0013\u0011\t\u0005\b\u0003#\u0002A\u0011AA*\u0011\u001d\tI\u0007\u0001C!\u0003W2q!a\u001d\u0001\u0011I\t)\bC\u0004\u0002\u00126!\t!a%\t\u000f\u0005]U\u0002\"\u0001\u0002\u001a\"9\u0011qT\u0007\u0005\u0002\u0005\u0005&AB'ba>\u00038O\u0003\u0002\u0014)\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003+Y\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0012!B:dC2\f7\u0001A\u000b\u00065!\u0012d(O\n\u0005\u0001myR\n\u0005\u0002\u001d;5\ta#\u0003\u0002\u001f-\t1\u0011I\\=SK\u001a\u0004R\u0001I\u0011$iaj\u0011\u0001F\u0005\u0003EQ\u00111\"\u0013;fe\u0006\u0014G.Z(qgB!A\u0004\n\u00142\u0013\t)cC\u0001\u0004UkBdWM\r\t\u0003O!b\u0001\u0001B\u0003*\u0001\t\u0007!FA\u0001L#\tYc\u0006\u0005\u0002\u001dY%\u0011QF\u0006\u0002\b\u001d>$\b.\u001b8h!\tar&\u0003\u00021-\t\u0019\u0011I\\=\u0011\u0005\u001d\u0012DAB\u001a\u0001\t\u000b\u0007!FA\u0001W!\t)d'D\u0001\u0013\u0013\t9$C\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\t9\u0013\b\u0002\u0004;\u0001\u0011\u0015\ra\u000f\u0002\u0002\u0007F\u00111\u0006\u0010\t\u0007k\u00011\u0013'\u0010\u001d\u0011\u0005\u001drDAB \u0001\t\u000b\u0007\u0001I\u0001\u0002D\u0007V\u0019\u0011)\u0012%\u0012\u0005-\u0012\u0005GA\"L!\u0019)\u0004\u0001R$>\u0015B\u0011q%\u0012\u0003\u0006\rz\u0012\rA\u000b\u0002\u00021B\u0011q\u0005\u0013\u0003\u0007\u0013z\")\u0019\u0001\u0016\u0003\u0003e\u0003\"aJ&\u0005\u00131s\u0014\u0011!A\u0001\u0006\u0003Q#aA0%cA1\u0001E\u0014\u00142{aJ!!\u0005\u000b\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0006C\u0001\u000fS\u0013\t\u0019fC\u0001\u0003V]&$\u0018\u0001B2pY2,\u0012A\u0016\n\u0004/bJf\u0001\u0002-\u0001\u0001Y\u0013A\u0002\u0010:fM&tW-\\3oiz\u0002Ba\n 'c\u00059!/Z7pm\u0016$GC\u0001\u001d]\u0011\u0015i6\u00011\u0001'\u0003\rYW-_\u0001\u0007I5Lg.^:\u0015\u0005a\u0002\u0007\"B/\u0005\u0001\u00041\u0003F\u0001\u0003c!\ta2-\u0003\u0002e-\t1\u0011N\u001c7j]\u0016$B\u0001\u000f4iU\")q-\u0002a\u0001M\u0005!1.Z=2\u0011\u0015IW\u00011\u0001'\u0003\u0011YW-\u001f\u001a\t\u000b-,\u0001\u0019\u00017\u0002\t-,\u0017p\u001d\t\u0004954\u0013B\u00018\u0017\u0005)a$/\u001a9fCR,GM\u0010\u0015\u0007\u000bA\u001cHO^<\u0011\u0005q\t\u0018B\u0001:\u0017\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005)\u0018AI+tK\u0002jS\u0006I<ji\"\u0004\u0013M\u001c\u0011fqBd\u0017nY5uA\r|G\u000e\\3di&|g.A\u0003tS:\u001cW-I\u0001y\u0003\u0019\u0011d&M\u001a/a\u0005Q!/Z7pm\u0016$\u0017\t\u001c7\u0015\u0005aZ\b\"B6\u0007\u0001\u0004a\bc\u0001\u0011~M%\u0011a\u0010\u0006\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\rI5Lg.^:%[&tWo\u001d\u000b\u0004q\u0005\r\u0001\"B6\b\u0001\u0004a\bFA\u0004c\u0003\u001d)\b\u000fZ1uK\u0012,B!a\u0003\u0002\u0012Q1\u0011QBA\f\u00033\u0001Ra\n '\u0003\u001f\u00012aJA\t\t\u001d\t\u0019\u0002\u0003b\u0001\u0003+\u0011!AV\u0019\u0012\u0005Er\u0003\"B/\t\u0001\u00041\u0003bBA\u000e\u0011\u0001\u0007\u0011qB\u0001\u0006m\u0006dW/Z\u0001\fkB$\u0017\r^3e/&$\b.\u0006\u0003\u0002\"\u0005%B\u0003BA\u0012\u0003{!B!!\n\u0002,A)qE\u0010\u0014\u0002(A\u0019q%!\u000b\u0005\u000f\u0005M\u0011B1\u0001\u0002\u0016!9\u0011QF\u0005A\u0002\u0005=\u0012!\u0005:f[\u0006\u0004\b/\u001b8h\rVt7\r^5p]B9A$!\r\u00026\u0005m\u0012bAA\u001a-\tIa)\u001e8di&|g.\r\t\u00059\u0005]\u0012'C\u0002\u0002:Y\u0011aa\u00149uS>t\u0007#\u0002\u000f\u00028\u0005\u001d\u0002\"B/\n\u0001\u00041\u0013!\u0002\u0013qYV\u001cX\u0003BA\"\u0003\u0013\"B!!\u0012\u0002LA)qE\u0010\u0014\u0002HA\u0019q%!\u0013\u0005\u000f\u0005M!B1\u0001\u0002\u0016!9\u0011Q\n\u0006A\u0002\u0005=\u0013AA6w!\u0015aBEJA$\u0003%!(/\u00198tM>\u0014X.\u0006\u0003\u0002V\u0005mC\u0003BA,\u0003?\u0002Ra\n '\u00033\u00022aJA.\t\u0019\tif\u0003b\u0001U\t\tq\u000bC\u0004\u0002b-\u0001\r!a\u0019\u0002\u0003\u0019\u0004r\u0001HA3ME\nI&C\u0002\u0002hY\u0011\u0011BR;oGRLwN\u001c\u001a\u0002\r-,\u0017pU3u+\t\ti\u0007\u0005\u00036\u0003_2\u0013bAA9%\t\u00191+\u001a;\u0003\u001f%kW.\u001e;bE2,7*Z=TKR\u001cr!DA<\u0003{\n)\t\u0005\u00036\u0003s2\u0013bAA>%\tY\u0011IY:ue\u0006\u001cGoU3u!\u0011\ty(!!\u000e\u0003\u0001I1!a!O\u0005%9UM\\&fsN+G\u000f\u0005\u0003\u0002\b\u00065UBAAE\u0015\r\tY\tF\u0001\bO\u0016tWM]5d\u0013\u0011\ty)!#\u0003'\u0011+g-Y;miN+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\t\t)\nE\u0002\u0002\u00005\tA!\u001b8dYR!\u0011QNAN\u0011\u0019\tij\u0004a\u0001M\u0005!Q\r\\3n\u0003\u0011)\u0007p\u00197\u0015\t\u00055\u00141\u0015\u0005\u0007\u0003;\u0003\u0002\u0019\u0001\u0014"
)
public interface MapOps extends scala.collection.MapOps {
   MapOps coll();

   MapOps removed(final Object key);

   // $FF: synthetic method
   static MapOps $minus$(final MapOps $this, final Object key) {
      return $this.$minus(key);
   }

   default MapOps $minus(final Object key) {
      return this.removed(key);
   }

   // $FF: synthetic method
   static MapOps $minus$(final MapOps $this, final Object key1, final Object key2, final Seq keys) {
      return $this.$minus(key1, key2, keys);
   }

   /** @deprecated */
   default MapOps $minus(final Object key1, final Object key2, final Seq keys) {
      return this.removed(key1).removed(key2).removedAll(keys);
   }

   // $FF: synthetic method
   static MapOps removedAll$(final MapOps $this, final IterableOnce keys) {
      return $this.removedAll(keys);
   }

   default MapOps removedAll(final IterableOnce keys) {
      return (MapOps)keys.iterator().foldLeft(this.coll(), (x$2, x$3) -> x$2.$minus(x$3));
   }

   // $FF: synthetic method
   static MapOps $minus$minus$(final MapOps $this, final IterableOnce keys) {
      return $this.$minus$minus(keys);
   }

   default MapOps $minus$minus(final IterableOnce keys) {
      return this.removedAll(keys);
   }

   MapOps updated(final Object key, final Object value);

   // $FF: synthetic method
   static MapOps updatedWith$(final MapOps $this, final Object key, final Function1 remappingFunction) {
      return $this.updatedWith(key, remappingFunction);
   }

   default MapOps updatedWith(final Object key, final Function1 remappingFunction) {
      Option previousValue = this.get(key);
      Option var4 = (Option)remappingFunction.apply(previousValue);
      if (None$.MODULE$.equals(var4)) {
         if (previousValue == null) {
            throw null;
         } else if (previousValue.isEmpty()) {
            return this.coll();
         } else {
            Object var6 = previousValue.get();
            return $anonfun$updatedWith$2(this, key, var6);
         }
      } else if (var4 instanceof Some) {
         Object nextValue = ((Some)var4).value();
         if (previousValue == null) {
            throw null;
         } else {
            boolean var10000;
            if (!previousValue.isEmpty()) {
               Object var7 = previousValue.get();
               if ($anonfun$updatedWith$3(nextValue, var7)) {
                  var10000 = true;
                  return var10000 ? this.coll() : this.coll().updated(key, nextValue);
               }
            }

            var10000 = false;
            return var10000 ? this.coll() : this.coll().updated(key, nextValue);
         }
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   static MapOps $plus$(final MapOps $this, final Tuple2 kv) {
      return $this.$plus(kv);
   }

   default MapOps $plus(final Tuple2 kv) {
      return this.updated(kv._1(), kv._2());
   }

   // $FF: synthetic method
   static MapOps transform$(final MapOps $this, final Function2 f) {
      return $this.transform(f);
   }

   default MapOps transform(final Function2 f) {
      return (MapOps)this.map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            return new Tuple2(k, f.apply(k, v));
         } else {
            throw new MatchError((Object)null);
         }
      });
   }

   // $FF: synthetic method
   static Set keySet$(final MapOps $this) {
      return $this.keySet();
   }

   default Set keySet() {
      return new ImmutableKeySet();
   }

   // $FF: synthetic method
   static MapOps $anonfun$updatedWith$1(final MapOps $this) {
      return $this.coll();
   }

   // $FF: synthetic method
   static MapOps $anonfun$updatedWith$2(final MapOps $this, final Object key$1, final Object x$4) {
      return $this.removed(key$1).coll();
   }

   // $FF: synthetic method
   static boolean $anonfun$updatedWith$3(final Object nextValue$1, final Object x$5) {
      return x$5 == nextValue$1;
   }

   static void $init$(final MapOps $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$updatedWith$3$adapted(final Object nextValue$1, final Object x$5) {
      return BoxesRunTime.boxToBoolean($anonfun$updatedWith$3(nextValue$1, x$5));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ImmutableKeySet extends AbstractSet implements scala.collection.MapOps.GenKeySet, DefaultSerializable {
      // $FF: synthetic field
      public final MapOps $outer;

      public Object writeReplace() {
         return DefaultSerializable.writeReplace$(this);
      }

      public Iterator iterator() {
         return scala.collection.MapOps.GenKeySet.iterator$(this);
      }

      public boolean contains(final Object key) {
         return scala.collection.MapOps.GenKeySet.contains$(this, key);
      }

      public int size() {
         return scala.collection.MapOps.GenKeySet.size$(this);
      }

      public int knownSize() {
         return scala.collection.MapOps.GenKeySet.knownSize$(this);
      }

      public boolean isEmpty() {
         return scala.collection.MapOps.GenKeySet.isEmpty$(this);
      }

      public Set incl(final Object elem) {
         if (this.contains(elem)) {
            return this;
         } else {
            SetOps var10000 = (scala.collection.SetOps)this.empty();
            if (var10000 == null) {
               throw null;
            } else {
               var10000 = (SetOps)var10000.concat(this);
               if (var10000 == null) {
                  throw null;
               } else {
                  return (Set)var10000.incl(elem);
               }
            }
         }
      }

      public Set excl(final Object elem) {
         if (this.contains(elem)) {
            SetOps var10000 = (scala.collection.SetOps)this.empty();
            if (var10000 == null) {
               throw null;
            } else {
               var10000 = (SetOps)var10000.concat(this);
               if (var10000 == null) {
                  throw null;
               } else {
                  return (Set)var10000.excl(elem);
               }
            }
         } else {
            return this;
         }
      }

      // $FF: synthetic method
      public MapOps scala$collection$immutable$MapOps$ImmutableKeySet$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public scala.collection.MapOps scala$collection$MapOps$GenKeySet$$$outer() {
         return this.scala$collection$immutable$MapOps$ImmutableKeySet$$$outer();
      }

      public ImmutableKeySet() {
         if (MapOps.this == null) {
            throw null;
         } else {
            this.$outer = MapOps.this;
            super();
         }
      }
   }
}
