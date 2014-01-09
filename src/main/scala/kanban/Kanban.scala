package kanban

import org.scalajs.dom
import org.scalajs.dom._

object Kanban {
    val d = dom.document
    
    def main(): Unit = {
        console.log("app initiated")
    }
    
    def createCard() {
        val card = d.createElement("div")
        card.className = "col-md-3"
        card.innerHTML = "div title"
        card.draggable = true
        card.ondragstart = (e: DragEvent) => dragFn(e, card.id)
        d.getElementById("todo").appendChild(card)
    }

    def dragFn(de: DragEvent, tid: String) = {
        console.log("there is a drag")
        de.dataTransfer.setData("asdf", tid)
    }
}
