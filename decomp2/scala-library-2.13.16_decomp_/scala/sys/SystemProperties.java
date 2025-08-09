package scala.sys;

import java.lang.invoke.SerializedLambda;
import java.security.AccessControlException;
import java.util.Properties;
import java.util.Set;
import scala.Function0;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.MapOps;
import scala.collection.convert.AsScalaExtensions;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.AbstractMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map$;
import scala.jdk.CollectionConverters$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d\u0001\u0002\u0011\"\u0001\u0019BQA\u000f\u0001\u0005\u0002mBQA\u0010\u0001\u0005B}BQa\u0011\u0001\u0005B\u0011CQa\u0012\u0001\u0005\u0002!CQ!\u0015\u0001\u0005BICQA\u0016\u0001\u0005\u0002]CQ!\u0017\u0001\u0005\u0002iCQa\u0018\u0001\u0005B\u0001DQA\u0019\u0001\u0005B\rDQa\u001a\u0001\u0005\u0002!DQa\u001b\u0001\u0005\u00021DQa\u001c\u0001\u0005\u0002ADa\"a\u0002\u0001!\u0003\r\t\u0011!C\u0005\u0003\u0013\tiaB\u0004\u0002\u0014\u0005B\t!!\u0006\u0007\r\u0001\n\u0003\u0012AA\f\u0011\u0019Qt\u0002\"\u0001\u0002 !9\u0011\u0011E\b\u0005\u0002\u0005\r\u0002bBA\u0018\u001f\u0011\r\u0011\u0011\u0007\u0005\n\u0003sy!\u0019!C\u0007\u0003wA\u0001\"a\u0011\u0010A\u00035\u0011Q\b\u0005\n\u0003\u000bz!\u0019!C\u0007\u0003\u000fB\u0001\"a\u0014\u0010A\u00035\u0011\u0011\n\u0005\n\u0003#z!\u0019!C\u0007\u0003'B\u0001\"a\u0017\u0010A\u00035\u0011Q\u000b\u0005\n\u0003;z!\u0019!C\u0007\u0003?B\u0001\"a\u001a\u0010A\u00035\u0011\u0011\r\u0005\b\u0003SzA\u0011AA6\u0011)\tyg\u0004EC\u0002\u0013\u0005\u0011\u0011\u000f\u0005\u000b\u0003sz\u0001R1A\u0005\u0002\u0005E\u0004BCA>\u001f!\u0015\r\u0011\"\u0001\u0002r!Q\u0011QP\b\t\u0006\u0004%\t!!\u001d\u0003!MK8\u000f^3n!J|\u0007/\u001a:uS\u0016\u001c(B\u0001\u0012$\u0003\r\u0019\u0018p\u001d\u0006\u0002I\u0005)1oY1mC\u000e\u00011C\u0001\u0001(!\u0011ASfL\u0018\u000e\u0003%R!AK\u0016\u0002\u000f5,H/\u00192mK*\u0011AfI\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u0018*\u0005-\t%m\u001d;sC\u000e$X*\u00199\u0011\u0005A:dBA\u00196!\t\u00114%D\u00014\u0015\t!T%\u0001\u0004=e>|GOP\u0005\u0003m\r\na\u0001\u0015:fI\u00164\u0017B\u0001\u001d:\u0005\u0019\u0019FO]5oO*\u0011agI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003q\u0002\"!\u0010\u0001\u000e\u0003\u0005\nQ!Z7qif,\u0012\u0001\u0011\t\u0005Q\u0005{s&\u0003\u0002CS\t\u0019Q*\u00199\u0002\u000f\u0011,g-Y;miR\u0011q&\u0012\u0005\u0006\r\u000e\u0001\raL\u0001\u0004W\u0016L\u0018\u0001C5uKJ\fGo\u001c:\u0016\u0003%\u00032AS&N\u001b\u0005Y\u0013B\u0001',\u0005!IE/\u001a:bi>\u0014\b\u0003\u0002(P_=j\u0011aI\u0005\u0003!\u000e\u0012a\u0001V;qY\u0016\u0014\u0014aB5t\u000b6\u0004H/_\u000b\u0002'B\u0011a\nV\u0005\u0003+\u000e\u0012qAQ8pY\u0016\fg.A\u0003oC6,7/F\u0001Y!\rQ5jL\u0001\u0004O\u0016$HCA._!\rqElL\u0005\u0003;\u000e\u0012aa\u00149uS>t\u0007\"\u0002$\b\u0001\u0004y\u0013\u0001C2p]R\f\u0017N\\:\u0015\u0005M\u000b\u0007\"\u0002$\t\u0001\u0004y\u0013!B2mK\u0006\u0014H#\u00013\u0011\u00059+\u0017B\u00014$\u0005\u0011)f.\u001b;\u0002\u0017M,(\r\u001e:bGR|e.\u001a\u000b\u0003S*l\u0011\u0001\u0001\u0005\u0006\r*\u0001\raL\u0001\u0007C\u0012$wJ\\3\u0015\u0005%l\u0007\"\u00028\f\u0001\u0004i\u0015AA6w\u0003)9(/\u00199BG\u000e,7o]\u000b\u0003cV$\"A\u001d@\u0011\u00079c6\u000f\u0005\u0002uk2\u0001A!\u0002<\r\u0005\u00049(!\u0001+\u0012\u0005a\\\bC\u0001(z\u0013\tQ8EA\u0004O_RD\u0017N\\4\u0011\u00059c\u0018BA?$\u0005\r\te.\u001f\u0005\b\u007f2!\t\u0019AA\u0001\u0003\u0011\u0011w\u000eZ=\u0011\t9\u000b\u0019a]\u0005\u0004\u0003\u000b\u0019#\u0001\u0003\u001fcs:\fW.\u001a \u0002\u001dM,\b/\u001a:%G>tG/Y5ogR\u00191+a\u0003\t\u000b\u0019k\u0001\u0019A\u0018\n\u0007}\u000by!C\u0002\u0002\u0012-\u0012a!T1q\u001fB\u001c\u0018\u0001E*zgR,W\u000e\u0015:pa\u0016\u0014H/[3t!\titbE\u0002\u0010\u00033\u00012ATA\u000e\u0013\r\tib\t\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\u0005U\u0011aC3yG2,8/\u001b<fYf,B!!\n\u0002*Q!\u0011qEA\u0016!\r!\u0018\u0011\u0006\u0003\u0006mF\u0011\ra\u001e\u0005\b\u007fF!\t\u0019AA\u0017!\u0015q\u00151AA\u0014\u0003m\u0019\u0018p\u001d;f[B\u0013x\u000e]3si&,7\u000fV8D_6\u0004\u0018M\\5p]R!\u00111GA\u001b\u001d\tid\u0002\u0003\u0004\u00028I\u0001\r\u0001P\u0001\u0002a\u0006Y\u0001*Z1eY\u0016\u001c8oS3z+\t\tid\u0004\u0002\u0002@\u0005\u0012\u0011\u0011I\u0001\u0012U\u00064\u0018ML1xi:BW-\u00193mKN\u001c\u0018\u0001\u0004%fC\u0012dWm]:LKf\u0004\u0013A\u0005)sK\u001a,'/\u0013)wiM#\u0018mY6LKf,\"!!\u0013\u0010\u0005\u0005-\u0013EAA'\u0003aQ\u0017M^1/]\u0016$h\u0006\u001d:fM\u0016\u0014\u0018\n\u0015<5'R\f7m[\u0001\u0014!J,g-\u001a:J!Z$4\u000b^1dW.+\u0017\u0010I\u0001\u0017!J,g-\u001a:J!Z4\u0014\t\u001a3sKN\u001cXm]&fsV\u0011\u0011QK\b\u0003\u0003/\n#!!\u0017\u00029)\fg/\u0019\u0018oKRt\u0003O]3gKJL\u0005K\u001e\u001cBI\u0012\u0014Xm]:fg\u00069\u0002K]3gKJL\u0005K\u001e\u001cBI\u0012\u0014Xm]:fg.+\u0017\u0010I\u0001\u0016\u001d>$&/Y2f'V\u0004\bO]3tg&|gnS3z+\t\t\tg\u0004\u0002\u0002d\u0005\u0012\u0011QM\u0001!g\u000e\fG.\u0019\u0018d_:$(o\u001c7/]>$&/Y2f'V\u0004\bO]3tg&|g.\u0001\fO_R\u0013\u0018mY3TkB\u0004(/Z:tS>t7*Z=!\u0003\u0011AW\r\u001c9\u0015\u0007=\ni\u0007C\u0003G7\u0001\u0007q&\u0001\u0005iK\u0006$G.Z:t+\t\t\u0019\bE\u0002>\u0003kJ1!a\u001e\"\u0005-\u0011un\u001c7fC:\u0004&o\u001c9\u0002\u001fA\u0014XMZ3s\u0013B3Hg\u0015;bG.\f1\u0003\u001d:fM\u0016\u0014\u0018\n\u0015<7\u0003\u0012$'/Z:tKN\f!C\\8Ue\u0006\u001cWmU;qaJ,7o]5p]\u0002"
)
public class SystemProperties extends AbstractMap {
   public static BooleanProp noTraceSuppression() {
      return SystemProperties$.MODULE$.noTraceSuppression();
   }

   public static BooleanProp preferIPv6Addresses() {
      return SystemProperties$.MODULE$.preferIPv6Addresses();
   }

   public static BooleanProp preferIPv4Stack() {
      return SystemProperties$.MODULE$.preferIPv4Stack();
   }

   public static BooleanProp headless() {
      return SystemProperties$.MODULE$.headless();
   }

   public static String help(final String key) {
      return SystemProperties$.MODULE$.help(key);
   }

   public static SystemProperties$ systemPropertiesToCompanion(final SystemProperties p) {
      return SystemProperties$.MODULE$;
   }

   public static Object exclusively(final Function0 body) {
      return SystemProperties$.MODULE$.exclusively(body);
   }

   // $FF: synthetic method
   private boolean super$contains(final String key) {
      return MapOps.contains$(this, key);
   }

   public Map empty() {
      return (Map)Map$.MODULE$.apply(Nil$.MODULE$);
   }

   public String default(final String key) {
      return null;
   }

   public Iterator iterator() {
      Option var10000 = this.wrapAccess(() -> {
         Properties ps = System.getProperties();
         return this.names().map((k) -> new Tuple2(k, ps.getProperty(k))).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$iterator$3(x$1)));
      });
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         if (getOrElse_this.isEmpty()) {
            Iterator$ var2 = Iterator$.MODULE$;
            var10000 = Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = (Option)getOrElse_this.get();
         }

         return (Iterator)var10000;
      }
   }

   public boolean isEmpty() {
      return this.iterator().isEmpty();
   }

   public Iterator names() {
      Option var10000 = this.wrapAccess(() -> {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         Set SetHasAsScala_s = System.getProperties().stringPropertyNames();
         CollectionConverters$ SetHasAsScala_this = var10000;
         AsScalaExtensions.SetHasAsScala var4 = SetHasAsScala_this.new SetHasAsScala(SetHasAsScala_s);
         SetHasAsScala_this = null;
         Object var3 = null;
         return var4.asScala().iterator();
      });
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         if (getOrElse_this.isEmpty()) {
            Iterator$ var2 = Iterator$.MODULE$;
            var10000 = Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = (Option)getOrElse_this.get();
         }

         return (Iterator)var10000;
      }
   }

   public Option get(final String key) {
      Option var10000 = this.wrapAccess(() -> Option$.MODULE$.apply(System.getProperty(key)));
      if (var10000 == null) {
         throw null;
      } else {
         Option flatMap_this = var10000;
         return (Option)(flatMap_this.isEmpty() ? None$.MODULE$ : (Option)flatMap_this.get());
      }
   }

   public boolean contains(final String key) {
      Option var10000 = this.wrapAccess(() -> this.super$contains(key));
      if (var10000 == null) {
         throw null;
      } else {
         Option exists_this = var10000;
         return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_this.get());
      }
   }

   public void clear() {
      this.wrapAccess(() -> System.getProperties().clear());
   }

   public SystemProperties subtractOne(final String key) {
      this.wrapAccess(() -> System.clearProperty(key));
      return this;
   }

   public SystemProperties addOne(final Tuple2 kv) {
      this.wrapAccess(() -> System.setProperty((String)kv._1(), (String)kv._2()));
      return this;
   }

   public Option wrapAccess(final Function0 body) {
      try {
         return new Some(body.apply());
      } catch (AccessControlException var2) {
         return None$.MODULE$;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$3(final Tuple2 x$1) {
      return x$1._2() != null;
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$iterator$4() {
      return Iterator$.MODULE$.empty();
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$names$2() {
      return Iterator$.MODULE$.empty();
   }

   // $FF: synthetic method
   public static final Option $anonfun$get$2(final Option x) {
      return x;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$contains$2(final boolean x) {
      return x;
   }

   // $FF: synthetic method
   public static final Object $anonfun$contains$2$adapted(final Object x) {
      return BoxesRunTime.boxToBoolean($anonfun$contains$2(BoxesRunTime.unboxToBoolean(x)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
