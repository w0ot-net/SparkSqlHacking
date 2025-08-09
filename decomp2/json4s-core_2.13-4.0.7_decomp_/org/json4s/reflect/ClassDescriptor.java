package org.json4s.reflect;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.HashSet;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mh\u0001\u0002\u0015*\u0001BB\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t#\u0002\u0011\t\u0012)A\u0005\u0013\"A!\u000b\u0001BK\u0002\u0013\u0005\u0001\n\u0003\u0005T\u0001\tE\t\u0015!\u0003J\u0011!!\u0006A!f\u0001\n\u0003)\u0006\u0002C-\u0001\u0005#\u0005\u000b\u0011\u0002,\t\u0011i\u0003!Q3A\u0005\u0002mC\u0001B\u0019\u0001\u0003\u0012\u0003\u0006I\u0001\u0018\u0005\tG\u0002\u0011)\u001a!C\u0001I\"A1\u000e\u0001B\tB\u0003%Q\r\u0003\u0005m\u0001\tU\r\u0011\"\u0001n\u0011!\u0011\bA!E!\u0002\u0013q\u0007\"B:\u0001\t\u0003!\b\"\u0002?\u0001\t\u0003i\b\u0002CA\u0005\u0001\u0001\u0006K!a\u0003\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0004\u0005\n\u0003O\u0001\u0011\u0013!C\u0001\u0003SA\u0011\"a\u0010\u0001#\u0003%\t!!\u000b\t\u0013\u0005\u0005\u0003!%A\u0005\u0002\u0005\r\u0003\"CA$\u0001E\u0005I\u0011AA%\u0011%\ti\u0005AI\u0001\n\u0003\ty\u0005C\u0005\u0002T\u0001\t\n\u0011\"\u0001\u0002V!I\u0011\u0011\f\u0001\u0002\u0002\u0013\u0005\u00131\f\u0005\n\u0003W\u0002\u0011\u0011!C\u0001\u0003[B\u0011\"!\u001e\u0001\u0003\u0003%\t!a\u001e\t\u0013\u0005\r\u0005!!A\u0005B\u0005\u0015\u0005\"CAJ\u0001\u0005\u0005I\u0011AAK\u0011%\ty\nAA\u0001\n\u0003\n\t\u000bC\u0005\u0002&\u0002\t\t\u0011\"\u0011\u0002(\"I\u0011\u0011\u0016\u0001\u0002\u0002\u0013\u0005\u00131\u0016\u0005\n\u0003[\u0003\u0011\u0011!C!\u0003_;\u0011\"a-*\u0003\u0003E\t!!.\u0007\u0011!J\u0013\u0011!E\u0001\u0003oCaa\u001d\u0012\u0005\u0002\u0005=\u0007\"CAUE\u0005\u0005IQIAV\u0011%\t\tNIA\u0001\n\u0003\u000b\u0019\u000eC\u0005\u0002b\n\n\t\u0011\"!\u0002d\"I\u0011\u0011\u001f\u0012\u0002\u0002\u0013%\u00111\u001f\u0002\u0010\u00072\f7o\u001d#fg\u000e\u0014\u0018\u000e\u001d;pe*\u0011!fK\u0001\be\u00164G.Z2u\u0015\taS&\u0001\u0004kg>tGg\u001d\u0006\u0002]\u0005\u0019qN]4\u0004\u0001M!\u0001!M\u001b<!\t\u00114'D\u0001*\u0013\t!\u0014F\u0001\tPE*,7\r\u001e#fg\u000e\u0014\u0018\u000e\u001d;peB\u0011a'O\u0007\u0002o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\t9\u0001K]8ek\u000e$\bC\u0001\u001fE\u001d\ti$I\u0004\u0002?\u00036\tqH\u0003\u0002A_\u00051AH]8pizJ\u0011\u0001O\u0005\u0003\u0007^\nq\u0001]1dW\u0006<W-\u0003\u0002F\r\na1+\u001a:jC2L'0\u00192mK*\u00111iN\u0001\u000bg&l\u0007\u000f\\3OC6,W#A%\u0011\u0005)seBA&M!\tqt'\u0003\u0002No\u00051\u0001K]3eK\u001aL!a\u0014)\u0003\rM#(/\u001b8h\u0015\tiu'A\u0006tS6\u0004H.\u001a(b[\u0016\u0004\u0013\u0001\u00034vY2t\u0015-\\3\u0002\u0013\u0019,H\u000e\u001c(b[\u0016\u0004\u0013aB3sCN,(/Z\u000b\u0002-B\u0011!gV\u0005\u00031&\u0012\u0011bU2bY\u0006$\u0016\u0010]3\u0002\u0011\u0015\u0014\u0018m];sK\u0002\n\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003q\u00032AN/`\u0013\tqvG\u0001\u0004PaRLwN\u001c\t\u0003e\u0001L!!Y\u0015\u0003'MKgn\u001a7fi>tG)Z:de&\u0004Ho\u001c:\u0002\u0015\r|W\u000e]1oS>t\u0007%\u0001\u0007d_:\u001cHO];di>\u00148/F\u0001f!\rad\r[\u0005\u0003O\u001a\u00131aU3r!\t\u0011\u0014.\u0003\u0002kS\t)2i\u001c8tiJ,8\r^8s\t\u0016\u001c8M]5qi>\u0014\u0018!D2p]N$(/^2u_J\u001c\b%\u0001\u0006qe>\u0004XM\u001d;jKN,\u0012A\u001c\t\u0004y\u0019|\u0007C\u0001\u001aq\u0013\t\t\u0018F\u0001\nQe>\u0004XM\u001d;z\t\u0016\u001c8M]5qi>\u0014\u0018a\u00039s_B,'\u000f^5fg\u0002\na\u0001P5oSRtDcB;wobL(p\u001f\t\u0003e\u0001AQaR\u0007A\u0002%CQAU\u0007A\u0002%CQ\u0001V\u0007A\u0002YCQAW\u0007A\u0002qCQaY\u0007A\u0002\u0015DQ\u0001\\\u0007A\u00029\fABY3ti6\u000bGo\u00195j]\u001e$\"A`@\u0011\u0007Yj\u0006\u000eC\u0004\u0002\u00029\u0001\r!a\u0001\u0002\u0011\u0005\u0014xMT1nKN\u0004B\u0001PA\u0003\u0013&\u0019\u0011q\u0001$\u0003\t1K7\u000f^\u0001\u0013?6|7\u000f^\"p[B\u0014X\r[3og&4X\r\u0005\u0003=M\u00065\u0001c\u0001\u001a\u0002\u0010%\u0019\u0011\u0011C\u0015\u00035\r{gn\u001d;sk\u000e$xN\u001d)be\u0006lG)Z:de&\u0004Ho\u001c:\u0002#5|7\u000f^\"p[B\u0014X\r[3og&4X-\u0006\u0002\u0002\f\u0005!1m\u001c9z)5)\u00181DA\u000f\u0003?\t\t#a\t\u0002&!9q)\u0005I\u0001\u0002\u0004I\u0005b\u0002*\u0012!\u0003\u0005\r!\u0013\u0005\b)F\u0001\n\u00111\u0001W\u0011\u001dQ\u0016\u0003%AA\u0002qCqaY\t\u0011\u0002\u0003\u0007Q\rC\u0004m#A\u0005\t\u0019\u00018\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u0006\u0016\u0004\u0013\u000652FAA\u0018!\u0011\t\t$a\u000f\u000e\u0005\u0005M\"\u0002BA\u001b\u0003o\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005er'\u0001\u0006b]:|G/\u0019;j_:LA!!\u0010\u00024\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA#U\r1\u0016QF\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\tYEK\u0002]\u0003[\tabY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0002R)\u001aQ-!\f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011\u0011q\u000b\u0016\u0004]\u00065\u0012!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002^A!\u0011qLA5\u001b\t\t\tG\u0003\u0003\u0002d\u0005\u0015\u0014\u0001\u00027b]\u001eT!!a\u001a\u0002\t)\fg/Y\u0005\u0004\u001f\u0006\u0005\u0014\u0001\u00049s_\u0012,8\r^!sSRLXCAA8!\r1\u0014\u0011O\u0005\u0004\u0003g:$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA=\u0003\u007f\u00022ANA>\u0013\r\tih\u000e\u0002\u0004\u0003:L\b\"CAA5\u0005\u0005\t\u0019AA8\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0011\t\u0007\u0003\u0013\u000by)!\u001f\u000e\u0005\u0005-%bAAGo\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00151\u0012\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0018\u0006u\u0005c\u0001\u001c\u0002\u001a&\u0019\u00111T\u001c\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011\u0011\u000f\u0002\u0002\u0003\u0007\u0011\u0011P\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002^\u0005\r\u0006\"CAA;\u0005\u0005\t\u0019AA8\u0003!A\u0017m\u001d5D_\u0012,GCAA8\u0003!!xn\u0015;sS:<GCAA/\u0003\u0019)\u0017/^1mgR!\u0011qSAY\u0011%\t\t\tIA\u0001\u0002\u0004\tI(A\bDY\u0006\u001c8\u000fR3tGJL\u0007\u000f^8s!\t\u0011$eE\u0003#\u0003s\u000b)\rE\u0006\u0002<\u0006\u0005\u0017*\u0013,]K:,XBAA_\u0015\r\tylN\u0001\beVtG/[7f\u0013\u0011\t\u0019-!0\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tg\u0007\u0005\u0003\u0002H\u00065WBAAe\u0015\u0011\tY-!\u001a\u0002\u0005%|\u0017bA#\u0002JR\u0011\u0011QW\u0001\u0006CB\u0004H.\u001f\u000b\u000ek\u0006U\u0017q[Am\u00037\fi.a8\t\u000b\u001d+\u0003\u0019A%\t\u000bI+\u0003\u0019A%\t\u000bQ+\u0003\u0019\u0001,\t\u000bi+\u0003\u0019\u0001/\t\u000b\r,\u0003\u0019A3\t\u000b1,\u0003\u0019\u00018\u0002\u000fUt\u0017\r\u001d9msR!\u0011Q]Aw!\u00111T,a:\u0011\u0013Y\nI/S%W9\u0016t\u0017bAAvo\t1A+\u001e9mKZB\u0001\"a<'\u0003\u0003\u0005\r!^\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA{!\u0011\ty&a>\n\t\u0005e\u0018\u0011\r\u0002\u0007\u001f\nTWm\u0019;"
)
public class ClassDescriptor extends ObjectDescriptor {
   private final String simpleName;
   private final String fullName;
   private final ScalaType erasure;
   private final Option companion;
   private final Seq constructors;
   private final Seq properties;
   private Seq _mostComprehensive;

   public static Option unapply(final ClassDescriptor x$0) {
      return ClassDescriptor$.MODULE$.unapply(x$0);
   }

   public static ClassDescriptor apply(final String simpleName, final String fullName, final ScalaType erasure, final Option companion, final Seq constructors, final Seq properties) {
      return ClassDescriptor$.MODULE$.apply(simpleName, fullName, erasure, companion, constructors, properties);
   }

   public static Function1 tupled() {
      return ClassDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClassDescriptor$.MODULE$.curried();
   }

   public String simpleName() {
      return this.simpleName;
   }

   public String fullName() {
      return this.fullName;
   }

   public ScalaType erasure() {
      return this.erasure;
   }

   public Option companion() {
      return this.companion;
   }

   public Seq constructors() {
      return this.constructors;
   }

   public Seq properties() {
      return this.properties;
   }

   public Option bestMatching(final List argNames) {
      LazyRef Score$module = new LazyRef();
      HashSet names = new HashSet(argNames) {
         // $FF: synthetic method
         public static final boolean $anonfun$new$1(final Object $this, final String x) {
            return $this.add(x);
         }

         public {
            argNames$1.foreach((x) -> BoxesRunTime.boxToBoolean($anonfun$new$1(this, x)));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      Object var10000;
      if (this.constructors().isEmpty()) {
         var10000 = .MODULE$;
      } else {
         Tuple2 best = (Tuple2)((IterableOnceOps)this.constructors().tail()).foldLeft(new Tuple2(this.constructors().head(), this.score$1(((ConstructorDescriptor)this.constructors().head()).params().toList(), names, Score$module)), (bestx, c) -> {
            Score$1 newScore = this.score$1(c.params().toList(), names, Score$module);
            return newScore.isBetterThan((Score$1)bestx._2()) ? new Tuple2(c, newScore) : bestx;
         });
         var10000 = new Some(best._1());
      }

      return (Option)var10000;

      class Score$1 implements Product, Serializable {
         private final int detailed;
         private final int optionalCount;
         private final int defaultCount;
         // $FF: synthetic field
         private final ClassDescriptor $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int detailed() {
            return this.detailed;
         }

         public int optionalCount() {
            return this.optionalCount;
         }

         public int defaultCount() {
            return this.defaultCount;
         }

         public boolean isBetterThan(final Score$1 other) {
            return this.detailed() == other.detailed() && this.optionalCount() < other.optionalCount() || this.detailed() == other.detailed() && this.defaultCount() > other.defaultCount() || this.detailed() > other.detailed();
         }

         public Score$1 copy(final int detailed, final int optionalCount, final int defaultCount) {
            return new Score$1(detailed, optionalCount, defaultCount);
         }

         public int copy$default$1() {
            return this.detailed();
         }

         public int copy$default$2() {
            return this.optionalCount();
         }

         public int copy$default$3() {
            return this.defaultCount();
         }

         public String productPrefix() {
            return "Score";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            Object var10000;
            switch (x$1) {
               case 0:
                  var10000 = BoxesRunTime.boxToInteger(this.detailed());
                  break;
               case 1:
                  var10000 = BoxesRunTime.boxToInteger(this.optionalCount());
                  break;
               case 2:
                  var10000 = BoxesRunTime.boxToInteger(this.defaultCount());
                  break;
               default:
                  var10000 = Statics.ioobe(x$1);
            }

            return var10000;
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Score$1;
         }

         public String productElementName(final int x$1) {
            String var10000;
            switch (x$1) {
               case 0:
                  var10000 = "detailed";
                  break;
               case 1:
                  var10000 = "optionalCount";
                  break;
               case 2:
                  var10000 = "defaultCount";
                  break;
               default:
                  var10000 = (String)Statics.ioobe(x$1);
            }

            return var10000;
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, this.detailed());
            var1 = Statics.mix(var1, this.optionalCount());
            var1 = Statics.mix(var1, this.defaultCount());
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label53: {
                  boolean var2;
                  if (x$1 instanceof Score$1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     Score$1 var4 = (Score$1)x$1;
                     if (this.detailed() == var4.detailed() && this.optionalCount() == var4.optionalCount() && this.defaultCount() == var4.defaultCount() && var4.canEqual(this)) {
                        break label53;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         public Score$1(final int detailed, final int optionalCount, final int defaultCount) {
            this.detailed = detailed;
            this.optionalCount = optionalCount;
            this.defaultCount = defaultCount;
            if (ClassDescriptor.this == null) {
               throw null;
            } else {
               this.$outer = ClassDescriptor.this;
               super();
               Product.$init$(this);
            }
         }
      }

   }

   public Seq mostComprehensive() {
      if (this._mostComprehensive == null) {
         Object var10001;
         if (this.constructors().nonEmpty()) {
            Seq primaryCtors = (Seq)this.constructors().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$mostComprehensive$1(x$3)));
            if (primaryCtors.length() > 1) {
               throw new IllegalArgumentException((new StringBuilder(56)).append("Two constructors annotated with PrimaryConstructor in `").append(this.fullName()).append("`").toString());
            }

            var10001 = (Seq)primaryCtors.headOption().orElse(() -> ((IterableOps)this.constructors().sortBy((x$4) -> BoxesRunTime.boxToInteger($anonfun$mostComprehensive$3(x$4)), scala.math.Ordering.Int..MODULE$)).headOption()).map((x$5) -> x$5.params()).getOrElse(() -> scala.package..MODULE$.Nil());
         } else {
            var10001 = scala.package..MODULE$.Nil();
         }

         this._mostComprehensive = (Seq)var10001;
      }

      return this._mostComprehensive;
   }

   public ClassDescriptor copy(final String simpleName, final String fullName, final ScalaType erasure, final Option companion, final Seq constructors, final Seq properties) {
      return new ClassDescriptor(simpleName, fullName, erasure, companion, constructors, properties);
   }

   public String copy$default$1() {
      return this.simpleName();
   }

   public String copy$default$2() {
      return this.fullName();
   }

   public ScalaType copy$default$3() {
      return this.erasure();
   }

   public Option copy$default$4() {
      return this.companion();
   }

   public Seq copy$default$5() {
      return this.constructors();
   }

   public Seq copy$default$6() {
      return this.properties();
   }

   public String productPrefix() {
      return "ClassDescriptor";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.simpleName();
            break;
         case 1:
            var10000 = this.fullName();
            break;
         case 2:
            var10000 = this.erasure();
            break;
         case 3:
            var10000 = this.companion();
            break;
         case 4:
            var10000 = this.constructors();
            break;
         case 5:
            var10000 = this.properties();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ClassDescriptor;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "simpleName";
            break;
         case 1:
            var10000 = "fullName";
            break;
         case 2:
            var10000 = "erasure";
            break;
         case 3:
            var10000 = "companion";
            break;
         case 4:
            var10000 = "constructors";
            break;
         case 5:
            var10000 = "properties";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var17;
      if (this != x$1) {
         label99: {
            boolean var2;
            if (x$1 instanceof ClassDescriptor) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label81: {
                  label90: {
                     ClassDescriptor var4 = (ClassDescriptor)x$1;
                     String var10000 = this.simpleName();
                     String var5 = var4.simpleName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label90;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label90;
                     }

                     var10000 = this.fullName();
                     String var6 = var4.fullName();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label90;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label90;
                     }

                     ScalaType var12 = this.erasure();
                     ScalaType var7 = var4.erasure();
                     if (var12 == null) {
                        if (var7 != null) {
                           break label90;
                        }
                     } else if (!var12.equals(var7)) {
                        break label90;
                     }

                     Option var13 = this.companion();
                     Option var8 = var4.companion();
                     if (var13 == null) {
                        if (var8 != null) {
                           break label90;
                        }
                     } else if (!var13.equals(var8)) {
                        break label90;
                     }

                     Seq var14 = this.constructors();
                     Seq var9 = var4.constructors();
                     if (var14 == null) {
                        if (var9 != null) {
                           break label90;
                        }
                     } else if (!var14.equals(var9)) {
                        break label90;
                     }

                     var14 = this.properties();
                     Seq var10 = var4.properties();
                     if (var14 == null) {
                        if (var10 != null) {
                           break label90;
                        }
                     } else if (!var14.equals(var10)) {
                        break label90;
                     }

                     if (var4.canEqual(this)) {
                        var17 = true;
                        break label81;
                     }
                  }

                  var17 = false;
               }

               if (var17) {
                  break label99;
               }
            }

            var17 = false;
            return var17;
         }
      }

      var17 = true;
      return var17;
   }

   // $FF: synthetic method
   private final Score$2$ Score$lzycompute$1(final LazyRef Score$module$1) {
      synchronized(Score$module$1){}

      Score$2$ var3;
      try {
         class Score$2$ extends AbstractFunction3 implements Serializable {
            // $FF: synthetic field
            private final ClassDescriptor $outer;

            public final String toString() {
               return "Score";
            }

            public Score$1 apply(final int detailed, final int optionalCount, final int defaultCount) {
               return new Score$1(detailed, optionalCount, defaultCount);
            }

            public Option unapply(final Score$1 x$0) {
               return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.detailed()), BoxesRunTime.boxToInteger(x$0.optionalCount()), BoxesRunTime.boxToInteger(x$0.defaultCount()))));
            }

            public Score$2$() {
               if (ClassDescriptor.this == null) {
                  throw null;
               } else {
                  this.$outer = ClassDescriptor.this;
                  super();
               }
            }
         }

         var3 = Score$module$1.initialized() ? (Score$2$)Score$module$1.value() : (Score$2$)Score$module$1.initialize(new Score$2$());
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private final Score$2$ Score$3(final LazyRef Score$module$1) {
      return Score$module$1.initialized() ? (Score$2$)Score$module$1.value() : this.Score$lzycompute$1(Score$module$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$bestMatching$1(final HashSet names$1, final int s, final ConstructorParamDescriptor arg) {
      return names$1.contains(arg.name()) ? s + 1 : (arg.isOptional() ? s : (arg.hasDefault() ? s : -100));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$bestMatching$2(final ConstructorParamDescriptor x$1) {
      return x$1.isOptional();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$bestMatching$3(final ConstructorParamDescriptor x$2) {
      return x$2.hasDefault();
   }

   private final Score$1 score$1(final List args, final HashSet names$1, final LazyRef Score$module$1) {
      return this.Score$3(Score$module$1).apply(BoxesRunTime.unboxToInt(args.foldLeft(BoxesRunTime.boxToInteger(0), (s, arg) -> BoxesRunTime.boxToInteger($anonfun$bestMatching$1(names$1, BoxesRunTime.unboxToInt(s), arg)))), args.count((x$1) -> BoxesRunTime.boxToBoolean($anonfun$bestMatching$2(x$1))), args.count((x$2) -> BoxesRunTime.boxToBoolean($anonfun$bestMatching$3(x$2))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mostComprehensive$1(final ConstructorDescriptor x$3) {
      return x$3.isPrimary();
   }

   // $FF: synthetic method
   public static final int $anonfun$mostComprehensive$3(final ConstructorDescriptor x$4) {
      return -x$4.params().size();
   }

   public ClassDescriptor(final String simpleName, final String fullName, final ScalaType erasure, final Option companion, final Seq constructors, final Seq properties) {
      this.simpleName = simpleName;
      this.fullName = fullName;
      this.erasure = erasure;
      this.companion = companion;
      this.constructors = constructors;
      this.properties = properties;
      this._mostComprehensive = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
