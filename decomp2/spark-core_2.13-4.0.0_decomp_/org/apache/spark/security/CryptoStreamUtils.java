package org.apache.spark.security;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Properties;
import javax.crypto.spec.SecretKeySpec;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.package$;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rvA\u0002\u0010 \u0011\u0003\tsE\u0002\u0004*?!\u0005\u0011E\u000b\u0005\u0006o\u0005!\t!\u000f\u0005\bu\u0005\u0011\r\u0011\"\u0001<\u0011\u0019y\u0014\u0001)A\u0005y!9\u0001)\u0001b\u0001\n\u0003\t\u0005B\u0002&\u0002A\u0003%!\tC\u0003L\u0003\u0011\u0005A\nC\u0003d\u0003\u0011\u0005A\rC\u0003r\u0003\u0011\u0005!\u000fC\u0003{\u0003\u0011\u00051\u0010C\u0004\u0002\u0006\u0005!\t!a\u0002\t\u000f\u0005e\u0011\u0001\"\u0001\u0002\u001c!A\u0011qD\u0001!\n\u0013\t\tC\u0002\u0004\u0002(\u0005!\u0011\u0011\u0006\u0005\n\u0003cq!\u0011!Q\u0001\n\u0015Daa\u000e\b\u0005\u0002\u0005M\u0002bBA\u001e\u001d\u0011\u0005\u0013Q\b\u0005\b\u0003\u0017rA\u0011IA'\u0011\u001d\t)F\u0004C!\u0003/2a!a\u0018\u0002\t\u0005\u0005\u0004\u0002\u0003/\u0015\u0005\u0003\u0005\u000b\u0011B/\t\u0011Y#\"\u0011!Q\u0001\n]Caa\u000e\u000b\u0005\u0002\u0005\r\u0004\"CA6)\t\u0007I\u0011AA7\u0011!\t\u0019\t\u0006Q\u0001\n\u0005=\u0004\"CAC)\t\u0007I\u0011AAD\u0011!\ti\n\u0006Q\u0001\n\u0005%\u0005\"CA\f)\t\u0007I\u0011AAP\u0011!\t\t\u000b\u0006Q\u0001\n\u0005%\u0011!E\"ssB$xn\u0015;sK\u0006lW\u000b^5mg*\u0011\u0001%I\u0001\tg\u0016\u001cWO]5us*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014x\r\u0005\u0002)\u00035\tqDA\tDef\u0004Ho\\*ue\u0016\fW.\u0016;jYN\u001c2!A\u00162!\tas&D\u0001.\u0015\u0005q\u0013!B:dC2\f\u0017B\u0001\u0019.\u0005\u0019\te.\u001f*fMB\u0011!'N\u0007\u0002g)\u0011A'I\u0001\tS:$XM\u001d8bY&\u0011ag\r\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0014\u0002%%3v\fT#O\u000fRCu,\u0013(`\u0005f#ViU\u000b\u0002yA\u0011A&P\u0005\u0003}5\u00121!\u00138u\u0003MIek\u0018'F\u001d\u001e#\u0006jX%O?\nKF+R*!\u0003%\u001a\u0006+\u0011*L?&{u,\u0012(D%f\u0003F+S(O?\u000e{U*T(O'~\u001buJ\u0014$J\u000f~\u0003&+\u0012$J1V\t!\t\u0005\u0002D\u00116\tAI\u0003\u0002F\r\u0006!A.\u00198h\u0015\u00059\u0015\u0001\u00026bm\u0006L!!\u0013#\u0003\rM#(/\u001b8h\u0003)\u001a\u0006+\u0011*L?&{u,\u0012(D%f\u0003F+S(O?\u000e{U*T(O'~\u001buJ\u0014$J\u000f~\u0003&+\u0012$J1\u0002\n\u0001d\u0019:fCR,7I]=qi>|U\u000f\u001e9viN#(/Z1n)\u0011i5+V.\u0011\u00059\u000bV\"A(\u000b\u0005A3\u0015AA5p\u0013\t\u0011vJ\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u0003U\u000f\u0001\u0007Q*\u0001\u0002pg\")ak\u0002a\u0001/\u0006I1\u000f]1sW\u000e{gN\u001a\t\u00031fk\u0011!I\u0005\u00035\u0006\u0012\u0011b\u00159be.\u001cuN\u001c4\t\u000bq;\u0001\u0019A/\u0002\u0007-,\u0017\u0010E\u0002-=\u0002L!aX\u0017\u0003\u000b\u0005\u0013(/Y=\u0011\u00051\n\u0017B\u00012.\u0005\u0011\u0011\u0015\u0010^3\u0002+\r\u0014X-\u0019;f/JLG/\u00192mK\u000eC\u0017M\u001c8fYR!Q-\\8q!\t17.D\u0001h\u0015\tA\u0017.\u0001\u0005dQ\u0006tg.\u001a7t\u0015\tQg)A\u0002oS>L!\u0001\\4\u0003']\u0013\u0018\u000e^1cY\u0016\u0014\u0015\u0010^3DQ\u0006tg.\u001a7\t\u000b9D\u0001\u0019A3\u0002\u000f\rD\u0017M\u001c8fY\")a\u000b\u0003a\u0001/\")A\f\u0003a\u0001;\u000692M]3bi\u0016\u001c%/\u001f9u_&s\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u0005gZD\u0018\u0010\u0005\u0002Oi&\u0011Qo\u0014\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW\u000eC\u0003x\u0013\u0001\u00071/\u0001\u0002jg\")a+\u0003a\u0001/\")A,\u0003a\u0001;\u0006)2M]3bi\u0016\u0014V-\u00193bE2,7\t[1o]\u0016dGC\u0002?\u0000\u0003\u0003\t\u0019\u0001\u0005\u0002g{&\u0011ap\u001a\u0002\u0014%\u0016\fG-\u00192mK\nKH/Z\"iC:tW\r\u001c\u0005\u0006]*\u0001\r\u0001 \u0005\u0006-*\u0001\ra\u0016\u0005\u00069*\u0001\r!X\u0001\ri>\u001c%/\u001f9u_\u000e{gN\u001a\u000b\u0005\u0003\u0013\t)\u0002\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\tyAR\u0001\u0005kRLG.\u0003\u0003\u0002\u0014\u00055!A\u0003)s_B,'\u000f^5fg\"1\u0011qC\u0006A\u0002]\u000bAaY8oM\u0006I1M]3bi\u0016\\U-\u001f\u000b\u0004;\u0006u\u0001BBA\f\u0019\u0001\u0007q+\u0001\u000ede\u0016\fG/Z%oSRL\u0017\r\\5{CRLwN\u001c,fGR|'\u000fF\u0002^\u0003GAq!!\n\u000e\u0001\u0004\tI!\u0001\u0006qe>\u0004XM\u001d;jKN\u00141c\u0011:zaR|\u0007*\u001a7qKJ\u001c\u0005.\u00198oK2\u001cBADA\u0016KB\u00191)!\f\n\u0007\u0005=BI\u0001\u0004PE*,7\r^\u0001\u0005g&t7\u000e\u0006\u0003\u00026\u0005e\u0002cAA\u001c\u001d5\t\u0011\u0001\u0003\u0004\u00022A\u0001\r!Z\u0001\u0006oJLG/\u001a\u000b\u0004y\u0005}\u0002bBA!#\u0001\u0007\u00111I\u0001\u0004gJ\u001c\u0007\u0003BA#\u0003\u000fj\u0011![\u0005\u0004\u0003\u0013J'A\u0003\"zi\u0016\u0014UO\u001a4fe\u00061\u0011n](qK:$\"!a\u0014\u0011\u00071\n\t&C\u0002\u0002T5\u0012qAQ8pY\u0016\fg.A\u0003dY>\u001cX\r\u0006\u0002\u0002ZA\u0019A&a\u0017\n\u0007\u0005uSF\u0001\u0003V]&$(\u0001D\"ssB$x\u000eU1sC6\u001c8C\u0001\u000b,)\u0019\t)'a\u001a\u0002jA\u0019\u0011q\u0007\u000b\t\u000bq;\u0002\u0019A/\t\u000bY;\u0002\u0019A,\u0002\u000f-,\u0017p\u00159fGV\u0011\u0011q\u000e\t\u0005\u0003c\ny(\u0004\u0002\u0002t)!\u0011QOA<\u0003\u0011\u0019\b/Z2\u000b\t\u0005e\u00141P\u0001\u0007GJL\b\u000f^8\u000b\u0005\u0005u\u0014!\u00026bm\u0006D\u0018\u0002BAA\u0003g\u0012QbU3de\u0016$8*Z=Ta\u0016\u001c\u0017\u0001C6fsN\u0003Xm\u0019\u0011\u0002\u001dQ\u0014\u0018M\\:g_Jl\u0017\r^5p]V\u0011\u0011\u0011\u0012\t\u0005\u0003\u0017\u000bIJ\u0004\u0003\u0002\u000e\u0006U\u0005cAAH[5\u0011\u0011\u0011\u0013\u0006\u0004\u0003'C\u0014A\u0002\u001fs_>$h(C\u0002\u0002\u00186\na\u0001\u0015:fI\u00164\u0017bA%\u0002\u001c*\u0019\u0011qS\u0017\u0002\u001fQ\u0014\u0018M\\:g_Jl\u0017\r^5p]\u0002*\"!!\u0003\u0002\u000b\r|gN\u001a\u0011"
)
public final class CryptoStreamUtils {
   public static byte[] createKey(final SparkConf conf) {
      return CryptoStreamUtils$.MODULE$.createKey(conf);
   }

   public static Properties toCryptoConf(final SparkConf conf) {
      return CryptoStreamUtils$.MODULE$.toCryptoConf(conf);
   }

   public static ReadableByteChannel createReadableChannel(final ReadableByteChannel channel, final SparkConf sparkConf, final byte[] key) {
      return CryptoStreamUtils$.MODULE$.createReadableChannel(channel, sparkConf, key);
   }

   public static InputStream createCryptoInputStream(final InputStream is, final SparkConf sparkConf, final byte[] key) {
      return CryptoStreamUtils$.MODULE$.createCryptoInputStream(is, sparkConf, key);
   }

   public static WritableByteChannel createWritableChannel(final WritableByteChannel channel, final SparkConf sparkConf, final byte[] key) {
      return CryptoStreamUtils$.MODULE$.createWritableChannel(channel, sparkConf, key);
   }

   public static OutputStream createCryptoOutputStream(final OutputStream os, final SparkConf sparkConf, final byte[] key) {
      return CryptoStreamUtils$.MODULE$.createCryptoOutputStream(os, sparkConf, key);
   }

   public static String SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX() {
      return CryptoStreamUtils$.MODULE$.SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX();
   }

   public static int IV_LENGTH_IN_BYTES() {
      return CryptoStreamUtils$.MODULE$.IV_LENGTH_IN_BYTES();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return CryptoStreamUtils$.MODULE$.LogStringContext(sc);
   }

   private static class CryptoHelperChannel implements WritableByteChannel {
      private final WritableByteChannel sink;

      public int write(final ByteBuffer src) {
         int count = src.remaining();

         while(src.hasRemaining()) {
            this.sink.write(src);
         }

         return count;
      }

      public boolean isOpen() {
         return this.sink.isOpen();
      }

      public void close() {
         this.sink.close();
      }

      public CryptoHelperChannel(final WritableByteChannel sink) {
         this.sink = sink;
      }
   }

   private static class CryptoParams {
      private final SecretKeySpec keySpec;
      private final String transformation;
      private final Properties conf;

      public SecretKeySpec keySpec() {
         return this.keySpec;
      }

      public String transformation() {
         return this.transformation;
      }

      public Properties conf() {
         return this.conf;
      }

      public CryptoParams(final byte[] key, final SparkConf sparkConf) {
         this.keySpec = new SecretKeySpec(key, "AES");
         this.transformation = (String)sparkConf.get(package$.MODULE$.IO_CRYPTO_CIPHER_TRANSFORMATION());
         this.conf = CryptoStreamUtils$.MODULE$.toCryptoConf(sparkConf);
      }
   }
}
