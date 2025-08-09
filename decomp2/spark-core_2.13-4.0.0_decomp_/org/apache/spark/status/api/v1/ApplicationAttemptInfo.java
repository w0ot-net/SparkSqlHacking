package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@JsonIgnoreProperties(
   value = {"startTimeEpoch", "endTimeEpoch", "lastUpdatedEpoch"},
   allowGetters = true
)
@ScalaSignature(
   bytes = "\u0006\u0005\teb\u0001\u0002\u00192\u0001zB\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\tC\u0002\u0011\t\u0012)A\u0005-\"A!\r\u0001BK\u0002\u0013\u00051\r\u0003\u0005m\u0001\tE\t\u0015!\u0003e\u0011!i\u0007A!f\u0001\n\u0003\u0019\u0007\u0002\u00038\u0001\u0005#\u0005\u000b\u0011\u00023\t\u0011=\u0004!Q3A\u0005\u0002\rD\u0001\u0002\u001d\u0001\u0003\u0012\u0003\u0006I\u0001\u001a\u0005\tc\u0002\u0011)\u001a!C\u0001e\"Aa\u000f\u0001B\tB\u0003%1\u000f\u0003\u0005x\u0001\tU\r\u0011\"\u0001y\u0011!I\bA!E!\u0002\u0013I\u0006\u0002\u0003>\u0001\u0005+\u0007I\u0011A>\t\u0011}\u0004!\u0011#Q\u0001\nqD\u0011\"!\u0001\u0001\u0005+\u0007I\u0011\u0001=\t\u0013\u0005\r\u0001A!E!\u0002\u0013I\u0006\u0002CA\u0003\u0001\u0011\u0005q'a\u0002\t\r\u0005u\u0001\u0001\"\u0001s\u0011\u0019\ty\u0002\u0001C\u0001e\"1\u0011\u0011\u0005\u0001\u0005\u0002ID\u0011\"a\t\u0001\u0003\u0003%\t!!\n\t\u0013\u0005]\u0002!%A\u0005\u0002\u0005e\u0002\"CA(\u0001E\u0005I\u0011AA)\u0011%\t)\u0006AI\u0001\n\u0003\t\t\u0006C\u0005\u0002X\u0001\t\n\u0011\"\u0001\u0002R!I\u0011\u0011\f\u0001\u0012\u0002\u0013\u0005\u00111\f\u0005\n\u0003?\u0002\u0011\u0013!C\u0001\u0003CB\u0011\"!\u001a\u0001#\u0003%\t!a\u001a\t\u0013\u0005-\u0004!%A\u0005\u0002\u0005\u0005\u0004\"CA7\u0001\u0005\u0005I\u0011IA8\u0011%\tY\bAA\u0001\n\u0003\ti\bC\u0005\u0002\u0006\u0002\t\t\u0011\"\u0001\u0002\b\"I\u00111\u0013\u0001\u0002\u0002\u0013\u0005\u0013Q\u0013\u0005\n\u0003G\u0003\u0011\u0011!C\u0001\u0003KC\u0011\"!+\u0001\u0003\u0003%\t%a+\t\u0013\u0005=\u0006!!A\u0005B\u0005E\u0006\"CAZ\u0001\u0005\u0005I\u0011IA[\u0011%\t9\fAA\u0001\n\u0003\nIlB\u0005\u0002jF\n\t\u0011#\u0001\u0002l\u001aA\u0001'MA\u0001\u0012\u0003\ti\u000fC\u0004\u0002\u0006!\"\tA!\u0002\t\u0013\u0005M\u0006&!A\u0005F\u0005U\u0006\"\u0003B\u0004Q\u0005\u0005I\u0011\u0011B\u0005\u0011%\u0011Y\u0002KI\u0001\n\u0003\t9\u0007C\u0005\u0003\u001e!\n\t\u0011\"!\u0003 !Q!Q\u0006\u0015\u0012\u0002\u0013\u0005q'a\u001a\t\u0013\t=\u0002&!A\u0005\n\tE\"AF!qa2L7-\u0019;j_:\fE\u000f^3naRLeNZ8\u000b\u0005I\u001a\u0014A\u0001<2\u0015\t!T'A\u0002ba&T!AN\u001c\u0002\rM$\u0018\r^;t\u0015\tA\u0014(A\u0003ta\u0006\u00148N\u0003\u0002;w\u00051\u0011\r]1dQ\u0016T\u0011\u0001P\u0001\u0004_J<7\u0001A\n\u0005\u0001}*\u0005\n\u0005\u0002A\u00076\t\u0011IC\u0001C\u0003\u0015\u00198-\u00197b\u0013\t!\u0015I\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0001\u001aK!aR!\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011*\u0015\b\u0003\u0015>s!a\u0013(\u000e\u00031S!!T\u001f\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0015B\u0001)B\u0003\u001d\u0001\u0018mY6bO\u0016L!AU*\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005A\u000b\u0015!C1ui\u0016l\u0007\u000f^%e+\u00051\u0006c\u0001!X3&\u0011\u0001,\u0011\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005isfBA.]!\tY\u0015)\u0003\u0002^\u0003\u00061\u0001K]3eK\u001aL!a\u00181\u0003\rM#(/\u001b8h\u0015\ti\u0016)\u0001\u0006biR,W\u000e\u001d;JI\u0002\n\u0011b\u001d;beR$\u0016.\\3\u0016\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\tU$\u0018\u000e\u001c\u0006\u0002S\u0006!!.\u0019<b\u0013\tYgM\u0001\u0003ECR,\u0017AC:uCJ$H+[7fA\u00059QM\u001c3US6,\u0017\u0001C3oIRKW.\u001a\u0011\u0002\u00171\f7\u000f^+qI\u0006$X\rZ\u0001\rY\u0006\u001cH/\u00169eCR,G\rI\u0001\tIV\u0014\u0018\r^5p]V\t1\u000f\u0005\u0002Ai&\u0011Q/\u0011\u0002\u0005\u0019>tw-A\u0005ekJ\fG/[8oA\u0005I1\u000f]1sWV\u001bXM]\u000b\u00023\u0006Q1\u000f]1sWV\u001bXM\u001d\u0011\u0002\u0013\r|W\u000e\u001d7fi\u0016$W#\u0001?\u0011\u0005\u0001k\u0018B\u0001@B\u0005\u001d\u0011un\u001c7fC:\f!bY8na2,G/\u001a3!\u0003=\t\u0007\u000f]*qCJ\\g+\u001a:tS>t\u0017\u0001E1qaN\u0003\u0018M]6WKJ\u001c\u0018n\u001c8!\u0003\u0019a\u0014N\\5u}Q\u0011\u0012\u0011BA\u0007\u0003\u001f\t\t\"a\u0005\u0002\u0016\u0005]\u0011\u0011DA\u000e!\r\tY\u0001A\u0007\u0002c!)A+\u0005a\u0001-\")!-\u0005a\u0001I\")Q.\u0005a\u0001I\")q.\u0005a\u0001I\")\u0011/\u0005a\u0001g\")q/\u0005a\u00013\"9!0\u0005I\u0001\u0002\u0004a\bBBA\u0001#\u0001\u0007\u0011,A\thKR\u001cF/\u0019:u)&lW-\u00129pG\"\fqbZ3u\u000b:$G+[7f\u000bB|7\r[\u0001\u0014O\u0016$H*Y:u+B$\u0017\r^3e\u000bB|7\r[\u0001\u0005G>\u0004\u0018\u0010\u0006\n\u0002\n\u0005\u001d\u0012\u0011FA\u0016\u0003[\ty#!\r\u00024\u0005U\u0002b\u0002+\u0016!\u0003\u0005\rA\u0016\u0005\bEV\u0001\n\u00111\u0001e\u0011\u001diW\u0003%AA\u0002\u0011Dqa\\\u000b\u0011\u0002\u0003\u0007A\rC\u0004r+A\u0005\t\u0019A:\t\u000f],\u0002\u0013!a\u00013\"9!0\u0006I\u0001\u0002\u0004a\b\u0002CA\u0001+A\u0005\t\u0019A-\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\b\u0016\u0004-\u0006u2FAA !\u0011\t\t%a\u0013\u000e\u0005\u0005\r#\u0002BA#\u0003\u000f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%\u0013)\u0001\u0006b]:|G/\u0019;j_:LA!!\u0014\u0002D\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u000b\u0016\u0004I\u0006u\u0012AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*\"!!\u0018+\u0007M\fi$\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0005\r$fA-\u0002>\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012:TCAA5U\ra\u0018QH\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\u000f\t\u0005\u0003g\nI(\u0004\u0002\u0002v)\u0019\u0011q\u000f5\u0002\t1\fgnZ\u0005\u0004?\u0006U\u0014\u0001\u00049s_\u0012,8\r^!sSRLXCAA@!\r\u0001\u0015\u0011Q\u0005\u0004\u0003\u0007\u000b%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAE\u0003\u001f\u00032\u0001QAF\u0013\r\ti)\u0011\u0002\u0004\u0003:L\b\"CAIA\u0005\u0005\t\u0019AA@\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0013\t\u0007\u00033\u000by*!#\u000e\u0005\u0005m%bAAO\u0003\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0005\u00161\u0014\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002}\u0003OC\u0011\"!%#\u0003\u0003\u0005\r!!#\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003c\ni\u000bC\u0005\u0002\u0012\u000e\n\t\u00111\u0001\u0002\u0000\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0000\u0005AAo\\*ue&tw\r\u0006\u0002\u0002r\u00051Q-];bYN$2\u0001`A^\u0011%\t\tJJA\u0001\u0002\u0004\tI\tK\u0006\u0001\u0003\u007f\u000b).a6\u0002f\u0006\u001d\b\u0003BAa\u0003#l!!a1\u000b\t\u0005%\u0013Q\u0019\u0006\u0005\u0003\u000f\fI-A\u0004kC\u000e\\7o\u001c8\u000b\t\u0005-\u0017QZ\u0001\nM\u0006\u001cH/\u001a:y[2T!!a4\u0002\u0007\r|W.\u0003\u0003\u0002T\u0006\r'\u0001\u0006&t_:LuM\\8sKB\u0013x\u000e]3si&,7/A\u0003wC2,X\r\f\u0004\u0002Z\u0006u\u0017\u0011]\u0011\u0003\u00037\fab\u001d;beR$\u0016.\\3Fa>\u001c\u0007.\t\u0002\u0002`\u0006aQM\u001c3US6,W\t]8dQ\u0006\u0012\u00111]\u0001\u0011Y\u0006\u001cH/\u00169eCR,G-\u00129pG\"\fA\"\u00197m_^<U\r\u001e;feNL\u0012!A\u0001\u0017\u0003B\u0004H.[2bi&|g.\u0011;uK6\u0004H/\u00138g_B\u0019\u00111\u0002\u0015\u0014\u000b!\ny/a?\u0011\u001d\u0005E\u0018q\u001f,eI\u0012\u001c\u0018\f`-\u0002\n5\u0011\u00111\u001f\u0006\u0004\u0003k\f\u0015a\u0002:v]RLW.Z\u0005\u0005\u0003s\f\u0019PA\tBEN$(/Y2u\rVt7\r^5p]b\u0002B!!@\u0003\u00045\u0011\u0011q \u0006\u0004\u0005\u0003A\u0017AA5p\u0013\r\u0011\u0016q \u000b\u0003\u0003W\fQ!\u00199qYf$\"#!\u0003\u0003\f\t5!q\u0002B\t\u0005'\u0011)Ba\u0006\u0003\u001a!)Ak\u000ba\u0001-\")!m\u000ba\u0001I\")Qn\u000ba\u0001I\")qn\u000ba\u0001I\")\u0011o\u000ba\u0001g\")qo\u000ba\u00013\"9!p\u000bI\u0001\u0002\u0004a\bBBA\u0001W\u0001\u0007\u0011,A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00138\u0003\u001d)h.\u00199qYf$BA!\t\u0003*A!\u0001i\u0016B\u0012!-\u0001%Q\u0005,eI\u0012\u001c\u0018\f`-\n\u0007\t\u001d\u0012I\u0001\u0004UkBdW\r\u000f\u0005\n\u0005Wi\u0013\u0011!a\u0001\u0003\u0013\t1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%o\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u0007\t\u0005\u0003g\u0012)$\u0003\u0003\u00038\u0005U$AB(cU\u0016\u001cG\u000f"
)
public class ApplicationAttemptInfo implements Product, Serializable {
   private final Option attemptId;
   private final Date startTime;
   private final Date endTime;
   private final Date lastUpdated;
   private final long duration;
   private final String sparkUser;
   private final boolean completed;
   private final String appSparkVersion;

   public static Option unapply(final ApplicationAttemptInfo x$0) {
      return ApplicationAttemptInfo$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$7() {
      return ApplicationAttemptInfo$.MODULE$.apply$default$7();
   }

   public static ApplicationAttemptInfo apply(final Option attemptId, final Date startTime, final Date endTime, final Date lastUpdated, final long duration, final String sparkUser, final boolean completed, final String appSparkVersion) {
      return ApplicationAttemptInfo$.MODULE$.apply(attemptId, startTime, endTime, lastUpdated, duration, sparkUser, completed, appSparkVersion);
   }

   public static Function1 tupled() {
      return ApplicationAttemptInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ApplicationAttemptInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option attemptId() {
      return this.attemptId;
   }

   public Date startTime() {
      return this.startTime;
   }

   public Date endTime() {
      return this.endTime;
   }

   public Date lastUpdated() {
      return this.lastUpdated;
   }

   public long duration() {
      return this.duration;
   }

   public String sparkUser() {
      return this.sparkUser;
   }

   public boolean completed() {
      return this.completed;
   }

   public String appSparkVersion() {
      return this.appSparkVersion;
   }

   public long getStartTimeEpoch() {
      return this.startTime().getTime();
   }

   public long getEndTimeEpoch() {
      return this.endTime().getTime();
   }

   public long getLastUpdatedEpoch() {
      return this.lastUpdated().getTime();
   }

   public ApplicationAttemptInfo copy(final Option attemptId, final Date startTime, final Date endTime, final Date lastUpdated, final long duration, final String sparkUser, final boolean completed, final String appSparkVersion) {
      return new ApplicationAttemptInfo(attemptId, startTime, endTime, lastUpdated, duration, sparkUser, completed, appSparkVersion);
   }

   public Option copy$default$1() {
      return this.attemptId();
   }

   public Date copy$default$2() {
      return this.startTime();
   }

   public Date copy$default$3() {
      return this.endTime();
   }

   public Date copy$default$4() {
      return this.lastUpdated();
   }

   public long copy$default$5() {
      return this.duration();
   }

   public String copy$default$6() {
      return this.sparkUser();
   }

   public boolean copy$default$7() {
      return this.completed();
   }

   public String copy$default$8() {
      return this.appSparkVersion();
   }

   public String productPrefix() {
      return "ApplicationAttemptInfo";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.attemptId();
         }
         case 1 -> {
            return this.startTime();
         }
         case 2 -> {
            return this.endTime();
         }
         case 3 -> {
            return this.lastUpdated();
         }
         case 4 -> {
            return BoxesRunTime.boxToLong(this.duration());
         }
         case 5 -> {
            return this.sparkUser();
         }
         case 6 -> {
            return BoxesRunTime.boxToBoolean(this.completed());
         }
         case 7 -> {
            return this.appSparkVersion();
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
      return x$1 instanceof ApplicationAttemptInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "attemptId";
         }
         case 1 -> {
            return "startTime";
         }
         case 2 -> {
            return "endTime";
         }
         case 3 -> {
            return "lastUpdated";
         }
         case 4 -> {
            return "duration";
         }
         case 5 -> {
            return "sparkUser";
         }
         case 6 -> {
            return "completed";
         }
         case 7 -> {
            return "appSparkVersion";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.attemptId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.startTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.endTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastUpdated()));
      var1 = Statics.mix(var1, Statics.longHash(this.duration()));
      var1 = Statics.mix(var1, Statics.anyHash(this.sparkUser()));
      var1 = Statics.mix(var1, this.completed() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.appSparkVersion()));
      return Statics.finalizeHash(var1, 8);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof ApplicationAttemptInfo) {
               ApplicationAttemptInfo var4 = (ApplicationAttemptInfo)x$1;
               if (this.duration() == var4.duration() && this.completed() == var4.completed()) {
                  label88: {
                     Option var10000 = this.attemptId();
                     Option var5 = var4.attemptId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label88;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label88;
                     }

                     Date var11 = this.startTime();
                     Date var6 = var4.startTime();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label88;
                        }
                     } else if (!var11.equals(var6)) {
                        break label88;
                     }

                     var11 = this.endTime();
                     Date var7 = var4.endTime();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label88;
                        }
                     } else if (!var11.equals(var7)) {
                        break label88;
                     }

                     var11 = this.lastUpdated();
                     Date var8 = var4.lastUpdated();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label88;
                        }
                     } else if (!var11.equals(var8)) {
                        break label88;
                     }

                     String var14 = this.sparkUser();
                     String var9 = var4.sparkUser();
                     if (var14 == null) {
                        if (var9 != null) {
                           break label88;
                        }
                     } else if (!var14.equals(var9)) {
                        break label88;
                     }

                     var14 = this.appSparkVersion();
                     String var10 = var4.appSparkVersion();
                     if (var14 == null) {
                        if (var10 != null) {
                           break label88;
                        }
                     } else if (!var14.equals(var10)) {
                        break label88;
                     }

                     if (var4.canEqual(this)) {
                        break label95;
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

   public ApplicationAttemptInfo(final Option attemptId, final Date startTime, final Date endTime, final Date lastUpdated, final long duration, final String sparkUser, final boolean completed, final String appSparkVersion) {
      this.attemptId = attemptId;
      this.startTime = startTime;
      this.endTime = endTime;
      this.lastUpdated = lastUpdated;
      this.duration = duration;
      this.sparkUser = sparkUser;
      this.completed = completed;
      this.appSparkVersion = appSparkVersion;
      Product.$init$(this);
   }
}
