package org.apache.spark.ml.param;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.NoSuchElementException;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\teb\u0001B\u000e\u001d\u0005\u001dB\u0001B\u000f\u0001\u0003\u0006\u0004%Ia\u000f\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005y!1A\n\u0001C\u0001=5CQ\u0001\u0014\u0001\u0005\u0002ACQA\u0017\u0001\u0005\u0002mCQA\u0017\u0001\u0005\u0002-DaA\u0017\u0001\u0005\u0002yy\bbBA\u000f\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011\u001d\tY\u0005\u0001C\u0001\u0003\u001bBq!a\u0017\u0001\t\u0003\ti\u0006C\u0004\u0002t\u0001!\t!!\u001e\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u0011Q\u0013\u0001\u0005\u0002\u0005]\u0005bBAN\u0001\u0011\u0005\u0013Q\u0014\u0005\b\u0003c\u0003A\u0011AAZ\u0011\u001d\tY\f\u0001C\u0001\u0003{Cq!a1\u0001\t\u0003\t)\r\u0003\u0005\u0002Z\u0002!\tAHAn\u0011\u001d\tI\u000f\u0001C\u0001\u0003W<q!a?\u001d\u0011\u0003\tiP\u0002\u0004\u001c9!\u0005\u0011q \u0005\u0007\u0019Z!\tAa\u0003\t\u000f\t5a\u0003\"\u0001\u0002\u0018\"9\u00111\n\f\u0005\u0002\tE\u0001\"\u0003B\u0013-\u0005\u0005I\u0011\u0002B\u0014\u0005!\u0001\u0016M]1n\u001b\u0006\u0004(BA\u000f\u001f\u0003\u0015\u0001\u0018M]1n\u0015\ty\u0002%\u0001\u0002nY*\u0011\u0011EI\u0001\u0006gB\f'o\u001b\u0006\u0003G\u0011\na!\u00199bG\",'\"A\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001Ac\u0006\u0005\u0002*Y5\t!FC\u0001,\u0003\u0015\u00198-\u00197b\u0013\ti#F\u0001\u0004B]f\u0014VM\u001a\t\u0003_]r!\u0001M\u001b\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005M2\u0013A\u0002\u001fs_>$h(C\u0001,\u0013\t1$&A\u0004qC\u000e\\\u0017mZ3\n\u0005aJ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001c+\u0003\ri\u0017\r]\u000b\u0002yA!QH\u0011#I\u001b\u0005q$BA A\u0003\u001diW\u000f^1cY\u0016T!!\u0011\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002D}\t\u0019Q*\u00199\u0011\u0007\u00153\u0005*D\u0001\u001d\u0013\t9EDA\u0003QCJ\fW\u000e\u0005\u0002*\u0013&\u0011!J\u000b\u0002\u0004\u0003:L\u0018\u0001B7ba\u0002\na\u0001P5oSRtDC\u0001(P!\t)\u0005\u0001C\u0003;\u0007\u0001\u0007A\bF\u0001OQ\r!!\u000b\u0017\t\u0003'Zk\u0011\u0001\u0016\u0006\u0003+\u0002\n!\"\u00198o_R\fG/[8o\u0013\t9FKA\u0003TS:\u001cW-I\u0001Z\u0003\u0015\tdF\r\u00181\u0003\r\u0001X\u000f^\u000b\u00039\n$2!\u00180i\u001b\u0005\u0001\u0001\"B\u000f\u0006\u0001\u0004y\u0006cA#GAB\u0011\u0011M\u0019\u0007\u0001\t\u0015\u0019WA1\u0001e\u0005\u0005!\u0016CA3I!\tIc-\u0003\u0002hU\t9aj\u001c;iS:<\u0007\"B5\u0006\u0001\u0004\u0001\u0017!\u0002<bYV,\u0007fA\u0003S1R\u0011Q\f\u001c\u0005\u0006[\u001a\u0001\rA\\\u0001\u000ba\u0006\u0014\u0018-\u001c)bSJ\u001c\bcA\u0015pc&\u0011\u0001O\u000b\u0002\u000byI,\u0007/Z1uK\u0012t\u0004G\u0001:w!\r)5/^\u0005\u0003ir\u0011\u0011\u0002U1sC6\u0004\u0016-\u001b:\u0011\u0005\u00054H!C<m\u0003\u0003\u0005\tQ!\u0001e\u0005\u0011yF%\r\u001c)\u0005\u0019I\bC\u0001>}\u001b\u0005Y(BA++\u0013\ti8PA\u0004wCJ\f'oZ:)\u0007\u0019\u0011\u0006\fF\u0002^\u0003\u0003Aa!\\\u0004A\u0002\u0005\r\u0001CBA\u0003\u0003\u001f\t\u0019\"\u0004\u0002\u0002\b)!\u0011\u0011BA\u0006\u0003\u0011)H/\u001b7\u000b\u0005\u00055\u0011\u0001\u00026bm\u0006LA!!\u0005\u0002\b\t!A*[:ua\u0011\t)\"!\u0007\u0011\t\u0015\u001b\u0018q\u0003\t\u0004C\u0006eAaCA\u000e\u0003\u0003\t\t\u0011!A\u0003\u0002\u0011\u0014Aa\u0018\u00132o\u0005\u0019q-\u001a;\u0016\t\u0005\u0005\u00121\u0006\u000b\u0005\u0003G\ti\u0003E\u0003*\u0003K\tI#C\u0002\u0002()\u0012aa\u00149uS>t\u0007cA1\u0002,\u0011)1\r\u0003b\u0001I\"1Q\u0004\u0003a\u0001\u0003_\u0001B!\u0012$\u0002*!\u001a\u0001B\u0015-\u0002\u0013\u001d,Go\u0014:FYN,W\u0003BA\u001c\u0003w!b!!\u000f\u0002>\u0005\u0005\u0003cA1\u0002<\u0011)1-\u0003b\u0001I\"1Q$\u0003a\u0001\u0003\u007f\u0001B!\u0012$\u0002:!9\u00111I\u0005A\u0002\u0005e\u0012a\u00023fM\u0006,H\u000e\u001e\u0015\u0005\u0013I\u000b9%\t\u0002\u0002J\u0005)\u0011G\f\u001b/a\u0005)\u0011\r\u001d9msV!\u0011qJA*)\u0011\t\t&!\u0016\u0011\u0007\u0005\f\u0019\u0006B\u0003d\u0015\t\u0007A\r\u0003\u0004\u001e\u0015\u0001\u0007\u0011q\u000b\t\u0005\u000b\u001a\u000b\t\u0006K\u0002\u000b%b\u000b\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u0003?\n)\u0007E\u0002*\u0003CJ1!a\u0019+\u0005\u001d\u0011un\u001c7fC:Da!H\u0006A\u0002\u0005\u001d\u0004\u0007BA5\u0003[\u0002B!\u0012$\u0002lA\u0019\u0011-!\u001c\u0005\u0017\u0005=\u0014QMA\u0001\u0002\u0003\u0015\t\u0001\u001a\u0002\u0005?\u0012\n\u0004\bK\u0002\f%b\u000baA]3n_Z,W\u0003BA<\u0003{\"B!!\u001f\u0002\u0000A)\u0011&!\n\u0002|A\u0019\u0011-! \u0005\u000b\rd!\u0019\u00013\t\rua\u0001\u0019AAA!\u0011)e)a\u001f)\t1\u0011\u0016qI\u0001\u0007M&dG/\u001a:\u0015\u00079\u000bI\tC\u0004\u0002\f6\u0001\r!!$\u0002\rA\f'/\u001a8u!\r)\u0015qR\u0005\u0004\u0003#c\"A\u0002)be\u0006l7\u000fK\u0002\u000e%b\u000bAaY8qsV\ta\nK\u0002\u000f%b\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003?\u0003B!!)\u0002*:!\u00111UAS!\t\t$&C\u0002\u0002(*\na\u0001\u0015:fI\u00164\u0017\u0002BAV\u0003[\u0013aa\u0015;sS:<'bAATU!\u001aqB\u0015-\u0002\u0015\u0011\u0002H.^:%a2,8\u000fF\u0002O\u0003kCa!a.\u0011\u0001\u0004q\u0015!B8uQ\u0016\u0014\bf\u0001\tS1\u0006iA\u0005\u001d7vg\u0012\u0002H.^:%KF$2!XA`\u0011\u0019\t9,\u0005a\u0001\u001d\"\u001a\u0011C\u0015-\u0002\u000bQ|7+Z9\u0016\u0005\u0005\u001d\u0007#B\u0018\u0002J\u00065\u0017bAAfs\t\u00191+Z91\t\u0005=\u00171\u001b\t\u0005\u000bN\f\t\u000eE\u0002b\u0003'$!\"!6\u0013\u0003\u0003\u0005\tQ!\u0001e\u0005\u0011yF%M\u001d)\u0007I\u0011\u0006,\u0001\u0004u_2K7\u000f^\u000b\u0003\u0003;\u0004b!!\u0002\u0002\u0010\u0005}\u0007\u0007BAq\u0003K\u0004B!R:\u0002dB\u0019\u0011-!:\u0005\u0015\u0005\u001d8#!A\u0001\u0002\u000b\u0005AM\u0001\u0003`II\u0002\u0014\u0001B:ju\u0016,\"!!<\u0011\u0007%\ny/C\u0002\u0002r*\u00121!\u00138uQ\u0011!\"+!>\"\u0005\u0005]\u0018!B\u0019/g9\u0002\u0004f\u0001\u0001S1\u0006A\u0001+\u0019:b[6\u000b\u0007\u000f\u0005\u0002F-M!a\u0003\u000bB\u0001!\u0011\u0011\u0019A!\u0003\u000e\u0005\t\u0015!\u0002\u0002B\u0004\u0003\u0017\t!![8\n\u0007a\u0012)\u0001\u0006\u0002\u0002~\u0006)Q-\u001c9us\"\u001a\u0001D\u0015-\u0015\u00079\u0013\u0019\u0002\u0003\u0004n3\u0001\u0007!Q\u0003\t\u0005S=\u00149\u0002\r\u0003\u0003\u001a\tu\u0001\u0003B#t\u00057\u00012!\u0019B\u000f\t-\u0011yBa\u0005\u0002\u0002\u0003\u0005)\u0011\u00013\u0003\t}##'\r\u0015\u00033eD3!\u0007*Y\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011I\u0003\u0005\u0003\u0003,\tERB\u0001B\u0017\u0015\u0011\u0011y#a\u0003\u0002\t1\fgnZ\u0005\u0005\u0005g\u0011iC\u0001\u0004PE*,7\r\u001e\u0015\u0004-IC\u0006fA\u000bS1\u0002"
)
public final class ParamMap implements Serializable {
   private final Map map;

   public static ParamMap empty() {
      return ParamMap$.MODULE$.empty();
   }

   public ParamMap put(final ParamPair... paramPairs) {
      return this.put((Seq).MODULE$.wrapRefArray(paramPairs));
   }

   private Map map() {
      return this.map;
   }

   public ParamMap put(final Param param, final Object value) {
      return this.put((Seq).MODULE$.wrapRefArray(new ParamPair[]{param.$minus$greater(value)}));
   }

   public ParamMap put(final Seq paramPairs) {
      paramPairs.foreach((p) -> {
         $anonfun$put$1(this, p);
         return BoxedUnit.UNIT;
      });
      return this;
   }

   public ParamMap put(final List paramPairs) {
      return this.put(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(paramPairs).asScala().toSeq());
   }

   public Option get(final Param param) {
      return this.map().get(param);
   }

   public Object getOrElse(final Param param, final Object default) {
      return this.get(param).getOrElse(() -> default);
   }

   public Object apply(final Param param) {
      return this.get(param).getOrElse(() -> {
         throw new NoSuchElementException("Cannot find param " + param.name() + ".");
      });
   }

   public boolean contains(final Param param) {
      return this.map().contains(param);
   }

   public Option remove(final Param param) {
      return this.map().remove(param);
   }

   public ParamMap filter(final Params parent) {
      Map filtered = (Map)this.map().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(parent, x0$1)));
      return new ParamMap(filtered);
   }

   public ParamMap copy() {
      return new ParamMap((Map)this.map().clone());
   }

   public String toString() {
      return ((IterableOnceOps)((IterableOps)this.map().toSeq().sortBy((x$15) -> ((Param)x$15._1()).name(), scala.math.Ordering.String..MODULE$)).map((x0$1) -> {
         if (x0$1 != null) {
            Param param = (Param)x0$1._1();
            Object value = x0$1._2();
            String var10000 = param.parent();
            return "\t" + var10000 + "-" + param.name() + ": " + value;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("{\n", ",\n", "\n}");
   }

   public ParamMap $plus$plus(final ParamMap other) {
      return new ParamMap((Map)this.map().$plus$plus(other.map()));
   }

   public ParamMap $plus$plus$eq(final ParamMap other) {
      this.map().$plus$plus$eq(other.map());
      return this;
   }

   public Seq toSeq() {
      return (Seq)this.map().toSeq().map((x0$1) -> {
         if (x0$1 != null) {
            Param param = (Param)x0$1._1();
            Object value = x0$1._2();
            return new ParamPair(param, value);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public List toList() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(this.toSeq()).asJava();
   }

   public int size() {
      return this.map().size();
   }

   // $FF: synthetic method
   public static final void $anonfun$put$1(final ParamMap $this, final ParamPair p) {
      $this.map().update(p.param(), p.value());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$1(final Params parent$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var6;
         label30: {
            Param k = (Param)x0$1._1();
            String var10000 = k.parent();
            String var5 = parent$1.uid();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public ParamMap(final Map map) {
      this.map = map;
   }

   public ParamMap() {
      this((Map)scala.collection.mutable.Map..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
