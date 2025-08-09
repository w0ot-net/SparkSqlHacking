package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d\u0001\u0002\u000f\u001e\u0001\u0012B\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005w!AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\tE\t\u0015!\u0003G\u0011!Q\u0005A!f\u0001\n\u0003Q\u0004\u0002C&\u0001\u0005#\u0005\u000b\u0011B\u001e\t\u000b1\u0003A\u0011A'\t\u000bI\u0003A\u0011I*\t\u000fi\u0003\u0011\u0011!C\u00017\"9q\fAI\u0001\n\u0003\u0001\u0007bB6\u0001#\u0003%\t\u0001\u001c\u0005\b]\u0002\t\n\u0011\"\u0001a\u0011\u001dy\u0007!!A\u0005BADq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011G\u0004\n\u0003ki\u0012\u0011!E\u0001\u0003o1\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011\u0011\b\u0005\u0007\u0019Z!\t!!\u0015\t\u0013\u0005Mc#!A\u0005F\u0005U\u0003\"CA,-\u0005\u0005I\u0011QA-\u0011%\t\tGFA\u0001\n\u0003\u000b\u0019\u0007C\u0005\u0002vY\t\t\u0011\"\u0003\u0002x\t\u0011RK\u001c9beN,G-\u00128uSRLH)Z2m\u0015\tqr$A\u0002ei\u0012T!\u0001I\u0011\u0002\u0007alGNC\u0001#\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019B\u0001A\u0013*[A\u0011aeJ\u0007\u0002;%\u0011\u0001&\b\u0002\u000b\u000b:$\u0018\u000e^=EK\u000ed\u0007C\u0001\u0016,\u001b\u0005\t\u0013B\u0001\u0017\"\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\f\u001c\u000f\u0005=\"dB\u0001\u00194\u001b\u0005\t$B\u0001\u001a$\u0003\u0019a$o\\8u}%\t!%\u0003\u00026C\u00059\u0001/Y2lC\u001e,\u0017BA\u001c9\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t)\u0014%\u0001\u0003oC6,W#A\u001e\u0011\u0005q\u0002eBA\u001f?!\t\u0001\u0014%\u0003\u0002@C\u00051\u0001K]3eK\u001aL!!\u0011\"\u0003\rM#(/\u001b8h\u0015\ty\u0014%A\u0003oC6,\u0007%A\u0003fqRLE)F\u0001G!\t1s)\u0003\u0002I;\tQQ\t\u001f;fe:\fG.\u0013#\u0002\r\u0015DH/\u0013#!\u0003!qw\u000e^1uS>t\u0017!\u00038pi\u0006$\u0018n\u001c8!\u0003\u0019a\u0014N\\5u}Q!aj\u0014)R!\t1\u0003\u0001C\u0003:\u000f\u0001\u00071\bC\u0003E\u000f\u0001\u0007a\tC\u0003K\u000f\u0001\u00071(A\u0006ck&dGm\u0015;sS:<GC\u0001+Y!\t)fK\u0004\u0002+i%\u0011q\u000b\u000f\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\t\u000beC\u0001\u0019\u0001+\u0002\u0005M\u0014\u0017\u0001B2paf$BA\u0014/^=\"9\u0011(\u0003I\u0001\u0002\u0004Y\u0004b\u0002#\n!\u0003\u0005\rA\u0012\u0005\b\u0015&\u0001\n\u00111\u0001<\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0019\u0016\u0003w\t\\\u0013a\u0019\t\u0003I&l\u0011!\u001a\u0006\u0003M\u001e\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005!\f\u0013AC1o]>$\u0018\r^5p]&\u0011!.\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002[*\u0012aIY\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u000f\u0005\u0002so6\t1O\u0003\u0002uk\u0006!A.\u00198h\u0015\u00051\u0018\u0001\u00026bm\u0006L!!Q:\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003i\u0004\"AK>\n\u0005q\f#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA@\u0002\u0006A\u0019!&!\u0001\n\u0007\u0005\r\u0011EA\u0002B]fD\u0001\"a\u0002\u0010\u0003\u0003\u0005\rA_\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u00055\u0001#BA\b\u0003+yXBAA\t\u0015\r\t\u0019\"I\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\f\u0003#\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QDA\u0012!\rQ\u0013qD\u0005\u0004\u0003C\t#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u000f\t\u0012\u0011!a\u0001\u007f\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\t\u0018\u0011\u0006\u0005\t\u0003\u000f\u0011\u0012\u0011!a\u0001u\u0006A\u0001.Y:i\u0007>$W\rF\u0001{\u0003\u0019)\u0017/^1mgR!\u0011QDA\u001a\u0011!\t9\u0001FA\u0001\u0002\u0004y\u0018AE+oa\u0006\u00148/\u001a3F]RLG/\u001f#fG2\u0004\"A\n\f\u0014\u000bY\tY$a\u0012\u0011\u0011\u0005u\u00121I\u001eGw9k!!a\u0010\u000b\u0007\u0005\u0005\u0013%A\u0004sk:$\u0018.\\3\n\t\u0005\u0015\u0013q\b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA%\u0003\u001fj!!a\u0013\u000b\u0007\u00055S/\u0001\u0002j_&\u0019q'a\u0013\u0015\u0005\u0005]\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003E\fQ!\u00199qYf$rATA.\u0003;\ny\u0006C\u0003:3\u0001\u00071\bC\u0003E3\u0001\u0007a\tC\u0003K3\u0001\u00071(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0015\u0014\u0011\u000f\t\u0006U\u0005\u001d\u00141N\u0005\u0004\u0003S\n#AB(qi&|g\u000e\u0005\u0004+\u0003[ZdiO\u0005\u0004\u0003_\n#A\u0002+va2,7\u0007\u0003\u0005\u0002ti\t\t\u00111\u0001O\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003s\u00022A]A>\u0013\r\tih\u001d\u0002\u0007\u001f\nTWm\u0019;"
)
public class UnparsedEntityDecl extends EntityDecl implements Product, Serializable {
   private final String name;
   private final ExternalID extID;
   private final String notation;

   public static Option unapply(final UnparsedEntityDecl x$0) {
      return UnparsedEntityDecl$.MODULE$.unapply(x$0);
   }

   public static UnparsedEntityDecl apply(final String name, final ExternalID extID, final String notation) {
      return UnparsedEntityDecl$.MODULE$.apply(name, extID, notation);
   }

   public static Function1 tupled() {
      return UnparsedEntityDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnparsedEntityDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public ExternalID extID() {
      return this.extID;
   }

   public String notation() {
      return this.notation;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      sb.append((new java.lang.StringBuilder(10)).append("<!ENTITY ").append(this.name()).append(" ").toString());
      return this.extID().buildString(sb).append((new java.lang.StringBuilder(8)).append(" NDATA ").append(this.notation()).append(">").toString());
   }

   public UnparsedEntityDecl copy(final String name, final ExternalID extID, final String notation) {
      return new UnparsedEntityDecl(name, extID, notation);
   }

   public String copy$default$1() {
      return this.name();
   }

   public ExternalID copy$default$2() {
      return this.extID();
   }

   public String copy$default$3() {
      return this.notation();
   }

   public String productPrefix() {
      return "UnparsedEntityDecl";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.extID();
         case 2:
            return this.notation();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof UnparsedEntityDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "extID";
         case 2:
            return "notation";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof UnparsedEntityDecl) {
               label56: {
                  UnparsedEntityDecl var4 = (UnparsedEntityDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  ExternalID var8 = this.extID();
                  ExternalID var6 = var4.extID();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  String var9 = this.notation();
                  String var7 = var4.notation();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
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

   public UnparsedEntityDecl(final String name, final ExternalID extID, final String notation) {
      this.name = name;
      this.extID = extID;
      this.notation = notation;
      Product.$init$(this);
   }
}
