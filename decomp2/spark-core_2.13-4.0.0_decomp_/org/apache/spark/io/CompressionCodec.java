package org.apache.spark.io;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005eba\u0002\u000f\u001e!\u0003\r\tA\n\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u00011\ta\r\u0005\u0007{\u0001!\ta\b \t\u000b\u0001\u0003a\u0011A!\t\r\u0019\u0003A\u0011A\u0010H\u000f\u0019\u0001V\u0004#\u0001 #\u001a1A$\bE\u0001?MCQ\u0001V\u0004\u0005\u0002UCaAV\u0004\u0005\u0002}9\u0006b\u00020\b\u0005\u0004%\ta\u0018\u0005\u0007M\u001e\u0001\u000b\u0011\u00021\t\u000f\u001d<!\u0019!C\u0001?\"1\u0001n\u0002Q\u0001\n\u0001Dq![\u0004C\u0002\u0013\u0005q\f\u0003\u0004k\u000f\u0001\u0006I\u0001\u0019\u0005\bW\u001e\u0011\r\u0011\"\u0001`\u0011\u0019aw\u0001)A\u0005A\"AQn\u0002b\u0001\n\u0003yb\u000e\u0003\u0004x\u000f\u0001\u0006Ia\u001c\u0005\u0006q\u001e!\t!\u001f\u0005\b\u0003+9A\u0011AA\f\u0011\u001d\t)b\u0002C\u0001\u00037Aq!a\t\b\t\u0003\t)\u0003\u0003\u0005\u0002*\u001d\u0011\r\u0011\"\u0001`\u0011\u001d\tYc\u0002Q\u0001\n\u0001D\u0011\"!\f\b\u0005\u0004%\t!a\f\t\u0011\u0005]r\u0001)A\u0005\u0003c\u0011\u0001cQ8naJ,7o]5p]\u000e{G-Z2\u000b\u0005yy\u0012AA5p\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7\u0001A\n\u0003\u0001\u001d\u0002\"\u0001K\u0016\u000e\u0003%R\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u00010!\tA\u0003'\u0003\u00022S\t!QK\\5u\u0003Y\u0019w.\u001c9sKN\u001cX\rZ(viB,Ho\u0015;sK\u0006lGC\u0001\u001b<!\t)\u0014(D\u00017\u0015\tqrGC\u00019\u0003\u0011Q\u0017M^1\n\u0005i2$\u0001D(viB,Ho\u0015;sK\u0006l\u0007\"\u0002\u001f\u0003\u0001\u0004!\u0014!A:\u0002A\r|W\u000e\u001d:fgN,GmQ8oi&tWo\\;t\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u000b\u0003i}BQ\u0001P\u0002A\u0002Q\nQcY8naJ,7o]3e\u0013:\u0004X\u000f^*ue\u0016\fW\u000e\u0006\u0002C\u000bB\u0011QgQ\u0005\u0003\tZ\u00121\"\u00138qkR\u001cFO]3b[\")A\b\u0002a\u0001\u0005\u0006y2m\\7qe\u0016\u001c8/\u001a3D_:$\u0018N\\;pkNLe\u000e];u'R\u0014X-Y7\u0015\u0005\tC\u0005\"\u0002\u001f\u0006\u0001\u0004\u0011\u0005F\u0001\u0001K!\tYe*D\u0001M\u0015\tiu$\u0001\u0006b]:|G/\u0019;j_:L!a\u0014'\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002!\r{W\u000e\u001d:fgNLwN\\\"pI\u0016\u001c\u0007C\u0001*\b\u001b\u0005i2CA\u0004(\u0003\u0019a\u0014N\\5u}Q\t\u0011+\u0001\u0015tkB\u0004xN\u001d;t\u0007>t7-\u0019;f]\u0006$\u0018n\u001c8PMN+'/[1mSj,Gm\u0015;sK\u0006l7\u000f\u0006\u0002Y7B\u0011\u0001&W\u0005\u00035&\u0012qAQ8pY\u0016\fg\u000eC\u0003]\u0013\u0001\u0007Q,A\u0003d_\u0012,7\r\u0005\u0002S\u0001\u0005\u0019AJ\u0017\u001b\u0016\u0003\u0001\u0004\"!\u00193\u000e\u0003\tT!aY\u001c\u0002\t1\fgnZ\u0005\u0003K\n\u0014aa\u0015;sS:<\u0017\u0001\u0002'[i\u0001\n1\u0001\u0014.G\u0003\u0011a%L\u0012\u0011\u0002\rMs\u0015\t\u0015)Z\u0003\u001d\u0019f*\u0011)Q3\u0002\nAAW*U\t\u0006)!l\u0015+EA\u0005Q2\u000f[8si\u000e{W\u000e\u001d:fgNLwN\\\"pI\u0016\u001cg*Y7fgV\tq\u000e\u0005\u0003qk\u0002\u0004W\"A9\u000b\u0005I\u001c\u0018!C5n[V$\u0018M\u00197f\u0015\t!\u0018&\u0001\u0006d_2dWm\u0019;j_:L!A^9\u0003\u00075\u000b\u0007/A\u000etQ>\u0014HoQ8naJ,7o]5p]\u000e{G-Z2OC6,7\u000fI\u0001\rO\u0016$8i\u001c3fG:\u000bW.\u001a\u000b\u0004u\u0006%\u0001cA>\u0002\u00069\u0019A0!\u0001\u0011\u0005uLS\"\u0001@\u000b\u0005},\u0013A\u0002\u001fs_>$h(C\u0002\u0002\u0004%\na\u0001\u0015:fI\u00164\u0017bA3\u0002\b)\u0019\u00111A\u0015\t\u000f\u0005-A\u00031\u0001\u0002\u000e\u0005!1m\u001c8g!\u0011\ty!!\u0005\u000e\u0003}I1!a\u0005 \u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0006de\u0016\fG/Z\"pI\u0016\u001cGcA/\u0002\u001a!9\u00111B\u000bA\u0002\u00055A#B/\u0002\u001e\u0005}\u0001bBA\u0006-\u0001\u0007\u0011Q\u0002\u0005\u0007\u0003C1\u0002\u0019\u0001>\u0002\u0013\r|G-Z2OC6,\u0017\u0001D4fiNCwN\u001d;OC6,Gc\u0001>\u0002(!1\u0011\u0011E\fA\u0002i\f!DR!M\u0019\n\u000b5iS0D\u001f6\u0003&+R*T\u0013>sulQ(E\u000b\u000e\u000b1DR!M\u0019\n\u000b5iS0D\u001f6\u0003&+R*T\u0013>sulQ(E\u000b\u000e\u0003\u0013AF!M\u0019~\u001bu*\u0014)S\u000bN\u001b\u0016j\u0014(`\u0007>#UiQ*\u0016\u0005\u0005E\u0002\u0003\u00029\u00024\u0001L1!!\u000er\u0005\r\u0019V-]\u0001\u0018\u00032culQ(N!J+5kU%P\u001d~\u001bu\nR#D'\u0002\u0002"
)
public interface CompressionCodec {
   static Seq ALL_COMPRESSION_CODECS() {
      return CompressionCodec$.MODULE$.ALL_COMPRESSION_CODECS();
   }

   static String FALLBACK_COMPRESSION_CODEC() {
      return CompressionCodec$.MODULE$.FALLBACK_COMPRESSION_CODEC();
   }

   static String getShortName(final String codecName) {
      return CompressionCodec$.MODULE$.getShortName(codecName);
   }

   static CompressionCodec createCodec(final SparkConf conf, final String codecName) {
      return CompressionCodec$.MODULE$.createCodec(conf, codecName);
   }

   static CompressionCodec createCodec(final SparkConf conf) {
      return CompressionCodec$.MODULE$.createCodec(conf);
   }

   static String getCodecName(final SparkConf conf) {
      return CompressionCodec$.MODULE$.getCodecName(conf);
   }

   static String ZSTD() {
      return CompressionCodec$.MODULE$.ZSTD();
   }

   static String SNAPPY() {
      return CompressionCodec$.MODULE$.SNAPPY();
   }

   static String LZF() {
      return CompressionCodec$.MODULE$.LZF();
   }

   static String LZ4() {
      return CompressionCodec$.MODULE$.LZ4();
   }

   OutputStream compressedOutputStream(final OutputStream s);

   // $FF: synthetic method
   static OutputStream compressedContinuousOutputStream$(final CompressionCodec $this, final OutputStream s) {
      return $this.compressedContinuousOutputStream(s);
   }

   default OutputStream compressedContinuousOutputStream(final OutputStream s) {
      return this.compressedOutputStream(s);
   }

   InputStream compressedInputStream(final InputStream s);

   // $FF: synthetic method
   static InputStream compressedContinuousInputStream$(final CompressionCodec $this, final InputStream s) {
      return $this.compressedContinuousInputStream(s);
   }

   default InputStream compressedContinuousInputStream(final InputStream s) {
      return this.compressedInputStream(s);
   }

   static void $init$(final CompressionCodec $this) {
   }
}
