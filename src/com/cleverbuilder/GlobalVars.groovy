#!/usr/bin/env groovy
package com.cleverbuilder

class GlobalVars {
   static String foo = "barn"

   // refer to this in a pipeline using:
   //
   // import com.cleverbuilder.GlobalVars
   // println GlobalVars.foo
}
