package scala.reflect.internal.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.ArrayOps;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t=g!B%K\u0003\u0003\u0019\u0006\"\u0002-\u0001\t\u0003IF!\u0002/\u0001\u0005\u0003iV\u0001\u00023\u0001\u0001\u0015DQ\u0001\u001d\u0001\u0007\u0002EDQ! \u0001\u0007\u0002yDq!!\u0003\u0001\r\u0003\tY\u0001C\u0004\u0002\u0018\u00011\t!!\u0007\t\u0011\u0005}\u0001\u0001)A\u0005\u0003CAq!a\u000e\u0001\t\u0013\tI\u0004C\u0004\u0002D\u0001!I!!\u0012\t\u000f\u00055\u0003\u0001\"\u0001\u0002P!9\u0011\u0011\u000b\u0001\u0005\u0002\u0005M\u0003bBA5\u0001\u0011\u0005\u00111\u000e\u0005\b\u0003[\u0002A\u0011AA6\u0011\u001d\ty\u0007\u0001C\u0001\u0003W:q!!\u001dK\u0011\u0003\t\u0019H\u0002\u0004J\u0015\"\u0005\u0011Q\u000f\u0005\u00071F!\t!a\u001e\t\u0011\u0005e\u0014\u0003)A\u0005\u0003wB\u0001\"!!\u0012A\u0003%\u00111\u0011\u0004\u0007\u0003\u000f\u000b\u0002)!#\t\u0013\u0005\rVC!f\u0001\n\u0003\t\b\"CAS+\tE\t\u0015!\u0003s\u0011%\t9+\u0006BK\u0002\u0013\u0005\u0011\u000fC\u0005\u0002*V\u0011\t\u0012)A\u0005e\"1\u0001,\u0006C\u0001\u0003WCq!!.\u0016\t\u0003\t9\fC\u0005\u0002<V\t\t\u0011\"\u0001\u0002>\"I\u00111Y\u000b\u0012\u0002\u0013\u0005\u0011Q\u0019\u0005\n\u00037,\u0012\u0013!C\u0001\u0003\u000bD\u0011\"!8\u0016\u0003\u0003%\t%a8\t\u0013\u0005\u0005X#!A\u0005\u0002\u0005\r\b\"CAs+\u0005\u0005I\u0011AAt\u0011%\ti/FA\u0001\n\u0003\ny\u000fC\u0005\u0002zV\t\t\u0011\"\u0001\u0002|\"I\u0011q`\u000b\u0002\u0002\u0013\u0005#\u0011\u0001\u0005\n\u0005\u000b)\u0012\u0011!C!\u0005\u000fA\u0011B!\u0003\u0016\u0003\u0003%\tEa\u0003\t\u0013\t5Q#!A\u0005B\t=q!\u0003B\n#\u0005\u0005\t\u0012\u0001B\u000b\r%\t9)EA\u0001\u0012\u0003\u00119\u0002\u0003\u0004YS\u0011\u0005!q\u0006\u0005\n\u0005\u0013I\u0013\u0011!C#\u0005\u0017A\u0011\"!\u0015*\u0003\u0003%\tI!\r\t\u0013\t]\u0012&!A\u0005\u0002\ne\u0002\"\u0003B&S\u0005\u0005I\u0011\u0002B'\u0011\u001d\u0011)&\u0005C\u0001\u0005/BqA!\u001a\u0012\t\u0003\u00119\u0007C\u0004\u0003nE!IAa\u001c\t\u000f\tM\u0014\u0003\"\u0003\u0003v!9\u0011\u0011K\t\u0005\u0002\t]\u0004bBA)#\u0011\u0005!1\u0010\u0004\u0007\u0005\u0007\u000b\u0002A!\"\t\u0011A,$Q1A\u0005\u0002ED\u0011Ba\"6\u0005\u0003\u0005\u000b\u0011\u0002:\t\u0015\t%UG!A!\u0002\u0013\ti\u000b\u0003\u0004Yk\u0011\u0005!1R\u0003\u00059V\u0002\u0001\u000e\u0003\u0004~k\u0011\u0005!1\u0013\u0005\b\u0003\u0013)D\u0011\u0001BL\u0011\u001d\t9\"\u000eC\u0001\u0005C3aA!*\u0012\u0001\t\u001d\u0006\u0002\u00039?\u0005\u000b\u0007I\u0011A9\t\u0013\t\u001deH!A!\u0002\u0013\u0011\bB\u0003BE}\t\u0005\t\u0015!\u0003\u0002.\"Q!\u0011\u0016 \u0003\u0002\u0003\u0006I!!\r\t\rasD\u0011\u0001BV\u000b\u0015af\b\u0001B[\u0011\u0019ih\b\"\u0001\u0003>\"9\u0011\u0011\u0002 \u0005\u0002\t\u0005\u0007bBA\f}\u0011\u0005!1\u001a\u0005\b\u0003\u001brD\u0011IA(\u0005\u001dy%/[4j]NT!a\u0013'\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001b:\u000b\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u001fB\u000bqA]3gY\u0016\u001cGOC\u0001R\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001+\u0011\u0005U3V\"\u0001)\n\u0005]\u0003&AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00025B\u00111\fA\u0007\u0002\u0015\n\u0019!+\u001a9\u0012\u0005y\u000b\u0007CA+`\u0013\t\u0001\u0007KA\u0004O_RD\u0017N\\4\u0011\u0005U\u0013\u0017BA2Q\u0005\r\te.\u001f\u0002\u000b'R\f7m[*mS\u000e,\u0007cA+gQ&\u0011q\r\u0015\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003S:l\u0011A\u001b\u0006\u0003W2\fA\u0001\\1oO*\tQ.\u0001\u0003kCZ\f\u0017BA8k\u0005E\u0019F/Y2l)J\f7-Z#mK6,g\u000e^\u0001\u0004i\u0006<W#\u0001:\u0011\u0005MThB\u0001;y!\t)\b+D\u0001w\u0015\t9(+\u0001\u0004=e>|GOP\u0005\u0003sB\u000ba\u0001\u0015:fI\u00164\u0017BA>}\u0005\u0019\u0019FO]5oO*\u0011\u0011\u0010U\u0001\tSN\u001cU\u000f^8gMR\u0019q0!\u0002\u0011\u0007U\u000b\t!C\u0002\u0002\u0004A\u0013qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002\b\u0015\u0001\r\u0001[\u0001\u0003K2\faA\\3x%\u0016\u0004H\u0003BA\u0007\u0003#\u00012!a\u0004\u0003\u001b\u0005\u0001\u0001bBA\n\r\u0001\u0007\u0011QC\u0001\u0003qN\u00042!a\u0004\u0004\u0003%\u0011X\r]*ue&tw\rF\u0002s\u00037Aq!!\b\b\u0001\u0004\ti!A\u0002sKB\fqa\u001c:jO&t7\u000f\u0005\u0005\u0002$\u00055\u0012QBA\u0019\u001b\t\t)C\u0003\u0003\u0002(\u0005%\u0012aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003W\u0001\u0016AC2pY2,7\r^5p]&!\u0011qFA\u0013\u0005\ri\u0015\r\u001d\t\u0004+\u0006M\u0012bAA\u001b!\n\u0019\u0011J\u001c;\u0002\u0007\u0005$G\r\u0006\u0003\u0002<\u0005\u0005\u0003cA+\u0002>%\u0019\u0011q\b)\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003'I\u0001\u0019AA\u0007\u0003\u0015!x\u000e^1m+\t\t9\u0005E\u0002V\u0003\u0013J1!a\u0013Q\u0005\u0011auN\\4\u0002\u0013I,\u0017\rZ*uC\u000e\\G#A3\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005U\u00131\f\u000b\u0005\u0003/\ny\u0006\u0005\u0003\u0002Z\u0005mC\u0002\u0001\u0003\u0007\u0003;b!\u0019A/\u0003\u0003QC\u0001\"!\u0019\r\t\u0003\u0007\u00111M\u0001\u0005E>$\u0017\u0010E\u0003V\u0003K\n9&C\u0002\u0002hA\u0013\u0001\u0002\u00102z]\u0006lWMP\u0001\u0006G2,\u0017M\u001d\u000b\u0003\u0003w\tAa\u001d5po\u0006)\u0001/\u001e:hK\u00069qJ]5hS:\u001c\bCA.\u0012'\t\tB\u000b\u0006\u0002\u0002t\u0005A1m\\;oi\u0016\u00148\u000f\u0005\u0004\u0002$\u0005u$OW\u0005\u0005\u0003\u007f\n)CA\u0004ICNDW*\u00199\u0002\u0013QD\u0017n]\"mCN\u001c\bcA5\u0002\u0006&\u00111P\u001b\u0002\t\u001fJLw-\u001b8JIN1Q\u0003VAF\u0003#\u00032!VAG\u0013\r\ty\t\u0015\u0002\b!J|G-^2u!\u0011\t\u0019*!(\u000f\t\u0005U\u0015\u0011\u0014\b\u0004k\u0006]\u0015\"A)\n\u0007\u0005m\u0005+A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0015\u0011\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u00037\u0003\u0016!C2mCN\u001ch*Y7f\u0003)\u0019G.Y:t\u001d\u0006lW\rI\u0001\u000b[\u0016$\bn\u001c3OC6,\u0017aC7fi\"|GMT1nK\u0002\"b!!,\u00022\u0006M\u0006cAAX+5\t\u0011\u0003\u0003\u0004\u0002$j\u0001\rA\u001d\u0005\u0007\u0003OS\u0002\u0019\u0001:\u0002\u000f5\fGo\u00195fgR\u0019q0!/\t\r\u0005\u001d1\u00041\u0001i\u0003\u0011\u0019w\u000e]=\u0015\r\u00055\u0016qXAa\u0011!\t\u0019\u000b\bI\u0001\u0002\u0004\u0011\b\u0002CAT9A\u0005\t\u0019\u0001:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0019\u0016\u0004e\u0006%7FAAf!\u0011\ti-a6\u000e\u0005\u0005='\u0002BAi\u0003'\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005U\u0007+\u0001\u0006b]:|G/\u0019;j_:LA!!7\u0002P\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a!\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005E\u0012A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004C\u0006%\b\"CAvC\u0005\u0005\t\u0019AA\u0019\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u001f\t\u0006\u0003g\f)0Y\u0007\u0003\u0003SIA!a>\u0002*\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ry\u0018Q \u0005\t\u0003W\u001c\u0013\u0011!a\u0001C\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\u0019Ia\u0001\t\u0013\u0005-H%!AA\u0002\u0005E\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005E\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\r\u0015AB3rk\u0006d7\u000fF\u0002\u0000\u0005#A\u0001\"a;(\u0003\u0003\u0005\r!Y\u0001\t\u001fJLw-\u001b8JIB\u0019\u0011qV\u0015\u0014\u000b%\u0012IB!\n\u0011\u0011\tm!\u0011\u0005:s\u0003[k!A!\b\u000b\u0007\t}\u0001+A\u0004sk:$\u0018.\\3\n\t\t\r\"Q\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003\u0002B\u0014\u0005[i!A!\u000b\u000b\u0007\t-B.\u0001\u0002j_&!\u0011q\u0014B\u0015)\t\u0011)\u0002\u0006\u0004\u0002.\nM\"Q\u0007\u0005\u0007\u0003Gc\u0003\u0019\u0001:\t\r\u0005\u001dF\u00061\u0001s\u0003\u001d)h.\u00199qYf$BAa\u000f\u0003HA)QK!\u0010\u0003B%\u0019!q\b)\u0003\r=\u0003H/[8o!\u0015)&1\t:s\u0013\r\u0011)\u0005\u0015\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\t%S&!AA\u0002\u00055\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\n\t\u0004S\nE\u0013b\u0001B*U\n1qJ\u00196fGR\fa\u0001\\8pWV\u0004H#\u0002.\u0003Z\tm\u0003\"\u000290\u0001\u0004\u0011\bb\u0002B/_\u0001\u0007!qL\u0001\u0007_J,En]3\u0011\u000bU\u0013\tG\u001d.\n\u0007\t\r\u0004KA\u0005Gk:\u001cG/[8oc\u0005A!/Z4jgR,'\u000fF\u0002[\u0005SBaAa\u001b1\u0001\u0004Q\u0016!\u0001=\u0002\u0013A\u0014XmQ;u_\u001a4GcA@\u0003r!1\u0011qA\u0019A\u0002!\f!BZ5oI\u000e+Ho\u001c4g)\t\ti\u000bF\u0002[\u0005sBQ\u0001]\u001aA\u0002I$RA\u0017B?\u0005\u007fBQ\u0001\u001d\u001bA\u0002IDqA!!5\u0001\u0004\t\t$\u0001\u0004ge\u0006lWm\u001d\u0002\b\u001f:,G*\u001b8f'\t)$,\u0001\u0003uC\u001e\u0004\u0013AA5e)\u0019\u0011iIa$\u0003\u0012B\u0019\u0011qV\u001b\t\u000bAL\u0004\u0019\u0001:\t\u000f\t%\u0015\b1\u0001\u0002.R\u0019qP!&\t\r\u0005\u001d1\b1\u0001i)\u0011\u0011IJ!(\u0011\u0007\tm%(D\u00016\u0011\u001d\t\u0019\u0002\u0010a\u0001\u0005?\u00032Aa'\u0004)\u0011\t\u0019Ia)\t\u000f\u0005uQ\b1\u0001\u0003\u001a\nIQ*\u001e7uS2Kg.Z\n\u0003}i\u000b\u0001B\\;n\u0019&tWm\u001d\u000b\t\u0005[\u0013yK!-\u00034B\u0019\u0011q\u0016 \t\u000bA\u001c\u0005\u0019\u0001:\t\u000f\t%5\t1\u0001\u0002.\"9!\u0011V\"A\u0002\u0005E\u0002#\u0002B\\\u0005sCgbA+\u0002\u001a&!!1XAQ\u0005\u0011a\u0015n\u001d;\u0015\u0007}\u0014y\f\u0003\u0004\u0002\b\u0015\u0003\r\u0001\u001b\u000b\u0005\u0005\u0007\u00149\rE\u0002\u0003F\u0012k\u0011A\u0010\u0005\b\u0003'1\u0005\u0019\u0001Be!\r\u0011)m\u0001\u000b\u0004e\n5\u0007bBA\u000f\u000f\u0002\u0007!1\u0019"
)
public abstract class Origins {
   private final Map origins = (new HashMap()).withDefaultValue(0);

   public static Origins register(final Origins x) {
      return Origins$.MODULE$.register(x);
   }

   public static Origins lookup(final String tag, final Function1 orElse) {
      return Origins$.MODULE$.lookup(tag, orElse);
   }

   public abstract String tag();

   public abstract boolean isCutoff(final StackTraceElement el);

   public abstract Object newRep(final StackTraceElement[] xs);

   public abstract String repString(final Object rep);

   private void add(final Object xs) {
      this.origins.update(xs, BoxesRunTime.unboxToInt(this.origins.apply(xs)) + 1);
   }

   private long total() {
      return BoxesRunTime.unboxToLong(this.origins.values().foldLeft(0L, (JFunction2.mcJJI.sp)(x$1, x$2) -> x$1 + (long)x$2));
   }

   public StackTraceElement[] readStack() {
      ArrayOps var10000 = .MODULE$;
      ArrayOps var10001 = .MODULE$;
      ArrayOps var10002 = .MODULE$;
      Object[] refArrayOps_xs = Thread.currentThread().getStackTrace();
      Object var15 = null;
      Object dropWhile$extension_$this = refArrayOps_xs;
      ArrayOps dropWhile$extension_this = var10002;
      int dropWhile$extension_indexWhere$extension_i = 0;

      while(true) {
         if (dropWhile$extension_indexWhere$extension_i >= ((Object[])dropWhile$extension_$this).length) {
            var23 = -1;
            break;
         }

         Object var13 = ((Object[])dropWhile$extension_$this)[dropWhile$extension_indexWhere$extension_i];
         if (!$anonfun$readStack$1(this, (StackTraceElement)var13)) {
            var23 = dropWhile$extension_indexWhere$extension_i;
            break;
         }

         ++dropWhile$extension_indexWhere$extension_i;
      }

      int dropWhile$extension_i = var23;
      int dropWhile$extension_lo = dropWhile$extension_i < 0 ? ((Object[])dropWhile$extension_$this).length : dropWhile$extension_i;
      Object var24 = dropWhile$extension_this.slice$extension(dropWhile$extension_$this, dropWhile$extension_lo, ((Object[])dropWhile$extension_$this).length);
      Object var17 = null;
      dropWhile$extension_$this = null;
      Object[] refArrayOps_xs = var24;
      Object var16 = null;
      Object[] dropWhile$extension_$this = refArrayOps_xs;
      ArrayOps dropWhile$extension_this = var10001;
      int dropWhile$extension_indexWhere$extension_i = 0;

      while(true) {
         if (dropWhile$extension_indexWhere$extension_i >= dropWhile$extension_$this.length) {
            var21 = -1;
            break;
         }

         StackTraceElement var14 = (StackTraceElement)dropWhile$extension_$this[dropWhile$extension_indexWhere$extension_i];
         if (!this.isCutoff(var14)) {
            var21 = dropWhile$extension_indexWhere$extension_i;
            break;
         }

         ++dropWhile$extension_indexWhere$extension_i;
      }

      int dropWhile$extension_i = var21;
      int dropWhile$extension_lo = dropWhile$extension_i < 0 ? dropWhile$extension_$this.length : dropWhile$extension_i;
      Object var22 = dropWhile$extension_this.slice$extension(dropWhile$extension_$this, dropWhile$extension_lo, dropWhile$extension_$this.length);
      Object var19 = null;
      dropWhile$extension_$this = null;
      return (StackTraceElement[])var10000.drop$extension(var22, 1);
   }

   public Object apply(final Function0 body) {
      this.add(this.newRep(this.readStack()));
      return body.apply();
   }

   public void clear() {
      this.origins.clear();
   }

   public void show() {
      Object println_x = scala.collection.StringOps..MODULE$.format$extension("\n>> Origins tag '%s' logged %s calls from %s distinguished sources.\n", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.tag(), this.total(), this.origins.keys().size()}));
      scala.Console..MODULE$.println(println_x);
      println_x = null;
      List var10000 = (List)this.origins.toList().sortBy((x$3) -> BoxesRunTime.boxToInteger($anonfun$show$1(x$3)), scala.math.Ordering.Int..MODULE$);
      if (var10000 == null) {
         throw null;
      } else {
         for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Tuple2 var3 = (Tuple2)foreach_these.head();
            $anonfun$show$2(this, var3);
         }

      }
   }

   public void purge() {
      this.show();
      this.clear();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readStack$1(final Origins $this, final StackTraceElement x) {
      return !$this.isCutoff(x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readStack$2(final Origins $this, final StackTraceElement el) {
      return $this.isCutoff(el);
   }

   // $FF: synthetic method
   public static final int $anonfun$show$1(final Tuple2 x$3) {
      return -x$3._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$show$2(final Origins $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         int v = x0$1._2$mcI$sp();
         Object println_x = scala.collection.StringOps..MODULE$.format$extension("%7s %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{v, $this.repString(k)}));
         scala.Console..MODULE$.println(println_x);
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$readStack$1$adapted(final Origins $this, final StackTraceElement x) {
      return BoxesRunTime.boxToBoolean($anonfun$readStack$1($this, x));
   }

   // $FF: synthetic method
   public static final Object $anonfun$readStack$2$adapted(final Origins $this, final StackTraceElement el) {
      return BoxesRunTime.boxToBoolean($anonfun$readStack$2($this, el));
   }

   // $FF: synthetic method
   public static final Object $anonfun$show$2$adapted(final Origins $this, final Tuple2 x0$1) {
      $anonfun$show$2($this, x0$1);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class OriginId implements Product, Serializable {
      private final String className;
      private final String methodName;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String className() {
         return this.className;
      }

      public String methodName() {
         return this.methodName;
      }

      public boolean matches(final StackTraceElement el) {
         String var10000 = this.methodName();
         String var2 = el.getMethodName();
         if (var10000 == null) {
            if (var2 != null) {
               return false;
            }
         } else if (!var10000.equals(var2)) {
            return false;
         }

         if (this.className().startsWith(el.getClassName())) {
            return true;
         } else {
            return false;
         }
      }

      public OriginId copy(final String className, final String methodName) {
         return new OriginId(className, methodName);
      }

      public String copy$default$1() {
         return this.className();
      }

      public String copy$default$2() {
         return this.methodName();
      }

      public String productPrefix() {
         return "OriginId";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.className();
            case 1:
               return this.methodName();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof OriginId;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "className";
            case 1:
               return "methodName";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof OriginId) {
               OriginId var2 = (OriginId)x$1;
               String var10000 = this.className();
               String var3 = var2.className();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.methodName();
               String var4 = var2.methodName();
               if (var10000 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var4)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public OriginId(final String className, final String methodName) {
         this.className = className;
         this.methodName = methodName;
      }
   }

   public static class OriginId$ extends AbstractFunction2 implements Serializable {
      public static final OriginId$ MODULE$ = new OriginId$();

      public final String toString() {
         return "OriginId";
      }

      public OriginId apply(final String className, final String methodName) {
         return new OriginId(className, methodName);
      }

      public Option unapply(final OriginId x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.className(), x$0.methodName())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(OriginId$.class);
      }
   }

   public static class OneLine extends Origins {
      private final String tag;
      private final OriginId id;

      public String tag() {
         return this.tag;
      }

      public boolean isCutoff(final StackTraceElement el) {
         return this.id.matches(el);
      }

      public StackTraceElement newRep(final StackTraceElement[] xs) {
         return xs != null && xs.length != 0 ? xs[0] : null;
      }

      public String repString(final StackTraceElement rep) {
         return (new StringBuilder(2)).append("  ").append(rep).toString();
      }

      public OneLine(final String tag, final OriginId id) {
         this.tag = tag;
         this.id = id;
      }
   }

   public static class MultiLine extends Origins {
      private final String tag;
      private final OriginId id;
      private final int numLines;

      public String tag() {
         return this.tag;
      }

      public boolean isCutoff(final StackTraceElement el) {
         return this.id.matches(el);
      }

      public List newRep(final StackTraceElement[] xs) {
         int take$extension_n = this.numLines;
         ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(.MODULE$.slice$extension(xs, 0, take$extension_n));
         if (var10000 == null) {
            throw null;
         } else {
            return IterableOnceOps.toList$(var10000);
         }
      }

      public String repString(final List rep) {
         if (rep == null) {
            throw null;
         } else {
            Object var10000;
            if (rep == scala.collection.immutable.Nil..MODULE$) {
               var10000 = scala.collection.immutable.Nil..MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$repString$1((StackTraceElement)rep.head()), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)rep.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$repString$1((StackTraceElement)map_rest.head()), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10000 = map_h;
            }

            Object var8 = null;
            Object var9 = null;
            Object var10 = null;
            Object var11 = null;
            AbstractIterable mkString_this = (AbstractIterable)var10000;
            String mkString_mkString_sep = "";
            return mkString_this.mkString("", mkString_mkString_sep, "");
         }
      }

      public StackTraceElement[] readStack() {
         return (StackTraceElement[]).MODULE$.drop$extension(super.readStack(), 1);
      }

      // $FF: synthetic method
      public static final String $anonfun$repString$1(final StackTraceElement x$5) {
         return (new StringBuilder(3)).append("\n  ").append(x$5).toString();
      }

      public MultiLine(final String tag, final OriginId id, final int numLines) {
         this.tag = tag;
         this.id = id;
         this.numLines = numLines;
      }
   }
}
