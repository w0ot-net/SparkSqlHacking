package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class SqlBaseParserBaseVisitor extends AbstractParseTreeVisitor implements SqlBaseParserVisitor {
   public Object visitCompoundOrSingleStatement(SqlBaseParser.CompoundOrSingleStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleCompoundStatement(SqlBaseParser.SingleCompoundStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBeginEndCompoundBlock(SqlBaseParser.BeginEndCompoundBlockContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCompoundBody(SqlBaseParser.CompoundBodyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCompoundStatement(SqlBaseParser.CompoundStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetVariableInsideSqlScript(SqlBaseParser.SetVariableInsideSqlScriptContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSqlStateValue(SqlBaseParser.SqlStateValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDeclareConditionStatement(SqlBaseParser.DeclareConditionStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitConditionValue(SqlBaseParser.ConditionValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitConditionValues(SqlBaseParser.ConditionValuesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDeclareHandlerStatement(SqlBaseParser.DeclareHandlerStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWhileStatement(SqlBaseParser.WhileStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIfElseStatement(SqlBaseParser.IfElseStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRepeatStatement(SqlBaseParser.RepeatStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLeaveStatement(SqlBaseParser.LeaveStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIterateStatement(SqlBaseParser.IterateStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSearchedCaseStatement(SqlBaseParser.SearchedCaseStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSimpleCaseStatement(SqlBaseParser.SimpleCaseStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLoopStatement(SqlBaseParser.LoopStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitForStatement(SqlBaseParser.ForStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleStatement(SqlBaseParser.SingleStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBeginLabel(SqlBaseParser.BeginLabelContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitEndLabel(SqlBaseParser.EndLabelContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleExpression(SqlBaseParser.SingleExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleMultipartIdentifier(SqlBaseParser.SingleMultipartIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleFunctionIdentifier(SqlBaseParser.SingleFunctionIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleDataType(SqlBaseParser.SingleDataTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleTableSchema(SqlBaseParser.SingleTableSchemaContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleRoutineParamList(SqlBaseParser.SingleRoutineParamListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStatementDefault(SqlBaseParser.StatementDefaultContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitVisitExecuteImmediate(SqlBaseParser.VisitExecuteImmediateContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDmlStatement(SqlBaseParser.DmlStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUse(SqlBaseParser.UseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUseNamespace(SqlBaseParser.UseNamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetCatalog(SqlBaseParser.SetCatalogContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateNamespace(SqlBaseParser.CreateNamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetNamespaceProperties(SqlBaseParser.SetNamespacePropertiesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnsetNamespaceProperties(SqlBaseParser.UnsetNamespacePropertiesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetNamespaceLocation(SqlBaseParser.SetNamespaceLocationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropNamespace(SqlBaseParser.DropNamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowNamespaces(SqlBaseParser.ShowNamespacesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateTable(SqlBaseParser.CreateTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateTableLike(SqlBaseParser.CreateTableLikeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitReplaceTable(SqlBaseParser.ReplaceTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAnalyze(SqlBaseParser.AnalyzeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAnalyzeTables(SqlBaseParser.AnalyzeTablesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAddTableColumns(SqlBaseParser.AddTableColumnsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRenameTableColumn(SqlBaseParser.RenameTableColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropTableColumns(SqlBaseParser.DropTableColumnsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRenameTable(SqlBaseParser.RenameTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetTableProperties(SqlBaseParser.SetTablePropertiesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnsetTableProperties(SqlBaseParser.UnsetTablePropertiesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterTableAlterColumn(SqlBaseParser.AlterTableAlterColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitHiveChangeColumn(SqlBaseParser.HiveChangeColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitHiveReplaceColumns(SqlBaseParser.HiveReplaceColumnsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetTableSerDe(SqlBaseParser.SetTableSerDeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAddTablePartition(SqlBaseParser.AddTablePartitionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRenameTablePartition(SqlBaseParser.RenameTablePartitionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropTablePartitions(SqlBaseParser.DropTablePartitionsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetTableLocation(SqlBaseParser.SetTableLocationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRecoverPartitions(SqlBaseParser.RecoverPartitionsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterClusterBy(SqlBaseParser.AlterClusterByContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterTableCollation(SqlBaseParser.AlterTableCollationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropTable(SqlBaseParser.DropTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropView(SqlBaseParser.DropViewContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateView(SqlBaseParser.CreateViewContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateTempViewUsing(SqlBaseParser.CreateTempViewUsingContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterViewQuery(SqlBaseParser.AlterViewQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterViewSchemaBinding(SqlBaseParser.AlterViewSchemaBindingContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateFunction(SqlBaseParser.CreateFunctionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateUserDefinedFunction(SqlBaseParser.CreateUserDefinedFunctionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropFunction(SqlBaseParser.DropFunctionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateVariable(SqlBaseParser.CreateVariableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropVariable(SqlBaseParser.DropVariableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExplain(SqlBaseParser.ExplainContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowTables(SqlBaseParser.ShowTablesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowTableExtended(SqlBaseParser.ShowTableExtendedContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowTblProperties(SqlBaseParser.ShowTblPropertiesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowColumns(SqlBaseParser.ShowColumnsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowViews(SqlBaseParser.ShowViewsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowPartitions(SqlBaseParser.ShowPartitionsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowFunctions(SqlBaseParser.ShowFunctionsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowCreateTable(SqlBaseParser.ShowCreateTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowCurrentNamespace(SqlBaseParser.ShowCurrentNamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShowCatalogs(SqlBaseParser.ShowCatalogsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDescribeFunction(SqlBaseParser.DescribeFunctionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDescribeNamespace(SqlBaseParser.DescribeNamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDescribeRelation(SqlBaseParser.DescribeRelationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDescribeQuery(SqlBaseParser.DescribeQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCommentNamespace(SqlBaseParser.CommentNamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCommentTable(SqlBaseParser.CommentTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRefreshTable(SqlBaseParser.RefreshTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRefreshFunction(SqlBaseParser.RefreshFunctionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRefreshResource(SqlBaseParser.RefreshResourceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCacheTable(SqlBaseParser.CacheTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUncacheTable(SqlBaseParser.UncacheTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitClearCache(SqlBaseParser.ClearCacheContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLoadData(SqlBaseParser.LoadDataContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTruncateTable(SqlBaseParser.TruncateTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRepairTable(SqlBaseParser.RepairTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitManageResource(SqlBaseParser.ManageResourceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateIndex(SqlBaseParser.CreateIndexContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDropIndex(SqlBaseParser.DropIndexContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCall(SqlBaseParser.CallContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFailNativeCommand(SqlBaseParser.FailNativeCommandContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFailSetRole(SqlBaseParser.FailSetRoleContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetTimeZone(SqlBaseParser.SetTimeZoneContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetVariable(SqlBaseParser.SetVariableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetQuotedConfiguration(SqlBaseParser.SetQuotedConfigurationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetConfiguration(SqlBaseParser.SetConfigurationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitResetQuotedConfiguration(SqlBaseParser.ResetQuotedConfigurationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitResetConfiguration(SqlBaseParser.ResetConfigurationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExecuteImmediate(SqlBaseParser.ExecuteImmediateContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExecuteImmediateUsing(SqlBaseParser.ExecuteImmediateUsingContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExecuteImmediateQueryParam(SqlBaseParser.ExecuteImmediateQueryParamContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExecuteImmediateArgument(SqlBaseParser.ExecuteImmediateArgumentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExecuteImmediateArgumentSeq(SqlBaseParser.ExecuteImmediateArgumentSeqContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTimezone(SqlBaseParser.TimezoneContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitConfigKey(SqlBaseParser.ConfigKeyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitConfigValue(SqlBaseParser.ConfigValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnsupportedHiveNativeCommands(SqlBaseParser.UnsupportedHiveNativeCommandsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateTableHeader(SqlBaseParser.CreateTableHeaderContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitReplaceTableHeader(SqlBaseParser.ReplaceTableHeaderContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitClusterBySpec(SqlBaseParser.ClusterBySpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBucketSpec(SqlBaseParser.BucketSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSkewSpec(SqlBaseParser.SkewSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLocationSpec(SqlBaseParser.LocationSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSchemaBinding(SqlBaseParser.SchemaBindingContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCommentSpec(SqlBaseParser.CommentSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleQuery(SqlBaseParser.SingleQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQuery(SqlBaseParser.QueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInsertIntoTable(SqlBaseParser.InsertIntoTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInsertIntoReplaceWhere(SqlBaseParser.InsertIntoReplaceWhereContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInsertOverwriteHiveDir(SqlBaseParser.InsertOverwriteHiveDirContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInsertOverwriteDir(SqlBaseParser.InsertOverwriteDirContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPartitionSpecLocation(SqlBaseParser.PartitionSpecLocationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPartitionSpec(SqlBaseParser.PartitionSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPartitionVal(SqlBaseParser.PartitionValContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamespace(SqlBaseParser.NamespaceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamespaces(SqlBaseParser.NamespacesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitVariable(SqlBaseParser.VariableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDescribeFuncName(SqlBaseParser.DescribeFuncNameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDescribeColName(SqlBaseParser.DescribeColNameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCtes(SqlBaseParser.CtesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamedQuery(SqlBaseParser.NamedQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableProvider(SqlBaseParser.TableProviderContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateTableClauses(SqlBaseParser.CreateTableClausesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPropertyList(SqlBaseParser.PropertyListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitProperty(SqlBaseParser.PropertyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPropertyKey(SqlBaseParser.PropertyKeyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPropertyValue(SqlBaseParser.PropertyValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExpressionPropertyList(SqlBaseParser.ExpressionPropertyListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExpressionProperty(SqlBaseParser.ExpressionPropertyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitConstantList(SqlBaseParser.ConstantListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNestedConstantList(SqlBaseParser.NestedConstantListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCreateFileFormat(SqlBaseParser.CreateFileFormatContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableFileFormat(SqlBaseParser.TableFileFormatContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitGenericFileFormat(SqlBaseParser.GenericFileFormatContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStorageHandler(SqlBaseParser.StorageHandlerContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitResource(SqlBaseParser.ResourceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSingleInsertQuery(SqlBaseParser.SingleInsertQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultiInsertQuery(SqlBaseParser.MultiInsertQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDeleteFromTable(SqlBaseParser.DeleteFromTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUpdateTable(SqlBaseParser.UpdateTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMergeIntoTable(SqlBaseParser.MergeIntoTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentifierReference(SqlBaseParser.IdentifierReferenceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCatalogIdentifierReference(SqlBaseParser.CatalogIdentifierReferenceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQueryOrganization(SqlBaseParser.QueryOrganizationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultiInsertQueryBody(SqlBaseParser.MultiInsertQueryBodyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOperatorPipeStatement(SqlBaseParser.OperatorPipeStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQueryTermDefault(SqlBaseParser.QueryTermDefaultContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetOperation(SqlBaseParser.SetOperationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQueryPrimaryDefault(SqlBaseParser.QueryPrimaryDefaultContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFromStmt(SqlBaseParser.FromStmtContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTable(SqlBaseParser.TableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInlineTableDefault1(SqlBaseParser.InlineTableDefault1Context ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSubquery(SqlBaseParser.SubqueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSortItem(SqlBaseParser.SortItemContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFromStatement(SqlBaseParser.FromStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFromStatementBody(SqlBaseParser.FromStatementBodyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTransformQuerySpecification(SqlBaseParser.TransformQuerySpecificationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRegularQuerySpecification(SqlBaseParser.RegularQuerySpecificationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTransformClause(SqlBaseParser.TransformClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSelectClause(SqlBaseParser.SelectClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetClause(SqlBaseParser.SetClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMatchedClause(SqlBaseParser.MatchedClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNotMatchedClause(SqlBaseParser.NotMatchedClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNotMatchedBySourceClause(SqlBaseParser.NotMatchedBySourceClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMatchedAction(SqlBaseParser.MatchedActionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNotMatchedAction(SqlBaseParser.NotMatchedActionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNotMatchedBySourceAction(SqlBaseParser.NotMatchedBySourceActionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExceptClause(SqlBaseParser.ExceptClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAssignmentList(SqlBaseParser.AssignmentListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAssignment(SqlBaseParser.AssignmentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWhereClause(SqlBaseParser.WhereClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitHavingClause(SqlBaseParser.HavingClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitHint(SqlBaseParser.HintContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitHintStatement(SqlBaseParser.HintStatementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFromClause(SqlBaseParser.FromClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTemporalClause(SqlBaseParser.TemporalClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAggregationClause(SqlBaseParser.AggregationClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitGroupByClause(SqlBaseParser.GroupByClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitGroupingAnalytics(SqlBaseParser.GroupingAnalyticsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitGroupingElement(SqlBaseParser.GroupingElementContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitGroupingSet(SqlBaseParser.GroupingSetContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPivotClause(SqlBaseParser.PivotClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPivotColumn(SqlBaseParser.PivotColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPivotValue(SqlBaseParser.PivotValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotClause(SqlBaseParser.UnpivotClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotNullClause(SqlBaseParser.UnpivotNullClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotOperator(SqlBaseParser.UnpivotOperatorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotSingleValueColumnClause(SqlBaseParser.UnpivotSingleValueColumnClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotMultiValueColumnClause(SqlBaseParser.UnpivotMultiValueColumnClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotColumnSet(SqlBaseParser.UnpivotColumnSetContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotValueColumn(SqlBaseParser.UnpivotValueColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotNameColumn(SqlBaseParser.UnpivotNameColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotColumnAndAlias(SqlBaseParser.UnpivotColumnAndAliasContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotColumn(SqlBaseParser.UnpivotColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnpivotAlias(SqlBaseParser.UnpivotAliasContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLateralView(SqlBaseParser.LateralViewContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSetQuantifier(SqlBaseParser.SetQuantifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRelation(SqlBaseParser.RelationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRelationExtension(SqlBaseParser.RelationExtensionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitJoinRelation(SqlBaseParser.JoinRelationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitJoinType(SqlBaseParser.JoinTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitJoinCriteria(SqlBaseParser.JoinCriteriaContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSample(SqlBaseParser.SampleContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSampleByPercentile(SqlBaseParser.SampleByPercentileContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSampleByRows(SqlBaseParser.SampleByRowsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSampleByBucket(SqlBaseParser.SampleByBucketContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSampleByBytes(SqlBaseParser.SampleByBytesContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentifierList(SqlBaseParser.IdentifierListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentifierSeq(SqlBaseParser.IdentifierSeqContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOrderedIdentifierList(SqlBaseParser.OrderedIdentifierListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOrderedIdentifier(SqlBaseParser.OrderedIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentifierCommentList(SqlBaseParser.IdentifierCommentListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentifierComment(SqlBaseParser.IdentifierCommentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableName(SqlBaseParser.TableNameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAliasedQuery(SqlBaseParser.AliasedQueryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAliasedRelation(SqlBaseParser.AliasedRelationContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInlineTableDefault2(SqlBaseParser.InlineTableDefault2Context ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableValuedFunction(SqlBaseParser.TableValuedFunctionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOptionsClause(SqlBaseParser.OptionsClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInlineTable(SqlBaseParser.InlineTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionTableSubqueryArgument(SqlBaseParser.FunctionTableSubqueryArgumentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableArgumentPartitioning(SqlBaseParser.TableArgumentPartitioningContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionTableNamedArgumentExpression(SqlBaseParser.FunctionTableNamedArgumentExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionTableReferenceArgument(SqlBaseParser.FunctionTableReferenceArgumentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionTableArgument(SqlBaseParser.FunctionTableArgumentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionTable(SqlBaseParser.FunctionTableContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableAlias(SqlBaseParser.TableAliasContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRowFormatSerde(SqlBaseParser.RowFormatSerdeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRowFormatDelimited(SqlBaseParser.RowFormatDelimitedContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultipartIdentifierList(SqlBaseParser.MultipartIdentifierListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultipartIdentifier(SqlBaseParser.MultipartIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultipartIdentifierPropertyList(SqlBaseParser.MultipartIdentifierPropertyListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultipartIdentifierProperty(SqlBaseParser.MultipartIdentifierPropertyContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionIdentifier(SqlBaseParser.FunctionIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamedExpression(SqlBaseParser.NamedExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamedExpressionSeq(SqlBaseParser.NamedExpressionSeqContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPartitionFieldList(SqlBaseParser.PartitionFieldListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPartitionTransform(SqlBaseParser.PartitionTransformContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPartitionColumn(SqlBaseParser.PartitionColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentityTransform(SqlBaseParser.IdentityTransformContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitApplyTransform(SqlBaseParser.ApplyTransformContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTransformArgument(SqlBaseParser.TransformArgumentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExpression(SqlBaseParser.ExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamedArgumentExpression(SqlBaseParser.NamedArgumentExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionArgument(SqlBaseParser.FunctionArgumentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExpressionSeq(SqlBaseParser.ExpressionSeqContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLogicalNot(SqlBaseParser.LogicalNotContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPredicated(SqlBaseParser.PredicatedContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExists(SqlBaseParser.ExistsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLogicalBinary(SqlBaseParser.LogicalBinaryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPredicate(SqlBaseParser.PredicateContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitErrorCapturingNot(SqlBaseParser.ErrorCapturingNotContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitValueExpressionDefault(SqlBaseParser.ValueExpressionDefaultContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitComparison(SqlBaseParser.ComparisonContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShiftExpression(SqlBaseParser.ShiftExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitArithmeticUnary(SqlBaseParser.ArithmeticUnaryContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitShiftOperator(SqlBaseParser.ShiftOperatorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDatetimeUnit(SqlBaseParser.DatetimeUnitContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStruct(SqlBaseParser.StructContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDereference(SqlBaseParser.DereferenceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCastByColon(SqlBaseParser.CastByColonContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTimestampadd(SqlBaseParser.TimestampaddContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSubstring(SqlBaseParser.SubstringContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCast(SqlBaseParser.CastContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLambda(SqlBaseParser.LambdaContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitParenthesizedExpression(SqlBaseParser.ParenthesizedExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAny_value(SqlBaseParser.Any_valueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTrim(SqlBaseParser.TrimContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSimpleCase(SqlBaseParser.SimpleCaseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCurrentLike(SqlBaseParser.CurrentLikeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColumnReference(SqlBaseParser.ColumnReferenceContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRowConstructor(SqlBaseParser.RowConstructorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLast(SqlBaseParser.LastContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStar(SqlBaseParser.StarContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOverlay(SqlBaseParser.OverlayContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSubscript(SqlBaseParser.SubscriptContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTimestampdiff(SqlBaseParser.TimestampdiffContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSubqueryExpression(SqlBaseParser.SubqueryExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCollate(SqlBaseParser.CollateContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitConstantDefault(SqlBaseParser.ConstantDefaultContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExtract(SqlBaseParser.ExtractContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionCall(SqlBaseParser.FunctionCallContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSearchedCase(SqlBaseParser.SearchedCaseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPosition(SqlBaseParser.PositionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFirst(SqlBaseParser.FirstContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLiteralType(SqlBaseParser.LiteralTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNullLiteral(SqlBaseParser.NullLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPosParameterLiteral(SqlBaseParser.PosParameterLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamedParameterLiteral(SqlBaseParser.NamedParameterLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIntervalLiteral(SqlBaseParser.IntervalLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTypeConstructor(SqlBaseParser.TypeConstructorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNumericLiteral(SqlBaseParser.NumericLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBooleanLiteral(SqlBaseParser.BooleanLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStringLiteral(SqlBaseParser.StringLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitComparisonOperator(SqlBaseParser.ComparisonOperatorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitArithmeticOperator(SqlBaseParser.ArithmeticOperatorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPredicateOperator(SqlBaseParser.PredicateOperatorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBooleanValue(SqlBaseParser.BooleanValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInterval(SqlBaseParser.IntervalContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitErrorCapturingMultiUnitsInterval(SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultiUnitsInterval(SqlBaseParser.MultiUnitsIntervalContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitErrorCapturingUnitToUnitInterval(SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnitToUnitInterval(SqlBaseParser.UnitToUnitIntervalContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIntervalValue(SqlBaseParser.IntervalValueContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnitInMultiUnits(SqlBaseParser.UnitInMultiUnitsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnitInUnitToUnit(SqlBaseParser.UnitInUnitToUnitContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColPosition(SqlBaseParser.ColPositionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCollationSpec(SqlBaseParser.CollationSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCollateClause(SqlBaseParser.CollateClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitType(SqlBaseParser.TypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitComplexDataType(SqlBaseParser.ComplexDataTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitYearMonthIntervalDataType(SqlBaseParser.YearMonthIntervalDataTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDayTimeIntervalDataType(SqlBaseParser.DayTimeIntervalDataTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPrimitiveDataType(SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQualifiedColTypeWithPositionList(SqlBaseParser.QualifiedColTypeWithPositionListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQualifiedColTypeWithPosition(SqlBaseParser.QualifiedColTypeWithPositionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColDefinitionDescriptorWithPosition(SqlBaseParser.ColDefinitionDescriptorWithPositionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDefaultExpression(SqlBaseParser.DefaultExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitVariableDefaultExpression(SqlBaseParser.VariableDefaultExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColTypeList(SqlBaseParser.ColTypeListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColType(SqlBaseParser.ColTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColDefinitionList(SqlBaseParser.ColDefinitionListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColDefinition(SqlBaseParser.ColDefinitionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitColDefinitionOption(SqlBaseParser.ColDefinitionOptionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitGeneratedColumn(SqlBaseParser.GeneratedColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentityColumn(SqlBaseParser.IdentityColumnContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentityColSpec(SqlBaseParser.IdentityColSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSequenceGeneratorOption(SqlBaseParser.SequenceGeneratorOptionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSequenceGeneratorStartOrStep(SqlBaseParser.SequenceGeneratorStartOrStepContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitComplexColTypeList(SqlBaseParser.ComplexColTypeListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitComplexColType(SqlBaseParser.ComplexColTypeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRoutineCharacteristics(SqlBaseParser.RoutineCharacteristicsContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRoutineLanguage(SqlBaseParser.RoutineLanguageContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSpecificName(SqlBaseParser.SpecificNameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDeterministic(SqlBaseParser.DeterministicContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSqlDataAccess(SqlBaseParser.SqlDataAccessContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNullCall(SqlBaseParser.NullCallContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRightsClause(SqlBaseParser.RightsClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWhenClause(SqlBaseParser.WhenClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWindowClause(SqlBaseParser.WindowClauseContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNamedWindow(SqlBaseParser.NamedWindowContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWindowRef(SqlBaseParser.WindowRefContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWindowDef(SqlBaseParser.WindowDefContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitWindowFrame(SqlBaseParser.WindowFrameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFrameBound(SqlBaseParser.FrameBoundContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQualifiedNameList(SqlBaseParser.QualifiedNameListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFunctionName(SqlBaseParser.FunctionNameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQualifiedName(SqlBaseParser.QualifiedNameContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitErrorCapturingIdentifier(SqlBaseParser.ErrorCapturingIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitErrorIdent(SqlBaseParser.ErrorIdentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitRealIdent(SqlBaseParser.RealIdentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIdentifier(SqlBaseParser.IdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitUnquotedIdentifier(SqlBaseParser.UnquotedIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQuotedIdentifierAlternative(SqlBaseParser.QuotedIdentifierAlternativeContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitQuotedIdentifier(SqlBaseParser.QuotedIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBackQuotedIdentifier(SqlBaseParser.BackQuotedIdentifierContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitExponentLiteral(SqlBaseParser.ExponentLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDecimalLiteral(SqlBaseParser.DecimalLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitLegacyDecimalLiteral(SqlBaseParser.LegacyDecimalLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitIntegerLiteral(SqlBaseParser.IntegerLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBigIntLiteral(SqlBaseParser.BigIntLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitSmallIntLiteral(SqlBaseParser.SmallIntLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitTinyIntLiteral(SqlBaseParser.TinyIntLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDoubleLiteral(SqlBaseParser.DoubleLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFloatLiteral(SqlBaseParser.FloatLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitBigDecimalLiteral(SqlBaseParser.BigDecimalLiteralContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterColumnSpecList(SqlBaseParser.AlterColumnSpecListContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterColumnSpec(SqlBaseParser.AlterColumnSpecContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAlterColumnAction(SqlBaseParser.AlterColumnActionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStringLit(SqlBaseParser.StringLitContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitComment(SqlBaseParser.CommentContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitVersion(SqlBaseParser.VersionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOperatorPipeRightSide(SqlBaseParser.OperatorPipeRightSideContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitOperatorPipeSetAssignmentSeq(SqlBaseParser.OperatorPipeSetAssignmentSeqContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAnsiNonReserved(SqlBaseParser.AnsiNonReservedContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitStrictNonReserved(SqlBaseParser.StrictNonReservedContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitNonReserved(SqlBaseParser.NonReservedContext ctx) {
      return this.visitChildren(ctx);
   }
}
