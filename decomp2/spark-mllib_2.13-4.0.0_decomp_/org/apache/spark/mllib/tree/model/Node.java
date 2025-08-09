package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.FeatureType$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tUd\u0001B\u00181\u0001uB\u0001B\u0016\u0001\u0003\u0006\u0004%\ta\u0016\u0005\tI\u0002\u0011\t\u0011)A\u00051\"Aa\r\u0001BA\u0002\u0013\u0005q\r\u0003\u0005n\u0001\t\u0005\r\u0011\"\u0001o\u0011!)\bA!A!B\u0013A\u0007\u0002C<\u0001\u0005\u0003\u0007I\u0011\u0001=\t\u0013}\u0004!\u00111A\u0005\u0002\u0005\u0005\u0001\"CA\u0004\u0001\t\u0005\t\u0015)\u0003z\u0011)\tY\u0001\u0001BA\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003/\u0001!\u00111A\u0005\u0002\u0005e\u0001BCA\u0010\u0001\t\u0005\t\u0015)\u0003\u0002\u0010!Q\u00111\u0005\u0001\u0003\u0002\u0004%\t!!\n\t\u0015\u0005U\u0002A!a\u0001\n\u0003\t9\u0004\u0003\u0006\u0002>\u0001\u0011\t\u0011)Q\u0005\u0003OA!\"!\u0011\u0001\u0005\u0003\u0007I\u0011AA\"\u0011)\tY\u0005\u0001BA\u0002\u0013\u0005\u0011Q\n\u0005\u000b\u0003'\u0002!\u0011!Q!\n\u0005\u0015\u0003BCA,\u0001\t\u0005\r\u0011\"\u0001\u0002D!Q\u00111\f\u0001\u0003\u0002\u0004%\t!!\u0018\t\u0015\u0005\r\u0004A!A!B\u0013\t)\u0005\u0003\u0006\u0002h\u0001\u0011\t\u0019!C\u0001\u0003SB!\"!\u001e\u0001\u0005\u0003\u0007I\u0011AA<\u0011)\ti\b\u0001B\u0001B\u0003&\u00111\u000e\u0005\b\u0003\u0003\u0003A\u0011AAB\u0011\u001d\t9\u000b\u0001C!\u0003SCaA\u001a\u0001\u0005\u0002\u0005m\u0006\u0002CAj\u0001\u0011\u0005!'!6\t\u000f\u0005]\u0007\u0001\"\u00013/\"9\u0011\u0011\u001c\u0001\u0005\u0002I:\u0006\u0002CAn\u0001\u0011\u0005!'!8\t\u0015\u0005\r\b!%A\u0005\u0002I\n)\u000f\u0003\u0005\u0002z\u0002!\tAMA~\u000f!\u0011)\u0001\rE\u0001m\t\u001daaB\u00181\u0011\u00031$\u0011\u0002\u0005\b\u0003\u0003\u0013C\u0011\u0001B\r\u0011\u001d\u0011YB\tC\u0001\u0005;AqAa\t#\t\u0003\u0011)\u0003C\u0004\u00030\t\"\tA!\r\t\u000f\tU\"\u0005\"\u0001\u00038!9!1\b\u0012\u0005\u0002\tu\u0002b\u0002B!E\u0011\u0005!1\t\u0005\b\u0005\u000f\u0012C\u0011\u0001B%\u0011\u001d\u0011iE\tC\u0001\u0005\u001fBqA!\u0016#\t\u0003\u00119\u0006C\u0004\u0003\\\t\"\tA!\u0018\t\u0013\t\u0015$%!A\u0005\n\t\u001d$\u0001\u0002(pI\u0016T!!\r\u001a\u0002\u000b5|G-\u001a7\u000b\u0005M\"\u0014\u0001\u0002;sK\u0016T!!\u000e\u001c\u0002\u000b5dG.\u001b2\u000b\u0005]B\u0014!B:qCJ\\'BA\u001d;\u0003\u0019\t\u0007/Y2iK*\t1(A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001}\u0011\u0003\u0006CA C\u001b\u0005\u0001%\"A!\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0003%AB!osJ+g\r\u0005\u0002F\u001b:\u0011ai\u0013\b\u0003\u000f*k\u0011\u0001\u0013\u0006\u0003\u0013r\na\u0001\u0010:p_Rt\u0014\"A!\n\u00051\u0003\u0015a\u00029bG.\fw-Z\u0005\u0003\u001d>\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0014!\u0011\u0005E#V\"\u0001*\u000b\u0005M3\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005U\u0013&a\u0002'pO\u001eLgnZ\u0001\u0003S\u0012,\u0012\u0001\u0017\t\u0003\u007feK!A\u0017!\u0003\u0007%sG\u000fK\u0002\u00029\n\u0004\"!\u00181\u000e\u0003yS!a\u0018\u001c\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002b=\n)1+\u001b8dK\u0006\n1-A\u00032]Ar\u0003'A\u0002jI\u0002B3A\u0001/c\u0003\u001d\u0001(/\u001a3jGR,\u0012\u0001\u001b\t\u0003S*l\u0011\u0001M\u0005\u0003WB\u0012q\u0001\u0015:fI&\u001cG\u000fK\u0002\u00049\n\f1\u0002\u001d:fI&\u001cGo\u0018\u0013fcR\u0011qN\u001d\t\u0003\u007fAL!!\u001d!\u0003\tUs\u0017\u000e\u001e\u0005\bg\u0012\t\t\u00111\u0001i\u0003\rAH%\r\u0015\u0004\tq\u0013\u0017\u0001\u00039sK\u0012L7\r\u001e\u0011)\u0007\u0015a&-\u0001\u0005j[B,(/\u001b;z+\u0005I\bCA {\u0013\tY\bI\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\rqk\u0018%\u0001@\u0002\u000bEr#G\f\u0019\u0002\u0019%l\u0007/\u001e:jif|F%Z9\u0015\u0007=\f\u0019\u0001C\u0004t\u000f\u0005\u0005\t\u0019A=)\u0007\u001daV0A\u0005j[B,(/\u001b;zA!\u001a\u0001\u0002X?\u0002\r%\u001cH*Z1g+\t\ty\u0001E\u0002@\u0003#I1!a\u0005A\u0005\u001d\u0011un\u001c7fC:D3!\u0003/c\u0003)I7\u000fT3bM~#S-\u001d\u000b\u0004_\u0006m\u0001\u0002C:\u000b\u0003\u0003\u0005\r!a\u0004)\u0007)a&-A\u0004jg2+\u0017M\u001a\u0011)\u0007-a&-A\u0003ta2LG/\u0006\u0002\u0002(A)q(!\u000b\u0002.%\u0019\u00111\u0006!\u0003\r=\u0003H/[8o!\rI\u0017qF\u0005\u0004\u0003c\u0001$!B*qY&$\bf\u0001\u0007]E\u0006I1\u000f\u001d7ji~#S-\u001d\u000b\u0004_\u0006e\u0002\u0002C:\u000e\u0003\u0003\u0005\r!a\n)\u00075a&-\u0001\u0004ta2LG\u000f\t\u0015\u0004\u001dq\u0013\u0017\u0001\u00037fMRtu\u000eZ3\u0016\u0005\u0005\u0015\u0003#B \u0002*\u0005\u001d\u0003CA5\u0001Q\ryALY\u0001\rY\u00164GOT8eK~#S-\u001d\u000b\u0004_\u0006=\u0003\u0002C:\u0011\u0003\u0003\u0005\r!!\u0012)\u0007Aa&-A\u0005mK\u001a$hj\u001c3fA!\u001a\u0011\u0003\u00182\u0002\u0013ILw\r\u001b;O_\u0012,\u0007f\u0001\n]E\u0006i!/[4ii:{G-Z0%KF$2a\\A0\u0011!\u00198#!AA\u0002\u0005\u0015\u0003fA\n]E\u0006Q!/[4ii:{G-\u001a\u0011)\u0007Qa&-A\u0003ti\u0006$8/\u0006\u0002\u0002lA)q(!\u000b\u0002nA\u0019\u0011.a\u001c\n\u0007\u0005E\u0004G\u0001\u000bJ]\u001a|'/\\1uS>tw)Y5o'R\fGo\u001d\u0015\u0004+q\u0013\u0017!C:uCR\u001cx\fJ3r)\ry\u0017\u0011\u0010\u0005\tgZ\t\t\u00111\u0001\u0002l!\u001aa\u0003\u00182\u0002\rM$\u0018\r^:!Q\r9BLY\u0001\u0007y%t\u0017\u000e\u001e \u0015%\u0005\u001d\u0013QQAE\u0003\u001b\u000b\t*!&\u0002\u001a\u0006u\u0015\u0011\u0015\u0005\u0006-b\u0001\r\u0001\u0017\u0015\u0005\u0003\u000bc&\rC\u0003g1\u0001\u0007\u0001\u000e\u000b\u0003\u0002\nr\u0013\u0007\"B<\u0019\u0001\u0004I\b\u0006BAG9vDq!a\u0003\u0019\u0001\u0004\ty\u0001\u000b\u0003\u0002\u0012r\u0013\u0007bBA\u00121\u0001\u0007\u0011q\u0005\u0015\u0005\u0003+c&\rC\u0004\u0002Ba\u0001\r!!\u0012)\t\u0005eEL\u0019\u0005\b\u0003/B\u0002\u0019AA#Q\u0011\ti\n\u00182\t\u000f\u0005\u001d\u0004\u00041\u0001\u0002l!\"\u0011\u0011\u0015/cQ\rAB,`\u0001\ti>\u001cFO]5oOR\u0011\u00111\u0016\t\u0005\u0003[\u000b)L\u0004\u0003\u00020\u0006E\u0006CA$A\u0013\r\t\u0019\fQ\u0001\u0007!J,G-\u001a4\n\t\u0005]\u0016\u0011\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005M\u0006\tF\u0002z\u0003{Cq!a0\u001b\u0001\u0004\t\t-\u0001\u0005gK\u0006$XO]3t!\u0011\t\u0019-!3\u000e\u0005\u0005\u0015'bAAdi\u00051A.\u001b8bY\u001eLA!a3\u0002F\n1a+Z2u_JDCA\u0007/\u0002P\u0006\u0012\u0011\u0011[\u0001\u0006c9\nd\u0006M\u0001\tI\u0016,\u0007oQ8qsR\u0011\u0011qI\u0001\u000f]VlG)Z:dK:$\u0017M\u001c;t\u00031\u0019XO\u0019;sK\u0016$U\r\u001d;i\u0003=\u0019XO\u0019;sK\u0016$vn\u0015;sS:<G\u0003BAV\u0003?D\u0001\"!9\u001f!\u0003\u0005\r\u0001W\u0001\rS:$WM\u001c;GC\u000e$xN]\u0001\u001agV\u0014GO]3f)>\u001cFO]5oO\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002h*\u001a\u0001,!;,\u0005\u0005-\b\u0003BAw\u0003kl!!a<\u000b\t\u0005E\u00181_\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0018!\n\t\u0005]\u0018q\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017aD:vER\u0014X-Z%uKJ\fGo\u001c:\u0016\u0005\u0005u\b#B#\u0002\u0000\u0006\u001d\u0013b\u0001B\u0001\u001f\nA\u0011\n^3sCR|'\u000fK\u0002\u00019\n\fAAT8eKB\u0011\u0011NI\n\u0005Ey\u0012Y\u0001\u0005\u0003\u0003\u000e\t]QB\u0001B\b\u0015\u0011\u0011\tBa\u0005\u0002\u0005%|'B\u0001B\u000b\u0003\u0011Q\u0017M^1\n\u00079\u0013y\u0001\u0006\u0002\u0003\b\u0005IQ-\u001c9us:{G-\u001a\u000b\u0005\u0003\u000f\u0012y\u0002\u0003\u0004\u0003\"\u0011\u0002\r\u0001W\u0001\n]>$W-\u00138eKb\fQ!\u00199qYf$\"\"a\u0012\u0003(\t%\"1\u0006B\u0017\u0011\u0019\u0011\t#\na\u00011\")a-\na\u0001Q\")q/\na\u0001s\"9\u00111B\u0013A\u0002\u0005=\u0011A\u00047fMR\u001c\u0005.\u001b7e\u0013:$W\r\u001f\u000b\u00041\nM\u0002B\u0002B\u0011M\u0001\u0007\u0001,A\bsS\u001eDGo\u00115jY\u0012Le\u000eZ3y)\rA&\u0011\b\u0005\u0007\u0005C9\u0003\u0019\u0001-\u0002\u0017A\f'/\u001a8u\u0013:$W\r\u001f\u000b\u00041\n}\u0002B\u0002B\u0011Q\u0001\u0007\u0001,\u0001\u0007j]\u0012,\u0007\u0010V8MKZ,G\u000eF\u0002Y\u0005\u000bBaA!\t*\u0001\u0004A\u0016aC5t\u0019\u00164Go\u00115jY\u0012$B!a\u0004\u0003L!1!\u0011\u0005\u0016A\u0002a\u000bq\"\\1y\u001d>$Wm]%o\u0019\u00164X\r\u001c\u000b\u00041\nE\u0003B\u0002B*W\u0001\u0007\u0001,A\u0003mKZ,G.A\tti\u0006\u0014H/\u00138eKbLe\u000eT3wK2$2\u0001\u0017B-\u0011\u0019\u0011\u0019\u0006\fa\u00011\u00069q-\u001a;O_\u0012,GCBA$\u0005?\u0012\t\u0007\u0003\u0004\u0003\"5\u0002\r\u0001\u0017\u0005\b\u0005Gj\u0003\u0019AA$\u0003!\u0011xn\u001c;O_\u0012,\u0017\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B5!\u0011\u0011YG!\u001d\u000e\u0005\t5$\u0002\u0002B8\u0005'\tA\u0001\\1oO&!!1\u000fB7\u0005\u0019y%M[3di\u0002"
)
public class Node implements Serializable, Logging {
   private final int id;
   private Predict predict;
   private double impurity;
   private boolean isLeaf;
   private Option split;
   private Option leftNode;
   private Option rightNode;
   private Option stats;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Node getNode(final int nodeIndex, final Node rootNode) {
      return Node$.MODULE$.getNode(nodeIndex, rootNode);
   }

   public static int startIndexInLevel(final int level) {
      return Node$.MODULE$.startIndexInLevel(level);
   }

   public static int maxNodesInLevel(final int level) {
      return Node$.MODULE$.maxNodesInLevel(level);
   }

   public static boolean isLeftChild(final int nodeIndex) {
      return Node$.MODULE$.isLeftChild(nodeIndex);
   }

   public static int indexToLevel(final int nodeIndex) {
      return Node$.MODULE$.indexToLevel(nodeIndex);
   }

   public static int parentIndex(final int nodeIndex) {
      return Node$.MODULE$.parentIndex(nodeIndex);
   }

   public static int rightChildIndex(final int nodeIndex) {
      return Node$.MODULE$.rightChildIndex(nodeIndex);
   }

   public static int leftChildIndex(final int nodeIndex) {
      return Node$.MODULE$.leftChildIndex(nodeIndex);
   }

   public static Node apply(final int nodeIndex, final Predict predict, final double impurity, final boolean isLeaf) {
      return Node$.MODULE$.apply(nodeIndex, predict, impurity, isLeaf);
   }

   public static Node emptyNode(final int nodeIndex) {
      return Node$.MODULE$.emptyNode(nodeIndex);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int id() {
      return this.id;
   }

   public Predict predict() {
      return this.predict;
   }

   public void predict_$eq(final Predict x$1) {
      this.predict = x$1;
   }

   public double impurity() {
      return this.impurity;
   }

   public void impurity_$eq(final double x$1) {
      this.impurity = x$1;
   }

   public boolean isLeaf() {
      return this.isLeaf;
   }

   public void isLeaf_$eq(final boolean x$1) {
      this.isLeaf = x$1;
   }

   public Option split() {
      return this.split;
   }

   public void split_$eq(final Option x$1) {
      this.split = x$1;
   }

   public Option leftNode() {
      return this.leftNode;
   }

   public void leftNode_$eq(final Option x$1) {
      this.leftNode = x$1;
   }

   public Option rightNode() {
      return this.rightNode;
   }

   public void rightNode_$eq(final Option x$1) {
      this.rightNode = x$1;
   }

   public Option stats() {
      return this.stats;
   }

   public void stats_$eq(final Option x$1) {
      this.stats = x$1;
   }

   public String toString() {
      int var10000 = this.id();
      return "id = " + var10000 + ", isLeaf = " + this.isLeaf() + ", predict = " + this.predict() + ", impurity = " + this.impurity() + ", split = " + this.split() + ", stats = " + this.stats();
   }

   public double predict(final Vector features) {
      if (this.isLeaf()) {
         return this.predict().predict();
      } else {
         Enumeration.Value var10000 = ((Split)this.split().get()).featureType();
         Enumeration.Value var2 = FeatureType$.MODULE$.Continuous();
         if (var10000 == null) {
            if (var2 == null) {
               return features.apply(((Split)this.split().get()).feature()) <= ((Split)this.split().get()).threshold() ? ((Node)this.leftNode().get()).predict(features) : ((Node)this.rightNode().get()).predict(features);
            }
         } else if (var10000.equals(var2)) {
            return features.apply(((Split)this.split().get()).feature()) <= ((Split)this.split().get()).threshold() ? ((Node)this.leftNode().get()).predict(features) : ((Node)this.rightNode().get()).predict(features);
         }

         if (((Split)this.split().get()).categories().contains(BoxesRunTime.boxToDouble(features.apply(((Split)this.split().get()).feature())))) {
            return ((Node)this.leftNode().get()).predict(features);
         } else {
            return ((Node)this.rightNode().get()).predict(features);
         }
      }
   }

   public Node deepCopy() {
      Option leftNodeCopy = (Option)(this.leftNode().isEmpty() ? .MODULE$ : new Some(((Node)this.leftNode().get()).deepCopy()));
      Option rightNodeCopy = (Option)(this.rightNode().isEmpty() ? .MODULE$ : new Some(((Node)this.rightNode().get()).deepCopy()));
      return new Node(this.id(), this.predict(), this.impurity(), this.isLeaf(), this.split(), leftNodeCopy, rightNodeCopy, this.stats());
   }

   public int numDescendants() {
      return this.isLeaf() ? 0 : 2 + ((Node)this.leftNode().get()).numDescendants() + ((Node)this.rightNode().get()).numDescendants();
   }

   public int subtreeDepth() {
      return this.isLeaf() ? 0 : 1 + scala.math.package..MODULE$.max(((Node)this.leftNode().get()).subtreeDepth(), ((Node)this.rightNode().get()).subtreeDepth());
   }

   public String subtreeToString(final int indentFactor) {
      String prefix = scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), indentFactor);
      return this.isLeaf() ? prefix + "Predict: " + this.predict().predict() + "\n" : prefix + "If " + splitToString$1((Split)this.split().get(), true) + "\n" + ((Node)this.leftNode().get()).subtreeToString(indentFactor + 1) + prefix + "Else " + splitToString$1((Split)this.split().get(), false) + "\n" + ((Node)this.rightNode().get()).subtreeToString(indentFactor + 1);
   }

   public int subtreeToString$default$1() {
      return 0;
   }

   public Iterator subtreeIterator() {
      return scala.package..MODULE$.Iterator().single(this).$plus$plus(() -> (Iterator)this.leftNode().map((x$1) -> x$1.subtreeIterator()).getOrElse(() -> scala.package..MODULE$.Iterator().empty())).$plus$plus(() -> (Iterator)this.rightNode().map((x$2) -> x$2.subtreeIterator()).getOrElse(() -> scala.package..MODULE$.Iterator().empty()));
   }

   private static final String splitToString$1(final Split split, final boolean left) {
      label45: {
         Enumeration.Value var3 = split.featureType();
         Enumeration.Value var10000 = FeatureType$.MODULE$.Continuous();
         if (var10000 == null) {
            if (var3 == null) {
               break label45;
            }
         } else if (var10000.equals(var3)) {
            break label45;
         }

         var10000 = FeatureType$.MODULE$.Categorical();
         if (var10000 == null) {
            if (var3 != null) {
               throw new MatchError(var3);
            }
         } else if (!var10000.equals(var3)) {
            throw new MatchError(var3);
         }

         if (left) {
            int var8 = split.feature();
            return "(feature " + var8 + " in " + split.categories().mkString("{", ",", "}") + ")";
         }

         int var7 = split.feature();
         return "(feature " + var7 + " not in " + split.categories().mkString("{", ",", "}") + ")";
      }

      if (left) {
         int var10 = split.feature();
         return "(feature " + var10 + " <= " + split.threshold() + ")";
      } else {
         int var9 = split.feature();
         return "(feature " + var9 + " > " + split.threshold() + ")";
      }
   }

   public Node(final int id, final Predict predict, final double impurity, final boolean isLeaf, final Option split, final Option leftNode, final Option rightNode, final Option stats) {
      this.id = id;
      this.predict = predict;
      this.impurity = impurity;
      this.isLeaf = isLeaf;
      this.split = split;
      this.leftNode = leftNode;
      this.rightNode = rightNode;
      this.stats = stats;
      super();
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
