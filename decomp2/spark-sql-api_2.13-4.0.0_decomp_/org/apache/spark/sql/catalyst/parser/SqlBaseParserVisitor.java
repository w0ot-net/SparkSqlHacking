package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public interface SqlBaseParserVisitor extends ParseTreeVisitor {
   Object visitCompoundOrSingleStatement(SqlBaseParser.CompoundOrSingleStatementContext var1);

   Object visitSingleCompoundStatement(SqlBaseParser.SingleCompoundStatementContext var1);

   Object visitBeginEndCompoundBlock(SqlBaseParser.BeginEndCompoundBlockContext var1);

   Object visitCompoundBody(SqlBaseParser.CompoundBodyContext var1);

   Object visitCompoundStatement(SqlBaseParser.CompoundStatementContext var1);

   Object visitSetVariableInsideSqlScript(SqlBaseParser.SetVariableInsideSqlScriptContext var1);

   Object visitSqlStateValue(SqlBaseParser.SqlStateValueContext var1);

   Object visitDeclareConditionStatement(SqlBaseParser.DeclareConditionStatementContext var1);

   Object visitConditionValue(SqlBaseParser.ConditionValueContext var1);

   Object visitConditionValues(SqlBaseParser.ConditionValuesContext var1);

   Object visitDeclareHandlerStatement(SqlBaseParser.DeclareHandlerStatementContext var1);

   Object visitWhileStatement(SqlBaseParser.WhileStatementContext var1);

   Object visitIfElseStatement(SqlBaseParser.IfElseStatementContext var1);

   Object visitRepeatStatement(SqlBaseParser.RepeatStatementContext var1);

   Object visitLeaveStatement(SqlBaseParser.LeaveStatementContext var1);

   Object visitIterateStatement(SqlBaseParser.IterateStatementContext var1);

   Object visitSearchedCaseStatement(SqlBaseParser.SearchedCaseStatementContext var1);

   Object visitSimpleCaseStatement(SqlBaseParser.SimpleCaseStatementContext var1);

   Object visitLoopStatement(SqlBaseParser.LoopStatementContext var1);

   Object visitForStatement(SqlBaseParser.ForStatementContext var1);

   Object visitSingleStatement(SqlBaseParser.SingleStatementContext var1);

   Object visitBeginLabel(SqlBaseParser.BeginLabelContext var1);

   Object visitEndLabel(SqlBaseParser.EndLabelContext var1);

   Object visitSingleExpression(SqlBaseParser.SingleExpressionContext var1);

   Object visitSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext var1);

   Object visitSingleMultipartIdentifier(SqlBaseParser.SingleMultipartIdentifierContext var1);

   Object visitSingleFunctionIdentifier(SqlBaseParser.SingleFunctionIdentifierContext var1);

   Object visitSingleDataType(SqlBaseParser.SingleDataTypeContext var1);

   Object visitSingleTableSchema(SqlBaseParser.SingleTableSchemaContext var1);

   Object visitSingleRoutineParamList(SqlBaseParser.SingleRoutineParamListContext var1);

   Object visitStatementDefault(SqlBaseParser.StatementDefaultContext var1);

   Object visitVisitExecuteImmediate(SqlBaseParser.VisitExecuteImmediateContext var1);

   Object visitDmlStatement(SqlBaseParser.DmlStatementContext var1);

   Object visitUse(SqlBaseParser.UseContext var1);

   Object visitUseNamespace(SqlBaseParser.UseNamespaceContext var1);

   Object visitSetCatalog(SqlBaseParser.SetCatalogContext var1);

   Object visitCreateNamespace(SqlBaseParser.CreateNamespaceContext var1);

   Object visitSetNamespaceProperties(SqlBaseParser.SetNamespacePropertiesContext var1);

   Object visitUnsetNamespaceProperties(SqlBaseParser.UnsetNamespacePropertiesContext var1);

   Object visitSetNamespaceLocation(SqlBaseParser.SetNamespaceLocationContext var1);

   Object visitDropNamespace(SqlBaseParser.DropNamespaceContext var1);

   Object visitShowNamespaces(SqlBaseParser.ShowNamespacesContext var1);

   Object visitCreateTable(SqlBaseParser.CreateTableContext var1);

   Object visitCreateTableLike(SqlBaseParser.CreateTableLikeContext var1);

   Object visitReplaceTable(SqlBaseParser.ReplaceTableContext var1);

   Object visitAnalyze(SqlBaseParser.AnalyzeContext var1);

   Object visitAnalyzeTables(SqlBaseParser.AnalyzeTablesContext var1);

   Object visitAddTableColumns(SqlBaseParser.AddTableColumnsContext var1);

   Object visitRenameTableColumn(SqlBaseParser.RenameTableColumnContext var1);

   Object visitDropTableColumns(SqlBaseParser.DropTableColumnsContext var1);

   Object visitRenameTable(SqlBaseParser.RenameTableContext var1);

   Object visitSetTableProperties(SqlBaseParser.SetTablePropertiesContext var1);

   Object visitUnsetTableProperties(SqlBaseParser.UnsetTablePropertiesContext var1);

   Object visitAlterTableAlterColumn(SqlBaseParser.AlterTableAlterColumnContext var1);

   Object visitHiveChangeColumn(SqlBaseParser.HiveChangeColumnContext var1);

   Object visitHiveReplaceColumns(SqlBaseParser.HiveReplaceColumnsContext var1);

   Object visitSetTableSerDe(SqlBaseParser.SetTableSerDeContext var1);

   Object visitAddTablePartition(SqlBaseParser.AddTablePartitionContext var1);

   Object visitRenameTablePartition(SqlBaseParser.RenameTablePartitionContext var1);

   Object visitDropTablePartitions(SqlBaseParser.DropTablePartitionsContext var1);

   Object visitSetTableLocation(SqlBaseParser.SetTableLocationContext var1);

   Object visitRecoverPartitions(SqlBaseParser.RecoverPartitionsContext var1);

   Object visitAlterClusterBy(SqlBaseParser.AlterClusterByContext var1);

   Object visitAlterTableCollation(SqlBaseParser.AlterTableCollationContext var1);

   Object visitDropTable(SqlBaseParser.DropTableContext var1);

   Object visitDropView(SqlBaseParser.DropViewContext var1);

   Object visitCreateView(SqlBaseParser.CreateViewContext var1);

   Object visitCreateTempViewUsing(SqlBaseParser.CreateTempViewUsingContext var1);

   Object visitAlterViewQuery(SqlBaseParser.AlterViewQueryContext var1);

   Object visitAlterViewSchemaBinding(SqlBaseParser.AlterViewSchemaBindingContext var1);

   Object visitCreateFunction(SqlBaseParser.CreateFunctionContext var1);

   Object visitCreateUserDefinedFunction(SqlBaseParser.CreateUserDefinedFunctionContext var1);

   Object visitDropFunction(SqlBaseParser.DropFunctionContext var1);

   Object visitCreateVariable(SqlBaseParser.CreateVariableContext var1);

   Object visitDropVariable(SqlBaseParser.DropVariableContext var1);

   Object visitExplain(SqlBaseParser.ExplainContext var1);

   Object visitShowTables(SqlBaseParser.ShowTablesContext var1);

   Object visitShowTableExtended(SqlBaseParser.ShowTableExtendedContext var1);

   Object visitShowTblProperties(SqlBaseParser.ShowTblPropertiesContext var1);

   Object visitShowColumns(SqlBaseParser.ShowColumnsContext var1);

   Object visitShowViews(SqlBaseParser.ShowViewsContext var1);

   Object visitShowPartitions(SqlBaseParser.ShowPartitionsContext var1);

   Object visitShowFunctions(SqlBaseParser.ShowFunctionsContext var1);

   Object visitShowCreateTable(SqlBaseParser.ShowCreateTableContext var1);

   Object visitShowCurrentNamespace(SqlBaseParser.ShowCurrentNamespaceContext var1);

   Object visitShowCatalogs(SqlBaseParser.ShowCatalogsContext var1);

   Object visitDescribeFunction(SqlBaseParser.DescribeFunctionContext var1);

   Object visitDescribeNamespace(SqlBaseParser.DescribeNamespaceContext var1);

   Object visitDescribeRelation(SqlBaseParser.DescribeRelationContext var1);

   Object visitDescribeQuery(SqlBaseParser.DescribeQueryContext var1);

   Object visitCommentNamespace(SqlBaseParser.CommentNamespaceContext var1);

   Object visitCommentTable(SqlBaseParser.CommentTableContext var1);

   Object visitRefreshTable(SqlBaseParser.RefreshTableContext var1);

   Object visitRefreshFunction(SqlBaseParser.RefreshFunctionContext var1);

   Object visitRefreshResource(SqlBaseParser.RefreshResourceContext var1);

   Object visitCacheTable(SqlBaseParser.CacheTableContext var1);

   Object visitUncacheTable(SqlBaseParser.UncacheTableContext var1);

   Object visitClearCache(SqlBaseParser.ClearCacheContext var1);

   Object visitLoadData(SqlBaseParser.LoadDataContext var1);

   Object visitTruncateTable(SqlBaseParser.TruncateTableContext var1);

   Object visitRepairTable(SqlBaseParser.RepairTableContext var1);

   Object visitManageResource(SqlBaseParser.ManageResourceContext var1);

   Object visitCreateIndex(SqlBaseParser.CreateIndexContext var1);

   Object visitDropIndex(SqlBaseParser.DropIndexContext var1);

   Object visitCall(SqlBaseParser.CallContext var1);

   Object visitFailNativeCommand(SqlBaseParser.FailNativeCommandContext var1);

   Object visitFailSetRole(SqlBaseParser.FailSetRoleContext var1);

   Object visitSetTimeZone(SqlBaseParser.SetTimeZoneContext var1);

   Object visitSetVariable(SqlBaseParser.SetVariableContext var1);

   Object visitSetQuotedConfiguration(SqlBaseParser.SetQuotedConfigurationContext var1);

   Object visitSetConfiguration(SqlBaseParser.SetConfigurationContext var1);

   Object visitResetQuotedConfiguration(SqlBaseParser.ResetQuotedConfigurationContext var1);

   Object visitResetConfiguration(SqlBaseParser.ResetConfigurationContext var1);

   Object visitExecuteImmediate(SqlBaseParser.ExecuteImmediateContext var1);

   Object visitExecuteImmediateUsing(SqlBaseParser.ExecuteImmediateUsingContext var1);

   Object visitExecuteImmediateQueryParam(SqlBaseParser.ExecuteImmediateQueryParamContext var1);

   Object visitExecuteImmediateArgument(SqlBaseParser.ExecuteImmediateArgumentContext var1);

   Object visitExecuteImmediateArgumentSeq(SqlBaseParser.ExecuteImmediateArgumentSeqContext var1);

   Object visitTimezone(SqlBaseParser.TimezoneContext var1);

   Object visitConfigKey(SqlBaseParser.ConfigKeyContext var1);

   Object visitConfigValue(SqlBaseParser.ConfigValueContext var1);

   Object visitUnsupportedHiveNativeCommands(SqlBaseParser.UnsupportedHiveNativeCommandsContext var1);

   Object visitCreateTableHeader(SqlBaseParser.CreateTableHeaderContext var1);

   Object visitReplaceTableHeader(SqlBaseParser.ReplaceTableHeaderContext var1);

   Object visitClusterBySpec(SqlBaseParser.ClusterBySpecContext var1);

   Object visitBucketSpec(SqlBaseParser.BucketSpecContext var1);

   Object visitSkewSpec(SqlBaseParser.SkewSpecContext var1);

   Object visitLocationSpec(SqlBaseParser.LocationSpecContext var1);

   Object visitSchemaBinding(SqlBaseParser.SchemaBindingContext var1);

   Object visitCommentSpec(SqlBaseParser.CommentSpecContext var1);

   Object visitSingleQuery(SqlBaseParser.SingleQueryContext var1);

   Object visitQuery(SqlBaseParser.QueryContext var1);

   Object visitInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext var1);

   Object visitInsertIntoTable(SqlBaseParser.InsertIntoTableContext var1);

   Object visitInsertIntoReplaceWhere(SqlBaseParser.InsertIntoReplaceWhereContext var1);

   Object visitInsertOverwriteHiveDir(SqlBaseParser.InsertOverwriteHiveDirContext var1);

   Object visitInsertOverwriteDir(SqlBaseParser.InsertOverwriteDirContext var1);

   Object visitPartitionSpecLocation(SqlBaseParser.PartitionSpecLocationContext var1);

   Object visitPartitionSpec(SqlBaseParser.PartitionSpecContext var1);

   Object visitPartitionVal(SqlBaseParser.PartitionValContext var1);

   Object visitNamespace(SqlBaseParser.NamespaceContext var1);

   Object visitNamespaces(SqlBaseParser.NamespacesContext var1);

   Object visitVariable(SqlBaseParser.VariableContext var1);

   Object visitDescribeFuncName(SqlBaseParser.DescribeFuncNameContext var1);

   Object visitDescribeColName(SqlBaseParser.DescribeColNameContext var1);

   Object visitCtes(SqlBaseParser.CtesContext var1);

   Object visitNamedQuery(SqlBaseParser.NamedQueryContext var1);

   Object visitTableProvider(SqlBaseParser.TableProviderContext var1);

   Object visitCreateTableClauses(SqlBaseParser.CreateTableClausesContext var1);

   Object visitPropertyList(SqlBaseParser.PropertyListContext var1);

   Object visitProperty(SqlBaseParser.PropertyContext var1);

   Object visitPropertyKey(SqlBaseParser.PropertyKeyContext var1);

   Object visitPropertyValue(SqlBaseParser.PropertyValueContext var1);

   Object visitExpressionPropertyList(SqlBaseParser.ExpressionPropertyListContext var1);

   Object visitExpressionProperty(SqlBaseParser.ExpressionPropertyContext var1);

   Object visitConstantList(SqlBaseParser.ConstantListContext var1);

   Object visitNestedConstantList(SqlBaseParser.NestedConstantListContext var1);

   Object visitCreateFileFormat(SqlBaseParser.CreateFileFormatContext var1);

   Object visitTableFileFormat(SqlBaseParser.TableFileFormatContext var1);

   Object visitGenericFileFormat(SqlBaseParser.GenericFileFormatContext var1);

   Object visitStorageHandler(SqlBaseParser.StorageHandlerContext var1);

   Object visitResource(SqlBaseParser.ResourceContext var1);

   Object visitSingleInsertQuery(SqlBaseParser.SingleInsertQueryContext var1);

   Object visitMultiInsertQuery(SqlBaseParser.MultiInsertQueryContext var1);

   Object visitDeleteFromTable(SqlBaseParser.DeleteFromTableContext var1);

   Object visitUpdateTable(SqlBaseParser.UpdateTableContext var1);

   Object visitMergeIntoTable(SqlBaseParser.MergeIntoTableContext var1);

   Object visitIdentifierReference(SqlBaseParser.IdentifierReferenceContext var1);

   Object visitCatalogIdentifierReference(SqlBaseParser.CatalogIdentifierReferenceContext var1);

   Object visitQueryOrganization(SqlBaseParser.QueryOrganizationContext var1);

   Object visitMultiInsertQueryBody(SqlBaseParser.MultiInsertQueryBodyContext var1);

   Object visitOperatorPipeStatement(SqlBaseParser.OperatorPipeStatementContext var1);

   Object visitQueryTermDefault(SqlBaseParser.QueryTermDefaultContext var1);

   Object visitSetOperation(SqlBaseParser.SetOperationContext var1);

   Object visitQueryPrimaryDefault(SqlBaseParser.QueryPrimaryDefaultContext var1);

   Object visitFromStmt(SqlBaseParser.FromStmtContext var1);

   Object visitTable(SqlBaseParser.TableContext var1);

   Object visitInlineTableDefault1(SqlBaseParser.InlineTableDefault1Context var1);

   Object visitSubquery(SqlBaseParser.SubqueryContext var1);

   Object visitSortItem(SqlBaseParser.SortItemContext var1);

   Object visitFromStatement(SqlBaseParser.FromStatementContext var1);

   Object visitFromStatementBody(SqlBaseParser.FromStatementBodyContext var1);

   Object visitTransformQuerySpecification(SqlBaseParser.TransformQuerySpecificationContext var1);

   Object visitRegularQuerySpecification(SqlBaseParser.RegularQuerySpecificationContext var1);

   Object visitTransformClause(SqlBaseParser.TransformClauseContext var1);

   Object visitSelectClause(SqlBaseParser.SelectClauseContext var1);

   Object visitSetClause(SqlBaseParser.SetClauseContext var1);

   Object visitMatchedClause(SqlBaseParser.MatchedClauseContext var1);

   Object visitNotMatchedClause(SqlBaseParser.NotMatchedClauseContext var1);

   Object visitNotMatchedBySourceClause(SqlBaseParser.NotMatchedBySourceClauseContext var1);

   Object visitMatchedAction(SqlBaseParser.MatchedActionContext var1);

   Object visitNotMatchedAction(SqlBaseParser.NotMatchedActionContext var1);

   Object visitNotMatchedBySourceAction(SqlBaseParser.NotMatchedBySourceActionContext var1);

   Object visitExceptClause(SqlBaseParser.ExceptClauseContext var1);

   Object visitAssignmentList(SqlBaseParser.AssignmentListContext var1);

   Object visitAssignment(SqlBaseParser.AssignmentContext var1);

   Object visitWhereClause(SqlBaseParser.WhereClauseContext var1);

   Object visitHavingClause(SqlBaseParser.HavingClauseContext var1);

   Object visitHint(SqlBaseParser.HintContext var1);

   Object visitHintStatement(SqlBaseParser.HintStatementContext var1);

   Object visitFromClause(SqlBaseParser.FromClauseContext var1);

   Object visitTemporalClause(SqlBaseParser.TemporalClauseContext var1);

   Object visitAggregationClause(SqlBaseParser.AggregationClauseContext var1);

   Object visitGroupByClause(SqlBaseParser.GroupByClauseContext var1);

   Object visitGroupingAnalytics(SqlBaseParser.GroupingAnalyticsContext var1);

   Object visitGroupingElement(SqlBaseParser.GroupingElementContext var1);

   Object visitGroupingSet(SqlBaseParser.GroupingSetContext var1);

   Object visitPivotClause(SqlBaseParser.PivotClauseContext var1);

   Object visitPivotColumn(SqlBaseParser.PivotColumnContext var1);

   Object visitPivotValue(SqlBaseParser.PivotValueContext var1);

   Object visitUnpivotClause(SqlBaseParser.UnpivotClauseContext var1);

   Object visitUnpivotNullClause(SqlBaseParser.UnpivotNullClauseContext var1);

   Object visitUnpivotOperator(SqlBaseParser.UnpivotOperatorContext var1);

   Object visitUnpivotSingleValueColumnClause(SqlBaseParser.UnpivotSingleValueColumnClauseContext var1);

   Object visitUnpivotMultiValueColumnClause(SqlBaseParser.UnpivotMultiValueColumnClauseContext var1);

   Object visitUnpivotColumnSet(SqlBaseParser.UnpivotColumnSetContext var1);

   Object visitUnpivotValueColumn(SqlBaseParser.UnpivotValueColumnContext var1);

   Object visitUnpivotNameColumn(SqlBaseParser.UnpivotNameColumnContext var1);

   Object visitUnpivotColumnAndAlias(SqlBaseParser.UnpivotColumnAndAliasContext var1);

   Object visitUnpivotColumn(SqlBaseParser.UnpivotColumnContext var1);

   Object visitUnpivotAlias(SqlBaseParser.UnpivotAliasContext var1);

   Object visitLateralView(SqlBaseParser.LateralViewContext var1);

   Object visitSetQuantifier(SqlBaseParser.SetQuantifierContext var1);

   Object visitRelation(SqlBaseParser.RelationContext var1);

   Object visitRelationExtension(SqlBaseParser.RelationExtensionContext var1);

   Object visitJoinRelation(SqlBaseParser.JoinRelationContext var1);

   Object visitJoinType(SqlBaseParser.JoinTypeContext var1);

   Object visitJoinCriteria(SqlBaseParser.JoinCriteriaContext var1);

   Object visitSample(SqlBaseParser.SampleContext var1);

   Object visitSampleByPercentile(SqlBaseParser.SampleByPercentileContext var1);

   Object visitSampleByRows(SqlBaseParser.SampleByRowsContext var1);

   Object visitSampleByBucket(SqlBaseParser.SampleByBucketContext var1);

   Object visitSampleByBytes(SqlBaseParser.SampleByBytesContext var1);

   Object visitIdentifierList(SqlBaseParser.IdentifierListContext var1);

   Object visitIdentifierSeq(SqlBaseParser.IdentifierSeqContext var1);

   Object visitOrderedIdentifierList(SqlBaseParser.OrderedIdentifierListContext var1);

   Object visitOrderedIdentifier(SqlBaseParser.OrderedIdentifierContext var1);

   Object visitIdentifierCommentList(SqlBaseParser.IdentifierCommentListContext var1);

   Object visitIdentifierComment(SqlBaseParser.IdentifierCommentContext var1);

   Object visitTableName(SqlBaseParser.TableNameContext var1);

   Object visitAliasedQuery(SqlBaseParser.AliasedQueryContext var1);

   Object visitAliasedRelation(SqlBaseParser.AliasedRelationContext var1);

   Object visitInlineTableDefault2(SqlBaseParser.InlineTableDefault2Context var1);

   Object visitTableValuedFunction(SqlBaseParser.TableValuedFunctionContext var1);

   Object visitOptionsClause(SqlBaseParser.OptionsClauseContext var1);

   Object visitInlineTable(SqlBaseParser.InlineTableContext var1);

   Object visitFunctionTableSubqueryArgument(SqlBaseParser.FunctionTableSubqueryArgumentContext var1);

   Object visitTableArgumentPartitioning(SqlBaseParser.TableArgumentPartitioningContext var1);

   Object visitFunctionTableNamedArgumentExpression(SqlBaseParser.FunctionTableNamedArgumentExpressionContext var1);

   Object visitFunctionTableReferenceArgument(SqlBaseParser.FunctionTableReferenceArgumentContext var1);

   Object visitFunctionTableArgument(SqlBaseParser.FunctionTableArgumentContext var1);

   Object visitFunctionTable(SqlBaseParser.FunctionTableContext var1);

   Object visitTableAlias(SqlBaseParser.TableAliasContext var1);

   Object visitRowFormatSerde(SqlBaseParser.RowFormatSerdeContext var1);

   Object visitRowFormatDelimited(SqlBaseParser.RowFormatDelimitedContext var1);

   Object visitMultipartIdentifierList(SqlBaseParser.MultipartIdentifierListContext var1);

   Object visitMultipartIdentifier(SqlBaseParser.MultipartIdentifierContext var1);

   Object visitMultipartIdentifierPropertyList(SqlBaseParser.MultipartIdentifierPropertyListContext var1);

   Object visitMultipartIdentifierProperty(SqlBaseParser.MultipartIdentifierPropertyContext var1);

   Object visitTableIdentifier(SqlBaseParser.TableIdentifierContext var1);

   Object visitFunctionIdentifier(SqlBaseParser.FunctionIdentifierContext var1);

   Object visitNamedExpression(SqlBaseParser.NamedExpressionContext var1);

   Object visitNamedExpressionSeq(SqlBaseParser.NamedExpressionSeqContext var1);

   Object visitPartitionFieldList(SqlBaseParser.PartitionFieldListContext var1);

   Object visitPartitionTransform(SqlBaseParser.PartitionTransformContext var1);

   Object visitPartitionColumn(SqlBaseParser.PartitionColumnContext var1);

   Object visitIdentityTransform(SqlBaseParser.IdentityTransformContext var1);

   Object visitApplyTransform(SqlBaseParser.ApplyTransformContext var1);

   Object visitTransformArgument(SqlBaseParser.TransformArgumentContext var1);

   Object visitExpression(SqlBaseParser.ExpressionContext var1);

   Object visitNamedArgumentExpression(SqlBaseParser.NamedArgumentExpressionContext var1);

   Object visitFunctionArgument(SqlBaseParser.FunctionArgumentContext var1);

   Object visitExpressionSeq(SqlBaseParser.ExpressionSeqContext var1);

   Object visitLogicalNot(SqlBaseParser.LogicalNotContext var1);

   Object visitPredicated(SqlBaseParser.PredicatedContext var1);

   Object visitExists(SqlBaseParser.ExistsContext var1);

   Object visitLogicalBinary(SqlBaseParser.LogicalBinaryContext var1);

   Object visitPredicate(SqlBaseParser.PredicateContext var1);

   Object visitErrorCapturingNot(SqlBaseParser.ErrorCapturingNotContext var1);

   Object visitValueExpressionDefault(SqlBaseParser.ValueExpressionDefaultContext var1);

   Object visitComparison(SqlBaseParser.ComparisonContext var1);

   Object visitShiftExpression(SqlBaseParser.ShiftExpressionContext var1);

   Object visitArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext var1);

   Object visitArithmeticUnary(SqlBaseParser.ArithmeticUnaryContext var1);

   Object visitShiftOperator(SqlBaseParser.ShiftOperatorContext var1);

   Object visitDatetimeUnit(SqlBaseParser.DatetimeUnitContext var1);

   Object visitStruct(SqlBaseParser.StructContext var1);

   Object visitDereference(SqlBaseParser.DereferenceContext var1);

   Object visitCastByColon(SqlBaseParser.CastByColonContext var1);

   Object visitTimestampadd(SqlBaseParser.TimestampaddContext var1);

   Object visitSubstring(SqlBaseParser.SubstringContext var1);

   Object visitCast(SqlBaseParser.CastContext var1);

   Object visitLambda(SqlBaseParser.LambdaContext var1);

   Object visitParenthesizedExpression(SqlBaseParser.ParenthesizedExpressionContext var1);

   Object visitAny_value(SqlBaseParser.Any_valueContext var1);

   Object visitTrim(SqlBaseParser.TrimContext var1);

   Object visitSimpleCase(SqlBaseParser.SimpleCaseContext var1);

   Object visitCurrentLike(SqlBaseParser.CurrentLikeContext var1);

   Object visitColumnReference(SqlBaseParser.ColumnReferenceContext var1);

   Object visitRowConstructor(SqlBaseParser.RowConstructorContext var1);

   Object visitLast(SqlBaseParser.LastContext var1);

   Object visitStar(SqlBaseParser.StarContext var1);

   Object visitOverlay(SqlBaseParser.OverlayContext var1);

   Object visitSubscript(SqlBaseParser.SubscriptContext var1);

   Object visitTimestampdiff(SqlBaseParser.TimestampdiffContext var1);

   Object visitSubqueryExpression(SqlBaseParser.SubqueryExpressionContext var1);

   Object visitCollate(SqlBaseParser.CollateContext var1);

   Object visitConstantDefault(SqlBaseParser.ConstantDefaultContext var1);

   Object visitExtract(SqlBaseParser.ExtractContext var1);

   Object visitFunctionCall(SqlBaseParser.FunctionCallContext var1);

   Object visitSearchedCase(SqlBaseParser.SearchedCaseContext var1);

   Object visitPosition(SqlBaseParser.PositionContext var1);

   Object visitFirst(SqlBaseParser.FirstContext var1);

   Object visitLiteralType(SqlBaseParser.LiteralTypeContext var1);

   Object visitNullLiteral(SqlBaseParser.NullLiteralContext var1);

   Object visitPosParameterLiteral(SqlBaseParser.PosParameterLiteralContext var1);

   Object visitNamedParameterLiteral(SqlBaseParser.NamedParameterLiteralContext var1);

   Object visitIntervalLiteral(SqlBaseParser.IntervalLiteralContext var1);

   Object visitTypeConstructor(SqlBaseParser.TypeConstructorContext var1);

   Object visitNumericLiteral(SqlBaseParser.NumericLiteralContext var1);

   Object visitBooleanLiteral(SqlBaseParser.BooleanLiteralContext var1);

   Object visitStringLiteral(SqlBaseParser.StringLiteralContext var1);

   Object visitComparisonOperator(SqlBaseParser.ComparisonOperatorContext var1);

   Object visitArithmeticOperator(SqlBaseParser.ArithmeticOperatorContext var1);

   Object visitPredicateOperator(SqlBaseParser.PredicateOperatorContext var1);

   Object visitBooleanValue(SqlBaseParser.BooleanValueContext var1);

   Object visitInterval(SqlBaseParser.IntervalContext var1);

   Object visitErrorCapturingMultiUnitsInterval(SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext var1);

   Object visitMultiUnitsInterval(SqlBaseParser.MultiUnitsIntervalContext var1);

   Object visitErrorCapturingUnitToUnitInterval(SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext var1);

   Object visitUnitToUnitInterval(SqlBaseParser.UnitToUnitIntervalContext var1);

   Object visitIntervalValue(SqlBaseParser.IntervalValueContext var1);

   Object visitUnitInMultiUnits(SqlBaseParser.UnitInMultiUnitsContext var1);

   Object visitUnitInUnitToUnit(SqlBaseParser.UnitInUnitToUnitContext var1);

   Object visitColPosition(SqlBaseParser.ColPositionContext var1);

   Object visitCollationSpec(SqlBaseParser.CollationSpecContext var1);

   Object visitCollateClause(SqlBaseParser.CollateClauseContext var1);

   Object visitType(SqlBaseParser.TypeContext var1);

   Object visitComplexDataType(SqlBaseParser.ComplexDataTypeContext var1);

   Object visitYearMonthIntervalDataType(SqlBaseParser.YearMonthIntervalDataTypeContext var1);

   Object visitDayTimeIntervalDataType(SqlBaseParser.DayTimeIntervalDataTypeContext var1);

   Object visitPrimitiveDataType(SqlBaseParser.PrimitiveDataTypeContext var1);

   Object visitQualifiedColTypeWithPositionList(SqlBaseParser.QualifiedColTypeWithPositionListContext var1);

   Object visitQualifiedColTypeWithPosition(SqlBaseParser.QualifiedColTypeWithPositionContext var1);

   Object visitColDefinitionDescriptorWithPosition(SqlBaseParser.ColDefinitionDescriptorWithPositionContext var1);

   Object visitDefaultExpression(SqlBaseParser.DefaultExpressionContext var1);

   Object visitVariableDefaultExpression(SqlBaseParser.VariableDefaultExpressionContext var1);

   Object visitColTypeList(SqlBaseParser.ColTypeListContext var1);

   Object visitColType(SqlBaseParser.ColTypeContext var1);

   Object visitColDefinitionList(SqlBaseParser.ColDefinitionListContext var1);

   Object visitColDefinition(SqlBaseParser.ColDefinitionContext var1);

   Object visitColDefinitionOption(SqlBaseParser.ColDefinitionOptionContext var1);

   Object visitGeneratedColumn(SqlBaseParser.GeneratedColumnContext var1);

   Object visitIdentityColumn(SqlBaseParser.IdentityColumnContext var1);

   Object visitIdentityColSpec(SqlBaseParser.IdentityColSpecContext var1);

   Object visitSequenceGeneratorOption(SqlBaseParser.SequenceGeneratorOptionContext var1);

   Object visitSequenceGeneratorStartOrStep(SqlBaseParser.SequenceGeneratorStartOrStepContext var1);

   Object visitComplexColTypeList(SqlBaseParser.ComplexColTypeListContext var1);

   Object visitComplexColType(SqlBaseParser.ComplexColTypeContext var1);

   Object visitRoutineCharacteristics(SqlBaseParser.RoutineCharacteristicsContext var1);

   Object visitRoutineLanguage(SqlBaseParser.RoutineLanguageContext var1);

   Object visitSpecificName(SqlBaseParser.SpecificNameContext var1);

   Object visitDeterministic(SqlBaseParser.DeterministicContext var1);

   Object visitSqlDataAccess(SqlBaseParser.SqlDataAccessContext var1);

   Object visitNullCall(SqlBaseParser.NullCallContext var1);

   Object visitRightsClause(SqlBaseParser.RightsClauseContext var1);

   Object visitWhenClause(SqlBaseParser.WhenClauseContext var1);

   Object visitWindowClause(SqlBaseParser.WindowClauseContext var1);

   Object visitNamedWindow(SqlBaseParser.NamedWindowContext var1);

   Object visitWindowRef(SqlBaseParser.WindowRefContext var1);

   Object visitWindowDef(SqlBaseParser.WindowDefContext var1);

   Object visitWindowFrame(SqlBaseParser.WindowFrameContext var1);

   Object visitFrameBound(SqlBaseParser.FrameBoundContext var1);

   Object visitQualifiedNameList(SqlBaseParser.QualifiedNameListContext var1);

   Object visitFunctionName(SqlBaseParser.FunctionNameContext var1);

   Object visitQualifiedName(SqlBaseParser.QualifiedNameContext var1);

   Object visitErrorCapturingIdentifier(SqlBaseParser.ErrorCapturingIdentifierContext var1);

   Object visitErrorIdent(SqlBaseParser.ErrorIdentContext var1);

   Object visitRealIdent(SqlBaseParser.RealIdentContext var1);

   Object visitIdentifier(SqlBaseParser.IdentifierContext var1);

   Object visitUnquotedIdentifier(SqlBaseParser.UnquotedIdentifierContext var1);

   Object visitQuotedIdentifierAlternative(SqlBaseParser.QuotedIdentifierAlternativeContext var1);

   Object visitQuotedIdentifier(SqlBaseParser.QuotedIdentifierContext var1);

   Object visitBackQuotedIdentifier(SqlBaseParser.BackQuotedIdentifierContext var1);

   Object visitExponentLiteral(SqlBaseParser.ExponentLiteralContext var1);

   Object visitDecimalLiteral(SqlBaseParser.DecimalLiteralContext var1);

   Object visitLegacyDecimalLiteral(SqlBaseParser.LegacyDecimalLiteralContext var1);

   Object visitIntegerLiteral(SqlBaseParser.IntegerLiteralContext var1);

   Object visitBigIntLiteral(SqlBaseParser.BigIntLiteralContext var1);

   Object visitSmallIntLiteral(SqlBaseParser.SmallIntLiteralContext var1);

   Object visitTinyIntLiteral(SqlBaseParser.TinyIntLiteralContext var1);

   Object visitDoubleLiteral(SqlBaseParser.DoubleLiteralContext var1);

   Object visitFloatLiteral(SqlBaseParser.FloatLiteralContext var1);

   Object visitBigDecimalLiteral(SqlBaseParser.BigDecimalLiteralContext var1);

   Object visitAlterColumnSpecList(SqlBaseParser.AlterColumnSpecListContext var1);

   Object visitAlterColumnSpec(SqlBaseParser.AlterColumnSpecContext var1);

   Object visitAlterColumnAction(SqlBaseParser.AlterColumnActionContext var1);

   Object visitStringLit(SqlBaseParser.StringLitContext var1);

   Object visitComment(SqlBaseParser.CommentContext var1);

   Object visitVersion(SqlBaseParser.VersionContext var1);

   Object visitOperatorPipeRightSide(SqlBaseParser.OperatorPipeRightSideContext var1);

   Object visitOperatorPipeSetAssignmentSeq(SqlBaseParser.OperatorPipeSetAssignmentSeqContext var1);

   Object visitAnsiNonReserved(SqlBaseParser.AnsiNonReservedContext var1);

   Object visitStrictNonReserved(SqlBaseParser.StrictNonReservedContext var1);

   Object visitNonReserved(SqlBaseParser.NonReservedContext var1);
}
