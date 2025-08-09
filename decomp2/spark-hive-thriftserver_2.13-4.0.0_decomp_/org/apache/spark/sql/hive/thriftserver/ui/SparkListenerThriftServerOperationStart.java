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
   bytes = "\u0006\u0005\u00055g!B\u0014)\u0001*2\u0004\u0002C*\u0001\u0005+\u0007I\u0011\u0001+\t\u0011u\u0003!\u0011#Q\u0001\nUC\u0001B\u0018\u0001\u0003\u0016\u0004%\t\u0001\u0016\u0005\t?\u0002\u0011\t\u0012)A\u0005+\"A\u0001\r\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005b\u0001\tE\t\u0015!\u0003V\u0011!\u0011\u0007A!f\u0001\n\u0003!\u0006\u0002C2\u0001\u0005#\u0005\u000b\u0011B+\t\u0011\u0011\u0004!Q3A\u0005\u0002\u0015D\u0001\"\u001b\u0001\u0003\u0012\u0003\u0006IA\u001a\u0005\tU\u0002\u0011)\u001a!C\u0001)\"A1\u000e\u0001B\tB\u0003%Q\u000bC\u0003m\u0001\u0011\u0005Q\u000eC\u0004w\u0001\u0005\u0005I\u0011A<\t\u000fy\u0004\u0011\u0013!C\u0001\u007f\"A\u0011Q\u0003\u0001\u0012\u0002\u0013\u0005q\u0010\u0003\u0005\u0002\u0018\u0001\t\n\u0011\"\u0001\u0000\u0011!\tI\u0002AI\u0001\n\u0003y\b\"CA\u000e\u0001E\u0005I\u0011AA\u000f\u0011!\t\t\u0003AI\u0001\n\u0003y\b\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u0011%\t)\u0004AA\u0001\n\u0003\t9\u0004C\u0005\u0002@\u0001\t\t\u0011\"\u0001\u0002B!I\u0011Q\n\u0001\u0002\u0002\u0013\u0005\u0013q\n\u0005\n\u0003;\u0002\u0011\u0011!C\u0001\u0003?B\u0011\"!\u001b\u0001\u0003\u0003%\t%a\u001b\t\u0013\u0005=\u0004!!A\u0005B\u0005E\u0004\"CA:\u0001\u0005\u0005I\u0011IA;\u0011%\t9\bAA\u0001\n\u0003\nIh\u0002\u0006\u0002~!\n\t\u0011#\u0001+\u0003\u007f2\u0011b\n\u0015\u0002\u0002#\u0005!&!!\t\r1|B\u0011AAM\u0011%\t\u0019hHA\u0001\n\u000b\n)\bC\u0005\u0002\u001c~\t\t\u0011\"!\u0002\u001e\"A\u00111V\u0010\u0012\u0002\u0013\u0005q\u0010C\u0005\u0002.~\t\t\u0011\"!\u00020\"A\u0011\u0011Y\u0010\u0012\u0002\u0013\u0005q\u0010C\u0005\u0002D~\t\t\u0011\"\u0003\u0002F\n93\u000b]1sW2K7\u000f^3oKJ$\u0006N]5giN+'O^3s\u001fB,'/\u0019;j_:\u001cF/\u0019:u\u0015\tI#&\u0001\u0002vS*\u00111\u0006L\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003[9\nA\u0001[5wK*\u0011q\u0006M\u0001\u0004gFd'BA\u00193\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019D'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002k\u0005\u0019qN]4\u0014\u000b\u00019Th\u0011$\u0011\u0005aZT\"A\u001d\u000b\u0003i\nQa]2bY\u0006L!\u0001P\u001d\u0003\r\u0005s\u0017PU3g!\tq\u0014)D\u0001@\u0015\t\u0001\u0005'A\u0005tG\",G-\u001e7fe&\u0011!i\u0010\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u00029\t&\u0011Q)\u000f\u0002\b!J|G-^2u!\t9\u0005K\u0004\u0002I\u001d:\u0011\u0011*T\u0007\u0002\u0015*\u00111\nT\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t!(\u0003\u0002Ps\u00059\u0001/Y2lC\u001e,\u0017BA)S\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ty\u0015(\u0001\u0002jIV\tQ\u000b\u0005\u0002W5:\u0011q\u000b\u0017\t\u0003\u0013fJ!!W\u001d\u0002\rA\u0013X\rZ3g\u0013\tYFL\u0001\u0004TiJLgn\u001a\u0006\u00033f\n1!\u001b3!\u0003%\u0019Xm]:j_:LE-\u0001\u0006tKN\u001c\u0018n\u001c8JI\u0002\n\u0011b\u001d;bi\u0016lWM\u001c;\u0002\u0015M$\u0018\r^3nK:$\b%A\u0004he>,\b/\u00133\u0002\u0011\u001d\u0014x.\u001e9JI\u0002\n\u0011b\u001d;beR$\u0016.\\3\u0016\u0003\u0019\u0004\"\u0001O4\n\u0005!L$\u0001\u0002'p]\u001e\f!b\u001d;beR$\u0016.\\3!\u0003!)8/\u001a:OC6,\u0017!C;tKJt\u0015-\\3!\u0003\u0019a\u0014N\\5u}Q9a\u000e]9sgR,\bCA8\u0001\u001b\u0005A\u0003\"B*\u000e\u0001\u0004)\u0006\"\u00020\u000e\u0001\u0004)\u0006\"\u00021\u000e\u0001\u0004)\u0006\"\u00022\u000e\u0001\u0004)\u0006\"\u00023\u000e\u0001\u00041\u0007b\u00026\u000e!\u0003\u0005\r!V\u0001\u0005G>\u0004\u0018\u0010F\u0004oqfT8\u0010`?\t\u000fMs\u0001\u0013!a\u0001+\"9aL\u0004I\u0001\u0002\u0004)\u0006b\u00021\u000f!\u0003\u0005\r!\u0016\u0005\bE:\u0001\n\u00111\u0001V\u0011\u001d!g\u0002%AA\u0002\u0019DqA\u001b\b\u0011\u0002\u0003\u0007Q+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0005!fA+\u0002\u0004-\u0012\u0011Q\u0001\t\u0005\u0003\u000f\t\t\"\u0004\u0002\u0002\n)!\u00111BA\u0007\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0010e\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019\"!\u0003\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"\u0014AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003?Q3AZA\u0002\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0014!\u0011\tI#a\r\u000e\u0005\u0005-\"\u0002BA\u0017\u0003_\tA\u0001\\1oO*\u0011\u0011\u0011G\u0001\u0005U\u00064\u0018-C\u0002\\\u0003W\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u000f\u0011\u0007a\nY$C\u0002\u0002>e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0011\u0002JA\u0019\u0001(!\u0012\n\u0007\u0005\u001d\u0013HA\u0002B]fD\u0011\"a\u0013\u0018\u0003\u0003\u0005\r!!\u000f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\u0006\u0005\u0004\u0002T\u0005e\u00131I\u0007\u0003\u0003+R1!a\u0016:\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00037\n)F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA1\u0003O\u00022\u0001OA2\u0013\r\t)'\u000f\u0002\b\u0005>|G.Z1o\u0011%\tY%GA\u0001\u0002\u0004\t\u0019%\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0014\u0003[B\u0011\"a\u0013\u001b\u0003\u0003\u0005\r!!\u000f\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u000f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\n\u0002\r\u0015\fX/\u00197t)\u0011\t\t'a\u001f\t\u0013\u0005-S$!AA\u0002\u0005\r\u0013aJ*qCJ\\G*[:uK:,'\u000f\u00165sS\u001a$8+\u001a:wKJ|\u0005/\u001a:bi&|gn\u0015;beR\u0004\"a\\\u0010\u0014\u000b}\t\u0019)a$\u0011\u0017\u0005\u0015\u00151R+V+V3WK\\\u0007\u0003\u0003\u000fS1!!#:\u0003\u001d\u0011XO\u001c;j[\u0016LA!!$\u0002\b\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001c\u0011\t\u0005E\u0015qS\u0007\u0003\u0003'SA!!&\u00020\u0005\u0011\u0011n\\\u0005\u0004#\u0006MECAA@\u0003\u0015\t\u0007\u000f\u001d7z)5q\u0017qTAQ\u0003G\u000b)+a*\u0002*\")1K\ta\u0001+\")aL\ta\u0001+\")\u0001M\ta\u0001+\")!M\ta\u0001+\")AM\ta\u0001M\"9!N\tI\u0001\u0002\u0004)\u0016aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011WA_!\u0015A\u00141WA\\\u0013\r\t),\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013a\nI,V+V+\u001a,\u0016bAA^s\t1A+\u001e9mKZB\u0001\"a0%\u0003\u0003\u0005\rA\\\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$c'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002HB!\u0011\u0011FAe\u0013\u0011\tY-a\u000b\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkListenerThriftServerOperationStart implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final String sessionId;
   private final String statement;
   private final String groupId;
   private final long startTime;
   private final String userName;

   public static String $lessinit$greater$default$6() {
      return SparkListenerThriftServerOperationStart$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final SparkListenerThriftServerOperationStart x$0) {
      return SparkListenerThriftServerOperationStart$.MODULE$.unapply(x$0);
   }

   public static String apply$default$6() {
      return SparkListenerThriftServerOperationStart$.MODULE$.apply$default$6();
   }

   public static SparkListenerThriftServerOperationStart apply(final String id, final String sessionId, final String statement, final String groupId, final long startTime, final String userName) {
      return SparkListenerThriftServerOperationStart$.MODULE$.apply(id, sessionId, statement, groupId, startTime, userName);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationStart$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationStart$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String id() {
      return this.id;
   }

   public String sessionId() {
      return this.sessionId;
   }

   public String statement() {
      return this.statement;
   }

   public String groupId() {
      return this.groupId;
   }

   public long startTime() {
      return this.startTime;
   }

   public String userName() {
      return this.userName;
   }

   public SparkListenerThriftServerOperationStart copy(final String id, final String sessionId, final String statement, final String groupId, final long startTime, final String userName) {
      return new SparkListenerThriftServerOperationStart(id, sessionId, statement, groupId, startTime, userName);
   }

   public String copy$default$1() {
      return this.id();
   }

   public String copy$default$2() {
      return this.sessionId();
   }

   public String copy$default$3() {
      return this.statement();
   }

   public String copy$default$4() {
      return this.groupId();
   }

   public long copy$default$5() {
      return this.startTime();
   }

   public String copy$default$6() {
      return this.userName();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationStart";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
         }
         case 1 -> {
            return this.sessionId();
         }
         case 2 -> {
            return this.statement();
         }
         case 3 -> {
            return this.groupId();
         }
         case 4 -> {
            return BoxesRunTime.boxToLong(this.startTime());
         }
         case 5 -> {
            return this.userName();
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
      return x$1 instanceof SparkListenerThriftServerOperationStart;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "sessionId";
         }
         case 2 -> {
            return "statement";
         }
         case 3 -> {
            return "groupId";
         }
         case 4 -> {
            return "startTime";
         }
         case 5 -> {
            return "userName";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.id()));
      var1 = Statics.mix(var1, Statics.anyHash(this.sessionId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.statement()));
      var1 = Statics.mix(var1, Statics.anyHash(this.groupId()));
      var1 = Statics.mix(var1, Statics.longHash(this.startTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.userName()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof SparkListenerThriftServerOperationStart) {
               SparkListenerThriftServerOperationStart var4 = (SparkListenerThriftServerOperationStart)x$1;
               if (this.startTime() == var4.startTime()) {
                  label76: {
                     String var10000 = this.id();
                     String var5 = var4.id();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     var10000 = this.sessionId();
                     String var6 = var4.sessionId();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label76;
                     }

                     var10000 = this.statement();
                     String var7 = var4.statement();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label76;
                     }

                     var10000 = this.groupId();
                     String var8 = var4.groupId();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label76;
                     }

                     var10000 = this.userName();
                     String var9 = var4.userName();
                     if (var10000 == null) {
                        if (var9 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var9)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
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

   public SparkListenerThriftServerOperationStart(final String id, final String sessionId, final String statement, final String groupId, final long startTime, final String userName) {
      this.id = id;
      this.sessionId = sessionId;
      this.statement = statement;
      this.groupId = groupId;
      this.startTime = startTime;
      this.userName = userName;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
