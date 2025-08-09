package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.storage.BlockUpdatedInfo;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005y!)1\t\u0001C\u0001\t\"9q\tAA\u0001\n\u0003A\u0005b\u0002&\u0001#\u0003%\ta\u0013\u0005\b-\u0002\t\t\u0011\"\u0011X\u0011\u001d\u0001\u0007!!A\u0005\u0002\u0005Dq!\u001a\u0001\u0002\u0002\u0013\u0005a\rC\u0004m\u0001\u0005\u0005I\u0011I7\t\u000fQ\u0004\u0011\u0011!C\u0001k\"9!\u0010AA\u0001\n\u0003Z\bbB?\u0001\u0003\u0003%\tE \u0005\t\u007f\u0002\t\t\u0011\"\u0011\u0002\u0002!I\u00111\u0001\u0001\u0002\u0002\u0013\u0005\u0013QA\u0004\n\u0003+9\u0012\u0011!E\u0001\u0003/1\u0001BF\f\u0002\u0002#\u0005\u0011\u0011\u0004\u0005\u0007\u0007B!\t!!\r\t\u0011}\u0004\u0012\u0011!C#\u0003\u0003A\u0011\"a\r\u0011\u0003\u0003%\t)!\u000e\t\u0013\u0005e\u0002#!A\u0005\u0002\u0006m\u0002\"CA$!\u0005\u0005I\u0011BA%\u0005e\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\ncwnY6Va\u0012\fG/\u001a3\u000b\u0005aI\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7\u0001A\n\u0006\u0001\u0005:3F\f\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!JS\"A\f\n\u0005):\"AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"A\t\u0017\n\u00055\u001a#a\u0002)s_\u0012,8\r\u001e\t\u0003_]r!\u0001M\u001b\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005Mz\u0012A\u0002\u001fs_>$h(C\u0001%\u0013\t14%A\u0004qC\u000e\\\u0017mZ3\n\u0005aJ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001c$\u0003A\u0011Gn\\2l+B$\u0017\r^3e\u0013:4w.F\u0001=!\ti\u0004)D\u0001?\u0015\ty\u0014$A\u0004ti>\u0014\u0018mZ3\n\u0005\u0005s$\u0001\u0005\"m_\u000e\\W\u000b\u001d3bi\u0016$\u0017J\u001c4p\u0003E\u0011Gn\\2l+B$\u0017\r^3e\u0013:4w\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u00153\u0005C\u0001\u0015\u0001\u0011\u0015Q4\u00011\u0001=\u0003\u0011\u0019w\u000e]=\u0015\u0005\u0015K\u0005b\u0002\u001e\u0005!\u0003\u0005\r\u0001P\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005a%F\u0001\u001fNW\u0005q\u0005CA(U\u001b\u0005\u0001&BA)S\u0003%)hn\u00195fG.,GM\u0003\u0002TG\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005U\u0003&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u0017\t\u00033zk\u0011A\u0017\u0006\u00037r\u000bA\u0001\\1oO*\tQ,\u0001\u0003kCZ\f\u0017BA0[\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t!\r\u0005\u0002#G&\u0011Am\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003O*\u0004\"A\t5\n\u0005%\u001c#aA!os\"91\u000eCA\u0001\u0002\u0004\u0011\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001o!\ry'oZ\u0007\u0002a*\u0011\u0011oI\u0001\u000bG>dG.Z2uS>t\u0017BA:q\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005YL\bC\u0001\u0012x\u0013\tA8EA\u0004C_>dW-\u00198\t\u000f-T\u0011\u0011!a\u0001O\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tAF\u0010C\u0004l\u0017\u0005\u0005\t\u0019\u00012\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AY\u0001\ti>\u001cFO]5oOR\t\u0001,\u0001\u0004fcV\fGn\u001d\u000b\u0004m\u0006\u001d\u0001bB6\u000f\u0003\u0003\u0005\ra\u001a\u0015\u0004\u0001\u0005-\u0001\u0003BA\u0007\u0003#i!!a\u0004\u000b\u0005MK\u0012\u0002BA\n\u0003\u001f\u0011A\u0002R3wK2|\u0007/\u001a:Ba&\f\u0011d\u00159be.d\u0015n\u001d;f]\u0016\u0014(\t\\8dWV\u0003H-\u0019;fIB\u0011\u0001\u0006E\n\u0006!\u0005m\u0011q\u0005\t\u0007\u0003;\t\u0019\u0003P#\u000e\u0005\u0005}!bAA\u0011G\u00059!/\u001e8uS6,\u0017\u0002BA\u0013\u0003?\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\tI#a\f\u000e\u0005\u0005-\"bAA\u00179\u0006\u0011\u0011n\\\u0005\u0004q\u0005-BCAA\f\u0003\u0015\t\u0007\u000f\u001d7z)\r)\u0015q\u0007\u0005\u0006uM\u0001\r\u0001P\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti$a\u0011\u0011\t\t\ny\u0004P\u0005\u0004\u0003\u0003\u001a#AB(qi&|g\u000e\u0003\u0005\u0002FQ\t\t\u00111\u0001F\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0017\u00022!WA'\u0013\r\tyE\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkListenerBlockUpdated implements SparkListenerEvent, Product, Serializable {
   private final BlockUpdatedInfo blockUpdatedInfo;

   public static Option unapply(final SparkListenerBlockUpdated x$0) {
      return SparkListenerBlockUpdated$.MODULE$.unapply(x$0);
   }

   public static SparkListenerBlockUpdated apply(final BlockUpdatedInfo blockUpdatedInfo) {
      return SparkListenerBlockUpdated$.MODULE$.apply(blockUpdatedInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerBlockUpdated$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerBlockUpdated$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public BlockUpdatedInfo blockUpdatedInfo() {
      return this.blockUpdatedInfo;
   }

   public SparkListenerBlockUpdated copy(final BlockUpdatedInfo blockUpdatedInfo) {
      return new SparkListenerBlockUpdated(blockUpdatedInfo);
   }

   public BlockUpdatedInfo copy$default$1() {
      return this.blockUpdatedInfo();
   }

   public String productPrefix() {
      return "SparkListenerBlockUpdated";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.blockUpdatedInfo();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SparkListenerBlockUpdated;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockUpdatedInfo";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof SparkListenerBlockUpdated) {
               label40: {
                  SparkListenerBlockUpdated var4 = (SparkListenerBlockUpdated)x$1;
                  BlockUpdatedInfo var10000 = this.blockUpdatedInfo();
                  BlockUpdatedInfo var5 = var4.blockUpdatedInfo();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public SparkListenerBlockUpdated(final BlockUpdatedInfo blockUpdatedInfo) {
      this.blockUpdatedInfo = blockUpdatedInfo;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
