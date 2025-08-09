package org.apache.spark.sql.protobuf;

import java.util.Map;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.Column;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005r!B\b\u0011\u0011\u0003Yb!B\u000f\u0011\u0011\u0003q\u0002\"B\u0013\u0002\t\u00031\u0003\"B\u0014\u0002\t\u0003A\u0003\"B\u0014\u0002\t\u0003y\u0005\"B\u0014\u0002\t\u0003a\u0006\"B\u0014\u0002\t\u0003\t\u0007\"B\u0014\u0002\t\u00031\u0007\"B\u0014\u0002\t\u0003Y\u0007\"\u00029\u0002\t\u0003\t\b\"\u00029\u0002\t\u00031\b\"\u00029\u0002\t\u0003Y\bB\u00029\u0002\t\u0003\t\u0019\u0001\u0003\u0004q\u0003\u0011\u0005\u0011q\u0002\u0005\u0007a\u0006!\t!a\u0006\u0002\u0013\u0019,hn\u0019;j_:\u001c(BA\t\u0013\u0003!\u0001(o\u001c;pEV4'BA\n\u0015\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sO\u000e\u0001\u0001C\u0001\u000f\u0002\u001b\u0005\u0001\"!\u00034v]\u000e$\u0018n\u001c8t'\t\tq\u0004\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003m\tQB\u001a:p[~\u0003(o\u001c;pEV4G#B\u0015._qr\u0004C\u0001\u0016,\u001b\u0005\u0011\u0012B\u0001\u0017\u0013\u0005\u0019\u0019u\u000e\\;n]\")af\u0001a\u0001S\u0005!A-\u0019;b\u0011\u0015\u00014\u00011\u00012\u0003-iWm]:bO\u0016t\u0015-\\3\u0011\u0005IJdBA\u001a8!\t!\u0014%D\u00016\u0015\t1$$\u0001\u0004=e>|GOP\u0005\u0003q\u0005\na\u0001\u0015:fI\u00164\u0017B\u0001\u001e<\u0005\u0019\u0019FO]5oO*\u0011\u0001(\t\u0005\u0006{\r\u0001\r!M\u0001\rI\u0016\u001c8MR5mKB\u000bG\u000f\u001b\u0005\u0006\u007f\r\u0001\r\u0001Q\u0001\b_B$\u0018n\u001c8t!\u0011\te)M\u0019\u000e\u0003\tS!a\u0011#\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u000b\u0006!!.\u0019<b\u0013\t9%IA\u0002NCBD#aA%\u0011\u0005)kU\"A&\u000b\u00051#\u0012AC1o]>$\u0018\r^5p]&\u0011aj\u0013\u0002\r\u000bb\u0004XM]5nK:$\u0018\r\u001c\u000b\u0006SA\u000b&K\u0017\u0005\u0006]\u0011\u0001\r!\u000b\u0005\u0006a\u0011\u0001\r!\r\u0005\u0006'\u0012\u0001\r\u0001V\u0001\u0018E&t\u0017M]=GS2,G)Z:de&\u0004Ho\u001c:TKR\u00042\u0001I+X\u0013\t1\u0016EA\u0003BeJ\f\u0017\u0010\u0005\u0002!1&\u0011\u0011,\t\u0002\u0005\u0005f$X\rC\u0003@\t\u0001\u0007\u0001\t\u000b\u0002\u0005\u0013R!\u0011&\u00180`\u0011\u0015qS\u00011\u0001*\u0011\u0015\u0001T\u00011\u00012\u0011\u0015iT\u00011\u00012Q\t)\u0011\n\u0006\u0003*E\u000e$\u0007\"\u0002\u0018\u0007\u0001\u0004I\u0003\"\u0002\u0019\u0007\u0001\u0004\t\u0004\"B*\u0007\u0001\u0004!\u0006F\u0001\u0004J)\rIs\r\u001b\u0005\u0006]\u001d\u0001\r!\u000b\u0005\u0006S\u001e\u0001\r!M\u0001\u0011[\u0016\u001c8/Y4f\u00072\f7o\u001d(b[\u0016D#aB%\u0015\t%bWN\u001c\u0005\u0006]!\u0001\r!\u000b\u0005\u0006S\"\u0001\r!\r\u0005\u0006\u007f!\u0001\r\u0001\u0011\u0015\u0003\u0011%\u000b1\u0002^8`aJ|Go\u001c2vMR!\u0011F]:u\u0011\u0015q\u0013\u00021\u0001*\u0011\u0015\u0001\u0014\u00021\u00012\u0011\u0015i\u0014\u00021\u00012Q\tI\u0011\n\u0006\u0003*obL\b\"\u0002\u0018\u000b\u0001\u0004I\u0003\"\u0002\u0019\u000b\u0001\u0004\t\u0004\"B*\u000b\u0001\u0004!\u0006F\u0001\u0006J)\u0015IC0 @\u0000\u0011\u0015q3\u00021\u0001*\u0011\u0015\u00014\u00021\u00012\u0011\u0015i4\u00021\u00012\u0011\u0015y4\u00021\u0001AQ\tY\u0011\nF\u0005*\u0003\u000b\t9!!\u0003\u0002\f!)a\u0006\u0004a\u0001S!)\u0001\u0007\u0004a\u0001c!)1\u000b\u0004a\u0001)\")q\b\u0004a\u0001\u0001\"\u0012A\"\u0013\u000b\u0006S\u0005E\u00111\u0003\u0005\u0006]5\u0001\r!\u000b\u0005\u0006S6\u0001\r!\r\u0015\u0003\u001b%#r!KA\r\u00037\ti\u0002C\u0003/\u001d\u0001\u0007\u0011\u0006C\u0003j\u001d\u0001\u0007\u0011\u0007C\u0003@\u001d\u0001\u0007\u0001\t\u000b\u0002\u000f\u0013\u0002"
)
public final class functions {
   @Experimental
   public static Column to_protobuf(final Column data, final String messageClassName, final Map options) {
      return functions$.MODULE$.to_protobuf(data, messageClassName, options);
   }

   @Experimental
   public static Column to_protobuf(final Column data, final String messageClassName) {
      return functions$.MODULE$.to_protobuf(data, messageClassName);
   }

   @Experimental
   public static Column to_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet, final Map options) {
      return functions$.MODULE$.to_protobuf(data, messageName, binaryFileDescriptorSet, options);
   }

   @Experimental
   public static Column to_protobuf(final Column data, final String messageName, final String descFilePath, final Map options) {
      return functions$.MODULE$.to_protobuf(data, messageName, descFilePath, options);
   }

   @Experimental
   public static Column to_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet) {
      return functions$.MODULE$.to_protobuf(data, messageName, binaryFileDescriptorSet);
   }

   @Experimental
   public static Column to_protobuf(final Column data, final String messageName, final String descFilePath) {
      return functions$.MODULE$.to_protobuf(data, messageName, descFilePath);
   }

   @Experimental
   public static Column from_protobuf(final Column data, final String messageClassName, final Map options) {
      return functions$.MODULE$.from_protobuf(data, messageClassName, options);
   }

   @Experimental
   public static Column from_protobuf(final Column data, final String messageClassName) {
      return functions$.MODULE$.from_protobuf(data, messageClassName);
   }

   @Experimental
   public static Column from_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet) {
      return functions$.MODULE$.from_protobuf(data, messageName, binaryFileDescriptorSet);
   }

   @Experimental
   public static Column from_protobuf(final Column data, final String messageName, final String descFilePath) {
      return functions$.MODULE$.from_protobuf(data, messageName, descFilePath);
   }

   @Experimental
   public static Column from_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet, final Map options) {
      return functions$.MODULE$.from_protobuf(data, messageName, binaryFileDescriptorSet, options);
   }

   @Experimental
   public static Column from_protobuf(final Column data, final String messageName, final String descFilePath, final Map options) {
      return functions$.MODULE$.from_protobuf(data, messageName, descFilePath, options);
   }
}
