package org.apache.spark.util.collection;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-:Q!\u0002\u0004\t\nE1Qa\u0005\u0004\t\nQAQaG\u0001\u0005\u0002qAq!H\u0001C\u0002\u0013\u0015a\u0004\u0003\u0004+\u0003\u0001\u0006iaH\u0001\r\u000bJ\u0014xN]'fgN\fw-\u001a\u0006\u0003\u000f!\t!bY8mY\u0016\u001cG/[8o\u0015\tI!\"\u0001\u0003vi&d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001A\u0011!#A\u0007\u0002\r\taQI\u001d:pe6+7o]1hKN\u0011\u0011!\u0006\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\t\u0012aA7tOV\tq\u0004\u0005\u0002!O9\u0011\u0011%\n\t\u0003E]i\u0011a\t\u0006\u0003IA\ta\u0001\u0010:p_Rt\u0014B\u0001\u0014\u0018\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001&\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019:\u0012\u0001B7tO\u0002\u0002"
)
public final class ErrorMessage {
   public static String msg() {
      return ErrorMessage$.MODULE$.msg();
   }
}
