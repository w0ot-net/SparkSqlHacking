package scala.reflect.api;

import java.io.ObjectStreamException;
import java.io.Serializable;
import scala.Equals;
import scala.Option;
import scala.Some;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rh\u0001\u0003\u0012$!\u0003\r\tA\u000b-\t\u000b=\u0002A\u0011\u0001\u0019\u0007\u000fQ\u0002\u0001\u0013aA\u0001k!)qF\u0001C\u0001a!9\u0011I\u0001b\u0001\u000e\u0003\u0011\u0005\"B%\u0003\r\u0003Q\u0005\"\u00026\u0003\r\u0003Y\u0007\"B9\u0003\r\u0003\u0011\b\"\u0002=\u0003\r\u0003\u0011\b\"B=\u0003\r\u0003Q\b\u0002CA\u0005\u0005\t\u0007i\u0011\u0001>\t\u000f\u0005E!\u0001\"\u0011\u0002\u0014!9\u0011q\u0004\u0002\u0005B\u0005\u0005\u0002bBA\u0013\u0005\u0011\u0005\u0013q\u0005\u0005\b\u0003_\u0011A\u0011IA\u0019\u000f\u001d\t\u0019\u0005\u0001E\u0001\u0003\u000b2a\u0001\u000e\u0001\t\u0002\u0005\u001d\u0003bBA*!\u0011\u0005\u0011Q\u000b\u0005\b\u0003/\u0002B\u0011AA-\u0011\u001d\t\t\t\u0005C\u0001\u0003\u00073a!a&\u0001\t\u0005e\u0005\u0002C!\u0015\u0005\u000b\u0007I\u0011\u0001\"\t\u0013\u0005\rFC!A!\u0002\u0013\u0019\u0005BCA=)\t\u0015\r\u0011\"\u0001\u0002&\"Q\u0011q\u0015\u000b\u0003\u0002\u0003\u0006I!a\u001f\t\u0015\u0005%FCaA!\u0002\u0017\tY\u000bC\u0004\u0002TQ!\t!!,\t\r%#B\u0011AA]\u0011!QG\u0003#b\u0001\n\u0003Y\u0007\u0002C9\u0015\u0011\u000b\u0007I\u0011\u0001:\t\u000ba$B\u0011\u0001:\t\re$B\u0011AAg\u0011)\tI\u0001\u0006EC\u0002\u0013\u0005\u0011Q\u001a\u0005\b\u0003\u001f$B\u0011BAi\u0005\u0015)\u0005\u0010\u001d:t\u0015\t!S%A\u0002ba&T!AJ\u0014\u0002\u000fI,g\r\\3di*\t\u0001&A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Y\u0003C\u0001\u0017.\u001b\u00059\u0013B\u0001\u0018(\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\r\t\u0003YIJ!aM\u0014\u0003\tUs\u0017\u000e\u001e\u0002\u0005\u000bb\u0004(/\u0006\u00027AN!!aK\u001c;!\ta\u0003(\u0003\u0002:O\t1Q)];bYN\u0004\"a\u000f \u000f\u00051b\u0014BA\u001f(\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0010!\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005u:\u0013AB7jeJ|'/F\u0001D!\t!U)D\u0001\u0001\u0013\t1uI\u0001\u0004NSJ\u0014xN]\u0005\u0003\u0011\u000e\u0012q!T5se>\u00148/\u0001\u0002j]V\u00111j\u0014\u000b\u0003\u0019\u001a\u00042!\u0014\u0002`!\tqu\n\u0004\u0001\u0005\u000bA+!\u0019A)\u0003\u0003U\u000b\"AU+\u0011\u00051\u001a\u0016B\u0001+(\u0005\u001dqu\u000e\u001e5j]\u001e\u00142A\u0016-]\r\u00119&\u0001A+\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005eSV\"A\u0012\n\u0005m\u001b#\u0001C+oSZ,'o]3\u0011\u00051j\u0016B\u00010(\u0005%\u0019\u0016N\\4mKR|g\u000e\u0005\u0002OA\u00121\u0011M\u0001CC\u0002\t\u0014\u0011\u0001V\t\u0003%\u000e\u0004\"\u0001\f3\n\u0005\u0015<#aA!os\")q-\u0002a\u0001Q\u0006Yq\u000e\u001e5fe6K'O]8s!\rI\u0016.T\u0005\u0003\r\u000e\nA\u0001\u001e:fKV\tA\u000e\u0005\u0002E[&\u0011an\u001c\u0002\u0005)J,W-\u0003\u0002qG\t)AK]3fg\u0006Q1\u000f^1uS\u000e$\u0016\u0010]3\u0016\u0003M\u0004\"\u0001\u0012;\n\u0005U4(\u0001\u0002+za\u0016L!a^\u0012\u0003\u000bQK\b/Z:\u0002\u0015\u0005\u001cG/^1m)f\u0004X-\u0001\u0004ta2L7-Z\u000b\u0002?\"\"\u0011\u0002`A\u0003!\ri\u0018\u0011A\u0007\u0002}*\u0011qpJ\u0001\u000bC:tw\u000e^1uS>t\u0017bAA\u0002}\ny1m\\7qS2,G+[7f\u001f:d\u00170\t\u0002\u0002\b\u0005y3\u000f\u001d7jG\u0016\u0004S.^:uA\t,\u0007%\u001a8dY>\u001cX\r\u001a\u0011xSRD\u0017N\u001c\u0011bAI,\u0017NZ=!wv\u0004#\r\\8dW\u0006)a/\u00197vK\"\"!\u0002`A\u0007C\t\ty!A dC:tw\u000e\u001e\u0011vg\u0016\u0004c/\u00197vK\u0002*\u0007pY3qi\u00022wN\u001d\u0011tS\u001et\u0017\r^;sKN\u0004sN\u001a\u0011nC\u000e\u0014x\u000eI5na2,W.\u001a8uCRLwN\\:\u0002\u0011\r\fg.R9vC2$B!!\u0006\u0002\u001cA\u0019A&a\u0006\n\u0007\u0005eqEA\u0004C_>dW-\u00198\t\r\u0005u1\u00021\u0001d\u0003\u0005A\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0016\u0005\r\u0002BBA\u000f\u0019\u0001\u00071-\u0001\u0005iCND7i\u001c3f)\t\tI\u0003E\u0002-\u0003WI1!!\f(\u0005\rIe\u000e^\u0001\ti>\u001cFO]5oOR\u0011\u00111\u0007\t\u0005\u0003k\ty$\u0004\u0002\u00028)!\u0011\u0011HA\u001e\u0003\u0011a\u0017M\\4\u000b\u0005\u0005u\u0012\u0001\u00026bm\u0006LA!!\u0011\u00028\t11\u000b\u001e:j]\u001e\fA!\u0012=qeB\u0011A\tE\n\u0005!-\nI\u0005\u0005\u0003\u0002L\u0005ESBAA'\u0015\u0011\ty%a\u000f\u0002\u0005%|\u0017bA \u0002N\u00051A(\u001b8jiz\"\"!!\u0012\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005m\u00131\r\u000b\u0007\u0003;\n\u0019(a\u001e\u0015\t\u0005}\u0013Q\r\t\u0005\t\n\t\t\u0007E\u0002O\u0003G\"Q!\u0019\nC\u0002\tD\u0011\"a\u001a\u0013\u0003\u0003\u0005\u001d!!\u001b\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003E\u0003W\n\t'\u0003\u0003\u0002n\u0005=$aC,fC.$\u0016\u0010]3UC\u001eL1!!\u001d$\u0005!!\u0016\u0010]3UC\u001e\u001c\bBB!\u0013\u0001\u0004\t)\bE\u0002ZS\u0012Cq!!\u001f\u0013\u0001\u0004\tY(A\u0003ue\u0016,7\rE\u0002Z\u0003{J1!a $\u0005-!&/Z3De\u0016\fGo\u001c:\u0002\u000fUt\u0017\r\u001d9msV!\u0011QQAK)\u0011\t9)!$\u0011\t1\nI\t\\\u0005\u0004\u0003\u0017;#AB(qi&|g\u000eC\u0004\u0002\u0010N\u0001\r!!%\u0002\t\u0015D\bO\u001d\t\u0005\t\n\t\u0019\nE\u0002O\u0003+#Q!Y\nC\u0002\t\u0014\u0001\"\u0012=qe&k\u0007\u000f\\\u000b\u0005\u00037\u000b\tk\u0005\u0003\u0015W\u0005u\u0005\u0003\u0002#\u0003\u0003?\u00032ATAQ\t\u0019\tG\u0003\"b\u0001E\u00069Q.\u001b:s_J\u0004SCAA>\u0003\u0019!(/Z3dA\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\u000b\u0011\u000bY'a(\u0015\r\u0005=\u0016QWA\\)\u0011\t\t,a-\u0011\t\u0011#\u0012q\u0014\u0005\b\u0003SS\u00029AAV\u0011\u0015\t%\u00041\u0001D\u0011\u001d\tIH\u0007a\u0001\u0003w*B!a/\u0002BR!\u0011QXAe!\u0015\tyLAAP!\rq\u0015\u0011\u0019\u0003\u0007!n\u0011\r!a1\u0012\u0007I\u000b)M\u0005\u0003\u0002Hbcf!B,\u0015\u0001\u0005\u0015\u0007BB4\u001c\u0001\u0004\tY\r\u0005\u0003ZS\u0006}VCAAP\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005Y\u0003&B\u0011\u0002V\u0006\u0005\b#\u0002\u0017\u0002X\u0006m\u0017bAAmO\t1A\u000f\u001b:poN\u0004B!a\u0013\u0002^&!\u0011q\\A'\u0005Uy%M[3diN#(/Z1n\u000bb\u001cW\r\u001d;j_:\u001c#!a7"
)
public interface Exprs {
   Expr$ Expr();

   static void $init$(final Exprs $this) {
   }

   public interface Expr extends Equals, Serializable {
      Mirror mirror();

      Expr in(final Mirror otherMirror);

      Trees.TreeApi tree();

      Types.TypeApi staticType();

      Types.TypeApi actualType();

      Object splice();

      Object value();

      default boolean canEqual(final Object x) {
         return x instanceof Expr;
      }

      default boolean equals(final Object x) {
         if (x instanceof Expr) {
            Mirror var10000 = this.mirror();
            Mirror var2 = ((Expr)x).mirror();
            if (var10000 == null) {
               if (var2 != null) {
                  return false;
               }
            } else if (!var10000.equals(var2)) {
               return false;
            }

            Trees.TreeApi var4 = this.tree();
            Trees.TreeApi var3 = ((Expr)x).tree();
            if (var4 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var4.equals(var3)) {
               return true;
            }
         }

         return false;
      }

      default int hashCode() {
         return this.mirror().hashCode() * 31 + this.tree().hashCode();
      }

      default String toString() {
         return (new StringBuilder(8)).append("Expr[").append(this.staticType()).append("](").append(this.tree()).append(")").toString();
      }

      // $FF: synthetic method
      Exprs scala$reflect$api$Exprs$Expr$$$outer();

      static void $init$(final Expr $this) {
      }
   }

   public class Expr$ implements Serializable {
      // $FF: synthetic field
      private final Universe $outer;

      public Expr apply(final Mirror mirror, final TreeCreator treec, final TypeTags.WeakTypeTag evidence$1) {
         return this.$outer.new ExprImpl(mirror, treec, evidence$1);
      }

      public Option unapply(final Expr expr) {
         return new Some(expr.tree());
      }

      public Expr$() {
         if (Exprs.this == null) {
            throw null;
         } else {
            this.$outer = Exprs.this;
            super();
         }
      }
   }

   private class ExprImpl implements Expr {
      private Trees.TreeApi tree;
      private Types.TypeApi staticType;
      private Object value;
      private final Mirror mirror;
      private final TreeCreator treec;
      private final TypeTags.WeakTypeTag evidence$2;
      private volatile byte bitmap$0;
      // $FF: synthetic field
      public final Universe $outer;

      public boolean canEqual(final Object x) {
         return Exprs.Expr.super.canEqual(x);
      }

      public boolean equals(final Object x) {
         return Exprs.Expr.super.equals(x);
      }

      public int hashCode() {
         return Exprs.Expr.super.hashCode();
      }

      public String toString() {
         return Exprs.Expr.super.toString();
      }

      public Mirror mirror() {
         return this.mirror;
      }

      public TreeCreator treec() {
         return this.treec;
      }

      public Expr in(final Mirror otherMirror) {
         TypeTags.WeakTypeTag tag1 = this.evidence$2.in(otherMirror);
         return otherMirror.universe().Expr().apply(otherMirror, this.treec(), tag1);
      }

      private Trees.TreeApi tree$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.tree = this.treec().apply(this.mirror());
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.tree;
      }

      public Trees.TreeApi tree() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.tree$lzycompute() : this.tree;
      }

      private Types.TypeApi staticType$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.staticType = this.evidence$2.tpe();
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.staticType;
      }

      public Types.TypeApi staticType() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.staticType$lzycompute() : this.staticType;
      }

      public Types.TypeApi actualType() {
         return this.tree().tpe();
      }

      public Object splice() {
         throw new UnsupportedOperationException(.MODULE$.stripMargin$extension("\n      |the function you're calling has not been spliced by the compiler.\n      |this means there is a cross-stage evaluation involved, and it needs to be invoked explicitly.\n      |if you're sure this is not an oversight, add scala-compiler.jar to the classpath,\n      |import `scala.tools.reflect.Eval` and call `<your expr>.eval` instead.".trim(), '|'));
      }

      private Object value$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               throw new UnsupportedOperationException(.MODULE$.stripMargin$extension("\n      |the value you're calling is only meant to be used in cross-stage path-dependent types.\n      |if you want to splice the underlying expression, use `<your expr>.splice`.\n      |if you want to get a value of the underlying expression, add scala-compiler.jar to the classpath,\n      |import `scala.tools.reflect.Eval` and call `<your expr>.eval` instead.".trim(), '|'));
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.value;
      }

      public Object value() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.value$lzycompute() : this.value;
      }

      private Object writeReplace() throws ObjectStreamException {
         return new SerializedExpr(this.treec(), this.evidence$2.in(((Mirrors)scala.reflect.runtime.package$.MODULE$.universe()).rootMirror()));
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Exprs$ExprImpl$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Exprs scala$reflect$api$Exprs$Expr$$$outer() {
         return this.scala$reflect$api$Exprs$ExprImpl$$$outer();
      }

      public ExprImpl(final Mirror mirror, final TreeCreator treec, final TypeTags.WeakTypeTag evidence$2) {
         this.mirror = mirror;
         this.treec = treec;
         this.evidence$2 = evidence$2;
         if (Exprs.this == null) {
            throw null;
         } else {
            this.$outer = Exprs.this;
            super();
         }
      }
   }
}
