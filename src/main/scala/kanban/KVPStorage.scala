
package kanban

import org.scalajs.dom
import org.scalajs.dom._

object KVPStorage {
    val storage = dom.localStorage
    def saveTask(task: Task) {
        storage.setItem(task.name, task.desc)
    }
}

case class Task(name: String, desc: String)

object Conversion {
    implicit def toTask(name: (String, String)): Task = Task(name._1, name._2)
    implicit def fromTask(task: Task) = (task.name, task.desc)
}

class TaskSaver {
    import Conversion.toTask

    def doStuff {
        KVPStorage.saveTask("fasdfs", "asdfas")
    }
}