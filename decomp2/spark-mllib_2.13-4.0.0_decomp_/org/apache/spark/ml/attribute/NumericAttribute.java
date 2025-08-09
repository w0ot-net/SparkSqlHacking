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
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%h\u0001B\u001b7\u0001\u0005C\u0001B\u0012\u0001\u0003\u0006\u0004%\te\u0012\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0011\"A!\f\u0001BC\u0002\u0013\u00053\f\u0003\u0005a\u0001\t\u0005\t\u0015!\u0003]\u0011!\t\u0007A!b\u0001\n\u0003\u0011\u0007\u0002C4\u0001\u0005\u0003\u0005\u000b\u0011B2\t\u0011!\u0004!Q1A\u0005\u0002\tD\u0001\"\u001b\u0001\u0003\u0002\u0003\u0006Ia\u0019\u0005\tU\u0002\u0011)\u0019!C\u0001E\"A1\u000e\u0001B\u0001B\u0003%1\r\u0003\u0005m\u0001\t\u0015\r\u0011\"\u0001c\u0011!i\u0007A!A!\u0002\u0013\u0019\u0007B\u00028\u0001\t\u0003At\u000eC\u0003x\u0001\u0011\u0005\u0003\u0010C\u0003}\u0001\u0011\u0005S\u0010\u0003\u0004\u0000\u0001\u0011\u0005\u0013\u0011\u0001\u0005\b\u0003\u0007\u0001A\u0011IA\u0003\u0011\u001d\tI\u0001\u0001C!\u0003\u0003Aq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0012\u0001!\t!!\u0001\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016!9\u0011\u0011\u0004\u0001\u0005\u0002\u0005\u0005\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003C\u0001A\u0011AA\u0001\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!!\u000b\u0001\t\u0003\t\t\u0001C\u0004\u0002,\u0001!\t!!\u0001\t\u000f\u00055\u0002\u0001\"\u0011\u00020!9\u0011q\u0007\u0001\u0005B\u0005=\u0002\u0002CA\u001d\u0001\u0011\u0005c'a\u000f\t\u000f\u0005E\u0003\u0001\"\u0003\u0002T!I\u0011\u0011\r\u0001\u0012\u0002\u0013%\u00111\r\u0005\n\u0003s\u0002\u0011\u0013!C\u0005\u0003wB\u0011\"a \u0001#\u0003%I!!!\t\u0013\u0005\u0015\u0005!%A\u0005\n\u0005\u0005\u0005\"CAD\u0001E\u0005I\u0011BAA\u0011%\tI\tAI\u0001\n\u0013\t\t\tC\u0004\u0002\f\u0002!\t%!$\t\u000f\u0005e\u0005\u0001\"\u0011\u0002\u001c\u001e9\u0011Q\u0014\u001c\t\u0002\u0005}eAB\u001b7\u0011\u0003\t\t\u000b\u0003\u0004oS\u0011\u0005\u0011q\u0018\u0005\n\u0003\u0003L#\u0019!C\u0001\u0003\u0003Aq!a1*A\u0003%\u0001\u000f\u0003\u0005\u0002F&\"\tENAd\u0011)\ti-KI\u0001\n\u0003A\u00141\r\u0005\u000b\u0003\u001fL\u0013\u0013!C\u0001q\u0005m\u0004BCAiSE\u0005I\u0011\u0001\u001d\u0002\u0002\"Q\u00111[\u0015\u0012\u0002\u0013\u0005\u0001(!!\t\u0015\u0005U\u0017&%A\u0005\u0002a\n\t\t\u0003\u0006\u0002X&\n\n\u0011\"\u00019\u0003\u0003C\u0011\"!7*\u0003\u0003%I!a7\u0003!9+X.\u001a:jG\u0006#HO]5ckR,'BA\u001c9\u0003%\tG\u000f\u001e:jEV$XM\u0003\u0002:u\u0005\u0011Q\u000e\u001c\u0006\u0003wq\nQa\u001d9be.T!!\u0010 \u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0014aA8sO\u000e\u00011C\u0001\u0001C!\t\u0019E)D\u00017\u0013\t)eGA\u0005BiR\u0014\u0018NY;uK\u0006!a.Y7f+\u0005A\u0005cA%M\u001d6\t!JC\u0001L\u0003\u0015\u00198-\u00197b\u0013\ti%J\u0001\u0004PaRLwN\u001c\t\u0003\u001fZs!\u0001\u0015+\u0011\u0005ESU\"\u0001*\u000b\u0005M\u0003\u0015A\u0002\u001fs_>$h(\u0003\u0002V\u0015\u00061\u0001K]3eK\u001aL!a\u0016-\u0003\rM#(/\u001b8h\u0015\t)&*A\u0003oC6,\u0007%A\u0003j]\u0012,\u00070F\u0001]!\rIE*\u0018\t\u0003\u0013zK!a\u0018&\u0003\u0007%sG/\u0001\u0004j]\u0012,\u0007\u0010I\u0001\u0004[&tW#A2\u0011\u0007%cE\r\u0005\u0002JK&\u0011aM\u0013\u0002\u0007\t>,(\r\\3\u0002\t5Lg\u000eI\u0001\u0004[\u0006D\u0018\u0001B7bq\u0002\n1a\u001d;e\u0003\u0011\u0019H\u000f\u001a\u0011\u0002\u0011M\u0004\u0018M]:jif\f\u0011b\u001d9beNLG/\u001f\u0011\u0002\rqJg.\u001b;?)\u001d\u0001\u0018O]:ukZ\u0004\"a\u0011\u0001\t\u000f\u0019k\u0001\u0013!a\u0001\u0011\"9!,\u0004I\u0001\u0002\u0004a\u0006bB1\u000e!\u0003\u0005\ra\u0019\u0005\bQ6\u0001\n\u00111\u0001d\u0011\u001dQW\u0002%AA\u0002\rDq\u0001\\\u0007\u0011\u0002\u0003\u00071-\u0001\u0005biR\u0014H+\u001f9f+\u0005I\bCA\"{\u0013\tYhGA\u0007BiR\u0014\u0018NY;uKRK\b/Z\u0001\to&$\bNT1nKR\u0011\u0001O \u0005\u0006\r>\u0001\rAT\u0001\fo&$\bn\\;u\u001d\u0006lW-F\u0001q\u0003%9\u0018\u000e\u001e5J]\u0012,\u0007\u0010F\u0002q\u0003\u000fAQAW\tA\u0002u\u000bAb^5uQ>,H/\u00138eKb\fqa^5uQ6Kg\u000eF\u0002q\u0003\u001fAQ!Y\nA\u0002\u0011\f!b^5uQ>,H/T5o\u0003\u001d9\u0018\u000e\u001e5NCb$2\u0001]A\f\u0011\u0015AW\u00031\u0001e\u0003)9\u0018\u000e\u001e5pkRl\u0015\r_\u0001\bo&$\bn\u0015;e)\r\u0001\u0018q\u0004\u0005\u0006U^\u0001\r\u0001Z\u0001\u000bo&$\bn\\;u'R$\u0017\u0001D<ji\"\u001c\u0006/\u0019:tSRLHc\u00019\u0002(!)A.\u0007a\u0001I\u0006yq/\u001b;i_V$8\u000b]1sg&$\u00180\u0001\bxSRDw.\u001e;Tk6l\u0017M]=\u0002\u0013%\u001ch*^7fe&\u001cWCAA\u0019!\rI\u00151G\u0005\u0004\u0003kQ%a\u0002\"p_2,\u0017M\\\u0001\nSNtu.\\5oC2\fa\u0002^8NKR\fG-\u0019;b\u00136\u0004H\u000e\u0006\u0003\u0002>\u00055\u0003\u0003BA \u0003\u0013j!!!\u0011\u000b\t\u0005\r\u0013QI\u0001\u0006if\u0004Xm\u001d\u0006\u0004\u0003\u000fR\u0014aA:rY&!\u00111JA!\u0005!iU\r^1eCR\f\u0007bBA(=\u0001\u0007\u0011\u0011G\u0001\to&$\b\u000eV=qK\u0006!1m\u001c9z)5\u0001\u0018QKA,\u00033\nY&!\u0018\u0002`!9ai\bI\u0001\u0002\u0004A\u0005b\u0002. !\u0003\u0005\r\u0001\u0018\u0005\bC~\u0001\n\u00111\u0001d\u0011\u001dAw\u0004%AA\u0002\rDqA[\u0010\u0011\u0002\u0003\u00071\rC\u0004m?A\u0005\t\u0019A2\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\r\u0016\u0004\u0011\u0006\u001d4FAA5!\u0011\tY'!\u001e\u000e\u0005\u00055$\u0002BA8\u0003c\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005M$*\u0001\u0006b]:|G/\u0019;j_:LA!a\u001e\u0002n\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011Q\u0010\u0016\u00049\u0006\u001d\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003\u0007S3aYA4\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nabY8qs\u0012\"WMZ1vYR$S'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0002\r\u0015\fX/\u00197t)\u0011\t\t$a$\t\u000f\u0005Ee\u00051\u0001\u0002\u0014\u0006)q\u000e\u001e5feB\u0019\u0011*!&\n\u0007\u0005]%JA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002;\u0006\u0001b*^7fe&\u001c\u0017\t\u001e;sS\n,H/\u001a\t\u0003\u0007&\u001ar!KAR\u0003S\u000by\u000bE\u0002J\u0003KK1!a*K\u0005\u0019\te.\u001f*fMB\u00191)a+\n\u0007\u00055fG\u0001\tBiR\u0014\u0018NY;uK\u001a\u000b7\r^8ssB!\u0011\u0011WA^\u001b\t\t\u0019L\u0003\u0003\u00026\u0006]\u0016AA5p\u0015\t\tI,\u0001\u0003kCZ\f\u0017\u0002BA_\u0003g\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a(\u0002\u0017\u0011,g-Y;mi\u0006#HO]\u0001\rI\u00164\u0017-\u001e7u\u0003R$(\u000fI\u0001\rMJ|W.T3uC\u0012\fG/\u0019\u000b\u0004a\u0006%\u0007bBAf[\u0001\u0007\u0011QH\u0001\t[\u0016$\u0018\rZ1uC\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00137\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u000e\u0005\u0003\u0002`\u0006\u0015XBAAq\u0015\u0011\t\u0019/a.\u0002\t1\fgnZ\u0005\u0005\u0003O\f\tO\u0001\u0004PE*,7\r\u001e"
)
public class NumericAttribute extends Attribute {
   private final Option name;
   private final Option index;
   private final Option min;
   private final Option max;
   private final Option std;
   private final Option sparsity;

   public static NumericAttribute defaultAttr() {
      return NumericAttribute$.MODULE$.defaultAttr();
   }

   public static Attribute fromStructField(final StructField field) {
      return NumericAttribute$.MODULE$.fromStructField(field);
   }

   public Option name() {
      return this.name;
   }

   public Option index() {
      return this.index;
   }

   public Option min() {
      return this.min;
   }

   public Option max() {
      return this.max;
   }

   public Option std() {
      return this.std;
   }

   public Option sparsity() {
      return this.sparsity;
   }

   public AttributeType attrType() {
      return AttributeType$.MODULE$.Numeric();
   }

   public NumericAttribute withName(final String name) {
      return this.copy(new Some(name), this.copy$default$2(), this.copy$default$3(), this.copy$default$4(), this.copy$default$5(), this.copy$default$6());
   }

   public NumericAttribute withoutName() {
      return this.copy(.MODULE$, this.copy$default$2(), this.copy$default$3(), this.copy$default$4(), this.copy$default$5(), this.copy$default$6());
   }

   public NumericAttribute withIndex(final int index) {
      Some x$1 = new Some(BoxesRunTime.boxToInteger(index));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$3();
      Option x$4 = this.copy$default$4();
      Option x$5 = this.copy$default$5();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$1, x$3, x$4, x$5, x$6);
   }

   public NumericAttribute withoutIndex() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$3();
      Option x$4 = this.copy$default$4();
      Option x$5 = this.copy$default$5();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$1, x$3, x$4, x$5, x$6);
   }

   public NumericAttribute withMin(final double min) {
      Some x$1 = new Some(BoxesRunTime.boxToDouble(min));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$4();
      Option x$5 = this.copy$default$5();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$1, x$4, x$5, x$6);
   }

   public NumericAttribute withoutMin() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$4();
      Option x$5 = this.copy$default$5();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$1, x$4, x$5, x$6);
   }

   public NumericAttribute withMax(final double max) {
      Some x$1 = new Some(BoxesRunTime.boxToDouble(max));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$5();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$4, x$1, x$5, x$6);
   }

   public NumericAttribute withoutMax() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$5();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$4, x$1, x$5, x$6);
   }

   public NumericAttribute withStd(final double std) {
      Some x$1 = new Some(BoxesRunTime.boxToDouble(std));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$4();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$4, x$5, x$1, x$6);
   }

   public NumericAttribute withoutStd() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$4();
      Option x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$4, x$5, x$1, x$6);
   }

   public NumericAttribute withSparsity(final double sparsity) {
      Some x$1 = new Some(BoxesRunTime.boxToDouble(sparsity));
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$4();
      Option x$6 = this.copy$default$5();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$1);
   }

   public NumericAttribute withoutSparsity() {
      None x$1 = .MODULE$;
      Option x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$4();
      Option x$6 = this.copy$default$5();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$1);
   }

   public NumericAttribute withoutSummary() {
      None x$1 = .MODULE$;
      None x$2 = .MODULE$;
      None x$3 = .MODULE$;
      None x$4 = .MODULE$;
      Option x$5 = this.copy$default$1();
      Option x$6 = this.copy$default$2();
      return this.copy(x$5, x$6, x$1, x$2, x$3, x$4);
   }

   public boolean isNumeric() {
      return true;
   }

   public boolean isNominal() {
      return false;
   }

   public Metadata toMetadataImpl(final boolean withType) {
      MetadataBuilder bldr = new MetadataBuilder();
      if (withType) {
         bldr.putString(AttributeKeys$.MODULE$.TYPE(), this.attrType().name());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.name().foreach((x$1) -> bldr.putString(AttributeKeys$.MODULE$.NAME(), x$1));
      this.index().foreach((x$2) -> $anonfun$toMetadataImpl$2(bldr, BoxesRunTime.unboxToInt(x$2)));
      this.min().foreach((x$3) -> $anonfun$toMetadataImpl$3(bldr, BoxesRunTime.unboxToDouble(x$3)));
      this.max().foreach((x$4) -> $anonfun$toMetadataImpl$4(bldr, BoxesRunTime.unboxToDouble(x$4)));
      this.std().foreach((x$5) -> $anonfun$toMetadataImpl$5(bldr, BoxesRunTime.unboxToDouble(x$5)));
      this.sparsity().foreach((x$6) -> $anonfun$toMetadataImpl$6(bldr, BoxesRunTime.unboxToDouble(x$6)));
      return bldr.build();
   }

   private NumericAttribute copy(final Option name, final Option index, final Option min, final Option max, final Option std, final Option sparsity) {
      return new NumericAttribute(name, index, min, max, std, sparsity);
   }

   private Option copy$default$1() {
      return this.name();
   }

   private Option copy$default$2() {
      return this.index();
   }

   private Option copy$default$3() {
      return this.min();
   }

   private Option copy$default$4() {
      return this.max();
   }

   private Option copy$default$5() {
      return this.std();
   }

   private Option copy$default$6() {
      return this.sparsity();
   }

   public boolean equals(final Object other) {
      if (!(other instanceof NumericAttribute var4)) {
         return false;
      } else {
         boolean var16;
         label72: {
            label66: {
               Option var10000 = this.name();
               Option var5 = var4.name();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label66;
                  }
               } else if (!var10000.equals(var5)) {
                  break label66;
               }

               var10000 = this.index();
               Option var6 = var4.index();
               if (var10000 == null) {
                  if (var6 != null) {
                     break label66;
                  }
               } else if (!var10000.equals(var6)) {
                  break label66;
               }

               var10000 = this.min();
               Option var7 = var4.min();
               if (var10000 == null) {
                  if (var7 != null) {
                     break label66;
                  }
               } else if (!var10000.equals(var7)) {
                  break label66;
               }

               var10000 = this.max();
               Option var8 = var4.max();
               if (var10000 == null) {
                  if (var8 != null) {
                     break label66;
                  }
               } else if (!var10000.equals(var8)) {
                  break label66;
               }

               var10000 = this.std();
               Option var9 = var4.std();
               if (var10000 == null) {
                  if (var9 != null) {
                     break label66;
                  }
               } else if (!var10000.equals(var9)) {
                  break label66;
               }

               var10000 = this.sparsity();
               Option var10 = var4.sparsity();
               if (var10000 == null) {
                  if (var10 == null) {
                     break label72;
                  }
               } else if (var10000.equals(var10)) {
                  break label72;
               }
            }

            var16 = false;
            return var16;
         }

         var16 = true;
         return var16;
      }
   }

   public int hashCode() {
      int sum = 17;
      sum = 37 * sum + this.name().hashCode();
      sum = 37 * sum + this.index().hashCode();
      sum = 37 * sum + this.min().hashCode();
      sum = 37 * sum + this.max().hashCode();
      sum = 37 * sum + this.std().hashCode();
      sum = 37 * sum + this.sparsity().hashCode();
      return sum;
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$2(final MetadataBuilder bldr$1, final int x$2) {
      return bldr$1.putLong(AttributeKeys$.MODULE$.INDEX(), (long)x$2);
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$3(final MetadataBuilder bldr$1, final double x$3) {
      return bldr$1.putDouble(AttributeKeys$.MODULE$.MIN(), x$3);
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$4(final MetadataBuilder bldr$1, final double x$4) {
      return bldr$1.putDouble(AttributeKeys$.MODULE$.MAX(), x$4);
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$5(final MetadataBuilder bldr$1, final double x$5) {
      return bldr$1.putDouble(AttributeKeys$.MODULE$.STD(), x$5);
   }

   // $FF: synthetic method
   public static final MetadataBuilder $anonfun$toMetadataImpl$6(final MetadataBuilder bldr$1, final double x$6) {
      return bldr$1.putDouble(AttributeKeys$.MODULE$.SPARSITY(), x$6);
   }

   public NumericAttribute(final Option name, final Option index, final Option min, final Option max, final Option std, final Option sparsity) {
      this.name = name;
      this.index = index;
      this.min = min;
      this.max = max;
      this.std = std;
      this.sparsity = sparsity;
      std.foreach((JFunction1.mcVD.sp)(s) -> scala.Predef..MODULE$.require(s >= (double)0.0F, () -> "Standard deviation cannot be negative but got " + s + "."));
      sparsity.foreach((JFunction1.mcVD.sp)(s) -> scala.Predef..MODULE$.require(s >= (double)0.0F && s <= (double)1.0F, () -> "Sparsity must be in [0, 1] but got " + s + "."));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
