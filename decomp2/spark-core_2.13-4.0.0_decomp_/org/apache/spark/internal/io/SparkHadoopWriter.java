package org.apache.spark.internal.io;

import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ErAB\u0003\u0007\u0011\u0003Q\u0001C\u0002\u0004\u0013\r!\u0005!b\u0005\u0005\u0006=\u0005!\t\u0001\t\u0005\u0006C\u0005!\tA\t\u0005\u0006\u001b\u0006!IAT\u0001\u0012'B\f'o\u001b%bI>|\u0007o\u0016:ji\u0016\u0014(BA\u0004\t\u0003\tIwN\u0003\u0002\n\u0015\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h!\t\t\u0012!D\u0001\u0007\u0005E\u0019\u0006/\u0019:l\u0011\u0006$wn\u001c9Xe&$XM]\n\u0004\u0003QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\r\u0005\u0002\u001c95\t\u0001\"\u0003\u0002\u001e\u0011\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003A\tQa\u001e:ji\u0016,2a\t$3)\r!3\b\u0013\u000b\u0003K!\u0002\"!\u0006\u0014\n\u0005\u001d2\"\u0001B+oSRDq!K\u0002\u0002\u0002\u0003\u000f!&\u0001\u0006fm&$WM\\2fIE\u00022a\u000b\u00181\u001b\u0005a#BA\u0017\u0017\u0003\u001d\u0011XM\u001a7fGRL!a\f\u0017\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"!\r\u001a\r\u0001\u0011)1g\u0001b\u0001i\t\ta+\u0005\u00026qA\u0011QCN\u0005\u0003oY\u0011qAT8uQ&tw\r\u0005\u0002\u0016s%\u0011!H\u0006\u0002\u0004\u0003:L\b\"\u0002\u001f\u0004\u0001\u0004i\u0014a\u0001:eIB\u0019a\b\u0011\"\u000e\u0003}R!\u0001\u0010\u0006\n\u0005\u0005{$a\u0001*E\tB!QcQ#1\u0013\t!eC\u0001\u0004UkBdWM\r\t\u0003c\u0019#QaR\u0002C\u0002Q\u0012\u0011a\u0013\u0005\u0006\u0013\u000e\u0001\rAS\u0001\u0007G>tg-[4\u0011\tEYU\tM\u0005\u0003\u0019\u001a\u0011Q\u0003S1e_>\u0004xK]5uK\u000e{gNZ5h+RLG.A\u0006fq\u0016\u001cW\u000f^3UCN\\WcA(tSRi\u0001K\u001b9u}\u0006\u001d\u00111BA\b\u00033!\"!U3\u0011\u0005I\u0013gBA*a\u001d\t!vL\u0004\u0002V=:\u0011a+\u0018\b\u0003/rs!\u0001W.\u000e\u0003eS!AW\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005y\u0011BA\u0007\u000f\u0013\tYA\"\u0003\u0002\n\u0015%\u0011q\u0001C\u0005\u0003C\u001a\t!CR5mK\u000e{W.\\5u!J|Go\\2pY&\u00111\r\u001a\u0002\u0012)\u0006\u001c8nQ8n[&$X*Z:tC\u001e,'BA1\u0007\u0011\u001d1G!!AA\u0004\u001d\f!\"\u001a<jI\u0016t7-\u001a\u00133!\rYc\u0006\u001b\t\u0003c%$Qa\r\u0003C\u0002QBQa\u001b\u0003A\u00021\fqaY8oi\u0016DH\u000f\u0005\u0002n]6\t!\"\u0003\u0002p\u0015\tYA+Y:l\u0007>tG/\u001a=u\u0011\u0015IE\u00011\u0001r!\u0011\t2J\u001d5\u0011\u0005E\u001aH!B$\u0005\u0005\u0004!\u0004\"B;\u0005\u0001\u00041\u0018\u0001\u00046pER\u0013\u0018mY6fe&#\u0007CA<|\u001d\tA\u0018\u0010\u0005\u0002Y-%\u0011!PF\u0001\u0007!J,G-\u001a4\n\u0005ql(AB*ue&twM\u0003\u0002{-!1q\u0010\u0002a\u0001\u0003\u0003\t1bY8n[&$(j\u001c2JIB\u0019Q#a\u0001\n\u0007\u0005\u0015aCA\u0002J]RDq!!\u0003\u0005\u0001\u0004\t\t!\u0001\tta\u0006\u00148\u000eU1si&$\u0018n\u001c8JI\"9\u0011Q\u0002\u0003A\u0002\u0005\u0005\u0011AE:qCJ\\\u0017\t\u001e;f[B$h*^7cKJDq!!\u0005\u0005\u0001\u0004\t\u0019\"A\u0005d_6l\u0017\u000e\u001e;feB\u0019\u0011#!\u0006\n\u0007\u0005]aA\u0001\nGS2,7i\\7nSR\u0004&o\u001c;pG>d\u0007bBA\u000e\t\u0001\u0007\u0011QD\u0001\tSR,'/\u0019;peB1\u0011qDA\u0015\u0003_qA!!\t\u0002&9\u0019\u0001,a\t\n\u0003]I1!a\n\u0017\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u000b\u0002.\tA\u0011\n^3sCR|'OC\u0002\u0002(Y\u0001B!F\"sQ\u0002"
)
public final class SparkHadoopWriter {
   public static void write(final RDD rdd, final HadoopWriteConfigUtil config, final ClassTag evidence$1) {
      SparkHadoopWriter$.MODULE$.write(rdd, config, evidence$1);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SparkHadoopWriter$.MODULE$.LogStringContext(sc);
   }
}
