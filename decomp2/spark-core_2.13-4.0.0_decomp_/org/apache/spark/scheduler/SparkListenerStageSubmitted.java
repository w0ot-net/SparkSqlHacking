package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001B\u000e\u001d\u0001\u0016B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\t\u0001BK\u0002\u0013\u0005a\t\u0003\u0005P\u0001\tE\t\u0015!\u0003H\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u001d)\u0006!!A\u0005\u0002YCq!\u0017\u0001\u0012\u0002\u0013\u0005!\fC\u0004f\u0001E\u0005I\u0011\u00014\t\u000f!\u0004\u0011\u0011!C!S\"9\u0001\u000fAA\u0001\n\u0003\t\bbB;\u0001\u0003\u0003%\tA\u001e\u0005\by\u0002\t\t\u0011\"\u0011~\u0011%\tI\u0001AA\u0001\n\u0003\tY\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003CA\u0011\"a\t\u0001\u0003\u0003%\t%!\n\b\u0013\u0005UB$!A\t\u0002\u0005]b\u0001C\u000e\u001d\u0003\u0003E\t!!\u000f\t\rA\u001bB\u0011AA)\u0011%\tybEA\u0001\n\u000b\n\t\u0003C\u0005\u0002TM\t\t\u0011\"!\u0002V!A\u00111L\n\u0012\u0002\u0013\u0005a\rC\u0005\u0002^M\t\t\u0011\"!\u0002`!A\u0011\u0011O\n\u0012\u0002\u0013\u0005a\rC\u0005\u0002tM\t\t\u0011\"\u0003\u0002v\tY2\u000b]1sW2K7\u000f^3oKJ\u001cF/Y4f'V\u0014W.\u001b;uK\u0012T!!\b\u0010\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M)\u0001A\n\u00171gA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"!\f\u0018\u000e\u0003qI!a\f\u000f\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003OEJ!A\r\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013BA\u001e)\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mB\u0013!C:uC\u001e,\u0017J\u001c4p+\u0005\t\u0005CA\u0017C\u0013\t\u0019EDA\u0005Ti\u0006<W-\u00138g_\u0006Q1\u000f^1hK&sgm\u001c\u0011\u0002\u0015A\u0014x\u000e]3si&,7/F\u0001H!\tAU*D\u0001J\u0015\tQ5*\u0001\u0003vi&d'\"\u0001'\u0002\t)\fg/Y\u0005\u0003\u001d&\u0013!\u0002\u0015:pa\u0016\u0014H/[3t\u0003-\u0001(o\u001c9feRLWm\u001d\u0011\u0002\rqJg.\u001b;?)\r\u00116\u000b\u0016\t\u0003[\u0001AQaP\u0003A\u0002\u0005Cq!R\u0003\u0011\u0002\u0003\u0007q)\u0001\u0003d_BLHc\u0001*X1\"9qH\u0002I\u0001\u0002\u0004\t\u0005bB#\u0007!\u0003\u0005\raR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Y&FA!]W\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003%)hn\u00195fG.,GM\u0003\u0002cQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011|&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A4+\u0005\u001dc\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001k!\tYg.D\u0001m\u0015\ti7*\u0001\u0003mC:<\u0017BA8m\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t!\u000f\u0005\u0002(g&\u0011A\u000f\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003oj\u0004\"a\n=\n\u0005eD#aA!os\"91pCA\u0001\u0002\u0004\u0011\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001\u007f!\u0011y\u0018QA<\u000e\u0005\u0005\u0005!bAA\u0002Q\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u001d\u0011\u0011\u0001\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u000e\u0005M\u0001cA\u0014\u0002\u0010%\u0019\u0011\u0011\u0003\u0015\u0003\u000f\t{w\u000e\\3b]\"910DA\u0001\u0002\u00049\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A[A\r\u0011\u001dYh\"!AA\u0002I\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002e\u0006AAo\\*ue&tw\rF\u0001k\u0003\u0019)\u0017/^1mgR!\u0011QBA\u0014\u0011\u001dY\u0018#!AA\u0002]D3\u0001AA\u0016!\u0011\ti#!\r\u000e\u0005\u0005=\"B\u00012\u001f\u0013\u0011\t\u0019$a\f\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u00027M\u0003\u0018M]6MSN$XM\\3s'R\fw-Z*vE6LG\u000f^3e!\ti3cE\u0003\u0014\u0003w\t9\u0005E\u0004\u0002>\u0005\r\u0013i\u0012*\u000e\u0005\u0005}\"bAA!Q\u00059!/\u001e8uS6,\u0017\u0002BA#\u0003\u007f\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\tI%a\u0014\u000e\u0005\u0005-#bAA'\u0017\u0006\u0011\u0011n\\\u0005\u0004{\u0005-CCAA\u001c\u0003\u0015\t\u0007\u000f\u001d7z)\u0015\u0011\u0016qKA-\u0011\u0015yd\u00031\u0001B\u0011\u001d)e\u0003%AA\u0002\u001d\u000bq\"\u00199qYf$C-\u001a4bk2$HEM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t'!\u001c\u0011\u000b\u001d\n\u0019'a\u001a\n\u0007\u0005\u0015\u0004F\u0001\u0004PaRLwN\u001c\t\u0006O\u0005%\u0014iR\u0005\u0004\u0003WB#A\u0002+va2,'\u0007\u0003\u0005\u0002pa\t\t\u00111\u0001S\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0004cA6\u0002z%\u0019\u00111\u00107\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkListenerStageSubmitted implements SparkListenerEvent, Product, Serializable {
   private final StageInfo stageInfo;
   private final Properties properties;

   public static Properties $lessinit$greater$default$2() {
      return SparkListenerStageSubmitted$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final SparkListenerStageSubmitted x$0) {
      return SparkListenerStageSubmitted$.MODULE$.unapply(x$0);
   }

   public static Properties apply$default$2() {
      return SparkListenerStageSubmitted$.MODULE$.apply$default$2();
   }

   public static SparkListenerStageSubmitted apply(final StageInfo stageInfo, final Properties properties) {
      return SparkListenerStageSubmitted$.MODULE$.apply(stageInfo, properties);
   }

   public static Function1 tupled() {
      return SparkListenerStageSubmitted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerStageSubmitted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public StageInfo stageInfo() {
      return this.stageInfo;
   }

   public Properties properties() {
      return this.properties;
   }

   public SparkListenerStageSubmitted copy(final StageInfo stageInfo, final Properties properties) {
      return new SparkListenerStageSubmitted(stageInfo, properties);
   }

   public StageInfo copy$default$1() {
      return this.stageInfo();
   }

   public Properties copy$default$2() {
      return this.properties();
   }

   public String productPrefix() {
      return "SparkListenerStageSubmitted";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.stageInfo();
         }
         case 1 -> {
            return this.properties();
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
      return x$1 instanceof SparkListenerStageSubmitted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageInfo";
         }
         case 1 -> {
            return "properties";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof SparkListenerStageSubmitted) {
               label48: {
                  SparkListenerStageSubmitted var4 = (SparkListenerStageSubmitted)x$1;
                  StageInfo var10000 = this.stageInfo();
                  StageInfo var5 = var4.stageInfo();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Properties var7 = this.properties();
                  Properties var6 = var4.properties();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public SparkListenerStageSubmitted(final StageInfo stageInfo, final Properties properties) {
      this.stageInfo = stageInfo;
      this.properties = properties;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
