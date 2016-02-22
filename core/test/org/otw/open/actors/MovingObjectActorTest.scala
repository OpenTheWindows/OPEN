package org.otw.open.actors

import com.badlogic.gdx.Screen
import org.mockito.Mockito
import org.otw.open.engine.impl.StaticAnimationEngine
import org.otw.open.screens.{EraserGameScreen, CauseAndEffectScreen}
import org.otw.open.testconfig.UnitSpec
import org.otw.open.{GameScreen, OpenGame}
import org.scalatest.BeforeAndAfterEach

/**
  * Created by smirakovska on 2/19/2016.
  */
class MovingObjectActorTest extends UnitSpec with BeforeAndAfterEach {

  var actor: MovingObjectActor = _

  override protected def beforeEach(): Unit = {
    actor = new MovingObjectActor
    OpenGame.changeScreen(new CauseAndEffectScreen)
  }

  test("isOnInitialPosition should return true when actor in inital state") {
    assert(actor.isOnInitialPosition)
  }

  test("isInMotion should return false when actor in inital state") {
    assert(!actor.isInMotion)
  }

  test("actorFinishedAllActions should return false when actor in inital state") {
    assert(!actor.actorFinishedAllActions)
  }

  test("incrementMissCount should increment missedCount for actor") {
    actor.incrementMissCount
    assert(actor.getObjectMissCount == 1)
  }

  test("decrementMissCount should increment missedCount for actor") {
    actor.incrementMissCount
    actor.incrementMissCount
    actor.decrementMissCount
    assert(actor.getObjectMissCount == 1)
  }

  test("isInMotion should return true is actor is moving") {
    actor.setX(30)
    assert(actor.isInMotion)
  }

  test("actorFinishedAllActions should return true when actor in end state and was moved 3 times") {
    actor.setX(1000)
    actor.move
    actor.move
    actor.move
    assert(actor.actorFinishedAllActions)
  }

  test("Unhappy animation should be shown when actor was missed 3 times") {
    actor.incrementMissCount
    actor.incrementMissCount
    actor.incrementMissCount

    val screen: Screen = OpenGame.getGame.getScreen
    assert(getGameScreen(screen).engine.isInstanceOf[StaticAnimationEngine])
  }

  def getGameScreen(screen: Screen) = screen match {
    case x: GameScreen => x
    case _ => throw new ClassCastException
  }
}