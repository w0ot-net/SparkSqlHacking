package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0010\u0001\u0011\u0005\u0001\u0003C\u0003\u0015\u0001\u0011\rQ\u0003C\u0003A\u0001\u0011\r\u0011IA\u000bCk&dGM\u0012:p[2{w\u000f\u0015:j_JLG/\u001f\u001a\u000b\u0005\u00199\u0011AC2pY2,7\r^5p]*\t\u0001\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Y\u0001C\u0001\u0007\u000e\u001b\u00059\u0011B\u0001\b\b\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0019II!aE\u0004\u0003\tUs\u0017\u000e^\u0001\u0015EVLG\u000e\u001a$s_6LE/\u001a:bE2,w\n]:\u0016\tYi\"(P\u000b\u0002/A)\u0001$G\u000e=\u007f5\tQ!\u0003\u0002\u001b\u000b\tI!)^5mI\u001a\u0013x.\u001c\t\u00049uID\u0002\u0001\u0003\u0006=\t\u0011\ra\b\u0002\u0003\u0007\u000e+\"\u0001I\u0016\u0012\u0005\u0005\"\u0003C\u0001\u0007#\u0013\t\u0019sAA\u0004O_RD\u0017N\\4\u0013\u0007\u0015:\u0013G\u0002\u0003'\u0001\u0001!#\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004c\u0001\r)U%\u0011\u0011&\u0002\u0002\t\u0013R,'/\u00192mKB\u0011Ad\u000b\u0003\u0006Yu\u0011\r!\f\u0002\u00021F\u0011\u0011E\f\t\u0003\u0019=J!\u0001M\u0004\u0003\u0007\u0005s\u0017\u0010\r\u00023oA)\u0001d\r\u00166m%\u0011A'\u0002\u0002\f\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0002\u001d;A\u0011Ad\u000e\u0003\nqu\t\t\u0011!A\u0003\u00025\u0012Aa\u0018\u00132iA\u0011AD\u000f\u0003\u0006w\t\u0011\r!\f\u0002\u0003\u0003B\u0002\"\u0001H\u001f\u0005\u000by\u0012!\u0019A\u0017\u0003\u0003\u0005\u00032\u0001H\u000f=\u0003E\u0011W/\u001b7e\rJ|W.\u0013;fe\u0006$xN]\u000b\u0003\u00052+\u0012a\u0011\t\u00061e!5*\u0014\u0019\u0003\u000b&\u00032\u0001\u0007$I\u0013\t9UA\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\ta\u0012\nB\u0005K\u0007\u0005\u0005\t\u0011!B\u0001[\t!q\fJ\u00198!\taB\nB\u0003?\u0007\t\u0007Q\u0006E\u0002\u0019\r.\u0003"
)
public interface BuildFromLowPriority2 {
   // $FF: synthetic method
   static BuildFrom buildFromIterableOps$(final BuildFromLowPriority2 $this) {
      return $this.buildFromIterableOps();
   }

   default BuildFrom buildFromIterableOps() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final Iterable from) {
            return from.iterableFactory().newBuilder();
         }

         public Iterable fromSpecific(final Iterable from, final IterableOnce it) {
            return (Iterable)from.iterableFactory().from(it);
         }
      };
   }

   // $FF: synthetic method
   static BuildFrom buildFromIterator$(final BuildFromLowPriority2 $this) {
      return $this.buildFromIterator();
   }

   default BuildFrom buildFromIterator() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final Iterator from) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return new ImmutableBuilder() {
               public <undefinedtype> addOne(final Object elem) {
                  Iterator var10001 = (Iterator)this.elems();
                  Function0 $plus$plus_xs = () -> Iterator$.MODULE$.single(elem);
                  if (var10001 == null) {
                     throw null;
                  } else {
                     var10001 = var10001.concat($plus$plus_xs);
                     $plus$plus_xs = null;
                     this.elems_$eq(var10001);
                     return this;
                  }
               }

               public {
                  Iterator$ var10001 = Iterator$.MODULE$;
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return var0.lambdaDeserialize<invokedynamic>(var0);
               }
            };
         }

         public Iterator fromSpecific(final Iterator from, final IterableOnce it) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return it.iterator();
         }
      };
   }

   static void $init$(final BuildFromLowPriority2 $this) {
   }
}
