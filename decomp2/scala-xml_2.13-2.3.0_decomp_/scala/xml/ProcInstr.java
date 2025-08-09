package scala.xml;

import scala.Function1;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001\u0002\u000e\u001c\u0001\u0002B\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005o!A\u0001\t\u0001BK\u0002\u0013\u0005a\u0007\u0003\u0005B\u0001\tE\t\u0015!\u00038\u0011\u0015\u0011\u0005\u0001\"\u0001D\u0011\u00159\u0005\u0001\"\u0012I\u0011\u0015a\u0005\u0001\"\u0012I\u0011\u0015i\u0005\u0001\"\u00127\u0011\u0015q\u0005\u0001\"\u00117\u0011\u0015y\u0005\u0001\"\u0011Q\u0011\u001d9\u0006!!A\u0005\u0002aCqa\u0017\u0001\u0012\u0002\u0013\u0005A\fC\u0004h\u0001E\u0005I\u0011\u0001/\t\u000f!\u0004\u0011\u0011!C!S\"9\u0011\u000fAA\u0001\n\u0003\u0011\bb\u0002<\u0001\u0003\u0003%\ta\u001e\u0005\b{\u0002\t\t\u0011\"\u0011\u007f\u0011%\tY\u0001AA\u0001\n\u0003\niaB\u0005\u0002\u0012m\t\t\u0011#\u0001\u0002\u0014\u0019A!dGA\u0001\u0012\u0003\t)\u0002\u0003\u0004C)\u0011\u0005\u0011Q\u0006\u0005\n\u0003_!\u0012\u0011!C#\u0003cA\u0011\"a\r\u0015\u0003\u0003%\t)!\u000e\t\u0013\u0005mB#!A\u0005\u0002\u0006u\u0002\"CA()\u0005\u0005I\u0011BA)\u0005%\u0001&o\\2J]N$(O\u0003\u0002\u001d;\u0005\u0019\u00010\u001c7\u000b\u0003y\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001C\u0015J\u0003C\u0001\u0012$\u001b\u0005Y\u0012B\u0001\u0013\u001c\u0005-\u0019\u0006/Z2jC2tu\u000eZ3\u0011\u0005\u0019:S\"A\u000f\n\u0005!j\"a\u0002)s_\u0012,8\r\u001e\t\u0003UIr!a\u000b\u0019\u000f\u00051zS\"A\u0017\u000b\u00059z\u0012A\u0002\u001fs_>$h(C\u0001\u001f\u0013\t\tT$A\u0004qC\u000e\\\u0017mZ3\n\u0005M\"$\u0001D*fe&\fG.\u001b>bE2,'BA\u0019\u001e\u0003\u0019!\u0018M]4fiV\tq\u0007\u0005\u00029y9\u0011\u0011H\u000f\t\u0003YuI!aO\u000f\u0002\rA\u0013X\rZ3g\u0013\tidH\u0001\u0004TiJLgn\u001a\u0006\u0003wu\tq\u0001^1sO\u0016$\b%\u0001\u0005qe>\u001cG/\u001a=u\u0003%\u0001(o\\2uKb$\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\t\u00163\u0005C\u0001\u0012\u0001\u0011\u0015)T\u00011\u00018\u0011\u0015\u0001U\u00011\u00018\u0003M!wnQ8mY\u0016\u001cGOT1nKN\u0004\u0018mY3t+\u0005I\u0005C\u0001\u0014K\u0013\tYUDA\u0004C_>dW-\u00198\u0002\u0017\u0011|GK]1og\u001a|'/\\\u0001\u0006Y\u0006\u0014W\r\\\u0001\u0005i\u0016DH/A\u0006ck&dGm\u0015;sS:<GCA)V!\t\u00116K\u0004\u0002'a%\u0011A\u000b\u000e\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\t\u000bYS\u0001\u0019A)\u0002\u0005M\u0014\u0017\u0001B2paf$2\u0001R-[\u0011\u001d)4\u0002%AA\u0002]Bq\u0001Q\u0006\u0011\u0002\u0003\u0007q'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003uS#a\u000e0,\u0003}\u0003\"\u0001Y3\u000e\u0003\u0005T!AY2\u0002\u0013Ut7\r[3dW\u0016$'B\u00013\u001e\u0003)\tgN\\8uCRLwN\\\u0005\u0003M\u0006\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00016\u0011\u0005-\u0004X\"\u00017\u000b\u00055t\u0017\u0001\u00027b]\u001eT\u0011a\\\u0001\u0005U\u00064\u0018-\u0003\u0002>Y\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t1\u000f\u0005\u0002'i&\u0011Q/\b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003qn\u0004\"AJ=\n\u0005il\"aA!os\"9A\u0010EA\u0001\u0002\u0004\u0019\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001\u0000!\u0015\t\t!a\u0002y\u001b\t\t\u0019AC\u0002\u0002\u0006u\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI!a\u0001\u0003\u0011%#XM]1u_J\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019!.a\u0004\t\u000fq\u0014\u0012\u0011!a\u0001g\u0006I\u0001K]8d\u0013:\u001cHO\u001d\t\u0003EQ\u0019R\u0001FA\f\u0003G\u0001r!!\u0007\u0002 ]:D)\u0004\u0002\u0002\u001c)\u0019\u0011QD\u000f\u0002\u000fI,h\u000e^5nK&!\u0011\u0011EA\u000e\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003K\tY#\u0004\u0002\u0002()\u0019\u0011\u0011\u00068\u0002\u0005%|\u0017bA\u001a\u0002(Q\u0011\u00111C\u0001\ti>\u001cFO]5oOR\t!.A\u0003baBd\u0017\u0010F\u0003E\u0003o\tI\u0004C\u00036/\u0001\u0007q\u0007C\u0003A/\u0001\u0007q'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00121\n\t\u0006M\u0005\u0005\u0013QI\u0005\u0004\u0003\u0007j\"AB(qi&|g\u000eE\u0003'\u0003\u000f:t'C\u0002\u0002Ju\u0011a\u0001V;qY\u0016\u0014\u0004\u0002CA'1\u0005\u0005\t\u0019\u0001#\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002TA\u00191.!\u0016\n\u0007\u0005]CN\u0001\u0004PE*,7\r\u001e"
)
public class ProcInstr extends SpecialNode implements Product {
   private final String target;
   private final String proctext;

   public static Function1 tupled() {
      return ProcInstr$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ProcInstr$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String target() {
      return this.target;
   }

   public String proctext() {
      return this.proctext;
   }

   public final boolean doCollectNamespaces() {
      return false;
   }

   public final boolean doTransform() {
      return false;
   }

   public final String label() {
      return "#PI";
   }

   public String text() {
      return "";
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append((new java.lang.StringBuilder(4)).append("<?").append(this.target()).append(this.proctext().isEmpty() ? "" : (new java.lang.StringBuilder(1)).append(" ").append(this.proctext()).toString()).append("?>").toString());
   }

   public ProcInstr copy(final String target, final String proctext) {
      return new ProcInstr(target, proctext);
   }

   public String copy$default$1() {
      return this.target();
   }

   public String copy$default$2() {
      return this.proctext();
   }

   public String productPrefix() {
      return "ProcInstr";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.target();
         case 1:
            return this.proctext();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "target";
         case 1:
            return "proctext";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public ProcInstr(final String target, final String proctext) {
      this.target = target;
      this.proctext = proctext;
      Product.$init$(this);
      if (!Utility$.MODULE$.isName(target)) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(20)).append(target).append(" must be an XML Name").toString());
      } else if (proctext.contains("?>")) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(21)).append(proctext).append(" may not contain \"?>\"").toString());
      } else {
         String var10000 = target.toLowerCase();
         String var3 = "xml";
         if (var10000 == null) {
            if (var3 == null) {
               throw new IllegalArgumentException((new java.lang.StringBuilder(12)).append(target).append(" is reserved").toString());
            }
         } else if (var10000.equals(var3)) {
            throw new IllegalArgumentException((new java.lang.StringBuilder(12)).append(target).append(" is reserved").toString());
         }

      }
   }
}
