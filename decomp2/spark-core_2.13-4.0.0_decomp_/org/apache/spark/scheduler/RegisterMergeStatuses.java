package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\t\u0003!\u0011#Q\u0001\n}B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t#\u0002\u0011\t\u0012)A\u0005\u000b\")!\u000b\u0001C\u0001'\"9q\u000bAA\u0001\n\u0003A\u0006bB.\u0001#\u0003%\t\u0001\u0018\u0005\bO\u0002\t\n\u0011\"\u0001i\u0011\u001dQ\u0007!!A\u0005B-Dq\u0001\u001e\u0001\u0002\u0002\u0013\u0005Q\u000fC\u0004w\u0001\u0005\u0005I\u0011A<\t\u000fu\u0004\u0011\u0011!C!}\"I\u00111\u0002\u0001\u0002\u0002\u0013\u0005\u0011Q\u0002\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\t\u0013\u0005\u0005\u0002!!A\u0005B\u0005\r\u0002\"CA\u0013\u0001\u0005\u0005I\u0011IA\u0014\u000f)\tYCGA\u0001\u0012\u0003Q\u0012Q\u0006\u0004\n3i\t\t\u0011#\u0001\u001b\u0003_AaAU\n\u0005\u0002\u0005\u001d\u0003\"CA\u0011'\u0005\u0005IQIA\u0012\u0011%\tIeEA\u0001\n\u0003\u000bY\u0005C\u0005\u0002RM\t\t\u0011\"!\u0002T!I\u0011\u0011M\n\u0002\u0002\u0013%\u00111\r\u0002\u0016%\u0016<\u0017n\u001d;fe6+'oZ3Ti\u0006$Xo]3t\u0015\tYB$A\u0005tG\",G-\u001e7fe*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xmE\u0003\u0001G%j\u0003\u0007\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011AG\u0005\u0003Yi\u0011\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019;\u001d\t\u0011\u0004H\u0004\u00024o5\tAG\u0003\u00026m\u00051AH]8piz\u001a\u0001!C\u0001'\u0013\tIT%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d&\u0003\u0015\u0019H/Y4f+\u0005y\u0004C\u0001\u0016A\u0013\t\t%DA\bTQV4g\r\\3NCB\u001cF/Y4f\u0003\u0019\u0019H/Y4fA\u0005iQ.\u001a:hKN#\u0018\r^;tKN,\u0012!\u0012\t\u0004c\u0019C\u0015BA$=\u0005\r\u0019V-\u001d\t\u0005I%[e*\u0003\u0002KK\t1A+\u001e9mKJ\u0002\"\u0001\n'\n\u00055+#aA%oiB\u0011!fT\u0005\u0003!j\u00111\"T3sO\u0016\u001cF/\u0019;vg\u0006qQ.\u001a:hKN#\u0018\r^;tKN\u0004\u0013A\u0002\u001fj]&$h\bF\u0002U+Z\u0003\"A\u000b\u0001\t\u000bu*\u0001\u0019A \t\u000b\r+\u0001\u0019A#\u0002\t\r|\u0007/\u001f\u000b\u0004)fS\u0006bB\u001f\u0007!\u0003\u0005\ra\u0010\u0005\b\u0007\u001a\u0001\n\u00111\u0001F\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0018\u0016\u0003\u007fy[\u0013a\u0018\t\u0003A\u0016l\u0011!\u0019\u0006\u0003E\u000e\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0011,\u0013AC1o]>$\u0018\r^5p]&\u0011a-\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002S*\u0012QIX\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00031\u0004\"!\u001c:\u000e\u00039T!a\u001c9\u0002\t1\fgn\u001a\u0006\u0002c\u0006!!.\u0019<b\u0013\t\u0019hN\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002\u0017\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001=|!\t!\u00130\u0003\u0002{K\t\u0019\u0011I\\=\t\u000fq\\\u0011\u0011!a\u0001\u0017\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a \t\u0006\u0003\u0003\t9\u0001_\u0007\u0003\u0003\u0007Q1!!\u0002&\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0013\t\u0019A\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\b\u0003+\u00012\u0001JA\t\u0013\r\t\u0019\"\n\u0002\b\u0005>|G.Z1o\u0011\u001daX\"!AA\u0002a\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A.a\u0007\t\u000fqt\u0011\u0011!a\u0001\u0017\u0006A\u0001.Y:i\u0007>$W\rF\u0001L\u0003!!xn\u0015;sS:<G#\u00017\u0002\r\u0015\fX/\u00197t)\u0011\ty!!\u000b\t\u000fq\f\u0012\u0011!a\u0001q\u0006)\"+Z4jgR,'/T3sO\u0016\u001cF/\u0019;vg\u0016\u001c\bC\u0001\u0016\u0014'\u0015\u0019\u0012\u0011GA\u001f!\u001d\t\u0019$!\u000f@\u000bRk!!!\u000e\u000b\u0007\u0005]R%A\u0004sk:$\u0018.\\3\n\t\u0005m\u0012Q\u0007\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA \u0003\u000bj!!!\u0011\u000b\u0007\u0005\r\u0003/\u0001\u0002j_&\u00191(!\u0011\u0015\u0005\u00055\u0012!B1qa2LH#\u0002+\u0002N\u0005=\u0003\"B\u001f\u0017\u0001\u0004y\u0004\"B\"\u0017\u0001\u0004)\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003+\ni\u0006E\u0003%\u0003/\nY&C\u0002\u0002Z\u0015\u0012aa\u00149uS>t\u0007\u0003\u0002\u0013J\u007f\u0015C\u0001\"a\u0018\u0018\u0003\u0003\u0005\r\u0001V\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA3!\ri\u0017qM\u0005\u0004\u0003Sr'AB(cU\u0016\u001cG\u000f"
)
public class RegisterMergeStatuses implements DAGSchedulerEvent, Product, Serializable {
   private final ShuffleMapStage stage;
   private final Seq mergeStatuses;

   public static Option unapply(final RegisterMergeStatuses x$0) {
      return RegisterMergeStatuses$.MODULE$.unapply(x$0);
   }

   public static RegisterMergeStatuses apply(final ShuffleMapStage stage, final Seq mergeStatuses) {
      return RegisterMergeStatuses$.MODULE$.apply(stage, mergeStatuses);
   }

   public static Function1 tupled() {
      return RegisterMergeStatuses$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RegisterMergeStatuses$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ShuffleMapStage stage() {
      return this.stage;
   }

   public Seq mergeStatuses() {
      return this.mergeStatuses;
   }

   public RegisterMergeStatuses copy(final ShuffleMapStage stage, final Seq mergeStatuses) {
      return new RegisterMergeStatuses(stage, mergeStatuses);
   }

   public ShuffleMapStage copy$default$1() {
      return this.stage();
   }

   public Seq copy$default$2() {
      return this.mergeStatuses();
   }

   public String productPrefix() {
      return "RegisterMergeStatuses";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.stage();
         }
         case 1 -> {
            return this.mergeStatuses();
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
      return x$1 instanceof RegisterMergeStatuses;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stage";
         }
         case 1 -> {
            return "mergeStatuses";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof RegisterMergeStatuses) {
               label48: {
                  RegisterMergeStatuses var4 = (RegisterMergeStatuses)x$1;
                  ShuffleMapStage var10000 = this.stage();
                  ShuffleMapStage var5 = var4.stage();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.mergeStatuses();
                  Seq var6 = var4.mergeStatuses();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public RegisterMergeStatuses(final ShuffleMapStage stage, final Seq mergeStatuses) {
      this.stage = stage;
      this.mergeStatuses = mergeStatuses;
      Product.$init$(this);
   }
}
