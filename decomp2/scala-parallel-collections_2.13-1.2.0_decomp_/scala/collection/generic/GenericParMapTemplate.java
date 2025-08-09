package scala.collection.generic;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003/\u0001\u0011\u0005q\u0006\u0003\u00044\u0001\u0001&\t\u0006\u000e\u0005\u0006#\u00021\tA\u0015\u0005\u0006/\u0002!\t\u0001\u0017\u0002\u0016\u000f\u0016tWM]5d!\u0006\u0014X*\u00199UK6\u0004H.\u0019;f\u0015\t9\u0001\"A\u0004hK:,'/[2\u000b\u0005%Q\u0011AC2pY2,7\r^5p]*\t1\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t9ab\u0005R\n\u0004\u0001=\u0019\u0002C\u0001\t\u0012\u001b\u0005Q\u0011B\u0001\n\u000b\u0005\u0019\te.\u001f*fMB!A#F\f)\u001b\u00051\u0011B\u0001\f\u0007\u0005I9UM\\3sS\u000e\u0004\u0016M\u001d+f[Bd\u0017\r^3\u0011\tAA\"$J\u0005\u00033)\u0011a\u0001V;qY\u0016\u0014\u0004CA\u000e\u001d\u0019\u0001!Q!\b\u0001C\u0002y\u0011\u0011aS\t\u0003?\t\u0002\"\u0001\u0005\u0011\n\u0005\u0005R!a\u0002(pi\"Lgn\u001a\t\u0003!\rJ!\u0001\n\u0006\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001cM\u00111q\u0005\u0001CC\u0002y\u0011\u0011A\u0016\t\u0003S1j\u0011A\u000b\u0006\u0003W!\t\u0001\u0002]1sC2dW\r\\\u0005\u0003[)\u00121\u0002U1s\u0013R,'/\u00192mK\u00061A%\u001b8ji\u0012\"\u0012\u0001\r\t\u0003!EJ!A\r\u0006\u0003\tUs\u0017\u000e^\u0001\f]\u0016<8i\\7cS:,'/F\u00016!\u0011Ic\u0007O\"\n\u0005]R#\u0001C\"p[\nLg.\u001a:\u0011\tAA\"$\u000f\u0016\u0003KiZ\u0013a\u000f\t\u0003y\u0005k\u0011!\u0010\u0006\u0003}}\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0001S\u0011AC1o]>$\u0018\r^5p]&\u0011!)\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\u0003B\u000eE5e\"a!\u0012\u0001\u0005\u0006\u00041%AA\"D+\r9EjT\t\u0003?!\u0003B!K%L\u001d&\u0011!J\u000b\u0002\u0007!\u0006\u0014X*\u00199\u0011\u0005maE!B'E\u0005\u0004q\"!\u0001-\u0011\u0005myE!\u0002)E\u0005\u0004q\"!A-\u0002\u00195\f\u0007oQ8na\u0006t\u0017n\u001c8\u0016\u0003M\u00032\u0001\u0006+W\u0013\t)fA\u0001\fHK:,'/[2QCJl\u0015\r]\"p[B\fg.[8o!\tYB)\u0001\nhK:,'/[2NCB\u001cu.\u001c2j]\u0016\u0014XcA-^AV\t!\f\u0005\u0003*mm\u0013\u0007\u0003\u0002\t\u00199~\u0003\"aG/\u0005\u000by#!\u0019\u0001\u0010\u0003\u0003A\u0003\"a\u00071\u0005\u000b\u0005$!\u0019\u0001\u0010\u0003\u0003E\u0003Ba\u0007#]?\u0002"
)
public interface GenericParMapTemplate extends GenericParTemplate {
   // $FF: synthetic method
   static Combiner newCombiner$(final GenericParMapTemplate $this) {
      return $this.newCombiner();
   }

   default Combiner newCombiner() {
      Combiner cb = this.mapCompanion().newCombiner();
      return cb;
   }

   GenericParMapCompanion mapCompanion();

   // $FF: synthetic method
   static Combiner genericMapCombiner$(final GenericParMapTemplate $this) {
      return $this.genericMapCombiner();
   }

   default Combiner genericMapCombiner() {
      Combiner cb = this.mapCompanion().newCombiner();
      return cb;
   }

   static void $init$(final GenericParMapTemplate $this) {
   }
}
