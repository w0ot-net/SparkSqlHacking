package scala.xml;

import scala.Option;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua\u0001\u0002\f\u0018\u0001qA\u0001\u0002\n\u0001\u0003\u0006\u0004%\t%\n\u0005\tc\u0001\u0011\t\u0011)A\u0005M!A!\u0007\u0001BC\u0002\u0013\u00053\u0007\u0003\u0005>\u0001\t\u0005\t\u0015!\u00035\u0011!q\u0004A!A!\u0002\u0013i\u0002\"B \u0001\t\u0003\u0001\u0005bB#\u0001\u0005\u0004%)E\u0012\u0005\u0007\u0017\u0002\u0001\u000bQB$\t\u000f1\u0003!\u0019!C!\u001b\"1a\n\u0001Q\u0001\nuAQa\u0010\u0001\u0005\u0002=CQa\u0010\u0001\u0005\u0002MCQA\u0017\u0001\u0005BmCQ!\u0018\u0001\u0005FyCQ!\u0019\u0001\u0005B\tDQ!\u0019\u0001\u0005B\u0011<Q!\\\f\t\u000294QAF\f\t\u0002=DQa\u0010\n\u0005\u0002mDQ\u0001 \n\u0005\u0002uD\u0011\"!\u0004\u0013\u0003\u0003%I!a\u0004\u0003'Us\u0007O]3gSb,G-\u0011;ue&\u0014W\u000f^3\u000b\u0005aI\u0012a\u0001=nY*\t!$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001i\u0012\u0005\u0005\u0002\u001f?5\tq#\u0003\u0002!/\tAQ*\u001a;b\t\u0006$\u0018\r\u0005\u0002\u001fE%\u00111e\u0006\u0002\n\u0003R$(/\u001b2vi\u0016\f1a[3z+\u00051\u0003CA\u0014/\u001d\tAC\u0006\u0005\u0002*35\t!F\u0003\u0002,7\u00051AH]8pizJ!!L\r\u0002\rA\u0013X\rZ3g\u0013\ty\u0003G\u0001\u0004TiJLgn\u001a\u0006\u0003[e\tAa[3zA\u0005)a/\u00197vKV\tA\u0007E\u00026qij\u0011A\u000e\u0006\u0003oe\t!bY8mY\u0016\u001cG/[8o\u0013\tIdGA\u0002TKF\u0004\"AH\u001e\n\u0005q:\"\u0001\u0002(pI\u0016\faA^1mk\u0016\u0004\u0013!\u00028fqR\f\u0014A\u0002\u001fj]&$h\b\u0006\u0003B\u0005\u000e#\u0005C\u0001\u0010\u0001\u0011\u0015!c\u00011\u0001'\u0011\u0015\u0011d\u00011\u00015\u0011\u0015qd\u00011\u0001\u001e\u0003\r\u0001(/Z\u000b\u0002\u000fB\u0011\u0001*S\u0007\u00023%\u0011!*\u0007\u0002\u0005\u001dVdG.\u0001\u0003qe\u0016\u0004\u0013\u0001\u00028fqR,\u0012!H\u0001\u0006]\u0016DH\u000f\t\u000b\u0005\u0003B\u000b&\u000bC\u0003%\u0017\u0001\u0007a\u0005C\u00033\u0017\u0001\u0007a\u0005C\u0003M\u0017\u0001\u0007Q\u0004\u0006\u0003B)VK\u0006\"\u0002\u0013\r\u0001\u00041\u0003\"\u0002\u001a\r\u0001\u00041\u0006c\u0001%Xi%\u0011\u0001,\u0007\u0002\u0007\u001fB$\u0018n\u001c8\t\u000b1c\u0001\u0019A\u000f\u0002\t\r|\u0007/\u001f\u000b\u0003\u0003rCQ\u0001T\u0007A\u0002u\tAbZ3u\u001d\u0006lWm\u001d9bG\u0016$\"AJ0\t\u000b\u0001t\u0001\u0019\u0001\u001e\u0002\u000b=<h.\u001a:\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005Q\u001a\u0007\"\u0002\u0013\u0010\u0001\u00041C\u0003\u0002\u001bfO2DQA\u001a\tA\u0002\u0019\n\u0011B\\1nKN\u0004\u0018mY3\t\u000b!\u0004\u0002\u0019A5\u0002\u000bM\u001cw\u000e]3\u0011\u0005yQ\u0017BA6\u0018\u0005Aq\u0015-\\3ta\u0006\u001cWMQ5oI&tw\rC\u0003%!\u0001\u0007a%A\nV]B\u0014XMZ5yK\u0012\fE\u000f\u001e:jEV$X\r\u0005\u0002\u001f%M\u0019!\u0003]:\u0011\u0005!\u000b\u0018B\u0001:\u001a\u0005\u0019\te.\u001f*fMB\u0011A/_\u0007\u0002k*\u0011ao^\u0001\u0003S>T\u0011\u0001_\u0001\u0005U\u00064\u0018-\u0003\u0002{k\na1+\u001a:jC2L'0\u00192mKR\ta.A\u0004v]\u0006\u0004\b\u000f\\=\u0015\u0007y\fI\u0001\u0005\u0003I\u007f\u0006\r\u0011bAA\u00013\t!1k\\7f!\u0019A\u0015Q\u0001\u00145;%\u0019\u0011qA\r\u0003\rQ+\b\u000f\\34\u0011\u0019\tY\u0001\u0006a\u0001\u0003\u0006\t\u00010\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0012A!\u00111CA\r\u001b\t\t)BC\u0002\u0002\u0018]\fA\u0001\\1oO&!\u00111DA\u000b\u0005\u0019y%M[3di\u0002"
)
public class UnprefixedAttribute extends MetaData implements Attribute {
   private final String key;
   private final Seq value;
   private final scala.runtime.Null pre;
   private final MetaData next;

   public static Some unapply(final UnprefixedAttribute x) {
      return UnprefixedAttribute$.MODULE$.unapply(x);
   }

   public MetaData remove(final String key) {
      return Attribute.remove$(this, key);
   }

   public MetaData remove(final String namespace, final NamespaceBinding scope, final String key) {
      return Attribute.remove$(this, namespace, scope, key);
   }

   public boolean isPrefixed() {
      return Attribute.isPrefixed$(this);
   }

   public boolean wellformed(final NamespaceBinding scope) {
      return Attribute.wellformed$(this, scope);
   }

   public Iterator iterator() {
      return Attribute.iterator$(this);
   }

   public int size() {
      return Attribute.size$(this);
   }

   public void toString1(final StringBuilder sb) {
      Attribute.toString1$(this, sb);
   }

   public String key() {
      return this.key;
   }

   public Seq value() {
      return this.value;
   }

   public final scala.runtime.Null pre() {
      return this.pre;
   }

   public MetaData next() {
      return this.next;
   }

   public UnprefixedAttribute copy(final MetaData next) {
      return new UnprefixedAttribute(this.key(), this.value(), next);
   }

   public final String getNamespace(final Node owner) {
      return null;
   }

   public Seq apply(final String key) {
      String var2 = this.key();
      if (key == null) {
         if (var2 == null) {
            return this.value();
         }
      } else if (key.equals(var2)) {
         return this.value();
      }

      return this.next().apply(key);
   }

   public Seq apply(final String namespace, final NamespaceBinding scope, final String key) {
      return this.next().apply(namespace, scope, key);
   }

   public UnprefixedAttribute(final String key, final Seq value, final MetaData next1) {
      this.key = key;
      this.value = value;
      Attribute.$init$(this);
      this.pre = null;
      this.next = value != null ? next1 : next1.remove(key);
   }

   public UnprefixedAttribute(final String key, final String value, final MetaData next) {
      this(key, (Seq)(value != null ? Text$.MODULE$.apply(value) : null), next);
   }

   public UnprefixedAttribute(final String key, final Option value, final MetaData next) {
      this(key, (Seq)value.orNull(.MODULE$.refl()), next);
   }
}
