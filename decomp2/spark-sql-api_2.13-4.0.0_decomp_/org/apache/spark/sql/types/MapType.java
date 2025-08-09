package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.StringConcat;
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
   bytes = "\u0006\u0005\t\u0005b\u0001B\u0016-\u0001^B\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t!\u0002\u0011\t\u0012)A\u0005q!A\u0011\u000b\u0001BK\u0002\u0013\u0005q\n\u0003\u0005S\u0001\tE\t\u0015!\u00039\u0011!\u0019\u0006A!f\u0001\n\u0003!\u0006\u0002\u0003-\u0001\u0005#\u0005\u000b\u0011B+\t\u000be\u0003A\u0011\u0001.\t\u000be\u0003A\u0011A0\t\r\u0001\u0004A\u0011\u0001\u0018b\u0011!q\b!%A\u0005\u00029z\b\u0002CA\u000b\u0001\u0011\u0005c&a\u0006\t\u000f\u0005M\u0002\u0001\"\u0011\u00026!9\u0011q\u0007\u0001\u0005B\u0005e\u0002bBA\u001e\u0001\u0011\u0005\u0013\u0011\b\u0005\u0007_\u0001!\t%!\u000f\t\u0011\u0005u\u0002\u0001\"\u00111\u0003\u007fAq!!\u0011\u0001\t\u0003\ty\u0004\u0003\u0005\u0002D\u0001!\t\u0005MA#\u0011!\t\t\u0006\u0001C!a\u0005M\u0003\"CA/\u0001\u0005\u0005I\u0011AA0\u0011%\t9\u0007AI\u0001\n\u0003\tI\u0007C\u0005\u0002n\u0001\t\n\u0011\"\u0001\u0002j!I\u0011q\u000e\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003k\u0002\u0011\u0011!C!\u0003oB\u0011\"a\"\u0001\u0003\u0003%\t!!\u000e\t\u0013\u0005%\u0005!!A\u0005\u0002\u0005-\u0005\"CAL\u0001\u0005\u0005I\u0011IAM\u0011%\t9\u000bAA\u0001\n\u0003\tI\u000bC\u0005\u0002.\u0002\t\t\u0011\"\u0011\u00020\"I\u00111\u0017\u0001\u0002\u0002\u0013\u0005\u0013Q\u0017\u0005\n\u0003o\u0003\u0011\u0011!C!\u0003sC\u0011\"a/\u0001\u0003\u0003%\t%!0\b\u000f\u00055G\u0006#\u0001\u0002P\u001a11\u0006\fE\u0001\u0003#Da!\u0017\u0012\u0005\u0002\u0005\r\bbBAsE\u0011\u0005cf\u0014\u0005\t\u0003O\u0014C\u0011\t\u0018\u0002j\"A\u0011q\u0007\u0012\u0005B9\nI\u0004C\u0004\u0002p\n\"\t!!=\t\u0013\u0005=(%!A\u0005\u0002\u0006]\b\"CA\u0000E\u0005\u0005I\u0011\u0011B\u0001\u0011%\u0011\u0019BIA\u0001\n\u0013\u0011)BA\u0004NCB$\u0016\u0010]3\u000b\u00055r\u0013!\u0002;za\u0016\u001c(BA\u00181\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003cI\nQa\u001d9be.T!a\r\u001b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0014aA8sO\u000e\u00011\u0003\u0002\u00019y\t\u0003\"!\u000f\u001e\u000e\u00031J!a\u000f\u0017\u0003\u0011\u0011\u000bG/\u0019+za\u0016\u0004\"!\u0010!\u000e\u0003yR\u0011aP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0003z\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002D\u0017:\u0011A)\u0013\b\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000fZ\na\u0001\u0010:p_Rt\u0014\"A \n\u0005)s\u0014a\u00029bG.\fw-Z\u0005\u0003\u00196\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0013 \u0002\u000f-,\u0017\u0010V=qKV\t\u0001(\u0001\u0005lKf$\u0016\u0010]3!\u0003%1\u0018\r\\;f)f\u0004X-\u0001\u0006wC2,X\rV=qK\u0002\n\u0011C^1mk\u0016\u001cuN\u001c;bS:\u001ch*\u001e7m+\u0005)\u0006CA\u001fW\u0013\t9fHA\u0004C_>dW-\u00198\u0002%Y\fG.^3D_:$\u0018-\u001b8t\u001dVdG\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tmcVL\u0018\t\u0003s\u0001AQAT\u0004A\u0002aBQ!U\u0004A\u0002aBQaU\u0004A\u0002U#\u0012aW\u0001\u0015EVLG\u000e\u001a$pe6\fG\u000f^3e'R\u0014\u0018N\\4\u0015\t\t,w.\u001f\t\u0003{\rL!\u0001\u001a \u0003\tUs\u0017\u000e\u001e\u0005\u0006M&\u0001\raZ\u0001\u0007aJ,g-\u001b=\u0011\u0005!dgBA5k!\t)e(\u0003\u0002l}\u00051\u0001K]3eK\u001aL!!\u001c8\u0003\rM#(/\u001b8h\u0015\tYg\bC\u0003q\u0013\u0001\u0007\u0011/\u0001\u0007tiJLgnZ\"p]\u000e\fG\u000f\u0005\u0002so6\t1O\u0003\u0002uk\u0006!Q\u000f^5m\u0015\t1h&\u0001\u0005dCR\fG._:u\u0013\tA8O\u0001\u0007TiJLgnZ\"p]\u000e\fG\u000fC\u0004{\u0013A\u0005\t\u0019A>\u0002\u00115\f\u0007\u0010R3qi\"\u0004\"!\u0010?\n\u0005ut$aA%oi\u0006q\"-^5mI\u001a{'/\\1ui\u0016$7\u000b\u001e:j]\u001e$C-\u001a4bk2$HeM\u000b\u0003\u0003\u0003Q3a_A\u0002W\t\t)\u0001\u0005\u0003\u0002\b\u0005EQBAA\u0005\u0015\u0011\tY!!\u0004\u0002\u0013Ut7\r[3dW\u0016$'bAA\b}\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005M\u0011\u0011\u0002\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00036t_:4\u0016\r\\;f+\t\tI\u0002\u0005\u0003\u0002\u001c\u00055b\u0002BA\u000f\u0003OqA!a\b\u0002$9\u0019Q)!\t\n\u0003UJ1!!\n5\u0003\u0019Q7o\u001c85g&!\u0011\u0011FA\u0016\u0003\u001dQ5o\u001c8B'RS1!!\n5\u0013\u0011\ty#!\r\u0003\r)3\u0016\r\\;f\u0015\u0011\tI#a\u000b\u0002\u0017\u0011,g-Y;miNK'0Z\u000b\u0002w\u0006a1/[7qY\u0016\u001cFO]5oOV\tq-A\u0007dCR\fGn\\4TiJLgnZ\u0001\u000bCNtU\u000f\u001c7bE2,W#A.\u0002\u0015Q|g*\u001e7mC\ndW-A\tfq&\u001cHo\u001d*fGV\u00148/\u001b<fYf$2!VA$\u0011\u001d\tIE\u0005a\u0001\u0003\u0017\n\u0011A\u001a\t\u0006{\u00055\u0003(V\u0005\u0004\u0003\u001fr$!\u0003$v]\u000e$\u0018n\u001c82\u0003Q!(/\u00198tM>\u0014XNU3dkJ\u001c\u0018N^3msR\u0019\u0001(!\u0016\t\u000f\u0005%3\u00031\u0001\u0002XA)Q(!\u00179q%\u0019\u00111\f \u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\fAaY8qsR91,!\u0019\u0002d\u0005\u0015\u0004b\u0002(\u0015!\u0003\u0005\r\u0001\u000f\u0005\b#R\u0001\n\u00111\u00019\u0011\u001d\u0019F\u0003%AA\u0002U\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002l)\u001a\u0001(a\u0001\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA:U\r)\u00161A\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005e\u0004\u0003BA>\u0003\u000bk!!! \u000b\t\u0005}\u0014\u0011Q\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0004\u0006!!.\u0019<b\u0013\ri\u0017QP\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\ti)a%\u0011\u0007u\ny)C\u0002\u0002\u0012z\u00121!\u00118z\u0011!\t)JGA\u0001\u0002\u0004Y\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u001cB1\u0011QTAR\u0003\u001bk!!a(\u000b\u0007\u0005\u0005f(\u0001\u0006d_2dWm\u0019;j_:LA!!*\u0002 \nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r)\u00161\u0016\u0005\n\u0003+c\u0012\u0011!a\u0001\u0003\u001b\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011PAY\u0011!\t)*HA\u0001\u0002\u0004Y\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003m\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003s\na!Z9vC2\u001cHcA+\u0002@\"I\u0011Q\u0013\u0011\u0002\u0002\u0003\u0007\u0011Q\u0012\u0015\u0004\u0001\u0005\r\u0007\u0003BAc\u0003\u0013l!!a2\u000b\u0007\u0005=\u0001'\u0003\u0003\u0002L\u0006\u001d'AB*uC\ndW-A\u0004NCB$\u0016\u0010]3\u0011\u0005e\u00123#\u0002\u0012\u0002T\u0006e\u0007cA\u001d\u0002V&\u0019\u0011q\u001b\u0017\u0003!\u0005\u00137\u000f\u001e:bGR$\u0015\r^1UsB,\u0007\u0003BAn\u0003Cl!!!8\u000b\t\u0005}\u0017\u0011Q\u0001\u0003S>L1\u0001TAo)\t\ty-A\neK\u001a\fW\u000f\u001c;D_:\u001c'/\u001a;f)f\u0004X-A\u0006bG\u000e,\u0007\u000f^:UsB,GcA+\u0002l\"1\u0011Q^\u0013A\u0002a\nQa\u001c;iKJ\fQ!\u00199qYf$RaWAz\u0003kDQAT\u0014A\u0002aBQ!U\u0014A\u0002a\"raWA}\u0003w\fi\u0010C\u0003OQ\u0001\u0007\u0001\bC\u0003RQ\u0001\u0007\u0001\bC\u0003TQ\u0001\u0007Q+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t\r!q\u0002\t\u0006{\t\u0015!\u0011B\u0005\u0004\u0005\u000fq$AB(qi&|g\u000e\u0005\u0004>\u0005\u0017A\u0004(V\u0005\u0004\u0005\u001bq$A\u0002+va2,7\u0007\u0003\u0005\u0003\u0012%\n\t\u00111\u0001\\\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005/\u0001B!a\u001f\u0003\u001a%!!1DA?\u0005\u0019y%M[3di\"\u001a!%a1)\u0007\u0005\n\u0019\r"
)
public class MapType extends DataType implements Product, Serializable {
   private final DataType keyType;
   private final DataType valueType;
   private final boolean valueContainsNull;

   public static Option unapply(final MapType x$0) {
      return MapType$.MODULE$.unapply(x$0);
   }

   public static MapType apply(final DataType keyType, final DataType valueType, final boolean valueContainsNull) {
      return MapType$.MODULE$.apply(keyType, valueType, valueContainsNull);
   }

   public static MapType apply(final DataType keyType, final DataType valueType) {
      return MapType$.MODULE$.apply(keyType, valueType);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public DataType keyType() {
      return this.keyType;
   }

   public DataType valueType() {
      return this.valueType;
   }

   public boolean valueContainsNull() {
      return this.valueContainsNull;
   }

   public void buildFormattedString(final String prefix, final StringConcat stringConcat, final int maxDepth) {
      if (maxDepth > 0) {
         stringConcat.append(prefix + "-- key: " + this.keyType().typeName() + "\n");
         DataType$.MODULE$.buildFormattedString(this.keyType(), prefix + "    |", stringConcat, maxDepth);
         stringConcat.append(prefix + "-- value: " + this.valueType().typeName() + " (valueContainsNull = " + this.valueContainsNull() + ")\n");
         DataType$.MODULE$.buildFormattedString(this.valueType(), prefix + "    |", stringConcat, maxDepth);
      }
   }

   public int buildFormattedString$default$3() {
      return Integer.MAX_VALUE;
   }

   public JValue jsonValue() {
      return .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), this.typeName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("keyType"), this.keyType().jsonValue()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("valueType"), this.valueType().jsonValue()))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("valueContainsNull"), BoxesRunTime.boxToBoolean(this.valueContainsNull())), (x) -> $anonfun$jsonValue$3(BoxesRunTime.unboxToBoolean(x))));
   }

   public int defaultSize() {
      return 1 * (this.keyType().defaultSize() + this.valueType().defaultSize());
   }

   public String simpleString() {
      String var10000 = this.keyType().simpleString();
      return "map<" + var10000 + "," + this.valueType().simpleString() + ">";
   }

   public String catalogString() {
      String var10000 = this.keyType().catalogString();
      return "map<" + var10000 + "," + this.valueType().catalogString() + ">";
   }

   public String sql() {
      String var10000 = this.keyType().sql();
      return "MAP<" + var10000 + ", " + this.valueType().sql() + ">";
   }

   public MapType asNullable() {
      return new MapType(this.keyType().asNullable(), this.valueType().asNullable(), true);
   }

   public MapType toNullable() {
      return this.asNullable();
   }

   public boolean existsRecursively(final Function1 f) {
      return BoxesRunTime.unboxToBoolean(f.apply(this)) || this.keyType().existsRecursively(f) || this.valueType().existsRecursively(f);
   }

   public DataType transformRecursively(final PartialFunction f) {
      return (DataType)(f.isDefinedAt(this) ? (DataType)f.apply(this) : new MapType(this.keyType().transformRecursively(f), this.valueType().transformRecursively(f), this.valueContainsNull()));
   }

   public MapType copy(final DataType keyType, final DataType valueType, final boolean valueContainsNull) {
      return new MapType(keyType, valueType, valueContainsNull);
   }

   public DataType copy$default$1() {
      return this.keyType();
   }

   public DataType copy$default$2() {
      return this.valueType();
   }

   public boolean copy$default$3() {
      return this.valueContainsNull();
   }

   public String productPrefix() {
      return "MapType";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.keyType();
         }
         case 1 -> {
            return this.valueType();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.valueContainsNull());
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
      return x$1 instanceof MapType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "keyType";
         }
         case 1 -> {
            return "valueType";
         }
         case 2 -> {
            return "valueContainsNull";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.keyType()));
      var1 = Statics.mix(var1, Statics.anyHash(this.valueType()));
      var1 = Statics.mix(var1, this.valueContainsNull() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof MapType) {
               MapType var4 = (MapType)x$1;
               if (this.valueContainsNull() == var4.valueContainsNull()) {
                  label52: {
                     DataType var10000 = this.keyType();
                     DataType var5 = var4.keyType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     var10000 = this.valueType();
                     DataType var6 = var4.valueType();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   // $FF: synthetic method
   public static final JValue $anonfun$jsonValue$3(final boolean x) {
      return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
   }

   public MapType(final DataType keyType, final DataType valueType, final boolean valueContainsNull) {
      this.keyType = keyType;
      this.valueType = valueType;
      this.valueContainsNull = valueContainsNull;
      Product.$init$(this);
   }

   public MapType() {
      this((DataType)null, (DataType)null, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
