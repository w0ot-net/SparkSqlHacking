package com.fasterxml.jackson.module.scala.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=<a!\u0003\u0006\t\u0002)1bA\u0002\r\u000b\u0011\u0003Q\u0011\u0004C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0004#\u0003\t\u0007I\u0011B\u0012\t\r-\n\u0001\u0015!\u0003%\u0011\u0015a\u0013\u0001\"\u0001.\u0011\u0015Q\u0016\u0001\"\u0001\\\u0011\u0015\t\u0017\u0001\"\u0001c\u0011\u0015A\u0017\u0001\"\u0001j\u0003eQ\u0015M^1QCJ\fW.\u001a;fe&sGO]8ta\u0016\u001cGo\u001c:\u000b\u0005-a\u0011AC5oiJ|7\u000f]3di*\u0011QBD\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u001fA\ta!\\8ek2,'BA\t\u0013\u0003\u001dQ\u0017mY6t_:T!a\u0005\u000b\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\u000b\u0002\u0007\r|W\u000e\u0005\u0002\u0018\u00035\t!BA\rKCZ\f\u0007+\u0019:b[\u0016$XM]%oiJ|7\u000f]3di>\u00148CA\u0001\u001b!\tYR$D\u0001\u001d\u0015\u0005i\u0011B\u0001\u0010\u001d\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0017\u0003%\u0001\u0018M]1oC6,'/F\u0001%!\t)\u0013&D\u0001'\u0015\t\u0011sE\u0003\u0002))\u0005aA\u000f[8vO\"$xo\u001c:lg&\u0011!F\n\u0002\u0011\u0007\u0006\u001c\u0007.\u001b8h!\u0006\u0014\u0018M\\1nKJ\f!\u0002]1sC:\fW.\u001a:!\u0003E9W\r^\"u_J\u0004\u0016M]1n\u001d\u0006lWm\u001d\u000b\u0003]\t\u00032aL\u001c;\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024A\u00051AH]8pizJ\u0011!D\u0005\u0003mq\tq\u0001]1dW\u0006<W-\u0003\u00029s\tQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005Yb\u0002CA\u001e@\u001d\taT\b\u0005\u000229%\u0011a\bH\u0001\u0007!J,G-\u001a4\n\u0005\u0001\u000b%AB*ue&twM\u0003\u0002?9!)1)\u0002a\u0001\t\u0006!1\r^8sa\t)\u0015\u000bE\u0002G\u001b>k\u0011a\u0012\u0006\u0003\u0011&\u000bqA]3gY\u0016\u001cGO\u0003\u0002K\u0017\u0006!A.\u00198h\u0015\u0005a\u0015\u0001\u00026bm\u0006L!AT$\u0003\u0017\r{gn\u001d;sk\u000e$xN\u001d\t\u0003!Fc\u0001\u0001B\u0005S\u0005\u0006\u0005\t\u0011!B\u0001'\n\u0019q\fJ\u0019\u0012\u0005Q;\u0006CA\u000eV\u0013\t1FDA\u0004O_RD\u0017N\\4\u0011\u0005mA\u0016BA-\u001d\u0005\r\te._\u0001\u0014O\u0016$X*\u001a;i_\u0012\u0004\u0016M]1n\u001d\u0006lWm\u001d\u000b\u0003]qCQ!\u0018\u0004A\u0002y\u000b1!\u001c;e!\t1u,\u0003\u0002a\u000f\n1Q*\u001a;i_\u0012\fAbZ3u\r&,G\u000e\u001a(b[\u0016$\"AO2\t\u000b\u0011<\u0001\u0019A3\u0002\u000b\u0019LW\r\u001c3\u0011\u0005\u00193\u0017BA4H\u0005\u00151\u0015.\u001a7e\u0003A9W\r\u001e)be\u0006lW\r^3s\u001d\u0006lW\r\u0006\u0002;U\")1\u000e\u0003a\u0001Y\u0006I\u0001/\u0019:b[\u0016$XM\u001d\t\u0003\r6L!A\\$\u0003\u0013A\u000b'/Y7fi\u0016\u0014\b"
)
public final class JavaParameterIntrospector {
   public static String getParameterName(final Parameter parameter) {
      return JavaParameterIntrospector$.MODULE$.getParameterName(parameter);
   }

   public static String getFieldName(final Field field) {
      return JavaParameterIntrospector$.MODULE$.getFieldName(field);
   }

   public static IndexedSeq getMethodParamNames(final Method mtd) {
      return JavaParameterIntrospector$.MODULE$.getMethodParamNames(mtd);
   }

   public static IndexedSeq getCtorParamNames(final Constructor ctor) {
      return JavaParameterIntrospector$.MODULE$.getCtorParamNames(ctor);
   }
}
