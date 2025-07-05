package com.hmyh.moviejc.domain

/**
 * Created by Vincent on 2019-10-21
 */

abstract class UseCase<I, O> {
    fun execute(params: I): O = provide(params)
    protected abstract fun provide(params: I): O
}