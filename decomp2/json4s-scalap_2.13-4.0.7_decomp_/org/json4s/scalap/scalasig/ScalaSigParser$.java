package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.ByteCodecs$;
import org.json4s.scalap.Main$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.runtime.BoxesRunTime;

public final class ScalaSigParser$ {
   public static final ScalaSigParser$ MODULE$ = new ScalaSigParser$();

   public Option scalaSigFromAnnotation(final ClassFile classFile) {
      return classFile.annotation(Main$.MODULE$.SCALA_SIG_ANNOTATION()).orElse(() -> classFile.annotation(Main$.MODULE$.SCALA_LONG_SIG_ANNOTATION())).map((x0$1) -> {
         if (x0$1 != null) {
            Seq elements = x0$1.elementValuePairs();
            ClassFileParser.AnnotationElement bytesElem = (ClassFileParser.AnnotationElement)elements.find((elem) -> BoxesRunTime.boxToBoolean($anonfun$scalaSigFromAnnotation$4(classFile, elem))).get();
            byte[] bytes = getBytes$1(bytesElem, classFile);
            int length = ByteCodecs$.MODULE$.decode(bytes);
            ScalaSig var2 = ScalaSigAttributeParsers$.MODULE$.parse(ByteCode$.MODULE$.apply((byte[]).MODULE$.take$extension(scala.Predef..MODULE$.byteArrayOps(bytes), length)));
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Option scalaSigFromAttribute(final ClassFile classFile) {
      return classFile.attribute(Main$.MODULE$.SCALA_SIG()).map((x$1) -> x$1.byteCode()).map((byteCode) -> ScalaSigAttributeParsers$.MODULE$.parse(byteCode));
   }

   public Option parse(final ClassFile classFile) {
      Option scalaSig = this.scalaSigFromAttribute(classFile);
      Option var2;
      if (scalaSig instanceof Some) {
         Some var5 = (Some)scalaSig;
         ScalaSig var6 = (ScalaSig)var5.value();
         if (var6 != null) {
            Seq entries = var6.table();
            if (entries.isEmpty()) {
               var2 = this.scalaSigFromAnnotation(classFile);
               return var2;
            }
         }
      }

      var2 = scalaSig;
      return var2;
   }

   public Option parse(final Class clazz) {
      ByteCode byteCode = ByteCode$.MODULE$.forClass(clazz);
      ClassFile classFile = ClassFileParser$.MODULE$.parse(byteCode);
      return this.parse(classFile);
   }

   private static final byte[] getBytes$1(final ClassFileParser.AnnotationElement bytesElem, final ClassFile classFile$1) {
      ClassFileParser.ElementValue var3 = bytesElem.elementValue();
      byte[] var2;
      if (var3 instanceof ClassFileParser.ConstValueIndex) {
         ClassFileParser.ConstValueIndex var4 = (ClassFileParser.ConstValueIndex)var3;
         int index = var4.index();
         var2 = bytesForIndex$1(index, classFile$1);
      } else {
         if (!(var3 instanceof ClassFileParser.ArrayValue)) {
            throw new MatchError(var3);
         }

         ClassFileParser.ArrayValue var6 = (ClassFileParser.ArrayValue)var3;
         Seq signatureParts = var6.values();
         var2 = mergedLongSignatureBytes$1(signatureParts, classFile$1);
      }

      return var2;
   }

   private static final byte[] mergedLongSignatureBytes$1(final Seq signatureParts, final ClassFile classFile$1) {
      return (byte[])((IterableOnceOps)signatureParts.flatMap((x0$1) -> {
         if (x0$1 instanceof ClassFileParser.ConstValueIndex) {
            ClassFileParser.ConstValueIndex var4 = (ClassFileParser.ConstValueIndex)x0$1;
            int index = var4.index();
            ArraySeq.ofByte var2 = scala.Predef..MODULE$.wrapByteArray(bytesForIndex$1(index, classFile$1));
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      })).toArray(scala.reflect.ClassTag..MODULE$.Byte());
   }

   private static final byte[] bytesForIndex$1(final int index, final ClassFile classFile$1) {
      return ((StringBytesPair)classFile$1.constantWrapped(index)).bytes();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$scalaSigFromAnnotation$4(final ClassFile classFile$1, final ClassFileParser.AnnotationElement elem) {
      boolean var3;
      label23: {
         Object var10000 = classFile$1.constant(elem.elementNameIndex());
         String var2 = Main$.MODULE$.BYTES_VALUE();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private ScalaSigParser$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
