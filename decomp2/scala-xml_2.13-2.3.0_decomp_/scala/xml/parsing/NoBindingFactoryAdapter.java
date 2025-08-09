package scala.xml.parsing;

import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.xml.Elem;
import scala.xml.Elem$;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.Node;
import scala.xml.NodeSeq$;
import scala.xml.PCData;
import scala.xml.Text;
import scala.xml.factory.NodeFactory;

@ScalaSignature(
   bytes = "\u0006\u0005u4A!\u0003\u0006\u0001#!)\u0001\u0005\u0001C\u0001C!)1\u0005\u0001C!I!)a\u0007\u0001C)o!)\u0001\u000b\u0001C!#\")a\f\u0001C!?\")Q\r\u0001C!M\")q\u000e\u0001C!a\")q\u000f\u0001C!q\n9bj\u001c\"j]\u0012Lgn\u001a$bGR|'/_!eCB$XM\u001d\u0006\u0003\u00171\tq\u0001]1sg&twM\u0003\u0002\u000e\u001d\u0005\u0019\u00010\u001c7\u000b\u0003=\tQa]2bY\u0006\u001c\u0001aE\u0002\u0001%Y\u0001\"a\u0005\u000b\u000e\u0003)I!!\u0006\u0006\u0003\u001d\u0019\u000b7\r^8ss\u0006#\u0017\r\u001d;feB\u0019qC\u0007\u000f\u000e\u0003aQ!!\u0007\u0007\u0002\u000f\u0019\f7\r^8ss&\u00111\u0004\u0007\u0002\f\u001d>$WMR1di>\u0014\u0018\u0010\u0005\u0002\u001e=5\tA\"\u0003\u0002 \u0019\t!Q\t\\3n\u0003\u0019a\u0014N\\5u}Q\t!\u0005\u0005\u0002\u0014\u0001\u0005\u0001bn\u001c3f\u0007>tG/Y5ogR+\u0007\u0010\u001e\u000b\u0003K%\u0002\"AJ\u0014\u000e\u00039I!\u0001\u000b\b\u0003\u000f\t{w\u000e\\3b]\")!F\u0001a\u0001W\u0005)A.\u00192fYB\u0011Af\r\b\u0003[E\u0002\"A\f\b\u000e\u0003=R!\u0001\r\t\u0002\rq\u0012xn\u001c;?\u0013\t\u0011d\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003iU\u0012aa\u0015;sS:<'B\u0001\u001a\u000f\u0003\u0019\u0019'/Z1uKR1A\u0004\u000f\u001e<\u0001\u0016CQ!O\u0002A\u0002-\n1\u0001\u001d:f\u0011\u0015Q3\u00011\u0001,\u0011\u0015a4\u00011\u0001>\u0003\u0015\tG\u000f\u001e:t!\tib(\u0003\u0002@\u0019\tAQ*\u001a;b\t\u0006$\u0018\rC\u0003B\u0007\u0001\u0007!)A\u0003tG>\u0004X\r\u0005\u0002\u001e\u0007&\u0011A\t\u0004\u0002\u0011\u001d\u0006lWm\u001d9bG\u0016\u0014\u0015N\u001c3j]\u001eDQAR\u0002A\u0002\u001d\u000b\u0001b\u00195jY\u0012\u0014XM\u001c\t\u0004\u0011.kU\"A%\u000b\u0005)s\u0011AC2pY2,7\r^5p]&\u0011A*\u0013\u0002\u0004'\u0016\f\bCA\u000fO\u0013\tyEB\u0001\u0003O_\u0012,\u0017AC2sK\u0006$XMT8eKR1ADU*U+ZCQ!\u000f\u0003A\u0002-BQA\u000b\u0003A\u0002-BQ\u0001\u0010\u0003A\u0002uBQ!\u0011\u0003A\u0002\tCQA\u0012\u0003A\u0002]\u00032\u0001W.N\u001d\t1\u0013,\u0003\u0002[\u001d\u00059\u0001/Y2lC\u001e,\u0017B\u0001/^\u0005\u0011a\u0015n\u001d;\u000b\u0005is\u0011AC2sK\u0006$X\rV3yiR\u0011\u0001m\u0019\t\u0003;\u0005L!A\u0019\u0007\u0003\tQ+\u0007\u0010\u001e\u0005\u0006I\u0016\u0001\raK\u0001\u0005i\u0016DH/A\bde\u0016\fG/\u001a)s_\u000eLen\u001d;s)\r97.\u001c\t\u0004\u0011.C\u0007CA\u000fj\u0013\tQGBA\u0005Qe>\u001c\u0017J\\:ue\")AN\u0002a\u0001W\u00051A/\u0019:hKRDQA\u001c\u0004A\u0002-\nA\u0001Z1uC\u0006i1M]3bi\u0016\u001cu.\\7f]R$\"!];\u0011\u0007![%\u000f\u0005\u0002\u001eg&\u0011A\u000f\u0004\u0002\b\u0007>lW.\u001a8u\u0011\u00151x\u00011\u0001,\u0003)\u0019\u0007.\u0019:bGR,'o]\u0001\rGJ,\u0017\r^3Q\u0007\u0012\u000bG/\u0019\u000b\u0003sr\u0004\"!\b>\n\u0005md!A\u0002)D\t\u0006$\u0018\rC\u0003w\u0011\u0001\u00071\u0006"
)
public class NoBindingFactoryAdapter extends FactoryAdapter implements NodeFactory {
   private boolean ignoreComments;
   private boolean ignoreProcInstr;
   private HashMap cache;

   public Node construct(final int hash, final List old, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      return NodeFactory.construct$(this, hash, old, pre, name, attrSeq, scope, children);
   }

   public boolean eqElements(final Seq ch1, final Seq ch2) {
      return NodeFactory.eqElements$(this, ch1, ch2);
   }

   public boolean nodeEquals(final Node n, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      return NodeFactory.nodeEquals$(this, n, pre, name, attrSeq, scope, children);
   }

   public Node makeNode(final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      return NodeFactory.makeNode$(this, pre, name, attrSeq, scope, children);
   }

   public Text makeText(final String s) {
      return NodeFactory.makeText$(this, s);
   }

   public PCData makePCData(final String s) {
      return NodeFactory.makePCData$(this, s);
   }

   public Seq makeComment(final String s) {
      return NodeFactory.makeComment$(this, s);
   }

   public Seq makeProcInstr(final String t, final String s) {
      return NodeFactory.makeProcInstr$(this, t, s);
   }

   public boolean ignoreComments() {
      return this.ignoreComments;
   }

   public boolean ignoreProcInstr() {
      return this.ignoreProcInstr;
   }

   public HashMap cache() {
      return this.cache;
   }

   public void scala$xml$factory$NodeFactory$_setter_$ignoreComments_$eq(final boolean x$1) {
      this.ignoreComments = x$1;
   }

   public void scala$xml$factory$NodeFactory$_setter_$ignoreProcInstr_$eq(final boolean x$1) {
      this.ignoreProcInstr = x$1;
   }

   public void scala$xml$factory$NodeFactory$_setter_$cache_$eq(final HashMap x$1) {
      this.cache = x$1;
   }

   public boolean nodeContainsText(final String label) {
      return true;
   }

   public Elem create(final String pre, final String label, final MetaData attrs, final NamespaceBinding scope, final Seq children) {
      return Elem$.MODULE$.apply(pre, label, attrs, scope, children.isEmpty(), NodeSeq$.MODULE$.seqToNodeSeq(children));
   }

   public Elem createNode(final String pre, final String label, final MetaData attrs, final NamespaceBinding scope, final List children) {
      return Elem$.MODULE$.apply(pre, label, attrs, scope, children.isEmpty(), children);
   }

   public Text createText(final String text) {
      return this.makeText(text);
   }

   public Seq createProcInstr(final String target, final String data) {
      return this.makeProcInstr(target, data);
   }

   public Seq createComment(final String characters) {
      return this.makeComment(characters);
   }

   public PCData createPCData(final String characters) {
      return this.makePCData(characters);
   }

   public NoBindingFactoryAdapter() {
      NodeFactory.$init$(this);
      Statics.releaseFence();
   }
}
