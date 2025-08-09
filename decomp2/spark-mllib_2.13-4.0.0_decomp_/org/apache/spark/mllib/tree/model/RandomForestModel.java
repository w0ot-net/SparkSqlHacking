package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.EnsembleCombiningStrategy$;
import org.apache.spark.mllib.util.Saveable;
import scala.Enumeration;
import scala.Array.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001\u0002\t\u0012\u0001yA\u0001\"\u000b\u0001\u0003\u0006\u0004%\tE\u000b\u0005\n\u0019\u0002\u0011\t\u0011)A\u0005W5C\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0005\u0015\u0005\n7\u0002\u0011\t\u0011)A\u0005#rCQA\u0018\u0001\u0005\u0002}CQA\u001a\u0001\u0005B\u001d<aa`\t\t\u0002\u0005\u0005aA\u0002\t\u0012\u0011\u0003\t\u0019\u0001\u0003\u0004_\u0011\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003GAA\u0011IA\u0013\u000f\u001d\ti\u0003\u0003E\u0005\u0003_1q!a\r\t\u0011\u0013\t)\u0004\u0003\u0004_\u0019\u0011\u0005\u0011q\u0007\u0005\b\u0003saA\u0011AA\u001e\u0011%\ti\u0004CA\u0001\n\u0013\tyDA\tSC:$w.\u001c$pe\u0016\u001cH/T8eK2T!AE\n\u0002\u000b5|G-\u001a7\u000b\u0005Q)\u0012\u0001\u0002;sK\u0016T!AF\f\u0002\u000b5dG.\u001b2\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001?\r\u0002\"\u0001I\u0011\u000e\u0003EI!AI\t\u0003#Q\u0013X-Z#og\u0016l'\r\\3N_\u0012,G\u000e\u0005\u0002%O5\tQE\u0003\u0002'+\u0005!Q\u000f^5m\u0013\tASE\u0001\u0005TCZ,\u0017M\u00197f\u0003\u0011\tGnZ8\u0016\u0003-\u0002\"\u0001\f!\u000f\u00055jdB\u0001\u0018<\u001d\ty#H\u0004\u00021s9\u0011\u0011\u0007\u000f\b\u0003e]r!a\r\u001c\u000e\u0003QR!!N\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012B\u0001\u000e\u001c\u0013\tA\u0012$\u0003\u0002\u0017/%\u0011A#F\u0005\u0003yM\tQbY8oM&<WO]1uS>t\u0017B\u0001 @\u0003\u0011\tEnZ8\u000b\u0005q\u001a\u0012BA!C\u0005\u0011\tEnZ8\u000b\u0005yz\u0004fA\u0001E\u0015B\u0011Q\tS\u0007\u0002\r*\u0011qiF\u0001\u000bC:tw\u000e^1uS>t\u0017BA%G\u0005\u0015\u0019\u0016N\\2fC\u0005Y\u0015!B\u0019/e9\u0002\u0014!B1mO>\u0004\u0013BA\u0015\"Q\r\u0011AIS\u0001\u0006iJ,Wm]\u000b\u0002#B\u0019!+V,\u000e\u0003MS\u0011\u0001V\u0001\u0006g\u000e\fG.Y\u0005\u0003-N\u0013Q!\u0011:sCf\u0004\"\u0001\t-\n\u0005e\u000b\"!\u0005#fG&\u001c\u0018n\u001c8Ue\u0016,Wj\u001c3fY\"\u001a1\u0001\u0012&\u0002\rQ\u0014X-Z:!\u0013\ty\u0015\u0005K\u0002\u0005\t*\u000ba\u0001P5oSRtDc\u00011bGB\u0011\u0001\u0005\u0001\u0005\u0006S\u0015\u0001\ra\u000b\u0015\u0004C\u0012S\u0005\"B(\u0006\u0001\u0004\t\u0006fA2E\u0015\"\u001aQ\u0001\u0012&\u0002\tM\fg/\u001a\u000b\u0004Q.\f\bC\u0001*j\u0013\tQ7K\u0001\u0003V]&$\b\"\u00027\u0007\u0001\u0004i\u0017AA:d!\tqw.D\u0001\u0018\u0013\t\u0001xC\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0003s\r\u0001\u00071/\u0001\u0003qCRD\u0007C\u0001;y\u001d\t)h\u000f\u0005\u00024'&\u0011qoU\u0001\u0007!J,G-\u001a4\n\u0005eT(AB*ue&twM\u0003\u0002x'\"\u001aa\u0001\u0012?\"\u0003u\fQ!\r\u00184]AB3\u0001\u0001#K\u0003E\u0011\u0016M\u001c3p[\u001a{'/Z:u\u001b>$W\r\u001c\t\u0003A!\u0019r\u0001CA\u0003\u0003\u0017\t\t\u0002E\u0002S\u0003\u000fI1!!\u0003T\u0005\u0019\te.\u001f*fMB!A%!\u0004a\u0013\r\ty!\n\u0002\u0007\u0019>\fG-\u001a:\u0011\t\u0005M\u0011QD\u0007\u0003\u0003+QA!a\u0006\u0002\u001a\u0005\u0011\u0011n\u001c\u0006\u0003\u00037\tAA[1wC&!\u0011qDA\u000b\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t\t!\u0001\u0003m_\u0006$G#\u00021\u0002(\u0005%\u0002\"\u00027\u000b\u0001\u0004i\u0007\"\u0002:\u000b\u0001\u0004\u0019\bf\u0001\u0006Ey\u0006a1+\u0019<f\u0019>\fGMV\u0019`aA\u0019\u0011\u0011\u0007\u0007\u000e\u0003!\u0011AbU1wK2{\u0017\r\u001a,2?B\u001a2\u0001DA\u0003)\t\ty#A\u0007uQ&\u001c8\t\\1tg:\u000bW.Z\u000b\u0002g\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\t\t\u0005\u0003\u0007\nI%\u0004\u0002\u0002F)!\u0011qIA\r\u0003\u0011a\u0017M\\4\n\t\u0005-\u0013Q\t\u0002\u0007\u001f\nTWm\u0019;)\u0007!!E\u0010K\u0002\b\tr\u0004"
)
public class RandomForestModel extends TreeEnsembleModel implements Saveable {
   public static RandomForestModel load(final SparkContext sc, final String path) {
      return RandomForestModel$.MODULE$.load(sc, path);
   }

   public Enumeration.Value algo() {
      return super.algo();
   }

   public DecisionTreeModel[] trees() {
      return super.trees();
   }

   public void save(final SparkContext sc, final String path) {
      TreeEnsembleModel.SaveLoadV1_0$.MODULE$.save(sc, path, this, RandomForestModel.SaveLoadV1_0$.MODULE$.thisClassName());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$1(final RandomForestModel $this, final DecisionTreeModel x$1) {
      boolean var3;
      label23: {
         Enumeration.Value var10000 = x$1.algo();
         Enumeration.Value var2 = $this.algo();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public RandomForestModel(final Enumeration.Value algo, final DecisionTreeModel[] trees) {
      double[] var10003;
      Enumeration.Value var10004;
      label17: {
         label16: {
            var10003 = (double[]).MODULE$.fill(trees.length, new Serializable() {
               private static final long serialVersionUID = 0L;

               public final double apply() {
                  return this.apply$mcD$sp();
               }

               public final double apply$mcD$sp() {
                  return (double)1.0F;
               }
            }, scala.reflect.ClassTag..MODULE$.Double());
            Enumeration.Value var3 = Algo$.MODULE$.Classification();
            if (algo == null) {
               if (var3 == null) {
                  break label16;
               }
            } else if (algo.equals(var3)) {
               break label16;
            }

            var10004 = EnsembleCombiningStrategy$.MODULE$.Average();
            break label17;
         }

         var10004 = EnsembleCombiningStrategy$.MODULE$.Vote();
      }

      super(algo, trees, var10003, var10004);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$new$1(this, x$1))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisClassName() {
         return "org.apache.spark.mllib.tree.model.RandomForestModel";
      }

      public SaveLoadV1_0$() {
      }
   }
}
