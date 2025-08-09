package org.apache.spark.ui.scope;

import java.io.Serializable;
import org.apache.spark.internal.Logging;
import org.apache.spark.scheduler.StageInfo;
import scala.Option;
import scala.Product;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra!B\u0013'\u0001*\u0002\u0004\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011M\u0003!\u0011#Q\u0001\n%C\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t+\u0002\u0011\t\u0012)A\u0005\u0013\"Aa\u000b\u0001BK\u0002\u0013\u0005\u0001\n\u0003\u0005X\u0001\tE\t\u0015!\u0003J\u0011!A\u0006A!f\u0001\n\u0003I\u0006\u0002C/\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u000by\u0003A\u0011A0\t\u000f\u0015\u0004\u0011\u0011!C\u0001M\"91\u000eAI\u0001\n\u0003a\u0007bB<\u0001#\u0003%\t\u0001\u001c\u0005\bq\u0002\t\n\u0011\"\u0001m\u0011\u001dI\b!%A\u0005\u0002iDq\u0001 \u0001\u0002\u0002\u0013\u0005S\u0010C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0001\u0002\u0010!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0004\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"a\f\u0001\u0003\u0003%\t!!\r\t\u0013\u0005m\u0002!!A\u0005B\u0005u\u0002\"CA!\u0001\u0005\u0005I\u0011IA\"\u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002J\u0001\t\t\u0011\"\u0011\u0002L\u001dA\u0011q\n\u0014\t\u0002)\n\tFB\u0004&M!\u0005!&a\u0015\t\ryKB\u0011AA6\u0011!\ti'\u0007b\u0001\n\u0003i\bbBA83\u0001\u0006IA \u0005\b\u0003cJB\u0011AA:\u0011\u001d\tI)\u0007C\u0001\u0003\u0017Cq!a(\u001a\t\u0013\t\t\u000bC\u0004\u0002.f!I!a,\t\u0013\u0005M\u0017$%A\u0005\n\u0005U\u0007\"CAm3\u0005\u0005I\u0011QAn\u0011%\t)/GA\u0001\n\u0003\u000b9\u000fC\u0005\u0002zf\t\t\u0011\"\u0003\u0002|\n\t\"\u000b\u0012#Pa\u0016\u0014\u0018\r^5p]\u001e\u0013\u0018\r\u001d5\u000b\u0005\u001dB\u0013!B:d_B,'BA\u0015+\u0003\t)\u0018N\u0003\u0002,Y\u0005)1\u000f]1sW*\u0011QFL\u0001\u0007CB\f7\r[3\u000b\u0003=\n1a\u001c:h'\u0011\u0001\u0011g\u000e\u001e\u0011\u0005I*T\"A\u001a\u000b\u0003Q\nQa]2bY\u0006L!AN\u001a\u0003\r\u0005s\u0017PU3g!\t\u0011\u0004(\u0003\u0002:g\t9\u0001K]8ek\u000e$\bCA\u001eE\u001d\ta$I\u0004\u0002>\u00036\taH\u0003\u0002@\u0001\u00061AH]8piz\u001a\u0001!C\u00015\u0013\t\u00195'A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00153%\u0001D*fe&\fG.\u001b>bE2,'BA\"4\u0003\u0015)GmZ3t+\u0005I\u0005c\u0001&N\u001f6\t1J\u0003\u0002Mg\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00059[%aA*fcB\u0011\u0001+U\u0007\u0002M%\u0011!K\n\u0002\u0011%\u0012#u\n]3sCRLwN\\#eO\u0016\fa!\u001a3hKN\u0004\u0013!D8vi\u001e|\u0017N\\4FI\u001e,7/\u0001\bpkR<w.\u001b8h\u000b\u0012<Wm\u001d\u0011\u0002\u001b%t7m\\7j]\u001e,EmZ3t\u00039IgnY8nS:<W\tZ4fg\u0002\n1B]8pi\u000ecWo\u001d;feV\t!\f\u0005\u0002Q7&\u0011AL\n\u0002\u0014%\u0012#u\n]3sCRLwN\\\"mkN$XM]\u0001\re>|Go\u00117vgR,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\u0001\f'm\u00193\u0011\u0005A\u0003\u0001\"B$\n\u0001\u0004I\u0005\"\u0002+\n\u0001\u0004I\u0005\"\u0002,\n\u0001\u0004I\u0005\"\u0002-\n\u0001\u0004Q\u0016\u0001B2paf$R\u0001Y4iS*Dqa\u0012\u0006\u0011\u0002\u0003\u0007\u0011\nC\u0004U\u0015A\u0005\t\u0019A%\t\u000fYS\u0001\u0013!a\u0001\u0013\"9\u0001L\u0003I\u0001\u0002\u0004Q\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002[*\u0012\u0011J\\\u0016\u0002_B\u0011\u0001/^\u0007\u0002c*\u0011!o]\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001^\u001a\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002wc\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0002w*\u0012!L\\\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003y\u00042a`A\u0005\u001b\t\t\tA\u0003\u0003\u0002\u0004\u0005\u0015\u0011\u0001\u00027b]\u001eT!!a\u0002\u0002\t)\fg/Y\u0005\u0005\u0003\u0017\t\tA\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003#\u00012AMA\n\u0013\r\t)b\r\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u00037\t\t\u0003E\u00023\u0003;I1!a\b4\u0005\r\te.\u001f\u0005\n\u0003G\t\u0012\u0011!a\u0001\u0003#\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0015!\u0015Q\u00151FA\u000e\u0013\r\tic\u0013\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00024\u0005e\u0002c\u0001\u001a\u00026%\u0019\u0011qG\u001a\u0003\u000f\t{w\u000e\\3b]\"I\u00111E\n\u0002\u0002\u0003\u0007\u00111D\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002\u007f\u0003\u007fA\u0011\"a\t\u0015\u0003\u0003\u0005\r!!\u0005\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0005\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A`\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0012Q\n\u0005\n\u0003G9\u0012\u0011!a\u0001\u00037\t\u0011C\u0015#E\u001fB,'/\u0019;j_:<%/\u00199i!\t\u0001\u0016d\u0005\u0004\u001ac\u0005U\u0013\u0011\r\t\u0005\u0003/\ni&\u0004\u0002\u0002Z)\u0019\u00111\f\u0016\u0002\u0011%tG/\u001a:oC2LA!a\u0018\u0002Z\t9Aj\\4hS:<\u0007\u0003BA2\u0003Sj!!!\u001a\u000b\t\u0005\u001d\u0014QA\u0001\u0003S>L1!RA3)\t\t\t&\u0001\u000bT)\u0006;UiX\"M+N#VIU0Q%\u00163\u0015\nW\u0001\u0016'R\u000bu)R0D\u0019V\u001bF+\u0012*`!J+e)\u0013-!\u0003Ii\u0017m[3Pa\u0016\u0014\u0018\r^5p]\u001e\u0013\u0018\r\u001d5\u0015\u000b\u0001\f)(!\"\t\u000f\u0005]T\u00041\u0001\u0002z\u0005)1\u000f^1hKB!\u00111PAA\u001b\t\tiHC\u0002\u0002\u0000)\n\u0011b]2iK\u0012,H.\u001a:\n\t\u0005\r\u0015Q\u0010\u0002\n'R\fw-Z%oM>Dq!a\"\u001e\u0001\u0004\t\t\"A\u0007sKR\f\u0017N\\3e\u001d>$Wm]\u0001\f[\u0006\\W\rR8u\r&dW\r\u0006\u0003\u0002\u000e\u0006m\u0005\u0003BAH\u0003/sA!!%\u0002\u0014B\u0011QhM\u0005\u0004\u0003+\u001b\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002\f\u0005e%bAAKg!1\u0011Q\u0014\u0010A\u0002\u0001\fQa\u001a:ba\"\f1\"\\1lK\u0012{GOT8eKR!\u0011QRAR\u0011\u001d\t)k\ba\u0001\u0003O\u000bAA\\8eKB\u0019\u0001+!+\n\u0007\u0005-fE\u0001\tS\t\u0012{\u0005/\u001a:bi&|gNT8eK\u0006yQ.Y6f\t>$8+\u001e2he\u0006\u0004\b\u000e\u0006\u0006\u00022\u0006]\u0016qYAf\u0003\u001f\u00042AMAZ\u0013\r\t)l\r\u0002\u0005+:LG\u000fC\u0004\u0002:\u0002\u0002\r!a/\u0002\u0011M,(m\u001a:ba\"\u0004B!!0\u0002D6\u0011\u0011q\u0018\u0006\u0004\u0003\u0003\\\u0015aB7vi\u0006\u0014G.Z\u0005\u0005\u0003\u000b\fyLA\u0007TiJLgn\u001a\"vS2$WM\u001d\u0005\u0007\u0003\u0013\u0004\u0003\u0019\u0001.\u0002\u000f\rdWo\u001d;fe\"9\u0011Q\u001a\u0011A\u0002\u00055\u0015AB5oI\u0016tG\u000fC\u0005\u0002R\u0002\u0002\n\u00111\u0001\u0002\u000e\u00061\u0001O]3gSb\f\u0011$\\1lK\u0012{GoU;cOJ\f\u0007\u000f\u001b\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011q\u001b\u0016\u0004\u0003\u001bs\u0017!B1qa2LH#\u00031\u0002^\u0006}\u0017\u0011]Ar\u0011\u00159%\u00051\u0001J\u0011\u0015!&\u00051\u0001J\u0011\u00151&\u00051\u0001J\u0011\u0015A&\u00051\u0001[\u0003\u001d)h.\u00199qYf$B!!;\u0002vB)!'a;\u0002p&\u0019\u0011Q^\u001a\u0003\r=\u0003H/[8o!\u001d\u0011\u0014\u0011_%J\u0013jK1!a=4\u0005\u0019!V\u000f\u001d7fi!A\u0011q_\u0012\u0002\u0002\u0003\u0007\u0001-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!@\u0011\u0007}\fy0\u0003\u0003\u0003\u0002\u0005\u0005!AB(cU\u0016\u001cG\u000f"
)
public class RDDOperationGraph implements Product, Serializable {
   private final Seq edges;
   private final Seq outgoingEdges;
   private final Seq incomingEdges;
   private final RDDOperationCluster rootCluster;

   public static Option unapply(final RDDOperationGraph x$0) {
      return RDDOperationGraph$.MODULE$.unapply(x$0);
   }

   public static RDDOperationGraph apply(final Seq edges, final Seq outgoingEdges, final Seq incomingEdges, final RDDOperationCluster rootCluster) {
      return RDDOperationGraph$.MODULE$.apply(edges, outgoingEdges, incomingEdges, rootCluster);
   }

   public static String makeDotFile(final RDDOperationGraph graph) {
      return RDDOperationGraph$.MODULE$.makeDotFile(graph);
   }

   public static RDDOperationGraph makeOperationGraph(final StageInfo stage, final int retainedNodes) {
      return RDDOperationGraph$.MODULE$.makeOperationGraph(stage, retainedNodes);
   }

   public static String STAGE_CLUSTER_PREFIX() {
      return RDDOperationGraph$.MODULE$.STAGE_CLUSTER_PREFIX();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return RDDOperationGraph$.MODULE$.LogStringContext(sc);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq edges() {
      return this.edges;
   }

   public Seq outgoingEdges() {
      return this.outgoingEdges;
   }

   public Seq incomingEdges() {
      return this.incomingEdges;
   }

   public RDDOperationCluster rootCluster() {
      return this.rootCluster;
   }

   public RDDOperationGraph copy(final Seq edges, final Seq outgoingEdges, final Seq incomingEdges, final RDDOperationCluster rootCluster) {
      return new RDDOperationGraph(edges, outgoingEdges, incomingEdges, rootCluster);
   }

   public Seq copy$default$1() {
      return this.edges();
   }

   public Seq copy$default$2() {
      return this.outgoingEdges();
   }

   public Seq copy$default$3() {
      return this.incomingEdges();
   }

   public RDDOperationCluster copy$default$4() {
      return this.rootCluster();
   }

   public String productPrefix() {
      return "RDDOperationGraph";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.edges();
         }
         case 1 -> {
            return this.outgoingEdges();
         }
         case 2 -> {
            return this.incomingEdges();
         }
         case 3 -> {
            return this.rootCluster();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof RDDOperationGraph;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "edges";
         }
         case 1 -> {
            return "outgoingEdges";
         }
         case 2 -> {
            return "incomingEdges";
         }
         case 3 -> {
            return "rootCluster";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof RDDOperationGraph) {
               label64: {
                  RDDOperationGraph var4 = (RDDOperationGraph)x$1;
                  Seq var10000 = this.edges();
                  Seq var5 = var4.edges();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  var10000 = this.outgoingEdges();
                  Seq var6 = var4.outgoingEdges();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label64;
                  }

                  var10000 = this.incomingEdges();
                  Seq var7 = var4.incomingEdges();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label64;
                  }

                  RDDOperationCluster var11 = this.rootCluster();
                  RDDOperationCluster var8 = var4.rootCluster();
                  if (var11 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var11.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public RDDOperationGraph(final Seq edges, final Seq outgoingEdges, final Seq incomingEdges, final RDDOperationCluster rootCluster) {
      this.edges = edges;
      this.outgoingEdges = outgoingEdges;
      this.incomingEdges = incomingEdges;
      this.rootCluster = rootCluster;
      Product.$init$(this);
   }
}
