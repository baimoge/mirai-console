/*
 * Copyright 2019-2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AFFERO GENERAL PUBLIC LICENSE version 3 license that can be found via the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

@file:Suppress("unused")

package net.mamoe.mirai.console.plugin

import net.mamoe.mirai.console.command.CommandOwner
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.disable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.description.PluginDescription
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalAPI
import java.io.File
import java.nio.file.Path

/**
 * 表示一个 mirai-console 插件.
 *
 * @see PluginManager.enable 启用一个插件
 * @see PluginManager.disable 禁用一个插件
 * @see PluginManager.description 获取一个插件的 [描述][PluginDescription]
 *
 * @see PluginDescription 插件描述， 需由 [PluginLoader] 帮助提供（[PluginLoader.description]）
 * @see JvmPlugin Java, Kotlin 或其他 JVM 平台插件
 * @see PluginFileExtensions 支持文件系统存储的扩展
 *
 * @see PluginLoader 插件加载器
 */
public interface Plugin : CommandOwner {
    /**
     * 判断此插件是否已启用
     *
     * @see PluginManager.enable 启用一个插件
     * @see PluginManager.disable 禁用一个插件
     */
    public val isEnabled: Boolean

    /**
     * 所属插件加载器实例, 此加载器必须能加载这个 [Plugin].
     */
    public val loader: PluginLoader<*, *>
}

/**
 * 支持文件系统存储的扩展.
 *
 * @see JvmPlugin
 */
@ConsoleExperimentalAPI("classname is subject to change")
public interface PluginFileExtensions {
    /**
     * 数据目录
     */
    public val dataFolderPath: Path

    /**
     * 从数据目录获取一个文件.
     * @see dataFolderPath
     */
    @JvmDefault
    public fun resolveDataFile(relativePath: String): File = dataFolderPath.resolve(relativePath).toFile()

    /**
     * 从数据目录获取一个文件.
     * @see dataFolderPath
     */
    @JvmDefault
    public fun resolveDataPath(relativePath: String): Path = dataFolderPath.resolve(relativePath)

    /**
     * 从数据目录获取一个文件.
     * @see dataFolderPath
     */
    @JvmDefault
    public fun resolveDataFile(relativePath: Path): File = dataFolderPath.resolve(relativePath).toFile()

    /**
     * 从数据目录获取一个文件路径.
     * @see dataFolderPath
     */
    @JvmDefault
    public fun resolveDataPath(relativePath: Path): Path = dataFolderPath.resolve(relativePath)
}