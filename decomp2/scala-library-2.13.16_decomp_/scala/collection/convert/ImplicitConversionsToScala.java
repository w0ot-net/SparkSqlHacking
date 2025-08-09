package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r:Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQAF\u0001\u0005\u0002]\t!$S7qY&\u001c\u0017\u000e^\"p]Z,'o]5p]N$vnU2bY\u0006T!!\u0002\u0004\u0002\u000f\r|gN^3si*\u0011q\u0001C\u0001\u000bG>dG.Z2uS>t'\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011A\"A\u0007\u0002\t\tQ\u0012*\u001c9mS\u000eLGoQ8om\u0016\u00148/[8ogR{7kY1mCN\u0019\u0011aD\n\u0011\u0005A\tR\"\u0001\u0005\n\u0005IA!AB!osJ+g\r\u0005\u0002\r)%\u0011Q\u0003\u0002\u0002\u0011)>\u001c6-\u00197b\u00136\u0004H.[2jiN\fa\u0001P5oSRtD#A\u0006)\r\u0005IB$H\u0010!!\t\u0001\"$\u0003\u0002\u001c\u0011\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\na$\u0001\u0017Vg\u0016\u0004\u0003m]2bY\u0006t#\u000eZ6/\u0007>dG.Z2uS>t7i\u001c8wKJ$XM]:aA%t7\u000f^3bI\u0006)1/\u001b8dK\u0006\n\u0011%\u0001\u00043]E\u001ad\u0006\r\u0015\u0007\u0001eaRd\b\u0011"
)
public final class ImplicitConversionsToScala {
   public static Map properties$u0020AsScalaMap(final Properties p) {
      return ImplicitConversionsToScala$.MODULE$.properties$u0020AsScalaMap(p);
   }

   public static Map dictionary$u0020AsScalaMap(final Dictionary p) {
      return ImplicitConversionsToScala$.MODULE$.dictionary$u0020AsScalaMap(p);
   }

   public static scala.collection.concurrent.Map map$u0020AsScalaConcurrentMap(final ConcurrentMap m) {
      return ImplicitConversionsToScala$.MODULE$.map$u0020AsScalaConcurrentMap(m);
   }

   public static Map map$u0020AsScala(final java.util.Map m) {
      return ImplicitConversionsToScala$.MODULE$.map$u0020AsScala(m);
   }

   public static Set set$u0020asScala(final java.util.Set s) {
      return ImplicitConversionsToScala$.MODULE$.set$u0020asScala(s);
   }

   public static Buffer list$u0020asScalaBuffer(final List l) {
      return ImplicitConversionsToScala$.MODULE$.list$u0020asScalaBuffer(l);
   }

   public static Iterable collection$u0020AsScalaIterable(final Collection i) {
      return ImplicitConversionsToScala$.MODULE$.collection$u0020AsScalaIterable(i);
   }

   public static Iterable iterable$u0020AsScalaIterable(final java.lang.Iterable i) {
      return ImplicitConversionsToScala$.MODULE$.iterable$u0020AsScalaIterable(i);
   }

   public static Iterator enumeration$u0020AsScalaIterator(final Enumeration i) {
      return ImplicitConversionsToScala$.MODULE$.enumeration$u0020AsScalaIterator(i);
   }

   public static Iterator iterator$u0020asScala(final java.util.Iterator it) {
      return ImplicitConversionsToScala$.MODULE$.iterator$u0020asScala(it);
   }
}
