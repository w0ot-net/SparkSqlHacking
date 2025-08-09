package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import org.apache.commons.text.StringSubstitutor;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.SparkEnvUtils$;
import scala.Array;
import scala.MatchError;
import scala.Option;
import scala.Predef.ArrowAssoc.;
import scala.collection.IterableOnceOps;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001\u0002\u000b\u0016\u0001qA\u0001b\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\u0006q\u0001!\t!\u000f\u0005\t{\u0001\u0011\r\u0011\"\u0001\u0016}!1Q\n\u0001Q\u0001\n}BQA\u0014\u0001\u0005\u0002=CQ\u0001\u0017\u0001\u0005\u0002eCQ\u0001\u0018\u0001\u0005\u0002uCQa\u0018\u0001\u0005\u0002\u0001DQA\u0019\u0001\u0005\u0002\r<Qa\\\u000b\t\nA4Q\u0001F\u000b\t\nEDQ\u0001O\u0006\u0005\u0002IDqa]\u0006C\u0002\u0013%A\u000f\u0003\u0004~\u0017\u0001\u0006I!\u001e\u0005\b}.\u0011\r\u0011\"\u0003\u0000\u0011!\t\tb\u0003Q\u0001\n\u0005\u0005\u0001\"CA\n\u0017\t\u0007I\u0011BA\u000b\u0011!\t\u0019d\u0003Q\u0001\n\u0005]\u0001bBA\u001b\u0017\u0011%\u0011q\u0007\u0002\u0017\u000bJ\u0014xN]\"mCN\u001cXm\u001d&t_:\u0014V-\u00193fe*\u0011acF\u0001\u0006gB\f'o\u001b\u0006\u00031e\ta!\u00199bG\",'\"\u0001\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001i\u0002C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g-\u0001\u0007kg>tg)\u001b7f+Jc5\u000fE\u0002&[Ar!AJ\u0016\u000f\u0005\u001dRS\"\u0001\u0015\u000b\u0005%Z\u0012A\u0002\u001fs_>$h(C\u0001!\u0013\tas$A\u0004qC\u000e\\\u0017mZ3\n\u00059z#aA*fc*\u0011Af\b\t\u0003cYj\u0011A\r\u0006\u0003gQ\n1A\\3u\u0015\u0005)\u0014\u0001\u00026bm\u0006L!a\u000e\u001a\u0003\u0007U\u0013F*\u0001\u0004=S:LGO\u0010\u000b\u0003uq\u0002\"a\u000f\u0001\u000e\u0003UAQa\t\u0002A\u0002\u0011\nA\"\u001a:s_JLeNZ8NCB,\u0012a\u0010\t\u0005\u0001\u0012;%J\u0004\u0002B\u0005B\u0011qeH\u0005\u0003\u0007~\ta\u0001\u0015:fI\u00164\u0017BA#G\u0005\ri\u0015\r\u001d\u0006\u0003\u0007~\u0001\"\u0001\u0011%\n\u0005%3%AB*ue&tw\r\u0005\u0002<\u0017&\u0011A*\u0006\u0002\n\u000bJ\u0014xN]%oM>\fQ\"\u001a:s_JLeNZ8NCB\u0004\u0013aD4fi\u0016\u0013(o\u001c:NKN\u001c\u0018mZ3\u0015\u0007\u001d\u0003&\u000bC\u0003R\u000b\u0001\u0007q)\u0001\u0006feJ|'o\u00117bgNDQaU\u0003A\u0002Q\u000b\u0011#\\3tg\u0006<W\rU1sC6,G/\u001a:t!\u0011\u0001EiR+\u0011\u0005y1\u0016BA, \u0005\r\te._\u0001\u0015O\u0016$X*Z:tC\u001e,\u0007+\u0019:b[\u0016$XM]:\u0015\u0005i[\u0006cA\u0013.\u000f\")\u0011K\u0002a\u0001\u000f\u0006\u0011r-\u001a;NKN\u001c\u0018mZ3UK6\u0004H.\u0019;f)\t9e\fC\u0003R\u000f\u0001\u0007q)A\u0006hKR\u001c\u0016\u000f\\*uCR,GCA$b\u0011\u0015\t\u0006\u00021\u0001H\u0003EI7OV1mS\u0012,%O]8s\u00072\f7o\u001d\u000b\u0003I\u001e\u0004\"AH3\n\u0005\u0019|\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006#&\u0001\ra\u0012\u0015\u0003\u0001%\u0004\"A[7\u000e\u0003-T!\u0001\\\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002oW\naA)\u001a<fY>\u0004XM]!qS\u00061RI\u001d:pe\u000ec\u0017m]:fg*\u001bxN\u001c*fC\u0012,'\u000f\u0005\u0002<\u0017M\u00111\"\b\u000b\u0002a\u0006qA+R'Q\u0019\u0006#Vi\u0018*F\u000f\u0016CV#A;\u0011\u0005Y\\X\"A<\u000b\u0005aL\u0018\u0001C7bi\u000eD\u0017N\\4\u000b\u0005i|\u0012\u0001B;uS2L!\u0001`<\u0003\u000bI+w-\u001a=\u0002\u001fQ+U\n\u0015'B)\u0016{&+R$F1\u0002\nQ#T(S\u000b~\u0003\u0016IU!N'~\u000bE\nT(X\u0019&\u001bF+\u0006\u0002\u0002\u0002A)a$a\u0001\u0002\b%\u0019\u0011QA\u0010\u0003\u000b\u0005\u0013(/Y=\u0011\t\u0005%\u0011qB\u0007\u0003\u0003\u0017Q1!!\u00045\u0003\u0011a\u0017M\\4\n\u0007%\u000bY!\u0001\fN\u001fJ+u\fU!S\u00036\u001bv,\u0011'M\u001f^c\u0015j\u0015+!\u0003\u0019i\u0017\r\u001d9feV\u0011\u0011q\u0003\t\u0005\u00033\ty#\u0004\u0002\u0002\u001c)!\u0011QDA\u0010\u0003\u0011Q7o\u001c8\u000b\t\u0005\u0005\u00121E\u0001\tI\u0006$\u0018MY5oI*!\u0011QEA\u0014\u0003\u001dQ\u0017mY6t_:TA!!\u000b\u0002,\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0003\u0003[\t1aY8n\u0013\u0011\t\t$a\u0007\u0003\u0015)\u001bxN\\'baB,'/A\u0004nCB\u0004XM\u001d\u0011\u0002\u0013I,\u0017\rZ!t\u001b\u0006\u0004HcA \u0002:!1\u00111H\nA\u0002A\n1!\u001e:m\u0001"
)
public class ErrorClassesJsonReader {
   private final Map errorInfoMap;

   public Map errorInfoMap() {
      return this.errorInfoMap;
   }

   public String getErrorMessage(final String errorClass, final Map messageParameters) {
      String messageTemplate = this.getMessageTemplate(errorClass);
      Map sanitizedParameters = (Map)messageParameters.map((x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Object var4 = x0$1._2();
            if (var4 == null) {
               return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), "null");
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Object value = x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value);
         } else {
            throw new MatchError(x0$1);
         }
      });
      StringSubstitutor sub = new StringSubstitutor(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(sanitizedParameters).asJava());
      sub.setEnableUndefinedVariableException(true);
      sub.setDisableSubstitutionInValues(true);

      String var10000;
      try {
         var10000 = sub.replace(ErrorClassesJsonReader$.MODULE$.org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX().replaceAllIn(messageTemplate, "\\$\\{$1\\}"));
      } catch (IllegalArgumentException var9) {
         throw SparkException$.MODULE$.internalError("Undefined error message parameter for error class: '" + errorClass + "', MessageTemplate: " + messageTemplate + ", Parameters: " + messageParameters, (Throwable)var9);
      }

      String errorMessage = var10000;
      if (SparkEnvUtils$.MODULE$.isTesting()) {
         int placeHoldersNum = ErrorClassesJsonReader$.MODULE$.org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX().findAllIn(messageTemplate).length();
         if (placeHoldersNum < sanitizedParameters.size() && !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])ErrorClassesJsonReader$.MODULE$.org$apache$spark$ErrorClassesJsonReader$$MORE_PARAMS_ALLOWLIST()), errorClass)) {
            throw SparkException$.MODULE$.internalError("Found unused message parameters of the error class '" + errorClass + "'. Its error message format has " + placeHoldersNum + " placeholders, but the passed message parameters map has " + sanitizedParameters.size() + " items. Consider to add placeholders to the error format or remove unused message parameters.");
         }
      }

      return errorMessage;
   }

   public Seq getMessageParameters(final String errorClass) {
      String messageTemplate = this.getMessageTemplate(errorClass);
      Seq matches = ErrorClassesJsonReader$.MODULE$.org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX().findAllIn(messageTemplate).toSeq();
      return (Seq)matches.map((m) -> scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(m), ">")), "<"));
   }

   public String getMessageTemplate(final String errorClass) {
      String[] errorClasses = errorClass.split("\\.");
      scala.Predef..MODULE$.assert(errorClasses.length == 1 || errorClasses.length == 2);
      String mainErrorClass = (String)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])errorClasses));
      Option subErrorClass = scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.refArrayOps((Object[])errorClasses))));
      ErrorInfo errorInfo = (ErrorInfo)this.errorInfoMap().getOrElse(mainErrorClass, () -> {
         throw SparkException$.MODULE$.internalError("Cannot find main error class '" + errorClass + "'");
      });
      scala.Predef..MODULE$.assert(errorInfo.subClass().isDefined() == subErrorClass.isDefined());
      if (subErrorClass.isEmpty()) {
         return errorInfo.messageTemplate();
      } else {
         ErrorSubInfo errorSubInfo = (ErrorSubInfo)((MapOps)errorInfo.subClass().get()).getOrElse(subErrorClass.get(), () -> {
            throw SparkException$.MODULE$.internalError("Cannot find sub error class '" + errorClass + "'");
         });
         String var10000 = errorInfo.messageTemplate();
         return var10000 + " " + errorSubInfo.messageTemplate();
      }
   }

   public String getSqlState(final String errorClass) {
      return (String)scala.Option..MODULE$.apply(errorClass).flatMap((x$3) -> scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(x$3), '.')))).flatMap((key) -> this.errorInfoMap().get(key)).flatMap((x$4) -> x$4.sqlState()).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public boolean isValidErrorClass(final String errorClass) {
      String[] errorClasses = errorClass.split("\\.");
      if (errorClasses != null) {
         Object var5 = scala.Array..MODULE$.unapplySeq(errorClasses);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var5) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 1) == 0) {
            String mainClass = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 0);
            return this.errorInfoMap().contains(mainClass);
         }
      }

      if (errorClasses != null) {
         Object var7 = scala.Array..MODULE$.unapplySeq(errorClasses);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var7) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 2) == 0) {
            String mainClass = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 0);
            String subClass = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 1);
            return this.errorInfoMap().get(mainClass).exists((info) -> BoxesRunTime.boxToBoolean($anonfun$isValidErrorClass$1(subClass, info)));
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isValidErrorClass$1(final String subClass$1, final ErrorInfo info) {
      return ((MapOps)info.subClass().get()).contains(subClass$1);
   }

   public ErrorClassesJsonReader(final Seq jsonFileURLs) {
      scala.Predef..MODULE$.assert(jsonFileURLs.nonEmpty());
      this.errorInfoMap = (Map)((IterableOnceOps)jsonFileURLs.map((url) -> ErrorClassesJsonReader$.MODULE$.org$apache$spark$ErrorClassesJsonReader$$readAsMap(url))).reduce((x$1, x$2) -> (Map)x$1.$plus$plus(x$2));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
