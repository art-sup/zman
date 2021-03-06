package com.peterservice.zman.core.zookeeper.commands

import com.peterservice.zman.core.zookeeper.commands.CommandUtils.throwNotFoundException
import mu.KLogging
import org.apache.curator.framework.CuratorFramework

class DeleteCommand(private val client: CuratorFramework,
                    private val path: String) {

    private companion object : KLogging()

    fun execute() {
        client.checkExists().forPath(path) ?: throwNotFoundException(path)
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path)
    }

}
