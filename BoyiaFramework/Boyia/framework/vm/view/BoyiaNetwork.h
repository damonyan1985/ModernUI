#ifndef BoyiaNetwork_h
#define BoyiaNetwork_h

#include "AppThread.h"
#include "BoyiaCore.h"
#include "NetworkBase.h"
#include "StringBuilder.h"

namespace boyia {
// BoyiaNetwork会根据回调的情况，自动释放自己
class BoyiaNetwork : public yanbo::NetworkClient, public yanbo::AppEvent {
public:
    BoyiaNetwork(BoyiaValue* callback, BoyiaValue* obj);
    virtual ~BoyiaNetwork();

public:
    void load(const String& url);

    virtual void onDataReceived(const LByte* data, LInt size);
    virtual void onStatusCode(LInt statusCode);
    virtual void onFileLen(LInt len);
    virtual void onRedirectUrl(const String& redirectUrl);
    virtual void onLoadError(LInt error);
    virtual void onLoadFinished();

    virtual LVoid run();

private:
    BoyiaValue m_callback;
    BoyiaValue m_obj;
    yanbo::StringBuilder m_builder;
    BoyiaPtr<String> m_data;
};
}
#endif
