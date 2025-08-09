package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]fa\u0002\n\u0014!\u0003\r\tA\u0007\u0005\u00069\u0002!\t!\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006G\u0002!)\u0001\u001a\u0005\u0006G\u0002!)!\u001d\u0005\u0006}\u0002!\ta \u0005\b\u0003\u001b\u0001A\u0011AA\b\u0011\u001d\t)\u0002\u0001C\u0001\u0003/Aq!a\n\u0001\t\u0003\tI\u0003C\u0004\u0002H\u0001!\t!!\u0013\t\r\u00055\u0003\u0001\"\u0001^\u0011\u0019\ty\u0005\u0001C!E\"9\u0011\u0011\u000b\u0001\u0005\u0006\u0005M\u0003bBA;\u0001\u0011\u0005\u0011q\u000f\u0005\b\u0003w\u0002AQAA?\u0011\u001d\ti\t\u0001C\u0001\u0003\u001fCq!a%\u0001\t\u0003\t)\nC\u0004\u0002.\u0002!\t%a,\u0003\r5\u000b\u0007o\u00149t\u0015\t!R#A\u0004nkR\f'\r\\3\u000b\u0005Y9\u0012AC2pY2,7\r^5p]*\t\u0001$A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u000bmI3g\u0010\u001e\u0014\u0011\u0001a\u0002E\u0014)T-f\u0003\"!\b\u0010\u000e\u0003]I!aH\f\u0003\r\u0005s\u0017PU3g!\u0015\t#\u0005J\u001b:\u001b\u0005)\u0012BA\u0012\u0016\u0005-IE/\u001a:bE2,w\n]:\u0011\tu)sEM\u0005\u0003M]\u0011a\u0001V;qY\u0016\u0014\u0004C\u0001\u0015*\u0019\u0001!QA\u000b\u0001C\u0002-\u0012\u0011aS\t\u0003Y=\u0002\"!H\u0017\n\u00059:\"a\u0002(pi\"Lgn\u001a\t\u0003;AJ!!M\f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002)g\u0011)A\u0007\u0001b\u0001W\t\ta\u000b\u0005\u00027o5\t1#\u0003\u00029'\tA\u0011\n^3sC\ndW\r\u0005\u0002)u\u001111\b\u0001CC\u0002q\u0012\u0011aQ\t\u0003Yu\u0002bA\u000e\u0001(eyJ\u0004C\u0001\u0015@\t\u0019\u0001\u0005\u0001\"b\u0001\u0003\n\u00111iQ\u000b\u0004\u0005\u001aK\u0015C\u0001\u0017Da\t!E\n\u0005\u00047\u0001\u0015Ceh\u0013\t\u0003Q\u0019#QaR C\u0002-\u0012\u0011\u0001\u0017\t\u0003Q%#QAS C\u0002-\u0012\u0011!\u0017\t\u0003Q1#\u0011\"T \u0002\u0002\u0003\u0005)\u0011A\u0016\u0003\u0007}#\u0013\u0007\u0005\u0004\"\u001f\u001e\u0012d(O\u0005\u0003%U\u00012AN):\u0013\t\u00116CA\u0005DY>tW-\u00192mKB!a\u0007\u0016\u0013:\u0013\t)6CA\u0004Ck&dG-\u001a:\u0011\u0007Y:F%\u0003\u0002Y'\tAqI]8xC\ndW\rE\u000275\u001eJ!aW\n\u0003\u0015MC'/\u001b8lC\ndW-\u0001\u0004%S:LG\u000f\n\u000b\u0002=B\u0011QdX\u0005\u0003A^\u0011A!\u00168ji\u00061!/Z:vYR$\u0012!O\u0001\u0007I5Lg.^:\u0015\u0005e*\u0007\"\u00024\u0004\u0001\u00049\u0013aA6fs\"21\u0001[6m]>\u0004\"!H5\n\u0005)<\"A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017%A7\u0002GU\u001bX\rI\u0017!_J\u0004#/Z7pm\u0016\u0004sN\u001c\u0011b]\u0002JW.\\;uC\ndW\rI'ba\u0006)1/\u001b8dK\u0006\n\u0001/\u0001\u00043]E\u001ad\u0006\r\u000b\u0005sI$h\u000fC\u0003t\t\u0001\u0007q%\u0001\u0003lKf\f\u0004\"B;\u0005\u0001\u00049\u0013\u0001B6fsJBQa\u001e\u0003A\u0002a\fAa[3zgB\u0019Q$_\u0014\n\u0005i<\"A\u0003\u001fsKB,\u0017\r^3e}!2A\u0001[6}]>\f\u0013!`\u0001(+N,\u0007%L\u0017!_J\u0004#/Z7pm\u0016\fE\u000e\u001c\u0011p]\u0002\ng\u000eI5n[V$\u0018M\u00197fA5\u000b\u0007/A\u0002qkR$b!!\u0001\u0002\b\u0005%\u0001\u0003B\u000f\u0002\u0004IJ1!!\u0002\u0018\u0005\u0019y\u0005\u000f^5p]\")a-\u0002a\u0001O!1\u00111B\u0003A\u0002I\nQA^1mk\u0016\fa!\u001e9eCR,G#\u00020\u0002\u0012\u0005M\u0001\"\u00024\u0007\u0001\u00049\u0003BBA\u0006\r\u0001\u0007!'\u0001\u0006va\u0012\fG/Z,ji\"$B!!\u0007\u0002&Q!\u0011\u0011AA\u000e\u0011\u001d\tib\u0002a\u0001\u0003?\t\u0011C]3nCB\u0004\u0018N\\4Gk:\u001cG/[8o!\u001di\u0012\u0011EA\u0001\u0003\u0003I1!a\t\u0018\u0005%1UO\\2uS>t\u0017\u0007C\u0003g\u000f\u0001\u0007q%A\bhKR|%/\u00127tKV\u0003H-\u0019;f)\u0015\u0011\u00141FA\u0017\u0011\u00151\u0007\u00021\u0001(\u0011!\ty\u0003\u0003CA\u0002\u0005E\u0012\u0001\u00043fM\u0006,H\u000e\u001e,bYV,\u0007\u0003B\u000f\u00024IJ1!!\u000e\u0018\u0005!a$-\u001f8b[\u0016t\u0004\u0006CA\u0017\u0003s\ty$a\u0011\u0011\u0007u\tY$C\u0002\u0002>]\u0011a\u0002Z3qe\u0016\u001c\u0017\r^3e\u001d\u0006lW-\t\u0002\u0002B\u0005\u0011q\u000e]\u0011\u0003\u0003\u000b\nqA\r\u00182g9\n4'\u0001\u0004sK6|g/\u001a\u000b\u0005\u0003\u0003\tY\u0005C\u0003g\u0013\u0001\u0007q%A\u0003dY\u0016\f'/A\u0003dY>tW-\u0001\u0004sKR\f\u0017N\u001c\u000b\u0005\u0003+\n9&D\u0001\u0001\u0011\u001d\tI\u0006\u0004a\u0001\u00037\n\u0011\u0001\u001d\t\b;\u0005usEMA1\u0013\r\tyf\u0006\u0002\n\rVt7\r^5p]J\u00022!HA2\u0013\r\t)g\u0006\u0002\b\u0005>|G.Z1oQ\u001da\u0001n[A5]>\f#!a\u001b\u00023U\u001bX\r\t4jYR,'/\u00138QY\u0006\u001cW\rI5ogR,\u0017\r\u001a\u0015\u0004\u0019\u0005=\u0004cA\u000f\u0002r%\u0019\u00111O\f\u0003\r%tG.\u001b8f\u000351\u0017\u000e\u001c;fe&s\u0007\u000b\\1dKR!\u0011QKA=\u0011\u001d\tI&\u0004a\u0001\u00037\n\u0011\u0002\u001e:b]N4wN]7\u0015\t\u0005U\u0013q\u0010\u0005\b\u0003\u0003s\u0001\u0019AAB\u0003\u00051\u0007CB\u000f\u0002^\u001d\u0012$\u0007K\u0004\u000fQ.\f9I\\8\"\u0005\u0005%\u0015\u0001H+tK\u0002j\u0017\r\u001d,bYV,7/\u00138QY\u0006\u001cW\rI5ogR,\u0017\r\u001a\u0015\u0004\u001d\u0005=\u0014\u0001E7baZ\u000bG.^3t\u0013:\u0004F.Y2f)\u0011\t)&!%\t\u000f\u0005\u0005u\u00021\u0001\u0002\u0004\u00069Q\u000f\u001d3bi\u0016$W\u0003BAL\u0003;#b!!'\u0002$\u0006\u0015\u0006#\u0002\u0015@O\u0005m\u0005c\u0001\u0015\u0002\u001e\u00129\u0011q\u0014\tC\u0002\u0005\u0005&A\u0001,2#\t\u0011t\u0006C\u0003g!\u0001\u0007q\u0005C\u0004\u0002\fA\u0001\r!a')\u000fAA7.!+o_\u0006\u0012\u00111V\u00017+N,\u0007%\u001c\u0018dY>tW\rK\u0015/C\u0012$wJ\\3)Q-dc/K\u0015!S:\u001cH/Z1eA=4\u0007%\u001c\u0018va\u0012\fG/\u001a3)W2\u0002c/K\u0001\nW:|wO\\*ju\u0016,\"!!-\u0011\u0007u\t\u0019,C\u0002\u00026^\u00111!\u00138u\u0001"
)
public interface MapOps extends scala.collection.MapOps, Cloneable, Builder, Shrinkable {
   // $FF: synthetic method
   static MapOps result$(final MapOps $this) {
      return $this.result();
   }

   default MapOps result() {
      return (MapOps)this.coll();
   }

   // $FF: synthetic method
   static MapOps $minus$(final MapOps $this, final Object key) {
      return $this.$minus(key);
   }

   /** @deprecated */
   default MapOps $minus(final Object key) {
      MapOps var10000 = this.clone();
      if (var10000 == null) {
         throw null;
      } else {
         return (MapOps)var10000.subtractOne(key);
      }
   }

   // $FF: synthetic method
   static MapOps $minus$(final MapOps $this, final Object key1, final Object key2, final scala.collection.immutable.Seq keys) {
      return $this.$minus(key1, key2, keys);
   }

   /** @deprecated */
   default MapOps $minus(final Object key1, final Object key2, final scala.collection.immutable.Seq keys) {
      Shrinkable var10000 = this.clone();
      if (var10000 == null) {
         throw null;
      } else {
         var10000 = var10000.subtractOne(key1);
         if (var10000 == null) {
            throw null;
         } else {
            var10000 = var10000.subtractOne(key2);
            if (var10000 == null) {
               throw null;
            } else {
               return (MapOps)var10000.subtractAll(keys);
            }
         }
      }
   }

   // $FF: synthetic method
   static Option put$(final MapOps $this, final Object key, final Object value) {
      return $this.put(key, value);
   }

   default Option put(final Object key, final Object value) {
      Option r = this.get(key);
      this.update(key, value);
      return r;
   }

   // $FF: synthetic method
   static void update$(final MapOps $this, final Object key, final Object value) {
      $this.update(key, value);
   }

   default void update(final Object key, final Object value) {
      Growable var10000 = (Growable)this.coll();
      Tuple2 $plus$eq_elem = new Tuple2(key, value);
      if (var10000 == null) {
         throw null;
      } else {
         var10000.addOne($plus$eq_elem);
      }
   }

   // $FF: synthetic method
   static Option updateWith$(final MapOps $this, final Object key, final Function1 remappingFunction) {
      return $this.updateWith(key, remappingFunction);
   }

   default Option updateWith(final Object key, final Function1 remappingFunction) {
      Option previousValue = this.get(key);
      Option nextValue = (Option)remappingFunction.apply(previousValue);
      Tuple2 var5 = new Tuple2(previousValue, nextValue);
      if (!None$.MODULE$.equals(previousValue) || !None$.MODULE$.equals(nextValue)) {
         if (previousValue instanceof Some && None$.MODULE$.equals(nextValue)) {
            this.remove(key);
         } else {
            if (!(nextValue instanceof Some)) {
               throw new MatchError(var5);
            }

            Object v = ((Some)nextValue).value();
            this.update(key, v);
         }
      }

      return nextValue;
   }

   // $FF: synthetic method
   static Object getOrElseUpdate$(final MapOps $this, final Object key, final Function0 defaultValue) {
      return $this.getOrElseUpdate(key, defaultValue);
   }

   default Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      Option var3 = this.get(key);
      if (var3 instanceof Some) {
         return ((Some)var3).value();
      } else if (None$.MODULE$.equals(var3)) {
         Object d = defaultValue.apply();
         this.update(key, d);
         return d;
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   static Option remove$(final MapOps $this, final Object key) {
      return $this.remove(key);
   }

   default Option remove(final Object key) {
      Option r = this.get(key);
      if (r.isDefined()) {
         this.subtractOne(key);
      }

      return r;
   }

   // $FF: synthetic method
   static void clear$(final MapOps $this) {
      $this.clear();
   }

   default void clear() {
      this.keysIterator().foreach((elem) -> (MapOps)this.$minus$eq(elem));
   }

   // $FF: synthetic method
   static MapOps clone$(final MapOps $this) {
      return $this.clone();
   }

   default MapOps clone() {
      Growable var10000 = (Growable)this.empty();
      if (var10000 == null) {
         throw null;
      } else {
         return (MapOps)var10000.addAll(this);
      }
   }

   // $FF: synthetic method
   static MapOps retain$(final MapOps $this, final Function2 p) {
      return $this.retain(p);
   }

   /** @deprecated */
   default MapOps retain(final Function2 p) {
      return this.filterInPlace(p);
   }

   // $FF: synthetic method
   static MapOps filterInPlace$(final MapOps $this, final Function2 p) {
      return $this.filterInPlace(p);
   }

   default MapOps filterInPlace(final Function2 p) {
      if (!this.isEmpty()) {
         if (this instanceof scala.collection.concurrent.Map) {
            ((scala.collection.concurrent.Map)this).filterInPlaceImpl(p);
         } else {
            Object[] array = this.toArray(ClassTag$.MODULE$.Any());
            int arrayLength = array.length;

            for(int i = 0; i < arrayLength; ++i) {
               Tuple2 var5 = (Tuple2)array[i];
               if (var5 == null) {
                  throw new MatchError((Object)null);
               }

               Object k = var5._1();
               Object v = var5._2();
               if (!BoxesRunTime.unboxToBoolean(p.apply(k, v))) {
                  this.subtractOne(k);
               }
            }
         }
      }

      return this;
   }

   // $FF: synthetic method
   static MapOps transform$(final MapOps $this, final Function2 f) {
      return $this.transform(f);
   }

   /** @deprecated */
   default MapOps transform(final Function2 f) {
      return this.mapValuesInPlace(f);
   }

   // $FF: synthetic method
   static MapOps mapValuesInPlace$(final MapOps $this, final Function2 f) {
      return $this.mapValuesInPlace(f);
   }

   default MapOps mapValuesInPlace(final Function2 f) {
      if (!this.isEmpty()) {
         if (this instanceof HashMap) {
            ((HashMap)this).mapValuesInPlaceImpl(f);
         } else if (this instanceof scala.collection.concurrent.Map) {
            ((scala.collection.concurrent.Map)this).mapValuesInPlaceImpl(f);
         } else {
            Object[] array = this.toArray(ClassTag$.MODULE$.Any());
            int arrayLength = array.length;

            for(int i = 0; i < arrayLength; ++i) {
               Tuple2 var5 = (Tuple2)array[i];
               if (var5 == null) {
                  throw new MatchError((Object)null);
               }

               Object k = var5._1();
               Object v = var5._2();
               this.update(k, f.apply(k, v));
            }
         }
      }

      return this;
   }

   // $FF: synthetic method
   static MapOps updated$(final MapOps $this, final Object key, final Object value) {
      return $this.updated(key, value);
   }

   /** @deprecated */
   default MapOps updated(final Object key, final Object value) {
      return (MapOps)this.clone().addOne(new Tuple2(key, value));
   }

   // $FF: synthetic method
   static int knownSize$(final MapOps $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   static void $init$(final MapOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
