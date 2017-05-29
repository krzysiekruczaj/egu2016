package com.ownedoutcomes.view

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.ownedoutcomes.Runner
import com.ownedoutcomes.logic.GameController
import com.ownedoutcomes.logic.currentGameLevel
import com.ownedoutcomes.logic.currentGamePoints
import ktx.actors.onChange
import ktx.actors.onClick
import ktx.assets.getValue
import ktx.assets.loadOnDemand
import ktx.inject.Context
import ktx.scene2d.button
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textButton

class Menu(context: Context, assetManager: AssetManager, stage: Stage) : AbstractView(stage) {
    val backgroundImage by assetManager.loadOnDemand<Texture>(path = "menu-background.png")
    override val root = table {
        setFillParent(true)
        backgroundImage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        background = TextureRegionDrawable(TextureRegion(backgroundImage, 0, 0, 1000, 750))
        button(style = "start") { cell ->
            onChange { event, button ->
                context.inject<GameController>().reload()
                context.inject<Runner>().setCurrentView(context, context.inject<Game>())
            }
            cell.expand().pad(100f).align(Align.bottomLeft)
        }
    }
}

class GameOver(context: Context, stage: Stage) : AbstractView(stage) {
    override val root = table {
        background = skin.getDrawable("gameover")
        setFillParent(true)
        defaults().align(Align.left).expandX()
        table {
            defaults().pad(40f)
            button(style = "play-again") { cell ->
                onClick { event, button ->
                    context.inject<GameController>().reload()
                    context.inject<Runner>().setCurrentView(context, context.inject<Game>())
                }
            }
            this.row()
            button(style = "to-menu") { cell ->
                onClick { event, button ->
                    context.inject<Runner>().setCurrentView(context, context.inject<Menu>())
                }
            }
        }
    }

    override fun show() {
        currentGameLevel = 1
        currentGamePoints = 0
        super.show()
    }
}

class NextLevel(context: Context, stage: Stage) : AbstractView(stage) {
    override val root = table {
        background = skin.getDrawable("background0")
        setFillParent(true)
        label(text = "Next level!", style = "game-over") {
            color = Color.FIREBRICK
        }
        row()
        textButton(text = "Go to next level!", style = "game-over-button") { cell ->
            pad(25f)
            color = Color.FIREBRICK
            onClick { event, button ->
                context.inject<GameController>().reload()
                context.inject<Runner>().setCurrentView(context, context.inject<Game>())
            }
            cell.padTop(15f)
        }
    }
}
