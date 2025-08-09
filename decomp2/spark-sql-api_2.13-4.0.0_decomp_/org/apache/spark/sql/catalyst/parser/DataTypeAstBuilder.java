package org.apache.spark.sql.catalyst.parser;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.apache.spark.sql.catalyst.util.SparkParserUtils$;
import org.apache.spark.sql.connector.catalog.IdentityColumnSpec;
import org.apache.spark.sql.errors.QueryParsingErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.types.ArrayType$;
import org.apache.spark.sql.types.BinaryType$;
import org.apache.spark.sql.types.BooleanType$;
import org.apache.spark.sql.types.ByteType$;
import org.apache.spark.sql.types.CalendarIntervalType$;
import org.apache.spark.sql.types.CharType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DateType$;
import org.apache.spark.sql.types.DayTimeIntervalType;
import org.apache.spark.sql.types.DayTimeIntervalType$;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.DecimalType$;
import org.apache.spark.sql.types.DoubleType$;
import org.apache.spark.sql.types.FloatType$;
import org.apache.spark.sql.types.IntegerType$;
import org.apache.spark.sql.types.LongType$;
import org.apache.spark.sql.types.MapType$;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.NullType$;
import org.apache.spark.sql.types.ShortType$;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructField$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructType$;
import org.apache.spark.sql.types.TimestampNTZType$;
import org.apache.spark.sql.types.TimestampType$;
import org.apache.spark.sql.types.VarcharType;
import org.apache.spark.sql.types.VariantType$;
import org.apache.spark.sql.types.YearMonthIntervalType;
import org.apache.spark.sql.types.YearMonthIntervalType$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eh\u0001B\u000b\u0017\u0001\rBQA\f\u0001\u0005\u0002=BQ!\r\u0001\u0005\u0012IBQ!\u0014\u0001\u0005B9CQ\u0001\u001c\u0001\u0005B5DQ!\u001e\u0001\u0005BYDaa \u0001\u0005B\u0005\u0005\u0001bBA\u0017\u0001\u0011\u0005\u0013q\u0006\u0005\b\u0003s\u0001A\u0011IA\u001e\u0011\u001d\t)\u0005\u0001C!\u0003\u000fBq!!\u0015\u0001\t\u0003\n\u0019\u0006C\u0004\u0002^\u0001!\t\"a\u0018\t\u000f\u0005%\u0004\u0001\"\u0011\u0002l!9\u0011q\u000f\u0001\u0005B\u0005e\u0004bBAB\u0001\u0011E\u0011Q\u0011\u0005\b\u0003\u001f\u0003A\u0011IAI\u0011\u001d\t)\n\u0001C!\u0003/Cq!!)\u0001\t\u0003\n\u0019\u000bC\u0004\u0002.\u0002!\t%a,\t\u000f\u0005e\u0006\u0001\"\u0005\u0002<\"9\u0011\u0011\u001c\u0001\u0005B\u0005m'A\u0005#bi\u0006$\u0016\u0010]3BgR\u0014U/\u001b7eKJT!a\u0006\r\u0002\rA\f'o]3s\u0015\tI\"$\u0001\u0005dCR\fG._:u\u0015\tYB$A\u0002tc2T!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001'\t\u0001A\u0005E\u0002&M!j\u0011AF\u0005\u0003OY\u0011\u0001dU9m\u0005\u0006\u001cX\rU1sg\u0016\u0014()Y:f-&\u001c\u0018\u000e^8s!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012\u0001\r\t\u0003K\u0001\t!\u0002^=qK\u00124\u0016n]5u+\t\u0019d\u0007\u0006\u00025\u007fA\u0011QG\u000e\u0007\u0001\t\u00159$A1\u00019\u0005\u0005!\u0016CA\u001d=!\tI#(\u0003\u0002<U\t9aj\u001c;iS:<\u0007CA\u0015>\u0013\tq$FA\u0002B]fDQ\u0001\u0011\u0002A\u0002\u0005\u000b1a\u0019;y!\t\u00115*D\u0001D\u0015\t!U)\u0001\u0003ue\u0016,'B\u0001$H\u0003\u001d\u0011XO\u001c;j[\u0016T!\u0001S%\u0002\u0005Y$$B\u0001&!\u0003\u0015\tg\u000e\u001e7s\u0013\ta5IA\u0005QCJ\u001cX\r\u0016:fK\u0006\u0019b/[:jiNKgn\u001a7f\t\u0006$\u0018\rV=qKR\u0011q*\u0016\t\u0003!Nk\u0011!\u0015\u0006\u0003%j\tQ\u0001^=qKNL!\u0001V)\u0003\u0011\u0011\u000bG/\u0019+za\u0016DQ\u0001Q\u0002A\u0002Y\u0003\"aV5\u000f\u0005a;gBA-g\u001d\tQVM\u0004\u0002\\I:\u0011Al\u0019\b\u0003;\nt!AX1\u000e\u0003}S!\u0001\u0019\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0013BA\u0010!\u0013\tib$\u0003\u0002\u001c9%\u0011\u0011DG\u0005\u0003/aI!\u0001\u001b\f\u0002\u001bM\u000bHNQ1tKB\u000b'o]3s\u0013\tQ7NA\u000bTS:<G.\u001a#bi\u0006$\u0016\u0010]3D_:$X\r\u001f;\u000b\u0005!4\u0012A\u0006<jg&$8+\u001b8hY\u0016$\u0016M\u00197f'\u000eDW-\\1\u0015\u00059\f\bC\u0001)p\u0013\t\u0001\u0018K\u0001\u0006TiJ,8\r\u001e+za\u0016DQ\u0001\u0011\u0003A\u0002I\u0004\"aV:\n\u0005Q\\'\u0001G*j]\u001edW\rV1cY\u0016\u001c6\r[3nC\u000e{g\u000e^3yi\u0006qa/[:jiN#(/\u001b8h\u0019&$HCA<|!\tA\u00180D\u0001F\u0013\tQXIA\u0003U_.,g\u000eC\u0003A\u000b\u0001\u0007A\u0010\u0005\u0002X{&\u0011ap\u001b\u0002\u0011'R\u0014\u0018N\\4MSR\u001cuN\u001c;fqR\f\u0001D^5tSRlU\u000f\u001c;ja\u0006\u0014H/\u00133f]RLg-[3s)\u0011\t\u0019!!\n\u0011\r\u0005\u0015\u0011qBA\u000b\u001d\u0011\t9!a\u0003\u000f\u0007y\u000bI!C\u0001,\u0013\r\tiAK\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t\"a\u0005\u0003\u0007M+\u0017OC\u0002\u0002\u000e)\u0002B!a\u0006\u0002 9!\u0011\u0011DA\u000e!\tq&&C\u0002\u0002\u001e)\na\u0001\u0015:fI\u00164\u0017\u0002BA\u0011\u0003G\u0011aa\u0015;sS:<'bAA\u000fU!1\u0001I\u0002a\u0001\u0003O\u00012aVA\u0015\u0013\r\tYc\u001b\u0002\u001b\u001bVdG/\u001b9beRLE-\u001a8uS\u001aLWM]\"p]R,\u0007\u0010^\u0001\u0017m&\u001c\u0018\u000e\u001e)sS6LG/\u001b<f\t\u0006$\u0018\rV=qKR\u0019q*!\r\t\r\u0001;\u0001\u0019AA\u001a!\r9\u0016QG\u0005\u0004\u0003oY'\u0001\u0007)sS6LG/\u001b<f\t\u0006$\u0018\rV=qK\u000e{g\u000e^3yi\u0006qb/[:jif+\u0017M]'p]RD\u0017J\u001c;feZ\fG\u000eR1uCRK\b/\u001a\u000b\u0004\u001f\u0006u\u0002B\u0002!\t\u0001\u0004\ty\u0004E\u0002X\u0003\u0003J1!a\u0011l\u0005\u0001JV-\u0019:N_:$\b.\u00138uKJ4\u0018\r\u001c#bi\u0006$\u0016\u0010]3D_:$X\r\u001f;\u00029YL7/\u001b;ECf$\u0016.\\3J]R,'O^1m\t\u0006$\u0018\rV=qKR\u0019q*!\u0013\t\r\u0001K\u0001\u0019AA&!\r9\u0016QJ\u0005\u0004\u0003\u001fZ'A\b#bsRKW.Z%oi\u0016\u0014h/\u00197ECR\fG+\u001f9f\u0007>tG/\u001a=u\u0003Q1\u0018n]5u\u0007>l\u0007\u000f\\3y\t\u0006$\u0018\rV=qKR\u0019q*!\u0016\t\r\u0001S\u0001\u0019AA,!\r9\u0016\u0011L\u0005\u0004\u00037Z'AF\"p[BdW\r\u001f#bi\u0006$\u0016\u0010]3D_:$X\r\u001f;\u0002\u0019\r\u0014X-\u0019;f'\u000eDW-\\1\u0015\u00079\f\t\u0007\u0003\u0004A\u0017\u0001\u0007\u00111\r\t\u0004/\u0006\u0015\u0014bAA4W\n\u00112i\u001c7UsB,G*[:u\u0007>tG/\u001a=u\u0003A1\u0018n]5u\u0007>dG+\u001f9f\u0019&\u001cH\u000f\u0006\u0003\u0002n\u0005U\u0004CBA\u0003\u0003\u001f\ty\u0007E\u0002Q\u0003cJ1!a\u001dR\u0005-\u0019FO];di\u001aKW\r\u001c3\t\r\u0001c\u0001\u0019AA2\u000311\u0018n]5u\u0007>dG+\u001f9f)\u0011\ty'a\u001f\t\r\u0001k\u0001\u0019AA?!\r9\u0016qP\u0005\u0004\u0003\u0003['AD\"pYRK\b/Z\"p]R,\u0007\u0010^\u0001\u0011GJ,\u0017\r^3TiJ,8\r\u001e+za\u0016$2A\\AD\u0011\u0019\u0001e\u00021\u0001\u0002\nB\u0019q+a#\n\u0007\u000555NA\rD_6\u0004H.\u001a=D_2$\u0016\u0010]3MSN$8i\u001c8uKb$\u0018a\u0006<jg&$8i\\7qY\u0016D8i\u001c7UsB,G*[:u)\u0011\ti'a%\t\r\u0001{\u0001\u0019AAE\u0003M1\u0018n]5u\u0007>l\u0007\u000f\\3y\u0007>dG+\u001f9f)\u0011\ty'!'\t\r\u0001\u0003\u0002\u0019AAN!\r9\u0016QT\u0005\u0004\u0003?['!F\"p[BdW\r_\"pYRK\b/Z\"p]R,\u0007\u0010^\u0001\u0011m&\u001c\u0018\u000e^\"p[6,g\u000e^*qK\u000e$B!!\u0006\u0002&\"1\u0001)\u0005a\u0001\u0003O\u00032aVAU\u0013\r\tYk\u001b\u0002\u0013\u0007>lW.\u001a8u'B,7mQ8oi\u0016DH/\u0001\nwSNLGoQ8mY\u0006$Xm\u00117bkN,G\u0003BA\u0002\u0003cCa\u0001\u0011\nA\u0002\u0005M\u0006cA,\u00026&\u0019\u0011qW6\u0003)\r{G\u000e\\1uK\u000ec\u0017-^:f\u0007>tG/\u001a=u\u0003M1\u0018n]5u\u0013\u0012,g\u000e^5us\u000e{G.^7o)\u0019\ti,!4\u0002VB!\u0011qXAe\u001b\t\t\tM\u0003\u0003\u0002D\u0006\u0015\u0017aB2bi\u0006dwn\u001a\u0006\u0004\u0003\u000fT\u0012!C2p]:,7\r^8s\u0013\u0011\tY-!1\u0003%%#WM\u001c;jif\u001cu\u000e\\;n]N\u0003Xm\u0019\u0005\u0007\u0001N\u0001\r!a4\u0011\u0007]\u000b\t.C\u0002\u0002T.\u0014Q#\u00133f]RLG/_\"pYVlgnQ8oi\u0016DH\u000f\u0003\u0004\u0002XN\u0001\raT\u0001\tI\u0006$\u0018\rV=qK\u0006!b/[:ji&#WM\u001c;jif\u001cu\u000e\\*qK\u000e$B!!8\u0002jB9\u0011&a8\u0002d\u0006\r\u0018bAAqU\t1A+\u001e9mKJ\u00022!KAs\u0013\r\t9O\u000b\u0002\u0005\u0019>tw\r\u0003\u0004A)\u0001\u0007\u00111\u001e\t\u0004/\u00065\u0018bAAxW\n1\u0012\nZ3oi&$\u0018pQ8m'B,7mQ8oi\u0016DH\u000f"
)
public class DataTypeAstBuilder extends SqlBaseParserBaseVisitor {
   public Object typedVisit(final ParseTree ctx) {
      return ctx.accept(this);
   }

   public DataType visitSingleDataType(final SqlBaseParser.SingleDataTypeContext ctx) {
      return (DataType)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> (DataType)this.typedVisit(ctx.dataType()));
   }

   public StructType visitSingleTableSchema(final SqlBaseParser.SingleTableSchemaContext ctx) {
      return (StructType)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> StructType$.MODULE$.apply(this.visitColTypeList(ctx.colTypeList())));
   }

   public Token visitStringLit(final SqlBaseParser.StringLitContext ctx) {
      if (ctx != null) {
         return ctx.STRING_LITERAL() != null ? ctx.STRING_LITERAL().getSymbol() : ctx.DOUBLEQUOTED_STRING().getSymbol();
      } else {
         return null;
      }
   }

   public Seq visitMultipartIdentifier(final SqlBaseParser.MultipartIdentifierContext ctx) {
      return (Seq)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> ((IterableOnceOps).MODULE$.ListHasAsScala(ctx.parts).asScala().map((x$1) -> x$1.getText())).toSeq());
   }

   public DataType visitPrimitiveDataType(final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return (DataType)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> {
         SqlBaseParser.TypeContext typeCtx = ctx.type();
         Tuple2 var16 = new Tuple2(BoxesRunTime.boxToInteger(typeCtx.start.getType()), .MODULE$.ListHasAsScala(ctx.INTEGER_VALUE()).asScala().toList());
         if (var16 != null) {
            int var17 = var16._1$mcI$sp();
            List var18 = (List)var16._2();
            if (SqlBaseParser.BOOLEAN == var17 && scala.collection.immutable.Nil..MODULE$.equals(var18)) {
               return BooleanType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var19 = var16._1$mcI$sp();
            List var20 = (List)var16._2();
            if ((SqlBaseParser.TINYINT == var19 ? true : SqlBaseParser.BYTE == var19) && scala.collection.immutable.Nil..MODULE$.equals(var20)) {
               return ByteType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var21 = var16._1$mcI$sp();
            List var22 = (List)var16._2();
            if ((SqlBaseParser.SMALLINT == var21 ? true : SqlBaseParser.SHORT == var21) && scala.collection.immutable.Nil..MODULE$.equals(var22)) {
               return ShortType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var23 = var16._1$mcI$sp();
            List var24 = (List)var16._2();
            if ((SqlBaseParser.INT == var23 ? true : SqlBaseParser.INTEGER == var23) && scala.collection.immutable.Nil..MODULE$.equals(var24)) {
               return IntegerType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var25 = var16._1$mcI$sp();
            List var26 = (List)var16._2();
            if ((SqlBaseParser.BIGINT == var25 ? true : SqlBaseParser.LONG == var25) && scala.collection.immutable.Nil..MODULE$.equals(var26)) {
               return LongType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var27 = var16._1$mcI$sp();
            List var28 = (List)var16._2();
            if ((SqlBaseParser.FLOAT == var27 ? true : SqlBaseParser.REAL == var27) && scala.collection.immutable.Nil..MODULE$.equals(var28)) {
               return FloatType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var29 = var16._1$mcI$sp();
            List var30 = (List)var16._2();
            if (SqlBaseParser.DOUBLE == var29 && scala.collection.immutable.Nil..MODULE$.equals(var30)) {
               return DoubleType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var31 = var16._1$mcI$sp();
            List var32 = (List)var16._2();
            if (SqlBaseParser.DATE == var31 && scala.collection.immutable.Nil..MODULE$.equals(var32)) {
               return DateType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var33 = var16._1$mcI$sp();
            List var34 = (List)var16._2();
            if (SqlBaseParser.TIMESTAMP == var33 && scala.collection.immutable.Nil..MODULE$.equals(var34)) {
               return SqlApiConf$.MODULE$.get().timestampType();
            }
         }

         if (var16 != null) {
            int var35 = var16._1$mcI$sp();
            List var36 = (List)var16._2();
            if (SqlBaseParser.TIMESTAMP_NTZ == var35 && scala.collection.immutable.Nil..MODULE$.equals(var36)) {
               return TimestampNTZType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var37 = var16._1$mcI$sp();
            List var38 = (List)var16._2();
            if (SqlBaseParser.TIMESTAMP_LTZ == var37 && scala.collection.immutable.Nil..MODULE$.equals(var38)) {
               return TimestampType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var39 = var16._1$mcI$sp();
            List var40 = (List)var16._2();
            if (SqlBaseParser.STRING == var39 && scala.collection.immutable.Nil..MODULE$.equals(var40)) {
               Seq var41 = .MODULE$.ListHasAsScala(typeCtx.children).asScala().toSeq();
               if (var41 != null) {
                  SeqOps var42 = scala.package..MODULE$.Seq().unapplySeq(var41);
                  if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var42) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var42)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var42), 1) == 0) {
                     return StringType$.MODULE$;
                  }
               }

               if (var41 != null) {
                  SeqOps var43 = scala.package..MODULE$.Seq().unapplySeq(var41);
                  if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var43) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43), 2) == 0) {
                     ParseTree ctx = (ParseTree)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43), 1);
                     if (ctx instanceof SqlBaseParser.CollateClauseContext) {
                        SqlBaseParser.CollateClauseContext var45 = (SqlBaseParser.CollateClauseContext)ctx;
                        String[] collationNameParts = (String[])this.visitCollateClause(var45).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
                        int collationId = CollationFactory.collationNameToId(CollationFactory.resolveFullyQualifiedName(collationNameParts));
                        return StringType$.MODULE$.apply(collationId);
                     }
                  }
               }

               throw new MatchError(var41);
            }
         }

         if (var16 != null) {
            int var48 = var16._1$mcI$sp();
            List var49 = (List)var16._2();
            if ((SqlBaseParser.CHARACTER == var48 ? true : SqlBaseParser.CHAR == var48) && var49 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var50 = (scala.collection.immutable..colon.colon)var49;
               TerminalNode length = (TerminalNode)var50.head();
               List var52 = var50.next$access$1();
               if (scala.collection.immutable.Nil..MODULE$.equals(var52)) {
                  return new CharType(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(length.getText())));
               }
            }
         }

         if (var16 != null) {
            int var53 = var16._1$mcI$sp();
            List var54 = (List)var16._2();
            if (SqlBaseParser.VARCHAR == var53 && var54 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var55 = (scala.collection.immutable..colon.colon)var54;
               TerminalNode length = (TerminalNode)var55.head();
               List var57 = var55.next$access$1();
               if (scala.collection.immutable.Nil..MODULE$.equals(var57)) {
                  return new VarcharType(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(length.getText())));
               }
            }
         }

         if (var16 != null) {
            int var58 = var16._1$mcI$sp();
            List var59 = (List)var16._2();
            if (SqlBaseParser.BINARY == var58 && scala.collection.immutable.Nil..MODULE$.equals(var59)) {
               return BinaryType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var60 = var16._1$mcI$sp();
            List var61 = (List)var16._2();
            if ((SqlBaseParser.DECIMAL == var60 ? true : (SqlBaseParser.DEC == var60 ? true : SqlBaseParser.NUMERIC == var60)) && scala.collection.immutable.Nil..MODULE$.equals(var61)) {
               return DecimalType$.MODULE$.USER_DEFAULT();
            }
         }

         if (var16 != null) {
            int var62 = var16._1$mcI$sp();
            List var63 = (List)var16._2();
            if ((SqlBaseParser.DECIMAL == var62 ? true : (SqlBaseParser.DEC == var62 ? true : SqlBaseParser.NUMERIC == var62)) && var63 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var64 = (scala.collection.immutable..colon.colon)var63;
               TerminalNode precision = (TerminalNode)var64.head();
               List var66 = var64.next$access$1();
               if (scala.collection.immutable.Nil..MODULE$.equals(var66)) {
                  return new DecimalType(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(precision.getText())), 0);
               }
            }
         }

         if (var16 != null) {
            int var67 = var16._1$mcI$sp();
            List var68 = (List)var16._2();
            if ((SqlBaseParser.DECIMAL == var67 ? true : (SqlBaseParser.DEC == var67 ? true : SqlBaseParser.NUMERIC == var67)) && var68 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var69 = (scala.collection.immutable..colon.colon)var68;
               TerminalNode precision = (TerminalNode)var69.head();
               List var71 = var69.next$access$1();
               if (var71 instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var72 = (scala.collection.immutable..colon.colon)var71;
                  TerminalNode scale = (TerminalNode)var72.head();
                  List var74 = var72.next$access$1();
                  if (scala.collection.immutable.Nil..MODULE$.equals(var74)) {
                     return new DecimalType(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(precision.getText())), scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(scale.getText())));
                  }
               }
            }
         }

         if (var16 != null) {
            int var75 = var16._1$mcI$sp();
            List var76 = (List)var16._2();
            if (SqlBaseParser.VOID == var75 && scala.collection.immutable.Nil..MODULE$.equals(var76)) {
               return NullType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var77 = var16._1$mcI$sp();
            List var78 = (List)var16._2();
            if (SqlBaseParser.INTERVAL == var77 && scala.collection.immutable.Nil..MODULE$.equals(var78)) {
               return CalendarIntervalType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var79 = var16._1$mcI$sp();
            List var80 = (List)var16._2();
            if (SqlBaseParser.VARIANT == var79 && scala.collection.immutable.Nil..MODULE$.equals(var80)) {
               return VariantType$.MODULE$;
            }
         }

         if (var16 != null) {
            int var81 = var16._1$mcI$sp();
            List var82 = (List)var16._2();
            if ((SqlBaseParser.CHARACTER == var81 ? true : (SqlBaseParser.CHAR == var81 ? true : SqlBaseParser.VARCHAR == var81)) && scala.collection.immutable.Nil..MODULE$.equals(var82)) {
               throw QueryParsingErrors$.MODULE$.charTypeMissingLengthError(ctx.type().getText(), ctx);
            }
         }

         if (var16 != null) {
            int var83 = var16._1$mcI$sp();
            List var84 = (List)var16._2();
            if ((SqlBaseParser.ARRAY == var83 ? true : (SqlBaseParser.STRUCT == var83 ? true : SqlBaseParser.MAP == var83)) && scala.collection.immutable.Nil..MODULE$.equals(var84)) {
               throw QueryParsingErrors$.MODULE$.nestedTypeMissingElementTypeError(ctx.type().getText(), ctx);
            }
         }

         if (var16 != null) {
            List params = (List)var16._2();
            String badType = ctx.type().getText();
            String dtStr = params.nonEmpty() ? badType + "(" + params.mkString(",") + ")" : badType;
            throw QueryParsingErrors$.MODULE$.dataTypeUnsupportedError(dtStr, ctx);
         } else {
            throw new MatchError(var16);
         }
      });
   }

   public DataType visitYearMonthIntervalDataType(final SqlBaseParser.YearMonthIntervalDataTypeContext ctx) {
      String startStr = ctx.from.getText().toLowerCase(Locale.ROOT);
      byte start = BoxesRunTime.unboxToByte(YearMonthIntervalType$.MODULE$.stringToField().apply(startStr));
      if (ctx.to != null) {
         String endStr = ctx.to.getText().toLowerCase(Locale.ROOT);
         byte end = BoxesRunTime.unboxToByte(YearMonthIntervalType$.MODULE$.stringToField().apply(endStr));
         if (end <= start) {
            throw QueryParsingErrors$.MODULE$.fromToIntervalUnsupportedError(startStr, endStr, ctx);
         } else {
            return new YearMonthIntervalType(start, end);
         }
      } else {
         return YearMonthIntervalType$.MODULE$.apply(start);
      }
   }

   public DataType visitDayTimeIntervalDataType(final SqlBaseParser.DayTimeIntervalDataTypeContext ctx) {
      String startStr = ctx.from.getText().toLowerCase(Locale.ROOT);
      byte start = BoxesRunTime.unboxToByte(DayTimeIntervalType$.MODULE$.stringToField().apply(startStr));
      if (ctx.to != null) {
         String endStr = ctx.to.getText().toLowerCase(Locale.ROOT);
         byte end = BoxesRunTime.unboxToByte(DayTimeIntervalType$.MODULE$.stringToField().apply(endStr));
         if (end <= start) {
            throw QueryParsingErrors$.MODULE$.fromToIntervalUnsupportedError(startStr, endStr, ctx);
         } else {
            return new DayTimeIntervalType(start, end);
         }
      } else {
         return DayTimeIntervalType$.MODULE$.apply(start);
      }
   }

   public DataType visitComplexDataType(final SqlBaseParser.ComplexDataTypeContext ctx) {
      return (DataType)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> {
         int var3 = ctx.complex.getType();
         if (SqlBaseParser.ARRAY == var3) {
            return ArrayType$.MODULE$.apply((DataType)this.typedVisit(ctx.dataType(0)));
         } else if (SqlBaseParser.MAP == var3) {
            return MapType$.MODULE$.apply((DataType)this.typedVisit(ctx.dataType(0)), (DataType)this.typedVisit(ctx.dataType(1)));
         } else if (SqlBaseParser.STRUCT == var3) {
            return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.Option..MODULE$.option2Iterable(scala.Option..MODULE$.apply(ctx.complexColTypeList())).toArray(scala.reflect.ClassTag..MODULE$.apply(SqlBaseParser.ComplexColTypeListContext.class))), (ctxx) -> this.visitComplexColTypeList(ctxx), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
         } else {
            throw new MatchError(BoxesRunTime.boxToInteger(var3));
         }
      });
   }

   public StructType createSchema(final SqlBaseParser.ColTypeListContext ctx) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.Option..MODULE$.option2Iterable(scala.Option..MODULE$.apply(ctx)).toArray(scala.reflect.ClassTag..MODULE$.apply(SqlBaseParser.ColTypeListContext.class))), (ctxx) -> this.visitColTypeList(ctxx), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public Seq visitColTypeList(final SqlBaseParser.ColTypeListContext ctx) {
      return (Seq)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> ((IterableOnceOps).MODULE$.ListHasAsScala(ctx.colType()).asScala().map((ctxx) -> this.visitColType(ctxx))).toSeq());
   }

   public StructField visitColType(final SqlBaseParser.ColTypeContext ctx) {
      return (StructField)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> {
         MetadataBuilder builder = new MetadataBuilder();
         scala.Option..MODULE$.apply(ctx.commentSpec()).map((ctxx) -> this.visitCommentSpec(ctxx)).foreach((x$2) -> builder.putString("comment", x$2));
         return new StructField(ctx.colName.getText(), (DataType)this.typedVisit(ctx.dataType()), ctx.NULL() == null, builder.build());
      });
   }

   public StructType createStructType(final SqlBaseParser.ComplexColTypeListContext ctx) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.Option..MODULE$.option2Iterable(scala.Option..MODULE$.apply(ctx)).toArray(scala.reflect.ClassTag..MODULE$.apply(SqlBaseParser.ComplexColTypeListContext.class))), (ctxx) -> this.visitComplexColTypeList(ctxx), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public Seq visitComplexColTypeList(final SqlBaseParser.ComplexColTypeListContext ctx) {
      return (Seq)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> ((IterableOnceOps).MODULE$.ListHasAsScala(ctx.complexColType()).asScala().map((ctxx) -> this.visitComplexColType(ctxx))).toSeq());
   }

   public StructField visitComplexColType(final SqlBaseParser.ComplexColTypeContext ctx) {
      return (StructField)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> {
         StructField structField = new StructField(ctx.errorCapturingIdentifier().getText(), (DataType)this.typedVisit(ctx.dataType()), ctx.NULL() == null, StructField$.MODULE$.apply$default$4());
         return (StructField)scala.Option..MODULE$.apply(ctx.commentSpec()).map((ctxx) -> this.visitCommentSpec(ctxx)).map((comment) -> structField.withComment(comment)).getOrElse(() -> structField);
      });
   }

   public String visitCommentSpec(final SqlBaseParser.CommentSpecContext ctx) {
      return (String)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> SparkParserUtils$.MODULE$.string(this.visitStringLit(ctx.stringLit())));
   }

   public Seq visitCollateClause(final SqlBaseParser.CollateClauseContext ctx) {
      return (Seq)SparkParserUtils$.MODULE$.withOrigin(ctx, SparkParserUtils$.MODULE$.withOrigin$default$2(), () -> this.visitMultipartIdentifier(ctx.collationName));
   }

   public IdentityColumnSpec visitIdentityColumn(final SqlBaseParser.IdentityColumnContext ctx, final DataType dataType) {
      label36: {
         LongType$ var4 = LongType$.MODULE$;
         if (dataType == null) {
            if (var4 == null) {
               break label36;
            }
         } else if (dataType.equals(var4)) {
            break label36;
         }

         IntegerType$ var5 = IntegerType$.MODULE$;
         if (dataType == null) {
            if (var5 != null) {
               throw QueryParsingErrors$.MODULE$.identityColumnUnsupportedDataType(ctx, dataType.toString());
            }
         } else if (!dataType.equals(var5)) {
            throw QueryParsingErrors$.MODULE$.identityColumnUnsupportedDataType(ctx, dataType.toString());
         }
      }

      boolean allowExplicitInsert = ctx.BY() != null && ctx.DEFAULT() != null;
      Tuple2 var8 = this.visitIdentityColSpec(ctx.identityColSpec());
      if (var8 != null) {
         long start = var8._1$mcJ$sp();
         long step = var8._2$mcJ$sp();
         Tuple2.mcJJ.sp var7 = new Tuple2.mcJJ.sp(start, step);
         long start = ((Tuple2)var7)._1$mcJ$sp();
         long step = ((Tuple2)var7)._2$mcJ$sp();
         return new IdentityColumnSpec(start, step, allowExplicitInsert);
      } else {
         throw new MatchError(var8);
      }
   }

   public Tuple2 visitIdentityColSpec(final SqlBaseParser.IdentityColSpecContext ctx) {
      int defaultStart = 1;
      int defaultStep = 1;
      if (ctx == null) {
         return new Tuple2.mcJJ.sp((long)defaultStart, (long)defaultStep);
      } else {
         Tuple2 var6 = new Tuple2(scala.None..MODULE$, scala.None..MODULE$);
         if (var6 != null) {
            Option start = (Option)var6._1();
            Option step = (Option)var6._2();
            Tuple2 var5 = new Tuple2(start, step);
            ObjectRef start = ObjectRef.create((Option)var5._1());
            ObjectRef step = ObjectRef.create((Option)var5._2());
            .MODULE$.ListHasAsScala(ctx.sequenceGeneratorOption()).asScala().foreach((option) -> {
               $anonfun$visitIdentityColSpec$1(start, ctx, step, option);
               return BoxedUnit.UNIT;
            });
            return new Tuple2.mcJJ.sp(BoxesRunTime.unboxToLong(((Option)start.elem).getOrElse((JFunction0.mcJ.sp)() -> (long)defaultStart)), BoxesRunTime.unboxToLong(((Option)step.elem).getOrElse((JFunction0.mcJ.sp)() -> (long)defaultStep)));
         } else {
            throw new MatchError(var6);
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$visitIdentityColSpec$1(final ObjectRef start$1, final SqlBaseParser.IdentityColSpecContext ctx$12, final ObjectRef step$1, final SqlBaseParser.SequenceGeneratorOptionContext option) {
      if (option.start != null) {
         if (((Option)start$1.elem).isDefined()) {
            throw QueryParsingErrors$.MODULE$.identityColumnDuplicatedSequenceGeneratorOption(ctx$12, "START");
         } else {
            start$1.elem = new Some(BoxesRunTime.boxToLong(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(option.start.getText()))));
         }
      } else if (option.step != null) {
         if (((Option)step$1.elem).isDefined()) {
            throw QueryParsingErrors$.MODULE$.identityColumnDuplicatedSequenceGeneratorOption(ctx$12, "STEP");
         } else {
            step$1.elem = new Some(BoxesRunTime.boxToLong(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(option.step.getText()))));
            if (BoxesRunTime.unboxToLong(((Option)step$1.elem).get()) == 0L) {
               throw QueryParsingErrors$.MODULE$.identityColumnIllegalStep(ctx$12);
            }
         }
      } else {
         throw org.apache.spark.SparkException..MODULE$.internalError("Invalid identity column sequence generator option: " + option.getText());
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
