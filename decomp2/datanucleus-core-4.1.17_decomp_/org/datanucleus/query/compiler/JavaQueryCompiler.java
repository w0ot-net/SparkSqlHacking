package org.datanucleus.query.compiler;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.ExpressionCompiler;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.PrimaryExpressionIsClassLiteralException;
import org.datanucleus.query.expression.PrimaryExpressionIsClassStaticFieldException;
import org.datanucleus.query.expression.PrimaryExpressionIsVariableException;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolResolver;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.store.query.QueryCompilerSyntaxException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Imports;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class JavaQueryCompiler implements SymbolResolver {
   protected JavaQueryCompiler parentCompiler;
   protected Map parameterSubtitutionMap;
   protected int parameterSubstitutionNumber = 0;
   protected final MetaDataManager metaDataManager;
   protected final ClassLoaderResolver clr;
   protected boolean caseSensitiveAliases = true;
   protected Class candidateClass;
   protected String candidateAlias = "this";
   protected String candidateAliasOrig = null;
   protected String from;
   protected Collection candidates;
   protected String update;
   protected String filter;
   protected String ordering;
   protected String parameters;
   protected String variables;
   protected String grouping;
   protected String having;
   protected String result;
   protected Imports imports;
   protected SymbolTable symtbl;
   protected Parser parser;
   protected Map queryMethodAliasByPrefix = null;
   protected Map options;

   public JavaQueryCompiler(MetaDataManager metaDataManager, ClassLoaderResolver clr, String from, Class candidateClass, Collection candidates, String filter, Imports imports, String ordering, String result, String grouping, String having, String params, String variables, String update) {
      this.metaDataManager = metaDataManager;
      this.clr = clr;
      ConfigurationElement[] queryMethodAliases = metaDataManager.getNucleusContext().getPluginManager().getConfigurationElementsForExtension("org.datanucleus.query_method_prefix", (String)null, (String)null);
      if (queryMethodAliases != null && queryMethodAliases.length > 0) {
         this.queryMethodAliasByPrefix = new HashMap();

         for(int i = 0; i < queryMethodAliases.length; ++i) {
            this.queryMethodAliasByPrefix.put(queryMethodAliases[i].getAttribute("prefix"), queryMethodAliases[i].getAttribute("alias"));
         }
      }

      this.from = from;
      this.candidateClass = candidateClass;
      this.candidates = candidates;
      this.filter = filter;
      this.result = result;
      this.grouping = grouping;
      this.having = having;
      this.ordering = ordering;
      this.parameters = params;
      this.variables = variables;
      this.update = update;
      this.imports = imports;
      if (imports == null) {
         this.imports = new Imports();
         if (candidateClass != null) {
            this.imports.importClass(candidateClass.getName());
            this.imports.importPackage(candidateClass.getName());
         }
      }

   }

   public abstract String getLanguage();

   public void setOption(String name, Object value) {
      if (this.options == null) {
         this.options = new HashMap();
      }

      this.options.put(name, value);
   }

   public void setLinkToParentQuery(JavaQueryCompiler parentCompiler, Map paramSubstitutionMap) {
      this.parentCompiler = parentCompiler;
      this.parameterSubtitutionMap = paramSubstitutionMap;
   }

   public abstract QueryCompilation compile(Map var1, Map var2);

   public void compileCandidatesParametersVariables(Map parameters) {
      this.compileCandidates();
      this.compileVariables();
      this.compileParameters();
   }

   protected Expression[] compileFrom() {
      if (this.from == null) {
         return null;
      } else {
         Node[] node = this.parser.parseFrom(this.from);
         Expression[] expr = new Expression[node.length];

         for(int i = 0; i < node.length; ++i) {
            String className = (String)node[i].getNodeValue();
            String classAlias = null;
            Class cls = null;
            if (this.parentCompiler != null) {
               cls = this.getClassForSubqueryClassExpression(className);
            } else {
               cls = this.resolveClass(className);
            }

            List children = node[i].getChildNodes();

            for(int j = 0; j < children.size(); ++j) {
               Node child = (Node)children.get(j);
               if (child.getNodeType() == NodeType.NAME) {
                  classAlias = (String)child.getNodeValue();
               }
            }

            if (i == 0 && classAlias == null) {
               throw new QueryCompilerSyntaxException("FROM clause of query has class " + cls.getName() + " but no alias");
            }

            if (classAlias != null) {
               if (i == 0) {
                  this.candidateClass = cls;
                  if (this.parentCompiler != null && this.parentCompiler.candidateAlias.equals(classAlias)) {
                     this.candidateAliasOrig = classAlias;
                     this.candidateAlias = "sub_" + this.candidateAlias;
                     classAlias = this.candidateAlias;
                     this.swapCandidateAliasNodeName(node[i].getChildNode(0));
                  } else {
                     this.candidateAlias = classAlias;
                  }
               }

               if (this.symtbl.getSymbol(classAlias) == null) {
                  this.symtbl.addSymbol(new PropertySymbol(classAlias, cls));
               }
            }

            for(Node childNode : node[i].getChildNodes()) {
               if (childNode.getNodeType() == NodeType.OPERATOR) {
                  Node joinedNode = childNode.getFirstChild();
                  String joinedAlias = (String)joinedNode.getNodeValue();
                  Symbol joinedSym = this.caseSensitiveAliases ? this.symtbl.getSymbol(joinedAlias) : this.symtbl.getSymbolIgnoreCase(joinedAlias);
                  if (joinedSym == null) {
                     throw new QueryCompilerSyntaxException("FROM clause has identifier " + joinedNode.getNodeValue() + " but this is unknown");
                  }

                  AbstractClassMetaData joinedCmd = this.metaDataManager.getMetaDataForClass(joinedSym.getValueType(), this.clr);
                  Class joinedCls = joinedSym.getValueType();

                  while(joinedNode.getFirstChild() != null) {
                     joinedNode = joinedNode.getFirstChild();
                     String joinedMember = (String)joinedNode.getNodeValue();
                     if (joinedNode.getNodeType() == NodeType.CAST) {
                        String castTypeName = (String)joinedNode.getNodeValue();
                        if (castTypeName.indexOf(46) < 0) {
                           castTypeName = ClassUtils.createFullClassName(joinedCmd.getPackageName(), castTypeName);
                        }

                        joinedCls = this.clr.classForName(castTypeName);
                        joinedNode.setNodeValue(castTypeName);
                     } else {
                        String[] joinedMembers = joinedMember.contains(".") ? StringUtils.split(joinedMember, ".") : new String[]{joinedMember};

                        for(int k = 0; k < joinedMembers.length; ++k) {
                           AbstractMemberMetaData mmd = joinedCmd.getMetaDataForMember(joinedMembers[k]);
                           if (mmd == null) {
                              if (childNode.getNodeValue().equals("JOIN_OUTER") || childNode.getNodeValue().equals("JOIN_OUTER_FETCH")) {
                                 String[] subclasses = this.metaDataManager.getSubclassesForClass(joinedCmd.getFullClassName(), true);

                                 for(int l = 0; l < subclasses.length; ++l) {
                                    AbstractClassMetaData subCmd = this.metaDataManager.getMetaDataForClass(subclasses[l], this.clr);
                                    if (subCmd != null) {
                                       mmd = subCmd.getMetaDataForMember(joinedMembers[k]);
                                       if (mmd != null) {
                                          NucleusLogger.QUERY.debug("Polymorphic join found at " + joinedMembers[k] + " of " + subCmd.getFullClassName());
                                          joinedCmd = subCmd;
                                          break;
                                       }
                                    }
                                 }
                              }

                              if (mmd == null) {
                                 throw new QueryCompilerSyntaxException("FROM clause has reference to " + joinedCmd.getFullClassName() + "." + joinedMembers[k] + " but it doesn't exist!");
                              }
                           }

                           RelationType relationType = mmd.getRelationType(this.clr);
                           if (RelationType.isRelationSingleValued(relationType)) {
                              joinedCls = mmd.getType();
                              joinedCmd = this.metaDataManager.getMetaDataForClass(joinedCls, this.clr);
                           } else if (RelationType.isRelationMultiValued(relationType)) {
                              if (mmd.hasCollection()) {
                                 joinedCmd = mmd.getCollection().getElementClassMetaData(this.clr, this.metaDataManager);
                                 joinedCls = this.clr.classForName(joinedCmd.getFullClassName());
                              } else if (mmd.hasMap()) {
                                 joinedCmd = mmd.getMap().getValueClassMetaData(this.clr, this.metaDataManager);
                                 if (joinedCmd != null) {
                                    joinedCls = this.clr.classForName(joinedCmd.getFullClassName());
                                 }
                              } else if (mmd.hasArray()) {
                                 joinedCmd = mmd.getArray().getElementClassMetaData(this.clr, this.metaDataManager);
                                 joinedCls = this.clr.classForName(joinedCmd.getFullClassName());
                              }
                           }
                        }
                     }
                  }

                  Node aliasNode = childNode.getNextChild();
                  if (aliasNode.getNodeType() == NodeType.NAME) {
                     this.symtbl.addSymbol(new PropertySymbol((String)aliasNode.getNodeValue(), joinedCls));
                  }

                  Node nextNode = childNode.getNextChild();
                  if (nextNode != null) {
                     ExpressionCompiler comp = new ExpressionCompiler();
                     comp.setSymbolTable(this.symtbl);
                     comp.setMethodAliases(this.queryMethodAliasByPrefix);
                     Expression nextExpr = comp.compileExpression(nextNode);
                     nextExpr.bind(this.symtbl);
                  }
               }
            }

            boolean classIsExpression = false;
            String[] tokens = StringUtils.split(className, ".");
            if (this.symtbl.getParentSymbolTable() != null && this.symtbl.getParentSymbolTable().hasSymbol(tokens[0])) {
               classIsExpression = true;
            }

            ExpressionCompiler comp = new ExpressionCompiler();
            comp.setSymbolTable(this.symtbl);
            comp.setMethodAliases(this.queryMethodAliasByPrefix);
            expr[i] = comp.compileFromExpression(node[i], classIsExpression);
            if (expr[i] != null) {
               expr[i].bind(this.symtbl);
            }
         }

         return expr;
      }
   }

   private Class getClassForSubqueryClassExpression(String classExpr) {
      if (classExpr == null) {
         return null;
      } else {
         String[] tokens = StringUtils.split(classExpr, ".");
         Class cls = null;
         if (tokens[0].equalsIgnoreCase(this.parentCompiler.candidateAlias)) {
            cls = this.parentCompiler.candidateClass;
         } else {
            Symbol sym = this.parentCompiler.symtbl.getSymbolIgnoreCase(tokens[0]);
            if (sym == null) {
               return this.resolveClass(classExpr);
            }

            cls = sym.getValueType();
         }

         AbstractClassMetaData cmd = this.metaDataManager.getMetaDataForClass(cls, this.clr);

         for(int i = 1; i < tokens.length; ++i) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForMember(tokens[i]);
            RelationType relationType = mmd.getRelationType(this.clr);
            if (relationType != RelationType.ONE_TO_ONE_BI && relationType != RelationType.ONE_TO_ONE_UNI && relationType != RelationType.MANY_TO_ONE_BI) {
               if (relationType == RelationType.ONE_TO_MANY_UNI || relationType == RelationType.ONE_TO_MANY_BI || relationType == RelationType.MANY_TO_MANY_BI) {
                  if (mmd.hasCollection()) {
                     cls = this.clr.classForName(mmd.getCollection().getElementType());
                  } else if (mmd.hasMap()) {
                     cls = this.clr.classForName(mmd.getMap().getValueType());
                  } else if (mmd.hasArray()) {
                     cls = this.clr.classForName(mmd.getArray().getElementType());
                  }
               }
            } else {
               cls = mmd.getType();
            }

            if (i < tokens.length - 1) {
               cmd = this.metaDataManager.getMetaDataForClass(cls, this.clr);
            }
         }

         return cls;
      }
   }

   private void compileCandidates() {
      if (this.symtbl.getSymbol(this.candidateAlias) == null) {
         if (this.parentCompiler != null && this.parentCompiler.candidateAlias.equals(this.candidateAlias)) {
            this.candidateAliasOrig = this.candidateAlias;
            this.candidateAlias = "sub_" + this.candidateAlias;
         }

         PropertySymbol symbol = new PropertySymbol(this.candidateAlias, this.candidateClass);
         this.symtbl.addSymbol(symbol);
      }

   }

   public Expression[] compileUpdate() {
      if (this.update == null) {
         return null;
      } else {
         Node[] node = this.parser.parseTuple(this.update);
         Expression[] expr = new Expression[node.length];

         for(int i = 0; i < node.length; ++i) {
            ExpressionCompiler comp = new ExpressionCompiler();
            comp.setSymbolTable(this.symtbl);
            comp.setMethodAliases(this.queryMethodAliasByPrefix);
            expr[i] = comp.compileExpression(node[i]);
            expr[i].bind(this.symtbl);
         }

         return expr;
      }
   }

   public Expression compileFilter() {
      if (this.filter != null) {
         Node node = this.parser.parse(this.filter);
         if (this.candidateAliasOrig != null) {
            this.swapCandidateAliasNodeName(node);
         }

         if (this.parameterSubtitutionMap != null) {
            node = this.swapSubqueryParameters(node);
         }

         ExpressionCompiler comp = new ExpressionCompiler();
         comp.setSymbolTable(this.symtbl);
         comp.setMethodAliases(this.queryMethodAliasByPrefix);
         Expression expr = comp.compileExpression(node);
         expr.bind(this.symtbl);
         return expr;
      } else {
         return null;
      }
   }

   protected void swapCandidateAliasNodeName(Node node) {
      if (node != null) {
         switch (node.getNodeType()) {
            case IDENTIFIER:
               if (node.getNodeValue().equals(this.candidateAliasOrig)) {
                  node.setNodeValue(this.candidateAlias);
               }
               break;
            case OPERATOR:
               while(node.hasNextChild()) {
                  Node childNode = node.getNextChild();
                  this.swapCandidateAliasNodeName(childNode);
               }
               break;
            case INVOKE:
               if (node.hasProperties()) {
                  for(Node propNode : node.getProperties()) {
                     this.swapCandidateAliasNodeName(propNode);
                  }
               }
               break;
            case CAST:
               Node childNode = node.getChildNode(0);
               this.swapCandidateAliasNodeName(childNode);
               break;
            case NAME:
               if (node.getNodeValue().equals(this.candidateAliasOrig)) {
                  node.setNodeValue(this.candidateAlias);
               }
            case CLASS:
            case CASE:
            case PARAMETER:
            case SUBQUERY:
            case LITERAL:
         }

      }
   }

   protected Node swapSubqueryParameters(Node node) {
      if (node != null && this.parameterSubtitutionMap != null) {
         Node swapNode = null;
         switch (node.getNodeType()) {
            case OPERATOR:
               List childNodes = node.getChildNodes();

               for(int i = 0; i < childNodes.size(); ++i) {
                  Node swappedNode = this.swapSubqueryParameters((Node)childNodes.get(i));
                  node.removeChildNode((Node)childNodes.get(i));
                  node.insertChildNode(swappedNode, i);
               }
               break;
            case INVOKE:
               if (node.hasProperties()) {
                  List<Node> propNodes = node.getProperties();

                  for(int i = 0; i < propNodes.size(); ++i) {
                     Node propNode = (Node)propNodes.get(i);
                     swapNode = this.swapSubqueryParameters(propNode);
                     if (swapNode != propNode) {
                        node.setPropertyAtPosition(i, swapNode);
                     }
                  }
               }
               break;
            case PARAMETER:
               Object paramName = node.getNodeValue();
               if (this.parameterSubtitutionMap.containsKey(paramName)) {
                  String paramValue = (String)this.parameterSubtitutionMap.get(paramName);
                  swapNode = this.parser.parse(paramValue);
               } else {
                  String paramValue = (String)this.parameterSubtitutionMap.get(this.parameterSubstitutionNumber++);
                  swapNode = this.parser.parse(paramValue);
               }

               return swapNode;
         }

         return node;
      } else {
         return null;
      }
   }

   public Expression[] compileResult() {
      if (this.result == null) {
         return null;
      } else {
         Node[] node = this.parser.parseResult(this.result);
         Expression[] expr = new Expression[node.length];

         for(int i = 0; i < node.length; ++i) {
            ExpressionCompiler comp = new ExpressionCompiler();
            comp.setSymbolTable(this.symtbl);
            comp.setMethodAliases(this.queryMethodAliasByPrefix);
            String alias = null;
            Node aliasNode = null;

            while(node[i].hasNextChild()) {
               Node childNode = node[i].getNextChild();
               if (childNode.getNodeType() == NodeType.NAME) {
                  aliasNode = childNode;
               }
            }

            if (aliasNode != null) {
               alias = (String)aliasNode.getNodeValue();
               node[i].removeChildNode(aliasNode);
            }

            if (this.candidateAliasOrig != null) {
               this.swapCandidateAliasNodeName(node[i]);
            }

            if (this.parameterSubtitutionMap != null) {
               node[i] = this.swapSubqueryParameters(node[i]);
            }

            expr[i] = comp.compileExpression(node[i]);
            if (alias != null) {
               expr[i].setAlias(alias);
            }

            try {
               expr[i].bind(this.symtbl);
            } catch (PrimaryExpressionIsClassLiteralException peil) {
               expr[i] = peil.getLiteral();
               expr[i].bind(this.symtbl);
            } catch (PrimaryExpressionIsClassStaticFieldException peil) {
               Field fld = peil.getLiteralField();

               try {
                  Object value = fld.get((Object)null);
                  expr[i] = new Literal(value);
                  expr[i].bind(this.symtbl);
               } catch (Exception e) {
                  throw new NucleusUserException("Error processing static field " + fld.getName(), e);
               }
            } catch (PrimaryExpressionIsVariableException pive) {
               expr[i] = pive.getVariableExpression();
               expr[i].bind(this.symtbl);
            }

            if (expr[i] instanceof PrimaryExpression) {
               String id = ((PrimaryExpression)expr[i]).getId();
               if (this.isKeyword(id)) {
                  throw new NucleusUserException(Localiser.msg("021052", this.getLanguage(), id));
               }
            } else if (expr[i] instanceof ParameterExpression) {
               String id = ((ParameterExpression)expr[i]).getId();
               if (this.isKeyword(id)) {
                  throw new NucleusUserException(Localiser.msg("021052", this.getLanguage(), id));
               }
            } else if (expr[i] instanceof VariableExpression) {
               String id = ((VariableExpression)expr[i]).getId();
               if (this.isKeyword(id)) {
                  throw new NucleusUserException(Localiser.msg("021052", this.getLanguage(), id));
               }
            }
         }

         return expr;
      }
   }

   public Expression[] compileGrouping() {
      if (this.grouping == null) {
         return null;
      } else {
         Node[] node = this.parser.parseTuple(this.grouping);
         Expression[] expr = new Expression[node.length];

         for(int i = 0; i < node.length; ++i) {
            if (this.candidateAliasOrig != null) {
               this.swapCandidateAliasNodeName(node[i]);
            }

            if (this.parameterSubtitutionMap != null) {
               node[i] = this.swapSubqueryParameters(node[i]);
            }

            ExpressionCompiler comp = new ExpressionCompiler();
            comp.setSymbolTable(this.symtbl);
            comp.setMethodAliases(this.queryMethodAliasByPrefix);
            expr[i] = comp.compileExpression(node[i]);
            expr[i].bind(this.symtbl);
         }

         return expr;
      }
   }

   public Expression compileHaving() {
      if (this.having == null) {
         return null;
      } else {
         Node node = this.parser.parse(this.having);
         if (this.candidateAliasOrig != null) {
            this.swapCandidateAliasNodeName(node);
         }

         if (this.parameterSubtitutionMap != null) {
            node = this.swapSubqueryParameters(node);
         }

         ExpressionCompiler comp = new ExpressionCompiler();
         comp.setSymbolTable(this.symtbl);
         comp.setMethodAliases(this.queryMethodAliasByPrefix);
         Expression expr = comp.compileExpression(node);
         expr.bind(this.symtbl);
         return expr;
      }
   }

   private void compileVariables() {
      if (this.variables != null) {
         Node[][] node = this.parser.parseVariables(this.variables);

         for(int i = 0; i < node.length; ++i) {
            String varName = (String)node[i][1].getNodeValue();
            if (this.isKeyword(varName) || varName.equals(this.candidateAlias)) {
               throw new NucleusUserException(Localiser.msg("021052", this.getLanguage(), varName));
            }

            Symbol varSym = this.symtbl.getSymbol(varName);
            Class nodeCls = this.resolveClass(node[i][0].getNodeChildId());
            if (varSym != null) {
               if (nodeCls != null) {
                  varSym.setValueType(nodeCls);
               }
            } else {
               PropertySymbol sym = new PropertySymbol(varName, nodeCls);
               sym.setType(2);
               this.symtbl.addSymbol(sym);
            }
         }

      }
   }

   private void compileParameters() {
      if (this.parameters != null) {
         Node[][] node = this.parser.parseParameters(this.parameters);

         for(int i = 0; i < node.length; ++i) {
            String paramName = (String)node[i][1].getNodeValue();
            if (this.isKeyword(paramName) || paramName.equals(this.candidateAlias)) {
               throw new NucleusUserException(Localiser.msg("021052", this.getLanguage(), paramName));
            }

            Symbol paramSym = this.symtbl.getSymbol(paramName);
            Class nodeCls = this.resolveClass(node[i][0].getNodeChildId());
            if (paramSym == null) {
               PropertySymbol sym = new PropertySymbol(paramName, nodeCls);
               sym.setType(1);
               this.symtbl.addSymbol(sym);
            }
         }

      }
   }

   public Expression[] compileOrdering() {
      if (this.ordering == null) {
         return null;
      } else {
         Node[] node = this.parser.parseOrder(this.ordering);
         Expression[] expr = new Expression[node.length];

         for(int i = 0; i < node.length; ++i) {
            if (this.candidateAliasOrig != null) {
               this.swapCandidateAliasNodeName(node[i]);
            }

            if (this.parameterSubtitutionMap != null) {
               node[i] = this.swapSubqueryParameters(node[i]);
            }

            ExpressionCompiler comp = new ExpressionCompiler();
            comp.setSymbolTable(this.symtbl);
            comp.setMethodAliases(this.queryMethodAliasByPrefix);
            expr[i] = comp.compileOrderExpression(node[i]);
            expr[i].bind(this.symtbl);
         }

         return expr;
      }
   }

   public Class getPrimaryClass() {
      return this.candidateClass;
   }

   public Class resolveClass(String className) {
      if (this.imports != null) {
         try {
            Class cls = this.imports.resolveClassDeclaration(className, this.clr, (ClassLoader)null);
            if (cls != null) {
               return cls;
            }
         } catch (NucleusException var4) {
         }
      }

      AbstractClassMetaData acmd = this.metaDataManager.getMetaDataForEntityName(className);
      if (acmd != null) {
         String fullClassName = acmd.getFullClassName();
         if (fullClassName != null) {
            return this.clr.classForName(fullClassName);
         }
      }

      throw new ClassNotResolvedException("Class " + className + " for query has not been resolved. Check the query and any imports/aliases specification");
   }

   public Class getType(List tuples) {
      Class type = null;
      Symbol symbol = null;
      String firstTuple = (String)tuples.get(0);
      if (this.caseSensitiveSymbolNames()) {
         symbol = this.symtbl.getSymbol(firstTuple);
      } else {
         symbol = this.symtbl.getSymbol(firstTuple);
         if (symbol == null) {
            symbol = this.symtbl.getSymbol(firstTuple.toUpperCase());
         }

         if (symbol == null) {
            symbol = this.symtbl.getSymbol(firstTuple.toLowerCase());
         }
      }

      if (symbol != null) {
         type = symbol.getValueType();
         if (type == null) {
            throw new NucleusUserException("Cannot find type of " + tuples.get(0) + " since symbol has no type; implicit variable?");
         }

         for(int i = 1; i < tuples.size(); ++i) {
            type = this.getType(type, (String)tuples.get(i));
         }
      } else {
         symbol = this.symtbl.getSymbol(this.candidateAlias);
         type = symbol.getValueType();

         for(int i = 0; i < tuples.size(); ++i) {
            type = this.getType(type, (String)tuples.get(i));
         }
      }

      return type;
   }

   Class getType(Class cls, String fieldName) {
      AbstractClassMetaData acmd = this.metaDataManager.getMetaDataForClass(cls, this.clr);
      if (acmd != null) {
         AbstractMemberMetaData fmd = acmd.getMetaDataForMember(fieldName);
         if (fmd == null) {
            throw new NucleusUserException("Cannot access field " + fieldName + " on type " + cls.getName());
         } else {
            return fmd.getType();
         }
      } else {
         Field field = ClassUtils.getFieldForClass(cls, fieldName);
         if (field == null) {
            throw new NucleusUserException("Cannot access field " + fieldName + " on type " + cls.getName());
         } else {
            return field.getType();
         }
      }
   }

   protected abstract boolean isKeyword(String var1);
}
