package org.apache.spark.metrics;

import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q<QAD\b\t\u0002b1QAG\b\t\u0002nAQ\u0001N\u0001\u0005\u0002UBqAN\u0001C\u0002\u0013\u0005s\u0007\u0003\u0004I\u0003\u0001\u0006I\u0001\u000f\u0005\u0007\u0013\u0006!\t%\u0005&\t\u000fe\u000b\u0011\u0011!C!5\"91,AA\u0001\n\u0003a\u0006b\u00021\u0002\u0003\u0003%\t!\u0019\u0005\bO\u0006\t\t\u0011\"\u0011i\u0011\u001di\u0017!!A\u0005\u00029Dqa]\u0001\u0002\u0002\u0013\u0005C\u000fC\u0004v\u0003\u0005\u0005I\u0011\t<\t\u000f]\f\u0011\u0011!C\u0005q\u0006\u0011\u0002K]8dKN\u001cHK]3f\u001b\u0016$(/[2t\u0015\t\u0001\u0012#A\u0004nKR\u0014\u0018nY:\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u001a\u00035\tqB\u0001\nQe>\u001cWm]:Ue\u0016,W*\u001a;sS\u000e\u001c8#B\u0001\u001dE\u0015B\u0003CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g\r\u0005\u0002\u001aG%\u0011Ae\u0004\u0002\u0013\u000bb,7-\u001e;pe6+GO]5d)f\u0004X\r\u0005\u0002\u001eM%\u0011qE\b\u0002\b!J|G-^2u!\tI\u0013G\u0004\u0002+_9\u00111FL\u0007\u0002Y)\u0011QfF\u0001\u0007yI|w\u000e\u001e \n\u0003}I!\u0001\r\u0010\u0002\u000fA\f7m[1hK&\u0011!g\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003ay\ta\u0001P5oSRtD#\u0001\r\u0002\u000b9\fW.Z:\u0016\u0003a\u00022!\u000f A\u001b\u0005Q$BA\u001e=\u0003%IW.\\;uC\ndWM\u0003\u0002>=\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005}R$aA*fcB\u0011\u0011IR\u0007\u0002\u0005*\u00111\tR\u0001\u0005Y\u0006twMC\u0001F\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0013%AB*ue&tw-\u0001\u0004oC6,7\u000fI\u0001\u0010O\u0016$X*\u001a;sS\u000e4\u0016\r\\;fgR\u00111*\u0015\t\u0004;1s\u0015BA'\u001f\u0005\u0015\t%O]1z!\tir*\u0003\u0002Q=\t!Aj\u001c8h\u0011\u0015\u0011V\u00011\u0001T\u00035iW-\\8ss6\u000bg.Y4feB\u0011AkV\u0007\u0002+*\u0011a+E\u0001\u0007[\u0016lwN]=\n\u0005a+&!D'f[>\u0014\u00180T1oC\u001e,'/A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002\u0001\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tQ\f\u0005\u0002\u001e=&\u0011qL\b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003E\u0016\u0004\"!H2\n\u0005\u0011t\"aA!os\"9a\rCA\u0001\u0002\u0004i\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001j!\rQ7NY\u0007\u0002y%\u0011A\u000e\u0010\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002peB\u0011Q\u0004]\u0005\u0003cz\u0011qAQ8pY\u0016\fg\u000eC\u0004g\u0015\u0005\u0005\t\u0019\u00012\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!X\u0001\ti>\u001cFO]5oOR\t\u0001)\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001z!\t\t%0\u0003\u0002|\u0005\n1qJ\u00196fGR\u0004"
)
public final class ProcessTreeMetrics {
   public static String toString() {
      return ProcessTreeMetrics$.MODULE$.toString();
   }

   public static int hashCode() {
      return ProcessTreeMetrics$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return ProcessTreeMetrics$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return ProcessTreeMetrics$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return ProcessTreeMetrics$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return ProcessTreeMetrics$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return ProcessTreeMetrics$.MODULE$.productPrefix();
   }

   public static Seq names() {
      return ProcessTreeMetrics$.MODULE$.names();
   }

   public static Iterator productElementNames() {
      return ProcessTreeMetrics$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return ProcessTreeMetrics$.MODULE$.productElementName(n);
   }
}
