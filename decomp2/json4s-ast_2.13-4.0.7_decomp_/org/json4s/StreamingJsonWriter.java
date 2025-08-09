package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005g!B\u0012%\u0003SI\u0003\"B#\u0001\t\u00031\u0005B\u0002%\u0001A\u001bE\u0011\n\u0003\u0004N\u0001\u00016\t\"\u0013\u0005\u0007\u001d\u0002\u0001k\u0011C(\t\rM\u0003\u0001U\"\u0005U\u0011\u001d)\u0006A1Q\u0007\u0012=CQA\u0016\u0001\u0005\u0002]CQ\u0001\u0017\u0001\u0005\u0002]CQ!\u0017\u0001\u0007\u0002iCQ\u0001\u001b\u0001\u0007\u0002%DQa\u001b\u0001\u0005\u0002]CQ\u0001\u001c\u0001\u0005\u00025DQ\u0001\u001d\u0001\u0005\u0002EDQ\u0001\u001e\u0001\u0005\u0002UDQA\u001f\u0001\u0005\u0002mDQ! \u0001\u0005\u0002yDq!a\u0002\u0001\t\u0003\tI\u0001C\u0004\u0002 \u0001!\t!!\t\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002(!1\u0011\u0011\u0007\u0001\u0005\u0002]Cq!a\r\u0001\t\u0003\t)\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005-\u0003\u0001\"\u0001\u0002N!9\u0011q\u000b\u0001\u0005\u0002\u0005e\u0003bBA.\u0001\u0011\u0005\u0011Q\f\u0005\b\u0003S\u0002A\u0011CA6\u0011%\t9\bAI\u0001\n#\tIh\u0002\u0005\u0002\"\u0012B\t\u0001JAR\r\u001d\u0019C\u0005#\u0001%\u0003KCa!R\u000f\u0005\u0002\u0005\u001d\u0006\u0002CAU;\u0001\u0006I!a+\t\u0011\u0005UV\u0004)A\u0005\u0003WC\u0001\"a.\u001e\t\u0003!\u0013\u0011\u0018\u0005\t\u0003okB\u0011\u0001\u0013\u0002>\n\u00192\u000b\u001e:fC6Lgn\u001a&t_:<&/\u001b;fe*\u0011QEJ\u0001\u0007UN|g\u000eN:\u000b\u0003\u001d\n1a\u001c:h\u0007\u0001)\"AK\u001c\u0014\u0007\u0001Y\u0013\u0007\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VM\u001a\t\u0004eM*T\"\u0001\u0013\n\u0005Q\"#A\u0003&t_:<&/\u001b;feB\u0011ag\u000e\u0007\u0001\t\u0015A\u0004A1\u0001:\u0005\u0005!\u0016C\u0001\u001e>!\ta3(\u0003\u0002=[\t9aj\u001c;iS:<\u0007C\u0001 D\u001b\u0005y$B\u0001!B\u0003\tIwNC\u0001C\u0003\u0011Q\u0017M^1\n\u0005\u0011{$AB,sSR,'/\u0001\u0004=S:LGO\u0010\u000b\u0002\u000fB\u0019!\u0007A\u001b\u0002\u000b1,g/\u001a7\u0016\u0003)\u0003\"\u0001L&\n\u00051k#aA%oi\u000611\u000f]1dKN\fa\u0001\u001d:fiRLX#\u0001)\u0011\u00051\n\u0016B\u0001*.\u0005\u001d\u0011un\u001c7fC:\fQA\\8eKN,\u0012!N\u0001\u0014C2<\u0018-_:Fg\u000e\f\u0007/Z+oS\u000e|G-Z\u0001\u000bgR\f'\u000f^!se\u0006LH#A\u0019\u0002\u0017M$\u0018M\u001d;PE*,7\r^\u0001\bC\u0012$gj\u001c3f)\t\t4\fC\u0003]\u0013\u0001\u0007Q,\u0001\u0003o_\u0012,\u0007C\u00010f\u001d\ty6\r\u0005\u0002a[5\t\u0011M\u0003\u0002cQ\u00051AH]8pizJ!\u0001Z\u0017\u0002\rA\u0013X\rZ3g\u0013\t1wM\u0001\u0004TiJLgn\u001a\u0006\u0003I6\nq\"\u00193e\u0003:$\u0017+^8uK:{G-\u001a\u000b\u0003c)DQ\u0001\u0018\u0006A\u0002u\u000b\u0011\"\u001a8e\u001f\nTWm\u0019;\u0002\u0015M$\u0018M\u001d;GS\u0016dG\r\u0006\u00022]\")q\u000e\u0004a\u0001;\u0006!a.Y7f\u0003\u0019\u0019HO]5oOR\u0011\u0011G\u001d\u0005\u0006g6\u0001\r!X\u0001\u0006m\u0006dW/Z\u0001\u0005Ef$X\r\u0006\u00022m\")1O\u0004a\u0001oB\u0011A\u0006_\u0005\u0003s6\u0012AAQ=uK\u0006\u0019\u0011N\u001c;\u0015\u0005Eb\b\"B:\u0010\u0001\u0004Q\u0015\u0001\u00027p]\u001e$\"!M@\t\rM\u0004\u0002\u0019AA\u0001!\ra\u00131A\u0005\u0004\u0003\u000bi#\u0001\u0002'p]\u001e\faAY5h\u0013:$HcA\u0019\u0002\f!11/\u0005a\u0001\u0003\u001b\u0001B!a\u0004\u0002\u001a9!\u0011\u0011CA\u000b\u001d\r\u0001\u00171C\u0005\u0002]%\u0019\u0011qC\u0017\u0002\u000fA\f7m[1hK&!\u00111DA\u000f\u0005\u0019\u0011\u0015nZ%oi*\u0019\u0011qC\u0017\u0002\u000f\t|w\u000e\\3b]R\u0019\u0011'a\t\t\u000bM\u0014\u0002\u0019\u0001)\u0002\u000bMDwN\u001d;\u0015\u0007E\nI\u0003\u0003\u0004t'\u0001\u0007\u00111\u0006\t\u0004Y\u00055\u0012bAA\u0018[\t)1\u000b[8si\u0006AQM\u001c3BeJ\f\u00170A\u0003gY>\fG\u000fF\u00022\u0003oAaa]\u000bA\u0002\u0005e\u0002c\u0001\u0017\u0002<%\u0019\u0011QH\u0017\u0003\u000b\u0019cw.\u0019;\u0002\r\u0011|WO\u00197f)\r\t\u00141\t\u0005\u0007gZ\u0001\r!!\u0012\u0011\u00071\n9%C\u0002\u0002J5\u0012a\u0001R8vE2,\u0017A\u00032jO\u0012+7-[7bYR\u0019\u0011'a\u0014\t\rM<\u0002\u0019AA)!\u0011\ty!a\u0015\n\t\u0005U\u0013Q\u0004\u0002\u000b\u0005&<G)Z2j[\u0006d\u0017\u0001\u0004:fgVdGo\u0015;sS:<W#A/\u0002\u0013\u0005$GM\u0013,bYV,GcA\u0019\u0002`!9\u0011\u0011M\rA\u0002\u0005\r\u0014A\u00016w!\r\u0011\u0014QM\u0005\u0004\u0003O\"#A\u0002&WC2,X-A\u0006xe&$X\r\u0015:fiRLH\u0003BA7\u0003g\u00022\u0001LA8\u0013\r\t\t(\f\u0002\u0005+:LG\u000f\u0003\u0005\u0002vi\u0001\n\u00111\u0001K\u0003\u001dyW\u000f\u001e3f]R\fQc\u001e:ji\u0016\u0004&/\u001a;us\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002|)\u001a!*! ,\u0005\u0005}\u0004\u0003BAA\u0003\u0017k!!a!\u000b\t\u0005\u0015\u0015qQ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!#.\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u001b\u000b\u0019IA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016L\u0013\u0002AAI\u0003+\u000bI*!(\n\u0007\u0005MEE\u0001\rBeJ\f\u0017p\u0015;sK\u0006l\u0017N\\4Kg>twK]5uKJL1!a&%\u0005a1\u0015.\u001a7e'R\u0014X-Y7j]\u001eT5o\u001c8Xe&$XM]\u0005\u0004\u00037##!G(cU\u0016\u001cGo\u0015;sK\u0006l\u0017N\\4Kg>twK]5uKJL1!a(%\u0005]\u0011vn\u001c;TiJ,\u0017-\\5oO*\u001bxN\\,sSR,'/A\nTiJ,\u0017-\\5oO*\u001bxN\\,sSR,'\u000f\u0005\u00023;M\u0011Qd\u000b\u000b\u0003\u0003G\u000ba\u0002]8t\u0013:4\u0017N\\5usZ\u000bG\u000e\u0005\u0003\u0002.\u0006MVBAAX\u0015\r\t\t,Q\u0001\u0005Y\u0006tw-C\u0002g\u0003_\u000baB\\3h\u0013:4\u0017N\\5uKZ\u000bG.\u0001\biC:$G.Z%oM&t\u0017\u000e^=\u0015\u0007u\u000bY\f\u0003\u0004tC\u0001\u0007\u0011\u0011\b\u000b\u0004;\u0006}\u0006BB:#\u0001\u0004\t)\u0005"
)
public abstract class StreamingJsonWriter implements JsonWriter {
   public abstract int level();

   public abstract int spaces();

   public abstract boolean pretty();

   public abstract java.io.Writer nodes();

   public abstract boolean alwaysEscapeUnicode();

   public JsonWriter startArray() {
      return new ArrayStreamingJsonWriter(this.nodes(), this.level() + 1, this, this.pretty(), this.spaces(), this.alwaysEscapeUnicode());
   }

   public JsonWriter startObject() {
      return new ObjectStreamingJsonWriter(this.nodes(), this.level() + 1, this, this.pretty(), this.spaces(), this.alwaysEscapeUnicode());
   }

   public abstract JsonWriter addNode(final String node);

   public abstract JsonWriter addAndQuoteNode(final String node);

   public JsonWriter endObject() {
      throw .MODULE$.error("You have to start an object to be able to end it (endObject called before startObject)");
   }

   public JsonWriter startField(final String name) {
      throw .MODULE$.error("You have to start an object before starting a field.");
   }

   public JsonWriter string(final String value) {
      return this.addAndQuoteNode(value);
   }

   public JsonWriter byte(final byte value) {
      return this.addNode(Byte.toString(value));
   }

   public JsonWriter int(final int value) {
      return this.addNode(Integer.toString(value));
   }

   public JsonWriter long(final long value) {
      return this.addNode(Long.toString(value));
   }

   public JsonWriter bigInt(final BigInt value) {
      return this.addNode(value.toString());
   }

   public JsonWriter boolean(final boolean value) {
      return this.addNode(value ? "true" : "false");
   }

   public JsonWriter short(final short value) {
      return this.addNode(Short.toString(value));
   }

   public JsonWriter endArray() {
      throw .MODULE$.error("You have to start an object to be able to end it (endArray called before startArray)");
   }

   public JsonWriter float(final float value) {
      return this.addNode(StreamingJsonWriter$.MODULE$.handleInfinity(value));
   }

   public JsonWriter double(final double value) {
      return this.addNode(StreamingJsonWriter$.MODULE$.handleInfinity(value));
   }

   public JsonWriter bigDecimal(final BigDecimal value) {
      return this.addNode(value.toString());
   }

   public String resultString() {
      return this.result().toString();
   }

   public JsonWriter addJValue(final JValue jv) {
      Object var2;
      if (JNull$.MODULE$.equals(jv)) {
         var2 = this.addNode("null");
      } else if (jv instanceof JString) {
         JString var4 = (JString)jv;
         String str = var4.s();
         var2 = this.string(str);
      } else if (jv instanceof JInt) {
         JInt var6 = (JInt)jv;
         BigInt i = var6.num();
         var2 = this.bigInt(i);
      } else if (jv instanceof JLong) {
         JLong var8 = (JLong)jv;
         long i = var8.num();
         var2 = this.long(i);
      } else if (jv instanceof JDouble) {
         JDouble var11 = (JDouble)jv;
         double d = var11.num();
         var2 = this.double(d);
      } else if (jv instanceof JDecimal) {
         JDecimal var14 = (JDecimal)jv;
         BigDecimal d = var14.num();
         var2 = this.bigDecimal(d);
      } else if (jv instanceof JBool) {
         JBool var16 = (JBool)jv;
         boolean b = var16.value();
         var2 = this.boolean(b);
      } else if (jv instanceof JArray) {
         JArray var18 = (JArray)jv;
         List arr = var18.arr();
         JsonWriter ab = this.startArray();
         arr.foreach((jvx) -> ab.addJValue(jvx));
         var2 = ab.endArray();
      } else if (jv instanceof JSet) {
         JSet var21 = (JSet)jv;
         Set s = var21.set();
         JsonWriter ab = this.startArray();
         s.foreach((jvx) -> ab.addJValue(jvx));
         var2 = ab.endArray();
      } else if (jv instanceof JObject) {
         JObject var24 = (JObject)jv;
         List flds = var24.obj();
         JsonWriter obj = this.startObject();
         flds.foreach((x0$1) -> {
            Object var3;
            label33: {
               if (x0$1 != null) {
                  JValue v = (JValue)x0$1._2();
                  JNothing$ var6 = JNothing$.MODULE$;
                  if (v == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (v.equals(var6)) {
                     break label33;
                  }
               }

               if (x0$1 == null) {
                  throw new MatchError(x0$1);
               }

               String k = (String)x0$1._1();
               JValue v = (JValue)x0$1._2();
               var3 = obj.startField(k).addJValue(v);
               return (JsonWriter)var3;
            }

            var3 = this;
            return (JsonWriter)var3;
         });
         var2 = obj.endObject();
      } else {
         if (!JNothing$.MODULE$.equals(jv)) {
            throw new MatchError(jv);
         }

         var2 = this;
      }

      return (JsonWriter)var2;
   }

   public void writePretty(final int outdent) {
      if (this.pretty()) {
         this.nodes().write(10);
         this.nodes().write(scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), this.level() * this.spaces() - outdent));
      }

   }

   public int writePretty$default$1() {
      return 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
