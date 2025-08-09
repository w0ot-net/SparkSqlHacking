package scala.collection.immutable;

import scala.collection.AbstractIterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4a!\u0005\n\u0002\u0002IA\u0002\"B\u0016\u0001\t\u0003a\u0003b\u0002\u001c\u0001\u0001\u0004%\tb\u000e\u0005\bw\u0001\u0001\r\u0011\"\u0005=\u0011\u0019\u0011\u0005\u0001)Q\u0005q!I1\t\u0001a\u0001\u0002\u0004%\t\u0002\u0012\u0005\n\u000b\u0002\u0001\r\u00111A\u0005\u0012\u0019C\u0011\u0002\u0013\u0001A\u0002\u0003\u0005\u000b\u0015B\u0018\t\r%\u0003\u0001\u0015)\u00039\u0011\u0019Q\u0005\u0001)A\u0005\u0017\"1a\n\u0001Q\u0001\n=CQa\u000b\u0001\u0005\u0002ACQa\u0015\u0001\u0005\u000eQCQa\u0016\u0001\u0005\u000eaCQA\u0017\u0001\u0005\u000emCQ\u0001\u0018\u0001\u0005\u000euCQ!\u0019\u0001\u0005\u0006\t\u0014\u0001d\u00115b[B\u0014\u0015m]3SKZ,'o]3Ji\u0016\u0014\u0018\r^8s\u0015\t\u0019B#A\u0005j[6,H/\u00192mK*\u0011QCF\u0001\u000bG>dG.Z2uS>t'\"A\f\u0002\u000bM\u001c\u0017\r\\1\u0016\u0007e\u0001\u0003g\u0005\u0002\u00015A\u00191\u0004\b\u0010\u000e\u0003QI!!\b\u000b\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\bCA\u0010!\u0019\u0001!Q!\t\u0001C\u0002\r\u0012\u0011!Q\u0002\u0001#\t!\u0003\u0006\u0005\u0002&M5\ta#\u0003\u0002(-\t9aj\u001c;iS:<\u0007CA\u0013*\u0013\tQcCA\u0002B]f\fa\u0001P5oSRtD#A\u0017\u0011\t9\u0002adL\u0007\u0002%A\u0011q\u0004\r\u0003\u0006c\u0001\u0011\rA\r\u0002\u0002)F\u0011Ae\r\t\u0004]Qz\u0013BA\u001b\u0013\u0005\u0011qu\u000eZ3\u0002%\r,(O]3oiZ\u000bG.^3DkJ\u001cxN]\u000b\u0002qA\u0011Q%O\u0005\u0003uY\u00111!\u00138u\u0003Y\u0019WO\u001d:f]R4\u0016\r\\;f\u0007V\u00148o\u001c:`I\u0015\fHCA\u001fA!\t)c(\u0003\u0002@-\t!QK\\5u\u0011\u001d\t5!!AA\u0002a\n1\u0001\u001f\u00132\u0003M\u0019WO\u001d:f]R4\u0016\r\\;f\u0007V\u00148o\u001c:!\u0003A\u0019WO\u001d:f]R4\u0016\r\\;f\u001d>$W-F\u00010\u0003Q\u0019WO\u001d:f]R4\u0016\r\\;f\u001d>$Wm\u0018\u0013fcR\u0011Qh\u0012\u0005\b\u0003\u001a\t\t\u00111\u00010\u0003E\u0019WO\u001d:f]R4\u0016\r\\;f\u001d>$W\rI\u0001\u0012GV\u0014(/\u001a8u'R\f7m\u001b'fm\u0016d\u0017!\u00038pI\u0016Le\u000eZ3y!\r)C\nO\u0005\u0003\u001bZ\u0011Q!\u0011:sCf\f\u0011B\\8eKN#\u0018mY6\u0011\u0007\u0015bu\u0006\u0006\u0002.#\")!k\u0003a\u0001_\u0005A!o\\8u\u001d>$W-\u0001\ttKR,\b\u000fU1zY>\fGMT8eKR\u0011Q(\u0016\u0005\u0006-2\u0001\raL\u0001\u0005]>$W-\u0001\u0005qkNDgj\u001c3f)\ti\u0014\fC\u0003W\u001b\u0001\u0007q&A\u0004q_Btu\u000eZ3\u0015\u0003u\n1c]3be\u000eDg*\u001a=u-\u0006dW/\u001a(pI\u0016$\u0012A\u0018\t\u0003K}K!\u0001\u0019\f\u0003\u000f\t{w\u000e\\3b]\u00069\u0001.Y:OKb$X#\u00010"
)
public abstract class ChampBaseReverseIterator extends AbstractIterator {
   private int currentValueCursor;
   private Node currentValueNode;
   private int currentStackLevel;
   private final int[] nodeIndex;
   private final Node[] nodeStack;

   public int currentValueCursor() {
      return this.currentValueCursor;
   }

   public void currentValueCursor_$eq(final int x$1) {
      this.currentValueCursor = x$1;
   }

   public Node currentValueNode() {
      return this.currentValueNode;
   }

   public void currentValueNode_$eq(final Node x$1) {
      this.currentValueNode = x$1;
   }

   private final void setupPayloadNode(final Node node) {
      this.currentValueNode_$eq(node);
      this.currentValueCursor_$eq(node.payloadArity() - 1);
   }

   private final void pushNode(final Node node) {
      ++this.currentStackLevel;
      this.nodeStack[this.currentStackLevel] = node;
      this.nodeIndex[this.currentStackLevel] = node.nodeArity() - 1;
   }

   private final void popNode() {
      --this.currentStackLevel;
   }

   private final boolean searchNextValueNode() {
      while(true) {
         if (this.currentStackLevel >= 0) {
            int nodeCursor = this.nodeIndex[this.currentStackLevel]--;
            if (nodeCursor >= 0) {
               Node nextNode = this.nodeStack[this.currentStackLevel].getNode(nodeCursor);
               this.pushNode(nextNode);
               continue;
            }

            Node currNode = this.nodeStack[this.currentStackLevel];
            this.popNode();
            if (!currNode.hasPayload()) {
               continue;
            }

            this.setupPayloadNode(currNode);
            return true;
         }

         return false;
      }
   }

   public final boolean hasNext() {
      return this.currentValueCursor() >= 0 || this.searchNextValueNode();
   }

   public ChampBaseReverseIterator() {
      this.currentValueCursor = -1;
      this.currentStackLevel = -1;
      this.nodeIndex = new int[Node$.MODULE$.MaxDepth() + 1];
      this.nodeStack = new Node[Node$.MODULE$.MaxDepth() + 1];
   }

   public ChampBaseReverseIterator(final Node rootNode) {
      this();
      this.pushNode(rootNode);
      this.searchNextValueNode();
   }
}
