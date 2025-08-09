package org.apache.spark.deploy.history;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import org.apache.spark.util.kvstore.KVIndex;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005d!\u0002\u00180\u0001>J\u0004\u0002\u0003)\u0001\u0005+\u0007I\u0011A)\t\u00115\u0004!\u0011#Q\u0001\nIC\u0001B\u001c\u0001\u0003\u0016\u0004%\ta\u001c\u0005\tm\u0002\u0011\t\u0012)A\u0005a\"Aq\u000f\u0001BK\u0002\u0013\u0005\u0001\u0010C\u0005\u0002\u0006\u0001\u0011\t\u0012)A\u0005s\"Q\u0011q\u0001\u0001\u0003\u0016\u0004%\t!!\u0003\t\u0015\u0005E\u0001A!E!\u0002\u0013\tY\u0001\u0003\u0006\u0002\u0014\u0001\u0011)\u001a!C\u0001\u0003\u0013A!\"!\u0006\u0001\u0005#\u0005\u000b\u0011BA\u0006\u0011%\t9\u0002\u0001BK\u0002\u0013\u0005q\u000eC\u0005\u0002\u001a\u0001\u0011\t\u0012)A\u0005a\"Q\u00111\u0004\u0001\u0003\u0016\u0004%\t!!\b\t\u0015\u0005\u0005\u0002A!E!\u0002\u0013\ty\u0002\u0003\u0006\u0002$\u0001\u0011)\u001a!C\u0001\u0003;A!\"!\n\u0001\u0005#\u0005\u000b\u0011BA\u0010\u0011)\t9\u0003\u0001BK\u0002\u0013\u0005\u0011\u0011\u0006\u0005\u000b\u0003c\u0001!\u0011#Q\u0001\n\u0005-\u0002bBA\u001a\u0001\u0011\u0005\u0011Q\u0007\u0005\n\u0003w\u0002\u0011\u0011!C\u0001\u0003{B\u0011\"!%\u0001#\u0003%\t!a%\t\u0013\u0005\u0015\u0006!%A\u0005\u0002\u0005\u001d\u0006\"CAV\u0001E\u0005I\u0011AAW\u0011%\t\t\fAI\u0001\n\u0003\t\u0019\fC\u0005\u00028\u0002\t\n\u0011\"\u0001\u00024\"I\u0011\u0011\u0018\u0001\u0012\u0002\u0013\u0005\u0011q\u0015\u0005\n\u0003w\u0003\u0011\u0013!C\u0001\u0003{C\u0011\"!1\u0001#\u0003%\t!!0\t\u0013\u0005\r\u0007!%A\u0005\u0002\u0005\u0015\u0007\"CAe\u0001\u0005\u0005I\u0011IAf\u0011%\t\t\u000eAA\u0001\n\u0003\t\u0019\u000eC\u0005\u0002\\\u0002\t\t\u0011\"\u0001\u0002^\"I\u0011\u0011\u001e\u0001\u0002\u0002\u0013\u0005\u00131\u001e\u0005\n\u0003s\u0004\u0011\u0011!C\u0001\u0003wD\u0011\"a@\u0001\u0003\u0003%\tE!\u0001\t\u0013\t\u0015\u0001!!A\u0005B\t\u001d\u0001\"\u0003B\u0005\u0001\u0005\u0005I\u0011\tB\u0006\u0011%\u0011i\u0001AA\u0001\n\u0003\u0012ya\u0002\u0006\u0003\u0014=\n\t\u0011#\u00010\u0005+1\u0011BL\u0018\u0002\u0002#\u0005qFa\u0006\t\u000f\u0005M\u0002\u0006\"\u0001\u00030!I!\u0011\u0002\u0015\u0002\u0002\u0013\u0015#1\u0002\u0005\n\u0005cA\u0013\u0011!CA\u0005gA\u0011Ba\u0012)\u0003\u0003%\tI!\u0013\t\u0013\t]\u0003&!A\u0005\n\te#a\u0002'pO&sgm\u001c\u0006\u0003aE\nq\u0001[5ti>\u0014\u0018P\u0003\u00023g\u00051A-\u001a9m_fT!\u0001N\u001b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y:\u0014AB1qC\u000eDWMC\u00019\u0003\ry'oZ\n\u0005\u0001i\u00025\t\u0005\u0002<}5\tAHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyDH\u0001\u0004B]f\u0014VM\u001a\t\u0003w\u0005K!A\u0011\u001f\u0003\u000fA\u0013x\u000eZ;diB\u0011A)\u0014\b\u0003\u000b.s!A\u0012&\u000e\u0003\u001dS!\u0001S%\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!P\u0005\u0003\u0019r\nq\u0001]1dW\u0006<W-\u0003\u0002O\u001f\na1+\u001a:jC2L'0\u00192mK*\u0011A\nP\u0001\bY><\u0007+\u0019;i+\u0005\u0011\u0006CA*X\u001d\t!V\u000b\u0005\u0002Gy%\u0011a\u000bP\u0001\u0007!J,G-\u001a4\n\u0005aK&AB*ue&twM\u0003\u0002Wy!\u0012\u0011a\u0017\u0016\u00039\u0012\u0004\"!\u00182\u000e\u0003yS!a\u00181\u0002\u000f-48\u000f^8sK*\u0011\u0011mM\u0001\u0005kRLG.\u0003\u0002d=\n91JV%oI\u0016D8&A3\u0011\u0005\u0019\\W\"A4\u000b\u0005!L\u0017\u0001B7fi\u0006T!A\u001b\u001f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002mO\n1q-\u001a;uKJ\f\u0001\u0002\\8h!\u0006$\b\u000eI\u0001\u000eY\u0006\u001cH\u000f\u0015:pG\u0016\u001c8/\u001a3\u0016\u0003A\u0004\"aO9\n\u0005Id$\u0001\u0002'p]\u001eDCaA.uk\u0006)a/\u00197vK\u0006\na.\u0001\bmCN$\bK]8dKN\u001cX\r\u001a\u0011\u0002\u000f1|w\rV=qKV\t\u0011\u0010\u0005\u0002{}:\u00111\u0010`\u0007\u0002_%\u0011QpL\u0001\b\u0019><G+\u001f9f\u0013\ry\u0018\u0011\u0001\u0002\u0006-\u0006dW/Z\u0005\u0004\u0003\u0007a$aC#ok6,'/\u0019;j_:\f\u0001\u0002\\8h)f\u0004X\rI\u0001\u0006CB\u0004\u0018\nZ\u000b\u0003\u0003\u0017\u0001BaOA\u0007%&\u0019\u0011q\u0002\u001f\u0003\r=\u0003H/[8o\u0003\u0019\t\u0007\u000f]%eA\u0005I\u0011\r\u001e;f[B$\u0018\nZ\u0001\u000bCR$X-\u001c9u\u0013\u0012\u0004\u0013\u0001\u00034jY\u0016\u001c\u0016N_3\u0002\u0013\u0019LG.Z*ju\u0016\u0004\u0013!\u00037bgRLe\u000eZ3y+\t\ty\u0002\u0005\u0003<\u0003\u001b\u0001\u0018A\u00037bgRLe\u000eZ3yA\u0005QB.Y:u\u000bZ\fG.^1uK\u00124uN]\"p[B\f7\r^5p]\u0006YB.Y:u\u000bZ\fG.^1uK\u00124uN]\"p[B\f7\r^5p]\u0002\n!\"[:D_6\u0004H.\u001a;f+\t\tY\u0003E\u0002<\u0003[I1!a\f=\u0005\u001d\u0011un\u001c7fC:\f1\"[:D_6\u0004H.\u001a;fA\u00051A(\u001b8jiz\"B#a\u000e\u0002:\u0005m\u0012QHA \u0003\u0003\n\u0019%!\u0012\u0002v\u0005e\u0004CA>\u0001\u0011\u0015\u00016\u00031\u0001S\u0011\u0015q7\u00031\u0001q\u0011\u001598\u00031\u0001z\u0011\u001d\t9a\u0005a\u0001\u0003\u0017Aq!a\u0005\u0014\u0001\u0004\tY\u0001\u0003\u0004\u0002\u0018M\u0001\r\u0001\u001d\u0005\b\u00037\u0019\u0002\u0019AA\u0010Q!\t)%!\u0013\u0002d\u0005\u0015\u0004\u0003BA&\u0003?j!!!\u0014\u000b\u0007)\fyE\u0003\u0003\u0002R\u0005M\u0013\u0001\u00033bi\u0006\u0014\u0017N\u001c3\u000b\t\u0005U\u0013qK\u0001\bU\u0006\u001c7n]8o\u0015\u0011\tI&a\u0017\u0002\u0013\u0019\f7\u000f^3sq6d'BAA/\u0003\r\u0019w.\\\u0005\u0005\u0003C\niEA\bKg>tG)Z:fe&\fG.\u001b>f\u0003%\u0019wN\u001c;f]R\f5o\t\u0002\u0002hA!\u0011\u0011NA:\u001b\t\tYG\u0003\u0003\u0002n\u0005=\u0014\u0001\u00027b]\u001eT!!!\u001d\u0002\t)\fg/Y\u0005\u0004e\u0006-\u0004bBA\u0012'\u0001\u0007\u0011q\u0004\u0015\t\u0003k\nI%a\u0019\u0002f!9\u0011qE\nA\u0002\u0005-\u0012\u0001B2paf$B#a\u000e\u0002\u0000\u0005\u0005\u00151QAC\u0003\u000f\u000bI)a#\u0002\u000e\u0006=\u0005b\u0002)\u0015!\u0003\u0005\rA\u0015\u0005\b]R\u0001\n\u00111\u0001q\u0011\u001d9H\u0003%AA\u0002eD\u0011\"a\u0002\u0015!\u0003\u0005\r!a\u0003\t\u0013\u0005MA\u0003%AA\u0002\u0005-\u0001\u0002CA\f)A\u0005\t\u0019\u00019\t\u0013\u0005mA\u0003%AA\u0002\u0005}\u0001\"CA\u0012)A\u0005\t\u0019AA\u0010\u0011%\t9\u0003\u0006I\u0001\u0002\u0004\tY#\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005U%f\u0001*\u0002\u0018.\u0012\u0011\u0011\u0014\t\u0005\u00037\u000b\t+\u0004\u0002\u0002\u001e*\u0019\u0011qT5\u0002\u0013Ut7\r[3dW\u0016$\u0017\u0002BAR\u0003;\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!++\u0007A\f9*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005=&fA=\u0002\u0018\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA[U\u0011\tY!a&\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%k\u0005q1m\u001c9zI\u0011,g-Y;mi\u00122\u0014AD2paf$C-\u001a4bk2$HeN\u000b\u0003\u0003\u007fSC!a\b\u0002\u0018\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012B\u0014AD2paf$C-\u001a4bk2$H%O\u000b\u0003\u0003\u000fTC!a\u000b\u0002\u0018\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!4\u0011\t\u0005%\u0014qZ\u0005\u00041\u0006-\u0014\u0001\u00049s_\u0012,8\r^!sSRLXCAAk!\rY\u0014q[\u0005\u0004\u00033d$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAp\u0003K\u00042aOAq\u0013\r\t\u0019\u000f\u0010\u0002\u0004\u0003:L\b\"CAtA\u0005\u0005\t\u0019AAk\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u001e\t\u0007\u0003_\f)0a8\u000e\u0005\u0005E(bAAzy\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0018\u0011\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002,\u0005u\b\"CAtE\u0005\u0005\t\u0019AAp\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u00055'1\u0001\u0005\n\u0003O\u001c\u0013\u0011!a\u0001\u0003+\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003+\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u001b\fa!Z9vC2\u001cH\u0003BA\u0016\u0005#A\u0011\"a:'\u0003\u0003\u0005\r!a8\u0002\u000f1{w-\u00138g_B\u00111\u0010K\n\u0006Q\te!Q\u0005\t\u0015\u00057\u0011\tC\u00159z\u0003\u0017\tY\u0001]A\u0010\u0003?\tY#a\u000e\u000e\u0005\tu!b\u0001B\u0010y\u00059!/\u001e8uS6,\u0017\u0002\u0002B\u0012\u0005;\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c8:!\u0011\u00119C!\f\u000e\u0005\t%\"\u0002\u0002B\u0016\u0003_\n!![8\n\u00079\u0013I\u0003\u0006\u0002\u0003\u0016\u0005)\u0011\r\u001d9msR!\u0012q\u0007B\u001b\u0005o\u0011IDa\u000f\u0003>\t}\"\u0011\tB\"\u0005\u000bBQ\u0001U\u0016A\u0002ICQA\\\u0016A\u0002ADQa^\u0016A\u0002eDq!a\u0002,\u0001\u0004\tY\u0001C\u0004\u0002\u0014-\u0002\r!a\u0003\t\r\u0005]1\u00061\u0001q\u0011\u001d\tYb\u000ba\u0001\u0003?Aq!a\t,\u0001\u0004\ty\u0002C\u0004\u0002(-\u0002\r!a\u000b\u0002\u000fUt\u0017\r\u001d9msR!!1\nB*!\u0015Y\u0014Q\u0002B'!EY$q\n*qs\u0006-\u00111\u00029\u0002 \u0005}\u00111F\u0005\u0004\u0005#b$A\u0002+va2,\u0017\bC\u0005\u0003V1\n\t\u00111\u0001\u00028\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tm\u0003\u0003BA5\u0005;JAAa\u0018\u0002l\t1qJ\u00196fGR\u0004"
)
public class LogInfo implements Product, Serializable {
   private final String logPath;
   private final long lastProcessed;
   private final Enumeration.Value logType;
   private final Option appId;
   private final Option attemptId;
   private final long fileSize;
   private final Option lastIndex;
   private final Option lastEvaluatedForCompaction;
   private final boolean isComplete;

   public static Option unapply(final LogInfo x$0) {
      return LogInfo$.MODULE$.unapply(x$0);
   }

   public static LogInfo apply(final String logPath, final long lastProcessed, final Enumeration.Value logType, final Option appId, final Option attemptId, final long fileSize, final Option lastIndex, final Option lastEvaluatedForCompaction, final boolean isComplete) {
      return LogInfo$.MODULE$.apply(logPath, lastProcessed, logType, appId, attemptId, fileSize, lastIndex, lastEvaluatedForCompaction, isComplete);
   }

   public static Function1 tupled() {
      return LogInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return LogInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   @KVIndex
   public String logPath() {
      return this.logPath;
   }

   @KVIndex("lastProcessed")
   public long lastProcessed() {
      return this.lastProcessed;
   }

   public Enumeration.Value logType() {
      return this.logType;
   }

   public Option appId() {
      return this.appId;
   }

   public Option attemptId() {
      return this.attemptId;
   }

   public long fileSize() {
      return this.fileSize;
   }

   public Option lastIndex() {
      return this.lastIndex;
   }

   public Option lastEvaluatedForCompaction() {
      return this.lastEvaluatedForCompaction;
   }

   public boolean isComplete() {
      return this.isComplete;
   }

   public LogInfo copy(final String logPath, final long lastProcessed, final Enumeration.Value logType, final Option appId, final Option attemptId, final long fileSize, final Option lastIndex, final Option lastEvaluatedForCompaction, final boolean isComplete) {
      return new LogInfo(logPath, lastProcessed, logType, appId, attemptId, fileSize, lastIndex, lastEvaluatedForCompaction, isComplete);
   }

   public String copy$default$1() {
      return this.logPath();
   }

   public long copy$default$2() {
      return this.lastProcessed();
   }

   public Enumeration.Value copy$default$3() {
      return this.logType();
   }

   public Option copy$default$4() {
      return this.appId();
   }

   public Option copy$default$5() {
      return this.attemptId();
   }

   public long copy$default$6() {
      return this.fileSize();
   }

   public Option copy$default$7() {
      return this.lastIndex();
   }

   public Option copy$default$8() {
      return this.lastEvaluatedForCompaction();
   }

   public boolean copy$default$9() {
      return this.isComplete();
   }

   public String productPrefix() {
      return "LogInfo";
   }

   public int productArity() {
      return 9;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.logPath();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.lastProcessed());
         }
         case 2 -> {
            return this.logType();
         }
         case 3 -> {
            return this.appId();
         }
         case 4 -> {
            return this.attemptId();
         }
         case 5 -> {
            return BoxesRunTime.boxToLong(this.fileSize());
         }
         case 6 -> {
            return this.lastIndex();
         }
         case 7 -> {
            return this.lastEvaluatedForCompaction();
         }
         case 8 -> {
            return BoxesRunTime.boxToBoolean(this.isComplete());
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
      return x$1 instanceof LogInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "logPath";
         }
         case 1 -> {
            return "lastProcessed";
         }
         case 2 -> {
            return "logType";
         }
         case 3 -> {
            return "appId";
         }
         case 4 -> {
            return "attemptId";
         }
         case 5 -> {
            return "fileSize";
         }
         case 6 -> {
            return "lastIndex";
         }
         case 7 -> {
            return "lastEvaluatedForCompaction";
         }
         case 8 -> {
            return "isComplete";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.logPath()));
      var1 = Statics.mix(var1, Statics.longHash(this.lastProcessed()));
      var1 = Statics.mix(var1, Statics.anyHash(this.logType()));
      var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.attemptId()));
      var1 = Statics.mix(var1, Statics.longHash(this.fileSize()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastIndex()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastEvaluatedForCompaction()));
      var1 = Statics.mix(var1, this.isComplete() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 9);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label99: {
            if (x$1 instanceof LogInfo) {
               LogInfo var4 = (LogInfo)x$1;
               if (this.lastProcessed() == var4.lastProcessed() && this.fileSize() == var4.fileSize() && this.isComplete() == var4.isComplete()) {
                  label92: {
                     String var10000 = this.logPath();
                     String var5 = var4.logPath();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label92;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label92;
                     }

                     Enumeration.Value var11 = this.logType();
                     Enumeration.Value var6 = var4.logType();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label92;
                        }
                     } else if (!var11.equals(var6)) {
                        break label92;
                     }

                     Option var12 = this.appId();
                     Option var7 = var4.appId();
                     if (var12 == null) {
                        if (var7 != null) {
                           break label92;
                        }
                     } else if (!var12.equals(var7)) {
                        break label92;
                     }

                     var12 = this.attemptId();
                     Option var8 = var4.attemptId();
                     if (var12 == null) {
                        if (var8 != null) {
                           break label92;
                        }
                     } else if (!var12.equals(var8)) {
                        break label92;
                     }

                     var12 = this.lastIndex();
                     Option var9 = var4.lastIndex();
                     if (var12 == null) {
                        if (var9 != null) {
                           break label92;
                        }
                     } else if (!var12.equals(var9)) {
                        break label92;
                     }

                     var12 = this.lastEvaluatedForCompaction();
                     Option var10 = var4.lastEvaluatedForCompaction();
                     if (var12 == null) {
                        if (var10 != null) {
                           break label92;
                        }
                     } else if (!var12.equals(var10)) {
                        break label92;
                     }

                     if (var4.canEqual(this)) {
                        break label99;
                     }
                  }
               }
            }

            var16 = false;
            return var16;
         }
      }

      var16 = true;
      return var16;
   }

   public LogInfo(final String logPath, final long lastProcessed, final Enumeration.Value logType, final Option appId, final Option attemptId, final long fileSize, @JsonDeserialize(contentAs = Long.class) final Option lastIndex, @JsonDeserialize(contentAs = Long.class) final Option lastEvaluatedForCompaction, final boolean isComplete) {
      this.logPath = logPath;
      this.lastProcessed = lastProcessed;
      this.logType = logType;
      this.appId = appId;
      this.attemptId = attemptId;
      this.fileSize = fileSize;
      this.lastIndex = lastIndex;
      this.lastEvaluatedForCompaction = lastEvaluatedForCompaction;
      this.isComplete = isComplete;
      Product.$init$(this);
   }
}
