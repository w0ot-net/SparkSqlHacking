package org.apache.spark.sql.internal;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.MapFunction;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t-v!\u0002\n\u0014\u0011\u0003qb!\u0002\u0011\u0014\u0011\u0003\t\u0003\"\u0002\u001b\u0002\t\u0003)\u0004\"\u0002\u001c\u0002\t\u00039\u0004\"\u0002\u001c\u0002\t\u0003!\u0006\"\u00025\u0002\t\u0003I\u0007\"\u00025\u0002\t\u0003!\bbBA\u0002\u0003\u0011\u0005\u0011Q\u0001\u0005\b\u0003g\tA\u0011AA\u001b\u0011\u001d\t\u0019$\u0001C\u0001\u0003\u001bBq!!\u0019\u0002\t\u0003\t\u0019\u0007C\u0004\u0002z\u0005!\t!a\u001f\t\u000f\u0005e\u0015\u0001\"\u0001\u0002\u001c\"9\u00111X\u0001\u0005\u0002\u0005u\u0006bBAw\u0003\u0011\u0005\u0011q\u001e\u0005\b\u0005g\tA\u0011\u0001B\u001b\u0011\u001d\u0011i&\u0001C\u0001\u0005?B\u0011Ba#\u0002\u0003\u0003%IA!$\u0002\u0017U#e)\u00113baR|'o\u001d\u0006\u0003)U\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003-]\t1a]9m\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<7\u0001\u0001\t\u0003?\u0005i\u0011a\u0005\u0002\f+\u00123\u0015\tZ1qi>\u00148oE\u0002\u0002E!\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012a!\u00118z%\u00164\u0007CA\u00152\u001d\tQsF\u0004\u0002,]5\tAF\u0003\u0002.;\u00051AH]8pizJ\u0011!J\u0005\u0003a\u0011\nq\u0001]1dW\u0006<W-\u0003\u00023g\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001\u0007J\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003y\taC\u001a7bi6\u000b\u0007\u000fV8NCB\u0004\u0016M\u001d;ji&|gn]\u000b\u0004q\u0005cECA\u001dO!\u0011\u0019#\b\u0010&\n\u0005m\"#!\u0003$v]\u000e$\u0018n\u001c82!\rIShP\u0005\u0003}M\u0012\u0001\"\u0013;fe\u0006$xN\u001d\t\u0003\u0001\u0006c\u0001\u0001B\u0003C\u0007\t\u00071IA\u0001W#\t!u\t\u0005\u0002$\u000b&\u0011a\t\n\u0002\b\u001d>$\b.\u001b8h!\t\u0019\u0003*\u0003\u0002JI\t\u0019\u0011I\\=\u0011\u0007%j4\n\u0005\u0002A\u0019\u0012)Qj\u0001b\u0001\u0007\n\tQ\u000bC\u0003P\u0007\u0001\u0007\u0001+A\u0001g!\u0011\u0019#hP)\u0011\u0007%\u00126*\u0003\u0002Tg\ta\u0011\n^3sC\ndWm\u00148dKV\u0019Q+\u0017/\u0015\u0005Yk\u0006\u0003B\u0012;/j\u00032!K\u001fY!\t\u0001\u0015\fB\u0003C\t\t\u00071\tE\u0002*{m\u0003\"\u0001\u0011/\u0005\u000b5#!\u0019A\"\t\u000b=#\u0001\u0019\u00010\u0011\t}3\u0007lW\u0007\u0002A*\u0011\u0011MY\u0001\tMVt7\r^5p]*\u00111\rZ\u0001\u0005U\u00064\u0018M\u0003\u0002f/\u0005\u0019\u0011\r]5\n\u0005\u001d\u0004'a\u0004$mCRl\u0015\r\u001d$v]\u000e$\u0018n\u001c8\u0002%5\f\u0007\u000fV8NCB\u0004\u0016M\u001d;ji&|gn]\u000b\u0004U:\fHCA6s!\u0011\u0019#\b\\8\u0011\u0007%jT\u000e\u0005\u0002A]\u0012)!)\u0002b\u0001\u0007B\u0019\u0011&\u00109\u0011\u0005\u0001\u000bH!B'\u0006\u0005\u0004\u0019\u0005\"B(\u0006\u0001\u0004\u0019\b\u0003B\u0012;[B,2!^=})\t1X\u0010\u0005\u0003$u]T\bcA\u0015>qB\u0011\u0001)\u001f\u0003\u0006\u0005\u001a\u0011\ra\u0011\t\u0004SuZ\bC\u0001!}\t\u0015ieA1\u0001D\u0011\u0015ye\u00011\u0001\u007f!\u0011yv\u0010_>\n\u0007\u0005\u0005\u0001MA\u0006NCB4UO\\2uS>t\u0017!C7baZ\u000bG.^3t+\u0019\t9!a\u0004\u0002$QA\u0011\u0011BA\u000e\u0003K\ty\u0003\u0005\u0004$u\u0005-\u00111\u0003\t\u0005Su\ni\u0001E\u0002A\u0003\u001f!a!!\u0005\b\u0005\u0004\u0019%AA%W!\u0011IS(!\u0006\u0011\u000b\r\n9bR$\n\u0007\u0005eAE\u0001\u0004UkBdWM\r\u0005\b\u0003;9\u0001\u0019AA\u0010\u0003\u00151h)\u001e8d!\u0019\u0019#(!\u0004\u0002\"A\u0019\u0001)a\t\u0005\u000b\t;!\u0019A\"\t\u000f\u0005\u001dr\u00011\u0001\u0002*\u0005Q\u0011N^%t'R\u0014Xo\u0019;\u0011\u0007\r\nY#C\u0002\u0002.\u0011\u0012qAQ8pY\u0016\fg\u000eC\u0004\u00022\u001d\u0001\r!!\u000b\u0002\u0013YL5o\u0015;sk\u000e$\u0018!\u00074pe\u0016\f7\r\u001b+p\r>\u0014X-Y2i!\u0006\u0014H/\u001b;j_:,B!a\u000e\u0002@Q!\u0011\u0011HA%!\u0019\u0019#(a\u000f\u0002DA!\u0011&PA\u001f!\r\u0001\u0015q\b\u0003\u0007\u0003\u0003B!\u0019A\"\u0003\u0003Q\u00032aIA#\u0013\r\t9\u0005\n\u0002\u0005+:LG\u000f\u0003\u0004P\u0011\u0001\u0007\u00111\n\t\u0007Gi\ni$a\u0011\u0016\t\u0005=\u0013q\u000b\u000b\u0005\u0003#\nI\u0006\u0005\u0004$u\u0005M\u00131\t\t\u0005Su\n)\u0006E\u0002A\u0003/\"a!!\u0011\n\u0005\u0004\u0019\u0005BB(\n\u0001\u0004\tY\u0006E\u0003`\u0003;\n)&C\u0002\u0002`\u0001\u0014qBR8sK\u0006\u001c\u0007NR;oGRLwN\\\u0001 M>\u0014X-Y2i!\u0006\u0014H/\u001b;j_:$v.T1q!\u0006\u0014H/\u001b;j_:\u001cXCBA3\u0003[\n\u0019\b\u0006\u0003\u0002h\u0005U\u0004CB\u0012;\u0003S\ny\u0007\u0005\u0003*{\u0005-\u0004c\u0001!\u0002n\u0011)!I\u0003b\u0001\u0007B!\u0011&PA9!\r\u0001\u00151\u000f\u0003\u0006\u001b*\u0011\ra\u0011\u0005\u0007\u001f*\u0001\r!a\u001e\u0011\r\rR\u0014\u0011NA\"\u0003EIG/\u001a:bE2,wJ\\2f)>\u001cV-]\u000b\u0007\u0003{\n\u0019)a$\u0015\t\u0005}\u00141\u0013\t\u0007Gi\n\t)a\"\u0011\u0007\u0001\u000b\u0019\t\u0002\u0004\u0002\u0006.\u0011\ra\u0011\u0002\u0002\u0003B)\u0011&!#\u0002\u000e&\u0019\u00111R\u001a\u0003\u0007M+\u0017\u000fE\u0002A\u0003\u001f#a!!%\f\u0005\u0004\u0019%!\u0001\"\t\r=[\u0001\u0019AAK!\u0019\u0019#(!!\u0002\u0018B!\u0011FUAG\u0003ai\u0017\r]$s_V\u00048\u000fV8GY\u0006$X*\u00199He>,\bo]\u000b\t\u0003;\u000b9+a,\u00026R!\u0011qTA\\!%\u0019\u0013\u0011UAS\u0003W\u000b\t,C\u0002\u0002$\u0012\u0012\u0011BR;oGRLwN\u001c\u001a\u0011\u0007\u0001\u000b9\u000b\u0002\u0004\u0002*2\u0011\ra\u0011\u0002\u0002\u0017B!\u0011&PAW!\r\u0001\u0015q\u0016\u0003\u0006\u00052\u0011\ra\u0011\t\u0005Su\n\u0019\fE\u0002A\u0003k#Q!\u0014\u0007C\u0002\rCaa\u0014\u0007A\u0002\u0005e\u0006#C\u0012\u0002\"\u0006\u0015\u00161VAZ\u0003\u0011j\u0017\r]$s_V\u00048oV5uQN#\u0018\r^3U_\u001ac\u0017\r^'ba^KG\u000f[*uCR,WCCA`\u0003\u0013\fy-a8\u0002hR!\u0011\u0011YAu!-\u0019\u00131YAd\u0003\u0017\f\t.a9\n\u0007\u0005\u0015GEA\u0005Gk:\u001cG/[8ogA\u0019\u0001)!3\u0005\r\u0005%VB1\u0001D!\u0011IS(!4\u0011\u0007\u0001\u000by\rB\u0003C\u001b\t\u00071\t\u0005\u0004\u0002T\u0006e\u0017Q\\\u0007\u0003\u0003+T1!a6\u0016\u0003%\u0019HO]3b[&tw-\u0003\u0003\u0002\\\u0006U'AC$s_V\u00048\u000b^1uKB\u0019\u0001)a8\u0005\r\u0005\u0005XB1\u0001D\u0005\u0005\u0019\u0006\u0003B\u0015>\u0003K\u00042\u0001QAt\t\u0015iUB1\u0001D\u0011\u0019yU\u00021\u0001\u0002lBY1%a1\u0002H\u0006-\u0017\u0011[As\u0003]\u0019wn\u0012:pkB<\u0016\u000e\u001e5NCB\u0004X\r\u001a,bYV,7/\u0006\b\u0002r\u0006](q\u0003B\u000f\u0005\u0017\tiPa\u0001\u0015\u0011\u0005M(q\u0002B\u0010\u0005W\u00012bIAb\u0003k\fI0a@\u0003\bA\u0019\u0001)a>\u0005\r\u0005%fB1\u0001D!\u0011IS(a?\u0011\u0007\u0001\u000bi\u0010\u0002\u0004\u0002\u00129\u0011\ra\u0011\t\u0005Su\u0012\t\u0001E\u0002A\u0005\u0007!aA!\u0002\u000f\u0005\u0004\u0019%AA%V!\u0011I#K!\u0003\u0011\u0007\u0001\u0013Y\u0001\u0002\u0004\u0003\u000e9\u0011\ra\u0011\u0002\u0002%\"1qJ\u0004a\u0001\u0005#\u00012bIAb\u0003k\u0014\u0019B!\u0007\u0003\bA!\u0011&\u0010B\u000b!\r\u0001%q\u0003\u0003\u0006\u0005:\u0011\ra\u0011\t\u0005Su\u0012Y\u0002E\u0002A\u0005;!Q!\u0014\bC\u0002\rCqA!\t\u000f\u0001\u0004\u0011\u0019#\u0001\tmK\u001a$h+\u00197vK6\u000b\u0007OR;oGB)1E!\n\u0003*%\u0019!q\u0005\u0013\u0003\r=\u0003H/[8o!\u0019\u0019#(a?\u0003\u0016!9!Q\u0006\bA\u0002\t=\u0012!\u0005:jO\"$h+\u00197vK6\u000b\u0007OR;oGB)1E!\n\u00032A11E\u000fB\u0001\u00057\tQD\u001a7bi6\u000b\u0007o\u0012:pkB\u001cx+\u001b;i\u001b\u0006\u0004\b/\u001a3WC2,Xm]\u000b\u000b\u0005o\u0011iDa\u0011\u0003T\t%CC\u0002B\u001d\u0005\u0017\u0012)\u0006E\u0005$\u0003C\u0013YDa\u0010\u0003FA\u0019\u0001I!\u0010\u0005\r\u0005%vB1\u0001D!\u0011ISH!\u0011\u0011\u0007\u0001\u0013\u0019\u0005\u0002\u0004\u0002\u0012=\u0011\ra\u0011\t\u0005SI\u00139\u0005E\u0002A\u0005\u0013\"aA!\u0004\u0010\u0005\u0004\u0019\u0005BB(\u0010\u0001\u0004\u0011i\u0005E\u0005$\u0003C\u0013YDa\u0014\u0003FA!\u0011&\u0010B)!\r\u0001%1\u000b\u0003\u0006\u0005>\u0011\ra\u0011\u0005\b\u0005/z\u0001\u0019\u0001B-\u000311\u0018\r\\;f\u001b\u0006\u0004h)\u001e8d!\u0015\u0019#Q\u0005B.!\u0019\u0019#H!\u0011\u0003R\u00051c\r\\1u\u001b\u0006\u0004xI]8vaN<\u0016\u000e\u001e5Ti\u0006$XmV5uQ6\u000b\u0007\u000f]3e-\u0006dW/Z:\u0016\u0019\t\u0005$q\rB7\u0005\u0007\u0013\u0019H!\u001f\u0015\r\t\r$1\u0010BC!-\u0019\u00131\u0019B3\u0005S\u0012yG!\u001e\u0011\u0007\u0001\u00139\u0007\u0002\u0004\u0002*B\u0011\ra\u0011\t\u0005Su\u0012Y\u0007E\u0002A\u0005[\"a!!\u0005\u0011\u0005\u0004\u0019\u0005CBAj\u00033\u0014\t\bE\u0002A\u0005g\"a!!9\u0011\u0005\u0004\u0019\u0005\u0003B\u0015>\u0005o\u00022\u0001\u0011B=\t\u0015i\u0005C1\u0001D\u0011\u0019y\u0005\u00031\u0001\u0003~AY1%a1\u0003f\t}$q\u000eB;!\u0011ISH!!\u0011\u0007\u0001\u0013\u0019\tB\u0003C!\t\u00071\tC\u0004\u0003XA\u0001\rAa\"\u0011\u000b\r\u0012)C!#\u0011\r\rR$1\u000eBA\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011y\t\u0005\u0003\u0003\u0012\neUB\u0001BJ\u0015\u0011\u0011)Ja&\u0002\t1\fgn\u001a\u0006\u0002G&!!1\u0014BJ\u0005\u0019y%M[3di\":\u0011Aa(\u0003&\n\u001d\u0006cA\u0012\u0003\"&\u0019!1\u0015\u0013\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001)\u000f\u0001\u0011yJ!*\u0003(\u0002"
)
public final class UDFAdaptors {
   public static Function3 flatMapGroupsWithStateWithMappedValues(final Function3 f, final Option valueMapFunc) {
      return UDFAdaptors$.MODULE$.flatMapGroupsWithStateWithMappedValues(f, valueMapFunc);
   }

   public static Function2 flatMapGroupsWithMappedValues(final Function2 f, final Option valueMapFunc) {
      return UDFAdaptors$.MODULE$.flatMapGroupsWithMappedValues(f, valueMapFunc);
   }

   public static Function3 coGroupWithMappedValues(final Function3 f, final Option leftValueMapFunc, final Option rightValueMapFunc) {
      return UDFAdaptors$.MODULE$.coGroupWithMappedValues(f, leftValueMapFunc, rightValueMapFunc);
   }

   public static Function3 mapGroupsWithStateToFlatMapWithState(final Function3 f) {
      return UDFAdaptors$.MODULE$.mapGroupsWithStateToFlatMapWithState(f);
   }

   public static Function2 mapGroupsToFlatMapGroups(final Function2 f) {
      return UDFAdaptors$.MODULE$.mapGroupsToFlatMapGroups(f);
   }

   public static Function1 iterableOnceToSeq(final Function1 f) {
      return UDFAdaptors$.MODULE$.iterableOnceToSeq(f);
   }

   public static Function1 foreachPartitionToMapPartitions(final Function1 f) {
      return UDFAdaptors$.MODULE$.foreachPartitionToMapPartitions(f);
   }

   public static Function1 foreachToForeachPartition(final ForeachFunction f) {
      return UDFAdaptors$.MODULE$.foreachToForeachPartition(f);
   }

   public static Function1 foreachToForeachPartition(final Function1 f) {
      return UDFAdaptors$.MODULE$.foreachToForeachPartition(f);
   }

   public static Function1 mapValues(final Function1 vFunc, final boolean ivIsStruct, final boolean vIsStruct) {
      return UDFAdaptors$.MODULE$.mapValues(vFunc, ivIsStruct, vIsStruct);
   }

   public static Function1 mapToMapPartitions(final MapFunction f) {
      return UDFAdaptors$.MODULE$.mapToMapPartitions(f);
   }

   public static Function1 mapToMapPartitions(final Function1 f) {
      return UDFAdaptors$.MODULE$.mapToMapPartitions(f);
   }

   public static Function1 flatMapToMapPartitions(final FlatMapFunction f) {
      return UDFAdaptors$.MODULE$.flatMapToMapPartitions(f);
   }

   public static Function1 flatMapToMapPartitions(final Function1 f) {
      return UDFAdaptors$.MODULE$.flatMapToMapPartitions(f);
   }
}
