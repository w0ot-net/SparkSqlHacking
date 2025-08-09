package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.storage.BlockManagerId;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUb!B\u0010!\u0001\u00022\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\u001d\u0003!\u0011#Q\u0001\n}B\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\t!\u0013\u0005\tC\u0002\u0011\t\u0012)A\u0005\u0015\"AA\u000e\u0001BK\u0002\u0013\u0005Q\u000e\u0003\u0005u\u0001\tE\t\u0015!\u0003o\u0011!)\bA!f\u0001\n\u00031\b\"CA\n\u0001\tE\t\u0015!\u0003x\u0011\u001d\t)\u0002\u0001C\u0001\u0003/A\u0011\"a\u000e\u0001\u0003\u0003%\t!!\u000f\t\u0013\u0005\r\u0003!%A\u0005\u0002\u0005\u0015\u0003\"CA.\u0001E\u0005I\u0011AA/\u0011%\t\t\u0007AI\u0001\n\u0003\t\u0019\u0007C\u0005\u0002h\u0001\t\n\u0011\"\u0001\u0002j!I\u0011Q\u000e\u0001\u0002\u0002\u0013\u0005\u0013q\u000e\u0005\n\u0003\u007f\u0002\u0011\u0011!C\u0001\u0003\u0003C\u0011\"a!\u0001\u0003\u0003%\t!!\"\t\u0013\u0005-\u0005!!A\u0005B\u00055\u0005\"CAL\u0001\u0005\u0005I\u0011AAM\u0011%\t\u0019\u000bAA\u0001\n\u0003\n)\u000bC\u0005\u0002*\u0002\t\t\u0011\"\u0011\u0002,\"I\u0011Q\u0016\u0001\u0002\u0002\u0013\u0005\u0013q\u0016\u0005\n\u0003c\u0003\u0011\u0011!C!\u0003g;!\"a.!\u0003\u0003E\t\u0001IA]\r%y\u0002%!A\t\u0002\u0001\nY\fC\u0004\u0002\u0016e!\t!!:\t\u0013\u00055\u0016$!A\u0005F\u0005=\u0006\"CAt3\u0005\u0005I\u0011QAu\u0011%\u0011)!GA\u0001\n\u0003\u00139\u0001C\u0005\u0003,e\t\t\u0011\"\u0003\u0003.\tI\u0001*Z1si\n,\u0017\r\u001e\u0006\u0003C\t\nQa\u001d9be.T!a\t\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0013aA8sON!\u0001aJ\u00171!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011\u0001FL\u0005\u0003_%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00022u9\u0011!\u0007\u000f\b\u0003g]j\u0011\u0001\u000e\u0006\u0003kY\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002U%\u0011\u0011(K\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:S\u0005QQ\r_3dkR|'/\u00133\u0016\u0003}\u0002\"\u0001\u0011#\u000f\u0005\u0005\u0013\u0005CA\u001a*\u0013\t\u0019\u0015&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u0013aa\u0015;sS:<'BA\"*\u0003-)\u00070Z2vi>\u0014\u0018\n\u001a\u0011\u0002\u0019\u0005\u001c7-^7Va\u0012\fG/Z:\u0016\u0003)\u00032\u0001K&N\u0013\ta\u0015FA\u0003BeJ\f\u0017\u0010\u0005\u0003)\u001dB\u001b\u0016BA(*\u0005\u0019!V\u000f\u001d7feA\u0011\u0001&U\u0005\u0003%&\u0012A\u0001T8oOB\u0019\u0011\u0007\u0016,\n\u0005Uc$aA*fcB\u001aqk\u00186\u0011\ta[V,[\u0007\u00023*\u0011!\fI\u0001\u0005kRLG.\u0003\u0002]3\ni\u0011iY2v[Vd\u0017\r^8s-J\u0002\"AX0\r\u0001\u0011I\u0001\rBA\u0001\u0002\u0003\u0015\tA\u0019\u0002\u0004?\u0012\n\u0014!D1dGVlW\u000b\u001d3bi\u0016\u001c\b%\u0005\u0002dMB\u0011\u0001\u0006Z\u0005\u0003K&\u0012qAT8uQ&tw\r\u0005\u0002)O&\u0011\u0001.\u000b\u0002\u0004\u0003:L\bC\u00010k\t%YG!!A\u0001\u0002\u000b\u0005!MA\u0002`II\naB\u00197pG.l\u0015M\\1hKJLE-F\u0001o!\ty'/D\u0001q\u0015\t\t\b%A\u0004ti>\u0014\u0018mZ3\n\u0005M\u0004(A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0001\u0010E2|7m['b]\u0006<WM]%eA\u0005yQ\r_3dkR|'/\u00169eCR,7/F\u0001x!\u0015AXp`A\u0004\u001b\u0005I(B\u0001>|\u0003\u001diW\u000f^1cY\u0016T!\u0001`\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002\u007fs\n\u0019Q*\u00199\u0011\r!r\u0015\u0011AA\u0001!\rA\u00131A\u0005\u0004\u0003\u000bI#aA%oiB!\u0011\u0011BA\b\u001b\t\tYAC\u0002\u0002\u000e\u0001\n\u0001\"\u001a=fGV$xN]\u0005\u0005\u0003#\tYAA\bFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t\u0003A)\u00070Z2vi>\u0014X\u000b\u001d3bi\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u000b\u00033\ti\"a\b\u00024\u0005U\u0002cAA\u000e\u00015\t\u0001\u0005C\u0003>\u0013\u0001\u0007q\b\u0003\u0004I\u0013\u0001\u0007\u0011\u0011\u0005\t\u0005Q-\u000b\u0019\u0003E\u0003)\u001dB\u000b)\u0003\u0005\u00032)\u0006\u001d\u0002GBA\u0015\u0003[\t\t\u0004\u0005\u0004Y7\u0006-\u0012q\u0006\t\u0004=\u00065BA\u00031\u0002 \u0005\u0005\t\u0011!B\u0001EB\u0019a,!\r\u0005\u0015-\fy\"!A\u0001\u0002\u000b\u0005!\rC\u0003m\u0013\u0001\u0007a\u000eC\u0003v\u0013\u0001\u0007q/\u0001\u0003d_BLHCCA\r\u0003w\ti$a\u0010\u0002B!9QH\u0003I\u0001\u0002\u0004y\u0004\u0002\u0003%\u000b!\u0003\u0005\r!!\t\t\u000f1T\u0001\u0013!a\u0001]\"9QO\u0003I\u0001\u0002\u00049\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u000fR3aPA%W\t\tY\u0005\u0005\u0003\u0002N\u0005]SBAA(\u0015\u0011\t\t&a\u0015\u0002\u0013Ut7\r[3dW\u0016$'bAA+S\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005e\u0013q\n\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003?R3ASA%\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!!\u001a+\u00079\fI%\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005-$fA<\u0002J\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u001d\u0011\t\u0005M\u0014QP\u0007\u0003\u0003kRA!a\u001e\u0002z\u0005!A.\u00198h\u0015\t\tY(\u0001\u0003kCZ\f\u0017bA#\u0002v\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011\u0011A\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\r1\u0017q\u0011\u0005\n\u0003\u0013\u000b\u0012\u0011!a\u0001\u0003\u0003\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAH!\u0015\t\t*a%g\u001b\u0005Y\u0018bAAKw\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY*!)\u0011\u0007!\ni*C\u0002\u0002 &\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\nN\t\t\u00111\u0001g\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005E\u0014q\u0015\u0005\n\u0003\u0013#\u0012\u0011!a\u0001\u0003\u0003\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0003\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003c\na!Z9vC2\u001cH\u0003BAN\u0003kC\u0001\"!#\u0018\u0003\u0003\u0005\rAZ\u0001\n\u0011\u0016\f'\u000f\u001e2fCR\u00042!a\u0007\u001a'\u0015I\u0012QXAn!-\ty,!2@\u0003\u0013tw/!\u0007\u000e\u0005\u0005\u0005'bAAbS\u00059!/\u001e8uS6,\u0017\u0002BAd\u0003\u0003\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011A3*a3\u0011\u000b!r\u0005+!4\u0011\tE\"\u0016q\u001a\u0019\u0007\u0003#\f).!7\u0011\ra[\u00161[Al!\rq\u0016Q\u001b\u0003\nAf\t\t\u0011!A\u0003\u0002\t\u00042AXAm\t%Y\u0017$!A\u0001\u0002\u000b\u0005!\r\u0005\u0003\u0002^\u0006\rXBAAp\u0015\u0011\t\t/!\u001f\u0002\u0005%|\u0017bA\u001e\u0002`R\u0011\u0011\u0011X\u0001\u0006CB\u0004H.\u001f\u000b\u000b\u00033\tY/!<\u0003\u0002\t\r\u0001\"B\u001f\u001d\u0001\u0004y\u0004B\u0002%\u001d\u0001\u0004\ty\u000f\u0005\u0003)\u0017\u0006E\b#\u0002\u0015O!\u0006M\b\u0003B\u0019U\u0003k\u0004d!a>\u0002|\u0006}\bC\u0002-\\\u0003s\fi\u0010E\u0002_\u0003w$!\u0002YAw\u0003\u0003\u0005\tQ!\u0001c!\rq\u0016q \u0003\u000bW\u00065\u0018\u0011!A\u0001\u0006\u0003\u0011\u0007\"\u00027\u001d\u0001\u0004q\u0007\"B;\u001d\u0001\u00049\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0005\u0013\u00119\u0003E\u0003)\u0005\u0017\u0011y!C\u0002\u0003\u000e%\u0012aa\u00149uS>t\u0007\u0003\u0003\u0015\u0003\u0012}\u0012)B\\<\n\u0007\tM\u0011F\u0001\u0004UkBdW\r\u000e\t\u0005Q-\u00139\u0002E\u0003)\u001dB\u0013I\u0002\u0005\u00032)\nm\u0001G\u0002B\u000f\u0005C\u0011)\u0003\u0005\u0004Y7\n}!1\u0005\t\u0004=\n\u0005B!\u00031\u001e\u0003\u0003\u0005\tQ!\u0001c!\rq&Q\u0005\u0003\nWv\t\t\u0011!A\u0003\u0002\tD\u0011B!\u000b\u001e\u0003\u0003\u0005\r!!\u0007\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00030A!\u00111\u000fB\u0019\u0013\u0011\u0011\u0019$!\u001e\u0003\r=\u0013'.Z2u\u0001"
)
public class Heartbeat implements Product, Serializable {
   private final String executorId;
   private final Tuple2[] accumUpdates;
   private final BlockManagerId blockManagerId;
   private final Map executorUpdates;

   public static Option unapply(final Heartbeat x$0) {
      return Heartbeat$.MODULE$.unapply(x$0);
   }

   public static Heartbeat apply(final String executorId, final Tuple2[] accumUpdates, final BlockManagerId blockManagerId, final Map executorUpdates) {
      return Heartbeat$.MODULE$.apply(executorId, accumUpdates, blockManagerId, executorUpdates);
   }

   public static Function1 tupled() {
      return Heartbeat$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Heartbeat$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String executorId() {
      return this.executorId;
   }

   public Tuple2[] accumUpdates() {
      return this.accumUpdates;
   }

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public Map executorUpdates() {
      return this.executorUpdates;
   }

   public Heartbeat copy(final String executorId, final Tuple2[] accumUpdates, final BlockManagerId blockManagerId, final Map executorUpdates) {
      return new Heartbeat(executorId, accumUpdates, blockManagerId, executorUpdates);
   }

   public String copy$default$1() {
      return this.executorId();
   }

   public Tuple2[] copy$default$2() {
      return this.accumUpdates();
   }

   public BlockManagerId copy$default$3() {
      return this.blockManagerId();
   }

   public Map copy$default$4() {
      return this.executorUpdates();
   }

   public String productPrefix() {
      return "Heartbeat";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.executorId();
         }
         case 1 -> {
            return this.accumUpdates();
         }
         case 2 -> {
            return this.blockManagerId();
         }
         case 3 -> {
            return this.executorUpdates();
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
      return x$1 instanceof Heartbeat;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "executorId";
         }
         case 1 -> {
            return "accumUpdates";
         }
         case 2 -> {
            return "blockManagerId";
         }
         case 3 -> {
            return "executorUpdates";
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
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof Heartbeat) {
               label59: {
                  Heartbeat var4 = (Heartbeat)x$1;
                  String var10000 = this.executorId();
                  String var5 = var4.executorId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label59;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label59;
                  }

                  if (this.accumUpdates() == var4.accumUpdates()) {
                     label60: {
                        BlockManagerId var8 = this.blockManagerId();
                        BlockManagerId var6 = var4.blockManagerId();
                        if (var8 == null) {
                           if (var6 != null) {
                              break label60;
                           }
                        } else if (!var8.equals(var6)) {
                           break label60;
                        }

                        Map var9 = this.executorUpdates();
                        Map var7 = var4.executorUpdates();
                        if (var9 == null) {
                           if (var7 != null) {
                              break label60;
                           }
                        } else if (!var9.equals(var7)) {
                           break label60;
                        }

                        if (var4.canEqual(this)) {
                           break label67;
                        }
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

   public Heartbeat(final String executorId, final Tuple2[] accumUpdates, final BlockManagerId blockManagerId, final Map executorUpdates) {
      this.executorId = executorId;
      this.accumUpdates = accumUpdates;
      this.blockManagerId = blockManagerId;
      this.executorUpdates = executorUpdates;
      Product.$init$(this);
   }
}
