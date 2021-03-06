/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.aparapi.examples.configuration;

import com.aparapi.examples.mandel.Main;

public class AutoCleanUpArraysDemo {
   public static void main(String[] ignored) {

      System.setProperty("com.aparapi.dumpProfileOnExecution", "true");

      int size = 1024;
      int[] rgbs = new int[size * size];
      Main.MandelKernel kernel = new Main.MandelKernel(size, size, rgbs);
      kernel.setAutoCleanUpArrays(true);
      kernel.execute(size * size);
      System.out.println("length = " + kernel.getRgbs().length);
      kernel.resetImage(size, size, rgbs);
      kernel.execute(size * size);
      System.out.println("length = " + kernel.getRgbs().length);
   }
}
