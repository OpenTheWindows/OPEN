package org.otw.open.engine.impl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import org.otw.open.dto.Drawing
import org.otw.open.engine.Engine
import org.otw.open.engine.util.Animator

/**
  * An engine class used for displaying animation that should be static and placed in the center of the screen.
  * Created by jivanovski on 1/25/2016.
  */
class StaticAnimationEngine(val atlasFileName: String) extends Engine {
  val animator = new Animator(atlasFileName)
  val background = new Texture(Gdx.files.internal("street.png"))
  private var timePassed = 0f

  override def getDrawings(delta: Float): List[Drawing] = {
    timePassed = timePassed + delta
    List(new Drawing(background, 0, 0), new Drawing(animator.getCurrentTexture(timePassed), 464, 194))
  }

  override def dispose(): Unit = {
    animator.dispose()
    background.dispose()
  }
}