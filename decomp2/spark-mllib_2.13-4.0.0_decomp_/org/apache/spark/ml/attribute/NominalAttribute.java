package org.apache.spark.ml.attribute;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.StructField;
import scala.None;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001da\u0001\u0002\u001a4\u0001yB\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0005\u0012\u0005\t-\u0002\u0011\t\u0011)A\u0005\u000b\"Aq\u000b\u0001BC\u0002\u0013\u0005\u0003\f\u0003\u0005^\u0001\t\u0005\t\u0015!\u0003Z\u0011!q\u0006A!b\u0001\n\u0003y\u0006\u0002\u00033\u0001\u0005\u0003\u0005\u000b\u0011\u00021\t\u0011\u0015\u0004!Q1A\u0005\u0002aC\u0001B\u001a\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\tO\u0002\u0011)\u0019!C\u0001Q\"AQ\u000e\u0001B\u0001B\u0003%\u0011\u000e\u0003\u0004o\u0001\u0011\u0005Qg\u001c\u0005\u0006m\u0002!\te\u001e\u0005\u0006w\u0002!\t\u0005 \u0005\u0006{\u0002!\t\u0005 \u0005\t}\u0002A)\u0019!C\u0005\u007f\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\b\u0001\u0011\u0005\u0011\u0011\u0003\u0005\b\u0003+\u0001A\u0011AA\f\u0011\u001d\tY\u0002\u0001C!\u0003;Aq!!\t\u0001\t\u0003\n\u0019\u0003C\u0004\u0002&\u0001!\t%a\n\t\u000f\u0005-\u0002\u0001\"\u0011\u0002$!9\u0011Q\u0006\u0001\u0005\u0002\u0005=\u0002bBA\u0017\u0001\u0011\u0005\u00111\u0007\u0005\b\u0003#\u0002A\u0011AA\u0012\u0011\u001d\t\u0019\u0006\u0001C\u0001\u0003+Bq!!\u0017\u0001\t\u0003\t\u0019\u0003\u0003\u0004\u0002\\\u0001!\t\u0001\u0017\u0005\b\u0003;\u0002A\u0011BA0\u0011%\tY\u0007AI\u0001\n\u0013\ti\u0007C\u0005\u0002\u0000\u0001\t\n\u0011\"\u0003\u0002\u0002\"I\u0011Q\u0011\u0001\u0012\u0002\u0013%\u0011q\u0011\u0005\n\u0003\u0017\u0003\u0011\u0013!C\u0005\u0003\u0003C\u0011\"!$\u0001#\u0003%I!a$\t\u0011\u0005M\u0005\u0001\"\u00114\u0003+Cq!a+\u0001\t\u0003\ni\u000bC\u0004\u0002:\u0002!\t%a/\b\u000f\u0005u6\u0007#\u0001\u0002@\u001a1!g\rE\u0001\u0003\u0003DaA\\\u0014\u0005\u0002\u0005}\u0007\"CAqO\t\u0007IQAA\u0012\u0011\u001d\t\u0019o\nQ\u0001\u000eAD\u0001\"!:(\t\u0003\u001a\u0014q\u001d\u0005\u000b\u0003[<\u0013\u0013!C\u0001k\u00055\u0004BCAxOE\u0005I\u0011A\u001b\u0002\u0002\"Q\u0011\u0011_\u0014\u0012\u0002\u0013\u0005Q'a\"\t\u0015\u0005Mx%%A\u0005\u0002U\n\t\t\u0003\u0006\u0002v\u001e\n\n\u0011\"\u00016\u0003\u001fC\u0011\"a>(\u0003\u0003%I!!?\u0003!9{W.\u001b8bY\u0006#HO]5ckR,'B\u0001\u001b6\u0003%\tG\u000f\u001e:jEV$XM\u0003\u00027o\u0005\u0011Q\u000e\u001c\u0006\u0003qe\nQa\u001d9be.T!AO\u001e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0014aA8sO\u000e\u00011C\u0001\u0001@!\t\u0001\u0015)D\u00014\u0013\t\u00115GA\u0005BiR\u0014\u0018NY;uK\u0006!a.Y7f+\u0005)\u0005c\u0001$J\u00176\tqIC\u0001I\u0003\u0015\u00198-\u00197b\u0013\tQuI\u0001\u0004PaRLwN\u001c\t\u0003\u0019Ns!!T)\u0011\u00059;U\"A(\u000b\u0005Ak\u0014A\u0002\u001fs_>$h(\u0003\u0002S\u000f\u00061\u0001K]3eK\u001aL!\u0001V+\u0003\rM#(/\u001b8h\u0015\t\u0011v)A\u0003oC6,\u0007%A\u0003j]\u0012,\u00070F\u0001Z!\r1\u0015J\u0017\t\u0003\rnK!\u0001X$\u0003\u0007%sG/\u0001\u0004j]\u0012,\u0007\u0010I\u0001\nSN|%\u000fZ5oC2,\u0012\u0001\u0019\t\u0004\r&\u000b\u0007C\u0001$c\u0013\t\u0019wIA\u0004C_>dW-\u00198\u0002\u0015%\u001cxJ\u001d3j]\u0006d\u0007%A\u0005ok64\u0016\r\\;fg\u0006Qa.^7WC2,Xm\u001d\u0011\u0002\rY\fG.^3t+\u0005I\u0007c\u0001$JUB\u0019ai[&\n\u00051<%!B!se\u0006L\u0018a\u0002<bYV,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\rA\f(o\u001d;v!\t\u0001\u0005\u0001C\u0004D\u0017A\u0005\t\u0019A#\t\u000f][\u0001\u0013!a\u00013\"9al\u0003I\u0001\u0002\u0004\u0001\u0007bB3\f!\u0003\u0005\r!\u0017\u0005\bO.\u0001\n\u00111\u0001j\u0003!\tG\u000f\u001e:UsB,W#\u0001=\u0011\u0005\u0001K\u0018B\u0001>4\u00055\tE\u000f\u001e:jEV$X\rV=qK\u0006I\u0011n\u001d(v[\u0016\u0014\u0018nY\u000b\u0002C\u0006I\u0011n\u001d(p[&t\u0017\r\\\u0001\rm\u0006dW/\u001a+p\u0013:$W\r_\u000b\u0003\u0003\u0003\u0001R\u0001TA\u0002\u0017jK1!!\u0002V\u0005\ri\u0015\r]\u0001\bS:$W\r_(g)\rQ\u00161\u0002\u0005\u0007\u0003\u001b\u0001\u0002\u0019A&\u0002\u000bY\fG.^3\u0002\u0011!\f7OV1mk\u0016$2!YA\n\u0011\u0019\ti!\u0005a\u0001\u0017\u0006Aq-\u001a;WC2,X\rF\u0002L\u00033AQa\u0016\nA\u0002i\u000b\u0001b^5uQ:\u000bW.\u001a\u000b\u0004a\u0006}\u0001\"B\"\u0014\u0001\u0004Y\u0015aC<ji\"|W\u000f\u001e(b[\u0016,\u0012\u0001]\u0001\no&$\b.\u00138eKb$2\u0001]A\u0015\u0011\u00159V\u00031\u0001[\u000319\u0018\u000e\u001e5pkRLe\u000eZ3y\u0003)9\u0018\u000e\u001e5WC2,Xm\u001d\u000b\u0004a\u0006E\u0002\"B4\u0018\u0001\u0004QG#\u00029\u00026\u0005e\u0002BBA\u001c1\u0001\u00071*A\u0003gSJ\u001cH\u000fC\u0004\u0002<a\u0001\r!!\u0010\u0002\r=$\b.\u001a:t!\u00111\u0015qH&\n\u0007\u0005\u0005sI\u0001\u0006=e\u0016\u0004X-\u0019;fIzB3\u0001GA#!\u0011\t9%!\u0014\u000e\u0005\u0005%#bAA&\u000f\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005=\u0013\u0011\n\u0002\bm\u0006\u0014\u0018M]4t\u000359\u0018\u000e\u001e5pkR4\u0016\r\\;fg\u0006iq/\u001b;i\u001dVlg+\u00197vKN$2\u0001]A,\u0011\u0015)'\u00041\u0001[\u0003A9\u0018\u000e\u001e5pkRtU/\u001c,bYV,7/\u0001\u0007hKRtU/\u001c,bYV,7/\u0001\u0003d_BLHc\u00039\u0002b\u0005\r\u0014QMA4\u0003SBqaQ\u000f\u0011\u0002\u0003\u0007Q\tC\u0004X;A\u0005\t\u0019A-\t\u000fyk\u0002\u0013!a\u0001A\"9Q-\bI\u0001\u0002\u0004I\u0006bB4\u001e!\u0003\u0005\r![\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyGK\u0002F\u0003cZ#!a\u001d\u0011\t\u0005U\u00141P\u0007\u0003\u0003oRA!!\u001f\u0002J\u0005IQO\\2iK\u000e\\W\rZ\u0005\u0005\u0003{\n9HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\u0004*\u001a\u0011,!\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\u0012\u0016\u0004A\u0006E\u0014AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\t\t\tJK\u0002j\u0003c\na\u0002^8NKR\fG-\u0019;b\u00136\u0004H\u000e\u0006\u0003\u0002\u0018\u0006\u001d\u0006\u0003BAM\u0003Gk!!a'\u000b\t\u0005u\u0015qT\u0001\u0006if\u0004Xm\u001d\u0006\u0004\u0003C;\u0014aA:rY&!\u0011QUAN\u0005!iU\r^1eCR\f\u0007BBAUG\u0001\u0007\u0011-\u0001\u0005xSRDG+\u001f9f\u0003\u0019)\u0017/^1mgR\u0019\u0011-a,\t\u000f\u0005EF\u00051\u0001\u00024\u0006)q\u000e\u001e5feB\u0019a)!.\n\u0007\u0005]vIA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00025\u0006\u0001bj\\7j]\u0006d\u0017\t\u001e;sS\n,H/\u001a\t\u0003\u0001\u001e\u001araJAb\u0003\u0013\fy\rE\u0002G\u0003\u000bL1!a2H\u0005\u0019\te.\u001f*fMB\u0019\u0001)a3\n\u0007\u000557G\u0001\tBiR\u0014\u0018NY;uK\u001a\u000b7\r^8ssB!\u0011\u0011[An\u001b\t\t\u0019N\u0003\u0003\u0002V\u0006]\u0017AA5p\u0015\t\tI.\u0001\u0003kCZ\f\u0017\u0002BAo\u0003'\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\"!a0\u0002\u0017\u0011,g-Y;mi\u0006#HO]\u0001\rI\u00164\u0017-\u001e7u\u0003R$(\u000fI\u0001\rMJ|W.T3uC\u0012\fG/\u0019\u000b\u0004a\u0006%\bbBAvW\u0001\u0007\u0011qS\u0001\t[\u0016$\u0018\rZ1uC\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\b\u0003BA\u007f\u0005\u0007i!!a@\u000b\t\t\u0005\u0011q[\u0001\u0005Y\u0006tw-\u0003\u0003\u0003\u0006\u0005}(AB(cU\u0016\u001cG\u000f"
)
public class NominalAttribute extends Attribute {
   private Map valueToIndex;
   private final Option name;
   private final Option index;
   private final Option isOrdinal;
   private final Option numValues;
   private final Option values;
   private volatile boolean bitmap$0;

   public static NominalAttribute defaultAttr() {
      return NominalAttribute$.MODULE$.defaultAttr();
   }

   public static Attribute fromStructField(final StructField field) {
      return NominalAttribute$.MODULE$.fromStructField(field);
   }

   public NominalAttribute withValues(final String first, final String... others) {
      return this.withValues(first, (Seq).MODULE$.wrapRefArray((Object[])others));
   }

   public Option name() {
      return this.name;
   }

   public Option index() {
      return this.index;
   }

   public Option isOrdinal() {
      return this.isOrdinal;
   }

   public Option numValues() {
      return this.numValues;
   }

   public Option values() {
      return this.values;
   }

   public AttributeType attrType() {
      return AttributeType$.MODULE$.Nominal();
   }

   public boolean isNumeric() {
      return false;
   }

   public boolean isNominal() {
      return true;
   }

   private Map valueToIndex$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.valueToIndex = (Map)this.values().map((x$7) -> org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(scala.Predef..MODULE$.wrapRefArray((Object[])x$7))).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.valueToIndex;
   }

   private Map valueToIndex() {
      return !this.bitmap$0 ? this.valueToIndex$lzycompute() : this.valueToIndex;
   }

   public int indexOf(final String value) {
      return BoxesRunTime.unboxToInt(this.valueToIndex().apply(value));
   }

   public boolean hasValue(final String value) {
      return this.valueToIndex().contains(value);
   }

   public String getValue(final int index) {
      return ((String[])this.values().get())[index];
   }

   public NominalAttribute withName(final String name) {
      return this.copy(new Some(name), this.copy$default$2(), this.copy$default$3(), this.copy$default$4(), this.copy$default$5());
   }

   public NominalAttribute withoutName() {
      return this.copy(scala.None..MODULE$, this.copy$default$2(), this.copy$default$3(), this.copy$default$4(), this.copy$default$5());
   }

   public NominalAttribute withIndex(final int index) {
      Some x$1 = new Some(BoxesRunTime.boxToInteger(index));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$3();
      Option x$4 = this.copy$default$4();
      Option x$5 = this.copy$default$5();
      return this.copy(x$2, x$1, x$3, x$4, x$5);
   }

   public NominalAttribute withoutIndex() {
      None x$1 = scala.None..MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$3();
      Option x$4 = this.copy$default$4();
      Option x$5 = this.copy$default$5();
      return this.copy(x$2, x$1, x$3, x$4, x$5);
   }

   public NominalAttribute withValues(final String[] values) {
      None x$1 = scala.None..MODULE$;
      Some x$2 = new Some(values);
      Option x$3 = this.copy$default$1();
      Option x$4 = this.copy$default$2();
      Option x$5 = this.copy$default$3();
      return this.copy(x$3, x$4, x$5, x$1, x$2);
   }

   public NominalAttribute withValues(final String first, final Seq others) {
      None x$1 = scala.None..MODULE$;
      Some x$2 = new Some(((IterableOnceOps)others.$plus$colon(first)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
      Option x$3 = this.copy$default$1();
      Option x$4 = this.copy$default$2();
      Option x$5 = this.copy$default$3();
      return this.copy(x$3, x$4, x$5, x$1, x$2);
   }

   public NominalAttribute withoutValues() {
      None x$1 = scala.None..MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$4();
      return this.copy(x$2, x$3, x$4, x$5, x$1);
   }

   public NominalAttribute withNumValues(final int numValues) {
      Some x$1 = new Some(BoxesRunTime.boxToInteger(numValues));
      None x$2 = scala.None..MODULE$;
      Option x$3 = this.copy$default$1();
      Option x$4 = this.copy$default$2();
      Option x$5 = this.copy$default$3();
      return this.copy(x$3, x$4, x$5, x$1, x$2);
   }

   public NominalAttribute withoutNumValues() {
      None x$1 = scala.None..MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$5();
      return this.copy(x$2, x$3, x$4, x$1, x$5);
   }

   public Option getNumValues() {
      if (this.numValues().nonEmpty()) {
         return this.numValues();
      } else {
         return (Option)(this.values().nonEmpty() ? new Some(BoxesRunTime.boxToInteger(((String[])this.values().get()).length)) : scala.None..MODULE$);
      }
   }

   private NominalAttribute copy(final Option name, final Option index, final Option isOrdinal, final Option numValues, final Option values) {
      return new NominalAttribute(name, index, isOrdinal, numValues, values);
   }

   private Option copy$default$1() {
      return this.name();
   }

   private Option copy$default$2() {
      return this.index();
   }

   private Option copy$default$3() {
      return this.isOrdinal();
   }

   private Option copy$default$4() {
      return this.numValues();
   }

   private Option copy$default$5() {
      return this.values();
   }

   public Metadata toMetadataImpl(final boolean withType) {
      MetadataBuilder bldr = new MetadataBuilder();
      if (withType) {
         bldr.putString(AttributeKeys$.MODULE$.TYPE(), this.attrType().name());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.name().foreach((x$8) -> bldr.putString(AttributeKeys$.MODULE$.NAME(), x$8));
      this.index().foreach((x$9) -> $anonfun$toMetadataImpl$8(bldr, BoxesRunTime.unboxToInt(x$9)));
      this.isOrdinal().foreach((x$10) -> $anonfun$toMetadataImpl$9(bldr, BoxesRunTime.unboxToBoolean(x$10)));
      this.numValues().foreach((x$11) -> $anonfun$toMetadataImpl$10(bldr, BoxesRunTime.unboxToInt(x$11)));
      this.values().foreach((v) -> bldr.putStringArray(AttributeKeys$.MODULE$.VALUES(), v));
      return bldr.build();
   }

   public boolean equals(final Object other) {
      if (!(other instanceof NominalAttribute var4)) {
         return false;
      } else {
         boolean var14;
         label64: {
            label58: {
               Option var10000 = this.name();
               Option var5 = var4.name();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label58;
                  }
               } else if (!var10000.equals(var5)) {
                  break label58;
               }

               var10000 = this.index();
               Option var6 = var4.index();
               if (var10000 == null) {
                  if (var6 != null) {
                     break label58;
                  }
               } else if (!var10000.equals(var6)) {
                  break label58;
               }

               var10000 = this.isOrdinal();
               Option var7 = var4.isOrdinal();
               if (var10000 == null) {
                  if (var7 != null) {
                     break label58;
                  }
               } else if (!var10000.equals(var7)) {
                  break label58;
               }

               var10000 = this.numValues();
               Option var8 = var4.numValues();
               if (var10000 == null) {
                  if (var8 != null) {
                     break label58;
                  }
               } else if (!var10000.equals(var8)) {
                  break label58;
               }

               var10000 = this.values().map((x$12) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$12).toImmutableArraySeq());
               Option var9 = var4.values().map((x$13) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$13).toImmutableArraySeq());
               if (var10000 == null) {
                  if (var9 == null) {
                     break label64;
                  }
               } else if (var10000.equals(var9)) {
                  break label64;
               }
            }

            var14 = false;
            return var14;
         }

         var14 = true;
         return var14;
      }
   }

   public int hashCode() {
      int sum = 17;
      sum = 37 * sum + this.name().hashCode();
      sum = 37 * sum + this.index().hashCode();
      sum = 37 * sum + this.isOrdinal().hashCode();
      sum = 37 * sum + this.numValues().hashCode();
      sum = 37 * sum + this.values().map((x$14) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$14).toImmutableArraySeq()).hashCode();
      return sum;
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$8(final MetadataBuilder bldr$2, final int x$9) {
      return bldr$2.putLong(AttributeKeys$.MODULE$.INDEX(), (long)x$9);
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$9(final MetadataBuilder bldr$2, final boolean x$10) {
      return bldr$2.putBoolean(AttributeKeys$.MODULE$.ORDINAL(), x$10);
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$10(final MetadataBuilder bldr$2, final int x$11) {
      return bldr$2.putLong(AttributeKeys$.MODULE$.NUM_VALUES(), (long)x$11);
   }

   public NominalAttribute(final Option name, final Option index, final Option isOrdinal, final Option numValues, final Option values) {
      this.name = name;
      this.index = index;
      this.isOrdinal = isOrdinal;
      this.numValues = numValues;
      this.values = values;
      numValues.foreach((JFunction1.mcVI.sp)(n) -> scala.Predef..MODULE$.require(n >= 0, () -> "numValues cannot be negative but got " + n + "."));
      scala.Predef..MODULE$.require(!numValues.isDefined() || !values.isDefined(), () -> "Cannot have both numValues and values defined.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
