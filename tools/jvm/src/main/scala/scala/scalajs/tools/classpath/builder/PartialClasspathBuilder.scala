/*                     __                                               *\
**     ________ ___   / /  ___      __ ____  Scala.js tools             **
**    / __/ __// _ | / /  / _ | __ / // __/  (c) 2013-2014, LAMP/EPFL   **
**  __\ \/ /__/ __ |/ /__/ __ |/_// /_\ \    http://scala-js.org/       **
** /____/\___/_/ |_/____/_/ | |__/ /____/                               **
**                          |/____/                                     **
\*                                                                      */


package scala.scalajs.tools.classpath.builder

import scala.scalajs.tools.classpath._
import scala.collection.immutable.Seq

/**
 * Allows to create a PartialClasspathBuilder from a (filesystem) classpath
 *
 * Rules for classpath reading:
 * - If top-level JS is present, all IR is ignored
 * - Top-level JS goes to scalaJSCode / IR goes to scalaJSIR
 * - If top-level JS is present, a PartialClasspath is created, otherwise a
 *   PartialIRClasspath is created
 * - Descends into JARs, no top-level JS in JARs
 * - Entries stay in order of ‘cp‘, IR remains unordered
 * - Earlier IR entries shadow later IR entries with the same relative path
 * - Non-top level JS goes to availableLibs (earlier libs take precedence)
 * - JS_DEPENDENCIES are added to dependencies
 */
class PartialClasspathBuilder extends AbstractPartialClasspathBuilder
                                 with PhysicalFileSystem

object PartialClasspathBuilder {
  /** Convenience method. The same as
   *
   *    (new PartialClasspathBuilder).build(cp)
   */
  def build(cp: Seq[java.io.File]): PartialClasspath =
    (new PartialClasspathBuilder).build(cp)

  /** Convenience method. The same as
   *
   *    (new PartialClasspathBuilder).buildIR(cp)
   */
  def buildIR(cp: Seq[java.io.File]): PartialIRClasspath =
    (new PartialClasspathBuilder).buildIR(cp)
}
