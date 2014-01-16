package skanban

import org.scalajs.dom
import org.scalajs.dom._

object SKanbanStorage {

    def saveLane(file: java.io.File, contents: String) {
        import java.io.FileWriter
        val fw = new FileWriter(file, false)
        fw.write(contents)
        fw.close()
    }
}

case class Task(name: String, desc: String)

object Conversion {
    implicit def toTask(name: (String, String)): Task = Task(name._1, name._2)
    implicit def fromTask(task: Task) = (task.name, task.desc)
    import java.io.File
    implicit def laneNameToFile(lane: String): File =
        new File(s"/home/prasanna/skanban/default/${lane}.txt")
}

object TaskSaver extends App {
    import Conversion._
    import java.util.Date
    SKanbanStorage.saveLane("todo", "asfsadf" + new Date())
}