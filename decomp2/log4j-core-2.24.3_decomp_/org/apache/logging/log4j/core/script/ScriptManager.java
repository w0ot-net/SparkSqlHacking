package org.apache.logging.log4j.core.script;

import java.io.File;
import java.nio.file.Path;
import java.security.AccessController;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.util.FileWatcher;
import org.apache.logging.log4j.core.util.WatchManager;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;

public class ScriptManager implements FileWatcher {
   private static final String KEY_THREADING = "THREADING";
   private static final Logger logger = StatusLogger.getLogger();
   private final Configuration configuration;
   private final ScriptEngineManager manager = new ScriptEngineManager();
   private final ConcurrentMap scriptRunners = new ConcurrentHashMap();
   private final String languages;
   private final Set allowedLanguages;
   private final WatchManager watchManager;

   public ScriptManager(final Configuration configuration, final WatchManager watchManager, final String scriptLanguages) {
      this.configuration = configuration;
      this.watchManager = watchManager;
      List<ScriptEngineFactory> factories = this.manager.getEngineFactories();
      this.allowedLanguages = (Set)Arrays.stream(Strings.splitList(scriptLanguages)).map(Strings::toRootLowerCase).collect(Collectors.toSet());
      if (logger.isDebugEnabled()) {
         StringBuilder sb = new StringBuilder();
         int factorySize = factories.size();
         logger.debug("Installed {} script engine{}", factorySize, factorySize != 1 ? "s" : "");

         for(ScriptEngineFactory factory : factories) {
            String threading = Objects.toString(factory.getParameter("THREADING"), (String)null);
            if (threading == null) {
               threading = "Not Thread Safe";
            }

            StringBuilder names = new StringBuilder();
            List<String> languageNames = factory.getNames();

            for(String name : languageNames) {
               if (this.allowedLanguages.contains(Strings.toRootLowerCase(name))) {
                  if (names.length() > 0) {
                     names.append(", ");
                  }

                  names.append(name);
               }
            }

            if (names.length() > 0) {
               if (sb.length() > 0) {
                  sb.append(", ");
               }

               sb.append(names);
               boolean compiled = factory.getScriptEngine() instanceof Compilable;
               logger.debug("{} version: {}, language: {}, threading: {}, compile: {}, names: {}, factory class: {}", factory.getEngineName(), factory.getEngineVersion(), factory.getLanguageName(), threading, compiled, languageNames, factory.getClass().getName());
            }
         }

         this.languages = sb.toString();
      } else {
         StringBuilder names = new StringBuilder();

         for(ScriptEngineFactory factory : factories) {
            for(String name : factory.getNames()) {
               if (this.allowedLanguages.contains(Strings.toRootLowerCase(name))) {
                  if (names.length() > 0) {
                     names.append(", ");
                  }

                  names.append(name);
               }
            }
         }

         this.languages = names.toString();
      }

   }

   public Set getAllowedLanguages() {
      return this.allowedLanguages;
   }

   public boolean addScript(final AbstractScript script) {
      if (this.allowedLanguages.contains(Strings.toRootLowerCase(script.getLanguage()))) {
         ScriptEngine engine = this.manager.getEngineByName(script.getLanguage());
         if (engine == null) {
            logger.error("No ScriptEngine found for language " + script.getLanguage() + ". Available languages are: " + this.languages);
            return false;
         } else {
            if (engine.getFactory().getParameter("THREADING") == null) {
               this.scriptRunners.put(script.getName(), new ThreadLocalScriptRunner(script));
            } else {
               this.scriptRunners.put(script.getName(), new MainScriptRunner(engine, script));
            }

            if (script instanceof ScriptFile) {
               ScriptFile scriptFile = (ScriptFile)script;
               Path path = scriptFile.getPath();
               if (scriptFile.isWatched() && path != null) {
                  this.watchManager.watchFile(path.toFile(), this);
               }
            }

            return true;
         }
      } else {
         logger.error("Unable to add script {}, {} has not been configured as an allowed language", script.getName(), script.getLanguage());
         return false;
      }
   }

   public Bindings createBindings(final AbstractScript script) {
      return this.getScriptRunner(script).createBindings();
   }

   public AbstractScript getScript(final String name) {
      ScriptRunner runner = (ScriptRunner)this.scriptRunners.get(name);
      return runner != null ? runner.getScript() : null;
   }

   public void fileModified(final File file) {
      ScriptRunner runner = (ScriptRunner)this.scriptRunners.get(file.toString());
      if (runner == null) {
         logger.info("{} is not a running script", file.getName());
      } else {
         ScriptEngine engine = runner.getScriptEngine();
         AbstractScript script = runner.getScript();
         if (engine.getFactory().getParameter("THREADING") == null) {
            this.scriptRunners.put(script.getName(), new ThreadLocalScriptRunner(script));
         } else {
            this.scriptRunners.put(script.getName(), new MainScriptRunner(engine, script));
         }

      }
   }

   public Object execute(final String name, final Bindings bindings) {
      ScriptRunner scriptRunner = (ScriptRunner)this.scriptRunners.get(name);
      if (scriptRunner == null) {
         logger.warn("No script named {} could be found", name);
         return null;
      } else {
         return AccessController.doPrivileged(() -> scriptRunner.execute(bindings));
      }
   }

   private ScriptRunner getScriptRunner(final AbstractScript script) {
      return (ScriptRunner)this.scriptRunners.get(script.getName());
   }

   private abstract class AbstractScriptRunner implements ScriptRunner {
      private static final String KEY_STATUS_LOGGER = "statusLogger";
      private static final String KEY_CONFIGURATION = "configuration";

      private AbstractScriptRunner() {
      }

      public Bindings createBindings() {
         SimpleBindings bindings = new SimpleBindings();
         bindings.put("configuration", ScriptManager.this.configuration);
         bindings.put("statusLogger", ScriptManager.logger);
         return bindings;
      }
   }

   private class MainScriptRunner extends AbstractScriptRunner {
      private final AbstractScript script;
      private final CompiledScript compiledScript;
      private final ScriptEngine scriptEngine;

      public MainScriptRunner(final ScriptEngine scriptEngine, final AbstractScript script) {
         this.script = script;
         this.scriptEngine = scriptEngine;
         CompiledScript compiled = null;
         if (scriptEngine instanceof Compilable) {
            ScriptManager.logger.debug("Script {} is compilable", script.getName());
            compiled = (CompiledScript)AccessController.doPrivileged(() -> {
               try {
                  return ((Compilable)scriptEngine).compile(script.getScriptText());
               } catch (Throwable ex) {
                  ScriptManager.logger.warn("Error compiling script", ex);
                  return null;
               }
            });
         }

         this.compiledScript = compiled;
      }

      public ScriptEngine getScriptEngine() {
         return this.scriptEngine;
      }

      public Object execute(final Bindings bindings) {
         if (this.compiledScript != null) {
            try {
               return this.compiledScript.eval(bindings);
            } catch (ScriptException ex) {
               ScriptManager.logger.error("Error running script " + this.script.getName(), ex);
               return null;
            }
         } else {
            try {
               return this.scriptEngine.eval(this.script.getScriptText(), bindings);
            } catch (ScriptException ex) {
               ScriptManager.logger.error("Error running script " + this.script.getName(), ex);
               return null;
            }
         }
      }

      public AbstractScript getScript() {
         return this.script;
      }
   }

   private class ThreadLocalScriptRunner extends AbstractScriptRunner {
      private final AbstractScript script;
      private final ThreadLocal runners = new ThreadLocal() {
         protected MainScriptRunner initialValue() {
            ScriptEngine engine = ScriptManager.this.manager.getEngineByName(ThreadLocalScriptRunner.this.script.getLanguage());
            return ScriptManager.this.new MainScriptRunner(engine, ThreadLocalScriptRunner.this.script);
         }
      };

      public ThreadLocalScriptRunner(final AbstractScript script) {
         this.script = script;
      }

      public Object execute(final Bindings bindings) {
         return ((MainScriptRunner)this.runners.get()).execute(bindings);
      }

      public AbstractScript getScript() {
         return this.script;
      }

      public ScriptEngine getScriptEngine() {
         return ((MainScriptRunner)this.runners.get()).getScriptEngine();
      }
   }

   private interface ScriptRunner {
      Bindings createBindings();

      Object execute(Bindings bindings);

      AbstractScript getScript();

      ScriptEngine getScriptEngine();
   }
}
