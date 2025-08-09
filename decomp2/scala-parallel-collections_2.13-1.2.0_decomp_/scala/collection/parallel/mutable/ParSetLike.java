package scala.collection.parallel.mutable;

import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Shrinkable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U4q!\u0003\u0006\u0011\u0002\u0007\u00051\u0003C\u0003Z\u0001\u0011\u0005!\fC\u0003_\u0001\u0011\u0005s\fC\u0003d\u0001\u0019\u0005C\rC\u0003f\u0001\u0019\u0005a\rC\u0003k\u0001\u0019\u00051\u000eC\u0003n\u0001\u0011\u0005a\u000eC\u0003q\u0001\u0011\u0005\u0011\u000fC\u0003t\u0001\u0011\u0005CO\u0001\u0006QCJ\u001cV\r\u001e'jW\u0016T!a\u0003\u0007\u0002\u000f5,H/\u00192mK*\u0011QBD\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011q\u0002E\u0001\u000bG>dG.Z2uS>t'\"A\t\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U)AcH\u00156{M9\u0001!F\rO!N3\u0006C\u0001\f\u0018\u001b\u0005\u0001\u0012B\u0001\r\u0011\u0005\u0019\te.\u001f*fMB1!dG\u000f)iqj\u0011\u0001D\u0005\u000391\u0011q\u0002U1s\u0013R,'/\u00192mK2K7.\u001a\t\u0003=}a\u0001\u0001B\u0003!\u0001\t\u0007\u0011EA\u0001U#\t\u0011S\u0005\u0005\u0002\u0017G%\u0011A\u0005\u0005\u0002\b\u001d>$\b.\u001b8h!\t1b%\u0003\u0002(!\t\u0019\u0011I\\=\u0011\u0005yICA\u0002\u0016\u0001\t\u000b\u00071F\u0001\u0002D\u0007V\u0011AFM\t\u0003E5\u00022AL\u00182\u001b\u0005Q\u0011B\u0001\u0019\u000b\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\u0005y\u0011D!B\u001a*\u0005\u0004\t#!\u0001-\u0011\u0005y)DA\u0002\u001c\u0001\t\u000b\u0007qG\u0001\u0003SKB\u0014\u0018C\u0001\u00129%\rI4h\u0013\u0004\u0005u\u0001\u0001\u0001H\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0004/\u0001uAC\u0007\u0010\t\u0003=u\"aA\u0010\u0001\u0005\u0006\u0004y$AC*fcV,g\u000e^5bYF\u0011!\u0005\u0011\n\u0004\u0003\n;e\u0001\u0002\u001e\u0001\u0001\u0001\u00032aQ#\u001e\u001b\u0005!%BA\u0006\u000f\u0013\t1EIA\u0002TKR\u0004Ra\u0011%\u001e\u0015rJ!!\u0013#\u0003\rM+Go\u00149t!\t\u0019U\tE\u0002/\u0019vI!!\u0014\u0006\u0003\rA\u000b'oU3u!\u0019Qr*\b\u00155y%\u0011\u0011\u0002\u0004\t\u0004\u0007Fk\u0012B\u0001*E\u0005!9%o\\<bE2,\u0007cA\"U;%\u0011Q\u000b\u0012\u0002\u000b'\"\u0014\u0018N\\6bE2,\u0007cA\"Xi%\u0011\u0001\f\u0012\u0002\n\u00072|g.Z1cY\u0016\fa\u0001J5oSR$C#A.\u0011\u0005Ya\u0016BA/\u0011\u0005\u0011)f.\u001b;\u0002\u0013-twn\u001e8TSj,W#\u00011\u0011\u0005Y\t\u0017B\u00012\u0011\u0005\rIe\u000e^\u0001\u0006K6\u0004H/_\u000b\u0002i\u00051\u0011\r\u001a3P]\u0016$\"a\u001a5\u000e\u0003\u0001AQ!\u001b\u0003A\u0002u\tA!\u001a7f[\u0006Y1/\u001e2ue\u0006\u001cGo\u00148f)\t9G\u000eC\u0003j\u000b\u0001\u0007Q$A\u0003%a2,8\u000f\u0006\u00025_\")\u0011N\u0002a\u0001;\u00051A%\\5okN$\"\u0001\u000e:\t\u000b%<\u0001\u0019A\u000f\u0002\u000b\rdwN\\3\u0015\u0003Q\u0002"
)
public interface ParSetLike extends scala.collection.parallel.ParSetLike, Growable, Shrinkable, Cloneable {
   // $FF: synthetic method
   static int knownSize$(final ParSetLike $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   ParSet empty();

   ParSetLike addOne(final Object elem);

   ParSetLike subtractOne(final Object elem);

   // $FF: synthetic method
   static ParSet $plus$(final ParSetLike $this, final Object elem) {
      return $this.$plus(elem);
   }

   default ParSet $plus(final Object elem) {
      return (ParSet)this.clone().$plus$eq(elem);
   }

   // $FF: synthetic method
   static ParSet $minus$(final ParSetLike $this, final Object elem) {
      return $this.$minus(elem);
   }

   default ParSet $minus(final Object elem) {
      return (ParSet)this.clone().$minus$eq(elem);
   }

   // $FF: synthetic method
   static ParSet clone$(final ParSetLike $this) {
      return $this.clone();
   }

   default ParSet clone() {
      return (ParSet)this.empty().$plus$plus$eq(this);
   }

   static void $init$(final ParSetLike $this) {
   }
}
