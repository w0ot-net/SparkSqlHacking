package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface SqlBaseParserListener extends ParseTreeListener {
   void enterCompoundOrSingleStatement(SqlBaseParser.CompoundOrSingleStatementContext var1);

   void exitCompoundOrSingleStatement(SqlBaseParser.CompoundOrSingleStatementContext var1);

   void enterSingleCompoundStatement(SqlBaseParser.SingleCompoundStatementContext var1);

   void exitSingleCompoundStatement(SqlBaseParser.SingleCompoundStatementContext var1);

   void enterBeginEndCompoundBlock(SqlBaseParser.BeginEndCompoundBlockContext var1);

   void exitBeginEndCompoundBlock(SqlBaseParser.BeginEndCompoundBlockContext var1);

   void enterCompoundBody(SqlBaseParser.CompoundBodyContext var1);

   void exitCompoundBody(SqlBaseParser.CompoundBodyContext var1);

   void enterCompoundStatement(SqlBaseParser.CompoundStatementContext var1);

   void exitCompoundStatement(SqlBaseParser.CompoundStatementContext var1);

   void enterSetVariableInsideSqlScript(SqlBaseParser.SetVariableInsideSqlScriptContext var1);

   void exitSetVariableInsideSqlScript(SqlBaseParser.SetVariableInsideSqlScriptContext var1);

   void enterSqlStateValue(SqlBaseParser.SqlStateValueContext var1);

   void exitSqlStateValue(SqlBaseParser.SqlStateValueContext var1);

   void enterDeclareConditionStatement(SqlBaseParser.DeclareConditionStatementContext var1);

   void exitDeclareConditionStatement(SqlBaseParser.DeclareConditionStatementContext var1);

   void enterConditionValue(SqlBaseParser.ConditionValueContext var1);

   void exitConditionValue(SqlBaseParser.ConditionValueContext var1);

   void enterConditionValues(SqlBaseParser.ConditionValuesContext var1);

   void exitConditionValues(SqlBaseParser.ConditionValuesContext var1);

   void enterDeclareHandlerStatement(SqlBaseParser.DeclareHandlerStatementContext var1);

   void exitDeclareHandlerStatement(SqlBaseParser.DeclareHandlerStatementContext var1);

   void enterWhileStatement(SqlBaseParser.WhileStatementContext var1);

   void exitWhileStatement(SqlBaseParser.WhileStatementContext var1);

   void enterIfElseStatement(SqlBaseParser.IfElseStatementContext var1);

   void exitIfElseStatement(SqlBaseParser.IfElseStatementContext var1);

   void enterRepeatStatement(SqlBaseParser.RepeatStatementContext var1);

   void exitRepeatStatement(SqlBaseParser.RepeatStatementContext var1);

   void enterLeaveStatement(SqlBaseParser.LeaveStatementContext var1);

   void exitLeaveStatement(SqlBaseParser.LeaveStatementContext var1);

   void enterIterateStatement(SqlBaseParser.IterateStatementContext var1);

   void exitIterateStatement(SqlBaseParser.IterateStatementContext var1);

   void enterSearchedCaseStatement(SqlBaseParser.SearchedCaseStatementContext var1);

   void exitSearchedCaseStatement(SqlBaseParser.SearchedCaseStatementContext var1);

   void enterSimpleCaseStatement(SqlBaseParser.SimpleCaseStatementContext var1);

   void exitSimpleCaseStatement(SqlBaseParser.SimpleCaseStatementContext var1);

   void enterLoopStatement(SqlBaseParser.LoopStatementContext var1);

   void exitLoopStatement(SqlBaseParser.LoopStatementContext var1);

   void enterForStatement(SqlBaseParser.ForStatementContext var1);

   void exitForStatement(SqlBaseParser.ForStatementContext var1);

   void enterSingleStatement(SqlBaseParser.SingleStatementContext var1);

   void exitSingleStatement(SqlBaseParser.SingleStatementContext var1);

   void enterBeginLabel(SqlBaseParser.BeginLabelContext var1);

   void exitBeginLabel(SqlBaseParser.BeginLabelContext var1);

   void enterEndLabel(SqlBaseParser.EndLabelContext var1);

   void exitEndLabel(SqlBaseParser.EndLabelContext var1);

   void enterSingleExpression(SqlBaseParser.SingleExpressionContext var1);

   void exitSingleExpression(SqlBaseParser.SingleExpressionContext var1);

   void enterSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext var1);

   void exitSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext var1);

   void enterSingleMultipartIdentifier(SqlBaseParser.SingleMultipartIdentifierContext var1);

   void exitSingleMultipartIdentifier(SqlBaseParser.SingleMultipartIdentifierContext var1);

   void enterSingleFunctionIdentifier(SqlBaseParser.SingleFunctionIdentifierContext var1);

   void exitSingleFunctionIdentifier(SqlBaseParser.SingleFunctionIdentifierContext var1);

   void enterSingleDataType(SqlBaseParser.SingleDataTypeContext var1);

   void exitSingleDataType(SqlBaseParser.SingleDataTypeContext var1);

   void enterSingleTableSchema(SqlBaseParser.SingleTableSchemaContext var1);

   void exitSingleTableSchema(SqlBaseParser.SingleTableSchemaContext var1);

   void enterSingleRoutineParamList(SqlBaseParser.SingleRoutineParamListContext var1);

   void exitSingleRoutineParamList(SqlBaseParser.SingleRoutineParamListContext var1);

   void enterStatementDefault(SqlBaseParser.StatementDefaultContext var1);

   void exitStatementDefault(SqlBaseParser.StatementDefaultContext var1);

   void enterVisitExecuteImmediate(SqlBaseParser.VisitExecuteImmediateContext var1);

   void exitVisitExecuteImmediate(SqlBaseParser.VisitExecuteImmediateContext var1);

   void enterDmlStatement(SqlBaseParser.DmlStatementContext var1);

   void exitDmlStatement(SqlBaseParser.DmlStatementContext var1);

   void enterUse(SqlBaseParser.UseContext var1);

   void exitUse(SqlBaseParser.UseContext var1);

   void enterUseNamespace(SqlBaseParser.UseNamespaceContext var1);

   void exitUseNamespace(SqlBaseParser.UseNamespaceContext var1);

   void enterSetCatalog(SqlBaseParser.SetCatalogContext var1);

   void exitSetCatalog(SqlBaseParser.SetCatalogContext var1);

   void enterCreateNamespace(SqlBaseParser.CreateNamespaceContext var1);

   void exitCreateNamespace(SqlBaseParser.CreateNamespaceContext var1);

   void enterSetNamespaceProperties(SqlBaseParser.SetNamespacePropertiesContext var1);

   void exitSetNamespaceProperties(SqlBaseParser.SetNamespacePropertiesContext var1);

   void enterUnsetNamespaceProperties(SqlBaseParser.UnsetNamespacePropertiesContext var1);

   void exitUnsetNamespaceProperties(SqlBaseParser.UnsetNamespacePropertiesContext var1);

   void enterSetNamespaceLocation(SqlBaseParser.SetNamespaceLocationContext var1);

   void exitSetNamespaceLocation(SqlBaseParser.SetNamespaceLocationContext var1);

   void enterDropNamespace(SqlBaseParser.DropNamespaceContext var1);

   void exitDropNamespace(SqlBaseParser.DropNamespaceContext var1);

   void enterShowNamespaces(SqlBaseParser.ShowNamespacesContext var1);

   void exitShowNamespaces(SqlBaseParser.ShowNamespacesContext var1);

   void enterCreateTable(SqlBaseParser.CreateTableContext var1);

   void exitCreateTable(SqlBaseParser.CreateTableContext var1);

   void enterCreateTableLike(SqlBaseParser.CreateTableLikeContext var1);

   void exitCreateTableLike(SqlBaseParser.CreateTableLikeContext var1);

   void enterReplaceTable(SqlBaseParser.ReplaceTableContext var1);

   void exitReplaceTable(SqlBaseParser.ReplaceTableContext var1);

   void enterAnalyze(SqlBaseParser.AnalyzeContext var1);

   void exitAnalyze(SqlBaseParser.AnalyzeContext var1);

   void enterAnalyzeTables(SqlBaseParser.AnalyzeTablesContext var1);

   void exitAnalyzeTables(SqlBaseParser.AnalyzeTablesContext var1);

   void enterAddTableColumns(SqlBaseParser.AddTableColumnsContext var1);

   void exitAddTableColumns(SqlBaseParser.AddTableColumnsContext var1);

   void enterRenameTableColumn(SqlBaseParser.RenameTableColumnContext var1);

   void exitRenameTableColumn(SqlBaseParser.RenameTableColumnContext var1);

   void enterDropTableColumns(SqlBaseParser.DropTableColumnsContext var1);

   void exitDropTableColumns(SqlBaseParser.DropTableColumnsContext var1);

   void enterRenameTable(SqlBaseParser.RenameTableContext var1);

   void exitRenameTable(SqlBaseParser.RenameTableContext var1);

   void enterSetTableProperties(SqlBaseParser.SetTablePropertiesContext var1);

   void exitSetTableProperties(SqlBaseParser.SetTablePropertiesContext var1);

   void enterUnsetTableProperties(SqlBaseParser.UnsetTablePropertiesContext var1);

   void exitUnsetTableProperties(SqlBaseParser.UnsetTablePropertiesContext var1);

   void enterAlterTableAlterColumn(SqlBaseParser.AlterTableAlterColumnContext var1);

   void exitAlterTableAlterColumn(SqlBaseParser.AlterTableAlterColumnContext var1);

   void enterHiveChangeColumn(SqlBaseParser.HiveChangeColumnContext var1);

   void exitHiveChangeColumn(SqlBaseParser.HiveChangeColumnContext var1);

   void enterHiveReplaceColumns(SqlBaseParser.HiveReplaceColumnsContext var1);

   void exitHiveReplaceColumns(SqlBaseParser.HiveReplaceColumnsContext var1);

   void enterSetTableSerDe(SqlBaseParser.SetTableSerDeContext var1);

   void exitSetTableSerDe(SqlBaseParser.SetTableSerDeContext var1);

   void enterAddTablePartition(SqlBaseParser.AddTablePartitionContext var1);

   void exitAddTablePartition(SqlBaseParser.AddTablePartitionContext var1);

   void enterRenameTablePartition(SqlBaseParser.RenameTablePartitionContext var1);

   void exitRenameTablePartition(SqlBaseParser.RenameTablePartitionContext var1);

   void enterDropTablePartitions(SqlBaseParser.DropTablePartitionsContext var1);

   void exitDropTablePartitions(SqlBaseParser.DropTablePartitionsContext var1);

   void enterSetTableLocation(SqlBaseParser.SetTableLocationContext var1);

   void exitSetTableLocation(SqlBaseParser.SetTableLocationContext var1);

   void enterRecoverPartitions(SqlBaseParser.RecoverPartitionsContext var1);

   void exitRecoverPartitions(SqlBaseParser.RecoverPartitionsContext var1);

   void enterAlterClusterBy(SqlBaseParser.AlterClusterByContext var1);

   void exitAlterClusterBy(SqlBaseParser.AlterClusterByContext var1);

   void enterAlterTableCollation(SqlBaseParser.AlterTableCollationContext var1);

   void exitAlterTableCollation(SqlBaseParser.AlterTableCollationContext var1);

   void enterDropTable(SqlBaseParser.DropTableContext var1);

   void exitDropTable(SqlBaseParser.DropTableContext var1);

   void enterDropView(SqlBaseParser.DropViewContext var1);

   void exitDropView(SqlBaseParser.DropViewContext var1);

   void enterCreateView(SqlBaseParser.CreateViewContext var1);

   void exitCreateView(SqlBaseParser.CreateViewContext var1);

   void enterCreateTempViewUsing(SqlBaseParser.CreateTempViewUsingContext var1);

   void exitCreateTempViewUsing(SqlBaseParser.CreateTempViewUsingContext var1);

   void enterAlterViewQuery(SqlBaseParser.AlterViewQueryContext var1);

   void exitAlterViewQuery(SqlBaseParser.AlterViewQueryContext var1);

   void enterAlterViewSchemaBinding(SqlBaseParser.AlterViewSchemaBindingContext var1);

   void exitAlterViewSchemaBinding(SqlBaseParser.AlterViewSchemaBindingContext var1);

   void enterCreateFunction(SqlBaseParser.CreateFunctionContext var1);

   void exitCreateFunction(SqlBaseParser.CreateFunctionContext var1);

   void enterCreateUserDefinedFunction(SqlBaseParser.CreateUserDefinedFunctionContext var1);

   void exitCreateUserDefinedFunction(SqlBaseParser.CreateUserDefinedFunctionContext var1);

   void enterDropFunction(SqlBaseParser.DropFunctionContext var1);

   void exitDropFunction(SqlBaseParser.DropFunctionContext var1);

   void enterCreateVariable(SqlBaseParser.CreateVariableContext var1);

   void exitCreateVariable(SqlBaseParser.CreateVariableContext var1);

   void enterDropVariable(SqlBaseParser.DropVariableContext var1);

   void exitDropVariable(SqlBaseParser.DropVariableContext var1);

   void enterExplain(SqlBaseParser.ExplainContext var1);

   void exitExplain(SqlBaseParser.ExplainContext var1);

   void enterShowTables(SqlBaseParser.ShowTablesContext var1);

   void exitShowTables(SqlBaseParser.ShowTablesContext var1);

   void enterShowTableExtended(SqlBaseParser.ShowTableExtendedContext var1);

   void exitShowTableExtended(SqlBaseParser.ShowTableExtendedContext var1);

   void enterShowTblProperties(SqlBaseParser.ShowTblPropertiesContext var1);

   void exitShowTblProperties(SqlBaseParser.ShowTblPropertiesContext var1);

   void enterShowColumns(SqlBaseParser.ShowColumnsContext var1);

   void exitShowColumns(SqlBaseParser.ShowColumnsContext var1);

   void enterShowViews(SqlBaseParser.ShowViewsContext var1);

   void exitShowViews(SqlBaseParser.ShowViewsContext var1);

   void enterShowPartitions(SqlBaseParser.ShowPartitionsContext var1);

   void exitShowPartitions(SqlBaseParser.ShowPartitionsContext var1);

   void enterShowFunctions(SqlBaseParser.ShowFunctionsContext var1);

   void exitShowFunctions(SqlBaseParser.ShowFunctionsContext var1);

   void enterShowCreateTable(SqlBaseParser.ShowCreateTableContext var1);

   void exitShowCreateTable(SqlBaseParser.ShowCreateTableContext var1);

   void enterShowCurrentNamespace(SqlBaseParser.ShowCurrentNamespaceContext var1);

   void exitShowCurrentNamespace(SqlBaseParser.ShowCurrentNamespaceContext var1);

   void enterShowCatalogs(SqlBaseParser.ShowCatalogsContext var1);

   void exitShowCatalogs(SqlBaseParser.ShowCatalogsContext var1);

   void enterDescribeFunction(SqlBaseParser.DescribeFunctionContext var1);

   void exitDescribeFunction(SqlBaseParser.DescribeFunctionContext var1);

   void enterDescribeNamespace(SqlBaseParser.DescribeNamespaceContext var1);

   void exitDescribeNamespace(SqlBaseParser.DescribeNamespaceContext var1);

   void enterDescribeRelation(SqlBaseParser.DescribeRelationContext var1);

   void exitDescribeRelation(SqlBaseParser.DescribeRelationContext var1);

   void enterDescribeQuery(SqlBaseParser.DescribeQueryContext var1);

   void exitDescribeQuery(SqlBaseParser.DescribeQueryContext var1);

   void enterCommentNamespace(SqlBaseParser.CommentNamespaceContext var1);

   void exitCommentNamespace(SqlBaseParser.CommentNamespaceContext var1);

   void enterCommentTable(SqlBaseParser.CommentTableContext var1);

   void exitCommentTable(SqlBaseParser.CommentTableContext var1);

   void enterRefreshTable(SqlBaseParser.RefreshTableContext var1);

   void exitRefreshTable(SqlBaseParser.RefreshTableContext var1);

   void enterRefreshFunction(SqlBaseParser.RefreshFunctionContext var1);

   void exitRefreshFunction(SqlBaseParser.RefreshFunctionContext var1);

   void enterRefreshResource(SqlBaseParser.RefreshResourceContext var1);

   void exitRefreshResource(SqlBaseParser.RefreshResourceContext var1);

   void enterCacheTable(SqlBaseParser.CacheTableContext var1);

   void exitCacheTable(SqlBaseParser.CacheTableContext var1);

   void enterUncacheTable(SqlBaseParser.UncacheTableContext var1);

   void exitUncacheTable(SqlBaseParser.UncacheTableContext var1);

   void enterClearCache(SqlBaseParser.ClearCacheContext var1);

   void exitClearCache(SqlBaseParser.ClearCacheContext var1);

   void enterLoadData(SqlBaseParser.LoadDataContext var1);

   void exitLoadData(SqlBaseParser.LoadDataContext var1);

   void enterTruncateTable(SqlBaseParser.TruncateTableContext var1);

   void exitTruncateTable(SqlBaseParser.TruncateTableContext var1);

   void enterRepairTable(SqlBaseParser.RepairTableContext var1);

   void exitRepairTable(SqlBaseParser.RepairTableContext var1);

   void enterManageResource(SqlBaseParser.ManageResourceContext var1);

   void exitManageResource(SqlBaseParser.ManageResourceContext var1);

   void enterCreateIndex(SqlBaseParser.CreateIndexContext var1);

   void exitCreateIndex(SqlBaseParser.CreateIndexContext var1);

   void enterDropIndex(SqlBaseParser.DropIndexContext var1);

   void exitDropIndex(SqlBaseParser.DropIndexContext var1);

   void enterCall(SqlBaseParser.CallContext var1);

   void exitCall(SqlBaseParser.CallContext var1);

   void enterFailNativeCommand(SqlBaseParser.FailNativeCommandContext var1);

   void exitFailNativeCommand(SqlBaseParser.FailNativeCommandContext var1);

   void enterFailSetRole(SqlBaseParser.FailSetRoleContext var1);

   void exitFailSetRole(SqlBaseParser.FailSetRoleContext var1);

   void enterSetTimeZone(SqlBaseParser.SetTimeZoneContext var1);

   void exitSetTimeZone(SqlBaseParser.SetTimeZoneContext var1);

   void enterSetVariable(SqlBaseParser.SetVariableContext var1);

   void exitSetVariable(SqlBaseParser.SetVariableContext var1);

   void enterSetQuotedConfiguration(SqlBaseParser.SetQuotedConfigurationContext var1);

   void exitSetQuotedConfiguration(SqlBaseParser.SetQuotedConfigurationContext var1);

   void enterSetConfiguration(SqlBaseParser.SetConfigurationContext var1);

   void exitSetConfiguration(SqlBaseParser.SetConfigurationContext var1);

   void enterResetQuotedConfiguration(SqlBaseParser.ResetQuotedConfigurationContext var1);

   void exitResetQuotedConfiguration(SqlBaseParser.ResetQuotedConfigurationContext var1);

   void enterResetConfiguration(SqlBaseParser.ResetConfigurationContext var1);

   void exitResetConfiguration(SqlBaseParser.ResetConfigurationContext var1);

   void enterExecuteImmediate(SqlBaseParser.ExecuteImmediateContext var1);

   void exitExecuteImmediate(SqlBaseParser.ExecuteImmediateContext var1);

   void enterExecuteImmediateUsing(SqlBaseParser.ExecuteImmediateUsingContext var1);

   void exitExecuteImmediateUsing(SqlBaseParser.ExecuteImmediateUsingContext var1);

   void enterExecuteImmediateQueryParam(SqlBaseParser.ExecuteImmediateQueryParamContext var1);

   void exitExecuteImmediateQueryParam(SqlBaseParser.ExecuteImmediateQueryParamContext var1);

   void enterExecuteImmediateArgument(SqlBaseParser.ExecuteImmediateArgumentContext var1);

   void exitExecuteImmediateArgument(SqlBaseParser.ExecuteImmediateArgumentContext var1);

   void enterExecuteImmediateArgumentSeq(SqlBaseParser.ExecuteImmediateArgumentSeqContext var1);

   void exitExecuteImmediateArgumentSeq(SqlBaseParser.ExecuteImmediateArgumentSeqContext var1);

   void enterTimezone(SqlBaseParser.TimezoneContext var1);

   void exitTimezone(SqlBaseParser.TimezoneContext var1);

   void enterConfigKey(SqlBaseParser.ConfigKeyContext var1);

   void exitConfigKey(SqlBaseParser.ConfigKeyContext var1);

   void enterConfigValue(SqlBaseParser.ConfigValueContext var1);

   void exitConfigValue(SqlBaseParser.ConfigValueContext var1);

   void enterUnsupportedHiveNativeCommands(SqlBaseParser.UnsupportedHiveNativeCommandsContext var1);

   void exitUnsupportedHiveNativeCommands(SqlBaseParser.UnsupportedHiveNativeCommandsContext var1);

   void enterCreateTableHeader(SqlBaseParser.CreateTableHeaderContext var1);

   void exitCreateTableHeader(SqlBaseParser.CreateTableHeaderContext var1);

   void enterReplaceTableHeader(SqlBaseParser.ReplaceTableHeaderContext var1);

   void exitReplaceTableHeader(SqlBaseParser.ReplaceTableHeaderContext var1);

   void enterClusterBySpec(SqlBaseParser.ClusterBySpecContext var1);

   void exitClusterBySpec(SqlBaseParser.ClusterBySpecContext var1);

   void enterBucketSpec(SqlBaseParser.BucketSpecContext var1);

   void exitBucketSpec(SqlBaseParser.BucketSpecContext var1);

   void enterSkewSpec(SqlBaseParser.SkewSpecContext var1);

   void exitSkewSpec(SqlBaseParser.SkewSpecContext var1);

   void enterLocationSpec(SqlBaseParser.LocationSpecContext var1);

   void exitLocationSpec(SqlBaseParser.LocationSpecContext var1);

   void enterSchemaBinding(SqlBaseParser.SchemaBindingContext var1);

   void exitSchemaBinding(SqlBaseParser.SchemaBindingContext var1);

   void enterCommentSpec(SqlBaseParser.CommentSpecContext var1);

   void exitCommentSpec(SqlBaseParser.CommentSpecContext var1);

   void enterSingleQuery(SqlBaseParser.SingleQueryContext var1);

   void exitSingleQuery(SqlBaseParser.SingleQueryContext var1);

   void enterQuery(SqlBaseParser.QueryContext var1);

   void exitQuery(SqlBaseParser.QueryContext var1);

   void enterInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext var1);

   void exitInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext var1);

   void enterInsertIntoTable(SqlBaseParser.InsertIntoTableContext var1);

   void exitInsertIntoTable(SqlBaseParser.InsertIntoTableContext var1);

   void enterInsertIntoReplaceWhere(SqlBaseParser.InsertIntoReplaceWhereContext var1);

   void exitInsertIntoReplaceWhere(SqlBaseParser.InsertIntoReplaceWhereContext var1);

   void enterInsertOverwriteHiveDir(SqlBaseParser.InsertOverwriteHiveDirContext var1);

   void exitInsertOverwriteHiveDir(SqlBaseParser.InsertOverwriteHiveDirContext var1);

   void enterInsertOverwriteDir(SqlBaseParser.InsertOverwriteDirContext var1);

   void exitInsertOverwriteDir(SqlBaseParser.InsertOverwriteDirContext var1);

   void enterPartitionSpecLocation(SqlBaseParser.PartitionSpecLocationContext var1);

   void exitPartitionSpecLocation(SqlBaseParser.PartitionSpecLocationContext var1);

   void enterPartitionSpec(SqlBaseParser.PartitionSpecContext var1);

   void exitPartitionSpec(SqlBaseParser.PartitionSpecContext var1);

   void enterPartitionVal(SqlBaseParser.PartitionValContext var1);

   void exitPartitionVal(SqlBaseParser.PartitionValContext var1);

   void enterNamespace(SqlBaseParser.NamespaceContext var1);

   void exitNamespace(SqlBaseParser.NamespaceContext var1);

   void enterNamespaces(SqlBaseParser.NamespacesContext var1);

   void exitNamespaces(SqlBaseParser.NamespacesContext var1);

   void enterVariable(SqlBaseParser.VariableContext var1);

   void exitVariable(SqlBaseParser.VariableContext var1);

   void enterDescribeFuncName(SqlBaseParser.DescribeFuncNameContext var1);

   void exitDescribeFuncName(SqlBaseParser.DescribeFuncNameContext var1);

   void enterDescribeColName(SqlBaseParser.DescribeColNameContext var1);

   void exitDescribeColName(SqlBaseParser.DescribeColNameContext var1);

   void enterCtes(SqlBaseParser.CtesContext var1);

   void exitCtes(SqlBaseParser.CtesContext var1);

   void enterNamedQuery(SqlBaseParser.NamedQueryContext var1);

   void exitNamedQuery(SqlBaseParser.NamedQueryContext var1);

   void enterTableProvider(SqlBaseParser.TableProviderContext var1);

   void exitTableProvider(SqlBaseParser.TableProviderContext var1);

   void enterCreateTableClauses(SqlBaseParser.CreateTableClausesContext var1);

   void exitCreateTableClauses(SqlBaseParser.CreateTableClausesContext var1);

   void enterPropertyList(SqlBaseParser.PropertyListContext var1);

   void exitPropertyList(SqlBaseParser.PropertyListContext var1);

   void enterProperty(SqlBaseParser.PropertyContext var1);

   void exitProperty(SqlBaseParser.PropertyContext var1);

   void enterPropertyKey(SqlBaseParser.PropertyKeyContext var1);

   void exitPropertyKey(SqlBaseParser.PropertyKeyContext var1);

   void enterPropertyValue(SqlBaseParser.PropertyValueContext var1);

   void exitPropertyValue(SqlBaseParser.PropertyValueContext var1);

   void enterExpressionPropertyList(SqlBaseParser.ExpressionPropertyListContext var1);

   void exitExpressionPropertyList(SqlBaseParser.ExpressionPropertyListContext var1);

   void enterExpressionProperty(SqlBaseParser.ExpressionPropertyContext var1);

   void exitExpressionProperty(SqlBaseParser.ExpressionPropertyContext var1);

   void enterConstantList(SqlBaseParser.ConstantListContext var1);

   void exitConstantList(SqlBaseParser.ConstantListContext var1);

   void enterNestedConstantList(SqlBaseParser.NestedConstantListContext var1);

   void exitNestedConstantList(SqlBaseParser.NestedConstantListContext var1);

   void enterCreateFileFormat(SqlBaseParser.CreateFileFormatContext var1);

   void exitCreateFileFormat(SqlBaseParser.CreateFileFormatContext var1);

   void enterTableFileFormat(SqlBaseParser.TableFileFormatContext var1);

   void exitTableFileFormat(SqlBaseParser.TableFileFormatContext var1);

   void enterGenericFileFormat(SqlBaseParser.GenericFileFormatContext var1);

   void exitGenericFileFormat(SqlBaseParser.GenericFileFormatContext var1);

   void enterStorageHandler(SqlBaseParser.StorageHandlerContext var1);

   void exitStorageHandler(SqlBaseParser.StorageHandlerContext var1);

   void enterResource(SqlBaseParser.ResourceContext var1);

   void exitResource(SqlBaseParser.ResourceContext var1);

   void enterSingleInsertQuery(SqlBaseParser.SingleInsertQueryContext var1);

   void exitSingleInsertQuery(SqlBaseParser.SingleInsertQueryContext var1);

   void enterMultiInsertQuery(SqlBaseParser.MultiInsertQueryContext var1);

   void exitMultiInsertQuery(SqlBaseParser.MultiInsertQueryContext var1);

   void enterDeleteFromTable(SqlBaseParser.DeleteFromTableContext var1);

   void exitDeleteFromTable(SqlBaseParser.DeleteFromTableContext var1);

   void enterUpdateTable(SqlBaseParser.UpdateTableContext var1);

   void exitUpdateTable(SqlBaseParser.UpdateTableContext var1);

   void enterMergeIntoTable(SqlBaseParser.MergeIntoTableContext var1);

   void exitMergeIntoTable(SqlBaseParser.MergeIntoTableContext var1);

   void enterIdentifierReference(SqlBaseParser.IdentifierReferenceContext var1);

   void exitIdentifierReference(SqlBaseParser.IdentifierReferenceContext var1);

   void enterCatalogIdentifierReference(SqlBaseParser.CatalogIdentifierReferenceContext var1);

   void exitCatalogIdentifierReference(SqlBaseParser.CatalogIdentifierReferenceContext var1);

   void enterQueryOrganization(SqlBaseParser.QueryOrganizationContext var1);

   void exitQueryOrganization(SqlBaseParser.QueryOrganizationContext var1);

   void enterMultiInsertQueryBody(SqlBaseParser.MultiInsertQueryBodyContext var1);

   void exitMultiInsertQueryBody(SqlBaseParser.MultiInsertQueryBodyContext var1);

   void enterOperatorPipeStatement(SqlBaseParser.OperatorPipeStatementContext var1);

   void exitOperatorPipeStatement(SqlBaseParser.OperatorPipeStatementContext var1);

   void enterQueryTermDefault(SqlBaseParser.QueryTermDefaultContext var1);

   void exitQueryTermDefault(SqlBaseParser.QueryTermDefaultContext var1);

   void enterSetOperation(SqlBaseParser.SetOperationContext var1);

   void exitSetOperation(SqlBaseParser.SetOperationContext var1);

   void enterQueryPrimaryDefault(SqlBaseParser.QueryPrimaryDefaultContext var1);

   void exitQueryPrimaryDefault(SqlBaseParser.QueryPrimaryDefaultContext var1);

   void enterFromStmt(SqlBaseParser.FromStmtContext var1);

   void exitFromStmt(SqlBaseParser.FromStmtContext var1);

   void enterTable(SqlBaseParser.TableContext var1);

   void exitTable(SqlBaseParser.TableContext var1);

   void enterInlineTableDefault1(SqlBaseParser.InlineTableDefault1Context var1);

   void exitInlineTableDefault1(SqlBaseParser.InlineTableDefault1Context var1);

   void enterSubquery(SqlBaseParser.SubqueryContext var1);

   void exitSubquery(SqlBaseParser.SubqueryContext var1);

   void enterSortItem(SqlBaseParser.SortItemContext var1);

   void exitSortItem(SqlBaseParser.SortItemContext var1);

   void enterFromStatement(SqlBaseParser.FromStatementContext var1);

   void exitFromStatement(SqlBaseParser.FromStatementContext var1);

   void enterFromStatementBody(SqlBaseParser.FromStatementBodyContext var1);

   void exitFromStatementBody(SqlBaseParser.FromStatementBodyContext var1);

   void enterTransformQuerySpecification(SqlBaseParser.TransformQuerySpecificationContext var1);

   void exitTransformQuerySpecification(SqlBaseParser.TransformQuerySpecificationContext var1);

   void enterRegularQuerySpecification(SqlBaseParser.RegularQuerySpecificationContext var1);

   void exitRegularQuerySpecification(SqlBaseParser.RegularQuerySpecificationContext var1);

   void enterTransformClause(SqlBaseParser.TransformClauseContext var1);

   void exitTransformClause(SqlBaseParser.TransformClauseContext var1);

   void enterSelectClause(SqlBaseParser.SelectClauseContext var1);

   void exitSelectClause(SqlBaseParser.SelectClauseContext var1);

   void enterSetClause(SqlBaseParser.SetClauseContext var1);

   void exitSetClause(SqlBaseParser.SetClauseContext var1);

   void enterMatchedClause(SqlBaseParser.MatchedClauseContext var1);

   void exitMatchedClause(SqlBaseParser.MatchedClauseContext var1);

   void enterNotMatchedClause(SqlBaseParser.NotMatchedClauseContext var1);

   void exitNotMatchedClause(SqlBaseParser.NotMatchedClauseContext var1);

   void enterNotMatchedBySourceClause(SqlBaseParser.NotMatchedBySourceClauseContext var1);

   void exitNotMatchedBySourceClause(SqlBaseParser.NotMatchedBySourceClauseContext var1);

   void enterMatchedAction(SqlBaseParser.MatchedActionContext var1);

   void exitMatchedAction(SqlBaseParser.MatchedActionContext var1);

   void enterNotMatchedAction(SqlBaseParser.NotMatchedActionContext var1);

   void exitNotMatchedAction(SqlBaseParser.NotMatchedActionContext var1);

   void enterNotMatchedBySourceAction(SqlBaseParser.NotMatchedBySourceActionContext var1);

   void exitNotMatchedBySourceAction(SqlBaseParser.NotMatchedBySourceActionContext var1);

   void enterExceptClause(SqlBaseParser.ExceptClauseContext var1);

   void exitExceptClause(SqlBaseParser.ExceptClauseContext var1);

   void enterAssignmentList(SqlBaseParser.AssignmentListContext var1);

   void exitAssignmentList(SqlBaseParser.AssignmentListContext var1);

   void enterAssignment(SqlBaseParser.AssignmentContext var1);

   void exitAssignment(SqlBaseParser.AssignmentContext var1);

   void enterWhereClause(SqlBaseParser.WhereClauseContext var1);

   void exitWhereClause(SqlBaseParser.WhereClauseContext var1);

   void enterHavingClause(SqlBaseParser.HavingClauseContext var1);

   void exitHavingClause(SqlBaseParser.HavingClauseContext var1);

   void enterHint(SqlBaseParser.HintContext var1);

   void exitHint(SqlBaseParser.HintContext var1);

   void enterHintStatement(SqlBaseParser.HintStatementContext var1);

   void exitHintStatement(SqlBaseParser.HintStatementContext var1);

   void enterFromClause(SqlBaseParser.FromClauseContext var1);

   void exitFromClause(SqlBaseParser.FromClauseContext var1);

   void enterTemporalClause(SqlBaseParser.TemporalClauseContext var1);

   void exitTemporalClause(SqlBaseParser.TemporalClauseContext var1);

   void enterAggregationClause(SqlBaseParser.AggregationClauseContext var1);

   void exitAggregationClause(SqlBaseParser.AggregationClauseContext var1);

   void enterGroupByClause(SqlBaseParser.GroupByClauseContext var1);

   void exitGroupByClause(SqlBaseParser.GroupByClauseContext var1);

   void enterGroupingAnalytics(SqlBaseParser.GroupingAnalyticsContext var1);

   void exitGroupingAnalytics(SqlBaseParser.GroupingAnalyticsContext var1);

   void enterGroupingElement(SqlBaseParser.GroupingElementContext var1);

   void exitGroupingElement(SqlBaseParser.GroupingElementContext var1);

   void enterGroupingSet(SqlBaseParser.GroupingSetContext var1);

   void exitGroupingSet(SqlBaseParser.GroupingSetContext var1);

   void enterPivotClause(SqlBaseParser.PivotClauseContext var1);

   void exitPivotClause(SqlBaseParser.PivotClauseContext var1);

   void enterPivotColumn(SqlBaseParser.PivotColumnContext var1);

   void exitPivotColumn(SqlBaseParser.PivotColumnContext var1);

   void enterPivotValue(SqlBaseParser.PivotValueContext var1);

   void exitPivotValue(SqlBaseParser.PivotValueContext var1);

   void enterUnpivotClause(SqlBaseParser.UnpivotClauseContext var1);

   void exitUnpivotClause(SqlBaseParser.UnpivotClauseContext var1);

   void enterUnpivotNullClause(SqlBaseParser.UnpivotNullClauseContext var1);

   void exitUnpivotNullClause(SqlBaseParser.UnpivotNullClauseContext var1);

   void enterUnpivotOperator(SqlBaseParser.UnpivotOperatorContext var1);

   void exitUnpivotOperator(SqlBaseParser.UnpivotOperatorContext var1);

   void enterUnpivotSingleValueColumnClause(SqlBaseParser.UnpivotSingleValueColumnClauseContext var1);

   void exitUnpivotSingleValueColumnClause(SqlBaseParser.UnpivotSingleValueColumnClauseContext var1);

   void enterUnpivotMultiValueColumnClause(SqlBaseParser.UnpivotMultiValueColumnClauseContext var1);

   void exitUnpivotMultiValueColumnClause(SqlBaseParser.UnpivotMultiValueColumnClauseContext var1);

   void enterUnpivotColumnSet(SqlBaseParser.UnpivotColumnSetContext var1);

   void exitUnpivotColumnSet(SqlBaseParser.UnpivotColumnSetContext var1);

   void enterUnpivotValueColumn(SqlBaseParser.UnpivotValueColumnContext var1);

   void exitUnpivotValueColumn(SqlBaseParser.UnpivotValueColumnContext var1);

   void enterUnpivotNameColumn(SqlBaseParser.UnpivotNameColumnContext var1);

   void exitUnpivotNameColumn(SqlBaseParser.UnpivotNameColumnContext var1);

   void enterUnpivotColumnAndAlias(SqlBaseParser.UnpivotColumnAndAliasContext var1);

   void exitUnpivotColumnAndAlias(SqlBaseParser.UnpivotColumnAndAliasContext var1);

   void enterUnpivotColumn(SqlBaseParser.UnpivotColumnContext var1);

   void exitUnpivotColumn(SqlBaseParser.UnpivotColumnContext var1);

   void enterUnpivotAlias(SqlBaseParser.UnpivotAliasContext var1);

   void exitUnpivotAlias(SqlBaseParser.UnpivotAliasContext var1);

   void enterLateralView(SqlBaseParser.LateralViewContext var1);

   void exitLateralView(SqlBaseParser.LateralViewContext var1);

   void enterSetQuantifier(SqlBaseParser.SetQuantifierContext var1);

   void exitSetQuantifier(SqlBaseParser.SetQuantifierContext var1);

   void enterRelation(SqlBaseParser.RelationContext var1);

   void exitRelation(SqlBaseParser.RelationContext var1);

   void enterRelationExtension(SqlBaseParser.RelationExtensionContext var1);

   void exitRelationExtension(SqlBaseParser.RelationExtensionContext var1);

   void enterJoinRelation(SqlBaseParser.JoinRelationContext var1);

   void exitJoinRelation(SqlBaseParser.JoinRelationContext var1);

   void enterJoinType(SqlBaseParser.JoinTypeContext var1);

   void exitJoinType(SqlBaseParser.JoinTypeContext var1);

   void enterJoinCriteria(SqlBaseParser.JoinCriteriaContext var1);

   void exitJoinCriteria(SqlBaseParser.JoinCriteriaContext var1);

   void enterSample(SqlBaseParser.SampleContext var1);

   void exitSample(SqlBaseParser.SampleContext var1);

   void enterSampleByPercentile(SqlBaseParser.SampleByPercentileContext var1);

   void exitSampleByPercentile(SqlBaseParser.SampleByPercentileContext var1);

   void enterSampleByRows(SqlBaseParser.SampleByRowsContext var1);

   void exitSampleByRows(SqlBaseParser.SampleByRowsContext var1);

   void enterSampleByBucket(SqlBaseParser.SampleByBucketContext var1);

   void exitSampleByBucket(SqlBaseParser.SampleByBucketContext var1);

   void enterSampleByBytes(SqlBaseParser.SampleByBytesContext var1);

   void exitSampleByBytes(SqlBaseParser.SampleByBytesContext var1);

   void enterIdentifierList(SqlBaseParser.IdentifierListContext var1);

   void exitIdentifierList(SqlBaseParser.IdentifierListContext var1);

   void enterIdentifierSeq(SqlBaseParser.IdentifierSeqContext var1);

   void exitIdentifierSeq(SqlBaseParser.IdentifierSeqContext var1);

   void enterOrderedIdentifierList(SqlBaseParser.OrderedIdentifierListContext var1);

   void exitOrderedIdentifierList(SqlBaseParser.OrderedIdentifierListContext var1);

   void enterOrderedIdentifier(SqlBaseParser.OrderedIdentifierContext var1);

   void exitOrderedIdentifier(SqlBaseParser.OrderedIdentifierContext var1);

   void enterIdentifierCommentList(SqlBaseParser.IdentifierCommentListContext var1);

   void exitIdentifierCommentList(SqlBaseParser.IdentifierCommentListContext var1);

   void enterIdentifierComment(SqlBaseParser.IdentifierCommentContext var1);

   void exitIdentifierComment(SqlBaseParser.IdentifierCommentContext var1);

   void enterTableName(SqlBaseParser.TableNameContext var1);

   void exitTableName(SqlBaseParser.TableNameContext var1);

   void enterAliasedQuery(SqlBaseParser.AliasedQueryContext var1);

   void exitAliasedQuery(SqlBaseParser.AliasedQueryContext var1);

   void enterAliasedRelation(SqlBaseParser.AliasedRelationContext var1);

   void exitAliasedRelation(SqlBaseParser.AliasedRelationContext var1);

   void enterInlineTableDefault2(SqlBaseParser.InlineTableDefault2Context var1);

   void exitInlineTableDefault2(SqlBaseParser.InlineTableDefault2Context var1);

   void enterTableValuedFunction(SqlBaseParser.TableValuedFunctionContext var1);

   void exitTableValuedFunction(SqlBaseParser.TableValuedFunctionContext var1);

   void enterOptionsClause(SqlBaseParser.OptionsClauseContext var1);

   void exitOptionsClause(SqlBaseParser.OptionsClauseContext var1);

   void enterInlineTable(SqlBaseParser.InlineTableContext var1);

   void exitInlineTable(SqlBaseParser.InlineTableContext var1);

   void enterFunctionTableSubqueryArgument(SqlBaseParser.FunctionTableSubqueryArgumentContext var1);

   void exitFunctionTableSubqueryArgument(SqlBaseParser.FunctionTableSubqueryArgumentContext var1);

   void enterTableArgumentPartitioning(SqlBaseParser.TableArgumentPartitioningContext var1);

   void exitTableArgumentPartitioning(SqlBaseParser.TableArgumentPartitioningContext var1);

   void enterFunctionTableNamedArgumentExpression(SqlBaseParser.FunctionTableNamedArgumentExpressionContext var1);

   void exitFunctionTableNamedArgumentExpression(SqlBaseParser.FunctionTableNamedArgumentExpressionContext var1);

   void enterFunctionTableReferenceArgument(SqlBaseParser.FunctionTableReferenceArgumentContext var1);

   void exitFunctionTableReferenceArgument(SqlBaseParser.FunctionTableReferenceArgumentContext var1);

   void enterFunctionTableArgument(SqlBaseParser.FunctionTableArgumentContext var1);

   void exitFunctionTableArgument(SqlBaseParser.FunctionTableArgumentContext var1);

   void enterFunctionTable(SqlBaseParser.FunctionTableContext var1);

   void exitFunctionTable(SqlBaseParser.FunctionTableContext var1);

   void enterTableAlias(SqlBaseParser.TableAliasContext var1);

   void exitTableAlias(SqlBaseParser.TableAliasContext var1);

   void enterRowFormatSerde(SqlBaseParser.RowFormatSerdeContext var1);

   void exitRowFormatSerde(SqlBaseParser.RowFormatSerdeContext var1);

   void enterRowFormatDelimited(SqlBaseParser.RowFormatDelimitedContext var1);

   void exitRowFormatDelimited(SqlBaseParser.RowFormatDelimitedContext var1);

   void enterMultipartIdentifierList(SqlBaseParser.MultipartIdentifierListContext var1);

   void exitMultipartIdentifierList(SqlBaseParser.MultipartIdentifierListContext var1);

   void enterMultipartIdentifier(SqlBaseParser.MultipartIdentifierContext var1);

   void exitMultipartIdentifier(SqlBaseParser.MultipartIdentifierContext var1);

   void enterMultipartIdentifierPropertyList(SqlBaseParser.MultipartIdentifierPropertyListContext var1);

   void exitMultipartIdentifierPropertyList(SqlBaseParser.MultipartIdentifierPropertyListContext var1);

   void enterMultipartIdentifierProperty(SqlBaseParser.MultipartIdentifierPropertyContext var1);

   void exitMultipartIdentifierProperty(SqlBaseParser.MultipartIdentifierPropertyContext var1);

   void enterTableIdentifier(SqlBaseParser.TableIdentifierContext var1);

   void exitTableIdentifier(SqlBaseParser.TableIdentifierContext var1);

   void enterFunctionIdentifier(SqlBaseParser.FunctionIdentifierContext var1);

   void exitFunctionIdentifier(SqlBaseParser.FunctionIdentifierContext var1);

   void enterNamedExpression(SqlBaseParser.NamedExpressionContext var1);

   void exitNamedExpression(SqlBaseParser.NamedExpressionContext var1);

   void enterNamedExpressionSeq(SqlBaseParser.NamedExpressionSeqContext var1);

   void exitNamedExpressionSeq(SqlBaseParser.NamedExpressionSeqContext var1);

   void enterPartitionFieldList(SqlBaseParser.PartitionFieldListContext var1);

   void exitPartitionFieldList(SqlBaseParser.PartitionFieldListContext var1);

   void enterPartitionTransform(SqlBaseParser.PartitionTransformContext var1);

   void exitPartitionTransform(SqlBaseParser.PartitionTransformContext var1);

   void enterPartitionColumn(SqlBaseParser.PartitionColumnContext var1);

   void exitPartitionColumn(SqlBaseParser.PartitionColumnContext var1);

   void enterIdentityTransform(SqlBaseParser.IdentityTransformContext var1);

   void exitIdentityTransform(SqlBaseParser.IdentityTransformContext var1);

   void enterApplyTransform(SqlBaseParser.ApplyTransformContext var1);

   void exitApplyTransform(SqlBaseParser.ApplyTransformContext var1);

   void enterTransformArgument(SqlBaseParser.TransformArgumentContext var1);

   void exitTransformArgument(SqlBaseParser.TransformArgumentContext var1);

   void enterExpression(SqlBaseParser.ExpressionContext var1);

   void exitExpression(SqlBaseParser.ExpressionContext var1);

   void enterNamedArgumentExpression(SqlBaseParser.NamedArgumentExpressionContext var1);

   void exitNamedArgumentExpression(SqlBaseParser.NamedArgumentExpressionContext var1);

   void enterFunctionArgument(SqlBaseParser.FunctionArgumentContext var1);

   void exitFunctionArgument(SqlBaseParser.FunctionArgumentContext var1);

   void enterExpressionSeq(SqlBaseParser.ExpressionSeqContext var1);

   void exitExpressionSeq(SqlBaseParser.ExpressionSeqContext var1);

   void enterLogicalNot(SqlBaseParser.LogicalNotContext var1);

   void exitLogicalNot(SqlBaseParser.LogicalNotContext var1);

   void enterPredicated(SqlBaseParser.PredicatedContext var1);

   void exitPredicated(SqlBaseParser.PredicatedContext var1);

   void enterExists(SqlBaseParser.ExistsContext var1);

   void exitExists(SqlBaseParser.ExistsContext var1);

   void enterLogicalBinary(SqlBaseParser.LogicalBinaryContext var1);

   void exitLogicalBinary(SqlBaseParser.LogicalBinaryContext var1);

   void enterPredicate(SqlBaseParser.PredicateContext var1);

   void exitPredicate(SqlBaseParser.PredicateContext var1);

   void enterErrorCapturingNot(SqlBaseParser.ErrorCapturingNotContext var1);

   void exitErrorCapturingNot(SqlBaseParser.ErrorCapturingNotContext var1);

   void enterValueExpressionDefault(SqlBaseParser.ValueExpressionDefaultContext var1);

   void exitValueExpressionDefault(SqlBaseParser.ValueExpressionDefaultContext var1);

   void enterComparison(SqlBaseParser.ComparisonContext var1);

   void exitComparison(SqlBaseParser.ComparisonContext var1);

   void enterShiftExpression(SqlBaseParser.ShiftExpressionContext var1);

   void exitShiftExpression(SqlBaseParser.ShiftExpressionContext var1);

   void enterArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext var1);

   void exitArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext var1);

   void enterArithmeticUnary(SqlBaseParser.ArithmeticUnaryContext var1);

   void exitArithmeticUnary(SqlBaseParser.ArithmeticUnaryContext var1);

   void enterShiftOperator(SqlBaseParser.ShiftOperatorContext var1);

   void exitShiftOperator(SqlBaseParser.ShiftOperatorContext var1);

   void enterDatetimeUnit(SqlBaseParser.DatetimeUnitContext var1);

   void exitDatetimeUnit(SqlBaseParser.DatetimeUnitContext var1);

   void enterStruct(SqlBaseParser.StructContext var1);

   void exitStruct(SqlBaseParser.StructContext var1);

   void enterDereference(SqlBaseParser.DereferenceContext var1);

   void exitDereference(SqlBaseParser.DereferenceContext var1);

   void enterCastByColon(SqlBaseParser.CastByColonContext var1);

   void exitCastByColon(SqlBaseParser.CastByColonContext var1);

   void enterTimestampadd(SqlBaseParser.TimestampaddContext var1);

   void exitTimestampadd(SqlBaseParser.TimestampaddContext var1);

   void enterSubstring(SqlBaseParser.SubstringContext var1);

   void exitSubstring(SqlBaseParser.SubstringContext var1);

   void enterCast(SqlBaseParser.CastContext var1);

   void exitCast(SqlBaseParser.CastContext var1);

   void enterLambda(SqlBaseParser.LambdaContext var1);

   void exitLambda(SqlBaseParser.LambdaContext var1);

   void enterParenthesizedExpression(SqlBaseParser.ParenthesizedExpressionContext var1);

   void exitParenthesizedExpression(SqlBaseParser.ParenthesizedExpressionContext var1);

   void enterAny_value(SqlBaseParser.Any_valueContext var1);

   void exitAny_value(SqlBaseParser.Any_valueContext var1);

   void enterTrim(SqlBaseParser.TrimContext var1);

   void exitTrim(SqlBaseParser.TrimContext var1);

   void enterSimpleCase(SqlBaseParser.SimpleCaseContext var1);

   void exitSimpleCase(SqlBaseParser.SimpleCaseContext var1);

   void enterCurrentLike(SqlBaseParser.CurrentLikeContext var1);

   void exitCurrentLike(SqlBaseParser.CurrentLikeContext var1);

   void enterColumnReference(SqlBaseParser.ColumnReferenceContext var1);

   void exitColumnReference(SqlBaseParser.ColumnReferenceContext var1);

   void enterRowConstructor(SqlBaseParser.RowConstructorContext var1);

   void exitRowConstructor(SqlBaseParser.RowConstructorContext var1);

   void enterLast(SqlBaseParser.LastContext var1);

   void exitLast(SqlBaseParser.LastContext var1);

   void enterStar(SqlBaseParser.StarContext var1);

   void exitStar(SqlBaseParser.StarContext var1);

   void enterOverlay(SqlBaseParser.OverlayContext var1);

   void exitOverlay(SqlBaseParser.OverlayContext var1);

   void enterSubscript(SqlBaseParser.SubscriptContext var1);

   void exitSubscript(SqlBaseParser.SubscriptContext var1);

   void enterTimestampdiff(SqlBaseParser.TimestampdiffContext var1);

   void exitTimestampdiff(SqlBaseParser.TimestampdiffContext var1);

   void enterSubqueryExpression(SqlBaseParser.SubqueryExpressionContext var1);

   void exitSubqueryExpression(SqlBaseParser.SubqueryExpressionContext var1);

   void enterCollate(SqlBaseParser.CollateContext var1);

   void exitCollate(SqlBaseParser.CollateContext var1);

   void enterConstantDefault(SqlBaseParser.ConstantDefaultContext var1);

   void exitConstantDefault(SqlBaseParser.ConstantDefaultContext var1);

   void enterExtract(SqlBaseParser.ExtractContext var1);

   void exitExtract(SqlBaseParser.ExtractContext var1);

   void enterFunctionCall(SqlBaseParser.FunctionCallContext var1);

   void exitFunctionCall(SqlBaseParser.FunctionCallContext var1);

   void enterSearchedCase(SqlBaseParser.SearchedCaseContext var1);

   void exitSearchedCase(SqlBaseParser.SearchedCaseContext var1);

   void enterPosition(SqlBaseParser.PositionContext var1);

   void exitPosition(SqlBaseParser.PositionContext var1);

   void enterFirst(SqlBaseParser.FirstContext var1);

   void exitFirst(SqlBaseParser.FirstContext var1);

   void enterLiteralType(SqlBaseParser.LiteralTypeContext var1);

   void exitLiteralType(SqlBaseParser.LiteralTypeContext var1);

   void enterNullLiteral(SqlBaseParser.NullLiteralContext var1);

   void exitNullLiteral(SqlBaseParser.NullLiteralContext var1);

   void enterPosParameterLiteral(SqlBaseParser.PosParameterLiteralContext var1);

   void exitPosParameterLiteral(SqlBaseParser.PosParameterLiteralContext var1);

   void enterNamedParameterLiteral(SqlBaseParser.NamedParameterLiteralContext var1);

   void exitNamedParameterLiteral(SqlBaseParser.NamedParameterLiteralContext var1);

   void enterIntervalLiteral(SqlBaseParser.IntervalLiteralContext var1);

   void exitIntervalLiteral(SqlBaseParser.IntervalLiteralContext var1);

   void enterTypeConstructor(SqlBaseParser.TypeConstructorContext var1);

   void exitTypeConstructor(SqlBaseParser.TypeConstructorContext var1);

   void enterNumericLiteral(SqlBaseParser.NumericLiteralContext var1);

   void exitNumericLiteral(SqlBaseParser.NumericLiteralContext var1);

   void enterBooleanLiteral(SqlBaseParser.BooleanLiteralContext var1);

   void exitBooleanLiteral(SqlBaseParser.BooleanLiteralContext var1);

   void enterStringLiteral(SqlBaseParser.StringLiteralContext var1);

   void exitStringLiteral(SqlBaseParser.StringLiteralContext var1);

   void enterComparisonOperator(SqlBaseParser.ComparisonOperatorContext var1);

   void exitComparisonOperator(SqlBaseParser.ComparisonOperatorContext var1);

   void enterArithmeticOperator(SqlBaseParser.ArithmeticOperatorContext var1);

   void exitArithmeticOperator(SqlBaseParser.ArithmeticOperatorContext var1);

   void enterPredicateOperator(SqlBaseParser.PredicateOperatorContext var1);

   void exitPredicateOperator(SqlBaseParser.PredicateOperatorContext var1);

   void enterBooleanValue(SqlBaseParser.BooleanValueContext var1);

   void exitBooleanValue(SqlBaseParser.BooleanValueContext var1);

   void enterInterval(SqlBaseParser.IntervalContext var1);

   void exitInterval(SqlBaseParser.IntervalContext var1);

   void enterErrorCapturingMultiUnitsInterval(SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext var1);

   void exitErrorCapturingMultiUnitsInterval(SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext var1);

   void enterMultiUnitsInterval(SqlBaseParser.MultiUnitsIntervalContext var1);

   void exitMultiUnitsInterval(SqlBaseParser.MultiUnitsIntervalContext var1);

   void enterErrorCapturingUnitToUnitInterval(SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext var1);

   void exitErrorCapturingUnitToUnitInterval(SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext var1);

   void enterUnitToUnitInterval(SqlBaseParser.UnitToUnitIntervalContext var1);

   void exitUnitToUnitInterval(SqlBaseParser.UnitToUnitIntervalContext var1);

   void enterIntervalValue(SqlBaseParser.IntervalValueContext var1);

   void exitIntervalValue(SqlBaseParser.IntervalValueContext var1);

   void enterUnitInMultiUnits(SqlBaseParser.UnitInMultiUnitsContext var1);

   void exitUnitInMultiUnits(SqlBaseParser.UnitInMultiUnitsContext var1);

   void enterUnitInUnitToUnit(SqlBaseParser.UnitInUnitToUnitContext var1);

   void exitUnitInUnitToUnit(SqlBaseParser.UnitInUnitToUnitContext var1);

   void enterColPosition(SqlBaseParser.ColPositionContext var1);

   void exitColPosition(SqlBaseParser.ColPositionContext var1);

   void enterCollationSpec(SqlBaseParser.CollationSpecContext var1);

   void exitCollationSpec(SqlBaseParser.CollationSpecContext var1);

   void enterCollateClause(SqlBaseParser.CollateClauseContext var1);

   void exitCollateClause(SqlBaseParser.CollateClauseContext var1);

   void enterType(SqlBaseParser.TypeContext var1);

   void exitType(SqlBaseParser.TypeContext var1);

   void enterComplexDataType(SqlBaseParser.ComplexDataTypeContext var1);

   void exitComplexDataType(SqlBaseParser.ComplexDataTypeContext var1);

   void enterYearMonthIntervalDataType(SqlBaseParser.YearMonthIntervalDataTypeContext var1);

   void exitYearMonthIntervalDataType(SqlBaseParser.YearMonthIntervalDataTypeContext var1);

   void enterDayTimeIntervalDataType(SqlBaseParser.DayTimeIntervalDataTypeContext var1);

   void exitDayTimeIntervalDataType(SqlBaseParser.DayTimeIntervalDataTypeContext var1);

   void enterPrimitiveDataType(SqlBaseParser.PrimitiveDataTypeContext var1);

   void exitPrimitiveDataType(SqlBaseParser.PrimitiveDataTypeContext var1);

   void enterQualifiedColTypeWithPositionList(SqlBaseParser.QualifiedColTypeWithPositionListContext var1);

   void exitQualifiedColTypeWithPositionList(SqlBaseParser.QualifiedColTypeWithPositionListContext var1);

   void enterQualifiedColTypeWithPosition(SqlBaseParser.QualifiedColTypeWithPositionContext var1);

   void exitQualifiedColTypeWithPosition(SqlBaseParser.QualifiedColTypeWithPositionContext var1);

   void enterColDefinitionDescriptorWithPosition(SqlBaseParser.ColDefinitionDescriptorWithPositionContext var1);

   void exitColDefinitionDescriptorWithPosition(SqlBaseParser.ColDefinitionDescriptorWithPositionContext var1);

   void enterDefaultExpression(SqlBaseParser.DefaultExpressionContext var1);

   void exitDefaultExpression(SqlBaseParser.DefaultExpressionContext var1);

   void enterVariableDefaultExpression(SqlBaseParser.VariableDefaultExpressionContext var1);

   void exitVariableDefaultExpression(SqlBaseParser.VariableDefaultExpressionContext var1);

   void enterColTypeList(SqlBaseParser.ColTypeListContext var1);

   void exitColTypeList(SqlBaseParser.ColTypeListContext var1);

   void enterColType(SqlBaseParser.ColTypeContext var1);

   void exitColType(SqlBaseParser.ColTypeContext var1);

   void enterColDefinitionList(SqlBaseParser.ColDefinitionListContext var1);

   void exitColDefinitionList(SqlBaseParser.ColDefinitionListContext var1);

   void enterColDefinition(SqlBaseParser.ColDefinitionContext var1);

   void exitColDefinition(SqlBaseParser.ColDefinitionContext var1);

   void enterColDefinitionOption(SqlBaseParser.ColDefinitionOptionContext var1);

   void exitColDefinitionOption(SqlBaseParser.ColDefinitionOptionContext var1);

   void enterGeneratedColumn(SqlBaseParser.GeneratedColumnContext var1);

   void exitGeneratedColumn(SqlBaseParser.GeneratedColumnContext var1);

   void enterIdentityColumn(SqlBaseParser.IdentityColumnContext var1);

   void exitIdentityColumn(SqlBaseParser.IdentityColumnContext var1);

   void enterIdentityColSpec(SqlBaseParser.IdentityColSpecContext var1);

   void exitIdentityColSpec(SqlBaseParser.IdentityColSpecContext var1);

   void enterSequenceGeneratorOption(SqlBaseParser.SequenceGeneratorOptionContext var1);

   void exitSequenceGeneratorOption(SqlBaseParser.SequenceGeneratorOptionContext var1);

   void enterSequenceGeneratorStartOrStep(SqlBaseParser.SequenceGeneratorStartOrStepContext var1);

   void exitSequenceGeneratorStartOrStep(SqlBaseParser.SequenceGeneratorStartOrStepContext var1);

   void enterComplexColTypeList(SqlBaseParser.ComplexColTypeListContext var1);

   void exitComplexColTypeList(SqlBaseParser.ComplexColTypeListContext var1);

   void enterComplexColType(SqlBaseParser.ComplexColTypeContext var1);

   void exitComplexColType(SqlBaseParser.ComplexColTypeContext var1);

   void enterRoutineCharacteristics(SqlBaseParser.RoutineCharacteristicsContext var1);

   void exitRoutineCharacteristics(SqlBaseParser.RoutineCharacteristicsContext var1);

   void enterRoutineLanguage(SqlBaseParser.RoutineLanguageContext var1);

   void exitRoutineLanguage(SqlBaseParser.RoutineLanguageContext var1);

   void enterSpecificName(SqlBaseParser.SpecificNameContext var1);

   void exitSpecificName(SqlBaseParser.SpecificNameContext var1);

   void enterDeterministic(SqlBaseParser.DeterministicContext var1);

   void exitDeterministic(SqlBaseParser.DeterministicContext var1);

   void enterSqlDataAccess(SqlBaseParser.SqlDataAccessContext var1);

   void exitSqlDataAccess(SqlBaseParser.SqlDataAccessContext var1);

   void enterNullCall(SqlBaseParser.NullCallContext var1);

   void exitNullCall(SqlBaseParser.NullCallContext var1);

   void enterRightsClause(SqlBaseParser.RightsClauseContext var1);

   void exitRightsClause(SqlBaseParser.RightsClauseContext var1);

   void enterWhenClause(SqlBaseParser.WhenClauseContext var1);

   void exitWhenClause(SqlBaseParser.WhenClauseContext var1);

   void enterWindowClause(SqlBaseParser.WindowClauseContext var1);

   void exitWindowClause(SqlBaseParser.WindowClauseContext var1);

   void enterNamedWindow(SqlBaseParser.NamedWindowContext var1);

   void exitNamedWindow(SqlBaseParser.NamedWindowContext var1);

   void enterWindowRef(SqlBaseParser.WindowRefContext var1);

   void exitWindowRef(SqlBaseParser.WindowRefContext var1);

   void enterWindowDef(SqlBaseParser.WindowDefContext var1);

   void exitWindowDef(SqlBaseParser.WindowDefContext var1);

   void enterWindowFrame(SqlBaseParser.WindowFrameContext var1);

   void exitWindowFrame(SqlBaseParser.WindowFrameContext var1);

   void enterFrameBound(SqlBaseParser.FrameBoundContext var1);

   void exitFrameBound(SqlBaseParser.FrameBoundContext var1);

   void enterQualifiedNameList(SqlBaseParser.QualifiedNameListContext var1);

   void exitQualifiedNameList(SqlBaseParser.QualifiedNameListContext var1);

   void enterFunctionName(SqlBaseParser.FunctionNameContext var1);

   void exitFunctionName(SqlBaseParser.FunctionNameContext var1);

   void enterQualifiedName(SqlBaseParser.QualifiedNameContext var1);

   void exitQualifiedName(SqlBaseParser.QualifiedNameContext var1);

   void enterErrorCapturingIdentifier(SqlBaseParser.ErrorCapturingIdentifierContext var1);

   void exitErrorCapturingIdentifier(SqlBaseParser.ErrorCapturingIdentifierContext var1);

   void enterErrorIdent(SqlBaseParser.ErrorIdentContext var1);

   void exitErrorIdent(SqlBaseParser.ErrorIdentContext var1);

   void enterRealIdent(SqlBaseParser.RealIdentContext var1);

   void exitRealIdent(SqlBaseParser.RealIdentContext var1);

   void enterIdentifier(SqlBaseParser.IdentifierContext var1);

   void exitIdentifier(SqlBaseParser.IdentifierContext var1);

   void enterUnquotedIdentifier(SqlBaseParser.UnquotedIdentifierContext var1);

   void exitUnquotedIdentifier(SqlBaseParser.UnquotedIdentifierContext var1);

   void enterQuotedIdentifierAlternative(SqlBaseParser.QuotedIdentifierAlternativeContext var1);

   void exitQuotedIdentifierAlternative(SqlBaseParser.QuotedIdentifierAlternativeContext var1);

   void enterQuotedIdentifier(SqlBaseParser.QuotedIdentifierContext var1);

   void exitQuotedIdentifier(SqlBaseParser.QuotedIdentifierContext var1);

   void enterBackQuotedIdentifier(SqlBaseParser.BackQuotedIdentifierContext var1);

   void exitBackQuotedIdentifier(SqlBaseParser.BackQuotedIdentifierContext var1);

   void enterExponentLiteral(SqlBaseParser.ExponentLiteralContext var1);

   void exitExponentLiteral(SqlBaseParser.ExponentLiteralContext var1);

   void enterDecimalLiteral(SqlBaseParser.DecimalLiteralContext var1);

   void exitDecimalLiteral(SqlBaseParser.DecimalLiteralContext var1);

   void enterLegacyDecimalLiteral(SqlBaseParser.LegacyDecimalLiteralContext var1);

   void exitLegacyDecimalLiteral(SqlBaseParser.LegacyDecimalLiteralContext var1);

   void enterIntegerLiteral(SqlBaseParser.IntegerLiteralContext var1);

   void exitIntegerLiteral(SqlBaseParser.IntegerLiteralContext var1);

   void enterBigIntLiteral(SqlBaseParser.BigIntLiteralContext var1);

   void exitBigIntLiteral(SqlBaseParser.BigIntLiteralContext var1);

   void enterSmallIntLiteral(SqlBaseParser.SmallIntLiteralContext var1);

   void exitSmallIntLiteral(SqlBaseParser.SmallIntLiteralContext var1);

   void enterTinyIntLiteral(SqlBaseParser.TinyIntLiteralContext var1);

   void exitTinyIntLiteral(SqlBaseParser.TinyIntLiteralContext var1);

   void enterDoubleLiteral(SqlBaseParser.DoubleLiteralContext var1);

   void exitDoubleLiteral(SqlBaseParser.DoubleLiteralContext var1);

   void enterFloatLiteral(SqlBaseParser.FloatLiteralContext var1);

   void exitFloatLiteral(SqlBaseParser.FloatLiteralContext var1);

   void enterBigDecimalLiteral(SqlBaseParser.BigDecimalLiteralContext var1);

   void exitBigDecimalLiteral(SqlBaseParser.BigDecimalLiteralContext var1);

   void enterAlterColumnSpecList(SqlBaseParser.AlterColumnSpecListContext var1);

   void exitAlterColumnSpecList(SqlBaseParser.AlterColumnSpecListContext var1);

   void enterAlterColumnSpec(SqlBaseParser.AlterColumnSpecContext var1);

   void exitAlterColumnSpec(SqlBaseParser.AlterColumnSpecContext var1);

   void enterAlterColumnAction(SqlBaseParser.AlterColumnActionContext var1);

   void exitAlterColumnAction(SqlBaseParser.AlterColumnActionContext var1);

   void enterStringLit(SqlBaseParser.StringLitContext var1);

   void exitStringLit(SqlBaseParser.StringLitContext var1);

   void enterComment(SqlBaseParser.CommentContext var1);

   void exitComment(SqlBaseParser.CommentContext var1);

   void enterVersion(SqlBaseParser.VersionContext var1);

   void exitVersion(SqlBaseParser.VersionContext var1);

   void enterOperatorPipeRightSide(SqlBaseParser.OperatorPipeRightSideContext var1);

   void exitOperatorPipeRightSide(SqlBaseParser.OperatorPipeRightSideContext var1);

   void enterOperatorPipeSetAssignmentSeq(SqlBaseParser.OperatorPipeSetAssignmentSeqContext var1);

   void exitOperatorPipeSetAssignmentSeq(SqlBaseParser.OperatorPipeSetAssignmentSeqContext var1);

   void enterAnsiNonReserved(SqlBaseParser.AnsiNonReservedContext var1);

   void exitAnsiNonReserved(SqlBaseParser.AnsiNonReservedContext var1);

   void enterStrictNonReserved(SqlBaseParser.StrictNonReservedContext var1);

   void exitStrictNonReserved(SqlBaseParser.StrictNonReservedContext var1);

   void enterNonReserved(SqlBaseParser.NonReservedContext var1);

   void exitNonReserved(SqlBaseParser.NonReservedContext var1);
}
