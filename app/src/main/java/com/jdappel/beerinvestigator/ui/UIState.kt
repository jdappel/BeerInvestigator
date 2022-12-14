package com.jdappel.beerinvestigator.ui

sealed class UIState<T> {
    class Unknown<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    data class Success<T>(val result: T) : UIState<T>()
    data class Error<T>(val throwable: Throwable) : UIState<T>()
}