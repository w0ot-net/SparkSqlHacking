package com.ibm.icu.message2;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.CurrencyAmount;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class MFDataModelFormatter {
   private final Locale locale;
   private final MessageFormatter.ErrorHandlingBehavior errorHandlingBehavior;
   private final MFDataModel.Message dm;
   private final MFFunctionRegistry standardFunctions;
   private final MFFunctionRegistry customFunctions;
   private static final MFFunctionRegistry EMPTY_REGISTY = MFFunctionRegistry.builder().build();

   MFDataModelFormatter(MFDataModel.Message dm, Locale locale, MessageFormatter.ErrorHandlingBehavior errorHandlingBehavior, MFFunctionRegistry customFunctionRegistry) {
      this.locale = locale;
      this.errorHandlingBehavior = errorHandlingBehavior == null ? MessageFormatter.ErrorHandlingBehavior.BEST_EFFORT : errorHandlingBehavior;
      this.dm = dm;
      this.customFunctions = customFunctionRegistry == null ? EMPTY_REGISTY : customFunctionRegistry;
      this.standardFunctions = MFFunctionRegistry.builder().setFormatter("datetime", new DateTimeFormatterFactory("datetime")).setFormatter("date", new DateTimeFormatterFactory("date")).setFormatter("time", new DateTimeFormatterFactory("time")).setDefaultFormatterNameForType(Date.class, "datetime").setDefaultFormatterNameForType(Calendar.class, "datetime").setDefaultFormatterNameForType(java.util.Calendar.class, "datetime").setDefaultFormatterNameForType(Temporal.class, "datetime").setFormatter("number", new NumberFormatterFactory("number")).setFormatter("integer", new NumberFormatterFactory("integer")).setDefaultFormatterNameForType(Integer.class, "number").setDefaultFormatterNameForType(Double.class, "number").setDefaultFormatterNameForType(Number.class, "number").setDefaultFormatterNameForType(CurrencyAmount.class, "number").setFormatter("string", new IdentityFormatterFactory()).setDefaultFormatterNameForType(String.class, "string").setDefaultFormatterNameForType(CharSequence.class, "string").setSelector("number", new NumberFormatterFactory("number")).setSelector("integer", new NumberFormatterFactory("integer")).setSelector("string", new TextSelectorFactory()).setSelector("icu:gender", new TextSelectorFactory()).build();
   }

   String format(Map arguments) {
      MFDataModel.Pattern patternToRender = null;
      if (arguments == null) {
         arguments = new HashMap();
      }

      Map<String, Object> variables;
      if (this.dm instanceof MFDataModel.PatternMessage) {
         MFDataModel.PatternMessage pm = (MFDataModel.PatternMessage)this.dm;
         variables = this.resolveDeclarations(pm.declarations, arguments);
         if (pm.pattern == null) {
            fatalFormattingError("The PatternMessage is null.");
         }

         patternToRender = pm.pattern;
      } else {
         if (!(this.dm instanceof MFDataModel.SelectMessage)) {
            fatalFormattingError("Unknown message type.");
            return "ERROR!";
         }

         MFDataModel.SelectMessage sm = (MFDataModel.SelectMessage)this.dm;
         variables = this.resolveDeclarations(sm.declarations, arguments);
         patternToRender = this.findBestMatchingPattern(sm, variables, arguments);
         if (patternToRender == null) {
            fatalFormattingError("Cannor find a match for the selector.");
         }
      }

      StringBuilder result = new StringBuilder();

      for(MFDataModel.PatternPart part : patternToRender.parts) {
         if (part instanceof MFDataModel.StringPart) {
            MFDataModel.StringPart strPart = (MFDataModel.StringPart)part;
            result.append(strPart.value);
         } else if (part instanceof MFDataModel.Expression) {
            FormattedPlaceholder formattedExpression = this.formatExpression((MFDataModel.Expression)part, variables, arguments);
            result.append(formattedExpression.getFormattedValue().toString());
         } else if (!(part instanceof MFDataModel.Markup)) {
            fatalFormattingError("Unknown part type: " + part);
         }
      }

      return result.toString();
   }

   private MFDataModel.Pattern findBestMatchingPattern(MFDataModel.SelectMessage sm, Map variables, Map arguments) {
      MFDataModel.Pattern patternToRender = null;
      List<MFDataModel.Expression> selectors = sm.selectors;
      List<ResolvedSelector> res = new ArrayList(selectors.size());

      for(MFDataModel.Expression sel : selectors) {
         FormattedPlaceholder fph = this.formatExpression(sel, variables, arguments);
         String functionName = null;
         Object argument = null;
         Map<String, Object> options = new HashMap();
         if (fph.getInput() instanceof ResolvedExpression) {
            ResolvedExpression re = (ResolvedExpression)fph.getInput();
            argument = re.argument;
            functionName = re.functionName;
            options.putAll(re.options);
         } else if (fph.getInput() instanceof MFDataModel.VariableExpression) {
            MFDataModel.VariableExpression ve = (MFDataModel.VariableExpression)fph.getInput();
            argument = resolveLiteralOrVariable(ve.arg, variables, arguments);
            if (ve.annotation instanceof MFDataModel.FunctionAnnotation) {
               functionName = ((MFDataModel.FunctionAnnotation)ve.annotation).name;
            }
         } else if (fph.getInput() instanceof MFDataModel.LiteralExpression) {
            MFDataModel.LiteralExpression le = (MFDataModel.LiteralExpression)fph.getInput();
            argument = le.arg;
            if (le.annotation instanceof MFDataModel.FunctionAnnotation) {
               functionName = ((MFDataModel.FunctionAnnotation)le.annotation).name;
            }
         }

         SelectorFactory funcFactory = this.standardFunctions.getSelector(functionName);
         if (funcFactory == null) {
            funcFactory = this.customFunctions.getSelector(functionName);
         }

         if (funcFactory != null) {
            Selector selectorFunction = funcFactory.createSelector(this.locale, options);
            ResolvedSelector rs = new ResolvedSelector(argument, options, selectorFunction);
            res.add(rs);
         } else {
            fatalFormattingError("Unknown selector type: " + functionName);
         }
      }

      if (res.size() != selectors.size()) {
         fatalFormattingError("Something went wrong, not enough selector functions, " + res.size() + " vs. " + selectors.size());
      }

      List<List<String>> pref = new ArrayList();

      for(int i = 0; i < res.size(); ++i) {
         List<String> keys = new ArrayList();

         for(MFDataModel.Variant var : sm.variants) {
            MFDataModel.LiteralOrCatchallKey key = (MFDataModel.LiteralOrCatchallKey)var.keys.get(i);
            if (key instanceof MFDataModel.CatchallKey) {
               keys.add("*");
            } else if (key instanceof MFDataModel.Literal) {
               String ks = ((MFDataModel.Literal)key).value;
               keys.add(ks);
            } else {
               fatalFormattingError("Literal expected, but got " + key);
            }
         }

         ResolvedSelector rv = (ResolvedSelector)res.get(i);
         List<String> matches = this.matchSelectorKeys(rv, keys);
         pref.add(matches);
      }

      List<MFDataModel.Variant> vars = new ArrayList();

      for(MFDataModel.Variant var : sm.variants) {
         int found = 0;

         for(int i = 0; i < pref.size(); ++i) {
            MFDataModel.LiteralOrCatchallKey key = (MFDataModel.LiteralOrCatchallKey)var.keys.get(i);
            if (key instanceof MFDataModel.CatchallKey) {
               ++found;
            } else {
               if (!(key instanceof MFDataModel.Literal)) {
                  fatalFormattingError("Literal expected");
               }

               String ks = ((MFDataModel.Literal)key).value;
               List<String> matches = (List)pref.get(i);
               if (!matches.contains(ks)) {
                  break;
               }

               ++found;
            }
         }

         if (found == pref.size()) {
            vars.add(var);
         }
      }

      List<IntVarTuple> sortable = new ArrayList();

      for(MFDataModel.Variant var : vars) {
         IntVarTuple tuple = new IntVarTuple(-1, var);
         sortable.add(tuple);
      }

      int len = pref.size();

      for(int i = len - 1; i >= 0; --i) {
         List<String> matches = (List)pref.get(i);
         int minpref = matches.size();

         for(IntVarTuple tuple : sortable) {
            int matchpref = minpref;
            MFDataModel.LiteralOrCatchallKey key = (MFDataModel.LiteralOrCatchallKey)tuple.variant.keys.get(i);
            if (!(key instanceof MFDataModel.CatchallKey)) {
               if (!(key instanceof MFDataModel.Literal)) {
                  fatalFormattingError("Literal expected");
               }

               String ks = ((MFDataModel.Literal)key).value;
               matchpref = matches.indexOf(ks);
            }

            tuple.integer = matchpref;
         }

         sortable.sort(MFDataModelFormatter::sortVariants);
      }

      IntVarTuple var = (IntVarTuple)sortable.get(0);
      patternToRender = var.variant.value;
      if (patternToRender == null) {
         fatalFormattingError("The selection went wrong, cannot select any option.");
      }

      return patternToRender;
   }

   private static int sortVariants(IntVarTuple o1, IntVarTuple o2) {
      int result = Integer.compare(o1.integer, o2.integer);
      if (result != 0) {
         return result;
      } else {
         List<MFDataModel.LiteralOrCatchallKey> v1 = o1.variant.keys;
         List<MFDataModel.LiteralOrCatchallKey> v2 = o1.variant.keys;
         if (v1.size() != v2.size()) {
            fatalFormattingError("The number of keys is not equal.");
         }

         for(int i = 0; i < v1.size(); ++i) {
            MFDataModel.LiteralOrCatchallKey k1 = (MFDataModel.LiteralOrCatchallKey)v1.get(i);
            MFDataModel.LiteralOrCatchallKey k2 = (MFDataModel.LiteralOrCatchallKey)v2.get(i);
            String s1 = k1 instanceof MFDataModel.Literal ? ((MFDataModel.Literal)k1).value : "*";
            String s2 = k2 instanceof MFDataModel.Literal ? ((MFDataModel.Literal)k2).value : "*";
            int cmp = s1.compareTo(s2);
            if (cmp != 0) {
               return cmp;
            }
         }

         return 0;
      }
   }

   private List matchSelectorKeys(ResolvedSelector rv, List keys) {
      return rv.selectorFunction.matches(rv.argument, keys, rv.options);
   }

   private static void fatalFormattingError(String message) throws IllegalArgumentException {
      throw new IllegalArgumentException(message);
   }

   private FormatterFactory getFormattingFunctionFactoryByName(Object toFormat, String functionName) {
      if (functionName == null || functionName.isEmpty()) {
         if (toFormat == null) {
            return null;
         }

         Class<?> clazz = toFormat.getClass();
         functionName = this.standardFunctions.getDefaultFormatterNameForType(clazz);
         if (functionName == null) {
            functionName = this.customFunctions.getDefaultFormatterNameForType(clazz);
         }

         if (functionName == null) {
            fatalFormattingError("Object to format without a function, and unknown type: " + toFormat.getClass().getName());
         }
      }

      FormatterFactory func = this.standardFunctions.getFormatter(functionName);
      if (func == null) {
         func = this.customFunctions.getFormatter(functionName);
      }

      return func;
   }

   private static Object resolveLiteralOrVariable(MFDataModel.LiteralOrVariableRef value, Map localVars, Map arguments) {
      if (value instanceof MFDataModel.Literal) {
         String val = ((MFDataModel.Literal)value).value;
         return val;
      } else if (value instanceof MFDataModel.VariableRef) {
         String varName = ((MFDataModel.VariableRef)value).name;
         Object val = localVars.get(varName);
         if (val == null) {
            val = localVars.get(varName);
         }

         if (val == null) {
            val = arguments.get(varName);
         }

         return val;
      } else {
         return value;
      }
   }

   private static Map convertOptions(Map options, Map localVars, Map arguments) {
      Map<String, Object> result = new HashMap();

      for(MFDataModel.Option option : options.values()) {
         result.put(option.name, resolveLiteralOrVariable(option.value, localVars, arguments));
      }

      return result;
   }

   private FormattedPlaceholder formatExpression(MFDataModel.Expression expression, Map variables, Map arguments) {
      MFDataModel.Annotation annotation = null;
      String functionName = null;
      Object toFormat = null;
      Map<String, Object> options = new HashMap();
      String fallbackString = "{�}";
      if (expression instanceof MFDataModel.VariableExpression) {
         MFDataModel.VariableExpression varPart = (MFDataModel.VariableExpression)expression;
         fallbackString = "{$" + varPart.arg.name + "}";
         annotation = varPart.annotation;
         Object resolved = resolveLiteralOrVariable(varPart.arg, variables, arguments);
         if (resolved instanceof FormattedPlaceholder) {
            Object input = ((FormattedPlaceholder)resolved).getInput();
            if (input instanceof ResolvedExpression) {
               ResolvedExpression re = (ResolvedExpression)input;
               toFormat = re.argument;
               functionName = re.functionName;
               options.putAll(re.options);
            } else {
               toFormat = input;
            }
         } else {
            toFormat = resolved;
         }
      } else if (expression instanceof MFDataModel.FunctionExpression) {
         MFDataModel.FunctionExpression fe = (MFDataModel.FunctionExpression)expression;
         fallbackString = "{:" + fe.annotation.name + "}";
         annotation = fe.annotation;
      } else if (expression instanceof MFDataModel.LiteralExpression) {
         MFDataModel.LiteralExpression le = (MFDataModel.LiteralExpression)expression;
         annotation = le.annotation;
         fallbackString = "{|" + le.arg.value + "|}";
         toFormat = resolveLiteralOrVariable(le.arg, variables, arguments);
      } else {
         if (expression instanceof MFDataModel.Markup) {
            return new FormattedPlaceholder(expression, new PlainStringFormattedValue(""));
         }

         if (expression == null) {
            fatalFormattingError("unexpected null expression");
         } else {
            fatalFormattingError("unknown expression type " + expression.getClass().getName());
         }
      }

      if (annotation instanceof MFDataModel.FunctionAnnotation) {
         MFDataModel.FunctionAnnotation fa = (MFDataModel.FunctionAnnotation)annotation;
         if (functionName != null && !functionName.equals(fa.name)) {
            fatalFormattingError("invalid function overrides, '" + functionName + "' <> '" + fa.name + "'");
         }

         functionName = fa.name;
         Map<String, Object> newOptions = convertOptions(fa.options, variables, arguments);
         options.putAll(newOptions);
      }

      FormatterFactory funcFactory = this.getFormattingFunctionFactoryByName(toFormat, functionName);
      if (funcFactory == null) {
         if (this.errorHandlingBehavior == MessageFormatter.ErrorHandlingBehavior.STRICT) {
            fatalFormattingError("unable to find function at " + fallbackString);
         }

         return new FormattedPlaceholder(expression, new PlainStringFormattedValue(fallbackString));
      } else {
         Formatter ff = funcFactory.createFormatter(this.locale, options);
         String res = ff.formatToString(toFormat, arguments);
         if (res == null) {
            if (this.errorHandlingBehavior == MessageFormatter.ErrorHandlingBehavior.STRICT) {
               fatalFormattingError("unable to format string at " + fallbackString);
            }

            res = fallbackString;
         }

         ResolvedExpression resExpression = new ResolvedExpression(toFormat, functionName, options);
         return new FormattedPlaceholder(resExpression, new PlainStringFormattedValue(res));
      }
   }

   private Map resolveDeclarations(List declarations, Map arguments) {
      Map<String, Object> variables = new HashMap();
      if (declarations != null) {
         for(MFDataModel.Declaration declaration : declarations) {
            String name;
            MFDataModel.Expression value;
            if (declaration instanceof MFDataModel.InputDeclaration) {
               name = ((MFDataModel.InputDeclaration)declaration).name;
               value = ((MFDataModel.InputDeclaration)declaration).value;
            } else {
               if (!(declaration instanceof MFDataModel.LocalDeclaration)) {
                  continue;
               }

               name = ((MFDataModel.LocalDeclaration)declaration).name;
               value = ((MFDataModel.LocalDeclaration)declaration).value;
            }

            try {
               FormattedPlaceholder fmt = this.formatExpression(value, variables, arguments);
               variables.put(name, fmt);
            } catch (Exception var9) {
            }
         }
      }

      return variables;
   }

   private static class ResolvedSelector {
      final Object argument;
      final Map options;
      final Selector selectorFunction;

      public ResolvedSelector(Object argument, Map options, Selector selectorFunction) {
         this.argument = argument;
         this.options = new HashMap(options);
         this.selectorFunction = selectorFunction;
      }
   }

   static class ResolvedExpression implements MFDataModel.Expression {
      final Object argument;
      final String functionName;
      final Map options;

      public ResolvedExpression(Object argument, String functionName, Map options) {
         this.argument = argument;
         this.functionName = functionName;
         this.options = options;
      }
   }

   private static class IntVarTuple {
      int integer;
      final MFDataModel.Variant variant;

      public IntVarTuple(int integer, MFDataModel.Variant variant) {
         this.integer = integer;
         this.variant = variant;
      }
   }
}
