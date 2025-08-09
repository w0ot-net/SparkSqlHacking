package org.apache.spark.sql.hive.client;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rt!B2e\u0011\u0003\th!B:e\u0011\u0003!\b\"B>\u0002\t\u0003ahAB?\u0002\u0003C1g\u0010\u0003\u0006\u0002\u001a\r\u0011)\u0019!C\u0001\u00037A!\"!\f\u0004\u0005\u0003\u0005\u000b\u0011BA\u000f\u0011)\tyc\u0001BC\u0002\u0013\u0005\u0011\u0011\u0007\u0005\u000b\u0003s\u0019!\u0011!Q\u0001\n\u0005M\u0002BCA\u001e\u0007\t\u0015\r\u0011\"\u0001\u00022!Q\u0011QH\u0002\u0003\u0002\u0003\u0006I!a\r\t\rm\u001cA\u0011AA \u0011\u001d\t9e\u0001C!\u0003\u0013:!B!\u0011\u0002\u0003\u0003E\tA\u001aB\"\r%i\u0018!!A\t\u0002\u0019\u0014)\u0005\u0003\u0004|\u001b\u0011\u0005!q\t\u0005\n\u0005\u0013j\u0011\u0013!C\u0001\u0005\u0017B\u0011B!\u0019\u000e#\u0003%\tAa\u0013\b\u000f\u001d\f\u0001\u0012\u00014\u0002`\u0019A\u0011\u0011L\u0001\t\u0002\u0019\fY\u0006\u0003\u0004|%\u0011\u0005\u0011QL\u0004\b\u0003C\u0012\u0002\u0012QA2\r\u001d\t9F\u0005EA\u0005kAaa_\u000b\u0005\u0002\t]\u0002\"CA?+\u0005\u0005I\u0011IA@\u0011%\ty)FA\u0001\n\u0003\t\t\nC\u0005\u0002\u0014V\t\t\u0011\"\u0001\u0003:!I\u0011\u0011U\u000b\u0002\u0002\u0013\u0005\u00131\u0015\u0005\n\u0003c+\u0012\u0011!C\u0001\u0005{A\u0011\"!0\u0016\u0003\u0003%\t%a0\t\u0013\u0005\u0005W#!A\u0005B\u0005\r\u0007\"CAc+\u0005\u0005I\u0011BAd\u000f\u001d\t9G\u0005EA\u0003S2q!a\u001b\u0013\u0011\u0003\u000bi\u0007\u0003\u0004|A\u0011\u0005\u00111\u0010\u0005\n\u0003{\u0002\u0013\u0011!C!\u0003\u007fB\u0011\"a$!\u0003\u0003%\t!!%\t\u0013\u0005M\u0005%!A\u0005\u0002\u0005U\u0005\"CAQA\u0005\u0005I\u0011IAR\u0011%\t\t\fIA\u0001\n\u0003\t\u0019\fC\u0005\u0002>\u0002\n\t\u0011\"\u0011\u0002@\"I\u0011\u0011\u0019\u0011\u0002\u0002\u0013\u0005\u00131\u0019\u0005\n\u0003\u000b\u0004\u0013\u0011!C\u0005\u0003\u000f<q!a4\u0013\u0011\u0003\u000b\tNB\u0004\u0002TJA\t)!6\t\rm\\C\u0011AAl\u0011%\tihKA\u0001\n\u0003\ny\bC\u0005\u0002\u0010.\n\t\u0011\"\u0001\u0002\u0012\"I\u00111S\u0016\u0002\u0002\u0013\u0005\u0011\u0011\u001c\u0005\n\u0003C[\u0013\u0011!C!\u0003GC\u0011\"!-,\u0003\u0003%\t!!8\t\u0013\u0005u6&!A\u0005B\u0005}\u0006\"CAaW\u0005\u0005I\u0011IAb\u0011%\t)mKA\u0001\n\u0013\t9mB\u0004\u0002bJA\t)a9\u0007\u000f\u0005\u0015(\u0003#!\u0002h\"11P\u000eC\u0001\u0003SD\u0011\"! 7\u0003\u0003%\t%a \t\u0013\u0005=e'!A\u0005\u0002\u0005E\u0005\"CAJm\u0005\u0005I\u0011AAv\u0011%\t\tKNA\u0001\n\u0003\n\u0019\u000bC\u0005\u00022Z\n\t\u0011\"\u0001\u0002p\"I\u0011Q\u0018\u001c\u0002\u0002\u0013\u0005\u0013q\u0018\u0005\n\u0003\u00034\u0014\u0011!C!\u0003\u0007D\u0011\"!27\u0003\u0003%I!a2\b\u000f\u0005M(\u0003#!\u0002v\u001a9\u0011q\u001f\n\t\u0002\u0006e\bBB>B\t\u0003\tY\u0010C\u0005\u0002~\u0005\u000b\t\u0011\"\u0011\u0002\u0000!I\u0011qR!\u0002\u0002\u0013\u0005\u0011\u0011\u0013\u0005\n\u0003'\u000b\u0015\u0011!C\u0001\u0003{D\u0011\"!)B\u0003\u0003%\t%a)\t\u0013\u0005E\u0016)!A\u0005\u0002\t\u0005\u0001\"CA_\u0003\u0006\u0005I\u0011IA`\u0011%\t\t-QA\u0001\n\u0003\n\u0019\rC\u0005\u0002F\u0006\u000b\t\u0011\"\u0003\u0002H\u001e9!Q\u0001\n\t\u0002\n\u001daa\u0002B\u0005%!\u0005%1\u0002\u0005\u0007w2#\tA!\u0004\t\u0013\u0005uD*!A\u0005B\u0005}\u0004\"CAH\u0019\u0006\u0005I\u0011AAI\u0011%\t\u0019\nTA\u0001\n\u0003\u0011y\u0001C\u0005\u0002\"2\u000b\t\u0011\"\u0011\u0002$\"I\u0011\u0011\u0017'\u0002\u0002\u0013\u0005!1\u0003\u0005\n\u0003{c\u0015\u0011!C!\u0003\u007fC\u0011\"!1M\u0003\u0003%\t%a1\t\u0013\u0005\u0015G*!A\u0005\n\u0005\u001dwa\u0002B\f%!\u0005%\u0011\u0004\u0004\b\u00057\u0011\u0002\u0012\u0011B\u000f\u0011\u0019Yx\u000b\"\u0001\u0003 !I\u0011QP,\u0002\u0002\u0013\u0005\u0013q\u0010\u0005\n\u0003\u001f;\u0016\u0011!C\u0001\u0003#C\u0011\"a%X\u0003\u0003%\tA!\t\t\u0013\u0005\u0005v+!A\u0005B\u0005\r\u0006\"CAY/\u0006\u0005I\u0011\u0001B\u0013\u0011%\tilVA\u0001\n\u0003\ny\fC\u0005\u0002B^\u000b\t\u0011\"\u0011\u0002D\"I\u0011QY,\u0002\u0002\u0013%\u0011q\u0019\u0005\n\u0005S\u0011\"\u0019!C\u0001\u0005WA\u0001Ba\r\u0013A\u0003%!QF\u0001\ba\u0006\u001c7.Y4f\u0015\t)g-\u0001\u0004dY&,g\u000e\u001e\u0006\u0003O\"\fA\u0001[5wK*\u0011\u0011N[\u0001\u0004gFd'BA6m\u0003\u0015\u0019\b/\u0019:l\u0015\tig.\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002_\u0006\u0019qN]4\u0004\u0001A\u0011!/A\u0007\u0002I\n9\u0001/Y2lC\u001e,7CA\u0001v!\t1\u00180D\u0001x\u0015\u0005A\u0018!B:dC2\f\u0017B\u0001>x\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!\u001d\u0002\f\u0011&4XMV3sg&|gnE\u0002\u0004k~\u0004b!!\u0001\u0002\u0010\u0005Ua\u0002BA\u0002\u0003\u001bqA!!\u0002\u0002\f5\u0011\u0011q\u0001\u0006\u0004\u0003\u0013\u0001\u0018A\u0002\u001fs_>$h(C\u0001y\u0013\t\u0019w/\u0003\u0003\u0002\u0012\u0005M!aB(sI\u0016\u0014X\r\u001a\u0006\u0003G^\u00042!a\u0006\u0004\u001b\u0005\t\u0011a\u00034vY24VM]:j_:,\"!!\b\u0011\t\u0005}\u0011q\u0005\b\u0005\u0003C\t\u0019\u0003E\u0002\u0002\u0006]L1!!\nx\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011FA\u0016\u0005\u0019\u0019FO]5oO*\u0019\u0011QE<\u0002\u0019\u0019,H\u000e\u001c,feNLwN\u001c\u0011\u0002\u0013\u0015DHO]1EKB\u001cXCAA\u001a!\u0019\t\t!!\u000e\u0002\u001e%!\u0011qGA\n\u0005\r\u0019V-]\u0001\u000bKb$(/\u0019#faN\u0004\u0013AC3yG2,8/[8og\u0006YQ\r_2mkNLwN\\:!)!\t)\"!\u0011\u0002D\u0005\u0015\u0003bBA\r\u0015\u0001\u0007\u0011Q\u0004\u0005\n\u0003_Q\u0001\u0013!a\u0001\u0003gA\u0011\"a\u000f\u000b!\u0003\u0005\r!a\r\u0002\u000f\r|W\u000e]1sKR!\u00111JA)!\r1\u0018QJ\u0005\u0004\u0003\u001f:(aA%oi\"9\u00111K\u0006A\u0002\u0005U\u0011\u0001\u0002;iCRL\u0003bA\u000b!WY\nEj\u0016\u0002\u0005mJz\u0006G\u0001\u0003iSZ,7C\u0001\nv)\t\ty\u0006E\u0002\u0002\u0018I\tAA\u001e\u001a`aA\u0019\u0011QM\u000b\u000e\u0003I\tAA\u001e\u001a`cA\u0019\u0011Q\r\u0011\u0003\tY\u0014t,M\n\bA\u0005U\u0011qNA;!\r1\u0018\u0011O\u0005\u0004\u0003g:(a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003\u0003\t9(\u0003\u0003\u0002z\u0005M!\u0001D*fe&\fG.\u001b>bE2,GCAA5\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\u0011\t\u0005\u0003\u0007\u000bi)\u0004\u0002\u0002\u0006*!\u0011qQAE\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0015\u0001\u00026bm\u0006LA!!\u000b\u0002\u0006\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111J\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t9*!(\u0011\u0007Y\fI*C\u0002\u0002\u001c^\u00141!\u00118z\u0011%\ty\nJA\u0001\u0002\u0004\tY%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003K\u0003b!a*\u0002.\u0006]UBAAU\u0015\r\tYk^\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAX\u0003S\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QWA^!\r1\u0018qW\u0005\u0004\u0003s;(a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003?3\u0013\u0011!a\u0001\u0003/\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0017\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u0003\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!3\u0011\t\u0005\r\u00151Z\u0005\u0005\u0003\u001b\f)I\u0001\u0004PE*,7\r^\u0001\u0005mJz&\u0007E\u0002\u0002f-\u0012AA\u001e\u001a`eM91&!\u0006\u0002p\u0005UDCAAi)\u0011\t9*a7\t\u0013\u0005}u&!AA\u0002\u0005-C\u0003BA[\u0003?D\u0011\"a(2\u0003\u0003\u0005\r!a&\u0002\tY\u0014tl\r\t\u0004\u0003K2$\u0001\u0002<3?N\u001arANA\u000b\u0003_\n)\b\u0006\u0002\u0002dR!\u0011qSAw\u0011%\tyJOA\u0001\u0002\u0004\tY\u0005\u0006\u0003\u00026\u0006E\b\"CAPy\u0005\u0005\t\u0019AAL\u0003\u001118g\u0018\u0019\u0011\u0007\u0005\u0015\u0014I\u0001\u0003wg}\u00034cB!\u0002\u0016\u0005=\u0014Q\u000f\u000b\u0003\u0003k$B!a&\u0002\u0000\"I\u0011qT#\u0002\u0002\u0003\u0007\u00111\n\u000b\u0005\u0003k\u0013\u0019\u0001C\u0005\u0002 \u001e\u000b\t\u00111\u0001\u0002\u0018\u0006!aoM02!\r\t)\u0007\u0014\u0002\u0005mNz\u0016gE\u0004M\u0003+\ty'!\u001e\u0015\u0005\t\u001dA\u0003BAL\u0005#A\u0011\"a(Q\u0003\u0003\u0005\r!a\u0013\u0015\t\u0005U&Q\u0003\u0005\n\u0003?\u0013\u0016\u0011!a\u0001\u0003/\u000bAA\u001e\u001b`aA\u0019\u0011QM,\u0003\tY$t\fM\n\b/\u0006U\u0011qNA;)\t\u0011I\u0002\u0006\u0003\u0002\u0018\n\r\u0002\"CAP7\u0006\u0005\t\u0019AA&)\u0011\t)La\n\t\u0013\u0005}U,!AA\u0002\u0005]\u0015\u0001G1mYN+\b\u000f]8si\u0016$\u0007*\u001b<f-\u0016\u00148/[8ogV\u0011!Q\u0006\t\u0007\u0003?\u0011y#!\u0006\n\t\tE\u00121\u0006\u0002\u0004'\u0016$\u0018!G1mYN+\b\u000f]8si\u0016$\u0007*\u001b<f-\u0016\u00148/[8og\u0002\u001ar!FA\u000b\u0003_\n)\b\u0006\u0002\u0002dQ!\u0011q\u0013B\u001e\u0011%\ty*GA\u0001\u0002\u0004\tY\u0005\u0006\u0003\u00026\n}\u0002\"CAP7\u0005\u0005\t\u0019AAL\u0003-A\u0015N^3WKJ\u001c\u0018n\u001c8\u0011\u0007\u0005]Qb\u0005\u0002\u000ekR\u0011!1I\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\t5#\u0006BA\u001a\u0005\u001fZ#A!\u0015\u0011\t\tM#QL\u0007\u0003\u0005+RAAa\u0016\u0003Z\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u00057:\u0018AC1o]>$\u0018\r^5p]&!!q\fB+\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a"
)
public final class package {
   public abstract static class HiveVersion implements Ordered {
      private final String fullVersion;
      private final Seq extraDeps;
      private final Seq exclusions;

      public boolean $less(final Object that) {
         return Ordered.$less$(this, that);
      }

      public boolean $greater(final Object that) {
         return Ordered.$greater$(this, that);
      }

      public boolean $less$eq(final Object that) {
         return Ordered.$less$eq$(this, that);
      }

      public boolean $greater$eq(final Object that) {
         return Ordered.$greater$eq$(this, that);
      }

      public int compareTo(final Object that) {
         return Ordered.compareTo$(this, that);
      }

      public String fullVersion() {
         return this.fullVersion;
      }

      public Seq extraDeps() {
         return this.extraDeps;
      }

      public Seq exclusions() {
         return this.exclusions;
      }

      public int compare(final HiveVersion that) {
         Object var2 = new Object();

         int var10000;
         try {
            int[] thisVersionParts = (int[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(this.fullVersion()), '.')), (x$1) -> BoxesRunTime.boxToInteger($anonfun$compare$1(x$1)), scala.reflect.ClassTag..MODULE$.Int());
            int[] thatVersionParts = (int[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(that.fullVersion()), '.')), (x$2) -> BoxesRunTime.boxToInteger($anonfun$compare$2(x$2)), scala.reflect.ClassTag..MODULE$.Int());
            scala.Predef..MODULE$.assert(thisVersionParts.length == thatVersionParts.length);
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.intArrayOps(thisVersionParts), scala.Predef..MODULE$.wrapIntArray(thatVersionParts))), (x0$1) -> {
               $anonfun$compare$3(var2, x0$1);
               return BoxedUnit.UNIT;
            });
            var10000 = 0;
         } catch (NonLocalReturnControl var6) {
            if (var6.key() != var2) {
               throw var6;
            }

            var10000 = var6.value$mcI$sp();
         }

         return var10000;
      }

      // $FF: synthetic method
      public static final int $anonfun$compare$1(final String x$1) {
         return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
      }

      // $FF: synthetic method
      public static final int $anonfun$compare$2(final String x$2) {
         return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$2));
      }

      // $FF: synthetic method
      public static final void $anonfun$compare$3(final Object nonLocalReturnKey1$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            int l = x0$1._1$mcI$sp();
            int r = x0$1._2$mcI$sp();
            int candidate = l - r;
            if (candidate != 0) {
               throw new NonLocalReturnControl.mcI.sp(nonLocalReturnKey1$1, candidate);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x0$1);
         }
      }

      public HiveVersion(final String fullVersion, final Seq extraDeps, final Seq exclusions) {
         this.fullVersion = fullVersion;
         this.extraDeps = extraDeps;
         this.exclusions = exclusions;
         Ordered.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class HiveVersion$ {
      public static final HiveVersion$ MODULE$ = new HiveVersion$();

      public Seq $lessinit$greater$default$2() {
         return scala.collection.immutable.Nil..MODULE$;
      }

      public Seq $lessinit$greater$default$3() {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   public static class hive$ {
      public static final hive$ MODULE$ = new hive$();
      private static final Set allSupportedHiveVersions;

      static {
         allSupportedHiveVersions = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new HiveVersion[]{package$hive$v2_0$.MODULE$, package$hive$v2_1$.MODULE$, package$hive$v2_2$.MODULE$, package$hive$v2_3$.MODULE$, package$hive$v3_0$.MODULE$, package$hive$v3_1$.MODULE$, package$hive$v4_0$.MODULE$}));
      }

      public Set allSupportedHiveVersions() {
         return allSupportedHiveVersions;
      }
   }
}
