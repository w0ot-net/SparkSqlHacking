package scala.collection.concurrent;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]aaB\u0007\u000f!\u0003\r\t!\u0006\u0005\u0006]\u0001!\ta\f\u0005\u0006g\u00011\t\u0001\u000e\u0005\u0006y\u00011\t!\u0010\u0005\u0006\u0007\u00021\t\u0001\u0012\u0005\u0006\u0007\u00021\tA\u0013\u0005\u0006\u001b\u0002!\tE\u0014\u0005\u0007=\u0002!\t\u0001E0\t\r\t\u0004A\u0011\u0001\td\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u0015\u0011\b\u0001\"\u0003t\u0011\u0019q\b\u0001\"\u0001\u0011\u007f\"A\u0011Q\u0002\u0001\u0005\u0002A\tyAA\u0002NCBT!a\u0004\t\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002\u0012%\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003M\tQa]2bY\u0006\u001c\u0001!F\u0002\u0017E1\u001a2\u0001A\f\u001c!\tA\u0012$D\u0001\u0013\u0013\tQ\"C\u0001\u0004B]f\u0014VM\u001a\t\u00059}\u00013&D\u0001\u001e\u0015\tq\u0002#A\u0004nkR\f'\r\\3\n\u00055i\u0002CA\u0011#\u0019\u0001!Qa\t\u0001C\u0002\u0011\u0012\u0011aS\t\u0003K!\u0002\"\u0001\u0007\u0014\n\u0005\u001d\u0012\"a\u0002(pi\"Lgn\u001a\t\u00031%J!A\u000b\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\"Y\u0011)Q\u0006\u0001b\u0001I\t\ta+\u0001\u0004%S:LG\u000f\n\u000b\u0002aA\u0011\u0001$M\u0005\u0003eI\u0011A!\u00168ji\u0006Y\u0001/\u001e;JM\u0006\u00137/\u001a8u)\r)\u0004H\u000f\t\u00041YZ\u0013BA\u001c\u0013\u0005\u0019y\u0005\u000f^5p]\")\u0011H\u0001a\u0001A\u0005\t1\u000eC\u0003<\u0005\u0001\u00071&A\u0001w\u0003\u0019\u0011X-\\8wKR\u0019a(\u0011\"\u0011\u0005ay\u0014B\u0001!\u0013\u0005\u001d\u0011un\u001c7fC:DQ!O\u0002A\u0002\u0001BQaO\u0002A\u0002-\nqA]3qY\u0006\u001cW\r\u0006\u0003?\u000b\u001aC\u0005\"B\u001d\u0005\u0001\u0004\u0001\u0003\"B$\u0005\u0001\u0004Y\u0013\u0001C8mIZ\fG.^3\t\u000b%#\u0001\u0019A\u0016\u0002\u00119,wO^1mk\u0016$2!N&M\u0011\u0015IT\u00011\u0001!\u0011\u0015YT\u00011\u0001,\u0003=9W\r^(s\u000b2\u001cX-\u00169eCR,GcA\u0016P#\")\u0001K\u0002a\u0001A\u0005\u00191.Z=\t\rI3A\u00111\u0001T\u00031!WMZ1vYR4\u0016\r\\;f!\rABkK\u0005\u0003+J\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0015\u0005#^SF\f\u0005\u0002\u00191&\u0011\u0011L\u0005\u0002\u000fI\u0016\u0004(/Z2bi\u0016$g*Y7fC\u0005Y\u0016AA8qC\u0005i\u0016a\u0002\u001a/cMr\u0013gM\u0001\fe\u0016lwN^3SK\u001a,\u0015\u000fF\u0002?A\u0006DQ!O\u0004A\u0002\u0001BQaO\u0004A\u0002-\nAB]3qY\u0006\u001cWMU3g\u000bF$BA\u00103fO\")\u0011\b\u0003a\u0001A!)a\r\u0003a\u0001W\u0005Aq\u000e\u001c3WC2,X\rC\u0003i\u0011\u0001\u00071&\u0001\u0005oK^4\u0016\r\\;f\u0003))\b\u000fZ1uK^KG\u000f\u001b\u000b\u0003WF$\"!\u000e7\t\u000b5L\u0001\u0019\u00018\u0002#I,W.\u00199qS:<g)\u001e8di&|g\u000e\u0005\u0003\u0019_V*\u0014B\u00019\u0013\u0005%1UO\\2uS>t\u0017\u0007C\u0003Q\u0013\u0001\u0007\u0001%A\u0007va\u0012\fG/Z,ji\"\fU\u000f\u001f\u000b\u0003iZ$\"!N;\t\u000b5T\u0001\u0019\u00018\t\u000bAS\u0001\u0019\u0001\u0011)\u0005)A\bCA=}\u001b\u0005Q(BA>\u0013\u0003)\tgN\\8uCRLwN\\\u0005\u0003{j\u0014q\u0001^1jYJ,7-A\tgS2$XM]%o!2\f7-Z%na2$B!!\u0001\u0002\u00045\t\u0001\u0001C\u0004\u0002\u0006-\u0001\r!a\u0002\u0002\u0003A\u0004b\u0001GA\u0005A-r\u0014bAA\u0006%\tIa)\u001e8di&|gNM\u0001\u0015[\u0006\u0004h+\u00197vKNLe\u000e\u00157bG\u0016LU\u000e\u001d7\u0015\t\u0005\u0005\u0011\u0011\u0003\u0005\b\u0003'a\u0001\u0019AA\u000b\u0003\u00051\u0007C\u0002\r\u0002\n\u0001Z3\u0006"
)
public interface Map extends scala.collection.mutable.Map {
   Option putIfAbsent(final Object k, final Object v);

   boolean remove(final Object k, final Object v);

   boolean replace(final Object k, final Object oldvalue, final Object newvalue);

   Option replace(final Object k, final Object v);

   // $FF: synthetic method
   static Object getOrElseUpdate$(final Map $this, final Object key, final Function0 defaultValue) {
      return $this.getOrElseUpdate(key, defaultValue);
   }

   default Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      Option var3 = this.get(key);
      if (var3 instanceof Some) {
         return ((Some)var3).value();
      } else if (None$.MODULE$.equals(var3)) {
         Object v = defaultValue.apply();
         Option var5 = this.putIfAbsent(key, v);
         if (var5 instanceof Some) {
            return ((Some)var5).value();
         } else if (None$.MODULE$.equals(var5)) {
            return v;
         } else {
            throw new MatchError(var5);
         }
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   static boolean removeRefEq$(final Map $this, final Object k, final Object v) {
      return $this.removeRefEq(k, v);
   }

   default boolean removeRefEq(final Object k, final Object v) {
      return this.remove(k, v);
   }

   // $FF: synthetic method
   static boolean replaceRefEq$(final Map $this, final Object k, final Object oldValue, final Object newValue) {
      return $this.replaceRefEq(k, oldValue, newValue);
   }

   default boolean replaceRefEq(final Object k, final Object oldValue, final Object newValue) {
      return this.replace(k, oldValue, newValue);
   }

   // $FF: synthetic method
   static Option updateWith$(final Map $this, final Object key, final Function1 remappingFunction) {
      return $this.updateWith(key, remappingFunction);
   }

   default Option updateWith(final Object key, final Function1 remappingFunction) {
      while(true) {
         Option updateWithAux_previousValue = this.get(key);
         Option updateWithAux_nextValue = (Option)remappingFunction.apply(updateWithAux_previousValue);
         if (updateWithAux_previousValue instanceof Some) {
            Object updateWithAux_prev = ((Some)updateWithAux_previousValue).value();
            if (updateWithAux_nextValue instanceof Some) {
               Object updateWithAux_next = ((Some)updateWithAux_nextValue).value();
               if (this.replaceRefEq(key, updateWithAux_prev, updateWithAux_next)) {
                  return updateWithAux_nextValue;
               }
            } else if (this.removeRefEq(key, updateWithAux_prev)) {
               return None$.MODULE$;
            }
         } else {
            if (updateWithAux_nextValue instanceof Some) {
               Object updateWithAux_next = ((Some)updateWithAux_nextValue).value();
               if (!this.putIfAbsent(key, updateWithAux_next).isEmpty()) {
                  continue;
               }

               return updateWithAux_nextValue;
            }

            return None$.MODULE$;
         }
      }
   }

   private Option updateWithAux(final Object key, final Function1 remappingFunction) {
      while(true) {
         Option previousValue = this.get(key);
         Option nextValue = (Option)remappingFunction.apply(previousValue);
         if (previousValue instanceof Some) {
            Object prev = ((Some)previousValue).value();
            if (nextValue instanceof Some) {
               Object next = ((Some)nextValue).value();
               if (this.replaceRefEq(key, prev, next)) {
                  return nextValue;
               }
            } else if (this.removeRefEq(key, prev)) {
               return None$.MODULE$;
            }
         } else {
            if (!(nextValue instanceof Some)) {
               return None$.MODULE$;
            }

            Object next = ((Some)nextValue).value();
            if (this.putIfAbsent(key, next).isEmpty()) {
               return nextValue;
            }
         }

         remappingFunction = remappingFunction;
         key = key;
      }
   }

   // $FF: synthetic method
   static Map filterInPlaceImpl$(final Map $this, final Function2 p) {
      return $this.filterInPlaceImpl(p);
   }

   default Map filterInPlaceImpl(final Function2 p) {
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Tuple2 var3 = (Tuple2)it.next();
         if (var3 == null) {
            throw new MatchError((Object)null);
         }

         Object k = var3._1();
         Object v = var3._2();
         if (!BoxesRunTime.unboxToBoolean(p.apply(k, v))) {
            this.removeRefEq(k, v);
         }
      }

      return this;
   }

   // $FF: synthetic method
   static Map mapValuesInPlaceImpl$(final Map $this, final Function2 f) {
      return $this.mapValuesInPlaceImpl(f);
   }

   default Map mapValuesInPlaceImpl(final Function2 f) {
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Tuple2 var3 = (Tuple2)it.next();
         if (var3 == null) {
            throw new MatchError((Object)null);
         }

         Object k = var3._1();
         Object v = var3._2();
         this.replaceRefEq(k, v, f.apply(k, v));
      }

      return this;
   }

   static void $init$(final Map $this) {
   }
}
