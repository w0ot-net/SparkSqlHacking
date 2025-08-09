package org.apache.spark.sql.util;

import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}qAB\b\u0011\u0011\u0003\u0011\"D\u0002\u0004\u001d!!\u0005!#\b\u0005\u0006I\u0005!\tA\n\u0005\bO\u0005\u0011\r\u0011\"\u0001)\u0011\u0019\t\u0014\u0001)A\u0005S!)!'\u0001C\u0001g!9q+AI\u0001\n\u0003A\u0006\"B2\u0002\t\u0003!\u0007\"\u00024\u0002\t\u00039\u0007b\u0002:\u0002#\u0003%\t\u0001\u0017\u0005\u0006g\u0006!\t\u0001\u001e\u0005\u0006o\u0006!\t\u0001\u001f\u0005\u0006u\u0006!\ta\u001f\u0005\b\u0003#\tA\u0011AA\n\u0011\u001d\t9\"\u0001C\u0005\u00033\t!\"\u0011:s_^,F/\u001b7t\u0015\t\t\"#\u0001\u0003vi&d'BA\n\u0015\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sOB\u00111$A\u0007\u0002!\tQ\u0011I\u001d:poV#\u0018\u000e\\:\u0014\u0005\u0005q\u0002CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005Q\u0012!\u0004:p_R\fE\u000e\\8dCR|'/F\u0001*!\tQs&D\u0001,\u0015\taS&\u0001\u0004nK6|'/\u001f\u0006\u0003]Y\tQ!\u0019:s_^L!\u0001M\u0016\u0003\u001bI{w\u000e^!mY>\u001c\u0017\r^8s\u00039\u0011xn\u001c;BY2|7-\u0019;pe\u0002\n1\u0002^8BeJ|w\u000fV=qKR!AGP#S!\t)D(D\u00017\u0015\t9\u0004(\u0001\u0003q_*|'BA\u001d;\u0003\u0015!\u0018\u0010]3t\u0015\tYT&\u0001\u0004wK\u000e$xN]\u0005\u0003{Y\u0012\u0011\"\u0011:s_^$\u0016\u0010]3\t\u000b}*\u0001\u0019\u0001!\u0002\u0005\u0011$\bCA!D\u001b\u0005\u0011%BA\u001d\u0013\u0013\t!%I\u0001\u0005ECR\fG+\u001f9f\u0011\u00151U\u00011\u0001H\u0003)!\u0018.\\3[_:,\u0017\n\u001a\t\u0003\u0011>s!!S'\u0011\u0005)\u0003S\"A&\u000b\u00051+\u0013A\u0002\u001fs_>$h(\u0003\u0002OA\u00051\u0001K]3eK\u001aL!\u0001U)\u0003\rM#(/\u001b8h\u0015\tq\u0005\u0005C\u0004T\u000bA\u0005\t\u0019\u0001+\u0002\u001b1\f'oZ3WCJ$\u0016\u0010]3t!\tyR+\u0003\u0002WA\t9!i\\8mK\u0006t\u0017!\u0006;p\u0003J\u0014xn\u001e+za\u0016$C-\u001a4bk2$HeM\u000b\u00023*\u0012AKW\u0016\u00027B\u0011A,Y\u0007\u0002;*\u0011alX\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0019\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002c;\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001b\u0019\u0014x.\\!se><H+\u001f9f)\t\u0001U\rC\u0003@\u000f\u0001\u0007A'\u0001\u0007u_\u0006\u0013(o\\<GS\u0016dG\r\u0006\u0004iW6t\u0007/\u001d\t\u0003k%L!A\u001b\u001c\u0003\u000b\u0019KW\r\u001c3\t\u000b1D\u0001\u0019A$\u0002\t9\fW.\u001a\u0005\u0006\u007f!\u0001\r\u0001\u0011\u0005\u0006_\"\u0001\r\u0001V\u0001\t]VdG.\u00192mK\")a\t\u0003a\u0001\u000f\"91\u000b\u0003I\u0001\u0002\u0004!\u0016A\u0006;p\u0003J\u0014xn\u001e$jK2$G\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d%\u001ch+\u0019:jC:$h)[3mIR\u0011A+\u001e\u0005\u0006m*\u0001\r\u0001[\u0001\u0006M&,G\u000eZ\u0001\u000fMJ|W.\u0011:s_^4\u0015.\u001a7e)\t\u0001\u0015\u0010C\u0003w\u0017\u0001\u0007\u0001.A\u0007u_\u0006\u0013(o\\<TG\",W.\u0019\u000b\ty~\fI!a\u0003\u0002\u0010A\u0011Q'`\u0005\u0003}Z\u0012aaU2iK6\f\u0007bBA\u0001\u0019\u0001\u0007\u00111A\u0001\u0007g\u000eDW-\\1\u0011\u0007\u0005\u000b)!C\u0002\u0002\b\t\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u00151E\u00021\u0001H\u0011\u0019\ti\u0001\u0004a\u0001)\u0006YRM\u001d:pe>sG)\u001e9mS\u000e\fG/\u001a3GS\u0016dGMT1nKNDQa\u0015\u0007A\u0002Q\u000bqB\u001a:p[\u0006\u0013(o\\<TG\",W.\u0019\u000b\u0005\u0003\u0007\t)\u0002\u0003\u0004\u0002\u00025\u0001\r\u0001`\u0001\u0016I\u0016$W\u000f\u001d7jG\u0006$XMR5fY\u0012t\u0015-\\3t)\u0015\u0001\u00151DA\u000f\u0011\u0015yd\u00021\u0001A\u0011\u0019\tiA\u0004a\u0001)\u0002"
)
public final class ArrowUtils {
   public static StructType fromArrowSchema(final Schema schema) {
      return ArrowUtils$.MODULE$.fromArrowSchema(schema);
   }

   public static Schema toArrowSchema(final StructType schema, final String timeZoneId, final boolean errorOnDuplicatedFieldNames, final boolean largeVarTypes) {
      return ArrowUtils$.MODULE$.toArrowSchema(schema, timeZoneId, errorOnDuplicatedFieldNames, largeVarTypes);
   }

   public static DataType fromArrowField(final Field field) {
      return ArrowUtils$.MODULE$.fromArrowField(field);
   }

   public static boolean isVariantField(final Field field) {
      return ArrowUtils$.MODULE$.isVariantField(field);
   }

   public static boolean toArrowField$default$5() {
      return ArrowUtils$.MODULE$.toArrowField$default$5();
   }

   public static Field toArrowField(final String name, final DataType dt, final boolean nullable, final String timeZoneId, final boolean largeVarTypes) {
      return ArrowUtils$.MODULE$.toArrowField(name, dt, nullable, timeZoneId, largeVarTypes);
   }

   public static DataType fromArrowType(final ArrowType dt) {
      return ArrowUtils$.MODULE$.fromArrowType(dt);
   }

   public static boolean toArrowType$default$3() {
      return ArrowUtils$.MODULE$.toArrowType$default$3();
   }

   public static ArrowType toArrowType(final DataType dt, final String timeZoneId, final boolean largeVarTypes) {
      return ArrowUtils$.MODULE$.toArrowType(dt, timeZoneId, largeVarTypes);
   }

   public static RootAllocator rootAllocator() {
      return ArrowUtils$.MODULE$.rootAllocator();
   }
}
