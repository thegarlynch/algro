package com.algro.resume.helper.activity

enum class DisposeOn {

    /**
     *  Will disposed when [AbstractActivity.onDestroy] is called
     *
     *  @see [AbstractActivity.onDestroy]
     */
    DESTROYED,

    /**
     *  Default DisposeOn Mode @see [AbstractActivity.autoDispose]
     *  Will disposed when [AbstractActivity.onStop] is called
     *
     *  @see [AbstractActivity.onStop]
     */
    STOP,

    /**
     *  Will disposed when [AbstractActivity.onPause] is called
     *
     *  @see [AbstractActivity.onPause]
     */
    PAUSE

}