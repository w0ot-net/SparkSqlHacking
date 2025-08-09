package org.apache.spark.sql.catalyst.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataType$;
import org.json4s.JValue;
import org.json4s.jackson.JValueDeserializer;
import org.json4s.jackson.JValueSerializer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M<Q!\u0004\b\t\u0002m1Q!\b\b\t\u0002yAQ!J\u0001\u0005\u0002\u00192AaJ\u0001\u0001Q!)Qe\u0001C\u0001w!9ah\u0001b\u0001\n\u0013y\u0004BB$\u0004A\u0003%\u0001\tC\u0003I\u0007\u0011\u0005\u0013J\u0002\u0003]\u0003\u0001i\u0006\"B\u0013\t\t\u0003\t\u0007b\u0002 \t\u0005\u0004%Ia\u0019\u0005\u0007\u000f\"\u0001\u000b\u0011\u00023\t\u000b\u001dDA\u0011\t5\u0002#\u0011\u000bG/\u0019+za\u0016T5o\u001c8Vi&d7O\u0003\u0002\u0010!\u0005!Q\u000f^5m\u0015\t\t\"#\u0001\u0005dCR\fG._:u\u0015\t\u0019B#A\u0002tc2T!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\u0002\u0001!\ta\u0012!D\u0001\u000f\u0005E!\u0015\r^1UsB,'j]8o+RLGn]\n\u0003\u0003}\u0001\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u001c\u0005Y!\u0015\r^1UsB,'j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u00148CA\u0002*!\rQ3'N\u0007\u0002W)\u0011A&L\u0001\tI\u0006$\u0018MY5oI*\u0011afL\u0001\bU\u0006\u001c7n]8o\u0015\t\u0001\u0014'A\u0005gCN$XM\u001d=nY*\t!'A\u0002d_6L!\u0001N\u0016\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>feB\u0011a'O\u0007\u0002o)\u0011\u0001HE\u0001\u0006if\u0004Xm]\u0005\u0003u]\u0012\u0001\u0002R1uCRK\b/\u001a\u000b\u0002yA\u0011QhA\u0007\u0002\u0003\u0005AA-\u001a7fO\u0006$X-F\u0001A!\t\tU)D\u0001C\u0015\tq3I\u0003\u0002E1\u00051!n]8oiML!A\u0012\"\u0003!)3\u0016\r\\;f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018!\u00033fY\u0016<\u0017\r^3!\u0003%\u0019XM]5bY&TX\r\u0006\u0003K\u001b>;\u0006C\u0001\u0011L\u0013\ta\u0015E\u0001\u0003V]&$\b\"\u0002(\b\u0001\u0004)\u0014!\u0002<bYV,\u0007\"\u0002)\b\u0001\u0004\t\u0016aA4f]B\u0011!+V\u0007\u0002'*\u0011A+L\u0001\u0005G>\u0014X-\u0003\u0002W'\ni!j]8o\u000f\u0016tWM]1u_JDQ\u0001W\u0004A\u0002e\u000b\u0001\u0002\u001d:pm&$WM\u001d\t\u0003UiK!aW\u0016\u0003%M+'/[1mSj,'\u000f\u0015:pm&$WM\u001d\u0002\u0019\t\u0006$\u0018\rV=qK*\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u00148C\u0001\u0005_!\rQs,N\u0005\u0003A.\u0012\u0001CS:p]\u0012+7/\u001a:jC2L'0\u001a:\u0015\u0003\t\u0004\"!\u0010\u0005\u0016\u0003\u0011\u0004\"!Q3\n\u0005\u0019\u0014%A\u0005&WC2,X\rR3tKJL\u0017\r\\5{KJ\f1\u0002Z3tKJL\u0017\r\\5{KR\u0019Q'\u001b8\t\u000b)d\u0001\u0019A6\u0002\u0015)\u001cxN\u001c)beN,'\u000f\u0005\u0002SY&\u0011Qn\u0015\u0002\u000b\u0015N|g\u000eU1sg\u0016\u0014\b\"B8\r\u0001\u0004\u0001\u0018A\u00063fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:$X\r\u001f;\u0011\u0005)\n\u0018B\u0001:,\u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\b"
)
public final class DataTypeJsonUtils {
   public static class DataTypeJsonSerializer extends JsonSerializer {
      private final JValueSerializer delegate = new JValueSerializer();

      private JValueSerializer delegate() {
         return this.delegate;
      }

      public void serialize(final DataType value, final JsonGenerator gen, final SerializerProvider provider) {
         this.delegate().serialize(value.jsonValue(), gen, provider);
      }
   }

   public static class DataTypeJsonDeserializer extends JsonDeserializer {
      private final JValueDeserializer delegate = new JValueDeserializer(Object.class);

      private JValueDeserializer delegate() {
         return this.delegate;
      }

      public DataType deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) {
         Object json = this.delegate().deserialize(jsonParser, deserializationContext);
         return DataType$.MODULE$.parseDataType((JValue)json, DataType$.MODULE$.parseDataType$default$2(), DataType$.MODULE$.parseDataType$default$3());
      }
   }
}
