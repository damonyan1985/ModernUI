/*
 * BaseThread.h
 *
 *  Created on: 2015-7-29
 *      Author: yanbo
 */
#ifndef BaseThread_h
#define BaseThread_h

#include "Mutex.h"

namespace yanbo {

class Condition;
class BaseThread {
public:
    BaseThread();
    virtual ~BaseThread();

    void start();

    int wait();
    void waitOnNotify();
    void notify();
    void waitTimeOut(long time);
    int getId();

    static void sleepMS(long time);
    virtual void stop();
    virtual bool isAlive() { return false; };

protected:
    virtual void run() = 0;
    static void* startThread(void* ptr);

protected:
    Mutex m_lock;
    Condition* m_condition;
};
}
#endif
