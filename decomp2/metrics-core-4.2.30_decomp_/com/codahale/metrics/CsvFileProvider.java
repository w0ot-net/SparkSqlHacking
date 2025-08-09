package com.codahale.metrics;

import java.io.File;

public interface CsvFileProvider {
   File getFile(File directory, String metricName);
}
