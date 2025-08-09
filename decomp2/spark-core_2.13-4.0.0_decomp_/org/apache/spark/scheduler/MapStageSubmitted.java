package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.util.CallSite;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUc!B\u0014)\u0001\"\u0002\u0004\u0002C&\u0001\u0005+\u0007I\u0011\u0001'\t\u0011A\u0003!\u0011#Q\u0001\n5C\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t9\u0002\u0011\t\u0012)A\u0005'\"A!\u000e\u0001BK\u0002\u0013\u00051\u000e\u0003\u0005s\u0001\tE\t\u0015!\u0003m\u0011!\u0019\bA!f\u0001\n\u0003!\b\u0002\u0003=\u0001\u0005#\u0005\u000b\u0011B;\t\u0011e\u0004!Q3A\u0005\u0002iD\u0001B \u0001\u0003\u0012\u0003\u0006Ia\u001f\u0005\n\u007f\u0002\u0011)\u001a!C\u0001\u0003\u0003A!\"!\u0005\u0001\u0005#\u0005\u000b\u0011BA\u0002\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003+A\u0011\"!\u000e\u0001\u0003\u0003%\t!a\u000e\t\u0013\u0005\u0015\u0003!%A\u0005\u0002\u0005\u001d\u0003\"CA/\u0001E\u0005I\u0011AA0\u0011%\t\u0019\bAI\u0001\n\u0003\t)\bC\u0005\u0002z\u0001\t\n\u0011\"\u0001\u0002|!I\u0011q\u0010\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0011\u0005\n\u0003\u000b\u0003\u0011\u0013!C\u0001\u0003\u000fC\u0011\"a#\u0001\u0003\u0003%\t%!$\t\u0011\u0005m\u0005!!A\u0005\u00021C\u0011\"!(\u0001\u0003\u0003%\t!a(\t\u0013\u0005\u0015\u0006!!A\u0005B\u0005\u001d\u0006\"CA[\u0001\u0005\u0005I\u0011AA\\\u0011%\t\t\rAA\u0001\n\u0003\n\u0019\rC\u0005\u0002H\u0002\t\t\u0011\"\u0011\u0002J\"I\u00111\u001a\u0001\u0002\u0002\u0013\u0005\u0013Q\u001a\u0005\n\u0003\u001f\u0004\u0011\u0011!C!\u0003#<!\"!6)\u0003\u0003E\t\u0001KAl\r%9\u0003&!A\t\u0002!\nI\u000eC\u0004\u0002\u0014}!\tA!\u0001\t\u0013\u0005-w$!A\u0005F\u00055\u0007\"\u0003B\u0002?\u0005\u0005I\u0011\u0011B\u0003\u0011%\u0011\u0019cHI\u0001\n\u0003\t9\tC\u0005\u0003&}\t\t\u0011\"!\u0003(!I!\u0011J\u0010\u0012\u0002\u0013\u0005\u0011q\u0011\u0005\n\u0005\u0017z\u0012\u0011!C\u0005\u0005\u001b\u0012\u0011#T1q'R\fw-Z*vE6LG\u000f^3e\u0015\tI#&A\u0005tG\",G-\u001e7fe*\u00111\u0006L\u0001\u0006gB\f'o\u001b\u0006\u0003[9\na!\u00199bG\",'\"A\u0018\u0002\u0007=\u0014xmE\u0003\u0001c]Zd\b\u0005\u00023k5\t1GC\u00015\u0003\u0015\u00198-\u00197b\u0013\t14G\u0001\u0004B]f\u0014VM\u001a\t\u0003qej\u0011\u0001K\u0005\u0003u!\u0012\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t\u0011D(\u0003\u0002>g\t9\u0001K]8ek\u000e$\bCA I\u001d\t\u0001eI\u0004\u0002B\u000b6\t!I\u0003\u0002D\t\u00061AH]8piz\u001a\u0001!C\u00015\u0013\t95'A\u0004qC\u000e\\\u0017mZ3\n\u0005%S%\u0001D*fe&\fG.\u001b>bE2,'BA$4\u0003\u0015QwNY%e+\u0005i\u0005C\u0001\u001aO\u0013\ty5GA\u0002J]R\faA[8c\u0013\u0012\u0004\u0013A\u00033fa\u0016tG-\u001a8dsV\t1\u000b\r\u0003U5\u0016D\u0007#B+W1\u0012<W\"\u0001\u0016\n\u0005]S#!E*ik\u001a4G.\u001a#fa\u0016tG-\u001a8dsB\u0011\u0011L\u0017\u0007\u0001\t%YF!!A\u0001\u0002\u000b\u0005QLA\u0002`IQ\n1\u0002Z3qK:$WM\\2zAE\u0011a,\u0019\t\u0003e}K!\u0001Y\u001a\u0003\u000f9{G\u000f[5oOB\u0011!GY\u0005\u0003GN\u00121!\u00118z!\tIV\rB\u0005g\t\u0005\u0005\t\u0011!B\u0001;\n\u0019q\fJ\u001b\u0011\u0005eCG!C5\u0005\u0003\u0003\u0005\tQ!\u0001^\u0005\ryFEN\u0001\tG\u0006dGnU5uKV\tA\u000e\u0005\u0002na6\taN\u0003\u0002pU\u0005!Q\u000f^5m\u0013\t\thN\u0001\u0005DC2d7+\u001b;f\u0003%\u0019\u0017\r\u001c7TSR,\u0007%\u0001\u0005mSN$XM\\3s+\u0005)\bC\u0001\u001dw\u0013\t9\bFA\u0006K_\nd\u0015n\u001d;f]\u0016\u0014\u0018!\u00037jgR,g.\u001a:!\u0003-\t'\u000f^5gC\u000e$8+\u001a;\u0016\u0003m\u0004\"!\u0016?\n\u0005uT#A\u0004&pE\u0006\u0013H/\u001b4bGR\u001cV\r^\u0001\rCJ$\u0018NZ1diN+G\u000fI\u0001\u000baJ|\u0007/\u001a:uS\u0016\u001cXCAA\u0002!\u0011\t)!!\u0004\u000e\u0005\u0005\u001d!bA8\u0002\n)\u0011\u00111B\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0010\u0005\u001d!A\u0003)s_B,'\u000f^5fg\u0006Y\u0001O]8qKJ$\u0018.Z:!\u0003\u0019a\u0014N\\5u}Qq\u0011qCA\r\u00037\ti#a\f\u00022\u0005M\u0002C\u0001\u001d\u0001\u0011\u0015YU\u00021\u0001N\u0011\u0019\tV\u00021\u0001\u0002\u001eAB\u0011qDA\u0012\u0003O\tY\u0003\u0005\u0005V-\u0006\u0005\u0012QEA\u0015!\rI\u00161\u0005\u0003\u000b7\u0006m\u0011\u0011!A\u0001\u0006\u0003i\u0006cA-\u0002(\u0011Qa-a\u0007\u0002\u0002\u0003\u0005)\u0011A/\u0011\u0007e\u000bY\u0003\u0002\u0006j\u00037\t\t\u0011!A\u0003\u0002uCQA[\u0007A\u00021DQa]\u0007A\u0002UDQ!_\u0007A\u0002mD\u0001b`\u0007\u0011\u0002\u0003\u0007\u00111A\u0001\u0005G>\u0004\u0018\u0010\u0006\b\u0002\u0018\u0005e\u00121HA\u001f\u0003\u007f\t\t%a\u0011\t\u000f-s\u0001\u0013!a\u0001\u001b\"A\u0011K\u0004I\u0001\u0002\u0004\ti\u0002C\u0004k\u001dA\u0005\t\u0019\u00017\t\u000fMt\u0001\u0013!a\u0001k\"9\u0011P\u0004I\u0001\u0002\u0004Y\b\u0002C@\u000f!\u0003\u0005\r!a\u0001\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\n\u0016\u0004\u001b\u0006-3FAA'!\u0011\ty%!\u0017\u000e\u0005\u0005E#\u0002BA*\u0003+\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]3'\u0001\u0006b]:|G/\u0019;j_:LA!a\u0017\u0002R\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\r\u0019\t\u0003G\nI'!\u001c\u0002r)\"\u0011QMA&!!)f+a\u001a\u0002l\u0005=\u0004cA-\u0002j\u0011I1\fEA\u0001\u0002\u0003\u0015\t!\u0018\t\u00043\u00065D!\u00034\u0011\u0003\u0003\u0005\tQ!\u0001^!\rI\u0016\u0011\u000f\u0003\nSB\t\t\u0011!A\u0003\u0002u\u000babY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002x)\u001aA.a\u0013\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011Q\u0010\u0016\u0004k\u0006-\u0013AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003\u0007S3a_A&\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*\"!!#+\t\u0005\r\u00111J\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005=\u0005\u0003BAI\u0003/k!!a%\u000b\t\u0005U\u0015\u0011B\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001a\u0006M%AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007\u0005\f\t\u000b\u0003\u0005\u0002$^\t\t\u00111\u0001N\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0016\t\u0006\u0003W\u000b\t,Y\u0007\u0003\u0003[S1!a,4\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003g\u000biK\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA]\u0003\u007f\u00032AMA^\u0013\r\til\r\u0002\b\u0005>|G.Z1o\u0011!\t\u0019+GA\u0001\u0002\u0004\t\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a$\u0002F\"A\u00111\u0015\u000e\u0002\u0002\u0003\u0007Q*\u0001\u0005iCND7i\u001c3f)\u0005i\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005=\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0002:\u0006M\u0007\u0002CAR;\u0005\u0005\t\u0019A1\u0002#5\u000b\u0007o\u0015;bO\u0016\u001cVOY7jiR,G\r\u0005\u00029?M)q$a7\u0002xBq\u0011Q\\Ar\u001b\u0006\u001dH.^>\u0002\u0004\u0005]QBAAp\u0015\r\t\toM\u0001\beVtG/[7f\u0013\u0011\t)/a8\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tg\u0007\r\u0005\u0002j\u00065\u0018\u0011_A{!!)f+a;\u0002p\u0006M\bcA-\u0002n\u0012I1lHA\u0001\u0002\u0003\u0015\t!\u0018\t\u00043\u0006EH!\u00034 \u0003\u0003\u0005\tQ!\u0001^!\rI\u0016Q\u001f\u0003\nS~\t\t\u0011!A\u0003\u0002u\u0003B!!?\u0002\u00006\u0011\u00111 \u0006\u0005\u0003{\fI!\u0001\u0002j_&\u0019\u0011*a?\u0015\u0005\u0005]\u0017!B1qa2LHCDA\f\u0005\u000f\u0011IAa\u0007\u0003\u001e\t}!\u0011\u0005\u0005\u0006\u0017\n\u0002\r!\u0014\u0005\u0007#\n\u0002\rAa\u00031\u0011\t5!\u0011\u0003B\u000b\u00053\u0001\u0002\"\u0016,\u0003\u0010\tM!q\u0003\t\u00043\nEAAC.\u0003\n\u0005\u0005\t\u0011!B\u0001;B\u0019\u0011L!\u0006\u0005\u0015\u0019\u0014I!!A\u0001\u0002\u000b\u0005Q\fE\u0002Z\u00053!!\"\u001bB\u0005\u0003\u0003\u0005\tQ!\u0001^\u0011\u0015Q'\u00051\u0001m\u0011\u0015\u0019(\u00051\u0001v\u0011\u0015I(\u00051\u0001|\u0011!y(\u0005%AA\u0002\u0005\r\u0011aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\u0006B#!\u0015\u0011$1\u0006B\u0018\u0013\r\u0011ic\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0017I\u0012\t$\u0014B\u001bYV\\\u00181A\u0005\u0004\u0005g\u0019$A\u0002+va2,g\u0007\r\u0005\u00038\tm\"q\bB\"!!)fK!\u000f\u0003>\t\u0005\u0003cA-\u0003<\u0011I1\fJA\u0001\u0002\u0003\u0015\t!\u0018\t\u00043\n}B!\u00034%\u0003\u0003\u0005\tQ!\u0001^!\rI&1\t\u0003\nS\u0012\n\t\u0011!A\u0003\u0002uC\u0011Ba\u0012%\u0003\u0003\u0005\r!a\u0006\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u001f\u0002B!!%\u0003R%!!1KAJ\u0005\u0019y%M[3di\u0002"
)
public class MapStageSubmitted implements DAGSchedulerEvent, Product, Serializable {
   private final int jobId;
   private final ShuffleDependency dependency;
   private final CallSite callSite;
   private final JobListener listener;
   private final JobArtifactSet artifactSet;
   private final Properties properties;

   public static Properties $lessinit$greater$default$6() {
      return MapStageSubmitted$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final MapStageSubmitted x$0) {
      return MapStageSubmitted$.MODULE$.unapply(x$0);
   }

   public static Properties apply$default$6() {
      return MapStageSubmitted$.MODULE$.apply$default$6();
   }

   public static MapStageSubmitted apply(final int jobId, final ShuffleDependency dependency, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      return MapStageSubmitted$.MODULE$.apply(jobId, dependency, callSite, listener, artifactSet, properties);
   }

   public static Function1 tupled() {
      return MapStageSubmitted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MapStageSubmitted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int jobId() {
      return this.jobId;
   }

   public ShuffleDependency dependency() {
      return this.dependency;
   }

   public CallSite callSite() {
      return this.callSite;
   }

   public JobListener listener() {
      return this.listener;
   }

   public JobArtifactSet artifactSet() {
      return this.artifactSet;
   }

   public Properties properties() {
      return this.properties;
   }

   public MapStageSubmitted copy(final int jobId, final ShuffleDependency dependency, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      return new MapStageSubmitted(jobId, dependency, callSite, listener, artifactSet, properties);
   }

   public int copy$default$1() {
      return this.jobId();
   }

   public ShuffleDependency copy$default$2() {
      return this.dependency();
   }

   public CallSite copy$default$3() {
      return this.callSite();
   }

   public JobListener copy$default$4() {
      return this.listener();
   }

   public JobArtifactSet copy$default$5() {
      return this.artifactSet();
   }

   public Properties copy$default$6() {
      return this.properties();
   }

   public String productPrefix() {
      return "MapStageSubmitted";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.jobId());
         }
         case 1 -> {
            return this.dependency();
         }
         case 2 -> {
            return this.callSite();
         }
         case 3 -> {
            return this.listener();
         }
         case 4 -> {
            return this.artifactSet();
         }
         case 5 -> {
            return this.properties();
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
      return x$1 instanceof MapStageSubmitted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jobId";
         }
         case 1 -> {
            return "dependency";
         }
         case 2 -> {
            return "callSite";
         }
         case 3 -> {
            return "listener";
         }
         case 4 -> {
            return "artifactSet";
         }
         case 5 -> {
            return "properties";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.jobId());
      var1 = Statics.mix(var1, Statics.anyHash(this.dependency()));
      var1 = Statics.mix(var1, Statics.anyHash(this.callSite()));
      var1 = Statics.mix(var1, Statics.anyHash(this.listener()));
      var1 = Statics.mix(var1, Statics.anyHash(this.artifactSet()));
      var1 = Statics.mix(var1, Statics.anyHash(this.properties()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof MapStageSubmitted) {
               MapStageSubmitted var4 = (MapStageSubmitted)x$1;
               if (this.jobId() == var4.jobId()) {
                  label76: {
                     ShuffleDependency var10000 = this.dependency();
                     ShuffleDependency var5 = var4.dependency();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     CallSite var10 = this.callSite();
                     CallSite var6 = var4.callSite();
                     if (var10 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10.equals(var6)) {
                        break label76;
                     }

                     JobListener var11 = this.listener();
                     JobListener var7 = var4.listener();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var7)) {
                        break label76;
                     }

                     JobArtifactSet var12 = this.artifactSet();
                     JobArtifactSet var8 = var4.artifactSet();
                     if (var12 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var12.equals(var8)) {
                        break label76;
                     }

                     Properties var13 = this.properties();
                     Properties var9 = var4.properties();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label76;
                        }
                     } else if (!var13.equals(var9)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public MapStageSubmitted(final int jobId, final ShuffleDependency dependency, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      this.jobId = jobId;
      this.dependency = dependency;
      this.callSite = callSite;
      this.listener = listener;
      this.artifactSet = artifactSet;
      this.properties = properties;
      Product.$init$(this);
   }
}
