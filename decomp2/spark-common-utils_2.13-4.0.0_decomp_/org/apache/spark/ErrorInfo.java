package org.apache.spark;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-f\u0001\u0002\u0010 \t\u001aB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005}!A!\n\u0001BK\u0002\u0013\u00051\n\u0003\u0005W\u0001\tE\t\u0015!\u0003M\u0011!9\u0006A!f\u0001\n\u0003A\u0006\u0002\u0003.\u0001\u0005#\u0005\u000b\u0011B-\t\u000bm\u0003A\u0011\u0001/\t\u000f\u0005\u0004!\u0019!C\u0001E\"11\r\u0001Q\u0001\n\u0005Cq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004w\u0001E\u0005I\u0011A<\t\u0013\u0005\r\u0001!%A\u0005\u0002\u0005\u0015\u0001\"CA\u0005\u0001E\u0005I\u0011AA\u0006\u0011%\ty\u0001AA\u0001\n\u0003\n\t\u0002C\u0005\u0002\"\u0001\t\t\u0011\"\u0001\u0002$!I\u00111\u0006\u0001\u0002\u0002\u0013\u0005\u0011Q\u0006\u0005\n\u0003s\u0001\u0011\u0011!C!\u0003wA\u0011\"!\u0013\u0001\u0003\u0003%\t!a\u0013\t\u0013\u0005U\u0003!!A\u0005B\u0005]\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u0011%\ty\u0006AA\u0001\n\u0003\n\t\u0007C\u0005\u0002d\u0001\t\t\u0011\"\u0011\u0002f\u001dI\u0011\u0011N\u0010\u0002\u0002#%\u00111\u000e\u0004\t=}\t\t\u0011#\u0003\u0002n!11\f\u0007C\u0001\u0003\u000bC\u0011\"a\u0018\u0019\u0003\u0003%)%!\u0019\t\u0013\u0005\u001d\u0005$!A\u0005\u0002\u0006%\u0005\"CAI1\u0005\u0005I\u0011QAJ\u0011%\t\t\u000bGA\u0001\n\u0013\t\u0019KA\u0005FeJ|'/\u00138g_*\u0011\u0001%I\u0001\u0006gB\f'o\u001b\u0006\u0003E\r\na!\u00199bG\",'\"\u0001\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00019S\u0006\r\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!r\u0013BA\u0018*\u0005\u001d\u0001&o\u001c3vGR\u0004\"!M\u001d\u000f\u0005I:dBA\u001a7\u001b\u0005!$BA\u001b&\u0003\u0019a$o\\8u}%\t!&\u0003\u00029S\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001e<\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tA\u0014&A\u0004nKN\u001c\u0018mZ3\u0016\u0003y\u00022!M B\u0013\t\u00015HA\u0002TKF\u0004\"A\u0011$\u000f\u0005\r#\u0005CA\u001a*\u0013\t)\u0015&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000f\"\u0013aa\u0015;sS:<'BA#*\u0003!iWm]:bO\u0016\u0004\u0013\u0001C:vE\u000ec\u0017m]:\u0016\u00031\u00032\u0001K'P\u0013\tq\u0015F\u0001\u0004PaRLwN\u001c\t\u0005\u0005B\u000b%+\u0003\u0002R\u0011\n\u0019Q*\u00199\u0011\u0005M#V\"A\u0010\n\u0005U{\"\u0001D#se>\u00148+\u001e2J]\u001a|\u0017!C:vE\u000ec\u0017m]:!\u0003!\u0019\u0018\u000f\\*uCR,W#A-\u0011\u0007!j\u0015)A\u0005tc2\u001cF/\u0019;fA\u00051A(\u001b8jiz\"B!\u00180`AB\u00111\u000b\u0001\u0005\u0006y\u001d\u0001\rA\u0010\u0005\u0006\u0015\u001e\u0001\r\u0001\u0014\u0005\u0006/\u001e\u0001\r!W\u0001\u0010[\u0016\u001c8/Y4f)\u0016l\u0007\u000f\\1uKV\t\u0011)\u0001\tnKN\u001c\u0018mZ3UK6\u0004H.\u0019;fA!\u0012\u0011\"\u001a\t\u0003M>l\u0011a\u001a\u0006\u0003Q&\f!\"\u00198o_R\fG/[8o\u0015\tQ7.A\u0004kC\u000e\\7o\u001c8\u000b\u00051l\u0017!\u00034bgR,'\u000f_7m\u0015\u0005q\u0017aA2p[&\u0011\u0001o\u001a\u0002\u000b\u0015N|g.S4o_J,\u0017\u0001B2paf$B!X:uk\"9AH\u0003I\u0001\u0002\u0004q\u0004b\u0002&\u000b!\u0003\u0005\r\u0001\u0014\u0005\b/*\u0001\n\u00111\u0001Z\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u001f\u0016\u0003}e\\\u0013A\u001f\t\u0003w~l\u0011\u0001 \u0006\u0003{z\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005!L\u0013bAA\u0001y\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0001\u0016\u0003\u0019f\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u000e)\u0012\u0011,_\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005M\u0001\u0003BA\u000b\u0003?i!!a\u0006\u000b\t\u0005e\u00111D\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u001e\u0005!!.\u0019<b\u0013\r9\u0015qC\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003K\u00012\u0001KA\u0014\u0013\r\tI#\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003_\t)\u0004E\u0002)\u0003cI1!a\r*\u0005\r\te.\u001f\u0005\n\u0003o\u0001\u0012\u0011!a\u0001\u0003K\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u001f!\u0019\ty$!\u0012\u000205\u0011\u0011\u0011\t\u0006\u0004\u0003\u0007J\u0013AC2pY2,7\r^5p]&!\u0011qIA!\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u00131\u000b\t\u0004Q\u0005=\u0013bAA)S\t9!i\\8mK\u0006t\u0007\"CA\u001c%\u0005\u0005\t\u0019AA\u0018\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005M\u0011\u0011\f\u0005\n\u0003o\u0019\u0012\u0011!a\u0001\u0003K\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003K\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003'\ta!Z9vC2\u001cH\u0003BA'\u0003OB\u0011\"a\u000e\u0017\u0003\u0003\u0005\r!a\f\u0002\u0013\u0015\u0013(o\u001c:J]\u001a|\u0007CA*\u0019'\u0015A\u0012qNA>!!\t\t(a\u001e?\u0019fkVBAA:\u0015\r\t)(K\u0001\beVtG/[7f\u0013\u0011\tI(a\u001d\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002~\u0005\rUBAA@\u0015\u0011\t\t)a\u0007\u0002\u0005%|\u0017b\u0001\u001e\u0002\u0000Q\u0011\u00111N\u0001\u0006CB\u0004H.\u001f\u000b\b;\u0006-\u0015QRAH\u0011\u0015a4\u00041\u0001?\u0011\u0015Q5\u00041\u0001M\u0011\u001596\u00041\u0001Z\u0003\u001d)h.\u00199qYf$B!!&\u0002\u001eB!\u0001&TAL!\u0019A\u0013\u0011\u0014 M3&\u0019\u00111T\u0015\u0003\rQ+\b\u000f\\34\u0011!\ty\nHA\u0001\u0002\u0004i\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0015\t\u0005\u0003+\t9+\u0003\u0003\u0002*\u0006]!AB(cU\u0016\u001cG\u000f"
)
public class ErrorInfo implements Product, Serializable {
   private final Seq message;
   private final Option subClass;
   private final Option sqlState;
   @JsonIgnore
   private final String messageTemplate;

   public static Option unapply(final ErrorInfo x$0) {
      return ErrorInfo$.MODULE$.unapply(x$0);
   }

   public static ErrorInfo apply(final Seq message, final Option subClass, final Option sqlState) {
      return ErrorInfo$.MODULE$.apply(message, subClass, sqlState);
   }

   public static Function1 tupled() {
      return ErrorInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ErrorInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq message() {
      return this.message;
   }

   public Option subClass() {
      return this.subClass;
   }

   public Option sqlState() {
      return this.sqlState;
   }

   public String messageTemplate() {
      return this.messageTemplate;
   }

   public ErrorInfo copy(final Seq message, final Option subClass, final Option sqlState) {
      return new ErrorInfo(message, subClass, sqlState);
   }

   public Seq copy$default$1() {
      return this.message();
   }

   public Option copy$default$2() {
      return this.subClass();
   }

   public Option copy$default$3() {
      return this.sqlState();
   }

   public String productPrefix() {
      return "ErrorInfo";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.message();
         }
         case 1 -> {
            return this.subClass();
         }
         case 2 -> {
            return this.sqlState();
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
      return x$1 instanceof ErrorInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "message";
         }
         case 1 -> {
            return "subClass";
         }
         case 2 -> {
            return "sqlState";
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
         label63: {
            if (x$1 instanceof ErrorInfo) {
               label56: {
                  ErrorInfo var4 = (ErrorInfo)x$1;
                  Seq var10000 = this.message();
                  Seq var5 = var4.message();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Option var8 = this.subClass();
                  Option var6 = var4.subClass();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  var8 = this.sqlState();
                  Option var7 = var4.sqlState();
                  if (var8 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
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

   public ErrorInfo(final Seq message, final Option subClass, final Option sqlState) {
      this.message = message;
      this.subClass = subClass;
      this.sqlState = sqlState;
      Product.$init$(this);
      this.messageTemplate = message.mkString("\n");
   }
}
