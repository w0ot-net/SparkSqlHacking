package scala.collection.parallel.mutable;

import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.ArraySeq.;
import scala.collection.parallel.Task;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dda\u0002\r\u001a!\u0003\r\tA\t\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0005\u0002!\te\u0011\u0005\u0006\u0013\u0002!)A\u0013\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u0006+\u0002!\tE\u0016\u0004\u0005?\u0002\u0001\u0001\r\u0003\u0005h\r\t\u0005\t\u0015!\u0003i\u0011!YgA!A!\u0002\u00131\u0005\u0002\u00037\u0007\u0005\u0003\u0005\u000b\u0011\u0002$\t\u000b54A\u0011\u00018\t\u000fI4\u0001\u0019!C\u0001g\"9AO\u0002a\u0001\n\u0003)\bB\u0002=\u0007A\u0003&q\bC\u0003z\r\u0011\u0005!\u0010C\u0004\u0002\u0002\u0019!I!a\u0001\t\u000f\u0005ma\u0001\"\u0003\u0002\u001e!9\u0011\u0011\u0006\u0004\u0005\u0002\u0005-\u0002bBA\u001d\r\u0011\u0005\u00111H\u0004\b\u0003\u0007J\u0002\u0012AA#\r\u0019A\u0012\u0004#\u0001\u0002H!1Q\u000e\u0006C\u0001\u0003\u0013Bq!a\u0013\u0015\t\u0003\ti\u0005C\u0004\u0002LQ!\t!!\u0018\u00033I+7/\u001b>bE2,\u0007+\u0019:BeJ\f\u0017pQ8nE&tWM\u001d\u0006\u00035m\tq!\\;uC\ndWM\u0003\u0002\u001d;\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u001f?\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u0001\nQa]2bY\u0006\u001c\u0001!\u0006\u0002$]M\u0019\u0001\u0001\n\u0015\u0011\u0005\u00152S\"A\u0010\n\u0005\u001dz\"AB!osJ+g\rE\u0003*U1:$(D\u0001\u001a\u0013\tY\u0013D\u0001\u0007MCjL8i\\7cS:,'\u000f\u0005\u0002.]1\u0001A!B\u0018\u0001\u0005\u0004\u0001$!\u0001+\u0012\u0005E\"\u0004CA\u00133\u0013\t\u0019tDA\u0004O_RD\u0017N\\4\u0011\u0005\u0015*\u0014B\u0001\u001c \u0005\r\te.\u001f\t\u0004Sab\u0013BA\u001d\u001a\u0005!\u0001\u0016M]!se\u0006L\bcA\u0015<Y%\u0011A(\u0007\u0002\u0013\u000bb\u0004xn]3e\u0003J\u0014\u0018-\u001f\"vM\u001a,'/\u0001\u0004%S:LG\u000f\n\u000b\u0002\u007fA\u0011Q\u0005Q\u0005\u0003\u0003~\u0011A!\u00168ji\u0006A1/\u001b>f\u0011&tG\u000f\u0006\u0002@\t\")QI\u0001a\u0001\r\u0006\u00111O\u001f\t\u0003K\u001dK!\u0001S\u0010\u0003\u0007%sG/A\boK^d\u0015M_=D_6\u0014\u0017N\\3s)\tYE\nE\u0002*\u00011BQ!T\u0002A\u00029\u000b\u0011a\u0019\t\u0004\u001fFST\"\u0001)\u000b\u0005ii\u0012B\u0001*Q\u0005-\t%O]1z\u0005V4g-\u001a:\u0002\u001f\u0005dGn\\2bi\u0016\fe\u000eZ\"paf,\u0012aN\u0001\ti>\u001cFO]5oOR\tq\u000b\u0005\u0002Y;6\t\u0011L\u0003\u0002[7\u0006!A.\u00198h\u0015\u0005a\u0016\u0001\u00026bm\u0006L!AX-\u0003\rM#(/\u001b8h\u0005A\u0019u\u000e]=DQ\u0006Lg\u000eV8BeJ\f\u0017pE\u0002\u0007I\u0005\u0004BAY2@K6\t1$\u0003\u0002e7\t!A+Y:l!\t1g!D\u0001\u0001\u0003\u0015\t'O]1z!\r)\u0013\u000eN\u0005\u0003U~\u0011Q!\u0011:sCf\faa\u001c4gg\u0016$\u0018a\u00025po6\fg._\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0015|\u0007/\u001d\u0005\u0006O*\u0001\r\u0001\u001b\u0005\u0006W*\u0001\rA\u0012\u0005\u0006Y*\u0001\rAR\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0016\u0003}\n!B]3tk2$x\fJ3r)\tyd\u000fC\u0004x\u0019\u0005\u0005\t\u0019A \u0002\u0007a$\u0013'A\u0004sKN,H\u000e\u001e\u0011\u0002\t1,\u0017M\u001a\u000b\u0003\u007fmDQ\u0001 \bA\u0002u\fA\u0001\u001d:fmB\u0019QE` \n\u0005}|\"AB(qi&|g.A\u0005d_BL8\t[;oWRYq(!\u0002\u0002\f\u0005=\u00111CA\f\u0011\u001d\t9a\u0004a\u0001\u0003\u0013\tqAY;gM\u0006\u0014(\u000fE\u0002&S\u0012Ba!!\u0004\u0010\u0001\u00041\u0015!\u00032vM\u001a\u001cF/\u0019:u\u0011\u0019\t\tb\u0004a\u0001Q\u0006\u0011!/\u0019\u0005\u0007\u0003+y\u0001\u0019\u0001$\u0002\u0015\u0005\u0014(/Y=Ti\u0006\u0014H\u000f\u0003\u0004\u0002\u001a=\u0001\rAR\u0001\u0006k:$\u0018\u000e\\\u0001\nM&tGm\u0015;beR$B!a\b\u0002&A)Q%!\tG\r&\u0019\u00111E\u0010\u0003\rQ+\b\u000f\\33\u0011\u0019\t9\u0003\u0005a\u0001\r\u0006\u0019\u0001o\\:\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u00055\u0002#BA\u0018\u0003k)WBAA\u0019\u0015\r\t\u0019$H\u0001\nS6lW\u000f^1cY\u0016LA!a\u000e\u00022\t!A*[:u\u0003I\u0019\bn\\;mIN\u0003H.\u001b;GkJ$\b.\u001a:\u0016\u0005\u0005u\u0002cA\u0013\u0002@%\u0019\u0011\u0011I\u0010\u0003\u000f\t{w\u000e\\3b]\u0006I\"+Z:ju\u0006\u0014G.\u001a)be\u0006\u0013(/Y=D_6\u0014\u0017N\\3s!\tICc\u0005\u0002\u0015IQ\u0011\u0011QI\u0001\u0006CB\u0004H._\u000b\u0005\u0003\u001f\n)\u0006\u0006\u0003\u0002R\u0005]\u0003\u0003B\u0015\u0001\u0003'\u00022!LA+\t\u0015ycC1\u00011\u0011\u0019ie\u00031\u0001\u0002ZA!q*UA.!\u0011I3(a\u0015\u0016\t\u0005}\u0013Q\r\u000b\u0003\u0003C\u0002B!\u000b\u0001\u0002dA\u0019Q&!\u001a\u0005\u000b=:\"\u0019\u0001\u0019"
)
public interface ResizableParArrayCombiner extends LazyCombiner {
   static ResizableParArrayCombiner apply() {
      return ResizableParArrayCombiner$.MODULE$.apply();
   }

   static ResizableParArrayCombiner apply(final ArrayBuffer c) {
      return ResizableParArrayCombiner$.MODULE$.apply(c);
   }

   // $FF: synthetic method
   static void sizeHint$(final ResizableParArrayCombiner $this, final int sz) {
      $this.sizeHint(sz);
   }

   default void sizeHint(final int sz) {
      if (this.chain().length() == 1) {
         ((ExposedArrayBuffer)this.chain().apply(0)).sizeHint(sz);
      }
   }

   // $FF: synthetic method
   static ResizableParArrayCombiner newLazyCombiner$(final ResizableParArrayCombiner $this, final ArrayBuffer c) {
      return $this.newLazyCombiner(c);
   }

   default ResizableParArrayCombiner newLazyCombiner(final ArrayBuffer c) {
      return ResizableParArrayCombiner$.MODULE$.apply(c);
   }

   // $FF: synthetic method
   static ParArray allocateAndCopy$(final ResizableParArrayCombiner $this) {
      return $this.allocateAndCopy();
   }

   default ParArray allocateAndCopy() {
      if (this.chain().size() > 1) {
         Object[] array = new Object[this.size()];
         ArraySeq arrayseq = .MODULE$.make(array);
         this.combinerTaskSupport().executeAndWaitResult(new CopyChainToArray(array, 0, this.size()));
         return new ParArray(arrayseq);
      } else {
         return new ParArray(.MODULE$.make(((ExposedArrayBuffer)this.chain().apply(0)).internalArray()), this.size());
      }
   }

   // $FF: synthetic method
   static String toString$(final ResizableParArrayCombiner $this) {
      return $this.toString();
   }

   default String toString() {
      return (new StringBuilder(29)).append("ResizableParArrayCombiner(").append(this.size()).append("): ").toString();
   }

   static void $init$(final ResizableParArrayCombiner $this) {
   }

   public class CopyChainToArray implements Task {
      private final Object[] array;
      private final int offset;
      private final int howmany;
      private BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ResizableParArrayCombiner $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void merge(final Object that) {
         Task.merge$(this, that);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public void result() {
         BoxedUnit var10000 = this.result;
      }

      public void result_$eq(final BoxedUnit x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (this.howmany > 0) {
            int totalleft = this.howmany;
            Tuple2 var5 = this.findStart(this.offset);
            if (var5 != null) {
               int stbuff = var5._1$mcI$sp();
               int stind = var5._2$mcI$sp();
               Tuple2.mcII.sp var4 = new Tuple2.mcII.sp(stbuff, stind);
               int stbuffx = ((Tuple2)var4)._1$mcI$sp();
               int stindx = ((Tuple2)var4)._2$mcI$sp();
               int buffind = stbuffx;
               int ind = stindx;

               for(int arrayIndex = this.offset; totalleft > 0; ind = 0) {
                  ExposedArrayBuffer currbuff = (ExposedArrayBuffer)this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().chain().apply(buffind);
                  int chunksize = totalleft < currbuff.size() - ind ? totalleft : currbuff.size() - ind;
                  int until = ind + chunksize;
                  this.copyChunk(currbuff.internalArray(), ind, this.array, arrayIndex, until);
                  arrayIndex += chunksize;
                  int var10000 = ind + chunksize;
                  totalleft -= chunksize;
                  ++buffind;
               }

            } else {
               throw new MatchError(var5);
            }
         }
      }

      private void copyChunk(final Object[] buffarr, final int buffStart, final Object[] ra, final int arrayStart, final int until) {
         scala.Array..MODULE$.copy(buffarr, buffStart, ra, arrayStart, until - buffStart);
      }

      private Tuple2 findStart(final int pos) {
         int left = pos;

         int buffind;
         for(buffind = 0; left >= ((SeqOps)this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().chain().apply(buffind)).size(); ++buffind) {
            left -= ((SeqOps)this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().chain().apply(buffind)).size();
         }

         return new Tuple2.mcII.sp(buffind, left);
      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().new CopyChainToArray(this.array, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().new CopyChainToArray(this.array, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().size(), this.scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().combinerTaskSupport().parallelismLevel());
      }

      // $FF: synthetic method
      public ResizableParArrayCombiner scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer() {
         return this.$outer;
      }

      public CopyChainToArray(final Object[] array, final int offset, final int howmany) {
         this.array = array;
         this.offset = offset;
         this.howmany = howmany;
         if (ResizableParArrayCombiner.this == null) {
            throw null;
         } else {
            this.$outer = ResizableParArrayCombiner.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }
   }
}
