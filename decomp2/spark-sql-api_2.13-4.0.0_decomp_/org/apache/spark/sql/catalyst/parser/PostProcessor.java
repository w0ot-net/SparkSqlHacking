package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%t!\u0002\n\u0014\u0011\u0003\u0003c!\u0002\u0012\u0014\u0011\u0003\u001b\u0003\"B\u001d\u0002\t\u0003Q\u0004\"B\u001e\u0002\t\u0003b\u0004\"B%\u0002\t\u0003R\u0005\"B(\u0002\t\u0003\u0002\u0006\"B+\u0002\t\u00032\u0006\"B.\u0002\t\u0003b\u0006\"B1\u0002\t\u0013\u0011\u0007b\u0002?\u0002#\u0003%I! \u0005\n\u0003+\t\u0011\u0011!C!\u0003/A\u0011\"!\u000b\u0002\u0003\u0003%\t!a\u000b\t\u0013\u00055\u0012!!A\u0005\u0002\u0005=\u0002\"CA\u001e\u0003\u0005\u0005I\u0011IA\u001f\u0011%\tY%AA\u0001\n\u0003\ti\u0005C\u0005\u0002X\u0005\t\t\u0011\"\u0011\u0002Z!I\u00111L\u0001\u0002\u0002\u0013\u0005\u0013Q\f\u0005\n\u0003?\n\u0011\u0011!C\u0005\u0003C\nQ\u0002U8tiB\u0013xnY3tg>\u0014(B\u0001\u000b\u0016\u0003\u0019\u0001\u0018M]:fe*\u0011acF\u0001\tG\u0006$\u0018\r\\=ti*\u0011\u0001$G\u0001\u0004gFd'B\u0001\u000e\u001c\u0003\u0015\u0019\b/\u0019:l\u0015\taR$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002=\u0005\u0019qN]4\u0004\u0001A\u0011\u0011%A\u0007\u0002'\ti\u0001k\\:u!J|7-Z:t_J\u001cB!\u0001\u0013([A\u0011\u0011%J\u0005\u0003MM\u0011\u0011dU9m\u0005\u0006\u001cX\rU1sg\u0016\u0014()Y:f\u0019&\u001cH/\u001a8feB\u0011\u0001fK\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t9\u0001K]8ek\u000e$\bC\u0001\u00187\u001d\tyCG\u0004\u00021g5\t\u0011G\u0003\u00023?\u00051AH]8pizJ\u0011AK\u0005\u0003k%\nq\u0001]1dW\u0006<W-\u0003\u00028q\ta1+\u001a:jC2L'0\u00192mK*\u0011Q'K\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\na\"\u001a=ji\u0016\u0013(o\u001c:JI\u0016tG\u000f\u0006\u0002>\u0001B\u0011\u0001FP\u0005\u0003\u007f%\u0012A!\u00168ji\")\u0011i\u0001a\u0001\u0005\u0006\u00191\r\u001e=\u0011\u0005\r3eBA\u0011E\u0013\t)5#A\u0007Tc2\u0014\u0015m]3QCJ\u001cXM]\u0005\u0003\u000f\"\u0013\u0011#\u0012:s_JLE-\u001a8u\u0007>tG/\u001a=u\u0015\t)5#\u0001\ffq&$XK\\9v_R,G-\u00133f]RLg-[3s)\ti4\nC\u0003B\t\u0001\u0007A\n\u0005\u0002D\u001b&\u0011a\n\u0013\u0002\u001a+:\fXo\u001c;fI&#WM\u001c;jM&,'oQ8oi\u0016DH/\u0001\u000bfq&$\u0018+^8uK\u0012LE-\u001a8uS\u001aLWM\u001d\u000b\u0003{ECQ!Q\u0003A\u0002I\u0003\"aQ*\n\u0005QC%aF)v_R,G-\u00133f]RLg-[3s\u0007>tG/\u001a=u\u0003a)\u00070\u001b;CC\u000e\\\u0017+^8uK\u0012LE-\u001a8uS\u001aLWM\u001d\u000b\u0003{]CQ!\u0011\u0004A\u0002a\u0003\"aQ-\n\u0005iC%a\u0007\"bG.\fVo\u001c;fI&#WM\u001c;jM&,'oQ8oi\u0016DH/A\bfq&$hj\u001c8SKN,'O^3e)\tiT\fC\u0003B\u000f\u0001\u0007a\f\u0005\u0002D?&\u0011\u0001\r\u0013\u0002\u0013\u001d>t'+Z:feZ,GmQ8oi\u0016DH/\u0001\rsKBd\u0017mY3U_.,gNQ=JI\u0016tG/\u001b4jKJ$2aY:x)\tiD\rC\u0004f\u0011A\u0005\t\u0019\u00014\u0002\u0003\u0019\u0004B\u0001K4jS&\u0011\u0001.\u000b\u0002\n\rVt7\r^5p]F\u0002\"A[9\u000e\u0003-T!\u0001\\7\u0002\u000fI,h\u000e^5nK*\u0011an\\\u0001\u0003mRR!\u0001]\u000f\u0002\u000b\u0005tG\u000f\u001c:\n\u0005I\\'aC\"p[6|g\u000eV8lK:DQ!\u0011\u0005A\u0002Q\u0004\"A[;\n\u0005Y\\'!\u0005)beN,'OU;mK\u000e{g\u000e^3yi\")\u0001\u0010\u0003a\u0001s\u0006a1\u000f\u001e:ja6\u000b'oZ5ogB\u0011\u0001F_\u0005\u0003w&\u00121!\u00138u\u0003\t\u0012X\r\u001d7bG\u0016$vn[3o\u0005fLE-\u001a8uS\u001aLWM\u001d\u0013eK\u001a\fW\u000f\u001c;%gQ)a0!\u0005\u0002\u0014)\u0012am`\u0016\u0003\u0003\u0003\u0001B!a\u0001\u0002\u000e5\u0011\u0011Q\u0001\u0006\u0005\u0003\u000f\tI!A\u0005v]\u000eDWmY6fI*\u0019\u00111B\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0010\u0005\u0015!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\")\u0011)\u0003a\u0001i\")\u00010\u0003a\u0001s\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0007\u0011\t\u0005m\u0011QE\u0007\u0003\u0003;QA!a\b\u0002\"\u0005!A.\u00198h\u0015\t\t\u0019#\u0001\u0003kCZ\f\u0017\u0002BA\u0014\u0003;\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011GA\u001c!\rA\u00131G\u0005\u0004\u0003kI#aA!os\"A\u0011\u0011\b\u0007\u0002\u0002\u0003\u0007\u00110A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u007f\u0001b!!\u0011\u0002H\u0005ERBAA\"\u0015\r\t)%K\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA%\u0003\u0007\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qJA+!\rA\u0013\u0011K\u0005\u0004\u0003'J#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003sq\u0011\u0011!a\u0001\u0003c\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002s\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u001a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\r\t\u0005\u00037\t)'\u0003\u0003\u0002h\u0005u!AB(cU\u0016\u001cG\u000f"
)
public final class PostProcessor {
   public static String toString() {
      return PostProcessor$.MODULE$.toString();
   }

   public static int hashCode() {
      return PostProcessor$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return PostProcessor$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return PostProcessor$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return PostProcessor$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return PostProcessor$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return PostProcessor$.MODULE$.productPrefix();
   }

   public static void exitNonReserved(final SqlBaseParser.NonReservedContext ctx) {
      PostProcessor$.MODULE$.exitNonReserved(ctx);
   }

   public static void exitBackQuotedIdentifier(final SqlBaseParser.BackQuotedIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitBackQuotedIdentifier(ctx);
   }

   public static void exitQuotedIdentifier(final SqlBaseParser.QuotedIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitQuotedIdentifier(ctx);
   }

   public static void exitUnquotedIdentifier(final SqlBaseParser.UnquotedIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitUnquotedIdentifier(ctx);
   }

   public static void exitErrorIdent(final SqlBaseParser.ErrorIdentContext ctx) {
      PostProcessor$.MODULE$.exitErrorIdent(ctx);
   }

   public static Iterator productElementNames() {
      return PostProcessor$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return PostProcessor$.MODULE$.productElementName(n);
   }

   public static void visitErrorNode(final ErrorNode node) {
      PostProcessor$.MODULE$.visitErrorNode(node);
   }

   public static void visitTerminal(final TerminalNode node) {
      PostProcessor$.MODULE$.visitTerminal(node);
   }

   public static void exitEveryRule(final ParserRuleContext ctx) {
      PostProcessor$.MODULE$.exitEveryRule(ctx);
   }

   public static void enterEveryRule(final ParserRuleContext ctx) {
      PostProcessor$.MODULE$.enterEveryRule(ctx);
   }

   public static void enterNonReserved(final SqlBaseParser.NonReservedContext ctx) {
      PostProcessor$.MODULE$.enterNonReserved(ctx);
   }

   public static void exitStrictNonReserved(final SqlBaseParser.StrictNonReservedContext ctx) {
      PostProcessor$.MODULE$.exitStrictNonReserved(ctx);
   }

   public static void enterStrictNonReserved(final SqlBaseParser.StrictNonReservedContext ctx) {
      PostProcessor$.MODULE$.enterStrictNonReserved(ctx);
   }

   public static void exitAnsiNonReserved(final SqlBaseParser.AnsiNonReservedContext ctx) {
      PostProcessor$.MODULE$.exitAnsiNonReserved(ctx);
   }

   public static void enterAnsiNonReserved(final SqlBaseParser.AnsiNonReservedContext ctx) {
      PostProcessor$.MODULE$.enterAnsiNonReserved(ctx);
   }

   public static void exitOperatorPipeSetAssignmentSeq(final SqlBaseParser.OperatorPipeSetAssignmentSeqContext ctx) {
      PostProcessor$.MODULE$.exitOperatorPipeSetAssignmentSeq(ctx);
   }

   public static void enterOperatorPipeSetAssignmentSeq(final SqlBaseParser.OperatorPipeSetAssignmentSeqContext ctx) {
      PostProcessor$.MODULE$.enterOperatorPipeSetAssignmentSeq(ctx);
   }

   public static void exitOperatorPipeRightSide(final SqlBaseParser.OperatorPipeRightSideContext ctx) {
      PostProcessor$.MODULE$.exitOperatorPipeRightSide(ctx);
   }

   public static void enterOperatorPipeRightSide(final SqlBaseParser.OperatorPipeRightSideContext ctx) {
      PostProcessor$.MODULE$.enterOperatorPipeRightSide(ctx);
   }

   public static void exitVersion(final SqlBaseParser.VersionContext ctx) {
      PostProcessor$.MODULE$.exitVersion(ctx);
   }

   public static void enterVersion(final SqlBaseParser.VersionContext ctx) {
      PostProcessor$.MODULE$.enterVersion(ctx);
   }

   public static void exitComment(final SqlBaseParser.CommentContext ctx) {
      PostProcessor$.MODULE$.exitComment(ctx);
   }

   public static void enterComment(final SqlBaseParser.CommentContext ctx) {
      PostProcessor$.MODULE$.enterComment(ctx);
   }

   public static void exitStringLit(final SqlBaseParser.StringLitContext ctx) {
      PostProcessor$.MODULE$.exitStringLit(ctx);
   }

   public static void enterStringLit(final SqlBaseParser.StringLitContext ctx) {
      PostProcessor$.MODULE$.enterStringLit(ctx);
   }

   public static void exitAlterColumnAction(final SqlBaseParser.AlterColumnActionContext ctx) {
      PostProcessor$.MODULE$.exitAlterColumnAction(ctx);
   }

   public static void enterAlterColumnAction(final SqlBaseParser.AlterColumnActionContext ctx) {
      PostProcessor$.MODULE$.enterAlterColumnAction(ctx);
   }

   public static void exitAlterColumnSpec(final SqlBaseParser.AlterColumnSpecContext ctx) {
      PostProcessor$.MODULE$.exitAlterColumnSpec(ctx);
   }

   public static void enterAlterColumnSpec(final SqlBaseParser.AlterColumnSpecContext ctx) {
      PostProcessor$.MODULE$.enterAlterColumnSpec(ctx);
   }

   public static void exitAlterColumnSpecList(final SqlBaseParser.AlterColumnSpecListContext ctx) {
      PostProcessor$.MODULE$.exitAlterColumnSpecList(ctx);
   }

   public static void enterAlterColumnSpecList(final SqlBaseParser.AlterColumnSpecListContext ctx) {
      PostProcessor$.MODULE$.enterAlterColumnSpecList(ctx);
   }

   public static void exitBigDecimalLiteral(final SqlBaseParser.BigDecimalLiteralContext ctx) {
      PostProcessor$.MODULE$.exitBigDecimalLiteral(ctx);
   }

   public static void enterBigDecimalLiteral(final SqlBaseParser.BigDecimalLiteralContext ctx) {
      PostProcessor$.MODULE$.enterBigDecimalLiteral(ctx);
   }

   public static void exitFloatLiteral(final SqlBaseParser.FloatLiteralContext ctx) {
      PostProcessor$.MODULE$.exitFloatLiteral(ctx);
   }

   public static void enterFloatLiteral(final SqlBaseParser.FloatLiteralContext ctx) {
      PostProcessor$.MODULE$.enterFloatLiteral(ctx);
   }

   public static void exitDoubleLiteral(final SqlBaseParser.DoubleLiteralContext ctx) {
      PostProcessor$.MODULE$.exitDoubleLiteral(ctx);
   }

   public static void enterDoubleLiteral(final SqlBaseParser.DoubleLiteralContext ctx) {
      PostProcessor$.MODULE$.enterDoubleLiteral(ctx);
   }

   public static void exitTinyIntLiteral(final SqlBaseParser.TinyIntLiteralContext ctx) {
      PostProcessor$.MODULE$.exitTinyIntLiteral(ctx);
   }

   public static void enterTinyIntLiteral(final SqlBaseParser.TinyIntLiteralContext ctx) {
      PostProcessor$.MODULE$.enterTinyIntLiteral(ctx);
   }

   public static void exitSmallIntLiteral(final SqlBaseParser.SmallIntLiteralContext ctx) {
      PostProcessor$.MODULE$.exitSmallIntLiteral(ctx);
   }

   public static void enterSmallIntLiteral(final SqlBaseParser.SmallIntLiteralContext ctx) {
      PostProcessor$.MODULE$.enterSmallIntLiteral(ctx);
   }

   public static void exitBigIntLiteral(final SqlBaseParser.BigIntLiteralContext ctx) {
      PostProcessor$.MODULE$.exitBigIntLiteral(ctx);
   }

   public static void enterBigIntLiteral(final SqlBaseParser.BigIntLiteralContext ctx) {
      PostProcessor$.MODULE$.enterBigIntLiteral(ctx);
   }

   public static void exitIntegerLiteral(final SqlBaseParser.IntegerLiteralContext ctx) {
      PostProcessor$.MODULE$.exitIntegerLiteral(ctx);
   }

   public static void enterIntegerLiteral(final SqlBaseParser.IntegerLiteralContext ctx) {
      PostProcessor$.MODULE$.enterIntegerLiteral(ctx);
   }

   public static void exitLegacyDecimalLiteral(final SqlBaseParser.LegacyDecimalLiteralContext ctx) {
      PostProcessor$.MODULE$.exitLegacyDecimalLiteral(ctx);
   }

   public static void enterLegacyDecimalLiteral(final SqlBaseParser.LegacyDecimalLiteralContext ctx) {
      PostProcessor$.MODULE$.enterLegacyDecimalLiteral(ctx);
   }

   public static void exitDecimalLiteral(final SqlBaseParser.DecimalLiteralContext ctx) {
      PostProcessor$.MODULE$.exitDecimalLiteral(ctx);
   }

   public static void enterDecimalLiteral(final SqlBaseParser.DecimalLiteralContext ctx) {
      PostProcessor$.MODULE$.enterDecimalLiteral(ctx);
   }

   public static void exitExponentLiteral(final SqlBaseParser.ExponentLiteralContext ctx) {
      PostProcessor$.MODULE$.exitExponentLiteral(ctx);
   }

   public static void enterExponentLiteral(final SqlBaseParser.ExponentLiteralContext ctx) {
      PostProcessor$.MODULE$.enterExponentLiteral(ctx);
   }

   public static void enterBackQuotedIdentifier(final SqlBaseParser.BackQuotedIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterBackQuotedIdentifier(ctx);
   }

   public static void enterQuotedIdentifier(final SqlBaseParser.QuotedIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterQuotedIdentifier(ctx);
   }

   public static void exitQuotedIdentifierAlternative(final SqlBaseParser.QuotedIdentifierAlternativeContext ctx) {
      PostProcessor$.MODULE$.exitQuotedIdentifierAlternative(ctx);
   }

   public static void enterQuotedIdentifierAlternative(final SqlBaseParser.QuotedIdentifierAlternativeContext ctx) {
      PostProcessor$.MODULE$.enterQuotedIdentifierAlternative(ctx);
   }

   public static void enterUnquotedIdentifier(final SqlBaseParser.UnquotedIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterUnquotedIdentifier(ctx);
   }

   public static void exitIdentifier(final SqlBaseParser.IdentifierContext ctx) {
      PostProcessor$.MODULE$.exitIdentifier(ctx);
   }

   public static void enterIdentifier(final SqlBaseParser.IdentifierContext ctx) {
      PostProcessor$.MODULE$.enterIdentifier(ctx);
   }

   public static void exitRealIdent(final SqlBaseParser.RealIdentContext ctx) {
      PostProcessor$.MODULE$.exitRealIdent(ctx);
   }

   public static void enterRealIdent(final SqlBaseParser.RealIdentContext ctx) {
      PostProcessor$.MODULE$.enterRealIdent(ctx);
   }

   public static void enterErrorIdent(final SqlBaseParser.ErrorIdentContext ctx) {
      PostProcessor$.MODULE$.enterErrorIdent(ctx);
   }

   public static void exitErrorCapturingIdentifier(final SqlBaseParser.ErrorCapturingIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitErrorCapturingIdentifier(ctx);
   }

   public static void enterErrorCapturingIdentifier(final SqlBaseParser.ErrorCapturingIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterErrorCapturingIdentifier(ctx);
   }

   public static void exitQualifiedName(final SqlBaseParser.QualifiedNameContext ctx) {
      PostProcessor$.MODULE$.exitQualifiedName(ctx);
   }

   public static void enterQualifiedName(final SqlBaseParser.QualifiedNameContext ctx) {
      PostProcessor$.MODULE$.enterQualifiedName(ctx);
   }

   public static void exitFunctionName(final SqlBaseParser.FunctionNameContext ctx) {
      PostProcessor$.MODULE$.exitFunctionName(ctx);
   }

   public static void enterFunctionName(final SqlBaseParser.FunctionNameContext ctx) {
      PostProcessor$.MODULE$.enterFunctionName(ctx);
   }

   public static void exitQualifiedNameList(final SqlBaseParser.QualifiedNameListContext ctx) {
      PostProcessor$.MODULE$.exitQualifiedNameList(ctx);
   }

   public static void enterQualifiedNameList(final SqlBaseParser.QualifiedNameListContext ctx) {
      PostProcessor$.MODULE$.enterQualifiedNameList(ctx);
   }

   public static void exitFrameBound(final SqlBaseParser.FrameBoundContext ctx) {
      PostProcessor$.MODULE$.exitFrameBound(ctx);
   }

   public static void enterFrameBound(final SqlBaseParser.FrameBoundContext ctx) {
      PostProcessor$.MODULE$.enterFrameBound(ctx);
   }

   public static void exitWindowFrame(final SqlBaseParser.WindowFrameContext ctx) {
      PostProcessor$.MODULE$.exitWindowFrame(ctx);
   }

   public static void enterWindowFrame(final SqlBaseParser.WindowFrameContext ctx) {
      PostProcessor$.MODULE$.enterWindowFrame(ctx);
   }

   public static void exitWindowDef(final SqlBaseParser.WindowDefContext ctx) {
      PostProcessor$.MODULE$.exitWindowDef(ctx);
   }

   public static void enterWindowDef(final SqlBaseParser.WindowDefContext ctx) {
      PostProcessor$.MODULE$.enterWindowDef(ctx);
   }

   public static void exitWindowRef(final SqlBaseParser.WindowRefContext ctx) {
      PostProcessor$.MODULE$.exitWindowRef(ctx);
   }

   public static void enterWindowRef(final SqlBaseParser.WindowRefContext ctx) {
      PostProcessor$.MODULE$.enterWindowRef(ctx);
   }

   public static void exitNamedWindow(final SqlBaseParser.NamedWindowContext ctx) {
      PostProcessor$.MODULE$.exitNamedWindow(ctx);
   }

   public static void enterNamedWindow(final SqlBaseParser.NamedWindowContext ctx) {
      PostProcessor$.MODULE$.enterNamedWindow(ctx);
   }

   public static void exitWindowClause(final SqlBaseParser.WindowClauseContext ctx) {
      PostProcessor$.MODULE$.exitWindowClause(ctx);
   }

   public static void enterWindowClause(final SqlBaseParser.WindowClauseContext ctx) {
      PostProcessor$.MODULE$.enterWindowClause(ctx);
   }

   public static void exitWhenClause(final SqlBaseParser.WhenClauseContext ctx) {
      PostProcessor$.MODULE$.exitWhenClause(ctx);
   }

   public static void enterWhenClause(final SqlBaseParser.WhenClauseContext ctx) {
      PostProcessor$.MODULE$.enterWhenClause(ctx);
   }

   public static void exitRightsClause(final SqlBaseParser.RightsClauseContext ctx) {
      PostProcessor$.MODULE$.exitRightsClause(ctx);
   }

   public static void enterRightsClause(final SqlBaseParser.RightsClauseContext ctx) {
      PostProcessor$.MODULE$.enterRightsClause(ctx);
   }

   public static void exitNullCall(final SqlBaseParser.NullCallContext ctx) {
      PostProcessor$.MODULE$.exitNullCall(ctx);
   }

   public static void enterNullCall(final SqlBaseParser.NullCallContext ctx) {
      PostProcessor$.MODULE$.enterNullCall(ctx);
   }

   public static void exitSqlDataAccess(final SqlBaseParser.SqlDataAccessContext ctx) {
      PostProcessor$.MODULE$.exitSqlDataAccess(ctx);
   }

   public static void enterSqlDataAccess(final SqlBaseParser.SqlDataAccessContext ctx) {
      PostProcessor$.MODULE$.enterSqlDataAccess(ctx);
   }

   public static void exitDeterministic(final SqlBaseParser.DeterministicContext ctx) {
      PostProcessor$.MODULE$.exitDeterministic(ctx);
   }

   public static void enterDeterministic(final SqlBaseParser.DeterministicContext ctx) {
      PostProcessor$.MODULE$.enterDeterministic(ctx);
   }

   public static void exitSpecificName(final SqlBaseParser.SpecificNameContext ctx) {
      PostProcessor$.MODULE$.exitSpecificName(ctx);
   }

   public static void enterSpecificName(final SqlBaseParser.SpecificNameContext ctx) {
      PostProcessor$.MODULE$.enterSpecificName(ctx);
   }

   public static void exitRoutineLanguage(final SqlBaseParser.RoutineLanguageContext ctx) {
      PostProcessor$.MODULE$.exitRoutineLanguage(ctx);
   }

   public static void enterRoutineLanguage(final SqlBaseParser.RoutineLanguageContext ctx) {
      PostProcessor$.MODULE$.enterRoutineLanguage(ctx);
   }

   public static void exitRoutineCharacteristics(final SqlBaseParser.RoutineCharacteristicsContext ctx) {
      PostProcessor$.MODULE$.exitRoutineCharacteristics(ctx);
   }

   public static void enterRoutineCharacteristics(final SqlBaseParser.RoutineCharacteristicsContext ctx) {
      PostProcessor$.MODULE$.enterRoutineCharacteristics(ctx);
   }

   public static void exitComplexColType(final SqlBaseParser.ComplexColTypeContext ctx) {
      PostProcessor$.MODULE$.exitComplexColType(ctx);
   }

   public static void enterComplexColType(final SqlBaseParser.ComplexColTypeContext ctx) {
      PostProcessor$.MODULE$.enterComplexColType(ctx);
   }

   public static void exitComplexColTypeList(final SqlBaseParser.ComplexColTypeListContext ctx) {
      PostProcessor$.MODULE$.exitComplexColTypeList(ctx);
   }

   public static void enterComplexColTypeList(final SqlBaseParser.ComplexColTypeListContext ctx) {
      PostProcessor$.MODULE$.enterComplexColTypeList(ctx);
   }

   public static void exitSequenceGeneratorStartOrStep(final SqlBaseParser.SequenceGeneratorStartOrStepContext ctx) {
      PostProcessor$.MODULE$.exitSequenceGeneratorStartOrStep(ctx);
   }

   public static void enterSequenceGeneratorStartOrStep(final SqlBaseParser.SequenceGeneratorStartOrStepContext ctx) {
      PostProcessor$.MODULE$.enterSequenceGeneratorStartOrStep(ctx);
   }

   public static void exitSequenceGeneratorOption(final SqlBaseParser.SequenceGeneratorOptionContext ctx) {
      PostProcessor$.MODULE$.exitSequenceGeneratorOption(ctx);
   }

   public static void enterSequenceGeneratorOption(final SqlBaseParser.SequenceGeneratorOptionContext ctx) {
      PostProcessor$.MODULE$.enterSequenceGeneratorOption(ctx);
   }

   public static void exitIdentityColSpec(final SqlBaseParser.IdentityColSpecContext ctx) {
      PostProcessor$.MODULE$.exitIdentityColSpec(ctx);
   }

   public static void enterIdentityColSpec(final SqlBaseParser.IdentityColSpecContext ctx) {
      PostProcessor$.MODULE$.enterIdentityColSpec(ctx);
   }

   public static void exitIdentityColumn(final SqlBaseParser.IdentityColumnContext ctx) {
      PostProcessor$.MODULE$.exitIdentityColumn(ctx);
   }

   public static void enterIdentityColumn(final SqlBaseParser.IdentityColumnContext ctx) {
      PostProcessor$.MODULE$.enterIdentityColumn(ctx);
   }

   public static void exitGeneratedColumn(final SqlBaseParser.GeneratedColumnContext ctx) {
      PostProcessor$.MODULE$.exitGeneratedColumn(ctx);
   }

   public static void enterGeneratedColumn(final SqlBaseParser.GeneratedColumnContext ctx) {
      PostProcessor$.MODULE$.enterGeneratedColumn(ctx);
   }

   public static void exitColDefinitionOption(final SqlBaseParser.ColDefinitionOptionContext ctx) {
      PostProcessor$.MODULE$.exitColDefinitionOption(ctx);
   }

   public static void enterColDefinitionOption(final SqlBaseParser.ColDefinitionOptionContext ctx) {
      PostProcessor$.MODULE$.enterColDefinitionOption(ctx);
   }

   public static void exitColDefinition(final SqlBaseParser.ColDefinitionContext ctx) {
      PostProcessor$.MODULE$.exitColDefinition(ctx);
   }

   public static void enterColDefinition(final SqlBaseParser.ColDefinitionContext ctx) {
      PostProcessor$.MODULE$.enterColDefinition(ctx);
   }

   public static void exitColDefinitionList(final SqlBaseParser.ColDefinitionListContext ctx) {
      PostProcessor$.MODULE$.exitColDefinitionList(ctx);
   }

   public static void enterColDefinitionList(final SqlBaseParser.ColDefinitionListContext ctx) {
      PostProcessor$.MODULE$.enterColDefinitionList(ctx);
   }

   public static void exitColType(final SqlBaseParser.ColTypeContext ctx) {
      PostProcessor$.MODULE$.exitColType(ctx);
   }

   public static void enterColType(final SqlBaseParser.ColTypeContext ctx) {
      PostProcessor$.MODULE$.enterColType(ctx);
   }

   public static void exitColTypeList(final SqlBaseParser.ColTypeListContext ctx) {
      PostProcessor$.MODULE$.exitColTypeList(ctx);
   }

   public static void enterColTypeList(final SqlBaseParser.ColTypeListContext ctx) {
      PostProcessor$.MODULE$.enterColTypeList(ctx);
   }

   public static void exitVariableDefaultExpression(final SqlBaseParser.VariableDefaultExpressionContext ctx) {
      PostProcessor$.MODULE$.exitVariableDefaultExpression(ctx);
   }

   public static void enterVariableDefaultExpression(final SqlBaseParser.VariableDefaultExpressionContext ctx) {
      PostProcessor$.MODULE$.enterVariableDefaultExpression(ctx);
   }

   public static void exitDefaultExpression(final SqlBaseParser.DefaultExpressionContext ctx) {
      PostProcessor$.MODULE$.exitDefaultExpression(ctx);
   }

   public static void enterDefaultExpression(final SqlBaseParser.DefaultExpressionContext ctx) {
      PostProcessor$.MODULE$.enterDefaultExpression(ctx);
   }

   public static void exitColDefinitionDescriptorWithPosition(final SqlBaseParser.ColDefinitionDescriptorWithPositionContext ctx) {
      PostProcessor$.MODULE$.exitColDefinitionDescriptorWithPosition(ctx);
   }

   public static void enterColDefinitionDescriptorWithPosition(final SqlBaseParser.ColDefinitionDescriptorWithPositionContext ctx) {
      PostProcessor$.MODULE$.enterColDefinitionDescriptorWithPosition(ctx);
   }

   public static void exitQualifiedColTypeWithPosition(final SqlBaseParser.QualifiedColTypeWithPositionContext ctx) {
      PostProcessor$.MODULE$.exitQualifiedColTypeWithPosition(ctx);
   }

   public static void enterQualifiedColTypeWithPosition(final SqlBaseParser.QualifiedColTypeWithPositionContext ctx) {
      PostProcessor$.MODULE$.enterQualifiedColTypeWithPosition(ctx);
   }

   public static void exitQualifiedColTypeWithPositionList(final SqlBaseParser.QualifiedColTypeWithPositionListContext ctx) {
      PostProcessor$.MODULE$.exitQualifiedColTypeWithPositionList(ctx);
   }

   public static void enterQualifiedColTypeWithPositionList(final SqlBaseParser.QualifiedColTypeWithPositionListContext ctx) {
      PostProcessor$.MODULE$.enterQualifiedColTypeWithPositionList(ctx);
   }

   public static void exitPrimitiveDataType(final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      PostProcessor$.MODULE$.exitPrimitiveDataType(ctx);
   }

   public static void enterPrimitiveDataType(final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      PostProcessor$.MODULE$.enterPrimitiveDataType(ctx);
   }

   public static void exitDayTimeIntervalDataType(final SqlBaseParser.DayTimeIntervalDataTypeContext ctx) {
      PostProcessor$.MODULE$.exitDayTimeIntervalDataType(ctx);
   }

   public static void enterDayTimeIntervalDataType(final SqlBaseParser.DayTimeIntervalDataTypeContext ctx) {
      PostProcessor$.MODULE$.enterDayTimeIntervalDataType(ctx);
   }

   public static void exitYearMonthIntervalDataType(final SqlBaseParser.YearMonthIntervalDataTypeContext ctx) {
      PostProcessor$.MODULE$.exitYearMonthIntervalDataType(ctx);
   }

   public static void enterYearMonthIntervalDataType(final SqlBaseParser.YearMonthIntervalDataTypeContext ctx) {
      PostProcessor$.MODULE$.enterYearMonthIntervalDataType(ctx);
   }

   public static void exitComplexDataType(final SqlBaseParser.ComplexDataTypeContext ctx) {
      PostProcessor$.MODULE$.exitComplexDataType(ctx);
   }

   public static void enterComplexDataType(final SqlBaseParser.ComplexDataTypeContext ctx) {
      PostProcessor$.MODULE$.enterComplexDataType(ctx);
   }

   public static void exitType(final SqlBaseParser.TypeContext ctx) {
      PostProcessor$.MODULE$.exitType(ctx);
   }

   public static void enterType(final SqlBaseParser.TypeContext ctx) {
      PostProcessor$.MODULE$.enterType(ctx);
   }

   public static void exitCollateClause(final SqlBaseParser.CollateClauseContext ctx) {
      PostProcessor$.MODULE$.exitCollateClause(ctx);
   }

   public static void enterCollateClause(final SqlBaseParser.CollateClauseContext ctx) {
      PostProcessor$.MODULE$.enterCollateClause(ctx);
   }

   public static void exitCollationSpec(final SqlBaseParser.CollationSpecContext ctx) {
      PostProcessor$.MODULE$.exitCollationSpec(ctx);
   }

   public static void enterCollationSpec(final SqlBaseParser.CollationSpecContext ctx) {
      PostProcessor$.MODULE$.enterCollationSpec(ctx);
   }

   public static void exitColPosition(final SqlBaseParser.ColPositionContext ctx) {
      PostProcessor$.MODULE$.exitColPosition(ctx);
   }

   public static void enterColPosition(final SqlBaseParser.ColPositionContext ctx) {
      PostProcessor$.MODULE$.enterColPosition(ctx);
   }

   public static void exitUnitInUnitToUnit(final SqlBaseParser.UnitInUnitToUnitContext ctx) {
      PostProcessor$.MODULE$.exitUnitInUnitToUnit(ctx);
   }

   public static void enterUnitInUnitToUnit(final SqlBaseParser.UnitInUnitToUnitContext ctx) {
      PostProcessor$.MODULE$.enterUnitInUnitToUnit(ctx);
   }

   public static void exitUnitInMultiUnits(final SqlBaseParser.UnitInMultiUnitsContext ctx) {
      PostProcessor$.MODULE$.exitUnitInMultiUnits(ctx);
   }

   public static void enterUnitInMultiUnits(final SqlBaseParser.UnitInMultiUnitsContext ctx) {
      PostProcessor$.MODULE$.enterUnitInMultiUnits(ctx);
   }

   public static void exitIntervalValue(final SqlBaseParser.IntervalValueContext ctx) {
      PostProcessor$.MODULE$.exitIntervalValue(ctx);
   }

   public static void enterIntervalValue(final SqlBaseParser.IntervalValueContext ctx) {
      PostProcessor$.MODULE$.enterIntervalValue(ctx);
   }

   public static void exitUnitToUnitInterval(final SqlBaseParser.UnitToUnitIntervalContext ctx) {
      PostProcessor$.MODULE$.exitUnitToUnitInterval(ctx);
   }

   public static void enterUnitToUnitInterval(final SqlBaseParser.UnitToUnitIntervalContext ctx) {
      PostProcessor$.MODULE$.enterUnitToUnitInterval(ctx);
   }

   public static void exitErrorCapturingUnitToUnitInterval(final SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext ctx) {
      PostProcessor$.MODULE$.exitErrorCapturingUnitToUnitInterval(ctx);
   }

   public static void enterErrorCapturingUnitToUnitInterval(final SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext ctx) {
      PostProcessor$.MODULE$.enterErrorCapturingUnitToUnitInterval(ctx);
   }

   public static void exitMultiUnitsInterval(final SqlBaseParser.MultiUnitsIntervalContext ctx) {
      PostProcessor$.MODULE$.exitMultiUnitsInterval(ctx);
   }

   public static void enterMultiUnitsInterval(final SqlBaseParser.MultiUnitsIntervalContext ctx) {
      PostProcessor$.MODULE$.enterMultiUnitsInterval(ctx);
   }

   public static void exitErrorCapturingMultiUnitsInterval(final SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext ctx) {
      PostProcessor$.MODULE$.exitErrorCapturingMultiUnitsInterval(ctx);
   }

   public static void enterErrorCapturingMultiUnitsInterval(final SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext ctx) {
      PostProcessor$.MODULE$.enterErrorCapturingMultiUnitsInterval(ctx);
   }

   public static void exitInterval(final SqlBaseParser.IntervalContext ctx) {
      PostProcessor$.MODULE$.exitInterval(ctx);
   }

   public static void enterInterval(final SqlBaseParser.IntervalContext ctx) {
      PostProcessor$.MODULE$.enterInterval(ctx);
   }

   public static void exitBooleanValue(final SqlBaseParser.BooleanValueContext ctx) {
      PostProcessor$.MODULE$.exitBooleanValue(ctx);
   }

   public static void enterBooleanValue(final SqlBaseParser.BooleanValueContext ctx) {
      PostProcessor$.MODULE$.enterBooleanValue(ctx);
   }

   public static void exitPredicateOperator(final SqlBaseParser.PredicateOperatorContext ctx) {
      PostProcessor$.MODULE$.exitPredicateOperator(ctx);
   }

   public static void enterPredicateOperator(final SqlBaseParser.PredicateOperatorContext ctx) {
      PostProcessor$.MODULE$.enterPredicateOperator(ctx);
   }

   public static void exitArithmeticOperator(final SqlBaseParser.ArithmeticOperatorContext ctx) {
      PostProcessor$.MODULE$.exitArithmeticOperator(ctx);
   }

   public static void enterArithmeticOperator(final SqlBaseParser.ArithmeticOperatorContext ctx) {
      PostProcessor$.MODULE$.enterArithmeticOperator(ctx);
   }

   public static void exitComparisonOperator(final SqlBaseParser.ComparisonOperatorContext ctx) {
      PostProcessor$.MODULE$.exitComparisonOperator(ctx);
   }

   public static void enterComparisonOperator(final SqlBaseParser.ComparisonOperatorContext ctx) {
      PostProcessor$.MODULE$.enterComparisonOperator(ctx);
   }

   public static void exitStringLiteral(final SqlBaseParser.StringLiteralContext ctx) {
      PostProcessor$.MODULE$.exitStringLiteral(ctx);
   }

   public static void enterStringLiteral(final SqlBaseParser.StringLiteralContext ctx) {
      PostProcessor$.MODULE$.enterStringLiteral(ctx);
   }

   public static void exitBooleanLiteral(final SqlBaseParser.BooleanLiteralContext ctx) {
      PostProcessor$.MODULE$.exitBooleanLiteral(ctx);
   }

   public static void enterBooleanLiteral(final SqlBaseParser.BooleanLiteralContext ctx) {
      PostProcessor$.MODULE$.enterBooleanLiteral(ctx);
   }

   public static void exitNumericLiteral(final SqlBaseParser.NumericLiteralContext ctx) {
      PostProcessor$.MODULE$.exitNumericLiteral(ctx);
   }

   public static void enterNumericLiteral(final SqlBaseParser.NumericLiteralContext ctx) {
      PostProcessor$.MODULE$.enterNumericLiteral(ctx);
   }

   public static void exitTypeConstructor(final SqlBaseParser.TypeConstructorContext ctx) {
      PostProcessor$.MODULE$.exitTypeConstructor(ctx);
   }

   public static void enterTypeConstructor(final SqlBaseParser.TypeConstructorContext ctx) {
      PostProcessor$.MODULE$.enterTypeConstructor(ctx);
   }

   public static void exitIntervalLiteral(final SqlBaseParser.IntervalLiteralContext ctx) {
      PostProcessor$.MODULE$.exitIntervalLiteral(ctx);
   }

   public static void enterIntervalLiteral(final SqlBaseParser.IntervalLiteralContext ctx) {
      PostProcessor$.MODULE$.enterIntervalLiteral(ctx);
   }

   public static void exitNamedParameterLiteral(final SqlBaseParser.NamedParameterLiteralContext ctx) {
      PostProcessor$.MODULE$.exitNamedParameterLiteral(ctx);
   }

   public static void enterNamedParameterLiteral(final SqlBaseParser.NamedParameterLiteralContext ctx) {
      PostProcessor$.MODULE$.enterNamedParameterLiteral(ctx);
   }

   public static void exitPosParameterLiteral(final SqlBaseParser.PosParameterLiteralContext ctx) {
      PostProcessor$.MODULE$.exitPosParameterLiteral(ctx);
   }

   public static void enterPosParameterLiteral(final SqlBaseParser.PosParameterLiteralContext ctx) {
      PostProcessor$.MODULE$.enterPosParameterLiteral(ctx);
   }

   public static void exitNullLiteral(final SqlBaseParser.NullLiteralContext ctx) {
      PostProcessor$.MODULE$.exitNullLiteral(ctx);
   }

   public static void enterNullLiteral(final SqlBaseParser.NullLiteralContext ctx) {
      PostProcessor$.MODULE$.enterNullLiteral(ctx);
   }

   public static void exitLiteralType(final SqlBaseParser.LiteralTypeContext ctx) {
      PostProcessor$.MODULE$.exitLiteralType(ctx);
   }

   public static void enterLiteralType(final SqlBaseParser.LiteralTypeContext ctx) {
      PostProcessor$.MODULE$.enterLiteralType(ctx);
   }

   public static void exitFirst(final SqlBaseParser.FirstContext ctx) {
      PostProcessor$.MODULE$.exitFirst(ctx);
   }

   public static void enterFirst(final SqlBaseParser.FirstContext ctx) {
      PostProcessor$.MODULE$.enterFirst(ctx);
   }

   public static void exitPosition(final SqlBaseParser.PositionContext ctx) {
      PostProcessor$.MODULE$.exitPosition(ctx);
   }

   public static void enterPosition(final SqlBaseParser.PositionContext ctx) {
      PostProcessor$.MODULE$.enterPosition(ctx);
   }

   public static void exitSearchedCase(final SqlBaseParser.SearchedCaseContext ctx) {
      PostProcessor$.MODULE$.exitSearchedCase(ctx);
   }

   public static void enterSearchedCase(final SqlBaseParser.SearchedCaseContext ctx) {
      PostProcessor$.MODULE$.enterSearchedCase(ctx);
   }

   public static void exitFunctionCall(final SqlBaseParser.FunctionCallContext ctx) {
      PostProcessor$.MODULE$.exitFunctionCall(ctx);
   }

   public static void enterFunctionCall(final SqlBaseParser.FunctionCallContext ctx) {
      PostProcessor$.MODULE$.enterFunctionCall(ctx);
   }

   public static void exitExtract(final SqlBaseParser.ExtractContext ctx) {
      PostProcessor$.MODULE$.exitExtract(ctx);
   }

   public static void enterExtract(final SqlBaseParser.ExtractContext ctx) {
      PostProcessor$.MODULE$.enterExtract(ctx);
   }

   public static void exitConstantDefault(final SqlBaseParser.ConstantDefaultContext ctx) {
      PostProcessor$.MODULE$.exitConstantDefault(ctx);
   }

   public static void enterConstantDefault(final SqlBaseParser.ConstantDefaultContext ctx) {
      PostProcessor$.MODULE$.enterConstantDefault(ctx);
   }

   public static void exitCollate(final SqlBaseParser.CollateContext ctx) {
      PostProcessor$.MODULE$.exitCollate(ctx);
   }

   public static void enterCollate(final SqlBaseParser.CollateContext ctx) {
      PostProcessor$.MODULE$.enterCollate(ctx);
   }

   public static void exitSubqueryExpression(final SqlBaseParser.SubqueryExpressionContext ctx) {
      PostProcessor$.MODULE$.exitSubqueryExpression(ctx);
   }

   public static void enterSubqueryExpression(final SqlBaseParser.SubqueryExpressionContext ctx) {
      PostProcessor$.MODULE$.enterSubqueryExpression(ctx);
   }

   public static void exitTimestampdiff(final SqlBaseParser.TimestampdiffContext ctx) {
      PostProcessor$.MODULE$.exitTimestampdiff(ctx);
   }

   public static void enterTimestampdiff(final SqlBaseParser.TimestampdiffContext ctx) {
      PostProcessor$.MODULE$.enterTimestampdiff(ctx);
   }

   public static void exitSubscript(final SqlBaseParser.SubscriptContext ctx) {
      PostProcessor$.MODULE$.exitSubscript(ctx);
   }

   public static void enterSubscript(final SqlBaseParser.SubscriptContext ctx) {
      PostProcessor$.MODULE$.enterSubscript(ctx);
   }

   public static void exitOverlay(final SqlBaseParser.OverlayContext ctx) {
      PostProcessor$.MODULE$.exitOverlay(ctx);
   }

   public static void enterOverlay(final SqlBaseParser.OverlayContext ctx) {
      PostProcessor$.MODULE$.enterOverlay(ctx);
   }

   public static void exitStar(final SqlBaseParser.StarContext ctx) {
      PostProcessor$.MODULE$.exitStar(ctx);
   }

   public static void enterStar(final SqlBaseParser.StarContext ctx) {
      PostProcessor$.MODULE$.enterStar(ctx);
   }

   public static void exitLast(final SqlBaseParser.LastContext ctx) {
      PostProcessor$.MODULE$.exitLast(ctx);
   }

   public static void enterLast(final SqlBaseParser.LastContext ctx) {
      PostProcessor$.MODULE$.enterLast(ctx);
   }

   public static void exitRowConstructor(final SqlBaseParser.RowConstructorContext ctx) {
      PostProcessor$.MODULE$.exitRowConstructor(ctx);
   }

   public static void enterRowConstructor(final SqlBaseParser.RowConstructorContext ctx) {
      PostProcessor$.MODULE$.enterRowConstructor(ctx);
   }

   public static void exitColumnReference(final SqlBaseParser.ColumnReferenceContext ctx) {
      PostProcessor$.MODULE$.exitColumnReference(ctx);
   }

   public static void enterColumnReference(final SqlBaseParser.ColumnReferenceContext ctx) {
      PostProcessor$.MODULE$.enterColumnReference(ctx);
   }

   public static void exitCurrentLike(final SqlBaseParser.CurrentLikeContext ctx) {
      PostProcessor$.MODULE$.exitCurrentLike(ctx);
   }

   public static void enterCurrentLike(final SqlBaseParser.CurrentLikeContext ctx) {
      PostProcessor$.MODULE$.enterCurrentLike(ctx);
   }

   public static void exitSimpleCase(final SqlBaseParser.SimpleCaseContext ctx) {
      PostProcessor$.MODULE$.exitSimpleCase(ctx);
   }

   public static void enterSimpleCase(final SqlBaseParser.SimpleCaseContext ctx) {
      PostProcessor$.MODULE$.enterSimpleCase(ctx);
   }

   public static void exitTrim(final SqlBaseParser.TrimContext ctx) {
      PostProcessor$.MODULE$.exitTrim(ctx);
   }

   public static void enterTrim(final SqlBaseParser.TrimContext ctx) {
      PostProcessor$.MODULE$.enterTrim(ctx);
   }

   public static void exitAny_value(final SqlBaseParser.Any_valueContext ctx) {
      PostProcessor$.MODULE$.exitAny_value(ctx);
   }

   public static void enterAny_value(final SqlBaseParser.Any_valueContext ctx) {
      PostProcessor$.MODULE$.enterAny_value(ctx);
   }

   public static void exitParenthesizedExpression(final SqlBaseParser.ParenthesizedExpressionContext ctx) {
      PostProcessor$.MODULE$.exitParenthesizedExpression(ctx);
   }

   public static void enterParenthesizedExpression(final SqlBaseParser.ParenthesizedExpressionContext ctx) {
      PostProcessor$.MODULE$.enterParenthesizedExpression(ctx);
   }

   public static void exitLambda(final SqlBaseParser.LambdaContext ctx) {
      PostProcessor$.MODULE$.exitLambda(ctx);
   }

   public static void enterLambda(final SqlBaseParser.LambdaContext ctx) {
      PostProcessor$.MODULE$.enterLambda(ctx);
   }

   public static void exitCast(final SqlBaseParser.CastContext ctx) {
      PostProcessor$.MODULE$.exitCast(ctx);
   }

   public static void enterCast(final SqlBaseParser.CastContext ctx) {
      PostProcessor$.MODULE$.enterCast(ctx);
   }

   public static void exitSubstring(final SqlBaseParser.SubstringContext ctx) {
      PostProcessor$.MODULE$.exitSubstring(ctx);
   }

   public static void enterSubstring(final SqlBaseParser.SubstringContext ctx) {
      PostProcessor$.MODULE$.enterSubstring(ctx);
   }

   public static void exitTimestampadd(final SqlBaseParser.TimestampaddContext ctx) {
      PostProcessor$.MODULE$.exitTimestampadd(ctx);
   }

   public static void enterTimestampadd(final SqlBaseParser.TimestampaddContext ctx) {
      PostProcessor$.MODULE$.enterTimestampadd(ctx);
   }

   public static void exitCastByColon(final SqlBaseParser.CastByColonContext ctx) {
      PostProcessor$.MODULE$.exitCastByColon(ctx);
   }

   public static void enterCastByColon(final SqlBaseParser.CastByColonContext ctx) {
      PostProcessor$.MODULE$.enterCastByColon(ctx);
   }

   public static void exitDereference(final SqlBaseParser.DereferenceContext ctx) {
      PostProcessor$.MODULE$.exitDereference(ctx);
   }

   public static void enterDereference(final SqlBaseParser.DereferenceContext ctx) {
      PostProcessor$.MODULE$.enterDereference(ctx);
   }

   public static void exitStruct(final SqlBaseParser.StructContext ctx) {
      PostProcessor$.MODULE$.exitStruct(ctx);
   }

   public static void enterStruct(final SqlBaseParser.StructContext ctx) {
      PostProcessor$.MODULE$.enterStruct(ctx);
   }

   public static void exitDatetimeUnit(final SqlBaseParser.DatetimeUnitContext ctx) {
      PostProcessor$.MODULE$.exitDatetimeUnit(ctx);
   }

   public static void enterDatetimeUnit(final SqlBaseParser.DatetimeUnitContext ctx) {
      PostProcessor$.MODULE$.enterDatetimeUnit(ctx);
   }

   public static void exitShiftOperator(final SqlBaseParser.ShiftOperatorContext ctx) {
      PostProcessor$.MODULE$.exitShiftOperator(ctx);
   }

   public static void enterShiftOperator(final SqlBaseParser.ShiftOperatorContext ctx) {
      PostProcessor$.MODULE$.enterShiftOperator(ctx);
   }

   public static void exitArithmeticUnary(final SqlBaseParser.ArithmeticUnaryContext ctx) {
      PostProcessor$.MODULE$.exitArithmeticUnary(ctx);
   }

   public static void enterArithmeticUnary(final SqlBaseParser.ArithmeticUnaryContext ctx) {
      PostProcessor$.MODULE$.enterArithmeticUnary(ctx);
   }

   public static void exitArithmeticBinary(final SqlBaseParser.ArithmeticBinaryContext ctx) {
      PostProcessor$.MODULE$.exitArithmeticBinary(ctx);
   }

   public static void enterArithmeticBinary(final SqlBaseParser.ArithmeticBinaryContext ctx) {
      PostProcessor$.MODULE$.enterArithmeticBinary(ctx);
   }

   public static void exitShiftExpression(final SqlBaseParser.ShiftExpressionContext ctx) {
      PostProcessor$.MODULE$.exitShiftExpression(ctx);
   }

   public static void enterShiftExpression(final SqlBaseParser.ShiftExpressionContext ctx) {
      PostProcessor$.MODULE$.enterShiftExpression(ctx);
   }

   public static void exitComparison(final SqlBaseParser.ComparisonContext ctx) {
      PostProcessor$.MODULE$.exitComparison(ctx);
   }

   public static void enterComparison(final SqlBaseParser.ComparisonContext ctx) {
      PostProcessor$.MODULE$.enterComparison(ctx);
   }

   public static void exitValueExpressionDefault(final SqlBaseParser.ValueExpressionDefaultContext ctx) {
      PostProcessor$.MODULE$.exitValueExpressionDefault(ctx);
   }

   public static void enterValueExpressionDefault(final SqlBaseParser.ValueExpressionDefaultContext ctx) {
      PostProcessor$.MODULE$.enterValueExpressionDefault(ctx);
   }

   public static void exitErrorCapturingNot(final SqlBaseParser.ErrorCapturingNotContext ctx) {
      PostProcessor$.MODULE$.exitErrorCapturingNot(ctx);
   }

   public static void enterErrorCapturingNot(final SqlBaseParser.ErrorCapturingNotContext ctx) {
      PostProcessor$.MODULE$.enterErrorCapturingNot(ctx);
   }

   public static void exitPredicate(final SqlBaseParser.PredicateContext ctx) {
      PostProcessor$.MODULE$.exitPredicate(ctx);
   }

   public static void enterPredicate(final SqlBaseParser.PredicateContext ctx) {
      PostProcessor$.MODULE$.enterPredicate(ctx);
   }

   public static void exitLogicalBinary(final SqlBaseParser.LogicalBinaryContext ctx) {
      PostProcessor$.MODULE$.exitLogicalBinary(ctx);
   }

   public static void enterLogicalBinary(final SqlBaseParser.LogicalBinaryContext ctx) {
      PostProcessor$.MODULE$.enterLogicalBinary(ctx);
   }

   public static void exitExists(final SqlBaseParser.ExistsContext ctx) {
      PostProcessor$.MODULE$.exitExists(ctx);
   }

   public static void enterExists(final SqlBaseParser.ExistsContext ctx) {
      PostProcessor$.MODULE$.enterExists(ctx);
   }

   public static void exitPredicated(final SqlBaseParser.PredicatedContext ctx) {
      PostProcessor$.MODULE$.exitPredicated(ctx);
   }

   public static void enterPredicated(final SqlBaseParser.PredicatedContext ctx) {
      PostProcessor$.MODULE$.enterPredicated(ctx);
   }

   public static void exitLogicalNot(final SqlBaseParser.LogicalNotContext ctx) {
      PostProcessor$.MODULE$.exitLogicalNot(ctx);
   }

   public static void enterLogicalNot(final SqlBaseParser.LogicalNotContext ctx) {
      PostProcessor$.MODULE$.enterLogicalNot(ctx);
   }

   public static void exitExpressionSeq(final SqlBaseParser.ExpressionSeqContext ctx) {
      PostProcessor$.MODULE$.exitExpressionSeq(ctx);
   }

   public static void enterExpressionSeq(final SqlBaseParser.ExpressionSeqContext ctx) {
      PostProcessor$.MODULE$.enterExpressionSeq(ctx);
   }

   public static void exitFunctionArgument(final SqlBaseParser.FunctionArgumentContext ctx) {
      PostProcessor$.MODULE$.exitFunctionArgument(ctx);
   }

   public static void enterFunctionArgument(final SqlBaseParser.FunctionArgumentContext ctx) {
      PostProcessor$.MODULE$.enterFunctionArgument(ctx);
   }

   public static void exitNamedArgumentExpression(final SqlBaseParser.NamedArgumentExpressionContext ctx) {
      PostProcessor$.MODULE$.exitNamedArgumentExpression(ctx);
   }

   public static void enterNamedArgumentExpression(final SqlBaseParser.NamedArgumentExpressionContext ctx) {
      PostProcessor$.MODULE$.enterNamedArgumentExpression(ctx);
   }

   public static void exitExpression(final SqlBaseParser.ExpressionContext ctx) {
      PostProcessor$.MODULE$.exitExpression(ctx);
   }

   public static void enterExpression(final SqlBaseParser.ExpressionContext ctx) {
      PostProcessor$.MODULE$.enterExpression(ctx);
   }

   public static void exitTransformArgument(final SqlBaseParser.TransformArgumentContext ctx) {
      PostProcessor$.MODULE$.exitTransformArgument(ctx);
   }

   public static void enterTransformArgument(final SqlBaseParser.TransformArgumentContext ctx) {
      PostProcessor$.MODULE$.enterTransformArgument(ctx);
   }

   public static void exitApplyTransform(final SqlBaseParser.ApplyTransformContext ctx) {
      PostProcessor$.MODULE$.exitApplyTransform(ctx);
   }

   public static void enterApplyTransform(final SqlBaseParser.ApplyTransformContext ctx) {
      PostProcessor$.MODULE$.enterApplyTransform(ctx);
   }

   public static void exitIdentityTransform(final SqlBaseParser.IdentityTransformContext ctx) {
      PostProcessor$.MODULE$.exitIdentityTransform(ctx);
   }

   public static void enterIdentityTransform(final SqlBaseParser.IdentityTransformContext ctx) {
      PostProcessor$.MODULE$.enterIdentityTransform(ctx);
   }

   public static void exitPartitionColumn(final SqlBaseParser.PartitionColumnContext ctx) {
      PostProcessor$.MODULE$.exitPartitionColumn(ctx);
   }

   public static void enterPartitionColumn(final SqlBaseParser.PartitionColumnContext ctx) {
      PostProcessor$.MODULE$.enterPartitionColumn(ctx);
   }

   public static void exitPartitionTransform(final SqlBaseParser.PartitionTransformContext ctx) {
      PostProcessor$.MODULE$.exitPartitionTransform(ctx);
   }

   public static void enterPartitionTransform(final SqlBaseParser.PartitionTransformContext ctx) {
      PostProcessor$.MODULE$.enterPartitionTransform(ctx);
   }

   public static void exitPartitionFieldList(final SqlBaseParser.PartitionFieldListContext ctx) {
      PostProcessor$.MODULE$.exitPartitionFieldList(ctx);
   }

   public static void enterPartitionFieldList(final SqlBaseParser.PartitionFieldListContext ctx) {
      PostProcessor$.MODULE$.enterPartitionFieldList(ctx);
   }

   public static void exitNamedExpressionSeq(final SqlBaseParser.NamedExpressionSeqContext ctx) {
      PostProcessor$.MODULE$.exitNamedExpressionSeq(ctx);
   }

   public static void enterNamedExpressionSeq(final SqlBaseParser.NamedExpressionSeqContext ctx) {
      PostProcessor$.MODULE$.enterNamedExpressionSeq(ctx);
   }

   public static void exitNamedExpression(final SqlBaseParser.NamedExpressionContext ctx) {
      PostProcessor$.MODULE$.exitNamedExpression(ctx);
   }

   public static void enterNamedExpression(final SqlBaseParser.NamedExpressionContext ctx) {
      PostProcessor$.MODULE$.enterNamedExpression(ctx);
   }

   public static void exitFunctionIdentifier(final SqlBaseParser.FunctionIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitFunctionIdentifier(ctx);
   }

   public static void enterFunctionIdentifier(final SqlBaseParser.FunctionIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterFunctionIdentifier(ctx);
   }

   public static void exitTableIdentifier(final SqlBaseParser.TableIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitTableIdentifier(ctx);
   }

   public static void enterTableIdentifier(final SqlBaseParser.TableIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterTableIdentifier(ctx);
   }

   public static void exitMultipartIdentifierProperty(final SqlBaseParser.MultipartIdentifierPropertyContext ctx) {
      PostProcessor$.MODULE$.exitMultipartIdentifierProperty(ctx);
   }

   public static void enterMultipartIdentifierProperty(final SqlBaseParser.MultipartIdentifierPropertyContext ctx) {
      PostProcessor$.MODULE$.enterMultipartIdentifierProperty(ctx);
   }

   public static void exitMultipartIdentifierPropertyList(final SqlBaseParser.MultipartIdentifierPropertyListContext ctx) {
      PostProcessor$.MODULE$.exitMultipartIdentifierPropertyList(ctx);
   }

   public static void enterMultipartIdentifierPropertyList(final SqlBaseParser.MultipartIdentifierPropertyListContext ctx) {
      PostProcessor$.MODULE$.enterMultipartIdentifierPropertyList(ctx);
   }

   public static void exitMultipartIdentifier(final SqlBaseParser.MultipartIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitMultipartIdentifier(ctx);
   }

   public static void enterMultipartIdentifier(final SqlBaseParser.MultipartIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterMultipartIdentifier(ctx);
   }

   public static void exitMultipartIdentifierList(final SqlBaseParser.MultipartIdentifierListContext ctx) {
      PostProcessor$.MODULE$.exitMultipartIdentifierList(ctx);
   }

   public static void enterMultipartIdentifierList(final SqlBaseParser.MultipartIdentifierListContext ctx) {
      PostProcessor$.MODULE$.enterMultipartIdentifierList(ctx);
   }

   public static void exitRowFormatDelimited(final SqlBaseParser.RowFormatDelimitedContext ctx) {
      PostProcessor$.MODULE$.exitRowFormatDelimited(ctx);
   }

   public static void enterRowFormatDelimited(final SqlBaseParser.RowFormatDelimitedContext ctx) {
      PostProcessor$.MODULE$.enterRowFormatDelimited(ctx);
   }

   public static void exitRowFormatSerde(final SqlBaseParser.RowFormatSerdeContext ctx) {
      PostProcessor$.MODULE$.exitRowFormatSerde(ctx);
   }

   public static void enterRowFormatSerde(final SqlBaseParser.RowFormatSerdeContext ctx) {
      PostProcessor$.MODULE$.enterRowFormatSerde(ctx);
   }

   public static void exitTableAlias(final SqlBaseParser.TableAliasContext ctx) {
      PostProcessor$.MODULE$.exitTableAlias(ctx);
   }

   public static void enterTableAlias(final SqlBaseParser.TableAliasContext ctx) {
      PostProcessor$.MODULE$.enterTableAlias(ctx);
   }

   public static void exitFunctionTable(final SqlBaseParser.FunctionTableContext ctx) {
      PostProcessor$.MODULE$.exitFunctionTable(ctx);
   }

   public static void enterFunctionTable(final SqlBaseParser.FunctionTableContext ctx) {
      PostProcessor$.MODULE$.enterFunctionTable(ctx);
   }

   public static void exitFunctionTableArgument(final SqlBaseParser.FunctionTableArgumentContext ctx) {
      PostProcessor$.MODULE$.exitFunctionTableArgument(ctx);
   }

   public static void enterFunctionTableArgument(final SqlBaseParser.FunctionTableArgumentContext ctx) {
      PostProcessor$.MODULE$.enterFunctionTableArgument(ctx);
   }

   public static void exitFunctionTableReferenceArgument(final SqlBaseParser.FunctionTableReferenceArgumentContext ctx) {
      PostProcessor$.MODULE$.exitFunctionTableReferenceArgument(ctx);
   }

   public static void enterFunctionTableReferenceArgument(final SqlBaseParser.FunctionTableReferenceArgumentContext ctx) {
      PostProcessor$.MODULE$.enterFunctionTableReferenceArgument(ctx);
   }

   public static void exitFunctionTableNamedArgumentExpression(final SqlBaseParser.FunctionTableNamedArgumentExpressionContext ctx) {
      PostProcessor$.MODULE$.exitFunctionTableNamedArgumentExpression(ctx);
   }

   public static void enterFunctionTableNamedArgumentExpression(final SqlBaseParser.FunctionTableNamedArgumentExpressionContext ctx) {
      PostProcessor$.MODULE$.enterFunctionTableNamedArgumentExpression(ctx);
   }

   public static void exitTableArgumentPartitioning(final SqlBaseParser.TableArgumentPartitioningContext ctx) {
      PostProcessor$.MODULE$.exitTableArgumentPartitioning(ctx);
   }

   public static void enterTableArgumentPartitioning(final SqlBaseParser.TableArgumentPartitioningContext ctx) {
      PostProcessor$.MODULE$.enterTableArgumentPartitioning(ctx);
   }

   public static void exitFunctionTableSubqueryArgument(final SqlBaseParser.FunctionTableSubqueryArgumentContext ctx) {
      PostProcessor$.MODULE$.exitFunctionTableSubqueryArgument(ctx);
   }

   public static void enterFunctionTableSubqueryArgument(final SqlBaseParser.FunctionTableSubqueryArgumentContext ctx) {
      PostProcessor$.MODULE$.enterFunctionTableSubqueryArgument(ctx);
   }

   public static void exitInlineTable(final SqlBaseParser.InlineTableContext ctx) {
      PostProcessor$.MODULE$.exitInlineTable(ctx);
   }

   public static void enterInlineTable(final SqlBaseParser.InlineTableContext ctx) {
      PostProcessor$.MODULE$.enterInlineTable(ctx);
   }

   public static void exitOptionsClause(final SqlBaseParser.OptionsClauseContext ctx) {
      PostProcessor$.MODULE$.exitOptionsClause(ctx);
   }

   public static void enterOptionsClause(final SqlBaseParser.OptionsClauseContext ctx) {
      PostProcessor$.MODULE$.enterOptionsClause(ctx);
   }

   public static void exitTableValuedFunction(final SqlBaseParser.TableValuedFunctionContext ctx) {
      PostProcessor$.MODULE$.exitTableValuedFunction(ctx);
   }

   public static void enterTableValuedFunction(final SqlBaseParser.TableValuedFunctionContext ctx) {
      PostProcessor$.MODULE$.enterTableValuedFunction(ctx);
   }

   public static void exitInlineTableDefault2(final SqlBaseParser.InlineTableDefault2Context ctx) {
      PostProcessor$.MODULE$.exitInlineTableDefault2(ctx);
   }

   public static void enterInlineTableDefault2(final SqlBaseParser.InlineTableDefault2Context ctx) {
      PostProcessor$.MODULE$.enterInlineTableDefault2(ctx);
   }

   public static void exitAliasedRelation(final SqlBaseParser.AliasedRelationContext ctx) {
      PostProcessor$.MODULE$.exitAliasedRelation(ctx);
   }

   public static void enterAliasedRelation(final SqlBaseParser.AliasedRelationContext ctx) {
      PostProcessor$.MODULE$.enterAliasedRelation(ctx);
   }

   public static void exitAliasedQuery(final SqlBaseParser.AliasedQueryContext ctx) {
      PostProcessor$.MODULE$.exitAliasedQuery(ctx);
   }

   public static void enterAliasedQuery(final SqlBaseParser.AliasedQueryContext ctx) {
      PostProcessor$.MODULE$.enterAliasedQuery(ctx);
   }

   public static void exitTableName(final SqlBaseParser.TableNameContext ctx) {
      PostProcessor$.MODULE$.exitTableName(ctx);
   }

   public static void enterTableName(final SqlBaseParser.TableNameContext ctx) {
      PostProcessor$.MODULE$.enterTableName(ctx);
   }

   public static void exitIdentifierComment(final SqlBaseParser.IdentifierCommentContext ctx) {
      PostProcessor$.MODULE$.exitIdentifierComment(ctx);
   }

   public static void enterIdentifierComment(final SqlBaseParser.IdentifierCommentContext ctx) {
      PostProcessor$.MODULE$.enterIdentifierComment(ctx);
   }

   public static void exitIdentifierCommentList(final SqlBaseParser.IdentifierCommentListContext ctx) {
      PostProcessor$.MODULE$.exitIdentifierCommentList(ctx);
   }

   public static void enterIdentifierCommentList(final SqlBaseParser.IdentifierCommentListContext ctx) {
      PostProcessor$.MODULE$.enterIdentifierCommentList(ctx);
   }

   public static void exitOrderedIdentifier(final SqlBaseParser.OrderedIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitOrderedIdentifier(ctx);
   }

   public static void enterOrderedIdentifier(final SqlBaseParser.OrderedIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterOrderedIdentifier(ctx);
   }

   public static void exitOrderedIdentifierList(final SqlBaseParser.OrderedIdentifierListContext ctx) {
      PostProcessor$.MODULE$.exitOrderedIdentifierList(ctx);
   }

   public static void enterOrderedIdentifierList(final SqlBaseParser.OrderedIdentifierListContext ctx) {
      PostProcessor$.MODULE$.enterOrderedIdentifierList(ctx);
   }

   public static void exitIdentifierSeq(final SqlBaseParser.IdentifierSeqContext ctx) {
      PostProcessor$.MODULE$.exitIdentifierSeq(ctx);
   }

   public static void enterIdentifierSeq(final SqlBaseParser.IdentifierSeqContext ctx) {
      PostProcessor$.MODULE$.enterIdentifierSeq(ctx);
   }

   public static void exitIdentifierList(final SqlBaseParser.IdentifierListContext ctx) {
      PostProcessor$.MODULE$.exitIdentifierList(ctx);
   }

   public static void enterIdentifierList(final SqlBaseParser.IdentifierListContext ctx) {
      PostProcessor$.MODULE$.enterIdentifierList(ctx);
   }

   public static void exitSampleByBytes(final SqlBaseParser.SampleByBytesContext ctx) {
      PostProcessor$.MODULE$.exitSampleByBytes(ctx);
   }

   public static void enterSampleByBytes(final SqlBaseParser.SampleByBytesContext ctx) {
      PostProcessor$.MODULE$.enterSampleByBytes(ctx);
   }

   public static void exitSampleByBucket(final SqlBaseParser.SampleByBucketContext ctx) {
      PostProcessor$.MODULE$.exitSampleByBucket(ctx);
   }

   public static void enterSampleByBucket(final SqlBaseParser.SampleByBucketContext ctx) {
      PostProcessor$.MODULE$.enterSampleByBucket(ctx);
   }

   public static void exitSampleByRows(final SqlBaseParser.SampleByRowsContext ctx) {
      PostProcessor$.MODULE$.exitSampleByRows(ctx);
   }

   public static void enterSampleByRows(final SqlBaseParser.SampleByRowsContext ctx) {
      PostProcessor$.MODULE$.enterSampleByRows(ctx);
   }

   public static void exitSampleByPercentile(final SqlBaseParser.SampleByPercentileContext ctx) {
      PostProcessor$.MODULE$.exitSampleByPercentile(ctx);
   }

   public static void enterSampleByPercentile(final SqlBaseParser.SampleByPercentileContext ctx) {
      PostProcessor$.MODULE$.enterSampleByPercentile(ctx);
   }

   public static void exitSample(final SqlBaseParser.SampleContext ctx) {
      PostProcessor$.MODULE$.exitSample(ctx);
   }

   public static void enterSample(final SqlBaseParser.SampleContext ctx) {
      PostProcessor$.MODULE$.enterSample(ctx);
   }

   public static void exitJoinCriteria(final SqlBaseParser.JoinCriteriaContext ctx) {
      PostProcessor$.MODULE$.exitJoinCriteria(ctx);
   }

   public static void enterJoinCriteria(final SqlBaseParser.JoinCriteriaContext ctx) {
      PostProcessor$.MODULE$.enterJoinCriteria(ctx);
   }

   public static void exitJoinType(final SqlBaseParser.JoinTypeContext ctx) {
      PostProcessor$.MODULE$.exitJoinType(ctx);
   }

   public static void enterJoinType(final SqlBaseParser.JoinTypeContext ctx) {
      PostProcessor$.MODULE$.enterJoinType(ctx);
   }

   public static void exitJoinRelation(final SqlBaseParser.JoinRelationContext ctx) {
      PostProcessor$.MODULE$.exitJoinRelation(ctx);
   }

   public static void enterJoinRelation(final SqlBaseParser.JoinRelationContext ctx) {
      PostProcessor$.MODULE$.enterJoinRelation(ctx);
   }

   public static void exitRelationExtension(final SqlBaseParser.RelationExtensionContext ctx) {
      PostProcessor$.MODULE$.exitRelationExtension(ctx);
   }

   public static void enterRelationExtension(final SqlBaseParser.RelationExtensionContext ctx) {
      PostProcessor$.MODULE$.enterRelationExtension(ctx);
   }

   public static void exitRelation(final SqlBaseParser.RelationContext ctx) {
      PostProcessor$.MODULE$.exitRelation(ctx);
   }

   public static void enterRelation(final SqlBaseParser.RelationContext ctx) {
      PostProcessor$.MODULE$.enterRelation(ctx);
   }

   public static void exitSetQuantifier(final SqlBaseParser.SetQuantifierContext ctx) {
      PostProcessor$.MODULE$.exitSetQuantifier(ctx);
   }

   public static void enterSetQuantifier(final SqlBaseParser.SetQuantifierContext ctx) {
      PostProcessor$.MODULE$.enterSetQuantifier(ctx);
   }

   public static void exitLateralView(final SqlBaseParser.LateralViewContext ctx) {
      PostProcessor$.MODULE$.exitLateralView(ctx);
   }

   public static void enterLateralView(final SqlBaseParser.LateralViewContext ctx) {
      PostProcessor$.MODULE$.enterLateralView(ctx);
   }

   public static void exitUnpivotAlias(final SqlBaseParser.UnpivotAliasContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotAlias(ctx);
   }

   public static void enterUnpivotAlias(final SqlBaseParser.UnpivotAliasContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotAlias(ctx);
   }

   public static void exitUnpivotColumn(final SqlBaseParser.UnpivotColumnContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotColumn(ctx);
   }

   public static void enterUnpivotColumn(final SqlBaseParser.UnpivotColumnContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotColumn(ctx);
   }

   public static void exitUnpivotColumnAndAlias(final SqlBaseParser.UnpivotColumnAndAliasContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotColumnAndAlias(ctx);
   }

   public static void enterUnpivotColumnAndAlias(final SqlBaseParser.UnpivotColumnAndAliasContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotColumnAndAlias(ctx);
   }

   public static void exitUnpivotNameColumn(final SqlBaseParser.UnpivotNameColumnContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotNameColumn(ctx);
   }

   public static void enterUnpivotNameColumn(final SqlBaseParser.UnpivotNameColumnContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotNameColumn(ctx);
   }

   public static void exitUnpivotValueColumn(final SqlBaseParser.UnpivotValueColumnContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotValueColumn(ctx);
   }

   public static void enterUnpivotValueColumn(final SqlBaseParser.UnpivotValueColumnContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotValueColumn(ctx);
   }

   public static void exitUnpivotColumnSet(final SqlBaseParser.UnpivotColumnSetContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotColumnSet(ctx);
   }

   public static void enterUnpivotColumnSet(final SqlBaseParser.UnpivotColumnSetContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotColumnSet(ctx);
   }

   public static void exitUnpivotMultiValueColumnClause(final SqlBaseParser.UnpivotMultiValueColumnClauseContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotMultiValueColumnClause(ctx);
   }

   public static void enterUnpivotMultiValueColumnClause(final SqlBaseParser.UnpivotMultiValueColumnClauseContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotMultiValueColumnClause(ctx);
   }

   public static void exitUnpivotSingleValueColumnClause(final SqlBaseParser.UnpivotSingleValueColumnClauseContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotSingleValueColumnClause(ctx);
   }

   public static void enterUnpivotSingleValueColumnClause(final SqlBaseParser.UnpivotSingleValueColumnClauseContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotSingleValueColumnClause(ctx);
   }

   public static void exitUnpivotOperator(final SqlBaseParser.UnpivotOperatorContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotOperator(ctx);
   }

   public static void enterUnpivotOperator(final SqlBaseParser.UnpivotOperatorContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotOperator(ctx);
   }

   public static void exitUnpivotNullClause(final SqlBaseParser.UnpivotNullClauseContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotNullClause(ctx);
   }

   public static void enterUnpivotNullClause(final SqlBaseParser.UnpivotNullClauseContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotNullClause(ctx);
   }

   public static void exitUnpivotClause(final SqlBaseParser.UnpivotClauseContext ctx) {
      PostProcessor$.MODULE$.exitUnpivotClause(ctx);
   }

   public static void enterUnpivotClause(final SqlBaseParser.UnpivotClauseContext ctx) {
      PostProcessor$.MODULE$.enterUnpivotClause(ctx);
   }

   public static void exitPivotValue(final SqlBaseParser.PivotValueContext ctx) {
      PostProcessor$.MODULE$.exitPivotValue(ctx);
   }

   public static void enterPivotValue(final SqlBaseParser.PivotValueContext ctx) {
      PostProcessor$.MODULE$.enterPivotValue(ctx);
   }

   public static void exitPivotColumn(final SqlBaseParser.PivotColumnContext ctx) {
      PostProcessor$.MODULE$.exitPivotColumn(ctx);
   }

   public static void enterPivotColumn(final SqlBaseParser.PivotColumnContext ctx) {
      PostProcessor$.MODULE$.enterPivotColumn(ctx);
   }

   public static void exitPivotClause(final SqlBaseParser.PivotClauseContext ctx) {
      PostProcessor$.MODULE$.exitPivotClause(ctx);
   }

   public static void enterPivotClause(final SqlBaseParser.PivotClauseContext ctx) {
      PostProcessor$.MODULE$.enterPivotClause(ctx);
   }

   public static void exitGroupingSet(final SqlBaseParser.GroupingSetContext ctx) {
      PostProcessor$.MODULE$.exitGroupingSet(ctx);
   }

   public static void enterGroupingSet(final SqlBaseParser.GroupingSetContext ctx) {
      PostProcessor$.MODULE$.enterGroupingSet(ctx);
   }

   public static void exitGroupingElement(final SqlBaseParser.GroupingElementContext ctx) {
      PostProcessor$.MODULE$.exitGroupingElement(ctx);
   }

   public static void enterGroupingElement(final SqlBaseParser.GroupingElementContext ctx) {
      PostProcessor$.MODULE$.enterGroupingElement(ctx);
   }

   public static void exitGroupingAnalytics(final SqlBaseParser.GroupingAnalyticsContext ctx) {
      PostProcessor$.MODULE$.exitGroupingAnalytics(ctx);
   }

   public static void enterGroupingAnalytics(final SqlBaseParser.GroupingAnalyticsContext ctx) {
      PostProcessor$.MODULE$.enterGroupingAnalytics(ctx);
   }

   public static void exitGroupByClause(final SqlBaseParser.GroupByClauseContext ctx) {
      PostProcessor$.MODULE$.exitGroupByClause(ctx);
   }

   public static void enterGroupByClause(final SqlBaseParser.GroupByClauseContext ctx) {
      PostProcessor$.MODULE$.enterGroupByClause(ctx);
   }

   public static void exitAggregationClause(final SqlBaseParser.AggregationClauseContext ctx) {
      PostProcessor$.MODULE$.exitAggregationClause(ctx);
   }

   public static void enterAggregationClause(final SqlBaseParser.AggregationClauseContext ctx) {
      PostProcessor$.MODULE$.enterAggregationClause(ctx);
   }

   public static void exitTemporalClause(final SqlBaseParser.TemporalClauseContext ctx) {
      PostProcessor$.MODULE$.exitTemporalClause(ctx);
   }

   public static void enterTemporalClause(final SqlBaseParser.TemporalClauseContext ctx) {
      PostProcessor$.MODULE$.enterTemporalClause(ctx);
   }

   public static void exitFromClause(final SqlBaseParser.FromClauseContext ctx) {
      PostProcessor$.MODULE$.exitFromClause(ctx);
   }

   public static void enterFromClause(final SqlBaseParser.FromClauseContext ctx) {
      PostProcessor$.MODULE$.enterFromClause(ctx);
   }

   public static void exitHintStatement(final SqlBaseParser.HintStatementContext ctx) {
      PostProcessor$.MODULE$.exitHintStatement(ctx);
   }

   public static void enterHintStatement(final SqlBaseParser.HintStatementContext ctx) {
      PostProcessor$.MODULE$.enterHintStatement(ctx);
   }

   public static void exitHint(final SqlBaseParser.HintContext ctx) {
      PostProcessor$.MODULE$.exitHint(ctx);
   }

   public static void enterHint(final SqlBaseParser.HintContext ctx) {
      PostProcessor$.MODULE$.enterHint(ctx);
   }

   public static void exitHavingClause(final SqlBaseParser.HavingClauseContext ctx) {
      PostProcessor$.MODULE$.exitHavingClause(ctx);
   }

   public static void enterHavingClause(final SqlBaseParser.HavingClauseContext ctx) {
      PostProcessor$.MODULE$.enterHavingClause(ctx);
   }

   public static void exitWhereClause(final SqlBaseParser.WhereClauseContext ctx) {
      PostProcessor$.MODULE$.exitWhereClause(ctx);
   }

   public static void enterWhereClause(final SqlBaseParser.WhereClauseContext ctx) {
      PostProcessor$.MODULE$.enterWhereClause(ctx);
   }

   public static void exitAssignment(final SqlBaseParser.AssignmentContext ctx) {
      PostProcessor$.MODULE$.exitAssignment(ctx);
   }

   public static void enterAssignment(final SqlBaseParser.AssignmentContext ctx) {
      PostProcessor$.MODULE$.enterAssignment(ctx);
   }

   public static void exitAssignmentList(final SqlBaseParser.AssignmentListContext ctx) {
      PostProcessor$.MODULE$.exitAssignmentList(ctx);
   }

   public static void enterAssignmentList(final SqlBaseParser.AssignmentListContext ctx) {
      PostProcessor$.MODULE$.enterAssignmentList(ctx);
   }

   public static void exitExceptClause(final SqlBaseParser.ExceptClauseContext ctx) {
      PostProcessor$.MODULE$.exitExceptClause(ctx);
   }

   public static void enterExceptClause(final SqlBaseParser.ExceptClauseContext ctx) {
      PostProcessor$.MODULE$.enterExceptClause(ctx);
   }

   public static void exitNotMatchedBySourceAction(final SqlBaseParser.NotMatchedBySourceActionContext ctx) {
      PostProcessor$.MODULE$.exitNotMatchedBySourceAction(ctx);
   }

   public static void enterNotMatchedBySourceAction(final SqlBaseParser.NotMatchedBySourceActionContext ctx) {
      PostProcessor$.MODULE$.enterNotMatchedBySourceAction(ctx);
   }

   public static void exitNotMatchedAction(final SqlBaseParser.NotMatchedActionContext ctx) {
      PostProcessor$.MODULE$.exitNotMatchedAction(ctx);
   }

   public static void enterNotMatchedAction(final SqlBaseParser.NotMatchedActionContext ctx) {
      PostProcessor$.MODULE$.enterNotMatchedAction(ctx);
   }

   public static void exitMatchedAction(final SqlBaseParser.MatchedActionContext ctx) {
      PostProcessor$.MODULE$.exitMatchedAction(ctx);
   }

   public static void enterMatchedAction(final SqlBaseParser.MatchedActionContext ctx) {
      PostProcessor$.MODULE$.enterMatchedAction(ctx);
   }

   public static void exitNotMatchedBySourceClause(final SqlBaseParser.NotMatchedBySourceClauseContext ctx) {
      PostProcessor$.MODULE$.exitNotMatchedBySourceClause(ctx);
   }

   public static void enterNotMatchedBySourceClause(final SqlBaseParser.NotMatchedBySourceClauseContext ctx) {
      PostProcessor$.MODULE$.enterNotMatchedBySourceClause(ctx);
   }

   public static void exitNotMatchedClause(final SqlBaseParser.NotMatchedClauseContext ctx) {
      PostProcessor$.MODULE$.exitNotMatchedClause(ctx);
   }

   public static void enterNotMatchedClause(final SqlBaseParser.NotMatchedClauseContext ctx) {
      PostProcessor$.MODULE$.enterNotMatchedClause(ctx);
   }

   public static void exitMatchedClause(final SqlBaseParser.MatchedClauseContext ctx) {
      PostProcessor$.MODULE$.exitMatchedClause(ctx);
   }

   public static void enterMatchedClause(final SqlBaseParser.MatchedClauseContext ctx) {
      PostProcessor$.MODULE$.enterMatchedClause(ctx);
   }

   public static void exitSetClause(final SqlBaseParser.SetClauseContext ctx) {
      PostProcessor$.MODULE$.exitSetClause(ctx);
   }

   public static void enterSetClause(final SqlBaseParser.SetClauseContext ctx) {
      PostProcessor$.MODULE$.enterSetClause(ctx);
   }

   public static void exitSelectClause(final SqlBaseParser.SelectClauseContext ctx) {
      PostProcessor$.MODULE$.exitSelectClause(ctx);
   }

   public static void enterSelectClause(final SqlBaseParser.SelectClauseContext ctx) {
      PostProcessor$.MODULE$.enterSelectClause(ctx);
   }

   public static void exitTransformClause(final SqlBaseParser.TransformClauseContext ctx) {
      PostProcessor$.MODULE$.exitTransformClause(ctx);
   }

   public static void enterTransformClause(final SqlBaseParser.TransformClauseContext ctx) {
      PostProcessor$.MODULE$.enterTransformClause(ctx);
   }

   public static void exitRegularQuerySpecification(final SqlBaseParser.RegularQuerySpecificationContext ctx) {
      PostProcessor$.MODULE$.exitRegularQuerySpecification(ctx);
   }

   public static void enterRegularQuerySpecification(final SqlBaseParser.RegularQuerySpecificationContext ctx) {
      PostProcessor$.MODULE$.enterRegularQuerySpecification(ctx);
   }

   public static void exitTransformQuerySpecification(final SqlBaseParser.TransformQuerySpecificationContext ctx) {
      PostProcessor$.MODULE$.exitTransformQuerySpecification(ctx);
   }

   public static void enterTransformQuerySpecification(final SqlBaseParser.TransformQuerySpecificationContext ctx) {
      PostProcessor$.MODULE$.enterTransformQuerySpecification(ctx);
   }

   public static void exitFromStatementBody(final SqlBaseParser.FromStatementBodyContext ctx) {
      PostProcessor$.MODULE$.exitFromStatementBody(ctx);
   }

   public static void enterFromStatementBody(final SqlBaseParser.FromStatementBodyContext ctx) {
      PostProcessor$.MODULE$.enterFromStatementBody(ctx);
   }

   public static void exitFromStatement(final SqlBaseParser.FromStatementContext ctx) {
      PostProcessor$.MODULE$.exitFromStatement(ctx);
   }

   public static void enterFromStatement(final SqlBaseParser.FromStatementContext ctx) {
      PostProcessor$.MODULE$.enterFromStatement(ctx);
   }

   public static void exitSortItem(final SqlBaseParser.SortItemContext ctx) {
      PostProcessor$.MODULE$.exitSortItem(ctx);
   }

   public static void enterSortItem(final SqlBaseParser.SortItemContext ctx) {
      PostProcessor$.MODULE$.enterSortItem(ctx);
   }

   public static void exitSubquery(final SqlBaseParser.SubqueryContext ctx) {
      PostProcessor$.MODULE$.exitSubquery(ctx);
   }

   public static void enterSubquery(final SqlBaseParser.SubqueryContext ctx) {
      PostProcessor$.MODULE$.enterSubquery(ctx);
   }

   public static void exitInlineTableDefault1(final SqlBaseParser.InlineTableDefault1Context ctx) {
      PostProcessor$.MODULE$.exitInlineTableDefault1(ctx);
   }

   public static void enterInlineTableDefault1(final SqlBaseParser.InlineTableDefault1Context ctx) {
      PostProcessor$.MODULE$.enterInlineTableDefault1(ctx);
   }

   public static void exitTable(final SqlBaseParser.TableContext ctx) {
      PostProcessor$.MODULE$.exitTable(ctx);
   }

   public static void enterTable(final SqlBaseParser.TableContext ctx) {
      PostProcessor$.MODULE$.enterTable(ctx);
   }

   public static void exitFromStmt(final SqlBaseParser.FromStmtContext ctx) {
      PostProcessor$.MODULE$.exitFromStmt(ctx);
   }

   public static void enterFromStmt(final SqlBaseParser.FromStmtContext ctx) {
      PostProcessor$.MODULE$.enterFromStmt(ctx);
   }

   public static void exitQueryPrimaryDefault(final SqlBaseParser.QueryPrimaryDefaultContext ctx) {
      PostProcessor$.MODULE$.exitQueryPrimaryDefault(ctx);
   }

   public static void enterQueryPrimaryDefault(final SqlBaseParser.QueryPrimaryDefaultContext ctx) {
      PostProcessor$.MODULE$.enterQueryPrimaryDefault(ctx);
   }

   public static void exitSetOperation(final SqlBaseParser.SetOperationContext ctx) {
      PostProcessor$.MODULE$.exitSetOperation(ctx);
   }

   public static void enterSetOperation(final SqlBaseParser.SetOperationContext ctx) {
      PostProcessor$.MODULE$.enterSetOperation(ctx);
   }

   public static void exitQueryTermDefault(final SqlBaseParser.QueryTermDefaultContext ctx) {
      PostProcessor$.MODULE$.exitQueryTermDefault(ctx);
   }

   public static void enterQueryTermDefault(final SqlBaseParser.QueryTermDefaultContext ctx) {
      PostProcessor$.MODULE$.enterQueryTermDefault(ctx);
   }

   public static void exitOperatorPipeStatement(final SqlBaseParser.OperatorPipeStatementContext ctx) {
      PostProcessor$.MODULE$.exitOperatorPipeStatement(ctx);
   }

   public static void enterOperatorPipeStatement(final SqlBaseParser.OperatorPipeStatementContext ctx) {
      PostProcessor$.MODULE$.enterOperatorPipeStatement(ctx);
   }

   public static void exitMultiInsertQueryBody(final SqlBaseParser.MultiInsertQueryBodyContext ctx) {
      PostProcessor$.MODULE$.exitMultiInsertQueryBody(ctx);
   }

   public static void enterMultiInsertQueryBody(final SqlBaseParser.MultiInsertQueryBodyContext ctx) {
      PostProcessor$.MODULE$.enterMultiInsertQueryBody(ctx);
   }

   public static void exitQueryOrganization(final SqlBaseParser.QueryOrganizationContext ctx) {
      PostProcessor$.MODULE$.exitQueryOrganization(ctx);
   }

   public static void enterQueryOrganization(final SqlBaseParser.QueryOrganizationContext ctx) {
      PostProcessor$.MODULE$.enterQueryOrganization(ctx);
   }

   public static void exitCatalogIdentifierReference(final SqlBaseParser.CatalogIdentifierReferenceContext ctx) {
      PostProcessor$.MODULE$.exitCatalogIdentifierReference(ctx);
   }

   public static void enterCatalogIdentifierReference(final SqlBaseParser.CatalogIdentifierReferenceContext ctx) {
      PostProcessor$.MODULE$.enterCatalogIdentifierReference(ctx);
   }

   public static void exitIdentifierReference(final SqlBaseParser.IdentifierReferenceContext ctx) {
      PostProcessor$.MODULE$.exitIdentifierReference(ctx);
   }

   public static void enterIdentifierReference(final SqlBaseParser.IdentifierReferenceContext ctx) {
      PostProcessor$.MODULE$.enterIdentifierReference(ctx);
   }

   public static void exitMergeIntoTable(final SqlBaseParser.MergeIntoTableContext ctx) {
      PostProcessor$.MODULE$.exitMergeIntoTable(ctx);
   }

   public static void enterMergeIntoTable(final SqlBaseParser.MergeIntoTableContext ctx) {
      PostProcessor$.MODULE$.enterMergeIntoTable(ctx);
   }

   public static void exitUpdateTable(final SqlBaseParser.UpdateTableContext ctx) {
      PostProcessor$.MODULE$.exitUpdateTable(ctx);
   }

   public static void enterUpdateTable(final SqlBaseParser.UpdateTableContext ctx) {
      PostProcessor$.MODULE$.enterUpdateTable(ctx);
   }

   public static void exitDeleteFromTable(final SqlBaseParser.DeleteFromTableContext ctx) {
      PostProcessor$.MODULE$.exitDeleteFromTable(ctx);
   }

   public static void enterDeleteFromTable(final SqlBaseParser.DeleteFromTableContext ctx) {
      PostProcessor$.MODULE$.enterDeleteFromTable(ctx);
   }

   public static void exitMultiInsertQuery(final SqlBaseParser.MultiInsertQueryContext ctx) {
      PostProcessor$.MODULE$.exitMultiInsertQuery(ctx);
   }

   public static void enterMultiInsertQuery(final SqlBaseParser.MultiInsertQueryContext ctx) {
      PostProcessor$.MODULE$.enterMultiInsertQuery(ctx);
   }

   public static void exitSingleInsertQuery(final SqlBaseParser.SingleInsertQueryContext ctx) {
      PostProcessor$.MODULE$.exitSingleInsertQuery(ctx);
   }

   public static void enterSingleInsertQuery(final SqlBaseParser.SingleInsertQueryContext ctx) {
      PostProcessor$.MODULE$.enterSingleInsertQuery(ctx);
   }

   public static void exitResource(final SqlBaseParser.ResourceContext ctx) {
      PostProcessor$.MODULE$.exitResource(ctx);
   }

   public static void enterResource(final SqlBaseParser.ResourceContext ctx) {
      PostProcessor$.MODULE$.enterResource(ctx);
   }

   public static void exitStorageHandler(final SqlBaseParser.StorageHandlerContext ctx) {
      PostProcessor$.MODULE$.exitStorageHandler(ctx);
   }

   public static void enterStorageHandler(final SqlBaseParser.StorageHandlerContext ctx) {
      PostProcessor$.MODULE$.enterStorageHandler(ctx);
   }

   public static void exitGenericFileFormat(final SqlBaseParser.GenericFileFormatContext ctx) {
      PostProcessor$.MODULE$.exitGenericFileFormat(ctx);
   }

   public static void enterGenericFileFormat(final SqlBaseParser.GenericFileFormatContext ctx) {
      PostProcessor$.MODULE$.enterGenericFileFormat(ctx);
   }

   public static void exitTableFileFormat(final SqlBaseParser.TableFileFormatContext ctx) {
      PostProcessor$.MODULE$.exitTableFileFormat(ctx);
   }

   public static void enterTableFileFormat(final SqlBaseParser.TableFileFormatContext ctx) {
      PostProcessor$.MODULE$.enterTableFileFormat(ctx);
   }

   public static void exitCreateFileFormat(final SqlBaseParser.CreateFileFormatContext ctx) {
      PostProcessor$.MODULE$.exitCreateFileFormat(ctx);
   }

   public static void enterCreateFileFormat(final SqlBaseParser.CreateFileFormatContext ctx) {
      PostProcessor$.MODULE$.enterCreateFileFormat(ctx);
   }

   public static void exitNestedConstantList(final SqlBaseParser.NestedConstantListContext ctx) {
      PostProcessor$.MODULE$.exitNestedConstantList(ctx);
   }

   public static void enterNestedConstantList(final SqlBaseParser.NestedConstantListContext ctx) {
      PostProcessor$.MODULE$.enterNestedConstantList(ctx);
   }

   public static void exitConstantList(final SqlBaseParser.ConstantListContext ctx) {
      PostProcessor$.MODULE$.exitConstantList(ctx);
   }

   public static void enterConstantList(final SqlBaseParser.ConstantListContext ctx) {
      PostProcessor$.MODULE$.enterConstantList(ctx);
   }

   public static void exitExpressionProperty(final SqlBaseParser.ExpressionPropertyContext ctx) {
      PostProcessor$.MODULE$.exitExpressionProperty(ctx);
   }

   public static void enterExpressionProperty(final SqlBaseParser.ExpressionPropertyContext ctx) {
      PostProcessor$.MODULE$.enterExpressionProperty(ctx);
   }

   public static void exitExpressionPropertyList(final SqlBaseParser.ExpressionPropertyListContext ctx) {
      PostProcessor$.MODULE$.exitExpressionPropertyList(ctx);
   }

   public static void enterExpressionPropertyList(final SqlBaseParser.ExpressionPropertyListContext ctx) {
      PostProcessor$.MODULE$.enterExpressionPropertyList(ctx);
   }

   public static void exitPropertyValue(final SqlBaseParser.PropertyValueContext ctx) {
      PostProcessor$.MODULE$.exitPropertyValue(ctx);
   }

   public static void enterPropertyValue(final SqlBaseParser.PropertyValueContext ctx) {
      PostProcessor$.MODULE$.enterPropertyValue(ctx);
   }

   public static void exitPropertyKey(final SqlBaseParser.PropertyKeyContext ctx) {
      PostProcessor$.MODULE$.exitPropertyKey(ctx);
   }

   public static void enterPropertyKey(final SqlBaseParser.PropertyKeyContext ctx) {
      PostProcessor$.MODULE$.enterPropertyKey(ctx);
   }

   public static void exitProperty(final SqlBaseParser.PropertyContext ctx) {
      PostProcessor$.MODULE$.exitProperty(ctx);
   }

   public static void enterProperty(final SqlBaseParser.PropertyContext ctx) {
      PostProcessor$.MODULE$.enterProperty(ctx);
   }

   public static void exitPropertyList(final SqlBaseParser.PropertyListContext ctx) {
      PostProcessor$.MODULE$.exitPropertyList(ctx);
   }

   public static void enterPropertyList(final SqlBaseParser.PropertyListContext ctx) {
      PostProcessor$.MODULE$.enterPropertyList(ctx);
   }

   public static void exitCreateTableClauses(final SqlBaseParser.CreateTableClausesContext ctx) {
      PostProcessor$.MODULE$.exitCreateTableClauses(ctx);
   }

   public static void enterCreateTableClauses(final SqlBaseParser.CreateTableClausesContext ctx) {
      PostProcessor$.MODULE$.enterCreateTableClauses(ctx);
   }

   public static void exitTableProvider(final SqlBaseParser.TableProviderContext ctx) {
      PostProcessor$.MODULE$.exitTableProvider(ctx);
   }

   public static void enterTableProvider(final SqlBaseParser.TableProviderContext ctx) {
      PostProcessor$.MODULE$.enterTableProvider(ctx);
   }

   public static void exitNamedQuery(final SqlBaseParser.NamedQueryContext ctx) {
      PostProcessor$.MODULE$.exitNamedQuery(ctx);
   }

   public static void enterNamedQuery(final SqlBaseParser.NamedQueryContext ctx) {
      PostProcessor$.MODULE$.enterNamedQuery(ctx);
   }

   public static void exitCtes(final SqlBaseParser.CtesContext ctx) {
      PostProcessor$.MODULE$.exitCtes(ctx);
   }

   public static void enterCtes(final SqlBaseParser.CtesContext ctx) {
      PostProcessor$.MODULE$.enterCtes(ctx);
   }

   public static void exitDescribeColName(final SqlBaseParser.DescribeColNameContext ctx) {
      PostProcessor$.MODULE$.exitDescribeColName(ctx);
   }

   public static void enterDescribeColName(final SqlBaseParser.DescribeColNameContext ctx) {
      PostProcessor$.MODULE$.enterDescribeColName(ctx);
   }

   public static void exitDescribeFuncName(final SqlBaseParser.DescribeFuncNameContext ctx) {
      PostProcessor$.MODULE$.exitDescribeFuncName(ctx);
   }

   public static void enterDescribeFuncName(final SqlBaseParser.DescribeFuncNameContext ctx) {
      PostProcessor$.MODULE$.enterDescribeFuncName(ctx);
   }

   public static void exitVariable(final SqlBaseParser.VariableContext ctx) {
      PostProcessor$.MODULE$.exitVariable(ctx);
   }

   public static void enterVariable(final SqlBaseParser.VariableContext ctx) {
      PostProcessor$.MODULE$.enterVariable(ctx);
   }

   public static void exitNamespaces(final SqlBaseParser.NamespacesContext ctx) {
      PostProcessor$.MODULE$.exitNamespaces(ctx);
   }

   public static void enterNamespaces(final SqlBaseParser.NamespacesContext ctx) {
      PostProcessor$.MODULE$.enterNamespaces(ctx);
   }

   public static void exitNamespace(final SqlBaseParser.NamespaceContext ctx) {
      PostProcessor$.MODULE$.exitNamespace(ctx);
   }

   public static void enterNamespace(final SqlBaseParser.NamespaceContext ctx) {
      PostProcessor$.MODULE$.enterNamespace(ctx);
   }

   public static void exitPartitionVal(final SqlBaseParser.PartitionValContext ctx) {
      PostProcessor$.MODULE$.exitPartitionVal(ctx);
   }

   public static void enterPartitionVal(final SqlBaseParser.PartitionValContext ctx) {
      PostProcessor$.MODULE$.enterPartitionVal(ctx);
   }

   public static void exitPartitionSpec(final SqlBaseParser.PartitionSpecContext ctx) {
      PostProcessor$.MODULE$.exitPartitionSpec(ctx);
   }

   public static void enterPartitionSpec(final SqlBaseParser.PartitionSpecContext ctx) {
      PostProcessor$.MODULE$.enterPartitionSpec(ctx);
   }

   public static void exitPartitionSpecLocation(final SqlBaseParser.PartitionSpecLocationContext ctx) {
      PostProcessor$.MODULE$.exitPartitionSpecLocation(ctx);
   }

   public static void enterPartitionSpecLocation(final SqlBaseParser.PartitionSpecLocationContext ctx) {
      PostProcessor$.MODULE$.enterPartitionSpecLocation(ctx);
   }

   public static void exitInsertOverwriteDir(final SqlBaseParser.InsertOverwriteDirContext ctx) {
      PostProcessor$.MODULE$.exitInsertOverwriteDir(ctx);
   }

   public static void enterInsertOverwriteDir(final SqlBaseParser.InsertOverwriteDirContext ctx) {
      PostProcessor$.MODULE$.enterInsertOverwriteDir(ctx);
   }

   public static void exitInsertOverwriteHiveDir(final SqlBaseParser.InsertOverwriteHiveDirContext ctx) {
      PostProcessor$.MODULE$.exitInsertOverwriteHiveDir(ctx);
   }

   public static void enterInsertOverwriteHiveDir(final SqlBaseParser.InsertOverwriteHiveDirContext ctx) {
      PostProcessor$.MODULE$.enterInsertOverwriteHiveDir(ctx);
   }

   public static void exitInsertIntoReplaceWhere(final SqlBaseParser.InsertIntoReplaceWhereContext ctx) {
      PostProcessor$.MODULE$.exitInsertIntoReplaceWhere(ctx);
   }

   public static void enterInsertIntoReplaceWhere(final SqlBaseParser.InsertIntoReplaceWhereContext ctx) {
      PostProcessor$.MODULE$.enterInsertIntoReplaceWhere(ctx);
   }

   public static void exitInsertIntoTable(final SqlBaseParser.InsertIntoTableContext ctx) {
      PostProcessor$.MODULE$.exitInsertIntoTable(ctx);
   }

   public static void enterInsertIntoTable(final SqlBaseParser.InsertIntoTableContext ctx) {
      PostProcessor$.MODULE$.enterInsertIntoTable(ctx);
   }

   public static void exitInsertOverwriteTable(final SqlBaseParser.InsertOverwriteTableContext ctx) {
      PostProcessor$.MODULE$.exitInsertOverwriteTable(ctx);
   }

   public static void enterInsertOverwriteTable(final SqlBaseParser.InsertOverwriteTableContext ctx) {
      PostProcessor$.MODULE$.enterInsertOverwriteTable(ctx);
   }

   public static void exitQuery(final SqlBaseParser.QueryContext ctx) {
      PostProcessor$.MODULE$.exitQuery(ctx);
   }

   public static void enterQuery(final SqlBaseParser.QueryContext ctx) {
      PostProcessor$.MODULE$.enterQuery(ctx);
   }

   public static void exitSingleQuery(final SqlBaseParser.SingleQueryContext ctx) {
      PostProcessor$.MODULE$.exitSingleQuery(ctx);
   }

   public static void enterSingleQuery(final SqlBaseParser.SingleQueryContext ctx) {
      PostProcessor$.MODULE$.enterSingleQuery(ctx);
   }

   public static void exitCommentSpec(final SqlBaseParser.CommentSpecContext ctx) {
      PostProcessor$.MODULE$.exitCommentSpec(ctx);
   }

   public static void enterCommentSpec(final SqlBaseParser.CommentSpecContext ctx) {
      PostProcessor$.MODULE$.enterCommentSpec(ctx);
   }

   public static void exitSchemaBinding(final SqlBaseParser.SchemaBindingContext ctx) {
      PostProcessor$.MODULE$.exitSchemaBinding(ctx);
   }

   public static void enterSchemaBinding(final SqlBaseParser.SchemaBindingContext ctx) {
      PostProcessor$.MODULE$.enterSchemaBinding(ctx);
   }

   public static void exitLocationSpec(final SqlBaseParser.LocationSpecContext ctx) {
      PostProcessor$.MODULE$.exitLocationSpec(ctx);
   }

   public static void enterLocationSpec(final SqlBaseParser.LocationSpecContext ctx) {
      PostProcessor$.MODULE$.enterLocationSpec(ctx);
   }

   public static void exitSkewSpec(final SqlBaseParser.SkewSpecContext ctx) {
      PostProcessor$.MODULE$.exitSkewSpec(ctx);
   }

   public static void enterSkewSpec(final SqlBaseParser.SkewSpecContext ctx) {
      PostProcessor$.MODULE$.enterSkewSpec(ctx);
   }

   public static void exitBucketSpec(final SqlBaseParser.BucketSpecContext ctx) {
      PostProcessor$.MODULE$.exitBucketSpec(ctx);
   }

   public static void enterBucketSpec(final SqlBaseParser.BucketSpecContext ctx) {
      PostProcessor$.MODULE$.enterBucketSpec(ctx);
   }

   public static void exitClusterBySpec(final SqlBaseParser.ClusterBySpecContext ctx) {
      PostProcessor$.MODULE$.exitClusterBySpec(ctx);
   }

   public static void enterClusterBySpec(final SqlBaseParser.ClusterBySpecContext ctx) {
      PostProcessor$.MODULE$.enterClusterBySpec(ctx);
   }

   public static void exitReplaceTableHeader(final SqlBaseParser.ReplaceTableHeaderContext ctx) {
      PostProcessor$.MODULE$.exitReplaceTableHeader(ctx);
   }

   public static void enterReplaceTableHeader(final SqlBaseParser.ReplaceTableHeaderContext ctx) {
      PostProcessor$.MODULE$.enterReplaceTableHeader(ctx);
   }

   public static void exitCreateTableHeader(final SqlBaseParser.CreateTableHeaderContext ctx) {
      PostProcessor$.MODULE$.exitCreateTableHeader(ctx);
   }

   public static void enterCreateTableHeader(final SqlBaseParser.CreateTableHeaderContext ctx) {
      PostProcessor$.MODULE$.enterCreateTableHeader(ctx);
   }

   public static void exitUnsupportedHiveNativeCommands(final SqlBaseParser.UnsupportedHiveNativeCommandsContext ctx) {
      PostProcessor$.MODULE$.exitUnsupportedHiveNativeCommands(ctx);
   }

   public static void enterUnsupportedHiveNativeCommands(final SqlBaseParser.UnsupportedHiveNativeCommandsContext ctx) {
      PostProcessor$.MODULE$.enterUnsupportedHiveNativeCommands(ctx);
   }

   public static void exitConfigValue(final SqlBaseParser.ConfigValueContext ctx) {
      PostProcessor$.MODULE$.exitConfigValue(ctx);
   }

   public static void enterConfigValue(final SqlBaseParser.ConfigValueContext ctx) {
      PostProcessor$.MODULE$.enterConfigValue(ctx);
   }

   public static void exitConfigKey(final SqlBaseParser.ConfigKeyContext ctx) {
      PostProcessor$.MODULE$.exitConfigKey(ctx);
   }

   public static void enterConfigKey(final SqlBaseParser.ConfigKeyContext ctx) {
      PostProcessor$.MODULE$.enterConfigKey(ctx);
   }

   public static void exitTimezone(final SqlBaseParser.TimezoneContext ctx) {
      PostProcessor$.MODULE$.exitTimezone(ctx);
   }

   public static void enterTimezone(final SqlBaseParser.TimezoneContext ctx) {
      PostProcessor$.MODULE$.enterTimezone(ctx);
   }

   public static void exitExecuteImmediateArgumentSeq(final SqlBaseParser.ExecuteImmediateArgumentSeqContext ctx) {
      PostProcessor$.MODULE$.exitExecuteImmediateArgumentSeq(ctx);
   }

   public static void enterExecuteImmediateArgumentSeq(final SqlBaseParser.ExecuteImmediateArgumentSeqContext ctx) {
      PostProcessor$.MODULE$.enterExecuteImmediateArgumentSeq(ctx);
   }

   public static void exitExecuteImmediateArgument(final SqlBaseParser.ExecuteImmediateArgumentContext ctx) {
      PostProcessor$.MODULE$.exitExecuteImmediateArgument(ctx);
   }

   public static void enterExecuteImmediateArgument(final SqlBaseParser.ExecuteImmediateArgumentContext ctx) {
      PostProcessor$.MODULE$.enterExecuteImmediateArgument(ctx);
   }

   public static void exitExecuteImmediateQueryParam(final SqlBaseParser.ExecuteImmediateQueryParamContext ctx) {
      PostProcessor$.MODULE$.exitExecuteImmediateQueryParam(ctx);
   }

   public static void enterExecuteImmediateQueryParam(final SqlBaseParser.ExecuteImmediateQueryParamContext ctx) {
      PostProcessor$.MODULE$.enterExecuteImmediateQueryParam(ctx);
   }

   public static void exitExecuteImmediateUsing(final SqlBaseParser.ExecuteImmediateUsingContext ctx) {
      PostProcessor$.MODULE$.exitExecuteImmediateUsing(ctx);
   }

   public static void enterExecuteImmediateUsing(final SqlBaseParser.ExecuteImmediateUsingContext ctx) {
      PostProcessor$.MODULE$.enterExecuteImmediateUsing(ctx);
   }

   public static void exitExecuteImmediate(final SqlBaseParser.ExecuteImmediateContext ctx) {
      PostProcessor$.MODULE$.exitExecuteImmediate(ctx);
   }

   public static void enterExecuteImmediate(final SqlBaseParser.ExecuteImmediateContext ctx) {
      PostProcessor$.MODULE$.enterExecuteImmediate(ctx);
   }

   public static void exitResetConfiguration(final SqlBaseParser.ResetConfigurationContext ctx) {
      PostProcessor$.MODULE$.exitResetConfiguration(ctx);
   }

   public static void enterResetConfiguration(final SqlBaseParser.ResetConfigurationContext ctx) {
      PostProcessor$.MODULE$.enterResetConfiguration(ctx);
   }

   public static void exitResetQuotedConfiguration(final SqlBaseParser.ResetQuotedConfigurationContext ctx) {
      PostProcessor$.MODULE$.exitResetQuotedConfiguration(ctx);
   }

   public static void enterResetQuotedConfiguration(final SqlBaseParser.ResetQuotedConfigurationContext ctx) {
      PostProcessor$.MODULE$.enterResetQuotedConfiguration(ctx);
   }

   public static void exitSetConfiguration(final SqlBaseParser.SetConfigurationContext ctx) {
      PostProcessor$.MODULE$.exitSetConfiguration(ctx);
   }

   public static void enterSetConfiguration(final SqlBaseParser.SetConfigurationContext ctx) {
      PostProcessor$.MODULE$.enterSetConfiguration(ctx);
   }

   public static void exitSetQuotedConfiguration(final SqlBaseParser.SetQuotedConfigurationContext ctx) {
      PostProcessor$.MODULE$.exitSetQuotedConfiguration(ctx);
   }

   public static void enterSetQuotedConfiguration(final SqlBaseParser.SetQuotedConfigurationContext ctx) {
      PostProcessor$.MODULE$.enterSetQuotedConfiguration(ctx);
   }

   public static void exitSetVariable(final SqlBaseParser.SetVariableContext ctx) {
      PostProcessor$.MODULE$.exitSetVariable(ctx);
   }

   public static void enterSetVariable(final SqlBaseParser.SetVariableContext ctx) {
      PostProcessor$.MODULE$.enterSetVariable(ctx);
   }

   public static void exitSetTimeZone(final SqlBaseParser.SetTimeZoneContext ctx) {
      PostProcessor$.MODULE$.exitSetTimeZone(ctx);
   }

   public static void enterSetTimeZone(final SqlBaseParser.SetTimeZoneContext ctx) {
      PostProcessor$.MODULE$.enterSetTimeZone(ctx);
   }

   public static void exitFailSetRole(final SqlBaseParser.FailSetRoleContext ctx) {
      PostProcessor$.MODULE$.exitFailSetRole(ctx);
   }

   public static void enterFailSetRole(final SqlBaseParser.FailSetRoleContext ctx) {
      PostProcessor$.MODULE$.enterFailSetRole(ctx);
   }

   public static void exitFailNativeCommand(final SqlBaseParser.FailNativeCommandContext ctx) {
      PostProcessor$.MODULE$.exitFailNativeCommand(ctx);
   }

   public static void enterFailNativeCommand(final SqlBaseParser.FailNativeCommandContext ctx) {
      PostProcessor$.MODULE$.enterFailNativeCommand(ctx);
   }

   public static void exitCall(final SqlBaseParser.CallContext ctx) {
      PostProcessor$.MODULE$.exitCall(ctx);
   }

   public static void enterCall(final SqlBaseParser.CallContext ctx) {
      PostProcessor$.MODULE$.enterCall(ctx);
   }

   public static void exitDropIndex(final SqlBaseParser.DropIndexContext ctx) {
      PostProcessor$.MODULE$.exitDropIndex(ctx);
   }

   public static void enterDropIndex(final SqlBaseParser.DropIndexContext ctx) {
      PostProcessor$.MODULE$.enterDropIndex(ctx);
   }

   public static void exitCreateIndex(final SqlBaseParser.CreateIndexContext ctx) {
      PostProcessor$.MODULE$.exitCreateIndex(ctx);
   }

   public static void enterCreateIndex(final SqlBaseParser.CreateIndexContext ctx) {
      PostProcessor$.MODULE$.enterCreateIndex(ctx);
   }

   public static void exitManageResource(final SqlBaseParser.ManageResourceContext ctx) {
      PostProcessor$.MODULE$.exitManageResource(ctx);
   }

   public static void enterManageResource(final SqlBaseParser.ManageResourceContext ctx) {
      PostProcessor$.MODULE$.enterManageResource(ctx);
   }

   public static void exitRepairTable(final SqlBaseParser.RepairTableContext ctx) {
      PostProcessor$.MODULE$.exitRepairTable(ctx);
   }

   public static void enterRepairTable(final SqlBaseParser.RepairTableContext ctx) {
      PostProcessor$.MODULE$.enterRepairTable(ctx);
   }

   public static void exitTruncateTable(final SqlBaseParser.TruncateTableContext ctx) {
      PostProcessor$.MODULE$.exitTruncateTable(ctx);
   }

   public static void enterTruncateTable(final SqlBaseParser.TruncateTableContext ctx) {
      PostProcessor$.MODULE$.enterTruncateTable(ctx);
   }

   public static void exitLoadData(final SqlBaseParser.LoadDataContext ctx) {
      PostProcessor$.MODULE$.exitLoadData(ctx);
   }

   public static void enterLoadData(final SqlBaseParser.LoadDataContext ctx) {
      PostProcessor$.MODULE$.enterLoadData(ctx);
   }

   public static void exitClearCache(final SqlBaseParser.ClearCacheContext ctx) {
      PostProcessor$.MODULE$.exitClearCache(ctx);
   }

   public static void enterClearCache(final SqlBaseParser.ClearCacheContext ctx) {
      PostProcessor$.MODULE$.enterClearCache(ctx);
   }

   public static void exitUncacheTable(final SqlBaseParser.UncacheTableContext ctx) {
      PostProcessor$.MODULE$.exitUncacheTable(ctx);
   }

   public static void enterUncacheTable(final SqlBaseParser.UncacheTableContext ctx) {
      PostProcessor$.MODULE$.enterUncacheTable(ctx);
   }

   public static void exitCacheTable(final SqlBaseParser.CacheTableContext ctx) {
      PostProcessor$.MODULE$.exitCacheTable(ctx);
   }

   public static void enterCacheTable(final SqlBaseParser.CacheTableContext ctx) {
      PostProcessor$.MODULE$.enterCacheTable(ctx);
   }

   public static void exitRefreshResource(final SqlBaseParser.RefreshResourceContext ctx) {
      PostProcessor$.MODULE$.exitRefreshResource(ctx);
   }

   public static void enterRefreshResource(final SqlBaseParser.RefreshResourceContext ctx) {
      PostProcessor$.MODULE$.enterRefreshResource(ctx);
   }

   public static void exitRefreshFunction(final SqlBaseParser.RefreshFunctionContext ctx) {
      PostProcessor$.MODULE$.exitRefreshFunction(ctx);
   }

   public static void enterRefreshFunction(final SqlBaseParser.RefreshFunctionContext ctx) {
      PostProcessor$.MODULE$.enterRefreshFunction(ctx);
   }

   public static void exitRefreshTable(final SqlBaseParser.RefreshTableContext ctx) {
      PostProcessor$.MODULE$.exitRefreshTable(ctx);
   }

   public static void enterRefreshTable(final SqlBaseParser.RefreshTableContext ctx) {
      PostProcessor$.MODULE$.enterRefreshTable(ctx);
   }

   public static void exitCommentTable(final SqlBaseParser.CommentTableContext ctx) {
      PostProcessor$.MODULE$.exitCommentTable(ctx);
   }

   public static void enterCommentTable(final SqlBaseParser.CommentTableContext ctx) {
      PostProcessor$.MODULE$.enterCommentTable(ctx);
   }

   public static void exitCommentNamespace(final SqlBaseParser.CommentNamespaceContext ctx) {
      PostProcessor$.MODULE$.exitCommentNamespace(ctx);
   }

   public static void enterCommentNamespace(final SqlBaseParser.CommentNamespaceContext ctx) {
      PostProcessor$.MODULE$.enterCommentNamespace(ctx);
   }

   public static void exitDescribeQuery(final SqlBaseParser.DescribeQueryContext ctx) {
      PostProcessor$.MODULE$.exitDescribeQuery(ctx);
   }

   public static void enterDescribeQuery(final SqlBaseParser.DescribeQueryContext ctx) {
      PostProcessor$.MODULE$.enterDescribeQuery(ctx);
   }

   public static void exitDescribeRelation(final SqlBaseParser.DescribeRelationContext ctx) {
      PostProcessor$.MODULE$.exitDescribeRelation(ctx);
   }

   public static void enterDescribeRelation(final SqlBaseParser.DescribeRelationContext ctx) {
      PostProcessor$.MODULE$.enterDescribeRelation(ctx);
   }

   public static void exitDescribeNamespace(final SqlBaseParser.DescribeNamespaceContext ctx) {
      PostProcessor$.MODULE$.exitDescribeNamespace(ctx);
   }

   public static void enterDescribeNamespace(final SqlBaseParser.DescribeNamespaceContext ctx) {
      PostProcessor$.MODULE$.enterDescribeNamespace(ctx);
   }

   public static void exitDescribeFunction(final SqlBaseParser.DescribeFunctionContext ctx) {
      PostProcessor$.MODULE$.exitDescribeFunction(ctx);
   }

   public static void enterDescribeFunction(final SqlBaseParser.DescribeFunctionContext ctx) {
      PostProcessor$.MODULE$.enterDescribeFunction(ctx);
   }

   public static void exitShowCatalogs(final SqlBaseParser.ShowCatalogsContext ctx) {
      PostProcessor$.MODULE$.exitShowCatalogs(ctx);
   }

   public static void enterShowCatalogs(final SqlBaseParser.ShowCatalogsContext ctx) {
      PostProcessor$.MODULE$.enterShowCatalogs(ctx);
   }

   public static void exitShowCurrentNamespace(final SqlBaseParser.ShowCurrentNamespaceContext ctx) {
      PostProcessor$.MODULE$.exitShowCurrentNamespace(ctx);
   }

   public static void enterShowCurrentNamespace(final SqlBaseParser.ShowCurrentNamespaceContext ctx) {
      PostProcessor$.MODULE$.enterShowCurrentNamespace(ctx);
   }

   public static void exitShowCreateTable(final SqlBaseParser.ShowCreateTableContext ctx) {
      PostProcessor$.MODULE$.exitShowCreateTable(ctx);
   }

   public static void enterShowCreateTable(final SqlBaseParser.ShowCreateTableContext ctx) {
      PostProcessor$.MODULE$.enterShowCreateTable(ctx);
   }

   public static void exitShowFunctions(final SqlBaseParser.ShowFunctionsContext ctx) {
      PostProcessor$.MODULE$.exitShowFunctions(ctx);
   }

   public static void enterShowFunctions(final SqlBaseParser.ShowFunctionsContext ctx) {
      PostProcessor$.MODULE$.enterShowFunctions(ctx);
   }

   public static void exitShowPartitions(final SqlBaseParser.ShowPartitionsContext ctx) {
      PostProcessor$.MODULE$.exitShowPartitions(ctx);
   }

   public static void enterShowPartitions(final SqlBaseParser.ShowPartitionsContext ctx) {
      PostProcessor$.MODULE$.enterShowPartitions(ctx);
   }

   public static void exitShowViews(final SqlBaseParser.ShowViewsContext ctx) {
      PostProcessor$.MODULE$.exitShowViews(ctx);
   }

   public static void enterShowViews(final SqlBaseParser.ShowViewsContext ctx) {
      PostProcessor$.MODULE$.enterShowViews(ctx);
   }

   public static void exitShowColumns(final SqlBaseParser.ShowColumnsContext ctx) {
      PostProcessor$.MODULE$.exitShowColumns(ctx);
   }

   public static void enterShowColumns(final SqlBaseParser.ShowColumnsContext ctx) {
      PostProcessor$.MODULE$.enterShowColumns(ctx);
   }

   public static void exitShowTblProperties(final SqlBaseParser.ShowTblPropertiesContext ctx) {
      PostProcessor$.MODULE$.exitShowTblProperties(ctx);
   }

   public static void enterShowTblProperties(final SqlBaseParser.ShowTblPropertiesContext ctx) {
      PostProcessor$.MODULE$.enterShowTblProperties(ctx);
   }

   public static void exitShowTableExtended(final SqlBaseParser.ShowTableExtendedContext ctx) {
      PostProcessor$.MODULE$.exitShowTableExtended(ctx);
   }

   public static void enterShowTableExtended(final SqlBaseParser.ShowTableExtendedContext ctx) {
      PostProcessor$.MODULE$.enterShowTableExtended(ctx);
   }

   public static void exitShowTables(final SqlBaseParser.ShowTablesContext ctx) {
      PostProcessor$.MODULE$.exitShowTables(ctx);
   }

   public static void enterShowTables(final SqlBaseParser.ShowTablesContext ctx) {
      PostProcessor$.MODULE$.enterShowTables(ctx);
   }

   public static void exitExplain(final SqlBaseParser.ExplainContext ctx) {
      PostProcessor$.MODULE$.exitExplain(ctx);
   }

   public static void enterExplain(final SqlBaseParser.ExplainContext ctx) {
      PostProcessor$.MODULE$.enterExplain(ctx);
   }

   public static void exitDropVariable(final SqlBaseParser.DropVariableContext ctx) {
      PostProcessor$.MODULE$.exitDropVariable(ctx);
   }

   public static void enterDropVariable(final SqlBaseParser.DropVariableContext ctx) {
      PostProcessor$.MODULE$.enterDropVariable(ctx);
   }

   public static void exitCreateVariable(final SqlBaseParser.CreateVariableContext ctx) {
      PostProcessor$.MODULE$.exitCreateVariable(ctx);
   }

   public static void enterCreateVariable(final SqlBaseParser.CreateVariableContext ctx) {
      PostProcessor$.MODULE$.enterCreateVariable(ctx);
   }

   public static void exitDropFunction(final SqlBaseParser.DropFunctionContext ctx) {
      PostProcessor$.MODULE$.exitDropFunction(ctx);
   }

   public static void enterDropFunction(final SqlBaseParser.DropFunctionContext ctx) {
      PostProcessor$.MODULE$.enterDropFunction(ctx);
   }

   public static void exitCreateUserDefinedFunction(final SqlBaseParser.CreateUserDefinedFunctionContext ctx) {
      PostProcessor$.MODULE$.exitCreateUserDefinedFunction(ctx);
   }

   public static void enterCreateUserDefinedFunction(final SqlBaseParser.CreateUserDefinedFunctionContext ctx) {
      PostProcessor$.MODULE$.enterCreateUserDefinedFunction(ctx);
   }

   public static void exitCreateFunction(final SqlBaseParser.CreateFunctionContext ctx) {
      PostProcessor$.MODULE$.exitCreateFunction(ctx);
   }

   public static void enterCreateFunction(final SqlBaseParser.CreateFunctionContext ctx) {
      PostProcessor$.MODULE$.enterCreateFunction(ctx);
   }

   public static void exitAlterViewSchemaBinding(final SqlBaseParser.AlterViewSchemaBindingContext ctx) {
      PostProcessor$.MODULE$.exitAlterViewSchemaBinding(ctx);
   }

   public static void enterAlterViewSchemaBinding(final SqlBaseParser.AlterViewSchemaBindingContext ctx) {
      PostProcessor$.MODULE$.enterAlterViewSchemaBinding(ctx);
   }

   public static void exitAlterViewQuery(final SqlBaseParser.AlterViewQueryContext ctx) {
      PostProcessor$.MODULE$.exitAlterViewQuery(ctx);
   }

   public static void enterAlterViewQuery(final SqlBaseParser.AlterViewQueryContext ctx) {
      PostProcessor$.MODULE$.enterAlterViewQuery(ctx);
   }

   public static void exitCreateTempViewUsing(final SqlBaseParser.CreateTempViewUsingContext ctx) {
      PostProcessor$.MODULE$.exitCreateTempViewUsing(ctx);
   }

   public static void enterCreateTempViewUsing(final SqlBaseParser.CreateTempViewUsingContext ctx) {
      PostProcessor$.MODULE$.enterCreateTempViewUsing(ctx);
   }

   public static void exitCreateView(final SqlBaseParser.CreateViewContext ctx) {
      PostProcessor$.MODULE$.exitCreateView(ctx);
   }

   public static void enterCreateView(final SqlBaseParser.CreateViewContext ctx) {
      PostProcessor$.MODULE$.enterCreateView(ctx);
   }

   public static void exitDropView(final SqlBaseParser.DropViewContext ctx) {
      PostProcessor$.MODULE$.exitDropView(ctx);
   }

   public static void enterDropView(final SqlBaseParser.DropViewContext ctx) {
      PostProcessor$.MODULE$.enterDropView(ctx);
   }

   public static void exitDropTable(final SqlBaseParser.DropTableContext ctx) {
      PostProcessor$.MODULE$.exitDropTable(ctx);
   }

   public static void enterDropTable(final SqlBaseParser.DropTableContext ctx) {
      PostProcessor$.MODULE$.enterDropTable(ctx);
   }

   public static void exitAlterTableCollation(final SqlBaseParser.AlterTableCollationContext ctx) {
      PostProcessor$.MODULE$.exitAlterTableCollation(ctx);
   }

   public static void enterAlterTableCollation(final SqlBaseParser.AlterTableCollationContext ctx) {
      PostProcessor$.MODULE$.enterAlterTableCollation(ctx);
   }

   public static void exitAlterClusterBy(final SqlBaseParser.AlterClusterByContext ctx) {
      PostProcessor$.MODULE$.exitAlterClusterBy(ctx);
   }

   public static void enterAlterClusterBy(final SqlBaseParser.AlterClusterByContext ctx) {
      PostProcessor$.MODULE$.enterAlterClusterBy(ctx);
   }

   public static void exitRecoverPartitions(final SqlBaseParser.RecoverPartitionsContext ctx) {
      PostProcessor$.MODULE$.exitRecoverPartitions(ctx);
   }

   public static void enterRecoverPartitions(final SqlBaseParser.RecoverPartitionsContext ctx) {
      PostProcessor$.MODULE$.enterRecoverPartitions(ctx);
   }

   public static void exitSetTableLocation(final SqlBaseParser.SetTableLocationContext ctx) {
      PostProcessor$.MODULE$.exitSetTableLocation(ctx);
   }

   public static void enterSetTableLocation(final SqlBaseParser.SetTableLocationContext ctx) {
      PostProcessor$.MODULE$.enterSetTableLocation(ctx);
   }

   public static void exitDropTablePartitions(final SqlBaseParser.DropTablePartitionsContext ctx) {
      PostProcessor$.MODULE$.exitDropTablePartitions(ctx);
   }

   public static void enterDropTablePartitions(final SqlBaseParser.DropTablePartitionsContext ctx) {
      PostProcessor$.MODULE$.enterDropTablePartitions(ctx);
   }

   public static void exitRenameTablePartition(final SqlBaseParser.RenameTablePartitionContext ctx) {
      PostProcessor$.MODULE$.exitRenameTablePartition(ctx);
   }

   public static void enterRenameTablePartition(final SqlBaseParser.RenameTablePartitionContext ctx) {
      PostProcessor$.MODULE$.enterRenameTablePartition(ctx);
   }

   public static void exitAddTablePartition(final SqlBaseParser.AddTablePartitionContext ctx) {
      PostProcessor$.MODULE$.exitAddTablePartition(ctx);
   }

   public static void enterAddTablePartition(final SqlBaseParser.AddTablePartitionContext ctx) {
      PostProcessor$.MODULE$.enterAddTablePartition(ctx);
   }

   public static void exitSetTableSerDe(final SqlBaseParser.SetTableSerDeContext ctx) {
      PostProcessor$.MODULE$.exitSetTableSerDe(ctx);
   }

   public static void enterSetTableSerDe(final SqlBaseParser.SetTableSerDeContext ctx) {
      PostProcessor$.MODULE$.enterSetTableSerDe(ctx);
   }

   public static void exitHiveReplaceColumns(final SqlBaseParser.HiveReplaceColumnsContext ctx) {
      PostProcessor$.MODULE$.exitHiveReplaceColumns(ctx);
   }

   public static void enterHiveReplaceColumns(final SqlBaseParser.HiveReplaceColumnsContext ctx) {
      PostProcessor$.MODULE$.enterHiveReplaceColumns(ctx);
   }

   public static void exitHiveChangeColumn(final SqlBaseParser.HiveChangeColumnContext ctx) {
      PostProcessor$.MODULE$.exitHiveChangeColumn(ctx);
   }

   public static void enterHiveChangeColumn(final SqlBaseParser.HiveChangeColumnContext ctx) {
      PostProcessor$.MODULE$.enterHiveChangeColumn(ctx);
   }

   public static void exitAlterTableAlterColumn(final SqlBaseParser.AlterTableAlterColumnContext ctx) {
      PostProcessor$.MODULE$.exitAlterTableAlterColumn(ctx);
   }

   public static void enterAlterTableAlterColumn(final SqlBaseParser.AlterTableAlterColumnContext ctx) {
      PostProcessor$.MODULE$.enterAlterTableAlterColumn(ctx);
   }

   public static void exitUnsetTableProperties(final SqlBaseParser.UnsetTablePropertiesContext ctx) {
      PostProcessor$.MODULE$.exitUnsetTableProperties(ctx);
   }

   public static void enterUnsetTableProperties(final SqlBaseParser.UnsetTablePropertiesContext ctx) {
      PostProcessor$.MODULE$.enterUnsetTableProperties(ctx);
   }

   public static void exitSetTableProperties(final SqlBaseParser.SetTablePropertiesContext ctx) {
      PostProcessor$.MODULE$.exitSetTableProperties(ctx);
   }

   public static void enterSetTableProperties(final SqlBaseParser.SetTablePropertiesContext ctx) {
      PostProcessor$.MODULE$.enterSetTableProperties(ctx);
   }

   public static void exitRenameTable(final SqlBaseParser.RenameTableContext ctx) {
      PostProcessor$.MODULE$.exitRenameTable(ctx);
   }

   public static void enterRenameTable(final SqlBaseParser.RenameTableContext ctx) {
      PostProcessor$.MODULE$.enterRenameTable(ctx);
   }

   public static void exitDropTableColumns(final SqlBaseParser.DropTableColumnsContext ctx) {
      PostProcessor$.MODULE$.exitDropTableColumns(ctx);
   }

   public static void enterDropTableColumns(final SqlBaseParser.DropTableColumnsContext ctx) {
      PostProcessor$.MODULE$.enterDropTableColumns(ctx);
   }

   public static void exitRenameTableColumn(final SqlBaseParser.RenameTableColumnContext ctx) {
      PostProcessor$.MODULE$.exitRenameTableColumn(ctx);
   }

   public static void enterRenameTableColumn(final SqlBaseParser.RenameTableColumnContext ctx) {
      PostProcessor$.MODULE$.enterRenameTableColumn(ctx);
   }

   public static void exitAddTableColumns(final SqlBaseParser.AddTableColumnsContext ctx) {
      PostProcessor$.MODULE$.exitAddTableColumns(ctx);
   }

   public static void enterAddTableColumns(final SqlBaseParser.AddTableColumnsContext ctx) {
      PostProcessor$.MODULE$.enterAddTableColumns(ctx);
   }

   public static void exitAnalyzeTables(final SqlBaseParser.AnalyzeTablesContext ctx) {
      PostProcessor$.MODULE$.exitAnalyzeTables(ctx);
   }

   public static void enterAnalyzeTables(final SqlBaseParser.AnalyzeTablesContext ctx) {
      PostProcessor$.MODULE$.enterAnalyzeTables(ctx);
   }

   public static void exitAnalyze(final SqlBaseParser.AnalyzeContext ctx) {
      PostProcessor$.MODULE$.exitAnalyze(ctx);
   }

   public static void enterAnalyze(final SqlBaseParser.AnalyzeContext ctx) {
      PostProcessor$.MODULE$.enterAnalyze(ctx);
   }

   public static void exitReplaceTable(final SqlBaseParser.ReplaceTableContext ctx) {
      PostProcessor$.MODULE$.exitReplaceTable(ctx);
   }

   public static void enterReplaceTable(final SqlBaseParser.ReplaceTableContext ctx) {
      PostProcessor$.MODULE$.enterReplaceTable(ctx);
   }

   public static void exitCreateTableLike(final SqlBaseParser.CreateTableLikeContext ctx) {
      PostProcessor$.MODULE$.exitCreateTableLike(ctx);
   }

   public static void enterCreateTableLike(final SqlBaseParser.CreateTableLikeContext ctx) {
      PostProcessor$.MODULE$.enterCreateTableLike(ctx);
   }

   public static void exitCreateTable(final SqlBaseParser.CreateTableContext ctx) {
      PostProcessor$.MODULE$.exitCreateTable(ctx);
   }

   public static void enterCreateTable(final SqlBaseParser.CreateTableContext ctx) {
      PostProcessor$.MODULE$.enterCreateTable(ctx);
   }

   public static void exitShowNamespaces(final SqlBaseParser.ShowNamespacesContext ctx) {
      PostProcessor$.MODULE$.exitShowNamespaces(ctx);
   }

   public static void enterShowNamespaces(final SqlBaseParser.ShowNamespacesContext ctx) {
      PostProcessor$.MODULE$.enterShowNamespaces(ctx);
   }

   public static void exitDropNamespace(final SqlBaseParser.DropNamespaceContext ctx) {
      PostProcessor$.MODULE$.exitDropNamespace(ctx);
   }

   public static void enterDropNamespace(final SqlBaseParser.DropNamespaceContext ctx) {
      PostProcessor$.MODULE$.enterDropNamespace(ctx);
   }

   public static void exitSetNamespaceLocation(final SqlBaseParser.SetNamespaceLocationContext ctx) {
      PostProcessor$.MODULE$.exitSetNamespaceLocation(ctx);
   }

   public static void enterSetNamespaceLocation(final SqlBaseParser.SetNamespaceLocationContext ctx) {
      PostProcessor$.MODULE$.enterSetNamespaceLocation(ctx);
   }

   public static void exitUnsetNamespaceProperties(final SqlBaseParser.UnsetNamespacePropertiesContext ctx) {
      PostProcessor$.MODULE$.exitUnsetNamespaceProperties(ctx);
   }

   public static void enterUnsetNamespaceProperties(final SqlBaseParser.UnsetNamespacePropertiesContext ctx) {
      PostProcessor$.MODULE$.enterUnsetNamespaceProperties(ctx);
   }

   public static void exitSetNamespaceProperties(final SqlBaseParser.SetNamespacePropertiesContext ctx) {
      PostProcessor$.MODULE$.exitSetNamespaceProperties(ctx);
   }

   public static void enterSetNamespaceProperties(final SqlBaseParser.SetNamespacePropertiesContext ctx) {
      PostProcessor$.MODULE$.enterSetNamespaceProperties(ctx);
   }

   public static void exitCreateNamespace(final SqlBaseParser.CreateNamespaceContext ctx) {
      PostProcessor$.MODULE$.exitCreateNamespace(ctx);
   }

   public static void enterCreateNamespace(final SqlBaseParser.CreateNamespaceContext ctx) {
      PostProcessor$.MODULE$.enterCreateNamespace(ctx);
   }

   public static void exitSetCatalog(final SqlBaseParser.SetCatalogContext ctx) {
      PostProcessor$.MODULE$.exitSetCatalog(ctx);
   }

   public static void enterSetCatalog(final SqlBaseParser.SetCatalogContext ctx) {
      PostProcessor$.MODULE$.enterSetCatalog(ctx);
   }

   public static void exitUseNamespace(final SqlBaseParser.UseNamespaceContext ctx) {
      PostProcessor$.MODULE$.exitUseNamespace(ctx);
   }

   public static void enterUseNamespace(final SqlBaseParser.UseNamespaceContext ctx) {
      PostProcessor$.MODULE$.enterUseNamespace(ctx);
   }

   public static void exitUse(final SqlBaseParser.UseContext ctx) {
      PostProcessor$.MODULE$.exitUse(ctx);
   }

   public static void enterUse(final SqlBaseParser.UseContext ctx) {
      PostProcessor$.MODULE$.enterUse(ctx);
   }

   public static void exitDmlStatement(final SqlBaseParser.DmlStatementContext ctx) {
      PostProcessor$.MODULE$.exitDmlStatement(ctx);
   }

   public static void enterDmlStatement(final SqlBaseParser.DmlStatementContext ctx) {
      PostProcessor$.MODULE$.enterDmlStatement(ctx);
   }

   public static void exitVisitExecuteImmediate(final SqlBaseParser.VisitExecuteImmediateContext ctx) {
      PostProcessor$.MODULE$.exitVisitExecuteImmediate(ctx);
   }

   public static void enterVisitExecuteImmediate(final SqlBaseParser.VisitExecuteImmediateContext ctx) {
      PostProcessor$.MODULE$.enterVisitExecuteImmediate(ctx);
   }

   public static void exitStatementDefault(final SqlBaseParser.StatementDefaultContext ctx) {
      PostProcessor$.MODULE$.exitStatementDefault(ctx);
   }

   public static void enterStatementDefault(final SqlBaseParser.StatementDefaultContext ctx) {
      PostProcessor$.MODULE$.enterStatementDefault(ctx);
   }

   public static void exitSingleRoutineParamList(final SqlBaseParser.SingleRoutineParamListContext ctx) {
      PostProcessor$.MODULE$.exitSingleRoutineParamList(ctx);
   }

   public static void enterSingleRoutineParamList(final SqlBaseParser.SingleRoutineParamListContext ctx) {
      PostProcessor$.MODULE$.enterSingleRoutineParamList(ctx);
   }

   public static void exitSingleTableSchema(final SqlBaseParser.SingleTableSchemaContext ctx) {
      PostProcessor$.MODULE$.exitSingleTableSchema(ctx);
   }

   public static void enterSingleTableSchema(final SqlBaseParser.SingleTableSchemaContext ctx) {
      PostProcessor$.MODULE$.enterSingleTableSchema(ctx);
   }

   public static void exitSingleDataType(final SqlBaseParser.SingleDataTypeContext ctx) {
      PostProcessor$.MODULE$.exitSingleDataType(ctx);
   }

   public static void enterSingleDataType(final SqlBaseParser.SingleDataTypeContext ctx) {
      PostProcessor$.MODULE$.enterSingleDataType(ctx);
   }

   public static void exitSingleFunctionIdentifier(final SqlBaseParser.SingleFunctionIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitSingleFunctionIdentifier(ctx);
   }

   public static void enterSingleFunctionIdentifier(final SqlBaseParser.SingleFunctionIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterSingleFunctionIdentifier(ctx);
   }

   public static void exitSingleMultipartIdentifier(final SqlBaseParser.SingleMultipartIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitSingleMultipartIdentifier(ctx);
   }

   public static void enterSingleMultipartIdentifier(final SqlBaseParser.SingleMultipartIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterSingleMultipartIdentifier(ctx);
   }

   public static void exitSingleTableIdentifier(final SqlBaseParser.SingleTableIdentifierContext ctx) {
      PostProcessor$.MODULE$.exitSingleTableIdentifier(ctx);
   }

   public static void enterSingleTableIdentifier(final SqlBaseParser.SingleTableIdentifierContext ctx) {
      PostProcessor$.MODULE$.enterSingleTableIdentifier(ctx);
   }

   public static void exitSingleExpression(final SqlBaseParser.SingleExpressionContext ctx) {
      PostProcessor$.MODULE$.exitSingleExpression(ctx);
   }

   public static void enterSingleExpression(final SqlBaseParser.SingleExpressionContext ctx) {
      PostProcessor$.MODULE$.enterSingleExpression(ctx);
   }

   public static void exitEndLabel(final SqlBaseParser.EndLabelContext ctx) {
      PostProcessor$.MODULE$.exitEndLabel(ctx);
   }

   public static void enterEndLabel(final SqlBaseParser.EndLabelContext ctx) {
      PostProcessor$.MODULE$.enterEndLabel(ctx);
   }

   public static void exitBeginLabel(final SqlBaseParser.BeginLabelContext ctx) {
      PostProcessor$.MODULE$.exitBeginLabel(ctx);
   }

   public static void enterBeginLabel(final SqlBaseParser.BeginLabelContext ctx) {
      PostProcessor$.MODULE$.enterBeginLabel(ctx);
   }

   public static void exitSingleStatement(final SqlBaseParser.SingleStatementContext ctx) {
      PostProcessor$.MODULE$.exitSingleStatement(ctx);
   }

   public static void enterSingleStatement(final SqlBaseParser.SingleStatementContext ctx) {
      PostProcessor$.MODULE$.enterSingleStatement(ctx);
   }

   public static void exitForStatement(final SqlBaseParser.ForStatementContext ctx) {
      PostProcessor$.MODULE$.exitForStatement(ctx);
   }

   public static void enterForStatement(final SqlBaseParser.ForStatementContext ctx) {
      PostProcessor$.MODULE$.enterForStatement(ctx);
   }

   public static void exitLoopStatement(final SqlBaseParser.LoopStatementContext ctx) {
      PostProcessor$.MODULE$.exitLoopStatement(ctx);
   }

   public static void enterLoopStatement(final SqlBaseParser.LoopStatementContext ctx) {
      PostProcessor$.MODULE$.enterLoopStatement(ctx);
   }

   public static void exitSimpleCaseStatement(final SqlBaseParser.SimpleCaseStatementContext ctx) {
      PostProcessor$.MODULE$.exitSimpleCaseStatement(ctx);
   }

   public static void enterSimpleCaseStatement(final SqlBaseParser.SimpleCaseStatementContext ctx) {
      PostProcessor$.MODULE$.enterSimpleCaseStatement(ctx);
   }

   public static void exitSearchedCaseStatement(final SqlBaseParser.SearchedCaseStatementContext ctx) {
      PostProcessor$.MODULE$.exitSearchedCaseStatement(ctx);
   }

   public static void enterSearchedCaseStatement(final SqlBaseParser.SearchedCaseStatementContext ctx) {
      PostProcessor$.MODULE$.enterSearchedCaseStatement(ctx);
   }

   public static void exitIterateStatement(final SqlBaseParser.IterateStatementContext ctx) {
      PostProcessor$.MODULE$.exitIterateStatement(ctx);
   }

   public static void enterIterateStatement(final SqlBaseParser.IterateStatementContext ctx) {
      PostProcessor$.MODULE$.enterIterateStatement(ctx);
   }

   public static void exitLeaveStatement(final SqlBaseParser.LeaveStatementContext ctx) {
      PostProcessor$.MODULE$.exitLeaveStatement(ctx);
   }

   public static void enterLeaveStatement(final SqlBaseParser.LeaveStatementContext ctx) {
      PostProcessor$.MODULE$.enterLeaveStatement(ctx);
   }

   public static void exitRepeatStatement(final SqlBaseParser.RepeatStatementContext ctx) {
      PostProcessor$.MODULE$.exitRepeatStatement(ctx);
   }

   public static void enterRepeatStatement(final SqlBaseParser.RepeatStatementContext ctx) {
      PostProcessor$.MODULE$.enterRepeatStatement(ctx);
   }

   public static void exitIfElseStatement(final SqlBaseParser.IfElseStatementContext ctx) {
      PostProcessor$.MODULE$.exitIfElseStatement(ctx);
   }

   public static void enterIfElseStatement(final SqlBaseParser.IfElseStatementContext ctx) {
      PostProcessor$.MODULE$.enterIfElseStatement(ctx);
   }

   public static void exitWhileStatement(final SqlBaseParser.WhileStatementContext ctx) {
      PostProcessor$.MODULE$.exitWhileStatement(ctx);
   }

   public static void enterWhileStatement(final SqlBaseParser.WhileStatementContext ctx) {
      PostProcessor$.MODULE$.enterWhileStatement(ctx);
   }

   public static void exitDeclareHandlerStatement(final SqlBaseParser.DeclareHandlerStatementContext ctx) {
      PostProcessor$.MODULE$.exitDeclareHandlerStatement(ctx);
   }

   public static void enterDeclareHandlerStatement(final SqlBaseParser.DeclareHandlerStatementContext ctx) {
      PostProcessor$.MODULE$.enterDeclareHandlerStatement(ctx);
   }

   public static void exitConditionValues(final SqlBaseParser.ConditionValuesContext ctx) {
      PostProcessor$.MODULE$.exitConditionValues(ctx);
   }

   public static void enterConditionValues(final SqlBaseParser.ConditionValuesContext ctx) {
      PostProcessor$.MODULE$.enterConditionValues(ctx);
   }

   public static void exitConditionValue(final SqlBaseParser.ConditionValueContext ctx) {
      PostProcessor$.MODULE$.exitConditionValue(ctx);
   }

   public static void enterConditionValue(final SqlBaseParser.ConditionValueContext ctx) {
      PostProcessor$.MODULE$.enterConditionValue(ctx);
   }

   public static void exitDeclareConditionStatement(final SqlBaseParser.DeclareConditionStatementContext ctx) {
      PostProcessor$.MODULE$.exitDeclareConditionStatement(ctx);
   }

   public static void enterDeclareConditionStatement(final SqlBaseParser.DeclareConditionStatementContext ctx) {
      PostProcessor$.MODULE$.enterDeclareConditionStatement(ctx);
   }

   public static void exitSqlStateValue(final SqlBaseParser.SqlStateValueContext ctx) {
      PostProcessor$.MODULE$.exitSqlStateValue(ctx);
   }

   public static void enterSqlStateValue(final SqlBaseParser.SqlStateValueContext ctx) {
      PostProcessor$.MODULE$.enterSqlStateValue(ctx);
   }

   public static void exitSetVariableInsideSqlScript(final SqlBaseParser.SetVariableInsideSqlScriptContext ctx) {
      PostProcessor$.MODULE$.exitSetVariableInsideSqlScript(ctx);
   }

   public static void enterSetVariableInsideSqlScript(final SqlBaseParser.SetVariableInsideSqlScriptContext ctx) {
      PostProcessor$.MODULE$.enterSetVariableInsideSqlScript(ctx);
   }

   public static void exitCompoundStatement(final SqlBaseParser.CompoundStatementContext ctx) {
      PostProcessor$.MODULE$.exitCompoundStatement(ctx);
   }

   public static void enterCompoundStatement(final SqlBaseParser.CompoundStatementContext ctx) {
      PostProcessor$.MODULE$.enterCompoundStatement(ctx);
   }

   public static void exitCompoundBody(final SqlBaseParser.CompoundBodyContext ctx) {
      PostProcessor$.MODULE$.exitCompoundBody(ctx);
   }

   public static void enterCompoundBody(final SqlBaseParser.CompoundBodyContext ctx) {
      PostProcessor$.MODULE$.enterCompoundBody(ctx);
   }

   public static void exitBeginEndCompoundBlock(final SqlBaseParser.BeginEndCompoundBlockContext ctx) {
      PostProcessor$.MODULE$.exitBeginEndCompoundBlock(ctx);
   }

   public static void enterBeginEndCompoundBlock(final SqlBaseParser.BeginEndCompoundBlockContext ctx) {
      PostProcessor$.MODULE$.enterBeginEndCompoundBlock(ctx);
   }

   public static void exitSingleCompoundStatement(final SqlBaseParser.SingleCompoundStatementContext ctx) {
      PostProcessor$.MODULE$.exitSingleCompoundStatement(ctx);
   }

   public static void enterSingleCompoundStatement(final SqlBaseParser.SingleCompoundStatementContext ctx) {
      PostProcessor$.MODULE$.enterSingleCompoundStatement(ctx);
   }

   public static void exitCompoundOrSingleStatement(final SqlBaseParser.CompoundOrSingleStatementContext ctx) {
      PostProcessor$.MODULE$.exitCompoundOrSingleStatement(ctx);
   }

   public static void enterCompoundOrSingleStatement(final SqlBaseParser.CompoundOrSingleStatementContext ctx) {
      PostProcessor$.MODULE$.enterCompoundOrSingleStatement(ctx);
   }
}
