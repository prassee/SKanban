package kanban

import org.scalajs.dom
import org.scalajs.dom._

object Kanban {
  val d = dom.document

  def main(): Unit = {
    console.log("app initiated")
    val cols = List("Todo", "Doing", "Done")
    cols.foreach(col => {
      val outerCol = d.createElement("div")
      outerCol.className = "col-md-4"
      outerCol.innerHTML = s"<h3><span class='label label-default'>${col}</span></h3>"
      val innerCol = d.createElement("div")
      innerCol.id = col
      innerCol.className = "breadcrumb lane"
      innerCol.ondrop = (de: DragEvent) => ColumnEventHandlers.onDropFn(de)
      innerCol.ondragover = (de: DragEvent) => ColumnEventHandlers.ondragover(de)
      outerCol.appendChild(innerCol)
      d.getElementById("board").appendChild(outerCol)
    })
  }

  def createCard() {
    val x = ((d.getElementById("cname").asInstanceOf[HTMLInputElement]).value,
      (d.getElementById("cdesc").asInstanceOf[HTMLInputElement]).value)
    val card = d.createElement("div")
    card.className = "breadcrumb"
    card.draggable = true
    card.id = x._1
    // card.innerHTML = s"<ol class='breadcrumb'><li class='active'><b> ${x._1} </b></li></ol>"
    card.ondblclick = (e: MouseEvent) => {
      e.target
    }
    card.ondragstart = (e: DragEvent) => dragFn(e)
    val content = d.createElement("div")
    content.className = "caption"
    content.innerHTML = s"<p>${x._2}</p>"
    card.appendChild(content)
    d.getElementById("Todo").appendChild(card)
  }

  def dragFn(de: DragEvent) = {
    val targetId = (de.target.asInstanceOf[HTMLElement]).id
    console.log(s"there is a drag from id ${targetId}")
    de.dataTransfer.setData("Text", targetId)
  }
}

object ColumnEventHandlers {

  def onDropFn(de: DragEvent) = {
    val id = de.dataTransfer.getData("Text")
    (de.target.asInstanceOf[HTMLDivElement]).appendChild(Kanban.d.getElementById(id))
  }

  def ondragover(de: DragEvent) = {
    de.preventDefault()
    val targetEle = (de.target.asInstanceOf[HTMLDivElement])
    de.dataTransfer.setData("Text", targetEle.id)
  }
}