package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015:Qa\u0001\u0003\t\u0002E1Qa\u0005\u0003\t\u0002QAQaI\u0001\u0005\u0002\u0011\n\u0011\"S7qY&\u001c\u0017\u000e^:\u000b\u0005\u00151\u0011\u0001B;uS2T!a\u0002\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005%Q\u0011AB7pIVdWM\u0003\u0002\f\u0019\u00059!.Y2lg>t'BA\u0007\u000f\u0003%1\u0017m\u001d;feblGNC\u0001\u0010\u0003\r\u0019w.\\\u0002\u0001!\t\u0011\u0012!D\u0001\u0005\u0005%IU\u000e\u001d7jG&$8oE\u0003\u0002+ii\u0002\u0005\u0005\u0002\u001715\tqCC\u0001\b\u0013\tIrC\u0001\u0004B]f\u0014VM\u001a\t\u0003%mI!\u0001\b\u0003\u0003\u000f\rc\u0017m]:fgB\u0011!CH\u0005\u0003?\u0011\u0011qa\u00149uS>t7\u000f\u0005\u0002\u0013C%\u0011!\u0005\u0002\u0002\b'R\u0014\u0018N\\4t\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0003"
)
public final class Implicits {
   public static String unMkStringW(final StringW x) {
      return Implicits$.MODULE$.unMkStringW(x);
   }

   public static StringW mkStringW(final Function0 x) {
      return Implicits$.MODULE$.mkStringW(x);
   }

   public static Option unMkOptionW(final OptionW x) {
      return Implicits$.MODULE$.unMkOptionW(x);
   }

   public static OptionW mkOptionW(final Option x) {
      return Implicits$.MODULE$.mkOptionW(x);
   }

   public static Class unMkClassW(final ClassW x) {
      return Implicits$.MODULE$.unMkClassW(x);
   }

   public static ClassW mkClassW(final Function0 x) {
      return Implicits$.MODULE$.mkClassW(x);
   }
}
