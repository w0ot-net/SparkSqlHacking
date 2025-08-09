package org.apache.spark.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005usAB\u0007\u000f\u0011\u0003\u0001bC\u0002\u0004\u0019\u001d!\u0005\u0001#\u0007\u0005\u0006M\u0005!\t\u0001\u000b\u0005\u0006S\u0005!\tA\u000b\u0005\u0006U\u0006!\ta\u001b\u0005\u0006a\u0006!I!\u001d\u0005\u0006y\u0006!I! \u0005\b\u00033\tA\u0011AA\u000e\u0011%\t\t$\u0001b\u0001\n\u0013\t\u0019\u0004\u0003\u0005\u0002D\u0005\u0001\u000b\u0011BA\u001b\u0011%\t)%\u0001b\u0001\n\u0013\t\u0019\u0004\u0003\u0005\u0002H\u0005\u0001\u000b\u0011BA\u001b\u0011\u001d\tI%\u0001C\u0001\u0003\u0017\nQ\u0002S1e_>\u0004hiU+uS2\u001c(BA\b\u0011\u0003\u0011)H/\u001b7\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u0004\"aF\u0001\u000e\u00039\u0011Q\u0002S1e_>\u0004hiU+uS2\u001c8cA\u0001\u001bAA\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t1\u0011I\\=SK\u001a\u0004\"!\t\u0013\u000e\u0003\tR!a\t\t\u0002\u0011%tG/\u001a:oC2L!!\n\u0012\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0017\u0003U\u0001\u0018M]1mY\u0016dG*[:u\u0019\u0016\fgMR5mKN$\u0012b\u000b$M\u001f^c\u0016m\u00195\u0011\u00071\"tG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001gJ\u0001\u0007yI|w\u000e\u001e \n\u0003uI!a\r\u000f\u0002\u000fA\f7m[1hK&\u0011QG\u000e\u0002\u0004'\u0016\f(BA\u001a\u001d!\u0011Y\u0002H\u000f\"\n\u0005eb\"A\u0002+va2,'\u0007\u0005\u0002<\u00016\tAH\u0003\u0002>}\u0005\u0011am\u001d\u0006\u0003\u007fI\ta\u0001[1e_>\u0004\u0018BA!=\u0005\u0011\u0001\u0016\r\u001e5\u0011\u00071\"4\t\u0005\u0002<\t&\u0011Q\t\u0010\u0002\u000b\r&dWm\u0015;biV\u001c\b\"B$\u0004\u0001\u0004A\u0015AA:d!\tI%*D\u0001\u0011\u0013\tY\u0005C\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0003N\u0007\u0001\u0007a*A\u0003qCRD7\u000fE\u0002-iiBQ\u0001U\u0002A\u0002E\u000b!\u0002[1e_>\u00048i\u001c8g!\t\u0011V+D\u0001T\u0015\t!f(\u0001\u0003d_:4\u0017B\u0001,T\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\")\u0001l\u0001a\u00013\u00061a-\u001b7uKJ\u0004\"a\u000f.\n\u0005mc$A\u0003)bi\"4\u0015\u000e\u001c;fe\")Ql\u0001a\u0001=\u0006\u0011\u0012n\u001a8pe\u0016l\u0015n]:j]\u001e4\u0015\u000e\\3t!\tYr,\u0003\u0002a9\t9!i\\8mK\u0006t\u0007\"\u00022\u0004\u0001\u0004q\u0016AD5h]>\u0014X\rT8dC2LG/\u001f\u0005\u0006I\u000e\u0001\r!Z\u0001\u0015a\u0006\u0014\u0018\r\u001c7fY&\u001cX\u000e\u00165sKNDw\u000e\u001c3\u0011\u0005m1\u0017BA4\u001d\u0005\rIe\u000e\u001e\u0005\u0006S\u000e\u0001\r!Z\u0001\u000fa\u0006\u0014\u0018\r\u001c7fY&\u001cX.T1y\u0003%a\u0017n\u001d;GS2,7\u000f\u0006\u0003,Y:|\u0007\"B7\u0005\u0001\u0004Q\u0014\u0001\u00029bi\"DQ\u0001\u0015\u0003A\u0002ECQ\u0001\u0017\u0003A\u0002e\u000bQ\u0004]1sC2dW\r\u001c'jgRdU-\u00194GS2,7/\u00138uKJt\u0017\r\u001c\u000b\u000bWI\u001cH/\u001e<ysj\\\b\"B$\u0006\u0001\u0004A\u0005\"B'\u0006\u0001\u0004q\u0005\"\u0002)\u0006\u0001\u0004\t\u0006\"\u0002-\u0006\u0001\u0004I\u0006\"B<\u0006\u0001\u0004q\u0016aC5t%>|G\u000fT3wK2DQ!X\u0003A\u0002yCQAY\u0003A\u0002yCQ\u0001Z\u0003A\u0002\u0015DQ![\u0003A\u0002\u0015\fQ\u0002\\5ti2+\u0017M\u001a$jY\u0016\u001cH#\u0005\"\u007f\u007f\u0006\u0005\u00111AA\u0007\u0003\u001f\t\t\"!\u0006\u0002\u0018!)QN\u0002a\u0001u!)\u0001K\u0002a\u0001#\")\u0001L\u0002a\u00013\"9\u0011Q\u0001\u0004A\u0002\u0005\u001d\u0011AC2p]R,\u0007\u0010^(qiB!1$!\u0003I\u0013\r\tY\u0001\b\u0002\u0007\u001fB$\u0018n\u001c8\t\u000bu3\u0001\u0019\u00010\t\u000b\t4\u0001\u0019\u00010\t\r\u0005Ma\u00011\u0001_\u0003)I7OU8piB\u000bG\u000f\u001b\u0005\u0006I\u001a\u0001\r!\u001a\u0005\u0006S\u001a\u0001\r!Z\u0001\u0018g\"|W\u000f\u001c3GS2$XM](viB\u000bG\u000f\u001b(b[\u0016$2AXA\u000f\u0011\u001d\tyb\u0002a\u0001\u0003C\t\u0001\u0002]1uQ:\u000bW.\u001a\t\u0005\u0003G\tYC\u0004\u0003\u0002&\u0005\u001d\u0002C\u0001\u0018\u001d\u0013\r\tI\u0003H\u0001\u0007!J,G-\u001a4\n\t\u00055\u0012q\u0006\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005%B$\u0001\u0006v]\u0012,'o]2pe\u0016,\"!!\u000e\u0011\t\u0005]\u0012qH\u0007\u0003\u0003sQA!a\u000f\u0002>\u0005AQ.\u0019;dQ&twM\u0003\u0002\u00109%!\u0011\u0011IA\u001d\u0005\u0015\u0011VmZ3y\u0003-)h\u000eZ3sg\u000e|'/\u001a\u0011\u0002\u001bUtG-\u001a:tG>\u0014X-\u00128e\u00039)h\u000eZ3sg\u000e|'/Z#oI\u0002\n1c\u001d5pk2$g)\u001b7uKJ|U\u000f\u001e)bi\"$2AXA'\u0011\u0019iG\u00021\u0001\u0002\"!\u001aA\"!\u0015\u0011\t\u0005M\u0013\u0011L\u0007\u0003\u0003+R1!a\u0016\u001d\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00037\n)FA\u0004uC&d'/Z2"
)
public final class HadoopFSUtils {
   public static boolean shouldFilterOutPath(final String path) {
      return HadoopFSUtils$.MODULE$.shouldFilterOutPath(path);
   }

   public static boolean shouldFilterOutPathName(final String pathName) {
      return HadoopFSUtils$.MODULE$.shouldFilterOutPathName(pathName);
   }

   public static Seq listFiles(final Path path, final Configuration hadoopConf, final PathFilter filter) {
      return HadoopFSUtils$.MODULE$.listFiles(path, hadoopConf, filter);
   }

   public static Seq parallelListLeafFiles(final SparkContext sc, final Seq paths, final Configuration hadoopConf, final PathFilter filter, final boolean ignoreMissingFiles, final boolean ignoreLocality, final int parallelismThreshold, final int parallelismMax) {
      return HadoopFSUtils$.MODULE$.parallelListLeafFiles(sc, paths, hadoopConf, filter, ignoreMissingFiles, ignoreLocality, parallelismThreshold, parallelismMax);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return HadoopFSUtils$.MODULE$.LogStringContext(sc);
   }
}
