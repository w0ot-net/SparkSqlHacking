package scala.xml;

import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001B\f\u0019\u0001vA\u0001B\r\u0001\u0003\u0016\u0004%\ta\r\u0005\ty\u0001\u0011\t\u0012)A\u0005i!)Q\b\u0001C\u0001}!)\u0011\t\u0001C!g!)!\t\u0001C!g!)1\t\u0001C#\t\")\u0001\n\u0001C#\t\")\u0011\n\u0001C!\u0015\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\t\u0011\"\u0011b\u0011\u001dI\u0007!!A\u0005\u0002)DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004v\u0001\u0005\u0005I\u0011\t<\t\u000fu\u0004\u0011\u0011!C!}\u001eI\u0011\u0011\u0001\r\u0002\u0002#\u0005\u00111\u0001\u0004\t/a\t\t\u0011#\u0001\u0002\u0006!1Q(\u0005C\u0001\u0003;A\u0011\"a\b\u0012\u0003\u0003%)%!\t\t\u0013\u0005\r\u0012#!A\u0005\u0002\u0006\u0015\u0002\"CA\u0015#\u0005\u0005I\u0011QA\u0016\u0011%\t9$EA\u0001\n\u0013\tIDA\u0004D_6lWM\u001c;\u000b\u0005eQ\u0012a\u0001=nY*\t1$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001q\"E\n\t\u0003?\u0001j\u0011\u0001G\u0005\u0003Ca\u00111b\u00159fG&\fGNT8eKB\u00111\u0005J\u0007\u00025%\u0011QE\u0007\u0002\b!J|G-^2u!\t9sF\u0004\u0002)[9\u0011\u0011\u0006L\u0007\u0002U)\u00111\u0006H\u0001\u0007yI|w\u000e\u001e \n\u0003mI!A\f\u000e\u0002\u000fA\f7m[1hK&\u0011\u0001'\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003]i\t1bY8n[\u0016tG\u000fV3yiV\tA\u0007\u0005\u00026s9\u0011ag\u000e\t\u0003SiI!\u0001\u000f\u000e\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003qi\tAbY8n[\u0016tG\u000fV3yi\u0002\na\u0001P5oSRtDCA A!\ty\u0002\u0001C\u00033\u0007\u0001\u0007A'A\u0003mC\n,G.\u0001\u0003uKb$\u0018a\u00053p\u0007>dG.Z2u\u001d\u0006lWm\u001d9bG\u0016\u001cX#A#\u0011\u0005\r2\u0015BA$\u001b\u0005\u001d\u0011un\u001c7fC:\f1\u0002Z8Ue\u0006t7OZ8s[\u0006Y!-^5mIN#(/\u001b8h)\tYu\n\u0005\u0002M\u001b:\u00111%L\u0005\u0003\u001dF\u0012Qb\u0015;sS:<')^5mI\u0016\u0014\b\"\u0002)\t\u0001\u0004Y\u0015AA:c\u0003\u0011\u0019w\u000e]=\u0015\u0005}\u001a\u0006b\u0002\u001a\n!\u0003\u0005\r\u0001N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051&F\u0001\u001bXW\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003%)hn\u00195fG.,GM\u0003\u0002^5\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005}S&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\fA\u0001\\1oO*\tq-\u0001\u0003kCZ\f\u0017B\u0001\u001ee\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Y\u0007CA\u0012m\u0013\ti'DA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002qgB\u00111%]\u0005\u0003ej\u00111!\u00118z\u0011\u001d!X\"!AA\u0002-\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A<\u0011\u0007a\\\b/D\u0001z\u0015\tQ($\u0001\u0006d_2dWm\u0019;j_:L!\u0001`=\u0003\u0011%#XM]1u_J\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011!m \u0005\bi>\t\t\u00111\u0001l\u0003\u001d\u0019u.\\7f]R\u0004\"aH\t\u0014\u000bE\t9!a\u0005\u0011\r\u0005%\u0011q\u0002\u001b@\u001b\t\tYAC\u0002\u0002\u000ei\tqA];oi&lW-\u0003\u0003\u0002\u0012\u0005-!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011QCA\u000e\u001b\t\t9BC\u0002\u0002\u001a\u0019\f!![8\n\u0007A\n9\u0002\u0006\u0002\u0002\u0004\u0005AAo\\*ue&tw\rF\u0001c\u0003\u0015\t\u0007\u000f\u001d7z)\ry\u0014q\u0005\u0005\u0006eQ\u0001\r\u0001N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti#a\r\u0011\t\r\ny\u0003N\u0005\u0004\u0003cQ\"AB(qi&|g\u000e\u0003\u0005\u00026U\t\t\u00111\u0001@\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003w\u00012aYA\u001f\u0013\r\ty\u0004\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class Comment extends SpecialNode implements Product {
   private final String commentText;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String commentText() {
      return this.commentText;
   }

   public String label() {
      return "#REM";
   }

   public String text() {
      return "";
   }

   public final boolean doCollectNamespaces() {
      return false;
   }

   public final boolean doTransform() {
      return false;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append((new java.lang.StringBuilder(7)).append("<!--").append(this.commentText()).append("-->").toString());
   }

   public Comment copy(final String commentText) {
      return new Comment(commentText);
   }

   public String copy$default$1() {
      return this.commentText();
   }

   public String productPrefix() {
      return "Comment";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.commentText();
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
            return "commentText";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public Comment(final String commentText) {
      this.commentText = commentText;
      Product.$init$(this);
      if (commentText.contains("--")) {
         throw new IllegalArgumentException("text contains \"--\"");
      } else if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(commentText)) && commentText.charAt(commentText.length() - 1) == '-') {
         throw new IllegalArgumentException("The final character of a XML comment may not be '-'. See https://www.w3.org/TR/xml11//#IDA5CES");
      }
   }
}
