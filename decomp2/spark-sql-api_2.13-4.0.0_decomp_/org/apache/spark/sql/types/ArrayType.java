package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.StringConcat;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.JsonListAssoc.;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005}x!B\u0014)\u0011\u0003\u0019d!B\u001b)\u0011\u00031\u0004\"\u0002\"\u0002\t\u0003\u0019\u0005\"\u0002#\u0002\t\u0003)\u0005bBAg\u0003\u0011\u0005#&\u0018\u0005\t\u0003\u001f\fA\u0011\t\u0016\u0002R\"A\u00111F\u0001\u0005B1\ni\u0003\u0003\u0005E\u0003\u0005\u0005I\u0011QAl\u0011%\ti.AA\u0001\n\u0003\u000by\u000eC\u0005\u0002r\u0006\t\t\u0011\"\u0003\u0002t\u001a!Q\u0007\u000b!H\u0011!a&B!f\u0001\n\u0003i\u0006\u0002\u00030\u000b\u0005#\u0005\u000b\u0011\u0002%\t\u0011}S!Q3A\u0005\u0002\u0001D\u0001\u0002\u001a\u0006\u0003\u0012\u0003\u0006I!\u0019\u0005\u0006\u0005*!\t!\u001a\u0005\u0006\u0005*!\t\u0002\u001b\u0005\u0007S*!\tA\u000b6\t\u0011\u0005=!\u0002\"\u0011+\u0003#Aq!a\n\u000b\t\u0003\nI\u0003C\u0004\u0002,)!\t%!\f\t\u000f\u0005=\"\u0002\"\u0011\u0002.!11F\u0003C!\u0003[A\u0001\"!\r\u000b\t\u0003b\u00131\u0007\u0005\b\u0003kQA\u0011AA\u001a\u0011!\t9D\u0003C!Y\u0005e\u0002\u0002CA#\u0015\u0011\u0005C&a\u0012\t\u0013\u0005E#\"!A\u0005\u0002\u0005M\u0003\"CA-\u0015E\u0005I\u0011AA.\u0011%\t\tHCI\u0001\n\u0003\t\u0019\bC\u0005\u0002x)\t\t\u0011\"\u0011\u0002z!I\u0011Q\u0011\u0006\u0002\u0002\u0013\u0005\u0011\u0011\u0006\u0005\n\u0003\u000fS\u0011\u0011!C\u0001\u0003\u0013C\u0011\"!&\u000b\u0003\u0003%\t%a&\t\u0013\u0005\u0015&\"!A\u0005\u0002\u0005\u001d\u0006\"CAV\u0015\u0005\u0005I\u0011IAW\u0011%\t\tLCA\u0001\n\u0003\n\u0019\fC\u0005\u00026*\t\t\u0011\"\u0011\u00028\"I\u0011\u0011\u0018\u0006\u0002\u0002\u0013\u0005\u00131X\u0001\n\u0003J\u0014\u0018-\u001f+za\u0016T!!\u000b\u0016\u0002\u000bQL\b/Z:\u000b\u0005-b\u0013aA:rY*\u0011QFL\u0001\u0006gB\f'o\u001b\u0006\u0003_A\na!\u00199bG\",'\"A\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005Q\nQ\"\u0001\u0015\u0003\u0013\u0005\u0013(/Y=UsB,7cA\u00018uA\u0011A\u0007O\u0005\u0003s!\u0012\u0001#\u00112tiJ\f7\r\u001e#bi\u0006$\u0016\u0010]3\u0011\u0005m\u0002U\"\u0001\u001f\u000b\u0005ur\u0014AA5p\u0015\u0005y\u0014\u0001\u00026bm\u0006L!!\u0011\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\u0019\u0014!B1qa2LHc\u0001$\u0002LB\u0011AGC\n\u0005\u0015![\u0015\u000b\u0005\u00025\u0013&\u0011!\n\u000b\u0002\t\t\u0006$\u0018\rV=qKB\u0011AjT\u0007\u0002\u001b*\ta*A\u0003tG\u0006d\u0017-\u0003\u0002Q\u001b\n9\u0001K]8ek\u000e$\bC\u0001*[\u001d\t\u0019\u0006L\u0004\u0002U/6\tQK\u0003\u0002We\u00051AH]8pizJ\u0011AT\u0005\u000336\u000bq\u0001]1dW\u0006<W-\u0003\u0002B7*\u0011\u0011,T\u0001\fK2,W.\u001a8u)f\u0004X-F\u0001I\u00031)G.Z7f]R$\u0016\u0010]3!\u00031\u0019wN\u001c;bS:\u001ch*\u001e7m+\u0005\t\u0007C\u0001'c\u0013\t\u0019WJA\u0004C_>dW-\u00198\u0002\u001b\r|g\u000e^1j]NtU\u000f\u001c7!)\r1em\u001a\u0005\u00069>\u0001\r\u0001\u0013\u0005\u0006?>\u0001\r!\u0019\u000b\u0002\r\u0006!\"-^5mI\u001a{'/\\1ui\u0016$7\u000b\u001e:j]\u001e$Ra\u001b8y\u0003\u000b\u0001\"\u0001\u00147\n\u00055l%\u0001B+oSRDQa\\\tA\u0002A\fa\u0001\u001d:fM&D\bCA9v\u001d\t\u00118\u000f\u0005\u0002U\u001b&\u0011A/T\u0001\u0007!J,G-\u001a4\n\u0005Y<(AB*ue&twM\u0003\u0002u\u001b\")\u00110\u0005a\u0001u\u0006a1\u000f\u001e:j]\u001e\u001cuN\\2biB\u001910!\u0001\u000e\u0003qT!! @\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u007f*\n\u0001bY1uC2L8\u000f^\u0005\u0004\u0003\u0007a(\u0001D*ue&twmQ8oG\u0006$\bbBA\u0004#\u0001\u0007\u0011\u0011B\u0001\t[\u0006DH)\u001a9uQB\u0019A*a\u0003\n\u0007\u00055QJA\u0002J]R\f\u0011B[:p]Z\u000bG.^3\u0016\u0005\u0005M\u0001\u0003BA\u000b\u0003CqA!a\u0006\u0002\u001e5\u0011\u0011\u0011\u0004\u0006\u0004\u00037\u0001\u0014A\u00026t_:$4/\u0003\u0003\u0002 \u0005e\u0011a\u0002&t_:\f5\u000bV\u0005\u0005\u0003G\t)CA\u0004K\u001f\nTWm\u0019;\u000b\t\u0005}\u0011\u0011D\u0001\fI\u00164\u0017-\u001e7u'&TX-\u0006\u0002\u0002\n\u0005a1/[7qY\u0016\u001cFO]5oOV\t\u0001/A\u0007dCR\fGn\\4TiJLgnZ\u0001\u000bCNtU\u000f\u001c7bE2,W#\u0001$\u0002\u0015Q|g*\u001e7mC\ndW-A\tfq&\u001cHo\u001d*fGV\u00148/\u001b<fYf$2!YA\u001e\u0011\u001d\ti$\u0007a\u0001\u0003\u007f\t\u0011A\u001a\t\u0006\u0019\u0006\u0005\u0003*Y\u0005\u0004\u0003\u0007j%!\u0003$v]\u000e$\u0018n\u001c82\u0003Q!(/\u00198tM>\u0014XNU3dkJ\u001c\u0018N^3msR\u0019\u0001*!\u0013\t\u000f\u0005u\"\u00041\u0001\u0002LA)A*!\u0014I\u0011&\u0019\u0011qJ'\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\fAaY8qsR)a)!\u0016\u0002X!9Al\u0007I\u0001\u0002\u0004A\u0005bB0\u001c!\u0003\u0005\r!Y\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tiFK\u0002I\u0003?Z#!!\u0019\u0011\t\u0005\r\u0014QN\u0007\u0003\u0003KRA!a\u001a\u0002j\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003Wj\u0015AC1o]>$\u0018\r^5p]&!\u0011qNA3\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t)HK\u0002b\u0003?\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA>!\u0011\ti(a!\u000e\u0005\u0005}$bAAA}\u0005!A.\u00198h\u0013\r1\u0018qP\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tY)!%\u0011\u00071\u000bi)C\u0002\u0002\u00106\u00131!\u00118z\u0011%\t\u0019\nIA\u0001\u0002\u0004\tI!A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00033\u0003b!a'\u0002\"\u0006-UBAAO\u0015\r\ty*T\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAR\u0003;\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019\u0011-!+\t\u0013\u0005M%%!AA\u0002\u0005-\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u001f\u00020\"I\u00111S\u0012\u0002\u0002\u0003\u0007\u0011\u0011B\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011B\u0001\ti>\u001cFO]5oOR\u0011\u00111P\u0001\u0007KF,\u0018\r\\:\u0015\u0007\u0005\fi\fC\u0005\u0002\u0014\u001a\n\t\u00111\u0001\u0002\f\"\u001a!\"!1\u0011\t\u0005\r\u0017qY\u0007\u0003\u0003\u000bT1!a\u001b-\u0013\u0011\tI-!2\u0003\rM#\u0018M\u00197f\u0011\u0015a6\u00011\u0001I\u0003M!WMZ1vYR\u001cuN\\2sKR,G+\u001f9f\u0003-\t7mY3qiN$\u0016\u0010]3\u0015\u0007\u0005\f\u0019\u000e\u0003\u0004\u0002V\u0016\u0001\r\u0001S\u0001\u0006_RDWM\u001d\u000b\u0006\r\u0006e\u00171\u001c\u0005\u00069\u001e\u0001\r\u0001\u0013\u0005\u0006?\u001e\u0001\r!Y\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t/!<\u0011\u000b1\u000b\u0019/a:\n\u0007\u0005\u0015XJ\u0001\u0004PaRLwN\u001c\t\u0006\u0019\u0006%\b*Y\u0005\u0004\u0003Wl%A\u0002+va2,'\u0007\u0003\u0005\u0002p\"\t\t\u00111\u0001G\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u0004B!! \u0002x&!\u0011\u0011`A@\u0005\u0019y%M[3di\"\u001a\u0011!!1)\u0007\u0001\t\t\r"
)
public class ArrayType extends DataType implements Product, Serializable {
   private final DataType elementType;
   private final boolean containsNull;

   public static Option unapply(final ArrayType x$0) {
      return ArrayType$.MODULE$.unapply(x$0);
   }

   public static ArrayType apply(final DataType elementType, final boolean containsNull) {
      return ArrayType$.MODULE$.apply(elementType, containsNull);
   }

   public static ArrayType apply(final DataType elementType) {
      return ArrayType$.MODULE$.apply(elementType);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public DataType elementType() {
      return this.elementType;
   }

   public boolean containsNull() {
      return this.containsNull;
   }

   public void buildFormattedString(final String prefix, final StringConcat stringConcat, final int maxDepth) {
      if (maxDepth > 0) {
         stringConcat.append(prefix + "-- element: " + this.elementType().typeName() + " (containsNull = " + this.containsNull() + ")\n");
         DataType$.MODULE$.buildFormattedString(this.elementType(), prefix + "    |", stringConcat, maxDepth);
      }
   }

   public JObject jsonValue() {
      return .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), this.typeName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("elementType"), this.elementType().jsonValue()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), scala.Predef..MODULE$.$conforms())), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("containsNull"), BoxesRunTime.boxToBoolean(this.containsNull())), (x) -> $anonfun$jsonValue$3(BoxesRunTime.unboxToBoolean(x))));
   }

   public int defaultSize() {
      return 1 * this.elementType().defaultSize();
   }

   public String simpleString() {
      return "array<" + this.elementType().simpleString() + ">";
   }

   public String catalogString() {
      return "array<" + this.elementType().catalogString() + ">";
   }

   public String sql() {
      return "ARRAY<" + this.elementType().sql() + ">";
   }

   public ArrayType asNullable() {
      return new ArrayType(this.elementType().asNullable(), true);
   }

   public ArrayType toNullable() {
      return this.asNullable();
   }

   public boolean existsRecursively(final Function1 f) {
      return BoxesRunTime.unboxToBoolean(f.apply(this)) || this.elementType().existsRecursively(f);
   }

   public DataType transformRecursively(final PartialFunction f) {
      return (DataType)(f.isDefinedAt(this) ? (DataType)f.apply(this) : new ArrayType(this.elementType().transformRecursively(f), this.containsNull()));
   }

   public ArrayType copy(final DataType elementType, final boolean containsNull) {
      return new ArrayType(elementType, containsNull);
   }

   public DataType copy$default$1() {
      return this.elementType();
   }

   public boolean copy$default$2() {
      return this.containsNull();
   }

   public String productPrefix() {
      return "ArrayType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.elementType();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.containsNull());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ArrayType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "elementType";
         }
         case 1 -> {
            return "containsNull";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.elementType()));
      var1 = Statics.mix(var1, this.containsNull() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof ArrayType) {
               ArrayType var4 = (ArrayType)x$1;
               if (this.containsNull() == var4.containsNull()) {
                  label44: {
                     DataType var10000 = this.elementType();
                     DataType var5 = var4.elementType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   // $FF: synthetic method
   public static final JValue $anonfun$jsonValue$3(final boolean x) {
      return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
   }

   public ArrayType(final DataType elementType, final boolean containsNull) {
      this.elementType = elementType;
      this.containsNull = containsNull;
      Product.$init$(this);
   }

   public ArrayType() {
      this((DataType)null, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
