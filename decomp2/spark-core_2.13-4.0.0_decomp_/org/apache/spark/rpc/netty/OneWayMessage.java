package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\r\u001b\u0001j!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0015\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001c\u0011\u001d!\u0007!!A\u0005B\u0015DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\b\u0015\u0005}!$!A\t\u0002i\t\tCB\u0005\u001a5\u0005\u0005\t\u0012\u0001\u000e\u0002$!1Aj\u0005C\u0001\u0003wA\u0011\"!\u0006\u0014\u0003\u0003%)%a\u0006\t\u0013\u0005u2#!A\u0005\u0002\u0006}\u0002\"CA#'\u0005\u0005I\u0011QA$\u0011%\tIfEA\u0001\n\u0013\tYFA\u0007P]\u0016<\u0016-_'fgN\fw-\u001a\u0006\u00037q\tQA\\3uifT!!\b\u0010\u0002\u0007I\u00048M\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h'\u0015\u0001QeK\u00183!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019\te.\u001f*fMB\u0011A&L\u0007\u00025%\u0011aF\u0007\u0002\r\u0013:\u0014w\u000e_'fgN\fw-\u001a\t\u0003MAJ!!M\u0014\u0003\u000fA\u0013x\u000eZ;diB\u00111\u0007\u0010\b\u0003iir!!N\u001d\u000e\u0003YR!a\u000e\u001d\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001K\u0005\u0003w\u001d\nq\u0001]1dW\u0006<W-\u0003\u0002>}\ta1+\u001a:jC2L'0\u00192mK*\u00111hJ\u0001\u000eg\u0016tG-\u001a:BI\u0012\u0014Xm]:\u0016\u0003\u0005\u0003\"AQ\"\u000e\u0003qI!\u0001\u0012\u000f\u0003\u0015I\u00038-\u00113ee\u0016\u001c8/\u0001\btK:$WM]!eIJ,7o\u001d\u0011\u0002\u000f\r|g\u000e^3oiV\t\u0001\n\u0005\u0002'\u0013&\u0011!j\n\u0002\u0004\u0003:L\u0018\u0001C2p]R,g\u000e\u001e\u0011\u0002\rqJg.\u001b;?)\rqu\n\u0015\t\u0003Y\u0001AQaP\u0003A\u0002\u0005CQAR\u0003A\u0002!\u000bAaY8qsR\u0019aj\u0015+\t\u000f}2\u0001\u0013!a\u0001\u0003\"9aI\u0002I\u0001\u0002\u0004A\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002/*\u0012\u0011\tW\u0016\u00023B\u0011!lX\u0007\u00027*\u0011A,X\u0001\nk:\u001c\u0007.Z2lK\u0012T!AX\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002a7\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t1M\u000b\u0002I1\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001a\t\u0003O2l\u0011\u0001\u001b\u0006\u0003S*\fA\u0001\\1oO*\t1.\u0001\u0003kCZ\f\u0017BA7i\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0001\u000f\u0005\u0002'c&\u0011!o\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003\u0011VDqA^\u0006\u0002\u0002\u0003\u0007\u0001/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002sB\u0019!0 %\u000e\u0003mT!\u0001`\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002\u007fw\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019!!\u0003\u0011\u0007\u0019\n)!C\u0002\u0002\b\u001d\u0012qAQ8pY\u0016\fg\u000eC\u0004w\u001b\u0005\u0005\t\u0019\u0001%\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004M\u0006=\u0001b\u0002<\u000f\u0003\u0003\u0005\r\u0001]\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001/\u0001\u0005u_N#(/\u001b8h)\u00051\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0004\u0005u\u0001b\u0002<\u0012\u0003\u0003\u0005\r\u0001S\u0001\u000e\u001f:,w+Y=NKN\u001c\u0018mZ3\u0011\u00051\u001a2#B\n\u0002&\u0005E\u0002cBA\u0014\u0003[\t\u0005JT\u0007\u0003\u0003SQ1!a\u000b(\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\f\u0002*\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005M\u0012\u0011H\u0007\u0003\u0003kQ1!a\u000ek\u0003\tIw.C\u0002>\u0003k!\"!!\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b9\u000b\t%a\u0011\t\u000b}2\u0002\u0019A!\t\u000b\u00193\u0002\u0019\u0001%\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011JA+!\u00151\u00131JA(\u0013\r\tie\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0019\n\t&\u0011%\n\u0007\u0005MsE\u0001\u0004UkBdWM\r\u0005\t\u0003/:\u0012\u0011!a\u0001\u001d\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0003cA4\u0002`%\u0019\u0011\u0011\r5\u0003\r=\u0013'.Z2u\u0001"
)
public class OneWayMessage implements InboxMessage, Product, Serializable {
   private final RpcAddress senderAddress;
   private final Object content;

   public static Option unapply(final OneWayMessage x$0) {
      return OneWayMessage$.MODULE$.unapply(x$0);
   }

   public static OneWayMessage apply(final RpcAddress senderAddress, final Object content) {
      return OneWayMessage$.MODULE$.apply(senderAddress, content);
   }

   public static Function1 tupled() {
      return OneWayMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return OneWayMessage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public RpcAddress senderAddress() {
      return this.senderAddress;
   }

   public Object content() {
      return this.content;
   }

   public OneWayMessage copy(final RpcAddress senderAddress, final Object content) {
      return new OneWayMessage(senderAddress, content);
   }

   public RpcAddress copy$default$1() {
      return this.senderAddress();
   }

   public Object copy$default$2() {
      return this.content();
   }

   public String productPrefix() {
      return "OneWayMessage";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.senderAddress();
         }
         case 1 -> {
            return this.content();
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
      return x$1 instanceof OneWayMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "senderAddress";
         }
         case 1 -> {
            return "content";
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
         label49: {
            if (x$1 instanceof OneWayMessage) {
               label42: {
                  OneWayMessage var4 = (OneWayMessage)x$1;
                  RpcAddress var10000 = this.senderAddress();
                  RpcAddress var5 = var4.senderAddress();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label42;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label42;
                  }

                  if (BoxesRunTime.equals(this.content(), var4.content()) && var4.canEqual(this)) {
                     break label49;
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

   public OneWayMessage(final RpcAddress senderAddress, final Object content) {
      this.senderAddress = senderAddress;
      this.content = content;
      Product.$init$(this);
   }
}
