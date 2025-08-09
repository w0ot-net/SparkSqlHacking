package org.apache.spark.status.api.v1;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ug\u0001\u0002\u0015*\u0001ZB\u0001\u0002\u0014\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t-\u0002\u0011\t\u0012)A\u0005\u001d\"Aq\u000b\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005Y\u0001\tE\t\u0015!\u0003O\u0011!I\u0006A!f\u0001\n\u0003Q\u0006\u0002C1\u0001\u0005#\u0005\u000b\u0011B.\t\u0011\t\u0004!Q3A\u0005\u0002iC\u0001b\u0019\u0001\u0003\u0012\u0003\u0006Ia\u0017\u0005\tI\u0002\u0011)\u001a!C\u00015\"AQ\r\u0001B\tB\u0003%1\f\u0003\u0005g\u0001\tU\r\u0011\"\u0001[\u0011!9\u0007A!E!\u0002\u0013Y\u0006\u0002\u00035\u0001\u0005+\u0007I\u0011A5\t\u0011Q\u0004!\u0011#Q\u0001\n)Da!\u001e\u0001\u0005\u0002=2\b\u0002C@\u0001\u0003\u0003%\t!!\u0001\t\u0013\u0005E\u0001!%A\u0005\u0002\u0005M\u0001\"CA\u0015\u0001E\u0005I\u0011AA\n\u0011%\tY\u0003AI\u0001\n\u0003\ti\u0003C\u0005\u00022\u0001\t\n\u0011\"\u0001\u0002.!I\u00111\u0007\u0001\u0012\u0002\u0013\u0005\u0011Q\u0006\u0005\n\u0003k\u0001\u0011\u0013!C\u0001\u0003[A\u0011\"a\u000e\u0001#\u0003%\t!!\u000f\t\u0013\u0005u\u0002!!A\u0005B\u0005}\u0002\"CA(\u0001\u0005\u0005I\u0011AA)\u0011%\t\u0019\u0006AA\u0001\n\u0003\t)\u0006C\u0005\u0002b\u0001\t\t\u0011\"\u0011\u0002d!I\u00111\u000e\u0001\u0002\u0002\u0013\u0005\u0011Q\u000e\u0005\n\u0003o\u0002\u0011\u0011!C!\u0003sB\u0011\"! \u0001\u0003\u0003%\t%a \t\u0013\u0005\u0005\u0005!!A\u0005B\u0005\r\u0005\"CAC\u0001\u0005\u0005I\u0011IAD\u000f%\tY)KA\u0001\u0012\u0003\tiI\u0002\u0005)S\u0005\u0005\t\u0012AAH\u0011\u0019)(\u0005\"\u0001\u0002(\"I\u0011\u0011\u0011\u0012\u0002\u0002\u0013\u0015\u00131\u0011\u0005\n\u0003S\u0013\u0013\u0011!CA\u0003WC\u0011\"a/#\u0003\u0003%\t)!0\t\u0013\u0005-'%!A\u0005\n\u00055'aD!qa2L7-\u0019;j_:LeNZ8\u000b\u0005)Z\u0013A\u0001<2\u0015\taS&A\u0002ba&T!AL\u0018\u0002\rM$\u0018\r^;t\u0015\t\u0001\u0014'A\u0003ta\u0006\u00148N\u0003\u00023g\u00051\u0011\r]1dQ\u0016T\u0011\u0001N\u0001\u0004_J<7\u0001A\n\u0005\u0001]j\u0004\t\u0005\u00029w5\t\u0011HC\u0001;\u0003\u0015\u00198-\u00197b\u0013\ta\u0014H\u0001\u0004B]f\u0014VM\u001a\t\u0003qyJ!aP\u001d\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011)\u0013\b\u0003\u0005\u001es!a\u0011$\u000e\u0003\u0011S!!R\u001b\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0014B\u0001%:\u0003\u001d\u0001\u0018mY6bO\u0016L!AS&\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005!K\u0014AA5e+\u0005q\u0005CA(T\u001d\t\u0001\u0016\u000b\u0005\u0002Ds%\u0011!+O\u0001\u0007!J,G-\u001a4\n\u0005Q+&AB*ue&twM\u0003\u0002Ss\u0005\u0019\u0011\u000e\u001a\u0011\u0002\t9\fW.Z\u0001\u0006]\u0006lW\rI\u0001\rG>\u0014Xm]$sC:$X\rZ\u000b\u00027B\u0019\u0001\b\u00180\n\u0005uK$AB(qi&|g\u000e\u0005\u00029?&\u0011\u0001-\u000f\u0002\u0004\u0013:$\u0018!D2pe\u0016\u001cxI]1oi\u0016$\u0007%\u0001\u0005nCb\u001cuN]3t\u0003%i\u0017\r_\"pe\u0016\u001c\b%\u0001\td_J,7\u000fU3s\u000bb,7-\u001e;pe\u0006\t2m\u001c:fgB+'/\u0012=fGV$xN\u001d\u0011\u0002'5,Wn\u001c:z!\u0016\u0014X\t_3dkR|'/\u0014\"\u0002)5,Wn\u001c:z!\u0016\u0014X\t_3dkR|'/\u0014\"!\u0003!\tG\u000f^3naR\u001cX#\u00016\u0011\u0007-t\u0007/D\u0001m\u0015\ti\u0017(\u0001\u0006d_2dWm\u0019;j_:L!a\u001c7\u0003\u0007M+\u0017\u000f\u0005\u0002re6\t\u0011&\u0003\u0002tS\t1\u0012\t\u001d9mS\u000e\fG/[8o\u0003R$X-\u001c9u\u0013:4w.A\u0005biR,W\u000e\u001d;tA\u00051A(\u001b8jiz\"\u0002b\u001e=zundXP \t\u0003c\u0002AQ\u0001T\bA\u00029CQaV\bA\u00029CQ!W\bA\u0002mCQAY\bA\u0002mCQ\u0001Z\bA\u0002mCQAZ\bA\u0002mCQ\u0001[\bA\u0002)\fAaY8qsRyq/a\u0001\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006\u0003\u001b\ty\u0001C\u0004M!A\u0005\t\u0019\u0001(\t\u000f]\u0003\u0002\u0013!a\u0001\u001d\"9\u0011\f\u0005I\u0001\u0002\u0004Y\u0006b\u00022\u0011!\u0003\u0005\ra\u0017\u0005\bIB\u0001\n\u00111\u0001\\\u0011\u001d1\u0007\u0003%AA\u0002mCq\u0001\u001b\t\u0011\u0002\u0003\u0007!.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005U!f\u0001(\u0002\u0018-\u0012\u0011\u0011\u0004\t\u0005\u00037\t)#\u0004\u0002\u0002\u001e)!\u0011qDA\u0011\u0003%)hn\u00195fG.,GMC\u0002\u0002$e\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9#!\b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011q\u0006\u0016\u00047\u0006]\u0011AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY\nabY8qs\u0012\"WMZ1vYR$s'\u0006\u0002\u0002<)\u001a!.a\u0006\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u0005\u0005\u0003\u0002D\u00055SBAA#\u0015\u0011\t9%!\u0013\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0017\nAA[1wC&\u0019A+!\u0012\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003y\u000ba\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002X\u0005u\u0003c\u0001\u001d\u0002Z%\u0019\u00111L\u001d\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002`i\t\t\u00111\u0001_\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\r\t\u0006W\u0006\u001d\u0014qK\u0005\u0004\u0003Sb'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u001c\u0002vA\u0019\u0001(!\u001d\n\u0007\u0005M\u0014HA\u0004C_>dW-\u00198\t\u0013\u0005}C$!AA\u0002\u0005]\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0011\u0002|!A\u0011qL\u000f\u0002\u0002\u0003\u0007a,\u0001\u0005iCND7i\u001c3f)\u0005q\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u0005\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002p\u0005%\u0005\"CA0A\u0005\u0005\t\u0019AA,\u0003=\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|\u0007CA9#'\u0015\u0011\u0013\u0011SAO!1\t\u0019*!'O\u001dn[6l\u00176x\u001b\t\t)JC\u0002\u0002\u0018f\nqA];oi&lW-\u0003\u0003\u0002\u001c\u0006U%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ooA!\u0011qTAS\u001b\t\t\tK\u0003\u0003\u0002$\u0006%\u0013AA5p\u0013\rQ\u0015\u0011\u0015\u000b\u0003\u0003\u001b\u000bQ!\u00199qYf$rb^AW\u0003_\u000b\t,a-\u00026\u0006]\u0016\u0011\u0018\u0005\u0006\u0019\u0016\u0002\rA\u0014\u0005\u0006/\u0016\u0002\rA\u0014\u0005\u00063\u0016\u0002\ra\u0017\u0005\u0006E\u0016\u0002\ra\u0017\u0005\u0006I\u0016\u0002\ra\u0017\u0005\u0006M\u0016\u0002\ra\u0017\u0005\u0006Q\u0016\u0002\rA[\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty,a2\u0011\tab\u0016\u0011\u0019\t\u000bq\u0005\rgJT.\\7nS\u0017bAAcs\t1A+\u001e9mK^B\u0001\"!3'\u0003\u0003\u0005\ra^\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAh!\u0011\t\u0019%!5\n\t\u0005M\u0017Q\t\u0002\u0007\u001f\nTWm\u0019;"
)
public class ApplicationInfo implements Product, Serializable {
   private final String id;
   private final String name;
   private final Option coresGranted;
   private final Option maxCores;
   private final Option coresPerExecutor;
   private final Option memoryPerExecutorMB;
   private final Seq attempts;

   public static Option unapply(final ApplicationInfo x$0) {
      return ApplicationInfo$.MODULE$.unapply(x$0);
   }

   public static ApplicationInfo apply(final String id, final String name, final Option coresGranted, final Option maxCores, final Option coresPerExecutor, final Option memoryPerExecutorMB, final Seq attempts) {
      return ApplicationInfo$.MODULE$.apply(id, name, coresGranted, maxCores, coresPerExecutor, memoryPerExecutorMB, attempts);
   }

   public static Function1 tupled() {
      return ApplicationInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ApplicationInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public Option coresGranted() {
      return this.coresGranted;
   }

   public Option maxCores() {
      return this.maxCores;
   }

   public Option coresPerExecutor() {
      return this.coresPerExecutor;
   }

   public Option memoryPerExecutorMB() {
      return this.memoryPerExecutorMB;
   }

   public Seq attempts() {
      return this.attempts;
   }

   public ApplicationInfo copy(final String id, final String name, final Option coresGranted, final Option maxCores, final Option coresPerExecutor, final Option memoryPerExecutorMB, final Seq attempts) {
      return new ApplicationInfo(id, name, coresGranted, maxCores, coresPerExecutor, memoryPerExecutorMB, attempts);
   }

   public String copy$default$1() {
      return this.id();
   }

   public String copy$default$2() {
      return this.name();
   }

   public Option copy$default$3() {
      return this.coresGranted();
   }

   public Option copy$default$4() {
      return this.maxCores();
   }

   public Option copy$default$5() {
      return this.coresPerExecutor();
   }

   public Option copy$default$6() {
      return this.memoryPerExecutorMB();
   }

   public Seq copy$default$7() {
      return this.attempts();
   }

   public String productPrefix() {
      return "ApplicationInfo";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return this.coresGranted();
         }
         case 3 -> {
            return this.maxCores();
         }
         case 4 -> {
            return this.coresPerExecutor();
         }
         case 5 -> {
            return this.memoryPerExecutorMB();
         }
         case 6 -> {
            return this.attempts();
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
      return x$1 instanceof ApplicationInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "coresGranted";
         }
         case 3 -> {
            return "maxCores";
         }
         case 4 -> {
            return "coresPerExecutor";
         }
         case 5 -> {
            return "memoryPerExecutorMB";
         }
         case 6 -> {
            return "attempts";
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
      boolean var18;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof ApplicationInfo) {
               label88: {
                  ApplicationInfo var4 = (ApplicationInfo)x$1;
                  String var10000 = this.id();
                  String var5 = var4.id();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label88;
                  }

                  var10000 = this.name();
                  String var6 = var4.name();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label88;
                  }

                  Option var13 = this.coresGranted();
                  Option var7 = var4.coresGranted();
                  if (var13 == null) {
                     if (var7 != null) {
                        break label88;
                     }
                  } else if (!var13.equals(var7)) {
                     break label88;
                  }

                  var13 = this.maxCores();
                  Option var8 = var4.maxCores();
                  if (var13 == null) {
                     if (var8 != null) {
                        break label88;
                     }
                  } else if (!var13.equals(var8)) {
                     break label88;
                  }

                  var13 = this.coresPerExecutor();
                  Option var9 = var4.coresPerExecutor();
                  if (var13 == null) {
                     if (var9 != null) {
                        break label88;
                     }
                  } else if (!var13.equals(var9)) {
                     break label88;
                  }

                  var13 = this.memoryPerExecutorMB();
                  Option var10 = var4.memoryPerExecutorMB();
                  if (var13 == null) {
                     if (var10 != null) {
                        break label88;
                     }
                  } else if (!var13.equals(var10)) {
                     break label88;
                  }

                  Seq var17 = this.attempts();
                  Seq var11 = var4.attempts();
                  if (var17 == null) {
                     if (var11 != null) {
                        break label88;
                     }
                  } else if (!var17.equals(var11)) {
                     break label88;
                  }

                  if (var4.canEqual(this)) {
                     break label95;
                  }
               }
            }

            var18 = false;
            return var18;
         }
      }

      var18 = true;
      return var18;
   }

   public ApplicationInfo(final String id, final String name, final Option coresGranted, final Option maxCores, final Option coresPerExecutor, final Option memoryPerExecutorMB, final Seq attempts) {
      this.id = id;
      this.name = name;
      this.coresGranted = coresGranted;
      this.maxCores = maxCores;
      this.coresPerExecutor = coresPerExecutor;
      this.memoryPerExecutorMB = memoryPerExecutorMB;
      this.attempts = attempts;
      Product.$init$(this);
   }
}
