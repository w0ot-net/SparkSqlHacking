package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re!\u0002\u000f\u001e\u0001v)\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011\u0015\u0003!\u0011#Q\u0001\n\tC\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\u0011\"A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005Z\u0001\tE\t\u0015!\u0003T\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u001d\u0001\u0007!!A\u0005\u0002\u0005Dq!\u001a\u0001\u0012\u0002\u0013\u0005a\rC\u0004r\u0001E\u0005I\u0011\u0001:\t\u000fQ\u0004\u0011\u0013!C\u0001k\"9q\u000fAA\u0001\n\u0003B\b\u0002CA\u0001\u0001\u0005\u0005I\u0011A!\t\u0013\u0005\r\u0001!!A\u0005\u0002\u0005\u0015\u0001\"CA\t\u0001\u0005\u0005I\u0011IA\n\u0011%\t\t\u0003AA\u0001\n\u0003\t\u0019\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u00111\u0007\u0001\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003sA\u0011\"a\u000f\u0001\u0003\u0003%\t%!\u0010\b\u0015\u0005\u0005S$!A\t\u0002u\t\u0019EB\u0005\u001d;\u0005\u0005\t\u0012A\u000f\u0002F!1!L\u0006C\u0001\u0003;B\u0011\"a\u000e\u0017\u0003\u0003%)%!\u000f\t\u0013\u0005}c#!A\u0005\u0002\u0006\u0005\u0004\"CA5-\u0005\u0005I\u0011QA6\u0011%\tIHFA\u0001\n\u0013\tYHA\u0006Ti\u0006<WMR1jY\u0016$'B\u0001\u0010 \u0003%\u00198\r[3ek2,'O\u0003\u0002!C\u0005)1\u000f]1sW*\u0011!eI\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\n1a\u001c:h'\u0015\u0001a\u0005\f\u00194!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u0019\te.\u001f*fMB\u0011QFL\u0007\u0002;%\u0011q&\b\u0002\u0012\t\u0006;5k\u00195fIVdWM]#wK:$\bCA\u00142\u0013\t\u0011\u0004FA\u0004Qe>$Wo\u0019;\u0011\u0005QjdBA\u001b<\u001d\t1$(D\u00018\u0015\tA\u0014(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005I\u0013B\u0001\u001f)\u0003\u001d\u0001\u0018mY6bO\u0016L!AP \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005qB\u0013aB:uC\u001e,\u0017\nZ\u000b\u0002\u0005B\u0011qeQ\u0005\u0003\t\"\u00121!\u00138u\u0003!\u0019H/Y4f\u0013\u0012\u0004\u0013A\u0002:fCN|g.F\u0001I!\tIUJ\u0004\u0002K\u0017B\u0011a\u0007K\u0005\u0003\u0019\"\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011A\nK\u0001\be\u0016\f7o\u001c8!\u0003%)\u0007pY3qi&|g.F\u0001T!\r9CKV\u0005\u0003+\"\u0012aa\u00149uS>t\u0007C\u0001\u001bX\u0013\tAvHA\u0005UQJ|w/\u00192mK\u0006QQ\r_2faRLwN\u001c\u0011\u0002\rqJg.\u001b;?)\u0011aVLX0\u0011\u00055\u0002\u0001\"\u0002!\b\u0001\u0004\u0011\u0005\"\u0002$\b\u0001\u0004A\u0005\"B)\b\u0001\u0004\u0019\u0016\u0001B2paf$B\u0001\u00182dI\"9\u0001\t\u0003I\u0001\u0002\u0004\u0011\u0005b\u0002$\t!\u0003\u0005\r\u0001\u0013\u0005\b#\"\u0001\n\u00111\u0001T\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u001a\u0016\u0003\u0005\"\\\u0013!\u001b\t\u0003U>l\u0011a\u001b\u0006\u0003Y6\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00059D\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001o\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002g*\u0012\u0001\n[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u00051(FA*i\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u0010\u0005\u0002{\u007f6\t1P\u0003\u0002}{\u0006!A.\u00198h\u0015\u0005q\u0018\u0001\u00026bm\u0006L!AT>\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qAA\u0007!\r9\u0013\u0011B\u0005\u0004\u0003\u0017A#aA!os\"A\u0011q\u0002\b\u0002\u0002\u0003\u0007!)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003+\u0001b!a\u0006\u0002\u001e\u0005\u001dQBAA\r\u0015\r\tY\u0002K\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0010\u00033\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QEA\u0016!\r9\u0013qE\u0005\u0004\u0003SA#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003\u001f\u0001\u0012\u0011!a\u0001\u0003\u000f\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u00110!\r\t\u0011\u0005=\u0011#!AA\u0002\t\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0005\u0006AAo\\*ue&tw\rF\u0001z\u0003\u0019)\u0017/^1mgR!\u0011QEA \u0011%\ty\u0001FA\u0001\u0002\u0004\t9!A\u0006Ti\u0006<WMR1jY\u0016$\u0007CA\u0017\u0017'\u00151\u0012qIA*!!\tI%a\u0014C\u0011NcVBAA&\u0015\r\ti\u0005K\u0001\beVtG/[7f\u0013\u0011\t\t&a\u0013\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002V\u0005mSBAA,\u0015\r\tI&`\u0001\u0003S>L1APA,)\t\t\u0019%A\u0003baBd\u0017\u0010F\u0004]\u0003G\n)'a\u001a\t\u000b\u0001K\u0002\u0019\u0001\"\t\u000b\u0019K\u0002\u0019\u0001%\t\u000bEK\u0002\u0019A*\u0002\u000fUt\u0017\r\u001d9msR!\u0011QNA;!\u00119C+a\u001c\u0011\r\u001d\n\tH\u0011%T\u0013\r\t\u0019\b\u000b\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u0005]$$!AA\u0002q\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\bE\u0002{\u0003\u007fJ1!!!|\u0005\u0019y%M[3di\u0002"
)
public class StageFailed implements DAGSchedulerEvent, Product, Serializable {
   private final int stageId;
   private final String reason;
   private final Option exception;

   public static Option unapply(final StageFailed x$0) {
      return StageFailed$.MODULE$.unapply(x$0);
   }

   public static StageFailed apply(final int stageId, final String reason, final Option exception) {
      return StageFailed$.MODULE$.apply(stageId, reason, exception);
   }

   public static Function1 tupled() {
      return StageFailed$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return StageFailed$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public String reason() {
      return this.reason;
   }

   public Option exception() {
      return this.exception;
   }

   public StageFailed copy(final int stageId, final String reason, final Option exception) {
      return new StageFailed(stageId, reason, exception);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public String copy$default$2() {
      return this.reason();
   }

   public Option copy$default$3() {
      return this.exception();
   }

   public String productPrefix() {
      return "StageFailed";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 1 -> {
            return this.reason();
         }
         case 2 -> {
            return this.exception();
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
      return x$1 instanceof StageFailed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageId";
         }
         case 1 -> {
            return "reason";
         }
         case 2 -> {
            return "exception";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      var1 = Statics.mix(var1, Statics.anyHash(this.exception()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof StageFailed) {
               StageFailed var4 = (StageFailed)x$1;
               if (this.stageId() == var4.stageId()) {
                  label52: {
                     String var10000 = this.reason();
                     String var5 = var4.reason();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.exception();
                     Option var6 = var4.exception();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
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

   public StageFailed(final int stageId, final String reason, final Option exception) {
      this.stageId = stageId;
      this.reason = reason;
      this.exception = exception;
      Product.$init$(this);
   }
}
