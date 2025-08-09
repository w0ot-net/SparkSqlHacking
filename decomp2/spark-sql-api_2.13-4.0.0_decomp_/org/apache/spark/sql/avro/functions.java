package org.apache.spark.sql.avro;

import java.util.Map;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.Column;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m;Q!\u0003\u0006\t\u0002U1Qa\u0006\u0006\t\u0002aAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\tBQ!I\u0001\u0005\u0002uBQaS\u0001\u0005\u00021CQaS\u0001\u0005\u0002=CQaU\u0001\u0005\u0002QCQaU\u0001\u0005\u0002]\u000b\u0011BZ;oGRLwN\\:\u000b\u0005-a\u0011\u0001B1we>T!!\u0004\b\u0002\u0007M\fHN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h\u0007\u0001\u0001\"AF\u0001\u000e\u0003)\u0011\u0011BZ;oGRLwN\\:\u0014\u0005\u0005I\u0002C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002+\u0005IaM]8n?\u00064(o\u001c\u000b\u0004G\u001dJ\u0003C\u0001\u0013&\u001b\u0005a\u0011B\u0001\u0014\r\u0005\u0019\u0019u\u000e\\;n]\")\u0001f\u0001a\u0001G\u0005!A-\u0019;b\u0011\u0015Q3\u00011\u0001,\u0003AQ7o\u001c8G_Jl\u0017\r^*dQ\u0016l\u0017\r\u0005\u0002-g9\u0011Q&\r\t\u0003]mi\u0011a\f\u0006\u0003aQ\ta\u0001\u0010:p_Rt\u0014B\u0001\u001a\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005IZ\u0002FA\u00028!\tA4(D\u0001:\u0015\tQd\"\u0001\u0006b]:|G/\u0019;j_:L!\u0001P\u001d\u0003\u0019\u0015C\b/\u001a:j[\u0016tG/\u00197\u0015\t\rrt\b\u0011\u0005\u0006Q\u0011\u0001\ra\t\u0005\u0006U\u0011\u0001\ra\u000b\u0005\u0006\u0003\u0012\u0001\rAQ\u0001\b_B$\u0018n\u001c8t!\u0011\u0019\u0005jK\u0016\u000e\u0003\u0011S!!\u0012$\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u000f\u0006!!.\u0019<b\u0013\tIEIA\u0002NCBD#\u0001B\u001c\u0002\u000fQ|w,\u0019<s_R\u00111%\u0014\u0005\u0006Q\u0015\u0001\ra\t\u0015\u0003\u000b]\"2a\t)R\u0011\u0015Ac\u00011\u0001$\u0011\u0015Qc\u00011\u0001,Q\t1q'\u0001\btG\",W.Y0pM~\u000bgO]8\u0015\u0005\r*\u0006\"\u0002\u0016\b\u0001\u0004Y\u0003FA\u00048)\r\u0019\u0003,\u0017\u0005\u0006U!\u0001\ra\u000b\u0005\u0006\u0003\"\u0001\rA\u0011\u0015\u0003\u0011]\u0002"
)
public final class functions {
   @Experimental
   public static Column schema_of_avro(final String jsonFormatSchema, final Map options) {
      return functions$.MODULE$.schema_of_avro(jsonFormatSchema, options);
   }

   @Experimental
   public static Column schema_of_avro(final String jsonFormatSchema) {
      return functions$.MODULE$.schema_of_avro(jsonFormatSchema);
   }

   @Experimental
   public static Column to_avro(final Column data, final String jsonFormatSchema) {
      return functions$.MODULE$.to_avro(data, jsonFormatSchema);
   }

   @Experimental
   public static Column to_avro(final Column data) {
      return functions$.MODULE$.to_avro(data);
   }

   @Experimental
   public static Column from_avro(final Column data, final String jsonFormatSchema, final Map options) {
      return functions$.MODULE$.from_avro(data, jsonFormatSchema, options);
   }

   @Experimental
   public static Column from_avro(final Column data, final String jsonFormatSchema) {
      return functions$.MODULE$.from_avro(data, jsonFormatSchema);
   }
}
