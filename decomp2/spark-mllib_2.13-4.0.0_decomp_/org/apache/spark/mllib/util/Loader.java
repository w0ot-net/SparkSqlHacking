package org.apache.spark.mllib.util;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.types.StructType;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015baB\u0005\u000b!\u0003\r\n!\u0006\u0005\u0006;\u00011\tAH\u0004\u0007\u0011*A\t\u0001D%\u0007\r%Q\u0001\u0012\u0001\u0007K\u0011\u0015Y5\u0001\"\u0001M\u0011\u0015i5\u0001\"\u0001O\u0011\u0015\u00016\u0001\"\u0001R\u0011\u0015\u00196\u0001\"\u0001U\u0011\u001d\tYa\u0001C\u0001\u0003\u001b\u0011a\u0001T8bI\u0016\u0014(BA\u0006\r\u0003\u0011)H/\u001b7\u000b\u00055q\u0011!B7mY&\u0014'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0004\u0001U\u0011a#I\n\u0003\u0001]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017\u0001\u00027pC\u0012$2aH\u00162!\t\u0001\u0013\u0005\u0004\u0001\u0005\u000b\t\u0002!\u0019A\u0012\u0003\u00035\u000b\"\u0001J\u0014\u0011\u0005a)\u0013B\u0001\u0014\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001K\u0015\u000e\u0003)I!A\u000b\u0006\u0003\u0011M\u000bg/Z1cY\u0016DQ\u0001L\u0001A\u00025\n!a]2\u0011\u00059zS\"\u0001\b\n\u0005Ar!\u0001D*qCJ\\7i\u001c8uKb$\b\"\u0002\u001a\u0002\u0001\u0004\u0019\u0014\u0001\u00029bi\"\u0004\"\u0001N\u001e\u000f\u0005UJ\u0004C\u0001\u001c\u001a\u001b\u00059$B\u0001\u001d\u0015\u0003\u0019a$o\\8u}%\u0011!(G\u0001\u0007!J,G-\u001a4\n\u0005qj$AB*ue&twM\u0003\u0002;3!\u001a\u0011aP#\u0011\u0005\u0001\u001bU\"A!\u000b\u0005\ts\u0011AC1o]>$\u0018\r^5p]&\u0011A)\u0011\u0002\u0006'&t7-Z\u0011\u0002\r\u0006)\u0011GL\u001a/a!\u001a\u0001aP#\u0002\r1{\u0017\rZ3s!\tA3a\u0005\u0002\u0004/\u00051A(\u001b8jiz\"\u0012!S\u0001\tI\u0006$\u0018\rU1uQR\u00111g\u0014\u0005\u0006e\u0015\u0001\raM\u0001\r[\u0016$\u0018\rZ1uCB\u000bG\u000f\u001b\u000b\u0003gICQA\r\u0004A\u0002M\n1b\u00195fG.\u001c6\r[3nCV\u0011Q+\u001e\u000b\u0003-n$\"a\u0016.\u0011\u0005aA\u0016BA-\u001a\u0005\u0011)f.\u001b;\t\u000fm;\u0011\u0011!a\u00029\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007usGO\u0004\u0002_W:\u0011q\f\u001b\b\u0003A\u0016t!!Y2\u000f\u0005Y\u0012\u0017\"\u0001\u000e\n\u0005\u0011L\u0012a\u0002:fM2,7\r^\u0005\u0003M\u001e\fqA];oi&lWM\u0003\u0002e3%\u0011\u0011N[\u0001\ba\u0006\u001c7.Y4f\u0015\t1w-\u0003\u0002m[\u0006AQO\\5wKJ\u001cXM\u0003\u0002jU&\u0011q\u000e\u001d\u0002\b)f\u0004X\rV1h\u0013\t\t(O\u0001\u0005UsB,G+Y4t\u0015\t\u0019x-A\u0002ba&\u0004\"\u0001I;\u0005\u000bY<!\u0019A<\u0003\t\u0011\u000bG/Y\t\u0003Ia\u0004\"\u0001G=\n\u0005iL\"aA!os\")Ap\u0002a\u0001{\u0006aAn\\1eK\u0012\u001c6\r[3nCB\u0019a0a\u0002\u000e\u0003}TA!!\u0001\u0002\u0004\u0005)A/\u001f9fg*\u0019\u0011Q\u0001\b\u0002\u0007M\fH.C\u0002\u0002\n}\u0014!b\u0015;sk\u000e$H+\u001f9f\u00031aw.\u00193NKR\fG-\u0019;b)\u0019\ty!!\t\u0002$A9\u0001$!\u00054g\u0005U\u0011bAA\n3\t1A+\u001e9mKN\u0002B!a\u0006\u0002\u001e5\u0011\u0011\u0011\u0004\u0006\u0004\u00037\u0011\u0012A\u00026t_:$4/\u0003\u0003\u0002 \u0005e!A\u0002&WC2,X\rC\u0003-\u0011\u0001\u0007Q\u0006C\u00033\u0011\u0001\u00071\u0007"
)
public interface Loader {
   static Tuple3 loadMetadata(final SparkContext sc, final String path) {
      return Loader$.MODULE$.loadMetadata(sc, path);
   }

   static void checkSchema(final StructType loadedSchema, final TypeTags.TypeTag evidence$1) {
      Loader$.MODULE$.checkSchema(loadedSchema, evidence$1);
   }

   static String metadataPath(final String path) {
      return Loader$.MODULE$.metadataPath(path);
   }

   static String dataPath(final String path) {
      return Loader$.MODULE$.dataPath(path);
   }

   Saveable load(final SparkContext sc, final String path);
}
