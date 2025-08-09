package org.apache.spark.mllib.util;

import org.apache.spark.SparkContext;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r}q!\u0002\u000f\u001e\u0011\u0003Ac!\u0002\u0016\u001e\u0011\u0003Y\u0003\"\u0002\u001d\u0002\t\u0003I\u0004\"\u0003\u001e\u0002\u0011\u000b\u0007I\u0011A\u0010<\u0011\u0015y\u0014\u0001\"\u0001A\u0011\u0019\u0001\u0018\u0001\"\u0001\"c\"110\u0001C\u0001CqDqa_\u0001\u0005\u0002\u0005\n\t\u0001\u0003\u0005\u00024\u0005!\t!IA\u001b\u0011\u0019y\u0014\u0001\"\u0001\u0002<!1q(\u0001C\u0001\u0003\u000bBq!!\u0014\u0002\t\u0003\ty\u0005C\u0004\u0002b\u0005!\t!a\u0019\t\u000f\u0005\u0005\u0014\u0001\"\u0001\u0002\u0000!9\u0011qQ\u0001\u0005\u0002\u0005%\u0005bBAD\u0003\u0011\u0005\u00111\u0013\u0005\b\u00037\u000bA\u0011AAO\u0011\u001d\tY*\u0001C\u0001\u0003?Dq!a'\u0002\t\u0003\u00119\u0001C\u0004\u0003B\u0005!\tAa\u0011\t\u000f\t-\u0013\u0001\"\u0001\u0003N!9!\u0011P\u0001\u0005\u0002\tm\u0004b\u0002BH\u0003\u0011\u0005!\u0011\u0013\u0005\b\u0005K\u000bA\u0011\u0001BT\u0011!\u0011Y,\u0001C\u0001?\tu\u0006B\u0003Bj\u0003E\u0005I\u0011A\u0010\u0003V\"A!q]\u0001\u0005\u0002\u0005\u0012I\u000fC\u0004\u0003p\u0006!\tA!=\u0002\u000f5cU\u000b^5mg*\u0011adH\u0001\u0005kRLGN\u0003\u0002!C\u0005)Q\u000e\u001c7jE*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005%\nQ\"A\u000f\u0003\u000f5cU\u000b^5mgN\u0019\u0011\u0001\f\u001a\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0003=\nQa]2bY\u0006L!!\r\u0018\u0003\r\u0005s\u0017PU3g!\t\u0019d'D\u00015\u0015\t)\u0014%\u0001\u0005j]R,'O\\1m\u0013\t9DGA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?)\u0005A\u0013aB#Q'&cuJT\u000b\u0002yA\u0011Q&P\u0005\u0003}9\u0012a\u0001R8vE2,\u0017A\u00047pC\u0012d\u0015NY*W\u001b\u001aKG.\u001a\u000b\u0006\u00036\u001b\u0006-\u001a\t\u0004\u0005\u0016;U\"A\"\u000b\u0005\u0011\u000b\u0013a\u0001:eI&\u0011ai\u0011\u0002\u0004%\u0012#\u0005C\u0001%L\u001b\u0005I%B\u0001& \u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0003\u0019&\u0013A\u0002T1cK2,G\rU8j]RDQA\u0014\u0003A\u0002=\u000b!a]2\u0011\u0005A\u000bV\"A\u0011\n\u0005I\u000b#\u0001D*qCJ\\7i\u001c8uKb$\b\"\u0002+\u0005\u0001\u0004)\u0016\u0001\u00029bi\"\u0004\"AV/\u000f\u0005][\u0006C\u0001-/\u001b\u0005I&B\u0001.(\u0003\u0019a$o\\8u}%\u0011ALL\u0001\u0007!J,G-\u001a4\n\u0005y{&AB*ue&twM\u0003\u0002]]!)\u0011\r\u0002a\u0001E\u0006Ya.^7GK\u0006$XO]3t!\ti3-\u0003\u0002e]\t\u0019\u0011J\u001c;\t\u000b\u0019$\u0001\u0019\u00012\u0002\u001b5Lg\u000eU1si&$\u0018n\u001c8tQ\r!\u0001N\u001c\t\u0003S2l\u0011A\u001b\u0006\u0003W\u0006\n!\"\u00198o_R\fG/[8o\u0013\ti'NA\u0003TS:\u001cW-I\u0001p\u0003\u0015\td\u0006\r\u00181\u0003I\u0019w.\u001c9vi\u0016tU/\u001c$fCR,(/Z:\u0015\u0005\t\u0014\b\"\u0002#\u0006\u0001\u0004\u0019\bc\u0001\"FiB)Q&\u001e\u001fxu&\u0011aO\f\u0002\u0007)V\u0004H.Z\u001a\u0011\u00075B(-\u0003\u0002z]\t)\u0011I\u001d:bsB\u0019Q\u0006\u001f\u001f\u0002\u001fA\f'o]3MS\n\u001cf+\u0014$jY\u0016$Ba]?\u007f\u007f\")aJ\u0002a\u0001\u001f\")AK\u0002a\u0001+\")aM\u0002a\u0001ER91/a\u0001\u0002\u0014\u0005%\u0002bBA\u0003\u000f\u0001\u0007\u0011qA\u0001\rgB\f'o[*fgNLwN\u001c\t\u0005\u0003\u0013\ty!\u0004\u0002\u0002\f)\u0019\u0011QB\u0011\u0002\u0007M\fH.\u0003\u0003\u0002\u0012\u0005-!\u0001D*qCJ\\7+Z:tS>t\u0007bBA\u000b\u000f\u0001\u0007\u0011qC\u0001\u0006a\u0006$\bn\u001d\t\u0006\u00033\t\u0019#\u0016\b\u0005\u00037\tyBD\u0002Y\u0003;I\u0011aL\u0005\u0004\u0003Cq\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003K\t9CA\u0002TKFT1!!\t/\u0011\u001d\tYc\u0002a\u0001\u0003[\tqa\u001c9uS>t7\u000fE\u0003W\u0003_)V+C\u0002\u00022}\u00131!T1q\u0003E\u0001\u0018M]:f\u0019&\u00147KV'SK\u000e|'\u000f\u001a\u000b\u0004i\u0006]\u0002BBA\u001d\u0011\u0001\u0007Q+\u0001\u0003mS:,GcB!\u0002>\u0005}\u0012\u0011\t\u0005\u0006\u001d&\u0001\ra\u0014\u0005\u0006)&\u0001\r!\u0016\u0005\u0006C&\u0001\rA\u0019\u0015\u0004\u0013!tG#B!\u0002H\u0005%\u0003\"\u0002(\u000b\u0001\u0004y\u0005\"\u0002+\u000b\u0001\u0004)\u0006f\u0001\u0006i]\u0006\u00012/\u0019<f\u0003Nd\u0015NY*W\u001b\u001aKG.\u001a\u000b\u0007\u0003#\n9&a\u0017\u0011\u00075\n\u0019&C\u0002\u0002V9\u0012A!\u00168ji\"1\u0011\u0011L\u0006A\u0002\u0005\u000bA\u0001Z1uC\"1\u0011QL\u0006A\u0002U\u000b1\u0001Z5sQ\rY\u0001N\\\u0001\fY>\fGMV3di>\u00148\u000f\u0006\u0005\u0002f\u0005M\u0014QOA<!\u0011\u0011U)a\u001a\u0011\t\u0005%\u0014qN\u0007\u0003\u0003WR1!!\u001c \u0003\u0019a\u0017N\\1mO&!\u0011\u0011OA6\u0005\u00191Vm\u0019;pe\")a\n\u0004a\u0001\u001f\")A\u000b\u0004a\u0001+\")a\r\u0004a\u0001E\"\"A\u0002[A>C\t\ti(A\u00032]Er\u0003\u0007\u0006\u0004\u0002f\u0005\u0005\u00151\u0011\u0005\u0006\u001d6\u0001\ra\u0014\u0005\u0006)6\u0001\r!\u0016\u0015\u0005\u001b!\fY(A\tm_\u0006$G*\u00192fY\u0016$\u0007k\\5oiN$r!QAF\u0003\u001b\u000by\tC\u0003O\u001d\u0001\u0007q\nC\u0003U\u001d\u0001\u0007Q\u000bC\u0003g\u001d\u0001\u0007!\r\u000b\u0003\u000fQ\u0006mD#B!\u0002\u0016\u0006]\u0005\"\u0002(\u0010\u0001\u0004y\u0005BBA/\u001f\u0001\u0007Q\u000b\u000b\u0003\u0010Q\u0006m\u0014!B6G_2$W\u0003BAP\u0003c#\u0002\"!)\u0002T\u0006U\u0017\u0011\u001c\u000b\u0005\u0003G\u000b\u0019\r\u0005\u0003.q\u0006\u0015\u0006cB\u0017\u0002(\u0006-\u00161V\u0005\u0004\u0003Ss#A\u0002+va2,'\u0007\u0005\u0003C\u000b\u00065\u0006\u0003BAX\u0003cc\u0001\u0001B\u0004\u00024B\u0011\r!!.\u0003\u0003Q\u000bB!a.\u0002>B\u0019Q&!/\n\u0007\u0005mfFA\u0004O_RD\u0017N\\4\u0011\u00075\ny,C\u0002\u0002B:\u00121!\u00118z\u0011%\t)\rEA\u0001\u0002\b\t9-\u0001\u0006fm&$WM\\2fIE\u0002b!!3\u0002P\u00065VBAAf\u0015\r\tiML\u0001\be\u00164G.Z2u\u0013\u0011\t\t.a3\u0003\u0011\rc\u0017m]:UC\u001eDa\u0001\u0012\tA\u0002\u0005-\u0006BBAl!\u0001\u0007!-\u0001\u0005ok64u\u000e\u001c3t\u0011\u0019\tY\u000e\u0005a\u0001E\u0006!1/Z3eQ\r\u0001\u0002N\\\u000b\u0005\u0003C\fi\u000f\u0006\u0005\u0002d\u0006U\u0018q_A})\u0011\t)/a<\u0011\t5B\u0018q\u001d\t\b[\u0005\u001d\u0016\u0011^Au!\u0011\u0011U)a;\u0011\t\u0005=\u0016Q\u001e\u0003\b\u0003g\u000b\"\u0019AA[\u0011%\t\t0EA\u0001\u0002\b\t\u00190\u0001\u0006fm&$WM\\2fII\u0002b!!3\u0002P\u0006-\bB\u0002#\u0012\u0001\u0004\tI\u000f\u0003\u0004\u0002XF\u0001\rA\u0019\u0005\b\u00037\f\u0002\u0019AA~!\ri\u0013Q`\u0005\u0004\u0003\u007ft#\u0001\u0002'p]\u001eDC!\u00055\u0003\u0004\u0005\u0012!QA\u0001\u0006e9\u0002d\u0006\r\u000b\t\u0005\u0013\u0011)B!\u000e\u00038A!Q\u0006\u001fB\u0006!\u001di\u0013q\u0015B\u0007\u0005\u001b\u0001BAQ#\u0003\u0010A!\u0011\u0011\u0002B\t\u0013\u0011\u0011\u0019\"a\u0003\u0003\u0007I{w\u000fC\u0004\u0003\u0018I\u0001\rA!\u0007\u0002\u0005\u00114\u0007\u0003\u0002B\u000e\u0005_qAA!\b\u0003.9!!q\u0004B\u0016\u001d\u0011\u0011\tC!\u000b\u000f\t\t\r\"q\u0005\b\u00041\n\u0015\u0012\"\u0001\u0014\n\u0005\u0011*\u0013B\u0001\u0012$\u0013\r\ti!I\u0005\u0005\u0003C\tY!\u0003\u0003\u00032\tM\"!\u0003#bi\u00064%/Y7f\u0015\u0011\t\t#a\u0003\t\r\u0005]'\u00031\u0001c\u0011\u0019\u0011ID\u0005a\u0001+\u0006Yam\u001c7e\u0007>dg*Y7fQ\u0011\u0011\u0002N!\u0010\"\u0005\t}\u0012!B\u001a/c9\u0002\u0014AC1qa\u0016tGMQ5bgR!\u0011q\rB#\u0011\u001d\u00119e\u0005a\u0001\u0003O\naA^3di>\u0014\bfA\ni]\u0006A2m\u001c8wKJ$h+Z2u_J\u001cu\u000e\\;n]N$v.\u0014'\u0015\r\te!q\nB1\u0011\u001d\u0011\t\u0006\u0006a\u0001\u0005'\nq\u0001Z1uCN,G\u000f\r\u0003\u0003V\tu\u0003CBA\u0005\u0005/\u0012Y&\u0003\u0003\u0003Z\u0005-!a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003_\u0013i\u0006\u0002\u0007\u0003`\t=\u0013\u0011!A\u0001\u0006\u0003\t)LA\u0002`IEBqAa\u0019\u0015\u0001\u0004\u0011)'\u0001\u0003d_2\u001c\b\u0003B\u0017\u0003hUK1A!\u001b/\u0005)a$/\u001a9fCR,GM\u0010\u0015\u0005)!\u0014\u0019\u0001K\u0002\u0015\u0005_\u0002BA!\u001d\u0003v5\u0011!1\u000f\u0006\u0003W:JAAa\u001e\u0003t\t9a/\u0019:be\u001e\u001c\u0018AG2p]Z,'\u000f\u001e,fGR|'oQ8mk6t7O\u0012:p[6cEC\u0002B\r\u0005{\u0012I\tC\u0004\u0003RU\u0001\rAa 1\t\t\u0005%Q\u0011\t\u0007\u0003\u0013\u00119Fa!\u0011\t\u0005=&Q\u0011\u0003\r\u0005\u000f\u0013i(!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0004?\u0012\u0012\u0004b\u0002B2+\u0001\u0007!Q\r\u0015\u0005+!\u0014\u0019\u0001K\u0002\u0016\u0005_\n\u0001dY8om\u0016\u0014H/T1ue&D8i\u001c7v[:\u001cHk\\'M)\u0019\u0011IBa%\u0003 \"9!\u0011\u000b\fA\u0002\tU\u0005\u0007\u0002BL\u00057\u0003b!!\u0003\u0003X\te\u0005\u0003BAX\u00057#AB!(\u0003\u0014\u0006\u0005\t\u0011!B\u0001\u0003k\u00131a\u0018\u00134\u0011\u001d\u0011\u0019G\u0006a\u0001\u0005KBCA\u00065\u0003\u0004!\u001aaCa\u001c\u00025\r|gN^3si6\u000bGO]5y\u0007>dW/\u001c8t\rJ|W.\u0014'\u0015\r\te!\u0011\u0016B[\u0011\u001d\u0011\tf\u0006a\u0001\u0005W\u0003DA!,\u00032B1\u0011\u0011\u0002B,\u0005_\u0003B!a,\u00032\u0012a!1\u0017BU\u0003\u0003\u0005\tQ!\u0001\u00026\n\u0019q\f\n\u001b\t\u000f\t\rt\u00031\u0001\u0003f!\"q\u0003\u001bB\u0002Q\r9\"qN\u0001\u0014M\u0006\u001cHoU9vCJ,G\rR5ti\u0006t7-\u001a\u000b\fy\t}&1\u0019Bd\u0005\u0017\u0014y\rC\u0004\u0003Bb\u0001\r!a\u001a\u0002\u0005Y\f\u0004B\u0002Bc1\u0001\u0007A(A\u0003o_Jl\u0017\u0007C\u0004\u0003Jb\u0001\r!a\u001a\u0002\u0005Y\u0014\u0004B\u0002Bg1\u0001\u0007A(A\u0003o_Jl'\u0007\u0003\u0005\u0003Rb\u0001\n\u00111\u0001=\u0003%\u0001(/Z2jg&|g.A\u000fgCN$8+];be\u0016$G)[:uC:\u001cW\r\n3fM\u0006,H\u000e\u001e\u00136+\t\u00119NK\u0002=\u00053\\#Aa7\u0011\t\tu'1]\u0007\u0003\u0005?TAA!9\u0003t\u0005IQO\\2iK\u000e\\W\rZ\u0005\u0005\u0005K\u0014yNA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001\u0002\\8hcA,\u0005\u0010\u001d\u000b\u0004y\t-\bB\u0002Bw5\u0001\u0007A(A\u0001y\u0003=y\u0007\u000f^5nSj,'OR1jY\u0016$GCBA)\u0005g\u001c)\u0001C\u0004\u0003vn\u0001\rAa>\u0002\u000b%t7\u000f\u001e:\u0011\t\te8\u0011A\u0007\u0003\u0005wT1A\bB\u007f\u0015\r\u0011y0I\u0001\u0003[2LAaa\u0001\u0003|\ny\u0011J\\:ueVlWM\u001c;bi&|g\u000eC\u0004\u0004\bm\u0001\ra!\u0003\u0002\u001d=\u0004H/[7ju\u0016\u00148\t\\1tgB\"11BB\n!\u001516QBB\t\u0013\r\u0019ya\u0018\u0002\u0006\u00072\f7o\u001d\t\u0005\u0003_\u001b\u0019\u0002\u0002\u0007\u0004\u0016\r\u0015\u0011\u0011!A\u0001\u0006\u0003\t)LA\u0002`IUBC!\u00015\u0004\u001a\u0005\u001211D\u0001\u0006a9Bd\u0006\r\u0015\u0005\u0001!\u001cI\u0002"
)
public final class MLUtils {
   public static Dataset convertMatrixColumnsFromML(final Dataset dataset, final String... cols) {
      return MLUtils$.MODULE$.convertMatrixColumnsFromML(dataset, cols);
   }

   public static Dataset convertMatrixColumnsToML(final Dataset dataset, final String... cols) {
      return MLUtils$.MODULE$.convertMatrixColumnsToML(dataset, cols);
   }

   public static Dataset convertVectorColumnsFromML(final Dataset dataset, final String... cols) {
      return MLUtils$.MODULE$.convertVectorColumnsFromML(dataset, cols);
   }

   public static Dataset convertVectorColumnsToML(final Dataset dataset, final String... cols) {
      return MLUtils$.MODULE$.convertVectorColumnsToML(dataset, cols);
   }

   public static void optimizerFailed(final Instrumentation instr, final Class optimizerClass) {
      MLUtils$.MODULE$.optimizerFailed(instr, optimizerClass);
   }

   public static Dataset convertMatrixColumnsFromML(final Dataset dataset, final Seq cols) {
      return MLUtils$.MODULE$.convertMatrixColumnsFromML(dataset, cols);
   }

   public static Dataset convertMatrixColumnsToML(final Dataset dataset, final Seq cols) {
      return MLUtils$.MODULE$.convertMatrixColumnsToML(dataset, cols);
   }

   public static Dataset convertVectorColumnsFromML(final Dataset dataset, final Seq cols) {
      return MLUtils$.MODULE$.convertVectorColumnsFromML(dataset, cols);
   }

   public static Dataset convertVectorColumnsToML(final Dataset dataset, final Seq cols) {
      return MLUtils$.MODULE$.convertVectorColumnsToML(dataset, cols);
   }

   public static Vector appendBias(final Vector vector) {
      return MLUtils$.MODULE$.appendBias(vector);
   }

   public static Tuple2[] kFold(final Dataset df, final int numFolds, final String foldColName) {
      return MLUtils$.MODULE$.kFold(df, numFolds, foldColName);
   }

   public static Tuple2[] kFold(final RDD rdd, final int numFolds, final long seed, final ClassTag evidence$2) {
      return MLUtils$.MODULE$.kFold(rdd, numFolds, seed, evidence$2);
   }

   public static Tuple2[] kFold(final RDD rdd, final int numFolds, final int seed, final ClassTag evidence$1) {
      return MLUtils$.MODULE$.kFold(rdd, numFolds, seed, evidence$1);
   }

   public static RDD loadLabeledPoints(final SparkContext sc, final String dir) {
      return MLUtils$.MODULE$.loadLabeledPoints(sc, dir);
   }

   public static RDD loadLabeledPoints(final SparkContext sc, final String path, final int minPartitions) {
      return MLUtils$.MODULE$.loadLabeledPoints(sc, path, minPartitions);
   }

   public static RDD loadVectors(final SparkContext sc, final String path) {
      return MLUtils$.MODULE$.loadVectors(sc, path);
   }

   public static RDD loadVectors(final SparkContext sc, final String path, final int minPartitions) {
      return MLUtils$.MODULE$.loadVectors(sc, path, minPartitions);
   }

   public static void saveAsLibSVMFile(final RDD data, final String dir) {
      MLUtils$.MODULE$.saveAsLibSVMFile(data, dir);
   }

   public static RDD loadLibSVMFile(final SparkContext sc, final String path) {
      return MLUtils$.MODULE$.loadLibSVMFile(sc, path);
   }

   public static RDD loadLibSVMFile(final SparkContext sc, final String path, final int numFeatures) {
      return MLUtils$.MODULE$.loadLibSVMFile(sc, path, numFeatures);
   }

   public static RDD loadLibSVMFile(final SparkContext sc, final String path, final int numFeatures, final int minPartitions) {
      return MLUtils$.MODULE$.loadLibSVMFile(sc, path, numFeatures, minPartitions);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return MLUtils$.MODULE$.LogStringContext(sc);
   }
}
