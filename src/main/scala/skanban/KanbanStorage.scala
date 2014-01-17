package skanban

import org.scalajs.dom
import org.scalajs.dom._

object SKanbanStorage {
    val appstorage:Storage = localStorage
    def saveLane(file: java.io.File, contents: String) {
        import java.io.FileWriter
        val fw = new FileWriter(file, false)
        fw.write(contents)
        fw.close()
    }

    def saveToStorage(key: String, data: String) {
        // appstorage(0)
        val path = this.getClass.getResource(s"/default/${key}.txt").getPath()
    }
}

case class Task(name: String, desc: String)

object Conversion {
    implicit def toTask(name: (String, String)): Task = Task(name._1, name._2)
    implicit def fromTask(task: Task) = (task.name, task.desc)
    import java.io.File
    implicit def laneNameToFile(lane: String): File = {
        console.log(s"${lane}")
        val path = this.getClass.getResource(s"/default/${lane}.txt").getPath()
        // "/home/prasanna/skanban/default/${lane}.txt"
        new File(path)
    }
    import org.scalajs.dom
    implicit def toElement(id: String): HTMLElement = dom.document.getElementById(id)
}

object TaskSaver extends App {
    import Conversion._
    import java.util.Date
    SKanbanStorage.saveLane("todo", "asfsadf" + new Date())
}