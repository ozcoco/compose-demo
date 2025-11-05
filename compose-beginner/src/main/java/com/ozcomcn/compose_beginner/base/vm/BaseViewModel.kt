package com.ozcomcn.compose_beginner.base.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * BaseViewModel 是一个抽象类，用于实现 MVI 架构中的 ViewModel 层。
 * 它定义了 Intent、State、Effect 三种类型，分别对应于用户操作、状态变化和副作用。
 * 子类需要实现 initState、onIntent、updateState、postEffect 这四个方法。
 *
 * @param Intent 用户操作的类型，用于触发状态变化和副作用。
 * @param State 状态的类型，用于表示当前视图的状态。
 * @param Effect 副作用的类型，用于表示对外部环境的影响。
 */
abstract class BaseViewModel<Intent, State, Effect> : ViewModel() {

    /**
     * 状态流，用于表示当前视图的状态。
     */
    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(initState()) }

    val state: StateFlow<State> get() = _state.asStateFlow()

    /**
     * 副作用流，用于表示对外部环境产生的影响。
     */
    private val _effect: MutableSharedFlow<Effect> by lazy { MutableSharedFlow() }
    val effect: SharedFlow<Effect> get() = _effect.asSharedFlow()

    /**
     * 初始化状态。
     *
     * @return 初始状态。
     */
    protected abstract fun initState(): State

    /**
     * 处理用户操作。
     *
     * @param intent 用户操作的意图。
     */
    abstract fun onIntent(intent: Intent)

    /**
     * 更新状态。
     *
     * @param reducer 状态 reducer，用于更新状态。
     */
    protected fun updateState(reducer: State.() -> State) {
        _state.update(reducer)
    }
    
    /**
     * 发布副作用。
     *
     * @param builder 副作用构建器，用于创建副作用。
     */
    protected fun postEffect(builder: () -> Effect) {
        viewModelScope.launch {
            _effect.emit(builder())
        }
    }

}