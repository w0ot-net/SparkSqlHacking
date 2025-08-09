package scala.collection.immutable;

import scala.collection.AbstractIterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554a!\u0006\f\u0002\u0002Ya\u0002\"B\u0018\u0001\t\u0003\u0001\u0004b\u0002\u001e\u0001\u0001\u0004%\tb\u000f\u0005\b\u007f\u0001\u0001\r\u0011\"\u0005A\u0011\u00191\u0005\u0001)Q\u0005y!9q\t\u0001a\u0001\n#Y\u0004b\u0002%\u0001\u0001\u0004%\t\"\u0013\u0005\u0007\u0017\u0002\u0001\u000b\u0015\u0002\u001f\t\u00131\u0003\u0001\u0019!a\u0001\n#i\u0005\"\u0003(\u0001\u0001\u0004\u0005\r\u0011\"\u0005P\u0011%\t\u0006\u00011A\u0001B\u0003&1\u0007\u0003\u0004S\u0001\u0001\u0006K\u0001\u0010\u0005\n'\u0002\u0001\r\u0011!Q!\nQC\u0011b\u0016\u0001A\u0002\u0003\u0005\u000b\u0015\u0002-\t\u000be\u0003A\u0011\u0002.\t\u000b=\u0002A\u0011A.\t\u000by\u0003AQB0\t\u000b\t\u0004AQB2\t\u000b\u0015\u0004AQ\u0002.\t\u000b\u0019\u0004AQB4\t\u000b-\u0004AQ\u00017\u0003#\rC\u0017-\u001c9CCN,\u0017\n^3sCR|'O\u0003\u0002\u00181\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u00033i\t!bY8mY\u0016\u001cG/[8o\u0015\u0005Y\u0012!B:dC2\fWcA\u000f%iM\u0011\u0001A\b\t\u0004?\u0001\u0012S\"\u0001\r\n\u0005\u0005B\"\u0001E!cgR\u0014\u0018m\u0019;Ji\u0016\u0014\u0018\r^8s!\t\u0019C\u0005\u0004\u0001\u0005\u000b\u0015\u0002!\u0019A\u0014\u0003\u0003\u0005\u001b\u0001!\u0005\u0002)YA\u0011\u0011FK\u0007\u00025%\u00111F\u0007\u0002\b\u001d>$\b.\u001b8h!\tIS&\u0003\u0002/5\t\u0019\u0011I\\=\u0002\rqJg.\u001b;?)\u0005\t\u0004\u0003\u0002\u001a\u0001EMj\u0011A\u0006\t\u0003GQ\"Q!\u000e\u0001C\u0002Y\u0012\u0011\u0001V\t\u0003Q]\u00022A\r\u001d4\u0013\tIdC\u0001\u0003O_\u0012,\u0017AE2veJ,g\u000e\u001e,bYV,7)\u001e:t_J,\u0012\u0001\u0010\t\u0003SuJ!A\u0010\u000e\u0003\u0007%sG/\u0001\fdkJ\u0014XM\u001c;WC2,XmQ;sg>\u0014x\fJ3r)\t\tE\t\u0005\u0002*\u0005&\u00111I\u0007\u0002\u0005+:LG\u000fC\u0004F\u0007\u0005\u0005\t\u0019\u0001\u001f\u0002\u0007a$\u0013'A\ndkJ\u0014XM\u001c;WC2,XmQ;sg>\u0014\b%\u0001\ndkJ\u0014XM\u001c;WC2,X\rT3oORD\u0017AF2veJ,g\u000e\u001e,bYV,G*\u001a8hi\"|F%Z9\u0015\u0005\u0005S\u0005bB#\u0007\u0003\u0003\u0005\r\u0001P\u0001\u0014GV\u0014(/\u001a8u-\u0006dW/\u001a'f]\u001e$\b\u000eI\u0001\u0011GV\u0014(/\u001a8u-\u0006dW/\u001a(pI\u0016,\u0012aM\u0001\u0015GV\u0014(/\u001a8u-\u0006dW/\u001a(pI\u0016|F%Z9\u0015\u0005\u0005\u0003\u0006bB#\n\u0003\u0003\u0005\raM\u0001\u0012GV\u0014(/\u001a8u-\u0006dW/\u001a(pI\u0016\u0004\u0013!E2veJ,g\u000e^*uC\u000e\\G*\u001a<fY\u0006)bn\u001c3f\u0007V\u00148o\u001c:t\u0003:$G*\u001a8hi\"\u001c\bcA\u0015Vy%\u0011aK\u0007\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0006]>$Wm\u001d\t\u0004SU\u001b\u0014!C5oSRtu\u000eZ3t)\u0005\tECA\u0019]\u0011\u0015iv\u00021\u00014\u0003!\u0011xn\u001c;O_\u0012,\u0017\u0001E:fiV\u0004\b+Y=m_\u0006$gj\u001c3f)\t\t\u0005\rC\u0003b!\u0001\u00071'\u0001\u0003o_\u0012,\u0017\u0001\u00039vg\"tu\u000eZ3\u0015\u0005\u0005#\u0007\"B1\u0012\u0001\u0004\u0019\u0014a\u00029pa:{G-Z\u0001\u0014g\u0016\f'o\u00195OKb$h+\u00197vK:{G-\u001a\u000b\u0002QB\u0011\u0011&[\u0005\u0003Uj\u0011qAQ8pY\u0016\fg.A\u0004iCNtU\r\u001f;\u0016\u0003!\u0004"
)
public abstract class ChampBaseIterator extends AbstractIterator {
   private int currentValueCursor;
   private int currentValueLength;
   private Node currentValueNode;
   private int currentStackLevel;
   private int[] nodeCursorsAndLengths;
   private Node[] nodes;

   public int currentValueCursor() {
      return this.currentValueCursor;
   }

   public void currentValueCursor_$eq(final int x$1) {
      this.currentValueCursor = x$1;
   }

   public int currentValueLength() {
      return this.currentValueLength;
   }

   public void currentValueLength_$eq(final int x$1) {
      this.currentValueLength = x$1;
   }

   public Node currentValueNode() {
      return this.currentValueNode;
   }

   public void currentValueNode_$eq(final Node x$1) {
      this.currentValueNode = x$1;
   }

   private void initNodes() {
      if (this.nodeCursorsAndLengths == null) {
         this.nodeCursorsAndLengths = new int[Node$.MODULE$.MaxDepth() * 2];
         this.nodes = new Node[Node$.MODULE$.MaxDepth()];
      }
   }

   private final void setupPayloadNode(final Node node) {
      this.currentValueNode_$eq(node);
      this.currentValueCursor_$eq(0);
      this.currentValueLength_$eq(node.payloadArity());
   }

   private final void pushNode(final Node node) {
      this.initNodes();
      ++this.currentStackLevel;
      int cursorIndex = this.currentStackLevel * 2;
      int lengthIndex = this.currentStackLevel * 2 + 1;
      this.nodes[this.currentStackLevel] = node;
      this.nodeCursorsAndLengths[cursorIndex] = 0;
      this.nodeCursorsAndLengths[lengthIndex] = node.nodeArity();
   }

   private final void popNode() {
      --this.currentStackLevel;
   }

   private final boolean searchNextValueNode() {
      while(this.currentStackLevel >= 0) {
         int cursorIndex = this.currentStackLevel * 2;
         int lengthIndex = this.currentStackLevel * 2 + 1;
         int nodeCursor = this.nodeCursorsAndLengths[cursorIndex];
         int nodeLength = this.nodeCursorsAndLengths[lengthIndex];
         if (nodeCursor < nodeLength) {
            int[] var5 = this.nodeCursorsAndLengths;
            int var10002 = var5[cursorIndex]++;
            Node nextNode = this.nodes[this.currentStackLevel].getNode(nodeCursor);
            if (nextNode.hasNodes()) {
               this.pushNode(nextNode);
            }

            if (nextNode.hasPayload()) {
               this.setupPayloadNode(nextNode);
               return true;
            }
         } else {
            this.popNode();
         }
      }

      return false;
   }

   public final boolean hasNext() {
      return this.currentValueCursor() < this.currentValueLength() || this.searchNextValueNode();
   }

   public ChampBaseIterator() {
      this.currentValueCursor = 0;
      this.currentValueLength = 0;
      this.currentStackLevel = -1;
   }

   public ChampBaseIterator(final Node rootNode) {
      this();
      if (rootNode.hasNodes()) {
         this.pushNode(rootNode);
      }

      if (rootNode.hasPayload()) {
         this.setupPayloadNode(rootNode);
      }

   }
}
