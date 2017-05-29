package com.ownedoutcomes.asset

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.assets.loadOnDemand
import ktx.style.*

fun loadSkin(assetManager: AssetManager) = skin(atlas = assetManager.loadOnDemand<TextureAtlas>(path = "skin.atlas").asset) {
    val bitmapFont = BitmapFont(Gdx.files.internal("font-export.fnt"), getRegion("font-export"), false)
    textButton("play-button") {
        font = bitmapFont
        up = it.getDrawable("button")
        over = it.getDrawable("button-pressed")
        down = it.getDrawable("button-pressed")
    }

    window("game-over") {
        titleFont = bitmapFont
    }

    textButton("game-over-button") {
        font = bitmapFont
        up = it.getDrawable("button")
        over = it.getDrawable("button-pressed")
        down = it.getDrawable("button-pressed")
    }

    label("game-over") {
        font = bitmapFont
    }

    label("points") {
        font = bitmapFont
    }

    button("start") {
        up = it.getDrawable("start-up")
        over = it.getDrawable("start-over")
        down = it.getDrawable("start-down")
    }

    button("to-menu") {
        up = it.getDrawable("to-menu")
    }

    button("play-again") {
        up = it.getDrawable("play-again")
    }
}
