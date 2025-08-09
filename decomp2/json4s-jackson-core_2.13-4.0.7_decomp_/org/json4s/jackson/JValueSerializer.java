package org.json4s.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.lang.invoke.SerializedLambda;
import org.json4s.JArray;
import org.json4s.JBool;
import org.json4s.JDecimal;
import org.json4s.JDouble;
import org.json4s.JInt;
import org.json4s.JLong;
import org.json4s.JNothing;
import org.json4s.JNull;
import org.json4s.JObject;
import org.json4s.JSet;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.JsonAST.;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)!\u0006\u0001C\u0001W!)\u0011\t\u0001C!\u0005\n\u0001\"JV1mk\u0016\u001cVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tqA[1dWN|gN\u0003\u0002\t\u0013\u00051!n]8oiMT\u0011AC\u0001\u0004_J<7\u0001A\n\u0003\u00015\u00012A\u0004\f\u0019\u001b\u0005y!B\u0001\t\u0012\u0003!!\u0017\r^1cS:$'B\u0001\u0004\u0013\u0015\t\u0019B#A\u0005gCN$XM\u001d=nY*\tQ#A\u0002d_6L!aF\b\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>feB\u0011\u0011d\t\b\u00035\u0005r!a\u0007\u0011\u000f\u0005qyR\"A\u000f\u000b\u0005yY\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\tA\u0011\"\u0003\u0002#\u000f\u00059!j]8o\u0003N#\u0016B\u0001\u0013&\u0005\u0019Qe+\u00197vK*\u0011!eB\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003!\u0002\"!\u000b\u0001\u000e\u0003\u0015\t\u0011b]3sS\u0006d\u0017N_3\u0015\t1\u0012D\u0007\u0010\t\u0003[Aj\u0011A\f\u0006\u0002_\u0005)1oY1mC&\u0011\u0011G\f\u0002\u0005+:LG\u000fC\u00034\u0005\u0001\u0007\u0001$A\u0003wC2,X\rC\u00036\u0005\u0001\u0007a'\u0001\u0003kg>t\u0007CA\u001c;\u001b\u0005A$BA\u001d\u0012\u0003\u0011\u0019wN]3\n\u0005mB$!\u0004&t_:<UM\\3sCR|'\u000fC\u0003>\u0005\u0001\u0007a(\u0001\u0005qe>4\u0018\u000eZ3s!\tqq(\u0003\u0002A\u001f\t\u00112+\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s\u0003\u001dI7/R7qif$\"a\u0011$\u0011\u00055\"\u0015BA#/\u0005\u001d\u0011un\u001c7fC:DQaM\u0002A\u0002a\u0001"
)
public class JValueSerializer extends JsonSerializer {
   public void serialize(final JValue value, final JsonGenerator json, final SerializerProvider provider) {
      if (value == null) {
         json.writeNull();
      } else if (value instanceof JInt) {
         JInt var6 = (JInt)value;
         BigInt v = var6.num();
         json.writeNumber(v.bigInteger());
         BoxedUnit var37 = BoxedUnit.UNIT;
      } else if (value instanceof JLong) {
         JLong var8 = (JLong)value;
         long v = var8.num();
         json.writeNumber(v);
         BoxedUnit var36 = BoxedUnit.UNIT;
      } else if (value instanceof JDouble) {
         JDouble var11 = (JDouble)value;
         double v = var11.num();
         json.writeNumber(v);
         BoxedUnit var35 = BoxedUnit.UNIT;
      } else if (value instanceof JDecimal) {
         JDecimal var14 = (JDecimal)value;
         BigDecimal v = var14.num();
         json.writeNumber(v.bigDecimal());
         BoxedUnit var34 = BoxedUnit.UNIT;
      } else if (value instanceof JString) {
         JString var16 = (JString)value;
         String v = var16.s();
         json.writeString(v);
         BoxedUnit var33 = BoxedUnit.UNIT;
      } else if (value instanceof JBool) {
         JBool var18 = (JBool)value;
         boolean v = var18.value();
         json.writeBoolean(v);
         BoxedUnit var32 = BoxedUnit.UNIT;
      } else if (value instanceof JArray) {
         JArray var20 = (JArray)value;
         List elements = var20.arr();
         json.writeStartArray();
         elements.foreach((x) -> {
            $anonfun$serialize$1(this, json, provider, x);
            return BoxedUnit.UNIT;
         });
         json.writeEndArray();
         BoxedUnit var31 = BoxedUnit.UNIT;
      } else if (value instanceof JSet) {
         JSet var22 = (JSet)value;
         Set elements = var22.set();
         json.writeStartArray();
         elements.foreach((x) -> {
            $anonfun$serialize$2(this, json, provider, x);
            return BoxedUnit.UNIT;
         });
         json.writeEndArray();
         BoxedUnit var30 = BoxedUnit.UNIT;
      } else if (value instanceof JObject) {
         JObject var24 = (JObject)value;
         List fields = var24.obj();
         json.writeStartObject();
         fields.foreach((x0$1) -> {
            $anonfun$serialize$3(this, json, provider, x0$1);
            return BoxedUnit.UNIT;
         });
         json.writeEndObject();
         BoxedUnit var29 = BoxedUnit.UNIT;
      } else {
         label102: {
            JNull var10000 = .MODULE$.JNull();
            if (var10000 == null) {
               if (value == null) {
                  break label102;
               }
            } else if (var10000.equals(value)) {
               break label102;
            }

            JNothing var38 = .MODULE$.JNothing();
            if (var38 == null) {
               if (value != null) {
                  throw new MatchError(value);
               }
            } else if (!var38.equals(value)) {
               throw new MatchError(value);
            }

            BoxedUnit var4 = BoxedUnit.UNIT;
            return;
         }

         json.writeNull();
         BoxedUnit var28 = BoxedUnit.UNIT;
      }

   }

   public boolean isEmpty(final JValue value) {
      boolean var10000;
      label23: {
         JNothing var2 = .MODULE$.JNothing();
         if (value == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (value.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$serialize$1(final JValueSerializer $this, final JsonGenerator json$1, final SerializerProvider provider$1, final JValue x) {
      $this.serialize(x, json$1, provider$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$serialize$2(final JValueSerializer $this, final JsonGenerator json$1, final SerializerProvider provider$1, final JValue x) {
      $this.serialize(x, json$1, provider$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$serialize$3(final JValueSerializer $this, final JsonGenerator json$1, final SerializerProvider provider$1, final Tuple2 x0$1) {
      label33: {
         if (x0$1 != null) {
            JValue var6 = (JValue)x0$1._2();
            JNothing var10000 = .MODULE$.JNothing();
            if (var10000 == null) {
               if (var6 == null) {
                  break label33;
               }
            } else if (var10000.equals(var6)) {
               break label33;
            }
         }

         if (x0$1 == null) {
            throw new MatchError(x0$1);
         }

         String n = (String)x0$1._1();
         JValue v = (JValue)x0$1._2();
         json$1.writeFieldName(n);
         $this.serialize(v, json$1, provider$1);
         BoxedUnit var4 = BoxedUnit.UNIT;
         return;
      }

      BoxedUnit var10 = BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
