package scala.reflect;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.SetOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.mutable.ArrayBuilder;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0005mh!\u0003\n\u0014!\u0003\r\t\u0001GAz\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u0015Q\u0005\u0001\"\u0003L\u0011\u0015i\u0006\u0001\"\u0003_\u0011\u0015)\b\u0001\"\u0001w\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001fAq!a\b\u0001\t\u0003\n\t\u0003C\u0004\u0002(\u0001!\t\"!\u000b\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u0011q\u000b\u0001\u0005\u0002\u0005e\u0003bBA7\u0001\u0011\u0005\u0011q\u000e\u0005\b\u0003w\u0002A\u0011AA?\u0011\u001d\tI\t\u0001C\u0001\u0003\u0017Cq!a&\u0001\t\u0003\tI\nC\u0004\u00024\u0002!\t!!.\t\u000f\u0005\r\u0007\u0001\"\u0001\u0002F\"9\u0011\u0011\u001c\u0001\u0005\u0012\u0005m'aG\"mCN\u001cX*\u00198jM\u0016\u001cH\u000fR3qe\u0016\u001c\u0017\r^3e\u0003BL7O\u0003\u0002\u0015+\u00059!/\u001a4mK\u000e$(\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0011\u0004J\n\u0004\u0001iq\u0002CA\u000e\u001d\u001b\u0005)\u0012BA\u000f\u0016\u0005\u0019\te.\u001f*fMB\u0019q\u0004\t\u0012\u000e\u0003MI!!I\n\u0003\u0017=\u0003H/T1oS\u001a,7\u000f\u001e\t\u0003G\u0011b\u0001\u0001B\u0003&\u0001\t\u0007aEA\u0001U#\t9#\u0006\u0005\u0002\u001cQ%\u0011\u0011&\u0006\u0002\b\u001d>$\b.\u001b8h!\tY2&\u0003\u0002-+\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005y\u0003CA\u000e1\u0013\t\tTC\u0001\u0003V]&$\u0018aB3sCN,(/Z\u000b\u0002iA\u0012QG\u0010\t\u0004mmjT\"A\u001c\u000b\u0005aJ\u0014\u0001\u00027b]\u001eT\u0011AO\u0001\u0005U\u00064\u0018-\u0003\u0002=o\t)1\t\\1tgB\u00111E\u0010\u0003\n\u007f\t\t\t\u0011!A\u0003\u0002\u0019\u00121a\u0018\u00132Q\u0019\u0011\u0011\tR#H\u0011B\u00111DQ\u0005\u0003\u0007V\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013AR\u0001\u0019kN,\u0007E];oi&lWm\u00117bgN\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017%A%\u0002\rIr\u0013\u0007\r\u00181\u0003\u001d\u0019XO\u0019;za\u0016$2\u0001T(W!\tYR*\u0003\u0002O+\t9!i\\8mK\u0006t\u0007\"\u0002)\u0004\u0001\u0004\t\u0016aA:vEB\u0012!\u000b\u0016\t\u0004mm\u001a\u0006CA\u0012U\t%)v*!A\u0001\u0002\u000b\u0005aEA\u0002`IIBQaV\u0002A\u0002a\u000b1a];qa\tI6\fE\u00027wi\u0003\"aI.\u0005\u0013q3\u0016\u0011!A\u0001\u0006\u00031#aA0%g\u000591/\u001e2be\u001e\u001cHc\u0001'`[\")\u0001\r\u0002a\u0001C\u0006)\u0011M]4tcA\u0019!-\u001a5\u000f\u0005m\u0019\u0017B\u00013\u0016\u0003\u001d\u0001\u0018mY6bO\u0016L!AZ4\u0003\t1K7\u000f\u001e\u0006\u0003IV\u0001$![6\u0011\u0007}\u0001#\u000e\u0005\u0002$W\u0012IAnXA\u0001\u0002\u0003\u0015\tA\n\u0002\u0004?\u00122\u0004\"\u00028\u0005\u0001\u0004y\u0017!B1sON\u0014\u0004c\u00012faB\u0012\u0011o\u001d\t\u0004?\u0001\u0012\bCA\u0012t\t%!X.!A\u0001\u0002\u000b\u0005aEA\u0002`I]\n\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0015\u00051;\b\"\u0002=\u0006\u0001\u0004I\u0018\u0001\u0002;iCR\u00044A_A\u0002!\u0011YX0!\u0001\u000f\u0005}a\u0018B\u00013\u0014\u0013\tqxPA\u0007DY\u0006\u001c8/T1oS\u001a,7\u000f\u001e\u0006\u0003IN\u00012aIA\u0002\t)\t)a^A\u0001\u0002\u0003\u0015\tA\n\u0002\u0004?\u0012B\u0004fB\u0003B\t\u0006%q\tS\u0011\u0003\u0003\u0017\tq)^:fAM\u001c\u0017\r\\1/e\u00164G.Z2u]I,h\u000e^5nK:*h.\u001b<feN,g\u0006V=qKR\u000bw\r\t4pe\u0002\u001aXO\u0019;za\u0016\u00043\r[3dW&tw\rI5ogR,\u0017\rZ\u0001\u0017I\u001d\u0014X-\u0019;fe\u0012\u001aw\u000e\\8oI\u001d\u0014X-\u0019;feR\u0019A*!\u0005\t\ra4\u0001\u0019AA\na\u0011\t)\"!\u0007\u0011\tml\u0018q\u0003\t\u0004G\u0005eAaCA\u000e\u0003#\t\t\u0011!A\u0003\u0002\u0019\u0012Aa\u0018\u00132a!:a!\u0011#\u0002\n\u001dC\u0015\u0001C2b]\u0016\u000bX/\u00197\u0015\u00071\u000b\u0019\u0003\u0003\u0004\u0002&\u001d\u0001\rAK\u0001\u0006_RDWM]\u0001\u000bCJ\u0014\u0018-_\"mCN\u001cX\u0003BA\u0016\u0003o!B!!\f\u0002<A!agOA\u0018!\u0015Y\u0012\u0011GA\u001b\u0013\r\t\u0019$\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004G\u0005]BABA\u001d\u0011\t\u0007aEA\u0001B\u0011\u001d\ti\u0004\u0003a\u0001\u0003\u007f\t!\u0001\u001e91\t\u0005\u0005\u0013Q\t\t\u0005mm\n\u0019\u0005E\u0002$\u0003\u000b\"1\"a\u0012\u0002<\u0005\u0005\t\u0011!B\u0001M\t!q\fJ\u00192\u00035\t'O]1z\u001b\u0006t\u0017NZ3tiV\u0011\u0011Q\n\t\u0005wv\fy\u0005\u0005\u0003\u001c\u0003c\u0011\u0003fB\u0005B\t\u0006Ms\tS\u0011\u0003\u0003+\n\u0001#^:fA]\u0014\u0018\r\u001d\u0011j]N$X-\u00193\u0002\u00139,w/\u0011:sCf\u0014D\u0003BA.\u0003;\u0002RaGA\u0019\u0003\u001fBq!a\u0018\u000b\u0001\u0004\t\t'A\u0002mK:\u00042aGA2\u0013\r\t)'\u0006\u0002\u0004\u0013:$\bf\u0002\u0006B\t\u0006%t\tS\u0011\u0003\u0003W\n\u0011$^:fA]\u0014\u0018\r\u001d\u0018oK^\f%O]1zA%t7\u000f^3bI\u0006Ia.Z<BeJ\f\u0017p\r\u000b\u0005\u0003c\n\u0019\bE\u0003\u001c\u0003c\tY\u0006C\u0004\u0002`-\u0001\r!!\u0019)\u000f-\tE)a\u001eH\u0011\u0006\u0012\u0011\u0011P\u0001\u001fkN,\u0007e\u001e:ba::(/\u00199/]\u0016<\u0018I\u001d:bs\u0002Jgn\u001d;fC\u0012\f\u0011B\\3x\u0003J\u0014\u0018-\u001f\u001b\u0015\t\u0005}\u0014\u0011\u0011\t\u00067\u0005E\u0012\u0011\u000f\u0005\b\u0003?b\u0001\u0019AA1Q\u001da\u0011\tRAC\u000f\"\u000b#!a\"\u0002GU\u001cX\rI<sCBtsO]1q]]\u0014\u0018\r\u001d\u0018oK^\f%O]1zA%t7\u000f^3bI\u0006Ia.Z<BeJ\f\u00170\u000e\u000b\u0005\u0003\u001b\u000by\tE\u0003\u001c\u0003c\ty\bC\u0004\u0002`5\u0001\r!!\u0019)\u000f5\tE)a%H\u0011\u0006\u0012\u0011QS\u0001)kN,\u0007e\u001e:ba::(/\u00199/oJ\f\u0007OL<sCBtc.Z<BeJ\f\u0017\u0010I5ogR,\u0017\rZ\u0001\u0010]\u0016<xK]1qa\u0016$\u0017I\u001d:bsR!\u00111TAV!\u0015\ti*a*#\u001b\t\tyJ\u0003\u0003\u0002\"\u0006\r\u0016aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003K+\u0012AC2pY2,7\r^5p]&!\u0011\u0011VAP\u0005!\t%O]1z'\u0016\f\bbBA0\u001d\u0001\u0007\u0011\u0011\r\u0015\b\u001d\u0005#\u0015qV$IC\t\t\t,\u0001\u0013de\u0016\fG/\u001a\u0011Xe\u0006\u0004\b/\u001a3BeJ\f\u0017\u0010\t3je\u0016\u001cG\u000f\\=!S:\u001cH/Z1e\u0003=qWm^!se\u0006L()^5mI\u0016\u0014HCAA\\!\u0015\ti*!/#\u0013\u0011\tY,a(\u0003\u0019\u0005\u0013(/Y=Ck&dG-\u001a:)\u000f=\tE)a0H\u0011\u0006\u0012\u0011\u0011Y\u0001$kN,\u0007%\u0011:sCf\u0014U/\u001b7eKJtS.Y6fQQD\u0017n]\u0015!S:\u001cH/Z1e\u00035!\u0018\u0010]3Be\u001e,X.\u001a8ugV\u0011\u0011q\u0019\t\u0005E\u0016\fI\r\r\u0003\u0002L\u0006=\u0007\u0003B\u0010!\u0003\u001b\u00042aIAh\t)\t\t\u000eEA\u0001\u0002\u0003\u0015\tA\n\u0002\u0005?\u0012\n$\u0007K\u0004\u0011\u0003\u0012\u000b)n\u0012%\"\u0005\u0005]\u0017\u0001T;tK\u0002\u001a8-\u00197b]I,g\r\\3di:\u0012XO\u001c;j[\u0016tSO\\5wKJ\u001cXM\f+za\u0016$\u0016m\u001a\u0011u_\u0002\u001a\u0017\r\u001d;ve\u0016\u0004C/\u001f9fAM$(/^2ukJ,\u0007%\u001b8ti\u0016\fG-A\u0005be\u001e\u001cFO]5oOV\u0011\u0011Q\u001c\t\u0005\u0003?\fiO\u0004\u0003\u0002b\u0006%\bcAAr+5\u0011\u0011Q\u001d\u0006\u0004\u0003O<\u0012A\u0002\u001fs_>$h(C\u0002\u0002lV\ta\u0001\u0015:fI\u00164\u0017\u0002BAx\u0003c\u0014aa\u0015;sS:<'bAAv+A\u001910 \u0012)\u000f\u0001\tE)a>H\u0011\u0006\u0012\u0011\u0011`\u0001#kN,\u0007e]2bY\u0006t#/\u001a4mK\u000e$hf\u00117bgN$\u0016m\u001a\u0011j]N$X-\u00193"
)
public interface ClassManifestDeprecatedApis extends OptManifest {
   // $FF: synthetic method
   static Class erasure$(final ClassManifestDeprecatedApis $this) {
      return $this.erasure();
   }

   /** @deprecated */
   default Class erasure() {
      return ((ClassTag)this).runtimeClass();
   }

   private boolean subtype(final Class sub, final Class sup) {
      Set$ var10001 = Predef$.MODULE$.Set();
      ArraySeq apply_elems = ScalaRunTime$.MODULE$.wrapRefArray(new Class[]{sub});
      if (var10001 == null) {
         throw null;
      } else {
         Set var7 = var10001.from(apply_elems);
         apply_elems = null;
         Set$ var10002 = Predef$.MODULE$.Set();
         Nil$ apply_elems = Nil$.MODULE$;
         if (var10002 == null) {
            throw null;
         } else {
            Set var8 = var10002.from(apply_elems);
            apply_elems = null;
            return this.loop$1(var7, var8, sup);
         }
      }
   }

   private boolean subargs(final List args1, final List args2) {
      return args1.corresponds(args2, (x0$1, x1$1) -> BoxesRunTime.boxToBoolean($anonfun$subargs$1(x0$1, x1$1)));
   }

   // $FF: synthetic method
   static boolean $less$colon$less$(final ClassManifestDeprecatedApis $this, final ClassTag that) {
      return $this.$less$colon$less(that);
   }

   /** @deprecated */
   default boolean $less$colon$less(final ClassTag that) {
      if (!cannotMatch$1(that)) {
         label41: {
            Class var10000 = ((ClassTag)this).runtimeClass();
            Class var2 = that.runtimeClass();
            if (var10000 == null) {
               if (var2 != null) {
                  break label41;
               }
            } else if (!var10000.equals(var2)) {
               break label41;
            }

            if (this.subargs(this.typeArguments(), that.typeArguments())) {
               return true;
            }

            return false;
         }

         if (that.typeArguments().isEmpty() && this.subtype(((ClassTag)this).runtimeClass(), that.runtimeClass())) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean $greater$colon$greater$(final ClassManifestDeprecatedApis $this, final ClassTag that) {
      return $this.$greater$colon$greater(that);
   }

   /** @deprecated */
   default boolean $greater$colon$greater(final ClassTag that) {
      return that.$less$colon$less((ClassTag)this);
   }

   // $FF: synthetic method
   static boolean canEqual$(final ClassManifestDeprecatedApis $this, final Object other) {
      return $this.canEqual(other);
   }

   default boolean canEqual(final Object other) {
      return other instanceof ClassTag;
   }

   // $FF: synthetic method
   static Class arrayClass$(final ClassManifestDeprecatedApis $this, final Class tp) {
      return $this.arrayClass(tp);
   }

   default Class arrayClass(final Class tp) {
      return Array.newInstance(tp, 0).getClass();
   }

   // $FF: synthetic method
   static ClassTag arrayManifest$(final ClassManifestDeprecatedApis $this) {
      return $this.arrayManifest();
   }

   /** @deprecated */
   default ClassTag arrayManifest() {
      return package$.MODULE$.ClassManifest().classType((Class)this.arrayClass(((ClassTag)this).runtimeClass()), (OptManifest)this, Nil$.MODULE$);
   }

   // $FF: synthetic method
   static Object[] newArray2$(final ClassManifestDeprecatedApis $this, final int len) {
      return $this.newArray2(len);
   }

   /** @deprecated */
   default Object[] newArray2(final int len) {
      return Array.newInstance(this.arrayClass(((ClassTag)this).runtimeClass()), len);
   }

   // $FF: synthetic method
   static Object[][] newArray3$(final ClassManifestDeprecatedApis $this, final int len) {
      return $this.newArray3(len);
   }

   /** @deprecated */
   default Object[][] newArray3(final int len) {
      return Array.newInstance(this.arrayClass(this.arrayClass(((ClassTag)this).runtimeClass())), len);
   }

   // $FF: synthetic method
   static Object[][][] newArray4$(final ClassManifestDeprecatedApis $this, final int len) {
      return $this.newArray4(len);
   }

   /** @deprecated */
   default Object[][][] newArray4(final int len) {
      return Array.newInstance(this.arrayClass(this.arrayClass(this.arrayClass(((ClassTag)this).runtimeClass()))), len);
   }

   // $FF: synthetic method
   static Object[][][][] newArray5$(final ClassManifestDeprecatedApis $this, final int len) {
      return $this.newArray5(len);
   }

   /** @deprecated */
   default Object[][][][] newArray5(final int len) {
      return Array.newInstance(this.arrayClass(this.arrayClass(this.arrayClass(this.arrayClass(((ClassTag)this).runtimeClass())))), len);
   }

   // $FF: synthetic method
   static scala.collection.mutable.ArraySeq newWrappedArray$(final ClassManifestDeprecatedApis $this, final int len) {
      return $this.newWrappedArray(len);
   }

   /** @deprecated */
   default scala.collection.mutable.ArraySeq newWrappedArray(final int len) {
      return new scala.collection.mutable.ArraySeq.ofRef(((ClassTag)this).newArray(len));
   }

   // $FF: synthetic method
   static ArrayBuilder newArrayBuilder$(final ClassManifestDeprecatedApis $this) {
      return $this.newArrayBuilder();
   }

   /** @deprecated */
   default ArrayBuilder newArrayBuilder() {
      return new ArrayBuilder.ofRef((ClassTag)this);
   }

   // $FF: synthetic method
   static List typeArguments$(final ClassManifestDeprecatedApis $this) {
      return $this.typeArguments();
   }

   /** @deprecated */
   default List typeArguments() {
      return Nil$.MODULE$;
   }

   // $FF: synthetic method
   static String argString$(final ClassManifestDeprecatedApis $this) {
      return $this.argString();
   }

   default String argString() {
      if (this.typeArguments().nonEmpty()) {
         List var10000 = this.typeArguments();
         String mkString_end = "]";
         String mkString_sep = ", ";
         String mkString_start = "[";
         if (var10000 == null) {
            throw null;
         } else {
            return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
         }
      } else {
         return ((ClassTag)this).runtimeClass().isArray() ? (new StringBuilder(2)).append("[").append(package$.MODULE$.ClassManifest().fromClass(((ClassTag)this).runtimeClass().getComponentType())).append("]").toString() : "";
      }
   }

   private boolean loop$1(final Set left, final Set seen, final Class sup$1) {
      while(true) {
         if (left.nonEmpty()) {
            Class next = (Class)left.head();
            scala.collection.mutable.ArraySeq.ofRef var10000 = Predef$.MODULE$.wrapRefArray(next.getInterfaces());
            if (var10000 == null) {
               throw null;
            }

            AbstractIterable toSet_this = var10000;
            SetOps var11 = Set$.MODULE$.from(toSet_this);
            toSet_this = null;
            Option $plus$plus_that = Option$.MODULE$.apply(next.getSuperclass());
            if (var11 == null) {
               throw null;
            }

            var11 = var11.concat($plus$plus_that);
            $plus$plus_that = null;
            Set supers = (Set)var11;
            if (supers == null) {
               throw null;
            }

            if (!supers.contains(sup$1)) {
               Set xs = (Set)left.concat(supers).filterNot(seen);
               ClassTag var13 = (ClassTag)this;
               if (xs == null) {
                  throw null;
               }

               Set var10001 = (Set)xs.excl(next);
               if (seen == null) {
                  throw null;
               }

               seen = (Set)seen.incl(next);
               left = var10001;
               this = var13;
               continue;
            }

            return true;
         }

         return false;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$subargs$1(final OptManifest x0$1, final OptManifest x1$1) {
      if (x0$1 instanceof ClassTag) {
         ClassTag var2 = (ClassTag)x0$1;
         if (x1$1 instanceof ClassTag) {
            ClassTag var3 = (ClassTag)x1$1;
            return var2.$less$colon$less(var3);
         }
      }

      return x0$1 == NoManifest$.MODULE$ && x1$1 == NoManifest$.MODULE$;
   }

   private static boolean cannotMatch$1(final ClassTag that$1) {
      return that$1 instanceof AnyValManifest || that$1 == Manifest$.MODULE$.AnyVal() || that$1 == Manifest$.MODULE$.Nothing() || that$1 == Manifest$.MODULE$.Null();
   }

   static void $init$(final ClassManifestDeprecatedApis $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
