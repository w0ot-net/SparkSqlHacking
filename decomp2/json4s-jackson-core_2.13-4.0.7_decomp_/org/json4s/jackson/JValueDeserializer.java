package org.json4s.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.json4s.JArray;
import org.json4s.JDecimal;
import org.json4s.JDouble;
import org.json4s.JInt;
import org.json4s.JLong;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.JNull.;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y3Aa\u0002\u0005\u0001\u001f!A1\u0005\u0001B\u0001B\u0003%A\u0005C\u0003?\u0001\u0011\u0005q\bC\u0003H\u0001\u0011\u0005\u0001\nC\u0003W\u0001\u0011\u0005s\u000bC\u0003\\\u0001\u0011\u0005C\fC\u0003^\u0001\u0011\u0005CL\u0001\nK-\u0006dW/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0005\u000b\u0003\u001dQ\u0017mY6t_:T!a\u0003\u0007\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005i\u0011aA8sO\u000e\u00011C\u0001\u0001\u0011!\r\t\u0012dG\u0007\u0002%)\u00111\u0003F\u0001\tI\u0006$\u0018MY5oI*\u0011\u0011\"\u0006\u0006\u0003-]\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003a\t1aY8n\u0013\tQ\"C\u0001\tKg>tG)Z:fe&\fG.\u001b>feB\u0011A$I\u0007\u0002;)\u0011adH\u0001\u0005Y\u0006twMC\u0001!\u0003\u0011Q\u0017M^1\n\u0005\tj\"AB(cU\u0016\u001cG/A\u0003lY\u0006\u001c8\u000f\r\u0002&iA\u0019ae\f\u001a\u000f\u0005\u001dj\u0003C\u0001\u0015,\u001b\u0005I#B\u0001\u0016\u000f\u0003\u0019a$o\\8u})\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\u000b\rc\u0017m]:\u000b\u00059Z\u0003CA\u001a5\u0019\u0001!\u0011\"N\u0001\u0002\u0002\u0003\u0005)\u0011\u0001\u001c\u0003\u0007}#\u0013'\u0005\u00028wA\u0011\u0001(O\u0007\u0002W%\u0011!h\u000b\u0002\b\u001d>$\b.\u001b8h!\tAD(\u0003\u0002>W\t\u0019\u0011I\\=\u0002\rqJg.\u001b;?)\t\u0001%\t\u0005\u0002B\u00015\t\u0001\u0002C\u0003$\u0005\u0001\u00071\t\r\u0002E\rB\u0019aeL#\u0011\u0005M2E!C\u001bC\u0003\u0003\u0005\tQ!\u00017\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0007mI\u0015\u000bC\u0003K\u0007\u0001\u00071*\u0001\u0002kaB\u0011AjT\u0007\u0002\u001b*\u0011a\nF\u0001\u0005G>\u0014X-\u0003\u0002Q\u001b\nQ!j]8o!\u0006\u00148/\u001a:\t\u000bI\u001b\u0001\u0019A*\u0002\t\r$\b\u0010\u001e\t\u0003#QK!!\u0016\n\u0003-\u0011+7/\u001a:jC2L'0\u0019;j_:\u001cuN\u001c;fqR\f!\"[:DC\u000eD\u0017M\u00197f)\u0005A\u0006C\u0001\u001dZ\u0013\tQ6FA\u0004C_>dW-\u00198\u0002\u0019\u001d,GOT;mYZ\u000bG.^3\u0015\u0003m\tQbZ3u\u000b6\u0004H/\u001f,bYV,\u0007"
)
public class JValueDeserializer extends JsonDeserializer {
   private final Class klass;

   public Object deserialize(final JsonParser jp, final DeserializationContext ctxt) {
      Object value = this._deserialize$1(jp, ctxt);
      if (!this.klass.isAssignableFrom(value.getClass())) {
         ctxt.handleUnexpectedToken(this.klass, jp);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return value;
   }

   public boolean isCachable() {
      return true;
   }

   public Object getNullValue() {
      return .MODULE$;
   }

   public Object getEmptyValue() {
      return org.json4s.JNothing..MODULE$;
   }

   private final Object _deserialize$1(final JsonParser jp, final DeserializationContext ctxt) {
      while(true) {
         if (jp.getCurrentToken() == null) {
            jp.nextToken();
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         int var4 = jp.getCurrentToken().id();
         Object var9;
         switch (var4) {
            case 1:
               jp.nextToken();
               ctxt = ctxt;
               jp = jp;
               continue;
            case 2:
            case 5:
               ListBuffer fields = new ListBuffer();

               while(jp.getCurrentToken().id() != 2) {
                  String name = jp.getCurrentName();
                  jp.nextToken();
                  fields.$plus$eq(org.json4s.JsonAST..MODULE$.JField().apply(name, (JValue)this._deserialize$1(jp, ctxt)));
                  jp.nextToken();
               }

               var9 = new JObject(fields.toList());
               break;
            case 3:
               ListBuffer values = new ListBuffer();
               jp.nextToken();

               while(true) {
                  JsonToken var10 = jp.getCurrentToken();
                  JsonToken var6 = JsonToken.END_ARRAY;
                  if (var10 == null) {
                     if (var6 == null) {
                        break;
                     }
                  } else if (var10.equals(var6)) {
                     break;
                  }

                  values.$plus$eq((JValue)this._deserialize$1(jp, ctxt));
                  jp.nextToken();
               }

               var9 = new JArray(values.toList());
               break;
            case 4:
            default:
               var9 = ctxt.handleUnexpectedToken(JValue.class, jp);
               break;
            case 6:
               var9 = new JString(jp.getText());
               break;
            case 7:
               var9 = ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS) ? new JInt(scala.package..MODULE$.BigInt().apply(jp.getText())) : new JLong(jp.getLongValue());
               break;
            case 8:
               var9 = ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ? new JDecimal(scala.package..MODULE$.BigDecimal().apply(jp.getDecimalValue())) : new JDouble(jp.getDoubleValue());
               break;
            case 9:
               var9 = org.json4s.JBool..MODULE$.True();
               break;
            case 10:
               var9 = org.json4s.JBool..MODULE$.False();
               break;
            case 11:
               var9 = .MODULE$;
         }

         return var9;
      }
   }

   public JValueDeserializer(final Class klass) {
      this.klass = klass;
   }
}
