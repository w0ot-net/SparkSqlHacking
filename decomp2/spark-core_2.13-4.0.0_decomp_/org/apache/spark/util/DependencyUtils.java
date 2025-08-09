package org.apache.spark.util;

import java.io.File;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=tAB\b\u0011\u0011\u0003\u0011\u0002D\u0002\u0004\u001b!!\u0005!c\u0007\u0005\u0006Q\u0005!\tA\u000b\u0005\u0006W\u0005!\t\u0001\f\u0005\u0006a\u0005!\t!\r\u0005\u0006a\u0005!\t\u0001\u0015\u0005\u0006G\u0006!\t\u0001\u001a\u0005\u0006s\u0006!\tA\u001f\u0005\b\u0003\u0013\tA\u0011AA\u0006\u0011\u001d\t)#\u0001C\u0001\u0003OAq!a\r\u0002\t\u0003\t)\u0004C\u0004\u0002>\u0005!\t!a\u0010\t\u000f\u0005\u001d\u0013\u0001\"\u0001\u0002J!9\u0011QK\u0001\u0005\n\u0005]\u0003bBA1\u0003\u0011%\u00111M\u0001\u0010\t\u0016\u0004XM\u001c3f]\u000eLX\u000b^5mg*\u0011\u0011CE\u0001\u0005kRLGN\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h!\tI\u0012!D\u0001\u0011\u0005=!U\r]3oI\u0016t7-_+uS2\u001c8cA\u0001\u001dEA\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\u0004\"a\t\u0014\u000e\u0003\u0011R!!\n\n\u0002\u0011%tG/\u001a:oC2L!a\n\u0013\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0019\u0003A9W\r^%wsB\u0013x\u000e]3si&,7\u000fF\u0001.!\tIb&\u0003\u00020!\ti\u0011J^=Qe>\u0004XM\u001d;jKN\f\u0001D]3t_24X-T1wK:$U\r]3oI\u0016t7-[3t)\t\u0011d\tE\u00024wyr!\u0001N\u001d\u000f\u0005UBT\"\u0001\u001c\u000b\u0005]J\u0013A\u0002\u001fs_>$h(C\u0001 \u0013\tQd$A\u0004qC\u000e\\\u0017mZ3\n\u0005qj$aA*fc*\u0011!H\b\t\u0003\u007f\rs!\u0001Q!\u0011\u0005Ur\u0012B\u0001\"\u001f\u0003\u0019\u0001&/\u001a3fM&\u0011A)\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\ts\u0002\"B$\u0005\u0001\u0004A\u0015aA;sSB\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\u0004]\u0016$(\"A'\u0002\t)\fg/Y\u0005\u0003\u001f*\u00131!\u0016*J)\u001d\u0011\u0014K\u0016-[9zCQAU\u0003A\u0002M\u000b!\u0003]1dW\u0006<Wm\u001d+sC:\u001c\u0018\u000e^5wKB\u0011Q\u0004V\u0005\u0003+z\u0011qAQ8pY\u0016\fg\u000eC\u0003X\u000b\u0001\u0007a(\u0001\nqC\u000e\\\u0017mZ3t\u000bb\u001cG.^:j_:\u001c\b\"B-\u0006\u0001\u0004q\u0014\u0001\u00039bG.\fw-Z:\t\u000bm+\u0001\u0019\u0001 \u0002\u0019I,\u0007o\\:ji>\u0014\u0018.Z:\t\u000bu+\u0001\u0019\u0001 \u0002\u0017%4\u0018PU3q_B\u000bG\u000f\u001b\u0005\u0006?\u0016\u0001\r\u0001Y\u0001\u0010SZL8+\u001a;uS:<7\u000fU1uQB\u0019Q$\u0019 \n\u0005\tt\"AB(qi&|g.\u0001\fsKN|GN^3B]\u0012$un\u001e8m_\u0006$'*\u0019:t)\u0015qTmZ5p\u0011\u00151g\u00011\u0001?\u0003\u0011Q\u0017M]:\t\u000b!4\u0001\u0019\u0001 \u0002\u000fU\u001cXM\u001d&be\")!N\u0002a\u0001W\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003Y6l\u0011AE\u0005\u0003]J\u0011\u0011b\u00159be.\u001cuN\u001c4\t\u000bA4\u0001\u0019A9\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0002so6\t1O\u0003\u0002uk\u0006!1m\u001c8g\u0015\t1H#\u0001\u0004iC\u0012|w\u000e]\u0005\u0003qN\u0014QbQ8oM&<WO]1uS>t\u0017AE1eI*\u000b'o\u001d+p\u00072\f7o\u001d)bi\"$2a\u001f@\u0000!\tiB0\u0003\u0002~=\t!QK\\5u\u0011\u00151w\u00011\u0001?\u0011\u001d\t\ta\u0002a\u0001\u0003\u0007\ta\u0001\\8bI\u0016\u0014\bcA\r\u0002\u0006%\u0019\u0011q\u0001\t\u0003+5+H/\u00192mKV\u0013Fj\u00117bgNdu.\u00193fe\u0006\u0001Bm\\<oY>\fGMR5mK2K7\u000f\u001e\u000b\n}\u00055\u0011\u0011CA\u0011\u0003GAa!a\u0004\t\u0001\u0004q\u0014\u0001\u00034jY\u0016d\u0015n\u001d;\t\u000f\u0005M\u0001\u00021\u0001\u0002\u0016\u0005IA/\u0019:hKR$\u0015N\u001d\t\u0005\u0003/\ti\"\u0004\u0002\u0002\u001a)\u0019\u00111\u0004'\u0002\u0005%|\u0017\u0002BA\u0010\u00033\u0011AAR5mK\")!\u000e\u0003a\u0001W\")\u0001\u000f\u0003a\u0001c\u0006aAm\\<oY>\fGMR5mKRIa(!\u000b\u0002.\u0005=\u0012\u0011\u0007\u0005\u0007\u0003WI\u0001\u0019\u0001 \u0002\tA\fG\u000f\u001b\u0005\b\u0003'I\u0001\u0019AA\u000b\u0011\u0015Q\u0017\u00021\u0001l\u0011\u0015\u0001\u0018\u00021\u0001r\u0003A\u0011Xm]8mm\u0016<En\u001c2QCRD7\u000fF\u0003?\u0003o\tY\u0004\u0003\u0004\u0002:)\u0001\rAP\u0001\u0006a\u0006$\bn\u001d\u0005\u0006a*\u0001\r!]\u0001\u0012C\u0012$'*\u0019:U_\u000ec\u0017m]:qCRDG#B>\u0002B\u0005\u0015\u0003BBA\"\u0017\u0001\u0007a(\u0001\u0005m_\u000e\fGNS1s\u0011\u001d\t\ta\u0003a\u0001\u0003\u0007\ta\"\\3sO\u00164\u0015\u000e\\3MSN$8\u000fF\u0002?\u0003\u0017Bq!!\u0014\r\u0001\u0004\ty%A\u0003mSN$8\u000f\u0005\u0003\u001e\u0003#r\u0014bAA*=\tQAH]3qK\u0006$X\r\u001a \u0002\u001fM\u0004H.\u001b;P]\u001a\u0013\u0018mZ7f]R$B!!\u0017\u0002`A)Q$a\u0017IA&\u0019\u0011Q\f\u0010\u0003\rQ+\b\u000f\\33\u0011\u0019\tY#\u0004a\u0001}\u0005y!/Z:pYZ,w\t\\8c!\u0006$\b\u000e\u0006\u0004\u0002f\u0005-\u0014Q\u000e\t\u0005;\u0005\u001dd(C\u0002\u0002jy\u0011Q!\u0011:sCfDQa\u0012\bA\u0002!CQ\u0001\u001d\bA\u0002E\u0004"
)
public final class DependencyUtils {
   public static String mergeFileLists(final Seq lists) {
      return DependencyUtils$.MODULE$.mergeFileLists(lists);
   }

   public static void addJarToClasspath(final String localJar, final MutableURLClassLoader loader) {
      DependencyUtils$.MODULE$.addJarToClasspath(localJar, loader);
   }

   public static String resolveGlobPaths(final String paths, final Configuration hadoopConf) {
      return DependencyUtils$.MODULE$.resolveGlobPaths(paths, hadoopConf);
   }

   public static String downloadFile(final String path, final File targetDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      return DependencyUtils$.MODULE$.downloadFile(path, targetDir, sparkConf, hadoopConf);
   }

   public static String downloadFileList(final String fileList, final File targetDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      return DependencyUtils$.MODULE$.downloadFileList(fileList, targetDir, sparkConf, hadoopConf);
   }

   public static void addJarsToClassPath(final String jars, final MutableURLClassLoader loader) {
      DependencyUtils$.MODULE$.addJarsToClassPath(jars, loader);
   }

   public static String resolveAndDownloadJars(final String jars, final String userJar, final SparkConf sparkConf, final Configuration hadoopConf) {
      return DependencyUtils$.MODULE$.resolveAndDownloadJars(jars, userJar, sparkConf, hadoopConf);
   }

   public static Seq resolveMavenDependencies(final boolean packagesTransitive, final String packagesExclusions, final String packages, final String repositories, final String ivyRepoPath, final Option ivySettingsPath) {
      return DependencyUtils$.MODULE$.resolveMavenDependencies(packagesTransitive, packagesExclusions, packages, repositories, ivyRepoPath, ivySettingsPath);
   }

   public static Seq resolveMavenDependencies(final URI uri) {
      return DependencyUtils$.MODULE$.resolveMavenDependencies(uri);
   }

   public static IvyProperties getIvyProperties() {
      return DependencyUtils$.MODULE$.getIvyProperties();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return DependencyUtils$.MODULE$.LogStringContext(sc);
   }
}
