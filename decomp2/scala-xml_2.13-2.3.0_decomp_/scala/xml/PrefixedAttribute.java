package scala.xml;

import scala.Option;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001B\f\u0019\u0001uA\u0001\"\n\u0001\u0003\u0006\u0004%\tE\n\u0005\te\u0001\u0011\t\u0011)A\u0005O!A1\u0007\u0001BC\u0002\u0013\u0005c\u0005\u0003\u00055\u0001\t\u0005\t\u0015!\u0003(\u0011!)\u0004A!b\u0001\n\u00032\u0004\u0002\u0003!\u0001\u0005\u0003\u0005\u000b\u0011B\u001c\t\u0011\u0005\u0003!Q1A\u0005\u0002\tC\u0001b\u0011\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006\t\u0002!\t!\u0012\u0005\b\u0017\u0002\u0011\r\u0011\"\u0011C\u0011\u0019a\u0005\u0001)A\u0005=!)A\t\u0001C\u0001\u001b\")A\t\u0001C\u0001%\")1\f\u0001C!9\")a\f\u0001C!?\")!\r\u0001C!G\")!\r\u0001C!K\u001e)a\u000e\u0007E\u0001_\u001a)q\u0003\u0007E\u0001a\")Ai\u0005C\u0001y\")Qp\u0005C\u0001}\"I\u0011qB\n\u0002\u0002\u0013%\u0011\u0011\u0003\u0002\u0012!J,g-\u001b=fI\u0006#HO]5ckR,'BA\r\u001b\u0003\rAX\u000e\u001c\u0006\u00027\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u001fEA\u0011q\u0004I\u0007\u00021%\u0011\u0011\u0005\u0007\u0002\t\u001b\u0016$\u0018\rR1uCB\u0011qdI\u0005\u0003Ia\u0011\u0011\"\u0011;ue&\u0014W\u000f^3\u0002\u0007A\u0014X-F\u0001(!\tAsF\u0004\u0002*[A\u0011!FG\u0007\u0002W)\u0011A\u0006H\u0001\u0007yI|w\u000e\u001e \n\u00059R\u0012A\u0002)sK\u0012,g-\u0003\u00021c\t11\u000b\u001e:j]\u001eT!A\f\u000e\u0002\tA\u0014X\rI\u0001\u0004W\u0016L\u0018\u0001B6fs\u0002\nQA^1mk\u0016,\u0012a\u000e\t\u0004qmjT\"A\u001d\u000b\u0005iR\u0012AC2pY2,7\r^5p]&\u0011A(\u000f\u0002\u0004'\u0016\f\bCA\u0010?\u0013\ty\u0004D\u0001\u0003O_\u0012,\u0017A\u0002<bYV,\u0007%A\u0003oKb$\u0018'F\u0001\u001f\u0003\u0019qW\r\u001f;2A\u00051A(\u001b8jiz\"RAR$I\u0013*\u0003\"a\b\u0001\t\u000b\u0015J\u0001\u0019A\u0014\t\u000bMJ\u0001\u0019A\u0014\t\u000bUJ\u0001\u0019A\u001c\t\u000b\u0005K\u0001\u0019\u0001\u0010\u0002\t9,\u0007\u0010^\u0001\u0006]\u0016DH\u000f\t\u000b\u0006\r:{\u0005+\u0015\u0005\u0006K1\u0001\ra\n\u0005\u0006g1\u0001\ra\n\u0005\u0006k1\u0001\ra\n\u0005\u0006\u00172\u0001\rA\b\u000b\u0006\rN#VK\u0017\u0005\u0006K5\u0001\ra\n\u0005\u0006g5\u0001\ra\n\u0005\u0006k5\u0001\rA\u0016\t\u0004/b;T\"\u0001\u000e\n\u0005eS\"AB(qi&|g\u000eC\u0003L\u001b\u0001\u0007a$\u0001\u0003d_BLHC\u0001$^\u0011\u0015Ye\u00021\u0001\u001f\u000319W\r\u001e(b[\u0016\u001c\b/Y2f)\t9\u0003\rC\u0003b\u001f\u0001\u0007Q(A\u0003po:,'/A\u0003baBd\u0017\u0010\u0006\u00028I\")1\u0007\u0005a\u0001OQ!qG\u001a5n\u0011\u00159\u0017\u00031\u0001(\u0003%q\u0017-\\3ta\u0006\u001cW\rC\u0003j#\u0001\u0007!.A\u0003tG>\u0004X\r\u0005\u0002 W&\u0011A\u000e\u0007\u0002\u0011\u001d\u0006lWm\u001d9bG\u0016\u0014\u0015N\u001c3j]\u001eDQaM\tA\u0002\u001d\n\u0011\u0003\u0015:fM&DX\rZ!uiJL'-\u001e;f!\ty2cE\u0002\u0014cR\u0004\"a\u0016:\n\u0005MT\"AB!osJ+g\r\u0005\u0002vu6\taO\u0003\u0002xq\u0006\u0011\u0011n\u001c\u0006\u0002s\u0006!!.\u0019<b\u0013\tYhO\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001p\u0003\u001d)h.\u00199qYf$2a`A\u0006!\u00159\u0016\u0011AA\u0003\u0013\r\t\u0019A\u0007\u0002\u0005'>lW\rE\u0004X\u0003\u000f9se\u000e\u0010\n\u0007\u0005%!D\u0001\u0004UkBdW\r\u000e\u0005\u0007\u0003\u001b)\u0002\u0019\u0001$\u0002\u0003a\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0005\u0011\t\u0005U\u00111D\u0007\u0003\u0003/Q1!!\u0007y\u0003\u0011a\u0017M\\4\n\t\u0005u\u0011q\u0003\u0002\u0007\u001f\nTWm\u0019;"
)
public class PrefixedAttribute extends MetaData implements Attribute {
   private final String pre;
   private final String key;
   private final Seq value;
   private final MetaData next1;
   private final MetaData next;

   public static Some unapply(final PrefixedAttribute x) {
      return PrefixedAttribute$.MODULE$.unapply(x);
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

   public String pre() {
      return this.pre;
   }

   public String key() {
      return this.key;
   }

   public Seq value() {
      return this.value;
   }

   public MetaData next1() {
      return this.next1;
   }

   public MetaData next() {
      return this.next;
   }

   public PrefixedAttribute copy(final MetaData next) {
      return new PrefixedAttribute(this.pre(), this.key(), this.value(), next);
   }

   public String getNamespace(final Node owner) {
      return owner.getNamespace(this.pre());
   }

   public Seq apply(final String key) {
      return this.next().apply(key);
   }

   public Seq apply(final String namespace, final NamespaceBinding scope, final String key) {
      String var4 = this.key();
      if (key == null) {
         if (var4 != null) {
            return this.next().apply(namespace, scope, key);
         }
      } else if (!key.equals(var4)) {
         return this.next().apply(namespace, scope, key);
      }

      String var10000 = scope.getURI(this.pre());
      if (var10000 == null) {
         if (namespace == null) {
            return this.value();
         }
      } else if (var10000.equals(namespace)) {
         return this.value();
      }

      return this.next().apply(namespace, scope, key);
   }

   public PrefixedAttribute(final String pre, final String key, final Seq value, final MetaData next1) {
      this.pre = pre;
      this.key = key;
      this.value = value;
      this.next1 = next1;
      Attribute.$init$(this);
      this.next = value != null ? next1 : next1.remove(key);
   }

   public PrefixedAttribute(final String pre, final String key, final String value, final MetaData next) {
      this(pre, key, (Seq)(value != null ? Text$.MODULE$.apply(value) : null), next);
   }

   public PrefixedAttribute(final String pre, final String key, final Option value, final MetaData next) {
      this(pre, key, (Seq)value.orNull(.MODULE$.refl()), next);
   }
}
