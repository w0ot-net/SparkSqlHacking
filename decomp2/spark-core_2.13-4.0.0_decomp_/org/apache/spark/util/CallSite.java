package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d!\u0002\u0010 \u0001\u0006:\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011!\u0003!\u0011#Q\u0001\n\u0001C\u0001\"\u0013\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0001\")1\n\u0001C\u0001\u0019\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001W\u0011\u001d\u0011\u0007!!A\u0005B\rDqa\u001b\u0001\u0002\u0002\u0013\u0005A\u000eC\u0004q\u0001\u0005\u0005I\u0011A9\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\b\u0011\u0005}q\u0004#\u0001\"\u0003C1qAH\u0010\t\u0002\u0005\n\u0019\u0003\u0003\u0004L'\u0011\u0005\u0011q\u0006\u0005\t\u0003c\u0019\"\u0019!C\u0001G\"9\u00111G\n!\u0002\u0013!\u0007\u0002CA\u001b'\t\u0007I\u0011A2\t\u000f\u0005]2\u0003)A\u0005I\"I\u0011\u0011H\nC\u0002\u0013\u0005\u00111\b\u0005\b\u0003{\u0019\u0002\u0015!\u0003N\u0011%\tydEA\u0001\n\u0003\u000b\t\u0005C\u0005\u0002HM\t\t\u0011\"!\u0002J!I\u00111L\n\u0002\u0002\u0013%\u0011Q\f\u0002\t\u0007\u0006dGnU5uK*\u0011\u0001%I\u0001\u0005kRLGN\u0003\u0002#G\u0005)1\u000f]1sW*\u0011A%J\u0001\u0007CB\f7\r[3\u000b\u0003\u0019\n1a\u001c:h'\u0011\u0001\u0001FL\u0019\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g!\tIs&\u0003\u00021U\t9\u0001K]8ek\u000e$\bC\u0001\u001a<\u001d\t\u0019\u0014H\u0004\u00025q5\tQG\u0003\u00027o\u00051AH]8piz\u001a\u0001!C\u0001,\u0013\tQ$&A\u0004qC\u000e\\\u0017mZ3\n\u0005qj$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001e+\u0003%\u0019\bn\u001c:u\r>\u0014X.F\u0001A!\t\tUI\u0004\u0002C\u0007B\u0011AGK\u0005\u0003\t*\na\u0001\u0015:fI\u00164\u0017B\u0001$H\u0005\u0019\u0019FO]5oO*\u0011AIK\u0001\u000bg\"|'\u000f\u001e$pe6\u0004\u0013\u0001\u00037p]\u001e4uN]7\u0002\u00131|gn\u001a$pe6\u0004\u0013A\u0002\u001fj]&$h\bF\u0002N\u001fB\u0003\"A\u0014\u0001\u000e\u0003}AQAP\u0003A\u0002\u0001CQ!S\u0003A\u0002\u0001\u000bAaY8qsR\u0019Qj\u0015+\t\u000fy2\u0001\u0013!a\u0001\u0001\"9\u0011J\u0002I\u0001\u0002\u0004\u0001\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002/*\u0012\u0001\tW\u0016\u00023B\u0011!lX\u0007\u00027*\u0011A,X\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u0018\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002a7\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fA\u0001\\1oO*\t\u0011.\u0001\u0003kCZ\f\u0017B\u0001$g\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\u0007CA\u0015o\u0013\ty'FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002skB\u0011\u0011f]\u0005\u0003i*\u00121!\u00118z\u0011\u001d18\"!AA\u00025\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A=\u0011\u0007il(/D\u0001|\u0015\ta(&\u0001\u0006d_2dWm\u0019;j_:L!A`>\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0007\tI\u0001E\u0002*\u0003\u000bI1!a\u0002+\u0005\u001d\u0011un\u001c7fC:DqA^\u0007\u0002\u0002\u0003\u0007!/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00013\u0002\u0010!9aODA\u0001\u0002\u0004i\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00035\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002I\u00061Q-];bYN$B!a\u0001\u0002\u001e!9a/EA\u0001\u0002\u0004\u0011\u0018\u0001C\"bY2\u001c\u0016\u000e^3\u0011\u00059\u001b2\u0003B\n)\u0003K\u0001B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0004\u0003WA\u0017AA5p\u0013\ra\u0014\u0011\u0006\u000b\u0003\u0003C\t!b\u0015%P%R{fi\u0014*N\u0003-\u0019\u0006j\u0014*U?\u001a{%+\u0014\u0011\u0002\u00131{ejR0G\u001fJk\u0015A\u0003'P\u001d\u001e{fi\u0014*NA\u0005)Q-\u001c9usV\tQ*\u0001\u0004f[B$\u0018\u0010I\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u001b\u0006\r\u0013Q\t\u0005\u0006}m\u0001\r\u0001\u0011\u0005\u0006\u0013n\u0001\r\u0001Q\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY%a\u0016\u0011\u000b%\ni%!\u0015\n\u0007\u0005=#F\u0001\u0004PaRLwN\u001c\t\u0006S\u0005M\u0003\tQ\u0005\u0004\u0003+R#A\u0002+va2,'\u0007\u0003\u0005\u0002Zq\t\t\u00111\u0001N\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u00022!ZA1\u0013\r\t\u0019G\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class CallSite implements Product, Serializable {
   private final String shortForm;
   private final String longForm;

   public static Option unapply(final CallSite x$0) {
      return CallSite$.MODULE$.unapply(x$0);
   }

   public static CallSite apply(final String shortForm, final String longForm) {
      return CallSite$.MODULE$.apply(shortForm, longForm);
   }

   public static CallSite empty() {
      return CallSite$.MODULE$.empty();
   }

   public static String LONG_FORM() {
      return CallSite$.MODULE$.LONG_FORM();
   }

   public static String SHORT_FORM() {
      return CallSite$.MODULE$.SHORT_FORM();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String shortForm() {
      return this.shortForm;
   }

   public String longForm() {
      return this.longForm;
   }

   public CallSite copy(final String shortForm, final String longForm) {
      return new CallSite(shortForm, longForm);
   }

   public String copy$default$1() {
      return this.shortForm();
   }

   public String copy$default$2() {
      return this.longForm();
   }

   public String productPrefix() {
      return "CallSite";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.shortForm();
         }
         case 1 -> {
            return this.longForm();
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
      return x$1 instanceof CallSite;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shortForm";
         }
         case 1 -> {
            return "longForm";
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
            if (x$1 instanceof CallSite) {
               label48: {
                  CallSite var4 = (CallSite)x$1;
                  String var10000 = this.shortForm();
                  String var5 = var4.shortForm();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.longForm();
                  String var6 = var4.longForm();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public CallSite(final String shortForm, final String longForm) {
      this.shortForm = shortForm;
      this.longForm = longForm;
      Product.$init$(this);
   }
}
