package org.apache.spark.ml.attribute;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.StructField;
import scala.None;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001\u0002\u0012$\u00019B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0005\u000e\u0005\t\r\u0002\u0011\t\u0011)A\u0005k!Aq\t\u0001BC\u0002\u0013\u0005\u0003\n\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003J\u0011!q\u0005A!b\u0001\n\u0003y\u0005\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011\u0002)\t\rU\u0003A\u0011A\u0013W\u0011\u0015Y\u0006\u0001\"\u0011]\u0011\u0015\u0001\u0007\u0001\"\u0011b\u0011\u0015)\u0007\u0001\"\u0011b\u0011\u00151\u0007\u0001\"\u0011h\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u0015Y\u0007\u0001\"\u0011m\u0011\u0015q\u0007\u0001\"\u0011k\u0011\u0015y\u0007\u0001\"\u0001q\u0011\u0015)\b\u0001\"\u0001k\u0011\u00151\b\u0001\"\u0003x\u0011\u001dY\b!%A\u0005\nqD\u0011\"a\u0004\u0001#\u0003%I!!\u0005\t\u0013\u0005U\u0001!%A\u0005\n\u0005]\u0001\u0002CA\u000e\u0001\u0011\u00053%!\b\t\u000f\u0005M\u0002\u0001\"\u0011\u00026!9\u0011\u0011\t\u0001\u0005B\u0005\rsaBA#G!\u0005\u0011q\t\u0004\u0007E\rB\t!!\u0013\t\rUKB\u0011AA4\u0011!\tI'\u0007b\u0001\n\u000bQ\u0007bBA63\u0001\u0006ia\u0016\u0005\t\u0003[JB\u0011I\u0012\u0002p!I\u0011QO\r\u0012\u0002\u0013\u0005Q\u0005 \u0005\u000b\u0003oJ\u0012\u0013!C\u0001K\u0005E\u0001BCA=3E\u0005I\u0011A\u0013\u0002\u0018!I\u00111P\r\u0002\u0002\u0013%\u0011Q\u0010\u0002\u0010\u0005&t\u0017M]=BiR\u0014\u0018NY;uK*\u0011A%J\u0001\nCR$(/\u001b2vi\u0016T!AJ\u0014\u0002\u00055d'B\u0001\u0015*\u0003\u0015\u0019\b/\u0019:l\u0015\tQ3&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002Y\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\f\t\u0003aEj\u0011aI\u0005\u0003e\r\u0012\u0011\"\u0011;ue&\u0014W\u000f^3\u0002\t9\fW.Z\u000b\u0002kA\u0019a'O\u001e\u000e\u0003]R\u0011\u0001O\u0001\u0006g\u000e\fG.Y\u0005\u0003u]\u0012aa\u00149uS>t\u0007C\u0001\u001fD\u001d\ti\u0014\t\u0005\u0002?o5\tqH\u0003\u0002A[\u00051AH]8pizJ!AQ\u001c\u0002\rA\u0013X\rZ3g\u0013\t!UI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0005^\nQA\\1nK\u0002\nQ!\u001b8eKb,\u0012!\u0013\t\u0004meR\u0005C\u0001\u001cL\u0013\tauGA\u0002J]R\fa!\u001b8eKb\u0004\u0013A\u0002<bYV,7/F\u0001Q!\r1\u0014(\u0015\t\u0004mI[\u0014BA*8\u0005\u0015\t%O]1z\u0003\u001d1\u0018\r\\;fg\u0002\na\u0001P5oSRtD\u0003B,Y3j\u0003\"\u0001\r\u0001\t\u000fM:\u0001\u0013!a\u0001k!9qi\u0002I\u0001\u0002\u0004I\u0005b\u0002(\b!\u0003\u0005\r\u0001U\u0001\tCR$(\u000fV=qKV\tQ\f\u0005\u00021=&\u0011ql\t\u0002\u000e\u0003R$(/\u001b2vi\u0016$\u0016\u0010]3\u0002\u0013%\u001ch*^7fe&\u001cW#\u00012\u0011\u0005Y\u001a\u0017B\u000138\u0005\u001d\u0011un\u001c7fC:\f\u0011\"[:O_6Lg.\u00197\u0002\u0011]LG\u000f\u001b(b[\u0016$\"a\u00165\t\u000bMZ\u0001\u0019A\u001e\u0002\u0017]LG\u000f[8vi:\u000bW.Z\u000b\u0002/\u0006Iq/\u001b;i\u0013:$W\r\u001f\u000b\u0003/6DQaR\u0007A\u0002)\u000bAb^5uQ>,H/\u00138eKb\f!b^5uQZ\u000bG.^3t)\r9\u0016o\u001d\u0005\u0006e>\u0001\raO\u0001\t]\u0016<\u0017\r^5wK\")Ao\u0004a\u0001w\u0005A\u0001o\\:ji&4X-A\u0007xSRDw.\u001e;WC2,Xm]\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003XqfT\bbB\u001a\u0012!\u0003\u0005\r!\u000e\u0005\b\u000fF\u0001\n\u00111\u0001J\u0011\u001dq\u0015\u0003%AA\u0002A\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001~U\t)dpK\u0001\u0000!\u0011\t\t!a\u0003\u000e\u0005\u0005\r!\u0002BA\u0003\u0003\u000f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%q'\u0001\u0006b]:|G/\u0019;j_:LA!!\u0004\u0002\u0004\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0003\u0016\u0003\u0013z\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u001a)\u0012\u0001K`\u0001\u000fi>lU\r^1eCR\f\u0017*\u001c9m)\u0011\ty\"a\f\u0011\t\u0005\u0005\u00121F\u0007\u0003\u0003GQA!!\n\u0002(\u0005)A/\u001f9fg*\u0019\u0011\u0011F\u0014\u0002\u0007M\fH.\u0003\u0003\u0002.\u0005\r\"\u0001C'fi\u0006$\u0017\r^1\t\r\u0005ER\u00031\u0001c\u0003!9\u0018\u000e\u001e5UsB,\u0017AB3rk\u0006d7\u000fF\u0002c\u0003oAq!!\u000f\u0017\u0001\u0004\tY$A\u0003pi\",'\u000fE\u00027\u0003{I1!a\u00108\u0005\r\te._\u0001\tQ\u0006\u001c\bnQ8eKR\t!*A\bCS:\f'/_!uiJL'-\u001e;f!\t\u0001\u0014dE\u0004\u001a\u0003\u0017\n\t&a\u0016\u0011\u0007Y\ni%C\u0002\u0002P]\u0012a!\u00118z%\u00164\u0007c\u0001\u0019\u0002T%\u0019\u0011QK\u0012\u0003!\u0005#HO]5ckR,g)Y2u_JL\b\u0003BA-\u0003Gj!!a\u0017\u000b\t\u0005u\u0013qL\u0001\u0003S>T!!!\u0019\u0002\t)\fg/Y\u0005\u0005\u0003K\nYF\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002H\u0005YA-\u001a4bk2$\u0018\t\u001e;s\u00031!WMZ1vYR\fE\u000f\u001e:!\u000311'o\\7NKR\fG-\u0019;b)\r9\u0016\u0011\u000f\u0005\b\u0003gj\u0002\u0019AA\u0010\u0003!iW\r^1eCR\f\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0004\u0003BAA\u0003\u000fk!!a!\u000b\t\u0005\u0015\u0015qL\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\n\u0006\r%AB(cU\u0016\u001cG\u000f"
)
public class BinaryAttribute extends Attribute {
   private final Option name;
   private final Option index;
   private final Option values;

   public static BinaryAttribute defaultAttr() {
      return BinaryAttribute$.MODULE$.defaultAttr();
   }

   public static Attribute fromStructField(final StructField field) {
      return BinaryAttribute$.MODULE$.fromStructField(field);
   }

   public Option name() {
      return this.name;
   }

   public Option index() {
      return this.index;
   }

   public Option values() {
      return this.values;
   }

   public AttributeType attrType() {
      return AttributeType$.MODULE$.Binary();
   }

   public boolean isNumeric() {
      return true;
   }

   public boolean isNominal() {
      return true;
   }

   public BinaryAttribute withName(final String name) {
      return this.copy(new Some(name), this.copy$default$2(), this.copy$default$3());
   }

   public BinaryAttribute withoutName() {
      return this.copy(.MODULE$, this.copy$default$2(), this.copy$default$3());
   }

   public BinaryAttribute withIndex(final int index) {
      Some x$1 = new Some(BoxesRunTime.boxToInteger(index));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$3();
      return this.copy(x$2, x$1, x$3);
   }

   public BinaryAttribute withoutIndex() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$3();
      return this.copy(x$2, x$1, x$3);
   }

   public BinaryAttribute withValues(final String negative, final String positive) {
      Some x$1 = new Some((Object[])(new String[]{negative, positive}));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      return this.copy(x$2, x$3, x$1);
   }

   public BinaryAttribute withoutValues() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      return this.copy(x$2, x$3, x$1);
   }

   private BinaryAttribute copy(final Option name, final Option index, final Option values) {
      return new BinaryAttribute(name, index, values);
   }

   private Option copy$default$1() {
      return this.name();
   }

   private Option copy$default$2() {
      return this.index();
   }

   private Option copy$default$3() {
      return this.values();
   }

   public Metadata toMetadataImpl(final boolean withType) {
      MetadataBuilder bldr = new MetadataBuilder();
      if (withType) {
         bldr.putString(AttributeKeys$.MODULE$.TYPE(), this.attrType().name());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.name().foreach((x$15) -> bldr.putString(AttributeKeys$.MODULE$.NAME(), x$15));
      this.index().foreach((x$16) -> $anonfun$toMetadataImpl$13(bldr, BoxesRunTime.unboxToInt(x$16)));
      this.values().foreach((v) -> bldr.putStringArray(AttributeKeys$.MODULE$.VALUES(), v));
      return bldr.build();
   }

   public boolean equals(final Object other) {
      if (!(other instanceof BinaryAttribute var4)) {
         return false;
      } else {
         boolean var10;
         label48: {
            label42: {
               Option var10000 = this.name();
               Option var5 = var4.name();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var5)) {
                  break label42;
               }

               var10000 = this.index();
               Option var6 = var4.index();
               if (var10000 == null) {
                  if (var6 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var6)) {
                  break label42;
               }

               var10000 = this.values().map((x$17) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$17).toImmutableArraySeq());
               Option var7 = var4.values().map((x$18) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$18).toImmutableArraySeq());
               if (var10000 == null) {
                  if (var7 == null) {
                     break label48;
                  }
               } else if (var10000.equals(var7)) {
                  break label48;
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public int hashCode() {
      int sum = 17;
      sum = 37 * sum + this.name().hashCode();
      sum = 37 * sum + this.index().hashCode();
      sum = 37 * sum + this.values().map((x$19) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$19).toImmutableArraySeq()).hashCode();
      return sum;
   }

   // $FF: synthetic method
   public static final void $anonfun$new$12(final String[] v) {
      scala.Predef..MODULE$.require(v.length == 2, () -> "Number of values must be 2 for a binary attribute but got " + org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(v).toImmutableArraySeq() + ".");
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$13(final MetadataBuilder bldr$3, final int x$16) {
      return bldr$3.putLong(AttributeKeys$.MODULE$.INDEX(), (long)x$16);
   }

   public BinaryAttribute(final Option name, final Option index, final Option values) {
      this.name = name;
      this.index = index;
      this.values = values;
      values.foreach((v) -> {
         $anonfun$new$12(v);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
