package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SqlBaseParserBaseListener implements SqlBaseParserListener {
   public void enterCompoundOrSingleStatement(SqlBaseParser.CompoundOrSingleStatementContext ctx) {
   }

   public void exitCompoundOrSingleStatement(SqlBaseParser.CompoundOrSingleStatementContext ctx) {
   }

   public void enterSingleCompoundStatement(SqlBaseParser.SingleCompoundStatementContext ctx) {
   }

   public void exitSingleCompoundStatement(SqlBaseParser.SingleCompoundStatementContext ctx) {
   }

   public void enterBeginEndCompoundBlock(SqlBaseParser.BeginEndCompoundBlockContext ctx) {
   }

   public void exitBeginEndCompoundBlock(SqlBaseParser.BeginEndCompoundBlockContext ctx) {
   }

   public void enterCompoundBody(SqlBaseParser.CompoundBodyContext ctx) {
   }

   public void exitCompoundBody(SqlBaseParser.CompoundBodyContext ctx) {
   }

   public void enterCompoundStatement(SqlBaseParser.CompoundStatementContext ctx) {
   }

   public void exitCompoundStatement(SqlBaseParser.CompoundStatementContext ctx) {
   }

   public void enterSetVariableInsideSqlScript(SqlBaseParser.SetVariableInsideSqlScriptContext ctx) {
   }

   public void exitSetVariableInsideSqlScript(SqlBaseParser.SetVariableInsideSqlScriptContext ctx) {
   }

   public void enterSqlStateValue(SqlBaseParser.SqlStateValueContext ctx) {
   }

   public void exitSqlStateValue(SqlBaseParser.SqlStateValueContext ctx) {
   }

   public void enterDeclareConditionStatement(SqlBaseParser.DeclareConditionStatementContext ctx) {
   }

   public void exitDeclareConditionStatement(SqlBaseParser.DeclareConditionStatementContext ctx) {
   }

   public void enterConditionValue(SqlBaseParser.ConditionValueContext ctx) {
   }

   public void exitConditionValue(SqlBaseParser.ConditionValueContext ctx) {
   }

   public void enterConditionValues(SqlBaseParser.ConditionValuesContext ctx) {
   }

   public void exitConditionValues(SqlBaseParser.ConditionValuesContext ctx) {
   }

   public void enterDeclareHandlerStatement(SqlBaseParser.DeclareHandlerStatementContext ctx) {
   }

   public void exitDeclareHandlerStatement(SqlBaseParser.DeclareHandlerStatementContext ctx) {
   }

   public void enterWhileStatement(SqlBaseParser.WhileStatementContext ctx) {
   }

   public void exitWhileStatement(SqlBaseParser.WhileStatementContext ctx) {
   }

   public void enterIfElseStatement(SqlBaseParser.IfElseStatementContext ctx) {
   }

   public void exitIfElseStatement(SqlBaseParser.IfElseStatementContext ctx) {
   }

   public void enterRepeatStatement(SqlBaseParser.RepeatStatementContext ctx) {
   }

   public void exitRepeatStatement(SqlBaseParser.RepeatStatementContext ctx) {
   }

   public void enterLeaveStatement(SqlBaseParser.LeaveStatementContext ctx) {
   }

   public void exitLeaveStatement(SqlBaseParser.LeaveStatementContext ctx) {
   }

   public void enterIterateStatement(SqlBaseParser.IterateStatementContext ctx) {
   }

   public void exitIterateStatement(SqlBaseParser.IterateStatementContext ctx) {
   }

   public void enterSearchedCaseStatement(SqlBaseParser.SearchedCaseStatementContext ctx) {
   }

   public void exitSearchedCaseStatement(SqlBaseParser.SearchedCaseStatementContext ctx) {
   }

   public void enterSimpleCaseStatement(SqlBaseParser.SimpleCaseStatementContext ctx) {
   }

   public void exitSimpleCaseStatement(SqlBaseParser.SimpleCaseStatementContext ctx) {
   }

   public void enterLoopStatement(SqlBaseParser.LoopStatementContext ctx) {
   }

   public void exitLoopStatement(SqlBaseParser.LoopStatementContext ctx) {
   }

   public void enterForStatement(SqlBaseParser.ForStatementContext ctx) {
   }

   public void exitForStatement(SqlBaseParser.ForStatementContext ctx) {
   }

   public void enterSingleStatement(SqlBaseParser.SingleStatementContext ctx) {
   }

   public void exitSingleStatement(SqlBaseParser.SingleStatementContext ctx) {
   }

   public void enterBeginLabel(SqlBaseParser.BeginLabelContext ctx) {
   }

   public void exitBeginLabel(SqlBaseParser.BeginLabelContext ctx) {
   }

   public void enterEndLabel(SqlBaseParser.EndLabelContext ctx) {
   }

   public void exitEndLabel(SqlBaseParser.EndLabelContext ctx) {
   }

   public void enterSingleExpression(SqlBaseParser.SingleExpressionContext ctx) {
   }

   public void exitSingleExpression(SqlBaseParser.SingleExpressionContext ctx) {
   }

   public void enterSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext ctx) {
   }

   public void exitSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext ctx) {
   }

   public void enterSingleMultipartIdentifier(SqlBaseParser.SingleMultipartIdentifierContext ctx) {
   }

   public void exitSingleMultipartIdentifier(SqlBaseParser.SingleMultipartIdentifierContext ctx) {
   }

   public void enterSingleFunctionIdentifier(SqlBaseParser.SingleFunctionIdentifierContext ctx) {
   }

   public void exitSingleFunctionIdentifier(SqlBaseParser.SingleFunctionIdentifierContext ctx) {
   }

   public void enterSingleDataType(SqlBaseParser.SingleDataTypeContext ctx) {
   }

   public void exitSingleDataType(SqlBaseParser.SingleDataTypeContext ctx) {
   }

   public void enterSingleTableSchema(SqlBaseParser.SingleTableSchemaContext ctx) {
   }

   public void exitSingleTableSchema(SqlBaseParser.SingleTableSchemaContext ctx) {
   }

   public void enterSingleRoutineParamList(SqlBaseParser.SingleRoutineParamListContext ctx) {
   }

   public void exitSingleRoutineParamList(SqlBaseParser.SingleRoutineParamListContext ctx) {
   }

   public void enterStatementDefault(SqlBaseParser.StatementDefaultContext ctx) {
   }

   public void exitStatementDefault(SqlBaseParser.StatementDefaultContext ctx) {
   }

   public void enterVisitExecuteImmediate(SqlBaseParser.VisitExecuteImmediateContext ctx) {
   }

   public void exitVisitExecuteImmediate(SqlBaseParser.VisitExecuteImmediateContext ctx) {
   }

   public void enterDmlStatement(SqlBaseParser.DmlStatementContext ctx) {
   }

   public void exitDmlStatement(SqlBaseParser.DmlStatementContext ctx) {
   }

   public void enterUse(SqlBaseParser.UseContext ctx) {
   }

   public void exitUse(SqlBaseParser.UseContext ctx) {
   }

   public void enterUseNamespace(SqlBaseParser.UseNamespaceContext ctx) {
   }

   public void exitUseNamespace(SqlBaseParser.UseNamespaceContext ctx) {
   }

   public void enterSetCatalog(SqlBaseParser.SetCatalogContext ctx) {
   }

   public void exitSetCatalog(SqlBaseParser.SetCatalogContext ctx) {
   }

   public void enterCreateNamespace(SqlBaseParser.CreateNamespaceContext ctx) {
   }

   public void exitCreateNamespace(SqlBaseParser.CreateNamespaceContext ctx) {
   }

   public void enterSetNamespaceProperties(SqlBaseParser.SetNamespacePropertiesContext ctx) {
   }

   public void exitSetNamespaceProperties(SqlBaseParser.SetNamespacePropertiesContext ctx) {
   }

   public void enterUnsetNamespaceProperties(SqlBaseParser.UnsetNamespacePropertiesContext ctx) {
   }

   public void exitUnsetNamespaceProperties(SqlBaseParser.UnsetNamespacePropertiesContext ctx) {
   }

   public void enterSetNamespaceLocation(SqlBaseParser.SetNamespaceLocationContext ctx) {
   }

   public void exitSetNamespaceLocation(SqlBaseParser.SetNamespaceLocationContext ctx) {
   }

   public void enterDropNamespace(SqlBaseParser.DropNamespaceContext ctx) {
   }

   public void exitDropNamespace(SqlBaseParser.DropNamespaceContext ctx) {
   }

   public void enterShowNamespaces(SqlBaseParser.ShowNamespacesContext ctx) {
   }

   public void exitShowNamespaces(SqlBaseParser.ShowNamespacesContext ctx) {
   }

   public void enterCreateTable(SqlBaseParser.CreateTableContext ctx) {
   }

   public void exitCreateTable(SqlBaseParser.CreateTableContext ctx) {
   }

   public void enterCreateTableLike(SqlBaseParser.CreateTableLikeContext ctx) {
   }

   public void exitCreateTableLike(SqlBaseParser.CreateTableLikeContext ctx) {
   }

   public void enterReplaceTable(SqlBaseParser.ReplaceTableContext ctx) {
   }

   public void exitReplaceTable(SqlBaseParser.ReplaceTableContext ctx) {
   }

   public void enterAnalyze(SqlBaseParser.AnalyzeContext ctx) {
   }

   public void exitAnalyze(SqlBaseParser.AnalyzeContext ctx) {
   }

   public void enterAnalyzeTables(SqlBaseParser.AnalyzeTablesContext ctx) {
   }

   public void exitAnalyzeTables(SqlBaseParser.AnalyzeTablesContext ctx) {
   }

   public void enterAddTableColumns(SqlBaseParser.AddTableColumnsContext ctx) {
   }

   public void exitAddTableColumns(SqlBaseParser.AddTableColumnsContext ctx) {
   }

   public void enterRenameTableColumn(SqlBaseParser.RenameTableColumnContext ctx) {
   }

   public void exitRenameTableColumn(SqlBaseParser.RenameTableColumnContext ctx) {
   }

   public void enterDropTableColumns(SqlBaseParser.DropTableColumnsContext ctx) {
   }

   public void exitDropTableColumns(SqlBaseParser.DropTableColumnsContext ctx) {
   }

   public void enterRenameTable(SqlBaseParser.RenameTableContext ctx) {
   }

   public void exitRenameTable(SqlBaseParser.RenameTableContext ctx) {
   }

   public void enterSetTableProperties(SqlBaseParser.SetTablePropertiesContext ctx) {
   }

   public void exitSetTableProperties(SqlBaseParser.SetTablePropertiesContext ctx) {
   }

   public void enterUnsetTableProperties(SqlBaseParser.UnsetTablePropertiesContext ctx) {
   }

   public void exitUnsetTableProperties(SqlBaseParser.UnsetTablePropertiesContext ctx) {
   }

   public void enterAlterTableAlterColumn(SqlBaseParser.AlterTableAlterColumnContext ctx) {
   }

   public void exitAlterTableAlterColumn(SqlBaseParser.AlterTableAlterColumnContext ctx) {
   }

   public void enterHiveChangeColumn(SqlBaseParser.HiveChangeColumnContext ctx) {
   }

   public void exitHiveChangeColumn(SqlBaseParser.HiveChangeColumnContext ctx) {
   }

   public void enterHiveReplaceColumns(SqlBaseParser.HiveReplaceColumnsContext ctx) {
   }

   public void exitHiveReplaceColumns(SqlBaseParser.HiveReplaceColumnsContext ctx) {
   }

   public void enterSetTableSerDe(SqlBaseParser.SetTableSerDeContext ctx) {
   }

   public void exitSetTableSerDe(SqlBaseParser.SetTableSerDeContext ctx) {
   }

   public void enterAddTablePartition(SqlBaseParser.AddTablePartitionContext ctx) {
   }

   public void exitAddTablePartition(SqlBaseParser.AddTablePartitionContext ctx) {
   }

   public void enterRenameTablePartition(SqlBaseParser.RenameTablePartitionContext ctx) {
   }

   public void exitRenameTablePartition(SqlBaseParser.RenameTablePartitionContext ctx) {
   }

   public void enterDropTablePartitions(SqlBaseParser.DropTablePartitionsContext ctx) {
   }

   public void exitDropTablePartitions(SqlBaseParser.DropTablePartitionsContext ctx) {
   }

   public void enterSetTableLocation(SqlBaseParser.SetTableLocationContext ctx) {
   }

   public void exitSetTableLocation(SqlBaseParser.SetTableLocationContext ctx) {
   }

   public void enterRecoverPartitions(SqlBaseParser.RecoverPartitionsContext ctx) {
   }

   public void exitRecoverPartitions(SqlBaseParser.RecoverPartitionsContext ctx) {
   }

   public void enterAlterClusterBy(SqlBaseParser.AlterClusterByContext ctx) {
   }

   public void exitAlterClusterBy(SqlBaseParser.AlterClusterByContext ctx) {
   }

   public void enterAlterTableCollation(SqlBaseParser.AlterTableCollationContext ctx) {
   }

   public void exitAlterTableCollation(SqlBaseParser.AlterTableCollationContext ctx) {
   }

   public void enterDropTable(SqlBaseParser.DropTableContext ctx) {
   }

   public void exitDropTable(SqlBaseParser.DropTableContext ctx) {
   }

   public void enterDropView(SqlBaseParser.DropViewContext ctx) {
   }

   public void exitDropView(SqlBaseParser.DropViewContext ctx) {
   }

   public void enterCreateView(SqlBaseParser.CreateViewContext ctx) {
   }

   public void exitCreateView(SqlBaseParser.CreateViewContext ctx) {
   }

   public void enterCreateTempViewUsing(SqlBaseParser.CreateTempViewUsingContext ctx) {
   }

   public void exitCreateTempViewUsing(SqlBaseParser.CreateTempViewUsingContext ctx) {
   }

   public void enterAlterViewQuery(SqlBaseParser.AlterViewQueryContext ctx) {
   }

   public void exitAlterViewQuery(SqlBaseParser.AlterViewQueryContext ctx) {
   }

   public void enterAlterViewSchemaBinding(SqlBaseParser.AlterViewSchemaBindingContext ctx) {
   }

   public void exitAlterViewSchemaBinding(SqlBaseParser.AlterViewSchemaBindingContext ctx) {
   }

   public void enterCreateFunction(SqlBaseParser.CreateFunctionContext ctx) {
   }

   public void exitCreateFunction(SqlBaseParser.CreateFunctionContext ctx) {
   }

   public void enterCreateUserDefinedFunction(SqlBaseParser.CreateUserDefinedFunctionContext ctx) {
   }

   public void exitCreateUserDefinedFunction(SqlBaseParser.CreateUserDefinedFunctionContext ctx) {
   }

   public void enterDropFunction(SqlBaseParser.DropFunctionContext ctx) {
   }

   public void exitDropFunction(SqlBaseParser.DropFunctionContext ctx) {
   }

   public void enterCreateVariable(SqlBaseParser.CreateVariableContext ctx) {
   }

   public void exitCreateVariable(SqlBaseParser.CreateVariableContext ctx) {
   }

   public void enterDropVariable(SqlBaseParser.DropVariableContext ctx) {
   }

   public void exitDropVariable(SqlBaseParser.DropVariableContext ctx) {
   }

   public void enterExplain(SqlBaseParser.ExplainContext ctx) {
   }

   public void exitExplain(SqlBaseParser.ExplainContext ctx) {
   }

   public void enterShowTables(SqlBaseParser.ShowTablesContext ctx) {
   }

   public void exitShowTables(SqlBaseParser.ShowTablesContext ctx) {
   }

   public void enterShowTableExtended(SqlBaseParser.ShowTableExtendedContext ctx) {
   }

   public void exitShowTableExtended(SqlBaseParser.ShowTableExtendedContext ctx) {
   }

   public void enterShowTblProperties(SqlBaseParser.ShowTblPropertiesContext ctx) {
   }

   public void exitShowTblProperties(SqlBaseParser.ShowTblPropertiesContext ctx) {
   }

   public void enterShowColumns(SqlBaseParser.ShowColumnsContext ctx) {
   }

   public void exitShowColumns(SqlBaseParser.ShowColumnsContext ctx) {
   }

   public void enterShowViews(SqlBaseParser.ShowViewsContext ctx) {
   }

   public void exitShowViews(SqlBaseParser.ShowViewsContext ctx) {
   }

   public void enterShowPartitions(SqlBaseParser.ShowPartitionsContext ctx) {
   }

   public void exitShowPartitions(SqlBaseParser.ShowPartitionsContext ctx) {
   }

   public void enterShowFunctions(SqlBaseParser.ShowFunctionsContext ctx) {
   }

   public void exitShowFunctions(SqlBaseParser.ShowFunctionsContext ctx) {
   }

   public void enterShowCreateTable(SqlBaseParser.ShowCreateTableContext ctx) {
   }

   public void exitShowCreateTable(SqlBaseParser.ShowCreateTableContext ctx) {
   }

   public void enterShowCurrentNamespace(SqlBaseParser.ShowCurrentNamespaceContext ctx) {
   }

   public void exitShowCurrentNamespace(SqlBaseParser.ShowCurrentNamespaceContext ctx) {
   }

   public void enterShowCatalogs(SqlBaseParser.ShowCatalogsContext ctx) {
   }

   public void exitShowCatalogs(SqlBaseParser.ShowCatalogsContext ctx) {
   }

   public void enterDescribeFunction(SqlBaseParser.DescribeFunctionContext ctx) {
   }

   public void exitDescribeFunction(SqlBaseParser.DescribeFunctionContext ctx) {
   }

   public void enterDescribeNamespace(SqlBaseParser.DescribeNamespaceContext ctx) {
   }

   public void exitDescribeNamespace(SqlBaseParser.DescribeNamespaceContext ctx) {
   }

   public void enterDescribeRelation(SqlBaseParser.DescribeRelationContext ctx) {
   }

   public void exitDescribeRelation(SqlBaseParser.DescribeRelationContext ctx) {
   }

   public void enterDescribeQuery(SqlBaseParser.DescribeQueryContext ctx) {
   }

   public void exitDescribeQuery(SqlBaseParser.DescribeQueryContext ctx) {
   }

   public void enterCommentNamespace(SqlBaseParser.CommentNamespaceContext ctx) {
   }

   public void exitCommentNamespace(SqlBaseParser.CommentNamespaceContext ctx) {
   }

   public void enterCommentTable(SqlBaseParser.CommentTableContext ctx) {
   }

   public void exitCommentTable(SqlBaseParser.CommentTableContext ctx) {
   }

   public void enterRefreshTable(SqlBaseParser.RefreshTableContext ctx) {
   }

   public void exitRefreshTable(SqlBaseParser.RefreshTableContext ctx) {
   }

   public void enterRefreshFunction(SqlBaseParser.RefreshFunctionContext ctx) {
   }

   public void exitRefreshFunction(SqlBaseParser.RefreshFunctionContext ctx) {
   }

   public void enterRefreshResource(SqlBaseParser.RefreshResourceContext ctx) {
   }

   public void exitRefreshResource(SqlBaseParser.RefreshResourceContext ctx) {
   }

   public void enterCacheTable(SqlBaseParser.CacheTableContext ctx) {
   }

   public void exitCacheTable(SqlBaseParser.CacheTableContext ctx) {
   }

   public void enterUncacheTable(SqlBaseParser.UncacheTableContext ctx) {
   }

   public void exitUncacheTable(SqlBaseParser.UncacheTableContext ctx) {
   }

   public void enterClearCache(SqlBaseParser.ClearCacheContext ctx) {
   }

   public void exitClearCache(SqlBaseParser.ClearCacheContext ctx) {
   }

   public void enterLoadData(SqlBaseParser.LoadDataContext ctx) {
   }

   public void exitLoadData(SqlBaseParser.LoadDataContext ctx) {
   }

   public void enterTruncateTable(SqlBaseParser.TruncateTableContext ctx) {
   }

   public void exitTruncateTable(SqlBaseParser.TruncateTableContext ctx) {
   }

   public void enterRepairTable(SqlBaseParser.RepairTableContext ctx) {
   }

   public void exitRepairTable(SqlBaseParser.RepairTableContext ctx) {
   }

   public void enterManageResource(SqlBaseParser.ManageResourceContext ctx) {
   }

   public void exitManageResource(SqlBaseParser.ManageResourceContext ctx) {
   }

   public void enterCreateIndex(SqlBaseParser.CreateIndexContext ctx) {
   }

   public void exitCreateIndex(SqlBaseParser.CreateIndexContext ctx) {
   }

   public void enterDropIndex(SqlBaseParser.DropIndexContext ctx) {
   }

   public void exitDropIndex(SqlBaseParser.DropIndexContext ctx) {
   }

   public void enterCall(SqlBaseParser.CallContext ctx) {
   }

   public void exitCall(SqlBaseParser.CallContext ctx) {
   }

   public void enterFailNativeCommand(SqlBaseParser.FailNativeCommandContext ctx) {
   }

   public void exitFailNativeCommand(SqlBaseParser.FailNativeCommandContext ctx) {
   }

   public void enterFailSetRole(SqlBaseParser.FailSetRoleContext ctx) {
   }

   public void exitFailSetRole(SqlBaseParser.FailSetRoleContext ctx) {
   }

   public void enterSetTimeZone(SqlBaseParser.SetTimeZoneContext ctx) {
   }

   public void exitSetTimeZone(SqlBaseParser.SetTimeZoneContext ctx) {
   }

   public void enterSetVariable(SqlBaseParser.SetVariableContext ctx) {
   }

   public void exitSetVariable(SqlBaseParser.SetVariableContext ctx) {
   }

   public void enterSetQuotedConfiguration(SqlBaseParser.SetQuotedConfigurationContext ctx) {
   }

   public void exitSetQuotedConfiguration(SqlBaseParser.SetQuotedConfigurationContext ctx) {
   }

   public void enterSetConfiguration(SqlBaseParser.SetConfigurationContext ctx) {
   }

   public void exitSetConfiguration(SqlBaseParser.SetConfigurationContext ctx) {
   }

   public void enterResetQuotedConfiguration(SqlBaseParser.ResetQuotedConfigurationContext ctx) {
   }

   public void exitResetQuotedConfiguration(SqlBaseParser.ResetQuotedConfigurationContext ctx) {
   }

   public void enterResetConfiguration(SqlBaseParser.ResetConfigurationContext ctx) {
   }

   public void exitResetConfiguration(SqlBaseParser.ResetConfigurationContext ctx) {
   }

   public void enterExecuteImmediate(SqlBaseParser.ExecuteImmediateContext ctx) {
   }

   public void exitExecuteImmediate(SqlBaseParser.ExecuteImmediateContext ctx) {
   }

   public void enterExecuteImmediateUsing(SqlBaseParser.ExecuteImmediateUsingContext ctx) {
   }

   public void exitExecuteImmediateUsing(SqlBaseParser.ExecuteImmediateUsingContext ctx) {
   }

   public void enterExecuteImmediateQueryParam(SqlBaseParser.ExecuteImmediateQueryParamContext ctx) {
   }

   public void exitExecuteImmediateQueryParam(SqlBaseParser.ExecuteImmediateQueryParamContext ctx) {
   }

   public void enterExecuteImmediateArgument(SqlBaseParser.ExecuteImmediateArgumentContext ctx) {
   }

   public void exitExecuteImmediateArgument(SqlBaseParser.ExecuteImmediateArgumentContext ctx) {
   }

   public void enterExecuteImmediateArgumentSeq(SqlBaseParser.ExecuteImmediateArgumentSeqContext ctx) {
   }

   public void exitExecuteImmediateArgumentSeq(SqlBaseParser.ExecuteImmediateArgumentSeqContext ctx) {
   }

   public void enterTimezone(SqlBaseParser.TimezoneContext ctx) {
   }

   public void exitTimezone(SqlBaseParser.TimezoneContext ctx) {
   }

   public void enterConfigKey(SqlBaseParser.ConfigKeyContext ctx) {
   }

   public void exitConfigKey(SqlBaseParser.ConfigKeyContext ctx) {
   }

   public void enterConfigValue(SqlBaseParser.ConfigValueContext ctx) {
   }

   public void exitConfigValue(SqlBaseParser.ConfigValueContext ctx) {
   }

   public void enterUnsupportedHiveNativeCommands(SqlBaseParser.UnsupportedHiveNativeCommandsContext ctx) {
   }

   public void exitUnsupportedHiveNativeCommands(SqlBaseParser.UnsupportedHiveNativeCommandsContext ctx) {
   }

   public void enterCreateTableHeader(SqlBaseParser.CreateTableHeaderContext ctx) {
   }

   public void exitCreateTableHeader(SqlBaseParser.CreateTableHeaderContext ctx) {
   }

   public void enterReplaceTableHeader(SqlBaseParser.ReplaceTableHeaderContext ctx) {
   }

   public void exitReplaceTableHeader(SqlBaseParser.ReplaceTableHeaderContext ctx) {
   }

   public void enterClusterBySpec(SqlBaseParser.ClusterBySpecContext ctx) {
   }

   public void exitClusterBySpec(SqlBaseParser.ClusterBySpecContext ctx) {
   }

   public void enterBucketSpec(SqlBaseParser.BucketSpecContext ctx) {
   }

   public void exitBucketSpec(SqlBaseParser.BucketSpecContext ctx) {
   }

   public void enterSkewSpec(SqlBaseParser.SkewSpecContext ctx) {
   }

   public void exitSkewSpec(SqlBaseParser.SkewSpecContext ctx) {
   }

   public void enterLocationSpec(SqlBaseParser.LocationSpecContext ctx) {
   }

   public void exitLocationSpec(SqlBaseParser.LocationSpecContext ctx) {
   }

   public void enterSchemaBinding(SqlBaseParser.SchemaBindingContext ctx) {
   }

   public void exitSchemaBinding(SqlBaseParser.SchemaBindingContext ctx) {
   }

   public void enterCommentSpec(SqlBaseParser.CommentSpecContext ctx) {
   }

   public void exitCommentSpec(SqlBaseParser.CommentSpecContext ctx) {
   }

   public void enterSingleQuery(SqlBaseParser.SingleQueryContext ctx) {
   }

   public void exitSingleQuery(SqlBaseParser.SingleQueryContext ctx) {
   }

   public void enterQuery(SqlBaseParser.QueryContext ctx) {
   }

   public void exitQuery(SqlBaseParser.QueryContext ctx) {
   }

   public void enterInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext ctx) {
   }

   public void exitInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext ctx) {
   }

   public void enterInsertIntoTable(SqlBaseParser.InsertIntoTableContext ctx) {
   }

   public void exitInsertIntoTable(SqlBaseParser.InsertIntoTableContext ctx) {
   }

   public void enterInsertIntoReplaceWhere(SqlBaseParser.InsertIntoReplaceWhereContext ctx) {
   }

   public void exitInsertIntoReplaceWhere(SqlBaseParser.InsertIntoReplaceWhereContext ctx) {
   }

   public void enterInsertOverwriteHiveDir(SqlBaseParser.InsertOverwriteHiveDirContext ctx) {
   }

   public void exitInsertOverwriteHiveDir(SqlBaseParser.InsertOverwriteHiveDirContext ctx) {
   }

   public void enterInsertOverwriteDir(SqlBaseParser.InsertOverwriteDirContext ctx) {
   }

   public void exitInsertOverwriteDir(SqlBaseParser.InsertOverwriteDirContext ctx) {
   }

   public void enterPartitionSpecLocation(SqlBaseParser.PartitionSpecLocationContext ctx) {
   }

   public void exitPartitionSpecLocation(SqlBaseParser.PartitionSpecLocationContext ctx) {
   }

   public void enterPartitionSpec(SqlBaseParser.PartitionSpecContext ctx) {
   }

   public void exitPartitionSpec(SqlBaseParser.PartitionSpecContext ctx) {
   }

   public void enterPartitionVal(SqlBaseParser.PartitionValContext ctx) {
   }

   public void exitPartitionVal(SqlBaseParser.PartitionValContext ctx) {
   }

   public void enterNamespace(SqlBaseParser.NamespaceContext ctx) {
   }

   public void exitNamespace(SqlBaseParser.NamespaceContext ctx) {
   }

   public void enterNamespaces(SqlBaseParser.NamespacesContext ctx) {
   }

   public void exitNamespaces(SqlBaseParser.NamespacesContext ctx) {
   }

   public void enterVariable(SqlBaseParser.VariableContext ctx) {
   }

   public void exitVariable(SqlBaseParser.VariableContext ctx) {
   }

   public void enterDescribeFuncName(SqlBaseParser.DescribeFuncNameContext ctx) {
   }

   public void exitDescribeFuncName(SqlBaseParser.DescribeFuncNameContext ctx) {
   }

   public void enterDescribeColName(SqlBaseParser.DescribeColNameContext ctx) {
   }

   public void exitDescribeColName(SqlBaseParser.DescribeColNameContext ctx) {
   }

   public void enterCtes(SqlBaseParser.CtesContext ctx) {
   }

   public void exitCtes(SqlBaseParser.CtesContext ctx) {
   }

   public void enterNamedQuery(SqlBaseParser.NamedQueryContext ctx) {
   }

   public void exitNamedQuery(SqlBaseParser.NamedQueryContext ctx) {
   }

   public void enterTableProvider(SqlBaseParser.TableProviderContext ctx) {
   }

   public void exitTableProvider(SqlBaseParser.TableProviderContext ctx) {
   }

   public void enterCreateTableClauses(SqlBaseParser.CreateTableClausesContext ctx) {
   }

   public void exitCreateTableClauses(SqlBaseParser.CreateTableClausesContext ctx) {
   }

   public void enterPropertyList(SqlBaseParser.PropertyListContext ctx) {
   }

   public void exitPropertyList(SqlBaseParser.PropertyListContext ctx) {
   }

   public void enterProperty(SqlBaseParser.PropertyContext ctx) {
   }

   public void exitProperty(SqlBaseParser.PropertyContext ctx) {
   }

   public void enterPropertyKey(SqlBaseParser.PropertyKeyContext ctx) {
   }

   public void exitPropertyKey(SqlBaseParser.PropertyKeyContext ctx) {
   }

   public void enterPropertyValue(SqlBaseParser.PropertyValueContext ctx) {
   }

   public void exitPropertyValue(SqlBaseParser.PropertyValueContext ctx) {
   }

   public void enterExpressionPropertyList(SqlBaseParser.ExpressionPropertyListContext ctx) {
   }

   public void exitExpressionPropertyList(SqlBaseParser.ExpressionPropertyListContext ctx) {
   }

   public void enterExpressionProperty(SqlBaseParser.ExpressionPropertyContext ctx) {
   }

   public void exitExpressionProperty(SqlBaseParser.ExpressionPropertyContext ctx) {
   }

   public void enterConstantList(SqlBaseParser.ConstantListContext ctx) {
   }

   public void exitConstantList(SqlBaseParser.ConstantListContext ctx) {
   }

   public void enterNestedConstantList(SqlBaseParser.NestedConstantListContext ctx) {
   }

   public void exitNestedConstantList(SqlBaseParser.NestedConstantListContext ctx) {
   }

   public void enterCreateFileFormat(SqlBaseParser.CreateFileFormatContext ctx) {
   }

   public void exitCreateFileFormat(SqlBaseParser.CreateFileFormatContext ctx) {
   }

   public void enterTableFileFormat(SqlBaseParser.TableFileFormatContext ctx) {
   }

   public void exitTableFileFormat(SqlBaseParser.TableFileFormatContext ctx) {
   }

   public void enterGenericFileFormat(SqlBaseParser.GenericFileFormatContext ctx) {
   }

   public void exitGenericFileFormat(SqlBaseParser.GenericFileFormatContext ctx) {
   }

   public void enterStorageHandler(SqlBaseParser.StorageHandlerContext ctx) {
   }

   public void exitStorageHandler(SqlBaseParser.StorageHandlerContext ctx) {
   }

   public void enterResource(SqlBaseParser.ResourceContext ctx) {
   }

   public void exitResource(SqlBaseParser.ResourceContext ctx) {
   }

   public void enterSingleInsertQuery(SqlBaseParser.SingleInsertQueryContext ctx) {
   }

   public void exitSingleInsertQuery(SqlBaseParser.SingleInsertQueryContext ctx) {
   }

   public void enterMultiInsertQuery(SqlBaseParser.MultiInsertQueryContext ctx) {
   }

   public void exitMultiInsertQuery(SqlBaseParser.MultiInsertQueryContext ctx) {
   }

   public void enterDeleteFromTable(SqlBaseParser.DeleteFromTableContext ctx) {
   }

   public void exitDeleteFromTable(SqlBaseParser.DeleteFromTableContext ctx) {
   }

   public void enterUpdateTable(SqlBaseParser.UpdateTableContext ctx) {
   }

   public void exitUpdateTable(SqlBaseParser.UpdateTableContext ctx) {
   }

   public void enterMergeIntoTable(SqlBaseParser.MergeIntoTableContext ctx) {
   }

   public void exitMergeIntoTable(SqlBaseParser.MergeIntoTableContext ctx) {
   }

   public void enterIdentifierReference(SqlBaseParser.IdentifierReferenceContext ctx) {
   }

   public void exitIdentifierReference(SqlBaseParser.IdentifierReferenceContext ctx) {
   }

   public void enterCatalogIdentifierReference(SqlBaseParser.CatalogIdentifierReferenceContext ctx) {
   }

   public void exitCatalogIdentifierReference(SqlBaseParser.CatalogIdentifierReferenceContext ctx) {
   }

   public void enterQueryOrganization(SqlBaseParser.QueryOrganizationContext ctx) {
   }

   public void exitQueryOrganization(SqlBaseParser.QueryOrganizationContext ctx) {
   }

   public void enterMultiInsertQueryBody(SqlBaseParser.MultiInsertQueryBodyContext ctx) {
   }

   public void exitMultiInsertQueryBody(SqlBaseParser.MultiInsertQueryBodyContext ctx) {
   }

   public void enterOperatorPipeStatement(SqlBaseParser.OperatorPipeStatementContext ctx) {
   }

   public void exitOperatorPipeStatement(SqlBaseParser.OperatorPipeStatementContext ctx) {
   }

   public void enterQueryTermDefault(SqlBaseParser.QueryTermDefaultContext ctx) {
   }

   public void exitQueryTermDefault(SqlBaseParser.QueryTermDefaultContext ctx) {
   }

   public void enterSetOperation(SqlBaseParser.SetOperationContext ctx) {
   }

   public void exitSetOperation(SqlBaseParser.SetOperationContext ctx) {
   }

   public void enterQueryPrimaryDefault(SqlBaseParser.QueryPrimaryDefaultContext ctx) {
   }

   public void exitQueryPrimaryDefault(SqlBaseParser.QueryPrimaryDefaultContext ctx) {
   }

   public void enterFromStmt(SqlBaseParser.FromStmtContext ctx) {
   }

   public void exitFromStmt(SqlBaseParser.FromStmtContext ctx) {
   }

   public void enterTable(SqlBaseParser.TableContext ctx) {
   }

   public void exitTable(SqlBaseParser.TableContext ctx) {
   }

   public void enterInlineTableDefault1(SqlBaseParser.InlineTableDefault1Context ctx) {
   }

   public void exitInlineTableDefault1(SqlBaseParser.InlineTableDefault1Context ctx) {
   }

   public void enterSubquery(SqlBaseParser.SubqueryContext ctx) {
   }

   public void exitSubquery(SqlBaseParser.SubqueryContext ctx) {
   }

   public void enterSortItem(SqlBaseParser.SortItemContext ctx) {
   }

   public void exitSortItem(SqlBaseParser.SortItemContext ctx) {
   }

   public void enterFromStatement(SqlBaseParser.FromStatementContext ctx) {
   }

   public void exitFromStatement(SqlBaseParser.FromStatementContext ctx) {
   }

   public void enterFromStatementBody(SqlBaseParser.FromStatementBodyContext ctx) {
   }

   public void exitFromStatementBody(SqlBaseParser.FromStatementBodyContext ctx) {
   }

   public void enterTransformQuerySpecification(SqlBaseParser.TransformQuerySpecificationContext ctx) {
   }

   public void exitTransformQuerySpecification(SqlBaseParser.TransformQuerySpecificationContext ctx) {
   }

   public void enterRegularQuerySpecification(SqlBaseParser.RegularQuerySpecificationContext ctx) {
   }

   public void exitRegularQuerySpecification(SqlBaseParser.RegularQuerySpecificationContext ctx) {
   }

   public void enterTransformClause(SqlBaseParser.TransformClauseContext ctx) {
   }

   public void exitTransformClause(SqlBaseParser.TransformClauseContext ctx) {
   }

   public void enterSelectClause(SqlBaseParser.SelectClauseContext ctx) {
   }

   public void exitSelectClause(SqlBaseParser.SelectClauseContext ctx) {
   }

   public void enterSetClause(SqlBaseParser.SetClauseContext ctx) {
   }

   public void exitSetClause(SqlBaseParser.SetClauseContext ctx) {
   }

   public void enterMatchedClause(SqlBaseParser.MatchedClauseContext ctx) {
   }

   public void exitMatchedClause(SqlBaseParser.MatchedClauseContext ctx) {
   }

   public void enterNotMatchedClause(SqlBaseParser.NotMatchedClauseContext ctx) {
   }

   public void exitNotMatchedClause(SqlBaseParser.NotMatchedClauseContext ctx) {
   }

   public void enterNotMatchedBySourceClause(SqlBaseParser.NotMatchedBySourceClauseContext ctx) {
   }

   public void exitNotMatchedBySourceClause(SqlBaseParser.NotMatchedBySourceClauseContext ctx) {
   }

   public void enterMatchedAction(SqlBaseParser.MatchedActionContext ctx) {
   }

   public void exitMatchedAction(SqlBaseParser.MatchedActionContext ctx) {
   }

   public void enterNotMatchedAction(SqlBaseParser.NotMatchedActionContext ctx) {
   }

   public void exitNotMatchedAction(SqlBaseParser.NotMatchedActionContext ctx) {
   }

   public void enterNotMatchedBySourceAction(SqlBaseParser.NotMatchedBySourceActionContext ctx) {
   }

   public void exitNotMatchedBySourceAction(SqlBaseParser.NotMatchedBySourceActionContext ctx) {
   }

   public void enterExceptClause(SqlBaseParser.ExceptClauseContext ctx) {
   }

   public void exitExceptClause(SqlBaseParser.ExceptClauseContext ctx) {
   }

   public void enterAssignmentList(SqlBaseParser.AssignmentListContext ctx) {
   }

   public void exitAssignmentList(SqlBaseParser.AssignmentListContext ctx) {
   }

   public void enterAssignment(SqlBaseParser.AssignmentContext ctx) {
   }

   public void exitAssignment(SqlBaseParser.AssignmentContext ctx) {
   }

   public void enterWhereClause(SqlBaseParser.WhereClauseContext ctx) {
   }

   public void exitWhereClause(SqlBaseParser.WhereClauseContext ctx) {
   }

   public void enterHavingClause(SqlBaseParser.HavingClauseContext ctx) {
   }

   public void exitHavingClause(SqlBaseParser.HavingClauseContext ctx) {
   }

   public void enterHint(SqlBaseParser.HintContext ctx) {
   }

   public void exitHint(SqlBaseParser.HintContext ctx) {
   }

   public void enterHintStatement(SqlBaseParser.HintStatementContext ctx) {
   }

   public void exitHintStatement(SqlBaseParser.HintStatementContext ctx) {
   }

   public void enterFromClause(SqlBaseParser.FromClauseContext ctx) {
   }

   public void exitFromClause(SqlBaseParser.FromClauseContext ctx) {
   }

   public void enterTemporalClause(SqlBaseParser.TemporalClauseContext ctx) {
   }

   public void exitTemporalClause(SqlBaseParser.TemporalClauseContext ctx) {
   }

   public void enterAggregationClause(SqlBaseParser.AggregationClauseContext ctx) {
   }

   public void exitAggregationClause(SqlBaseParser.AggregationClauseContext ctx) {
   }

   public void enterGroupByClause(SqlBaseParser.GroupByClauseContext ctx) {
   }

   public void exitGroupByClause(SqlBaseParser.GroupByClauseContext ctx) {
   }

   public void enterGroupingAnalytics(SqlBaseParser.GroupingAnalyticsContext ctx) {
   }

   public void exitGroupingAnalytics(SqlBaseParser.GroupingAnalyticsContext ctx) {
   }

   public void enterGroupingElement(SqlBaseParser.GroupingElementContext ctx) {
   }

   public void exitGroupingElement(SqlBaseParser.GroupingElementContext ctx) {
   }

   public void enterGroupingSet(SqlBaseParser.GroupingSetContext ctx) {
   }

   public void exitGroupingSet(SqlBaseParser.GroupingSetContext ctx) {
   }

   public void enterPivotClause(SqlBaseParser.PivotClauseContext ctx) {
   }

   public void exitPivotClause(SqlBaseParser.PivotClauseContext ctx) {
   }

   public void enterPivotColumn(SqlBaseParser.PivotColumnContext ctx) {
   }

   public void exitPivotColumn(SqlBaseParser.PivotColumnContext ctx) {
   }

   public void enterPivotValue(SqlBaseParser.PivotValueContext ctx) {
   }

   public void exitPivotValue(SqlBaseParser.PivotValueContext ctx) {
   }

   public void enterUnpivotClause(SqlBaseParser.UnpivotClauseContext ctx) {
   }

   public void exitUnpivotClause(SqlBaseParser.UnpivotClauseContext ctx) {
   }

   public void enterUnpivotNullClause(SqlBaseParser.UnpivotNullClauseContext ctx) {
   }

   public void exitUnpivotNullClause(SqlBaseParser.UnpivotNullClauseContext ctx) {
   }

   public void enterUnpivotOperator(SqlBaseParser.UnpivotOperatorContext ctx) {
   }

   public void exitUnpivotOperator(SqlBaseParser.UnpivotOperatorContext ctx) {
   }

   public void enterUnpivotSingleValueColumnClause(SqlBaseParser.UnpivotSingleValueColumnClauseContext ctx) {
   }

   public void exitUnpivotSingleValueColumnClause(SqlBaseParser.UnpivotSingleValueColumnClauseContext ctx) {
   }

   public void enterUnpivotMultiValueColumnClause(SqlBaseParser.UnpivotMultiValueColumnClauseContext ctx) {
   }

   public void exitUnpivotMultiValueColumnClause(SqlBaseParser.UnpivotMultiValueColumnClauseContext ctx) {
   }

   public void enterUnpivotColumnSet(SqlBaseParser.UnpivotColumnSetContext ctx) {
   }

   public void exitUnpivotColumnSet(SqlBaseParser.UnpivotColumnSetContext ctx) {
   }

   public void enterUnpivotValueColumn(SqlBaseParser.UnpivotValueColumnContext ctx) {
   }

   public void exitUnpivotValueColumn(SqlBaseParser.UnpivotValueColumnContext ctx) {
   }

   public void enterUnpivotNameColumn(SqlBaseParser.UnpivotNameColumnContext ctx) {
   }

   public void exitUnpivotNameColumn(SqlBaseParser.UnpivotNameColumnContext ctx) {
   }

   public void enterUnpivotColumnAndAlias(SqlBaseParser.UnpivotColumnAndAliasContext ctx) {
   }

   public void exitUnpivotColumnAndAlias(SqlBaseParser.UnpivotColumnAndAliasContext ctx) {
   }

   public void enterUnpivotColumn(SqlBaseParser.UnpivotColumnContext ctx) {
   }

   public void exitUnpivotColumn(SqlBaseParser.UnpivotColumnContext ctx) {
   }

   public void enterUnpivotAlias(SqlBaseParser.UnpivotAliasContext ctx) {
   }

   public void exitUnpivotAlias(SqlBaseParser.UnpivotAliasContext ctx) {
   }

   public void enterLateralView(SqlBaseParser.LateralViewContext ctx) {
   }

   public void exitLateralView(SqlBaseParser.LateralViewContext ctx) {
   }

   public void enterSetQuantifier(SqlBaseParser.SetQuantifierContext ctx) {
   }

   public void exitSetQuantifier(SqlBaseParser.SetQuantifierContext ctx) {
   }

   public void enterRelation(SqlBaseParser.RelationContext ctx) {
   }

   public void exitRelation(SqlBaseParser.RelationContext ctx) {
   }

   public void enterRelationExtension(SqlBaseParser.RelationExtensionContext ctx) {
   }

   public void exitRelationExtension(SqlBaseParser.RelationExtensionContext ctx) {
   }

   public void enterJoinRelation(SqlBaseParser.JoinRelationContext ctx) {
   }

   public void exitJoinRelation(SqlBaseParser.JoinRelationContext ctx) {
   }

   public void enterJoinType(SqlBaseParser.JoinTypeContext ctx) {
   }

   public void exitJoinType(SqlBaseParser.JoinTypeContext ctx) {
   }

   public void enterJoinCriteria(SqlBaseParser.JoinCriteriaContext ctx) {
   }

   public void exitJoinCriteria(SqlBaseParser.JoinCriteriaContext ctx) {
   }

   public void enterSample(SqlBaseParser.SampleContext ctx) {
   }

   public void exitSample(SqlBaseParser.SampleContext ctx) {
   }

   public void enterSampleByPercentile(SqlBaseParser.SampleByPercentileContext ctx) {
   }

   public void exitSampleByPercentile(SqlBaseParser.SampleByPercentileContext ctx) {
   }

   public void enterSampleByRows(SqlBaseParser.SampleByRowsContext ctx) {
   }

   public void exitSampleByRows(SqlBaseParser.SampleByRowsContext ctx) {
   }

   public void enterSampleByBucket(SqlBaseParser.SampleByBucketContext ctx) {
   }

   public void exitSampleByBucket(SqlBaseParser.SampleByBucketContext ctx) {
   }

   public void enterSampleByBytes(SqlBaseParser.SampleByBytesContext ctx) {
   }

   public void exitSampleByBytes(SqlBaseParser.SampleByBytesContext ctx) {
   }

   public void enterIdentifierList(SqlBaseParser.IdentifierListContext ctx) {
   }

   public void exitIdentifierList(SqlBaseParser.IdentifierListContext ctx) {
   }

   public void enterIdentifierSeq(SqlBaseParser.IdentifierSeqContext ctx) {
   }

   public void exitIdentifierSeq(SqlBaseParser.IdentifierSeqContext ctx) {
   }

   public void enterOrderedIdentifierList(SqlBaseParser.OrderedIdentifierListContext ctx) {
   }

   public void exitOrderedIdentifierList(SqlBaseParser.OrderedIdentifierListContext ctx) {
   }

   public void enterOrderedIdentifier(SqlBaseParser.OrderedIdentifierContext ctx) {
   }

   public void exitOrderedIdentifier(SqlBaseParser.OrderedIdentifierContext ctx) {
   }

   public void enterIdentifierCommentList(SqlBaseParser.IdentifierCommentListContext ctx) {
   }

   public void exitIdentifierCommentList(SqlBaseParser.IdentifierCommentListContext ctx) {
   }

   public void enterIdentifierComment(SqlBaseParser.IdentifierCommentContext ctx) {
   }

   public void exitIdentifierComment(SqlBaseParser.IdentifierCommentContext ctx) {
   }

   public void enterTableName(SqlBaseParser.TableNameContext ctx) {
   }

   public void exitTableName(SqlBaseParser.TableNameContext ctx) {
   }

   public void enterAliasedQuery(SqlBaseParser.AliasedQueryContext ctx) {
   }

   public void exitAliasedQuery(SqlBaseParser.AliasedQueryContext ctx) {
   }

   public void enterAliasedRelation(SqlBaseParser.AliasedRelationContext ctx) {
   }

   public void exitAliasedRelation(SqlBaseParser.AliasedRelationContext ctx) {
   }

   public void enterInlineTableDefault2(SqlBaseParser.InlineTableDefault2Context ctx) {
   }

   public void exitInlineTableDefault2(SqlBaseParser.InlineTableDefault2Context ctx) {
   }

   public void enterTableValuedFunction(SqlBaseParser.TableValuedFunctionContext ctx) {
   }

   public void exitTableValuedFunction(SqlBaseParser.TableValuedFunctionContext ctx) {
   }

   public void enterOptionsClause(SqlBaseParser.OptionsClauseContext ctx) {
   }

   public void exitOptionsClause(SqlBaseParser.OptionsClauseContext ctx) {
   }

   public void enterInlineTable(SqlBaseParser.InlineTableContext ctx) {
   }

   public void exitInlineTable(SqlBaseParser.InlineTableContext ctx) {
   }

   public void enterFunctionTableSubqueryArgument(SqlBaseParser.FunctionTableSubqueryArgumentContext ctx) {
   }

   public void exitFunctionTableSubqueryArgument(SqlBaseParser.FunctionTableSubqueryArgumentContext ctx) {
   }

   public void enterTableArgumentPartitioning(SqlBaseParser.TableArgumentPartitioningContext ctx) {
   }

   public void exitTableArgumentPartitioning(SqlBaseParser.TableArgumentPartitioningContext ctx) {
   }

   public void enterFunctionTableNamedArgumentExpression(SqlBaseParser.FunctionTableNamedArgumentExpressionContext ctx) {
   }

   public void exitFunctionTableNamedArgumentExpression(SqlBaseParser.FunctionTableNamedArgumentExpressionContext ctx) {
   }

   public void enterFunctionTableReferenceArgument(SqlBaseParser.FunctionTableReferenceArgumentContext ctx) {
   }

   public void exitFunctionTableReferenceArgument(SqlBaseParser.FunctionTableReferenceArgumentContext ctx) {
   }

   public void enterFunctionTableArgument(SqlBaseParser.FunctionTableArgumentContext ctx) {
   }

   public void exitFunctionTableArgument(SqlBaseParser.FunctionTableArgumentContext ctx) {
   }

   public void enterFunctionTable(SqlBaseParser.FunctionTableContext ctx) {
   }

   public void exitFunctionTable(SqlBaseParser.FunctionTableContext ctx) {
   }

   public void enterTableAlias(SqlBaseParser.TableAliasContext ctx) {
   }

   public void exitTableAlias(SqlBaseParser.TableAliasContext ctx) {
   }

   public void enterRowFormatSerde(SqlBaseParser.RowFormatSerdeContext ctx) {
   }

   public void exitRowFormatSerde(SqlBaseParser.RowFormatSerdeContext ctx) {
   }

   public void enterRowFormatDelimited(SqlBaseParser.RowFormatDelimitedContext ctx) {
   }

   public void exitRowFormatDelimited(SqlBaseParser.RowFormatDelimitedContext ctx) {
   }

   public void enterMultipartIdentifierList(SqlBaseParser.MultipartIdentifierListContext ctx) {
   }

   public void exitMultipartIdentifierList(SqlBaseParser.MultipartIdentifierListContext ctx) {
   }

   public void enterMultipartIdentifier(SqlBaseParser.MultipartIdentifierContext ctx) {
   }

   public void exitMultipartIdentifier(SqlBaseParser.MultipartIdentifierContext ctx) {
   }

   public void enterMultipartIdentifierPropertyList(SqlBaseParser.MultipartIdentifierPropertyListContext ctx) {
   }

   public void exitMultipartIdentifierPropertyList(SqlBaseParser.MultipartIdentifierPropertyListContext ctx) {
   }

   public void enterMultipartIdentifierProperty(SqlBaseParser.MultipartIdentifierPropertyContext ctx) {
   }

   public void exitMultipartIdentifierProperty(SqlBaseParser.MultipartIdentifierPropertyContext ctx) {
   }

   public void enterTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
   }

   public void exitTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
   }

   public void enterFunctionIdentifier(SqlBaseParser.FunctionIdentifierContext ctx) {
   }

   public void exitFunctionIdentifier(SqlBaseParser.FunctionIdentifierContext ctx) {
   }

   public void enterNamedExpression(SqlBaseParser.NamedExpressionContext ctx) {
   }

   public void exitNamedExpression(SqlBaseParser.NamedExpressionContext ctx) {
   }

   public void enterNamedExpressionSeq(SqlBaseParser.NamedExpressionSeqContext ctx) {
   }

   public void exitNamedExpressionSeq(SqlBaseParser.NamedExpressionSeqContext ctx) {
   }

   public void enterPartitionFieldList(SqlBaseParser.PartitionFieldListContext ctx) {
   }

   public void exitPartitionFieldList(SqlBaseParser.PartitionFieldListContext ctx) {
   }

   public void enterPartitionTransform(SqlBaseParser.PartitionTransformContext ctx) {
   }

   public void exitPartitionTransform(SqlBaseParser.PartitionTransformContext ctx) {
   }

   public void enterPartitionColumn(SqlBaseParser.PartitionColumnContext ctx) {
   }

   public void exitPartitionColumn(SqlBaseParser.PartitionColumnContext ctx) {
   }

   public void enterIdentityTransform(SqlBaseParser.IdentityTransformContext ctx) {
   }

   public void exitIdentityTransform(SqlBaseParser.IdentityTransformContext ctx) {
   }

   public void enterApplyTransform(SqlBaseParser.ApplyTransformContext ctx) {
   }

   public void exitApplyTransform(SqlBaseParser.ApplyTransformContext ctx) {
   }

   public void enterTransformArgument(SqlBaseParser.TransformArgumentContext ctx) {
   }

   public void exitTransformArgument(SqlBaseParser.TransformArgumentContext ctx) {
   }

   public void enterExpression(SqlBaseParser.ExpressionContext ctx) {
   }

   public void exitExpression(SqlBaseParser.ExpressionContext ctx) {
   }

   public void enterNamedArgumentExpression(SqlBaseParser.NamedArgumentExpressionContext ctx) {
   }

   public void exitNamedArgumentExpression(SqlBaseParser.NamedArgumentExpressionContext ctx) {
   }

   public void enterFunctionArgument(SqlBaseParser.FunctionArgumentContext ctx) {
   }

   public void exitFunctionArgument(SqlBaseParser.FunctionArgumentContext ctx) {
   }

   public void enterExpressionSeq(SqlBaseParser.ExpressionSeqContext ctx) {
   }

   public void exitExpressionSeq(SqlBaseParser.ExpressionSeqContext ctx) {
   }

   public void enterLogicalNot(SqlBaseParser.LogicalNotContext ctx) {
   }

   public void exitLogicalNot(SqlBaseParser.LogicalNotContext ctx) {
   }

   public void enterPredicated(SqlBaseParser.PredicatedContext ctx) {
   }

   public void exitPredicated(SqlBaseParser.PredicatedContext ctx) {
   }

   public void enterExists(SqlBaseParser.ExistsContext ctx) {
   }

   public void exitExists(SqlBaseParser.ExistsContext ctx) {
   }

   public void enterLogicalBinary(SqlBaseParser.LogicalBinaryContext ctx) {
   }

   public void exitLogicalBinary(SqlBaseParser.LogicalBinaryContext ctx) {
   }

   public void enterPredicate(SqlBaseParser.PredicateContext ctx) {
   }

   public void exitPredicate(SqlBaseParser.PredicateContext ctx) {
   }

   public void enterErrorCapturingNot(SqlBaseParser.ErrorCapturingNotContext ctx) {
   }

   public void exitErrorCapturingNot(SqlBaseParser.ErrorCapturingNotContext ctx) {
   }

   public void enterValueExpressionDefault(SqlBaseParser.ValueExpressionDefaultContext ctx) {
   }

   public void exitValueExpressionDefault(SqlBaseParser.ValueExpressionDefaultContext ctx) {
   }

   public void enterComparison(SqlBaseParser.ComparisonContext ctx) {
   }

   public void exitComparison(SqlBaseParser.ComparisonContext ctx) {
   }

   public void enterShiftExpression(SqlBaseParser.ShiftExpressionContext ctx) {
   }

   public void exitShiftExpression(SqlBaseParser.ShiftExpressionContext ctx) {
   }

   public void enterArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext ctx) {
   }

   public void exitArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext ctx) {
   }

   public void enterArithmeticUnary(SqlBaseParser.ArithmeticUnaryContext ctx) {
   }

   public void exitArithmeticUnary(SqlBaseParser.ArithmeticUnaryContext ctx) {
   }

   public void enterShiftOperator(SqlBaseParser.ShiftOperatorContext ctx) {
   }

   public void exitShiftOperator(SqlBaseParser.ShiftOperatorContext ctx) {
   }

   public void enterDatetimeUnit(SqlBaseParser.DatetimeUnitContext ctx) {
   }

   public void exitDatetimeUnit(SqlBaseParser.DatetimeUnitContext ctx) {
   }

   public void enterStruct(SqlBaseParser.StructContext ctx) {
   }

   public void exitStruct(SqlBaseParser.StructContext ctx) {
   }

   public void enterDereference(SqlBaseParser.DereferenceContext ctx) {
   }

   public void exitDereference(SqlBaseParser.DereferenceContext ctx) {
   }

   public void enterCastByColon(SqlBaseParser.CastByColonContext ctx) {
   }

   public void exitCastByColon(SqlBaseParser.CastByColonContext ctx) {
   }

   public void enterTimestampadd(SqlBaseParser.TimestampaddContext ctx) {
   }

   public void exitTimestampadd(SqlBaseParser.TimestampaddContext ctx) {
   }

   public void enterSubstring(SqlBaseParser.SubstringContext ctx) {
   }

   public void exitSubstring(SqlBaseParser.SubstringContext ctx) {
   }

   public void enterCast(SqlBaseParser.CastContext ctx) {
   }

   public void exitCast(SqlBaseParser.CastContext ctx) {
   }

   public void enterLambda(SqlBaseParser.LambdaContext ctx) {
   }

   public void exitLambda(SqlBaseParser.LambdaContext ctx) {
   }

   public void enterParenthesizedExpression(SqlBaseParser.ParenthesizedExpressionContext ctx) {
   }

   public void exitParenthesizedExpression(SqlBaseParser.ParenthesizedExpressionContext ctx) {
   }

   public void enterAny_value(SqlBaseParser.Any_valueContext ctx) {
   }

   public void exitAny_value(SqlBaseParser.Any_valueContext ctx) {
   }

   public void enterTrim(SqlBaseParser.TrimContext ctx) {
   }

   public void exitTrim(SqlBaseParser.TrimContext ctx) {
   }

   public void enterSimpleCase(SqlBaseParser.SimpleCaseContext ctx) {
   }

   public void exitSimpleCase(SqlBaseParser.SimpleCaseContext ctx) {
   }

   public void enterCurrentLike(SqlBaseParser.CurrentLikeContext ctx) {
   }

   public void exitCurrentLike(SqlBaseParser.CurrentLikeContext ctx) {
   }

   public void enterColumnReference(SqlBaseParser.ColumnReferenceContext ctx) {
   }

   public void exitColumnReference(SqlBaseParser.ColumnReferenceContext ctx) {
   }

   public void enterRowConstructor(SqlBaseParser.RowConstructorContext ctx) {
   }

   public void exitRowConstructor(SqlBaseParser.RowConstructorContext ctx) {
   }

   public void enterLast(SqlBaseParser.LastContext ctx) {
   }

   public void exitLast(SqlBaseParser.LastContext ctx) {
   }

   public void enterStar(SqlBaseParser.StarContext ctx) {
   }

   public void exitStar(SqlBaseParser.StarContext ctx) {
   }

   public void enterOverlay(SqlBaseParser.OverlayContext ctx) {
   }

   public void exitOverlay(SqlBaseParser.OverlayContext ctx) {
   }

   public void enterSubscript(SqlBaseParser.SubscriptContext ctx) {
   }

   public void exitSubscript(SqlBaseParser.SubscriptContext ctx) {
   }

   public void enterTimestampdiff(SqlBaseParser.TimestampdiffContext ctx) {
   }

   public void exitTimestampdiff(SqlBaseParser.TimestampdiffContext ctx) {
   }

   public void enterSubqueryExpression(SqlBaseParser.SubqueryExpressionContext ctx) {
   }

   public void exitSubqueryExpression(SqlBaseParser.SubqueryExpressionContext ctx) {
   }

   public void enterCollate(SqlBaseParser.CollateContext ctx) {
   }

   public void exitCollate(SqlBaseParser.CollateContext ctx) {
   }

   public void enterConstantDefault(SqlBaseParser.ConstantDefaultContext ctx) {
   }

   public void exitConstantDefault(SqlBaseParser.ConstantDefaultContext ctx) {
   }

   public void enterExtract(SqlBaseParser.ExtractContext ctx) {
   }

   public void exitExtract(SqlBaseParser.ExtractContext ctx) {
   }

   public void enterFunctionCall(SqlBaseParser.FunctionCallContext ctx) {
   }

   public void exitFunctionCall(SqlBaseParser.FunctionCallContext ctx) {
   }

   public void enterSearchedCase(SqlBaseParser.SearchedCaseContext ctx) {
   }

   public void exitSearchedCase(SqlBaseParser.SearchedCaseContext ctx) {
   }

   public void enterPosition(SqlBaseParser.PositionContext ctx) {
   }

   public void exitPosition(SqlBaseParser.PositionContext ctx) {
   }

   public void enterFirst(SqlBaseParser.FirstContext ctx) {
   }

   public void exitFirst(SqlBaseParser.FirstContext ctx) {
   }

   public void enterLiteralType(SqlBaseParser.LiteralTypeContext ctx) {
   }

   public void exitLiteralType(SqlBaseParser.LiteralTypeContext ctx) {
   }

   public void enterNullLiteral(SqlBaseParser.NullLiteralContext ctx) {
   }

   public void exitNullLiteral(SqlBaseParser.NullLiteralContext ctx) {
   }

   public void enterPosParameterLiteral(SqlBaseParser.PosParameterLiteralContext ctx) {
   }

   public void exitPosParameterLiteral(SqlBaseParser.PosParameterLiteralContext ctx) {
   }

   public void enterNamedParameterLiteral(SqlBaseParser.NamedParameterLiteralContext ctx) {
   }

   public void exitNamedParameterLiteral(SqlBaseParser.NamedParameterLiteralContext ctx) {
   }

   public void enterIntervalLiteral(SqlBaseParser.IntervalLiteralContext ctx) {
   }

   public void exitIntervalLiteral(SqlBaseParser.IntervalLiteralContext ctx) {
   }

   public void enterTypeConstructor(SqlBaseParser.TypeConstructorContext ctx) {
   }

   public void exitTypeConstructor(SqlBaseParser.TypeConstructorContext ctx) {
   }

   public void enterNumericLiteral(SqlBaseParser.NumericLiteralContext ctx) {
   }

   public void exitNumericLiteral(SqlBaseParser.NumericLiteralContext ctx) {
   }

   public void enterBooleanLiteral(SqlBaseParser.BooleanLiteralContext ctx) {
   }

   public void exitBooleanLiteral(SqlBaseParser.BooleanLiteralContext ctx) {
   }

   public void enterStringLiteral(SqlBaseParser.StringLiteralContext ctx) {
   }

   public void exitStringLiteral(SqlBaseParser.StringLiteralContext ctx) {
   }

   public void enterComparisonOperator(SqlBaseParser.ComparisonOperatorContext ctx) {
   }

   public void exitComparisonOperator(SqlBaseParser.ComparisonOperatorContext ctx) {
   }

   public void enterArithmeticOperator(SqlBaseParser.ArithmeticOperatorContext ctx) {
   }

   public void exitArithmeticOperator(SqlBaseParser.ArithmeticOperatorContext ctx) {
   }

   public void enterPredicateOperator(SqlBaseParser.PredicateOperatorContext ctx) {
   }

   public void exitPredicateOperator(SqlBaseParser.PredicateOperatorContext ctx) {
   }

   public void enterBooleanValue(SqlBaseParser.BooleanValueContext ctx) {
   }

   public void exitBooleanValue(SqlBaseParser.BooleanValueContext ctx) {
   }

   public void enterInterval(SqlBaseParser.IntervalContext ctx) {
   }

   public void exitInterval(SqlBaseParser.IntervalContext ctx) {
   }

   public void enterErrorCapturingMultiUnitsInterval(SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext ctx) {
   }

   public void exitErrorCapturingMultiUnitsInterval(SqlBaseParser.ErrorCapturingMultiUnitsIntervalContext ctx) {
   }

   public void enterMultiUnitsInterval(SqlBaseParser.MultiUnitsIntervalContext ctx) {
   }

   public void exitMultiUnitsInterval(SqlBaseParser.MultiUnitsIntervalContext ctx) {
   }

   public void enterErrorCapturingUnitToUnitInterval(SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext ctx) {
   }

   public void exitErrorCapturingUnitToUnitInterval(SqlBaseParser.ErrorCapturingUnitToUnitIntervalContext ctx) {
   }

   public void enterUnitToUnitInterval(SqlBaseParser.UnitToUnitIntervalContext ctx) {
   }

   public void exitUnitToUnitInterval(SqlBaseParser.UnitToUnitIntervalContext ctx) {
   }

   public void enterIntervalValue(SqlBaseParser.IntervalValueContext ctx) {
   }

   public void exitIntervalValue(SqlBaseParser.IntervalValueContext ctx) {
   }

   public void enterUnitInMultiUnits(SqlBaseParser.UnitInMultiUnitsContext ctx) {
   }

   public void exitUnitInMultiUnits(SqlBaseParser.UnitInMultiUnitsContext ctx) {
   }

   public void enterUnitInUnitToUnit(SqlBaseParser.UnitInUnitToUnitContext ctx) {
   }

   public void exitUnitInUnitToUnit(SqlBaseParser.UnitInUnitToUnitContext ctx) {
   }

   public void enterColPosition(SqlBaseParser.ColPositionContext ctx) {
   }

   public void exitColPosition(SqlBaseParser.ColPositionContext ctx) {
   }

   public void enterCollationSpec(SqlBaseParser.CollationSpecContext ctx) {
   }

   public void exitCollationSpec(SqlBaseParser.CollationSpecContext ctx) {
   }

   public void enterCollateClause(SqlBaseParser.CollateClauseContext ctx) {
   }

   public void exitCollateClause(SqlBaseParser.CollateClauseContext ctx) {
   }

   public void enterType(SqlBaseParser.TypeContext ctx) {
   }

   public void exitType(SqlBaseParser.TypeContext ctx) {
   }

   public void enterComplexDataType(SqlBaseParser.ComplexDataTypeContext ctx) {
   }

   public void exitComplexDataType(SqlBaseParser.ComplexDataTypeContext ctx) {
   }

   public void enterYearMonthIntervalDataType(SqlBaseParser.YearMonthIntervalDataTypeContext ctx) {
   }

   public void exitYearMonthIntervalDataType(SqlBaseParser.YearMonthIntervalDataTypeContext ctx) {
   }

   public void enterDayTimeIntervalDataType(SqlBaseParser.DayTimeIntervalDataTypeContext ctx) {
   }

   public void exitDayTimeIntervalDataType(SqlBaseParser.DayTimeIntervalDataTypeContext ctx) {
   }

   public void enterPrimitiveDataType(SqlBaseParser.PrimitiveDataTypeContext ctx) {
   }

   public void exitPrimitiveDataType(SqlBaseParser.PrimitiveDataTypeContext ctx) {
   }

   public void enterQualifiedColTypeWithPositionList(SqlBaseParser.QualifiedColTypeWithPositionListContext ctx) {
   }

   public void exitQualifiedColTypeWithPositionList(SqlBaseParser.QualifiedColTypeWithPositionListContext ctx) {
   }

   public void enterQualifiedColTypeWithPosition(SqlBaseParser.QualifiedColTypeWithPositionContext ctx) {
   }

   public void exitQualifiedColTypeWithPosition(SqlBaseParser.QualifiedColTypeWithPositionContext ctx) {
   }

   public void enterColDefinitionDescriptorWithPosition(SqlBaseParser.ColDefinitionDescriptorWithPositionContext ctx) {
   }

   public void exitColDefinitionDescriptorWithPosition(SqlBaseParser.ColDefinitionDescriptorWithPositionContext ctx) {
   }

   public void enterDefaultExpression(SqlBaseParser.DefaultExpressionContext ctx) {
   }

   public void exitDefaultExpression(SqlBaseParser.DefaultExpressionContext ctx) {
   }

   public void enterVariableDefaultExpression(SqlBaseParser.VariableDefaultExpressionContext ctx) {
   }

   public void exitVariableDefaultExpression(SqlBaseParser.VariableDefaultExpressionContext ctx) {
   }

   public void enterColTypeList(SqlBaseParser.ColTypeListContext ctx) {
   }

   public void exitColTypeList(SqlBaseParser.ColTypeListContext ctx) {
   }

   public void enterColType(SqlBaseParser.ColTypeContext ctx) {
   }

   public void exitColType(SqlBaseParser.ColTypeContext ctx) {
   }

   public void enterColDefinitionList(SqlBaseParser.ColDefinitionListContext ctx) {
   }

   public void exitColDefinitionList(SqlBaseParser.ColDefinitionListContext ctx) {
   }

   public void enterColDefinition(SqlBaseParser.ColDefinitionContext ctx) {
   }

   public void exitColDefinition(SqlBaseParser.ColDefinitionContext ctx) {
   }

   public void enterColDefinitionOption(SqlBaseParser.ColDefinitionOptionContext ctx) {
   }

   public void exitColDefinitionOption(SqlBaseParser.ColDefinitionOptionContext ctx) {
   }

   public void enterGeneratedColumn(SqlBaseParser.GeneratedColumnContext ctx) {
   }

   public void exitGeneratedColumn(SqlBaseParser.GeneratedColumnContext ctx) {
   }

   public void enterIdentityColumn(SqlBaseParser.IdentityColumnContext ctx) {
   }

   public void exitIdentityColumn(SqlBaseParser.IdentityColumnContext ctx) {
   }

   public void enterIdentityColSpec(SqlBaseParser.IdentityColSpecContext ctx) {
   }

   public void exitIdentityColSpec(SqlBaseParser.IdentityColSpecContext ctx) {
   }

   public void enterSequenceGeneratorOption(SqlBaseParser.SequenceGeneratorOptionContext ctx) {
   }

   public void exitSequenceGeneratorOption(SqlBaseParser.SequenceGeneratorOptionContext ctx) {
   }

   public void enterSequenceGeneratorStartOrStep(SqlBaseParser.SequenceGeneratorStartOrStepContext ctx) {
   }

   public void exitSequenceGeneratorStartOrStep(SqlBaseParser.SequenceGeneratorStartOrStepContext ctx) {
   }

   public void enterComplexColTypeList(SqlBaseParser.ComplexColTypeListContext ctx) {
   }

   public void exitComplexColTypeList(SqlBaseParser.ComplexColTypeListContext ctx) {
   }

   public void enterComplexColType(SqlBaseParser.ComplexColTypeContext ctx) {
   }

   public void exitComplexColType(SqlBaseParser.ComplexColTypeContext ctx) {
   }

   public void enterRoutineCharacteristics(SqlBaseParser.RoutineCharacteristicsContext ctx) {
   }

   public void exitRoutineCharacteristics(SqlBaseParser.RoutineCharacteristicsContext ctx) {
   }

   public void enterRoutineLanguage(SqlBaseParser.RoutineLanguageContext ctx) {
   }

   public void exitRoutineLanguage(SqlBaseParser.RoutineLanguageContext ctx) {
   }

   public void enterSpecificName(SqlBaseParser.SpecificNameContext ctx) {
   }

   public void exitSpecificName(SqlBaseParser.SpecificNameContext ctx) {
   }

   public void enterDeterministic(SqlBaseParser.DeterministicContext ctx) {
   }

   public void exitDeterministic(SqlBaseParser.DeterministicContext ctx) {
   }

   public void enterSqlDataAccess(SqlBaseParser.SqlDataAccessContext ctx) {
   }

   public void exitSqlDataAccess(SqlBaseParser.SqlDataAccessContext ctx) {
   }

   public void enterNullCall(SqlBaseParser.NullCallContext ctx) {
   }

   public void exitNullCall(SqlBaseParser.NullCallContext ctx) {
   }

   public void enterRightsClause(SqlBaseParser.RightsClauseContext ctx) {
   }

   public void exitRightsClause(SqlBaseParser.RightsClauseContext ctx) {
   }

   public void enterWhenClause(SqlBaseParser.WhenClauseContext ctx) {
   }

   public void exitWhenClause(SqlBaseParser.WhenClauseContext ctx) {
   }

   public void enterWindowClause(SqlBaseParser.WindowClauseContext ctx) {
   }

   public void exitWindowClause(SqlBaseParser.WindowClauseContext ctx) {
   }

   public void enterNamedWindow(SqlBaseParser.NamedWindowContext ctx) {
   }

   public void exitNamedWindow(SqlBaseParser.NamedWindowContext ctx) {
   }

   public void enterWindowRef(SqlBaseParser.WindowRefContext ctx) {
   }

   public void exitWindowRef(SqlBaseParser.WindowRefContext ctx) {
   }

   public void enterWindowDef(SqlBaseParser.WindowDefContext ctx) {
   }

   public void exitWindowDef(SqlBaseParser.WindowDefContext ctx) {
   }

   public void enterWindowFrame(SqlBaseParser.WindowFrameContext ctx) {
   }

   public void exitWindowFrame(SqlBaseParser.WindowFrameContext ctx) {
   }

   public void enterFrameBound(SqlBaseParser.FrameBoundContext ctx) {
   }

   public void exitFrameBound(SqlBaseParser.FrameBoundContext ctx) {
   }

   public void enterQualifiedNameList(SqlBaseParser.QualifiedNameListContext ctx) {
   }

   public void exitQualifiedNameList(SqlBaseParser.QualifiedNameListContext ctx) {
   }

   public void enterFunctionName(SqlBaseParser.FunctionNameContext ctx) {
   }

   public void exitFunctionName(SqlBaseParser.FunctionNameContext ctx) {
   }

   public void enterQualifiedName(SqlBaseParser.QualifiedNameContext ctx) {
   }

   public void exitQualifiedName(SqlBaseParser.QualifiedNameContext ctx) {
   }

   public void enterErrorCapturingIdentifier(SqlBaseParser.ErrorCapturingIdentifierContext ctx) {
   }

   public void exitErrorCapturingIdentifier(SqlBaseParser.ErrorCapturingIdentifierContext ctx) {
   }

   public void enterErrorIdent(SqlBaseParser.ErrorIdentContext ctx) {
   }

   public void exitErrorIdent(SqlBaseParser.ErrorIdentContext ctx) {
   }

   public void enterRealIdent(SqlBaseParser.RealIdentContext ctx) {
   }

   public void exitRealIdent(SqlBaseParser.RealIdentContext ctx) {
   }

   public void enterIdentifier(SqlBaseParser.IdentifierContext ctx) {
   }

   public void exitIdentifier(SqlBaseParser.IdentifierContext ctx) {
   }

   public void enterUnquotedIdentifier(SqlBaseParser.UnquotedIdentifierContext ctx) {
   }

   public void exitUnquotedIdentifier(SqlBaseParser.UnquotedIdentifierContext ctx) {
   }

   public void enterQuotedIdentifierAlternative(SqlBaseParser.QuotedIdentifierAlternativeContext ctx) {
   }

   public void exitQuotedIdentifierAlternative(SqlBaseParser.QuotedIdentifierAlternativeContext ctx) {
   }

   public void enterQuotedIdentifier(SqlBaseParser.QuotedIdentifierContext ctx) {
   }

   public void exitQuotedIdentifier(SqlBaseParser.QuotedIdentifierContext ctx) {
   }

   public void enterBackQuotedIdentifier(SqlBaseParser.BackQuotedIdentifierContext ctx) {
   }

   public void exitBackQuotedIdentifier(SqlBaseParser.BackQuotedIdentifierContext ctx) {
   }

   public void enterExponentLiteral(SqlBaseParser.ExponentLiteralContext ctx) {
   }

   public void exitExponentLiteral(SqlBaseParser.ExponentLiteralContext ctx) {
   }

   public void enterDecimalLiteral(SqlBaseParser.DecimalLiteralContext ctx) {
   }

   public void exitDecimalLiteral(SqlBaseParser.DecimalLiteralContext ctx) {
   }

   public void enterLegacyDecimalLiteral(SqlBaseParser.LegacyDecimalLiteralContext ctx) {
   }

   public void exitLegacyDecimalLiteral(SqlBaseParser.LegacyDecimalLiteralContext ctx) {
   }

   public void enterIntegerLiteral(SqlBaseParser.IntegerLiteralContext ctx) {
   }

   public void exitIntegerLiteral(SqlBaseParser.IntegerLiteralContext ctx) {
   }

   public void enterBigIntLiteral(SqlBaseParser.BigIntLiteralContext ctx) {
   }

   public void exitBigIntLiteral(SqlBaseParser.BigIntLiteralContext ctx) {
   }

   public void enterSmallIntLiteral(SqlBaseParser.SmallIntLiteralContext ctx) {
   }

   public void exitSmallIntLiteral(SqlBaseParser.SmallIntLiteralContext ctx) {
   }

   public void enterTinyIntLiteral(SqlBaseParser.TinyIntLiteralContext ctx) {
   }

   public void exitTinyIntLiteral(SqlBaseParser.TinyIntLiteralContext ctx) {
   }

   public void enterDoubleLiteral(SqlBaseParser.DoubleLiteralContext ctx) {
   }

   public void exitDoubleLiteral(SqlBaseParser.DoubleLiteralContext ctx) {
   }

   public void enterFloatLiteral(SqlBaseParser.FloatLiteralContext ctx) {
   }

   public void exitFloatLiteral(SqlBaseParser.FloatLiteralContext ctx) {
   }

   public void enterBigDecimalLiteral(SqlBaseParser.BigDecimalLiteralContext ctx) {
   }

   public void exitBigDecimalLiteral(SqlBaseParser.BigDecimalLiteralContext ctx) {
   }

   public void enterAlterColumnSpecList(SqlBaseParser.AlterColumnSpecListContext ctx) {
   }

   public void exitAlterColumnSpecList(SqlBaseParser.AlterColumnSpecListContext ctx) {
   }

   public void enterAlterColumnSpec(SqlBaseParser.AlterColumnSpecContext ctx) {
   }

   public void exitAlterColumnSpec(SqlBaseParser.AlterColumnSpecContext ctx) {
   }

   public void enterAlterColumnAction(SqlBaseParser.AlterColumnActionContext ctx) {
   }

   public void exitAlterColumnAction(SqlBaseParser.AlterColumnActionContext ctx) {
   }

   public void enterStringLit(SqlBaseParser.StringLitContext ctx) {
   }

   public void exitStringLit(SqlBaseParser.StringLitContext ctx) {
   }

   public void enterComment(SqlBaseParser.CommentContext ctx) {
   }

   public void exitComment(SqlBaseParser.CommentContext ctx) {
   }

   public void enterVersion(SqlBaseParser.VersionContext ctx) {
   }

   public void exitVersion(SqlBaseParser.VersionContext ctx) {
   }

   public void enterOperatorPipeRightSide(SqlBaseParser.OperatorPipeRightSideContext ctx) {
   }

   public void exitOperatorPipeRightSide(SqlBaseParser.OperatorPipeRightSideContext ctx) {
   }

   public void enterOperatorPipeSetAssignmentSeq(SqlBaseParser.OperatorPipeSetAssignmentSeqContext ctx) {
   }

   public void exitOperatorPipeSetAssignmentSeq(SqlBaseParser.OperatorPipeSetAssignmentSeqContext ctx) {
   }

   public void enterAnsiNonReserved(SqlBaseParser.AnsiNonReservedContext ctx) {
   }

   public void exitAnsiNonReserved(SqlBaseParser.AnsiNonReservedContext ctx) {
   }

   public void enterStrictNonReserved(SqlBaseParser.StrictNonReservedContext ctx) {
   }

   public void exitStrictNonReserved(SqlBaseParser.StrictNonReservedContext ctx) {
   }

   public void enterNonReserved(SqlBaseParser.NonReservedContext ctx) {
   }

   public void exitNonReserved(SqlBaseParser.NonReservedContext ctx) {
   }

   public void enterEveryRule(ParserRuleContext ctx) {
   }

   public void exitEveryRule(ParserRuleContext ctx) {
   }

   public void visitTerminal(TerminalNode node) {
   }

   public void visitErrorNode(ErrorNode node) {
   }
}
