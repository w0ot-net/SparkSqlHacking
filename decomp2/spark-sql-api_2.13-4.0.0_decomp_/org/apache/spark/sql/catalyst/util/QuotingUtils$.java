package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.util.regex.Pattern;
import org.apache.spark.sql.connector.catalog.Identifier;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;

public final class QuotingUtils$ {
   public static final QuotingUtils$ MODULE$ = new QuotingUtils$();
   private static final Pattern validIdentPattern = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*");

   private String quoteByDefault(final String elem) {
      return "\"" + elem + "\"";
   }

   public String toSQLConf(final String conf) {
      return this.quoteByDefault(conf);
   }

   public String toSQLSchema(final String schema) {
      return this.quoteByDefault(schema);
   }

   public String quoteIdentifier(final String name) {
      return "`" + name.replace("`", "``") + "`";
   }

   public String quoteNameParts(final Seq name) {
      return ((IterableOnceOps)name.map((part) -> MODULE$.quoteIdentifier(part))).mkString(".");
   }

   private Pattern validIdentPattern() {
      return validIdentPattern;
   }

   public boolean needQuote(final String part) {
      return !this.validIdentPattern().matcher(part).matches();
   }

   public String quoteIfNeeded(final String part) {
      return this.needQuote(part) ? this.quoteIdentifier(part) : part;
   }

   public String quoted(final String[] namespace) {
      return .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])namespace), (part) -> MODULE$.quoteIfNeeded(part), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(".");
   }

   public String quoted(final Identifier ident) {
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps((Object[])ident.namespace()))) {
         String var10000 = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])ident.namespace()), (part) -> MODULE$.quoteIfNeeded(part), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(".");
         return var10000 + "." + this.quoteIfNeeded(ident.name());
      } else {
         return this.quoteIfNeeded(ident.name());
      }
   }

   public String fullyQuoted(final Identifier ident) {
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps((Object[])ident.namespace()))) {
         String var10000 = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])ident.namespace()), (name) -> MODULE$.quoteIdentifier(name), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(".");
         return var10000 + "." + this.quoteIdentifier(ident.name());
      } else {
         return this.quoteIdentifier(ident.name());
      }
   }

   public String escapeSingleQuotedString(final String str) {
      StringBuilder builder = new StringBuilder();
      scala.collection.StringOps..MODULE$.foreach$extension(.MODULE$.augmentString(str), (x0$1) -> $anonfun$escapeSingleQuotedString$1(builder, BoxesRunTime.unboxToChar(x0$1)));
      return builder.toString();
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$escapeSingleQuotedString$1(final StringBuilder builder$1, final char x0$1) {
      switch (x0$1) {
         case '\'' -> {
            return builder$1.$plus$plus$eq("\\'");
         }
         default -> {
            return (StringBuilder)builder$1.$plus$eq(BoxesRunTime.boxToCharacter(x0$1));
         }
      }
   }

   private QuotingUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
