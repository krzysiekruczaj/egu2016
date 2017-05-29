package com.ownedoutcomes.music

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.math.MathUtils
import ktx.assets.getAsset
import ktx.assets.loadOnDemand

enum class Theme(val id: String, val volumeModifier: Float) {
    _0("music/theme.ogg", 0.9f),
    _1("music/funkorama.ogg", 0.25f),
    _2("music/buySomething.ogg", 1f),
    _3("music/unfinishedBusiness.ogg", 0.25f),
}

fun playMusic(assetManager: AssetManager) {
    val theme = randomTheme()
    val music = assetManager.getAsset<Music>(theme.id)
    music.setOnCompletionListener {
        playMusic(assetManager)
    }
    music.volume = theme.volumeModifier * 0.5f
    music.play()
}

private fun randomTheme() = Theme.values()[MathUtils.random(0, Theme.values().size - 1)]

fun loadMusic(assetManager: AssetManager) {
    Theme.values().forEach { assetManager.loadOnDemand<Music>(it.id).asset }
}