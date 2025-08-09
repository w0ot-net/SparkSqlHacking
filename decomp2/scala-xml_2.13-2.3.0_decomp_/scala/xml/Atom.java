package scala.xml;

import scala.collection.Seq;
import scala.collection.Seq.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}3A\u0001D\u0007\u0001%!A\u0001\u0005\u0001BC\u0002\u0013\u0005\u0011\u0005\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003#\u0011\u0015q\u0003\u0001\"\u00010\u0011\u0015\u0011\u0004\u0001\"\u00154\u0011\u0015Q\u0004\u0001\"\u0011<\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u00159\u0005\u0001\"\u0012I\u0011\u0015I\u0005\u0001\"\u0012I\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u00159\u0006\u0001\"\u0011Y\u0011\u0015q\u0006\u0001\"\u0011L\u0005\u0011\tEo\\7\u000b\u00059y\u0011a\u0001=nY*\t\u0001#A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005M!3c\u0001\u0001\u00151A\u0011QCF\u0007\u0002\u001b%\u0011q#\u0004\u0002\f'B,7-[1m\u001d>$W\r\u0005\u0002\u001a;9\u0011!dG\u0007\u0002\u001f%\u0011AdD\u0001\ba\u0006\u001c7.Y4f\u0013\tqrD\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u001d\u001f\u0005!A-\u0019;b+\u0005\u0011\u0003CA\u0012%\u0019\u0001!a!\n\u0001\u0005\u0006\u00041#!A!\u0012\u0005\u001dR\u0003C\u0001\u000e)\u0013\tIsBA\u0004O_RD\u0017N\\4\u0011\u0005iY\u0013B\u0001\u0017\u0010\u0005\r\te._\u0001\u0006I\u0006$\u0018\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A\n\u0004cA\u000b\u0001E!)\u0001e\u0001a\u0001E\u0005\u0001\"-Y:jg\u001a{'\u000fS1tQ\u000e{G-Z\u000b\u0002iA\u0019Q\u0007\u000f\u0016\u000e\u0003YR!aN\b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002:m\t\u00191+Z9\u0002\u001bM$(/[2u?\u0012*\u0017\u000fJ3r)\tat\b\u0005\u0002\u001b{%\u0011ah\u0004\u0002\b\u0005>|G.Z1o\u0011\u0015\u0001U\u00011\u0001B\u0003\u0015yG\u000f[3s!\t)\")\u0003\u0002D\u001b\tAQ)];bY&$\u00180\u0001\u0005dC:,\u0015/^1m)\tad\tC\u0003A\r\u0001\u0007!&A\ne_\u000e{G\u000e\\3di:\u000bW.Z:qC\u000e,7/F\u0001=\u0003-!w\u000e\u0016:b]N4wN]7\u0002\u000b1\f'-\u001a7\u0016\u00031\u0003\"!\u0014+\u000f\u00059\u0013\u0006CA(\u0010\u001b\u0005\u0001&BA)\u0012\u0003\u0019a$o\\8u}%\u00111kD\u0001\u0007!J,G-\u001a4\n\u0005U3&AB*ue&twM\u0003\u0002T\u001f\u0005Y!-^5mIN#(/\u001b8h)\tIF\f\u0005\u0002\u001a5&\u00111l\b\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\t\u000buS\u0001\u0019A-\u0002\u0005M\u0014\u0017\u0001\u0002;fqR\u0004"
)
public class Atom extends SpecialNode {
   private final Object data;

   public Object data() {
      return this.data;
   }

   public Seq basisForHashCode() {
      return (Seq).MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.data()}));
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (other instanceof Atom) {
         Atom var4 = (Atom)other;
         return BoxesRunTime.equals(this.data(), var4.data());
      } else {
         return false;
      }
   }

   public boolean canEqual(final Object other) {
      return other instanceof Atom;
   }

   public final boolean doCollectNamespaces() {
      return false;
   }

   public final boolean doTransform() {
      return false;
   }

   public String label() {
      return "#PCDATA";
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return Utility$.MODULE$.escape(this.data().toString(), sb);
   }

   public String text() {
      return this.data().toString();
   }

   public Atom(final Object data) {
      this.data = data;
      if (data == null) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(27)).append("cannot construct ").append(this.getClass().getSimpleName()).append(" with null").toString());
      }
   }
}
