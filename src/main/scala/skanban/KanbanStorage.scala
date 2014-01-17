package skanban

import org.scalajs.dom
import org.scalajs.dom._
import scala.scalajs.js

object SKanbanStorage {
    val appstorage = window.localStorage

    def saveLane(file: java.io.File, contents: String) {
        import java.io.FileWriter
        val fw = new FileWriter(file, false)
        fw.write(contents)
        fw.close()
    }

    def saveToStorage(key: String, data: String) {
        console.log(s"value in  ${key} is ${data}")
        appstorage.update(key, data)
        console.log(s"")
        console.log(s"todo has ${appstorage("Todo")}")
        console.log(s"doing has ${appstorage("Doing")}")
        console.log(s"done has ${appstorage("Done")}")
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