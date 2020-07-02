package com.algro.resume.fragment


enum class DisposeOn {
    /**
     *  Will disposed when [AbstractFragment.onDestroy] is called
     **/
    DESTROYED,

    /**
     *  Default DisposeOn Mode @see [AbstractFragment.autoDispose]
     *  Will disposed when [AbstractFragment.onStop] is called
     **/
    STOP,

    /**
     *  Will disposed when [AbstractFragment.onPause] is called
     **/
    PAUSE,

    /**
     *  Will disposed when [AbstractFragment.onDestroyView] is called
     **/
    VIEW_DESTROYED,

    /**
     *  Will disposed when [AbstractFragment.onDetach] is called
     **/
    DETACHED
}