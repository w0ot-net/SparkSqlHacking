package org.apache.spark.ml.tree;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c!B\u000b\u0017\u0003C\t\u0003\"\u0002\u001b\u0001\t\u0003)\u0004\"\u0002\u001d\u0001\r\u0003I\u0004\"B\u001f\u0001\r\u0003I\u0004B\u0002 \u0001\r\u0003Ar\b\u0003\u0004I\u0001\u0019\u0005\u0001$\u0013\u0005\u0007+\u00021\t\u0001\u0007,\t\r\u0019\u0004a\u0011\u0001\fh\u0011\u0019A\u0007A\"\u0001\u0017S\"AA\u000fAI\u0001\n\u00031R\u000fC\u0004\u0002\u0002\u00011\tAF4\t\u0011\u0005\r\u0001A\"\u0001\u0019\u0003\u000bA\u0001\"!\u0006\u0001\r\u0003A\u0012q\u0003\u0005\b\u00033\u0001a\u0011\u0001\f6\u000f!\t\tC\u0006E\u00011\u0005\rbaB\u000b\u0017\u0011\u0003A\u0012Q\u0005\u0005\u0007i=!\t!!\u000e\t\u000f\u0005]r\u0002\"\u0001\u0002:!I\u0011\u0011J\bC\u0002\u0013\u0005\u00111\n\u0005\b\u0003\u001bz\u0001\u0015!\u00037\u0011%\tyeDA\u0001\n\u0013\t\tF\u0001\u0003O_\u0012,'BA\f\u0019\u0003\u0011!(/Z3\u000b\u0005eQ\u0012AA7m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7\u0001A\n\u0004\u0001\tB\u0003CA\u0012'\u001b\u0005!#\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d\"#AB!osJ+g\r\u0005\u0002*c9\u0011!f\f\b\u0003W9j\u0011\u0001\f\u0006\u0003[\u0001\na\u0001\u0010:p_Rt\u0014\"A\u0013\n\u0005A\"\u0013a\u00029bG.\fw-Z\u0005\u0003eM\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\r\u0013\u0002\rqJg.\u001b;?)\u00051\u0004CA\u001c\u0001\u001b\u00051\u0012A\u00039sK\u0012L7\r^5p]V\t!\b\u0005\u0002$w%\u0011A\b\n\u0002\u0007\t>,(\r\\3\u0002\u0011%l\u0007/\u001e:jif\fQ\"[7qkJLG/_*uCR\u001cX#\u0001!\u0011\u0005\u00053U\"\u0001\"\u000b\u0005u\u001a%BA\fE\u0015\t)%$A\u0003nY2L'-\u0003\u0002H\u0005\n\u0011\u0012*\u001c9ve&$\u0018pQ1mGVd\u0017\r^8s\u0003-\u0001(/\u001a3jGRLU\u000e\u001d7\u0015\u0005)k\u0005CA\u001cL\u0013\taeC\u0001\u0005MK\u00064gj\u001c3f\u0011\u0015qU\u00011\u0001P\u0003!1W-\u0019;ve\u0016\u001c\bC\u0001)T\u001b\u0005\t&B\u0001*\u0019\u0003\u0019a\u0017N\\1mO&\u0011A+\u0015\u0002\u0007-\u0016\u001cGo\u001c:\u0002\u001bA\u0014X\rZ5di\nKgN\\3e)\rQuk\u0018\u0005\u00061\u001a\u0001\r!W\u0001\u0007E&tg.\u001a3\u0011\u0007\rRF,\u0003\u0002\\I\t)\u0011I\u001d:bsB\u00111%X\u0005\u0003=\u0012\u00121!\u00138u\u0011\u0015\u0001g\u00011\u0001b\u0003\u0019\u0019\b\u000f\\5ugB\u00191E\u00172\u0011\u0007\rR6\r\u0005\u00028I&\u0011QM\u0006\u0002\u0006'Bd\u0017\u000e^\u0001\u000f]VlG)Z:dK:$\u0017M\u001c;t+\u0005a\u0016aD:vER\u0014X-\u001a+p'R\u0014\u0018N\\4\u0015\u0005)\u0014\bCA6p\u001d\taW\u000e\u0005\u0002,I%\u0011a\u000eJ\u0001\u0007!J,G-\u001a4\n\u0005A\f(AB*ue&twM\u0003\u0002oI!91\u000f\u0003I\u0001\u0002\u0004a\u0016\u0001D5oI\u0016tGOR1di>\u0014\u0018!G:vER\u0014X-\u001a+p'R\u0014\u0018N\\4%I\u00164\u0017-\u001e7uIE*\u0012A\u001e\u0016\u00039^\\\u0013\u0001\u001f\t\u0003szl\u0011A\u001f\u0006\u0003wr\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005u$\u0013AC1o]>$\u0018\r^5p]&\u0011qP\u001f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001D:vER\u0014X-\u001a#faRD\u0017!\u0002;p\u001f2$G\u0003BA\u0004\u0003#\u0001B!!\u0003\u0002\u00105\u0011\u00111\u0002\u0006\u0004\u0003\u001b\u0019\u0015!B7pI\u0016d\u0017bA\u000b\u0002\f!1\u00111C\u0006A\u0002q\u000b!!\u001b3\u0002)5\f\u0007p\u00159mSR4U-\u0019;ve\u0016Le\u000eZ3y)\u0005a\u0016\u0001\u00033fKB\u001cu\u000e]=*\t\u0001\tibS\u0005\u0004\u0003?1\"\u0001D%oi\u0016\u0014h.\u00197O_\u0012,\u0017\u0001\u0002(pI\u0016\u0004\"aN\b\u0014\t=\u0011\u0013q\u0005\t\u0005\u0003S\t\u0019$\u0004\u0002\u0002,)!\u0011QFA\u0018\u0003\tIwN\u0003\u0002\u00022\u0005!!.\u0019<b\u0013\r\u0011\u00141\u0006\u000b\u0003\u0003G\tqA\u001a:p[>cG\rF\u00037\u0003w\ty\u0004C\u0004\u0002>E\u0001\r!a\u0002\u0002\u000f=dGMT8eK\"9\u0011\u0011I\tA\u0002\u0005\r\u0013aE2bi\u0016<wN]5dC24U-\u0019;ve\u0016\u001c\b#B6\u0002Fqc\u0016bAA$c\n\u0019Q*\u00199\u0002\u0013\u0011,X.\\=O_\u0012,W#\u0001\u001c\u0002\u0015\u0011,X.\\=O_\u0012,\u0007%\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002TA!\u0011QKA.\u001b\t\t9F\u0003\u0003\u0002Z\u0005=\u0012\u0001\u00027b]\u001eLA!!\u0018\u0002X\t1qJ\u00196fGR\u0004"
)
public abstract class Node implements Serializable {
   public static Node dummyNode() {
      return Node$.MODULE$.dummyNode();
   }

   public static Node fromOld(final org.apache.spark.mllib.tree.model.Node oldNode, final Map categoricalFeatures) {
      return Node$.MODULE$.fromOld(oldNode, categoricalFeatures);
   }

   public abstract double prediction();

   public abstract double impurity();

   public abstract ImpurityCalculator impurityStats();

   public abstract LeafNode predictImpl(final Vector features);

   public abstract LeafNode predictBinned(final int[] binned, final Split[][] splits);

   public abstract int numDescendants();

   public abstract String subtreeToString(final int indentFactor);

   public int subtreeToString$default$1() {
      return 0;
   }

   public abstract int subtreeDepth();

   public abstract org.apache.spark.mllib.tree.model.Node toOld(final int id);

   public abstract int maxSplitFeatureIndex();

   public abstract Node deepCopy();
}
