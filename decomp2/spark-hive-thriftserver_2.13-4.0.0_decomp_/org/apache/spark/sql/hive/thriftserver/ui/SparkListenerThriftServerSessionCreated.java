package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f!B\u0010!\u0001\nr\u0003\u0002C&\u0001\u0005+\u0007I\u0011\u0001'\t\u0011U\u0003!\u0011#Q\u0001\n5C\u0001B\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t/\u0002\u0011\t\u0012)A\u0005\u001b\"A\u0001\f\u0001BK\u0002\u0013\u0005A\n\u0003\u0005Z\u0001\tE\t\u0015!\u0003N\u0011!Q\u0006A!f\u0001\n\u0003Y\u0006\u0002C0\u0001\u0005#\u0005\u000b\u0011\u0002/\t\u000b\u0001\u0004A\u0011A1\t\u000f!\u0004\u0011\u0011!C\u0001S\"9a\u000eAI\u0001\n\u0003y\u0007b\u0002>\u0001#\u0003%\ta\u001c\u0005\bw\u0002\t\n\u0011\"\u0001p\u0011\u001da\b!%A\u0005\u0002uD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003#\u0001\u0011\u0011!C\u0001\u0003'A\u0011\"a\u0007\u0001\u0003\u0003%\t!!\b\t\u0013\u0005%\u0002!!A\u0005B\u0005-\u0002\"CA\u001d\u0001\u0005\u0005I\u0011AA\u001e\u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002L\u0001\t\t\u0011\"\u0011\u0002N!I\u0011q\n\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000b\u0005\n\u0003'\u0002\u0011\u0011!C!\u0003+:!\"!\u0017!\u0003\u0003E\tAIA.\r%y\u0002%!A\t\u0002\t\ni\u0006\u0003\u0004a3\u0011\u0005\u0011Q\u000f\u0005\n\u0003\u001fJ\u0012\u0011!C#\u0003#B\u0011\"a\u001e\u001a\u0003\u0003%\t)!\u001f\t\u0013\u0005\r\u0015$!A\u0005\u0002\u0006\u0015\u0005\"CAL3\u0005\u0005I\u0011BAM\u0005\u001d\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:TKN\u001c\u0018n\u001c8De\u0016\fG/\u001a3\u000b\u0005\u0005\u0012\u0013AA;j\u0015\t\u0019C%\u0001\u0007uQJLg\r^:feZ,'O\u0003\u0002&M\u0005!\u0001.\u001b<f\u0015\t9\u0003&A\u0002tc2T!!\u000b\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u0005-b\u0013AB1qC\u000eDWMC\u0001.\u0003\ry'oZ\n\u0006\u0001=*4H\u0010\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005YJT\"A\u001c\u000b\u0005aB\u0013!C:dQ\u0016$W\u000f\\3s\u0013\tQtG\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u0019=\u0013\ti\u0014GA\u0004Qe>$Wo\u0019;\u0011\u0005}BeB\u0001!G\u001d\t\tU)D\u0001C\u0015\t\u0019E)\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0014BA$2\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0013&\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u001d\u000b\u0014AA5q+\u0005i\u0005C\u0001(S\u001d\ty\u0005\u000b\u0005\u0002Bc%\u0011\u0011+M\u0001\u0007!J,G-\u001a4\n\u0005M#&AB*ue&twM\u0003\u0002Rc\u0005\u0019\u0011\u000e\u001d\u0011\u0002\u0013M,7o]5p]&#\u0017AC:fgNLwN\\%eA\u0005AQo]3s\u001d\u0006lW-A\u0005vg\u0016\u0014h*Y7fA\u0005I1\u000f^1siRKW.Z\u000b\u00029B\u0011\u0001'X\u0005\u0003=F\u0012A\u0001T8oO\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\rqJg.\u001b;?)\u0015\u0011G-\u001a4h!\t\u0019\u0007!D\u0001!\u0011\u0015Y\u0015\u00021\u0001N\u0011\u00151\u0016\u00021\u0001N\u0011\u0015A\u0016\u00021\u0001N\u0011\u0015Q\u0016\u00021\u0001]\u0003\u0011\u0019w\u000e]=\u0015\u000b\tT7\u000e\\7\t\u000f-S\u0001\u0013!a\u0001\u001b\"9aK\u0003I\u0001\u0002\u0004i\u0005b\u0002-\u000b!\u0003\u0005\r!\u0014\u0005\b5*\u0001\n\u00111\u0001]\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u001d\u0016\u0003\u001bF\\\u0013A\u001d\t\u0003gbl\u0011\u0001\u001e\u0006\u0003kZ\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005]\f\u0014AC1o]>$\u0018\r^5p]&\u0011\u0011\u0010\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\u0012A \u0016\u00039F\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0002!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\tA\u0001\\1oO*\u0011\u0011QB\u0001\u0005U\u00064\u0018-C\u0002T\u0003\u000f\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0006\u0011\u0007A\n9\"C\u0002\u0002\u001aE\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\b\u0002&A\u0019\u0001'!\t\n\u0007\u0005\r\u0012GA\u0002B]fD\u0011\"a\n\u0012\u0003\u0003\u0005\r!!\u0006\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0003\u0005\u0004\u00020\u0005U\u0012qD\u0007\u0003\u0003cQ1!a\r2\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003o\t\tD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u001f\u0003\u0007\u00022\u0001MA \u0013\r\t\t%\r\u0002\b\u0005>|G.Z1o\u0011%\t9cEA\u0001\u0002\u0004\ty\"\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0002\u0003\u0013B\u0011\"a\n\u0015\u0003\u0003\u0005\r!!\u0006\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0006\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0001\u0002\r\u0015\fX/\u00197t)\u0011\ti$a\u0016\t\u0013\u0005\u001dr#!AA\u0002\u0005}\u0011aJ*qCJ\\G*[:uK:,'\u000f\u00165sS\u001a$8+\u001a:wKJ\u001cVm]:j_:\u001c%/Z1uK\u0012\u0004\"aY\r\u0014\u000be\ty&a\u001b\u0011\u0013\u0005\u0005\u0014qM'N\u001br\u0013WBAA2\u0015\r\t)'M\u0001\beVtG/[7f\u0013\u0011\tI'a\u0019\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tG\u0007\u0005\u0003\u0002n\u0005MTBAA8\u0015\u0011\t\t(a\u0003\u0002\u0005%|\u0017bA%\u0002pQ\u0011\u00111L\u0001\u0006CB\u0004H.\u001f\u000b\nE\u0006m\u0014QPA@\u0003\u0003CQa\u0013\u000fA\u00025CQA\u0016\u000fA\u00025CQ\u0001\u0017\u000fA\u00025CQA\u0017\u000fA\u0002q\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\b\u0006M\u0005#\u0002\u0019\u0002\n\u00065\u0015bAAFc\t1q\n\u001d;j_:\u0004r\u0001MAH\u001b6kE,C\u0002\u0002\u0012F\u0012a\u0001V;qY\u0016$\u0004\u0002CAK;\u0005\u0005\t\u0019\u00012\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001cB!\u0011QAAO\u0013\u0011\ty*a\u0002\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkListenerThriftServerSessionCreated implements SparkListenerEvent, Product, Serializable {
   private final String ip;
   private final String sessionId;
   private final String userName;
   private final long startTime;

   public static Option unapply(final SparkListenerThriftServerSessionCreated x$0) {
      return SparkListenerThriftServerSessionCreated$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerSessionCreated apply(final String ip, final String sessionId, final String userName, final long startTime) {
      return SparkListenerThriftServerSessionCreated$.MODULE$.apply(ip, sessionId, userName, startTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerSessionCreated$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerSessionCreated$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String ip() {
      return this.ip;
   }

   public String sessionId() {
      return this.sessionId;
   }

   public String userName() {
      return this.userName;
   }

   public long startTime() {
      return this.startTime;
   }

   public SparkListenerThriftServerSessionCreated copy(final String ip, final String sessionId, final String userName, final long startTime) {
      return new SparkListenerThriftServerSessionCreated(ip, sessionId, userName, startTime);
   }

   public String copy$default$1() {
      return this.ip();
   }

   public String copy$default$2() {
      return this.sessionId();
   }

   public String copy$default$3() {
      return this.userName();
   }

   public long copy$default$4() {
      return this.startTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerSessionCreated";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.ip();
         }
         case 1 -> {
            return this.sessionId();
         }
         case 2 -> {
            return this.userName();
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.startTime());
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
      return x$1 instanceof SparkListenerThriftServerSessionCreated;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "ip";
         }
         case 1 -> {
            return "sessionId";
         }
         case 2 -> {
            return "userName";
         }
         case 3 -> {
            return "startTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.ip()));
      var1 = Statics.mix(var1, Statics.anyHash(this.sessionId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.userName()));
      var1 = Statics.mix(var1, Statics.longHash(this.startTime()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof SparkListenerThriftServerSessionCreated) {
               SparkListenerThriftServerSessionCreated var4 = (SparkListenerThriftServerSessionCreated)x$1;
               if (this.startTime() == var4.startTime()) {
                  label60: {
                     String var10000 = this.ip();
                     String var5 = var4.ip();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     var10000 = this.sessionId();
                     String var6 = var4.sessionId();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label60;
                     }

                     var10000 = this.userName();
                     String var7 = var4.userName();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public SparkListenerThriftServerSessionCreated(final String ip, final String sessionId, final String userName, final long startTime) {
      this.ip = ip;
      this.sessionId = sessionId;
      this.userName = userName;
      this.startTime = startTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
