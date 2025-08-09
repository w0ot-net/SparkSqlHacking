package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.module.scala.DefaultScalaModule.;
import java.io.File;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.kvstore.KVStore;
import org.apache.spark.util.kvstore.KVStoreSerializer;
import org.apache.spark.util.kvstore.KVStoreView;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t-rA\u0002\u000b\u0016\u0011\u00039RD\u0002\u0004 +!\u0005q\u0003\t\u0005\u0006[\u0005!\taL\u0003\u0005a\u0005\u0001\u0011\u0007C\u0003D\u0003\u0011%A\tC\u0003l\u0003\u0011%ANB\u0003p\u0003\u00019\u0002\u000fC\u0003.\r\u0011\u0005A\u000fC\u0003x\u0003\u0011\u0005\u0001\u0010C\u0004\u0002@\u0005!\t!!\u0011\t\u000f\u0005\u0015\u0013\u0001\"\u0001\u0002H!9\u0011qK\u0001\u0005\u0002\u0005e\u0003bBA,\u0003\u0011\u0005\u0011Q\u0013\u0005\b\u0003/\nA\u0011AAY\u0011\u001d\ty,\u0001C\u0001\u0003\u0003Dq!!6\u0002\t\u0003\t9\u000eC\u0004\u0002r\u0006!\t!a=\t\u000f\t=\u0011\u0001\"\u0001\u0003\u0012\u00199!QD\u0001\u0001/\t}\u0001BB\u0017\u0013\t\u0003\u00119#A\u0004L-V#\u0018\u000e\\:\u000b\u0005Y9\u0012AB:uCR,8O\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h!\tq\u0012!D\u0001\u0016\u0005\u001dYe+\u0016;jYN\u001c2!A\u0011(!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0011\u0001fK\u0007\u0002S)\u0011!fF\u0001\tS:$XM\u001d8bY&\u0011A&\u000b\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u000f\u0003\u0019-3\u0016J\u001c3fqB\u000b'/Y7+\u0005IR\u0004CA\u001a9\u001b\u0005!$BA\u001b7\u0003\u001dYgo\u001d;pe\u0016T!aN\f\u0002\tU$\u0018\u000e\\\u0005\u0003sQ\u0012qa\u0013,J]\u0012,\u0007pK\u0001<!\ta\u0014)D\u0001>\u0015\tqt(\u0001\u0003nKR\f'B\u0001!$\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0005v\u0012aaZ3ui\u0016\u0014\u0018a\u00022bG.,g\u000e\u001a\u000b\u0004\u000b\u00024\u0007C\u0001$]\u001d\t9\u0015L\u0004\u0002I-:\u0011\u0011\n\u0016\b\u0003\u0015Ns!a\u0013*\u000f\u00051\u000bfBA'Q\u001b\u0005q%BA(/\u0003\u0019a$o\\8u}%\tA$\u0003\u0002\u001b7%\u0011\u0001$G\u0005\u0003U]I!!V\u0015\u0002\r\r|gNZ5h\u0013\t9\u0006,A\u0004ISN$xN]=\u000b\u0005UK\u0013B\u0001.\\\u0003YA\u0015P\u0019:jIN#xN]3ESN\\')Y2lK:$'BA,Y\u0013\tifLA\u0003WC2,X-\u0003\u0002`G\tYQI\\;nKJ\fG/[8o\u0011\u0015\tG\u00011\u0001c\u0003\u0011\u0019wN\u001c4\u0011\u0005\r$W\"A\f\n\u0005\u0015<\"!C*qCJ\\7i\u001c8g\u0011\u00159G\u00011\u0001i\u0003\u0011a\u0017N^3\u0011\u0005\tJ\u0017B\u00016$\u0005\u001d\u0011un\u001c7fC:\f!b]3sS\u0006d\u0017N_3s)\riWO\u001e\t\u0003]\u001ai\u0011!\u0001\u0002\u0017\u0017Z\u001bFo\u001c:f'\u000e\fG.Y*fe&\fG.\u001b>feN\u0011a!\u001d\t\u0003gIL!a\u001d\u001b\u0003#-36\u000b^8sKN+'/[1mSj,'\u000fF\u0001n\u0011\u0015\tW\u00011\u0001c\u0011\u00159W\u00011\u0001i\u0003\u0011y\u0007/\u001a8\u0016\u0007e\f\t\u0002F\u0005{\u0003G\t9$a\u000f\u0002>Q\u00111P \t\u0003gqL!! \u001b\u0003\u000f-36\u000b^8sK\"Aq\u0010CA\u0001\u0002\b\t\t!\u0001\u0006fm&$WM\\2fIE\u0002b!a\u0001\u0002\n\u00055QBAA\u0003\u0015\r\t9aI\u0001\be\u00164G.Z2u\u0013\u0011\tY!!\u0002\u0003\u0011\rc\u0017m]:UC\u001e\u0004B!a\u0004\u0002\u00121\u0001AaBA\n\u0011\t\u0007\u0011Q\u0003\u0002\u0002\u001bF!\u0011qCA\u000f!\r\u0011\u0013\u0011D\u0005\u0004\u00037\u0019#a\u0002(pi\"Lgn\u001a\t\u0004E\u0005}\u0011bAA\u0011G\t\u0019\u0011I\\=\t\u000f\u0005\u0015\u0002\u00021\u0001\u0002(\u0005!\u0001/\u0019;i!\u0011\tI#a\r\u000e\u0005\u0005-\"\u0002BA\u0017\u0003_\t!![8\u000b\u0005\u0005E\u0012\u0001\u00026bm\u0006LA!!\u000e\u0002,\t!a)\u001b7f\u0011\u001d\tI\u0004\u0003a\u0001\u0003\u001b\t\u0001\"\\3uC\u0012\fG/\u0019\u0005\u0006C\"\u0001\rA\u0019\u0005\u0006O\"\u0001\r\u0001[\u0001\u001bg\u0016\u0014\u0018.\u00197ju\u0016\u0014hi\u001c:ISN$xN]=TKJ4XM\u001d\u000b\u0004[\u0006\r\u0003\"B1\n\u0001\u0004\u0011\u0017!D2sK\u0006$Xm\u0013,Ti>\u0014X\rF\u0004|\u0003\u0013\n\u0019&!\u0016\t\u000f\u0005-#\u00021\u0001\u0002N\u0005I1\u000f^8sKB\u000bG\u000f\u001b\t\u0006E\u0005=\u0013qE\u0005\u0004\u0003#\u001a#AB(qi&|g\u000eC\u0003h\u0015\u0001\u0007\u0001\u000eC\u0003b\u0015\u0001\u0007!-A\u0005wS\u0016<Hk\\*fcV!\u00111LA:)\u0019\ti&!!\u0002\fR!\u0011qLA<!\u0019\t\t'a\u001b\u0002r9!\u00111MA4\u001d\ri\u0015QM\u0005\u0002I%\u0019\u0011\u0011N\u0012\u0002\u000fA\f7m[1hK&!\u0011QNA8\u0005\r\u0019V-\u001d\u0006\u0004\u0003S\u001a\u0003\u0003BA\b\u0003g\"q!!\u001e\f\u0005\u0004\t)BA\u0001U\u0011\u001d\tIh\u0003a\u0001\u0003w\naAZ5mi\u0016\u0014\bC\u0002\u0012\u0002~\u0005E\u0004.C\u0002\u0002\u0000\r\u0012\u0011BR;oGRLwN\\\u0019\t\u000f\u0005\r5\u00021\u0001\u0002\u0006\u0006!a/[3x!\u0015\u0019\u0014qQA9\u0013\r\tI\t\u000e\u0002\f\u0017Z\u001bFo\u001c:f-&,w\u000fC\u0004\u0002\u000e.\u0001\r!a$\u0002\u00075\f\u0007\u0010E\u0002#\u0003#K1!a%$\u0005\rIe\u000e^\u000b\u0005\u0003/\u000by\n\u0006\u0005\u0002\u001a\u0006\u0015\u0016\u0011VAW)\u0011\tY*!)\u0011\r\u0005\u0005\u00141NAO!\u0011\ty!a(\u0005\u000f\u0005UDB1\u0001\u0002\u0016!9\u0011\u0011\u0010\u0007A\u0002\u0005\r\u0006C\u0002\u0012\u0002~\u0005u\u0005\u000eC\u0004\u0002\u00042\u0001\r!a*\u0011\u000bM\n9)!(\t\u000f\u0005-F\u00021\u0001\u0002\u0010\u0006!aM]8n\u0011\u001d\ty\u000b\u0004a\u0001\u0003\u001f\u000bQ!\u001e8uS2,B!a-\u0002:R!\u0011QWA^!\u0019\t\t'a\u001b\u00028B!\u0011qBA]\t\u001d\t)(\u0004b\u0001\u0003+Aq!a!\u000e\u0001\u0004\ti\fE\u00034\u0003\u000f\u000b9,A\u0003d_VtG/\u0006\u0003\u0002D\u0006=G\u0003BAc\u0003#$B!a$\u0002H\"9\u0011\u0011\u001a\bA\u0002\u0005-\u0017!C2pk:$h)\u001e8d!\u0019\u0011\u0013QPAgQB!\u0011qBAh\t\u001d\t)H\u0004b\u0001\u0003+Aq!a!\u000f\u0001\u0004\t\u0019\u000eE\u00034\u0003\u000f\u000bi-A\u0004g_J,\u0017m\u00195\u0016\t\u0005e\u00171\u001e\u000b\u0005\u00037\fi\u000f\u0006\u0003\u0002^\u0006\r\bc\u0001\u0012\u0002`&\u0019\u0011\u0011]\u0012\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003K|\u0001\u0019AAt\u0003-1wN]3bG\"4UO\\2\u0011\u000f\t\ni(!;\u0002^B!\u0011qBAv\t\u001d\t)h\u0004b\u0001\u0003+Aq!a!\u0010\u0001\u0004\ty\u000fE\u00034\u0003\u000f\u000bI/\u0001\u0005nCB$vnU3r+\u0019\t)P!\u0003\u0002~R!\u0011q\u001fB\u0006)\u0011\tIP!\u0001\u0011\r\u0005\u0005\u00141NA~!\u0011\ty!!@\u0005\u000f\u0005}\bC1\u0001\u0002\u0016\t\t!\tC\u0004\u0003\u0004A\u0001\rA!\u0002\u0002\u000f5\f\u0007OR;oGB9!%! \u0003\b\u0005m\b\u0003BA\b\u0005\u0013!q!!\u001e\u0011\u0005\u0004\t)\u0002C\u0004\u0002\u0004B\u0001\rA!\u0004\u0011\u000bM\n9Ia\u0002\u0002\tML'0Z\u000b\u0005\u0005'\u0011Y\u0002\u0006\u0003\u0002\u0010\nU\u0001bBAB#\u0001\u0007!q\u0003\t\u0006g\u0005\u001d%\u0011\u0004\t\u0005\u0003\u001f\u0011Y\u0002B\u0004\u0002vE\u0011\r!!\u0006\u000335+G/\u00193bi\u0006l\u0015n]7bi\u000eDW\t_2faRLwN\\\n\u0004%\t\u0005\u0002\u0003BA1\u0005GIAA!\n\u0002p\tIQ\t_2faRLwN\u001c\u000b\u0003\u0005S\u0001\"A\u001c\n"
)
public final class KVUtils {
   public static int size(final KVStoreView view) {
      return KVUtils$.MODULE$.size(view);
   }

   public static Seq mapToSeq(final KVStoreView view, final Function1 mapFunc) {
      return KVUtils$.MODULE$.mapToSeq(view, mapFunc);
   }

   public static void foreach(final KVStoreView view, final Function1 foreachFunc) {
      KVUtils$.MODULE$.foreach(view, foreachFunc);
   }

   public static int count(final KVStoreView view, final Function1 countFunc) {
      return KVUtils$.MODULE$.count(view, countFunc);
   }

   public static Seq viewToSeq(final KVStoreView view) {
      return KVUtils$.MODULE$.viewToSeq(view);
   }

   public static Seq viewToSeq(final KVStoreView view, final int from, final int until, final Function1 filter) {
      return KVUtils$.MODULE$.viewToSeq(view, from, until, filter);
   }

   public static Seq viewToSeq(final KVStoreView view, final int max, final Function1 filter) {
      return KVUtils$.MODULE$.viewToSeq(view, max, filter);
   }

   public static KVStore createKVStore(final Option storePath, final boolean live, final SparkConf conf) {
      return KVUtils$.MODULE$.createKVStore(storePath, live, conf);
   }

   public static KVStoreScalaSerializer serializerForHistoryServer(final SparkConf conf) {
      return KVUtils$.MODULE$.serializerForHistoryServer(conf);
   }

   public static KVStore open(final File path, final Object metadata, final SparkConf conf, final boolean live, final ClassTag evidence$1) {
      return KVUtils$.MODULE$.open(path, metadata, conf, live, evidence$1);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return KVUtils$.MODULE$.LogStringContext(sc);
   }

   public static class KVStoreScalaSerializer extends KVStoreSerializer {
      public KVStoreScalaSerializer() {
         this.mapper.registerModule(.MODULE$);
         this.mapper.setSerializationInclusion(Include.NON_ABSENT);
      }
   }

   public static class MetadataMismatchException extends Exception {
   }
}
