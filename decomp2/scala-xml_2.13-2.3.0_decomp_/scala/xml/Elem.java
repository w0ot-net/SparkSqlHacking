package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mu!B\u0011#\u0011\u00039c!B\u0015#\u0011\u0003Q\u0003\"B\u001c\u0002\t\u0003A\u0004\"B\u001d\u0002\t\u0003Q\u0004bBA1\u0003\u0011\u0005\u00111\r\u0005\n\u0003\u0007\u000b\u0011\u0011!C\u0005\u0003\u000b3A!\u000b\u0012\u0001y!AaI\u0002BC\u0002\u0013\u0005s\t\u0003\u0005T\r\t\u0005\t\u0015!\u0003I\u0011!!fA!b\u0001\n\u0003:\u0005\u0002C+\u0007\u0005\u0003\u0005\u000b\u0011\u0002%\t\u0011Y3!\u0011!Q\u0001\n]C\u0001B\u0017\u0004\u0003\u0006\u0004%\te\u0017\u0005\t?\u001a\u0011\t\u0011)A\u00059\"A\u0001M\u0002BC\u0002\u0013\u0005\u0011\r\u0003\u0005f\r\t\u0005\t\u0015!\u0003c\u0011!1gA!b\u0001\n\u0003:\u0007\u0002C6\u0007\u0005\u0003\u0005\u000b\u0011\u00025\t\u000b]2A\u0011\u00017\t\u000bM4AQI1\t\u000bQ4AQI1\t\u000fU4!\u0019!C!m\"1qO\u0002Q\u0001\n]CQ\u0001\u001f\u0004\u0005ReDq!a\u0002\u0007\t\u000b\tI\u0001C\u0004\u0002\u0010\u0019!\t!!\u0005\t\u0013\u0005\u0005b!%A\u0005\u0002\u0005\r\u0002\"CA\u001d\rE\u0005I\u0011AA\u0012\u0011%\tYDBI\u0001\n\u0003\ti\u0004C\u0005\u0002B\u0019\t\n\u0011\"\u0001\u0002D!I\u0011q\t\u0004\u0012\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003\u001b2\u0011\u0013!C\u0001\u0003\u001fBa!a\u0015\u0007\t\u0003:\u0015\u0001B#mK6T!a\t\u0013\u0002\u0007alGNC\u0001&\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"\u0001K\u0001\u000e\u0003\t\u0012A!\u00127f[N\u0019\u0011aK\u0018\u0011\u00051jS\"\u0001\u0013\n\u00059\"#AB!osJ+g\r\u0005\u00021k5\t\u0011G\u0003\u00023g\u0005\u0011\u0011n\u001c\u0006\u0002i\u0005!!.\u0019<b\u0013\t1\u0014G\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002O\u0005)\u0011\r\u001d9msRi1(!\u0016\u0002X\u0005e\u00131LA/\u0003?\u0002\"\u0001\u000b\u0004\u0014\u0007\u0019i\u0004\t\u0005\u0002)}%\u0011qH\t\u0002\u0005\u001d>$W\r\u0005\u0002B\t:\u0011AFQ\u0005\u0003\u0007\u0012\nq\u0001]1dW\u0006<W-\u0003\u00027\u000b*\u00111\tJ\u0001\u0007aJ,g-\u001b=\u0016\u0003!\u0003\"!\u0013)\u000f\u0005)s\u0005CA&%\u001b\u0005a%BA''\u0003\u0019a$o\\8u}%\u0011q\nJ\u0001\u0007!J,G-\u001a4\n\u0005E\u0013&AB*ue&twM\u0003\u0002PI\u00059\u0001O]3gSb\u0004\u0013!\u00027bE\u0016d\u0017A\u00027bE\u0016d\u0007%A\u0006biR\u0014\u0018NY;uKN\f\u0004C\u0001\u0015Y\u0013\tI&E\u0001\u0005NKR\fG)\u0019;b\u0003\u0015\u00198m\u001c9f+\u0005a\u0006C\u0001\u0015^\u0013\tq&E\u0001\tOC6,7\u000f]1dK\nKg\u000eZ5oO\u000611oY8qK\u0002\nQ\"\\5oS6L'0Z#naRLX#\u00012\u0011\u00051\u001a\u0017B\u00013%\u0005\u001d\u0011un\u001c7fC:\fa\"\\5oS6L'0Z#naRL\b%A\u0003dQ&dG-F\u0001i!\ra\u0013.P\u0005\u0003U\u0012\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003\u0019\u0019\u0007.\u001b7eAQ91(\u001c8paF\u0014\b\"\u0002$\u0013\u0001\u0004A\u0005\"\u0002+\u0013\u0001\u0004A\u0005\"\u0002,\u0013\u0001\u00049\u0006\"\u0002.\u0013\u0001\u0004a\u0006\"\u00021\u0013\u0001\u0004\u0011\u0007\"\u00024\u0013\u0001\u0004A\u0017a\u00053p\u0007>dG.Z2u\u001d\u0006lWm\u001d9bG\u0016\u001c\u0018a\u00033p)J\fgn\u001d4pe6\f!\"\u0019;ue&\u0014W\u000f^3t+\u00059\u0016aC1uiJL'-\u001e;fg\u0002\n\u0001CY1tSN4uN\u001d%bg\"\u001cu\u000eZ3\u0016\u0003i\u0004Ba\u001f@\u0002\u00025\tAP\u0003\u0002~I\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005}d(aA*fcB\u0019A&a\u0001\n\u0007\u0005\u0015AEA\u0002B]f\f\u0001\u0002\n9fe\u000e,g\u000e\u001e\u000b\u0004w\u0005-\u0001BBA\u00071\u0001\u0007q+A\u0004va\u0012\fG/Z:\u0002\t\r|\u0007/\u001f\u000b\u000ew\u0005M\u0011QCA\f\u00033\tY\"!\b\t\u000f\u0019K\u0002\u0013!a\u0001\u0011\"9A+\u0007I\u0001\u0002\u0004A\u0005bB;\u001a!\u0003\u0005\ra\u0016\u0005\b5f\u0001\n\u00111\u0001]\u0011\u001d\u0001\u0017\u0004%AA\u0002\tD\u0001BZ\r\u0011\u0002\u0003\u0007\u0011q\u0004\t\u0004wzl\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003KQ3\u0001SA\u0014W\t\tI\u0003\u0005\u0003\u0002,\u0005URBAA\u0017\u0015\u0011\ty#!\r\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001aI\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005]\u0012Q\u0006\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\tyDK\u0002X\u0003O\tabY8qs\u0012\"WMZ1vYR$C'\u0006\u0002\u0002F)\u001aA,a\n\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u00111\n\u0016\u0004E\u0006\u001d\u0012AD2paf$C-\u001a4bk2$HEN\u000b\u0003\u0003#RC!a\b\u0002(\u0005!A/\u001a=u\u0011\u001515\u00011\u0001I\u0011\u0015!6\u00011\u0001I\u0011\u0015)8\u00011\u0001X\u0011\u0015Q6\u00011\u0001]\u0011\u0015\u00017\u00011\u0001c\u0011\u001517\u00011\u0001i\u0003))h.\u00199qYf\u001cV-\u001d\u000b\u0005\u0003K\ny\bE\u0003-\u0003O\nY'C\u0002\u0002j\u0011\u0012aa\u00149uS>t\u0007#\u0003\u0017\u0002n!Cu\u000bXA9\u0013\r\ty\u0007\n\u0002\u0007)V\u0004H.Z\u001b\u0011\t\u0005M\u0014\u0011\u0010\b\u0004Q\u0005U\u0014bAA<E\u0005!2kY1mCZ+'o]5p]N\u0003XmY5gS\u000eLA!a\u001f\u0002~\t\t2+Z9O_\u0012,WK\\1qa2L8+Z9\u000b\u0007\u0005]$\u0005\u0003\u0004\u0002\u0002\u0012\u0001\r!P\u0001\u0002]\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0011\t\u0005\u0003\u0013\u000by)\u0004\u0002\u0002\f*\u0019\u0011QR\u001a\u0002\t1\fgnZ\u0005\u0005\u0003#\u000bYI\u0001\u0004PE*,7\r\u001e"
)
public class Elem extends Node {
   private final String prefix;
   private final String label;
   private final NamespaceBinding scope;
   private final boolean minimizeEmpty;
   private final Seq child;
   private final MetaData attributes;

   public static Option unapplySeq(final Node n) {
      return Elem$.MODULE$.unapplySeq(n);
   }

   public String prefix() {
      return this.prefix;
   }

   public String label() {
      return this.label;
   }

   public NamespaceBinding scope() {
      return this.scope;
   }

   public boolean minimizeEmpty() {
      return this.minimizeEmpty;
   }

   public Seq child() {
      return this.child;
   }

   public final boolean doCollectNamespaces() {
      return true;
   }

   public final boolean doTransform() {
      return true;
   }

   public MetaData attributes() {
      return this.attributes;
   }

   public scala.collection.Seq basisForHashCode() {
      String var1 = this.prefix();
      String var2 = this.label();
      MetaData var3 = this.attributes();
      return this.child().toList().$colon$colon(var3).$colon$colon(var2).$colon$colon(var1);
   }

   public final Elem $percent(final MetaData updates) {
      MetaData x$1 = MetaData$.MODULE$.update(this.attributes(), this.scope(), updates);
      String x$2 = this.copy$default$1();
      String x$3 = this.copy$default$2();
      NamespaceBinding x$4 = this.copy$default$4();
      boolean x$5 = this.copy$default$5();
      scala.collection.Seq x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$1, x$4, x$5, x$6);
   }

   public Elem copy(final String prefix, final String label, final MetaData attributes, final NamespaceBinding scope, final boolean minimizeEmpty, final scala.collection.Seq child) {
      return Elem$.MODULE$.apply(prefix, label, attributes, scope, minimizeEmpty, NodeSeq$.MODULE$.seqToNodeSeq(child));
   }

   public String copy$default$1() {
      return this.prefix();
   }

   public String copy$default$2() {
      return this.label();
   }

   public MetaData copy$default$3() {
      return this.attributes();
   }

   public NamespaceBinding copy$default$4() {
      return this.scope();
   }

   public boolean copy$default$5() {
      return this.minimizeEmpty();
   }

   public scala.collection.Seq copy$default$6() {
      return this.child();
   }

   public String text() {
      return ((IterableOnceOps)this.child().map((x$1) -> x$1.text())).mkString();
   }

   public Elem(final String prefix, final String label, final MetaData attributes1, final NamespaceBinding scope, final boolean minimizeEmpty, final Seq child) {
      this.prefix = prefix;
      this.label = label;
      this.scope = scope;
      this.minimizeEmpty = minimizeEmpty;
      this.child = child;
      this.attributes = MetaData$.MODULE$.normalize(attributes1, scope);
      String var7 = "";
      if (prefix == null) {
         if (var7 == null) {
            throw new IllegalArgumentException("prefix of zero length, use null instead");
         }
      } else if (prefix.equals(var7)) {
         throw new IllegalArgumentException("prefix of zero length, use null instead");
      }

      if (scope == null) {
         throw new IllegalArgumentException("scope is null, use scala.xml.TopScope for empty scope");
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
