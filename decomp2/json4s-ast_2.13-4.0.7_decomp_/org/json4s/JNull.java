package org.json4s;

import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Q!\u0004\b\t\u0002N1Q!\u0006\b\t\u0002ZAQ\u0001L\u0001\u0005\u00025*AAL\u0001\u0001_!)!'\u0001C\u0001g!9A'AA\u0001\n\u0003*\u0004b\u0002 \u0002\u0003\u0003%\ta\u0010\u0005\b\u0007\u0006\t\t\u0011\"\u0001E\u0011\u001dQ\u0015!!A\u0005B-CqAU\u0001\u0002\u0002\u0013\u00051\u000bC\u0004Y\u0003\u0005\u0005I\u0011I-\t\u000fi\u000b\u0011\u0011!C!7\"9A,AA\u0001\n\u0013i\u0016!\u0002&Ok2d'BA\b\u0011\u0003\u0019Q7o\u001c85g*\t\u0011#A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0015\u00035\taBA\u0003K\u001dVdGn\u0005\u0003\u0002/i\u0001\u0003C\u0001\u000b\u0019\u0013\tIbB\u0001\u0004K-\u0006dW/\u001a\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\b!J|G-^2u!\t\t\u0013F\u0004\u0002#O9\u00111EJ\u0007\u0002I)\u0011QEE\u0001\u0007yI|w\u000e\u001e \n\u0003uI!\u0001\u000b\u000f\u0002\u000fA\f7m[1hK&\u0011!f\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Qq\ta\u0001P5oSRtD#A\n\u0003\rY\u000bG.^3t!\tY\u0002'\u0003\u000229\t!a*\u001e7m\u0003\u00191\u0018\r\\;fgV\tq&A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002mA\u0011q\u0007P\u0007\u0002q)\u0011\u0011HO\u0001\u0005Y\u0006twMC\u0001<\u0003\u0011Q\u0017M^1\n\u0005uB$AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001A!\tY\u0012)\u0003\u0002C9\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Q\t\u0013\t\u00037\u0019K!a\u0012\u000f\u0003\u0007\u0005s\u0017\u0010C\u0004J\u000f\u0005\u0005\t\u0019\u0001!\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005a\u0005cA'Q\u000b6\taJ\u0003\u0002P9\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Es%\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"\u0001V,\u0011\u0005m)\u0016B\u0001,\u001d\u0005\u001d\u0011un\u001c7fC:Dq!S\u0005\u0002\u0002\u0003\u0007Q)\u0001\u0005iCND7i\u001c3f)\u0005\u0001\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Y\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012A\u0018\t\u0003o}K!\u0001\u0019\u001d\u0003\r=\u0013'.Z2u\u0001"
)
public final class JNull {
   public static String toString() {
      return JNull$.MODULE$.toString();
   }

   public static int hashCode() {
      return JNull$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return JNull$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return JNull$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return JNull$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return JNull$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return JNull$.MODULE$.productPrefix();
   }

   public static Null values() {
      return JNull$.MODULE$.values();
   }

   public static Option toSome() {
      return JNull$.MODULE$.toSome();
   }

   public static Option toOption() {
      return JNull$.MODULE$.toOption();
   }

   public static JValue $plus$plus(final JValue other) {
      return JNull$.MODULE$.$plus$plus(other);
   }

   public static JValue apply(final int i) {
      return JNull$.MODULE$.apply(i);
   }

   public static List children() {
      return JNull$.MODULE$.children();
   }

   public static Iterator productElementNames() {
      return JNull$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return JNull$.MODULE$.productElementName(n);
   }

   public static Diff diff(final JValue other) {
      return JNull$.MODULE$.diff(other);
   }
}
