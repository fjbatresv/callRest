package com.internal.android.telephony;

interface ITelephony {
    boolean endCall();
    void answerRinginCall();
    void silenceRinger();
}