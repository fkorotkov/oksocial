package com.baulsupp.oksocial.kotlin;

import java.io.File
import java.net.URLClassLoader
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.script.ScriptContext
import javax.script.ScriptEngineFactory
import kotlin.reflect.KClass

class KotlinAppScriptEngine(
    disposable: Disposable,
    factory: ScriptEngineFactory,
    val templateClasspath: List<File>,
    templateClassName: String,
    val getScriptArgs: (ScriptContext, Array<out KClass<out Any>>?) -> ScriptArgsWithTypes?,
    val scriptArgsTypes: Array<out KClass<out Any>>?
) : KotlinJsr223JvmScriptEngineBase(factory), KotlinJsr223JvmInvocableScriptEngine {

  override val replCompiler: ReplCompiler by lazy {
    GenericReplCompiler(
        disposable,
        makeScriptDefinition(templateClasspath, templateClassName),
        makeCompilerConfiguration(),
        PrintingMessageCollector(System.out, MessageRenderer.WITHOUT_PATHS, false))
  }
  // TODO: bindings passing works only once on the first eval, subsequent setContext/setBindings call have no effect. Consider making it dynamic, but take history into account
  private val localEvaluator by lazy { GenericReplCompilingEvaluator(replCompiler, templateClasspath, Thread.currentThread().contextClassLoader, getScriptArgs(getContext(), scriptArgsTypes)) }

  override val replEvaluator: ReplFullEvaluator get() = localEvaluator

  override val state: IReplStageState<*> get() = getCurrentState(getContext())

  override fun createState(lock: ReentrantReadWriteLock): IReplStageState<*> = replEvaluator.createState(lock)

  override fun overrideScriptArgs(context: ScriptContext): ScriptArgsWithTypes? = getScriptArgs(context, scriptArgsTypes)

  private fun makeScriptDefinition(templateClasspath: List<File>, templateClassName: String): KotlinScriptDefinition {
    val classloader = URLClassLoader(templateClasspath.map { it.toURI().toURL() }.toTypedArray(), this.javaClass.classLoader)
    val cls = classloader.loadClass(templateClassName)
    return KotlinScriptDefinitionFromAnnotatedTemplate(cls.kotlin, null, null, emptyMap())
  }

  private fun makeCompilerConfiguration() = CompilerConfiguration().apply {
    addJvmClasspathRoots(PathUtil.getJdkClassesRootsFromCurrentJre())
    addJvmClasspathRoots(templateClasspath)
    put(CommonConfigurationKeys.MODULE_NAME, "kotlin-script")
    languageVersionSettings = LanguageVersionSettingsImpl(
        LanguageVersion.LATEST_STABLE, ApiVersion.LATEST_STABLE, mapOf(AnalysisFlag.skipMetadataVersionCheck to true)
    )
  }
}