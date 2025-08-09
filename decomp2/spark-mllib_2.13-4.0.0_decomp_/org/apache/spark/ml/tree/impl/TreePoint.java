package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import org.apache.spark.ml.tree.Split;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua!B\b\u0011\u0001Ya\u0002\u0002\u0003\u0019\u0001\u0005\u000b\u0007I\u0011A\u0019\t\u0011U\u0002!\u0011!Q\u0001\nIB\u0001B\u000e\u0001\u0003\u0006\u0004%\ta\u000e\u0005\t}\u0001\u0011\t\u0011)A\u0005q!Aq\b\u0001BC\u0002\u0013\u0005\u0011\u0007\u0003\u0005A\u0001\t\u0005\t\u0015!\u00033\u0011\u0015\t\u0005\u0001\"\u0001C\u000f\u0019A\u0005\u0003#\u0001\u0017\u0013\u001a1q\u0002\u0005E\u0001-)CQ!Q\u0005\u0005\u0002ICQaU\u0005\u0005\u0002QCQ!]\u0005\u0005\nIDQa_\u0005\u0005\nqD\u0011\"!\u0002\n\u0003\u0003%I!a\u0002\u0003\u0013Q\u0013X-\u001a)pS:$(BA\t\u0013\u0003\u0011IW\u000e\u001d7\u000b\u0005M!\u0012\u0001\u0002;sK\u0016T!!\u0006\f\u0002\u00055d'BA\f\u0019\u0003\u0015\u0019\b/\u0019:l\u0015\tI\"$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0005\u0019qN]4\u0014\u0007\u0001i2\u0005\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VM\u001a\t\u0003I5r!!J\u0016\u000f\u0005\u0019RS\"A\u0014\u000b\u0005!J\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0001J!\u0001L\u0010\u0002\u000fA\f7m[1hK&\u0011af\f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Y}\tQ\u0001\\1cK2,\u0012A\r\t\u0003=MJ!\u0001N\u0010\u0003\r\u0011{WO\u00197f\u0003\u0019a\u0017MY3mA\u0005q!-\u001b8oK\u00124U-\u0019;ve\u0016\u001cX#\u0001\u001d\u0011\u0007yI4(\u0003\u0002;?\t)\u0011I\u001d:bsB\u0011a\u0004P\u0005\u0003{}\u00111!\u00138u\u0003=\u0011\u0017N\u001c8fI\u001a+\u0017\r^;sKN\u0004\u0013AB<fS\u001eDG/A\u0004xK&<\u0007\u000e\u001e\u0011\u0002\rqJg.\u001b;?)\u0011\u0019UIR$\u0011\u0005\u0011\u0003Q\"\u0001\t\t\u000bA:\u0001\u0019\u0001\u001a\t\u000bY:\u0001\u0019\u0001\u001d\t\u000b}:\u0001\u0019\u0001\u001a\u0002\u0013Q\u0013X-\u001a)pS:$\bC\u0001#\n'\rIQd\u0013\t\u0003\u0019Fk\u0011!\u0014\u0006\u0003\u001d>\u000b!![8\u000b\u0003A\u000bAA[1wC&\u0011a&\u0014\u000b\u0002\u0013\u0006\u00012m\u001c8wKJ$Hk\u001c+sK\u0016\u0014F\t\u0012\u000b\u0005+n#G\u000eE\u0002W3\u000ek\u0011a\u0016\u0006\u00031Z\t1A\u001d3e\u0013\tQvKA\u0002S\t\u0012CQ\u0001X\u0006A\u0002u\u000bQ!\u001b8qkR\u00042AV-_!\ty&-D\u0001a\u0015\t\tG#A\u0004gK\u0006$XO]3\n\u0005\r\u0004'\u0001C%ogR\fgnY3\t\u000b\u0015\\\u0001\u0019\u00014\u0002\rM\u0004H.\u001b;t!\rq\u0012h\u001a\t\u0004=eB\u0007CA5k\u001b\u0005\u0011\u0012BA6\u0013\u0005\u0015\u0019\u0006\u000f\\5u\u0011\u0015i7\u00021\u0001o\u0003!iW\r^1eCR\f\u0007C\u0001#p\u0013\t\u0001\bC\u0001\u000bEK\u000eL7/[8o)J,W-T3uC\u0012\fG/Y\u0001\u0018Y\u0006\u0014W\r\\3e!>Lg\u000e\u001e+p)J,W\rU8j]R$BaQ:vs\")A\u000f\u0004a\u0001=\u0006A\u0011N\\:uC:\u001cW\rC\u0003w\u0019\u0001\u0007q/\u0001\u0006uQJ,7\u000f[8mIN\u00042AH\u001dy!\rq\u0012H\r\u0005\u0006u2\u0001\r\u0001O\u0001\rM\u0016\fG/\u001e:f\u0003JLG/_\u0001\bM&tGMQ5o)\u001dYTp`A\u0001\u0003\u0007AQA`\u0007A\u0002m\nABZ3biV\u0014X-\u00138eKbDQ\u0001^\u0007A\u0002yCQA_\u0007A\u0002mBQA^\u0007A\u0002a\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0003\u0011\t\u0005-\u0011\u0011C\u0007\u0003\u0003\u001bQ1!a\u0004P\u0003\u0011a\u0017M\\4\n\t\u0005M\u0011Q\u0002\u0002\u0007\u001f\nTWm\u0019;"
)
public class TreePoint implements Serializable {
   private final double label;
   private final int[] binnedFeatures;
   private final double weight;

   public static RDD convertToTreeRDD(final RDD input, final Split[][] splits, final DecisionTreeMetadata metadata) {
      return TreePoint$.MODULE$.convertToTreeRDD(input, splits, metadata);
   }

   public double label() {
      return this.label;
   }

   public int[] binnedFeatures() {
      return this.binnedFeatures;
   }

   public double weight() {
      return this.weight;
   }

   public TreePoint(final double label, final int[] binnedFeatures, final double weight) {
      this.label = label;
      this.binnedFeatures = binnedFeatures;
      this.weight = weight;
   }
}
