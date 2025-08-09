package org.apache.spark.scheduler;

import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d!B\u000f\u001f\u0001\u00022\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011-\u0003!\u0011#Q\u0001\n\u0001C\u0001\u0002\u0014\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0007\")q\n\u0001C\u0001!\"9A\u000bAA\u0001\n\u0003)\u0006b\u0002-\u0001#\u0003%\t!\u0017\u0005\bI\u0002\t\n\u0011\"\u0001f\u0011\u001d9\u0007!!A\u0005B!Dq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004v\u0001\u0005\u0005I\u0011\u0001<\t\u000fq\u0004\u0011\u0011!C!{\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\t\u0013\u0005}\u0001!!A\u0005B\u0005\u0005r\u0001CA\u0013=!\u0005\u0001%a\n\u0007\u000fuq\u0002\u0012\u0001\u0011\u0002*!1qJ\u0005C\u0001\u0003wA\u0001\"!\u0010\u0013\u0005\u0004%\t\u0001\u001b\u0005\b\u0003\u007f\u0011\u0002\u0015!\u0003j\u0011%\t\tEEA\u0001\n\u0003\u000b\u0019\u0005\u0003\u0005\u0002JI\t\n\u0011\"\u0001Z\u0011!\tYEEI\u0001\n\u0003)\u0007\"CA'%\u0005\u0005I\u0011QA(\u0011!\tiFEI\u0001\n\u0003I\u0006\u0002CA0%E\u0005I\u0011A3\t\u0013\u0005\u0005$#!A\u0005\n\u0005\r$\u0001F#yK\u000e,Ho\u001c:EK\u000e|W.\\5tg&|gN\u0003\u0002 A\u0005I1o\u00195fIVdWM\u001d\u0006\u0003C\t\nQa\u001d9be.T!a\t\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0013aA8sON!\u0001aJ\u00162!\tA\u0013&D\u0001\u001f\u0013\tQcD\u0001\nFq\u0016\u001cW\u000f^8s\u0019>\u001c8OU3bg>t\u0007C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#a\u0002)s_\u0012,8\r\u001e\t\u0003emr!aM\u001d\u000f\u0005QBT\"A\u001b\u000b\u0005Y:\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u00039J!AO\u0017\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u5\n!b^8sW\u0016\u0014\bj\\:u+\u0005\u0001\u0005c\u0001\u0017B\u0007&\u0011!)\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0011CeBA#G!\t!T&\u0003\u0002H[\u00051\u0001K]3eK\u001aL!!\u0013&\u0003\rM#(/\u001b8h\u0015\t9U&A\u0006x_J\\WM\u001d%pgR\u0004\u0013A\u0002:fCN|g.F\u0001D\u0003\u001d\u0011X-Y:p]\u0002\na\u0001P5oSRtDcA)S'B\u0011\u0001\u0006\u0001\u0005\b}\u0015\u0001\n\u00111\u0001A\u0011\u001daU\u0001%AA\u0002\r\u000bAaY8qsR\u0019\u0011KV,\t\u000fy2\u0001\u0013!a\u0001\u0001\"9AJ\u0002I\u0001\u0002\u0004\u0019\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u00025*\u0012\u0001iW\u0016\u00029B\u0011QLY\u0007\u0002=*\u0011q\fY\u0001\nk:\u001c\u0007.Z2lK\u0012T!!Y\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002d=\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\taM\u000b\u0002D7\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001b\t\u0003U>l\u0011a\u001b\u0006\u0003Y6\fA\u0001\\1oO*\ta.\u0001\u0003kCZ\f\u0017BA%l\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0011\bC\u0001\u0017t\u0013\t!XFA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002xuB\u0011A\u0006_\u0005\u0003s6\u00121!\u00118z\u0011\u001dY8\"!AA\u0002I\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001@\u0011\t}\f)a^\u0007\u0003\u0003\u0003Q1!a\u0001.\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u000f\t\tA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0007\u0003'\u00012\u0001LA\b\u0013\r\t\t\"\f\u0002\b\u0005>|G.Z1o\u0011\u001dYX\"!AA\u0002]\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0011.!\u0007\t\u000fmt\u0011\u0011!a\u0001e\u0006A\u0001.Y:i\u0007>$W\rF\u0001s\u0003\u0019)\u0017/^1mgR!\u0011QBA\u0012\u0011\u001dY\b#!AA\u0002]\fA#\u0012=fGV$xN\u001d#fG>lW.[:tS>t\u0007C\u0001\u0015\u0013'\u0015\u0011\u00121FA\u0019!\ra\u0013QF\u0005\u0004\u0003_i#AB!osJ+g\r\u0005\u0003\u00024\u0005eRBAA\u001b\u0015\r\t9$\\\u0001\u0003S>L1\u0001PA\u001b)\t\t9#A\u0005ng\u001e\u0004&/\u001a4jq\u0006QQn]4Qe\u00164\u0017\u000e\u001f\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bE\u000b)%a\u0012\t\u000fy2\u0002\u0013!a\u0001\u0001\"9AJ\u0006I\u0001\u0002\u0004\u0019\u0015aD1qa2LH\u0005Z3gCVdG\u000fJ\u0019\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uII\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002R\u0005e\u0003\u0003\u0002\u0017B\u0003'\u0002R\u0001LA+\u0001\u000eK1!a\u0016.\u0005\u0019!V\u000f\u001d7fe!A\u00111L\r\u0002\u0002\u0003\u0007\u0011+A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002fA\u0019!.a\u001a\n\u0007\u0005%4N\u0001\u0004PE*,7\r\u001e"
)
public class ExecutorDecommission extends ExecutorLossReason implements Product {
   private final Option workerHost;
   private final String reason;

   public static String $lessinit$greater$default$2() {
      return ExecutorDecommission$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option $lessinit$greater$default$1() {
      return ExecutorDecommission$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ExecutorDecommission x$0) {
      return ExecutorDecommission$.MODULE$.unapply(x$0);
   }

   public static String apply$default$2() {
      return ExecutorDecommission$.MODULE$.apply$default$2();
   }

   public static Option apply$default$1() {
      return ExecutorDecommission$.MODULE$.apply$default$1();
   }

   public static ExecutorDecommission apply(final Option workerHost, final String reason) {
      return ExecutorDecommission$.MODULE$.apply(workerHost, reason);
   }

   public static String msgPrefix() {
      return ExecutorDecommission$.MODULE$.msgPrefix();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option workerHost() {
      return this.workerHost;
   }

   public String reason() {
      return this.reason;
   }

   public ExecutorDecommission copy(final Option workerHost, final String reason) {
      return new ExecutorDecommission(workerHost, reason);
   }

   public Option copy$default$1() {
      return this.workerHost();
   }

   public String copy$default$2() {
      return this.reason();
   }

   public String productPrefix() {
      return "ExecutorDecommission";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.workerHost();
         }
         case 1 -> {
            return this.reason();
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
      return x$1 instanceof ExecutorDecommission;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "workerHost";
         }
         case 1 -> {
            return "reason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ExecutorDecommission) {
               label48: {
                  ExecutorDecommission var4 = (ExecutorDecommission)x$1;
                  Option var10000 = this.workerHost();
                  Option var5 = var4.workerHost();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  String var7 = this.reason();
                  String var6 = var4.reason();
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

   public ExecutorDecommission(final Option workerHost, final String reason) {
      this.workerHost = workerHost;
      this.reason = reason;
      String var10001 = ExecutorDecommission$.MODULE$.msgPrefix();
      super(var10001 + reason);
      Product.$init$(this);
   }
}
