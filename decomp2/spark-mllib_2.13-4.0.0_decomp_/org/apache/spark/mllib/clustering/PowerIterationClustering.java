package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tmg\u0001B\u001c9\u0001\rC\u0001B\u0016\u0001\u0003\u0002\u0004%Ia\u0016\u0005\t7\u0002\u0011\t\u0019!C\u00059\"A!\r\u0001B\u0001B\u0003&\u0001\f\u0003\u0005d\u0001\t\u0005\r\u0011\"\u0003X\u0011!!\u0007A!a\u0001\n\u0013)\u0007\u0002C4\u0001\u0005\u0003\u0005\u000b\u0015\u0002-\t\u0011!\u0004!\u00111A\u0005\n%D\u0001B\u001d\u0001\u0003\u0002\u0004%Ia\u001d\u0005\tk\u0002\u0011\t\u0011)Q\u0005U\"1a\u000f\u0001C\u0001q]DQA\u001e\u0001\u0005\u0002uDq!a\u0004\u0001\t\u0003\t\t\u0002C\u0004\u0002\u001a\u0001!\t!a\u0007\t\u000f\u0005\u0005\u0002\u0001\"\u0001\u0002$!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002bBA\u0016\u0001\u0011\u0005\u0011\u0011\u000b\u0005\b\u0003W\u0001A\u0011AA9\u0011\u001d\tI\n\u0001C\u0005\u00037;q!a)9\u0011\u0003\t)K\u0002\u00048q!\u0005\u0011q\u0015\u0005\u0007mR!\t!a0\u0007\r\u0005\u0005G\u0003QAb\u0011)\tYM\u0006BK\u0002\u0013\u0005\u0011Q\u001a\u0005\u000b\u0003\u001f4\"\u0011#Q\u0001\n\u0005%\u0004\"CAi-\tU\r\u0011\"\u0001X\u0011%\t\u0019N\u0006B\tB\u0003%\u0001\f\u0003\u0004w-\u0011\u0005\u0011Q\u001b\u0005\n\u0003?4\u0012\u0011!C\u0001\u0003CD\u0011\"a:\u0017#\u0003%\t!!;\t\u0013\u0005uh#%A\u0005\u0002\u0005}\b\"\u0003B\u0002-\u0005\u0005I\u0011\tB\u0003\u0011!\u0011YAFA\u0001\n\u00039\u0006\"\u0003B\u0007-\u0005\u0005I\u0011\u0001B\b\u0011%\u0011IBFA\u0001\n\u0003\u0012Y\u0002C\u0005\u0003*Y\t\t\u0011\"\u0001\u0003,!I!Q\u0007\f\u0002\u0002\u0013\u0005#q\u0007\u0005\n\u0005w1\u0012\u0011!C!\u0005{A\u0011Ba\u0010\u0017\u0003\u0003%\tE!\u0011\t\u0013\t\rc#!A\u0005B\t\u0015s!\u0003B&)\u0005\u0005\t\u0012\u0001B'\r%\t\t\rFA\u0001\u0012\u0003\u0011y\u0005\u0003\u0004wS\u0011\u0005!Q\f\u0005\n\u0005\u007fI\u0013\u0011!C#\u0005\u0003B\u0011Ba\u0018*\u0003\u0003%\tI!\u0019\t\u0013\t\u001d\u0014&!A\u0005\u0002\n%\u0004\"\u0003B>S\u0005\u0005I\u0011\u0002B?\u0011!\u0011)\t\u0006C\u0001q\t\u001d\u0005\u0002\u0003BC)\u0011\u0005\u0001Ha#\t\u0011\t=E\u0003\"\u00019\u0005#C\u0001Ba&\u0015\t\u0003A$\u0011\u0014\u0005\t\u0005;#B\u0011\u0001\u001d\u0003 \"A!1\u0016\u000b\u0005\u0002a\u0012i\u000bC\u0004\u00038R!IA!/\t\u0013\tmD#!A\u0005\n\tu$\u0001\u0007)po\u0016\u0014\u0018\n^3sCRLwN\\\"mkN$XM]5oO*\u0011\u0011HO\u0001\u000bG2,8\u000f^3sS:<'BA\u001e=\u0003\u0015iG\u000e\\5c\u0015\tid(A\u0003ta\u0006\u00148N\u0003\u0002@\u0001\u00061\u0011\r]1dQ\u0016T\u0011!Q\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0011S\u0005CA#I\u001b\u00051%\"A$\u0002\u000bM\u001c\u0017\r\\1\n\u0005%3%AB!osJ+g\r\u0005\u0002L':\u0011A*\u0015\b\u0003\u001bBk\u0011A\u0014\u0006\u0003\u001f\n\u000ba\u0001\u0010:p_Rt\u0014\"A$\n\u0005I3\u0015a\u00029bG.\fw-Z\u0005\u0003)V\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0015$\u0002\u0003-,\u0012\u0001\u0017\t\u0003\u000bfK!A\u0017$\u0003\u0007%sG/A\u0003l?\u0012*\u0017\u000f\u0006\u0002^AB\u0011QIX\u0005\u0003?\u001a\u0013A!\u00168ji\"9\u0011MAA\u0001\u0002\u0004A\u0016a\u0001=%c\u0005\u00111\u000eI\u0001\u000e[\u0006D\u0018\n^3sCRLwN\\:\u0002#5\f\u00070\u0013;fe\u0006$\u0018n\u001c8t?\u0012*\u0017\u000f\u0006\u0002^M\"9\u0011-BA\u0001\u0002\u0004A\u0016AD7bq&#XM]1uS>t7\u000fI\u0001\tS:LG/T8eKV\t!\u000e\u0005\u0002l_:\u0011A.\u001c\t\u0003\u001b\u001aK!A\u001c$\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0018O\u0001\u0004TiJLgn\u001a\u0006\u0003]\u001a\u000bA\"\u001b8ji6{G-Z0%KF$\"!\u0018;\t\u000f\u0005D\u0011\u0011!a\u0001U\u0006I\u0011N\\5u\u001b>$W\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\taT8\u0010 \t\u0003s\u0002i\u0011\u0001\u000f\u0005\u0006-*\u0001\r\u0001\u0017\u0005\u0006G*\u0001\r\u0001\u0017\u0005\u0006Q*\u0001\rA\u001b\u000b\u0002q\"\"1b`A\u0006!\u0011\t\t!a\u0002\u000e\u0005\u0005\r!bAA\u0003y\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005%\u00111\u0001\u0002\u0006'&t7-Z\u0011\u0003\u0003\u001b\tQ!\r\u00184]A\nAa]3u\u0017R!\u00111CA\u000b\u001b\u0005\u0001\u0001\"\u0002,\r\u0001\u0004A\u0006\u0006\u0002\u0007\u0000\u0003\u0017\t\u0001c]3u\u001b\u0006D\u0018\n^3sCRLwN\\:\u0015\t\u0005M\u0011Q\u0004\u0005\u0006G6\u0001\r\u0001\u0017\u0015\u0005\u001b}\fY!A\u000btKRLe.\u001b;jC2L'0\u0019;j_:lu\u000eZ3\u0015\t\u0005M\u0011Q\u0005\u0005\u0007\u0003Oq\u0001\u0019\u00016\u0002\t5|G-\u001a\u0015\u0005\u001d}\fY!A\u0002sk:$B!a\f\u00026A\u0019\u00110!\r\n\u0007\u0005M\u0002HA\u000fQ_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001elu\u000eZ3m\u0011\u001d\t9d\u0004a\u0001\u0003s\tQa\u001a:ba\"\u0004\u0002\"a\u000f\u0002B\u0005\u0015\u0013QI\u0007\u0003\u0003{Q1!a\u0010=\u0003\u00199'/\u00199iq&!\u00111IA\u001f\u0005\u00159%/\u00199i!\r)\u0015qI\u0005\u0004\u0003\u00132%A\u0002#pk\ndW\r\u000b\u0003\u0010\u007f\u00065\u0013EAA(\u0003\u0015\td&\u000e\u00181)\u0011\ty#a\u0015\t\u000f\u0005U\u0003\u00031\u0001\u0002X\u0005a1/[7jY\u0006\u0014\u0018\u000e^5fgB1\u0011\u0011LA0\u0003Gj!!a\u0017\u000b\u0007\u0005uC(A\u0002sI\u0012LA!!\u0019\u0002\\\t\u0019!\u000b\u0012#\u0011\u0013\u0015\u000b)'!\u001b\u0002j\u0005\u0015\u0013bAA4\r\n1A+\u001e9mKN\u00022!RA6\u0013\r\tiG\u0012\u0002\u0005\u0019>tw\r\u000b\u0003\u0011\u007f\u0006-A\u0003BA\u0018\u0003gBq!!\u0016\u0012\u0001\u0004\t)\b\u0005\u0004\u0002x\u0005\u0005\u0015QQ\u0007\u0003\u0003sRA!a\u001f\u0002~\u0005!!.\u0019<b\u0015\r\ty\bP\u0001\u0004CBL\u0017\u0002BAB\u0003s\u0012qAS1wCJ#E\tE\u0005F\u0003K\n9)a\"\u0002\u0014B!\u0011\u0011RAI\u001b\t\tYI\u0003\u0003\u0002\u000e\u0006=\u0015\u0001\u00027b]\u001eT!!a\u001f\n\t\u00055\u00141\u0012\t\u0005\u0003\u0013\u000b)*\u0003\u0003\u0002J\u0005-\u0005\u0006B\t\u0000\u0003\u0017\t1\u0001]5d)\u0011\ty#!(\t\u000f\u0005}%\u00031\u0001\u0002:\u0005\tq\u000f\u000b\u0003\u0001\u007f\u0006-\u0011\u0001\u0007)po\u0016\u0014\u0018\n^3sCRLwN\\\"mkN$XM]5oOB\u0011\u0011\u0010F\n\u0007)\u0011\u000bI+!.\u0011\t\u0005-\u0016\u0011W\u0007\u0003\u0003[S1!a,=\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BAZ\u0003[\u0013q\u0001T8hO&tw\r\u0005\u0003\u00028\u0006uVBAA]\u0015\u0011\tY,a$\u0002\u0005%|\u0017b\u0001+\u0002:R\u0011\u0011Q\u0015\u0002\u000b\u0003N\u001c\u0018n\u001a8nK:$8#\u0002\fE\u0003\u000bT\u0005cA#\u0002H&\u0019\u0011\u0011\u001a$\u0003\u000fA\u0013x\u000eZ;di\u0006\u0011\u0011\u000eZ\u000b\u0003\u0003S\n1!\u001b3!\u0003\u001d\u0019G.^:uKJ\f\u0001b\u00197vgR,'\u000f\t\u000b\u0007\u0003/\fY.!8\u0011\u0007\u0005eg#D\u0001\u0015\u0011\u001d\tYm\u0007a\u0001\u0003SBa!!5\u001c\u0001\u0004A\u0016\u0001B2paf$b!a6\u0002d\u0006\u0015\b\"CAf9A\u0005\t\u0019AA5\u0011!\t\t\u000e\bI\u0001\u0002\u0004A\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003WTC!!\u001b\u0002n.\u0012\u0011q\u001e\t\u0005\u0003c\fI0\u0004\u0002\u0002t*!\u0011Q_A|\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0006\u0019KA!a?\u0002t\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!\u0011\u0001\u0016\u00041\u00065\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003\bA!\u0011\u0011\u0012B\u0005\u0013\r\u0001\u00181R\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\u0011\tBa\u0006\u0011\u0007\u0015\u0013\u0019\"C\u0002\u0003\u0016\u0019\u00131!\u00118z\u0011\u001d\t\u0017%!AA\u0002a\u000bq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005;\u0001bAa\b\u0003&\tEQB\u0001B\u0011\u0015\r\u0011\u0019CR\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\u0014\u0005C\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!Q\u0006B\u001a!\r)%qF\u0005\u0004\u0005c1%a\u0002\"p_2,\u0017M\u001c\u0005\tC\u000e\n\t\u00111\u0001\u0003\u0012\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u00119A!\u000f\t\u000f\u0005$\u0013\u0011!a\u00011\u0006A\u0001.Y:i\u0007>$W\rF\u0001Y\u0003!!xn\u0015;sS:<GC\u0001B\u0004\u0003\u0019)\u0017/^1mgR!!Q\u0006B$\u0011!\tw%!AA\u0002\tE\u0001\u0006\u0002\f\u0000\u0003\u0017\t!\"Q:tS\u001etW.\u001a8u!\r\tI.K\n\u0006S\tE\u0013Q\u0017\t\n\u0005'\u0012I&!\u001bY\u0003/l!A!\u0016\u000b\u0007\t]c)A\u0004sk:$\u0018.\\3\n\t\tm#Q\u000b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014DC\u0001B'\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\t9Na\u0019\u0003f!9\u00111\u001a\u0017A\u0002\u0005%\u0004BBAiY\u0001\u0007\u0001,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t-$q\u000f\t\u0006\u000b\n5$\u0011O\u0005\u0004\u0005_2%AB(qi&|g\u000e\u0005\u0004F\u0005g\nI\u0007W\u0005\u0004\u0005k2%A\u0002+va2,'\u0007C\u0005\u0003z5\n\t\u00111\u0001\u0002X\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t}\u0004\u0003BAE\u0005\u0003KAAa!\u0002\f\n1qJ\u00196fGR\f\u0011B\\8s[\u0006d\u0017N_3\u0015\t\u0005e\"\u0011\u0012\u0005\b\u0003oy\u0003\u0019AA\u001d)\u0011\tID!$\t\u000f\u0005U\u0003\u00071\u0001\u0002X\u0005Q!/\u00198e_6Le.\u001b;\u0015\t\u0005e\"1\u0013\u0005\b\u0005+\u000b\u0004\u0019AA\u001d\u0003\u00059\u0017\u0001E5oSR$Um\u001a:fKZ+7\r^8s)\u0011\tIDa'\t\u000f\tU%\u00071\u0001\u0002:\u0005I\u0001o\\<fe&#XM\u001d\u000b\u0007\u0005C\u00139K!+\u0011\r\u0005m\"1UA#\u0013\u0011\u0011)+!\u0010\u0003\u0013Y+'\u000f^3y%\u0012#\u0005b\u0002BKg\u0001\u0007\u0011\u0011\b\u0005\u0006GN\u0002\r\u0001W\u0001\u0007W6+\u0017M\\:\u0015\r\t=&\u0011\u0017B[!\u0015\tYDa)Y\u0011\u001d\u0011\u0019\f\u000ea\u0001\u0005C\u000b\u0011A\u001e\u0005\u0006-R\u0002\r\u0001W\u0001\f[\u0006$XM]5bY&TX\rF\u0002^\u0005wCqA!&6\u0001\u0004\u0011i\f\r\u0004\u0003@\n\u0015'1\u001b\t\t\u0003w\t\tE!1\u0003RB!!1\u0019Bc\u0019\u0001!ABa2\u0003<\u0006\u0005\t\u0011!B\u0001\u0005\u0013\u00141a\u0018\u00132#\u0011\u0011YM!\u0005\u0011\u0007\u0015\u0013i-C\u0002\u0003P\u001a\u0013qAT8uQ&tw\r\u0005\u0003\u0003D\nMG\u0001\u0004Bk\u0005w\u000b\t\u0011!A\u0003\u0002\t%'aA0%e!\"Ac`A\u0006Q\u0011\u0019r0a\u0003"
)
public class PowerIterationClustering implements Serializable {
   private int k;
   private int maxIterations;
   private String initMode;

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return PowerIterationClustering$.MODULE$.LogStringContext(sc);
   }

   private int k() {
      return this.k;
   }

   private void k_$eq(final int x$1) {
      this.k = x$1;
   }

   private int maxIterations() {
      return this.maxIterations;
   }

   private void maxIterations_$eq(final int x$1) {
      this.maxIterations = x$1;
   }

   private String initMode() {
      return this.initMode;
   }

   private void initMode_$eq(final String x$1) {
      this.initMode = x$1;
   }

   public PowerIterationClustering setK(final int k) {
      .MODULE$.require(k > 0, () -> "Number of clusters must be positive but got " + k);
      this.k_$eq(k);
      return this;
   }

   public PowerIterationClustering setMaxIterations(final int maxIterations) {
      .MODULE$.require(maxIterations >= 0, () -> "Maximum of iterations must be nonnegative but got " + maxIterations);
      this.maxIterations_$eq(maxIterations);
      return this;
   }

   public PowerIterationClustering setInitializationMode(final String mode) {
      label25: {
         switch (mode == null ? 0 : mode.hashCode()) {
            case -1335595316:
               if ("degree".equals(mode)) {
                  break label25;
               }
               break;
            case -938285885:
               if ("random".equals(mode)) {
                  break label25;
               }
         }

         throw new IllegalArgumentException("Invalid initialization mode: " + mode);
      }

      this.initMode_$eq(mode);
      return this;
   }

   public PowerIterationClusteringModel run(final Graph graph) {
      Graph w = PowerIterationClustering$.MODULE$.normalize(graph);
      String var5 = this.initMode();
      Graph var10000;
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -1335595316:
            if (!"degree".equals(var5)) {
               throw new MatchError(var5);
            }

            var10000 = PowerIterationClustering$.MODULE$.initDegreeVector(w);
            break;
         case -938285885:
            if ("random".equals(var5)) {
               var10000 = PowerIterationClustering$.MODULE$.randomInit(w);
               break;
            }

            throw new MatchError(var5);
         default:
            throw new MatchError(var5);
      }

      Graph w0 = var10000;
      w.unpersist(w.unpersist$default$1());
      return this.pic(w0);
   }

   public PowerIterationClusteringModel run(final RDD similarities) {
      Graph w = PowerIterationClustering$.MODULE$.normalize(similarities);
      String var5 = this.initMode();
      Graph var10000;
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -1335595316:
            if (!"degree".equals(var5)) {
               throw new MatchError(var5);
            }

            var10000 = PowerIterationClustering$.MODULE$.initDegreeVector(w);
            break;
         case -938285885:
            if ("random".equals(var5)) {
               var10000 = PowerIterationClustering$.MODULE$.randomInit(w);
               break;
            }

            throw new MatchError(var5);
         default:
            throw new MatchError(var5);
      }

      Graph w0 = var10000;
      w.unpersist(w.unpersist$default$1());
      return this.pic(w0);
   }

   public PowerIterationClusteringModel run(final JavaRDD similarities) {
      return this.run(similarities.rdd());
   }

   private PowerIterationClusteringModel pic(final Graph w) {
      VertexRDD v = PowerIterationClustering$.MODULE$.powerIter(w, this.maxIterations());
      RDD assignments = PowerIterationClustering$.MODULE$.kMeans(v, this.k()).map((x0$1) -> {
         if (x0$1 != null) {
            long id = x0$1._1$mcJ$sp();
            int cluster = x0$1._2$mcI$sp();
            return new Assignment(id, cluster);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Assignment.class));
      return new PowerIterationClusteringModel(this.k(), assignments);
   }

   public PowerIterationClustering(final int k, final int maxIterations, final String initMode) {
      this.k = k;
      this.maxIterations = maxIterations;
      this.initMode = initMode;
      super();
   }

   public PowerIterationClustering() {
      this(2, 100, "random");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Assignment implements Product, Serializable {
      private final long id;
      private final int cluster;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long id() {
         return this.id;
      }

      public int cluster() {
         return this.cluster;
      }

      public Assignment copy(final long id, final int cluster) {
         return new Assignment(id, cluster);
      }

      public long copy$default$1() {
         return this.id();
      }

      public int copy$default$2() {
         return this.cluster();
      }

      public String productPrefix() {
         return "Assignment";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.id());
            }
            case 1 -> {
               return BoxesRunTime.boxToInteger(this.cluster());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Assignment;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "id";
            }
            case 1 -> {
               return "cluster";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.id()));
         var1 = Statics.mix(var1, this.cluster());
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label38: {
               if (x$1 instanceof Assignment) {
                  Assignment var4 = (Assignment)x$1;
                  if (this.id() == var4.id() && this.cluster() == var4.cluster() && var4.canEqual(this)) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Assignment(final long id, final int cluster) {
         this.id = id;
         this.cluster = cluster;
         Product.$init$(this);
      }
   }

   public static class Assignment$ extends AbstractFunction2 implements Serializable {
      public static final Assignment$ MODULE$ = new Assignment$();

      public final String toString() {
         return "Assignment";
      }

      public Assignment apply(final long id, final int cluster) {
         return new Assignment(id, cluster);
      }

      public Option unapply(final Assignment x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcJI.sp(x$0.id(), x$0.cluster())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Assignment$.class);
      }
   }
}
