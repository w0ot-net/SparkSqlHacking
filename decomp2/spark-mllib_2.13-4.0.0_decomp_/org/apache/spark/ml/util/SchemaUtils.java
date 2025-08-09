package org.apache.spark.ml.util;

import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=tA\u0002\f\u0018\u0011\u0003Y\u0012E\u0002\u0004$/!\u00051\u0004\n\u0005\u0006W\u0005!\t!\f\u0005\u0006]\u0005!\ta\f\u0005\b#\u0006\t\n\u0011\"\u0001S\u0011\u0015i\u0016\u0001\"\u0001_\u0011\u001di\u0017!%A\u0005\u0002ICQA\\\u0001\u0005\u0002=Dqa]\u0001\u0012\u0002\u0013\u0005!\u000bC\u0003u\u0003\u0011\u0005Q\u000fC\u0004\u007f\u0003E\u0005I\u0011A@\t\rQ\fA\u0011AA\u0002\u0011\u001d\t\t\"\u0001C\u0001\u0003'Aq!a\t\u0002\t\u0003\t)\u0003C\u0004\u00020\u0005!\t!!\r\t\u000f\u0005]\u0012\u0001\"\u0001\u0002:!A\u0011QI\u0001\u0012\u0002\u0013\u0005q\u0010C\u0004\u0002H\u0005!\t!!\u0013\t\u000f\u0005=\u0013\u0001\"\u0001\u0002R!9\u0011qK\u0001\u0005\u0002\u0005e\u0003bBA0\u0003\u0011\u0005\u0011\u0011\r\u0005\b\u0003O\nA\u0011AA5\u0003-\u00196\r[3nCV#\u0018\u000e\\:\u000b\u0005aI\u0012\u0001B;uS2T!AG\u000e\u0002\u00055d'B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0011\u0005\t\nQ\"A\f\u0003\u0017M\u001b\u0007.Z7b+RLGn]\n\u0003\u0003\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\u0005\nqb\u00195fG.\u001cu\u000e\\;n]RK\b/\u001a\u000b\u0006aMj$j\u0014\t\u0003MEJ!AM\u0014\u0003\tUs\u0017\u000e\u001e\u0005\u0006i\r\u0001\r!N\u0001\u0007g\u000eDW-\\1\u0011\u0005YZT\"A\u001c\u000b\u0005aJ\u0014!\u0002;za\u0016\u001c(B\u0001\u001e\u001c\u0003\r\u0019\u0018\u000f\\\u0005\u0003y]\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015q4\u00011\u0001@\u0003\u001d\u0019w\u000e\u001c(b[\u0016\u0004\"\u0001Q$\u000f\u0005\u0005+\u0005C\u0001\"(\u001b\u0005\u0019%B\u0001#-\u0003\u0019a$o\\8u}%\u0011aiJ\u0001\u0007!J,G-\u001a4\n\u0005!K%AB*ue&twM\u0003\u0002GO!)1j\u0001a\u0001\u0019\u0006AA-\u0019;b)f\u0004X\r\u0005\u00027\u001b&\u0011aj\u000e\u0002\t\t\u0006$\u0018\rV=qK\"9\u0001k\u0001I\u0001\u0002\u0004y\u0014aA7tO\u0006I2\r[3dW\u000e{G.^7o)f\u0004X\r\n3fM\u0006,H\u000e\u001e\u00135+\u0005\u0019&FA UW\u0005)\u0006C\u0001,\\\u001b\u00059&B\u0001-Z\u0003%)hn\u00195fG.,GM\u0003\u0002[O\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005q;&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u00012\r[3dW\u000e{G.^7o)f\u0004Xm\u001d\u000b\u0006a}\u0003\u0017\r\u001c\u0005\u0006i\u0015\u0001\r!\u000e\u0005\u0006}\u0015\u0001\ra\u0010\u0005\u0006E\u0016\u0001\raY\u0001\nI\u0006$\u0018\rV=qKN\u00042\u0001Z5M\u001d\t)wM\u0004\u0002CM&\t\u0001&\u0003\u0002iO\u00059\u0001/Y2lC\u001e,\u0017B\u00016l\u0005\r\u0019V-\u001d\u0006\u0003Q\u001eBq\u0001U\u0003\u0011\u0002\u0003\u0007q(\u0001\u000edQ\u0016\u001c7nQ8mk6tG+\u001f9fg\u0012\"WMZ1vYR$C'\u0001\tdQ\u0016\u001c7NT;nKJL7\rV=qKR!\u0001\u0007]9s\u0011\u0015!t\u00011\u00016\u0011\u0015qt\u00011\u0001@\u0011\u001d\u0001v\u0001%AA\u0002}\n!d\u00195fG.tU/\\3sS\u000e$\u0016\u0010]3%I\u00164\u0017-\u001e7uIM\nA\"\u00199qK:$7i\u001c7v[:$R!\u000e<xqfDQ\u0001N\u0005A\u0002UBQAP\u0005A\u0002}BQaS\u0005A\u00021CqA_\u0005\u0011\u0002\u0003\u000710\u0001\u0005ok2d\u0017M\u00197f!\t1C0\u0003\u0002~O\t9!i\\8mK\u0006t\u0017AF1qa\u0016tGmQ8mk6tG\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005\u0005!FA>U)\u0015)\u0014QAA\u0004\u0011\u0015!4\u00021\u00016\u0011\u001d\tIa\u0003a\u0001\u0003\u0017\t1aY8m!\r1\u0014QB\u0005\u0004\u0003\u001f9$aC*ueV\u001cGOR5fY\u0012\f\u0001$\u001e9eCR,\u0017\t\u001e;sS\n,H/Z$s_V\u00048+\u001b>f)\u001d)\u0014QCA\f\u00033AQ\u0001\u000e\u0007A\u0002UBQA\u0010\u0007A\u0002}Bq!a\u0007\r\u0001\u0004\ti\"\u0001\u0003tSj,\u0007c\u0001\u0014\u0002 %\u0019\u0011\u0011E\u0014\u0003\u0007%sG/A\bva\u0012\fG/\u001a(v[Z\u000bG.^3t)\u001d)\u0014qEA\u0015\u0003WAQ\u0001N\u0007A\u0002UBQAP\u0007A\u0002}Bq!!\f\u000e\u0001\u0004\ti\"A\u0005ok64\u0016\r\\;fg\u0006iQ\u000f\u001d3bi\u0016tU/\\3sS\u000e$R!NA\u001a\u0003kAQ\u0001\u000e\bA\u0002UBQA\u0010\bA\u0002}\n1\"\u001e9eCR,g)[3mIR9Q'a\u000f\u0002>\u0005\u0005\u0003\"\u0002\u001b\u0010\u0001\u0004)\u0004bBA \u001f\u0001\u0007\u00111B\u0001\u0006M&,G\u000e\u001a\u0005\t\u0003\u0007z\u0001\u0013!a\u0001w\u0006\trN^3soJLG/Z'fi\u0006$\u0017\r^1\u0002+U\u0004H-\u0019;f\r&,G\u000e\u001a\u0013eK\u001a\fW\u000f\u001c;%g\u0005qb/\u00197jI\u0006$XMV3di>\u00148i\\7qCRL'\r\\3D_2,XN\u001c\u000b\u0006a\u0005-\u0013Q\n\u0005\u0006iE\u0001\r!\u000e\u0005\u0006}E\u0001\raP\u0001\bi>\u001c\u0016\u000bT%e)\ry\u00141\u000b\u0005\u0007\u0003+\u0012\u0002\u0019A \u0002\u000bA\f'\u000f^:\u0002\u001d\u001d,GoU2iK6\fg)[3mIR1\u00111BA.\u0003;BQ\u0001N\nA\u0002UBQAP\nA\u0002}\n!cZ3u'\u000eDW-\\1GS\u0016dG\rV=qKR)A*a\u0019\u0002f!)A\u0007\u0006a\u0001k!)a\b\u0006a\u0001\u007f\u0005)2\r[3dWN\u001b\u0007.Z7b\r&,G\u000eZ#ySN$H#B>\u0002l\u00055\u0004\"\u0002\u001b\u0016\u0001\u0004)\u0004\"\u0002 \u0016\u0001\u0004y\u0004"
)
public final class SchemaUtils {
   public static boolean checkSchemaFieldExist(final StructType schema, final String colName) {
      return SchemaUtils$.MODULE$.checkSchemaFieldExist(schema, colName);
   }

   public static DataType getSchemaFieldType(final StructType schema, final String colName) {
      return SchemaUtils$.MODULE$.getSchemaFieldType(schema, colName);
   }

   public static StructField getSchemaField(final StructType schema, final String colName) {
      return SchemaUtils$.MODULE$.getSchemaField(schema, colName);
   }

   public static String toSQLId(final String parts) {
      return SchemaUtils$.MODULE$.toSQLId(parts);
   }

   public static void validateVectorCompatibleColumn(final StructType schema, final String colName) {
      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(schema, colName);
   }

   public static boolean updateField$default$3() {
      return SchemaUtils$.MODULE$.updateField$default$3();
   }

   public static StructType updateField(final StructType schema, final StructField field, final boolean overwriteMetadata) {
      return SchemaUtils$.MODULE$.updateField(schema, field, overwriteMetadata);
   }

   public static StructType updateNumeric(final StructType schema, final String colName) {
      return SchemaUtils$.MODULE$.updateNumeric(schema, colName);
   }

   public static StructType updateNumValues(final StructType schema, final String colName, final int numValues) {
      return SchemaUtils$.MODULE$.updateNumValues(schema, colName, numValues);
   }

   public static StructType updateAttributeGroupSize(final StructType schema, final String colName, final int size) {
      return SchemaUtils$.MODULE$.updateAttributeGroupSize(schema, colName, size);
   }

   public static StructType appendColumn(final StructType schema, final StructField col) {
      return SchemaUtils$.MODULE$.appendColumn(schema, col);
   }

   public static boolean appendColumn$default$4() {
      return SchemaUtils$.MODULE$.appendColumn$default$4();
   }

   public static StructType appendColumn(final StructType schema, final String colName, final DataType dataType, final boolean nullable) {
      return SchemaUtils$.MODULE$.appendColumn(schema, colName, dataType, nullable);
   }

   public static String checkNumericType$default$3() {
      return SchemaUtils$.MODULE$.checkNumericType$default$3();
   }

   public static void checkNumericType(final StructType schema, final String colName, final String msg) {
      SchemaUtils$.MODULE$.checkNumericType(schema, colName, msg);
   }

   public static String checkColumnTypes$default$4() {
      return SchemaUtils$.MODULE$.checkColumnTypes$default$4();
   }

   public static void checkColumnTypes(final StructType schema, final String colName, final Seq dataTypes, final String msg) {
      SchemaUtils$.MODULE$.checkColumnTypes(schema, colName, dataTypes, msg);
   }

   public static String checkColumnType$default$4() {
      return SchemaUtils$.MODULE$.checkColumnType$default$4();
   }

   public static void checkColumnType(final StructType schema, final String colName, final DataType dataType, final String msg) {
      SchemaUtils$.MODULE$.checkColumnType(schema, colName, dataType, msg);
   }
}
