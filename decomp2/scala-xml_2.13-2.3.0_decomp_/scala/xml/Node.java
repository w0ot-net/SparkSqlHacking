package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uu!\u0002\u0012$\u0011\u0003Ac!\u0002\u0016$\u0011\u0003Y\u0003\"\u0002\u001d\u0002\t\u0003I\u0004\"\u0002\u001e\u0002\t\u000bY\u0004bB\"\u0002\u0005\u0004%\t\u0001\u0012\u0005\u0007!\u0006\u0001\u000b\u0011B#\t\u000bE\u000bA\u0011\u0001*\t\u0013\u0005\u0015\u0015!!A\u0005\n\u0005\u001de!\u0002\u0016$\u0003\u00031\u0007\"\u0002\u001d\t\t\u0003Q\u0007\"B6\t\t\u0003!\u0005\"\u00027\t\r\u0003!\u0005\"B7\t\t\u0003q\u0007\"\u0002:\t\t\u0003q\u0007\"B:\t\t\u0003q\u0007\"\u0002;\t\t\u0003)\b\"B=\t\t\u0003!\u0005\"\u0002>\t\t\u0003Y\b\"\u0002@\t\t\u000by\bB\u0002@\t\t\u000b\t9\u0002C\u0004\u0002 !!\t!!\t\t\u000f\u0005\r\u0002B\"\u0001\u0002&!9\u0011q\u0005\u0005\u0005\u0002\u0005\u0015\u0002bBA\u0015\u0011\u0011\u0005\u00111\u0006\u0005\b\u0003wAA\u0011AA\u0016\u0011\u001d\ti\u0004\u0003C!\u0003\u007fAq!a\u0013\t\t#\ni\u0005C\u0004\u0002R!!\t%a\u0015\t\u000f\u0005u\u0003\u0002\"\u0011\u0002&!9\u0011q\f\u0005\u0005\u0002\u0005\u0005\u0004bBA4\u0011\u0011\u0005\u0013\u0011\u000e\u0005\b\u0003WBA\u0011AA7\u0011\u001d\tI\b\u0003C\u0001\u0003wBa!a!\t\t\u0003\"\u0015\u0001\u0002(pI\u0016T!\u0001J\u0013\u0002\u0007alGNC\u0001'\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!K\u0001\u000e\u0003\r\u0012AAT8eKN\u0019\u0011\u0001\f\u0019\u0011\u00055rS\"A\u0013\n\u0005=*#AB!osJ+g\r\u0005\u00022m5\t!G\u0003\u00024i\u0005\u0011\u0011n\u001c\u0006\u0002k\u0005!!.\u0019<b\u0013\t9$G\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002Q\u0005aaj\\!uiJL'-\u001e;fgV\tA\b\u0005\u0002>\u0001:\u0011\u0011FP\u0005\u0003\u007f\r\nqdU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c'+\u001a;ve:$\u0016\u0010]3t\u0013\t\t%I\u0001\tO_\u0012,gj\\!uiJL'-\u001e;fg*\u0011qhI\u0001\u000f\u000b6\u0004H/\u001f(b[\u0016\u001c\b/Y2f+\u0005)\u0005C\u0001$N\u001d\t95\n\u0005\u0002IK5\t\u0011J\u0003\u0002KO\u00051AH]8pizJ!\u0001T\u0013\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019\u0016\nq\"R7qift\u0015-\\3ta\u0006\u001cW\rI\u0001\u000bk:\f\u0007\u000f\u001d7z'\u0016\fHCA*d!\riCKV\u0005\u0003+\u0016\u0012AaU8nKB)QfV#Z9&\u0011\u0001,\n\u0002\u0007)V\u0004H.Z\u001a\u0011\u0005%R\u0016BA.$\u0005!iU\r^1ECR\f\u0007CA/a\u001d\tIc,\u0003\u0002`G\u0005!2kY1mCZ+'o]5p]N\u0003XmY5gS\u000eL!!\u00192\u0003#M+\u0017OT8eKVs\u0017\r\u001d9msN+\u0017O\u0003\u0002`G!)AM\u0002a\u0001K\u0006\ta\u000e\u0005\u0002*\u0011M\u0011\u0001b\u001a\t\u0003S!L!![\u0012\u0003\u000f9{G-Z*fcR\tQ-\u0001\u0004qe\u00164\u0017\u000e_\u0001\u0006Y\u0006\u0014W\r\\\u0001\u0007SN\fEo\\7\u0016\u0003=\u0004\"!\f9\n\u0005E,#a\u0002\"p_2,\u0017M\\\u0001\u0014I>\u001cu\u000e\u001c7fGRt\u0015-\\3ta\u0006\u001cWm]\u0001\fI>$&/\u00198tM>\u0014X.A\u0003tG>\u0004X-F\u0001w!\tIs/\u0003\u0002yG\t\u0001b*Y7fgB\f7-\u001a\"j]\u0012LgnZ\u0001\n]\u0006lWm\u001d9bG\u0016\fAbZ3u\u001d\u0006lWm\u001d9bG\u0016$\"!\u0012?\t\u000bu\f\u0002\u0019A#\u0002\u0007A\u0014X-A\u0005biR\u0014\u0018NY;uKR!\u0011\u0011AA\n!\u0015i\u00131AA\u0004\u0013\r\t)!\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0005%\u0011qB3\u000e\u0005\u0005-!bAA\u0007K\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00111\u0002\u0002\u0004'\u0016\f\bBBA\u000b%\u0001\u0007Q)A\u0002lKf$b!!\u0001\u0002\u001a\u0005u\u0001BBA\u000e'\u0001\u0007Q)A\u0002ve&Da!!\u0006\u0014\u0001\u0004)\u0015AC1uiJL'-\u001e;fgV\t\u0011,A\u0003dQ&dG-\u0006\u0002\u0002\b\u0005\u0001bn\u001c8F[B$\u0018p\u00115jY\u0012\u0014XM\\\u0001\u000bI\u0016\u001c8-\u001a8eC:$XCAA\u0017!\u0015\ty#!\u000ef\u001d\ri\u0013\u0011G\u0005\u0004\u0003g)\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003o\tID\u0001\u0003MSN$(bAA\u001aK\u0005\u0011B-Z:dK:$\u0017M\u001c;`_J|6/\u001a7g\u0003!\u0019\u0017M\\#rk\u0006dGcA8\u0002B!9\u00111I\rA\u0002\u0005\u0015\u0013!B8uQ\u0016\u0014\bcA\u0017\u0002H%\u0019\u0011\u0011J\u0013\u0003\u0007\u0005s\u00170\u0001\tcCNL7OR8s\u0011\u0006\u001c\bnQ8eKV\u0011\u0011q\n\t\u0007\u0003\u0013\ty!!\u0012\u0002\u001bM$(/[2u?\u0012*\u0017\u000fJ3r)\ry\u0017Q\u000b\u0005\b\u0003\u0007Z\u0002\u0019AA,!\rI\u0013\u0011L\u0005\u0004\u00037\u001a#\u0001C#rk\u0006d\u0017\u000e^=\u0002\rQDWmU3r\u0003-\u0011W/\u001b7e'R\u0014\u0018N\\4\u0015\u0007\u0015\u000b\u0019\u0007\u0003\u0004\u0002fu\u0001\ra\\\u0001\u000egR\u0014\u0018\u000e]\"p[6,g\u000e^:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!R\u0001\r]\u0006lW\rV8TiJLgn\u001a\u000b\u0005\u0003_\n)\b\u0005\u0003\u00020\u0005E\u0014\u0002BA:\u0003s\u0011Qb\u0015;sS:<')^5mI\u0016\u0014\bbBA<?\u0001\u0007\u0011qN\u0001\u0003g\n\fq\u0001_7m)f\u0004X-\u0006\u0002\u0002~A\u0019\u0011&a \n\u0007\u0005\u00055E\u0001\u0006UsB,7+_7c_2\fA\u0001^3yi\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0012\t\u0005\u0003\u0017\u000b\t*\u0004\u0002\u0002\u000e*\u0019\u0011q\u0012\u001b\u0002\t1\fgnZ\u0005\u0005\u0003'\u000biI\u0001\u0004PE*,7\r\u001e"
)
public abstract class Node extends NodeSeq {
   public static Some unapplySeq(final Node n) {
      return Node$.MODULE$.unapplySeq(n);
   }

   public static String EmptyNamespace() {
      return Node$.MODULE$.EmptyNamespace();
   }

   public static MetaData NoAttributes() {
      return Node$.MODULE$.NoAttributes();
   }

   public String prefix() {
      return null;
   }

   public abstract String label();

   public boolean isAtom() {
      return this instanceof Atom;
   }

   public boolean doCollectNamespaces() {
      return true;
   }

   public boolean doTransform() {
      return true;
   }

   public NamespaceBinding scope() {
      return TopScope$.MODULE$;
   }

   public String namespace() {
      return this.getNamespace(this.prefix());
   }

   public String getNamespace(final String pre) {
      return this.scope() == null ? null : this.scope().getURI(pre);
   }

   public final Option attribute(final String key) {
      return this.attributes().get(key);
   }

   public final Option attribute(final String uri, final String key) {
      return this.attributes().get(uri, this, key);
   }

   public MetaData attributes() {
      return Null$.MODULE$;
   }

   public abstract Seq child();

   public Seq nonEmptyChildren() {
      return (Seq)this.child().filterNot((x$1) -> BoxesRunTime.boxToBoolean($anonfun$nonEmptyChildren$1(x$1)));
   }

   public List descendant() {
      return this.child().toList().flatMap((x) -> x.descendant().$colon$colon(x));
   }

   public List descendant_or_self() {
      return this.descendant().$colon$colon(this);
   }

   public boolean canEqual(final Object other) {
      if (other instanceof Group) {
         return false;
      } else {
         return other instanceof Node;
      }
   }

   public Seq basisForHashCode() {
      String var1 = this.prefix();
      String var2 = this.label();
      MetaData var3 = this.attributes();
      return this.nonEmptyChildren().toList().$colon$colon(var3).$colon$colon(var2).$colon$colon(var1);
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (other instanceof Group) {
         return false;
      } else if (!(other instanceof Node)) {
         return false;
      } else {
         boolean var10;
         label50: {
            Node var4 = (Node)other;
            String var10000 = this.prefix();
            String var5 = var4.prefix();
            if (var10000 == null) {
               if (var5 != null) {
                  break label50;
               }
            } else if (!var10000.equals(var5)) {
               break label50;
            }

            var10000 = this.label();
            String var6 = var4.label();
            if (var10000 == null) {
               if (var6 != null) {
                  break label50;
               }
            } else if (!var10000.equals(var6)) {
               break label50;
            }

            MetaData var9 = this.attributes();
            MetaData var7 = var4.attributes();
            if (var9 == null) {
               if (var7 != null) {
                  break label50;
               }
            } else if (!var9.equals(var7)) {
               break label50;
            }

            if (this.nonEmptyChildren().sameElements(var4.nonEmptyChildren())) {
               var10 = true;
               return var10;
            }
         }

         var10 = false;
         return var10;
      }
   }

   public Seq theSeq() {
      return .MODULE$.$colon$colon(this);
   }

   public String buildString(final boolean stripComments) {
      NamespaceBinding x$3 = Utility$.MODULE$.serialize$default$2();
      StringBuilder x$4 = Utility$.MODULE$.serialize$default$3();
      boolean x$5 = Utility$.MODULE$.serialize$default$5();
      boolean x$6 = Utility$.MODULE$.serialize$default$6();
      Enumeration.Value x$7 = Utility$.MODULE$.serialize$default$7();
      return Utility$.MODULE$.serialize(this, x$3, x$4, stripComments, x$5, x$6, x$7).toString();
   }

   public String toString() {
      return this.buildString(false);
   }

   public StringBuilder nameToString(final StringBuilder sb) {
      return sb.append((new java.lang.StringBuilder(0)).append(this.prefix() == null ? "" : (new java.lang.StringBuilder(1)).append(this.prefix()).append(":").toString()).append(this.label()).toString());
   }

   public TypeSymbol xmlType() {
      return null;
   }

   public String text() {
      return super.text();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$nonEmptyChildren$1(final Node x$1) {
      return x$1.toString().isEmpty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
