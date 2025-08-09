package org.apache.log4j.varia;

import java.io.InputStream;
import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class ReloadingPropertyConfigurator implements Configurator {
   PropertyConfigurator delegate = new PropertyConfigurator();

   public void doConfigure(final InputStream inputStream, final LoggerRepository repository) {
   }

   public void doConfigure(final URL url, final LoggerRepository repository) {
   }
}
