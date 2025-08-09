package org.apache.spark.ml.image;

import java.util.Map;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mr!B\f\u0019\u0011\u0003\u0019c!B\u0013\u0019\u0011\u00031\u0003\"B\u0017\u0002\t\u0003q\u0003bB\u0018\u0002\u0005\u0004%\t\u0001\r\u0005\u0007s\u0005\u0001\u000b\u0011B\u0019\t\u000fi\n!\u0019!C\u0001w!1A*\u0001Q\u0001\nqBq!T\u0001C\u0002\u0013\u0005a\n\u0003\u0004U\u0003\u0001\u0006Ia\u0014\u0005\b+\u0006\u0011\r\u0011\"\u0001W\u0011\u0019y\u0016\u0001)A\u0005/\"9\u0001-\u0001b\u0001\n\u0003\t\u0007BB3\u0002A\u0003%!\rC\u0004g\u0003\t\u0007I\u0011\u0001,\t\r\u001d\f\u0001\u0015!\u0003X\u0011\u0015A\u0017\u0001\"\u0001j\u0011\u0015\u0001\u0018\u0001\"\u0001r\u0011\u0015\u0019\u0018\u0001\"\u0001u\u0011\u00151\u0018\u0001\"\u0001x\u0011\u0015I\u0018\u0001\"\u0001{\u0011\u0015a\u0018\u0001\"\u0001~\u0011!\t9!\u0001C\u00019\u0005%\u0001\u0002CA\b\u0003\u0011\u0005A$!\u0005\u0002\u0017%k\u0017mZ3TG\",W.\u0019\u0006\u00033i\tQ![7bO\u0016T!a\u0007\u000f\u0002\u00055d'BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0004\u0001A\u0011A%A\u0007\u00021\tY\u0011*\\1hKN\u001b\u0007.Z7b'\t\tq\u0005\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\r\n!#\u001e8eK\u001aLg.\u001a3J[\u0006<W\rV=qKV\t\u0011\u0007\u0005\u00023o5\t1G\u0003\u00025k\u0005!A.\u00198h\u0015\u00051\u0014\u0001\u00026bm\u0006L!\u0001O\u001a\u0003\rM#(/\u001b8h\u0003M)h\u000eZ3gS:,G-S7bO\u0016$\u0016\u0010]3!\u0003!y7M\u001e+za\u0016\u001cX#\u0001\u001f\u0011\tu\"u)\u0013\b\u0003}\t\u0003\"aP\u0015\u000e\u0003\u0001S!!\u0011\u0012\u0002\rq\u0012xn\u001c;?\u0013\t\u0019\u0015&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u00131!T1q\u0015\t\u0019\u0015\u0006\u0005\u0002>\u0011&\u0011\u0001H\u0012\t\u0003Q)K!aS\u0015\u0003\u0007%sG/A\u0005pGZ$\u0016\u0010]3tA\u0005a!.\u0019<b\u001f\u000e4H+\u001f9fgV\tq\n\u0005\u0003Q'\u001eKU\"A)\u000b\u0005I+\u0014\u0001B;uS2L!!R)\u0002\u001b)\fg/Y(dmRK\b/Z:!\u00031\u0019w\u000e\\;n]N\u001b\u0007.Z7b+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0015!\u0018\u0010]3t\u0015\taF$A\u0002tc2L!AX-\u0003\u0015M#(/^2u)f\u0004X-A\u0007d_2,XN\\*dQ\u0016l\u0017\rI\u0001\fS6\fw-\u001a$jK2$7/F\u0001c!\rA3mR\u0005\u0003I&\u0012Q!\u0011:sCf\fA\"[7bO\u00164\u0015.\u001a7eg\u0002\n1\"[7bO\u0016\u001c6\r[3nC\u0006a\u0011.\\1hKN\u001b\u0007.Z7bA\u0005Iq-\u001a;Pe&<\u0017N\u001c\u000b\u0003\u000f*DQa[\bA\u00021\f1A]8x!\tig.D\u0001\\\u0013\ty7LA\u0002S_^\f\u0011bZ3u\u0011\u0016Lw\r\u001b;\u0015\u0005%\u0013\b\"B6\u0011\u0001\u0004a\u0017\u0001C4fi^KG\r\u001e5\u0015\u0005%+\b\"B6\u0012\u0001\u0004a\u0017\u0001D4fi:\u001b\u0005.\u00198oK2\u001cHCA%y\u0011\u0015Y'\u00031\u0001m\u0003\u001d9W\r^'pI\u0016$\"!S>\t\u000b-\u001c\u0002\u0019\u00017\u0002\u000f\u001d,G\u000fR1uCR\u0019a0!\u0002\u0011\u0007!\u001aw\u0010E\u0002)\u0003\u0003I1!a\u0001*\u0005\u0011\u0011\u0015\u0010^3\t\u000b-$\u0002\u0019\u00017\u0002\u001f%tg/\u00197jI&k\u0017mZ3S_^$2\u0001\\A\u0006\u0011\u0019\ti!\u0006a\u0001\u000f\u00061qN]5hS:\fa\u0001Z3d_\u0012,GCBA\n\u00033\tY\u0002\u0005\u0003)\u0003+a\u0017bAA\fS\t1q\n\u001d;j_:Da!!\u0004\u0017\u0001\u00049\u0005BBA\u000f-\u0001\u0007a0A\u0003csR,7\u000fK\u0003\u0002\u0003C\ti\u0003\u0005\u0003\u0002$\u0005%RBAA\u0013\u0015\r\t9\u0003H\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0016\u0003K\u0011QaU5oG\u0016\f#!a\f\u0002\u000bIr3G\f\u0019)\u000b\u0001\t\t#!\f"
)
public final class ImageSchema {
   public static byte[] getData(final Row row) {
      return ImageSchema$.MODULE$.getData(row);
   }

   public static int getMode(final Row row) {
      return ImageSchema$.MODULE$.getMode(row);
   }

   public static int getNChannels(final Row row) {
      return ImageSchema$.MODULE$.getNChannels(row);
   }

   public static int getWidth(final Row row) {
      return ImageSchema$.MODULE$.getWidth(row);
   }

   public static int getHeight(final Row row) {
      return ImageSchema$.MODULE$.getHeight(row);
   }

   public static String getOrigin(final Row row) {
      return ImageSchema$.MODULE$.getOrigin(row);
   }

   public static StructType imageSchema() {
      return ImageSchema$.MODULE$.imageSchema();
   }

   public static String[] imageFields() {
      return ImageSchema$.MODULE$.imageFields();
   }

   public static StructType columnSchema() {
      return ImageSchema$.MODULE$.columnSchema();
   }

   public static Map javaOcvTypes() {
      return ImageSchema$.MODULE$.javaOcvTypes();
   }

   public static scala.collection.immutable.Map ocvTypes() {
      return ImageSchema$.MODULE$.ocvTypes();
   }

   public static String undefinedImageType() {
      return ImageSchema$.MODULE$.undefinedImageType();
   }
}
