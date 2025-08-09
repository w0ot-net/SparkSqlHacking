package scala.collection.parallel.immutable;

import scala.collection.Factory;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4qa\u0003\u0007\u0011\u0002\u0007\u0005Q\u0003C\u0003>\u0001\u0011\u0005a\bC\u0003C\u0001\u0011\u00053\tC\u0003E\u0001\u0011\u0005S\tC\u0003J\u0001\u0011\u0005#\nC\u0003T\u0001\u0011\u0005CkB\u0003\\\u0019!\u0005ALB\u0003\f\u0019!\u0005Q\fC\u0003b\u000f\u0011\u0005!\rC\u0003d\u000f\u0011\u0005A\rC\u0003m\u000f\u0011\rQN\u0001\u0004QCJ\u001cV\r\u001e\u0006\u0003\u001b9\t\u0011\"[7nkR\f'\r\\3\u000b\u0005=\u0001\u0012\u0001\u00039be\u0006dG.\u001a7\u000b\u0005E\u0011\u0012AC2pY2,7\r^5p]*\t1#A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005Y\u00193C\u0002\u0001\u001879\nD\u0007\u0005\u0002\u001935\t!#\u0003\u0002\u001b%\t1\u0011I\\=SK\u001a\u0004B\u0001H\u0010\"Y5\tQD\u0003\u0002\u001f!\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0011\u001e\u0005I9UM\\3sS\u000e\u0004\u0016M\u001d+f[Bd\u0017\r^3\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\r!\n\u0002\u0002)F\u0011a%\u000b\t\u00031\u001dJ!\u0001\u000b\n\u0003\u000f9{G\u000f[5oOB\u0011\u0001DK\u0005\u0003WI\u00111!\u00118z!\ti\u0003!D\u0001\r!\ry\u0003'I\u0007\u0002\u001d%\u00111B\u0004\t\u0004[I\n\u0013BA\u001a\r\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\r=*\u0014\u0005L\u001c9\u0013\t1dB\u0001\u0006QCJ\u001cV\r\u001e'jW\u0016\u00042!\f\u0001\"!\rI4(I\u0007\u0002u)\u0011Q\u0002E\u0005\u0003yi\u00121aU3u\u0003\u0019!\u0013N\\5uIQ\tq\b\u0005\u0002\u0019\u0001&\u0011\u0011I\u0005\u0002\u0005+:LG/A\u0003f[B$\u00180F\u00018\u0003%\u0019w.\u001c9b]&|g.F\u0001G!\rar\tL\u0005\u0003\u0011v\u00111cR3oKJL7\rU1s\u0007>l\u0007/\u00198j_:\fAb\u001d;sS:<\u0007K]3gSb,\u0012a\u0013\t\u0003\u0019Fk\u0011!\u0014\u0006\u0003\u001d>\u000bA\u0001\\1oO*\t\u0001+\u0001\u0003kCZ\f\u0017B\u0001*N\u0005\u0019\u0019FO]5oO\u0006)Ao\\*fiV\u0011Q\u000bW\u000b\u0002-B\u0019Q\u0006A,\u0011\u0005\tBF!B-\u0006\u0005\u0004Q&!A+\u0012\u0005\u0005J\u0013A\u0002)beN+G\u000f\u0005\u0002.\u000fM\u0011qA\u0018\t\u00049}c\u0013B\u00011\u001e\u00055\u0001\u0016M]*fi\u001a\u000b7\r^8ss\u00061A(\u001b8jiz\"\u0012\u0001X\u0001\f]\u0016<8i\\7cS:,'/\u0006\u0002fUV\ta\r\u0005\u00030O&\\\u0017B\u00015\u000f\u0005!\u0019u.\u001c2j]\u0016\u0014\bC\u0001\u0012k\t\u0015!\u0013B1\u0001&!\ri\u0003![\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u0004]R<X#A8\u0011\u000bq\u0001(O\u001e=\n\u0005El\"AD\"b]\u000e{WNY5oK\u001a\u0013x.\u001c\t\u0004[\u0001\u0019\bC\u0001\u0012u\t\u0015)(B1\u0001&\u0005\u0005\u0019\u0006C\u0001\u0012x\t\u0015!#B1\u0001&!\ri\u0003A\u001e"
)
public interface ParSet extends scala.collection.parallel.ParSet, ParIterable {
   static CanCombineFrom canBuildFrom() {
      return ParSet$.MODULE$.canBuildFrom();
   }

   static Factory toFactory() {
      return ParSet$.MODULE$.toFactory();
   }

   // $FF: synthetic method
   static ParSet empty$(final ParSet $this) {
      return $this.empty();
   }

   default ParSet empty() {
      return (ParSet)ParHashSet$.MODULE$.apply(.MODULE$);
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParSet $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParSet$.MODULE$;
   }

   // $FF: synthetic method
   static String stringPrefix$(final ParSet $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "ParSet";
   }

   // $FF: synthetic method
   static ParSet toSet$(final ParSet $this) {
      return $this.toSet();
   }

   default ParSet toSet() {
      return this;
   }

   static void $init$(final ParSet $this) {
   }
}
