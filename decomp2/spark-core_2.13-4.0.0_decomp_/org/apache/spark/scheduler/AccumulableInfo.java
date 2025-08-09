package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=h\u0001B\u0017/\u0001^B\u0001\"\u0014\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t%\u0002\u0011\t\u0012)A\u0005\u001f\"A1\u000b\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005a\u0001\tE\t\u0015!\u0003V\u0011!\t\u0007A!f\u0001\n\u0003\u0011\u0007\u0002C4\u0001\u0005#\u0005\u000b\u0011B2\t\u0011!\u0004!Q3A\u0005\u0002\tD\u0001\"\u001b\u0001\u0003\u0012\u0003\u0006Ia\u0019\u0005\nU\u0002\u0011)\u0019!C\u0001a-D\u0001b\u001c\u0001\u0003\u0012\u0003\u0006I\u0001\u001c\u0005\na\u0002\u0011)\u0019!C\u0001a-D\u0001\"\u001d\u0001\u0003\u0012\u0003\u0006I\u0001\u001c\u0005\ne\u0002\u0011)\u0019!C\u0001aQC\u0001b\u001d\u0001\u0003\u0012\u0003\u0006I!\u0016\u0005\u0007i\u0002!\t\u0001M;\t\u0011}\u0004\u0011\u0011!C\u0001\u0003\u0003A\u0011\"!\u0005\u0001#\u0003%\t!a\u0005\t\u0013\u0005%\u0002!%A\u0005\u0002\u0005-\u0002\"CA\u0018\u0001E\u0005I\u0011AA\u0019\u0011%\t)\u0004AI\u0001\n\u0003\t\t\u0004C\u0005\u00028\u0001\t\n\u0011\"\u0001\u0002:!I\u0011Q\b\u0001\u0012\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u007f\u0001\u0011\u0013!C\u0001\u0003WA\u0001\"!\u0011\u0001\u0017\u0003%\ta\u001b\u0005\t\u0003\u0007\u00021\u0012!C\u0001W\"A\u0011Q\t\u0001\f\u0002\u0013\u0005A\u000bC\u0005\u0002H\u0001\t\t\u0011\"\u0011\u0002J!I\u0011\u0011\f\u0001\u0002\u0002\u0013\u0005\u00111\f\u0005\n\u0003G\u0002\u0011\u0011!C\u0001\u0003KB\u0011\"a\u001b\u0001\u0003\u0003%\t%!\u001c\t\u0013\u0005m\u0004!!A\u0005\u0002\u0005u\u0004\"CAA\u0001\u0005\u0005I\u0011IAB\u0011%\t9\tAA\u0001\n\u0003\nI\tC\u0005\u0002\f\u0002\t\t\u0011\"\u0011\u0002\u000e\"I\u0011q\u0012\u0001\u0002\u0002\u0013\u0005\u0013\u0011S\u0004\n\u0003Cs\u0013\u0011!E\u0001\u0003G3\u0001\"\f\u0018\u0002\u0002#\u0005\u0011Q\u0015\u0005\u0007i\u0016\"\t!!0\t\u0013\u0005-U%!A\u0005F\u00055\u0005\"CA`K\u0005\u0005I\u0011QAa\u0011%\t\t.JI\u0001\n\u0003\tY\u0003C\u0005\u0002T\u0016\n\t\u0011\"!\u0002V\"Q\u00111]\u0013\u0012\u0002\u0013\u0005\u0001'a\u000b\t\u0013\u0005\u0015X%!A\u0005\n\u0005\u001d(aD!dGVlW\u000f\\1cY\u0016LeNZ8\u000b\u0005=\u0002\u0014!C:dQ\u0016$W\u000f\\3s\u0015\t\t$'A\u0003ta\u0006\u00148N\u0003\u00024i\u00051\u0011\r]1dQ\u0016T\u0011!N\u0001\u0004_J<7\u0001A\n\u0005\u0001ar\u0014\t\u0005\u0002:y5\t!HC\u0001<\u0003\u0015\u00198-\u00197b\u0013\ti$H\u0001\u0004B]f\u0014VM\u001a\t\u0003s}J!\u0001\u0011\u001e\u0003\u000fA\u0013x\u000eZ;diB\u0011!I\u0013\b\u0003\u0007\"s!\u0001R$\u000e\u0003\u0015S!A\u0012\u001c\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0014BA%;\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0013'\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005%S\u0014AA5e+\u0005y\u0005CA\u001dQ\u0013\t\t&H\u0001\u0003M_:<\u0017aA5eA\u0005!a.Y7f+\u0005)\u0006cA\u001dW1&\u0011qK\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005ekfB\u0001.\\!\t!%(\u0003\u0002]u\u00051\u0001K]3eK\u001aL!AX0\u0003\rM#(/\u001b8h\u0015\ta&(A\u0003oC6,\u0007%\u0001\u0004va\u0012\fG/Z\u000b\u0002GB\u0019\u0011H\u00163\u0011\u0005e*\u0017B\u00014;\u0005\r\te._\u0001\bkB$\u0017\r^3!\u0003\u00151\u0018\r\\;f\u0003\u00191\u0018\r\\;fA\u0005A\u0011N\u001c;fe:\fG.F\u0001m!\tIT.\u0003\u0002ou\t9!i\\8mK\u0006t\u0017!C5oi\u0016\u0014h.\u00197!\u0003E\u0019w.\u001e8u\r\u0006LG.\u001a3WC2,Xm]\u0001\u0013G>,h\u000e\u001e$bS2,GMV1mk\u0016\u001c\b%\u0001\u0005nKR\fG-\u0019;b\u0003%iW\r^1eCR\f\u0007%\u0001\u0004=S:LGO\u0010\u000b\tmbL(p\u001f?~}B\u0011q\u000fA\u0007\u0002]!)Qj\u0004a\u0001\u001f\")1k\u0004a\u0001+\")\u0011m\u0004a\u0001G\")\u0001n\u0004a\u0001G\")!n\u0004a\u0001Y\")\u0001o\u0004a\u0001Y\"9!o\u0004I\u0001\u0002\u0004)\u0016\u0001B2paf$rB^A\u0002\u0003\u000b\t9!!\u0003\u0002\f\u00055\u0011q\u0002\u0005\b\u001bB\u0001\n\u00111\u0001P\u0011\u001d\u0019\u0006\u0003%AA\u0002UCq!\u0019\t\u0011\u0002\u0003\u00071\rC\u0004i!A\u0005\t\u0019A2\t\u000f)\u0004\u0002\u0013!a\u0001Y\"9\u0001\u000f\u0005I\u0001\u0002\u0004a\u0007b\u0002:\u0011!\u0003\u0005\r!V\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t)BK\u0002P\u0003/Y#!!\u0007\u0011\t\u0005m\u0011QE\u0007\u0003\u0003;QA!a\b\u0002\"\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003GQ\u0014AC1o]>$\u0018\r^5p]&!\u0011qEA\u000f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tiCK\u0002V\u0003/\tabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u00024)\u001a1-a\u0006\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAA\u001eU\ra\u0017qC\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]\n\u0011#\u001b8uKJt\u0017\r\u001c\u0013bG\u000e,7o\u001d\u00135\u0003i\u0019w.\u001e8u\r\u0006LG.\u001a3WC2,Xm\u001d\u0013bG\u000e,7o\u001d\u00136\u0003EiW\r^1eCR\fG%Y2dKN\u001cHEN\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005-\u0003\u0003BA'\u0003/j!!a\u0014\u000b\t\u0005E\u00131K\u0001\u0005Y\u0006twM\u0003\u0002\u0002V\u0005!!.\u0019<b\u0013\rq\u0016qJ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003;\u00022!OA0\u0013\r\t\tG\u000f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004I\u0006\u001d\u0004\"CA5;\u0005\u0005\t\u0019AA/\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u000e\t\u0006\u0003c\n9\bZ\u0007\u0003\u0003gR1!!\u001e;\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003s\n\u0019H\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u00017\u0002\u0000!A\u0011\u0011N\u0010\u0002\u0002\u0003\u0007A-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA&\u0003\u000bC\u0011\"!\u001b!\u0003\u0003\u0005\r!!\u0018\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0018\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0013\u0002\r\u0015\fX/\u00197t)\ra\u00171\u0013\u0005\t\u0003S\u001a\u0013\u0011!a\u0001I\"\u001a\u0001!a&\u0011\t\u0005e\u0015QT\u0007\u0003\u00037S1!a\t1\u0013\u0011\ty*a'\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u001f\u0005\u001b7-^7vY\u0006\u0014G.Z%oM>\u0004\"a^\u0013\u0014\u000b\u0015\n9+a-\u0011\u0019\u0005%\u0016qV(VG\u000edG.\u0016<\u000e\u0005\u0005-&bAAWu\u00059!/\u001e8uS6,\u0017\u0002BAY\u0003W\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c88!\u0011\t),a/\u000e\u0005\u0005]&\u0002BA]\u0003'\n!![8\n\u0007-\u000b9\f\u0006\u0002\u0002$\u0006)\u0011\r\u001d9msRya/a1\u0002F\u0006\u001d\u0017\u0011ZAf\u0003\u001b\fy\rC\u0003NQ\u0001\u0007q\nC\u0003TQ\u0001\u0007Q\u000bC\u0003bQ\u0001\u00071\rC\u0003iQ\u0001\u00071\rC\u0003kQ\u0001\u0007A\u000eC\u0003qQ\u0001\u0007A\u000eC\u0004sQA\u0005\t\u0019A+\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uI]\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002X\u0006}\u0007\u0003B\u001dW\u00033\u0004\"\"OAn\u001fV\u001b7\r\u001c7V\u0013\r\tiN\u000f\u0002\u0007)V\u0004H.Z\u001c\t\u0011\u0005\u0005(&!AA\u0002Y\f1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%o\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u001e\t\u0005\u0003\u001b\nY/\u0003\u0003\u0002n\u0006=#AB(cU\u0016\u001cG\u000f"
)
public class AccumulableInfo implements Product, Serializable {
   private final long id;
   private final Option name;
   private final Option update;
   private final Option value;
   private final boolean internal;
   private final boolean countFailedValues;
   private final Option metadata;

   public static Option unapply(final AccumulableInfo x$0) {
      return AccumulableInfo$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$7() {
      return AccumulableInfo$.MODULE$.apply$default$7();
   }

   public static AccumulableInfo apply(final long id, final Option name, final Option update, final Option value, final boolean internal, final boolean countFailedValues, final Option metadata) {
      return AccumulableInfo$.MODULE$.apply(id, name, update, value, internal, countFailedValues, metadata);
   }

   public static Function1 tupled() {
      return AccumulableInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AccumulableInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean internal$access$4() {
      return this.internal;
   }

   public boolean countFailedValues$access$5() {
      return this.countFailedValues;
   }

   public Option metadata$access$6() {
      return this.metadata;
   }

   public long id() {
      return this.id;
   }

   public Option name() {
      return this.name;
   }

   public Option update() {
      return this.update;
   }

   public Option value() {
      return this.value;
   }

   public boolean internal() {
      return this.internal;
   }

   public boolean countFailedValues() {
      return this.countFailedValues;
   }

   public Option metadata() {
      return this.metadata;
   }

   public AccumulableInfo copy(final long id, final Option name, final Option update, final Option value, final boolean internal, final boolean countFailedValues, final Option metadata) {
      return new AccumulableInfo(id, name, update, value, internal, countFailedValues, metadata);
   }

   public long copy$default$1() {
      return this.id();
   }

   public Option copy$default$2() {
      return this.name();
   }

   public Option copy$default$3() {
      return this.update();
   }

   public Option copy$default$4() {
      return this.value();
   }

   public boolean copy$default$5() {
      return this.internal();
   }

   public boolean copy$default$6() {
      return this.countFailedValues();
   }

   public Option copy$default$7() {
      return this.metadata();
   }

   public String productPrefix() {
      return "AccumulableInfo";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.id());
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return this.update();
         }
         case 3 -> {
            return this.value();
         }
         case 4 -> {
            return BoxesRunTime.boxToBoolean(this.internal$access$4());
         }
         case 5 -> {
            return BoxesRunTime.boxToBoolean(this.countFailedValues$access$5());
         }
         case 6 -> {
            return this.metadata$access$6();
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
      return x$1 instanceof AccumulableInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "update";
         }
         case 3 -> {
            return "value";
         }
         case 4 -> {
            return "internal";
         }
         case 5 -> {
            return "countFailedValues";
         }
         case 6 -> {
            return "metadata";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.update()));
      var1 = Statics.mix(var1, Statics.anyHash(this.value()));
      var1 = Statics.mix(var1, this.internal$access$4() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.countFailedValues$access$5() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.metadata$access$6()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof AccumulableInfo) {
               AccumulableInfo var4 = (AccumulableInfo)x$1;
               if (this.id() == var4.id() && this.internal$access$4() == var4.internal$access$4() && this.countFailedValues$access$5() == var4.countFailedValues$access$5()) {
                  label76: {
                     Option var10000 = this.name();
                     Option var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     var10000 = this.update();
                     Option var6 = var4.update();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label76;
                     }

                     var10000 = this.value();
                     Option var7 = var4.value();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label76;
                     }

                     var10000 = this.metadata$access$6();
                     Option var8 = var4.metadata$access$6();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
                     }
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

   public AccumulableInfo(final long id, final Option name, final Option update, final Option value, final boolean internal, final boolean countFailedValues, final Option metadata) {
      this.id = id;
      this.name = name;
      this.update = update;
      this.value = value;
      this.internal = internal;
      this.countFailedValues = countFailedValues;
      this.metadata = metadata;
      Product.$init$(this);
   }
}
