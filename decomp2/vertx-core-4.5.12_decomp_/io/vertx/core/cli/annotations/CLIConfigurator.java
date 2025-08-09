package io.vertx.core.cli.annotations;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.TypedArgument;
import io.vertx.core.cli.TypedOption;
import io.vertx.core.cli.converters.Converter;
import io.vertx.core.cli.impl.DefaultCLI;
import io.vertx.core.cli.impl.ReflectionUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CLIConfigurator {
   public static CLI define(Class clazz) {
      CLI cli = new DefaultCLI();
      Summary summary = (Summary)clazz.getAnnotation(Summary.class);
      Description desc = (Description)clazz.getAnnotation(Description.class);
      Hidden hidden = (Hidden)clazz.getAnnotation(Hidden.class);
      Name name = (Name)clazz.getAnnotation(Name.class);
      if (name == null) {
         throw new IllegalArgumentException("The command cannot be defined, the @Name annotation is missing.");
      } else if (name.value().isEmpty()) {
         throw new IllegalArgumentException("The command cannot be defined, the @Name value is empty or null.");
      } else {
         cli.setName(name.value());
         cli.setPriority(name.priority());
         if (summary != null) {
            cli.setSummary(summary.value());
         }

         if (desc != null) {
            cli.setDescription(desc.value());
         }

         if (hidden != null) {
            cli.setHidden(true);
         }

         for(Method method : ReflectionUtils.getSetterMethods(clazz)) {
            Option option = (Option)method.getAnnotation(Option.class);
            Argument argument = (Argument)method.getAnnotation(Argument.class);
            if (option != null) {
               cli.addOption(createOption(method));
            }

            if (argument != null) {
               cli.addArgument(createArgument(method));
            }
         }

         return cli;
      }
   }

   private static io.vertx.core.cli.Option createOption(Method method) {
      TypedOption opt = new TypedOption();
      Option option = (Option)method.getAnnotation(Option.class);
      opt.setLongName(option.longName()).setShortName(option.shortName()).setMultiValued(option.acceptMultipleValues()).setSingleValued(option.acceptValue()).setArgName(option.argName()).setFlag(option.flag()).setHelp(option.help()).setRequired(option.required());
      Description description = (Description)method.getAnnotation(Description.class);
      if (description != null) {
         opt.setDescription(description.value());
      }

      Hidden hidden = (Hidden)method.getAnnotation(Hidden.class);
      if (hidden != null) {
         opt.setHidden(true);
      }

      if (ReflectionUtils.isMultiple(method)) {
         opt.setType(ReflectionUtils.getComponentType(method.getParameters()[0])).setMultiValued(true);
      } else {
         Class<?> type = method.getParameters()[0].getType();
         opt.setType(type);
         if (type != Boolean.TYPE && type != Boolean.class) {
            opt.setSingleValued(true);
         }
      }

      ConvertedBy convertedBy = (ConvertedBy)method.getAnnotation(ConvertedBy.class);
      if (convertedBy != null) {
         opt.setConverter((Converter)ReflectionUtils.newInstance(convertedBy.value()));
      }

      ParsedAsList parsedAsList = (ParsedAsList)method.getAnnotation(ParsedAsList.class);
      if (parsedAsList != null) {
         opt.setParsedAsList(true).setListSeparator(parsedAsList.separator());
      }

      DefaultValue defaultValue = (DefaultValue)method.getAnnotation(DefaultValue.class);
      if (defaultValue != null) {
         opt.setDefaultValue(defaultValue.value());
      }

      opt.ensureValidity();
      return opt;
   }

   private static io.vertx.core.cli.Argument createArgument(Method method) {
      TypedArgument arg = new TypedArgument();
      Argument argument = (Argument)method.getAnnotation(Argument.class);
      arg.setIndex(argument.index());
      arg.setArgName(argument.argName());
      arg.setRequired(argument.required());
      Description description = (Description)method.getAnnotation(Description.class);
      if (description != null) {
         arg.setDescription(description.value());
      }

      if (ReflectionUtils.isMultiple(method)) {
         arg.setType(ReflectionUtils.getComponentType(method.getParameters()[0])).setMultiValued(true);
      } else {
         Class<?> type = method.getParameters()[0].getType();
         arg.setType(type);
      }

      Hidden hidden = (Hidden)method.getAnnotation(Hidden.class);
      if (hidden != null) {
         arg.setHidden(true);
      }

      ConvertedBy convertedBy = (ConvertedBy)method.getAnnotation(ConvertedBy.class);
      if (convertedBy != null) {
         arg.setConverter((Converter)ReflectionUtils.newInstance(convertedBy.value()));
      }

      DefaultValue defaultValue = (DefaultValue)method.getAnnotation(DefaultValue.class);
      if (defaultValue != null) {
         arg.setDefaultValue(defaultValue.value());
      }

      return arg;
   }

   private static Object getOptionValue(Method method, String name, CommandLine commandLine) {
      io.vertx.core.cli.Option option = commandLine.cli().getOption(name);
      if (option == null) {
         return null;
      } else {
         boolean multiple = ReflectionUtils.isMultiple(method);
         return multiple ? createMultiValueContainer(method, commandLine.getOptionValues(name)) : commandLine.getOptionValue(name);
      }
   }

   private static Object getArgumentValue(Method method, int index, CommandLine commandLine) {
      io.vertx.core.cli.Argument argument = commandLine.cli().getArgument(index);
      if (argument == null) {
         return null;
      } else {
         boolean multiple = ReflectionUtils.isMultiple(method);
         return multiple ? createMultiValueContainer(method, commandLine.getArgumentValues(argument.getIndex())) : commandLine.getArgumentValue(argument.getIndex());
      }
   }

   public static void inject(CommandLine cli, Object object) throws CLIException {
      for(Method method : ReflectionUtils.getSetterMethods(object.getClass())) {
         Option option = (Option)method.getAnnotation(Option.class);
         Argument argument = (Argument)method.getAnnotation(Argument.class);
         if (option != null) {
            String name = option.longName();
            if (name == null) {
               name = option.shortName();
            }

            try {
               Object injected = getOptionValue(method, name, cli);
               if (injected != null) {
                  method.setAccessible(true);
                  method.invoke(object, injected);
               }
            } catch (Exception e) {
               throw new CLIException("Cannot inject value for option '" + name + "'", e);
            }
         }

         if (argument != null) {
            int index = argument.index();

            try {
               Object injected = getArgumentValue(method, index, cli);
               if (injected != null) {
                  method.setAccessible(true);
                  method.invoke(object, injected);
               }
            } catch (Exception e) {
               throw new CLIException("Cannot inject value for argument '" + index + "'", e);
            }
         }
      }

   }

   private static Object createMultiValueContainer(Method setter, List values) {
      Class<?> type = setter.getParameterTypes()[0];
      if (!type.isArray()) {
         if (Set.class.isAssignableFrom(type)) {
            return new LinkedHashSet(values);
         } else {
            return !List.class.isAssignableFrom(type) && !Collection.class.isAssignableFrom(type) ? null : values;
         }
      } else {
         Object array = Array.newInstance(type.getComponentType(), values.size());

         for(int i = 0; i < values.size(); ++i) {
            Array.set(array, i, values.get(i));
         }

         return array;
      }
   }
}
