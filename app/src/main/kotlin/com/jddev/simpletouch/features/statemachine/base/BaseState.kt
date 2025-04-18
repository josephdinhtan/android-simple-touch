package com.jddev.simpletouch.features.statemachine.base

interface BaseState<S> {
    fun handleEvent(event: BaseEvent): S
    fun onEnter()
    fun onExit()
}