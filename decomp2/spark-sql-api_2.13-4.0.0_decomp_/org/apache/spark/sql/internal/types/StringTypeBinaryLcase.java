package org.apache.spark.sql.internal.types;

import org.apache.spark.sql.types.StringType;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001\u0002\f\u0018\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!\r\"9a\nAA\u0001\n\u0003y\u0005bB)\u0001#\u0003%\tA\u0015\u0005\b;\u0002\t\t\u0011\"\u0011_\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0002\u0002\u0013\u0005Q\u000eC\u0004t\u0001\u0005\u0005I\u0011\t;\t\u000fm\u0004\u0011\u0011!C\u0001y\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e\u001d9\u0011\u0011C\f\t\u0002\u0005MaA\u0002\f\u0018\u0011\u0003\t)\u0002\u0003\u0004B#\u0011\u0005\u0011q\u0003\u0005\b\u00033\tB\u0011AA\u000e\u0011%\ty\"EA\u0001\n\u0003\u000b\t\u0003C\u0005\u0002.E\t\t\u0011\"\u0003\u00020\t)2\u000b\u001e:j]\u001e$\u0016\u0010]3CS:\f'/\u001f'dCN,'B\u0001\r\u001a\u0003\u0015!\u0018\u0010]3t\u0015\tQ2$\u0001\u0005j]R,'O\\1m\u0015\taR$A\u0002tc2T!AH\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0001\n\u0013AB1qC\u000eDWMC\u0001#\u0003\ry'oZ\u0002\u0001'\u0011\u0001Q%K\u0018\u0011\u0005\u0019:S\"A\f\n\u0005!:\"AE!cgR\u0014\u0018m\u0019;TiJLgn\u001a+za\u0016\u0004\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\r\na\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u0005]Z\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aN\u0016\u0002+M,\b\u000f]8siN$&/[7D_2d\u0017\r^5p]V\tQ\b\u0005\u0002+}%\u0011qh\u000b\u0002\b\u0005>|G.Z1o\u0003Y\u0019X\u000f\u001d9peR\u001cHK]5n\u0007>dG.\u0019;j_:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002D\tB\u0011a\u0005\u0001\u0005\u0006w\r\u0001\r!P\u0001\u0012C\u000e\u001cW\r\u001d;t'R\u0014\u0018N\\4UsB,GCA\u001fH\u0011\u0015AE\u00011\u0001J\u0003\u0015yG\u000f[3s!\tQE*D\u0001L\u0015\tA2$\u0003\u0002N\u0017\nQ1\u000b\u001e:j]\u001e$\u0016\u0010]3\u0002\t\r|\u0007/\u001f\u000b\u0003\u0007BCqaO\u0003\u0011\u0002\u0003\u0007Q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003MS#!\u0010+,\u0003U\u0003\"AV.\u000e\u0003]S!\u0001W-\u0002\u0013Ut7\r[3dW\u0016$'B\u0001.,\u0003)\tgN\\8uCRLwN\\\u0005\u00039^\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\f\u0005\u0002aK6\t\u0011M\u0003\u0002cG\u0006!A.\u00198h\u0015\u0005!\u0017\u0001\u00026bm\u0006L!AZ1\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005I\u0007C\u0001\u0016k\u0013\tY7FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002ocB\u0011!f\\\u0005\u0003a.\u00121!\u00118z\u0011\u001d\u0011\u0018\"!AA\u0002%\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A;\u0011\u0007YLh.D\u0001x\u0015\tA8&\u0001\u0006d_2dWm\u0019;j_:L!A_<\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003{uDqA]\u0006\u0002\u0002\u0003\u0007a.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA0\u0002\u0002!9!\u000fDA\u0001\u0002\u0004I\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003%\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002?\u00061Q-];bYN$2!PA\b\u0011\u001d\u0011x\"!AA\u00029\fQc\u0015;sS:<G+\u001f9f\u0005&t\u0017M]=MG\u0006\u001cX\r\u0005\u0002'#M\u0011\u0011c\u0011\u000b\u0003\u0003'\tQ!\u00199qYf$2aQA\u000f\u0011\u0015Y4\u00031\u0001>\u0003\u001d)h.\u00199qYf$B!a\t\u0002*A!!&!\n>\u0013\r\t9c\u000b\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005-B#!AA\u0002\r\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0004E\u0002a\u0003gI1!!\u000eb\u0005\u0019y%M[3di\u0002"
)
public class StringTypeBinaryLcase extends AbstractStringType implements Product {
   private final boolean supportsTrimCollation;

   public static Option unapply(final StringTypeBinaryLcase x$0) {
      return StringTypeBinaryLcase$.MODULE$.unapply(x$0);
   }

   public static StringTypeBinaryLcase apply(final boolean supportsTrimCollation) {
      return StringTypeBinaryLcase$.MODULE$.apply(supportsTrimCollation);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean supportsTrimCollation() {
      return this.supportsTrimCollation;
   }

   public boolean acceptsStringType(final StringType other) {
      return other.supportsBinaryEquality() || other.isUTF8LcaseCollation();
   }

   public StringTypeBinaryLcase copy(final boolean supportsTrimCollation) {
      return new StringTypeBinaryLcase(supportsTrimCollation);
   }

   public boolean copy$default$1() {
      return this.supportsTrimCollation();
   }

   public String productPrefix() {
      return "StringTypeBinaryLcase";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.supportsTrimCollation());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof StringTypeBinaryLcase;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "supportsTrimCollation";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.supportsTrimCollation() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof StringTypeBinaryLcase) {
               StringTypeBinaryLcase var4 = (StringTypeBinaryLcase)x$1;
               if (this.supportsTrimCollation() == var4.supportsTrimCollation() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public StringTypeBinaryLcase(final boolean supportsTrimCollation) {
      super(supportsTrimCollation);
      this.supportsTrimCollation = supportsTrimCollation;
      Product.$init$(this);
   }
}
