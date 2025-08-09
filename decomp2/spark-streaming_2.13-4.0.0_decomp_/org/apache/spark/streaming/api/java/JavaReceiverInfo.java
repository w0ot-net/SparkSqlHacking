package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005h!B\u0016-\u0001BB\u0004\u0002C(\u0001\u0005+\u0007I\u0011\u0001)\t\u0011Q\u0003!\u0011#Q\u0001\nEC\u0001\"\u0016\u0001\u0003\u0016\u0004%\tA\u0016\u0005\t?\u0002\u0011\t\u0012)A\u0005/\"A\u0001\r\u0001BK\u0002\u0013\u0005\u0011\r\u0003\u0005f\u0001\tE\t\u0015!\u0003c\u0011!1\u0007A!f\u0001\n\u00031\u0006\u0002C4\u0001\u0005#\u0005\u000b\u0011B,\t\u0011!\u0004!Q3A\u0005\u0002YC\u0001\"\u001b\u0001\u0003\u0012\u0003\u0006Ia\u0016\u0005\tU\u0002\u0011)\u001a!C\u0001-\"A1\u000e\u0001B\tB\u0003%q\u000b\u0003\u0005m\u0001\tU\r\u0011\"\u0001W\u0011!i\u0007A!E!\u0002\u00139\u0006\u0002\u00038\u0001\u0005+\u0007I\u0011A8\t\u0011M\u0004!\u0011#Q\u0001\nADQ\u0001\u001e\u0001\u0005\u0002UD\u0011\"!\u0001\u0001\u0003\u0003%\t!a\u0001\t\u0013\u0005U\u0001!%A\u0005\u0002\u0005]\u0001\"CA\u0017\u0001E\u0005I\u0011AA\u0018\u0011%\t\u0019\u0004AI\u0001\n\u0003\t)\u0004C\u0005\u0002:\u0001\t\n\u0011\"\u0001\u00020!I\u00111\b\u0001\u0012\u0002\u0013\u0005\u0011q\u0006\u0005\n\u0003{\u0001\u0011\u0013!C\u0001\u0003_A\u0011\"a\u0010\u0001#\u0003%\t!a\f\t\u0013\u0005\u0005\u0003!%A\u0005\u0002\u0005\r\u0003\"CA$\u0001\u0005\u0005I\u0011IA%\u0011!\t9\u0006AA\u0001\n\u0003\u0001\u0006\"CA-\u0001\u0005\u0005I\u0011AA.\u0011%\t9\u0007AA\u0001\n\u0003\nI\u0007C\u0005\u0002x\u0001\t\t\u0011\"\u0001\u0002z!I\u0011Q\u0010\u0001\u0002\u0002\u0013\u0005\u0013q\u0010\u0005\n\u0003\u0007\u0003\u0011\u0011!C!\u0003\u000bC\u0011\"a\"\u0001\u0003\u0003%\t%!#\t\u0013\u0005-\u0005!!A\u0005B\u00055uACAIY\u0005\u0005\t\u0012\u0001\u0019\u0002\u0014\u001aI1\u0006LA\u0001\u0012\u0003\u0001\u0014Q\u0013\u0005\u0007i\u0016\"\t!!,\t\u0013\u0005\u001dU%!A\u0005F\u0005%\u0005\"CAXK\u0005\u0005I\u0011QAY\u0011%\t\u0019-JA\u0001\n\u0003\u000b)\rC\u0005\u0002X\u0016\n\t\u0011\"\u0003\u0002Z\n\u0001\"*\u0019<b%\u0016\u001cW-\u001b<fe&sgm\u001c\u0006\u0003[9\nAA[1wC*\u0011q\u0006M\u0001\u0004CBL'BA\u00193\u0003%\u0019HO]3b[&twM\u0003\u00024i\u0005)1\u000f]1sW*\u0011QGN\u0001\u0007CB\f7\r[3\u000b\u0003]\n1a\u001c:h'\u0011\u0001\u0011h\u0010\"\u0011\u0005ijT\"A\u001e\u000b\u0003q\nQa]2bY\u0006L!AP\u001e\u0003\r\u0005s\u0017PU3g!\tQ\u0004)\u0003\u0002Bw\t9\u0001K]8ek\u000e$\bCA\"M\u001d\t!%J\u0004\u0002F\u00136\taI\u0003\u0002H\u0011\u00061AH]8piz\u001a\u0001!C\u0001=\u0013\tY5(A\u0004qC\u000e\\\u0017mZ3\n\u00055s%\u0001D*fe&\fG.\u001b>bE2,'BA&<\u0003!\u0019HO]3b[&#W#A)\u0011\u0005i\u0012\u0016BA*<\u0005\rIe\u000e^\u0001\ngR\u0014X-Y7JI\u0002\nAA\\1nKV\tq\u000b\u0005\u0002Y9:\u0011\u0011L\u0017\t\u0003\u000bnJ!aW\u001e\u0002\rA\u0013X\rZ3g\u0013\tifL\u0001\u0004TiJLgn\u001a\u0006\u00037n\nQA\\1nK\u0002\na!Y2uSZ,W#\u00012\u0011\u0005i\u001a\u0017B\u00013<\u0005\u001d\u0011un\u001c7fC:\fq!Y2uSZ,\u0007%\u0001\u0005m_\u000e\fG/[8o\u0003%awnY1uS>t\u0007%\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\f1\"\u001a=fGV$xN]%eA\u0005\u0001B.Y:u\u000bJ\u0014xN]'fgN\fw-Z\u0001\u0012Y\u0006\u001cH/\u0012:s_JlUm]:bO\u0016\u0004\u0013!\u00037bgR,%O]8s\u0003)a\u0017m\u001d;FeJ|'\u000fI\u0001\u000eY\u0006\u001cH/\u0012:s_J$\u0016.\\3\u0016\u0003A\u0004\"AO9\n\u0005I\\$\u0001\u0002'p]\u001e\fa\u0002\\1ti\u0016\u0013(o\u001c:US6,\u0007%\u0001\u0004=S:LGO\u0010\u000b\nmbL(p\u001f?~}~\u0004\"a\u001e\u0001\u000e\u00031BQaT\tA\u0002ECQ!V\tA\u0002]CQ\u0001Y\tA\u0002\tDQAZ\tA\u0002]CQ\u0001[\tA\u0002]CQA[\tA\u0002]CQ\u0001\\\tA\u0002]CQA\\\tA\u0002A\fAaY8qsR\tb/!\u0002\u0002\b\u0005%\u00111BA\u0007\u0003\u001f\t\t\"a\u0005\t\u000f=\u0013\u0002\u0013!a\u0001#\"9QK\u0005I\u0001\u0002\u00049\u0006b\u00021\u0013!\u0003\u0005\rA\u0019\u0005\bMJ\u0001\n\u00111\u0001X\u0011\u001dA'\u0003%AA\u0002]CqA\u001b\n\u0011\u0002\u0003\u0007q\u000bC\u0004m%A\u0005\t\u0019A,\t\u000f9\u0014\u0002\u0013!a\u0001a\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\rU\r\t\u00161D\u0016\u0003\u0003;\u0001B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#A\u0005v]\u000eDWmY6fI*\u0019\u0011qE\u001e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002,\u0005\u0005\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u0019U\r9\u00161D\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t9DK\u0002c\u00037\tabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%m\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012:\u0014AD2paf$C-\u001a4bk2$H\u0005O\u000b\u0003\u0003\u000bR3\u0001]A\u000e\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\n\t\u0005\u0003\u001b\n)&\u0004\u0002\u0002P)!\u0011\u0011KA*\u0003\u0011a\u0017M\\4\u000b\u00035J1!XA(\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0018\u0002dA\u0019!(a\u0018\n\u0007\u0005\u00054HA\u0002B]fD\u0001\"!\u001a\u001e\u0003\u0003\u0005\r!U\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005-\u0004CBA7\u0003g\ni&\u0004\u0002\u0002p)\u0019\u0011\u0011O\u001e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002v\u0005=$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2AYA>\u0011%\t)gHA\u0001\u0002\u0004\ti&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA&\u0003\u0003C\u0001\"!\u001a!\u0003\u0003\u0005\r!U\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011+\u0001\u0005u_N#(/\u001b8h)\t\tY%\u0001\u0004fcV\fGn\u001d\u000b\u0004E\u0006=\u0005\"CA3G\u0005\u0005\t\u0019AA/\u0003AQ\u0015M^1SK\u000e,\u0017N^3s\u0013:4w\u000e\u0005\u0002xKM)Q%a&\u0002$Bi\u0011\u0011TAP#^\u0013wkV,XaZl!!a'\u000b\u0007\u0005u5(A\u0004sk:$\u0018.\\3\n\t\u0005\u0005\u00161\u0014\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:D\u0004\u0003BAS\u0003Wk!!a*\u000b\t\u0005%\u00161K\u0001\u0003S>L1!TAT)\t\t\u0019*A\u0003baBd\u0017\u0010F\tw\u0003g\u000b),a.\u0002:\u0006m\u0016QXA`\u0003\u0003DQa\u0014\u0015A\u0002ECQ!\u0016\u0015A\u0002]CQ\u0001\u0019\u0015A\u0002\tDQA\u001a\u0015A\u0002]CQ\u0001\u001b\u0015A\u0002]CQA\u001b\u0015A\u0002]CQ\u0001\u001c\u0015A\u0002]CQA\u001c\u0015A\u0002A\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002H\u0006M\u0007#\u0002\u001e\u0002J\u00065\u0017bAAfw\t1q\n\u001d;j_:\u00042BOAh#^\u0013wkV,Xa&\u0019\u0011\u0011[\u001e\u0003\rQ+\b\u000f\\39\u0011!\t).KA\u0001\u0002\u00041\u0018a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001c\t\u0005\u0003\u001b\ni.\u0003\u0003\u0002`\u0006=#AB(cU\u0016\u001cG\u000f"
)
public class JavaReceiverInfo implements Product, Serializable {
   private final int streamId;
   private final String name;
   private final boolean active;
   private final String location;
   private final String executorId;
   private final String lastErrorMessage;
   private final String lastError;
   private final long lastErrorTime;

   public static Option unapply(final JavaReceiverInfo x$0) {
      return JavaReceiverInfo$.MODULE$.unapply(x$0);
   }

   public static JavaReceiverInfo apply(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return JavaReceiverInfo$.MODULE$.apply(streamId, name, active, location, executorId, lastErrorMessage, lastError, lastErrorTime);
   }

   public static Function1 tupled() {
      return JavaReceiverInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JavaReceiverInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public String name() {
      return this.name;
   }

   public boolean active() {
      return this.active;
   }

   public String location() {
      return this.location;
   }

   public String executorId() {
      return this.executorId;
   }

   public String lastErrorMessage() {
      return this.lastErrorMessage;
   }

   public String lastError() {
      return this.lastError;
   }

   public long lastErrorTime() {
      return this.lastErrorTime;
   }

   public JavaReceiverInfo copy(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return new JavaReceiverInfo(streamId, name, active, location, executorId, lastErrorMessage, lastError, lastErrorTime);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public String copy$default$2() {
      return this.name();
   }

   public boolean copy$default$3() {
      return this.active();
   }

   public String copy$default$4() {
      return this.location();
   }

   public String copy$default$5() {
      return this.executorId();
   }

   public String copy$default$6() {
      return this.lastErrorMessage();
   }

   public String copy$default$7() {
      return this.lastError();
   }

   public long copy$default$8() {
      return this.lastErrorTime();
   }

   public String productPrefix() {
      return "JavaReceiverInfo";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.active());
         }
         case 3 -> {
            return this.location();
         }
         case 4 -> {
            return this.executorId();
         }
         case 5 -> {
            return this.lastErrorMessage();
         }
         case 6 -> {
            return this.lastError();
         }
         case 7 -> {
            return BoxesRunTime.boxToLong(this.lastErrorTime());
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
      return x$1 instanceof JavaReceiverInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "active";
         }
         case 3 -> {
            return "location";
         }
         case 4 -> {
            return "executorId";
         }
         case 5 -> {
            return "lastErrorMessage";
         }
         case 6 -> {
            return "lastError";
         }
         case 7 -> {
            return "lastErrorTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.streamId());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, this.active() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.location()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastErrorMessage()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastError()));
      var1 = Statics.mix(var1, Statics.longHash(this.lastErrorTime()));
      return Statics.finalizeHash(var1, 8);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof JavaReceiverInfo) {
               JavaReceiverInfo var4 = (JavaReceiverInfo)x$1;
               if (this.streamId() == var4.streamId() && this.active() == var4.active() && this.lastErrorTime() == var4.lastErrorTime()) {
                  label84: {
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     var10000 = this.location();
                     String var6 = var4.location();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label84;
                     }

                     var10000 = this.executorId();
                     String var7 = var4.executorId();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label84;
                     }

                     var10000 = this.lastErrorMessage();
                     String var8 = var4.lastErrorMessage();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label84;
                     }

                     var10000 = this.lastError();
                     String var9 = var4.lastError();
                     if (var10000 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var9)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public JavaReceiverInfo(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      this.streamId = streamId;
      this.name = name;
      this.active = active;
      this.location = location;
      this.executorId = executorId;
      this.lastErrorMessage = lastErrorMessage;
      this.lastError = lastError;
      this.lastErrorTime = lastErrorTime;
      Product.$init$(this);
   }
}
